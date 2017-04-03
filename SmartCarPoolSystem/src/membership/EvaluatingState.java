package membership;
import java.time.LocalDateTime;

public class EvaluatingState implements MembershipState{

	private MembershipInterface memberShip;
	
	public EvaluatingState(MembershipInterface memberShip) {
		super();
		this.memberShip = memberShip;
	}

	@Override
	public String receiveApplication() {
		return "Application is already received";
	}

	/**
	 * Method evaluates the credit card number if its 16 digit memebership is accepted 
	 */
	@Override
	public String qualifyApplicant(Member member) {
		if (member instanceof Driver) {
			Driver driver = (Driver) member;
			if (driver.getCardDetails().getCardNumber().length() == 16 ) 
//				&& driver.getLicenseDetails().getValidUntil().isAfter(LocalDateTime.now().toLocalDate())
			{
				memberShip.setState(new ProcessingState(memberShip));
				return "The membership is accepted";
			} else {
				memberShip.setState(new WaitingState(memberShip));
				return "The membership is denied";
			}
		}
		if (member instanceof Customer) {
			Customer customer = (Customer) member;
			if (customer.getCardDetails().getCardNumber().length() == 16) {
				memberShip.setState(new ProcessingState(memberShip));
				return "The membership is accepted";
			} else {
				memberShip.setState(new WaitingState(memberShip));
				return "The membership is denied";
			}
		}
		if (member instanceof Admin) {
			Admin manager = (Admin) member;
			if (manager.getCardDetails().getCardNumber().length() == 16) {
				memberShip.setState(new ProcessingState(memberShip));
				return "The membership is accepted";
			} else {
				memberShip.setState(new WaitingState(memberShip));
				return "The membership is denied";
			}
		}
		return null;
	}

	@Override
	public String confirmMember() {
		return "Applicantion must evaluate first";
	}

}
