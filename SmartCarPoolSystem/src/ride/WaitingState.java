package ride;

import java.util.Scanner;

public class WaitingState implements iState {

	iRide ride;

	public WaitingState(iRide r) {
		ride = r;
	}

	/**
	 * Started State
	 */
	@Override
	public String startRide(int choice) {
		return "Ride has been already started and waiting for customers to arrive!";
	}

	/**
	 * Waiting State
	 */
	@Override
	public String waitingRide(int choice) {

		switch (choice) {

		case 1:
			ride.setState(new StartedState(ride));
			ride.setStateName("Ride Started!");
			break;

		case 2:
			ride.setState(new CancelState(ride));
			ride.setStateName("Ride Canceled!");
			break;

		case 3:
			ride.setState(new DelayedState(ride));
			ride.setStateName("Ride Delayed!");
			break;

		case 4:
			ride.setState(new EndState(ride));
			ride.setStateName("Ride Ended!");
			break;

		case 5:
			System.out.println("Current ride state is : " + ride.getStateName());
			break;

		case 0:
			System.exit(0);

		default:
			System.out.println("Waiting for user input!");
			break;
		}
		return null;
	}

	/**
	 * Delayed State
	 */
	@Override
	public String delayRide(int choice) {
		return "Driver is currently waiting for customers to arrive!";
	}

	@Override
	public String endRide(int choice) {
		return "Driver is currently waiting for customers to arrive!";
	}

	/**
	 * Cancel State
	 */
	@Override
	public String cancelRide(int choice) {
		return "Driver is currently waiting for customers to arrive!";
	}

	@Override
	public String initiateRide() {
		// TODO Auto-generated method stub
		return null;
	}

}
