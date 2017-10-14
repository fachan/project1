package uber2;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User {
   //Map properties;
   private Location source;
   private Location destination;
   // time
   Delivery order;
   
   public Customer(String name, float balance) {
      super(name, balance);
      //properties = new HashMap();
   }
   
   //sendRequest(Delivery order)
   
   public float computeBalance() {
      
   }
}
