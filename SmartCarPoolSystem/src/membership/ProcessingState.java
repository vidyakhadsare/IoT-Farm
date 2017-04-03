package membership;

public class ProcessingState implements MembershipState {
	private MembershipInterface memberShip;
	
	public ProcessingState(MembershipInterface memberShip) {
		this.memberShip = memberShip;
	}

	@Override
	public String receiveApplication() {
		return "Already received an application";
	}

	@Override
	public String qualifyApplicant(Member member) {
		return "Application is qualified";
	}

	@Override
	public String confirmMember() {
		return "Registered successfully!";
	}

}
