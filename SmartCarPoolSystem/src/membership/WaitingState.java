package membership;

public class WaitingState implements MembershipState{

	private MembershipInterface member;
	
	public WaitingState(MembershipInterface member) {
		this.member = member;
	}

	@Override
	public String receiveApplication() {
		member.setState(new EvaluatingState(member));
		return "Application is received";
	}

	@Override
	public String qualifyApplicant(Member member) {
		return "Application must be received first";
	}

	@Override
	public String confirmMember() {
		return "Application is not received or denied";
	}
}
