package notification;

public abstract class NotificationCenter {
	
		Message message;

		public NotificationCenter(){
			
		}			
		public NotificationCenter(Message message) {
			this.message = message;
		}	
		
		protected void customerNotification(String message) {
			this.message.customerNotification( message);
		}
		
		protected void driverNotification( String message) {
			this.message.driverNotification( message);
		}	
		public abstract void memberNotification();
}
