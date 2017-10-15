package uber2;

import java.lang.Math;

public class Location {
	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getx() {
	   return this.x;
	}
	
	public int gety() {
	   return this.y;
	}
	
	public double getDistance(Location compareTo) {
		double xCoord = Math.pow(this.x - compareTo.x, 2);
		double yCoord = Math.pow(this.y - compareTo.y, 2);
		
		return Math.sqrt(xCoord + yCoord);
	}
}
