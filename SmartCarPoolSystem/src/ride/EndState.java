package ride;

import java.util.Scanner;

public class EndState implements iState {

	iRide ride;

	public EndState(iRide r) {

		ride = r;
	}

	/**
	 * Started State
	 */
	@Override
	public String startRide(int choice) {

		return "Ride has ended!";
	}

	/**
	 * Waiting State
	 */
	@Override
	public String waitingRide(int choice) {
		return "Ride has ended!";
	}

	/**
	 * Delayed State
	 */
	@Override
	public String delayRide(int choice) {
		return "Ride has ended!";
	}

	@Override
	public String endRide(int choice) {

		return "Dear customer, ride ended! Thank you! See you again!\n";
	}

	/**
	 * Cancel State
	 */
	@Override
	public String cancelRide(int choice) {
		return "Ride has ended!";
	}

	@Override
	public String initiateRide() {
		// TODO Auto-generated method stub
		return null;
	}

}
