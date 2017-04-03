package schedular;

import java.util.HashMap;
import java.util.Queue;

public interface MappingStrategy {	
	public HashMap<String,Queue> MapDriverAndRequest(Queue reqQueue, Queue driverQueue, String driverName);
}
