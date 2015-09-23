/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2008-8-5</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.web;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import com.sysFrams.db.DBManage;
import com.sysFrams.util.CommonProperties;

/**
 * 系统启动之初加载必须属性文件
 */
public class AppInit extends HttpServlet {
	public void init() {
		CommonProperties commonProperties=CommonProperties.getInstance();
		try {
			FileInputStream fis = new FileInputStream(getServletContext().getRealPath("/")+getInitParameter("appProperties"));
			Properties pt = new Properties();
			// 读入文件的信息
			pt.load(fis);
			// 关闭文件
			fis.close();
			// 将文件中的信息写入变量中
			commonProperties.setLogLevel(pt.getProperty(CommonProperties.LOGLEVEL).trim().toLowerCase());
			commonProperties.setPropertiesEncoding(pt.getProperty(CommonProperties.PROPERTIESENCODING).trim());
			commonProperties.setDatasourceJNDIName(pt.getProperty(CommonProperties.DATASOURCEJNDINAME).trim());
			commonProperties.setInitInfo(pt.getProperty(CommonProperties.INITINFO).trim());
			commonProperties.setDestroyInfo(pt.getProperty(CommonProperties.DESTROYINFO).trim());
			commonProperties.setMapPackage(pt.getProperty(CommonProperties.MAPPACKGE).trim());
			commonProperties.setWebPackage(pt.getProperty(CommonProperties.WEBPACKGE).trim());
			commonProperties.setMessageIP(pt.getProperty(CommonProperties.MESSAGEIP).trim());			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("%%%%%%%%%%%% 读取系统配置文件失败！ %%%%%%%%%%%%");
			System.exit(0);
		}
		//打印系统启动标示
		System.out.println(CommonProperties.getInstance().getInitInfo());
		try{
			//测试数据库是否连接上
			DBManage dBManage=new DBManage();
			commonProperties.setDbType(dBManage.getDbType());
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("%%%%%%%%%%%% 测试数据库连接池失败！ %%%%%%%%%%%%");
		}
	}

	public void destroy() {
        //打印系统关闭标示
		System.out.println(CommonProperties.getInstance().getDestroyInfo());
	}
}