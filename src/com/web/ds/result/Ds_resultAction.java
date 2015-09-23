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

import com.alibaba.fastjson.JSONObject;
import com.map.Ds_result;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Ds_resultAction extends BaseAction {
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
			return "/ds/result/ds_resultSelect.jsp";
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("mulselect")) 
				return "/ds/result/ds_resultMulSelect.jsp";
		return "/ds/result/ds_resultList.jsp";
	}
	
	//审核
	public String appList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		return "/ds/result/appList.jsp";
	}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		Page page1=searchProduct(request,1,100);
		Page page2=searchCost(request,1,1);
		Page page3=searchSchedule(request,1,50);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		List list1= ((ArrayList)page1.getThisPageElements());
		List list2= ((ArrayList)page2.getThisPageElements());
		List list3= ((ArrayList)page3.getThisPageElements());
		if(list1.size()!=0){
			request.setAttribute("record1", ((ArrayList)page1.getThisPageElements()));
		}
		if(list2.size()!=0){
			request.setAttribute("record2", ((ArrayList)page2.getThisPageElements()).get(0));
		}
		if(list3.size()!=0){
			request.setAttribute("record3", ((ArrayList)page3.getThisPageElements()));
		}
		
		request.setAttribute("editMod", "show");
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/ds/result/ds_resultDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select dr.*,pr.name proj_name," +
				"(select hb.name from hr_base_info hb where hb.id=dr.compiler_id) compiler_name," +
				"(select hb.name from hr_base_info hb where hb.id = dr.approver_id) approver_name," +
				"(select ds.sc_num from ds_scheme ds where ds.id = dr.scheme_id) sc_num " +
				"from Ds_result dr,pr_project pr " +
				"where 1=1 and dr.proj_id=pr.id(+) /~ and dr.id={id} ~/"
				+ "/~ and pr.name like '%[proj_name]%' ~/ "
				+ "/~ and dr.ds_type={ds_type} ~/ "
				+ "/~ and dr.num like '%[sc_num]%' ~/ "
				+ "/~ and dr.compile_time >= to_date({compile_time1},'yyyy-MM-dd') ~/" +
				"/~ and dr.compile_time < to_date({compile_time2},'yyyy-MM-dd') ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	private Page searchProduct(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select dp.* from Ds_prc_table dp where 1=1 " +
				"/~ and dp.result_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchCost(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Ds_cost where 1=1 " +
				"/~ and result_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchSchedule(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Ds_schedule where 1=1 " +
				"/~ and result_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Ds_result ds_result = (Ds_result) getMapObject(request);
			Map param = getParameterMap(request);
			String obj = (String) param.get("chkval");
			if(obj != null){
				String js_deptid = JSONObject.parseObject(obj).getString("js_deptid");
				ds_result.setJs_deptid(js_deptid);
			}
			int i =dataBaseControl.insertByBean(ds_result);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		
		String cur_id=(dataBaseControl.getSeq(Ds_result.class)).toString();
		int result_id = Integer.parseInt(cur_id)+1;
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String compile_time = dFormat.format(today);

		buildDDL(request);
		request.setAttribute("result_id", result_id);
		request.setAttribute("compile_time", compile_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/ds/result/ds_resultDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Ds_result ds_result = (Ds_result) getMapObject(request);
			Map param = getParameterMap(request);
			String obj = (String) param.get("chkval");
			if(obj != null){
				String js_deptid = JSONObject.parseObject(obj).getString("js_deptid");
				ds_result.setJs_deptid(js_deptid);
			}
			int i = dataBaseControl.updateByBean(ds_result);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/ds/result/ds_resultDtl.jsp";
	}
	
	public String appr(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select t.js_deptid from ds_result t where t.id=?", new Object[]{id});
			String js_deptid = map.get("js_deptid").toString();
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(request.getParameter("ds_type").equals("0")){
				dataBaseControl.executeSql("update pr_project p set p.pr_step = '6' where p.id=?", new Object[]{request.getParameter("proj_id")});
			}
			if(request.getParameter("ds_type").equals("1")){
				dataBaseControl.executeSql("update pr_project p set p.pr_step = '7' where p.id=?", new Object[]{request.getParameter("proj_id")});
			}
			if(i==1)
				dataBaseControl.executeSql("update ds_result t set t.js_deptid=? where t.id=?", new Object[]{js_deptid,id});
				dataBaseControl.executeSql("update ds_scheme t set t.fns_flag='1' where t.id=?", new Object[]{request.getParameter("scheme_id")});
				request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			Page page1=searchProduct(request,1,100);
			Page page2=searchCost(request,1,1);
			Page page3=searchSchedule(request,1,50);
			
			request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
			List list1= ((ArrayList)page1.getThisPageElements());
			List list2= ((ArrayList)page2.getThisPageElements());
			List list3= ((ArrayList)page3.getThisPageElements());
			if(list1.size()!=0){
				request.setAttribute("record1", ((ArrayList)page1.getThisPageElements()));
			}
			if(list2.size()!=0){
				request.setAttribute("record2", ((ArrayList)page2.getThisPageElements()).get(0));
			}
			if(list3.size()!=0){
				request.setAttribute("record3", ((ArrayList)page3.getThisPageElements()));
			}
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String apptime = dFormat.format(today);
		
		request.setAttribute("apptime", apptime);
		request.setAttribute("editMod", "appr");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/ds/result/resultAppr.jsp";
	}
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		dataBaseControl.executeSql("update ds_result t set t.appstate='1' where t.id=?", new Object[]{id});
		
		return "/ds/result/Ds_result!searchList.do";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Ds_result WHERE ID in (?)", new Object[]{s_id});
		Ds_productAction prc = new Ds_productAction();
		prc.deleteTable(request, response);
		dataBaseControl.executeSql("DELETE FROM Ds_cost WHERE result_id in (?)", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Ds_schedule WHERE result_id in (?)", new Object[]{s_id});
		
		return "/ds/result/Ds_result!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("ds_type",codeTableUtil.getCodeMap("ds_type"));
		request.setAttribute("appstate",codeTableUtil.getCodeMap("isPassByApproval"));
	}

}