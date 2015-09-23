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

public class Hr_pchangeAction extends BaseAction {
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
			return "/hr/info/hr_pchangeSelect.jsp";
		return "/hr/info/hr_pchangeList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search1(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/info/hr_pchangeDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select hp.*," +
				"(select mr.branchname from mrbranch mr where mr.id=hp.dept_o) deptname_o," +
				"(select mr.branchname from mrbranch mr where mr.id=hp.dept_n) deptname_n from Hr_pchange hp where 1=1 /~ and hp.p_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select hp.*," +
				"(select mr.branchname from mrbranch mr where mr.id=hp.dept_o) deptname_o," +
				"(select mr.branchname from mrbranch mr where mr.id=hp.dept_n) deptname_n from Hr_pchange hp where 1=1 /~ and hp.id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String id = request.getParameter("pid")==null?"":request.getParameter("pid");
		Map baseinfoMap = dataBaseControl.getOneResultSet("select mr.branchname,hb.duty_id,hb.hr_type,hb.dept_id,hb.position " +
				"from hr_base_info hb,mrbranch mr where hb.dept_id=mr.id(+) and hb.id=?", new Object[]{id});
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(getMapObject(request));			
			if(i==1){
				String p_id = request.getParameter("p_id");
				String dept_n = request.getParameter("dept_n");
				String position_n = request.getParameter("position_n");
				String dept_id_n = request.getParameter("dept_id_n");
				String hr_type_n = request.getParameter("hr_type_n");
				dataBaseControl.beginTransaction();
				dataBaseControl.executeSql("update hr_base_info t set t.dept_id=?,t.duty_id=?,t.hr_type=?,t.position=? where t.id=?", new Object[]{dept_n,dept_id_n,hr_type_n,position_n,p_id});
				dataBaseControl.endTransaction();
				request.setAttribute("operationSign", "closeDG_refreshW();");
			}
				
		}
		buildDDL(request);
		request.setAttribute("pid", id);
		request.setAttribute("baseinfo",baseinfoMap);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/info/hr_pchangeDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1){
				String p_id = request.getParameter("p_id");
				String dept_n = request.getParameter("dept_n");
				String position_n = request.getParameter("position_n");
				String dept_id_n = request.getParameter("dept_id_n");
				String hr_type_n = request.getParameter("hr_type_n");
				dataBaseControl.beginTransaction();
				dataBaseControl.executeSql("update hr_base_info t set t.dept_id=?,t.duty_id=?,t.hr_type=?,t.position=? where t.id=?", new Object[]{dept_n,dept_id_n,hr_type_n,position_n,p_id});
				dataBaseControl.endTransaction();
				request.setAttribute("operationSign", "closeDG_refreshW();");
			}
		}else{
			Page page=search1(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/info/hr_pchangeDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		String pidString=(dataBaseControl.getOneResultSet("select p_id from Hr_pchange where id=?",  new Object[]{id})).get("p_id").toString();
		
		dataBaseControl.executeSql("DELETE FROM Hr_pchange WHERE ID in (?)", new Object[]{id});
		
		return "/hr/info/Hr_pchange!searchList.do?id="+pidString;
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("dept_id_o",codeTableUtil.getCodeMap("duty_id")); 
		request.setAttribute("dept_id_n",codeTableUtil.getCodeMap("duty_id")); 
		request.setAttribute("hr_type_o",codeTableUtil.getCodeMap("hr_type")); 
		request.setAttribute("hr_type_n",codeTableUtil.getCodeMap("hr_type")); 
	}

}