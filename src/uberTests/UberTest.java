package uberTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uber.*;

public class UberTest {
   private static Uber system;
   
 //initGrid
   
   @Before
   public void initUberTest() {
      system = new Uber(10, 5);
   }
   
   @Test
   public void testWithinGridTrue() {
      Location loc = new Location(0, 3);
      assertTrue(system.withinGrid(loc));
   }
   
   @Test
   public void testWithinGridFalse() {
      Location loc = new Location(11, 3);
      assertFalse(system.withinGrid(loc));
   }
   
   @Test
   public void testWithinGridFalse2() {
      Location loc = new Location(11, 7);
      assertFalse(system.withinGrid(loc));
   }
   
   @Test
   public void testWithinGridInnerBorderEdge() {
      Location loc = new Location(0, 3);
      assertTrue(system.withinGrid(loc));
   }
   
   @Test
   public void testWithinGridBorderTrue() {
      Location loc = new Location(9, 1);
      assertTrue(system.withinGrid(loc));
   }
   
   @Test
   public void testWithinGridBorderFalse() {
      Location loc = new Location(8, 6);
      assertFalse(system.withinGrid(loc));
   }
   
   @Test
   public void testWithinGridBorderFalse2() {
      Location loc = new Location(10, 3);
      assertFalse(system.withinGrid(loc));
   }
   
   @Test
   public void testWithinGridNegative() {
      Location loc = new Location(-1, 4);
      assertFalse(system.withinGrid(loc));
   }

   @Test
   public void testHasDrivers() {
      fail("Not yet implemented");
   }

   @Test
   public void testFindDriver() {
      fail("Not yet implemented");
   }

   @Test
   public void testGetAccountType() {
      fail("Not yet implemented");
   }

}
