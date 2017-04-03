package request;

import java.util.Scanner;
/**
 * 
 * @author VidyaKhadsare
 */
public abstract class Request {	

	public static Integer reqId = 0;
	Scanner keyboard = new Scanner( System.in );
	String input = "";
	String[] numbersStr;
	
	public Request() {
		 reqId++;
	}
	public int getID(){
		return reqId;
	}
	public abstract void display();	
}
