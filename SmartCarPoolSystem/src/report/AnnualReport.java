package report;

public class AnnualReport extends SeasonalReport {

	public AnnualReport(String reportDetails) {
		this();
		this.reportDetails = reportDetails;
	}

	public AnnualReport() {
		super();
		reportName = "Annual Report";
	}

	@Override
	public void displayReport() {
		super.displayReport();
	}
}
