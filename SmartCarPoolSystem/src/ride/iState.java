package ride;

public interface iState {
	public String initiateRide();
	public String startRide(int choice);
	public String waitingRide(int choice);
	public String delayRide(int choice);
	public String endRide(int choice);
	public String cancelRide(int choice);
}
