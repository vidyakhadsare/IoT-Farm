package report;

import java.util.List;

import DAO.MySQLAccess;
import ride.Ride;

public class RideReport extends Report {

	public RideReport(String reportDetails) {
		this();
		this.reportDetails = reportDetails;
	}

	public RideReport() {
		reportName = "Ride Report";		
	}

	@Override
	public void displayReport() {
		super.displayReport();
		MySQLAccess da = new MySQLAccess();	
		List<Ride> rideList = null;
		rideList = 	da.getAllRides();
		if (rideList != null) {
			for (Ride ride : rideList) {
				System.out.println();
				System.out.println(ride.toString());
			}
		}
		System.out.println();		
	}
}
