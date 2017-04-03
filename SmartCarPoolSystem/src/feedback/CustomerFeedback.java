package feedback;

public class CustomerFeedback extends FeedBack {

	boolean wasCarClean;;

	
	public boolean wasCarClean() {
		return wasCarClean;
	}

	public void setWasCarClean(boolean wasCarClean) {
		this.wasCarClean = wasCarClean;
	}

	public CustomerFeedback(String comment, int rating, boolean wasCarClean) {
		setComment(comment);
		setRating(rating);
		setWasCarClean(wasCarClean);
	}
	/**
	 * Displays the feedback
	 */
	@Override
	public void displayFeedback() {
		String feedbackBody = super.toString();
		System.out.println("CustomerFeedback:"+"\n"+feedbackBody+ ", Was car clean?" + wasCarClean);
	}
}
