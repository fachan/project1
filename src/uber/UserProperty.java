package uber;

public enum UserProperty {
   ACCOUNT, NAME, BALANCE, CAR, STATUS;
   
   public String toString() {
      switch(this) {
         case ACCOUNT:     return "Account";
         case NAME:        return "Name";
         case BALANCE:     return "Balance";
         case CAR:         return "Car";
         case STATUS:      return "Status";
         default:          return "Unspecified";
      }
   }
}
