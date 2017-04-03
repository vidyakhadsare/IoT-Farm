package membership;

import java.time.LocalDate;
import java.util.Date;

import feedback.Complain;
import feedback.CustomerFeedback;
import feedback.DriverFeedback;
import feedback.Recommendation;
import payment.CardPayment;
import vehicle.Vehicle;

public class Driver extends Member {
	private String availability;
	private LicenseDetails licenseDetails;
	private Vehicle vehicle;
	private boolean readyToAssist;
	private int rating;

	private int X;
	private int Y;


	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public LicenseDetails getLicenseDetails() {
		return licenseDetails;
	}

	public void setLicenseDetails(LicenseDetails licenseDetails) {
		this.licenseDetails = licenseDetails;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
	public void setReadytoAssist(boolean assist) {
		readyToAssist = assist;
	}
	public boolean getReadytoAssist() {
		return readyToAssist;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		String member = super.toString();
		String license = licenseDetails.toString();
		String vehicledetails = vehicle.toString();
		return "Driver Details: "+"\n" + member +", Rating: "+ rating+"\n"+"Availability: " 
				+ availability +"\n" + "LicenseDetails:"+"\n" + license +"\n"+ "Vehicle Details: " +"\n"+ vehicledetails;
	}
}
