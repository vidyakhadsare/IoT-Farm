package payment;

public abstract class Payment {
	
	int payment_id;
	public double ind_cost;
	public double tot_cost;
	public int no_of_memb;
	protected CalculatePayment CalPmt;
	protected Payment Pmt;
	
	public Payment(CalculatePayment CalPmt, int no_of_memb)
	{
		this.CalPmt = CalPmt;
		this.no_of_memb = no_of_memb;
	}
	
	public Payment(Payment Pmt, int no_of_memb)
	{
		this.Pmt = Pmt;
		this.no_of_memb = no_of_memb;
	}
	
	abstract double total_payment();
	
	public double distribute(int no_members)
	{
		return ind_cost = CalPmt.distribute(no_members, tot_cost);
	}
	abstract void make_payment();	
}

