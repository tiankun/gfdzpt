package com.web.gm.travel;

import java.math.BigDecimal;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.record.chart.BeginRecord;
import org.phprpc.PHPRPC_Client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.asm.Type;
import com.map.Fi_advance;
import com.map.Fi_fina_account_dtl;
import com.map.Gm_travel;
import com.map.Gm_travel_approval;
import com.map.Gm_travel_route;
import com.map.Gm_travel_sub;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;
import com.web.common.BuildDDLUtil;
import com.zsc.Mas;

public class Gm_travelAction extends BaseAction {
	private DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
		
	//出差列表
	public String travelList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		Page page = null;
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("mulselect")){
			page=searchSelf(request,pageNo,pageSize);
		}
		else{
			page=searchTravel(request,pageNo,pageSize);
		}
		
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
		
		request.setAttribute("searchMap", searchMap);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/gm/travel/travelSelect.jsp";
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("mulselect"))
			return "/gm/travel/travelMulSelect.jsp";
		return "/gm/travel/travelList.jsp";
	}
	
	//查看详情
	public String showDtl(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page pageTravel=searchShow(request,1,1);
		Page pageApp=searchApp(request,1,20);
		Page pageSub=searchSub(request,1,20);
		Page pageRoute=searchRoute(request,1,20);
		Page pageAdv=searchAdv(request, 1, 1);
		
		request.setAttribute("recordTravel", ((ArrayList)pageTravel.getThisPageElements()).get(0));
		request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
		request.setAttribute("recordSub", (ArrayList)pageSub.getThisPageElements());
		request.setAttribute("recordRoute", (ArrayList)pageRoute.getThisPageElements());
		if(((ArrayList)pageAdv.getThisPageElements()).size()!=0)
			request.setAttribute("recordAdv", ((ArrayList)pageAdv.getThisPageElements()).get(0));
		
		request.setAttribute("btnDisplay", "display:none");
		request.setAttribute("editMod", "show");
		buildDDL(request);
		return "/gm/travel/travelDtl.jsp";
	}
	
	//搜索出差表
	private Page searchTravel(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String duty_id = (String) myuser.get("duty_id");
		String dept_id = branchid.toString();
		String p_id= myuser.get("base_info_id").toString();
		Map searchMap=getParameterMap(request);
		String sqlDept = "";
		if(!dept_id.equals("1")){
			if(duty_id.equals("1")){	//职员只可查看自己
				searchMap.put("p_id", p_id);
			}
			else if(duty_id.equals("4")){	//崔总可查看除以下部门的
				sqlDept = " and gt.deptid not in (1,4,9,11,12) ";
			}
			else{
				searchMap.put("dept_id", dept_id);	//部门经理查看本部门的
				sqlDept = "/~ and gt.deptid={dept_id} ~/";
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
		
		String sql="select gt.*,hb.name person_name," +
				"(select mb.branchname from mrbranch mb where mb.id=gt.deptid) dept_name " +
				"from gm_travel gt,hr_base_info hb where 1=1 and gt.personid=hb.id(+) " +
				"/~ and gt.id={id} ~/" +
				"/~ and gt.personid={p_id} ~/" +
				"/~ and gt.appstate={appstate} ~/" +
				"/~ and hb.name like '%[personname]%' ~/" +
				"/~ and gt.applicate_time >= to_date({apptime1},'yyyy-MM-dd') ~/" +
				"/~ and gt.applicate_time <= to_date({apptime2},'yyyy-MM-dd') ~/"+sqlDept+
				"order by gt.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//搜索出差表
	private Page searchShow(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select gt.*,hb.name person_name," +
				"(select mb.branchname from mrbranch mb where mb.id=gt.deptid) dept_name " +
				"from gm_travel gt,hr_base_info hb where 1=1 and gt.personid=hb.id(+) " +
				"/~ and gt.id={id} ~/" +
				"/~ and gt.personid={p_id} ~/" +
				"/~ and gt.appstate={appstate} ~/" +
				"/~ and hb.name like '%[personname]%' ~/" +
				"/~ and gt.applicate_time >= to_date({apptime1},'yyyy-MM-dd') ~/" +
				"/~ and gt.applicate_time <= to_date({apptime2},'yyyy-MM-dd') ~/"+
				"order by gt.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//搜索出差表
	private Page searchSelf(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map myuser=(Map)request.getSession().getAttribute("user");
		String p_id= myuser.get("base_info_id").toString();
		Map searchMap=getParameterMap(request);
		searchMap.put("p_id", p_id);
		
		String sql="select gt.b_time,gt.e_time,gt.deptid,gt.id,gt.personid,hb.name person_name," +
				"(select mb.branchname from mrbranch mb where mb.id=gt.deptid) dept_name " +
				"from gm_travel gt,hr_base_info hb where 1=1 and gt.personid=hb.id(+) " +
				"/~ and gt.id={id} ~/" +
				"/~ and gt.personid={p_id} ~/" +
				"/~ and hb.name like '%[personname]%' ~/" +
				"/~ and gt.applicate_time >= to_date({apptime1},'yyyy-MM-dd') ~/" +
				"/~ and gt.applicate_time <= to_date({apptime2},'yyyy-MM-dd') ~/" +
				"and gt.appstate='2' and gt.accflag='0' "+
				"order by gt.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//搜索意见表
	private Page searchApp(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*," +
				"(select hb.name from hr_base_info hb where hb.id=t.auditid) person_name from gm_travel_approval t where 1=1 /~ and t.travelid={id} ~/" +
				"order by t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
		
	//搜索随行表
	private Page searchSub(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*," +
				"(select hb.name from hr_base_info hb where hb.id=t.suiteid) person_name," +
				"(select mb.branchname from mrbranch mb where mb.id=(select hb.dept_id from hr_base_info hb where hb.id=t.suiteid)) dept_name " +
				"from gm_travel_sub t where 1=1 " +
				"/~ and t.travelid={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//搜索路线表
	private Page searchRoute(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*," +
				"(select sa.fullname from sys_area sa where sa.id = t.district) areaname," +
				"(select sa.name from sys_area sa where sa.id = t.province) proname," +
				"(select sa.name from sys_area sa where sa.id = t.city) cityname," +
				"(select sa.name from sys_area sa where sa.id = t.district) disname," +
				"(select c.dmzmc from CODE c where c.dmlb = 'vehicles' and c.dmz = t.vehicles) vehicle from gm_travel_route t where 1=1 /~ and t.travelid={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//搜索申请金额
	private Page searchAdv(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.* from fi_advance t where 1=1 /~ and t.travelid={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	//添加出差信息
	public String addDtl(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		Map map = dataBaseControl.getOneResultSet("select * from mrbranch t where t.id = ? ", new Object[]{branchid});
		String IsPostBack = request.getParameter("IsPostBack");
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String applicate_time = dFormat.format(today);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Long parentid = dataBaseControl.getSeq(Gm_travel.class);
			try {
				dataBaseControl.beginTransaction();
				int j = dataBaseControl.insertByBean(getMapObject(request),parentid);
				
				//随行人员插入
				String subIds = request.getParameter("subids");
				if(!(subIds==null||subIds.equals(""))){
					JSONArray jsonArr = JSON.parseArray(subIds);
					Gm_travel_sub sub = new Gm_travel_sub();
					for (int i = 0; i < jsonArr.size(); i++) {
						sub.setTravelid(parentid);
						sub.setSuiteid(Long.parseLong((String) jsonArr.getJSONObject(i).get("ID")));
						dataBaseControl.insertByBean(sub);
					}
				}
				
				//出差路线插入
				String[] rtSaves = request.getParameterValues("rtSave");
				Gm_travel_route route = null;
				for(int i=0;i<rtSaves.length;i++){
					List<Gm_travel_route> routeList = JSON.parseArray("["+rtSaves[i]+"]", Gm_travel_route.class);
					if(routeList.size()!=0){
						route = (Gm_travel_route)routeList.get(0);
						route.setTravelid(parentid);
						dataBaseControl.insertByBean(route);
					}
				}
				
				if(j==1){
					/* 下一阶段审批人 */
					String nextapproverid = "";
					String appstate = "";
					String duty_id = "";
					Long travelid = parentid;
					Map pMap = dataBaseControl.getOneResultSet("select t.personid,t.deptid from gm_travel t where t.id=?", new Object[]{travelid});
					String p_id = pMap.get("personid").toString();
					String dept_id = pMap.get("deptid").toString();
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
					int s = dataBaseControl.executeSql("update gm_travel t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,travelid});
					
					/* 发短信 */
					//初始化
					String num="";
					PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
					Mas m = (Mas) client.useService(Mas.class);
					String infor = "";
					
					if(s==1){
						List plist = dataBaseControl.getOutResultSet("select * from message t where t.pid=?", new Object[]{nextapproverid});
						if(plist != null && !plist.isEmpty())
						{
							num = ""+((Map)plist.toArray()[0]).get("num");
						}
						
						infor="您有一条出差待审批请求，请您审批！";
						m.sendGFDZ(num,infor);
					}
					
					/* 生成暂支申请 */
					String apply_money = request.getParameter("apply_money");
					if(apply_money!=null){
						Fi_advance advance = new Fi_advance();
						advance.setPid(Long.parseLong(p_id));
						advance.setDeptid(Long.parseLong(dept_id));
						advance.setApply_time((new java.sql.Date((fmt.parse(applicate_time).getTime()))));
						advance.setApply_money(BigDecimal.valueOf(Double.parseDouble(apply_money)));
						advance.setChn(request.getParameter("chn"));
						advance.setAdvance_type("2");
						advance.setApply_reason("1");
						advance.setTravelid(travelid);
						advance.setPrint(BigDecimal.valueOf(Double.parseDouble("0")));
						advance.setNextapproverid(Long.parseLong(nextapproverid));
						advance.setRemark(applicate_time+"出差");
						
						s = dataBaseControl.insertByBean(advance);
						
						/* 发短信 */
						if(s==1){
							List plist = dataBaseControl.getOutResultSet("select * from message t where t.pid=?", new Object[]{nextapproverid});
							if(plist != null && !plist.isEmpty())
							{
								num = ""+((Map)plist.toArray()[0]).get("num");
							}
							
							infor="您有一条暂支待审批请求，请您审批！";
							m.sendGFDZ(num,infor);
						}
					}
				}
				dataBaseControl.endTransaction();
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		
		buildDDL(request);
		request.setAttribute("applicate_time", applicate_time);
		request.setAttribute("dept_name", map.get("branchname"));
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/gm/travel/travelDtl.jsp";
	}
	
	public String editDtl(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String id = request.getParameter("id");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			try {
				dataBaseControl.beginTransaction();
				int i =dataBaseControl.updateByBean(getMapObject(request));
				//出差路线插入
				Gm_travel_route route;
				String[] rtSaves = request.getParameterValues("rtSave");
				for(i=0;i<rtSaves.length;i++){
					List<Gm_travel_route> routeList = JSON.parseArray("["+rtSaves[i]+"]", Gm_travel_route.class);
					if(routeList.size()!=0){
						route = (Gm_travel_route)routeList.get(0);
						route.setTravelid(Long.parseLong(id));
						if(route.getId()==null){
							dataBaseControl.insertByBean(route);
						}else{
							dataBaseControl.updateByBean(route);
						}
					}
				}
				
				//随行人员插入
				String subIds = request.getParameter("subids");
				if(!(subIds.equals("")||subIds==null)){
					//删了再插
					dataBaseControl.executeSql("delete from gm_travel_sub t where t.travelid=?", new Object[]{id});
					JSONArray jsonArr = JSON.parseArray(subIds);
					Gm_travel_sub sub = new Gm_travel_sub();
					for (i = 0; i < jsonArr.size(); i++) {
						sub.setTravelid(Long.parseLong(id));
						sub.setSuiteid(Long.parseLong((String) jsonArr.getJSONObject(i).get("ID")));
						
						dataBaseControl.insertByBean(sub);
					}
				}
				
				if(i==1){
					//打回再修改
					/*	写入下一步审核人员id	*/
					String nextapproverid = "";
					String appstate = "";
					String duty_id = "";
					Map pMap = dataBaseControl.getOneResultSet("select t.personid,t.deptid from gm_travel t where t.id=?", new Object[]{id});
					String p_id = pMap.get("personid").toString();
					String dept_id = pMap.get("deptid").toString();
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
					int s = dataBaseControl.executeSql("update gm_travel t set t.nextapproverid=?,t.appstate=? where t.id=?", new Object[]{nextapproverid,appstate,id});
					
					/* 发短信 */
					//初始化
					String num="";
					PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
					Mas m = (Mas) client.useService(Mas.class);
					String infor = "";
					
					if(s==1){
						List plist = dataBaseControl.getOutResultSet("select * from message t where t.pid=?", new Object[]{nextapproverid});
						if(plist != null && !plist.isEmpty())
						{
							num = ""+((Map)plist.toArray()[0]).get("num");
						}
						
						infor="您有一条出差待审批请求，请您审批！";
						m.sendGFDZ(num,infor);
					}
					
					/* 生成暂支申请 */
					String apply_money = request.getParameter("apply_money");
					if(apply_money!=null){
						String chn = request.getParameter("chn");
						
						s = dataBaseControl.executeSql("update fi_advance t set t.apply_money=?,t.chn=?,t.nextapproverid=? where t.travelid=?", new Object[]{apply_money,chn,nextapproverid,id});
						
						/* 发短信 */
						if(s==1){
							List plist = dataBaseControl.getOutResultSet("select * from message t where t.pid=?", new Object[]{nextapproverid});
							if(plist != null && !plist.isEmpty())
							{
								num = ""+((Map)plist.toArray()[0]).get("num");
							}
							
							infor="您有一条出差待审批请求，请您审批！";
							m.sendGFDZ(num,infor);
						}
					}
				}
				dataBaseControl.endTransaction();
				request.setAttribute("operationSign", "closeDG_refreshW();");
			} catch (Exception e) {
				dataBaseControl.endTransaction();
			}
		}
		else{
			Page pageTravel=searchShow(request,1,1);
			Page pageApp=searchApp(request,1,20);
			Page pageSub=searchSub(request,1,20);
			Page pageRoute=searchRoute(request,1,20);
			Page pageAdv=searchAdv(request, 1, 1);
			
			request.setAttribute("recordTravel", ((ArrayList)pageTravel.getThisPageElements()).get(0));
			request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
			request.setAttribute("recordSub", (ArrayList)pageSub.getThisPageElements());
			request.setAttribute("recordRoute", (ArrayList)pageRoute.getThisPageElements());
			request.setAttribute("routeC", pageRoute.getThisPageElements().size());
			if(((ArrayList)pageAdv.getThisPageElements()).size()!=0)
				request.setAttribute("recordAdv", ((ArrayList)pageAdv.getThisPageElements()).get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/gm/travel/editDtl.jsp";
	}
	
	public String delRoute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from gm_travel_route t where t.id = ? ", new Object[]{request.getParameter("id")});
		dataBaseControl.executeSql("DELETE FROM gm_travel_route WHERE ID in (?)", new Object[]{request.getParameter("id")});
		return "/gm/travel/Gm_travel!editDtl.do?id="+map.get("travelid");
	}
	
	public void getVehicles(HttpServletRequest request , HttpServletResponse response) throws Exception {
		String sql = "select t.dmz,t.dmzmc from CODE t where t.dmlb = 'vehicles' order by t.plsx ";
		List<Map<String,Object>> vehiclesList = dataBaseControl.getOutResultSet(sql, null);		
		String jsonStr = JSON.toJSONString(vehiclesList);
		setAjaxInfo(response,jsonStr);
	}
	
	/**
	 * 审批
	 */
	public String appr(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Map myuser=(Map)request.getSession().getAttribute("user");
		String dutyid = myuser.get("duty_id").toString();
		String deptid = myuser.get("branchid").toString();
		Date today = new Date();
		String apptime = dFormat.format(today);
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String travelid = request.getParameter("travelid");
			
			Map travelMap = dataBaseControl.getOneResultSet("select t.appstate from gm_travel t where t.id=?", new Object[]{travelid});
			String travel_app = travelMap.get("appstate").toString();
			
			String departid = request.getParameter("departid");
			String auditid = request.getParameter("auditid");
			String appstate = request.getParameter("appstate");
			String appopinion = request.getParameter("appopinion");
			
			Gm_travel_approval approval = new Gm_travel_approval();
			
			approval.setTravelid(Long.parseLong(travelid));
			approval.setAuditid(Long.parseLong(auditid));
			approval.setDepartid(Long.parseLong(departid));
			approval.setApptime((new java.sql.Date((fmt.parse(apptime)).getTime())));
			approval.setAppopinion(appopinion);
			
			try {
				dataBaseControl.beginTransaction();
				int i =dataBaseControl.insertByBean(approval);
				if(i==1){
					/*	写入下一步审核人员id	*/
					if(travel_app.equals("0")){//部门经理审批
						if(appstate.equals("1")){
							if(deptid.equals("4"))//软件部
								dataBaseControl.executeSql("update gm_travel t set t.nextapproverid=?,t.appstate=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=3 and t.hr_type=3", null)).get("id").toString(),travelid});
							if(deptid.equals("9"))//成都
								dataBaseControl.executeSql("update gm_travel t set t.nextapproverid=0,t.appstate=2 where t.id=?", new Object[]{travelid});
							if(!(deptid.equals("4")||deptid.equals("9")))
								dataBaseControl.executeSql("update gm_travel t set t.nextapproverid=?,t.appstate=1 where t.id=?", new Object[]{(dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.duty_id=4 and t.hr_type=3", null)).get("id").toString(),travelid});
						}else{
							dataBaseControl.executeSql("update gm_travel t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,travelid});
						}
					}else{
						if(appstate.equals("1"))
							dataBaseControl.executeSql("update gm_travel t set t.appstate=2,t.nextapproverid=0 where t.id=?", new Object[]{travelid});
						if(!appstate.equals("1"))
							dataBaseControl.executeSql("update gm_travel t set t.appstate=?,t.nextapproverid=0 where t.id=?", new Object[]{appstate,travelid});
					}
					/* 发短信 */
					Map messageMap = dataBaseControl.getOneResultSet("select t.appstate,t.nextapproverid from gm_travel t where t.id=?", new Object[]{travelid});
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
							
							infor="您有一条出差待审批请求，请您审批！";
							m.sendGFDZ(num,infor);
						}
						
						/* 短信提醒审批结果 */
						String state = messageMap.get("appstate").toString();
						if(state.equals("2")||state.equals("-1")||state.equals("-2")){
							Map numMap = dataBaseControl.getOneResultSet("select t.phone from hr_base_info t where t.id=(select t.personid from gm_travel t where t.id=?) and t.hr_type=3", new Object[]{travelid});
							if(numMap.size()!=0){
								num = numMap.get("phone").toString();
							}
							if(state.equals("2"))
								infor="您的出差请求审批已通过！";
							if(state.equals("-1"))
								infor="您的出差请求被打回！";
							if(state.equals("-2"))
								infor="您的出差请求审批不通过！";
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
			Page pageRoute=searchRoute(request,1,10);
			
			request.setAttribute("recordTravel", ((ArrayList)pageTravel.getThisPageElements()).get(0));
			request.setAttribute("recordApp", (ArrayList)pageApp.getThisPageElements());
			request.setAttribute("recordSub", (ArrayList)pageSub.getThisPageElements());
			request.setAttribute("recordRoute", (ArrayList)pageRoute.getThisPageElements());
		}
		
		request.setAttribute("apptime", apptime);
		request.setAttribute("editMod", "appr");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/gm/travel/appr.jsp";
	}
	
	public String delDtl(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String id = request.getParameter("id");
		dataBaseControl.beginTransaction();
		dataBaseControl.executeSql("delete from gm_travel t where t.id=?", new Object[]{id});
		dataBaseControl.executeSql("delete from gm_travel_sub t where t.travelid=?", new Object[]{id});
		dataBaseControl.executeSql("delete from gm_travel_route t where t.travelid=?", new Object[]{id});
		dataBaseControl.executeSql("delete from fi_advance t where t.travelid=?", new Object[]{id});
		dataBaseControl.endTransaction();
		return "/gm/travel/Gm_travel!travelList.do";
	}
	
	/**
	 * 保存提交
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String sub(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String id = request.getParameter("id");
		dataBaseControl.executeSql("update gm_travel t set t.subflag='1' where t.id=?", new Object[]{id});
		return "/gm/travel/Gm_travel!travelList.do";
	}
	
	@SuppressWarnings("static-access")
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		Map<Object,Object> mrbranch = BuildDDLUtil.getInstance().getDataMap(null,new String[]
				{"select t.id,t.branchname from MRBRANCH t order by t.id ","id","branchname"});
		request.setAttribute("mrbranch",mrbranch); 
		
		Map<Object,Object> personMap = BuildDDLUtil.getInstance().getDataMap(null,new String[]
				{"select t.id,t.name from hr_base_info t order by id ","id","name"});	
		request.setAttribute("personMap",personMap); 

		Map<Object,Object> apprCancel = BuildDDLUtil.getInstance().getDataMap(null,new String[] {
				"select co.dmzmc,co.DMZ from code co where co.dmlb='isPassByApproval' order by co.plsx ",
				"dmz", "dmzmc" });
		request.setAttribute("apprCancel",apprCancel); 
		
		Map<Object,Object> submitStatus = BuildDDLUtil.getInstance().getDataMap(null,new String[] {
				"select co.dmzmc,co.DMZ from code co where co.dmlb='submitStatus' order by co.plsx ",
				"dmz", "dmzmc" });
		request.setAttribute("submitStatus",submitStatus); 
		
		request.setAttribute("vehicleTool",codeTableUtil.getCodeMap("vehicles")); 
		request.setAttribute("travelesti",codeTableUtil.getCodeMap("travelesti"));
		request.setAttribute("travel_appstate",codeTableUtil.getCodeMap("travel_appstate"));
	}
	

}