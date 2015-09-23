/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2008-9-9</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.web;

import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.converters.StringConverter;

import com.map.code;
import com.sysFrams.db.DataBaseControl;
import com.sys.util.SysProperties;
import com.sys.util.SysTool;
import com.sysFrams.db.Page;
import com.sysFrams.util.CommonProperties;

public class BaseAction {
	protected DataBaseControl dataBaseControl=DataBaseControl.getInstance();
	static {
		ConvertUtils.register(new StringConverter(null), String.class);
		ConvertUtils.register(new SqlDateConverter(null), Date.class);
		ConvertUtils.register(new SqlTimeConverter(null), Time.class);
		ConvertUtils.register(new SqlTimestampConverter(null), Timestamp.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		ConvertUtils.register(new BigIntegerConverter(null), BigInteger.class);
	}
	/**
	 * 把前台提交数据设置到bean实体对象
	 * @param request
	 * @return
	 */
	public Object getMapObject(HttpServletRequest request,Class... className) throws Exception {
		Class clazz = null;
		if(className==null||className.length<1)
			clazz = Class.forName(CommonProperties.getInstance().getMapPackage()+ "."
				+ this.getClass().getSimpleName().replaceAll("Action", ""));
		else
			clazz=className[0];
		Object obj=clazz.newInstance();
		Map map=request.getParameterMap();
		BeanUtils.populate(obj, map);
		return obj;
	}
    /**
     * 把前台提交数据设置到bean实体对象,并且添加需要特别设置的内容
     * @param request
     * @param changeParameter
     * @return
     */
	public Object getMapObject(HttpServletRequest request,HashMap<String, Object> changeParameter,Class... className) throws Exception {
		Class clazz = null;
		if(className==null||className.length<1)
			clazz=Class.forName(CommonProperties.getInstance().getMapPackage()+ "."
				+ this.getClass().getSimpleName().replaceAll("Action", ""));
		else
			clazz=className[0];
		Object obj=clazz.newInstance();
		Map map=request.getParameterMap();   
		BeanUtils.populate(obj, map);
		if(changeParameter!=null&&!changeParameter.isEmpty()){ 
			Object[] keys=changeParameter.keySet().toArray();
			Object[] values=changeParameter.values().toArray();
		    for(int i=0;i<changeParameter.size();i++){  
		    	BeanUtils.setProperty(obj,keys[i].toString(), values[i]);   
		    }
	    } 
		return obj;
	}
	/** 
	 * 设置Ajax输出信息
	 * @throws Exception 
	 */
	public void setAjaxInfo(HttpServletResponse response, String info) throws Exception { 
		PrintWriter pw =response.getWriter();
		pw.print(info); // 将错误信息返回前台
		pw.flush();
		pw.close();
	}
	//////////////////////////
	/** 
	 * 根据给定sql和页面请求参数进行搜索 
	 **/
	protected void defaultList(HttpServletRequest request,HttpServletResponse response,StringBuffer sql,String... sortorders) throws Exception {
		String page=request.getParameter("page");
		String pagesize=request.getParameter("rows");
		String sort=request.getParameter("sort");
		String order=request.getParameter("order");
		String sortorder=null;
		HashMap<String, String> parameter=getParameterMap(request);
		int pagei=1;
		int pagesizei=20;
		try {pagei=Integer.parseInt(SysTool.defaultIfEmpty(page, "1"));} catch (Exception e) {}
		try {pagesizei=Integer.parseInt(SysTool.defaultIfEmpty(pagesize, "20"));} catch (Exception e) {}
		if(sortorders.length==0&&(sort==null||"".equals(sort))){
			sortorder="id desc";
		}else if((sortorders.length==0)&&sort!=null&&!"".equals(sort)){
			sortorder=sort+" "+SysTool.defaultIfEmpty(order, "desc");
		}else if((sortorders.length!=0)&&(sort==null||"".equals(sort))){
			sortorder=sortorders[0];
		}else{
			sortorder=sort+" "+SysTool.defaultIfEmpty(order, "desc")+","+sortorders[0];
		}
		processSql(sql, parameter);
		Page mPage=dataBaseControl.getPageResultSet1(new String(sql), parameter.values().toArray(),pagei,pagesizei,sortorder);
		setAjaxInfo(response,mPage.getJsoin());
	}
	/**
	 * 混合xsqlbuilder的sql语句和参数使其处理成程序可用的语句和参数
	 * @param sql　xsqlbuilder的sql语句（传入处理后并返回）
	 * @param parameter　混合前语句（传入处理后并返回）
	 */
	protected void processSql(StringBuffer sql,HashMap<String, String> parameter){
		XsqlFilterResult result = new XsqlBuilder().generateSql(new String(sql), parameter);
		sql.delete(0, sql.length());//sql清空
		sql.append(result.getXsql());//装载为处理好的sql
		parameter.clear();//参数清空
		parameter.putAll(result.getAcceptedFilters());//装载处理好的参数
	}
	private Class getClassT() throws Exception {
		Type type =  getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType)//是否为泛型类型  
		   return (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
		else
		   return Class.forName(SysProperties.MAPPACKGE+ "."+ this.getClass().getSimpleName().replaceAll("Action", ""));  
	}
	/**
	 *根据请求的id数组删除对象
	 **/
	protected void defaultDelete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer("DELETE FROM "+SysProperties.TABSATRT+getClassT().getSimpleName()+" WHERE ID IN (");
		String[] ids=request.getParameterValues("id");
		for(int i=0;i<ids.length;i++) {
			str.append("?,");
		}
		String delStr = str.substring(0, str.length()-1)+")";
		dataBaseControl.executeSql(delStr,ids);					
		setAjaxInfo(response,"{\"status\":\"ok\"}");
	}
	
	///////////////////////////
	
	public void doResult(HttpServletResponse response, String error) {
		response.setContentType("text/html; charset=gbk");
		response.setCharacterEncoding("gbk");
		try {
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("history.back();");
			out.println("alert('" + error + "');");
			out.println("</script>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 把请求的参数数组处理扁平化
	 * @param request
	 * @return
	 */
	protected LinkedHashMap<String, String> getParameterMap(HttpServletRequest request) {
		Map temp=request.getParameterMap();
		LinkedHashMap<String, String> out=new LinkedHashMap<String, String>();
		for(Iterator it = temp.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry)it.next();
			out.put((String)entry.getKey(), request.getParameter((String)entry.getKey()));
		}
		return out;
	}
	
//	/**
//	 * 方便组装查询数据
//	 * @param map 页面请求参数MAP
//	 * @return 返回查询结果集
//	 * 调用方式：List list=search(getParameterMap(request));
//	 */
//	public List search(Map map) {/
//		List list=null;
//        //sql语句,/~ ~/为一个语法块
//		String sql= "select * from Alter where 1=1 " 
//		          + "/~ and altercompany = {altercompany} ~/"   
//		          + "/~ and sendperson like '%[sendperson]%' ~/";   
//		XsqlFilterResult result = new XsqlBuilder().generateSql(sql, map);  
//		DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
//		try {
//			list=(List) dataBaseControl.getOutResultSet(result.getXsql(), result.getAcceptedFilters().values().toArray());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

}