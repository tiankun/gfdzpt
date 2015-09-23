package com.web.ds.tbs;

import java.util.ArrayList;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Ds_tbsAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	//任务分解
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/ds/tbs/ds_tbsSelect.jsp";
		return "/ds/tbs/ds_tbsList.jsp";
	}
	
	//个人任务查看
	public String selfList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search1(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		return "/ds/tbs/selfList.jsp";
	}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/ds/tbs/ds_tbsDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select tb.*,hb.name p_name from (select tbs.*,ds.sc_code," +
				"(select br.branchname from mrbranch br where br.id=tbs.ph_deptid) branchname " +
				"from Ds_tbs tbs,ds_scheme ds where 1=1 " +
				"and tbs.scheme_id=ds.id(+)) tb,hr_base_info hb where 1=1 and tb.designer_id=hb.id " +
				"/~ and tb.id={id} ~/"
				+ "/~ and ds.sc_code like '%[sc_code]%' ~/ " +
				"/~ and hb.name like '%[p_name]%' ~/"
				+ "/~ and tb.finish_time1 >= to_date({finish_time1},'yyyy-MM-dd') ~/ " +
				"/~ and tb.finish_time2 <= to_date({finish_time2},'yyyy-MM-dd') ~/ ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		searchMap.put("p_id", myuser.get("base_info_id").toString());
		String sql="select tbs.*," +
				"(select ds.sc_code from ds_scheme ds where ds.id=tbs.scheme_id) sc_code," +
				"(select br.branchname from mrbranch br where br.id=tbs.ph_deptid) branchname," +
				"(select hb.name from hr_base_info hb where hb.id=tbs.designer_id) p_name " +
				"from Ds_tbs tbs where 1=1 /~ and id={id} ~/"
		+ "/~ and tbs.designer_id={p_id} ~/ "
		+ "/~ and tbs.finish_time1 >= to_date({finish_time1},'yyyy-MM-dd') ~/ " +
		"/~ and tbs.finish_time2 <= to_date({finish_time2},'yyyy-MM-dd') ~/ ";
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
		return "/ds/tbs/ds_tbsDtl.jsp";
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
		return "/ds/tbs/ds_tbsDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Ds_tbs WHERE ID in (?)", new Object[]{s_id});
		
		return "/ds/tbs/Ds_tbs!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}