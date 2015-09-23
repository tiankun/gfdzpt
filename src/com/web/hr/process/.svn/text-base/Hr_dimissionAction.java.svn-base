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

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.web.common.BuildDDLUtil;
import com.zsc.Mas;

public class Hr_dimissionAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		Map searchMap=getParameterMap(request);
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("searchMap", searchMap);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/process/hr_dimissionSelect.jsp";
		return "/hr/process/hr_dimissionList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/process/hr_dimissionDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String duty_id = (String) myuser.get("duty_id");
		String dept_id = branchid.toString();
		String p_id= myuser.get("base_info_id").toString();
		Map searchMap=getParameterMap(request);
		if(!(dept_id.equals("1")||dept_id.equals("2")||dept_id.equals("3")||dept_id.equals("6"))){
			if(duty_id.equals("1")){
				searchMap.put("p_id", p_id);
			}
			else{
				searchMap.put("dept_id", dept_id);
			}
		}
		String sql="select t.*," +
				"(select hb.hr_type from hr_base_info hb where t.p_id=hb.id) hr_type from Hr_dimission t where 1=1 /~ and t.id={id} ~/" +
				"/~ and t.p_id={p_id} ~/" +
				"/~ and t.dept_id={dept_id} ~/";
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
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String dim_time = dFormat.format(today);
		
		buildDDL(request);
		request.setAttribute("dim_time", dim_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/process/hr_dimissionDtl.jsp";
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
		return "/hr/process/hr_dimissionDtl.jsp";
	}
	
	
	//确认
	public String affirm(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String dept_id = request.getParameter("dept_id")==null?"":request.getParameter("dept_id");
		Map map = dataBaseControl.getOneResultSet("select t.p_id from hr_dimission t where t.id=?", new Object[]{id});
		String p_id = map.get("p_id").toString();
		
		if(!dept_id.equals("2")){
			if(dept_id.equals("3"))
				dataBaseControl.executeSql("update hr_dimission t set t.gm_affirm='1' where t.id=?", new Object[]{id});
			if(dept_id.equals("6"))
				dataBaseControl.executeSql("update hr_dimission t set t.fi_affirm='1' where t.id=?", new Object[]{id});
			if(!(dept_id.equals("6")||dept_id.equals("3")))
				dataBaseControl.executeSql("update hr_dimission t set t.dm_affirm='1' where t.id=?", new Object[]{id});
			List list = dataBaseControl.getOutResultSet("select * from hr_dimission t where t.id=? and t.gm_affirm='1' and t.fi_affirm='1' and t.dm_affirm='1' ", new Object[]{id});
			if(list.size()!=0)
				dataBaseControl.executeSql("update hr_dimission t set t.appstate='5' where t.id=?", new Object[]{id});
		}
		if(dept_id.equals("2")){
			dataBaseControl.beginTransaction();
			dataBaseControl.executeSql("update hr_dimission t set t.gm_affirm='1',t.appstate='6' where t.id=?", new Object[]{id});
			dataBaseControl.executeSql("update sysuser t set t.delflag='1' where t.base_info_id=?", new Object[]{p_id});
			dataBaseControl.executeSql("update hr_base_info t set t.hr_type='4' where t.id=?", new Object[]{p_id});
			dataBaseControl.endTransaction();
		}
			
		return "/hr/process/Hr_dimission!searchList.do";
	}
	
	//审批
	public String audit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?request.getParameter("id"):request.getParameter("id");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map map = dataBaseControl.getOneResultSet("select t.dim_apply from hr_dimission t where t.id=?", new Object[]{id});
			String dim_apply = map.get("dim_apply").toString();
			dataBaseControl.beginTransaction();
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1){
				dataBaseControl.executeSql("update hr_dimission t set t.dim_apply=? where t.id=?", new Object[]{dim_apply,id});
				request.setAttribute("operationSign", "closeDG_refreshW();");
			}
			dataBaseControl.endTransaction();
		}
		else{
			Map dim = dataBaseControl.getOneResultSet("select * from hr_dimission t where id=?", new Object[]{id});
			String appstate = dim.get("appstate").toString();
			if(appstate.equals("1")||appstate.equals("2")||appstate.equals("3")){
				if(appstate.equals("1")||appstate.equals("2")){
					if(appstate.equals("1"))
						request.setAttribute("editMod", "dm");
					else
						request.setAttribute("editMod", "hr");
				}
				else{
					request.setAttribute("editMod", "gm");
				}
			}
			request.setAttribute("record", dim);
		}
		request.setAttribute("IsPostBack", "1");
		return "/hr/process/audit.jsp";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Hr_dimission WHERE ID in (?)", new Object[]{s_id});
		
		return "/hr/process/Hr_dimission!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		Map<Object,Object> p_name = BuildDDLUtil.getInstance().getDataMap(null,new String[]
				{"select t.id,t.name from hr_base_info t order by t.id ","id","name"});
		request.setAttribute("p_name",p_name);
		
		request.setAttribute("appstate",codeTableUtil.getCodeMap("dim_state"));
	}

}