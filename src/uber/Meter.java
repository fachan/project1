package uber;

import java.util.Iterator;
import java.util.LinkedList;

public class Meter implements Observer {
   private double rate;
   
   public Meter(Subject handler, double rate) {
      handler.registerObserver(this);
      this.rate = rate;
   }
   
   public double getRate() {
      return this.rate;
   }
   
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
      System.out.println("Transaction failed: Insufficient funds. ");
      System.out.printf("\tFare: %.2f\n", fare);
      System.out.printf("\tCustomer %d balance: %.2f\n",
            customer.getID(), customer.getBalance());
   }
   
   private void printReceipt(Driver driver, Customer customer, 
         double oldCBalance, double oldDBalance, double fare) {
            System.out.println("Receipt:");
      System.out.printf("\tFare: %.2f\n", fare);
      
      System.out.println();
      
      System.out.println("\tCustomer balance: ");
      System.out.printf("\t\t  %.2f\n", oldCBalance);
      System.out.printf("\t\t- %.2f\n", fare);
      System.out.printf("\t\t= %.2f\n", customer.getBalance());
      
      System.out.println();
      
      System.out.println("\tDriver balance: ");
      System.out.printf("\t\t  %.2f\n", oldDBalance);
      System.out.printf("\t\t+ %.2f\n", fare);
      System.out.printf("\t\t= %.2f\t* %1.2f\n", 
            driver.getBalance() / Driver.DRIVER_SHARE, Driver.DRIVER_SHARE);
      System.out.printf("\t\t= %.2f\n", driver.getBalance());
      
      System.out.println();
   }
}
