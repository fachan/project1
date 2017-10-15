package uber2;

import java.util.HashMap;

public class User {
	private HashMap properties;
	private Location loc;
	
   //is it ok to have parameters here and initialize the thing?
   // do i need another enum for all the possible fields (keys) in the HM?
	public User(HashMap properties) {
		//this.name = name;
		//this.balance = balance;
	}
	
	/*public String getName() {
		return this.name;
	}
	
	public void setBalance(float newBalance) {
	   this.balance = newBalance;
	}
	
	public float getBalance() {
		return this.balance;
	}*/
	
	public void setLocation(Location newLoc) {
	   this.loc = newLoc;
	}
	
	public Location getLocation() {
	   return this.loc;
	}
	
	public float computeBalance() {
	   return 0;
	}
}
