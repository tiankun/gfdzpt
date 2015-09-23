package com.sysFrams.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static String  _yyyyMMddHHmmssS	= "yyyy-MM-dd HH:mm:ss.S";
	public static String  _yyyyMMddHHmmss	= "yyyy-MM-dd HH:mm:ss";
	public static String  _yyyyMMddHHmm		= "yyyy-MM-dd HH:mm";
	public static String  _yyyyMMddHH		= "yyyy-MM-dd HH";
	public static String  _yyyyMMdd			= "yyyy-MM-dd";

	public static String getFormatDate(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.SIMPLIFIED_CHINESE);
		String timeStr = sdf.format(new Date()); 
		return timeStr;
	}
	/**
	 * 获取年份
	 * 
	 * @param date
	 * @return
	 */
	public String getYear(java.util.Date date) {
		return (new SimpleDateFormat("yyyy")).format(date);
	}
	public static Date toDate(String input) {
		SimpleDateFormat format = new SimpleDateFormat(_yyyyMMdd,Locale.SIMPLIFIED_CHINESE);
		Date out;
		try {
			out = format.parse(input);
		} catch (ParseException e) {
			// e.printStackTrace();
			out = new Date();
		}
		return out;
	}
	public static Date toDate(String input,String _format) {
		SimpleDateFormat format = new SimpleDateFormat(_format,Locale.SIMPLIFIED_CHINESE);
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
		SimpleDateFormat format = new SimpleDateFormat(_yyyyMMdd,Locale.SIMPLIFIED_CHINESE);
		return format.format(input);
	}
	public static String toString(Date input,String _format) {
		SimpleDateFormat format = new SimpleDateFormat(_format,Locale.SIMPLIFIED_CHINESE);
		return format.format(input);
	}
}
