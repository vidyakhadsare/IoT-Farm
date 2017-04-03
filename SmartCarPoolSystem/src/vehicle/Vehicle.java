package vehicle;

public abstract class Vehicle {
	private String licensePlate;
	
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	@Override
	public String toString() {
		return "License Plate: " + licensePlate + " ";
	}
	
}
