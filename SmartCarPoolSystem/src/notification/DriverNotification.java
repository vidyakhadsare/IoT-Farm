package notification;

public class DriverNotification extends NotificationCenter {

	private String Message;
	public DriverNotification(Message message, String inputMessage) {
		super(message);
		Message = inputMessage;
	}

	@Override
	public void memberNotification() {
		driverNotification(Message);		
	}
}
