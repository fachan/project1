package uber2;

public enum UserProperty {
   NAME, BALANCE, CAR, STATUS; //TODO: RATING INITIALIZED??
   
   public String toString() {
      switch(this) {
         case NAME:        return "Name";
         case BALANCE:     return "Balance";
         case CAR:         return "Car";
         case STATUS:      return "Status";
         default:          return "Unspecified";
      }
   }
}
