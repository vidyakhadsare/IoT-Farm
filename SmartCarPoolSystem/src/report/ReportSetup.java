package report;

public class ReportSetup {

	public void reportSetup() {

		RideReport ride1 = new RideReport("Ride Report");

		ParkingReport parking1 = new ParkingReport("Parking Report");

		WeeklyReport weekly1 = new WeeklyReport("Weekly Report");
		weekly1.addReport(ride1);
		weekly1.addReport(parking1);
		
		AnnualReport annualReport = new AnnualReport("Annual Report");
		annualReport.addReport(weekly1);
			
		annualReport.displayReport();
	}
}
