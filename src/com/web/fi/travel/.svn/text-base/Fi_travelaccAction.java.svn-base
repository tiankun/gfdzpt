package com.web.fi.travel;

import java.io.PrintWriter;
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
import org.phprpc.PHPRPC_Client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.map.Fi_travelacc;
import com.map.Fi_travelacc_appr;
import com.map.Fi_travelacc_dtl;
import com.map.Fi_travelacc_sub;
import com.map.Gm_travel;
import com.map.Gm_travel_approval;
import com.map.Gm_travel_route;
import com.map.Gm_travel_sub;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Fi_travelaccAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=searchAcc(request,pageNo,pageSize);
		
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
			return "/fi/travel/fi_travelaccSelect.jsp";
		return "/fi/travel/fi_travelaccList.jsp";
	}
	////////////////////////////// 出差报账管理统计
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
				sqlDept = " and fc.deptid not in (1,4,9,11,12) ";
			}
			else{
				searchMap.put("dept_id", dept_id);	//部门经理查看本部门的
				sqlDept = "/~ and fc.deptid={dept_id} ~/";
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
		
		String sql="select fc.*,hb.name person_name," +
				"(select mb.branchname from mrbranch mb where mb.id=fc.deptid) dept_name," +
				"(select gt.b_time from gm_travel gt where gt.id=fc.travelid) b_time," +
				"(select c.dmzmc from code c where c.dmlb='travelacc_appstate' and c.dmz=fc.appstate) spzt,"+
				"(select pr.name from pr_project pr where pr.id=fc.proj_id) proj_name from Fi_travelacc fc,hr_base_info hb where 1=1 and fc.p_id=hb.id(+)" +
				" /~ and fc.deptid={mid} ~/" +
				" /~ and fc.id={id} ~/" +
				"/~ and fc.p_id={p_id} ~/" +
				"/~ and fc.appstate={appstate} ~/"+
				"/~ and hb.name like '%[personname]%' ~/" +
				"/~ and fc.reim_time >= to_date({app_date1},'yyyy-MM-dd') ~/" +
				"/~ and fc.reim_time <= to_date({app_date2},'yyyy-MM-dd') ~/"+sqlDept;
			
			defaultList(request,response,new StringBuffer(sql),"fc.id desc"); 
			
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
				sqlDept = " and fc.deptid not in (1,4,9,11,12) ";
			}
			else{
				searchMap.put("dept_id", dept_id);	//部门经理查看本部门的
				sqlDept = "/~ and fc.deptid={dept_id} ~/";
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
		
		String sql="select sum(fc.totalcost)sum,count(*) count" +
				" from Fi_travelacc fc,hr_base_info hb where 1=1 and fc.p_id=hb.id(+)" +
				" /~ and fc.id={id} ~/" +
				" /~ and fc.deptid={mid} ~/" +
				"/~ and fc.p_id={p_id} ~/" +
				"/~ and fc.appstate={appstate} ~/"+
				"/~ and hb.name like '%[personname]%' ~/" +
				"/~ and fc.reim_time >= to_date({app_date1},'yyyy-MM-dd') ~/" +
				"/~ and fc.reim_time <= to_date({app_date2},'yyyy-MM-dd') ~/"+sqlDept;
		
			
			XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
			Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
			Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
			
			String jsonStr = JSON.toJSONString(map);
			setAjaxInfo(response,jsonStr);
		
		}
	//////////////////////////////
	public String showDtl(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page pageAcc=searchShow(request,1,1);
		Page pageApp=searchApp(request,1,10);
		Page pageSub=searchSub(request,1,10);
		Page pageDtl=searchDtl(request,1,10);
		
		request.setAttribute("recordAcc", ((ArrayList)pageAcc.getThisPageElements()).get(0));
		request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
		request.setAttribute("recordSub", (ArrayList)pageSub.getThisPageElements());
		request.setAttribute("recordDtl", (ArrayList)pageDtl.getThisPageElements());
		request.setAttribute("btnDisplay", "display:none");
		request.setAttribute("editMod", "show");
		buildDDL(request);
		return "/fi/travel/showDtl.jsp";
	}
	
	//打印
	public String print(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String idString = request.getParameter("id");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int count = Integer.parseInt((dataBaseControl.getOneResultSet("select print from Fi_travelacc where id=?", new Object[]{idString})).get("print").toString());
			count++;
			dataBaseControl.executeSql("update Fi_travelacc set print=? where id=?", new Object[]{count,idString});
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		Page pageAcc=searchShow(request,1,1);
		Page pageDtl=searchDtl(request,1,10);
		List dmList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from fi_travelacc_appr t,hr_base_info hb,fi_travelacc ft where ft.id=t.accid(+) and t.auditid = hb.id(+) and hb.dept_id = ft.deptid and ft.id=? order by t.id desc", new Object[]{idString}, 1);
		List cpaList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from fi_travelacc_appr t,hr_base_info hb where t.auditid=hb.id(+) and t.departid=6 and hb.duty_id=1 and t.accid=? order by t.id desc", new Object[]{idString}, 1);
		List fmList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from fi_travelacc_appr t,hr_base_info hb where t.auditid=hb.id(+) and t.departid=6 and hb.duty_id=2 and t.accid=? order by t.id desc", new Object[]{idString}, 1);
		List gmList = dataBaseControl.getTopResultSet("select hb.name,t.appopinion,t.apptime from fi_travelacc_appr t,hr_base_info hb where t.auditid=hb.id(+) and hb.duty_id!=1 and hb.duty_id!=2 and t.accid=? order by t.id desc", new Object[]{idString}, 1);
		
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
		String subCountString = (dataBaseControl.getOneResultSet("select count(id) subCount from fi_travelacc_sub where accid=?", new Object[]{idString}).get("subCount")).toString();
		
		request.setAttribute("subCount", subCountString);
		request.setAttribute("dmMap", dmMap);
		request.setAttribute("fmMap", fmMap);
		request.setAttribute("gmMap", gmMap);
		request.setAttribute("cpaMap", cpaMap);
		request.setAttribute("recordAcc", ((ArrayList)pageAcc.getThisPageElements()).get(0));
		request.setAttribute("recordDtl", (ArrayList)pageDtl.getThisPageElements());
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/travel/print.jsp";
	}
	
	private Page searchAcc(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
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
				sqlDept = " and fc.deptid not in (1,4,9,11,12) ";
			}
			else{
				searchMap.put("dept_id", dept_id);	//部门经理查看本部门的
				sqlDept = "/~ and fc.deptid={dept_id} ~/";
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
		
		String sql="select fc.*,hb.name person_name," +
				"(select mb.branchname from mrbranch mb where mb.id=fc.deptid) dept_name," +
				"(select gt.b_time from gm_travel gt where gt.id=fc.travelid) b_time," +
				"(select pr.name from pr_project pr where pr.id=fc.proj_id) proj_name from Fi_travelacc fc,hr_base_info hb where 1=1 and fc.p_id=hb.id(+)" +
				" /~ and fc.id={id} ~/" +
				"/~ and fc.p_id={p_id} ~/" +
				"/~ and fc.appstate={appstate} ~/"+
				"/~ and hb.name like '%[personname]%' ~/" +
				"/~ and fc.reim_time >= to_date({reim_time1},'yyyy-MM-dd') ~/" +
				"/~ and fc.reim_time <= to_date({reim_time2},'yyyy-MM-dd') ~/"+sqlDept+
				"order by fc.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchShow(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		String sql="select fc.*,hb.name person_name," +
				"(select hb.name from hr_base_info hb where hb.id=fc.zf_person) zf_pname," +
				"(select mb.branchname from mrbranch mb where mb.id=fc.deptid) dept_name," +
				"(select gt.b_time from gm_travel gt where gt.id=fc.travelid) b_time," +
				"(select pr.name from pr_project pr where pr.id=fc.proj_id) proj_name," +
				"(select gt.reason from gm_travel gt where gt.id=fc.travelid) gtreason from Fi_travelacc fc,hr_base_info hb where 1=1 and fc.p_id=hb.id(+)" +
				" /~ and fc.id={id} ~/" +
				"/~ and fc.p_id={p_id} ~/" +
				"/~ and fc.appstate={appstate} ~/"+
				"/~ and hb.name like '%[personname]%' ~/" +
				"/~ and fc.applicate_time >= to_date({apptime1},'yyyy-MM-dd') ~/" +
				"/~ and fc.applicate_time <= to_date({apptime2},'yyyy-MM-dd') ~/"+
				"order by fc.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchApp(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*," +
				"(select hb.name from hr_base_info hb where hb.id=t.auditid) person_name from Fi_travelacc_appr t where 1=1 /~ and t.accid={id} ~/ order by t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchSub(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*," +
				"(select hb.name from hr_base_info hb where hb.id=t.suiteid) person_name," +
				"(select mb.branchname from mrbranch mb where mb.id=(select hb.dept_id from hr_base_info hb where hb.id=t.suiteid)) dept_name  from Fi_travelacc_sub t where 1=1 /~ and t.accid={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	private Page searchDtl(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*," +
				"(select sa.fullname from sys_area sa where sa.id = t.district) areaname," +
				"(select sa.name from sys_area sa where sa.id = t.province) proname," +
				"(select sa.name from sys_area sa where sa.id = t.city) cityname," +
				"(select sa.name from sys_area sa where sa.id = t.district) disname," +
				"(select c.dmzmc from CODE c where c.dmlb = 'vehicles' and c.dmz = t.vehicles) vehicle from fi_travelacc_dtl t where 1=1 /~ and t.accid={id} ~/" +
				" order by t.r_time desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String reim_time = dFormat.format(today);
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Long parentid = dataBaseControl.getSeq(Fi_travelacc.class);
			
			try {
				dataBaseControl.beginTransaction();
				//主表插入
				int j =dataBaseControl.insertByBean(getMapObject(request),parentid);
				
				//报账细节插入
				Fi_travelacc_dtl dtl;
				String[] rtSaves = request.getParameterValues("rtSave");
				for(int i=0;i<rtSaves.length;i++){
					List<Fi_travelacc_dtl> dtlList = JSON.parseArray("["+rtSaves[i]+"]", Fi_travelacc_dtl.class);
					if(dtlList.size()!=0){
						dtl = (Fi_travelacc_dtl)dtlList.get(0);
						dtl.setAccid(parentid);
						dataBaseControl.insertByBean(dtl);
					}
				}
				
				//随行人员插入
				String subIds = request.getParameter("subids");
				if(!(subIds==null||subIds.equals(""))){
					JSONArray jsonArr = JSON.parseArray(subIds);
					Fi_travelacc_sub sub = new Fi_travelacc_sub();
					for (int i = 0; i < jsonArr.size(); i++) {
						sub.setAccid(parentid);
						sub.setSuiteid(Long.parseLong((String) jsonArr.getJSONObject(i).get("ID")));
						dataBaseControl.insertByBean(sub);
					}
				}
				if(j==1){
					
					/* 下一阶段审批人 */
					String nextapproverid = "";
					String appstate = "";
					String duty_id = "";
					Long accid = parentid;
					Map pMap = dataBaseControl.getOneResultSet("select t.p_id,t.deptid from fi_travelacc t where t.id=?", new Object[]{accid});
					String p_id = pMap.get("p_id").toString();
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
					//当为客户部或成都公司时，提交后直接交由相应领导审批
					if((dept_id.equals("9")||dept_id.equals("10"))&&(dutyString.equals("1")||dutyString.equals("2")))
						appstate = "3";
					int s = dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,accid});
					
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
						
						String infor="您有一条出差报账待审批请求，请您审批！";
						m.sendGFDZ(num,infor);
					}
					request.setAttribute("operationSign", "closeDG_refreshW();");
				}
				dataBaseControl.endTransaction();
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
		}
		buildDDL(request);
		request.setAttribute("reim_time", reim_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/fi/travel/fi_travelaccDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String id = request.getParameter("id");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			try {
				dataBaseControl.beginTransaction();
				//主表插入
				int j = dataBaseControl.updateByBean(getMapObject(request));
				//报账细节插入
				Fi_travelacc_dtl dtl;
				String[] rtSaves = request.getParameterValues("rtSave");
				for(int i=0;i<rtSaves.length;i++){
					List<Fi_travelacc_dtl> dtlList = JSON.parseArray("["+rtSaves[i]+"]", Fi_travelacc_dtl.class);
					if(dtlList.size()!=0){
						dtl = (Fi_travelacc_dtl)dtlList.get(0);
						dtl.setAccid(Long.parseLong(id));
						if(dtl.getId()==null){
							dataBaseControl.insertByBean(dtl);
						}else{
							dataBaseControl.updateByBean(dtl);
						}
						
					}
				}
				
				//随行人员插入
				String subIds = request.getParameter("subids");
				if(!(subIds.equals("")||subIds==null)){
					dataBaseControl.executeSql("delete from fi_travelacc_sub t where t.accid=?", new Object[]{id});
					JSONArray jsonArr = JSON.parseArray(subIds);
					Fi_travelacc_sub sub = new Fi_travelacc_sub();
					for (int i = 0; i < jsonArr.size(); i++) {
						sub.setAccid(Long.parseLong(id));
						sub.setSuiteid(Long.parseLong((String) jsonArr.getJSONObject(i).get("ID")));
						dataBaseControl.insertByBean(sub);
						
					}
				}
				
				if(j==1){
					//打回再修改
					String nextapproverid = "";
					String appstate = "";
					String duty_id = "";
					Map pMap = dataBaseControl.getOneResultSet("select t.p_id,t.deptid from fi_travelacc t where t.id=?", new Object[]{id});
					String p_id = pMap.get("p_id").toString();
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
					//当为客户部或成都公司时，提交后直接交由相应领导审批
					if((dept_id.equals("9")||dept_id.equals("10"))&&(dutyString.equals("1")||dutyString.equals("2")))
						appstate = "3";
					int s = dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,id});
					
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
						
						String infor="您有一条出差报账待审批请求，请您审批！";
						m.sendGFDZ(num,infor);
					}
					request.setAttribute("operationSign", "closeDG_refreshW();");
				}
				dataBaseControl.endTransaction();
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
			
		}else{
			Page pageAcc=searchShow(request,1,1);
			Page pageApp=searchApp(request,1,10);
			Page pageSub=searchSub(request,1,10);
			Page pageDtl=searchDtl(request,1,10);
			
			request.setAttribute("recordAcc", ((ArrayList)pageAcc.getThisPageElements()).get(0));
			request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
			request.setAttribute("recordSub", (ArrayList)pageSub.getThisPageElements());
			request.setAttribute("recordDtl", (ArrayList)pageDtl.getThisPageElements());
			request.setAttribute("dtlC", pageDtl.getThisPageElements().size());
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/travel/editDtl.jsp";
	}
	
	//审批
	public String appr(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		Map myuser=(Map)request.getSession().getAttribute("user");
		
		/* 计算实际消费金额，以便领导特批时计算方便  */
		String base_info_id = myuser.get("base_info_id").toString();
		if(((dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString()).equals(base_info_id)){
			String id = request.getParameter("id");
			List realList = dataBaseControl.getOutResultSet("select t.citytrans_cost+t.hotel_cost realcost from fi_travelacc_dtl t where t.accid=?", new Object[]{id});
			Double realcost = 0.0;
			for (int i = 0; i < realList.size(); i++) {
				Map realMap = (Map) realList.get(i);
				realcost += Double.parseDouble(realMap.get("realcost").toString());
			}
			request.setAttribute("cpaFlag", "cpaFlag");
			request.setAttribute("realcost", realcost);
		}
		
		/* 财务选择付款单位 */
		if((dataBaseControl.getOneResultSet("select * from hr_base_info where dept_id=6 and id=?", new Object[]{base_info_id})).size()!=0){
			request.setAttribute("fiFlag", "fiFlag");
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		
		String dutyid = myuser.get("duty_id").toString();
		String deptid = myuser.get("branchid").toString();
		String accdept = request.getParameter("accdept");
		Date today = new Date();
		String apptime = dFormat.format(today);
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String accid = request.getParameter("accid");
			
			Map travelMap = dataBaseControl.getOneResultSet("select t.appstate,t.travelid,hb.duty_id,t.p_id,t.fiflag from fi_travelacc t,hr_base_info hb where t.p_id=hb.id(+) and t.id=?", new Object[]{accid});
			String acc_app = travelMap.get("appstate").toString();
			String acc_pid = travelMap.get("p_id").toString();
			String travelid = travelMap.get("travelid")==null?"":travelMap.get("travelid").toString();
			String accduty = travelMap.get("duty_id").toString();
			String fiflag = travelMap.get("fiflag").toString();
			
			String departid = request.getParameter("departid");
			String auditid = request.getParameter("auditid");
			String appstate = request.getParameter("appstate");
			String appopinion = request.getParameter("appopinion");
			String totalcost = request.getParameter("totalcost");
			String costchn = request.getParameter("costchn");
			String cpaflag = request.getParameter("cpaflag");
			
			Fi_travelacc_appr approval = new Fi_travelacc_appr();
			
			approval.setAccid(Long.parseLong(accid));
			approval.setAuditid(Long.parseLong(auditid));
			approval.setDepartid(Long.parseLong(departid));
			approval.setApptime((new java.sql.Date((fmt.parse(apptime)).getTime())));
			approval.setAppopinion(appopinion);
			
			try {
				dataBaseControl.beginTransaction();
				int i =dataBaseControl.insertByBean(approval);
				
				String fukuan_unit = request.getParameter("fukuan_unit")==null?"":request.getParameter("fukuan_unit");
				if(!fukuan_unit.equals("")){
					dataBaseControl.executeSql("update fi_travelacc set fukuan_unit=? where id=?", new Object[]{fukuan_unit,accid});
				}
				
				if(i==1){
					/*	赵总报账	*/
					if(accduty.equals("3")){	
						if(acc_app.equals("1")){//会计审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=2 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else{//财务经理审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=0,t.appstate=4,t.fiflag=1 where t.id=?", new Object[]{accid});
							}else{
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
					}
					/*	崔总、向总报账	*/
					else if(accduty.equals("4")||accduty.equals("5")){
						if(acc_app.equals("1")){//会计审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=2 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else if(acc_app.equals("2")){//财务经理审批
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=3,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3 and t.hr_type=3", null)).get("id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else{
							if(appstate.equals("1")){
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=4,t.nextapproverid=0 where t.id=?", new Object[]{accid});
								if(!(travelid==null||travelid.equals(""))){
									dataBaseControl.executeSql("update gm_travel t set t.accflag='1' where t.id=?", new Object[]{travelid});
								}
							}
							else{
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
					}
					/*	其他人员报账	*/
					else{	
						if(acc_app.equals("0")){//部门经理审批
							if(appstate.equals("1")){
								//特例：若是会计提交，则财务经理审批后直接到崔总审批
								if(((dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString()).equals(acc_pid)){
									dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=3 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString(),accid});
								}
								else{
									dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString(),accid});
								}
							}else{
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else if(acc_app.equals("1")){//会计审批
							if(appstate.equals("1")){
								//特例：若是财务经理提交，则会计审批后直接到崔总审批
								if(((dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString()).equals(acc_pid)){
									dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=3 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString(),accid});
								}else{
									dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=2 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 6", null)).get("base_info_id").toString(),accid});
								}
							}else{
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else if(acc_app.equals("2")){//财务经理审批
							if(appstate.equals("1")){
								if(accdept.equals("4"))//软件部
									dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=3,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3 and t.hr_type=3", null)).get("id").toString(),accid});
								if(accdept.equals("9"))//成都
									dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=3,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=5 and t.hr_type=3", null)).get("id").toString(),accid});
								if(!(accdept.equals("4")||accdept.equals("9")))//公司其他部门
									dataBaseControl.executeSql("update fi_travelacc t set t.nextapproverid=?,t.appstate=3,t.fiflag=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString(),accid});
							}else{
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
						else{	//公司领导审批
							if(appstate.equals("1")){
								//客户部及成都公司交由崔、向总审批后，转由会计审批
								//fiflag为0表示财务未审核过，则经由财务流程审核
								//fiflag为1表示财务已审核过，则崔、向审批后结束
								if((accdept.equals("10")||accdept.equals("9"))&&fiflag.equals("0")) {
									dataBaseControl.executeSql("update fi_travelacc t set t.appstate=1,t.nextapproverid=? where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select su.base_info_id from sysuser su,sysuserroles sr where sr.userid=su.id(+) and sr.rolesid = 16", null)).get("base_info_id").toString(),accid});
								}else{
									dataBaseControl.executeSql("update fi_travelacc t set t.appstate=4,t.nextapproverid=0 where t.id=?", new Object[]{accid});
								}
								if(!(travelid==null||travelid.equals(""))){
									dataBaseControl.executeSql("update gm_travel t set t.accflag='1' where t.id=?", new Object[]{travelid});
								}
							}
							else{
								dataBaseControl.executeSql("update fi_travelacc t set t.appstate=?,t.nextapproverid=0,t.fiflag=0 where t.id=?", new Object[]{appstate,accid});
							}
						}
					}
					
					if(cpaflag.equals("1")){
						dataBaseControl.executeSql("update fi_travelacc t set t.totalcost=?,t.costchn=? where t.id=?", new Object[]{totalcost,costchn,accid});
					}
					
					/* 发短信 */
					Map messageMap = dataBaseControl.getOneResultSet("select t.appstate,t.nextapproverid from fi_travelacc t where t.id=?", new Object[]{accid});
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
							
							infor="您有一条出差报账待审批请求，请您审批！";
							m.sendGFDZ(num,infor);
						}
						
						/* 短信提醒审批结果 */
						String state = messageMap.get("appstate").toString();
						if(state.equals("4")||state.equals("-1")||state.equals("-2")){
							Map numMap = dataBaseControl.getOneResultSet("select t.phone from hr_base_info t where t.id=(select t.p_id from fi_travelacc t where t.id=?) and t.hr_type=3", new Object[]{accid});
							if(numMap.size()!=0){
								num = numMap.get("phone").toString();
							}
							if(state.equals("4"))
								infor="您的出差报账请求审批已通过！";
							if(state.equals("-1"))
								infor="您的出差报账请求被打回！";
							if(state.equals("-2"))
								infor="您的出差报账请求审批不通过！";
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
			Page pageTravel=searchShow(request,1,1);
			Page pageApp=searchApp(request,1,10);
			Page pageSub=searchSub(request,1,10);
			Page pageRoute=searchDtl(request,1,10);
			
			request.setAttribute("recordAcc", ((ArrayList)pageTravel.getThisPageElements()).get(0));
			request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
			request.setAttribute("recordSub", (ArrayList)pageSub.getThisPageElements());
			request.setAttribute("recordDtl", (ArrayList)pageRoute.getThisPageElements());
		}
		
		request.setAttribute("apptime", apptime);
		request.setAttribute("editMod", "appr");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/travel/appr.jsp";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		dataBaseControl.beginTransaction();
		dataBaseControl.executeSql("DELETE FROM Fi_travelacc WHERE ID=?", new Object[]{id});
		dataBaseControl.executeSql("DELETE FROM Fi_travelacc_dtl WHERE accid=?", new Object[]{id});
		dataBaseControl.executeSql("DELETE FROM Fi_travelacc_sub WHERE accid=?", new Object[]{id});
		dataBaseControl.endTransaction();
		
		return "/fi/travel/Fi_travelacc!searchList.do";
	}
	
	public void getAccDtl(HttpServletRequest request , HttpServletResponse response) throws Exception {
		String travelid = request.getParameter("travelid");
		String sql = "select t.*," +
				"(select sa.name from sys_area sa where sa.id = t.province) proname," +
				"(select sa.name from sys_area sa where sa.id = t.city) cityname," +
				"(select sa.name from sys_area sa where sa.id = t.district) disname," +
				"(select c.dmzmc from CODE c where c.dmlb = 'vehicles' and c.dmz = t.vehicles) vehicle from gm_travel_route t where t.travelid = ? order by t.id desc ";
		List<Map<String,Object>> accDtlList = dataBaseControl.getOutResultSet(sql, new Object[]{travelid});		
		String jsonStr = JSON.toJSONString(accDtlList);
		setAjaxInfo(response,jsonStr);
	}
	
	public void getSub(HttpServletRequest request , HttpServletResponse response) throws Exception {
		String travelid = request.getParameter("travelid");
		Map subMap = dataBaseControl.getOneResultSet("select t.subids from gm_travel t where t.id = ?", new Object[]{travelid});
		String subIds="";
		if(subMap.get("subids")!=null){
			subIds = subMap.get("subids").toString();
		}
		PrintWriter writer =response.getWriter();
		writer.write(subIds);
		writer.flush();
		writer.close();
	}
	
	public void getSubs(HttpServletRequest request , HttpServletResponse response) throws Exception {
		String travelid = request.getParameter("travelid");
		List<Map<String,Object>> accSubList = dataBaseControl.getOutResultSet("select hb.name from GM_TRAVEL_SUB gs,hr_base_info hb where gs.suiteid=hb.id and gs.travelid=?", new Object[]{travelid});		
		String jsonStr = JSON.toJSONString(accSubList);
		setAjaxInfo(response,jsonStr);
	}
	
	public void getStayType(HttpServletRequest request , HttpServletResponse response) throws Exception {
		String sql = "select t.dmz,t.dmzmc from CODE t where t.dmlb = 'stay_type' order by t.plsx ";
		List<Map<String,Object>> vehiclesList = dataBaseControl.getOutResultSet(sql, null);		
		String jsonStr = JSON.toJSONString(vehiclesList);
		setAjaxInfo(response,jsonStr);
	}
	
	public String addAccDtl(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		
		return null;
	}
	
	public String accDtldel(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from fi_travelacc_dtl t where t.id = ? ", new Object[]{request.getParameter("id")});
		dataBaseControl.executeSql("DELETE FROM fi_travelacc_dtl WHERE ID in (?)", new Object[]{request.getParameter("id")});
		
		request.setAttribute("editMod", "delEdit");
		return "/fi/travel/Fi_travelacc!edit.do?id="+map.get("accid");
	}
	
	//提交
	public String sub(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String id = request.getParameter("id");
		dataBaseControl.executeSql("update fi_travelacc t set t.subflag='1' where t.id=?", new Object[]{id});
		return "/fi/travel/Fi_travelacc!searchList.do";
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
			int i=dataBaseControl.executeSql("update fi_travelacc t set t.appstate='-3',t.zf_reason=?,t.zf_person=?,t.zf_time=to_date(?,'yyyy-MM-dd') where t.id=?", new Object[]{zf_reasonString,zf_personString,zf_timeString,idString});
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		
		Page pageAcc=searchShow(request,1,1);
		Page pageApp=searchApp(request,1,10);
		Page pageSub=searchSub(request,1,10);
		Page pageDtl=searchDtl(request,1,10);
		
		request.setAttribute("recordAcc", ((ArrayList)pageAcc.getThisPageElements()).get(0));
		request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
		request.setAttribute("recordSub", (ArrayList)pageSub.getThisPageElements());
		request.setAttribute("recordDtl", (ArrayList)pageDtl.getThisPageElements());
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/fi/travel/zuofei.jsp";
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("vehicleTool",codeTableUtil.getCodeMap("vehicles"));
		request.setAttribute("stayType",codeTableUtil.getCodeMap("stay_type"));
		request.setAttribute("travelacc_appstate",codeTableUtil.getCodeMap("travelacc_appstate"));
		request.setAttribute("fukuan",codeTableUtil.getCodeMap("fukuan_unit"));
	}

}