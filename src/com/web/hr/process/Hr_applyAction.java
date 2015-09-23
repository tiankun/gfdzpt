package com.web.hr.process;

import java.math.BigDecimal;
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

import org.phprpc.PHPRPC_Client;

import com.map.Hr_opinion;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Hr_applyAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		String p_id= myuser.get("base_info_id").toString();
		Map p_info = dataBaseControl.getOneResultSet("select hr_type from hr_base_info where id=?", new Object[]{p_id});
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("searchMap", searchMap);
		request.setAttribute("hr_type", p_info.get("hr_type"));
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/process/hr_applySelect.jsp";
		return "/hr/process/hr_applyList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/process/hr_applyDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String duty_id = (String) myuser.get("duty_id");
		String dept_id = branchid.toString();
		String p_id= myuser.get("base_info_id").toString();
		Map searchMap=getParameterMap(request);
		
		if(!(dept_id.equals("2")||dept_id.equals("1"))){
			if(duty_id.equals("1")){
				searchMap.put("p_id", p_id);
			}
			else{
				searchMap.put("dept_id", dept_id);
			}
		}
		
		String sql="select ha.*,hb.name,hb.hr_type," +
				"(select mb.branchname from mrbranch mb where mb.id=ha.dept_id) dept_name " +
				"from Hr_apply ha,hr_base_info hb where 1=1 and hb.id=ha.p_id " +
				"/~ and ha.id={id} ~/" +
				"/~ and ha.p_id={p_id} ~/" +
				"/~ and ha.dept_id={dept_id} ~/ " +
				"/~ and ha.apply_time >= to_date({apply_time1},'yyyy-MM-dd') ~/" +
				"/~ and ha.apply_time <= to_date({apply_time2},'yyyy-MM-dd') ~/ order by ha.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal base_info_id = (BigDecimal) myuser.get("base_info_id");
		Map hr_typeMap = dataBaseControl.getOneResultSet("select t.hr_type from hr_base_info t where t.id=?", new Object[]{base_info_id});
		String hr_type = hr_typeMap.get("hr_type").toString();
//		Map map = dataBaseControl.getOneResultSet("select t.* from hr_apply t where t.p_id=?", new Object[]{base_info_id});
//		if(map.size()!=0){
//			return "/hr/process/alreadySub.jsp";
//		}
		String pid = request.getParameter("p_id")==null?"":request.getParameter("p_id");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			dataBaseControl.beginTransaction();
			int i =dataBaseControl.insertByBean(getMapObject(request));	
			Long oid=dataBaseControl.getSeq(Hr_opinion.class);
			//在详细信息填报后，同时新增审核意见信息
			dataBaseControl.executeSql("insert into hr_opinion (id,p_id,hr_state,hr_type) values(?,?,?,?)", new Object[]{oid,pid,"1","6"});
			dataBaseControl.endTransaction();
			//发短信
			String num="";
			List plist = dataBaseControl.getOutResultSet("select * from message t " +
					"where t.rolename = '1' " +
					"and t.dept = (select hb.dept_id from hr_base_info hb where hb.id=?)", new Object[]{pid});
			if(plist != null && !plist.isEmpty())
			{
				num = ""+((Map)plist.toArray()[0]).get("num");
			}
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			
			String infor="您有一条转正审批请求，请您审批！";
			m.sendGFDZ(num,infor);
			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String apply_time = dFormat.format(today);
		
		request.setAttribute("hr_type", hr_type);
		request.setAttribute("apply_time", apply_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/process/hr_applyDtl.jsp";
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
		return "/hr/process/hr_applyDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Hr_apply WHERE ID in (?)", new Object[]{s_id});
		
		return "/hr/process/Hr_apply!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("hr_state",codeTableUtil.getCodeMap("hr_state"));
	}

}