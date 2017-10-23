package uber;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class UberSimulator {
   static final String INPUT_FILE = "./Input1.txt";
   static final double RATE = 0.75;
   static final double SPEED = 2;       // minutes per mile, for example
   
   public static void main(String[] args) {
      Uber system = new Uber(/*-1, -1*/25, 20);
      RequestHandler handler = new RequestHandler(system);
      Meter meter = new Meter(handler, RATE);
      RouteTimer timer = new RouteTimer(handler, SPEED);
      HashMap<Integer, Customer> customers;
      
      Request r10, r11, r12, r13, r14, r15;
      
      try {
         system.initGrid(INPUT_FILE);
      } catch (FileNotFoundException e) {
         System.err.println("Input file not found");
         e.printStackTrace();
         System.exit(0);
      }
      
      customers = system.getCustomers();
      r10 = customers.get(10).sendRequest(new Location(100, 100));
      r11 = customers.get(11).sendRequest(new Location(20, 15));
      r12 = customers.get(12).sendRequest(new Location(10, 1));
      r13 = customers.get(13).sendRequest(new Location(0, 0));
      r14 = customers.get(14).sendRequest(new Location(2, 5));
      r15 = customers.get(15).sendRequest(new Location(1, 1));

      // Simulate all the random stuff
      handler.processRequest(r10);
      handler.processRequest(r11);
      handler.processRequest(r12);
      handler.processRequest(r13);
      handler.processRequest(r14);
      handler.processRequest(r15);
      
      system.printGrid();
   }
}
