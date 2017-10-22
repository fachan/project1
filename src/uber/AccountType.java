package uber;

public enum AccountType {
   DRIVER, CUSTOMER;
   
   public String toString() {
      switch(this) {
         case DRIVER:      return "Driver";
         case CUSTOMER:    return "Customer";
         default:          return "Unspecified";
      }
   }
}
