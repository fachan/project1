package uber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;

public class UberSimulator {
   static final String INPUT_FILE = "./Input1.txt";
   static final String TRIP_LOG = "./TripLog.txt";
   static final String FINAL_STATE_LOG = "./FinalLog.txt";
   static final double RATE = 0.75;
   static final double SPEED = 2;       // minutes per mile, for example5
   
   static PrintStream ps;
   static PrintStream ps2;
   
   public static void main(String[] args) {
      Uber system = new Uber(25, 20);
      RequestHandler handler = new RequestHandler(system);
      Meter meter = new Meter(handler, RATE);
      RouteTimer timer = new RouteTimer(handler, SPEED);
      HashMap<Integer, Customer> customers;
      HashMap<Integer, Driver> drivers;
      
      File tripLog = new File(TRIP_LOG);
      File finalLoc = new File(FINAL_STATE_LOG);
      
      try {
         FileOutputStream stream = new FileOutputStream(tripLog);
         ps = new PrintStream(stream);
         ps2 = new PrintStream(System.out);
      } catch (FileNotFoundException e) {
         System.err.println("Could not find log files");
         e.printStackTrace();
         System.exit(0);
      }
      
      Request r10, r11, r12, r13, r14, r15;
      
      try {
         system.initGrid(INPUT_FILE);
      } catch (FileNotFoundException e) {
         System.err.println("Input file not found");
         e.printStackTrace();
         System.exit(0);
      }
      
      customers = system.getCustomers();
      drivers = system.getDrivers();
      
      Rating r0 = new Rating(2.43, 10);
      drivers.get(0).setRating(r0);
      
      Rating r1 = new Rating(3.03, 9);
      drivers.get(1).setRating(r1);
      
      Rating r2 = new Rating(4.55, 15);
      drivers.get(2).setRating(r2);
      
      Rating r3 = new Rating(4.1, 9);
      drivers.get(3).setRating(r3);
      
      Rating r4 = new Rating(4.77, 90);
      drivers.get(4).setRating(r4);

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
      
      try {
         Thread.currentThread().sleep(8000);
      }  catch (InterruptedException e) {}
      
      //print final states
      printFinalStates(system);
   }
   
   public static void printFinalStates(Uber system) {
      File finalLog = new File(FINAL_STATE_LOG);
      
      try {
         FileOutputStream stream = new FileOutputStream(finalLog);
         ps = new PrintStream(stream);
         System.setOut(ps);
      } catch (FileNotFoundException e) {
         System.err.println("Could not find log files");
         e.printStackTrace();
         System.exit(0);
      }
      
      HashMap<Integer, Driver> drivers = system.getDrivers();
      HashMap<Integer, Customer> customers = system.getCustomers();
      
      System.out.println("Drivers:");
      System.out.println("Location\t\t\t\tBalance\t\tScore");
      for (Iterator<Integer> i = drivers.keySet().iterator(); i.hasNext(); ) {
         Driver next = drivers.get(i.next());
         Location loc = next.getLocation();
         System.out.print("Driver " + next.getID() + ": (" + loc.getRow() + 
               ", " + loc.getCol() + ")");
         System.out.printf("\t\t$%.2f", next.getBalance());
         System.out.printf("\t\t%.2f\n", next.getRating().getScore());
      }
      
      System.out.println();
      
      System.out.println("Customer locations:");
      System.out.println("Location\t\t\t\tBalance");
      for (Iterator<Integer> i = customers.keySet().iterator(); i.hasNext(); ) {
         Customer next = customers.get(i.next());
         Location loc = next.getLocation();
         System.out.print("Customer " + next.getID() + ": (" + loc.getRow() + 
               ", " + loc.getCol() + ")");
         System.out.printf("\t\t$%.2f\n", next.getBalance());
      }
   }
}
