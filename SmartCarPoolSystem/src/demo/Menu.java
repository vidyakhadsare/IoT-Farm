package demo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.sql.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;

import org.omg.CORBA.Request;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;

import membership.LicenseDetails;
import membership.MemberShip;
import payment.CardPayment;
import report.ReportSetup;
import request.RideRequest;
import schedular.Schedular;
import vehicle.Car;
import vehicle.Vehicle;

public class Menu {

	private Customer customer = new Customer();
	private AdminMenu adminMenu = new AdminMenu();
	private DriverMenu driverMenu = new DriverMenu();
	private CustomerMenu customerMenu = new CustomerMenu();
	private Driver driver = new Driver();

	RideRequest req;
	private Queue reqQueue = new LinkedList();
	private Queue reqQueueThreeDays = new LinkedList();
	Scanner keyboard = new Scanner(System.in);
	String input = "";
	Customer cust = new Customer();
	RideRequest request;
	MySQLAccess db = new MySQLAccess();
	ListIterator<RideRequest> listIterator = (ListIterator<RideRequest>) reqQueue.iterator();

	public Menu() {
		while (true)
			displayMainMenu();
	}

	/*
	 * Created by shital
	 */
	private void displayMainMenu() {
		// This is the main menu for the system
		System.out.println();
		System.out.println("--------Welcome to SmartPool system--------");
		System.out.println("-------------------------------------------");
		System.out.println("1. Customer Menu");
		System.out.println("2. Driver Menu");
		System.out.println("3. Admin Menu");
		System.out.println("0. Exit system");
		System.out.println("Enter menu option : ");
		try {
			String answer = Client.getReader().readLine();
			if (answer.isEmpty() || answer.length() >= 2) {
				System.out.println("Input is wrong. Please select again.");
				displayMainMenu();
			} else {
				int input = Integer.parseInt(answer);
				switch (input) {
				case 0:
					System.out.println("Successfully exited the system");
					System.exit(0);
					break;
				case 1:
					displayCustomerMenu();
					break;
				case 2:
					displayDriverMenu();
					break;
				case 3:
					displayAdminMenu();
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayCustomerMenu() {

		try {
			while (true) {
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.println("Welcome Customer");
				System.out.println("1. Register");
				System.out.println("2. View account");
				System.out.println("3. Update account");
				System.out.println("4. Delete account");
				System.out.println("5. Request a ride");
				System.out.println("6. Give feedback for recent ride");
				System.out.println("0. Back to Main Menu");
				System.out.println("Enter menu option: ");
				String line = Client.getReader().readLine();
				if (line.isEmpty() || line.length() >= 2) {
					System.out.println("Input is wrong. Please select again.");
					displayMainMenu();
				} else {
					int option = Integer.parseInt(line);
					switch (option) {
					case 0:
						break;
					case 1:
						customer = customerMenu.registration();
						break;
					case 2:
						customer = customerMenu.getCustomerByUserName();
						if (customer != null) {
							System.out.println(customer.toString());
						} else
							System.out.println("Wrong id! Try again.");
						break;
					case 3:
						customer = customerMenu.updateCustomer();
						if (customer != null) {
							System.out.println(customer.toString());
						}
						break;
					case 4:
						customerMenu.deleteCustomer();
						break;
					case 5:
						System.out.println("\nDear Customer, Please enter request details:");
						acceptRequest();
						break;

					case 6:
						customerMenu.addCustomerFeedback();
						break;
					}
					if (option == 0) {
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 */
	private void displayDriverMenu() {
		Schedular sc = null;
		try {
			while (true) {
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.println("Welcome Driver");
				System.out.println("1. Register");
				System.out.println("2. View account");
				System.out.println("3. Update account");
				System.out.println("4. Delete account");
				System.out.println("5. Update Car");
				System.out.println("6. Check for ride requests");
				System.out.println("7. Initiate the ride");
				System.out.println("8. Give feedback for customer");
				System.out.println("0. Back to Main Menu");
				System.out.println("Enter menu option: ");
				String line = Client.getReader().readLine();
				if (line.isEmpty() || line.length() >= 2) {
					System.out.println("Input is wrong. Please select again.");
					displayMainMenu();
				} else {
					int option = Integer.parseInt(line);
					switch (option) {
					case 0:
						break;
					case 1:
						driver = driverMenu.registration();
						break;
					case 2:
						driver = driverMenu.getDriverByUserName();
						if (driver != null) {
							System.out.println(driver.toString());
						} else
							System.out.println("Wrong id! Try again.");
						break;
					case 3:
						driver = driverMenu.updateDriverCard();
						if (driver != null) {
							System.out.println(driver.toString());
						}
						break;
					case 4:
						driverMenu.deleteDriver();
						break;
					case 5:
						driver = driverMenu.updateDriverCar();
						if (driver != null) {
							System.out.println(driver.toString());
						}
						break;
					case 6:
						System.out.println("Enter user name : ");
						String inputName = Client.getReader().readLine();
						selectRequestForThreeDays();
						sc = new Schedular(reqQueueThreeDays, inputName);
						sc.scheduleRide();
						break;
					case 7:
						sc.startRide();
						break;
					case 8:
						driverMenu.addDriverFeedback();
						break;
					}
					if (option == 0) {
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayAdminMenu() {
		try {
			while (true) {
				adminMenu = new AdminMenu();
				System.out.println();
				System.out.println("-------------------------------------------");
				System.out.println("Welcome Admin");
				System.out.println("1. List of Customers");
				System.out.println("2. List of Drivers");
				System.out.println("3. Generate Reports");
				System.out.println("4. Notify Offers to Customers");
				System.out.println("0. Back to Main Menu");
				System.out.println("Enter menu option: ");
				String line = Client.getReader().readLine();
				if (line.isEmpty() || line.length() >= 2) {
					System.out.println("Input is wrong. Please select again.");
					displayMainMenu();
				} else {
					int option = Integer.parseInt(line);
					switch (option) {
					case 0:
						break;
					case 1:
						adminMenu.getListOfCustomers();
						break;
					case 2:
						adminMenu.getListOfDrivers();
						break;
					case 3:
						adminMenu.generateReports();
						break;
					case 4:
						adminMenu.NotifyOffers();
						break;
					}
					if (option == 0) {
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Accept ride request from the customer
	 *
	 */

	public void acceptRequest() {

		request = new RideRequest();
		request.acceptUserName();
		String userName = request.getUserName();
		while (true) {
			request.acceptSource();
			request.acceptDestination();

			Date date = null;
			while (date == null) {
				date = request.getInputBookingDate();
				if (date == null) {
					System.out.println("Please enter the date in proper format.");
				}
			}
			request.setDateTime(date);
			request.acceptCarType();
			request.acceptDriverRating();

			request.setUserName(userName);
			request.setCustomerID(db.getCustomerByUserName(request.getUserName()).getMemberId());
			reqQueue.add(request);

			System.out.println("Do you want request more rides? (Y / N): ");
			input = keyboard.nextLine();

			if (!input.equalsIgnoreCase("y")) {
				break;
			}
			request = new RideRequest();
		}
	}

	/**
	 * A function to create a queue only for next 3 days after the date entered
	 * by the driver
	 *
	 */

	public void selectRequestForThreeDays() {

		java.util.Date parsed = null;
		listIterator = (ListIterator<RideRequest>) reqQueue.iterator();

		// Accept the date
		try {
			System.out.println("Please enter date to check ride schedule[MM/dd/yyyy hh:mm]: ");

			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
			input = keyboard.nextLine();
			parsed = (java.util.Date) format.parse(input);
		} catch (Exception e) {
		}

		// Calculate date after 3 days and select the requests baseed on it
		Date nextDate = new java.sql.Date(parsed.getTime() + 3 * 24 * 60 * 60 * 1000);
		while (listIterator.hasNext()) {
			req = (RideRequest) listIterator.next();
			if (req.getDateTime().before(nextDate)) {
				reqQueueThreeDays.add(req);
			}
		}
	}
}
