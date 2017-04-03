package payment;
import rules.PaymentRule;
public abstract class CalculatePayment {
	
	PaymentRule rule=new PaymentRule();
	public double cost_per_mile = rule.getRule1();
	public double miles;
	public int no_of_memb;
	public double ind_cost;
	public double tot_cost;
	
	public CalculatePayment(double miles,int no_of_memb)
	{
		this.miles = miles;
		this.no_of_memb = no_of_memb;
	}
	
	public abstract double cost_for_distance();

	public double distribute(int no_members, double tot_cost2) {
		if(no_members <= 0)
		{
			System.out.println("Invalid number of members");
			return 0;
		}
		else
		{
			return tot_cost2 / no_members;
		}
	}
	
}
