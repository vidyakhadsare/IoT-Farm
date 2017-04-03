package membership;

import java.time.LocalDate;
import java.util.Date;

import feedback.Complain;
import feedback.CustomerFeedback;
import feedback.FeedBack;
import feedback.Recommendation;
import payment.CardPayment;

public class Customer extends Member {
	private String customerType;


	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	@Override
	public String toString() {
		String member = super.toString();
		return "Customer Details: "+ "\n" + member + "\n"+"Membership plan: " + this.customerType ;
	}
}

