package uber2;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;

public class Uber {
	private User[][] grid;
	private static int GRID_SIZE = 300;
	private PriorityQueue<Driver> drivers;
	
	public Uber(int size) {
	   if (size < 0) {
	      grid = new User[GRID_SIZE][GRID_SIZE];
	   } else {
	      grid = new User[size][size];
	   }
	}
	
	public Driver findDriver() {
		return null;
	}
	
	public void initGrid(String filename) throws FileNotFoundException {
	   File inputFile = new File(filename);
	   Scanner in = new Scanner(inputFile);
	   
	   // TODO: ask about COLLISIONS?
	   //enum for different users and their properties
	   placeDrivers(in);
	   placePassengers(in);
	}
	
	private void placeDrivers(Scanner in) {
	   
	}
	
	private void placePassengers(Scanner in) {
	   
	}
}
