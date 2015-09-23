package com.web.fi.advance;

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

import com.alibaba.fastjson.JSON;
import com.map.Fi_advance;
import com.map.Fi_advance_appr;
import com.map.Fi_advance_back;
import com.map.Fi_fina_account_appr;
import com.map.Fi_financial_account;
import com.map.Fi_payfor;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Fi_advanceAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		//会计角色标志
		Map user = (Map)request.getSession().getAttribute("user");
		
		String cpaStr = (dataBaseControl.getOneResultSet("select count(su.id) cpaStr from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16 and su.base_info_id=?", new Object[]{user.get("base_info_id").toString()})).get("cpaStr").toString();
		Page page=searchAd(request,pageNo,pageSize);
		
		Map searchMap = getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		String duty_id = (String) myuser.get("duty_id");
		if(!duty_id.equals("1")){
			if(duty_id.equals("3")){
				String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("4")){
				String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("5")){
				String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else{}
		}
		
		request.setAttribute("searchMap", searchMap);
		request.setAttribute("cpaStr", cpaStr);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/advance/fi_advanceSelect.jsp";
		return "/fi/advance/fi_advanceList.jsp";
	}
	////////////////////////////////////暂支管理统计
public String searchListDemo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	Map myuser=(Map)request.getSession().getAttribute("user");
	BigDecimal branchid = (BigDecimal) myuser.get("branchid");
	String duty_id = (String) myuser.get("duty_id");
	String dept_id = branchid.toString();
	String p_id= myuser.get("base_info_id").toString();
	Map searchMap=getParameterMap(request);
	String sqlDept = "";
	if(!(dept_id.equals("1")||dept_id.equals("6"))){
		if(duty_id.equals("1")){	//职员只可查看自己
			searchMap.put("p_id", p_id);
		}
		else if(duty_id.equals("4")){	//崔总可查看除以下部门的
			sqlDept = " and fa.deptid not in (1,4,9,11,12) ";
		}
		else{
			searchMap.put("dept_id", dept_id);	//部门经理查看本部门的
			sqlDept = "/~ and fa.deptid={dept_id} ~/";
		}
	}
	
	//各角色默认查询状态
	if(!duty_id.equals("1")){
		if(duty_id.equals("3")){
			String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
			searchMap.put("appstate", appstate);
		}
		else if(duty_id.equals("4")){
			String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
			searchMap.put("appstate", appstate);
		}
		else if(duty_id.equals("5")){
			String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
			searchMap.put("appstate", appstate);
		}
		else{}
	}
	
	String sql="select fa.*,hb.name person_name, c.dmzmc ,(fa.apply_money-fa.back_money)qk," +       
			"(select mb.branchname from mrbranch mb where mb.id=fa.deptid) dept_name " +
			"from Fi_advance fa,hr_base_info hb ,code c where 1=1 and c.dmlb='advance_appstate'and c.dmz=fa.appstate and fa.pid=hb.id(+) /~ and fa.id={id} ~/" +
			"/~ and hb.name like '%[personname]%' ~/" +
			"/~ and fa.apply_reason={apply_reason} ~/" +
			"/~ and fa.deptid={deptid} ~/" +
			"/~ and fa.pid={p_id} ~/" +
			"/~ and fa.advance_type={advance_type} ~/" +
			"/~ and fa.apply_time >= to_date({apply_date1},'yyyy-MM-dd') ~/"+ 
			"/~ and fa.apply_time <= to_date({apply_date2},'yyyy-MM-dd') ~/"+
			"/~ and fa.appstate={appstate} ~/ "+sqlDept;
		defaultList(request,response,new StringBuffer(sql),"fa.id desc"); 
		
		return null;
	
	}
public void total(HttpServletRequest request,HttpServletResponse response) throws Exception {
	Map myuser=(Map)request.getSession().getAttribute("user");
	BigDecimal branchid = (BigDecimal) myuser.get("branchid");
	String duty_id = (String) myuser.get("duty_id");
	String dept_id = branchid.toString();
	String p_id= myuser.get("base_info_id").toString();
	Map searchMap=getParameterMap(request);
	String sqlDept = "";
	if(!(dept_id.equals("1")||dept_id.equals("6"))){
		if(duty_id.equals("1")){	//职员只可查看自己
			searchMap.put("p_id", p_id);
		}
		else if(duty_id.equals("4")){	//崔总可查看除以下部门的
			sqlDept = " and fa.deptid not in (1,4,9,11,12) ";
		}
		else{
			searchMap.put("dept_id", dept_id);	//部门经理查看本部门的
			sqlDept = "/~ and fa.deptid={dept_id} ~/";
		}
	}
	
	//各角色默认查询状态
	if(!duty_id.equals("1")){
		if(duty_id.equals("3")){
			String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
			searchMap.put("appstate", appstate);
		}
		else if(duty_id.equals("4")){
			String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
			searchMap.put("appstate", appstate);
		}
		else if(duty_id.equals("5")){
			String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
			searchMap.put("appstate", appstate);
		}
		else{}
	}
	
	String sql="select sum(fa.apply_money)sum,sum(fa.apply_money-fa.back_money)sum1,count(*)count from Fi_advance fa,hr_base_info hb ,code c where 1=1 and c.dmlb='advance_appstate'and c.dmz=fa.appstate and fa.pid=hb.id(+) /~ and fa.id={id} ~/" +
			"/~ and hb.name like '%[personname]%' ~/" +
			"/~ and fa.apply_reason={apply_reason} ~/" +
			"/~ and fa.deptid={deptid} ~/" +
			"/~ and fa.pid={p_id} ~/" +
			"/~ and fa.advance_type={advance_type} ~/" +
			"/~ and fa.apply_time >= to_date({apply_date1},'yyyy-MM-dd') ~/"+ 
			"/~ and fa.apply_time <= to_date({apply_date2},'yyyy-MM-dd') ~/"+
			"/~ and fa.appstate={appstate} ~/ "+sqlDept;
	
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
		Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
		
		String jsonStr = JSON.toJSONString(map);
		setAjaxInfo(response,jsonStr);
	
	}
	////////////////////////////////////
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page pageAd=searchShow(request,1,1);
		Page pageApp=searchApp(request,1,10);
		Page pageBak=searchBak(request,1,10);
		
		request.setAttribute("recordAd", ((ArrayList)pageAd.getThisPageElements()).get(0));
		request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
		request.setAttribute("recordBak", (ArrayList)pageBak.getThisPageElements());
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/fi/advance/showDtl.jsp";
	}
	
	private Page searchAd(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String duty_id = (String) myuser.get("duty_id");
		String dept_id = branchid.toString();
		String p_id= myuser.get("base_info_id").toString();
		Map searchMap=getParameterMap(request);
		String sqlDept = "";
		if(!(dept_id.equals("1")||dept_id.equals("6"))){
			if(duty_id.equals("1")){	//职员只可查看自己
				searchMap.put("p_id", p_id);
			}
			else if(duty_id.equals("4")){	//崔总可查看除以下部门的
				sqlDept = " and fa.deptid not in (1,4,9,11,12) ";
			}
			else{
				searchMap.put("dept_id", dept_id);	//部门经理查看本部门的
				sqlDept = "/~ and fa.deptid={dept_id} ~/";
			}
		}
		
		//各角色默认查询状态
		if(!duty_id.equals("1")){
			if(duty_id.equals("3")){
				String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("4")){
				String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else if(duty_id.equals("5")){
				String appstate = request.getParameter("appstate")==null?"2":request.getParameter("appstate");
				searchMap.put("appstate", appstate);
			}
			else{}
		}
		
		String sql="select fa.*,hb.name person_name," +
				"(select mb.branchname from mrbranch mb where mb.id=fa.deptid) dept_name " +
				"from Fi_advance fa,hr_base_info hb where 1=1 and fa.pid=hb.id(+) /~ and fa.id={id} ~/" +
				"/~ and hb.name like '%[personname]%' ~/" +
				"/~ and fa.pid={p_id} ~/" +
				"/~ and fa.apply_time >= to_date({apply_date1},'yyyy-MM-dd') ~/"+ 
				"/~ and fa.apply_time <= to_date({apply_date2},'yyyy-MM-dd') ~/"+
				"/~ and fa.appstate={appstate} ~/ "+sqlDept+
				"order by fa.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchShow(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		String sql="select fa.*,hb.name person_name," +
				"(select hb.name from hr_base_info hb where hb.id=fa.zf_person) zf_pname," +
				"(select mb.branchname from mrbranch mb where mb.id=fa.deptid) dept_name " +
				"from Fi_advance fa,hr_base_info hb where 1=1 and fa.pid=hb.id(+) /~ and fa.id={id} ~/" +
				"/~ and hb.name like '%[personname]%' ~/" +
				"/~ and fa.pid={p_id} ~/" +
				"/~ and fa.appstate={appstate} ~/ "+
				"order by fa.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//查询审批记录
	private Page searchApp(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*," +
				"(select hb.name from hr_base_info hb where hb.id=t.auditid) person_name from Fi_advance_appr t where 1=1 /~ and t.adaid={id} ~/ order by t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//查询还款记录
	private Page searchBak(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.* from Fi_advance_back t where 1=1 /~ and t.advid={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//打印
	public String print(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String idString = request.getParameter("id");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int count = Integer.parseInt((dataBaseControl.getOneResultSet("select print from fi_advance where id=?", new Object[]{idString})).get("print").toString());
			count++;
			dataBaseControl.executeSql("update fi_advance set print=? where id=?", new Object[]{count,idString});
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		Page pageAd=searchShow(request,1,1);
		List dmList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from fi_advance_appr t,hr_base_info hb,fi_advance fa where fa.id=t.adaid(+) and t.auditid = hb.id(+) and hb.dept_id = fa.deptid and fa.id=? order by t.id desc", new Object[]{idString}, 1);
		List fmList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from fi_advance_appr t,hr_base_info hb where t.auditid=hb.id(+) and t.deptid=6 and t.adaid=? order by t.id desc", new Object[]{idString}, 1);
		List gmList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from fi_advance_appr t,hr_base_info hb where t.auditid=hb.id(+) and hb.duty_id!=1 and hb.duty_id!=2 and t.adaid=? order by t.id desc", new Object[]{idString}, 1);
		
		Map dmMap = null;
		Map fmMap = null;
		Map gmMap = null;
		if(dmList.size()!=0){
			dmMap = (Map) dmList.get(0);
		}
		if(fmList.size()!=0){
			fmMap = (Map) fmList.get(0);
		}
		if(gmList.size()!=0){
			gmMap = (Map) gmList.get(0);
		}
		
		request.setAttribute("recordAd", ((ArrayList)pageAd.getThisPageElements()).get(0));
		request.setAttribute("dmMap", dmMap);
		request.setAttribute("fmMap", fmMap);
		request.setAttribute("gmMap", gmMap);
		request.setAttribute("btnDisplay", "display:none");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/advance/print.jsp";
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String apply_time = dFormat.format(today);
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Long id = dataBaseControl.getSeq(Fi_advance.class);
			try {
				dataBaseControl.beginTransaction();
				int i =dataBaseControl.insertByBean(getMapObject(request),id);			
				if(i==1){
					/* 下一阶段审批人 */
					String nextapproverid = "";
					String appstate = "";
					String duty_id = "";
					Map pMap = dataBaseControl.getOneResultSet("select t.pid,t.deptid from fi_advance t where t.id=?", new Object[]{id});
					String p_id = pMap.get("pid").toString();
					String dutyString = (dataBaseControl.getOneResultSet("select duty_id from hr_base_info where id=?", new Object[]{p_id})).get("duty_id").toString();
					String dept_id = pMap.get("deptid").toString();
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
					if(p_id.equals(dp_id)){	//判断是否是部门经理提交 若是 则跳到财务审批节点
						//特例 由财务经理自己提交的申请 转由会计审批 rolesid = 16为会计角色
						//特例 客户部邵德由崔岩审批
						if(dutyString.equals("2")&&dept_id.equals("10")){
							nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
						}else{
							if(((dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString()).equals(p_id)){
								nextapproverid = (dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString();
							}
							else{	//rolesid = 6为财务经理角色
								nextapproverid = (dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString();
							}
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
						appstate = "2";
					int s = dataBaseControl.executeSql("update fi_advance t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,id});
					
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
						
						String infor="您有一条暂支待审批请求，请您审批！";
						m.sendGFDZ(num,infor);
					}
					
					request.setAttribute("operationSign", "closeDG_refreshW();");
				}
				dataBaseControl.endTransaction();
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
		}else{
			Long id = dataBaseControl.getSeq(Fi_advance.class);
			request.setAttribute("accid", id+1);
		}
		buildDDL(request);
		request.setAttribute("apply_time", apply_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/fi/advance/fi_advanceDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String id = request.getParameter("id");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			
			try {
				dataBaseControl.beginTransaction();
				int i = dataBaseControl.updateByBean(getMapObject(request));
				if(i==1){
					/* 下一阶段审批人 */
					String nextapproverid = "";
					String appstate = "";
					String duty_id = "";
					Map pMap = dataBaseControl.getOneResultSet("select t.pid,t.deptid from fi_advance t where t.id=?", new Object[]{id});
					String p_id = pMap.get("pid").toString();
					String dutyString = (dataBaseControl.getOneResultSet("select duty_id from hr_base_info where id=?", new Object[]{p_id})).get("duty_id").toString();
					String dept_id = pMap.get("deptid").toString();
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
					if(p_id.equals(dp_id)){	//判断是否是部门经理提交 若是 则跳到财务审批节点
						//特例 客户部邵德由崔岩审批
						if(dutyString.equals("2")&&dept_id.equals("10")){
							nextapproverid = (dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString();
						}else{
							if(((dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString()).equals(p_id)){
								nextapproverid = (dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString();
							}
							else{	//rolesid = 6为财务经理角色
								nextapproverid = (dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString();
							}
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
						appstate = "2";
					int s = dataBaseControl.executeSql("update fi_advance t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,id});
					
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
						
						String infor="您有一条暂支待审批请求，请您审批！";
						m.sendGFDZ(num,infor);
					}
					
					request.setAttribute("operationSign", "closeDG_refreshW();");
				}
				dataBaseControl.endTransaction();
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
		}else{
			Page page=searchShow(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/advance/fi_advanceDtl.jsp";
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
			
			Map accMap = dataBaseControl.getOneResultSet("select t.appstate,hb.duty_id,t.fiflag from fi_advance t,hr_base_info hb where t.pid=hb.id(+) and t.id=?", new Object[]{accid});
			String acc_app = accMap.get("appstate").toString();
			String accduty = accMap.get("duty_id").toString();
			String fiflag = accMap.get("fiflag").toString();
			
			String departid = request.getParameter("deptid");
			String auditid = request.getParameter("auditid");
			String appstate = request.getParameter("appstate");
			String appopinion = request.getParameter("appopinion");
			
			Fi_advance_appr approval = new Fi_advance_appr();
			
			approval.setAdaid(Long.parseLong(accid));
			approval.setAuditid(Long.parseLong(auditid));
			approval.setDeptid(Long.parseLong(departid));
			approval.setApptime((new java.sql.Date((fmt.parse(apptime)).getTime())));
			approval.setAppopinion(appopinion);
			
			try {
				dataBaseControl.beginTransaction();
				int i =dataBaseControl.insertByBean(approval);
				
				String fukuan_unit = request.getParameter("fukuan_unit")==null?"":request.getParameter("fukuan_unit");
				if(!fukuan_unit.equals("")){
					dataBaseControl.executeSql("update fi_advance set fukuan_unit=? where id=?", new Object[]{fukuan_unit,accid});
				}
				
				if(i==1){
					/*	赵总报账	*/
					if(accduty.equals("3")){	
						if(acc_app.equals("1")){//财务经理审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_advance t set t.nextapproverid=0,t.appstate=3,t.fiflag=1 where t.id=?", new Object[]{accid});
							}else{
								dataBaseControl.executeSql("update fi_advance t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
					}
					/*	崔总、向总报账	*/
					else if(accduty.equals("4")||accduty.equals("5")){
						if(acc_app.equals("1")){//财务经理审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_advance t set t.nextapproverid=?,t.appstate=2,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3", null)).get("id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_advance t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else{
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_advance t set t.appstate=3,t.nextapproverid=0 where t.id=?", new Object[]{accid});
							}
							else{
								dataBaseControl.executeSql("update fi_advance t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
					}
					/*	其他人员报账	*/
					else{	
						if(acc_app.equals("0")){//部门经理审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_advance t set t.nextapproverid=?,t.appstate=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_advance t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else if(acc_app.equals("1")){//财务经理审批
							if(appstate.equals("1")){
								if(accdept.equals("4"))//软件部
									dataBaseControl.executeSql("update fi_advance t set t.nextapproverid=?,t.appstate=2,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3", null)).get("id").toString(),accid});
								if(accdept.equals("9"))//成都
									dataBaseControl.executeSql("update fi_advance t set t.nextapproverid=?,t.appstate=2,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=5", null)).get("id").toString(),accid});
								if(!(accdept.equals("4")||accdept.equals("9")))//公司其他部门
									dataBaseControl.executeSql("update fi_advance t set t.nextapproverid=?,t.appstate=2,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4", null)).get("id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_advance t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else{	//公司领导审批
							if(appstate.equals("1")){
								//客户部及成都公司交由崔、向总审批后，转由财务经理审批
								//fiflag为0表示财务未审核过，则经由财务流程审核
								//fiflag为1表示财务已审核过，则崔、向审批后结束
								if((accdept.equals("10")||accdept.equals("9"))&&fiflag.equals("0")) {
									dataBaseControl.executeSql("update fi_advance t set t.appstate=1,t.nextapproverid=? where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString(),accid});
								}
								else{
									dataBaseControl.executeSql("update fi_advance t set t.appstate=3,t.nextapproverid=0 where t.id=?", new Object[]{accid});
								}
							}
							else{
								dataBaseControl.executeSql("update fi_advance t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
					}
					
					/* 发短信 */
					Map messageMap = dataBaseControl.getOneResultSet("select t.appstate,t.nextapproverid from fi_advance t where t.id=?", new Object[]{accid});
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
							
							infor="您有一条暂支待审批请求，请您审批！";
							m.sendGFDZ(num,infor);
						}
						
						/* 短信提醒审批结果 */
						String state = messageMap.get("appstate").toString();
						if(state.equals("3")||state.equals("-1")||state.equals("-2")){
							Map numMap = dataBaseControl.getOneResultSet("select t.phone from hr_base_info t where t.id=(select t.pid from fi_advance t where t.id=?) and t.hr_type=3", new Object[]{accid});
							if(numMap.size()!=0){
								num = numMap.get("phone").toString();
							}
							if(state.equals("3"))
								infor="您的暂支请求审批已通过！";
							if(state.equals("-1"))
								infor="您的暂支请求被打回！";
							if(state.equals("-2"))
								infor="您的暂支请求审批不通过！";
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
			Page pageAd=searchShow(request,1,1);
			Page pageApp=searchApp(request,1,10);
			
			request.setAttribute("recordAd", ((ArrayList)pageAd.getThisPageElements()).get(0));
			request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
		}
		
		request.setAttribute("apptime", apptime);
		request.setAttribute("editMod", "appr");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/advance/appr.jsp";
	}
	
	//提交
	public String sub(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String id = request.getParameter("id");
		dataBaseControl.executeSql("update Fi_advance t set t.subflag='1' where t.id=?", new Object[]{id});
		return "/fi/advance/Fi_advance!searchList.do";
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
			int i=dataBaseControl.executeSql("update Fi_advance t set t.appstate='-3',t.zf_reason=?,t.zf_person=?,t.zf_time=to_date(?,'yyyy-MM-dd') where t.id=?", new Object[]{zf_reasonString,zf_personString,zf_timeString,idString});
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		
		Page pageAd=searchShow(request,1,1);
		Page pageApp=searchApp(request,1,10);
		Page pageBak=searchBak(request,1,10);
		
		request.setAttribute("recordAd", ((ArrayList)pageAd.getThisPageElements()).get(0));
		request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
		request.setAttribute("recordBak", (ArrayList)pageBak.getThisPageElements());
		request.setAttribute("btnDisplay", "display:none");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/advance/zuofei.jsp";
	}
	
	public String back(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String id = request.getParameter("id");
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map moneyMap = dataBaseControl.getOneResultSet("select t.back_money from fi_advance t where t.id=?", new Object[]{id});
			Double back_money = Double.parseDouble(moneyMap.get("back_money").toString());
			Double backed_money = Double.parseDouble(request.getParameter("backed_money"));
			
			//重新写入目前为止已还欠款
			back_money = back_money + backed_money;
			
			try {
				dataBaseControl.beginTransaction();
				dataBaseControl.executeSql("update fi_advance t set t.back_money=? where t.id=?", new Object[]{back_money,id});
				
				Fi_advance_back back = new Fi_advance_back();
				back.setAdvid(Long.parseLong(id));
				back.setBack_date((new java.sql.Date((fmt.parse(request.getParameter("back_date")).getTime()))));
				back.setBacked_money(BigDecimal.valueOf(backed_money));
				back.setRemark(request.getParameter("remark"));
				int i = dataBaseControl.insertByBean(back);
				
				/* 发短信 */
				Map messageMap = dataBaseControl.getOneResultSet("select (t.apply_money-t.back_money) debt,t.apply_time from Fi_advance t where t.id=?", new Object[]{id});
				if(!messageMap.isEmpty()){
					String num="";
					String infor="";
					PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
					Mas m = (Mas) client.useService(Mas.class);
					Double debt = Double.parseDouble(messageMap.get("debt").toString());
					String apply_timeString = messageMap.get("apply_time").toString();
					Map numMap = dataBaseControl.getOneResultSet("select t.phone from hr_base_info t where t.id=(select t.pid from Fi_advance t where t.id=?)", new Object[]{id});
					if(numMap.size()!=0){
						num = numMap.get("phone").toString();
					}
					if(debt>0)
						infor = "您"+apply_timeString+"的暂支还欠"+debt+"元！";
					if(debt==0)
						infor = "您"+apply_timeString+"的暂支还欠已还清！";
					m.sendGFDZ(num,infor);
				}
				if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
				dataBaseControl.endTransaction();
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
		}else{
			Page pageAd=searchShow(request,1,1);
			Page pageApp=searchApp(request,1,10);
			Page pageBak=searchBak(request,1,10);
			
			request.setAttribute("recordAd", ((ArrayList)pageAd.getThisPageElements()).get(0));
			request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
			request.setAttribute("recordBak", (ArrayList)pageBak.getThisPageElements());
		}
		
		request.setAttribute("editMod", "back");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		
		return "/fi/advance/back.jsp";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Fi_advance WHERE ID in (?)", new Object[]{s_id});
		
		return "/fi/advance/Fi_advance!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("advance_type",codeTableUtil.getCodeMap("advance_type"));
		request.setAttribute("apply_reason",codeTableUtil.getCodeMap("apply_reason"));
		request.setAttribute("advance_appstate",codeTableUtil.getCodeMap("advance_appstate"));
		request.setAttribute("fukuan",codeTableUtil.getCodeMap("fukuan_unit"));
	}

}