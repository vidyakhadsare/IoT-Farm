package schedular;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MappingContext {

	private MappingStrategy mapSt;
	private Queue reqQueue = new LinkedList();
	
	public MappingContext(){
		
	}
	
	public MappingStrategy setMappingStrategy(String str, Queue reqQueue) { 	
		
		if(str.equalsIgnoreCase("Rating")){
			mapSt = new MapRating();
		} 
		else{
			mapSt = new MapLocation();
		}	
		return mapSt;
	}
	
	public HashMap<String,Queue> MapDriverAndRequest(String strategy, Queue reqQueue, Queue driverQueue, String driverName){
		
		HashMap<String,Queue> hm;
		mapSt = setMappingStrategy(strategy,reqQueue);
		hm = mapSt.MapDriverAndRequest(reqQueue,driverQueue,driverName);
		return hm;
	}

}
