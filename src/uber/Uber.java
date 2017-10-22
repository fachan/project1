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
	private static final String ENTRY_DELIMITER = ", ";
	private static final String CATEGORY_DELIMITER = ": ";
	private static final int CATEGORY_LOC = 0;
	private static final int VALUE_LOC = 1;
	private static int nextUserID;
	
	private User[][] grid;
	private int rows;
	private int cols;
	private ArrayList<Driver> drivers;
	
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
	   this.drivers = new ArrayList<Driver>();
	   this.nextUserID = 0;
	}
	
	public int getGridRows() {
	   return this.rows;
	}
	
	public int getGridCols() {
	   return this.cols;
	}
	
	public void addDriver(Driver newDriver) {
	   
	}
	
	public void addCustomer(Customer newCustomer) {
	   
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
	
	public ArrayList<Driver> getDrivers() {
	   //for (Iterator i = this.drivers; )
	   
	   return this.drivers;
	}
	
	public Driver findDriver() {
	   //while driver not found (end of queue not reached)s
	   //try to find driver
		return null;
	}
	
	public void initGrid(String filename) throws FileNotFoundException {
	   File inputFile = new File(filename);
	   Scanner in = new Scanner(inputFile);
	   Random rand = new Random();
	   int row, col;
	   User newUser = null; /**/
	   
	   // TODO: ask about COLLISIONS? (can have if you want)
	   // TODO: ask about using HashMap rather than Map?? (will there ever be another map?)
	   // TODO: does she want a Car class, or a title..?
	   //enum for different users and their properties
	   
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
	
	private HashMap parseProperties(String properties) {
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
	}
	
	public static AccountType getAccountType(HashMap map) {
	   String accountType = (String)map.get(UserProperty.ACCOUNT);
	   return AccountType.valueOf(accountType.toUpperCase());
	}
	
	// TODO: split into different functions
	private void placeUser(String properties, Location loc) {
	   HashMap newMap = parseProperties(properties);
	   User newUser;
	   
      /* TODO: UGH */
      if (Uber.getAccountType(newMap).equals(AccountType.DRIVER)) {
         newUser = new Driver(newMap);
         drivers.add((Driver)newUser);
      } else {
         newUser = new Customer(newMap);
      }
      
      // Set other user information
      newUser.setID(nextUserID);
      nextUserID++;
      
      newUser.setLocation(loc);

      // Place user in grid
      grid[newUser.getLocation().getRow()][newUser.getLocation().getCol()] = 
            newUser;
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
	   
	   for (y = 0; y < this.rows; y++) {
	      for (x = 0; x < this.cols; x++) {
            if (grid[y][x] == null) {
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
