package demo;

/**
 * 
 */
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import DAO.MySQLAccess;
import feedback.Complain;
import feedback.DriverFeedback;
import feedback.Recommendation;
import membership.CardDetails;
import membership.Customer;
import membership.Driver;
import membership.LicenseDetails;
import membership.MemberShip;
import vehicle.Car;
import vehicle.Vehicle;

public class DriverMenu {

	private BufferedReader reader;

	public DriverMenu() {
		reader = Client.getReader();
	}
	MySQLAccess da = new MySQLAccess();
	/**
	 * Method takes all the inputs from driver and creates new customer
	 * 
	 * @return Driver
	 */

	public Driver registration() {

		try {
			Driver driver = new Driver();
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Registration for Driver");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter following details: ");

			System.out.println("First name: ");
			driver.setFirstName(reader.readLine());

			System.out.println("Last name: ");
			driver.setLastName(reader.readLine());

			System.out.println("Choose the user name: ");
			driver.setUserName(reader.readLine());

			System.out.println("Email address: ");
			driver.setEmailId(reader.readLine());

			System.out.println("Phone No: ");
			driver.setPhoneNumber(reader.readLine());

			CardDetails driverCardDetails = new CardDetails();
			System.out.println("Enter card details: ");

			System.out.println("Select card type [1. VISA/2. MASTERCARD]: ");
			int type = Integer.parseInt(reader.readLine());
			if (type == 1) {
				driverCardDetails.setCardType("VISA");
			} else
				driverCardDetails.setCardType("MASTERCARD");

			System.out.println("Card Number: ");
			driverCardDetails.setCardNumber(reader.readLine());

			System.out.println("Expiration month: ");
			driverCardDetails.setExpiryMonth(Integer.parseInt(reader.readLine()));

			System.out.println("Expiration year: ");
			driverCardDetails.setExpiryYear(Integer.parseInt(reader.readLine()));

			System.out.println("CVV: ");
			driverCardDetails.setCvv(Integer.parseInt(reader.readLine()));

			driver.setCardDetails(driverCardDetails);

			System.out.println("Choose membership type(1. Part/2. Full): ");
			int driverType = Integer.parseInt(reader.readLine());
			if (driverType == 2) {
				driver.setAvailability("Full");
			} else
				driver.setAvailability("part");

			System.out.println("Enter driving license details: ");
			LicenseDetails licenseDetails = new LicenseDetails();

			System.out.println("Driver License Number: ");
			licenseDetails.setLicenseNumber(reader.readLine());

			System.out.println("Licence expiration date [yyyy-mm]: ");
			licenseDetails.setValidUntil(reader.readLine());

			driver.setLicenseDetails(licenseDetails);

			System.out.println("Enter car details: ");
			Car car1 = new Car();

			System.out.println("License plate: ");
			car1.setLicensePlate(reader.readLine());

			System.out.println("Color :");
			car1.setColor(reader.readLine());

			System.out.println("Model (1.	Five seater/ 2.	Eight seater): ");
			int carModel = Integer.parseInt(reader.readLine());
			if (carModel == 2) {
				car1.setModel("Eight seater");
			} else
				car1.setModel("Five seater");

			driver.setVehicle(car1);

			System.out.println("Enter address: ");
			System.out.println("Enter address X co-ordinate: ");
			driver.setX(Integer.parseInt(reader.readLine()));

			System.out.println("Enter address y co-ordinate: ");
			driver.setY(Integer.parseInt(reader.readLine()));

			driver.setRating(5);

			MemberShip memberShip = new MemberShip();
			memberShip.receiveMembershipApplication();

			System.out.println("To complete the registration pay for membership [y/n] ?: ");

			if (reader.readLine().equalsIgnoreCase("y")) {
				System.out.println("$50 is debited from your card as a membership payment.");
			} else {
				System.out.println("Registration cancelled.");
				return null;
			}

			System.out.println("Submit the registration details? [y/n]: ");
			if (reader.readLine().equalsIgnoreCase("y")) {
				memberShip.qualifyApplicant(driver);

				MySQLAccess da = new MySQLAccess();
				int dri_id = da.addNewDriver(driver);
				memberShip.confirmMember();
				System.out.println("Your driver id is: " + dri_id);
				return driver;

			} else {
				System.out.println("Registration cancelled.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Some problem occured while registration.");
		return null;
	}

	/**
	 * Method asks for the driver id to search for the driver and returns the
	 * driver
	 * 
	 * @return Driver
	 */
	public Driver getDriverById() {

		Driver driver = new Driver();
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter driver id: ");
			int id = Integer.parseInt(reader.readLine());

			MySQLAccess da = new MySQLAccess();

			driver = da.getDriverById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * Method asks for the driver uername to search for the customer and returns
	 * the driver
	 * 
	 * @return Driver
	 */

	public Driver getDriverByUserName() {
		Driver driver = new Driver();
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter user name: ");
			String userName = reader.readLine();

			MySQLAccess da = new MySQLAccess();

			driver = da.getDriverByUserName(userName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * Method asks for the driver username and deletes the driver from database
	 */
	public void deleteDriver() {

		Driver driver = new Driver();
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter user name to delete account : ");
			String userName = reader.readLine();

			driver.setUserName(userName);
			MySQLAccess da = new MySQLAccess();

			da.deleteDriver(driver);

			System.out.println("Driver is deleted successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method asks the driver feedback and saves into the database
	 */
	public void addDriverFeedback() {
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Feedback for recent ride");
			System.out.println("-----------------------------------------------------------------");

			System.out.println("Enter driver user name: ");
			String usrName = reader.readLine();

			System.out.println("Enter comment: ");
			String comment = reader.readLine();

			System.out.println("Enter rating(1-5): ");
			int rating = Integer.parseInt(reader.readLine());

			System.out.println("Was customer on time? (y/n): ");
			String answer = (reader.readLine());
			Boolean wasOnTime;
			if (answer.equalsIgnoreCase("y"))
				wasOnTime = true;
			else
				wasOnTime = false;

			DriverFeedback driverFeedback = new DriverFeedback(comment, rating, wasOnTime);
			Recommendation driverRecommendation = new Recommendation(driverFeedback);
			Complain driverComplain = new Complain(driverFeedback);

			System.out.println("Want to add (Recommendation/Complain) [y/n] ?: ");
			if (reader.readLine().equalsIgnoreCase("y")) {
				System.out.println("1.Recommendation ");
				System.out.println("2.Complain ");
				int option = Integer.parseInt(reader.readLine());

				if (option == 1) {

					driverRecommendation.setRecommended(true);
					driverComplain.setComplain("");
					System.out.println("Customer is recommended by you! ");
					System.out.println("Thank you for recommendation. ");
				}
				if (option == 2) {
					driverRecommendation.setRecommended(false);
					System.out.println("Enter the complain : ");
					driverComplain.setComplain(reader.readLine());
					System.out.println("Complain is added ");
					System.out.println("We will get back to you about this complain. ");
				}
				System.out.println("Submit feedback? [y/n] :");
				if (reader.readLine().equalsIgnoreCase("y")) {
					MySQLAccess da = new MySQLAccess();
					da.addDriverFeedback(driverFeedback, usrName, driverRecommendation, driverComplain);
					System.out.println("Successfully submitted the feedback.");
				} else {
					System.out.println("Feedback submission cancelled.");
				}
			} else {
				System.out.println("Submit feedback? [y/n] :");
				if (reader.readLine().equalsIgnoreCase("y")) {
				
					da.addDriverFeedback(driverFeedback, usrName, driverRecommendation, driverComplain);
					System.out.println("Successfully submitted the feedback.");
				} else {
					System.out.println("Feedback submission cancelled.");
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Method asks for the driver username and update fields. Then updates the
	 * fields of driver
	 * 
	 * @return Driver
	 */
	public Driver updateDriverCard() {
		CardDetails driverCardDetails = new CardDetails();
		try {

			System.out.println();
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Update form for driver: ");
			System.out.println("-------------------------------------------------------------------");
			
			System.out.println("Enter customer user name: ");
			String usrName = reader.readLine();
			
			Driver driver = da.getDriverByUserName(usrName);
			
			System.out.println("Please enter following fields to update: ");
			
			System.out.println("Credit card details:  ");

			System.out.println("Select card type [1.VISA/ 2. MASTERCARD]: ");
			int type = Integer.parseInt(reader.readLine());
			if (type == 1) {
				driverCardDetails.setCardType("VISA");
			} else
				driverCardDetails.setCardType("MASTERCARD");

			System.out.println("Card Number: ");
			driverCardDetails.setCardNumber(reader.readLine());

			System.out.println("Expiration month: ");
			driverCardDetails.setExpiryMonth(Integer.parseInt(reader.readLine()));

			System.out.println("Expiration year: ");
			driverCardDetails.setExpiryYear(Integer.parseInt(reader.readLine()));

			System.out.println("CVV: ");
			driverCardDetails.setCvv(Integer.parseInt(reader.readLine()));

			driver.setCardDetails(driverCardDetails);

			System.out.println("Do you want to Submit update? [y:n]: ");
			if (reader.readLine().equalsIgnoreCase("y")) {

				MySQLAccess da = new MySQLAccess();
				da.updateDriverCard(driver);

				System.out.println("You are successfully Updated.");
				return driver;

			} else {
				System.out.println("Successfully Cancelled.");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Problem while updating driver information.");
		return null;
	}

	/**
	 * Method asks for the driver username and update fields. Then updates the
	 * fields of car
	 * 
	 * @return Customer
	 */
	public Driver updateDriverCar() {
		Car updatedCar = new Car();
		try {

			System.out.println();
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Update form for vehicle: ");
			System.out.println("-------------------------------------------------------------------");
			
			System.out.println("Enter customer user name: ");
			String usrName = reader.readLine();
			
			Driver driver = da.getDriverByUserName(usrName);
			System.out.println("Please enter following fields to update: ");
			System.out.println("Enter car details: ");

			System.out.println("License plate: ");
			updatedCar.setLicensePlate(reader.readLine());

			System.out.println("Color :");
			updatedCar.setColor(reader.readLine());

			System.out.println("Model (1. Five seater/ 2. Eight seater): ");
			int carModel = Integer.parseInt(reader.readLine());
			if (carModel == 2) {
				updatedCar.setModel("Eight seater");
			} else
				updatedCar.setModel("Five seater");

			driver.setVehicle(updatedCar);

			System.out.println("Do you want to Submit update? [y:n]: ");
			if (reader.readLine().equalsIgnoreCase("y")) {

				MySQLAccess da = new MySQLAccess();
				da.updateDriverCard(driver);

				System.out.println("Car is successfully Updated.");
				return driver;

			} else {
				System.out.println("Successfully Cancelled.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Problem while updating vehicle information.");
		return null;
	}
}
