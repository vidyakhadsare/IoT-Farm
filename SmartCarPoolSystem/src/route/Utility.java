package route;

/**
 * This is a utility function that gives the place 
 * using (x,y) co-ordinates entered by the customer.
 * 
 * @author hegde
 *
 */
public class Utility {

	/**
	 * This method takes input coordinates and returns
	 * corresponding location from the matrix
	 * @param i
	 * @param j
	 * 
	 */
	public int locateNode(int i, int j){
		
		int[][] nodeMatrix={   {1, 2, 3, 4, 5},
				               {6, 7, 8, 9, 10},
				               {11,12,13,14,15},
				               {16,17,18,19,20},
				               {21,22,23,24,25} };
		
		return nodeMatrix[i][j];
	}
	
	/**
	 * This takes source and destination as input parameters and 
	 * returns traffic condition.   
	 * @param src
	 * @param dst
	 * @return
	 */
	int trafficCondition(int src, int dst){
		
		int condition=(int)(Math.random() * 1); 
		return condition;
	}
	
	
}
