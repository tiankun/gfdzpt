package com.web.ds.scheme;

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

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.map.Ds_scheme;
import com.map.Ds_tbs;
import com.map.Ds_tbsVirtual;
import com.map.Fi_expenses_items;
import com.map.Fi_expenses_planVirtual;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.web.common.BuildDDLUtil;

public class Ds_schemeAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	//编制策划书
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
			return "/ds/scheme/ds_schemeSelect.jsp";
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("mulselect")){ 
				page = searchSelect(request,pageNo,pageSize);
				request.setAttribute("record", page.getThisPageElements());
				request.setAttribute("page", page);
				return "/ds/scheme/ds_schemeMulSelect.jsp";
				}
		return "/ds/scheme/ds_schemeList.jsp";
	}
	
	//审核列表
	public String appList(HttpServletRequest request,HttpServletResponse response) throws Exception {
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
			return "/ds/scheme/ds_schemeSelect.jsp";
		return "/ds/scheme/appList.jsp";
	}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		Page page1=search1(request,1,20);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("record1", ((ArrayList)page1.getThisPageElements()));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/ds/scheme/ds_schemeDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select sc.*,pr.name proj_name," +
				"(select hb.name from hr_base_info hb where hb.id = sc.compiler_id) compiler," +
				"(select hb.name from hr_base_info hb where hb.id = sc.approver_id) approver_name," +
				"(select dd.dstask_num from ds_dstask dd where dd.id = sc.task_id) task_code " +
				" from Ds_scheme sc,pr_project pr where 1=1 and sc.proj_id=pr.id(+) " +
				"/~ and sc.id={id} ~/" +
				"/~ and sc.compiler_id={designerList} ~/" +
				"/~ and pr.name like '%[proj_name]%' ~/ "
				+ "/~ and sc.ds_type={ds_type} ~/ order by sc.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchSelect(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select sc.*,pr.name proj_name," +
				"(select hb.name from hr_base_info hb where hb.id = sc.compiler_id) compiler," +
				"(select hb.name from hr_base_info hb where hb.id = sc.approver_id) approver_name," +
				"(select dd.dstask_num from ds_dstask dd where dd.id = sc.task_id) task_code " +
				" from Ds_scheme sc,pr_project pr where 1=1 and sc.proj_id=pr.id(+) and sc.fns_flag=0 " +
				"/~ and sc.id={id} ~/" +
				"/~ and sc.compiler_id={designerList} ~/" +
				"/~ and pr.name like '%[proj_name]%' ~/ "
				+ "/~ and sc.ds_type={ds_type} ~/ order by sc.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select tbs.*," +
				"(select hb.name from hr_base_info hb where hb.id=tbs.designer_id) p_name," +
				"(select br.branchname from mrbranch br where br.id=tbs.ph_deptid) branchname " +
				"from Ds_tbs tbs where 1=1 " +
				"/~ and tbs.scheme_id={id} ~/ order by tbs.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String param = request.getParameter("param");
		String task_id = request.getParameter("task_id");
		
		if(param != null){
			Ds_tbsVirtual tbsVir = null;
			try {
				tbsVir = JSON.parseObject(param,Ds_tbsVirtual.class);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
			if(tbsVir == null){
				Map maps = JSON.parseObject(param,Map.class);
				if(maps != null){
					task_id = String.valueOf(maps.get("task_id")); 
					String designers = String.valueOf(maps.get("designer_id"));
					String ph_deptids = String.valueOf(maps.get("ph_deptid"));
					String tasks = String.valueOf(maps.get("task"));
					String finish_times = String.valueOf(maps.get("finish_time"));
					tbsVir = new Ds_tbsVirtual();
					
					List<String> tempList = new ArrayList<String>();
					tempList.add(designers);
					tbsVir.setDesigner_id(tempList);
					
					tempList = new ArrayList<String>();
					tempList.add(ph_deptids);
					tbsVir.setPh_deptid(tempList);
					
					tempList = new ArrayList<String>();
					tempList.add(tasks);
					tbsVir.setTask(tempList);
					
					tempList = new ArrayList<String>();
					tempList.add(finish_times);
					tbsVir.setFinish_time(tempList);
				}
			}
			
			Ds_scheme scheme = JSON.parseObject(param,Ds_scheme.class);
				
			//提交子表数据
			//开始事务
			dataBaseControl.beginTransaction();
			Long parentid = dataBaseControl.getSeq(Ds_scheme.class);
			dataBaseControl.insertByBean(scheme,parentid);
			
			List<String> designer_id = tbsVir.getDesigner_id();
			List<String> ph_deptid = tbsVir.getPh_deptid();
			List<String> task = tbsVir.getTask();
			List<String> finish_time = tbsVir.getFinish_time();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < designer_id.size(); i++) {
				Ds_tbs tbs = new Ds_tbs();
				tbs.setDesigner_id(new Integer(designer_id.get(i)));
				tbs.setPh_deptid(new Integer(ph_deptid.get(i)));
				tbs.setTask(task.get(i));
				tbs.setFinish_time(new java.sql.Date((fmt.parse(finish_time.get(i))).getTime()));
				tbs.setScheme_id(Integer.parseInt(parentid.toString()));
				dataBaseControl.insertByBean(tbs);
			}
			Map maps = JSON.parseObject(param,Map.class);
			task_id = String.valueOf(maps.get("task_id"));
			dataBaseControl.executeSql("update ds_dstask t set t.flag='3' where t.id=?", new Object[]{task_id});
			
			dataBaseControl.endTransaction();//结束事务
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		
		int sc_id = Integer.parseInt(dataBaseControl.getSeq(Ds_scheme.class).toString())+1;
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String compile_time = dFormat.format(today);
		
		buildDDL(request);
		request.setAttribute("sc_id", sc_id);
		request.setAttribute("compile_time", compile_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/ds/scheme/ds_schemeDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String id = request.getParameter("id");
		String count = request.getParameter("count");
		int i =0;
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			
			List list = dataBaseControl.getOutResultSet("select t.* from ds_tbs t where t.scheme_id = ? order by t.id ", new Object[]{request.getParameter("id")});
			//修改子项
			dataBaseControl.beginTransaction();	//开始事务
			for(i = 0 ; i < list.size(); i++)
			{
				Map tmap = (Map)list.toArray()[i];
				Ds_tbs tbs = new Ds_tbs();
				BeanUtils.populate(tbs, tmap);
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				
				String designer_id = request.getParameter("designer_id"+tbs.getId());
				String ph_deptid = request.getParameter("ph_deptid"+tbs.getId());
				String task = request.getParameter("task"+tbs.getId());
				String finish_time = request.getParameter("finish_time"+tbs.getId());
				
				tbs.setDesigner_id(Integer.parseInt(designer_id));
				tbs.setPh_deptid(Integer.parseInt(ph_deptid));
				tbs.setTask(task);
				tbs.setFinish_time(new java.sql.Date(fmt.parse(finish_time).getTime()));
				dataBaseControl.updateByBean(tbs);	
			}
			
			//修改页面新增子项
			for(i=0;i<Integer.parseInt(count);i++){
				Ds_tbs tbs = new Ds_tbs();
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				
				String designer_id = request.getParameter("designer_id"+(i+1));
				String ph_deptid = request.getParameter("ph_deptid"+(i+1));
				String task = request.getParameter("task"+(i+1));
				String finish_time = request.getParameter("finish_time"+(i+1));
				
				tbs.setDesigner_id(Integer.parseInt(designer_id));
				tbs.setPh_deptid(Integer.parseInt(ph_deptid));
				tbs.setTask(task);
				tbs.setFinish_time(new java.sql.Date(fmt.parse(finish_time).getTime()));
				tbs.setScheme_id(Integer.parseInt(id.toString()));
				
				dataBaseControl.insertByBean(tbs);
			}
			
			i = dataBaseControl.updateByBean(getMapObject(request));
			dataBaseControl.endTransaction();//结束事务
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			Page page1=search1(request,1,20);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
			request.setAttribute("record1", ((ArrayList)page1.getThisPageElements()));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/ds/scheme/ds_schemeDtl.jsp";
	}
	
	public String appr(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String task_id = request.getParameter("task_id");
		String id = request.getParameter("id");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map map = dataBaseControl.getOneResultSet("select t.gist,t.key from ds_scheme t where t.id=?", new Object[]{id});
			int i = dataBaseControl.updateByBean(getMapObject(request));
			String gist = map.get("gist").toString();
			String key = map.get("key").toString();
			dataBaseControl.executeSql("update ds_scheme t set t.gist=?,t.key=? where t.id=?", new Object[]{gist,key,id});
			if(request.getParameter("ds_type").equals("0")){
				dataBaseControl.executeSql("update pr_project p set p.pr_step = '4' where p.id=?", new Object[]{request.getParameter("proj_id")});
			}
			if(request.getParameter("ds_type").equals("1")){
				dataBaseControl.executeSql("update pr_project p set p.pr_step = '5' where p.id=?", new Object[]{request.getParameter("proj_id")});
			}
			dataBaseControl.executeSql("update ds_dstask t set t.task_state='5' where t.id=?", new Object[]{task_id});
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			Page page1=search1(request,1,20);
			
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
			request.setAttribute("record1", ((ArrayList)page1.getThisPageElements()));
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String apptime = dFormat.format(today);
		
		request.setAttribute("apptime", apptime);
		request.setAttribute("editMod", "appr");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/ds/scheme/schemeAppr.jsp";
	}
	
	public String accept(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		
		dataBaseControl.executeSql("update ds_dstask t set t.task_state='3' where t.id=?", new Object[]{id});
		
		return "/ds/scheme/Ds_scheme!searchList.do";
	}
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		dataBaseControl.executeSql("update ds_scheme t set t.appstate='1' where t.id=?", new Object[]{id});
		
		return "/ds/scheme/Ds_scheme!searchList.do";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Ds_scheme WHERE ID in (?)", new Object[]{s_id});
		
		return "/ds/scheme/Ds_scheme!searchList.do";
	}
	
	public String deleteTbs(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from ds_tbs t where t.id = ? ", new Object[]{request.getParameter("id")});
		dataBaseControl.executeSql("DELETE FROM ds_tbs WHERE ID in (?)", new Object[]{request.getParameter("id")});
		return "/ds/scheme/Ds_scheme!edit.do?id"+map.get("scheme_id");
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("ds_type",codeTableUtil.getCodeMap("ds_type"));
		request.setAttribute("appstate",codeTableUtil.getCodeMap("isPassByApproval"));
		
		Map<Object,Object> designerList = BuildDDLUtil.getInstance().getDataMap(null,new String[]
				{"select t.id,t.name from hr_base_info t where t.dept_id='8' and t.duty_id!='2' and (t.hr_type='2' or t.hr_type='3' or t.hr_type='6') order by t.id ","id","name"});
		request.setAttribute("designerList",designerList);
	}

}