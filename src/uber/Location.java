package uber;

import java.lang.Math;

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
}
