package uber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class RequestHandler implements Comparator<Driver> {
   private Uber system;
   private Customer requester;
   
   public RequestHandler(Uber system) {
      this.system = system;
      this.requester = null; // new Customer();?
   }
   
   public Customer getRequester() {
      return this.requester;
   }
   //might delete?
   public void setRequester(Customer requester) {
      this.requester = requester;
   }
   
   private void verifyLocation(Request newRequest) {
      Location src = newRequest.getSource();
      Location dest = newRequest.getDestination();
      int rows = system.getGridRows();
      int cols = system.getGridCols();
      
      /* If the location is null, put the source as the location of the 
       * requester */
      if (src == null) {
         src = requester.getLocation();
         newRequest.setSource(src);
      }
      
      if (!system.withinGrid(src)) {
         System.out.println("The requested pickup location " + 
               src.getCol() + ", " + src.getRow() + " for user " + 
               newRequest.getID() + " is not within the Uber grid. " + 
               "Please try again.");
      }

      if (!system.withinGrid(dest)) {
         System.out.println("The requested destination " + 
               dest.getCol() + ", " + dest.getRow() + " for user " + 
               newRequest.getID() + " is not within the Uber grid. " + 
               "Please try again.");
      }
   }
   
   private PriorityQueue<Driver> prioritizeDrivers() {
      PriorityQueue<Driver> drivers = new 
            PriorityQueue<Driver>(this);
      
      HashMap<Integer, Driver> driverList = system.getDrivers();
      Set<Integer> entries = driverList.keySet();
      
      for (Iterator<Integer> i = entries.iterator(); i.hasNext(); ) {
         Driver newDriver = driverList.get(i.next());
         drivers.add(newDriver);
      }
      
      return drivers;
   }
   
   public Customer findCustomer(int ID) {
      HashMap<Integer, Customer> customers = system.getCustomers();
      
      if (!customers.containsKey(ID)) {
         System.out.println("Customer ID " + ID + " not found. " + 
               " Please try again.");
      }
      
      return customers.get(ID);
   }
   
   public Driver findDriver(PriorityQueue<Driver> drivers, Request newRequest) {
      Driver next;
      Location src = newRequest.getSource();
      Location dest = newRequest.getDestination();

      while ((next = drivers.poll()) != null) {
         if (next.addRequest(newRequest)) {
            return next;
         }
         // else
         // print an error message?
      }
      
      return null;
   }
   
   public void processRequest(Request newRequest) {
      //TODO: ask: priority queue for each customer...? 
      PriorityQueue<Driver> drivers;
      Driver driverChoice;
      Location src, dest;
      
      //if (getRequester() == null) {
        // System.out.println(newRequest.getID());
         setRequester(findCustomer(newRequest.getID()));
      //}
   
      verifyLocation(newRequest);
      src = newRequest.getSource();
      dest = newRequest.getDestination();
      
      if (!system.hasDrivers()) {
         System.out.println("There are no drivers available. Exiting...");
         System.exit(0);
      }

      drivers = prioritizeDrivers();
      driverChoice = findDriver(drivers, newRequest);
      
      if (driverChoice == null) {
         System.out.println("No drivers found for trip from " + 
               src.getCol() + ", " + src.getRow() + " to " + 
               dest.getCol() + ", " + dest.getRow() + " for user " + 
               newRequest.getID() + ". Please try again later.");
      } else {
         System.out.println("next driver: " + driverChoice.getID());
      }
      
      
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
