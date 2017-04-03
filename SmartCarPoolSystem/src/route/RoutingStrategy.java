package route;

/**
 * routing strategy interface to calculate route
 * 
 * 
 * @author hegde
 *
 */
public interface RoutingStrategy {

	/**
	 * interface method
	 * 
	 * @param source
	 * @param destination
	 * 
	 */
	public int route(int source, int destination);
}
