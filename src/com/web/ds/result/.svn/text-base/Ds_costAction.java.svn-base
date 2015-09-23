package com.web.ds.result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Ds_costAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
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
			return "/ds/result/ds_costSelect.jsp";
		return "/ds/result/ds_costList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/ds/result/ds_costDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select dc.*,pr.name proj_name," +
				"(select hb.name from hr_base_info hb where hb.id = dc.operator) operator_name " +
				"from Ds_cost dc,pr_project pr where 1=1 " +
				"and dc.proj_id=pr.id(+) " +
				"/~ and dc.id={id} ~/" +
				"/~ and dc.result_id={result_id} ~/"
				+ "/~ and pr.name like '%[proj_name]%' ~/ "
				+ "/~ and dc.ds_type={ds_type} ~/ ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Ds_cost where 1=1 " +
				"/~ and result_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String result_id = request.getParameter("id");
		String proj_id = request.getParameter("proj_id");
		String ds_type = request.getParameter("ds_type");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(getMapObject(request));			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String op_time = dFormat.format(today);
		buildDDL(request);
		request.setAttribute("result_id", result_id);
		request.setAttribute("proj_id", proj_id);
		request.setAttribute("ds_type", ds_type);
		request.setAttribute("op_time", op_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/ds/result/ds_costDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String result_id = request.getParameter("id");
		String proj_id = request.getParameter("proj_id");
		String ds_type = request.getParameter("ds_type");
		
		List list=dataBaseControl.getOutResultSet("select * from ds_cost t where t.result_id=?", new Object[]{result_id});
		if(list.size()==0){
			if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
				int i =dataBaseControl.insertByBean(getMapObject(request));			
				if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			}
			DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String op_time = dFormat.format(today);
			buildDDL(request);
			request.setAttribute("result_id", result_id);
			request.setAttribute("proj_id", proj_id);
			request.setAttribute("ds_type", ds_type);
			request.setAttribute("op_time", op_time);
			request.setAttribute("editMod", "add");
			request.setAttribute("IsPostBack", "1");
			return "/ds/result/ds_costDtl.jsp";
		}
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search1(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/ds/result/ds_costDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Ds_cost WHERE ID in (?)", new Object[]{s_id});
		
		return "/ds/result/Ds_cost!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("ds_type",codeTableUtil.getCodeMap("ds_type")); 
	}

}