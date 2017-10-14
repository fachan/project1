package uber2;

public enum DeliveryType {
   PASSENGER, /*FOOD*/;
   
   public String toString() {
      switch(this) {
         case PASSENGER:   return "Passenger";
         //case FOOD:      return "Food";
         default:          return "Unspecified";
      }
   }
}
