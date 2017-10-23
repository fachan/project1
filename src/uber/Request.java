package uber;

/**
 * Represents a request (for a ride, for example), to send to Uber.
 * Contains a source and a destination location, as well as the ID of the
 * user who sent the request.
 * @author FaithChan
 *
 */
public class Request {
   private int ID;
   private Location src;
   private Location dest;
   
   /**
    * Constructor. Takes the User's ID, the start location of the route, and
    * the end location of the route.
    * @param ID The User ID
    * @param src The start location of the route
    * @param dest The end location of the route
    */
   public Request(int ID, Location src, Location dest) {
      this.ID = ID;
      this.src = src;
      this.dest = dest;
   }
   
   /**
    * Gets the User ID
    * @return User ID integer
    */
   public int getID() {
      return this.ID;
   }
   
   /**
    * Sets the User ID to newID.
    * @param newID The new User ID.
    */
   public void setID(int newID) {
      this.ID = newID;
   }
   
   /**
    * Gets the start location of the route.
    * @return The source Location
    */
   public Location getSource() {
      return this.src;
   }
   
   /**
    * Sets the start location to newSrc
    * @param newSrc The new start location
    */
   public void setSource(Location newSrc) {
      this.src = newSrc;
   }
   
   /**
    * Gets the ending location of the route.
    * @return Location representing the destination location.
    */
   public Location getDestination() {
      return this.dest;
   }
   
   /**
    * Sets the ending location of the route to newDest.
    * @param newDest The new destination of the route.
    */
   public void setDestination(Location newDest) {
      this.dest = newDest;
   }
}
