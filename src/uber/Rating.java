package uber;

public class Rating {
	private double score;
	private int numRatings;
	
	public Rating(double d, int numRatings) {
		this.score = d;
		this.numRatings = numRatings;
	}
	
	public double getRating() {
		return this.score;
	}
	
	public void setRating(double newScore) {
	   this.score = newScore;
	}
	
	public int getNumRatings() {
	   return this.numRatings;
	}
	
	public void setNumRatings(int newNum) {
	   this.numRatings = newNum;
	}
	
	public void addRating(double newScore) {
	   double s = getRating();
	   int ratings = getNumRatings();
	   
	   s = s * ratings;
	   s = s + newScore;

	   ratings = ratings + 1;

	   setRating(s / ratings);
	   setNumRatings(ratings);
	}
	
	public int compareTo(Rating rating2) {
	   if (this.score > rating2.getRating()) {
	      return -1;
	   } else if (this.score < rating2.getRating()) {
	      return 1;
	   }
	   
	   return 0;
	}
}
