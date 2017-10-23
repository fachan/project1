package uber;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A Timer class that serves as an Observer to RequestHandler. Determines the 
 * time estimate on a route and starts a timer according to this route.
 * @author FaithChan
 *
 */
public class RouteTimer implements Observer {
   private double speed;
   private RequestHandler handler;
   
   /**
    * Constructor. Takes the RequestHandler and the speed/rate at which the 
    * vehicle is moving.
    * @param handler The RequestHandler being observed.
    * @param speed The rate at which the vehicle is moving.
    */
   public RouteTimer(RequestHandler handler, double speed) {
      handler.registerObserver(this);
      this.handler = handler;
      this.speed = speed;
   }
   
   /**
    * Starts a timer to simulate the delay between a request and the completion
    * of the request/route. Signals the handler to begin the rating procedure.
    * @param driver The Driver in this request.
    * @param customer The Customer in this request.
    * @param timeEstimate The amount of time the route is expected to take.
    */
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

   /**
    * Updates when the Subject signals. Determines and displays the estimated
    * wait time and begins the timer.
    */
   public double update(Driver driver, Customer customer, 
         LinkedList<Location> route) {
      Location src = route.get(1);
      double distance = driver.getLocation().distanceTo(src);
      double timeEstimate = distance * speed;
     
      displayWaitTime(driver, route.get(1), timeEstimate);
      startTimer(driver, customer, timeEstimate);
      
      return timeEstimate;
   }
   
   /**
    * Helper function to provide a status update to the user.
    * @param driver The Driver in this request.
    * @param source The start location of the route.
    * @param timeEstimate The estimated amount of time the route will take.
    */
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
