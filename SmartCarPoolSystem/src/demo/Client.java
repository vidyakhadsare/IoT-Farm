package demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {

	static BufferedReader br = null;

	public static void main(String[] args) {
		Menu mainMenu = new Menu();
	}

	public static BufferedReader getReader() {
		if (br == null) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		return br;
	}

}
