package membership;

public interface MembershipState {
	public String receiveApplication();
	public String qualifyApplicant(Member member);
	public String confirmMember();
}
