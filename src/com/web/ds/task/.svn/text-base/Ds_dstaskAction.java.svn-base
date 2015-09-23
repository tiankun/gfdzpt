package com.web.ds.task;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.web.common.BuildDDLUtil;

public class Ds_dstaskAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	//任务书编制
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
			return "/ds/task/ds_dstaskSelect.jsp";
		return "/ds/task/ds_dstaskList.jsp";
	}
	//任务书审核
	public String appList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		return "/ds/task/appList.jsp";
	}
	
	//任务书接件分配负责人
	public String allotList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search1(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		return "/ds/task/allotList.jsp";
	}
	
	//个人接件查看
	public String taskList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search2(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("mulselect")) 
				return "/ds/task/ds_dstaskMulSelect.jsp";
		return "/ds/task/taskList.jsp";
	}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flag = request.getParameter("flag");
		Page page;
		if(flag.equals("0")){
			page=search(request,1,1);
		}
		else{
			page=search1(request,1,1);
		}
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/ds/task/ds_dstaskDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		String duty_id=myuser.get("duty_id").toString();
		if(!(duty_id.equals("2")||duty_id.equals("4")))
			searchMap.put("p_id", myuser.get("base_info_id").toString());
		String deptid=myuser.get("branchid").toString();
		if(deptid.equals("7"))
			searchMap.put("deptid", deptid);
		if(deptid.equals("5"))
			searchMap.put("deptid", "10");
		String sql="select ds.*,pr.name proj_name,pr.state proj_phase," +
		"(select br.branchname from mrbranch br where br.id = ds.launch_deptid) dept_name," +
		"(select hb.name from hr_base_info hb where hb.id = ds.launch_personid) p_name," +
		"(select hb.name from hr_base_info hb where hb.id = ds.approver_id) approver_name," +
		"(select hb.name from hr_base_info hb where hb.id = ds.task_fzr) task_fzrname " +
		" from Ds_dstask ds,pr_project pr " +
		" where 1=1 and ds.proj_id=pr.id(+) " +
		"/~ and ds.id={id} ~/"
		+ "/~ and ds.launch_personid={p_id} ~/ " +
		"/~ and ds.launch_deptid={deptid} ~/" +
		"/~ and pr.name like '%[proj_name]%' ~/ "
		+ "/~ and ds.ds_type={ds_type} ~/ "
		+ "/~ and ds.launch_time >= to_date({launch_time1},'yyyy-MM-dd') ~/ " +
		"/~ and ds.launch_time <= to_date({launch_time2},'yyyy-MM-dd') ~/" +
		"/~ and ds.delivery_time >= to_date({delivery_time1},'yyyy-MM-dd') ~/" +
		"/~ and ds.delivery_time <= to_date({delivery_time2},'yyyy-MM-dd') ~/" +
		"/~ and ds.apptime >= to_date({apptime1},'yyyy-MM-dd') ~/" +
		"/~ and ds.apptime <= to_date({apptime2},'yyyy-MM-dd') ~/ "
		+ "/~ and ds.appstate={appstate} ~/  order by ds.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		String duty_id=myuser.get("duty_id").toString();
		if(!duty_id.equals("2")){
			searchMap.put("fzr", myuser.get("base_info_id").toString());
		}
		String sql="select ds.*,pr.name proj_name,pr.state proj_phase," +
		"(select br.branchname from mrbranch br where br.id = ds.launch_deptid) dept_name," +
		"(select hb.name from hr_base_info hb where hb.id = ds.launch_personid) p_name," +
		"(select hb.name from hr_base_info hb where hb.id = ds.approver_id) approver_name," +
		"(select hb.name from hr_base_info hb where hb.id = ds.task_fzr) task_fzrname," +
		"(select hb.name from hr_base_info hb where hb.id = ds.design_fzr) fzr " +
		" from Ds_dstask ds,pr_project pr " +
		" where 1=1 and ds.proj_id=pr.id(+) " +
		"and ds.appstate='-1' " +
		"/~ and ds.id={id} ~/"
		+ "/~ and ds.launch_personid={p_id} ~/ " +
		"/~ and ds.design_fzr={fzr} ~/" +
		"/~ and ds.launch_deptid={deptid} ~/" +
		"/~ and pr.name like '%[proj_name]%' ~/ "
		+ "/~ and ds.ds_type={ds_type} ~/ "
		+ "/~ and ds.launch_time >= to_date({launch_time1},'yyyy-MM-dd') ~/ " +
		"/~ and ds.launch_time <= to_date({launch_time2},'yyyy-MM-dd') ~/" +
		"/~ and ds.delivery_time >= to_date({delivery_time1},'yyyy-MM-dd') ~/" +
		"/~ and ds.delivery_time <= to_date({delivery_time2},'yyyy-MM-dd') ~/" +
		"/~ and ds.apptime >= to_date({apptime1},'yyyy-MM-dd') ~/" +
		"/~ and ds.apptime <= to_date({apptime2},'yyyy-MM-dd') ~/ "
		+ "/~ and ds.appstate={appstate} ~/  order by ds.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page search2(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		String duty_id=myuser.get("duty_id").toString();
		if(!duty_id.equals("2")){
			searchMap.put("fzr", myuser.get("base_info_id").toString());
		}
		String sql="select ds.*,pr.name proj_name,pr.state proj_phase," +
		"(select br.branchname from mrbranch br where br.id = ds.launch_deptid) dept_name," +
		"(select hb.name from hr_base_info hb where hb.id = ds.launch_personid) p_name," +
		"(select hb.name from hr_base_info hb where hb.id = ds.approver_id) approver_name," +
		"(select hb.name from hr_base_info hb where hb.id = ds.task_fzr) task_fzrname," +
		"(select hb.name from hr_base_info hb where hb.id = ds.design_fzr) fzr " +
		" from Ds_dstask ds,pr_project pr " +
		" where 1=1 and ds.proj_id=pr.id(+) " +
		"and ds.appstate='-1' and ds.flag='1' " +
		"/~ and ds.id={id} ~/"
		+ "/~ and ds.launch_personid={p_id} ~/ " +
		"/~ and ds.design_fzr={fzr} ~/" +
		"/~ and ds.launch_deptid={deptid} ~/" +
		"/~ and pr.name like '%[proj_name]%' ~/ "
		+ "/~ and ds.ds_type={ds_type} ~/ "
		+ "/~ and ds.launch_time >= to_date({launch_time1},'yyyy-MM-dd') ~/ " +
		"/~ and ds.launch_time <= to_date({launch_time2},'yyyy-MM-dd') ~/" +
		"/~ and ds.delivery_time >= to_date({delivery_time1},'yyyy-MM-dd') ~/" +
		"/~ and ds.delivery_time <= to_date({delivery_time2},'yyyy-MM-dd') ~/" +
		"/~ and ds.apptime >= to_date({apptime1},'yyyy-MM-dd') ~/" +
		"/~ and ds.apptime <= to_date({apptime2},'yyyy-MM-dd') ~/ "
		+ "/~ and ds.appstate={appstate} ~/  order by ds.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		Map map = dataBaseControl.getOneResultSet("select * from mrbranch t where t.id = ? ", new Object[]{branchid});
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(getMapObject(request));	
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		
		buildDDL(request);
		request.setAttribute("dept_name", map.get("branchname"));
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/ds/task/ds_dstaskDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			Map myuser=(Map)request.getSession().getAttribute("user");
			if(((myuser.get("branchid")).toString()).equals("10")){
				dataBaseControl.executeSql("update pr_project p set p.pr_step = '2' where p.id=?", new Object[]{request.getParameter("proj_id")});
			}
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/ds/task/ds_dstaskDtl.jsp";
	}
	
	public String appr(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(request.getParameter("ds_type").equals("0")){
				dataBaseControl.executeSql("update pr_project p set p.pr_step = '2' where p.id=?", new Object[]{request.getParameter("proj_id")});
			}
			if(request.getParameter("ds_type").equals("1")){
				dataBaseControl.executeSql("update pr_project p set p.pr_step = '3' where p.id=?", new Object[]{request.getParameter("proj_id")});
			}
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String apptime = dFormat.format(today);
		
		request.setAttribute("apptime", apptime);
		request.setAttribute("editMod", "appr");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/ds/task/dstaskAppr.jsp";
	}
	
	public String accept(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		request.setAttribute("editMod", "accept");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/ds/task/design_fzr.jsp";
	}
	
	public String rejected(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		dataBaseControl.executeSql("update ds_dstask t set t.task_state='4',t.flag='2' where t.id=?", new Object[]{id});
		
		return "/ds/task/Ds_dstask!allotList.do";
	}
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		dataBaseControl.executeSql("update ds_dstask t set t.appstate='1' where t.id=?", new Object[]{id});
		
		return "/ds/task/Ds_dstask!searchList.do";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Ds_dstask WHERE ID in (?)", new Object[]{s_id});
		
		return "/ds/task/Ds_dstask!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("ds_type",codeTableUtil.getCodeMap("ds_type"));
		request.setAttribute("appstate",codeTableUtil.getCodeMap("isPassByApproval"));
		request.setAttribute("task_state",codeTableUtil.getCodeMap("task_state"));
		
		Map<Object,Object> designerList = BuildDDLUtil.getInstance().getDataMap(null,new String[]
				{"select t.id,t.name from hr_base_info t where t.dept_id='8' and t.duty_id!='2' and (t.hr_type='2' or t.hr_type='3' or t.hr_type='6') order by t.id ","id","name"});
		request.setAttribute("designerList",designerList);
	}

}