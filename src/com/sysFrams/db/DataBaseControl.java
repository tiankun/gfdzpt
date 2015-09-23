/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2008-7-31</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sysFrams.db.Page;
import com.sys.util.SqlTool1;
import com.sys.util.SysProperties;
import com.sysFrams.util.CommonProperties;

/**
 * 数据库操作的基类，可在mysql,sql server,oracle,db2,sqlite上通用，但是要求每个表的主键必须为id且自增（oracle使用表名的序列名）
 * oracle存储大对象用Long Raw字段类型
 */
public final class DataBaseControl {
	private DBManage dBManage;
	/**
	 * 初始化方法
	 */
	private DataBaseControl() {
		if(CommonProperties.getInstance().getDbType().indexOf("ORACLE")>=0)
			dBManage=new OracleDBManage();
		else
			dBManage=new DBManage();	
	}
	/**
	 * 产生一个对象的实例
	 */
	public static DataBaseControl getInstance() {
		return new DataBaseControl();
	}
	/**
	 * 获取数据库的链接 参数： 返回一个connection 对象
	 * @return Connection
	 */
	public Connection getConnection() throws Exception{
		return dBManage.getConnection();
	}
	/**
	 * 打开事务
	 */
	public void beginTransaction(){
		try {
			dBManage.setTransaction(true);
			getConnection().setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 事务结束并关闭
	 */
	public void endTransaction(){
		try {
			getConnection().commit();
		} catch (Exception e) {
			try {
				getConnection().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				dBManage.setTransaction(false);
				dBManage.clearAllDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据给定参数执行sql语句
	 * @param sql　预执行sql语句
	 * @param values 传入的参数数组
	 * @return int 影响的记录数目
	 */
	public int executeSql(String sql, Object[] values) throws Exception {
		return dBManage.executeSql(sql, values);
	}
	/**
	 * 根据给定参数执行存储过程
	 * @param sql　预执行sql存储过程调用语句
	 * @param values 传入的参数数组
	 * @return boolean 执行存储过程成功与否
	 */
	public boolean callStoreProcedure(String sql, Object[] values)throws Exception {
		return dBManage.callStoreProcedure(sql, values);
	}	
	/**
	 * 根据指定sql和参数获取一个List<Map<String,Object>>对象
	 * @param sql  执行的sql语句
	 * @param values 传入的参数数组
	 * @return 传回List<Map<String,Object>>对象
	 */
	public List<Map<String,Object>> getOutResultSet(String sql, Object[] values)throws Exception {
		return dBManage.getOutResultSet(sql, values);
	}
	/**
	 * 根据指定sql和参数获取单条对象
	 * @param sql  执行的sql语句
	 * @param values 传入的参数数组
	 * @return 传回Map对象
	 */
	public Map<String,Object> getOneResultSet(String sql, Object[] values)throws Exception {
			return dBManage.getOneResultSet(sql, values);
	}
	/**
	 * 获取指定sql语句的结果集条数 
	 * @param sql 执行的sql语句
	 * @param values 传入的参数数组
	 * @return 传回执行语句的结果集条数
	 */
	public int getResultSetSize(String sql, Object[] values)throws Exception {
		return dBManage.getResultSetSize(sql, values);
	}	
    /**
     * 进行分页处理
     * @param sql 查询语句（无任何修饰）
     * @param values　查询参数
     * @param pageNumber　当前页
     * @param pageSize　页大小
     * @return　返回页对象
     */
	public Page getPageResultSet(String sql, Object[] values,int pageNumber,int pageSize)
			throws SQLException, Exception {
        int count=getResultSetSize(sql,values);
        if(count==0)
		    return new Page(pageNumber,pageSize,count,null);
        else{
        	//处理页码超出范围
        	pageNumber=pageNumber<0?1:pageNumber;
        	int countPage=(count + pageSize -1) / pageSize ;
        	pageNumber=pageNumber>countPage?countPage:pageNumber;
        	String pageSql=SqlTool.getPageSql(sql,CommonProperties.getInstance().getDbType(),pageNumber,pageSize);	
        	return new Page(pageNumber,pageSize,count,getOutResultSet(pageSql,values));
        }
        	
	}
    /**
     * 按照排序进行分页处理(sql语句内部需要写分页)
     * @param sql 查询语句（无任何修饰）
     * @param values　查询参数
     * @param pageNumber　当前页
     * @param pageSize　页大小
     * @return　返回页对象
     */
	@SuppressWarnings("unchecked")
	public Page getPageResultSet(String sql, Object[] values,int pageNumber,int pageSize,String sortname,String sortorder)
			throws SQLException, Exception {
        int count=getResultSetSize(sql,values);
        if(count==0)
		    return new Page(pageNumber,pageSize,count,null);
        else{
        	//处理页码超出范围
        	pageNumber=pageNumber<0?1:pageNumber;
        	int countPage=(count + pageSize -1) / pageSize ;
        	pageNumber=pageNumber>countPage?countPage:pageNumber;        	
        	String pageSql=SqlTool.getPageSqlByOrder(sql,CommonProperties.getInstance().getDbType(),pageNumber,pageSize,sortname,sortorder);	
        	return new Page(pageNumber,pageSize,count,getOutResultSet(pageSql,values));
        }
        	
	}	
	///////////////////////////////////////////////////////////////////////
	
	
	
	/**
     * 按照排序进行分页处理(sql语句内部需要写分页)
     * @param sql 查询语句（无任何修饰）
     * @param values　查询参数
     * @param pageNumber　当前页
     * @param pageSize　页大小
     * @return　返回页对象
     */
	@SuppressWarnings("unchecked")
	public Page getPageResultSet1(String sql, Object[] values,int pageNumber,int pageSize,String sortorder)
			throws SQLException, Exception {
        int count=getResultSetSize(sql,values);
        if(count==0)
		    return new Page(pageNumber,pageSize,count,null);
        else{
        	//处理页码超出范围
        	pageNumber=pageNumber<0?1:pageNumber;
        	int countPage=(count + pageSize -1) / pageSize ;
        	pageNumber=pageNumber>countPage?countPage:pageNumber;
        	String pageSql=null;
        	if(sortorder==null)
        		pageSql=SqlTool1.getPageSql(sql,SysProperties.getInstance().getDbType(),pageNumber,pageSize);	
        	else        		
        		pageSql=SqlTool1.getPageSqlByOrder(sql,SysProperties.getInstance().getDbType(),pageNumber,pageSize,sortorder);	
        	return new Page(pageNumber,pageSize,count,getOutResultSet(pageSql,values));
        }
        	
	}	
	/////////////////////////////////////
    /**
     * 获取指定查询语句的前几条数据
     * @param sql 查询语句（无任何修饰）
     * @param values　查询参数
     * @param top　查询条数
	 * @return 传回List<Map<String,Object>>对象
     */
	public List<Map<String,Object>> getTopResultSet(String sql, Object[] values, int top)
			throws SQLException, Exception {
		String pageSql = SqlTool.getTopSql(sql, CommonProperties.getInstance().getDbType(), top);
		return getOutResultSet(pageSql, values);
	}
    /**
     * 按照排序获取指定查询语句的前几条数据
     * @param sql 查询语句（无任何修饰）
     * @param values　查询参数
     * @param top　查询条数
     * @param sortname 排序字段
     * @param sortname　排序方式（ASC|DESC）
	 * @return 传回List<Map<String,Object>>对象
     */
	public List<Map<String,Object>> getTopResultSet(String sql, Object[] values, int top,String sortname,String sortorder)
			throws SQLException, Exception {
		String pageSql = SqlTool.getTopSqlByOrder(sql, CommonProperties.getInstance().getDbType(), top,sortname,sortorder);
		return getOutResultSet(pageSql, values);
	}		
	/**
	 * 根据实体bean进行添加操作
	 * 此处针对不用默认序列来做id（当需要自定义id值的时候）
	 * @throws Exception
	 */
	public int insertByBean(Object bean,Object...id) throws Exception {
		int reInt=0;
		Object myId=null;
		if(id!=null&&id.length>0)
			myId=id[0];
		Object[] inserts=SqlTool.getInsertsFromBean(CommonProperties.getInstance().getDbType(), bean, myId);
		String sql=(String) inserts[0];
		List<Object> parms =(List<Object>)inserts[1];
		if (parms.isEmpty())
			return reInt;
		else{
			reInt = dBManage.executeSql(sql, parms.toArray());
			return reInt;			
		}
	}
	/**
	 * 根据实体bean进行添加操作并返回主键（XXX 针对自动生成主键数据库）
	 * @throws Exception
	 */
	public Long insertByBeanOfAutoId(Object bean) throws Exception {
		Long reInt=null;
		Object[] inserts=SqlTool.getInsertsFromBean(CommonProperties.getInstance().getDbType(), bean,null);
		String sql=(String) inserts[0];
		List<Object> parms =(List<Object>)inserts[1];
		if (parms.isEmpty())
			return reInt;
		else{
			reInt = dBManage.insertOfAutoId(sql, parms.toArray());
			return reInt;			
		}
	}
	/**
	 * 根据实体bean进行更新操作(必须有主键值)
	 */
	public int updateByBean(Object bean) throws Exception {
		Object[] inserts=SqlTool.getUpdateFromBean(bean);
		String sql=(String) inserts[0];
		List<Object> parms =(List<Object>)inserts[1];
		return executeSql(sql, parms.toArray());
	}
	/**
	 * 根据实体类,更新属性,更新值进行更新操作（只针对指定id值）
	 * @param parms 需要更新的参数/值键值队
	 * @param id  需要更新的数据的id值
	 */
	public int updateByClass(Class beanClass,HashMap<String, Object> parms,Object id) throws Exception {
		if(id==null||parms==null|parms.isEmpty())
			return 0;
		Object[] inserts=SqlTool.getUpdateByClass(beanClass, parms, id);
		String sql=(String) inserts[0];
		List<Object> myParms =(List<Object>)inserts[1];
		return executeSql(sql, myParms.toArray());
	}	
	/**
	 * 根据实体bean属性进行删除操作
	 */
	public int deleteByBean(Object bean) throws Exception {
		Object[] deletes=SqlTool.getDeleteFromBean( bean);
		String sql=(String) deletes[0];
		List<Object> parms =(List<Object>)deletes[1];
		return executeSql(sql, parms.toArray());
	}
	/**
	 * 获取单条对象,传入给PreparedStatement对象的参数存储在Map对象中
	 * @param tableName  对应的表名字
	 * @param values 存储传入给PreparedStatement对象的参数
	 * @return 传回Map对象
	 */
	public Map getByBean(Object bean)throws SQLException, Exception {
		Object[] inserts=SqlTool.getSelectFromBean(CommonProperties.getInstance().getDbType(), bean);
		String sql=(String) inserts[0];
		List<Object> parms =(List<Object>)inserts[1];
		return dBManage.getOneResultSet(sql,parms.toArray());
	}
	/**
	 * 获取单条对象,传入给PreparedStatement对象的参数存储在Map对象中
	 * @param bean  对应的表名字
	 * @param values 存储传入给PreparedStatement对象的参数
	 * @return 传回page对象
	 */
	public Page getPageByBean(Object bean,int pageNumber,int PageSize)throws SQLException, Exception {
		Object[] inserts=SqlTool.getSelectFromBean(CommonProperties.getInstance().getDbType(), bean);
		String sql=(String) inserts[0];
		List<Object> parms =(List<Object>)inserts[1];
		return getPageResultSet(sql, parms.toArray(), pageNumber, PageSize);
	}	
	/**
	 * 获取所有符合条件的对象,传入给PreparedStatement对象的参数存储在Map对象中
	 * @param values 存储传入给PreparedStatement对象的参数
	 * @return 传回Collection对象
	 */
	public List<Map<String,Object>> getCollectionByBean(Object bean)throws SQLException, Exception {
		Object[] inserts=SqlTool.getSelectFromBean(CommonProperties.getInstance().getDbType(), bean);
		String sql=(String) inserts[0];
		List<Object> parms =(List<Object>)inserts[1];
		return dBManage.getOutResultSet(sql, parms.toArray());
	}
	/**
	 * 根据实体bean进行添加操作 FIXME 只针对ORACLE数据库并且添加的数据有CLOB或者BLOB形
	 * 此处针对Oracle不用默认序列来做id（当需要自定义id值的时候）
	 */
	public boolean insertByBeanLob(Object bean,Long...id) throws Exception {
		return ((OracleDBManage)dBManage).insertByBeanLob(bean,id);
	}
	/**
	 * 根据实体bean进行更新操作 FIXME 只针对ORACLE数据库并且添加的数据有CLOB或者BLOB形
	 */
	public int updateByBeanLob(Object bean) throws Exception {
		return ((OracleDBManage)dBManage).updateByBeanLob(bean);
	}
	/**
	 * 获取oracle数据库的序列值
	 * @param beanClass
	 */
	public Long getSeq(Class beanClass) throws Exception {	
		return ((OracleDBManage)dBManage).getSeq(beanClass.getSimpleName());
	}
}
