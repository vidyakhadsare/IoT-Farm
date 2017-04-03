package membership;

public class MemberShip implements MembershipInterface {
	MembershipState state;

	public MemberShip() {
		state = new WaitingState(this);
	}

	public void receiveMembershipApplication() {
		System.out.println(state.receiveApplication());
	};

	public void qualifyApplicant(Member member) {
		System.out.println(state.qualifyApplicant(member));
	};

	public void confirmMember() {
		System.out.println(state.confirmMember());
	};

	public MembershipState getState() {
		return state;
	};

	public void setState(MembershipState s) {
		state = s;
	}
	
}
