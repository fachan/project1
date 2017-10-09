package uber;

public class Route {
	private Location source;
	private Location destination;
	private int numPassengers;
	private float fare;
	//starttime
	
	public Route(Location source, Location destination, int numPassengers) {
		this.source = source;
		this.destination = destination;
		this.numPassengers = numPassengers;
	}
	
	public Location getSource() {
		return source;
	}
	
	public Location getDestination() {
		return destination;
	}
}
