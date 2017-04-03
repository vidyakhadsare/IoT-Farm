package notification;

public class EmailNotification extends Message {

	@Override
	public void customerNotification(String message) {
		System.out.println("Email Notification to customer : " + message);
	}

	@Override
	public void driverNotification(String message) {
		System.out.println("Email Notification to driver : " + message);
	}	

}
