package feedback;

public abstract class FeedBack {
	protected String comment;
	int rating;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
		
	@Override
	public String toString() {
		return "Comment: " + comment + ", Rating:" + rating ;
	}

	abstract public void displayFeedback();
}