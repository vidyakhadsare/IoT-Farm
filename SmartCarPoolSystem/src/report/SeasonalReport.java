package report;

import java.util.Vector;

public abstract class SeasonalReport extends Report {

	protected Vector<Report> directReports = new Vector<Report>();

	public void displayReport() {

		super.displayReport();

		if (directReports.size() > 0) {

			for (Report report : directReports) {

				report.displayReport();
			}
		}
	}

	public void addReport(Report report) {

		this.directReports.addElement(report);
	}

}
