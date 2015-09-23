package com.web.fi.expenses_plan;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.phprpc.PHPRPC_Client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.map.Fi_expenses_items;
import com.map.Fi_expenses_plan;
import com.map.Fi_expenses_planVirtual;
import com.map.Fi_expenses_plan_approval;
import com.map.Gm_ration_item;
import com.map.Gm_travel_approval;
import com.map.Sys_approve;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.web.common.Approval;
import com.web.common.ApproveInfoTemp;
import com.web.common.ApproveUtil;
import com.web.common.BuildDDLUtil;
import com.web.common.IInsertApprovalInfo;
import com.zsc.Mas;

public class Fi_expenses_planAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	private static final String APPROVALPERMISSION = "exp_status";
	
	public String approval(HttpServletRequest request , HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Map myuser=(Map)request.getSession().getAttribute("user");
		String deptid = myuser.get("branchid").toString();
		Date today = new Date();
		String apptime = dFormat.format(today);
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String exp_id = request.getParameter("exp_id");
			
			Map expMap = dataBaseControl.getOneResultSet("select t.appstate from Fi_expenses_plan t where t.id=?", new Object[]{exp_id});
			String exp_app = expMap.get("appstate").toString();
			
			String departid = request.getParameter("dept_id");
			String auditid = request.getParameter("auditid");
			String appstate = request.getParameter("appstate");
			String appopinion = request.getParameter("appopinion");
			
			Fi_expenses_plan_approval approval = new Fi_expenses_plan_approval();
			
			approval.setExp_id(Long.parseLong(exp_id));
			approval.setAuditid(Long.parseLong(auditid));
			approval.setDept_id(Long.parseLong(departid));
			approval.setApptime((new java.sql.Date((fmt.parse(apptime)).getTime())));
			approval.setAppopinion(appopinion);
			
			dataBaseControl.beginTransaction();
			int i =dataBaseControl.insertByBean(approval);
			if(i==1){
				/*	写入下一步审核人员id	*/
				if(exp_app.equals("0")){//部门经理审批
					if(appstate.equals("1")){
						if(deptid.equals("4"))//软件部
							dataBaseControl.executeSql("update Fi_expenses_plan t set t.nextapproverid=?,t.appstate=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3 and t.hr_type=3", null)).get("id").toString(),exp_id});
						if(deptid.equals("9"))//成都
							dataBaseControl.executeSql("update Fi_expenses_plan t set t.nextapproverid=0,t.appstate=2 where t.id=?", new Object[]{exp_id});
						if(!(deptid.equals("4")||deptid.equals("9")))
							dataBaseControl.executeSql("update Fi_expenses_plan t set t.nextapproverid=?,t.appstate=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString(),exp_id});
					}else{
						dataBaseControl.executeSql("update Fi_expenses_plan t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,exp_id});
					}
				}else{
					if(appstate.equals("1"))
						dataBaseControl.executeSql("update Fi_expenses_plan t set t.appstate=2,t.nextapproverid=0 where t.id=?", new Object[]{exp_id});
					if(!appstate.equals("1"))
						dataBaseControl.executeSql("update Fi_expenses_plan t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,exp_id});
				}
				//写入批准金额
				String final_sum = request.getParameter("final_sum");
				if(final_sum!=null)
					dataBaseControl.executeSql("update fi_expenses_plan t set t.final_sum=? where t.id=?", new Object[]{final_sum,exp_id});
				
				/* 发短信 */
				Map messageMap = dataBaseControl.getOneResultSet("select t.appstate,t.nextapproverid from fi_expenses_plan t where t.id=?", new Object[]{exp_id});
				if(!messageMap.isEmpty()){
					/* 短信提醒下一步审批人 */
					//初始化
					String num="";
					String infor="";
					PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
					Mas m = (Mas) client.useService(Mas.class);
					
					String nextapproverid = messageMap.get("nextapproverid").toString();
					if(!nextapproverid.equals("0")){
						List plist = dataBaseControl.getOutResultSet("select * from message t where t.pid=?", new Object[]{nextapproverid});
						if(plist != null && !plist.isEmpty())
						{
							num = ""+((Map)plist.toArray()[0]).get("num");
						}
						
						infor="您有一条费用开支待审批请求，请您审批！";
						m.sendGFDZ(num,infor);
					}
					
					/* 短信提醒审批结果 */
					String state = messageMap.get("appstate").toString();
					if(state.equals("2")||state.equals("-1")||state.equals("-2")){
						num = (dataBaseControl.getOneResultSet("select t.phone from hr_base_info t where t.id=(select t.personid from fi_expenses_plan t where t.id=?)", new Object[]{exp_id})).get("phone").toString();
						
						if(state.equals("2"))
							infor="您的费用开支请求审批已通过！";
						if(state.equals("-1"))
							infor="您的费用开支请求被打回！";
						if(state.equals("-2"))
							infor="您的费用开支请求审批不通过！";
						m.sendGFDZ(num,infor);
					}
				}
				request.setAttribute("operationSign", "closeDG_refreshW();");
			}
			dataBaseControl.endTransaction();
		}
		else{
			Page pageExp=searchShow(request,1,1);
			Page pageApp=searchApp(request,1,10);
			Page pageItem=searchItem(request,1,10);
			
			request.setAttribute("recordExp", ((ArrayList)pageExp.getThisPageElements()).get(0));
			request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
			request.setAttribute("recordItem", (ArrayList)pageItem.getThisPageElements());
		}
		
		request.setAttribute("apptime", apptime);
		request.setAttribute("editMod", "appr");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/expenses_plan/appr.jsp";
	}
	
	//列表页面
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map searchMap = getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		String duty_id = (String) myuser.get("duty_id");
		if(!duty_id.equals("1")){
			if(duty_id.equals("3")){
				String appstate = request.getParameter("appstate")==null?"1":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("4")){
				String appstate = request.getParameter("appstate")==null?"1":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("5")){
				String appstate = request.getParameter("appstate")==null?"1":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else{}
		}
		
		Page page=searchExp(request,pageNo,pageSize);
		
		request.setAttribute("searchMap", searchMap);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/expenses_plan/fi_expenses_planSelect.jsp";
		return "/fi/expenses_plan/fi_expenses_planList.jsp";
	}
	 public String searchListDemo (HttpServletRequest request,HttpServletResponse response) throws Exception  
 	{
 		
		 String sql="select ep.*,(select hb.name from hr_base_info hb where hb.id=ep.personid) name,c.dmzmc,m.branchname " +
					" from Fi_expenses_plan ep,code c,mrbranch m where 1=1 and m.id=ep.departid and c.dmlb='exp_status' and c.dmz=ep.appstate " +
					"/~ and ep.id={id} ~/" +
					"/~ and m.id={mid} ~/"+
					"/~ and ep.personid={pid} ~/" +
					"/~ and ep.personid={p_id} ~/" +
					"/~ and ep.appstate={appstate} ~/"
					+"/~ and ep.apply_time >= to_date({app_date1},'yyyy-MM-dd') ~/" 
					+"/~ and ep.apply_time <= to_date({app_date2},'yyyy-MM-dd') ~/";
 		defaultList(request,response,new StringBuffer(sql),"ep.id desc"); 
 		return null;
 	}
	 public void total(HttpServletRequest request,HttpServletResponse response) throws Exception {
			String sql="select sum(ep.total_sum) sum,sum(ep.final_sum)sum1,count(*) count  " +
					" from Fi_expenses_plan ep,code c,mrbranch m where 1=1 and m.id=ep.departid and c.dmlb='exp_status' and c.dmz=ep.appstate " +
					"/~ and ep.id={id} ~/" +
					"/~ and m.id={mid} ~/"+
					"/~ and ep.personid={pid} ~/" +
					"/~ and ep.personid={p_id} ~/" +
					"/~ and ep.appstate={appstate} ~/"
					+"/~ and ep.apply_time >= to_date({app_date1},'yyyy-MM-dd') ~/" 
					+"/~ and ep.apply_time <= to_date({app_date2},'yyyy-MM-dd') ~/";
				
				XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
				Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
				Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
				
				String jsonStr = JSON.toJSONString(map);
				setAjaxInfo(response,jsonStr);
			
			}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page pageExp=searchShow(request,1,1);
		Page pageApp=searchApp(request,1,10);
		Page pageItem=searchItem(request,1,10);
		
		request.setAttribute("recordExp", ((ArrayList)pageExp.getThisPageElements()).get(0));
		request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
		request.setAttribute("recordItem", (ArrayList)pageItem.getThisPageElements());
		request.setAttribute("editMod", "show");
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/fi/expenses_plan/fi_expenses_planDtl.jsp";
	}
	
	private Page searchExp(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String duty_id = (String) myuser.get("duty_id");
		String departid = branchid.toString();
		String p_id= myuser.get("base_info_id").toString();
		String sqlDept = "";
		if(!(departid.equals("1")||departid.equals("6"))){
			if(departid.equals("3")){
				sqlDept = " and ((ep.buy=1 and ep.departid!=3) or (ep.departid=3)) ";
			}else{
				if(duty_id.equals("1")){	//职员只可查看自己
					searchMap.put("p_id", p_id);
				}
				else if(duty_id.equals("4")){	//崔总可查看除以下部门的
					sqlDept = " and ep.departid not in (1,4,9,11,12) ";
				}
				else{
					searchMap.put("departid", departid);	//部门经理查看本部门的
					sqlDept = "/~ and ep.departid={departid} ~/";
				}
			}
		}
		
		//各角色默认查询状态
		if(!duty_id.equals("1")){
			if(duty_id.equals("3")){
				String appstate = request.getParameter("appstate")==null?"1":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("4")){
				String appstate = request.getParameter("appstate")==null?"1":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("5")){
				String appstate = request.getParameter("appstate")==null?"1":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else{}
		}
		
		String sql="select ep.*,(select hb.name from hr_base_info hb where hb.id=ep.personid) name," +
				"(select br.branchname from mrbranch br where br.id=ep.departid) dept_name " +
				" from Fi_expenses_plan ep where 1=1  " +
				"/~ and ep.id={id} ~/" +
				"/~ and ep.personid={pid} ~/" +
				"/~ and ep.personid={p_id} ~/" +
				"/~ and ep.appstate={appstate} ~/"
				+"/~ and ep.apply_time >= to_date({input_date1},'yyyy-MM-dd') ~/" 
				+"/~ and ep.apply_time <= to_date({input_date2},'yyyy-MM-dd') ~/"+sqlDept+
				" order by ep.id desc";
				
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchShow(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		String sql="select ep.*,hb.name," +
				"(select br.branchname from mrbranch br where br.id=ep.departid) dept_name " +
				" from Fi_expenses_plan ep,hr_base_info hb where 1=1 and hb.id=ep.personid " +
				"/~ and hb.name like '%[name]%' ~/" +
				"/~ and ep.id={id} ~/" +
				"/~ and ep.personid={p_id} ~/" +
				"/~ and ep.appstate={appstate} ~/"
				+"/~ and ep.apply_time >= to_date({input_date1},'yyyy-MM-dd') ~/" 
				+"/~ and ep.apply_time <= to_date({input_date2},'yyyy-MM-dd') ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	private Page searchItem(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Fi_expenses_items where 1=1 /~ and exp_id={id} ~/ order by id";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page searchApp(HttpServletRequest request,int pageNo,int pageSize) throws Exception {
		Map searchMap=getParameterMap(request);
		String sql="select ba.name,mr.branchname,exp.appopinion,exp.apptime from FI_EXPENSES_PLAN_APPROVAL exp , " +
					" hr_base_info ba,mrbranch mr where 1=1 and exp.dept_id = mr.id and exp.auditid = ba.id " +
					"/~ and exp.exp_id = {id} ~/ order by exp.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String param = request.getParameter("param");
		
		if(param != null){
			Fi_expenses_planVirtual expenses_planVir = null;
			try {
				expenses_planVir = JSON.parseObject(param,Fi_expenses_planVirtual.class);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			if(expenses_planVir == null){
				Map maps = JSON.parseObject(param,Map.class);
				if(maps != null){
					String item = String.valueOf(maps.get("item"));
					String itemVal = String.valueOf(maps.get("plan_money"));
					
					expenses_planVir = new Fi_expenses_planVirtual();
					
					List<String> tempList = new ArrayList<String>();
					tempList.add(item);
					expenses_planVir.setItem(tempList);
					
					tempList = new ArrayList<String>();
					tempList.add(itemVal);
					expenses_planVir.setPlan_money(tempList);
				}
			}
			
			Fi_expenses_plan expenses_plan = JSON.parseObject(param,Fi_expenses_plan.class);
			
			Approval approval = new Approval();
			approval.setMainT(expenses_plan);
			approval.setDmlb(APPROVALPERMISSION);
			approval.setLoginUser((Map) request.getSession().getAttribute("user"));
			String curStatus = null;
			Map<String,Object> initStatus = ApproveUtil.getInstance().saveAppLevelByInitStatus(approval);
			if(initStatus != null && initStatus.get("dmz")!=null){
				curStatus =  String.valueOf(initStatus.get("dmz"));	
//				&& initStatus.get("sys_approve")!=null 
//				Sys_approve sys_approve = (Sys_approve) initStatus.get("sys_approve");
//				expenses_plan.setNextapproverid(sys_approve.getLicensorid());
			}
			
			
			//提交子表数据
			//开始事务
			dataBaseControl.beginTransaction();
			
			Long parentid = dataBaseControl.getSeq(Fi_expenses_plan.class);
			dataBaseControl.insertByBean(expenses_plan,parentid);
			
			/* 下一阶段审批人 */
			String nextapproverid = "";
			String appstate = "";
			String duty_id = "";
			Map pMap = dataBaseControl.getOneResultSet("select t.personid,t.departid from fi_expenses_plan t where t.id=?", new Object[]{parentid});
			String p_id = pMap.get("personid").toString();
			String dept_id = pMap.get("departid").toString();
			if(dept_id.equals("9"))//成都公司
			{
				duty_id = "5";
			}else if(dept_id.equals("5"))//崔总提交申请
			{
				duty_id = "4";
			}
			else
			{
				duty_id = "2";
			}
			Map appMap = dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.dept_id=? and t.duty_id=? and t.hr_type=3", new Object[]{dept_id,duty_id});
			String dp_id = appMap.get("id").toString();
			if(p_id.equals(dp_id)){
				if(dept_id.equals("4")||duty_id.equals("4")||duty_id.equals("5"))//软件部、崔总、向总由赵总审批
				{
					nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3 and t.hr_type=3", null)).get("id").toString();
				}
				else{
					nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
				}
				appstate = "1";
			}
			else if(dept_id.equals("10")){	//客户部申请由崔总审批
				nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
			}
			else{
				appstate = "0";
				nextapproverid = dp_id;
			}
			//当为客户部或成都公司时，提交后直接交由公司领导审批
			if(dept_id.equals("9")||dept_id.equals("10"))
				appstate = "1";
			int s = dataBaseControl.executeSql("update fi_expenses_plan t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,parentid});
			
			/* 发短信 */
			if(s==1){
				String num="";
				List plist = dataBaseControl.getOutResultSet("select * from message t where t.pid=?", new Object[]{nextapproverid});
				if(plist != null && !plist.isEmpty())
				{
					num = ""+((Map)plist.toArray()[0]).get("num");
				}
				PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
				Mas m = (Mas) client.useService(Mas.class);
				
				String infor="您有一条费用开支待审批请求，请您审批！";
				m.sendGFDZ(num,infor);
			}
			
			List<String> item = expenses_planVir.getItem();
			List<String> plan_money = expenses_planVir.getPlan_money();
			for (int i = 0; i < item.size(); i++) {
				Fi_expenses_items items = new Fi_expenses_items();
				items.setItem(item.get(i));				
				items.setPlan_money(new BigDecimal(plan_money.get(i)));
				items.setExp_id(BigDecimal.valueOf(parentid));
				dataBaseControl.insertByBean(items);
			}
			dataBaseControl.endTransaction();//结束事务
		}	
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String apply_time = dFormat.format(today);
		
		buildDDL(request);
		request.setAttribute("apply_time", apply_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/fi/expenses_plan/fi_expenses_planDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String id = request.getParameter("id");
		String count = request.getParameter("count");
		int i =0;
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			
			List list = dataBaseControl.getOutResultSet("select t.* from fi_expenses_items t where t.exp_id = ? order by t.id ", new Object[]{request.getParameter("id")});
			//修改子项
			dataBaseControl.beginTransaction();
			for(i = 0 ; i < list.size(); i++)
			{
				Map tmap = (Map)list.toArray()[i];
				Fi_expenses_items exp_item = new Fi_expenses_items();
				BeanUtils.populate(exp_item, tmap);
				
				String item = request.getParameter("item"+exp_item.getId());
				String plan_money = request.getParameter("plan_money"+exp_item.getId());
				exp_item.setItem(item);
				exp_item.setPlan_money(new BigDecimal(Double.parseDouble(plan_money.trim())));
				dataBaseControl.updateByBean(exp_item);	
			}
			
			//修改页面新增子项
			for(i=0;i<Integer.parseInt(count);i++){
				Fi_expenses_items exp_item = new Fi_expenses_items();
				String item = request.getParameter("item"+(i+1));
				String plan_money = request.getParameter("plan_money"+(i+1));
				exp_item.setItem(item);
				exp_item.setPlan_money(new BigDecimal(Integer.parseInt(plan_money.trim())));
				exp_item.setExp_id(new BigDecimal(Integer.parseInt(id)));
				dataBaseControl.insertByBean(exp_item);
			}
			
			i = dataBaseControl.updateByBean(getMapObject(request));
			
			/* 下一阶段审批人 */
			String nextapproverid = "";
			String appstate = "";
			String duty_id = "";
			Map pMap = dataBaseControl.getOneResultSet("select t.personid,t.departid from fi_expenses_plan t where t.id=?", new Object[]{id});
			String p_id = pMap.get("personid").toString();
			String dept_id = pMap.get("departid").toString();
			if(dept_id.equals("9"))//成都公司
			{
				duty_id = "5";
			}else if(dept_id.equals("5"))//崔总提交申请
			{
				duty_id = "4";
			}
			else
			{
				duty_id = "2";
			}
			Map appMap = dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.dept_id=? and t.duty_id=? and t.hr_type=3", new Object[]{dept_id,duty_id});
			String dp_id = appMap.get("id").toString();
			if(p_id.equals(dp_id)){
				if(dept_id.equals("4")||duty_id.equals("4")||duty_id.equals("5"))//软件部、崔总、向总由赵总审批
				{
					nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3 and t.hr_type=3", null)).get("id").toString();
				}
				else{
					nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
				}
				appstate = "1";
			}
			else if(dept_id.equals("10")){	//客户部申请由崔总审批
				nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
			}
			else{
				appstate = "0";
				nextapproverid = dp_id;
			}
			if(dept_id.equals("9")||dept_id.equals("10"))
				appstate = "1";
			int s = dataBaseControl.executeSql("update fi_expenses_plan t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,id});
			dataBaseControl.endTransaction();
			
			/* 发短信 */
			if(s==1){
				String num="";
				List plist = dataBaseControl.getOutResultSet("select * from message t where t.pid=?", new Object[]{nextapproverid});
				if(plist != null && !plist.isEmpty())
				{
					num = ""+((Map)plist.toArray()[0]).get("num");
				}
				PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
				Mas m = (Mas) client.useService(Mas.class);
				
				String infor="您有一条费用开支待审批请求，请您审批！";
				m.sendGFDZ(num,infor);
			}
			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page pageExp=searchShow(request,1,1);
			Page pageApp=searchApp(request,1,10);
			Page pageItem=searchItem(request,1,10);
			
			request.setAttribute("recordExp", ((ArrayList)pageExp.getThisPageElements()).get(0));
			request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
			request.setAttribute("recordItem", (ArrayList)pageItem.getThisPageElements());
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		
		return "/fi/expenses_plan/fi_expenses_planDtl.jsp";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.beginTransaction();
		dataBaseControl.executeSql("DELETE FROM Fi_expenses_plan WHERE ID in (?)", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Fi_expenses_items WHERE exp_id in (?)", new Object[]{s_id});
		dataBaseControl.endTransaction();
		
		return "/fi/expenses_plan/Fi_expenses_plan!searchList.do";
	}
	
	public String sub(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String id = request.getParameter("id");
		dataBaseControl.executeSql("update Fi_expenses_plan t set t.subflag='1' where t.id=?", new Object[]{id});
		return "/fi/expenses_plan/Fi_expenses_plan!searchList.do";
	}
	
	public String deleteItem(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from fi_expenses_items t where t.id = ? ", new Object[]{request.getParameter("id")});
		dataBaseControl.executeSql("DELETE FROM fi_expenses_items WHERE ID in (?)", new Object[]{request.getParameter("id")});
		return "/fi/expenses_plan/Fi_expenses_plan!edit.do?id"+map.get("exp_id");
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("exp_state",codeTableUtil.getCodeMap("exp_status")); 
		request.setAttribute("buy",codeTableUtil.getCodeMap("bool")); 
	}

}