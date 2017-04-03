package ride;

public interface iRide {
	public void initiateRide();
	public void startRide(int choice);
	public void waitingRide(int choice);
	public void delayRide(int choice);
	public void endRide(int choice);
	public void cancelRide(int choice);
	public void setState(iState s);
	public iState getState();
	public void setStateName(String inputStateName);
	public String getStateName();
}
