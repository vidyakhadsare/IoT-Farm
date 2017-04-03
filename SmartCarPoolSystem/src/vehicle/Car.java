package vehicle;

public class Car extends Vehicle {
	private String color;
	private String model;
	private boolean carStatus;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public boolean getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(boolean carStatus) {
		this.carStatus = carStatus;
	}

	@Override
	public String toString() {
		String vehicleDetail = super.toString();
		return vehicleDetail + "Color: " + color + ", Model: " + model + ", Car status: " + carStatus;
	}
}
