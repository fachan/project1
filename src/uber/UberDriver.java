package uber;

import java.io.FileNotFoundException;

public class UberDriver {
	public static void main(String[] args) {
		final String INPUT_FILE = "./Input1.txt";

		Uber system = new Uber(/*-1, -1*/5, 10);
		RequestHandler handler = new RequestHandler(system);
		Location src = new Location(0, 0);
		Location dest = new Location(100, 100);
		Request newRequest = new Request(src, dest, 1);
		
		try {
		   system.initGrid(INPUT_FILE);
		} catch (FileNotFoundException e) {
		   System.err.println("Input file not found");
		   e.printStackTrace();
		   System.exit(0);
		}
		
		// Simulate all the random stuff
		//handler.processRequest(u1, newRequest);
		//system.printGrid();
	}
}
