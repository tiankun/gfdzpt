package com.web.gm.carapply;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.phprpc.PHPRPC_Client;

import com.map.Gm_carapply;
import com.map.Gm_carapply_audit;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Gm_carapply_auditAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String dept_id = baseinfo.get("dept_id").toString();
		
		String forward	= "";
		if(!dept_id.equals("3"))
		{
			Page page=search(request,pageNo,pageSize);
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
			request.setAttribute("state", "1");
			forward = "/gm/carapply/gm_deptmanage_carapplyList.jsp";
		}
		else
		{
			Page page=zhbsearch(request,pageNo,pageSize);
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
			request.setAttribute("state", "2");
			forward = "/gm/carapply/gm_zhb_carapplyList.jsp";
		}
		
		
		request.setAttribute("searchMap", getParameterMap(request));
		
		
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/performance/gm_carapply_auditSelect.jsp";
		return forward;
	}
	
	
	public String zhbsearchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String dept_id = baseinfo.get("dept_id").toString();
		
		Page page=zhbsearch(request,pageNo,pageSize);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
	    request.setAttribute("searchMap", getParameterMap(request));
		
		
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/performance/gm_carapply_auditSelect.jsp";
		return "/gm/carapply/gm_zhb_carapplyList.jsp";
	}
	
	
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/performance/gm_carapply_auditDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map user = (Map)request.getSession().getAttribute("user");
		String dept_id = ""+user.get("branchid");
		//searchMap.put("sysuserid1", user.get("id"));
		//searchMap.put("sysuserid2", user.get("id"));
		
		if(dept_id.equals("5"))
		{
			searchMap.put("dept_id", "10");
		}
		else
		{
			searchMap.put("dept_id", user.get("branchid"));
		}
		
		searchMap.put("ppid", user.get("base_info_id"));
		
		
		String sql="select t.*, r.name,m.branchname from gm_carapply t, hr_base_info r,mrbranch m where t.dept_id = m.id and 1 = 1 and t.p_id = r.id " 
		//+ "/~ and (t.dept_id in (select s1.dataid from sys_data_scope s1 where s1.roleid in (select ss1.rolesid from sysuserroles ss1 where ss1.userid = {sysuserid1} ) and s1.data_scope_type = 0) or t.p_id in (select s2.dataid from sys_data_scope s2 where s2.roleid in (select ss2.rolesid from sysuserroles ss2 where ss2.userid = {sysuserid2} ) and s2.data_scope_type = 1)) ~/  "
		+ "/~ and r.name={name} ~/"
		+ "/~ and t.dept_id={dept_id} ~/"
		+ "/~ and t.p_id!={ppid} ~/"
		+ "/~ and m.branchname={branchname} ~/"
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.usetype={usetype} ~/"
		+ "/~ and t.carapply_state = {carapply_state} ~/ "
		+"/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" 
		+"/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/"  
		+ " order by t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	private Page zhbsearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*, r.name,m.branchname from gm_carapply t, hr_base_info r,mrbranch m where t.dept_id = m.id and 1 = 1 and t.p_id = r.id " 
		+ "/~ and r.name={name} ~/"
		+ "/~ and t.dept_id={dept_id} ~/"
		+ "/~ and m.branchname={branchname} ~/"
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.usetype={usetype} ~/"
		+ "/~ and t.carapply_state = {carapply_state} ~/ "
		+"/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" 
		+"/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/"  
		+ " order by t.id desc ";
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
		return "/performance/gm_carapply_auditDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select * from gm_carapply t where t.id = ?", new Object[]{id});
			Gm_carapply carapply = new Gm_carapply();
			BeanUtils.populate(carapply, map);
			carapply.setOpinion(request.getParameter("opinion"));
			String carapply_state = request.getParameter("carapply_state");
			Map user = (Map)request.getSession().getAttribute("user");
			Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{carapply.getP_id()});
			
			String num = "";
			String infor = "";
			String state = "";
			
			String num1 = "";
			String infor1 = "";
			
			if(carapply_state.equals("1"))
			{
				carapply.setCarapply_state("2");
				state = "2";
				//查询综合办审批人号码
				
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 3", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
				
				infor = baseinfo.get("name")+"提出用车申请，请您审批！";
				
				
				String usetype = carapply.getUsetype();
				if(usetype.equals("0"))
				{
					num1 = "15887120288";
					infor1 = baseinfo.get("name")+"提出拉货申请！";
				}
				
				
			}
			else
			{
				carapply.setCarapply_state("3");
				state = "3";
				num = ""+baseinfo.get("phone");
				infor = "您的用车申请部门经理未批准";
			}
			
			int i = dataBaseControl.updateByBean(carapply);
			
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			m.sendGFDZ(num,infor);
			m.sendGFDZ(num1,infor1);
            if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			
            Gm_carapply_audit carapply_audit = new Gm_carapply_audit();
            carapply_audit.setApply_id(new BigDecimal(id));
            carapply_audit.setAudit_date(new java.sql.Timestamp((new java.util.Date()).getTime())); 
            carapply_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
            carapply_audit.setOpinion(request.getParameter("opinion"));
            carapply_audit.setState(state);
            dataBaseControl.insertByBean(carapply_audit);

		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select r.*,(select b.name from hr_base_info b where b.id = r.audit_id) person from gm_carapply t, gm_carapply_audit r where t.id = r.apply_id and t.id = ?  order by r.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		
		return "/gm/carapply/gm_audit_carapplyDtl.jsp";
	}
	
	/**
	 * 综合办审批意见
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String zhbedit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select * from gm_carapply t where t.id = ?", new Object[]{id});
			Gm_carapply carapply = new Gm_carapply();
			BeanUtils.populate(carapply, map);
			carapply.setOpinion(request.getParameter("opinion"));
			String carapply_state = request.getParameter("carapply_state");
			Map user = (Map)request.getSession().getAttribute("user");
			Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{carapply.getP_id()});
			
			String num = "";
			String drinum = "";
			String infor = "";
			String driinfor = "";
			String state = "";
			
			if(carapply_state.equals("1"))
			{
				
				carapply.setDriver(new BigDecimal(request.getParameter("driver")));
				carapply.setCar_num(request.getParameter("car_num"));
				carapply.setCarapply_state("4");
				
				//查询综合办审批人号码
				Map drivermap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{carapply.getDriver()});
				
				
				state = "4";
				num = ""+baseinfo.get("phone");
				infor = "您的用车申请已被批准，驾驶员是"+drivermap.get("name");
				
				drinum = ""+drivermap.get("phone");
				driinfor = baseinfo.get("name")+"计划于"+carapply.getPlan_sdate()+"至"+carapply.getPlan_edate()+"用车，用车原因是"+carapply.getReason()+"，行车路线是"+
				           carapply.getRoad()+"，车辆是"+carapply.getCar_num();

			}
			else
			{
				carapply.setCarapply_state("5");
				state = "5";
				num = ""+baseinfo.get("phone");
				infor = "您的用车申请综合办未批准";
			}
			
			int i = dataBaseControl.updateByBean(carapply);
			//发给用车申请者
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			m.sendGFDZ(num,infor);
			//发给驾驶员
			m.sendGFDZ(drinum,driinfor);
			
            if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			
            Gm_carapply_audit carapply_audit = new Gm_carapply_audit();
            carapply_audit.setApply_id(new BigDecimal(id));
            carapply_audit.setAudit_date(new java.sql.Timestamp((new java.util.Date()).getTime()));
            carapply_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
            carapply_audit.setOpinion(request.getParameter("opinion"));
            carapply_audit.setState(state);
            dataBaseControl.insertByBean(carapply_audit);

		}else{
			Page page=zhbsearch(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
			
			//获得驾驶员
			List drivelist = dataBaseControl.getOutResultSet("select * from hr_base_info t where t.remark = '1' and t.hr_type in ('2','3')order by t.id", null);
			request.setAttribute("dlist", drivelist);
			
			List carlist = dataBaseControl.getOutResultSet("select t.* from code t where t.dmlb = 'car_num'", null);
			request.setAttribute("clist", carlist);
		}
		
		request.setAttribute("editMod", "zhbedit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select r.*,(select b.name from hr_base_info b where b.id = r.audit_id) person from gm_carapply t, gm_carapply_audit r where t.id = r.apply_id and t.id = ?  order by r.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		return "/gm/carapply/gm_audit_carapplyDtl.jsp";
	}
	
	
	
	public String driveredit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select * from gm_carapply t where t.id = ?", new Object[]{id});
			Gm_carapply carapply = new Gm_carapply();
			BeanUtils.populate(carapply, map);
			
			carapply.setAct_sdate(request.getParameter("act_sdate"));
			carapply.setAct_edate(request.getParameter("act_edate"));
			carapply.setLength(request.getParameter("length"));
			carapply.setCarapply_state("6");
			
			Map applymap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{carapply.getP_id()});
			
			
			String num = ""+applymap.get("phone");
			
			String infor = "驾驶员已将您此次的用车里程填写，请您核对！";
			
			
			int i = dataBaseControl.updateByBean(carapply);
			//发给用车申请者
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			m.sendGFDZ(num,infor);
			
            if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");	

		}else{
			Map map = dataBaseControl.getOneResultSet("select t.*,b.name from gm_carapply t,hr_base_info b where t.p_id = b.id and t.id = ?",new Object[]{ request.getParameter("id")});
			request.setAttribute("record", map);
			
		}
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select r.*,(select b.name from hr_base_info b where b.id = r.audit_id) person from gm_carapply t, gm_carapply_audit r where t.id = r.apply_id and t.id = ?  order by r.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		request.setAttribute("editMod", "driveredit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/gm/carapply/gm_audit_carapplyDtl.jsp";
	}
	
	
	public String pilsp(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String idss = request.getParameter("ids");
		List reslist = new ArrayList();
		
		request.setAttribute("idss", idss);
		
		String [] iids = idss.substring(1).split(";");
		String ids = "";
		
		
		for(int i = 0 ; i < iids.length;i++)
		{
				ids = ids + ";" + iids[i];
				Map map = dataBaseControl.getOneResultSet("select t.*,r.name from gm_carapply t ,hr_base_info r where t.id = ? and t.p_id = r.id ", new Object[]{iids[i].toString()});
				reslist.add(map);
		}
			
		
		
		//获得驾驶员
		List drivelist = dataBaseControl.getOutResultSet("select * from hr_base_info t where t.remark = '1' and t.hr_type in ('2','3')order by t.id", null);
		request.setAttribute("dlist", drivelist);
		
		List carlist = dataBaseControl.getOutResultSet("select t.* from code t where t.dmlb = 'car_num'", null);
		request.setAttribute("clist", carlist);
		request.setAttribute("res",reslist);
		
		return "/gm/carapply/mulaudit.jsp";
	}
	
	/**
	 * 保存批量审批结论
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String mulsave(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String idss = request.getParameter("idss");
		String [] iids = idss.substring(1).split(";");
		Map user = (Map)request.getSession().getAttribute("user");
		
		String carapply_state = request.getParameter("carapply_state");
		String state = "";
		String num = "";
		String infor = "";
		
		String dnum = "";
		String dinfor = "";
		
		String name = "";
		String btime = "";
		String etime = "";
		
		PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
		Mas m = (Mas) client.useService(Mas.class);
		
		
		if(carapply_state.equals("1"))
		{
			for(int i = 0 ; i < iids.length;i++)
			{
				Map map = dataBaseControl.getOneResultSet("select t.* from gm_carapply t  where t.id = ? ", new Object[]{iids[i].toString()});
				Gm_carapply carapply = new Gm_carapply();
				BeanUtils.populate(carapply, map);
				carapply.setDriver(new BigDecimal(request.getParameter("driver")));
				carapply.setCar_num(request.getParameter("car_num"));
				carapply.setCarapply_state("4");
				carapply.setOpinion(request.getParameter("opinion"));
				state = "4";
				dataBaseControl.updateByBean(carapply);
				
				Map applybaseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{carapply.getP_id()});
				Map drivebaseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{carapply.getDriver()});
				
				num = ""+applybaseinfo.get("phone");
				infor = "您的用车申请已被批准，驾驶员是"+drivebaseinfo.get("name");
				
				dnum = ""+drivebaseinfo.get("phone");
				
				name = name + ";" + applybaseinfo.get("name").toString();
				btime = carapply.getPlan_sdate();
				etime = carapply.getPlan_edate();
				m.sendGFDZ(num,infor);
				
				Gm_carapply_audit carapply_audit = new Gm_carapply_audit();
	            carapply_audit.setApply_id(carapply.getId());
	            carapply_audit.setAudit_date(new java.sql.Timestamp((new java.util.Date()).getTime()));
	            carapply_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
	            carapply_audit.setOpinion(request.getParameter("opinion"));
	            carapply_audit.setState(state);
	            dataBaseControl.insertByBean(carapply_audit);
				
				
			}
			dinfor = name.substring(1) +"计划于"+btime+"至"+etime+"用车，车辆是"+request.getParameter("car_num")+"，综合办意见是："+ request.getParameter("opinion");
			m.sendGFDZ(dnum,dinfor);
		}
		else
		{
			for(int i = 0 ; i < iids.length;i++)
			{
				Map map = dataBaseControl.getOneResultSet("select t.* from gm_carapply t  where t.id = ? ", new Object[]{iids[i].toString()});
				Gm_carapply carapply = new Gm_carapply();
				BeanUtils.populate(carapply, map);
				carapply.setCarapply_state("5");
				state = "5";
				dataBaseControl.updateByBean(carapply);

				Map applybaseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{carapply.getP_id()});
				num = ""+applybaseinfo.get("phone");
				infor = "您的用车申请综合办未批准";
				m.sendGFDZ(num,infor);
				
				Gm_carapply_audit carapply_audit = new Gm_carapply_audit();
	            carapply_audit.setApply_id(carapply.getId());
	            carapply_audit.setAudit_date(new java.sql.Timestamp((new java.util.Date()).getTime()));
	            carapply_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
	            carapply_audit.setOpinion(request.getParameter("opinion"));
	            carapply_audit.setState(state);
	            dataBaseControl.insertByBean(carapply_audit);
			}
			
		}
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/gm/carapply/mulaudit.jsp";
	}
	
	
	
	public String accomplish(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		    ? "1" : request.getParameter("pageno");
         int pageNo=(new Integer(page_no)).intValue();
         int pageSize=20;
        Page page = drisearch(request, pageNo, pageSize);
			//dataBaseControl.getPageResultSet("select t.*,b.name,m.branchname from gm_carapply t,hr_base_info b,mrbranch m where t.driver = ?  and t.p_id = b.id and t.dept_id = m.id and t.carapply_state in('4','6','7') ", new Object[]{user.get("base_info_id")}, pageNo, pageSize);
		request.setAttribute("searchMap", getParameterMap(request));
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		
		buildDDL(request);
		return "/gm/carapply/gm_driver_carapplyList.jsp";
	}
	
	
	private Page drisearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map user = (Map)request.getSession().getAttribute("user");
		searchMap.put("driver", user.get("base_info_id"));
		String sql="select t.*,b.name,m.branchname from gm_carapply t,hr_base_info b,mrbranch m where  t.p_id = b.id and t.dept_id = m.id and t.carapply_state in('4','6','7') " 
		+ "/~ and t.driver = {driver} ~/"
		+ "/~ and b.name={name} ~/"
		+ "/~ and t.dept_id={dept_id} ~/"
		+ "/~ and m.branchname={branchname} ~/"
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.carapply_state = {carapply_state} ~/ "
		+"/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" 
		+"/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/"  
		+ " order by t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("carapply_state",codeTableUtil.getCodeMap("carapply_state")); 
	}

}