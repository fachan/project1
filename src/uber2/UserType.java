package uber2;

public enum UserType {
   DRIVER, PASSENGER;
   
   public String toString() {
      switch(this) {
      case DRIVER:      return "Driver";
      case PASSENGER:   return "Passenger";
      default:          return "Unspecified";
      }
   }
}
