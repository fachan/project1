package uber;

import java.lang.Math;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Represents a location in the grid.
 * @author FaithChan
 *
 */
public class Location {
	private int row;
	private int col;
	
	/**
	 * Constructor.
	 * @param row The row in the grid.
	 * @param col The column in the grid.
	 */
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
	   return this.row;
	}
	
	public int getCol() {
	   return this.col;
	}
	
	/**
	 * Gets the distance between this object and another Location, called 
	 * compareTo.
	 * @param compareTo The Location to compare this object to.
	 * @return the distance between the two Locations.
	 */
	public double distanceTo(Location compareTo) {
		double xCoord = Math.pow(getRow() - compareTo.getRow(), 2);
		double yCoord = Math.pow(getCol() - compareTo.getCol(), 2);
		
		return Math.sqrt(xCoord + yCoord);
	}
	
	/**
	 * Gets the total distance to travel from the first Location in the list 
	 * to the second, etc.
	 * @param route The list of Locations to visit.
	 * @return The distance traveled to reach all of the Locations in the route.
	 */
   public static double getRouteDistance(LinkedList<Location> route) {
      double distance = 0;
      Location l1;
      
      if (route.isEmpty()) {
         return 0;
      }
      
      l1 = route.get(0);
      
      for (Iterator<Location> i = route.iterator(); i.hasNext(); ) {
         Location l2 = i.next();
         
         distance += l1.distanceTo(l2);
         l1 = l2;
      }
      
      return distance;
   }
}
