package com.web.sysAdmin;

import java.util.ArrayList;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;

public class codeAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
							? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("searchMap", searchMap);
		buildDDL(request);
		return "/sysAdmin/codeList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
        return "/sysAdmin/codeDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		String sql= "select * from code where 1=1 and kwhbz='1' " 
				+"/~ and id={id}~/"
		          + "/~ and dmlb = {dmlb} ~/"   
		          + "/~ and dmlbmc like '%[dmlbmc]%' ~/"
		          + " order by dmlb, plsx"; 
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql, getParameterMap(request));
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(), pageNo,pageSize);
		return page;
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(getMapObject(request));
			codeTableUtil.cleanCodeMap();
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/sysAdmin/codeDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			codeTableUtil.cleanCodeMap();
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		return "/sysAdmin/codeDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM code WHERE ID in (?)", new Object[]{s_id});
		codeTableUtil.cleanCodeMap();
		return "/sysAdmin/codeList.jsp";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}
