package ride;

/**
 * 
 * @author VidyaKhadsare
 */
import java.util.Scanner;

public class CancelState implements iState {

	iRide ride;

	/**
	 * Started State
	 */
	@Override
	public String startRide(int choice) {
		return "Ride Canceled!";
	}

	/**
	 * Waiting State
	 */
	@Override
	public String waitingRide(int choice) {
		return "Ride Canceled!";
	}

	/**
	 * Delayed State
	 */
	@Override
	public String delayRide(int choice) {
		return "Ride Canceled!";
	}

	@Override
	public String endRide(int choice) {
		return "Ride Canceled!";
	}

	@Override
	public String cancelRide(int choice) {
		ride.setState(new EndState(ride));
		return "Ride Canceled!";
	}

	@Override
	public String initiateRide() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Cancel State
	 */
	public CancelState(iRide r) {
		ride = r;
	}

}
