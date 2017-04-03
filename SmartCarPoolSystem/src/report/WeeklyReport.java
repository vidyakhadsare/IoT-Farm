package report;

public class WeeklyReport extends SeasonalReport {

	public WeeklyReport(String reportDetails) {
		this();
		this.reportDetails = reportDetails;
	}

	public WeeklyReport() {
		super();
		reportName = "Weekly Report";
	}

	@Override
	public void displayReport() {
		super.displayReport();
	}

}
