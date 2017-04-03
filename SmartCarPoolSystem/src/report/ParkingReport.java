package report;

import java.util.List;

import DAO.MySQLAccess;
import parking.Parking;
import ride.Ride;

public class ParkingReport extends Report {

	public ParkingReport(String reportDetails) {
		this();
		this.reportDetails = reportDetails;
	}

	public ParkingReport() {
		reportName = "Parking Report";
	}

	@Override
	public void displayReport() {
		super.displayReport();
		
		MySQLAccess da = new MySQLAccess();	
		List<Parking> parkingList = null;
		parkingList = 	da.getAllParking();
		if (parkingList != null) {
			for (Parking parking : parkingList) {
				System.out.println();
				System.out.println(parking.toString());
			}
		}
		System.out.println();	
	}
}
