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

public class RequestHandler implements Comparator<Driver>, Subject {
   private Uber system;
   private Customer requester;
   private ArrayList<Observer> observers;
   private static Lock inputLock;
   
   public RequestHandler(Uber system) {
      this.system = system;
      this.requester = null; // new Customer();?
      this.observers = new ArrayList<Observer>();
      this.inputLock = new ReentrantLock();
   }
   
   public Customer getRequester() {
      return this.requester;
   }
   //might delete?
   public void setRequester(Customer requester) {
      this.requester = requester;
   }
   
   public void setLock(boolean lock) {
      if (lock) {
         this.inputLock.lock();
      } else {
         this.inputLock.unlock();
      }
   }
   
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
      
      if (!system.withinGrid(src)) {
         System.out.println("The requested pickup location " + 
               src.getCol() + ", " + src.getRow() + " for customer " + 
               newRequest.getID() + " is not within the Uber grid. " + 
               "Request canceled.");
         return false;
      }

      if (!system.withinGrid(dest)) {
         System.out.println("The requested destination " + 
               dest.getCol() + ", " + dest.getRow() + " for customer " + 
               newRequest.getID() + " is not within the Uber grid. " + 
               "Request canceled.");
         return false;
      }
      
      return true;
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
               " Request canceled.");
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
   
   private void printBorder() {
      System.out.println("--------------------------------------------------");
   }
   
   public void processRequest(Request newRequest) {
      PriorityQueue<Driver> drivers;
      Driver driverChoice;
      Location src, dest;
      
      printBorder();
      System.out.println("Customer ID: " + newRequest.getID());
      
      //if (getRequester() == null) {
        // System.out.println(newRequest.getID());
         setRequester(findCustomer(newRequest.getID()));
      //}
   
      if (!verifyLocation(newRequest))
      {
         printBorder();
         System.out.println();
         return;
      }
      
      src = newRequest.getSource();
      dest = newRequest.getDestination();
      
      if (!system.hasDrivers()) {
         System.out.println("There are no drivers in the system. Exiting...");
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
         LinkedList<Location> route = new LinkedList<Location>();
         route.add(driverChoice.getLocation());
         route.add(newRequest.getSource());
         route.add(newRequest.getDestination());
         notifyObservers(driverChoice, requester, route);
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
        System.out.println();*/
      
      printBorder();
      System.out.println();
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
         }
      }
   }
   
   public void sendRating(Driver driver, Customer customer) {
      setLock(true);
      
      try {
         System.out.println("Ride with driver " + driver.getID() + 
               " for customer " + customer.getID() + " has ended.");
         
         getScore(driver);
         System.out.println("\tScore successfully added.\n");
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
}
