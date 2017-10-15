package uber;

import java.util.HashMap;
import java.util.Map;

public class User {
   private Map properties;
	//protected String name;
	//protected float balance;
	
   //is it ok to have parameters here and initialize the thing?
   // do i need another enum for all the possible fields (keys) in the HM?
	public User(String name, float balance) {
		//this.name = name;
		//this.balance = balance;
	   properties = new HashMap();
	   //properties.put()
	}
	
	public String getName() {
		return "";//this.name;
	}
	
	public float getBalance() {
		return 0;//this.balance;
	}
	
	//public abstract float computeBalance();
}
