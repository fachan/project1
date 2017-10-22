package uber;

import java.util.HashMap;
import java.util.LinkedList;

public class Driver extends User {
   public static final double DRIVER_SHARE = 0.75;
	private LinkedList<Request> requests;
	private Rating rating;
	
	private static int START_RATING = 1;
	
	public Driver(HashMap properties) {
	   super(properties);
      this.requests = new LinkedList<Request>();
      this.rating = new Rating(START_RATING, 0);
	}
	
	/*public String getCar() {
		return properties.get(UserProperty.CAR);
	}*/
	
	private void setAvailability(String availability) {
	   properties.put(UserProperty.STATUS, availability);
	}
	
	public boolean isAvailable() {
	   String status = (String)properties.get(UserProperty.STATUS);
	   Status statusEnum = Status.valueOf(status.toUpperCase());
		return statusEnum.equals(Status.AVAILABLE);
	}
	
	public Rating getRating() {
		return this.rating;
	}
	
	public void setRating(Rating newRating) {
	   this.rating = newRating;
	}
	
	public boolean addRequest(Request newRequest) {
	   if (isAvailable()) {
	      requests.add(newRequest);
	      setAvailability(Status.OCCUPIED.toString());
	      return true;
	   }
	   
	   // if is available in the time frame...
	   return false;
	}
	
	public double computeBalance(double fare) {
	   return (getBalance() + fare) * DRIVER_SHARE;
	}
}
