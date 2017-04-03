package demo;
/**
 * 
 */

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import DAO.MySQLAccess;
import feedback.Complain;
import feedback.CustomerFeedback;
import feedback.Recommendation;
import membership.CardDetails;
import membership.Customer;
import membership.Driver;
import membership.MemberShip;

public class CustomerMenu {

	private BufferedReader reader;

	public CustomerMenu() {
		reader = Client.getReader();
	}
	
	MySQLAccess da = new MySQLAccess();
/**
 * Method takes all the inputs from customer and creates new customer 
 * @return Customer
 */
	public Customer registration() {

		try {

			Customer customer = new Customer();
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Registration for customer");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter following details: ");

			System.out.println("First name: ");
			customer.setFirstName(reader.readLine());

			System.out.println("Last name: ");
			customer.setLastName(reader.readLine());

			System.out.println("Choose the user name: ");
			customer.setUserName(reader.readLine());

			System.out.println("Email address: ");
			customer.setEmailId(reader.readLine());

			System.out.println("Phone No: ");
			customer.setPhoneNumber(reader.readLine());

			System.out.println("Membership Plan [1. Basic/2. Premium]: ");
			int membershipType = Integer.parseInt(reader.readLine());
			if (membershipType == 2) {
				customer.setCustomerType("Premium");
			} else
				customer.setCustomerType("Basic");

			CardDetails customerCardDetails = new CardDetails();
			System.out.println("Enter card details: ");

			System.out.println("Select card type [1.VISA/ 2. MASTERCARD]: ");
			int type = Integer.parseInt(reader.readLine());
			if (type == 1) {
				customerCardDetails.setCardType("VISA");
			} else
				customerCardDetails.setCardType("MASTERCARD");

			System.out.println("Card Number: ");
			customerCardDetails.setCardNumber(reader.readLine());

			System.out.println("Expiration month: ");
			customerCardDetails.setExpiryMonth(Integer.parseInt(reader.readLine()));

			System.out.println("Expiration year: ");
			customerCardDetails.setExpiryYear(Integer.parseInt(reader.readLine()));

			System.out.println("CVV: ");
			customerCardDetails.setCvv(Integer.parseInt(reader.readLine()));

			customer.setCardDetails(customerCardDetails);

			MemberShip memberShip = new MemberShip();
			memberShip.receiveMembershipApplication();

			System.out.println("To complete the registration pay for membership [y/n] ?: ");

			if (reader.readLine().equalsIgnoreCase("y")) {
				if (membershipType == 2)
					System.out.println("$50 is debited from your card as a membership payment.");
				else
					System.out.println("$20 is debited from your card as a membership payment.");
			} else {
				System.out.println("Registration cancelled.");
				return null;
			}

			System.out.println("Submit the registration details? [y/n]: ");
			if (reader.readLine().equalsIgnoreCase("y")) {

				memberShip.qualifyApplicant(customer);

				MySQLAccess da = new MySQLAccess();
				int cust_id = da.addNewCustomer(customer);
				memberShip.confirmMember();
				System.out.println("Your customer id is: " + cust_id);
				return customer;

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
 * Method asks for the customer id to search for the customer and returns the customer
 * @return Customer
 */
	public Customer getCustomerById() {

		Customer customer = new Customer();
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter customer id: ");
			int id = Integer.parseInt(reader.readLine());

			MySQLAccess da = new MySQLAccess();

			customer = da.getCustomerById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	/**
	 * Method asks for the customer uername to search for the customer and returns the customer
	 * @return Customer
	 */
	public Customer getCustomerByUserName() {

		Customer customer = new Customer();
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter user name: ");
			String userName = reader.readLine();

			MySQLAccess da = new MySQLAccess();

			customer = da.getCustomerByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
/**
 * Method asks the customer feedback and saves into the database
 */
	public void addCustomerFeedback() {
		try {

			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Feedback for the recent ride");
			System.out.println("-----------------------------------------------------------------");

			System.out.println("Enter customer user name: ");
			String usrName = reader.readLine();

			System.out.println("Enter comment: ");
			String comment = reader.readLine();

			System.out.println("Enter rating(1-5): ");
			int rating = Integer.parseInt(reader.readLine());

			Boolean isCarClean;

			System.out.println("Was car clean? (y/n): ");
			String answer = (reader.readLine());

			if (answer.equalsIgnoreCase("y"))
				isCarClean = true;
			else
				isCarClean = false;

			CustomerFeedback customerFeedback = new CustomerFeedback(comment, rating, isCarClean);
			Recommendation customereRecommendation = new Recommendation(customerFeedback);
			Complain customerComplain = new Complain(customerFeedback);
			
			System.out.println("Want to add  (Recommendation/Complain) [y/n] ?: ");
			if (reader.readLine().equalsIgnoreCase("y")) {
				System.out.println("1.Recommendation ");
				System.out.println("2.Complain ");
				int option = Integer.parseInt(reader.readLine());
				if (option == 1) {
					customereRecommendation.setRecommended(true);
					customerComplain.setComplain("");
					System.out.println("Driver is recommended by you: ");
					System.out.println("Thank you for recommendation. ");
				}
				if (option == 2) {
					customereRecommendation.setRecommended(false);
					System.out.println("Enter the complain : ");
					customerComplain.setComplain(reader.readLine());
					System.out.println("Complain is added ");
					System.out.println("We will get back to you about this complain. ");
				}
				System.out.println("Submit feedback? [y/n] :");
				if (reader.readLine().equalsIgnoreCase("y")) {
					MySQLAccess da = new MySQLAccess();
					da.addCustomerFeedback(customerFeedback, usrName, customereRecommendation, customerComplain);
					System.out.println("Successfully submitted the feedback.");
	
				} else {
					System.out.println("Feedback submission cancelled.");
				}
			} else {
				System.out.println("Submit feedback? [y/n] :");
				if (reader.readLine().equalsIgnoreCase("y")) {
				
					da.addCustomerFeedback(customerFeedback, usrName, customereRecommendation, customerComplain);
					System.out.println("Successfully submitted the feedback.");
				} else {
					System.out.println("Feedback submission cancelled.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * Method asks for the customer username and deletes the customer from database
 */
	public void deleteCustomer() {

		Customer customer = new Customer();
		try {
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Enter user name to delete account : ");
			String userName = reader.readLine();

			customer.setUserName(userName);
			MySQLAccess da = new MySQLAccess();

			da.deleteCustomer(customer);

			System.out.println("Customer is deleted successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * Method asks for the customer username and update fields. Then updates the fields of customer
 * @return Customer
 */
	public Customer updateCustomer() {
		CardDetails customerCardDetails = new CardDetails();
		try {

			System.out.println();
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Update form for customer: ");
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Enter customer user name: ");
			String usrName = reader.readLine();
			
			Customer cust = da.getCustomerByUserName(usrName);
			
			System.out.println("Please enter following fields to update: ");
			System.out.println("Credit card details:  ");

			System.out.println("Select card type [1.VISA/ 2. MASTERCARD]: ");
			int type = Integer.parseInt(reader.readLine());
			if (type == 1) {
				customerCardDetails.setCardType("VISA");
			} else
				customerCardDetails.setCardType("MASTERCARD");

			System.out.println("Card Number: ");
			customerCardDetails.setCardNumber(reader.readLine());

			System.out.println("Expiration month: ");
			customerCardDetails.setExpiryMonth(Integer.parseInt(reader.readLine()));

			System.out.println("Expiration year: ");
			customerCardDetails.setExpiryYear(Integer.parseInt(reader.readLine()));

			System.out.println("CVV: ");
			customerCardDetails.setCvv(Integer.parseInt(reader.readLine()));

			cust.setCardDetails(customerCardDetails);

			System.out.println("Do you want to Submit update? [y:n]: ");
			if (reader.readLine().equalsIgnoreCase("y")) {

				MySQLAccess da = new MySQLAccess();
				da.updateCustomer(cust);

				System.out.println("You are successfully Updated.");
				return cust;

			} else {
				System.out.println("Successfully Cancelled.");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Problem while updating customer.");
		return null;
	}
}
