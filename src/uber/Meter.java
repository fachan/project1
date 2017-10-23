package uber;

import java.util.LinkedList;

/**
 * The system that keeps track of the rate and the fare for a Uber request.
 * @author FaithChan
 *
 */
public class Meter implements Observer {
   private double rate;
   
   /**
    * Constructor. Registers itself with a Subject (RequestHandler) and sets
    * its rate to the parameter given.
    * @param handler The RequestHandler to observe.
    * @param rate The rate for the fare.
    */
   public Meter(Subject handler, double rate) {
      handler.registerObserver(this);
      this.rate = rate;
   }
   
   /**
    * Gets the rate.
    * @return
    */
   public double getRate() {
      return this.rate;
   }
   
   /**
    * Sets the rate to the new rate, newRate.
    * @param newRate The new rate.
    */
   private void setRate(double newRate) {
      this.rate = newRate;
   }
   
   public double calculateFare(double numMiles) {
      return numMiles * getRate();
   }
   
   public double getFare(LinkedList<Location> route) {
      double distance = Location.getRouteDistance(route);
      return distance * rate;
   }
   
   public int updateBalance(Driver driver, Customer customer, double fare) {
      double driverBalance = driver.computeBalance(fare);
      double customerBalance = customer.computeBalance(fare);
      
      if (customerBalance < 0) {
         return -1;
      }
      
      driver.setBalance(driverBalance);
      customer.setBalance(customerBalance);
      return 0;
   }
   
   public double update(Driver driver, Customer customer, 
         LinkedList<Location> route) {
      double fare = getFare(route);
      double oldDriver = driver.getBalance();
      double oldCustomer = customer.getBalance();
      
      if (updateBalance(driver, customer, fare) < 0) {
         printTransactionFailure(customer, fare);
         return -1;
      }
   
      printReceipt(driver, customer, oldDriver, oldCustomer, fare);
      
      return fare;
   }
   
   private void printTransactionFailure(Customer customer, double fare) {
      UberHelper.write("Transaction failed: Insufficient funds. ");
      UberHelper.write(String.format("\tFare: %.2f", fare));
      UberHelper.write(String.format("\tCustomer %d balance: %.2f",
            customer.getID(), customer.getBalance()));
   }
   
   private void printReceipt(Driver driver, Customer customer, 
         double oldCBalance, double oldDBalance, double fare) {
      UberHelper.write("Receipt:");
      System.out.printf("\tFare: %.2f\n", fare);
      
      UberHelper.write("");
      
      UberHelper.write("\tCustomer balance: ");
      UberHelper.write(String.format("\t\t  %.2f", oldCBalance));
      UberHelper.write(String.format("\t\t- %.2f", fare));
      UberHelper.write(String.format("\t\t= %.2f", customer.getBalance()));
      
      UberHelper.write("");
      
      UberHelper.write(String.format("\tDriver balance: "));
      UberHelper.write(String.format("\t\t  %.2f", oldDBalance));
      UberHelper.write(String.format("\t\t+ %.2f", fare));
      UberHelper.write(String.format("\t\t= %.2f\t* %1.2f", 
            driver.getBalance() / Driver.DRIVER_SHARE, Driver.DRIVER_SHARE));
      UberHelper.write(String.format("\t\t= %.2f", driver.getBalance()));
      
      UberHelper.write("");
   }
}