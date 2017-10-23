package uber;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Uber {
	private static final int GRID_SIZE = 10;
	/*private static final String ENTRY_DELIMITER = ", ";
	private static final String CATEGORY_DELIMITER = ": ";
	private static final int CATEGORY_LOC = 0;
	private static final int VALUE_LOC = 1;
	*/private static int nextUserID;
	
	private User[][] grid;
	private int rows;
	private int cols;
	private HashMap<Integer, Driver> drivers;
	private HashMap<Integer, Customer> customers;
	
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
	
	public int getGridRows() {
	   return this.rows;
	}
	
	public int getGridCols() {
	   return this.cols;
	}
	
	/*public void addDriver(Driver newDriver) {
	   
	}
	
	public void addCustomer(Customer newCustomer) {
	   
	}*/
	
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
	
	public boolean hasDrivers() {
	   if (getDrivers() == null) {
	      return false;
	   }
	   
	   if (getDrivers().size() == 0) {
	      return false;
	   }
	   
	   return true;
	}
	
	public HashMap<Integer, Driver> getDrivers() {
	   return this.drivers;
	}
	  
   public HashMap<Integer, Customer> getCustomers() {
      return this.customers;
   }
	
	public void initGrid(String filename) throws FileNotFoundException {
	   File inputFile = new File(filename);
	   Scanner in = new Scanner(inputFile);
	   Random rand = new Random();
	   int row, col;

	   // TODO: does she want a Car class, or a title..?
	   
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
	
	public void setLocation(User user, Location destination) {
	   user.setLocation(destination);
	   grid[user.getLocation().getRow()][user.getLocation().getCol()] = null;
      grid[destination.getRow()][destination.getCol()] = user;
	}
	
	/*private static HashMap parseProperties(String properties) {
      String[] entry;
      UserProperty property;
      HashMap newMap = new HashMap();
	   
	   Scanner in = new Scanner(properties);
	   in.useDelimiter(ENTRY_DELIMITER);
	   
	   while (in.hasNext()) {
         
         entry = in.next().split(CATEGORY_DELIMITER);
         property = getCategory(entry[CATEGORY_LOC]);
        
         if (property == null) {
            System.out.println("Entry " + entry[CATEGORY_LOC] + 
                  " not found. Skipping");
            continue;
         }
         
         newMap.put(property, entry[VALUE_LOC]);
      }
      
      in.close();
      return newMap;
	}*/
	
	/*public static AccountType getAccountType(HashMap map) {
	   String accountType = (String)map.get(UserProperty.ACCOUNT);
	   return AccountType.valueOf(accountType.toUpperCase());
	}*/
	
	// TODO: split into different functions
	private void placeUser(String properties, Location loc) {
	   HashMap newMap = UberHelper.parseProperties(properties);
	   User newUser;
	   
      /* TODO: UGH */
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
	
	/*public static UserProperty getCategory(String input) {
	   for (UserProperty p : UserProperty.values()) {
	      if (p.name().equals(input)) {
	         return p;
	      }
	   }
	   
	   return null;
	}*/
	
	public void printGrid() {
	   int x, y;
	   int rows = getGridRows();
	   int cols = getGridCols();
	   // TODO: move??
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
