package uber;

public class Request {
   private int ID;
   private Location src;
   private Location dest;
   
   public Request(int ID, Location src, Location dest) {
      this.ID = ID;
      this.src = src;
      this.dest = dest;
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
   
   public void setDestination(Location newDest) {
      this.dest = newDest;
   }
}
