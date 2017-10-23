package uber;

public class Request {
   private int ID;
   private Location src;
   private Location dest;
   private int numItems;
   
   public Request(int ID, Location src, Location dest, int numItems) {
      this.ID = ID;
      this.src = src;
      this.dest = dest;
      this.numItems = numItems; // TODO: maybe don't do this?
   }
   
   public int getID() {
      return this.ID;
   }
   
   public void setID(int newID) {
      this.ID = newID;
   }
   
   public Location getSource() {
      return this.src;
   }
   
   public void setSource(Location newSrc) {
      this.src = newSrc;
   }
   
   public Location getDestination() {
      return this.dest;
   }
   
   public int getCapacity() {
      return this.numItems;
   }
}
