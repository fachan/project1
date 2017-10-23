package uber;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Uber Driver class, which inherits from User. Contains the same HashMap of 
 * properties that User has, but has additional fields such as Rating.
 * @author FaithChan
 *
 */
public class Driver extends User {
   public static final double DRIVER_SHARE = 0.75;
	private LinkedList<Request> requests;
	private Rating rating;
	
	private static int START_RATING = 1;
	
	/**
	 * Constructor. Takes a HashMap, containing its properties, as a parameter, 
	 * and initializes the list of requests and the rating for the Driver.
	 * @param properties HashMap of properties for the Driver.
	 */
	public Driver(HashMap properties) {
	   super(properties);
      this.requests = new LinkedList<Request>();
      this.rating = new Rating(START_RATING, 0);
	}
	
	/**
	 * Gets the title of the Driver's car.
	 * @return String representing the Driver's car.
	 */
	public String getCar() {
		return properties.get(UserProperty.CAR);
	}
	
	/**
	 * Sets the title of the car to the parameter newCar. Places the new
	 * String into the appropriate location in the Driver's properties.
	 * @param newCar The new Car title.
	 */
	public void setCar(String newCar) {
	   properties.put(UserProperty.CAR, newCar);
	}
	
	/**
	 * Checks if the Driver is available or not
	 * @return false if the Driver's car is occupied; true if not.
	 */
	public boolean isAvailable() {
	   String status = (String)properties.get(UserProperty.STATUS);
	   Status statusEnum = Status.valueOf(status.toUpperCase());
		return statusEnum.equals(Status.AVAILABLE);
	}
	
	/**
	 * Sets the Driver's availability
	 * @param availability The desired setting for the Driver's availability.
	 */
	private void setAvailability(String availability) {
	   properties.put(UserProperty.STATUS, availability);
	}
	
	/**
	 * Gets the Rating of the Driver.
	 * @return Rating object
	 */
	public Rating getRating() {
		return this.rating;
	}
	
	/** 
	 * Sets the Rating of the Driver to the parameter newRating.
	 * @param newRating The new Rating to give the Driver.
	 */
	public void setRating(Rating newRating) {
	   this.rating = newRating;
	}
	
	/**
	 * Adds a request to the Driver's list of requests.
	 * @param newRequest The request to add to the list.
	 * @return true if the request is successfully added to the list; false 
	 * if not.
	 */
	public boolean addRequest(Request newRequest) {
	   if (isAvailable()) {
	      requests.add(newRequest);
	      setAvailability(Status.OCCUPIED.toString());
	      return true;
	   }
	   
	   return false;
	}
	
	/**
	 * Computes the balance given a Uber fare. Overrides User's implementation 
	 * of the method.
	 * @param fare The Uber fare
	 * @return The computed balance for the Driver given the fare.
	 */
	public double computeBalance(double fare) {
	   return (getBalance() + fare) * DRIVER_SHARE;
	}
}
