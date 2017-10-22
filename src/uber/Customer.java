package uber;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Customer extends User {
   // time
   
   public Customer(HashMap properties) {
      super(properties);
   }
   
   //sendRequest(Delivery order)
   
   public double computeBalance(double fare) {
      return this.getBalance() - fare;
   }
}
