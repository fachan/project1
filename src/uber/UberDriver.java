package uber;

import java.io.FileNotFoundException;

public class UberDriver {
   static final String INPUT_FILE = "./Input1.txt";
	static final double RATE = 0.75;
	
	public static void main(String[] args) {
		Uber system = new Uber(/*-1, -1*/25, 20);
		RequestHandler handler = new RequestHandler(system);
		Meter meter = new Meter(handler, RATE);
		
		Request r10, r11, r12, r13, r14;
		
		try {
		   system.initGrid(INPUT_FILE);
		} catch (FileNotFoundException e) {
		   System.err.println("Input file not found");
		   e.printStackTrace();
		   System.exit(0);
		}
		
		r10 = new Request(10, null, new Location(100, 100), 1);
		r11 = new Request(11, null, new Location(20, 15), 1);
		r12 = new Request(12, null, new Location(10, 1), 1);
		r13 = new Request(13, null, new Location(0, 0), 1);
		r14 = new Request(14, null, new Location(2, 5), 1);

		// Simulate all the random stuff
		handler.processRequest(r10);
		handler.processRequest(r11);
		handler.processRequest(r12);
		handler.processRequest(r13);
		handler.processRequest(r14);
		//system.printGrid();
	}
}
