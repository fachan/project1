package uber;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Customer class to represent a User that can make a Request for a service
 * from Uber. Contains a HashMap of properties and inherits from the class 
 * User.
 * @author FaithChan
 *
 */
public class Customer extends User {
   
   /** 
    * Constructor. The parameter is a HashMap of the Customer's properties.
    * @param properties The Customer's properties.
    */
   public Customer(HashMap properties) {
      super(properties);
   }
   
   /**
    * Sends a new request to be processed by Uber. 
    * @param destination The Customer's desired location.
    * @return The new Request.
    */
   public Request sendRequest(Location destination) {
      return new Request(this.getID(), null, destination);
   }
 
   /**
    * Computes the balance for the customer given the fare.
    * @param fare The Uber fare charged to the Customer.
    * @return the potential balance after the fare is considered.
    */
   public double computeBalance(double fare) {
      return this.getBalance() - fare;
   }
}
