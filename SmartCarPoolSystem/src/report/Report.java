package report;

public abstract class Report {

	public Report() {
		// TODO Auto-generated constructor stub
	}
	
	public String reportName;
	public String reportDetails;

	public void displayReport() {
		System.out.println(reportName + ": " + this.reportDetails);
		System.out.println("");
	}

}
