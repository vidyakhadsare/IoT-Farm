package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;
import report.ReportSetup;
import specialOffer.CustomerGroup;
import specialOffer.CustomerOffers;

import specialOffer.Offers;

public class AdminMenu {

	private BufferedReader reader;

	public AdminMenu() {
		reader = Client.getReader();
	}

	/**
	 * Method displays the list of all customers which are fetched from database
	 */
	public void getListOfCustomers() {
		try {
			System.out.println("--------------------------------------------------------------");
			System.out.println("List of all customers: ");
			System.out.println("--------------------------------------------------------------");
			MySQLAccess da = new MySQLAccess();
			List<Customer> customerList = null;
			customerList = da.getAllCustomers();
			if (customerList != null) {
				for (Customer customer : customerList) {
					System.out.println();
					System.out.println(customer.toString());
				}
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method displays the list of all drivers which are fetched from database
	 */
	public void getListOfDrivers() {
		try {
			System.out.println("--------------------------------------------------------------");
			System.out.println("List of all drivers: ");
			System.out.println("--------------------------------------------------------------");
			MySQLAccess da = new MySQLAccess();
			Queue<Driver> driverQueue = null;
			driverQueue = da.getAllDrivers();
			if (driverQueue != null) {
				for (Driver driver : driverQueue) {
					System.out.println();
					System.out.println(driver.toString());
				}
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method generates the reports
	 */
	public void generateReports() {
		ReportSetup setup = new ReportSetup();
		setup.reportSetup();
	}

	/**
	 * Method sends the notification to all the users about the
	 */
	public void NotifyOffers() {
		MySQLAccess MSA = new MySQLAccess();
		ArrayList<Customer> customerList = MSA.getAllCustomers();
		Scanner sc = new Scanner(System.in);		

		System.out.println("Change customer Discount percentage to:");
		double pct = sc.nextDouble();
		
		CustomerOffers offer = new CustomerOffers(pct);
		for(int i = 0; i< customerList.size(); i++)
		{
			CustomerGroup cust = new CustomerGroup(customerList.get(i).getFirstName() + " "
											    	+ customerList.get(i).getLastName());			
			offer.addObservre(cust);			
		}
	
		offer.setDiscount(pct);
	}
}
