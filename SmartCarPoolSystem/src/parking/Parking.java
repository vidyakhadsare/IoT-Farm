package parking;

public class Parking {
	
	int id;
	int driver_id;
	String ParkType;
	double prk_per_person;
	double hours;
	int no_of_memb;
	double tot_cost;
	int rate_per_hr;
	double tot_prk_fee;
	
	public Parking(String ParkType, double hours, int no_of_memb, double tot_cost) 
	{
		this.ParkType = ParkType;
		this.hours = hours;
		this.no_of_memb =  no_of_memb;
		this.tot_cost = tot_cost;
	}
		
	public double getParkingVal() 
	{
		switch(ParkType)
		{
		case "1":
			rate_per_hr = 4;
			break;
			
		case "2":
			rate_per_hr = 2;
			break;
			
		default:
			prk_per_person = 0;
			//System.out.println("No Parking Fees");
			break;
				
		}	
		tot_prk_fee = rate_per_hr * hours;
		return tot_prk_fee;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getParkType() {
		return ParkType;
	}

	public void setParkType(String parkType) {
		ParkType = parkType;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}


	public double getTot_cost() {
		return tot_cost;
	}

	public void setTot_cost(double tot_cost) {
		this.tot_cost = tot_cost;
	}

	public double getTot_prk_fee() {
		return tot_prk_fee;
	}

	public void setTot_prk_fee(double tot_prk_fee) {
		this.tot_prk_fee = tot_prk_fee;
	}

	public int getRate_per_hr() {
		return rate_per_hr;
	}

	public void setRate_per_hr(int rate_per_hr) {
		this.rate_per_hr = rate_per_hr;
	}

	@Override
	public String toString() {
		return "Parking details :" +"\n"+ "id: " + id + ", Driver id: " + driver_id + ", Parking type: " + ParkType 
				+ ", Total Hours: " + hours +  ", Total cost : " +  tot_prk_fee;
	}	
}
