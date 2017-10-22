package uber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class RequestHandler implements Comparator<Driver> {
   private Uber system;
   private User requester;
   
   public RequestHandler(Uber system) {
      this.system = system;
      this.requester = null; // new Customer();?
   }
   
   public void setRequester(Customer requester) {
      this.requester = requester;
   }
   
   private void verifyLocation(Request newRequest) {
      Location src = newRequest.getSource();
      Location dest = newRequest.getDestination();
      
      //if (src.withinGrid(system.g))
   }
   
   private PriorityQueue<Driver> prioritizeDrivers() {
      PriorityQueue<Driver> drivers = new 
            PriorityQueue<Driver>(this);
      
      for (Iterator<Driver> i = system.getDrivers().iterator(); i.hasNext(); ) {
         Driver newDriver = i.next();
         drivers.add(newDriver);
      }
      
      return drivers;
   }
   
   public Driver findDriver(PriorityQueue<Driver> drivers) {
      Driver next;
      
      while ((next = drivers.poll()) != null) {
         // if driver accepts
         // return next;
         // else
         // print an error message?
      }
      
      System.out.println("No drivers found. Please try again later.");
      return null;
   }
   
   //Customer? or user?
   public void processRequest(Customer customer, Request newRequest) {
      //TODO: ask: priority queue for each customer...? 
      PriorityQueue<Driver> drivers;
      Driver driverChoice;
      
      //this.requester = customer;
      verifyLocation(newRequest);
      
      if (!system.hasDrivers()) {
         System.out.println("There are no drivers available. Exiting...");
         System.exit(0);
      }

      drivers = prioritizeDrivers();
      driverChoice = findDriver(drivers);
      
      /*print distances
       * while(true) {
         Driver nextDriver = drivers.poll();
         
         if (nextDriver == null)
            break;
         double distance = 
               requester.getLocation().distanceTo(nextDriver.getLocation());
         System.out.println(distance);
      }*/
   }
   //public void sendStatus()
   
   /* Returns -1 if d1 comes before d2 (its distance to the requester is 
    * smaller than the distance from d2 to the requester), 0 if they have 
    * the same priority, and 1 if d2 comes before d1
    */
   public int compare(Driver d1, Driver d2) {
      Location loc = requester.getLocation();
      double distance1 = loc.distanceTo(d1.getLocation());
      double distance2 = loc.distanceTo(d2.getLocation());
      
      if (distance1 < distance2) {
         return -1; // 1st argument has less priority..?
      } else if (distance1 > distance2) {
         return 1;
      } 

      return (d1.getRating()).compareTo(d2.getRating());
   }
}
