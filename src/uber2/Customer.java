package uber2;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User {
   //Map properties;
   private Location source;
   private Location destination;
   // time
   Delivery order;
   
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
