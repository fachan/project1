package uber;

import java.lang.Math;
import java.util.Iterator;
import java.util.LinkedList;

public class Location {
	private int row;
	private int col;
	
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
	
	public double distanceTo(Location compareTo) {
		double xCoord = Math.pow(getRow() - compareTo.getRow(), 2);
		double yCoord = Math.pow(getCol() - compareTo.getCol(), 2);
		
		return Math.sqrt(xCoord + yCoord);
	}
	
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
