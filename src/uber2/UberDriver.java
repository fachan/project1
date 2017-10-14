package uber2;

import java.io.FileNotFoundException;

public class UberDriver {
	public static void main(String[] args) {
		final String INPUT_FILE = "./Input1.txt";

		Uber system = new Uber(-1);
		
		try {
		   system.initGrid(INPUT_FILE);
		} catch (FileNotFoundException e) {
		   System.err.println("Input file not found");
		   e.printStackTrace();
		}
		
		
	}
}
