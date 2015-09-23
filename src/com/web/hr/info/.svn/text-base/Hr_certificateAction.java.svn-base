package com.web.hr.info;

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

public class Hr_certificateAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		String showFlag = request.getParameter("showFlag");
		request.setAttribute("showFlag", showFlag);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		Map map = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ? ", new Object[]{id});
		request.setAttribute("base_info", map);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/info/hr_certificateSelect.jsp";
		return "/hr/info/hr_certificateList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search1(request,1,1);
		if(page.getThisPageElements().size()==0){
			return "/hr/info/rep.jsp";
		}
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/info/hr_certificateDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Hr_certificate where 1=1 /~ and p_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Hr_certificate where 1=1 /~ and id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String id = request.getParameter("pid")==null?"":request.getParameter("pid");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(getMapObject(request));			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("pid", id);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/info/hr_certificateDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String pid = request.getParameter("pid")==null?"":request.getParameter("pid");
		request.setAttribute("pid", pid);
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
		return "/hr/info/hr_certificateDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		String pidString=(dataBaseControl.getOneResultSet("select p_id from Hr_certificate where id=?",  new Object[]{id})).get("p_id").toString();
		
		dataBaseControl.executeSql("DELETE FROM Hr_certificate WHERE ID in (?)", new Object[]{id});
		
		return "/hr/info/Hr_certificate!searchList.do?id="+pidString;
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("cert_type",codeTableUtil.getCodeMap("cert_type")); 
		request.setAttribute("cert_level",codeTableUtil.getCodeMap("cert_level")); 
		request.setAttribute("major_type",codeTableUtil.getCodeMap("major_type")); 
		request.setAttribute("diaplay",codeTableUtil.getCodeMap("bool"));
		request.setAttribute("ti_xing",codeTableUtil.getCodeMap("bool"));
		request.setAttribute("nian_jian",codeTableUtil.getCodeMap("bool"));
	}

}