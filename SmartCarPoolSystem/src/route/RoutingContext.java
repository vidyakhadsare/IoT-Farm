package route;


/**
 * 
 * @author hegde
 *
 */
public class RoutingContext {
	
	private RoutingStrategy rs;
	
	/**
	 * A utility function to call the routing strategy
	 * 
	 * 
	 * @param source
	 * @param destination
	 * @param avoidHighway
	 * @return optimalRoute
	 */
	public int route(int source, int destination){
		Utility utility=new Utility();
		int condition=utility.trafficCondition(source, destination);
		rs=setRoutingStrategy(condition);
		return rs.route(source,destination);
	}
	
	/**
	 *  A utility function to decide the routing strategy
	 * based on the traffic condition between the source and destination 
	 * 
	 * @param avoidHighway
	 * @return routingStrategy
	 */
	public RoutingStrategy setRoutingStrategy(int condition){
		
		if(condition==0){
			rs=new AlgoWithHighway();
		}
		if(condition==1){
			rs=new AlgorWithoutHighway();
		}
		
		return rs;
		
	}
	public static void main(String[] args) {
		

	}

}

