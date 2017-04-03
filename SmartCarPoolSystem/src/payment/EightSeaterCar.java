package payment;

public class EightSeaterCar extends CalculatePayment{
	
	public EightSeaterCar(double miles, int no_of_memb) {
		super(miles, no_of_memb);
		// TODO Auto-generated constructor stub
	}
	
	public double type_percent = 1.4;

	
	@Override
	public double cost_for_distance() 
	{
			tot_cost = super.miles * super.cost_per_mile * type_percent;
//			System.out.println("Total Cost for distance covered: " + tot_cost);
			ind_cost = tot_cost / super.no_of_memb;
			return tot_cost;	
	}
}
