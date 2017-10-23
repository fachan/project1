package uber;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Customer extends User {
   public Customer(HashMap properties) {
      super(properties);
   }
   
   public Request sendRequest(Location destination) {
      return new Request(this.getID(), null, destination);
   }
 
   public double computeBalance(double fare) {
      return this.getBalance() - fare;
   }
}
