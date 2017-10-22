package uberTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uber.Location;

public class LocationTest {

   private static double tolerance = 0.0001;
   
   @Test
   public void testGetDistanceSmaller() {
      Location loc1 = new Location(10, 4);
      Location loc2 = new Location(4, 5);
      double distance = loc1.distanceTo(loc2);
      
      assertEquals(6.0827, distance, tolerance);
   }
   
   @Test
   public void testGetDistanceSame() {
      Location loc1 = new Location(19, 50);
      Location loc2 = new Location(19, 50);
      double distance = loc1.distanceTo(loc2);
      
      assertEquals(0.0000, distance, tolerance);
   }
   
   @Test
   public void testGetDistanceNegative() {
      Location loc1 = new Location(-8, 45);
      Location loc2 = new Location(19, 5);
      double distance = loc1.distanceTo(loc2);
      
      assertEquals(48.2597, distance, tolerance);
   }
   
   @Test
   public void testGetDistanceBothNegative() {
      Location loc1 = new Location(-23, -18);
      Location loc2 = new Location(-16, -1);
      double distance = loc1.distanceTo(loc2);
      
      assertEquals(18.3847, distance, tolerance);
   }

}
