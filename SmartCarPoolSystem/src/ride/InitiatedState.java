package ride;

import java.util.Scanner;

public class InitiatedState implements iState {

	private iRide ride;

	public InitiatedState(iRide r) {
		ride = r;
	}

	@Override
	public String initiateRide() {
		ride.setState(new WaitingState(ride));
		return "Ride has been initiated";
	}

	/**
	 * Started State
	 */
	@Override
	public String startRide(int choice) {
		return "Ride initiated now!";
	}

	/**
	 * Waiting State
	 */
	@Override
	public String waitingRide(int choice) {
		return "Ride initiated now!";
	}

	/**
	 * Delayed State
	 */
	@Override
	public String delayRide(int choice) {
		return "Ride initiated now!";
	}

	@Override
	public String endRide(int choice) {
		return "Ride initiated now!";
	}

	/**
	 * Cancel State
	 */
	@Override
	public String cancelRide(int choice) {
		return null;
	}
}
