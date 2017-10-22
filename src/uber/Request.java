package uber;

public class Request {
   private Location src;
   private Location dest;
   private int numItems;
   /* time */
   
   public Request(Location src, Location dest, /* time */int numItems) {
      this.src = src;
      this.dest = dest;
      this.numItems = numItems; // TODO: maybe don't do this?
   }
   
   public Location getSource() {
      return this.src;
   }
   
   public Location getDestination() {
      return this.dest;
   }
   
   public int getCapacity() {
      return this.numItems;
   }
}
