package com.cts.fsd.util;

import java.time.DateTimeException;
import java.time.LocalDate;

public class ApplicationUtility {
	
	public static boolean isDateValid(String year, String month, String day) {
		
	    boolean dateIsValid = true;
	    try {
	        LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
	    } catch (NumberFormatException | DateTimeException e) {
	        dateIsValid = false;
	    }
	    
	    return dateIsValid;
	}
	
	
	public static void displayHorizontalLine() {
		System.out.println("==========================================================");
	}
	
	public static void displayOptionReceived(String option) {
		System.out.println("Input value \""+option+"\" received...\n\n\n");
	}
}
