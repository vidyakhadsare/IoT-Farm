package feedback;

public class DriverFeedback extends FeedBack {

	boolean wasCustomerOnTime;

	public boolean wasCustomerOnTime() {
		return wasCustomerOnTime;
	}

	public void setCustomerOnTime(boolean wasCustomerOnTime) {
		this.wasCustomerOnTime = wasCustomerOnTime;
	}

	public DriverFeedback(String comment, int rating, boolean wasCustomerOnTime) {
		setComment(comment);
		setRating(rating);
		setCustomerOnTime(wasCustomerOnTime);
	}

	@Override
	public void displayFeedback() {
		String feedbackBody = super.toString();
		System.out.println("DustomerFeedback:"+"\n"+feedbackBody+ ", Was customer on time" + wasCustomerOnTime);
	}
}
