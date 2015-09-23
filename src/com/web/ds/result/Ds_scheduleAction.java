package com.web.ds.result;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.map.Ds_schedule;
import com.map.Ds_scheme;
import com.map.Ds_tbs;
import com.map.Ds_tbsVirtual;
import com.map.Fi_expenses_items;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Ds_scheduleAction extends BaseAction {
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
			return "/ds/result/ds_scheduleSelect.jsp";
		return "/ds/result/ds_scheduleList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/ds/result/ds_scheduleDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select ds.*,pr.name proj_name," +
				"(select hb.name from hr_base_info hb where hb.id = ds.operator) operator_name," +
				"(select dr.name from ds_result dr where dr.id = ds.result_id) result_name," +
				"(select dss.task_name from Ds_schedule dss where dss.id = ds.f_task) ftask_name " +
				"from Ds_schedule ds,pr_project pr where 1=1 and ds.proj_id=pr.id(+) " +
				"/~ and ds.id={id} ~/"
				+ "/~ and pr.name like '%[proj_name]%' ~/ "
				+ "/~ and ds.name like '%[name]%' ~/ "
				+ "/~ and ds.f_task={f_task} ~/ ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
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
		String result_id = request.getParameter("id");
		String proj_id = request.getParameter("proj_id");
		String ds_type = request.getParameter("ds_type");
		String count = request.getParameter("count");
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Ds_schedule scd = null;
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			dataBaseControl.beginTransaction();
			for(int i = 1 ; i <= Integer.parseInt(count); i++)
			{
				scd = new Ds_schedule();
				
				String operator = request.getParameter("operator");
				String op_time = request.getParameter("op_time");
				result_id = request.getParameter("result_id");
				String task_name = request.getParameter("task_name"+i);
				String ftask_name = request.getParameter("ftask_name"+i);
				String sc_res = request.getParameter("sc_res"+i);
				String start_time = request.getParameter("start_time"+i);
				String end_time = request.getParameter("end_time"+i);
				
				scd.setTask_Name(task_name);
				scd.setFtask_name(ftask_name);
				scd.setSc_res(sc_res);
				scd.setStart_time(new java.sql.Date((fmt.parse(start_time)).getTime()));
				scd.setEnd_time(new java.sql.Date((fmt.parse(end_time)).getTime()));
				scd.setResult_id(Integer.parseInt(result_id.toString()));
				scd.setProj_id(Integer.parseInt(proj_id.toString()));
				scd.setDs_type(ds_type.toString());
				scd.setOperator(Integer.parseInt(operator.toString()));
				scd.setOp_time(new java.sql.Date((fmt.parse(op_time)).getTime()));
				dataBaseControl.insertByBean(scd);
			}
			dataBaseControl.endTransaction();//结束事务
			request.setAttribute("operationSign", "closeDG_refreshW();");
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
		return "/ds/result/ds_scheduleDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String result_id = request.getParameter("id").equals("")?request.getParameter("result_id"):request.getParameter("id");
		String proj_id = request.getParameter("proj_id");
		String ds_type = request.getParameter("ds_type");
		String count = request.getParameter("count");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Ds_schedule scd = null;
		int i=0;
		
		List list=dataBaseControl.getOutResultSet("select * from ds_schedule t where t.result_id=?", new Object[]{result_id});
		
		//添加
		if(list.size()==0){
			if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
				dataBaseControl.beginTransaction();
				for(i = 1 ; i <= Integer.parseInt(count); i++)
				{
					scd = new Ds_schedule();
					
					String operator = request.getParameter("operator");
					String op_time = request.getParameter("op_time");
					result_id = request.getParameter("result_id");
					String task_name = request.getParameter("task_name"+i);
					String ftask_name = request.getParameter("ftask_name"+i);
					String sc_res = request.getParameter("sc_res"+i);
					String start_time = request.getParameter("start_time"+i);
					String end_time = request.getParameter("end_time"+i);
					
					scd.setTask_Name(task_name);
					scd.setFtask_name(ftask_name);
					scd.setSc_res(sc_res);
					scd.setStart_time(new java.sql.Date((fmt.parse(start_time)).getTime()));
					scd.setEnd_time(new java.sql.Date((fmt.parse(end_time)).getTime()));
					scd.setResult_id(Integer.parseInt(result_id.toString()));
					scd.setProj_id(Integer.parseInt(proj_id.toString()));
					scd.setDs_type(ds_type.toString());
					scd.setOperator(Integer.parseInt(operator.toString()));
					scd.setOp_time(new java.sql.Date((fmt.parse(op_time)).getTime()));
					dataBaseControl.insertByBean(scd);
				}
				dataBaseControl.endTransaction();//结束事务
				request.setAttribute("operationSign", "closeDG_refreshW();");
			}
		}
		
		else{
			//修改
			if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
				result_id = request.getParameter("result_id");
				list = dataBaseControl.getOutResultSet("select t.* from ds_schedule t where t.result_id = ? order by t.id ", new Object[]{result_id});
				//修改子项
				dataBaseControl.beginTransaction();	//开始事务
				for(i = 0 ; i < list.size(); i++)
				{
					Map tmap = (Map)list.toArray()[i];
					scd = new Ds_schedule();
					BeanUtils.populate(scd, tmap);
					
					String task_name = request.getParameter("task_name"+scd.getId());
					String ftask_name = request.getParameter("ftask_name"+scd.getId());
					String sc_res = request.getParameter("sc_res"+scd.getId());
					String start_time = request.getParameter("start_time"+scd.getId());
					String end_time = request.getParameter("end_time"+scd.getId());
					
					scd.setTask_Name(task_name);
					scd.setFtask_name(ftask_name);
					scd.setSc_res(sc_res);
					scd.setStart_time(new java.sql.Date((fmt.parse(start_time)).getTime()));
					scd.setEnd_time(new java.sql.Date((fmt.parse(end_time)).getTime()));
					dataBaseControl.updateByBean(scd);	
				}
				
				//新增修改子项
				for(i = 1 ; i <= Integer.parseInt(count); i++)
				{
					scd = new Ds_schedule();
					String operator = request.getParameter("operator");
					String op_time = request.getParameter("op_time");
					result_id = request.getParameter("result_id");
					String task_name = request.getParameter("task_name"+i);
					String ftask_name = request.getParameter("ftask_name"+i);
					String sc_res = request.getParameter("sc_res"+i);
					String start_time = request.getParameter("start_time"+i);
					String end_time = request.getParameter("end_time"+i);
					
					scd.setTask_Name(task_name);
					scd.setFtask_name(ftask_name);
					scd.setSc_res(sc_res);
					scd.setStart_time(new java.sql.Date((fmt.parse(start_time)).getTime()));
					scd.setEnd_time(new java.sql.Date((fmt.parse(end_time)).getTime()));
					scd.setResult_id(Integer.parseInt(result_id.toString()));
					scd.setProj_id(Integer.parseInt(proj_id.toString()));
					scd.setDs_type(ds_type.toString());
					scd.setOperator(Integer.parseInt(operator.toString()));
					scd.setOp_time(new java.sql.Date((fmt.parse(op_time)).getTime()));
					dataBaseControl.insertByBean(scd);
				}
				dataBaseControl.endTransaction();//结束事务
				request.setAttribute("operationSign", "closeDG_refreshW();");
			}else{
				Page page1=search1(request,1,50);
				ArrayList<Map> record1= (ArrayList)page1.getThisPageElements();
				request.setAttribute("record1", record1);
			}
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String op_time = dFormat.format(today);
		buildDDL(request);
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		request.setAttribute("result_id", result_id);
		request.setAttribute("proj_id", proj_id);
		request.setAttribute("ds_type", ds_type);
		request.setAttribute("op_time", op_time);
		return "/ds/result/ds_scheduleDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Ds_schedule WHERE ID in (?)", new Object[]{s_id});
		
		return "/ds/result/Ds_schedule!searchList.do";
	}
	
	public String deleteSch(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from ds_schedule t where t.id = ? ", new Object[]{request.getParameter("id")});
		dataBaseControl.executeSql("DELETE FROM ds_schedule WHERE ID in (?)", new Object[]{request.getParameter("id")});
		return "/ds/result/Ds_schedule!edit.do?id="+map.get("result_id");
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("ds_type",codeTableUtil.getCodeMap("ds_type")); 
	}

}