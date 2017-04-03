package feedback;

public class Complain extends FeedBackDecorator {

	String complain;

	public Complain(FeedBack feedback) {
		super(feedback);
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}
	/**
	 * Displays the feedback
	 */
	@Override
	public void displayFeedback() {
		super.displayFeedback();
	}
}
