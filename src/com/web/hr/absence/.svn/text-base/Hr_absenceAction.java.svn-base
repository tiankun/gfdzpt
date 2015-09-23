package com.web.hr.absence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.phprpc.PHPRPC_Client;

import com.map.Fi_bill;
import com.map.Hr_absence;
import com.map.Hr_absence_audit;
import com.sysFrams.util.treeUtil;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;
import com.sysFrams.util.Constant;

public class Hr_absenceAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map map = getParameterMap(request);
		request.setAttribute("searchmap", map);
		
		map.put("pid", user.get("base_info_id"));
		Page page=search(map,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/absence/hr_absenceSelect.jsp";
		return "/hr/absence/hr_absenceList.jsp";
	}
	
	//人事查看请假情况
	public String checkList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map map = getParameterMap(request);
		request.setAttribute("searchmap", map);
		
		Page page=search(map,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		return "/hr/absence/checkList.jsp";
	}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(getParameterMap(request),1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select r.*,(select b.name from hr_base_info b where b.id = r.audit_id) person  from hr_absence t, hr_absence_audit r where t.id = r.absence_id and t.id = ?   order by r.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		return "/hr/absence/hr_absenceDtl.jsp";
	}
	private Page search(Map searchMap,int pageNo,int pageSize) throws Exception 
	{
		String sql="select t.*, r.name from hr_absence t, hr_base_info r where 1 = 1 and t.p_id = r.id " 
		+ "/~ and (t.dept_id in (select s1.dataid from sys_data_scope s1 where s1.roleid in (select ss1.rolesid from sysuserroles ss1 where ss1.userid = {sysuserid1} ) and s1.data_scope_type = 0) or t.p_id in (select s2.dataid from sys_data_scope s2 where s2.roleid in (select ss2.rolesid from sysuserroles ss2 where ss2.userid = {sysuserid2} ) and s2.data_scope_type = 1)) ~/  "
		+ "/~ and t.p_id={pid} ~/"
		+ "/~ and t.p_id!={auditpid} ~/"
		+ "/~ and t.dept_id={dept_id} ~/"
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.absence_type={absence_type} ~/ "
		+ "/~ and begin_date>=to_date({begin_date},'yyyy-MM-dd')  ~/ "
		+ "/~ and end_date<=to_date({end_date},'yyyy-MM-dd')   ~/ "
		+ "/~ and absence_state={absence_state} ~/ "
		+ " order by t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map user = (Map)request.getSession().getAttribute("user");
			Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
            
            Hr_absence absence = new Hr_absence();
			Map map = getParameterMap(request);
			BeanUtils.populate(absence, map);
			String sqj = request.getParameter("sqj");
			String eqj = request.getParameter("eqj");
			float days = 0 ;
			Map tmap = dataBaseControl.getOneResultSet("select to_date(?,'yyyy-MM-dd')-to_date(?,'yyyy-MM-dd') days from dual", new Object[]{request.getParameter("end_date"),request.getParameter("begin_date")});
			Map fmap = dataBaseControl.getOneResultSet("select count(*)days from hr_vacation_arrangement t where t.vacation_date >= to_date(?, 'yyyy-MM-dd') and t.vacation_date <=  to_date(?, 'yyyy-MM-dd') ", new Object[]{request.getParameter("begin_date"),request.getParameter("end_date")});
			days = Float.parseFloat(tmap.get("days").toString())+1;
			days = days - Float.parseFloat(fmap.get("days").toString());
			
			if(sqj.equals("1")&&eqj.equals("1"))
			{
				days = days - 0.5f;
			}
			
			if(sqj.equals("0")&&eqj.equals("1"))
			{
				days = days - 1f;
			}
			
			if(sqj.equals("0")&&eqj.equals("0"))
			{
				days = days - 0.5f;
			}
			
			if(sqj.equals("1"))
			{
				absence.setBam("1");
				absence.setBpm("0");
			}
			
			if(sqj.equals("0"))
			{
				absence.setBam("0");
				absence.setBpm("1");
			}
			
			if(eqj.equals("1"))
			{
				absence.setEam("1");
				absence.setEpm("0");
			}
			
			if(eqj.equals("0"))
			{
				absence.setEam("0");
				absence.setEpm("1");
			}
			
			String duty_id = baseinfo.get("duty_id").toString();  //职务id
			String dept_id = baseinfo.get("dept_id").toString();//部门
			String num = "";
			
			String state = request.getParameter("state");
			
			if(state.equals("0"))
			{
				absence.setAbsence_state("0");
			}
			else
			{
				//发短信，获取短信号码
				if(duty_id.equals("1"))//普通职员请假
				{
					absence.setAbsence_state("1");
					if(dept_id.equals("10"))
					{
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 10", null);
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
					else
					{
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
				}
				else //部门经理、崔总、向总请假
				{
					absence.setAbsence_state("2");
					if(dept_id.equals("4"))  //软件部
					{
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 5 ", null);
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
					else  //其他部门
					{
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 4 ", null);
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
				}
				
				
				/*
				List plist = dataBaseControl.getOutResultSet("select * from sys_message t where t.dept_id = ? and t.code = 'absence' order by t.id ", new Object[]{dept_id});
				if(plist != null && !plist.isEmpty())
				{
					if(plist.size()==2) //正常审批流程   提交-->待部门经理审批-->公司领导审批
					{
						if(duty_id.equals("2")||duty_id.equals("5"))//部门经理提交请假申请
						{
							absence.setAbsence_state("2");
							Map map2 = (Map)plist.toArray()[1];	
							num = map2.get("mobile_num").toString();
						}
						else 
						{
							absence.setAbsence_state("1");
							Map map2 = (Map)plist.toArray()[0];	
							num = map2.get("mobile_num").toString();
						}	
					}
					else   //特殊情况
					{
						if(duty_id.equals("4"))//崔总提交请假
						{
							absence.setAbsence_state("2");
							Map map2 = (Map)plist.toArray()[0];	
							num = map2.get("mobile_num").toString();
						}
						
						if(dept_id.equals("10"))//客户部
						{
							absence.setAbsence_state("1");
							Map map2 = (Map)plist.toArray()[0];	
							num = map2.get("mobile_num").toString();
						}
					}
				}*/
			}
			

			if(days>185)
			{
				this.doResult(response, "请假天数超过规定最大天数");
				return null;
			}
			
			absence.setP_id(new Long(baseinfo.get("id").toString()));
			absence.setDept_id(new Long(baseinfo.get("dept_id").toString()));
			absence.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			absence.setDays(""+days);
			int i =dataBaseControl.insertByBean(absence);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			
			
			//发短信通知
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			String infor=baseinfo.get("name")+"提出请假申请，请假类型是"+absence.getAbsence_type()+",请假天数是"+absence.getDays()+",请您审批！";
			m.sendGFDZ(num,infor);
			
			
		}
		buildDDL(request);
		Map map = new HashMap();
		map.put("bam","1");
		map.put("epm","1");
		request.setAttribute("record", map);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/absence/hr_absenceDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		String IsPostBack = request.getParameter("IsPostBack");
		if(type.equals("edit")) //添加
		{
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Hr_absence absence = new Hr_absence();
			Map map = getParameterMap(request);
			BeanUtils.populate(absence, map);
			String sqj = request.getParameter("sqj");
			String eqj = request.getParameter("eqj");
			float days = 0 ;
			Map tmap = dataBaseControl.getOneResultSet("select to_date(?,'yyyy-MM-dd')-to_date(?,'yyyy-MM-dd') days from dual", new Object[]{request.getParameter("end_date"),request.getParameter("begin_date")});
			Map fmap = dataBaseControl.getOneResultSet("select count(*)days from hr_vacation_arrangement t where t.vacation_date >= to_date(?, 'yyyy-MM-dd') and t.vacation_date <=  to_date(?, 'yyyy-MM-dd') ", new Object[]{request.getParameter("begin_date"),request.getParameter("end_date")});
			days = Float.parseFloat(tmap.get("days").toString())+1;
			days = days - Float.parseFloat(fmap.get("days").toString());
			
			if(sqj.equals("1")&&eqj.equals("1"))
			{
				days = days - 0.5f;
			}
			
			if(sqj.equals("0")&&eqj.equals("1"))
			{
				days = days - 1f;
			}
			
			if(sqj.equals("0")&&eqj.equals("0"))
			{
				days = days - 0.5f;
			}
			
			if(sqj.equals("1"))
			{
				absence.setBam("1");
				absence.setBpm("0");
			}
			
			if(sqj.equals("0"))
			{
				absence.setBam("0");
				absence.setBpm("1");
			}
			
			if(eqj.equals("1"))
			{
				absence.setEam("1");
				absence.setEpm("0");
			}
			
			if(eqj.equals("0"))
			{
				absence.setEam("0");
				absence.setEpm("1");
			}
			
			
			String duty_id = baseinfo.get("duty_id").toString();  //职务id
			String dept_id = baseinfo.get("dept_id").toString();//部门
			
			
            String state = request.getParameter("state");
            String num = "";
			if(state.equals("0"))
			{
				absence.setAbsence_state("0");
			}
			else
			{
				//发短信，获取短信号码
				if(duty_id.equals("1"))//普通职员请假
				{
					absence.setAbsence_state("1");
					if(dept_id.equals("10"))
					{
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 10", null);
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
					else
					{
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
				}
				else //部门经理、崔总、向总请假
				{
					absence.setAbsence_state("2");
					if(dept_id.equals("4"))  //软件部
					{
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 5 ", null);
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
					else  //其他部门
					{
						List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 4 ", null);
						if(slist!=null&&!slist.isEmpty())
						{
							Map smap = (Map)slist.get(0);
							num = smap.get("num").toString();
						}
					}
				}
				
			}
			
			if(days>185)
			{
				this.doResult(response, "请假天数超过规定最大天数");
				return null;
			}

			absence.setP_id(new Long(baseinfo.get("id").toString()));
			absence.setDept_id(new Long(baseinfo.get("dept_id").toString()));
			absence.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			absence.setDays(""+days);
			absence.setId(new Long(request.getParameter("id")));
			
			int i = dataBaseControl.updateByBean(absence);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			
			//发短信通知
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			String infor=baseinfo.get("name")+"提出请假申请，请假类型是"+absence.getAbsence_type()+",请假天数是"+absence.getDays()+",请您审批！";
			m.sendGFDZ(num,infor);
			
			
			
		}else{
			Page page=search(getParameterMap(request),1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		}
		//////////////////////////////////////////////////审批
		if(type.equals("audit"))
		{
			
			if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
				Hr_absence absence = new Hr_absence();
				String id = request.getParameter("id");
				
				Map map = dataBaseControl.getOneResultSet("select * from hr_absence t where t.id = ?", new Object[]{id});
				BeanUtils.populate(absence, map);
				String opinionid = request.getParameter("opinionid");
				String state = absence.getAbsence_state();
				String shstate = "1";
				String dept_id = absence.getDept_id().toString();
				
				Hr_absence_audit audit = new Hr_absence_audit();
				
				Map applyp = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{absence.getP_id()});
				String pinfo = "";
				
				
				if(state.equals("1"))
				{
					if(opinionid.equals("1"))  //部门经理同意
					{
						Map absTypeMap = dataBaseControl.getOneResultSet("select * from hr_absence_standard t  where t.parent_id = 0 start with t.absence_type = ? connect by prior  parent_id = id ", new Object[]{absence.getAbsence_type()});
						String absType = absTypeMap.get("id").toString();
						float days = Float.parseFloat(absence.getDays());
						if(dept_id.equals("9")||dept_id.equals("10"))
						{
							absence.setAbsence_state("3");
							shstate = "3";
							pinfo = "您的请假申请已批准";
						}
						else
						{
							if(!absType.equals("3")&&days<=3f)//非带薪假且小于等于3天
							{
								absence.setAbsence_state("3");
								shstate = "3";
								pinfo = "您的请假申请已批准";
							}
							else
							{
								absence.setAbsence_state("2");
								shstate = "2";
								String num = "";
								if(dept_id.equals("4"))  //软件部
								{
									List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 5 ", null);
									if(slist!=null&&!slist.isEmpty())
									{
										Map smap = (Map)slist.get(0);
										num = smap.get("num").toString();
									}
								}
								else  //其他部门
								{
									List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 4 ", null);
									if(slist!=null&&!slist.isEmpty())
									{
										Map smap = (Map)slist.get(0);
										num = smap.get("num").toString();
									}
								}
								
								PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
								Mas m = (Mas) client.useService(Mas.class);
								String infor=applyp.get("name")+"提交请假申请，请假类型是"+absence.getAbsence_type()+",请假天数是"+absence.getDays()+",请您审批！";
								if(!num.equals(""))
								{
									m.sendGFDZ(num,infor);
								}
							}
						}
						
					}
					else  //部门经理打回
					{
						absence.setAbsence_state("4");
						shstate = "4";
						pinfo = "您的请假申请未被部门经理批准";
					}
					
				}
				
				if(state.equals("2"))
				{
					if(opinionid.equals("1"))  //同意
					{
						absence.setAbsence_state("3");
						shstate = "3";
						pinfo = "您的请假申请已批准";
					}
					else  //公司经理打回
					{
						absence.setAbsence_state("4");
						shstate = "4";
						pinfo = "您的请假申请未被公司领导批准";
					}
					
				}

				absence.setOpinion(request.getParameter("opinion"));
				int i = dataBaseControl.updateByBean(absence);
				
				audit.setAbsence_id(absence.getId());
				audit.setAudit_date(new java.sql.Date((new java.util.Date()).getTime()));
				audit.setAudit_id(new Long(baseinfo.get("id").toString()));
				audit.setOpinion(request.getParameter("opinion"));
				audit.setState(shstate);
				dataBaseControl.insertByBean(audit);
				if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
				
				//发短信通知
				String nump = "";
				nump = applyp.get("phone") +"";
				PHPRPC_Client client1 = new PHPRPC_Client(Constant.HTTPDRESS);
				Mas m1 = (Mas) client1.useService(Mas.class);
				if(!pinfo.equals(""))
				{
					m1.sendGFDZ(nump,pinfo);
				}
				
				
				
			}else{
				//获得审批意见
				List splist = dataBaseControl.getOutResultSet("select r.*,(select b.name from hr_base_info b where b.id = r.audit_id) person  from hr_absence t, hr_absence_audit r where t.id = r.absence_id and t.id = ?   order by r.id ", new Object[]{request.getParameter("id")});
				request.setAttribute("splist", splist);
				
				Page page=search(getParameterMap(request),1,1);
				ArrayList<Map> record= (ArrayList)page.getThisPageElements();
				request.setAttribute("record", record.get(0));
			}
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/absence/hr_absenceDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Hr_absence WHERE ID in (?)", new Object[]{s_id});
		
		return "/hr/absence/Hr_absence!searchList.do";
	}
	
	/**
	 * 部门经理审批列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String auditList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		? "1" : request.getParameter("pageno");
         int pageNo=(new Integer(page_no)).intValue();
         int pageSize=20;

          Map user = (Map)request.getSession().getAttribute("user");
          Map map = getParameterMap(request);
          
          
         String state = "";
         String state1 = "";
  		 Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
  		 String duty_id = baseinfo.get("duty_id").toString();
  		 
  		 String forward = "";
  		 
  		 if(duty_id.equals("2")||duty_id.equals("5"))//部门经理
  		 {
  			 state = "1";
  			 String absence_state = request.getParameter("absence_state")==null?"1":request.getParameter("absence_state");
  			 map.put("absence_state",absence_state);
  			 forward = "/hr/absence/hr_manageaudit_absenceList.jsp";
  		 }
  		 
  		if(duty_id.equals("3"))//赵总
		 {
			 state = "2";
			 String absence_state = request.getParameter("absence_state")==null?"2":request.getParameter("absence_state");
  			 map.put("absence_state",absence_state);
			 forward = "/hr/absence/hr_manageaudit_absenceList.jsp";
		 }
  		 
  		 
  		if(duty_id.equals("4"))//崔总
 		 {
 			 state = "2";
 			 state1 = "1";
 			forward = "/hr/absence/hr_finalaudit_absenceList.jsp";
 		 }
  		


        map.put("sysuserid1", user.get("id"));
        map.put("sysuserid2", user.get("id"));
        map.put("auditpid", user.get("base_info_id"));
        Page page=search(map,pageNo,pageSize);

        request.setAttribute("record", page.getThisPageElements());
        request.setAttribute("page", page);
        buildDDL(request);
  		
  		
  		request.setAttribute("searchmap", map);
  		
  		 request.setAttribute("state", state);
  		request.setAttribute("state1", state1);
  		 
          if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
	           && request.getParameter("pageType").equals("select")) 
	         return "/hr/absence/hr_absenceSelect.jsp";
        return forward;
	}
	
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		
		Map map = (Map)dataBaseControl.getOneResultSet("select * from hr_absence t where t.id = ? ", new Object[]{id});
		Hr_absence absence = new Hr_absence();
		BeanUtils.populate(absence, map);
		
		
		String duty_id = baseinfo.get("duty_id").toString();  //职务id
		String dept_id = baseinfo.get("dept_id").toString();//部门
		
		//发短信，获取短信号码
		String num = "";
		List plist = dataBaseControl.getOutResultSet("select * from sys_message t where t.dept_id = ? and t.code = 'absence' order by t.id ", new Object[]{dept_id});
		
		
		//发短信，获取短信号码
		if(duty_id.equals("1"))//普通职员请假
		{
			absence.setAbsence_state("1");
			if(dept_id.equals("10"))
			{
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 10", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
			else
			{
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
		}
		else //部门经理、崔总、向总请假
		{
			absence.setAbsence_state("2");
			if(dept_id.equals("4"))  //软件部
			{
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 5 ", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
			else  //其他部门
			{
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 4 ", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
		}
		
		dataBaseControl.updateByBean(absence);
		PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
		Mas m = (Mas) client.useService(Mas.class);
		String infor=baseinfo.get("name")+"提出请假申请，请假类型是"+absence.getAbsence_type()+",请假天数是"+absence.getDays()+",请您审批！";
		m.sendGFDZ(num,infor);
		
		
		return "/hr/absence/Hr_absence!searchList.do";
	}
	
	
    private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("absence_state",codeTableUtil.getCodeMap("absence_state")); 
	}

}