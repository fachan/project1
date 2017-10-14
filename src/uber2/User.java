package uber2;

public abstract class User {
	protected String name;
	protected float balance;
	
   //is it ok to have parameters here and initialize the thing?
   // do i need another enum for all the possible fields (keys) in the HM?
	public User(String name, float balance) {
		//this.name = name;
		//this.balance = balance;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public abstract float computeBalance();
}
