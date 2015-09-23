/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2013-8-9</p>
 * <p> @author 邹申昶</p>
 */
package com.sysFrams.db;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.sysFrams.util.CommonProperties;
import com.sysFrams.util.Log;
import com.sysFrams.util.Type;


/**
 * 专为处理Oracle二进制相关
 */
public class OracleDBManage extends DBManage {
	/**
	 * 根据实体bean进行添加操作 FIXME 只针对ORACLE数据库并且添加的数据有CLOB或者BLOB形
	 * XXX 此处针对Oracle不用默认序列来做id（当需要自定义id值的时候）
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public boolean insertByBeanLob(Object bean,Long...id) throws Exception {
		long myid ;
		//进行数据库插入操作
		Field[] fields = bean.getClass().getDeclaredFields();
		StringBuffer sql = new StringBuffer("INSERT INTO "+CommonProperties.TABSATRT
				+ bean.getClass().getSimpleName().toUpperCase() + " (");
		StringBuffer sqlParms = new StringBuffer(") VALUES (");
		List<String> upParm = new ArrayList<String>();  //lob类型参数列表
		List<Object> upParms = new ArrayList<Object>();  //lob类型参数值
		List<String> upType=new ArrayList<String>();  //lob类型列表
		List<Object> parms = new ArrayList<Object>();
		sql.append("ID,");
		sqlParms.append("?,");
		if(id==null||id.length<1){
			myid=getSeq(bean.getClass().getSimpleName());
		}else{
			myid=id[0];
		}
		parms.add(myid);
		for (Field key : fields) {
			String beanProperty = key.getName();
			key.setAccessible(true); 
			Type type=key.getAnnotation(Type.class);
			if (!"id".equals(beanProperty)) {
				Object beanPropertyVale = key.get(bean);
				if (beanPropertyVale != null && !"".equals(beanPropertyVale)||type!=null&&("CLOB".equals(type.value())||"BLOB".equals(type.value()))) {
					sql.append(beanProperty.toUpperCase() + ",");
					if(type!=null&&"LOB".equals(type.value())){
						sqlParms.append("?,");
						parms.add(new ByteArrayInputStream(beanPropertyVale.toString().getBytes("GBK"))); 
					}else if(type!=null&&"CLOB".equals(type.value())){
						upType.add("CLOB");
						sqlParms.append("empty_clob(),");
						if(beanPropertyVale != null && !"".equals(beanPropertyVale)){
						upParm.add(beanProperty.toUpperCase());
						upParms.add(beanPropertyVale);
						}
					}else if(type!=null&&"BLOB".equals(type.value())){
						upType.add("BLOB");
						sqlParms.append("empty_blob(),");
						if(beanPropertyVale != null && !"".equals(beanPropertyVale)){
						upParm.add(beanProperty.toUpperCase());
						upParms.add(beanPropertyVale);
						}
					}else{
						sqlParms.append("?,");
						parms.add("null".equals(beanPropertyVale)?null:beanPropertyVale);
					}
				}
			}
		}
		String outSql = sql.toString().substring(0, sql.length() - 1);
		outSql += (sqlParms.toString().substring(0, sqlParms.length() - 1))
				+ ")";
		Log.debug(outSql);
		if(executeSql(outSql, parms.toArray())>0&&upParm!=null&&!upParm.isEmpty()){//进行更新操作
			String upSql="SELECT ";
			for (int i = 0; i < upParm.size(); i++) {
				upSql+=upParm.get(i)+",";
			}
			upSql=upSql.substring(0, upSql.length() - 1);
			upSql+=" FROM "+CommonProperties.TABSATRT+bean.getClass().getSimpleName().toUpperCase()+" WHERE ID="+myid+" FOR UPDATE";
			Log.debug(upSql);
			conn=getConnection();
			conn.setAutoCommit(false); 
			stmt=conn.createStatement();
			rs = stmt.executeQuery(upSql);    
		    try {
				if (rs.next())    
				{    
					for (int i = 0; i < upParm.size(); i++) {
						if("CLOB".equals(upType.get(i))){
						    //得到java.sql.Clob对象后强制转换为oracle.sql.CLOB    
				            oracle.sql.CLOB clob = (oracle.sql.CLOB)rs.getClob(upParm.get(i));
				            Writer out = clob.setCharacterStream(0);
				            out.write(upParms.get(i).toString());
				            out.flush();
				            out.close();
						}else{
							 oracle.sql.BLOB blob = (oracle.sql.BLOB)rs.getBlob(upParm.get(i));    
							 OutputStream os = blob.setBinaryStream(0); // 建立输出流
							 byte[] buff = new byte[2048];  //用做文件写入的缓冲
							 int bytesRead;
							 while(-1 != (bytesRead = ((InputStream)upParms.get(i)).read(buff, 0, buff.length))) {
							   os.write(buff, 0, bytesRead);
							  }
							 os.flush();
							 os.close(); 
							 }      
						}
					if(!transaction)//总事务未打开则执行提交
						conn.commit(); 
					}
				return true;
			} catch (Exception e) {//回滚数据
				transaction=false;
				conn.rollback();
				executeSql("DELETE FROM "+CommonProperties.TABSATRT+ bean.getClass().getSimpleName().toUpperCase() + " WHERE ID=?",new Object[]{id});
				throw e;
			}finally{
				clearAllDB();
			}
		}else
		  return false;
	}
	/**
	 * 根据实体bean进行更新操作 FIXME 只针对ORACLE数据库并且添加的数据有CLOB或者BLOB形
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public int updateByBeanLob(Object bean) throws Exception {
		int reInt = 0;
		Field[] fields = bean.getClass().getDeclaredFields();
		StringBuffer sql = new StringBuffer("UPDATE "+CommonProperties.TABSATRT+ bean.getClass().getSimpleName().toUpperCase() + " SET ");
		List<String> upParm = new ArrayList<String>();  //lob类型参数列表
		List<Object> upParms = new ArrayList<Object>();  //lob类型参数值
		List<String> upType=new ArrayList<String>();  //lob类型列表
		List<Object> parms = new ArrayList<Object>();
		Object id = null;
		for (Field key : fields) {
			String beanProperty = key.getName();
			key.setAccessible(true);
			if ("id".equals(beanProperty)) {
				id = key.get(bean);
				if (id == null || "".equals(id) || "0".equals(id))
					return reInt;
			} else {
				Object beanPropertyVale = key.get(bean);
				Type type=key.getAnnotation(Type.class);
				if(type!=null&&("CLOB".equals(type.value()))){
					upType.add("CLOB");
					if(beanPropertyVale != null && !"".equals(beanPropertyVale)){
						upParm.add(beanProperty.toUpperCase());
						upParms.add(beanPropertyVale);
						}
				}else if(type!=null&&("BLOB".equals(type.value()))){
					upType.add("BLOB");
					if(beanPropertyVale != null && !"".equals(beanPropertyVale)){
						upParm.add(beanProperty.toUpperCase());
						upParms.add(beanPropertyVale);
						}
				}else if (beanPropertyVale != null) {
					sql.append(beanProperty.toUpperCase() + "=?,");
					if(type!=null&&("LOB".equals(type.value()))){
						parms.add(new ByteArrayInputStream(beanPropertyVale.toString().getBytes("GBK"))); 
					}else
					parms.add("null".equals(beanPropertyVale)?null:beanPropertyVale);
				}
			}
		}
		if ((parms.isEmpty()&&upParms.isEmpty())|| id == null || "".equals(id))
			return reInt;
		else{
		if (!parms.isEmpty()) {
				String outSql = sql.toString().substring(0, sql.length() - 1)+ " WHERE ID=?";
				parms.add(id);
				Log.debug(outSql);
				reInt = executeSql(outSql, parms.toArray());
			}
		if(!upParm.isEmpty()){
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			//清空CLOB和BLOB
			String temp="UPDATE "+CommonProperties.TABSATRT+bean.getClass().getSimpleName().toUpperCase()+" SET ";
			for (int i = 0; i < upParm.size(); i++) {
				temp+=upParm.get(i)+"="+("CLOB".equals(upType.get(i))?"empty_clob()":"empty_blob()")+",";
			}
			temp = temp.substring(0, temp.length() - 1);
			stmt.executeUpdate(temp+" WHERE ID=" + id );
		    //更新CLOB和BLOB
		    String upSql = "SELECT ";
			for (int i = 0; i < upParm.size(); i++) {
				upSql += upParm.get(i) + ",";
			}
			upSql = upSql.substring(0, upSql.length() - 1);
			upSql += " FROM "+CommonProperties.TABSATRT + bean.getClass().getSimpleName().toUpperCase()+ " WHERE ID=" + id + " FOR UPDATE";
			Log.debug(upSql);
			rs = stmt.executeQuery(upSql);
			try {
				if (rs.next()) {
					for (int i = 0; i < upParm.size(); i++) {
						if ("CLOB".equals(upType.get(i))) {
							// 得到java.sql.Clob对象后强制转换为oracle.sql.CLOB
							oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob(upParm.get(i));
							Writer out = clob.setCharacterStream(0);
							out.write(upParms.get(i).toString());
							out.flush();
							out.close();
						} else {
							 oracle.sql.BLOB blob = (oracle.sql.BLOB)rs.getBlob(upParm.get(i));    
							 OutputStream os = blob.setBinaryStream(0); // 建立输出流
							 BufferedOutputStream output = new BufferedOutputStream(os); 
							 byte[] buff = new byte[2048];  //用做文件写入的缓冲
							 int bytesRead;
							 while(-1 != (bytesRead = ((InputStream)upParms.get(i)).read(buff, 0, buff.length))) {
							   output.write(buff, 0, bytesRead);
							  }
							 output.close();
							 os.close(); 
						}
					}
					if(!transaction)//总事务未打开则执行提交
						conn.commit(); 
				}
			} catch (Exception e) {// 回滚数据
				transaction=false;
				conn.rollback();
				throw e;
			} finally {
				clearAllDB();
			}
		}
		}
		return reInt;
	}		
	/**
	 * 获取数据库的序列值
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public Long getSeq(String beanName) throws Exception {	
		Long seq_id = 0L;		
        //获取插入的主键值
		createPrepareStatement("SELECT SEQ_"+CommonProperties.TABSATRT+beanName.toUpperCase()+".NEXTVAL FROM DUAL");
		try {
			rs = ptmt.executeQuery();
			rs.next();
			seq_id=rs.getLong(1);
		} catch (Exception e) {
			transaction=false;
			throw e;
		} finally {
			clearAllDB();
		}
		return seq_id;
	}
}
