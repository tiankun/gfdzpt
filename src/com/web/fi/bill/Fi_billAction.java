package com.web.fi.bill;

import java.math.BigDecimal;
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
import com.map.Fi_bill;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Fi_billAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("searchMap", getParameterMap(request));
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/bill/fi_billSelect.jsp";
		return "/fi/bill/fi_billList.jsp";
	}
	
	
	//////////////////////////////////////////////
	public String searchListDemo (HttpServletRequest request,HttpServletResponse response) throws Exception  
	{
		Map user = (Map)request.getSession().getAttribute("user");
		String p_id=user.get("base_info_id").toString();
		String sql="select t.*,b.name,m.branchname,c.dmzmc from fi_bill t,hr_base_info b,mrbranch m,code c where t.dept_id = m.id and t.p_id = b.id and t.bill_state=c.dmz and c.dmlb='bill_state' and t.p_id="+p_id+
				"/~ and t.id={id} ~/"+
				"/~ and t.dept_id={dept_id} ~/"+
				"/~ and t.unit={unit} ~/"+
				"/~ and t.money={money} ~/"+
				"/~ and t.bill_state={bill_state} ~/" +
			    "/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
			    "/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
			    " order by t.bill_state";
		
		defaultList(request,response,new StringBuffer(sql),"t.id desc"); 
		return null;
	}
	public void total(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
		String p_id=user.get("base_info_id").toString();
		String sql="select sum(t.money)sum,count(*)count from fi_bill t,hr_base_info b,mrbranch m,code c where t.dept_id = m.id and t.p_id = b.id and t.bill_state=c.dmz and c.dmlb='bill_state' and t.p_id="+p_id+
				"/~ and t.id={id} ~/"+
				"/~ and t.dept_id={dept_id} ~/"+
				"/~ and t.unit={unit} ~/"+
				"/~ and t.money={money} ~/"+
				"/~ and t.bill_state={bill_state} ~/" +
			    "/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
			    "/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
			    " order by t.bill_state";
			
			XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
			Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
			Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
			
			String jsonStr = JSON.toJSONString(map);
			setAjaxInfo(response,jsonStr);
		
		}
	///////////////////////////////////////////////////////
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		request.setAttribute("type", request.getParameter("type"));
		
		Map billmap = (Map)dataBaseControl.getOneResultSet("select t.* from fi_bill t where t.id = ?", new Object[]{request.getParameter("id")});
		request.setAttribute("bill", billmap);
		
		String bill_state = billmap.get("bill_state").toString();
		if(bill_state.equals("4")||bill_state.equals("5")||bill_state.equals("6")||bill_state.equals("7")||bill_state.equals("8"))
		{
			List auditlist = dataBaseControl.getOutResultSet("select r.* from fi_bill t,fi_bill_audit r where t.id = r.billapply_id and t.id = ? order by r.id", new Object[]{billmap.get("id")});
			request.setAttribute("deptopinion", ((Map)(auditlist.toArray()[0])).get("opinion"));
		}
		
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
		
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/fi/bill/look.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map user = (Map)request.getSession().getAttribute("user");
		searchMap.put("p_id", user.get("base_info_id"));
		
		String sql="select t.*,b.name,m.branchname from fi_bill t,hr_base_info b,mrbranch m where t.dept_id = m.id and t.p_id = b.id  " +
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
		
		//获得发票类型
		List ftypelist = new ArrayList();
		Map map1 = new HashMap();
		map1.put("name", "工程类");
		map1.put("code", "1");
		ftypelist.add(map1);
		Map map2 = new HashMap();
		map2.put("name", "收据类");
		map2.put("code", "2");
		ftypelist.add(map2);
		Map map3 = new HashMap();
		map3.put("name", "其他类");
		map3.put("code", "3");
		ftypelist.add(map3);
		request.setAttribute("ftypelist", ftypelist);
		
		List stypelist = new ArrayList();
		Map smap1 = new HashMap();
		smap1.put("name", "税控收款机专用发票(自开)");
		smap1.put("ftype", "1");
		stypelist.add(smap1);
		
		Map smap2 = new HashMap();
		smap2.put("name", "税务代开发票(对方开)");
		smap2.put("ftype", "1");
		stypelist.add(smap2);
		
		Map smap3 = new HashMap();
		smap3.put("name", "收款专用收据");
		smap3.put("ftype", "2");
		stypelist.add(smap3);
		
		Map smap4 = new HashMap();
		smap4.put("name", "增值税专用发票");
		smap4.put("ftype", "3");
		stypelist.add(smap4);
		
		Map smap5 = new HashMap();
		smap5.put("name", "增值税普通发票");
		smap5.put("ftype", "3");
		stypelist.add(smap5);
		
		request.setAttribute("stypelist", stypelist);
		
		//获得单位列表
		List unitlist = dataBaseControl.getOutResultSet("select distinct t.unit from fi_bill t", null);
		request.setAttribute("unitlist", unitlist);
		
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Fi_bill bill = new Fi_bill();
			BeanUtils.populate(bill, getParameterMap(request));
			Map user = (Map)request.getSession().getAttribute("user");
			Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
			String duty_id = baseinfo.get("duty_id").toString();
			String dept_id = baseinfo.get("dept_id").toString();
			List plist = new ArrayList();
			
			//获取填报人角色
			Map juese = dataBaseControl.getOneResultSet("select count(id) c from sysuserroles where userid=? and rolesid=38", new Object[]{user.get("id")});
			String jsCount = null;
			if(juese.size()!=0){
				jsCount = juese.get("c").toString();
			}else{
				jsCount = "";
			}
			bill.setP_id(new BigDecimal(user.get("base_info_id").toString()));
			bill.setDept_id(new BigDecimal(user.get("branchid").toString()));
			
			String[]zi_liaos=request.getParameterValues("fujian");
		    String fujian = "";
		    
		    Date date = new Date();
		    
		    if(zi_liaos!=null&&zi_liaos.length>0)
		    {
		    	for(int i = 0 ; i < zi_liaos.length;i++)
				{
					String str = zi_liaos[i];
					fujian = fujian + ";" + str;
					
				}
		    }
			bill.setPath(fujian);
			bill.setInput_date(new java.sql.Date(date.getTime()));
			
			
			String num = "";
			String state = request.getParameter("state");
			
			List slist = null;
			if(state.equals("0"))
			{
				bill.setBill_state(state);
			}
			else
			{
				//发短信，获取短信号码
				if(duty_id.equals("1"))//普通职员开发票
				{
					bill.setBill_state(state);
					if(dept_id.equals("10"))  //客户部
					{
						slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 10", null);
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
					else  //其他部门
					{
						/*//是否有软件部打印角色
						if(jsCount.equals("1")){
							bill.setBill_state("2");
							slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 2", null);*/
						//是否是软件部职员
						if(dept_id.equals("4")){
							bill.setBill_state("2");
							slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 2", null);
						}else{
							slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
						}
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
				}
				else //部门经理、崔总、向总开发票
				{
					bill.setBill_state("2");
					
					slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 2", null);
					if(slist!=null&&!slist.isEmpty())
					{
						Map smap = (Map)slist.get(0);
						num = smap.get("num").toString();
					}
					
				}
			}
			int i =dataBaseControl.insertByBean(bill);		
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			
			
			//发短信通知
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			String infor=baseinfo.get("name")+"提出开票申请，请您审批！";
			m.sendGFDZ(num,infor);
			
			
			
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/fi/bill/fi_billDtl.jsp";
	}
	
	
	/*
	 * 根据单位名称获得其他属性信息
	 */
	public String getCompData(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		String sql = "select * from fi_bill t where t.unit = ? ";
		String compname = request.getParameter("compname");
		//String compname1 = new String(compname.getBytes("ISO-8859-1"),"UTF-8");
		
		if(compname != null && !compname.equals("")){			
			List accom = (List)dataBaseControl.getOutResultSet(sql,new Object[]{compname});
			if(accom != null && !accom.isEmpty())
			{
				String accomJson = JSON.toJSONString(accom.get(0));
				setAjaxInfo(response,accomJson);
			}	
		}
		return null;
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Fi_bill bill = new Fi_bill();
			BeanUtils.populate(bill, getParameterMap(request));
			String[]zi_liaos=request.getParameterValues("fujian");
		    String fujian = "";
		    
		    Date date = new Date();
		    
		    if(zi_liaos!=null&&zi_liaos.length>0)
		    {
		    	for(int i = 0 ; i < zi_liaos.length;i++)
				{
					String str = zi_liaos[i];
					fujian = fujian + ";" + str;
					
				}
		    }
			bill.setPath(fujian);
			bill.setInput_date(new java.sql.Date(date.getTime()));
			bill.setBill_state(request.getParameter("state"));
			
			
			dataBaseControl.updateByBean(bill);
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("bill", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		
		buildDDL(request);
		return "/fi/bill/view.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Fi_bill WHERE ID in (?)", new Object[]{s_id});
		
		return "/fi/bill/Fi_bill!searchList.do";
	}
	
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfo.get("duty_id").toString();
		String dept_id = baseinfo.get("dept_id").toString();
		
		Map billmap = dataBaseControl.getOneResultSet("select * from fi_bill t where t.id = ?", new Object[]{id});
		Fi_bill bill = new Fi_bill();
		BeanUtils.populate(bill, billmap);
		
		String num = "";
		
		if(duty_id.equals("2")||duty_id.equals("4")||duty_id.equals("5"))
		{
			if(dept_id.equals("6"))
			{
				bill.setBill_state("9");
				//获得会计电话
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 3 ", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
			else
			{
				bill.setBill_state("2");
				
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 2", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
				
			}
			
		}
		else
		{
			bill.setBill_state("1");
			
			if(dept_id.equals("10"))  //客户部
			{
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 10", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
			else  //其他部门
			{
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
		
		}
		
		dataBaseControl.updateByBean(bill);
		

		PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
		Mas m = (Mas) client.useService(Mas.class);
		String infor=baseinfo.get("name")+"提出发票申请，请您审批！";
		m.sendGFDZ(num,infor);
		
		
		return "/fi/bill/Fi_bill!searchList.do";
	}
	
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("bill_state",codeTableUtil.getCodeMap("bill_state")); 
		request.setAttribute("fukuan_unit",codeTableUtil.getCodeMap("fukuan_unit"));
	}

}