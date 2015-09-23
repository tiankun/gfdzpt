package com.web.hr.process;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Hr_evaluationAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String showFlag = request.getParameter("showFlag")==null?"":request.getParameter("showFlag");
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		Page pageTotal=searchTotal(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("recordTotal", ((ArrayList)pageTotal.getThisPageElements()).get(0));
		request.setAttribute("page", page);
		request.setAttribute("showFlag", showFlag);
		Map map = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ? ", new Object[]{id});
		request.setAttribute("base_info", map);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/process/hr_evaluationSelect.jsp";
		return "/hr/process/hr_evaluationList.jsp";
	}
	
	//查看需填考评表列表
	public String eva_selfList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search2(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		Map map = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ? ", new Object[]{id});
		request.setAttribute("base_info", map);
		buildDDL(request);
		return "/hr/process/eva_selfList.jsp";
	}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search2(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/process/hr_evaluationDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select he.*," +
				"(select hb.name from hr_base_info hb where hb.id=he.ep_id) ev_named," +
				"(select hb.name from hr_base_info hb where hb.id=he.p_id) evd_named," +
				"(select ht.position from hr_test ht where ht.p_id=he.p_id) positiond " +
				"from Hr_evaluation he where 1=1  /~ and he.p_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchTotal(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select sum(t.s_total) sumtotal,sum(t.s_total)/count(t.id) avgtotal from Hr_evaluation t where 1=1 /~ and t.p_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		BigDecimal base_info_id = (BigDecimal) myuser.get("base_info_id");
		if(branchid.compareTo(BigDecimal.valueOf(2.0))!=0){
			searchMap.put("epid", base_info_id);
		}
		String sql="select he.*," +
				"(select hb.name from hr_base_info hb where hb.id=he.ep_id) ev_named," +
				"(select hb.name from hr_base_info hb where hb.id=he.p_id) evd_named," +
				"(select ht.position from hr_test ht where ht.p_id=he.p_id) positiond " +
				"from Hr_evaluation he where 1=1 and he.s_total is null /~ and he.id={id} ~/" +
				"/~ and he.ep_id={epid} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page search2(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		BigDecimal base_info_id = (BigDecimal) myuser.get("base_info_id");
		if(branchid.compareTo(BigDecimal.valueOf(2.0))!=0){
			searchMap.put("epid", base_info_id);
		}
		String sql="select he.*," +
				"(select hb.name from hr_base_info hb where hb.id=he.ep_id) ev_named," +
				"(select hb.name from hr_base_info hb where hb.id=he.p_id) evd_named," +
				"(select ht.position from hr_test ht where ht.p_id=he.p_id) positiond " +
				"from Hr_evaluation he where 1=1 /~ and he.id={id} ~/ " +
				" /~ and he.ep_id={epid} ~/ ";
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
		return "/hr/process/hr_evaluationDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search1(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			if(record.size()==0){
				return null;
			}
			request.setAttribute("record", record.get(0));
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String ev_time = dFormat.format(today);
		
		request.setAttribute("ev_time", ev_time);
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/process/hr_evaluationDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Hr_evaluation WHERE ID in (?)", new Object[]{s_id});
		
		return "/hr/process/Hr_evaluation!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}
	
	//进入填写考评职员选取功能
	public String allotStaff(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pid = request.getParameter("pid")==null?"":request.getParameter("pid");
		String dept_id = request.getParameter("dept_id")==null?"":request.getParameter("dept_id");
		request.setAttribute("pid", pid);
		request.setAttribute("dept_id", dept_id);
		return "//hr/process/allotStaff.jsp";
	}
	public String getDg1(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pid = request.getParameter("pid")==null?"":request.getParameter("pid");
		String dept_id = request.getParameter("dept_id")==null?"":request.getParameter("dept_id");

		String sql="select t.id,t.name from hr_base_info t where t.dept_id=? and t.duty_id!='2' " +
				"and t.hr_type in (2,3,6) " +
				"and t.id not in (select t.ep_id from hr_evaluation t where t.p_id=?) " +
				"and t.id != ? ";
		Collection col = dataBaseControl.getOutResultSet(sql, new Object[]{dept_id,pid,pid});
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSON.toJSONString(col));
        return null;
	}
	
	public String getDg2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pid = request.getParameter("pid")==null?"":request.getParameter("pid");
		String sql="select t.ep_id," +
				"(select name from hr_base_info where id=t.ep_id) ev_name from hr_evaluation t where t.p_id=? order by id";
		Collection col = dataBaseControl.getOutResultSet(sql, new Object[]{pid});
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSON.toJSONString(col));
        return null;
	}
	
	public String addStaff(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String ep_id = request.getParameter("ep_id")==null?"":request.getParameter("ep_id");
		String p_id = request.getParameter("p_id")==null?"":request.getParameter("p_id");
		String dept_id = request.getParameter("dept_id")==null?"":request.getParameter("dept_id");
		String sql_select = "select * from hr_evaluation where p_id=? and ep_id=?";
		if(ep_id!=null){
			String[] epid = ep_id.split(",");
			for (String _epid : epid) {
				int i = dataBaseControl.getResultSetSize(sql_select, new Object[]{p_id,_epid});
				if(i>0) continue;
				String sql = "insert into hr_evaluation (id,p_id,ep_id,dept_id) values(seq_hr_evaluation.nextval,?,?,?)";
				dataBaseControl.executeSql(sql, new Object[]{p_id,_epid,dept_id});
			}			
		}
		
		request.setAttribute("pid", p_id);
		request.setAttribute("dept_id", dept_id);
		request.setAttribute("ep_id", ep_id);
		return "/hr/process/allotStaff.jsp";
	}
	
	public String delStaff(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String ep_id = request.getParameter("ep_id")==null?"":request.getParameter("ep_id");
		String p_id = request.getParameter("p_id")==null?"":request.getParameter("p_id");
		String dept_id = request.getParameter("dept_id")==null?"":request.getParameter("dept_id");
		if(ep_id!=null){
			String[] epid = ep_id.split(",");
			for (String _epid : epid) {
				String sql = "delete from hr_evaluation where p_id=? and ep_id=? ";
				dataBaseControl.executeSql(sql, new Object[]{p_id,_epid});
			}			
		}
		request.setAttribute("pid", p_id);
		request.setAttribute("dept_id", dept_id);
		request.setAttribute("ep_id", ep_id);
		return "/hr/process/allotStaff.jsp";
	}

}