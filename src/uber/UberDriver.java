package uber;

import java.io.FileNotFoundException;

public class UberDriver {
	public static void main(String[] args) {
		final String INPUT_FILE = "./Input1.txt";

		Uber system = new Uber(/*-1, -1*/25, 20);
		RequestHandler handler = new RequestHandler(system);
		Request r0, r1, r2, r3, r4;
		
		try {
		   system.initGrid(INPUT_FILE);
		} catch (FileNotFoundException e) {
		   System.err.println("Input file not found");
		   e.printStackTrace();
		   System.exit(0);
		}
		
		r0 = new Request(null, new Location(100, 100), 1);
		r0 = new Request(null, new Location(100, 100), 1);
		r0 = new Request(null, new Location(100, 100), 1);
		r0 = new Request(null, new Location(100, 100), 1);
		r0 = new Request(null, new Location(100, 100), 1);

		// Simulate all the random stuff
		//handler.processRequest(u1, newRequest);
		//system.printGrid();
	}
}
