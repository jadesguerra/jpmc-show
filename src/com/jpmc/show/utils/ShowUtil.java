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
		try {
			String[] inputTokens = input.split(" ");
			if (inputTokens.length != 2) { // expecting only 2 tokens
				throw new IllegalArgumentException("Error: Invalid Input. Try this format: View <Show Number> ");
			}
			int showNumber = Integer.valueOf(inputTokens[1]);
			
			ViewShowRequest viewShowRequest = new ViewShowRequest(showNumber);
			return viewShowRequest;
		} catch (NumberFormatException e) {
			// Catches decimal values
			throw new NumberFormatException("Error: Invalid input. Expecting number " + e.getMessage());
		}
	}
}
