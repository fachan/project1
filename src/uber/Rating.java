package uber;

public class Rating {
	private int score;
	
	public Rating(int score) {
		this.score = score;
	}
	
	public int getRating() {
		return this.score;
	}
	
	public int calculateRating() {
		return 0;
	}
	
	public int compareTo(Rating rating2) {
	   if (this.score > rating2.getRating()) {
	      return 1;
	   } else if (this.score < rating2.getRating()) {
	      return -1;
	   }
	   
	   return 0;
	}
}
