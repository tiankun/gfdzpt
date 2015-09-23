package com.web.sysAdmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.util.treeUtil;
import com.sysFrams.web.BaseAction;

public class mrbranchAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		Map searchMap=getParameterMap(request);
//		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
//							? "1" : request.getParameter("pageno");
//		
//		int pageNo=(new Integer(page_no)).intValue();
//		int pageSize=20;
//		
//		Page page=search(request,pageNo,pageSize);
//		
//		request.setAttribute("record", page.getThisPageElements());
//		request.setAttribute("page", page);
//		request.setAttribute("searchMap", searchMap);
//		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("select")) 
			return "/sysAdmin/mrbranchSelect.jsp";
		return "/sysAdmin/mrbranchList.jsp";
	}
	
	public String gettree(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		if(searchMap ==null || searchMap.size()<1 ){
			searchMap.put("parentid", "0");
		}else if(searchMap.get("name")==null||searchMap.get("name").equals("")){
			searchMap.put("parentid", "0");
		}
		   String sql = "select id,branchname,simplecode,parentid,(select branchname from mrbranch where id = t.parentid)parentbranchname,branchtype,delflag,address,dcode from mrbranch t where 1=1 /~ and id={id} ~/"
					+ "/~ and parentid={parentid} ~/ /~ and branchname like '%[branchname]%' ~/ order by id";
		   String treejson = treeUtil.buildMyTreeGrid(sql,searchMap);
		   treejson="["+treejson+"]";
		   setAjaxInfo(response, treejson);
		   return null;
	}
	
	public String getTreeJson(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		String sql = "select t.id,t.branchname as text from mrbranch t where 1=1 and t.delflag=0 "
				   +" /~ and t.parentid={parentid} ~/ ";
		   String treejson = "["+treeUtil.buildMyTreeGrid(sql,searchMap)+"]";
		setAjaxInfo(response,treejson);
		return null;
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
        return "/sysAdmin/mrbranchDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select id,branchname,simplecode,parentid,(select branchname from mrbranch where id = t.parentid)parentbranchname,branchtype,delflag,address,dcode from mrbranch t where 1=1 /~ and id={id} ~/"
		+ "/~ and branchname={branchname} ~/ order by id";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(getMapObject(request));			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/sysAdmin/mrbranchDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/sysAdmin/mrbranchDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM mrbranch WHERE ID in (?)", new Object[]{s_id});
		
		return "/sysAdmin/mrbranch!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("branchtype",codeTableUtil.getCodeMap("branchtype")); 
		request.setAttribute("delflag",codeTableUtil.getCodeMap("delflag")); 
	}	

}
