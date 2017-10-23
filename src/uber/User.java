package uber;

import java.util.HashMap;

/**
 * A person that uses Uber.
 * @author FaithChan
 *
 */
public class User {
	protected HashMap<UserProperty, String> properties;
	protected Location loc;
	protected int ID;
	
	public User(HashMap<UserProperty, String> properties) {
	   this.properties = properties;
	}
	
	public HashMap getProperties() {
	   return this.properties;
	}
	
	public int getID() {
	   return this.ID;
	}
	
	public void setID(int ID) {
	   this.ID = ID;
	}
	
	public String getName() {
		return (String)getProperties().get(UserProperty.NAME);
	}
	
	public void setBalance(double newBalance) {
	   properties.put(UserProperty.BALANCE, ((Double)newBalance).toString());
	}
	
	public double getBalance() {
	   String balance = (String)getProperties().get(UserProperty.BALANCE);
	   double result;
	   
	   balance = balance.trim();
		result = Double.valueOf(balance);
		return result;
	}
	
	public void setLocation(Location newLoc) {
	   this.loc = newLoc;
	}
	
	public Location getLocation() {
	   return this.loc;
	}
	
	public double computeBalance(double fare) {
	   return this.getBalance();
	}
}
