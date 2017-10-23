package uber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Handles Uber requests; is responsible for choosing an appropriate driver for
 * the User who sent the Request. Signals Observers when something changes in
 * the system.
 * @author FaithChan
 *
 */
public class RequestHandler implements Comparator<Driver>, Subject {
   private Uber system;
   private Customer requester;
   private ArrayList<Observer> observers;
   private static Lock inputLock;
   
   /**
    * Constructor for RequestHandler. Takes as an argument the Uber system.
    * @param system The Uber system.
    */
   public RequestHandler(Uber system) {
      this.system = system;
      this.requester = null;
      this.observers = new ArrayList<Observer>();
      RequestHandler.inputLock = new ReentrantLock();
   }
   
   /** 
    * Get the Customer who made the Request.
    * @return The requester.
    */
   public Customer getRequester() {
      return this.requester;
   }
   
   /* For testing purposes */
   public void setRequester(Customer requester) {
      this.requester = requester;
   }
   
   /**
    * Sets the lock to the value specified by the parameter.
    * @param lock false if the desired state of the lock is unlocked; true 
    * otherwise.
    */
   public void setLock(boolean lock) {
      if (lock) {
         this.inputLock.lock();
      } else {
         this.inputLock.unlock();
      }
   }
   
   /**
    * Checks if there are any drivers in the system.
    */
   public void checkDrivers() {
      if (!system.hasDrivers()) {
         UberHelper.write("There are no drivers in the system. Exiting...");
         System.exit(0);
      }
   }
   
   /**
    * Verifies that the location in the newRequest is appropriate (e.g. within
    * the grid).
    * @param newRequest The Request being considered.
    * @return true if the location can be verified; false if not.
    */
   private boolean verifyLocation(Request newRequest) {
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
      
      printRequestInfo(newRequest);
      
      if (!system.withinGrid(src)) {
         printOutOfGrid(newRequest.getID(), src);
         return false;
      }

      if (!system.withinGrid(dest)) {
         printOutOfGrid(newRequest.getID(), dest);
         return false;
      }
      
      return true;
   }
   
   /**
    * Organizes the system's list of drivers according to their proximity from
    * the requester, and then their rate.
    * @return The ordered queue of Drivers.
    */
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
         printIDError(ID);
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
      }
      
      return null;
   }
   
   public void processRequest(Request newRequest) {
      PriorityQueue<Driver> drivers;
      Driver driverChoice;
      Location src, dest;
      
      printBorder();
      
      setRequester(findCustomer(newRequest.getID()));
   
      if (!verifyLocation(newRequest))
      {
         printBorder();
         UberHelper.write("");
         return;
      }
      
      src = newRequest.getSource();
      dest = newRequest.getDestination();
      
      checkDrivers();

      drivers = prioritizeDrivers();
      driverChoice = findDriver(drivers, newRequest);
      
      if (driverChoice == null) {
         printNoDrivers(newRequest);
      } else {
         LinkedList<Location> route = new LinkedList<Location>();
         route.add(driverChoice.getLocation());
         route.add(newRequest.getSource());
         route.add(newRequest.getDestination());
         
         notifyObservers(driverChoice, requester, route);
         
         system.setLocation(driverChoice, newRequest.getDestination());
         system.setLocation(requester, newRequest.getDestination());
      }
      
      //print distances
       /* while(true) {
         Driver nextDriver = drivers.poll();
         
         if (nextDriver == null)
            break;
         double distance = 
               requester.getLocation().distanceTo(nextDriver.getLocation());
         System.out.printf("%3f ", distance);
      }
        UberHelper.write();*/
      
      printBorder();
      UberHelper.write("");
   }
   
   public void getScore(Driver driver) {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter rating (1-5) for driver " + driver.getID() + ": ");
      
      while (true) {
         if (in.hasNextDouble()) {
            double score = in.nextDouble();
            in.nextLine();
            
            if ((score < 1) || (score > 5)) {
               System.out.print("\tPlease enter a score between 1 and 5: ");
            } else {
               Rating rating = driver.getRating();
               rating.addRating(score);
               
               break;
            }
         } else {
            System.out.print("\tInput is not a number. " +
                  "Please enter a score between 1 and 5: ");
            in.nextLine();
         }
      }
   }
   
   public void sendRating(Driver driver, Customer customer) {
      setLock(true);
      
      try {
         UberHelper.write("Ride with driver " + driver.getID() + 
               " for customer " + customer.getID() + " has ended.");
         
         getScore(driver);
         UberHelper.write("\tScore successfully added.\n");
      } finally {
         setLock(false);
      }
   }
   
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
   
   public void registerObserver(Observer o) {
      observers.add(o);
   }
   
   public void removeObserver(Observer o) {
      observers.remove(o);
   }
   
   public void notifyObservers(Driver driver, Customer customer, 
         LinkedList<Location> route) {
      for (Observer o : observers) {
         double result = o.update(driver, customer, route);
         if (result < 0) {
            return;
         }
      }
   }
   
   private void printRequestInfo(Request newRequest) {
      Location src = newRequest.getSource();
      Location dest = newRequest.getDestination();
      
      UberHelper.write("Customer ID: " + newRequest.getID());
      UberHelper.write("New request: (" + src.getRow() + ", " + 
            src.getCol() + ") to (" + dest.getRow() + ", " +
            dest.getCol() + ")");
      UberHelper.write("");
   }
   
   private void printOutOfGrid(int ID, Location loc) {
      UberHelper.write("The requested pickup/destination location " + 
            loc.getCol() + ", " + loc.getRow() + " for customer " + 
            ID + " is not within the Uber grid. " + 
            "Request canceled.");
   }
   
   private void printIDError(int ID) {
      UberHelper.write("Customer ID " + ID + " not found. " + 
            " Request canceled.");
   }
   
   private void printNoDrivers(Request newRequest) {
      Location src = newRequest.getSource();
      Location dest = newRequest.getDestination();
      
      UberHelper.write("No drivers found for trip from " + 
            src.getCol() + ", " + src.getRow() + " to " + 
            dest.getCol() + ", " + dest.getRow() + " for user " + 
            newRequest.getID() + ". Please try again later.");
   }
   
   private void printBorder() {
      UberHelper.write("--------------------------------------------------");
   }
}
