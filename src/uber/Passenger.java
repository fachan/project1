package uber;

public class Passenger extends User implements Deliverable {
	private Location source;
	private Location destination;
	// time
	
	public Passenger(String name, float balance) {
		super(name, balance);
	}
	
	public float computeBalance() {
		return this.balance;
	}
}
