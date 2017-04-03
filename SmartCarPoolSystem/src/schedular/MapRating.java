package schedular;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

import DAO.MySQLAccess;
import membership.Customer;
import membership.Driver;
import request.Location;
import request.Request;
import request.RideRequest;
import route.Utility;
import vehicle.Car;

public class MapRating implements MappingStrategy {

	Queue reqTempQueue = new LinkedList();
	Queue reqTempQueue1 = new LinkedList();
	Queue driverQueue1 = new LinkedList();
	Driver driver = null, closestDriver = null;
	MySQLAccess db = new MySQLAccess();
	HashMap hm = new HashMap<>();

	RideRequest req;
	ListIterator<Request> listIterator;
	ListIterator<Driver> driverIterator;

	int driverCount = 0;

	/**
	 * 
	 * This method will choose closest driver after comparing date, destination
	 * and car type to map requests with driver
	 */
	@Override
	public HashMap<String, Queue> MapDriverAndRequest(Queue reqQueue, Queue driverQueue, String driverName) {

		RideRequest reqTemp = (RideRequest) reqQueue.element();
		listIterator = (ListIterator<Request>) reqQueue.iterator();
		driverIterator = (ListIterator<Driver>) driverQueue.iterator();
		Location commonDestination = reqTemp.getDestination();

		// Compare it with every other request to find the match
		while (listIterator.hasNext()) {
			req = (RideRequest) listIterator.next();
			if (req.getDestination().getX() == commonDestination.getX()
					&& req.getDestination().getY() == commonDestination.getY()
					&& req.getCarType() == reqTemp.getCarType()) {
				reqTempQueue.add(req);
			}
		}

		// Compare car type
		listIterator = (ListIterator<Request>) reqTempQueue.iterator();
		while (listIterator.hasNext()) {

			req = (RideRequest) listIterator.next();

			while (driverIterator.hasNext()) {
				driver = (Driver) driverIterator.next();
				Car car = (Car) driver.getVehicle();
				if (req.getCarType().equalsIgnoreCase(car.getModel())) {
					driverQueue1.add(driver);
				}
			}
		}

		// Find driver based on rating
		driverIterator = (ListIterator<Driver>) driverQueue.iterator();

		listIterator = (ListIterator<Request>) reqTempQueue.iterator();
		while (listIterator.hasNext()) {

			req = (RideRequest) listIterator.next();

			while (driverIterator.hasNext() && (driverCount == 0)) {

				driver = (Driver) driverIterator.next();
				if (driver.getRating() == req.getDriverRating()) {
					driverQueue1.add(driver);
					driverCount++;
					closestDriver = driver;
				}
			}
		}

		listIterator = (ListIterator<Request>) reqTempQueue.iterator();

		// Compare it with every other request to find the match
		while (listIterator.hasNext()) {
			req = (RideRequest) listIterator.next();
			req.setDriverID(closestDriver.getMemberId());
		}
		divideRequestAccordingToDates();

		return hm;

	}

	/**
	 * 
	 * This method will match requests having same dates
	 */

	public void divideRequestAccordingToDates() {

		listIterator = (ListIterator<Request>) reqTempQueue.iterator();
		ListIterator<Request> listIterator2;
		Queue day1 = new LinkedList<>();
		Queue day2 = new LinkedList<>();
		Queue day3 = new LinkedList<>();

		RideRequest tempReq;
		listIterator2 = (ListIterator<Request>) reqTempQueue.iterator();
		req = (RideRequest) reqTempQueue.element();

		// System.out.println(req.getDateTime().toString());
		while (listIterator2.hasNext()) {
			tempReq = (RideRequest) listIterator2.next();
			int result = tempReq.getDateTime().compareTo(req.getDateTime());
			if (result == 0) {
				day1.add(tempReq);
			}
		}
		req = null;
		listIterator2 = (ListIterator<Request>) reqTempQueue.iterator();
		while (listIterator2.hasNext()) {
			tempReq = (RideRequest) listIterator2.next();
			if (!day1.contains(tempReq)) {
				if (req == null) {
					req = tempReq;
				}
				int result = tempReq.getDateTime().compareTo(req.getDateTime());
				if (result == 0) {
					day2.add(tempReq);
				}
			}
		}

		listIterator2 = (ListIterator<Request>) reqTempQueue.iterator();
		req = null;
		while (listIterator2.hasNext()) {
			tempReq = (RideRequest) listIterator2.next();
			if (!day1.contains(tempReq) && !day2.contains(tempReq)) {
				if (req == null) {
					req = (RideRequest) reqTempQueue.element();
				}
				int result = tempReq.getDateTime().compareTo(req.getDateTime());
				if (result == 0) {
					day3.add(tempReq);
				}
			}
		}
		hm.put("1", day1);
		hm.put("2", day2);
		hm.put("3", day3);
	
	}
}
