package com.web.gm.carapply;

import java.math.BigDecimal;
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

import com.map.Gm_carapply;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Gm_carapplyAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map map = getParameterMap(request);
		//map.put("p_id", user.get("base_info_id"));
		map.put("usetype", "1");
		
		request.setAttribute("usetype", "1");
		
		Page page=search(map,pageNo,pageSize);
		request.setAttribute("applyid",  user.get("base_info_id"));
		request.setAttribute("searchMap", getParameterMap(request));
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/gm/carapply/gm_carapplySelect.jsp";
		return "/gm/carapply/gm_carapplyList.jsp";
	}
	
	
	public String searchList1(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map map = getParameterMap(request);
		//map.put("p_id", user.get("base_info_id"));
		
		map.put("usetype", "0");
		request.setAttribute("usetype", "0");
		
		Page page=search(map,pageNo,pageSize);
		request.setAttribute("applyid",  user.get("base_info_id"));
		request.setAttribute("searchMap", getParameterMap(request));
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/gm/carapply/gm_carapplySelect.jsp";
		return "/gm/carapply/gm_carapplyList.jsp";
	}
	
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(getParameterMap(request),1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select r.*,(select b.name from hr_base_info b where b.id = r.audit_id) person from gm_carapply t, gm_carapply_audit r where t.id = r.apply_id and t.id = ?  order by r.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		
		return "/gm/carapply/gm_carapplyDtl.jsp";
	}
	private Page search(Map searchMap,int pageNo,int pageSize) throws Exception 
	{
		
		String sql="select t.*,m.branchname,b.name from Gm_carapply t,mrbranch m,hr_base_info b  where t.dept_id = m.id and 1=1 and t.p_id = b.id " +
				"/~ and t.id={id} ~/" +
				"/~ and t.p_id={p_id} ~/" +
				"/~ and t.usetype={usetype} ~/" +
				"/~ and t.carapply_state = {carapply_state} ~/" +
				"/~ and t.input_date >= to_date({input_date1},'yyyy-MM-dd') ~/" +
				"/~ and t.input_date <= to_date({input_date2},'yyyy-MM-dd') ~/" +
		        " order by t.carapply_state,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		request.setAttribute("usetype", request.getParameter("usetype"));
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map user = (Map)request.getSession().getAttribute("user");
			Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
			String duty_id = baseinfo.get("duty_id").toString();
			String dept_id = baseinfo.get("dept_id").toString();
			
			Date date = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			
			Gm_carapply carapply = new Gm_carapply();
			BeanUtils.populate(carapply, getParameterMap(request));
			carapply.setP_id(new BigDecimal(baseinfo.get("id").toString()));
			carapply.setDept_id(new BigDecimal(baseinfo.get("dept_id").toString()));
			carapply.setInput_date(new java.sql.Timestamp((fmt.parse(fmt.format(date))).getTime()));
			
            String state = request.getParameter("state");
            String num = "";
			
			if(state.equals("0"))
			{
				carapply.setCarapply_state("0");
			}
			else
			{
				//用车申请发短信
				//发短信，获取短信号码
				if(duty_id.equals("1"))//普通职员请假
				{
					
					if(dept_id.equals("3"))  //综合办职员提用车申请
					{
						carapply.setCarapply_state("2");
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
					else  //其他部门提出用车申请
					{
						carapply.setCarapply_state("1");
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
				}
				else //部门经理、崔总用车
				{
					carapply.setCarapply_state("2");
					List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 3 ", null);
					if(slist!=null&&!slist.isEmpty())
					{
						Map smap = (Map)slist.get(0);
						num = smap.get("num").toString();
					}
					
				}
			
			}
			
			int i =dataBaseControl.insertByBean(carapply);	
			
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			String infor=baseinfo.get("name")+"提出用车申请，请您审批！";
			m.sendGFDZ(num,infor);
			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/gm/carapply/gm_carapplyDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map1 = dataBaseControl.getOneResultSet("select * from gm_carapply t where t.id = ?", new Object[]{id});
			Gm_carapply carapply = new Gm_carapply();
			BeanUtils.populate(carapply, map1);
			carapply.setPlan_sdate(request.getParameter("plan_sdate"));
			carapply.setPlan_edate(request.getParameter("plan_edate"));
			carapply.setReason(request.getParameter("reason"));
			carapply.setRoad(request.getParameter("road"));
			
			
			String state = request.getParameter("state");
	        String num = "";
				
		    if(state.equals("0"))
			{
				carapply.setCarapply_state("0");
			}
			else
			{
				
				Map user = (Map)request.getSession().getAttribute("user");
				Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
				String duty_id = baseinfo.get("duty_id").toString();
				String dept_id = baseinfo.get("dept_id").toString();
				
				
				//用车申请发短信
				
				//发短信，获取短信号码
				if(duty_id.equals("1"))//普通职员请假
				{
					
					if(dept_id.equals("3"))  //综合办职员提用车申请
					{
						carapply.setCarapply_state("2");
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
					else  //其他部门提出用车申请
					{
						carapply.setCarapply_state("1");
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
				}
				else //部门经理、崔总用车
				{
					carapply.setCarapply_state("2");
					List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 3 ", null);
					if(slist!=null&&!slist.isEmpty())
					{
						Map smap = (Map)slist.get(0);
						num = smap.get("num").toString();
					}
					
				}
				
				
				
			}
			
			
			
			
			
			int i = dataBaseControl.updateByBean(carapply);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(getParameterMap(request),1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/gm/carapply/gm_carapplyDtl.jsp";
	}
	
	
	public String check(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select * from gm_carapply t where t.id = ?", new Object[]{id});
			Gm_carapply carapply = new Gm_carapply();
			BeanUtils.populate(carapply, map);
			
			carapply.setCarapply_state("7");
			
			
			int i = dataBaseControl.updateByBean(carapply);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(getParameterMap(request),1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select r.*,(select b.name from hr_base_info b where b.id = r.audit_id) person from gm_carapply t, gm_carapply_audit r where t.id = r.apply_id and t.id = ?  order by r.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		return "/gm/carapply/gm_carapplyDtl.jsp";
	}
	
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Gm_carapply WHERE ID in (?)", new Object[]{s_id});
		
		return "/gm/carapply/Gm_carapply!searchList.do";
	}
	
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfo.get("duty_id").toString();
		String dept_id = baseinfo.get("dept_id").toString();
		
		Map map1 = dataBaseControl.getOneResultSet("select * from gm_carapply t where t.id = ?", new Object[]{id});
		
		Gm_carapply carapply = new Gm_carapply();
		BeanUtils.populate(carapply, map1);
		String num = "";
		

		//用车申请发短信
		
		//查询部门经理手机号码
		//发短信，获取短信号码
		if(duty_id.equals("1"))//普通职员请假
		{
			
			if(dept_id.equals("3"))  //综合办职员提用车申请
			{
				carapply.setCarapply_state("2");
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
			else  //其他部门提出用车申请
			{
				carapply.setCarapply_state("1");
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
		}
		else //部门经理、崔总用车
		{
			carapply.setCarapply_state("2");
			List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 3 ", null);
			if(slist!=null&&!slist.isEmpty())
			{
				Map smap = (Map)slist.get(0);
				num = smap.get("num").toString();
			}
			
		}
		dataBaseControl.updateByBean(carapply);
		
		PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
		Mas m = (Mas) client.useService(Mas.class);
		String infor=baseinfo.get("name")+"提出用车申请，请您审批！";
		m.sendGFDZ(num,infor);
		
		
		
		return "/gm/carapply/Gm_carapply!searchList.do";
	}
	
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("carapply_state",codeTableUtil.getCodeMap("carapply_state")); 
	}

}