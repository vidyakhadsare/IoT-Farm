package ride;

public class DelayedState implements iState {

	iRide ride;

	public DelayedState(iRide r) {
		ride = r;
	}

	/**
	 * Started State
	 */
	@Override
	public String startRide(int choice) {
		return "Ride has been already started";
	}

	/**
	 * Waiting State
	 */
	@Override
	public String waitingRide(int choice) {
		return "Ride started!";
	}

	/**
	 * Delayed State
	 */
	@Override
	public String delayRide(int choice) {
		ride.setState(new StartedState(ride));
		return "Dear customers, sorry for delay.";
	}

	@Override
	public String endRide(int choice) {
		return "Ride ended!";
	}

	/**
	 * Cancel State
	 */
	@Override
	public String cancelRide(int choice) {
		return "Ride is currently delayed and still continuing";
	}

	@Override
	public String initiateRide() {
		// TODO Auto-generated method stub
		return null;
	}

}
