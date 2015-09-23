/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2008-9-26</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.sql.CLOB;

/**
 * ResultSet的加工类
 */
public class RowProcessor {
	public final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat format_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 把结果集的第一列记录转化成Set
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static Set<String> toSet(ResultSet rs) throws SQLException,Exception {
		Set<String> result = new HashSet<String>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		if (cols != 0)
			while (rs.next())
				result.add(rs.getString(1));
		return result;
	}	
	/**
	 * 把结果集的单行记录转化成Map
	 * @param rs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> toMap(ResultSet rs) throws SQLException,Exception {
		Map<String,Object> result = new CaseInsensitiveHashMap();
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		if (rs.next())
			for (int i = 1; i <= cols; i++) {
				int columnType=rsmd.getColumnType(i);
				switch (columnType) {
				case Types.CLOB:
					result.put(rsmd.getColumnLabel(i), ClobToString((oracle.sql.CLOB) rs.getClob(i)));
					break;
				case Types.BLOB:
					result.put(rsmd.getColumnLabel(i), rs.getBinaryStream(i));
                    //文件存盘
//					File fileOutput = new File("c:/backa.exe");
//					OutputStream fout = new FileOutputStream(fileOutput, true);
//				        //下面将BLOB数据写入文件
//				        byte[] b = new byte[2048];
//				        int len = 0;
//				        while((len = inputStream.read(b)) != -1) {
//				            fout.write(b, 0, len);
//				        }
//				        //依次关闭
//				        fout.close();
//				        inputStream.close();
					break;
				case Types.LONGVARBINARY:
					result.put(rsmd.getColumnLabel(i), inputStream2String(rs.getBinaryStream(i)));
					break;
				case Types.TIMESTAMP:
					Timestamp temp=rs.getTimestamp(i);
					if(temp==null)
						result.put(rsmd.getColumnLabel(i),temp);
					else
						result.put(rsmd.getColumnLabel(i),format_time.format(temp));
					break;
				case Types.DATE:
					Date tempDate=rs.getDate(i);
					if(tempDate==null)
						result.put(rsmd.getColumnLabel(i),tempDate);
					else
						result.put(rsmd.getColumnLabel(i),format.format(tempDate));
					break;					
				default:
					result.put(rsmd.getColumnLabel(i), rs.getObject(i));
					break;
				}
			}
		return result;
	}
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> getMaps(ResultSet rs) throws Exception {
		Date tempDate;
		Timestamp tempTime;
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		List list = new ArrayList();
		while (rs.next()) {
			Map row = new CaseInsensitiveHashMap();
			for (int i = 1; i <= cols; i++) {
				int columnType=rsmd.getColumnType(i);
				switch (columnType) {
				case Types.CLOB:
					row.put(rsmd.getColumnLabel(i), ClobToString((oracle.sql.CLOB) rs.getClob(i)));
					break;
				case Types.BLOB:
				    row.put(rsmd.getColumnLabel(i), rs.getBinaryStream(i));
					break;
				case Types.LONGVARBINARY:
					row.put(rsmd.getColumnLabel(i), inputStream2String(rs.getBinaryStream(i)));
					break;
				case Types.TIMESTAMP:
					tempTime=rs.getTimestamp(i);
					if(tempTime==null)
						row.put(rsmd.getColumnLabel(i),tempTime);
					else
						row.put(rsmd.getColumnLabel(i),format_time.format(tempTime));
					break;
				case Types.DATE:
					tempDate=rs.getDate(i);
					if(tempDate==null)
						row.put(rsmd.getColumnLabel(i),tempDate);
					else
						row.put(rsmd.getColumnLabel(i),format.format(tempDate));
					break;					
				default:
					row.put(rsmd.getColumnLabel(i), rs.getObject(i));
					break;
				}
			}
			list.add(row);
		} 
		return list;
	}
	public Object toBean(ResultSet rs, Class type) throws SQLException {
		return type;
	}

	public List toBeanList(ResultSet rs, Class type) throws SQLException {
		return null;
	}
	public static String inputStream2String(InputStream is) throws Exception {
		if (is != null) {
			BufferedReader in = new BufferedReader(new InputStreamReader(is,"GBK"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			try {
				while ((line = in.readLine()) != null) {
					buffer.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return buffer.toString();
		} else
			return "";
	}
	private static class CaseInsensitiveHashMap extends HashMap {

		/**
		 * @see java.util.Map#containsKey(java.lang.Object)
		 */
		public boolean containsKey(Object key) {
			return super.containsKey(key.toString().toLowerCase());
		}

		/**
		 * @see java.util.Map#get(java.lang.Object)
		 */
		public Object get(Object key) {
			return super.get(key.toString().toLowerCase());
		}

		/**
		 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
		 */
		@SuppressWarnings("unchecked")
		public Object put(Object key, Object value) {
			return super.put(key.toString().toLowerCase(), value);
		}

		/**
		 * @see java.util.Map#putAll(java.util.Map)
		 */
		public void putAll(Map m) {
			Iterator iter = m.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				Object value = m.get(key);
				this.put(key, value);
			}
		}

		/**
		 * @see java.util.Map#remove(java.lang.Object)
		 */
		public Object remove(Object key) {
			return super.remove(key.toString().toLowerCase());
		}
	}
	public static String ClobToString(CLOB clob) throws SQLException, IOException {
        String reString = "";
		if (clob != null) {
			Reader is = clob.getCharacterStream();// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			reString = sb.toString();
		}
        return reString;
    }	
}