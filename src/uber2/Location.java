package uber2;

import java.lang.Math;

public class Location {
	private double x;
	private double y;
	
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getDistance(Location compareTo) {
		double xCoord = Math.pow(this.x - compareTo.x, 2);
		double yCoord = Math.pow(this.y - compareTo.y, 2);
		
		return Math.sqrt(xCoord + yCoord);
	}
}
