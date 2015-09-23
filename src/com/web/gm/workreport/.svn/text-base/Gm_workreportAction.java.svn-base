package com.web.gm.workreport;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.map.Gm_workreport;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Gm_workreportAction extends BaseAction {
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
			return "/gm/workreport/gm_workreportSelect.jsp";
		return "/gm/workreport/gm_workreportList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/gm/workreport/gm_workreportDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map user = (Map)request.getSession().getAttribute("user");
		Map searchMap=getParameterMap(request);
		searchMap.put("p_id", user.get("base_info_id"));
		String sql="select t.*,b.name,to_number(to_char(t.input_date,'WW'))zhou,decode(t.isread,'0','未提交','1','否','是')state from gm_workreport t, hr_base_info b where t.p_id = b.id  " 
				+"/~ and t.id={id} ~/"
				+"/~ and t.p_id={p_id} ~/"
				+ " order by t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map user = (Map)request.getSession().getAttribute("user");
			Gm_workreport workreport = new Gm_workreport();
			BeanUtils.populate(workreport, getParameterMap(request));
			workreport.setContent(request.getParameter("content"));
			workreport.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			workreport.setIsread(request.getParameter("state"));
			workreport.setP_id(new BigDecimal(user.get("base_info_id").toString()));
			int i =dataBaseControl.insertByBean(workreport);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Map map = new HashMap();
		map.put("input_date", fmt.format(date));
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("record",map );
		return "/gm/workreport/gm_workreportDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map user = (Map)request.getSession().getAttribute("user");
			Gm_workreport workreport = new Gm_workreport();
			BeanUtils.populate(workreport, getParameterMap(request));
			workreport.setContent(request.getParameter("content"));
			workreport.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			workreport.setIsread("0");
			workreport.setP_id(new BigDecimal(user.get("base_info_id").toString()));
			int i = dataBaseControl.updateByBean(workreport);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/gm/workreport/gm_workreportDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Gm_workreport WHERE ID in (?)", new Object[]{s_id});
		
		return "/gm/workreport/Gm_workreport!searchList.do";
	}
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		dataBaseControl.executeSql("update gm_workreport t set t.isread = '1' where t.id = ?", new Object[]{id});
		
		return "/gm/workreport/Gm_workreport!searchList.do";
	}
	
	public String auditlist(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map map=getParameterMap(request);
		map.put("audit_person", user.get("base_info_id"));
		
		String isread = request.getParameter("isread")==null?"1":request.getParameter("isread");
		map.put("isread", isread);
		request.setAttribute("searchmap", map);
		
		Page page=search1(map,pageNo,pageSize);
		
		
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/gm/workreport/gm_workreportSelect.jsp";
		return "/gm/workreport/gm_audit_workreportList.jsp";
	}
	
	private Page search1(Map map,int pageNo,int pageSize) throws Exception 
	{
		
		String sql="select t.*,b.name,to_number(to_char(t.input_date,'WW'))zhou,decode(t.isread,'0','未提交','1','否','是')state from gm_workreport t, hr_base_info b,gm_workreport_set s where t.p_id = b.id and s.report_person = t.p_id and t.isread in ('1','2')  " 
				+"/~ and t.id={id} ~/"
				+"/~ and t.isread={isread} ~/"
				+"/~ and s.audit_person={audit_person} ~/"
				+"/~ and t.p_id={p_id} ~/"
				+"/~ and b.name={name} ~/"
				+ "/~ and t.input_date>=to_date({attend_date1},'yyyy-MM-dd')       ~/ "
				+ "/~ and t.input_date<=to_date({attend_date2},'yyyy-MM-dd')       ~/ "
				+ " order by t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,map); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String audit(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		Map map = dataBaseControl.getOneResultSet("select * from gm_workreport t where t.id = ?", new Object[]{request.getParameter("id")});
		request.setAttribute("record", map);
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Gm_workreport workreport = new Gm_workreport();
			BeanUtils.populate(workreport, map);
			workreport.setIsread("2");
			workreport.setOpinion(request.getParameter("opinion"));
			int i =dataBaseControl.updateByBean(workreport);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("type", "audit");
		request.setAttribute("IsPostBack", "1");
		return "/gm/workreport/gm_workreportDtl.jsp";
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}