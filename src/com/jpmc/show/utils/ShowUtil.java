package com.jpmc.show.utils;

import com.jpmc.show.requests.ViewShowRequest;

public class ShowUtil {

	public static void checkExitCommand(String input) {
		// check if exit program
		if (input.equals("-1")) {
			System.out.println("Exiting the program! Bye!");
			System.exit(0);
		}
	}

	public static ViewShowRequest buildViewShowRequest(String input) {
		String[] inputTokens = input.split(" ");
		int showNumber = Integer.valueOf(inputTokens[1]);
		
		ViewShowRequest viewShowRequest = new ViewShowRequest(showNumber);
		return viewShowRequest;
	}
}
