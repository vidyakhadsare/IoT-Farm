package membership;

import java.time.LocalDate;

import payment.CardPayment;

public class Admin extends Member {
	private String designation;
	
	public String getDesignation() {
		return designation;
	}

	public void setdesignation(String qualification) {
		this.designation = qualification;
	}

	@Override
	public String toString() {
		return super.toString()+" "+"Staff [designation=" + designation + "]";
	}
	
}
