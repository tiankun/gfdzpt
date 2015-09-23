/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2013-8-9</p>
 * <p> @author 邹申昶</p>
 */
package com.sysFrams.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sysFrams.util.CommonProperties;


/**
 * 底层数据库基础处理
 */
public class DBManage {
	private static final String gDsjn = CommonProperties.getInstance().getDatasourceJNDIName(); // 链接池的链接名
	protected Boolean transaction=false;//是否打开事务	
	private static Context ctx=getInitialContext();
	protected Connection conn;
	protected PreparedStatement ptmt;
	protected Statement stmt;
	protected ResultSet rs=null;
	/**
	 * 设置事务打开状态
	 */
	public void setTransaction(Boolean transaction) {
		this.transaction = transaction;
	}		
	/**
	 * 获取一个InitialContext实例
	 * @return
	 */
	private static Context getInitialContext() {
	      if (ctx == null) {
	    	  try {
				ctx = new InitialContext();
			} catch (NamingException e) {
				e.printStackTrace();
			}
	      }
	      return ctx;
	}
	/**
	 * 获取数据库的链接 参数： 返回一个connection 对象
	 * @return Connection
	 */
	public Connection getConnection() throws Exception {
		// 用链接池方式得到connection对象
		if (conn == null) {
			DataSource ds = (DataSource) ctx.lookup(gDsjn);
			conn = ds.getConnection();
//			Class.forName("com.mysql.jdbc.Driver") ;   	  
//	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zjw?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=GBK","root","123456") ;   
		}
		return conn;
	}
	/**
	 * 获取所连接的数据库类型
	 * @return 返回所连接的数据库类型（大写）
	 */
	public String getDbType() throws Exception {
		String dbType;
		try{
			dbType=getConnection().getMetaData().getDatabaseProductName().toUpperCase();
		}finally{
			clearAllDB();			
		}
		return dbType;	
	}	
	/**
	 * 创建preparedstatement
	 * @param sql　要执行的预处理sql语句
	 */
	protected void createPrepareStatement(String sql) throws Exception {
		if (ptmt == null) {// 如果不存在就创建
		conn = getConnection();
		ptmt = conn.prepareStatement(sql);
		}
	}

	/**
	 * 创建Statement
	 */
	protected void createStatement() throws Exception {
		if (stmt == null) {// 如果不存在就创建
			conn = getConnection();
			stmt = conn.createStatement();
		}
	}
	/**
	 * 清除所有数据库链接和存储对象
	 */
	public void clearAllDB() throws Exception {
			if (rs != null) {// 关闭结果集				
				try {rs.close();} catch (Exception e) {}
				rs = null;
			}
			if (ptmt != null) {
				try {// 清除所有参数值					
					ptmt.clearParameters();
					ptmt.close();
				} catch (Exception e) {}
				ptmt = null;
			}
			if (stmt != null) {// 关闭statement
				try {stmt.clearBatch();stmt.close();} catch (Exception e) {}
				stmt = null;
			}
			if (!transaction&&conn != null) {// 关闭数据库的链接				
				try {conn.close();} catch (Exception e) {}
				conn = null;
			}
	}
	/**
	 * 根据给定参数执行sql语句
	 * @param sql　预执行sql语句
	 * @param values 传入的参数数组
	 * @return int 影响的记录数目
	 */
	public int executeSql(String sql, Object[] values) throws Exception {
		int reInt = 0;
		createPrepareStatement(sql);
		try {
			if (values != null)
				for (int i = 0; i < values.length; i++) {
					if (values[i] instanceof InputStream) {
						try {
							ptmt.setBinaryStream(i + 1,(InputStream) values[i],((InputStream) values[i]).available());
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else
						ptmt.setObject(i+1, values[i]);
				}
			reInt = ptmt.executeUpdate();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			transaction=false;
			throw e;
		} finally {
			clearAllDB();
		}
		return reInt;
	}
	/**
	 * 根据给定参数执行存储过程
	 * @param sql　预执行sql存储过程调用语句
	 * @param values 传入的参数数组
	 * @return boolean 执行存储过程成功与否
	 */
	public boolean callStoreProcedure(String sql, Object[] values)throws Exception {
		boolean reBool = false;
		conn = this.getConnection();
		CallableStatement callpsm = conn.prepareCall(sql);
		try {
			if (values != null)
				for (int i = 0; i < values.length; i++) {
					callpsm.setObject(i + 1, values[i]);
				}
			reBool = callpsm.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			// 关闭数据库的链接
			try {callpsm.close();} catch (Exception e) {}
			callpsm = null;
			// 关闭数据库的链接
			try {conn.close();} catch (Exception e) {}
			conn = null;
		}
		return reBool;
	}	
	/**
	 * 根据指定sql和参数获取一个List<Map<String,Object>>对象
	 * @param sql  执行的sql语句
	 * @param values 传入的参数数组
	 * @return 传回List<Map<String,Object>>对象
	 */
	public List<Map<String,Object>> getOutResultSet(String sql, Object[] values)throws Exception {
		List<Map<String,Object>> maps ;
		createPrepareStatement(sql);// 创建PreparedStatement对象
		try {
			if (values != null&&values.length>0)
				for (int i = 0; i < values.length; i++) {
					ptmt.setObject(i + 1, values[i]);
				}
			rs = ptmt.executeQuery();
			maps=RowProcessor.getMaps(rs);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}			
			transaction=false;
			throw e;
		} finally {
			clearAllDB();
		}
		return maps;
	}
	/**
	 * 根据指定sql和参数获取单条对象
	 * @param sql  执行的sql语句
	 * @param values 传入的参数数组
	 * @return 传回Map对象
	 */
	public Map getOneResultSet(String sql, Object[] values)throws Exception {
		Map map = new HashMap();
		int size = getResultSetSize(sql, values);
		if (size == 0)
			return map;
		else if (size > 1)
			throw new Exception("======此条件的数据不唯一======");
		else {
			createPrepareStatement(sql);// 创建PreparedStatement对象
			try {
				if (values != null&&values.length>0)
					for (int i = 0; i < values.length; i++) {
						ptmt.setObject(i + 1, values[i]);
					}
				rs = ptmt.executeQuery();
				map = RowProcessor.toMap(rs);
			} catch (Exception e) {
				transaction=false;
				throw e;
			} finally {
				clearAllDB();
			}
			return map;
		}
	}
	/**
	 * 获取指定sql语句的结果集条数 
	 * @param sql 执行的sql语句
	 * @param values 传入的参数数组
	 * @return 传回执行语句的结果集条数
	 */
	public int getResultSetSize(String sql, Object[] values)throws Exception {
		int size=0;
		createPrepareStatement(SqlTool.getCountSql(sql));// 创建PreparedStatement对象
		try {
			if (values != null&&values.length>0)
				for (int i = 0; i < values.length; i++) {
					ptmt.setObject(i + 1, values[i]);
				}
			rs = ptmt.executeQuery();
			rs.next();
			size=rs.getInt(1);
		} catch (Exception e) {
			transaction=false;
			throw e;
		} finally {
			clearAllDB();
		}
		return size;
	}
	/**
	 * 根据指定参数插入数据库并返回插入数据的主键值
	 * @param sql 预执行插入语句
	 * @param parms　参数
	 * @return　所插入数据的主键值
	 */
	public Long insertOfAutoId(String sql,Object[] parms) throws Exception {
		Long reInt = null;
		conn = getConnection();
		ptmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		try {
				for (int i = 0; i <  parms.length; i++) {
					if ( parms[i] instanceof InputStream) {
						try {
							ptmt.setBinaryStream(i + 1,(InputStream)  parms[i],((InputStream)  parms[i]).available());
						} catch (IOException e) {
							e.printStackTrace();
							throw new SQLException("Error setting BinaryStream value in PreparedStatement");
						}
					} else
						ptmt.setObject(i+1,  parms[i]==null?"": parms[i]);
				}
			if (1 == ptmt.executeUpdate()) {
				rs = ptmt.getGeneratedKeys();
				if (rs.next())// 打印插入记录主键值
					reInt=rs.getLong(1);
			} else 
				throw new Exception("插入失败！");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}			
			transaction=false;
			throw e;
		} finally {
			clearAllDB();
		}		
		return reInt;
	}	
}
