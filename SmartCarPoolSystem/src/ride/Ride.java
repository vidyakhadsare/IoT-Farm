package ride;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Queue;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;
import notification.CustomerNotification;
import notification.EmailNotification;
import notification.Message;
import notification.NotificationCenter;
import request.Location;
import request.Request;
import request.RideRequest;

public class Ride implements iRide {

	private iState state;
	private static int id;
	private int[] customer_ids = new int[3];
	private int driver_id;
	private String status;
	private Date start_time;
	private String end_time;
	private double fare;
	private Location srcLocation;
	private Location destLocation;
	private String stateName;
	Queue reqQueue;

	
	public Ride(Queue matchedReqAndDriverQueue) {
		state = new InitiatedState(this);
		setParams(matchedReqAndDriverQueue);
		id++;
		reqQueue = matchedReqAndDriverQueue;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Ride.id = id;
	}

	public int[] getCustomer_ids() {
		return customer_ids;
	}

	public void setCustomer_ids(int[] customer_ids) {
		this.customer_ids = customer_ids;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Location getSrcLocation() {
		return srcLocation;
	}

	public void setSrcLocation(Location srcLocation) {
		this.srcLocation = srcLocation;
	}

	public Location getDestLocation() {
		return destLocation;
	}

	public void setDestLocation(Location destLocation) {
		this.destLocation = destLocation;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	private void setParams(Queue matchedReqAndDriverQueue) {
		ListIterator<Request> listIterator = (ListIterator<Request>) matchedReqAndDriverQueue.iterator();
		int i = 0;
		while (listIterator.hasNext()) {
			RideRequest req = (RideRequest) listIterator.next();
			customer_ids[i++] = req.getCustomerID();
			driver_id = req.getDriverID();
			start_time = req.getDateTime();
			fare = req.getFare();
			srcLocation = req.getSource();
			destLocation = req.getDestination();
		}
	}

	@Override
	public void initiateRide() {
		System.out.println(state.initiateRide());
		setStateName("Ride Initiated!");
	}

	@Override
	public void startRide(int choice) {
		state.startRide(choice);
		NotifyRideStarted(reqQueue);
		setStateName("Ride Started!");
	}

	@Override
	public void waitingRide(int choice) {
		state.waitingRide(choice);
	}

	@Override
	public void delayRide(int choice) {
		state.delayRide(choice);
		NotifyRideDelayed(reqQueue);
		setStateName("Ride Delayed!");
	}

	@Override
	public void endRide(int choice) {
		System.out.println(state.endRide(choice));
		setStateName("Ride Ended!");
		NotifyRideEnded(reqQueue);
	}

	@Override
	public void cancelRide(int choice) {
		state.cancelRide(choice);
		setStateName("Ride Canceled!");
		NotifyRideCanceled(reqQueue);
	}

	@Override
	public void setState(iState s) {
		state = s;
	}

	@Override
	public iState getState() {
		return state;
	}

	public int getRideID() {
		return id;
	}

	public void setEndTime() {

	}

	public double getFare() {
		return fare;
	}

	public Location getSource() {
		return srcLocation;
	}

	public Location getDestination() {
		return destLocation;
	}

	public void setStateName(String inputStateName) {
		stateName = inputStateName;
	}

	public String getStateName() {
		return stateName;
	}

	private void NotifyRideDelayed(Queue reqQueue) {

		ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();
		while (listIterator.hasNext()) {
			RideRequest req = (RideRequest) listIterator.next();
			NotifyCustomer(req, "Ride has been delayed, Ride is starting again soon!");
		}
	}

	private void NotifyRideCanceled(Queue reqQueue) {

		ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();
		while (listIterator.hasNext()) {
			RideRequest req = (RideRequest) listIterator.next();
			NotifyCustomer(req, "Ride has been canceled, Sorry for inconvenience!");
		}
	}

	private void NotifyRideStarted(Queue reqQueue) {

		ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();
		while (listIterator.hasNext()) {
			RideRequest req = (RideRequest) listIterator.next();
			NotifyCustomer(req, ", Ride started!");
		}
	}

	private void NotifyRideEnded(Queue reqQueue) {

		ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();
		while (listIterator.hasNext()) {
			RideRequest req = (RideRequest) listIterator.next();
			NotifyCustomer(req, ", Ride ended!");
		}
	}

	public void NotifyCustomer(RideRequest req, String messageToCustomer) {
		MySQLAccess da = new MySQLAccess();
		NotificationCenter notify;
		Message message;
		message = new EmailNotification();
		Driver driver = da.getDriverById(req.getDriverID());

		Customer cust = da.getCustomerByUserName(req.getUserName());

		notify = new CustomerNotification(message,
				"Dear " + cust.getFirstName() + " " + cust.getLastName() + messageToCustomer);

		notify.memberNotification();
	}

	@Override
	public String toString() {
		return "Ride details : "+ "\n"+ "Customer ids: " + Arrays.toString(customer_ids) + ", Driver id: " + driver_id
				+ ", Fare: " + fare +", Status: " +status;
	}
}
