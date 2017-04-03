package payment;

public class EstimatePayment extends CalculatePayment {

	String CarType;
	public double type_percent;

	public EstimatePayment(double miles, int no_of_memb, String CarType) {
		super(miles, no_of_memb);
		this.CarType = CarType;
		// TODO Auto-generated constructor stub

		switch (CarType) {
		case "fiveSeater":
			type_percent = 1.2;

			break;
		case "eightSeater":
			type_percent = 1.4;
			break;

		}
	}
	@Override

	public double cost_for_distance() {
		tot_cost = super.miles * super.cost_per_mile * type_percent;
		//System.out.println("Total Cost for distance covered: " + tot_cost);
		ind_cost = tot_cost / super.no_of_memb;
		return tot_cost;		
	}
}
