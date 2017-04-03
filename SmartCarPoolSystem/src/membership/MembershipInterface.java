package membership;

public interface MembershipInterface {
	public void receiveMembershipApplication();
	public void qualifyApplicant(Member member);
	public void confirmMember();
	
	public MembershipState getState();
	public void setState(MembershipState s);
}
