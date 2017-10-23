package uber;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Uber system. Contains the grid containing all of the users in the 
 * system, keeps track of the Drivers and Customers in the system, and 
 * controls the movements of these Users in the grid.
 * @author FaithChan
 *
 */
public class Uber {
	private static final int GRID_SIZE = 10;
	private static int nextUserID;
	
	private User[][] grid;
	private int rows;
	private int cols;
	private HashMap<Integer, Driver> drivers;
	private HashMap<Integer, Customer> customers;
	
	/**
	 * Uber constructor. Uses the input parameters to create a grid of the 
	 * appropriate size; will default to a predetermined value if negative.
	 * Initializes the HashMaps of Customers and Drivers.
	 * @param rows The number of rows the grid will have.
	 * @param cols The number of columns the grid will have.
	 */
	public Uber(int rows, int cols) {
	   this.rows = rows;
      this.cols = cols;
	   
	   if (rows < 0) {
	      this.rows = GRID_SIZE;
	   } 
	   
	   if (cols < 0) {
	      this.cols = GRID_SIZE;
	   } 
	   
	   this.grid = new User[this.rows][this.cols];	   
	   this.drivers = new HashMap<Integer, Driver>();
	   this.customers = new HashMap<Integer, Customer>();
	   this.nextUserID = 0;
	}
	
	/**
	 * Gets the number of rows in the grid.
	 * @return The integer number of rows.
	 */
	public int getGridRows() {
	   return this.rows;
	}
	
	/**
	 * Gets the number of columns in the grid.
	 * @return The integer number of columns.
	 */
	public int getGridCols() {
	   return this.cols;
	}
	
	/**
	 * Checks if the paramter Location loc is within the bounds of the Uber
	 * grid.
	 * @param loc The Location being checked
	 * @return true if the location is within bounds; false if not.
	 */
	public boolean withinGrid(Location loc) {
	   int row = loc.getRow();
	   int col = loc.getCol();
	   
	   if ((row < 0) || (row >= getGridRows())) {
	      return false;
	   }
	   
	   if ((col < 0) || (col >= getGridCols())) {
         return false;
      }
	   
	   return true;
	}
	
	/**
	 * Checks if there are any drivers in the Uber system.
	 * @return false if there are none; true otherwise.
	 */
	public boolean hasDrivers() {
	   if (getDrivers() == null) {
	      return false;
	   }
	   
	   if (getDrivers().size() == 0) {
	      return false;
	   }
	   
	   return true;
	}
	
	/**
	 * Gets the HashMap of drivers held by Uber.
	 * @return A HashMap of Drivers, mapped to their IDs.
	 */
	public HashMap<Integer, Driver> getDrivers() {
	   return this.drivers;
	}
	  
	/**
	 * Gets the HashMap of Customers on Uber.
	 * @return A HashMap of Customers, mapped to their IDs.
	 */
   public HashMap<Integer, Customer> getCustomers() {
      return this.customers;
   }
	
   /**
    * Initializes the Uber grid using the Users described in the input file.
    * @param filename The name of the input file.
    * @throws FileNotFoundException
    */
	public void initGrid(String filename) throws FileNotFoundException {
	   File inputFile = new File(filename);
	   Scanner in = new Scanner(inputFile);
	   Random rand = new Random();
	   int row, col;

	   // Get each user (each line in the input file)
	   while (in.hasNextLine()) {
	      do {
	         row = rand.nextInt(this.rows);
	         col = rand.nextInt(this.cols);
	      } while (grid[row][col] != null);
   	   
   	   placeUser(in.nextLine(), new Location(row, col));
	   }
	   
	   in.close();
	   printGrid();
	}
	
	/**
	 * Sets the location of the user to the parameter Location destination, and
	 * updates the state of the grid.
	 * @param user The user whose position is to be changed.
	 * @param destination The new location of the user.
	 */
	public void setLocation(User user, Location destination) {
	   grid[user.getLocation().getRow()][user.getLocation().getCol()] = null;
	   user.setLocation(destination);
      grid[destination.getRow()][destination.getCol()] = user;
	}
	
	/**
	 * Places the user in the Uber grid. Adds them to the appropriate lists--
	 * Customer or Driver.
	 * @param properties The string to be parsed for properties.
	 * @param loc The location at which to place the user.
	 */
	private void placeUser(String properties, Location loc) {
	   HashMap newMap = UberHelper.parseProperties(properties);
	   User newUser;
	   
      if (UberHelper.getAccountType(newMap).equals(AccountType.DRIVER)) {
         newUser = new Driver(newMap);
         drivers.put(nextUserID, (Driver)newUser);
      } else {
         newUser = new Customer(newMap);
         customers.put(nextUserID, (Customer)newUser);
      }
      
      // Set other user information
      newUser.setID(nextUserID);
      nextUserID++;
      
      newUser.setLocation(loc);

      // Place user in grid
      grid[newUser.getLocation().getRow()][newUser.getLocation().getCol()] = 
            newUser;
	}
	
	/**
	 * Helper function to print the grid.
	 */
	public void printGrid() {
	   int x, y;
	   int rows = getGridRows();
	   int cols = getGridCols();
	   
	   final String STR_FORMAT = "%5s";
	   final String INT_FORMAT = "%5d";
	   final String CUSTOMER_STR = "C";
	   final String DRIVER_STR = "D";
	   
	   /* Print column headers */
	   System.out.printf(STR_FORMAT, "");
	   
	   for (x = 0; x < cols; x++) {
	      System.out.printf(INT_FORMAT, x);
	   }
	   
	   System.out.println();
	   
	   for (y = 0; y < rows; y++) {
	      /* Print row header */
	      System.out.printf(INT_FORMAT, y);
	      
	      for (x = 0; x < cols; x++) {
	         
            if (grid[y][x] == null) {
               System.out.printf(STR_FORMAT, "-");
            }
            else {
               User user = grid[y][x];
               HashMap map = user.getProperties();
               
               if (UberHelper.getAccountType(map).equals(AccountType.DRIVER)) {
                  System.out.printf(STR_FORMAT, user.getID() + DRIVER_STR);
               } else {
                  System.out.printf(STR_FORMAT, user.getID() + CUSTOMER_STR);
               }
             
            }
         }
         
         System.out.println();
      }
	}
}
