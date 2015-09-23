package com.sysFrams.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import com.alibaba.fastjson.JSON;
import com.sysFrams.db.DataBaseControl;

public class treeUtil {
	/**
	 *通用easyUI树tree生成,带link方式
	 */
	public static String easyUItree(String sql,String _rootParentId,String _key,String _value,String _url) throws Exception{
		String fun = buildEasyUItree(sql,_rootParentId,_key,_value,_url);
		return fun.replaceFirst("<ul>", "<ul id=\"tree1\" class=\"easyui-tree\" style=\"border:0;\">");		 
	}
	/**
	 *通用easyUI树tree生成,带checkBox
	 */
	public static String easyUItreeCheckBox(String sql,String _rootParentId,String _key,String _value,String _url) throws Exception{
		String fun = buildEasyUItree(sql,_rootParentId,_key,_value,_url);
		return fun.replaceFirst("<ul>", "<ul id=\"tree1\" class=\"easyui-tree\" data-options=\"animate:true,checkbox:true\" style=\"border:0;\">");		 
	}
	//通用easyUI树tree生成
	private static String buildEasyUItree(String sql,String _rootParentId,String _key,String _value,String _url) throws Exception{
		DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		StringBuffer script = new StringBuffer();
		List<Object> list = (List)dataBaseControl.getOutResultSet(sql, new Object[]{_rootParentId});
		if(list==null)return "";
			for (Object object : list) {
				Map item = (Map)object;
				if(script.length()<1) script.append("<ul></ul>");
				
				if(item.get("treenodetype").toString().indexOf("Catalog")>-1){
					script=script.replace(script.length() -5, script.length(),
							"<li><span>" + item.get(_value).toString() + "</span>replace_item</li></ul>");
					//递归！
					String _sbmenu = buildEasyUItree(sql,item.get(_key).toString(),_key,_value,_url);
	                if ((_sbmenu == "") || (_sbmenu == null)) _sbmenu = "";
	                int pos = script.indexOf("replace_item");
	                script=script.replace(pos, pos+12, _sbmenu);
				}
				if(item.get("treenodetype").toString().indexOf("Leaf")>-1){
					script = script.replace(script.length() -5, script.length(), 
							"<li><a href=\"javascript:f_addTab('"
							+ item.get(_value) + "','" 
							+ item.get(_url) + "')\" class=\"l-link2\">"
							+ item.get(_value)+"</a></li></ul>");
				}
			}

		return script.toString();
	}
	
	/**
	 *通用easyUI树tree所需json数据
	 */
	public static String buildTreeJson(String sql,String _rootParentId,String _key,String _value) throws Exception{
		DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		StringBuffer script = new StringBuffer();
		List<Object> list = (List)dataBaseControl.getOutResultSet(sql, new Object[]{_rootParentId});
		if(list==null)return "";
			script.append("[");
			for (Object object : list) {
				Map item = (Map)object;
				if(script.length()>2)script.append(",");
				
				script=script.append(
					"{\"id\":"+item.get(_key)+",\"text\":\""+item.get(_value)+"\",\"children\":replace_item}");
					
					//递归！
				String _sbmenu = buildTreeJson(sql,item.get(_key).toString(),_key,_value);
	            if ((_sbmenu == "") || (_sbmenu == null)) _sbmenu = "";
	            int pos = script.indexOf("replace_item");
	            script=script.replace(pos, pos+12, _sbmenu);
			}
			script.append("]");
		
		return script.toString();
	}	
	public static String buildMyTreeGrid(String sql,Map searchMap) throws Exception{
		DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		StringBuffer script = new StringBuffer();
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		List<Object> list = (List)dataBaseControl.getOutResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray());
		if(list==null)return "";
			//script.append("[");
			for (Object object : list) {
				if(script.length()>2)script.append(",");
				Map item = (Map)object;
				String str = JSON.toJSONString(item);
				str = str.substring(0, str.indexOf("}"))+ ",\"children\":[]"+"}";
                //递归！
				HashMap<String, String> _sub=new HashMap<String, String>();
				_sub.put("parentid" , item.get("id").toString() );
				String _sbmenu = buildMyTreeGrid(sql,_sub);
				if (_sbmenu == null||_sbmenu.equals("")){ 
					str = str.replace(",\"children\":[]", "");
				}else{
					str = str.substring(0,str.indexOf("]}"))+_sbmenu+"]}";
				}
	            
				script.append(str);
			}
			//script.append("]");

		return script.toString();
	}

	/**
	 *通用easyUI树tree所需json数据
	 */
	public static String buildTreeGridJson(String sql,Object[] value) throws Exception{
		DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		StringBuffer script = new StringBuffer();
		List<Object> list = (List)dataBaseControl.getOutResultSet(sql, value);
		if(list==null)return "";
			script.append("{\"total\":"+list.size()+",\"rows\":[");			
			for (Object object : list) {
				if(script.length()>25)script.append(",");
				Map item = (Map)object;
				String str = JSON.toJSONString(item);
				String tstr = str.substring(0, str.indexOf("}"));
                tstr = tstr + ",\"_parentId\":"+item.get("parentid");
                tstr = tstr +"}";
				script.append(tstr);
			}			
			script.append("]}");
		
		return script.toString();
	}
	
	/**
	 *通用easyUI树tree所需json数据
	 */
	public static String buildTreeJson(String sql,String _rootParentId,String _id,String _key,String _value,String open_state) throws Exception{
		DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		StringBuffer script = new StringBuffer();
		List<Object> list = (List)dataBaseControl.getOutResultSet(sql, new Object[]{_rootParentId});
		script.append("[");
		if(list!=null && list.size() > 0){
			for (Object object : list) {
				Map item = (Map)object;
				if(script.length()>2)script.append(",");
				
				script=script.append("{\"id\":"+item.get(_key)+",\"text\":\""+item.get(_value)+"\",\"children\":replace_item");
					
					//递归！
				String _sbmenu =  buildTreeJson(sql,item.get(_id).toString(),_id,_key,_value,open_state);
	            if ((_sbmenu == "") || (_sbmenu == null)) _sbmenu = "[]";
	            int pos = script.indexOf("replace_item");
	            script=script.replace(pos, pos+12, _sbmenu);
				if (open_state!=null&&open_state.equals("closed")&&!_sbmenu.equals("[]")) {
					script=script.append(",\"state\":\"closed\"");
				}
				script=script.append("}");
			}
		}
		script.append("]");
		return script.toString();
	}
	
	//通用ligerUI树tree生成
	public static String buildLigerUItree(String sql,String _rootParentId,String _key,String _value,String _url) throws Exception{
		DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		StringBuffer script = new StringBuffer();
		List<Object> list = (List)dataBaseControl.getOutResultSet(sql, new Object[]{_rootParentId});
		if(list==null)return "";
			for (Object object : list) {
				Map item = (Map)object;
				if(script.length()<1) script.append("<ul></ul>");
				
				if(item.get("treenodetype").toString().indexOf("Catalog")>-1){
					script=script.replace(script.length() -5, script.length(),
							"<li><span>" + item.get(_value).toString() + "</span>replace_item</li></ul>");
					//递归！
					String _sbmenu = buildLigerUItree(sql,item.get(_key).toString(),_key,_value,_url);
	                if ((_sbmenu == "") || (_sbmenu == null)) _sbmenu = "";
	                int pos = script.indexOf("replace_item");
	                script=script.replace(pos, pos+12, _sbmenu);
				}
				if(item.get("treenodetype").toString().indexOf("Leaf")>-1){
					script = script.replace(script.length() -5, script.length(), 
							"<li url=\""+ item.get(_url) + "\"><span>"+ item.get(_value)+"</span></li></ul>");
				}
			}

		return script.toString();
	}

	//通用ligerUI导航菜单div+tree生成
	public static String buildLigerUIDivTree(String sql,String _rootParentId,String _key,String _value,String _url) throws Exception{
		DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		StringBuffer script = new StringBuffer();
		List<Object> list = (List)dataBaseControl.getOutResultSet(sql, new Object[]{_rootParentId});
		if(list==null)return "";
			for (Object object : list) {
				Map item = (Map)object;
				script.append("<div title=\""+ item.get(_value)+"\" class=\"l-scroll\">");
				//递归！
				String _sbmenu = buildLigerUItree(sql,item.get(_key).toString(),_key,_value,_url);
                if ((_sbmenu == "") || (_sbmenu == null)) _sbmenu = "";
                if(_sbmenu.length()>9) _sbmenu = "<ul class=\"funlist\">"+ _sbmenu.substring(4);
                script=script.append(_sbmenu+"</div>");
			}

		return script.toString();
	}
}
