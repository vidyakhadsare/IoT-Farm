package specialOffer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;

import membership.Customer;
import membership.Member;

public abstract class Offers {
	String members[];

	double offer;
	ArrayList<iUserGroup> customerList = new ArrayList<>();
	
	public void notifySubscriber(double discount) {
						
		for(int i = 0; i< customerList.size(); i++)
		{					
			CustomerGroup cust = (CustomerGroup)customerList.get(i);
			cust.update(discount);		
		}				
	}
	
	public void addObservre(iUserGroup customer) {
		
		customerList.add(customer);
	}

	public void removeObserver(iUserGroup customer) {
		
		customerList.remove(customer);
	}
		
}


