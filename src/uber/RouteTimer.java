package uber;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class RouteTimer implements Observer {
   private double speed;
   private RequestHandler handler;
   
   public RouteTimer(RequestHandler handler, double speed) {
      handler.registerObserver(this);
      this.handler = handler;
      this.speed = speed;
   }
   
   public void startTimer(Driver driver, Customer customer, 
         double timeEstimate) {
      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
        
         public void run() {
            handler.sendRating(driver, customer);
            timer.cancel();
         }
        
      },Math.round(100 * timeEstimate));
   }

   public double update(Driver driver, Customer customer, 
         LinkedList<Location> route) {
      Location src = route.get(1);
      double distance = driver.getLocation().distanceTo(src);
      double timeEstimate = distance * speed;
     
      displayWaitTime(driver, route.get(1), timeEstimate);
      startTimer(driver, customer, timeEstimate);
      
      return timeEstimate;
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
}
