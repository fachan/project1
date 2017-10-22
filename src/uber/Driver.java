package uber;

import java.util.HashMap;
import java.util.LinkedList;

public class Driver extends User {
	private LinkedList<Request> requests;
	private Rating rating;
	
	private static int START_RATING = 0;
	
	public Driver(/*String name, float balance, Vehicle car, boolean available*/HashMap properties) {
		//super(name, balance);
	   super(properties);
      this.requests = new LinkedList<Request>();
      this.rating = new Rating(START_RATING);
	}
	
	public Car getCar() {
		return (Car)properties.get(UserProperty.CAR);
	}
	
	// TODO: Fix
	public boolean isAvailable() {
	   String status = (String)properties.get(UserProperty.STATUS);
	   Status statusEnum = Status.valueOf(status.toUpperCase());
		return statusEnum.equals(Status.AVAILABLE);
	}
	
	public Rating getRating() {
		return this.rating;
	}
	
	public boolean addRequest(Request newRequest) {
	   if (isAvailable()) {
	      requests.add(newRequest);
	      return true;
	   }
	   
	   // if is available in the time frame...
	   return false;
	}
	
	/*public float computeBalance() {
		return this.balance;
	}*/
}
