package org.groupmeetings.utils;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

public class DateFunctions {

	public static String getMonthForNumber(short number, Locale locale) {
		if (number < 1 || number > 12) {
			throw new IllegalArgumentException("number must be between 1 and 12");
		}
		String[] months = new DateFormatSymbols(locale).getMonths();
		return months[--number];
	}

	public static String getMonthForNumber(short number) {
		return getMonthForNumber(number, Locale.getDefault());
	}
	
	public static int maxAllowedYear() {
		LocalDate ld = LocalDate.now();
		int maxYear = ld.getYear() - 15;
		return maxYear;
	}

}
