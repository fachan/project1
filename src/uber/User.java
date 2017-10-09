package uber;

public abstract class User {
	protected String name;
	protected float balance;
	
	public User(String name, float balance) {
		this.name = name;
		this.balance = balance;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public abstract float computeBalance();
}
