package membership;

import java.time.LocalDate;
import java.util.Date;

import payment.CardPayment;

public abstract class Member {
	private String firstName;
	private String lastName;
	private String userName;
	private String emailId;
	private String phoneNumber;
	private CardDetails cardDetails;
	private int memberId;
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CardDetails getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		String card = cardDetails.toString();
		return "Id: "+ memberId + ", Name: " + firstName + " " +	lastName + ", User name: " + userName + ", Email id: "
				+ emailId + ", Contact number: " + phoneNumber + "\n"+ "CardDetails: " + "\n" + card;
	}
}
