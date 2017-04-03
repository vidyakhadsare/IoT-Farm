package payment;

import java.util.Scanner;

public class CardPayment extends Payment{
	
	double amount;
	int no_of_memb;
	Scanner scan = new Scanner(System.in);
	
	public CardPayment(CalculatePayment calPay1, int no_of_memb) {
		super(calPay1, no_of_memb);
		
	}

	@Override
	double total_payment() {
		
		amount = super.distribute(no_of_memb);
		return amount;
	}

	@Override
	public
	void make_payment() {
		System.out.println("Payment Made by Card");	
	}

}
