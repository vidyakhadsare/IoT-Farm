package ride;

import java.util.Scanner;

public class StartedState implements iState {

	private iRide ride;

	public StartedState(iRide r) {
		ride = r;
	}

	/**
	 * Started State
	 */
	@Override
	public String startRide(int choice) {
		ride.setState(new WaitingState(ride));
		return "Ride Started!";
	}

	/**
	 * Waiting State
	 */
	@Override
	public String waitingRide(int choice) {
		return "Ride just started!";
	}

	/**
	 * Delayed State
	 */
	@Override
	public String delayRide(int choice) {
		return "Ride just started!";
	}

	@Override
	public String endRide(int choice) {
		return "Ride just started!";
	}

	/**
	 * Cancel State
	 */
	@Override
	public String cancelRide(int choice) {
		return "Ride just started!";

	}

	@Override
	public String initiateRide() {
		return "Ride just started!";
	}

}
