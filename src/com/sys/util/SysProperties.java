/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2008-8-5</p>
 * <p> @author ShenChang zou</p>
 */
package com.sys.util;

import com.sysFrams.db.DBManage;


/**
 * 项目启动公共属性
 */
public class SysProperties {
	//session或者aplication中存放的常量
	/**登录所用随机验证码*/
    public final static String SRAND = "sRand"; 			//登录所用随机验证码
    /**存放aplication中或者session中的允许权限链接*/
    public final static String PURVIEW = "purview";         //存放aplication中或者session中的允许权限链接
    /**登录用户*/
    public final static String USER="logUser";              //登录用户   
    
	
	//系统公共属性名定义
	public static final String TABSATRT="";  //XXX 表前缀
	public static final String MAPPACKGE="com.map";  //XXX map包名称
	public static final String WEBPACKGE="com.web";  //XXX action包统一前缀
	
	private static SysProperties commonProperties=new SysProperties();
	private SysProperties() {}
	public static SysProperties getInstance(){
		return commonProperties;
	}
	
	public static final String LOGLEVEL="logLevel";
	public static final String DATASOURCEJNDINAME="datasourceJNDIName";
	public static final String INITINFO="initInfo";
	public static final String DESTROYINFO="destroyInfo";
	public static final String INDEXPAGEPATH="indexPagePath";
	public static final String INDEXURL="indexUrl";

	private String logLevel="warn";
	private String datasourceJNDIName;
	private String dbType;
	private String initInfo ;
	private String destroyInfo ;
	private String indexPagePath;//系统生成的首页存放的本地路径
	private String indexUrl;//系统实际的主页地址

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
			return new String(destroyInfo.getBytes("ISO8859-1"),"UTF-8");//XXX 属性文件编码
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
	/**
	 * @return the initInfo
	 */
	public String getInitInfo() {
		try {
			return new String(initInfo.getBytes("ISO8859-1"),"UTF-8");//XXX 属性文件编码
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
	public String getIndexPagePath() {
		return indexPagePath;
	}
	public void setIndexPagePath(String indexPagePath) {
		this.indexPagePath = indexPagePath;
	}
	public String getIndexUrl() {
		return indexUrl;
	}
	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}

}