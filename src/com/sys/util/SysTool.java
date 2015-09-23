/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2013-8-26</p>
 * <p> @author 邹申昶</p>
 */
package com.sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 邹申昶
 */
public class SysTool {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//默认日期格式
	/**
	 * 判断输入字符串是否为空（null或者空字符串）
	 * @param inputStr　传入字符串
	 * @return　是否为空结果
	 */
	public static Boolean isEmpty(String inputStr) {
			return (inputStr == null) || (inputStr.trim().length() == 0);
	}
    /**
     * 获取传入数据的处理值（如果传入值为空返回默认值，不为空返回传入值）
     * @param inputStr　传入字符串　
     * @param defaultvalue　当字符串为空的时候返回的默认值
     * @return 返回处理后的值　
     */
	public static String defaultIfEmpty(String inputStr, String defaultStr) {
			return isEmpty(inputStr) ? defaultStr : inputStr;
	}	
    /**
     * 字符串转日期
     * @param inputStr　给定字符串
     * @param format　日期格式
     * @return
     */
	public static Date strToDate(String inputStr) {
		Date out=null;
		try {
			out = format.parse(inputStr);
		} catch (ParseException e) {
			 e.printStackTrace();
		}
		return out;
	}
    /**
     * 按照指定格式将字符串转日期
     * @param inputStr　给定字符串
     * @return 返回日期
     */
	public static Date strToDate(String inputStr,String format) {
		Date out=null;
		try {
			SimpleDateFormat _format = new SimpleDateFormat(format);
			out = _format.parse(inputStr);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return out;
	}	
	/**
	 * 将日期转为字符串
	 * @param inputDate　给定日期
     * @return 返回日期字符串
	 */
	public static String dateToStr(Date inputDate) {
		return format.format(inputDate);
	}
    /**
     * 按照指定格式输出日期字符串
     * @param inputDate　给定日期
     * @param format　日期格式
     * @return 返回日期字符串
     */
	public static String dateToStr(Date inputDate,String format) {
		SimpleDateFormat _format = new SimpleDateFormat(format);
		return _format.format(inputDate);
	}	
}
