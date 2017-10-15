package uber2;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

public class Uber {
	private static int GRID_SIZE = 10;//300;
	private static String ENTRY_DELIMITER = ", ";
	private static String CATEGORY_DELIMITER = ": ";
	private static int CATEGORY_LOC = 0;
	private static int VALUE_LOC = 1;
	
	private User[][] grid;
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
	   Random rand = new Random();
	   int x, y;
	   
	   // TODO: ask about COLLISIONS?
	   // TODO: ask about using HashMap rather than Map?? (will there ever be another map?)
	   // TODO: does she want a Car class, or a title..?
	   //enum for different users and their properties
	   
	   // Get each user (each line in the input file)
	   while (in.hasNextLine()) {
   	   x = rand.nextInt(GRID_SIZE);
   	   y = rand.nextInt(GRID_SIZE);
   	
   	   placeUser(in.nextLine(), new Location(x, y));
	   }
	}
	
	private void placeUser(String properties, Location loc) {
	   User newUser;
	   HashMap newMap;
	   
	   Scanner in = new Scanner(properties);
	   UserProperty property;
	   String[] entry;
	   
	   in.useDelimiter(ENTRY_DELIMITER);
	   
	   // Loop through the categories for a user (one line)
	   while (in.hasNext()) {
	      entry = in.next().split(CATEGORY_DELIMITER);
	      property = getCategory(entry[CATEGORY_LOC]);
	     
	      if (property == null) {
	         System.out.println("Entry " + entry[CATEGORY_LOC] + 
	               " not found. Skipping");
	         continue;
	      }
	      
	      newMap = new HashMap();
	      newMap.put(property, entry[VALUE_LOC]);
	      
	      newUser = new User(newMap);
	      
	      // TODO: Do i really need this??
	      newUser.setLocation(loc);
	      
	      // Need new method?
	      grid[newUser.getLocation().getx()][newUser.getLocation().gety()] = 
	            newUser;
	   }
	}
	
	private UserProperty getCategory(String input) {
	   for (UserProperty p : UserProperty.values()) {
	      if (p.name().equals(input)) {
	         return p;
	      }
	   }
	   
	   return null;
	}
	
	public void printGrid() {
	   int x, y;
	   
	   for (y = 0; y < GRID_SIZE; y++) {
	      for (x = 0; x < GRID_SIZE; x++) {
            if (grid[x][y] == null) {
               System.out.print("null ");
            }
            else {
               System.out.print("USER ");
            }
         }
         
         System.out.println();
      }
	}
}
