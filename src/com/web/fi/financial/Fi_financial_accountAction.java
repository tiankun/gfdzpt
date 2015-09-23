package com.web.fi.financial;

import java.io.PrintWriter;
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

import org.omg.CORBA.Request;
import org.phprpc.PHPRPC_Client;

import com.alibaba.fastjson.JSON;
import com.map.Fi_fina_account_appr;
import com.map.Fi_fina_account_dtl;
import com.map.Fi_financial_account;
import com.map.Fi_payfor;
import com.map.Fi_travelacc;
import com.map.Fi_travelacc_appr;
import com.map.Gm_ration_item;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Fi_financial_accountAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		Map searchMap = getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		String duty_id = (String) myuser.get("duty_id");
		if(!duty_id.equals("1")){
			if(duty_id.equals("3")){
				String appstate = request.getParameter("appstate")==null?"3":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("4")){
				String appstate = request.getParameter("appstate")==null?"3":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("5")){
				String appstate = request.getParameter("appstate")==null?"3":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else{}
		}
		
		request.setAttribute("searchMap", searchMap);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/financial/fi_financial_accountSelect.jsp";
		return "/fi/financial/fi_financial_accountList.jsp";
	}
	
////////////////////////////////////////////////////////////////	统计列表
public String searchListDemo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	Map myuser=(Map)request.getSession().getAttribute("user");
	BigDecimal branchid = (BigDecimal) myuser.get("branchid");
	String duty_id = (String) myuser.get("duty_id");
	String departid = branchid.toString();
	String personid= myuser.get("base_info_id").toString();
	request.setAttribute("personid", personid);
	Map searchMap=getParameterMap(request);
	String sqlDept = "";
	if(!(departid.equals("1")||departid.equals("6"))){
		if(duty_id.equals("1")){	//职员只可查看自己
			searchMap.put("personid", personid);
		}
		else if(duty_id.equals("4")){	//崔总可查看除以下部门的
			sqlDept = " and ff.departid not in (1,4,9,11,12) ";
		}
		else{
			searchMap.put("departid", departid);	//部门经理查看本部门的
			sqlDept = "/~ and ff.departid={departid} ~/";
		}
	}	
	
	String sql="select ff.id,ff.personid,ff.print," +
			"hb.name pname,ff.departid,ff.projectid,(select name from Pr_project where id=ff.Projectid)pro_name,"
			+"ff.apply_date,ff.apply_money,ff.amount_in_words,ff.print_status,ff.advance_type,ff.appstate,ff.remark,ff.nextapproverid,ff.subflag"
			+",ff.fjpath,ff.skdw,(select name from customer where id=ff.Skdw)skdw_name from Fi_financial_account ff,hr_base_info hb where 1=1 and ff.personid=hb.id(+) " +
			"/~ and ff.id={id} ~/"
	+ "/~ and hb.name like '%[name]%' ~/ "
	+ "/~ and ff.departid={mid} ~/ " 
	+ "/~ and ff.personid={personid} ~/ " 
	+ "/~ and ff.apply_date>=to_date({app_date1},'yyyy-MM-dd')  ~/ "
    + "/~ and ff.apply_date<=to_date({app_date2},'yyyy-MM-dd')   ~/ "
	+ "/~ and ff.advance_type={advance_type} ~/ "
	+ "/~ and ff.appstate={appstate} ~/ "+sqlDept;
	
	
	
		defaultList(request,response,new StringBuffer(sql),"ff.id desc"); 
		
		return null;
	
	}
public void total(HttpServletRequest request,HttpServletResponse response) throws Exception {
	Map myuser=(Map)request.getSession().getAttribute("user");
	BigDecimal branchid = (BigDecimal) myuser.get("branchid");
	String duty_id = (String) myuser.get("duty_id");
	String departid = branchid.toString();
	String personid= myuser.get("base_info_id").toString();
	Map searchMap=getParameterMap(request);
	String sqlDept = "";
	if(!(departid.equals("1")||departid.equals("6"))){
		if(duty_id.equals("1")){	//职员只可查看自己
			searchMap.put("personid", personid);
		}
		else if(duty_id.equals("4")){	//崔总可查看除以下部门的
			sqlDept = " and ff.departid not in (1,4,9,11,12) ";
		}
		else{
			searchMap.put("departid", departid);	//部门经理查看本部门的
			sqlDept = "/~ and ff.departid={departid} ~/";
		}
	}	
	//各角色默认查询状态
			if(!duty_id.equals("1")){
				if(duty_id.equals("3")){
					String appstate = request.getParameter("appstate")==null?"3":request.getParameter("appstate");
					searchMap.put("appstate", appstate);
				}
				else if(duty_id.equals("4")){
					String appstate = request.getParameter("appstate")==null?"3":request.getParameter("appstate");
					searchMap.put("appstate", appstate);
				}
				else if(duty_id.equals("5")){
					String appstate = request.getParameter("appstate")==null?"3":request.getParameter("appstate");
					searchMap.put("appstate", appstate);
				}
				else{}
			}
	String sql="select sum(ff.apply_money) sum,count(*) count" +
			" from Fi_financial_account ff,hr_base_info hb where 1=1 and ff.personid=hb.id(+) " +
			"/~ and ff.id={id} ~/"
			+ "/~ and ff.departid={mid} ~/ " 
	        + "/~ and hb.name like '%[personname]%' ~/ "
	        + "/~ and ff.personid={personid} ~/ " 
	        + "/~ and ff.apply_date>=to_date({app_date1},'yyyy-MM-dd')  ~/ "
			+ "/~ and ff.apply_date<=to_date({app_date2},'yyyy-MM-dd')   ~/ "
	        + "/~ and ff.advance_type={advance_type} ~/ "
	        + "/~ and ff.appstate={appstate} ~/ "+sqlDept;
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
		Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
		
		String jsonStr = JSON.toJSONString(map);
		setAjaxInfo(response,jsonStr);
	
	}
////////////////////////////////////////////////////////////////////
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=searchShow(request,1,1);
		Page pageApp=searchApp(request, 1, 20);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
		request.setAttribute("record_mx", search_MX(request.getParameter("id")));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/fi/financial/showDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String duty_id = (String) myuser.get("duty_id");
		String departid = branchid.toString();
		String personid= myuser.get("base_info_id").toString();
		Map searchMap=getParameterMap(request);
		String sqlDept = "";
		if(!(departid.equals("1")||departid.equals("6"))){
			if(duty_id.equals("1")){	//职员只可查看自己
				searchMap.put("personid", personid);
			}
			else if(duty_id.equals("4")){	//崔总可查看除以下部门的
				sqlDept = " and ff.departid not in (1,4,9,11,12) ";
			}
			else{
				searchMap.put("departid", departid);	//部门经理查看本部门的
				sqlDept = "/~ and ff.departid={departid} ~/";
			}
		}
		
		//各角色默认查询状态
		if(!duty_id.equals("1")){
			if(duty_id.equals("3")){
				String appstate = request.getParameter("appstate")==null?"3":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("4")){
				String appstate = request.getParameter("appstate")==null?"3":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("5")){
				String appstate = request.getParameter("appstate")==null?"3":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else{}
		}
		
		String sql="select ff.id,ff.personid,ff.print," +
				"hb.name pname,ff.departid,ff.projectid,(select name from Pr_project where id=ff.Projectid)pro_name,"
				+"ff.apply_date,ff.apply_money,ff.amount_in_words,ff.print_status,ff.advance_type,ff.appstate,ff.remark,ff.nextapproverid,ff.subflag"
				+",ff.fjpath,ff.skdw,(select name from customer where id=ff.Skdw)skdw_name from Fi_financial_account ff,hr_base_info hb where 1=1 and ff.personid=hb.id(+) " +
				"/~ and ff.id={id} ~/"
		+ "/~ and hb.name like '%[personname]%' ~/ "
		+ "/~ and ff.personid={personid} ~/ " 
		+ "/~ and ff.advance_type={advance_type} ~/ "
		+ "/~ and ff.appstate={appstate} ~/ "+sqlDept+
		" order by ff.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchShow(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		String sql="select ff.id,ff.personid,ff.fukuan_unit,ff.print,ff.zf_reason,ff.zf_time," +
				"(select hb.name from hr_base_info hb where hb.id=ff.zf_person) zf_pname," +
				"hb.name pname,ff.departid,ff.projectid," +
				"(select mb.branchname from mrbranch mb where mb.id=ff.departid) dept_name," +
				"(select name from Pr_project where id=ff.Projectid)pro_name,"
				+"ff.apply_date,ff.apply_money,ff.amount_in_words,ff.print_status,ff.advance_type,ff.appstate,ff.remark,ff.nextapproverid,ff.subflag"
				+",ff.fjpath,ff.skdw,(select name from customer where id=ff.Skdw)skdw_name from Fi_financial_account ff,hr_base_info hb where 1=1 and ff.personid=hb.id(+) " +
				"/~ and ff.id={id} ~/"
		+ "/~ and hb.name like '%[personname]%' ~/ "
		+ "/~ and ff.personid={personid} ~/ " 
		+ "/~ and ff.projectid={projectid} ~/ "
		+ "/~ and ff.advance_type={advance_type} ~/ "
		+ "/~ and ff.appstate={appstate} ~/ "+
		" order by ff.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//查询明细
	private List search_MX(String id) throws Exception 
	{
		HashMap<String, String> searchMap=new HashMap<String, String>();
		searchMap.put("id", id);
		String sql="select * from Fi_fina_account_dtl where 1=1 /~ and accid={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		List list=dataBaseControl.getOutResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray());
		return list;
	}
	
	//查询审批记录
	private Page searchApp(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*," +
				"(select hb.name from hr_base_info hb where hb.id=t.auditid) person_name from Fi_fina_account_appr t where 1=1 /~ and t.accid={id} ~/ order by t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//打印
	public String print(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String idString = request.getParameter("id");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int count = Integer.parseInt((dataBaseControl.getOneResultSet("select print from Fi_financial_account where id=?", new Object[]{idString})).get("print").toString());
			count++;
			dataBaseControl.executeSql("update Fi_financial_account set print=? where id=?", new Object[]{count,idString});
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		Page page=searchShow(request,1,1);
		Page pageApp=searchApp(request, 1, 20);
		List dmList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from Fi_fina_account_appr t,hr_base_info hb,Fi_financial_account ff where ff.id=t.accid(+) and t.auditid = hb.id(+) and hb.dept_id = ff.departid and ff.id=? order by t.id desc", new Object[]{idString}, 1);
		List cpaList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from Fi_fina_account_appr t,hr_base_info hb where t.auditid=hb.id(+) and t.deptid=6 and hb.duty_id=1 and t.accid=? order by t.id desc", new Object[]{idString}, 1);
		List fmList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from Fi_fina_account_appr t,hr_base_info hb where t.auditid=hb.id(+) and t.deptid=6 and hb.duty_id=2 and t.accid=? order by t.id desc", new Object[]{idString}, 1);
		List gmList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from Fi_fina_account_appr t,hr_base_info hb where t.auditid=hb.id(+) and hb.duty_id!=1 and hb.duty_id!=2 and t.accid=? order by t.id desc", new Object[]{idString}, 1);
		
		Map dmMap = null;
		Map cpaMap = null;
		Map fmMap = null;
		Map gmMap = null;
		if(dmList.size()!=0){
			dmMap = (Map) dmList.get(0);
		}
		if(cpaList.size()!=0){
			cpaMap = (Map) cpaList.get(0);
		}
		if(fmList.size()!=0){
			fmMap = (Map) fmList.get(0);
		}
		if(gmList.size()!=0){
			gmMap = (Map) gmList.get(0);
		}
		
		request.setAttribute("dmMap", dmMap);
		request.setAttribute("fmMap", fmMap);
		request.setAttribute("gmMap", gmMap);
		request.setAttribute("cpaMap", cpaMap);
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("record_mx", search_MX(request.getParameter("id")));
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/financial/print.jsp";
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String subflag=request.getParameter("subflag");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			try {
				Long id = Long.parseLong(request.getParameter("id"));
				dataBaseControl.beginTransaction();
				
				Fi_financial_account account = (Fi_financial_account)getMapObject(request);
				Map user = (Map)request.getSession().getAttribute("user");
				account.setPersonid(new Long(user.get("base_info_id").toString()));
				account.setDepartid(new Long(Integer.valueOf(user.get("branchid").toString())));
				account.setApply_date(new java.sql.Date((new java.util.Date()).getTime()));
				account.setFiflag(new BigDecimal(0));
				dataBaseControl.insertByBean(account,id);
				
				String rowData = request.getParameter("mxJson");
				List<Fi_fina_account_dtl> mapList = JSON.parseArray(rowData, Fi_fina_account_dtl.class);
				for(int i = 0 ; i < mapList.size();i++){
					Fi_fina_account_dtl account_dtl = (Fi_fina_account_dtl)mapList.get(i);
					account_dtl.setAccid(id);
					dataBaseControl.insertByBean(account_dtl);
				}
				
				/* 下一阶段审批人 */
				String nextapproverid = "";
				String appstate = "";
				String duty_id = "";
				Long accid = id;
				Map pMap = dataBaseControl.getOneResultSet("select t.personid,t.departid from fi_financial_account t where t.id=?", new Object[]{accid});
				String p_id = pMap.get("personid").toString();
				String dutyString = (dataBaseControl.getOneResultSet("select duty_id from hr_base_info where id=?", new Object[]{p_id})).get("duty_id").toString();
				String dept_id = pMap.get("departid").toString();
				if(dept_id.equals("9"))//成都公司
				{
					duty_id = "5";
				}
				else if(dept_id.equals("5"))//崔总提交申请
				{
					duty_id = "4";
				}
				else if(dept_id.equals("1"))//赵总提交申请
				{
					duty_id = "3";
				}
				else
				{
					duty_id = "2";
				}
				Map appMap = dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.dept_id=? and t.duty_id=? and t.hr_type=3", new Object[]{dept_id,duty_id});
				String dp_id = appMap.get("id").toString();
				if(p_id.equals(dp_id)){	//判断是否是部门经理提交 若是 则跳到会计审批节点
					//特例 客户部邵德由崔岩审批
					if(dutyString.equals("2")&&dept_id.equals("10")){
						nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
					}else{
						nextapproverid = (dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString();
						appstate = "1";
					}
				}
				else if(dept_id.equals("10")){	//客户部申请由崔总审批
					nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
				}
				else{
					appstate = "0";
					nextapproverid = dp_id;
				}
				//当为客户部或成都公司时，提交后直接交由公司领导审批
				if((dept_id.equals("9")||dept_id.equals("10"))&&(dutyString.equals("1")||dutyString.equals("2")))
					appstate = "3";
				int s = dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,accid});
				
				if(!request.getParameter("subflag").equals("0")){
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
						
						String infor="您有一条财务报账待审批请求，请您审批！";
						m.sendGFDZ(num,infor);
					}
					
					
				}
				
				
				dataBaseControl.endTransaction();
				request.setAttribute("operationSign", "closeDG_refreshW();");
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
		}
		else{
			Long id = dataBaseControl.getSeq(Fi_financial_account.class);
			request.setAttribute("accid", id);
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/fi/financial/fi_financial_accountDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String id = request.getParameter("id");
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			try {
				dataBaseControl.beginTransaction();
				Fi_financial_account account = (Fi_financial_account)getMapObject(request);
				Map user = (Map)request.getSession().getAttribute("user");
				account.setPersonid(new Long(user.get("base_info_id").toString()));
				account.setDepartid(new Long(Integer.valueOf(user.get("branchid").toString())));
				account.setApply_date(new java.sql.Date((new java.util.Date()).getTime()));
				account.setFiflag(new BigDecimal("0"));
				dataBaseControl.updateByBean(account);
				
				String rowData = request.getParameter("mxJson");
				List<Fi_fina_account_dtl> mapList = JSON.parseArray(rowData, Fi_fina_account_dtl.class);
				for(int i = 0 ; i < mapList.size();i++){
					Fi_fina_account_dtl account_dtl = (Fi_fina_account_dtl)mapList.get(i);
					account_dtl.setAccid(account.getId());
					if(account_dtl.getId()!=null)
						dataBaseControl.updateByBean(account_dtl);
					else 
						dataBaseControl.insertByBean(account_dtl);
				}
				
				//打回再修改
				String nextapproverid = "";
				String appstate = "";
				String duty_id = "";
				Map pMap = dataBaseControl.getOneResultSet("select t.personid,t.departid from fi_financial_account t where t.id=?", new Object[]{id});
				String p_id = pMap.get("personid").toString();
				String dutyString = (dataBaseControl.getOneResultSet("select duty_id from hr_base_info where id=?", new Object[]{p_id})).get("duty_id").toString();
				String dept_id = pMap.get("departid").toString();
				if(dept_id.equals("9"))//成都公司
				{
					duty_id = "5";
				}
				else if(dept_id.equals("5"))//崔总提交申请
				{
					duty_id = "4";
				}
				else if(dept_id.equals("1"))//赵总提交申请
				{
					duty_id = "3";
				}
				else
				{
					duty_id = "2";
				}
				Map appMap = dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.dept_id=? and t.duty_id=? and t.hr_type=3", new Object[]{dept_id,duty_id});
				String dp_id = appMap.get("id").toString();
				if(p_id.equals(dp_id)){	//判断是否是部门经理提交 若是 则跳到会计审批节点
					//特例 客户部邵德由崔岩审批
					if(dutyString.equals("2")&&dept_id.equals("10")){
						nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
					}else{
						nextapproverid = (dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString();
						appstate = "1";
					}
				}
				else if(dept_id.equals("10")){	//客户部申请由崔总审批
					nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
				}
				else{
					appstate = "0";
					nextapproverid = dp_id;
				}
				//当为客户部或成都公司时，提交后直接交由公司领导审批
				if((dept_id.equals("9")||dept_id.equals("10"))&&(dutyString.equals("1")||dutyString.equals("2")))
					appstate = "3";
				int s = dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,id});
				
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
					
					String infor="您有一条财务报账待审批请求，请您审批！";
					m.sendGFDZ(num,infor);
				}
				dataBaseControl.endTransaction();
				request.setAttribute("operationSign", "closeDG_refreshW();");
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
		}else{
			Page page=searchShow(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
			request.setAttribute("record_mx", search_MX(request.getParameter("id")));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/financial/fi_financial_accountDtl.jsp";
	}
	
	//审批
	public String appr(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Map myuser=(Map)request.getSession().getAttribute("user");
		String accdept = request.getParameter("accdept");
		
		/* 财务选择付款单位 */
		String base_info_id = myuser.get("base_info_id").toString();
		if((dataBaseControl.getOneResultSet("select * from hr_base_info where dept_id=6 and id=?", new Object[]{base_info_id})).size()!=0){
			request.setAttribute("fiFlag", "fiFlag");
		}
		
		Date today = new Date();
		String apptime = dFormat.format(today);
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String accid = request.getParameter("accid");
			
			Map accMap = dataBaseControl.getOneResultSet("select t.appstate,hb.duty_id,t.personid,t.fiflag from fi_financial_account t,hr_base_info hb where t.personid=hb.id(+) and t.id=?", new Object[]{accid});
			String acc_app = accMap.get("appstate").toString();
			String acc_pid = accMap.get("personid").toString();
			String accduty = accMap.get("duty_id").toString();
			String fiflag = accMap.get("fiflag").toString();
			
			String departid = request.getParameter("departid");
			String auditid = request.getParameter("auditid");
			String appstate = request.getParameter("appstate");
			String appopinion = request.getParameter("appopinion");
			
			Fi_fina_account_appr approval = new Fi_fina_account_appr();
			
			approval.setAccid(Long.parseLong(accid));
			approval.setAuditid(Long.parseLong(auditid));
			approval.setDeptid(Long.parseLong(departid));
			approval.setApptime((new java.sql.Date((fmt.parse(apptime)).getTime())));
			approval.setAppopinion(appopinion);
			
			try {
				dataBaseControl.beginTransaction();
				int i =dataBaseControl.insertByBean(approval);
				
				String fukuan_unit = request.getParameter("fukuan_unit")==null?"":request.getParameter("fukuan_unit");
				if(!fukuan_unit.equals("")){
					dataBaseControl.executeSql("update fi_financial_account set fukuan_unit=? where id=?", new Object[]{fukuan_unit,accid});
				}
				
				if(i==1){
					/*	赵总报账	*/
					if(accduty.equals("3")){	
						if(acc_app.equals("1")){//会计审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=2 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else{//财务经理审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=0,t.appstate=4,t.fiflag=1 where t.id=?", new Object[]{accid});
							}else{
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
					}
					/*	崔总、向总报账	*/
					else if(accduty.equals("4")||accduty.equals("5")){
						if(acc_app.equals("1")){//会计审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=2 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else if(acc_app.equals("2")){//财务经理审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=3,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3 and t.hr_type=3", null)).get("id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else{
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=4,t.nextapproverid=0 where t.id=?", new Object[]{accid});
							}
							else{
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
					}
					/*	其他人员报账	*/
					else{	
						if(acc_app.equals("0")){//部门经理审批
							if(appstate.equals("1")){
								//特例：若是会计提交，则财务经理审批后直接到崔总审批
								if(((dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString()).equals(acc_pid)){
									dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=3 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString(),accid});
								}
								else{
									dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString(),accid});
								}
							}else{
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else if(acc_app.equals("1")){//会计审批
							if(appstate.equals("1")){
								//特例：若是财务经理提交，则会计审批后直接到崔总审批
								if(((dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString()).equals(acc_pid)){
									dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=3 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString(),accid});
								}else{
									dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=2 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString(),accid});
								}
							}else{
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else if(acc_app.equals("2")){//财务经理审批
							if(appstate.equals("1")){
								if(accdept.equals("4"))//软件部
									dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=3,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3 and t.hr_type=3", null)).get("id").toString(),accid});
								if(accdept.equals("9"))//成都
									dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=3,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=5 and t.hr_type=3", null)).get("id").toString(),accid});
								if(!(accdept.equals("4")||accdept.equals("9")))//公司其他部门
									dataBaseControl.executeSql("update fi_financial_account t set t.nextapproverid=?,t.appstate=3,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else{	//公司领导审批
							if(appstate.equals("1")){
								//客户部及成都公司交由崔、向总审批后，转由会计审批
								//fiflag为0表示财务未审核过，则经由财务流程审核
								//fiflag为1表示财务已审核过，则崔、向审批后结束
								if((accdept.equals("10")||accdept.equals("9"))&&fiflag.equals("0")) {
									dataBaseControl.executeSql("update fi_financial_account t set t.appstate=1,t.nextapproverid=? where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString(),accid});
								}
								else{
									dataBaseControl.executeSql("update fi_financial_account t set t.appstate=4,t.nextapproverid=0 where t.id=?", new Object[]{accid});
								}
							}
							else{
								dataBaseControl.executeSql("update fi_financial_account t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
					}
					
					/* 发短信 */
					Map messageMap = dataBaseControl.getOneResultSet("select t.appstate,t.nextapproverid from fi_financial_account t where t.id=?", new Object[]{accid});
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
							
							infor="您有一条财务报账待审批请求，请您审批！";
							m.sendGFDZ(num,infor);
						}
						
						/* 短信提醒审批结果 */
						String state = messageMap.get("appstate").toString();
						if(state.equals("4")||state.equals("-1")||state.equals("-2")){
							Map numMap = dataBaseControl.getOneResultSet("select t.phone from hr_base_info t where t.id=(select t.personid from fi_financial_account t where t.id=?) and t.hr_type=3", new Object[]{accid});
							if(numMap.size()!=0){
								num = numMap.get("phone").toString();
							}
							if(state.equals("4"))
								infor="您的财务报账请求审批已通过！";
							if(state.equals("-1"))
								infor="您的财务报账请求被打回！";
							if(state.equals("-2"))
								infor="您的财务报账请求审批不通过！";
							m.sendGFDZ(num,infor);
						}
					}
					request.setAttribute("operationSign", "closeDG_refreshW();");
				}
				dataBaseControl.endTransaction();
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
		}
		else{
			Page page=searchShow(request,1,1);
			Page pageApp=searchApp(request,1,10);
			
			request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
			request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
			request.setAttribute("record_mx", search_MX(request.getParameter("id")));
		}
		
		request.setAttribute("apptime", apptime);
		request.setAttribute("editMod", "appr");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/financial/appr.jsp";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.beginTransaction();
		dataBaseControl.executeSql("DELETE FROM Fi_financial_account WHERE ID = ?", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Fi_fina_account_appr WHERE ACCID = ?", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Fi_fina_account_dtl WHERE ACCID = ?", new Object[]{s_id});
		dataBaseControl.endTransaction();
		return "/fi/financial/Fi_financial_account!searchList.do";
	}
	public String deleteDemo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		defaultDelete(request, response);
		
	
		String id = request.getParameter("id");
		
		dataBaseControl.beginTransaction();
		dataBaseControl.executeSql("DELETE FROM Fi_financial_account WHERE ID = ?", new Object[]{id});
		dataBaseControl.executeSql("DELETE FROM Fi_fina_account_appr WHERE ACCID = ?", new Object[]{id});
		dataBaseControl.executeSql("DELETE FROM Fi_fina_account_dtl WHERE ACCID = ?", new Object[]{id});
		dataBaseControl.endTransaction();
		
        return null;
	}
	
	//提交
	public String sub(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String id = request.getParameter("id");
		dataBaseControl.executeSql("update Fi_financial_account t set t.subflag='1' where t.id=?", new Object[]{id});
		return "/fi/financial/Fi_financial_account!searchList.do";
	}
	public void subDemo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		int i=dataBaseControl.executeSql("update Fi_financial_account t set t.subflag='1' where t.id=?", new Object[]{id});
		
		PrintWriter pw =response.getWriter();
		pw.print(i);
		pw.flush();
		pw.close();
	}

	
	//作废
	public String invalid(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String idString = request.getParameter("id");
			String zf_reasonString = request.getParameter("zf_reason");
			String zf_personString = request.getParameter("pid");
			String zf_timeString = dFormat.format(today);
			int i=dataBaseControl.executeSql("update Fi_financial_account t set t.appstate='-3',t.zf_reason=?,t.zf_person=?,t.zf_time=to_date(?,'yyyy-MM-dd') where t.id=?", new Object[]{zf_reasonString,zf_personString,zf_timeString,idString});
			//if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			if(i==1)request.setAttribute("operationSign", "close_refreshW();");
		}
		
		Page page=searchShow(request,1,1);
		Page pageApp=searchApp(request, 1, 20);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
		request.setAttribute("record_mx", search_MX(request.getParameter("id")));
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/financial/zuofei.jsp";
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("advance_type",codeTableUtil.getCodeMap("advance_type")); 
		request.setAttribute("paytype",codeTableUtil.getCodeMap("PayType")); 
		request.setAttribute("fplx",codeTableUtil.getCodeMap("fplx")); 
		request.setAttribute("fiacc_state",codeTableUtil.getCodeMap("fiacc_state"));
		request.setAttribute("fukuan",codeTableUtil.getCodeMap("fukuan_unit"));
	}

}