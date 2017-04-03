package membership;

import java.time.LocalDate;

public class LicenseDetails {
	private String licenseNumber;
	private String validUntil;
	
	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}

	@Override
	public String toString() {
		return "License number: " + licenseNumber + ", Valid until: " + validUntil;
	}
	
	
}
