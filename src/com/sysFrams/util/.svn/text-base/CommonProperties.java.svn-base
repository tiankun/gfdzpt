/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2008-8-5</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.util;

import com.sysFrams.db.DBManage;


/**
 * 项目启动公共属性
 */
public class CommonProperties {
	private static CommonProperties commonProperties=new CommonProperties();
	private CommonProperties() {}
	public static CommonProperties getInstance(){
		return commonProperties;
	}
	//公共属性名定义
	public static final String TABSATRT="";//XXX 表前缀
	public static final String LOGLEVEL="logLevel";
	public static final String PROPERTIESENCODING="propertiesEncoding";
	public static final String DATASOURCEJNDINAME="datasourceJNDIName";
	public static final String INITINFO="initInfo";
	public static final String DESTROYINFO="destroyInfo";
	public static final String MAPPACKGE="mapPackage";
	public static final String WEBPACKGE="webPackage";
	public static final String MESSAGEIP="messageIP";	

	private String logLevel="warn";
	private String propertiesEncoding;
	private String datasourceJNDIName;
	private String dbType;
	private String initInfo ;
	private String destroyInfo ;
	private String mapPackage;
	private String webPackage;
	private String messageIP;	

	/**
	 * @return the logLevel
	 */
	public String getLogLevel() {
		return logLevel;
	}
	/**
	 * @param logLevel the logLevel to set
	 */
	public void setLogLevel(String logLevel) {
		if(logLevel.equals("debug")||logLevel.equals("info")||logLevel.equals("warn")||logLevel.equals("error"))
		   this.logLevel = logLevel;
	}
	/**
	 * @return the propertiesEncoding
	 */
	public String getPropertiesEncoding() {
		return propertiesEncoding;
	}
	/**
	 * @param propertiesEncoding the propertiesEncoding to set
	 */
	public void setPropertiesEncoding(String propertiesEncoding) {
		this.propertiesEncoding = propertiesEncoding;
	}
	/**
	 * @return the datasourceJNDIName
	 */
	public String getDatasourceJNDIName() {
		return datasourceJNDIName;
	}
	/**
	 * @param datasourceJNDIName the datasourceJNDIName to set
	 */
	public void setDatasourceJNDIName(String datasourceJNDIName) {
		this.datasourceJNDIName = datasourceJNDIName;
	}
	/**
	 * @return the dbType
	 */
	public String getDbType() {
		if(dbType==null)
			try {
				DBManage dBManage=new DBManage();
				commonProperties.setDbType(dBManage.getDbType());
			} catch (Exception e) {
				e.printStackTrace();
			}
		return dbType;
	}
	/**
	 * @param dbType the dbType to set
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	/**
	 * @return the destroyInfo
	 */
	public String getDestroyInfo() {
		try {
			return new String(destroyInfo.getBytes("ISO8859-1"),propertiesEncoding);
		} catch (Exception e) {
			return destroyInfo;
		}
	}
	/**
	 * @param destroyInfo the destroyInfo to set
	 */
	public void setDestroyInfo(String destroyInfo) {
		this.destroyInfo = destroyInfo;
	}
	public String getMapPackage() {
		return mapPackage;
	}
	public void setMapPackage(String mapPackage) {
		this.mapPackage = mapPackage;
	}
	/**
	 * @return the initInfo
	 */
	public String getInitInfo() {
		try {
			return new String(initInfo.getBytes("ISO8859-1"),propertiesEncoding);
		} catch (Exception e) {
			return initInfo;
		}
	}
	/**
	 * @param initInfo the initInfo to set
	 */
	public void setInitInfo(String initInfo) {
		this.initInfo = initInfo;
	}
	/**
	 * @return the webPackage
	 */
	public String getWebPackage() {
		return webPackage;
	}
	/**
	 * @param webPackage the webPackage to set
	 */
	public void setWebPackage(String webPackage) {
		this.webPackage = webPackage;
	}
	public String getMessageIP() {
		return messageIP;
	}
	public void setMessageIP(String messageIP) {
		this.messageIP = messageIP;
	}	
}