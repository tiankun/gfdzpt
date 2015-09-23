/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2013-8-9</p>
 * <p> @author 邹申昶</p>
 */
package com.sys.util;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sys.util.SysProperties;
import com.sys.util.Type;

/**
 * @author 邹申昶
 */
public class SqlTool1 {
	/**
	 * 去除select 子句 //XXX 未考虑union的情况 
	 * @param sql 需要处理的sql语句
	 * @return　处理好的sql语句
	 */
	public static String removeSelect(String sql) {
		int beginPos = sql.toLowerCase().indexOf("from");		
		return sql.substring(beginPos);
	}
	/**
	 * 去除order by 子句 //XXX 对语句中间存在order by的会出问题
	 * @param sql 需要处理的sql语句
	 * @return　处理好的sql语句
	 */
	public static String removeOrders(String sql) {
		int endPos = sql.toLowerCase().lastIndexOf("order");
		int kendPos = sql.toLowerCase().lastIndexOf(")");
		if(endPos!=-1){
			if(kendPos<endPos)
				return sql.substring(0, endPos-1);
			else
				return sql;	
		}else
			return sql;
	}
	/**
	 * 获取指定sql语句的计算总条数的sql语句
	 * @param sql 需要处理的sql语句
	 * @return　处理好的sql语句
	 */
	public static String getCountSql(String sql) {
		return "SELECT COUNT(*) FROM ("+sql+") a_";
	}
	/**
	 * 根据给定语句及数据库类型生成对应的分页语句
	 * FIXME 注意此处应用于SQL Server要求查询必须有一个主键，并且为ID,注意在SQL SERVER 中用order by排序时候排序字段不要带前缀
	 * @param sql
	 * @return
	 */
	public static String getPageSql(String sql,String type,int pageNumber,int pageSize) throws Exception {
		if((type.indexOf("MYSQL")>=0)||(type.indexOf("SQLITE")>=0))
			sql+=" LIMIT "+((pageNumber-1)*pageSize)+","+pageSize;
		else if(type.indexOf("ORACLE")>=0)
			sql="SELECT * FROM (SELECT a_.*,ROWNUM RN FROM ("+sql+")a_ WHERE ROWNUM <= "+(pageNumber*pageSize)+")b_ WHERE b_.RN>="+((pageNumber-1)*pageSize+1); 
		else if(type.indexOf("SQL SERVER")>=0){
			int orderBy=sql.toLowerCase().lastIndexOf("order by");
			String temp="";
			if(orderBy!=-1)
				temp+=sql.substring(orderBy);
			sql="SELECT TOP "+pageSize+" * FROM ("+removeOrders(sql)+")a_ WHERE ID NOT IN (SELECT TOP "+((pageNumber-1)*pageSize)+" ID "+removeSelect(sql)+")"+temp;
		}
		else if(type.indexOf("DB2")>=0)
			sql="SELECT * FROM (SELECT rownumber() AS RN, "+sql.substring(7)+") WHERE RN BETWEEN "+((pageNumber-1)*pageSize+1)+" AND "+(pageNumber*pageSize); 
		else
			throw new Exception("========对不起，此数据库暂不被支持智能分页========");
		return sql;
	}
	/**
	 * 根据给定语句及数据库类型生成对应的分页语句
	 * FIXME 注意此处应用于SQL Server要求查询必须有一个主键，并且为ID,注意在SQL SERVER 中用order by排序时候排序字段不要带前缀
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public static String getPageSqlByOrder(String sql,String type,int pageNumber,int pageSize,String sortorder) throws Exception {
		if((type.indexOf("MYSQL")>=0)||(type.indexOf("SQLITE")>=0))
			sql="select * from ("+sql+")_a order by "+sortorder+" LIMIT "+((pageNumber-1)*pageSize)+","+pageSize;
		else if(type.indexOf("ORACLE")>=0)
			sql="SELECT * FROM (SELECT a_.*,ROWNUM RN FROM ("+removeOrders(sql)+" order by "+sortorder+")a_ WHERE ROWNUM <= "+(pageNumber*pageSize)+")b_ WHERE b_.RN>="+((pageNumber-1)*pageSize+1); 
		else if(type.indexOf("SQL SERVER")>=0){
			sql="SELECT TOP "+pageSize+removeOrders(sql).substring(7) +" WHERE ID NOT IN (SELECT TOP "+((pageNumber-1)*pageSize)+" ID "+removeSelect(sql)+")"+" order by "+sortorder;
		}
		else if(type.indexOf("DB2")>=0)
			sql="SELECT * FROM (SELECT rownumber() AS RN, "+sql.substring(7)+" order by "+sortorder+") WHERE RN BETWEEN "+((pageNumber-1)*pageSize+1)+" AND "+(pageNumber*pageSize); 
		else
			throw new Exception("========对不起，此数据库暂不被支持智能分页========");
		return sql;
	}	
	/**
	 * 根据给定语句及数据库类型生成对应的分页语句
	 * FIXME 注意此处应用于SQL Server要求查询必须有一个主键，并且为ID,注意在SQL SERVER 中用order by排序时候排序字段不要带前缀
	 * @param sql
	 * @return
	 */
	public static String getTopSql(String sql,String type,int top) throws Exception {
		if((type.indexOf("MYSQL")>=0)||(type.indexOf("SQLITE")>=0))
			sql+=" LIMIT "+top;
		else if(type.indexOf("ORACLE")>=0)
			sql="SELECT * FROM (SELECT a_.*, ROWNUM row_num FROM ("+sql+")a_)b_ WHERE b_.row_num <="+top; 
		else if(type.indexOf("SQL SERVER")>=0){
			int orderBy=sql.toLowerCase().lastIndexOf("order by");
			String temp="";
			if(orderBy!=-1)
				temp+=sql.substring(orderBy);
			sql="SELECT TOP "+top+" "+sql.substring(7);
		}
		else if(type.indexOf("DB2")>=0)
			sql="SELECT * FROM (SELECT rownumber() as RN, "+sql.substring(7)+") WHERE RN <= "+top; 
		else
			throw new Exception("========对不起，此数据库暂不被支持智能提取前几条数据========");
		return sql;
	}
	/**
	 * 根据给定语句及数据库类型生成对应的分页语句
	 * FIXME 注意此处应用于SQL Server要求查询必须有一个主键，并且为ID,注意在SQL SERVER 中用order by排序时候排序字段不要带前缀
	 * @param sql
	 * @return
	 */
	public static String getTopSqlByOrder(String sql,String type,int top,String sortorder) throws Exception {
		if((type.indexOf("MYSQL")>=0)||(type.indexOf("SQLITE")>=0))
			sql="select * from ("+sql+")_a order by "+sortorder+" LIMIT "+top;
		else if(type.indexOf("ORACLE")>=0)
			sql="SELECT * FROM (SELECT a_.*, ROWNUM row_num FROM ("+removeOrders(sql)+" order by "+sortorder+")a_)b_ WHERE b_.row_num <="+top;
		else if(type.indexOf("SQL SERVER")>=0){
			int orderBy=sql.toLowerCase().lastIndexOf("order by");
			String temp="";
			if(orderBy!=-1)
				temp+=sql.substring(orderBy);
			sql="SELECT TOP "+top+" "+sql.substring(7)+" order by "+sortorder;
		}
		else if(type.indexOf("DB2")>=0)
			sql="SELECT * FROM (SELECT rownumber() as RN, "+sql.substring(7)+" order by "+sortorder+") WHERE RN <= "+top; 
		else
			throw new Exception("========对不起，此数据库暂不被支持智能提取前几条数据========");
		return sql;
	}
	/**
	 * 根据数据库类型、实体对象、主键生成insert语句和参数
	 * @param dbType　数据库类型
	 * @param bean　实体对象
	 * @param id　主键
	 * @return　insert语句和参数Object[]{insert语句,参数数组}
	 */
	public static Object[] getInsertsFromBean(String dbType,Object bean,Object id) throws Exception {
		Field[] fields = bean.getClass().getDeclaredFields();
		StringBuffer sql = new StringBuffer("INSERT INTO "+SysProperties.TABSATRT
				+ bean.getClass().getSimpleName().toUpperCase() + " (");
		StringBuffer sqlParms = new StringBuffer(") VALUES (");
		if(id!=null){
			sql.append("ID,");
			sqlParms.append(id+",");	
		}else if(dbType.indexOf("ORACLE")>=0){
		   sql.append("ID,");
		   sqlParms.append("SEQ_"+SysProperties.TABSATRT+ bean.getClass().getSimpleName().toUpperCase()+ ".nextval,");
		}			
		List<Object> parms = new ArrayList<Object>();
		for (Field key : fields) {
			String beanProperty = key.getName();
			key.setAccessible(true); 
			if (!"id".equals(beanProperty)) {
				Object beanPropertyVale = key.get(bean);
				if (beanPropertyVale != null && !"".equals(beanPropertyVale)) {
					sql.append(beanProperty.toUpperCase() + ",");
					sqlParms.append("?,");
					Type type=key.getAnnotation(Type.class);
					if(type!=null&&"LOB".equals(type.value())){
						parms.add(new ByteArrayInputStream(beanPropertyVale.toString().getBytes("GBK"))); 
					}else
						parms.add("null".equals(beanPropertyVale)?null:beanPropertyVale);
				}
			}
		}
		String outSql = sql.toString().substring(0, sql.length() - 1);
		outSql += (sqlParms.toString().substring(0, sqlParms.length() - 1))+ ")";
		return new Object[]{outSql,parms};
	}
	/**
	 * 根据数据库类型、实体对象生成select语句和参数
	 * @param dbType　数据库类型
	 * @param bean　实体对象
	 * @return　select语句和参数Object[]{select语句,参数数组}
	 */	
	public static Object[] getSelectFromBean(String dbType,Object bean) throws Exception {
		Field[] fields = bean.getClass().getDeclaredFields();
		StringBuffer sql = new StringBuffer("SELECT * FROM "+SysProperties.TABSATRT
				+ bean.getClass().getSimpleName().toUpperCase() + " WHERE 1=1");
		List<Object> parms = new ArrayList<Object>();
		for (Field key : fields) {
			String beanProperty = key.getName();
			key.setAccessible(true); 
			Object beanPropertyVale = key.get(bean);
			if (beanPropertyVale != null && !"".equals(beanPropertyVale)) {
				sql.append(" AND "+beanProperty.toUpperCase() + "=?");
				parms.add(beanPropertyVale);
			}
		}
		return new Object[]{sql.toString(),parms};
	}
	/**
	 * 根据实体对象生成delete语句和参数
	 * @param bean　实体对象
	 * @return　delete语句和参数Object[]{delete语句,参数数组}
	 */	
	public static Object[] getDeleteFromBean(Object bean) throws Exception {
		Field[] fields = bean.getClass().getDeclaredFields();
		StringBuffer sql = new StringBuffer("DELETE FROM "+SysProperties.TABSATRT
				+ bean.getClass().getSimpleName().toUpperCase() + " WHERE ");
		List<Object> parms = new ArrayList<Object>();
		for (Field key : fields) {
			String beanProperty = key.getName();
			key.setAccessible(true); 
			Object beanPropertyVale = key.get(bean);
			if (beanPropertyVale != null && !"".equals(beanPropertyVale)) {
				sql.append(beanProperty.toUpperCase() + "=? AND ");
				parms.add(beanPropertyVale);
			}
		}
		String outSql = sql.toString();
		if(parms.isEmpty())
			outSql=outSql.substring(0, outSql.length()-7);
		else
			outSql=outSql.substring(0, outSql.length()-5);
		return new Object[]{outSql.toString(),parms};
	}
	/**
	 * 根据实体对象生成update语句和参数
	 * @param bean　实体对象
	 * @return　update语句和参数Object[]{update语句,参数数组}
	 */	
	public static Object[] getUpdateFromBean(Object bean) throws Exception {
		Field[] fields = bean.getClass().getDeclaredFields();
		StringBuffer sql = new StringBuffer("UPDATE "+SysProperties.TABSATRT
				+ bean.getClass().getSimpleName().toUpperCase() + " SET ");
		List<Object> parms = new ArrayList<Object>();
		Object id = null;
		for (Field key : fields) {
			String beanProperty = key.getName();
			key.setAccessible(true);
			if ("id".equals(beanProperty)) {
				id = key.get(bean);
				if (id == null || "".equals(id))
					throw new Exception("========根据实体对象进行更新操作必须有主键值========");
			} else {
				Object beanPropertyVale = key.get(bean);
				sql.append(beanProperty + "=?,");
				Type type = key.getAnnotation(Type.class);
				if (type != null && ("LOB".equals(type.value()))) {
					parms.add(new ByteArrayInputStream(beanPropertyVale.toString().getBytes("GBK")));
				} else
					parms.add("null".equals(beanPropertyVale)?null:beanPropertyVale);
			}
		}
		String outSql = sql.toString().substring(0, sql.length() - 1)+ " WHERE ID=?";
		parms.add(id);
		return new Object[]{outSql.toString(),parms};
	}
	/**
	 * 根据实体类名、需要更新的键值对、主键生成update语句和参数
	 * @param beanClass　实体类名
	 * @param parms　需要更新的键值对
	 * @param id　更新的数据主键
	 * @return　update语句和参数Object[]{update语句,参数数组}
	 */	
	public static Object[] getUpdateByClass(Class beanClass,HashMap<String, Object> parms,Object id) throws Exception {
		if(id==null||parms==null|parms.isEmpty())
			throw new Exception("========根据Class进行更新操作必须有参数值和主键值========");
		Object[] keys=parms.keySet().toArray();
		Object[] values=parms.values().toArray();
		StringBuffer sql = new StringBuffer("UPDATE "+SysProperties.TABSATRT+ beanClass.getSimpleName().toUpperCase() + " SET ");
		List<Object> myPparms = new ArrayList<Object>();
		for (int i = 0; i < parms.size(); i++) {
			sql.append(keys[i].toString() + "=?,");
			myPparms.add(values[i]);
		}
		String outSql = sql.toString().substring(0, sql.length() - 1)+ " WHERE ID=?";
		myPparms.add(id);
		return new Object[]{outSql.toString(),myPparms};
	}	
}
