package uberTests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;

import uber.*;

public class RequestHandlerTest {

   private static RequestHandler handler;
   private static Customer c1;
   private static Driver d1;
   
   @Before
   public void initHandlerTest() {
      Uber system = new Uber(10, 10);
      handler = new RequestHandler(system);
   }
   
   @Before
   public void initCustomer() {
      Location loc = new Location(5, 4);
      HashMap cProperties = new HashMap();
      
      c1 = new Customer(cProperties);
      c1.setLocation(loc);
      handler.setRequester(c1);
   }
   
   @Before
   public void initDriver() {
      Location loc = new Location(10, 15);
      
      HashMap dProperties = new HashMap();
      dProperties.put(UserProperty.NAME, "d1");
      
      Rating newRating = new Rating(1.5, 1);
      
      d1 = new Driver(dProperties);
      d1.setLocation(loc);
      d1.setRating(newRating);
      d1.setID(1);
   }
   
   @Test
   public void testFindDriver() {
      fail("Not yet implemented");
   }

   @Test
   public void testProcessRequest() {
      fail("Not yet implemented");
   }

   @Test
   public void testCompareSmaller2ndDistance() {
      Driver d2;
      Location loc = new Location(0, 0);
      HashMap testP = new HashMap();
      
      d2 = new Driver(testP);
      d2.setLocation(loc);
      
      assertEquals(1, ((RequestHandler)handler).compare(d1, d2));
   }
   
   @Test
   public void testCompareGreater2ndDifference() {
      Driver d2;
      Location loc = new Location(-5, -20);
      HashMap testP = new HashMap();
      
      d2 = new Driver(testP);
      d2.setLocation(loc);
      
      assertEquals(-1, ((RequestHandler)handler).compare(d1, d2));
   }

   @Test
   public void testCompareSameDistance() {
      Driver d2;
      Location loc = new Location(10, 15);
      HashMap testP = new HashMap();
      Rating newRating = new Rating(4.1, 2);
      
      d2 = new Driver(testP);
      d2.setLocation(loc);
      
      assertEquals(-1, ((RequestHandler)handler).compare(d1, d2));
   }
   
   @Test
   public void testComparePriorityQueue() {
      Driver d2;
      Location loc = new Location(-5, -20);
      HashMap testP = new HashMap();
      testP.put(UserProperty.NAME, "d2");
      
      d2 = new Driver(testP);
      d2.setLocation(loc);
      
      Driver d3;
      Location loc2 = new Location(0, 0);
      HashMap testP2 = new HashMap();
      testP2.put(UserProperty.NAME, "d3");
      Rating r3 = new Rating(4.1, 2);
      
      d3 = new Driver(testP2);
      d3.setLocation(loc2);
      d3.setRating(r3);
      
      Driver d4;
      Location loc4 = new Location(0, 0);
      HashMap testP4 = new HashMap();
      testP2.put(UserProperty.NAME, "d4");
      Rating r4 = new Rating(3.3, 2);
      
      d4 = new Driver(testP4);
      d4.setLocation(loc4);
      d4.setRating(r4);
      
      PriorityQueue<Driver> drivers = new PriorityQueue(handler);
      
      drivers.add(d2);
      drivers.add(d1);
      drivers.add(d4);
      drivers.add(d3);
      
      assertSame(d3, drivers.poll());
      assertSame(d4, drivers.poll());
      assertSame(d1, drivers.poll());
      assertSame(d2, drivers.poll());
   }
   
}
