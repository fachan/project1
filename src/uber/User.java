package uber;

import java.util.HashMap;

public class User {
	protected HashMap properties;
	protected Location loc;
	protected int ID;
	
   //is it ok to have parameters here and initialize the thing?
   // do i need another enum for all the possible fields (keys) in the HM?
	public User(HashMap properties) {
		//this.name = name;
		//this.balance = balance;
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
	
	public AccountType getAccountType() {
	   HashMap tempProperties = getProperties();
	   return Uber.getAccountType(tempProperties);
	   /*String accountType;
	   
	   if (!tempProperties.containsKey(UserProperty.ACCOUNT)) {
	      return null;
	   }
	   
	   accountType = (String)tempProperties.get(UserProperty.ACCOUNT);
	   return AccountType.valueOf(accountType.toUpperCase());*/
	}
	
	public String getName() {
		return (String)getProperties().get(UserProperty.NAME);
	}
	/*
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
