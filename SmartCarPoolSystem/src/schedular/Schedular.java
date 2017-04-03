package schedular;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;
import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;
import notification.CustomerNotification;
import notification.EmailNotification;
import notification.Message;
import notification.NotificationCenter;
import parking.Parking;
import payment.CalculatePayment;
import payment.CardPayment;
import payment.EstimatePayment;
import payment.FiveSeaterCar;
import payment.OnlinePayment;

import payment.Payment;
import request.Location;
import request.Request;
import request.RideRequest;
import ride.Ride;
import route.RoutingContext;
import route.Utility;
import rules.RideRule;

public class Schedular implements iSchedular {

	RideRequest req;
	private Queue reqQueue = new LinkedList();
	private Queue matchingReqQueueLocation = new LinkedList();
	private Queue matchingReqQueueCarType = new LinkedList();
	private Queue matchedReqQueue = new LinkedList();
	private Queue acceptedReqQueue = new LinkedList();
	Queue day1 = new LinkedList<>(), day2 = new LinkedList<>(), day3 = new LinkedList<>();
	Scanner keyboard = new Scanner(System.in);
	String input = "";
	String driverName;
	MySQLAccess da = new MySQLAccess();
	Ride ride;

	ListIterator<Request> listIterator = (ListIterator<Request>) reqQueue.iterator();
	MappingStrategy mapStrategy;

	public Schedular(Queue reqQueue, String driverName) {
		this.reqQueue = reqQueue;
		this.driverName = driverName;
	}

	@Override
	public void scheduleRide() {

		/**
		 * 
		 * This method will decide which algorithm to choose at run time to Map
		 * request with driver
		 */

		MySQLAccess db = new MySQLAccess();

		MappingContext mappingCtx = new MappingContext();

		HashMap<String, Queue> hm = new HashMap<>();

		boolean ratingStrategy = false;
		listIterator = (ListIterator<Request>) reqQueue.iterator();
		while (listIterator.hasNext()) {
			RideRequest req = (RideRequest) listIterator.next();
			if (req.getDriverRating() == 5) {
				ratingStrategy = true;
				break;
			}
		}
		if (ratingStrategy) {
			System.out.println("\n Driver is going to be selected based on Rating!");
			hm = (HashMap<String, Queue>) mappingCtx.MapDriverAndRequest("Rating", reqQueue, db.getAllDrivers(),
					driverName);
		} else {
			System.out.println("\n Driver is going to be selected according to nearest location!");
			hm = (HashMap<String, Queue>) mappingCtx.MapDriverAndRequest("Location", reqQueue, db.getAllDrivers(),
					driverName);
		}
		day1 = hm.get("1");
		day2 = hm.get("2");
		day3 = hm.get("3");

		req = (RideRequest) day1.element();

		if (da.getDriverById(req.getDriverID()).getUserName().equalsIgnoreCase(driverName)) {
			if (day1 != null) {
				acceptRequestByDriver(day1);
				// Notify Customers about request acceptance
				NotifyRideAcceptance(day1);
			}
			if (day2.size() > 0) {
				acceptRequestByDriver(day2);
				// Notify Customers about request acceptance
				NotifyRideAcceptance(day2);
			}

			if (day3.size() > 0) {
				acceptRequestByDriver(day3);
				// Notify Customers about request acceptance
				NotifyRideAcceptance(day3);
			}
		}
		else
		{
			System.out.println("\n Sorry! No request are currently matching with you!");
		}
	}

	/**
	 * 
	 * This method will start the ride
	 */
	public void startRide() {

		ride = new Ride(day1);

		ride.initiateRide();
		int choice;

		do {
			System.out.println("\n ************Driver Menu*********");
			System.out.println("1.Start Ride ");
			System.out.println("2.Notify customer about the car cancelation due to failed car");
			System.out.println("3.Notify customer about the delay ");
			System.out.println("4.End Ride");

			System.out.println("\n ************Customer Menu*********!");
			System.out.println("5.Track the ride.");
			System.out.println("0.Back to Main menu");

			input = keyboard.nextLine();

			choice = Integer.parseInt(input);

			ride.waitingRide(choice);

			switch (choice) {
			case 1:
			ride.startRide(choice);
			//int id = da.addNewRide(ride);
			break;

			case 2:
				ride.cancelRide(choice);
				break;

			case 3:
				ride.delayRide(choice);
				break;

			case 4:
				ride.endRide(choice);
				int[] custArr = ride.getCustomer_ids();
				for(int i=0; i < custArr.length;i++){
					if(custArr[i] != 0){
						System.out.println("\nDear customer "+ da.getCustomerById(custArr[i]).getFirstName() + "," 
											+ " Kindly Pay for the ride!");
						payForRide();
					}
				}
				
				DoPayment();
				break;

			case 0:
				break;

			default:
				break;
			}
		} while (choice != 0);
	}

	/**
	 * 
	 * This method will check with the driver for acceptance
	 */
	private void acceptRequestByDriver(Queue reqQueue) {

		Customer customer;
		RideRequest request = new RideRequest();
		MySQLAccess db = new MySQLAccess();

		System.out.println("\n" + reqQueue.size() + " Customer request are matching with the you! ");
		listIterator = (ListIterator<Request>) reqQueue.iterator();

		while (listIterator.hasNext()) {
			req = (RideRequest) listIterator.next();
			customer = db.getCustomerByUserName(((RideRequest) req).getUserName());
			System.out.println("**********Customer List************");
			System.out.println("Customer Name : " + customer.getFirstName() + " " + customer.getLastName());
			System.out.println("Date : " + req.getDateTime());		
			
			System.out.println("\nDo you want to accept this ride request?");
			input = keyboard.nextLine();
			if (input.equalsIgnoreCase("y")) {
				acceptedReqQueue.add(req);
			}
		}
	}

	/**
	 * 
	 * Notify customer about acceptance
	 */
	private void NotifyRideAcceptance(Queue reqQueue) {

		listIterator = (ListIterator<Request>) reqQueue.iterator();
		Location commonSource = (Location) ((RideRequest) reqQueue.element()).getSource();
		while (listIterator.hasNext()) {
			RideRequest req = (RideRequest) listIterator.next();
			NotifyCustomer(req, commonSource);
		}
	}

	/**
	 * 
	 * Notification to customer
	 */
	public void NotifyCustomer(RideRequest req, Location commonSource) {	
		
		NotificationCenter notify;
		Message message;
		message = new EmailNotification();
		Driver driver = da.getDriverById(req.getDriverID());

		Customer cust = da.getCustomerByUserName(req.getUserName());
		
		
		int optimalRoute = FindRoutes(req.getSource().getX(), req.getSource().getY(), 
				req.getDestination().getX(), req.getDestination().getY());
		
		EstimatePayment estimate = new EstimatePayment(optimalRoute,2,req.getCarType());
		estimate.cost_for_distance();
		RideRule rule = new RideRule();
		notify = new CustomerNotification(message,
				"Dear " + cust.getFirstName() + " " + cust.getLastName() + ", "
						+ "Your Ride Request has been Accepted!" + "\n******Ride Details*****" + "\nDate and Time: "
						+ req.getDateTime() + "\nDrive Name: " + driver.getFirstName() + " " + driver.getLastName()
						+ "\nPick up point: " + commonSource.getX() + "," + commonSource.getY()
						+ "\nFare Estimation: " + estimate.cost_for_distance() +"\nDriver will wait only upto 2 mins!"
						+ "\nEstimated amount will be held from your account until the ride finishes!");
		notify.memberNotification();
	}

	public void FindClosestCustomers() {

		for (int i = 0; i < reqQueue.size(); i++) {
			RideRequest req = (RideRequest) reqQueue.remove();
			Location src = req.getSource();
			Location dest = req.getDestination();
			FindRoutes(src.getX(), src.getY(), dest.getX(), dest.getY());
		}
	}

	/**
	 * 
	 * Find routes
	 */
	public int FindRoutes(int source_i, int source_j, int dest_i, int dest_j) {

		Utility utility = new Utility();
		int source = utility.locateNode(source_i, source_j);
		int destination = utility.locateNode(dest_i, dest_j);

		RoutingContext rc = new RoutingContext();

		int optimalRoute = rc.route(source, destination);
		return optimalRoute;
	}

	/**
	 * 
	 * Payment Method
	 */
	public void DoPayment() {	
		Scanner scan = new Scanner(System.in);
		double park_hrs;
		String parkType;
		Payment pay1, pay2;
		CalculatePayment Typ1, Typ2, Typ3;
		CardPayment c1;
		OnlinePayment op1;
		double tot_ind_cost;
		int no_of_memb= (ride.getCustomer_ids()).length;
		String carType ="fiveSeater";
		int optimalRoute = FindRoutes(req.getSource().getX(), req.getSource().getY(), 
				req.getDestination().getX(), req.getDestination().getY());
		
		EstimatePayment estimate = new EstimatePayment(optimalRoute,no_of_memb,req.getCarType());
		estimate.cost_for_distance();

		if (carType.equalsIgnoreCase("fiveSeater")) {

			Typ1 = new  FiveSeaterCar(optimalRoute, no_of_memb);
			Typ1.cost_for_distance();
			System.out.println("---------------------------");
			System.out.println("For driver:");
			System.out.println("Specify Parking Type\n1.Covered\n2.Open");
			parkType = scan.next();
			System.out.println("Specify Parking Hours?");
			park_hrs = scan.nextDouble();

			Parking prk = new Parking(parkType, park_hrs, 3, Typ1.tot_cost);
			tot_ind_cost =  prk.getParkingVal();
			
			System.out.println("Total parking cost : " + tot_ind_cost);
			System.out.println("Choose Payment options:\n1.Card Payment \n2.Online Payment");
			String pay_type = scan.next();
			if (pay_type.equals("1") || pay_type.equalsIgnoreCase("Card Payment")) {
				c1 = new CardPayment(Typ1, no_of_memb);
				c1.make_payment();

			} else if (pay_type.equals("2") || pay_type.equals("Online Payment")) {
				op1 = new OnlinePayment(Typ1, no_of_memb);
				op1.make_payment();
			} else {
				System.out.println("\nIncorrect Option");
			}
		}

		else if (carType.equalsIgnoreCase("eightSeater")) {
			Typ2 = new FiveSeaterCar(optimalRoute, no_of_memb);
			Typ2.cost_for_distance();
			System.out.println("Specify Parking Type\n1.Covered\n2.Open");
			parkType = scan.next();
			System.out.println("Specify Parking Hours?");
			park_hrs = scan.nextDouble();
			Parking prk = new Parking(parkType, park_hrs, 3, Typ2.tot_cost);
			tot_ind_cost =  prk.getParkingVal();
			System.out.println("Total parking cost : " + tot_ind_cost);
			System.out.println("Choose Payment options:\n1.Card Payment \n2.Online Payment");
			String pay_type = scan.next();
			if (pay_type.equals("1") || pay_type.equalsIgnoreCase("Card Payment")) {
				c1 = new CardPayment(Typ2, no_of_memb);
				c1.make_payment();

			} else if (pay_type.equals("2") || pay_type.equals("Online Payment")) {
				op1 = new OnlinePayment(Typ2, no_of_memb);
				op1.make_payment();
			} else {
				System.out.println("\nIncorrect Option");
			}
		}
	}
	
	public void payForRide(){
		Scanner scan = new Scanner(System.in);
		Payment pay1, pay2;
		CalculatePayment Typ1 = null;
		CardPayment c1;
		OnlinePayment op1;
		System.out.println("---------------------------");
		System.out.println("Dear Customer choose Payment options:\n1.Card Payment \n2.Online Payment");
		String pay_type = scan.next();
		if (pay_type.equals("1") || pay_type.equalsIgnoreCase("Card Payment")) {
			c1 = new CardPayment(Typ1, (ride.getCustomer_ids()).length);
			c1.make_payment();
		} else if (pay_type.equals("2") || pay_type.equals("Online Payment")) {
			op1 = new OnlinePayment(Typ1, (ride.getCustomer_ids()).length);
			op1.make_payment();
		} else {
			System.out.println("\nIncorrect Option");
		}
	}
}
