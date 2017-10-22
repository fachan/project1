package uber;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Customer extends User {
   //Map properties;
   private Location src;
   private Location dest;
   // time
   Delivery order;
   PriorityQueue<Driver> drivers;
   
   public Customer(/*String name, float balance*/HashMap properties) {
      //super(name, balance);
      super(properties);
      //properties = new HashMap();
   }
   
   //sendRequest(Delivery order)
   
   public float computeBalance() {
      return 0;
   }
}
