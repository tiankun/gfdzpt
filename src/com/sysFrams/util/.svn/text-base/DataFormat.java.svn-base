package com.sysFrams.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormat {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public static Date toDate(String input) {
		Date out;
		try {
			out = format.parse(input);
		} catch (ParseException e) {
			// e.printStackTrace();
			out = new Date();
		}
		return out;
	}
	public static String toString(Date input) {
		return format.format(input);
	}	
}
