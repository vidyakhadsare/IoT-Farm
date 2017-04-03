package notification;

public class CustomerNotification extends NotificationCenter {
	
	private String Message;
	public CustomerNotification(Message message, String inputMessage) {
		super(message);
		Message = inputMessage;
	}

	@Override
	public void memberNotification() {
		customerNotification(Message);		
	}

}
