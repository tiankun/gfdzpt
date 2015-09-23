package com.web.fi.bill;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.phprpc.PHPRPC_Client;

import com.alibaba.fastjson.JSON;
import com.map.Fi_bill;
import com.map.Fi_bill_audit;
import com.map.Fi_bill_one;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Fi_bill_auditAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=deptsearch(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/bill/fi_bill_auditSelect.jsp";
		return "/fi/bill/fi_audit_billList.jsp";
	}
	
	public String searchcwList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=cwsearch(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/bill/fi_bill_auditSelect.jsp";
		return "/fi/bill/fi_cwaudit_billList.jsp";
	}
	
	public String searchkpList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=kpsearch(request,pageNo,pageSize);
		//request.setAttribute("searchMap", getParameterMap(request));
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/bill/fi_bill_auditSelect.jsp";
		return "/fi/bill/fi_kpaudit_billList.jsp";
	}
	
	
	public String searchkjList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=kjsearch(request,pageNo,pageSize);
		request.setAttribute("searchMap", getParameterMap(request));
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/bill/fi_bill_auditSelect.jsp";
		return "/fi/bill/fi_kjaudit_billList.jsp";
	}
	
	public String searchkjListDemo (HttpServletRequest request,HttpServletResponse response) throws Exception  
	{
		String sql="select t.*,b.name,m.branchname,c.dmzmc from fi_bill t,hr_base_info b,mrbranch m,code c where t.dept_id = m.id and t.p_id = b.id and c.dmlb='bill_state'and c.dmz=t.bill_state " +
				"/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
			    "/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
			    "/~ and t.bill_state={bill_state} ~/" +
			    "/~ and t.unit={unit} ~/"+
			    "/~ and t.money={money} ~/"+
			    "/~ and t.dept_id={dept_id} ~/"+
			    "/~ and m.id={mid} ~/"+
			    "/~ and t.p_id={p_id} ~/"+
				"/~ and t.p_id!={ppid} ~/"+
				"/~ and t.id={id} ~/"+	       
				
			    "/~ and b.name like '%[pname]%' ~/"+
			    " order by t.bill_state ";
		
		defaultList(request,response,new StringBuffer(sql),"t.id desc"); 
		return null;
	}
	///////////////////
	public void total(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String sql="select sum(t.money) sum,count(*) count from fi_bill t,hr_base_info b,mrbranch m,code c where t.dept_id = m.id and t.p_id = b.id and c.dmlb='bill_state'and c.dmz=t.bill_state " +
				"/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
				"/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
		        "/~ and t.id={id} ~/"+
				"/~ and t.p_id={p_id} ~/"+
				"/~ and t.p_id!={ppid} ~/"+
				"/~ and t.dept_id={dept_id} ~/"+
				"/~ and t.unit={unit} ~/"+
				"/~ and t.money={money} ~/"+
				"/~ and t.bill_state={bill_state} ~/" +
			    "/~ and m.id={mid} ~/"+
			    "/~ and b.name like '%[pname]%' ~/"+
			    " order by t.bill_state ";
			
			XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
			System.out.println(xsql.getXsql());
			Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
			Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
			
			String jsonStr = JSON.toJSONString(map);
			setAjaxInfo(response,jsonStr);
		
		}
	
	
	
	////////////////////
	
	
	
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from fi_bill t where t.id = ?", new Object[]{request.getParameter("id")});
		
		request.setAttribute("bill", map);
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/fi/bill/fi_bill_auditDtl.jsp";
	}
	private Page deptsearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		
		String bill_state = request.getParameter("bill_state")==null?"1":request.getParameter("bill_state");
		Map searchMap=getParameterMap(request);
		searchMap.put("bill_state", bill_state);
		Map user = (Map)request.getSession().getAttribute("user");
        
		String dept_id = ""+user.get("branchid");
		if(dept_id.equals("5"))
		{
			searchMap.put("dept_id", "10");
		}
		else
		{
			searchMap.put("dept_id", user.get("branchid"));
		}
		
		searchMap.put("ppid", user.get("base_info_id"));
		
		request.setAttribute("searchMap", searchMap);
		
		String sql="select t.*,b.name,m.branchname from fi_bill t,hr_base_info b,mrbranch m where t.dept_id = m.id and t.p_id = b.id  " +
		        //"/~ and (t.dept_id in (select s1.dataid from sys_data_scope s1 where s1.roleid in (select ss1.rolesid from sysuserroles ss1 where ss1.userid = {sysuserid1} ) and s1.data_scope_type = 0) or t.p_id in (select s2.dataid from sys_data_scope s2 where s2.roleid in (select ss2.rolesid from sysuserroles ss2 where ss2.userid = {sysuserid2} ) and s2.data_scope_type = 1)) ~/  " +
		        "/~ and t.id={id} ~/"+
				"/~ and t.p_id={p_id} ~/"+
				"/~ and t.p_id!={ppid} ~/"+
				"/~ and t.dept_id={dept_id} ~/"+
				"/~ and t.unit={unit} ~/"+
				"/~ and t.money={money} ~/"+
				"/~ and t.bill_state={bill_state} ~/" +
			    "/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
			    "/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
			    " order by t.bill_state ,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	private Page cwsearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map user = (Map)request.getSession().getAttribute("user");
		String bill_state = request.getParameter("bill_state")==null?"2":request.getParameter("bill_state");
		searchMap.put("bill_state", bill_state);
		request.setAttribute("searchMap", searchMap);
		searchMap.put("ppid", user.get("base_info_id"));
		String sql="select t.*,b.name,m.branchname from fi_bill t,hr_base_info b,mrbranch m where t.dept_id = m.id and t.p_id = b.id  " +
		        //"/~ and (t.dept_id in (select s1.dataid from sys_data_scope s1 where s1.roleid in (select ss1.rolesid from sysuserroles ss1 where ss1.userid = {sysuserid1} ) and s1.data_scope_type = 0) or t.p_id in (select s2.dataid from sys_data_scope s2 where s2.roleid in (select ss2.rolesid from sysuserroles ss2 where ss2.userid = {sysuserid2} ) and s2.data_scope_type = 1)) ~/  " +
		        "/~ and t.id={id} ~/"+
				"/~ and t.p_id={p_id} ~/"+
				"/~ and t.p_id!={ppid} ~/"+
				"/~ and t.dept_id={dept_id} ~/"+
				"/~ and t.unit={unit} ~/"+
				"/~ and t.money={money} ~/"+
				"/~ and t.bill_state={bill_state} ~/" +
			    "/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
			    "/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
			    " order by t.bill_state ,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	
	private Page kjsearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map user = (Map)request.getSession().getAttribute("user");
		searchMap.put("ppid", user.get("base_info_id"));
		
		String bill_state = request.getParameter("bill_state")==null?"9":request.getParameter("bill_state");
		searchMap.put("bill_state", bill_state);
		request.setAttribute("searchMap", searchMap);
		
		String sql="select t.*,b.name,m.branchname from fi_bill t,hr_base_info b,mrbranch m where t.dept_id = m.id and t.p_id = b.id  " +
		        "/~ and t.id={id} ~/"+
				"/~ and t.p_id={p_id} ~/"+
				"/~ and t.p_id!={ppid} ~/"+
				"/~ and t.dept_id={dept_id} ~/"+
				"/~ and t.unit={unit} ~/"+
				"/~ and t.money={money} ~/"+
				"/~ and t.bill_state={bill_state} ~/" +
			    "/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
			    "/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
			    "/~ and m.id={mid} ~/"+
			    "/~ and b.name like '%[pname]%' ~/"+
			    " order by t.bill_state ,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	private Page kpsearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		String bill_state = request.getParameter("bill_state")==null?"6":request.getParameter("bill_state");
		searchMap.put("bill_state", bill_state);
		request.setAttribute("searchMap", searchMap);
		
		String sql="select (t.money-t.receive_money)needmoney,t.*,b.name,m.branchname from fi_bill t,hr_base_info b,mrbranch m where t.dept_id = m.id and t.p_id = b.id  " +
		        "/~ and t.id={id} ~/"+
				"/~ and t.p_id={p_id} ~/"+
				"/~ and t.dept_id={dept_id} ~/"+
				"/~ and t.unit={unit} ~/"+
				"/~ and t.money={money} ~/"+
				"/~ and t.bill_state={bill_state} ~/" +
			    "/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
			    "/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
			    " order by t.bill_state ,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	private Page zfsearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		request.setAttribute("searchMap", searchMap);
		
		String sql="select (t.money-t.receive_money)needmoney,t.*,b.name,m.branchname from fi_bill t,hr_base_info b,mrbranch m where t.dept_id = m.id and t.p_id = b.id  " +
		        "/~ and t.id={id} ~/"+
				"/~ and t.p_id={p_id} ~/"+
				"/~ and t.dept_id={dept_id} ~/"+
				"/~ and t.unit={unit} ~/"+
				"/~ and t.money={money} ~/"+
				"/~ and t.bill_state={bill_state} ~/" +
			    "/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
			    "/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
			    " order by t.bill_state ,t.id desc";
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
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/fi/bill/fi_bill_auditDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select * from fi_bill t where t.id = ?", new Object[]{id});
			Fi_bill bill = new Fi_bill();
			BeanUtils.populate(bill, map);
			bill.setOpinion(request.getParameter("opinion"));
			String bill_state = request.getParameter("state");
			Map user = (Map)request.getSession().getAttribute("user");
			Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{bill.getP_id()});
			
			String num = "";
			String infor = "";
			String state = "";
			
			if(bill_state.equals("1"))
			{
				bill.setBill_state("2");
				state = "2";
				//查询财务主管审批人号码
				
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 2", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
				infor = baseinfo.get("name")+"提出发票申请，请您审批！";
				
			}
			else
			{
				bill.setBill_state("3");
				state = "3";
				num = ""+baseinfo.get("phone");
				infor = "您的发票申请部门经理未批准";
			}
			
			int i = dataBaseControl.updateByBean(bill);
			
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			m.sendGFDZ(num,infor);
            if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			
             Fi_bill_audit bill_audit = new Fi_bill_audit();
              bill_audit.setBillapply_id(new BigDecimal(id));
              bill_audit.setAudit_date(new java.sql.Date((new java.util.Date()).getTime()));
              bill_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
              bill_audit.setOpinion(request.getParameter("opinion"));
              bill_audit.setBill_state(state);
              dataBaseControl.insertByBean(bill_audit);

		}else{
			Page page=deptsearch(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("bill", record.get(0));
			//获得审批意见
			List splist = dataBaseControl.getOutResultSet("select fa.*,(select b.name from hr_base_info b where b.id = fa.audit_id)person from fi_bill f,fi_bill_audit fa where f.id = fa.billapply_id and f.id = ? order by fa.id ", new Object[]{request.getParameter("id")});
			request.setAttribute("splist", splist);
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("type", "audit");
		buildDDL(request);
		return "/fi/bill/look.jsp";
	}
	
	
	public String cwedit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select * from fi_bill t where t.id = ?", new Object[]{id});
			Fi_bill bill = new Fi_bill();
			BeanUtils.populate(bill, map);
			bill.setOpinion(request.getParameter("opinion"));
			bill.setFukuan_unit(request.getParameter("fukuan_unit"));
			String bill_state = request.getParameter("state");
			Map user = (Map)request.getSession().getAttribute("user");
			Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{bill.getP_id()});
			
			String num = "";
			String infor = "";
			String state = "";
			
			if(bill_state.equals("1"))
			{
				bill.setBill_state("6");
				state = "6";
				//查询财务主管审批人号码
				
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 6", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
				infor = baseinfo.get("name")+"提出发票申请，请开票！";
				
			}
			else if(bill_state.equals("0"))
			{
				bill.setBill_state("4");
				state = "4";
				num = ""+baseinfo.get("phone");
				infor = "您的发票申请财务主管未批准";
			}
			else
			{
				bill.setBill_state("5");
				state = "5";
				num = ""+baseinfo.get("phone");
				infor = "您的发票申请财务主管打回";
			}
			
			int i = dataBaseControl.updateByBean(bill);
			
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			m.sendGFDZ(num,infor);
            if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			
             Fi_bill_audit bill_audit = new Fi_bill_audit();
              bill_audit.setBillapply_id(new BigDecimal(id));
              bill_audit.setAudit_date(new java.sql.Date((new java.util.Date()).getTime()));
              bill_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
              bill_audit.setOpinion(request.getParameter("opinion"));
              bill_audit.setBill_state(state);
              dataBaseControl.insertByBean(bill_audit);

		}else{
			Page page=cwsearch(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("bill", record.get(0));
			
			//获得审批意见
			List splist = dataBaseControl.getOutResultSet("select fa.*,(select b.name from hr_base_info b where b.id = fa.audit_id)person from fi_bill f,fi_bill_audit fa where f.id = fa.billapply_id and f.id = ? order by fa.id ", new Object[]{request.getParameter("id")});
			request.setAttribute("splist", splist);
			
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("type", "audit");
		buildDDL(request);
		return "/fi/bill/look.jsp";
	}
	
	
	public String kpedit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select * from fi_bill t where t.id = ?", new Object[]{id});
			Fi_bill bill = new Fi_bill();
			BeanUtils.populate(bill, map);
			
			Map user = (Map)request.getSession().getAttribute("user");
			Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{bill.getP_id()});
			
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			
			String num = "";
			String infor = "";
		    bill.setBill_state("7");
		    bill.setReceive_money(new BigDecimal(request.getParameter("receive_money")));
		    bill.setAct_date(new java.sql.Date((fmt.parse(request.getParameter("act_date")).getTime())));
		    
		    bill.setFaph(request.getParameter("faph"));
		    bill.setDisgsh(request.getParameter("disgsh"));
			num = ""+baseinfo.get("phone");
			infor = "您的发票已开，请到财务领取！";
			
			
			int i = dataBaseControl.updateByBean(bill);
			
			
			Fi_bill_one bill_one = new Fi_bill_one();
			
			bill_one.setBill_id(new BigDecimal(id));
			bill_one.setMoney(new BigDecimal((request.getParameter("receive_money")==null||request.getParameter("receive_money").equals(""))?"0":request.getParameter("receive_money")));
			bill_one.setRdate(new java.sql.Date((new java.util.Date()).getTime()));
			dataBaseControl.insertByBean(bill_one);
			
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			m.sendGFDZ(num,infor);
            if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");

		}else{
			Page page=cwsearch(request,1,1);
			
			Map map = dataBaseControl.getOneResultSet("select * from fi_bill t where t.id = ?", new Object[]{request.getParameter("id")});
			
			map.put("nreceive_money", map.get("money"));
			
			
			request.setAttribute("bill",map);
			//获得审批意见
			List splist = dataBaseControl.getOutResultSet("select fa.*,(select b.name from hr_base_info b where b.id = fa.audit_id)person from fi_bill f,fi_bill_audit fa where f.id = fa.billapply_id and f.id = ? order by fa.id ", new Object[]{request.getParameter("id")});
			request.setAttribute("splist", splist);
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("type", "audit");
		buildDDL(request);
		return "/fi/bill/look.jsp";
	}
	
	
	public String zfedit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select * from fi_bill t where t.id = ?", new Object[]{id});
			Fi_bill bill = new Fi_bill();
			BeanUtils.populate(bill, map);

		    bill.setBill_state("8");
		    bill.setReason(request.getParameter("reason"));
			
			int i = dataBaseControl.updateByBean(bill);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");

		}else{
			Page page=zfsearch(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("bill", record.get(0));
			//获得审批意见
			List splist = dataBaseControl.getOutResultSet("select fa.*,(select b.name from hr_base_info b where b.id = fa.audit_id)person from fi_bill f,fi_bill_audit fa where f.id = fa.billapply_id and f.id = ? order by fa.id ", new Object[]{request.getParameter("id")});
			request.setAttribute("splist", splist);
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("type", "audit");
		buildDDL(request);
		return "/fi/bill/look.jsp";
	}
	
	
	public String hkedit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select * from fi_bill t where t.id = ?", new Object[]{id});
			Fi_bill bill = new Fi_bill();
			BeanUtils.populate(bill, map);
			
            Fi_bill_one bill_one = new Fi_bill_one();
			
			bill_one.setBill_id(new BigDecimal(id));
			bill_one.setMoney(new BigDecimal(request.getParameter("receive_money")));
			bill_one.setRdate(new java.sql.Date((new java.util.Date()).getTime()));
			dataBaseControl.insertByBean(bill_one);
			
			Map summap = (Map)dataBaseControl.getOneResultSet("select sum(t.money)summoney from fi_bill_one t where t.bill_id = ?", new Object[]{id});
			

			/*bill.setReceive_money(""+summap.get("summoney"));*/
			bill.setReceive_money(new BigDecimal(summap.get("summoney").toString()));
			int i = dataBaseControl.updateByBean(bill);
			
            
			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");

		}else{
            
			Map map = dataBaseControl.getOneResultSet("select t.*,(t.money-t.receive_money)needmoney from fi_bill t where t.id = ?", new Object[]{request.getParameter("id")});
			
			map.put("nreceive_money", map.get("needmoney"));
			request.setAttribute("bill", map);
			//获得审批意见
			List splist = dataBaseControl.getOutResultSet("select fa.*,(select b.name from hr_base_info b where b.id = fa.audit_id)person from fi_bill f,fi_bill_audit fa where f.id = fa.billapply_id and f.id = ? order by fa.id ", new Object[]{request.getParameter("id")});
			request.setAttribute("splist", splist);
			
			//获得分批还款记录
			List hklist = dataBaseControl.getOutResultSet("select * from fi_bill_one t where t.bill_id = ? order by t.id", new Object[]{request.getParameter("id")});
			request.setAttribute("hklist", hklist);
			
			if(hklist.size()!=0)
			{
				request.setAttribute("hk", "1");
			}
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("type", "audit");
		request.setAttribute("ttype", "1");
		buildDDL(request);
		return "/fi/bill/look.jsp";
	}
	
	
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Fi_bill_audit WHERE ID in (?)", new Object[]{s_id});
		
		return "/fi/bill/Fi_bill_audit!searchList.do";
	}
    private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("bill_state",codeTableUtil.getCodeMap("bill_state")); 
		request.setAttribute("fukuan_unit",codeTableUtil.getCodeMap("fukuan_unit"));
	}

}