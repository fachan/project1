package uber2;

public class Vehicle {
	private String type;
	private int capacity;
	
	public Vehicle(String type, int capacity) {
		this.type = type;
		this.capacity = capacity;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
}
