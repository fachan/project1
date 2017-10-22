package uber;

import java.util.Iterator;
import java.util.LinkedList;

public class Meter implements Observer {
   private double rate;
   
   public Meter(Subject handler, double rate) {
      this.rate = rate;
      handler.registerObserver(this);
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
   
   //TODO: should this be here?
   public double getRouteDistance(LinkedList<Location> route) {
      double distance = 0;
      Location l1;
      
      if (route.isEmpty()) {
         return 0;
      }
      
      l1 = route.get(0);
      
      for (Iterator<Location> i = route.iterator(); i.hasNext(); ) {
         Location l2 = i.next();
         
         distance += l1.distanceTo(l2);
         l1 = l2;
      }
      
      return distance;
   }
   
   public double getFare(LinkedList<Location> route) {
      double distance = getRouteDistance(route);
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
         System.out.println("Transaction failed: Insufficient funds. ");
         System.out.printf("\tFare: %.2f\n", fare);
         System.out.println("\tCustomer " + customer.getID() + " balance: " + 
               customer.getBalance());
         return -1;
      }
      
      // TODO: split into function
      System.out.println("Receipt:");
      System.out.printf("\tFare: %.2f\n", fare);
      
      System.out.println();
      
      System.out.println("\tCustomer balance: ");
      System.out.printf("\t\t  %.2f\n", oldCustomer);
      System.out.printf("\t\t- %.2f\n", fare);
      System.out.printf("\t\t= %.2f\n", customer.getBalance());
      
      System.out.println();
      
      System.out.println("\tDriver balance: ");
      System.out.printf("\t\t  %.2f\n", oldDriver);
      System.out.printf("\t\t+ %.2f\n", fare);
      System.out.printf("\t\t= %.2f\t* %1.2f\n", 
            driver.getBalance() / Driver.DRIVER_SHARE, Driver.DRIVER_SHARE);
      System.out.printf("\t\t= %.2f\n", driver.getBalance());
      
      return fare;
   }
}
