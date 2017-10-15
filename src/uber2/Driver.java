package uber2;

import java.util.HashMap;
import java.util.LinkedList;

public class Driver extends User implements Comparable<Driver> {
	private LinkedList<Route> routes;
	
	private Car car;
	private boolean available;
	private Rating rating;
	
	private static int START_RATING = 0;
	
	public Driver(/*String name, float balance, Vehicle car, boolean available*/HashMap properties) {
		//super(name, balance);
	   super(properties);
		this.car = car;
		this.available = available;
		this.rating = new Rating(START_RATING);
		
		this.routes = new LinkedList<Route>();
	}
	
	public Car getCar() {
		return this.car;
	}
	
	public boolean isAvailable() {
		return this.available;
	}
	
	public Rating rating() {
		return this.rating;
	}
	
	public void addRoute(Route newRoute) {
		routes.add(newRoute);
	}
	
	/*public float computeBalance() {
		return this.balance;
	}*/
	
	public int compareTo(Driver otherDriver) {
		return 1;
	}
}
