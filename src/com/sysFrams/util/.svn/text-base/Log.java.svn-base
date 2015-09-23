/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2008-9-27</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.util;

/**
 * 自己封装的一个简单日志输出类，日志级别分为四级(由低到高)：debug,info,warn,error
 */
public class Log {
	private static final String logLevel = CommonProperties.getInstance().getLogLevel(); // 日志等级

	public static void debug(String logStr) {
		if(logLevel.equals("debug"))
        System.out.println("#DEBUG: "+logStr);
	}
	public static void info(String logStr) {
		if(logLevel.equals("debug")||logLevel.equals("info"))
        System.out.println("#INFO: "+logStr);
	}
	public static void warn(String logStr) {
		if(!logLevel.equals("error"))
        System.out.println("#WARN: "+logStr);
	}
	public static void error(String logStr) {
        System.out.println("#ERROR: "+logStr);
	}
	public static boolean isDebug() {
		if(logLevel.equals("debug"))
            return true;
		else
			return false;
	}
	public static boolean isInfo() {
		if(logLevel.equals("debug")||logLevel.equals("info"))
          return true;
		else
		 return false;
	}
	public static boolean isWarn() {
		if(!logLevel.equals("error"))
          return true;
		else
		 return false;
	}
	public static boolean isError() {
          return true;
	}	
}