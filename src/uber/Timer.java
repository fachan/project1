package uber;

import java.util.LinkedList;

public class Timer implements Observer {
   private double speed;
   
   public Timer(Subject handler, double speed) {
      handler.registerObserver(this);
      this.speed = speed;
   }
   
   public void displayWaitTime(Driver driver, Location source, 
         double timeEstimate) {
      Location driverLoc = driver.getLocation();
      
      System.out.print("The estimated wait time for driver " + 
         driver.getID() + " to reach (" + source.getRow() + ", " + 
         source.getCol() + ") from (" + driverLoc.getRow() + ", " +
         driverLoc.getCol() + ") is ");
      System.out.printf("%.2f minutes.\n", timeEstimate);
   }
   
   public double update(Driver driver, Customer customer, 
         LinkedList<Location> route) {
      double distance = Location.getRouteDistance(route);
      double timeEstimate = distance * speed;
      
      displayWaitTime(driver, route.get(1), timeEstimate);
      
      return timeEstimate;
   }
}
