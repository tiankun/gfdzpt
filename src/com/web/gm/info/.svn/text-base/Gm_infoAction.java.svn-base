package com.web.gm.info;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.map.Gm_info;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Gm_infoAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = dataBaseControl.getOneResultSet("select t.*,m.branchname from hr_base_info t,mrbranch m where t.dept_id = m.id and t.id = ?", new Object[]{user.get("base_info_id")});
		
		Map role = dataBaseControl.getOneResultSet("select t.id from SYSUSERROLES t where t.userid=? and t.rolesid=(select id from SYSROLESDIC where rolename='新闻发布角色')", new Object[]{user.get("id")});
		if(role.size()!=0){
			request.setAttribute("role", 1);
		}
		request.setAttribute("dept_id", baseinfo.get("dept_id"));
		
		request.setAttribute("searchMap", getParameterMap(request));
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/gm/info/gm_infoSelect.jsp";
		return "/gm/info/gm_infoList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/gm/info/gm_infoDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Gm_info where 1=1 /~ and id={id} ~/"
		+ "/~ and title like '%[title]%' ~/ "
		+ "/~ and input_date=to_date({input_date},'yyyy-MM-dd') ~/ "
		+ " order by input_date desc, id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map user = (Map)request.getSession().getAttribute("user");
			Gm_info info = new Gm_info();
			BeanUtils.populate(info, getParameterMap(request));
			info.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			info.setP_id(new BigDecimal(user.get("base_info_id").toString()));
			
			boolean i =dataBaseControl.insertByBeanLob(info);			
			if(!i)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/gm/info/gm_infoDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map user = (Map)request.getSession().getAttribute("user");
			Gm_info info = new Gm_info();
			BeanUtils.populate(info, getParameterMap(request));
			info.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			info.setP_id(new BigDecimal(user.get("base_info_id").toString()));
			
			
			int i = dataBaseControl.updateByBean(info);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/gm/info/gm_infoDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Gm_info WHERE ID in (?)", new Object[]{s_id});
		
		return "/gm/info/Gm_info!searchList.do";
	}
	
	public String view(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = (request.getParameter("id")==null)?"":request.getParameter("id");
		
		Map map = dataBaseControl.getOneResultSet("select * from gm_info t where t.id = ?", new Object[]{id});
		Map baseinfomap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{map.get("p_id")});
		
		request.setAttribute("info", map);
		request.setAttribute("baseinfo", baseinfomap);
		
		return "/gm/info/view.jsp";
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}