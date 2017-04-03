package feedback;

public abstract class FeedBackDecorator extends FeedBack {

	protected FeedBack feedBack;
	public FeedBackDecorator(FeedBack feedback){
		this.feedBack = feedback;
	}
	
	/**
	 * Displays the feedback
	 */
	public void displayFeedback(){
		feedBack.displayFeedback();
	}
}
