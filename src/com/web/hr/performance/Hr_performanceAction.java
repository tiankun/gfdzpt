package com.web.hr.performance;

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

import com.map.Hr_achieve;
import com.map.Hr_achieve_cwb;
import com.map.Hr_achieve_gcb;
import com.map.Hr_achieve_khb;
import com.map.Hr_achieve_rjb;
import com.map.Hr_achieve_rsb;
import com.map.Hr_achieve_sjb;
import com.map.Hr_achieve_zc;
import com.map.Hr_achieve_zhb;
import com.map.Hr_kaoqin_audit;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Hr_performanceAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public void displaymonth(HttpServletRequest request,HttpServletResponse response) throws Exception{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdate = fmt.format(date);
		
		String nian = sdate.substring(0,4);
		String yue = sdate.substring(5,7);
		//当月
		Map map1 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),1),'yyyy-MM') khdate from dual", new Object[]{nian+"-"+yue});
		//前一月
		Map map2 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),0),'yyyy-MM') khdate from dual", new Object[]{nian+"-"+yue});
		//前二月
		Map map3 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),-1),'yyyy-MM') khdate from dual", new Object[]{nian+"-"+yue});
		//前三月
		Map map6 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),-2),'yyyy-MM') khdate from dual", new Object[]{nian+"-"+yue});
		
		//下月
		Map map4 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),2),'yyyy-MM') khdate from dual", new Object[]{nian+"-"+yue});
		
		List list = new ArrayList();
		list.add(map6);
		list.add(map3);
		list.add(map2);
		list.add(map1);
		list.add(map4);
		
		request.setAttribute("datelist", list);
	}
	
	
	
	/**
	 * 查询本部门人员
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String searchPersonList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.displaymonth(request, response);
		String audit_date = request.getParameter("audit_date");
		Map map = new HashMap();
		map.put("audit_date", audit_date);
		request.setAttribute("searchMap", map);
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfo.get("duty_id").toString();
		String dept_id = baseinfo.get("dept_id").toString();
		
		List plist = new ArrayList();
		
		if(duty_id.equals("2"))
		{
			plist = dataBaseControl.getOutResultSet("select * from hr_base_info t where t.hr_type in ('2','3','6') and t.dept_id = ? and t.duty_id =1 order by t.id desc", new Object[]{user.get("branchid").toString()});
			
		}
		
		if(duty_id.equals("3"))//总经理
		{
			plist = dataBaseControl.getOutResultSet("select * from hr_base_info t where t.hr_type in ('2','3','6') and  t.duty_id in('2','4','5') and t.id != 87", null);
			
		}
		
		if(duty_id.equals("4"))//崔
		{
			plist = dataBaseControl.getOutResultSet("select * from hr_base_info t where t.hr_type in ('2','3','6') and  t.dept_id in (10,5) and t.duty_id = 1", null);
			
		}
		
		if(duty_id.equals("5"))//向
		{
			plist = dataBaseControl.getOutResultSet("select * from hr_base_info t where t.hr_type in ('2','3','6') and  t.dept_id = 9 and t.duty_id = 1", null);
			
		}
		String forward = "";
		
		if(!duty_id.equals("3"))
		{
			if(dept_id.equals("4")) //软件部
			{
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				if(achievemap == null || achievemap.isEmpty())
				{
					Hr_achieve achieve = new Hr_achieve();
					achieve.setAudit_date(audit_date);
					achieve.setAudit_person(new Long(user.get("base_info_id").toString()));
					achieve.setDept_id(new Long(dept_id));
					achieve.setState("0");
					achieve.setType("0");
					achieve.setCount(new Long(plist.size()));
					dataBaseControl.insertByBean(achieve);
					Map fmap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = '0'", new Object[]{dept_id,audit_date});
					
					for(int i = 0 ; i < plist.size() ; i++)
					{
						Map pmap = (Map)plist.toArray()[i];
						Hr_achieve_rjb achieve_rjb = new Hr_achieve_rjb();
						achieve_rjb.setAchieve_date(audit_date);
						achieve_rjb.setF_id(new Long(fmap.get("id").toString()));
						achieve_rjb.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
						achieve_rjb.setP_id(new Long(pmap.get("id").toString()));
						dataBaseControl.insertByBean(achieve_rjb);
					}
				}
				List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,b.sex,b.birthday,(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))total from hr_achieve t,hr_achieve_rjb r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = ? and t.audit_date = ? and t.type = '0' order by r.p_id", new Object[]{dept_id,audit_date});
				request.setAttribute("reslist", khlist);	
				Map achievemap1 = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				
				request.setAttribute("achievemap", achievemap1);
				
			}
			
			if(dept_id.equals("3")) //综合部
			{
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				if(achievemap == null || achievemap.isEmpty())
				{
					Hr_achieve achieve = new Hr_achieve();
					achieve.setAudit_date(audit_date);
					achieve.setAudit_person(new Long(user.get("base_info_id").toString()));
					achieve.setDept_id(new Long(dept_id));
					achieve.setState("0");
					achieve.setType("0");
					achieve.setCount(new Long(plist.size()));
					dataBaseControl.insertByBean(achieve);
					Map fmap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = '0'", new Object[]{dept_id,audit_date});
					
					for(int i = 0 ; i < plist.size() ; i++)
					{
						Map pmap = (Map)plist.toArray()[i];
						Hr_achieve_zhb achieve_zhb = new Hr_achieve_zhb();
						achieve_zhb.setAchieve_date(audit_date);
						achieve_zhb.setF_id(new Long(fmap.get("id").toString()));
						achieve_zhb.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
						achieve_zhb.setP_id(new Long(pmap.get("id").toString()));
						dataBaseControl.insertByBean(achieve_zhb);
					}
				}
				
				List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,b.sex,b.birthday,(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))total from hr_achieve t,hr_achieve_zhb r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = ? and t.audit_date = ? and t.type = '0' order by r.p_id desc", new Object[]{dept_id,audit_date});
				request.setAttribute("reslist", khlist);	
				Map achievemap1 = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				
				request.setAttribute("achievemap", achievemap1);
			}
			
			
			if(dept_id.equals("6")) //财务部
			{
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				if(achievemap == null || achievemap.isEmpty())
				{
					Hr_achieve achieve = new Hr_achieve();
					achieve.setAudit_date(audit_date);
					achieve.setAudit_person(new Long(user.get("base_info_id").toString()));
					achieve.setDept_id(new Long(dept_id));
					achieve.setState("0");
					achieve.setType("0");
					achieve.setCount(new Long(plist.size()));
					dataBaseControl.insertByBean(achieve);
					Map fmap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = '0'", new Object[]{dept_id,audit_date});
					
					for(int i = 0 ; i < plist.size() ; i++)
					{
						Map pmap = (Map)plist.toArray()[i];
						Hr_achieve_cwb achieve_cwb = new Hr_achieve_cwb();
						achieve_cwb.setAchieve_date(audit_date);
						achieve_cwb.setF_id(new Long(fmap.get("id").toString()));
						achieve_cwb.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
						achieve_cwb.setP_id(new Long(pmap.get("id").toString()));
						dataBaseControl.insertByBean(achieve_cwb);
					}
				}
				
				List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,b.sex,b.birthday,((nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)))total from hr_achieve t,hr_achieve_cwb r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = ? and t.audit_date = ? and t.type = '0' order by r.p_id desc", new Object[]{dept_id,audit_date});
				request.setAttribute("reslist", khlist);	
				Map achievemap1 = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				
				request.setAttribute("achievemap", achievemap1);
			}
			
			if(dept_id.equals("2")) //人事部
			{
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				if(achievemap == null || achievemap.isEmpty())
				{
					Hr_achieve achieve = new Hr_achieve();
					achieve.setAudit_date(audit_date);
					achieve.setAudit_person(new Long(user.get("base_info_id").toString()));
					achieve.setDept_id(new Long(dept_id));
					achieve.setState("0");
					achieve.setType("0");
					achieve.setCount(new Long(plist.size()));
					dataBaseControl.insertByBean(achieve);
					Map fmap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = '0'", new Object[]{dept_id,audit_date});
					
					for(int i = 0 ; i < plist.size() ; i++)
					{
						Map pmap = (Map)plist.toArray()[i];
						Hr_achieve_rsb achieve_rsb = new Hr_achieve_rsb();
						achieve_rsb.setAchieve_date(audit_date);
						achieve_rsb.setF_id(new java.math.BigDecimal(fmap.get("id").toString()));
						achieve_rsb.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
						achieve_rsb.setP_id(new java.math.BigDecimal(pmap.get("id").toString()));
						dataBaseControl.insertByBean(achieve_rsb);
					}
				}
				
				List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,b.sex,b.birthday,(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))total from hr_achieve t,hr_achieve_rsb r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = ? and t.audit_date = ? and t.type = '0' order by r.p_id desc", new Object[]{dept_id,audit_date});
				request.setAttribute("reslist", khlist);
				Map achievemap1 = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				
				request.setAttribute("achievemap", achievemap1);
			}
			
			if(dept_id.equals("10")) //总经办
			{
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				if(achievemap == null || achievemap.isEmpty())
				{
					Hr_achieve achieve = new Hr_achieve();
					achieve.setAudit_date(audit_date);
					achieve.setAudit_person(new Long(user.get("base_info_id").toString()));
					achieve.setDept_id(new Long(dept_id));
					achieve.setState("0");
					achieve.setType("0");
					achieve.setCount(new Long(plist.size()));
					dataBaseControl.insertByBean(achieve);
					Map fmap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = '0'", new Object[]{dept_id,audit_date});
					
					for(int i = 0 ; i < plist.size() ; i++)
					{
						Map pmap = (Map)plist.toArray()[i];
						Hr_achieve_khb achieve_khb = new Hr_achieve_khb();
						achieve_khb.setAchieve_date(audit_date);
						achieve_khb.setF_id(new java.math.BigDecimal(fmap.get("id").toString()));
						achieve_khb.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
						achieve_khb.setP_id(new java.math.BigDecimal(pmap.get("id").toString()));
						dataBaseControl.insertByBean(achieve_khb);
					}
				}
				
				List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,b.sex,b.birthday,(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))total from hr_achieve t,hr_achieve_khb r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = ? and t.audit_date = ? and t.type = '0' order by r.p_id desc", new Object[]{dept_id,audit_date});
				request.setAttribute("reslist", khlist);	
				Map achievemap1 = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				
				request.setAttribute("achievemap", achievemap1);
			}
			
			if(dept_id.equals("8")) //技术办
			{
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				if(achievemap == null || achievemap.isEmpty())
				{
					Hr_achieve achieve = new Hr_achieve();
					achieve.setAudit_date(audit_date);
					achieve.setAudit_person(new Long(user.get("base_info_id").toString()));
					achieve.setDept_id(new Long(dept_id));
					achieve.setState("0");
					achieve.setType("0");
					achieve.setCount(new Long(plist.size()));
					dataBaseControl.insertByBean(achieve);
					Map fmap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = '0'", new Object[]{dept_id,audit_date});
					
					for(int i = 0 ; i < plist.size() ; i++)
					{
						Map pmap = (Map)plist.toArray()[i];
						Hr_achieve_sjb achieve_sjb = new Hr_achieve_sjb();
						achieve_sjb.setAchieve_date(audit_date);
						achieve_sjb.setF_id(new java.math.BigDecimal(fmap.get("id").toString()));
						achieve_sjb.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
						achieve_sjb.setP_id(new java.math.BigDecimal(pmap.get("id").toString()));
						dataBaseControl.insertByBean(achieve_sjb);
					}
				}
				
				List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,b.sex,b.birthday,(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)))total from hr_achieve t,hr_achieve_sjb r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = ? and t.audit_date = ? and t.type = '0' order by r.p_id desc", new Object[]{dept_id,audit_date});
				request.setAttribute("reslist", khlist);	
				Map achievemap1 = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				
				request.setAttribute("achievemap", achievemap1);
			}
			
			
			if(dept_id.equals("7")) //工程部
			{
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				if(achievemap == null || achievemap.isEmpty())
				{
					Hr_achieve achieve = new Hr_achieve();
					achieve.setAudit_date(audit_date);
					achieve.setAudit_person(new Long(user.get("base_info_id").toString()));
					achieve.setDept_id(new Long(dept_id));
					achieve.setState("0");
					achieve.setType("0");
					achieve.setCount(new Long(plist.size()));
					dataBaseControl.insertByBean(achieve);
					Map fmap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = '0'", new Object[]{dept_id,audit_date});
					
					for(int i = 0 ; i < plist.size() ; i++)
					{
						Map pmap = (Map)plist.toArray()[i];
						Hr_achieve_gcb achieve_gcb = new Hr_achieve_gcb();
						achieve_gcb.setAchieve_date(audit_date);
						achieve_gcb.setF_id(new java.math.BigDecimal(fmap.get("id").toString()));
						achieve_gcb.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
						achieve_gcb.setP_id(new java.math.BigDecimal(pmap.get("id").toString()));
						dataBaseControl.insertByBean(achieve_gcb);
					}
				}
				
				List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,b.sex,b.birthday,(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))total from hr_achieve t,hr_achieve_gcb r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = ? and t.audit_date = ? and t.type = '0' order by r.p_id desc", new Object[]{dept_id,audit_date});
				request.setAttribute("reslist", khlist);	
				Map achievemap1 = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,audit_date,"0"});
				
				request.setAttribute("achievemap", achievemap1);
			}
			
			
			forward = "/hr/performance/hr_kaohe_performanceList.jsp";
			
		}
		else   //总经理
		{
			
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{0,audit_date,"1"});
				if(achievemap == null || achievemap.isEmpty())
				{
					Hr_achieve achieve = new Hr_achieve();
					achieve.setAudit_date(audit_date);
					achieve.setAudit_person(new Long(user.get("base_info_id").toString()));
					achieve.setDept_id(new Long(0));
					achieve.setState("0");
					achieve.setType("1");
					achieve.setCount(new Long(plist.size()));
					dataBaseControl.insertByBean(achieve);
					Map fmap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = '1'", new Object[]{0,audit_date});
					
					for(int i = 0 ; i < plist.size() ; i++)
					{
						Map pmap = (Map)plist.toArray()[i];
						Hr_achieve_zc achieve_zc = new Hr_achieve_zc();
						achieve_zc.setAchieve_date(audit_date);
						achieve_zc.setF_id(new java.math.BigDecimal(fmap.get("id").toString()));
						achieve_zc.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
						achieve_zc.setP_id(new java.math.BigDecimal(pmap.get("id").toString()));
						dataBaseControl.insertByBean(achieve_zc);
					}
				}
				
				List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))item21,round((((nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))/280)*100),0)item22 from hr_achieve t,hr_achieve_zc r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = ? and t.audit_date = ? and t.type = '1' order by r.p_id desc", new Object[]{0,audit_date});
				request.setAttribute("reslist", khlist);	
				Map achievemap1 = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{0,audit_date,"1"});
				
				request.setAttribute("achievemap", achievemap1);
				dept_id = "0";
				forward = "/hr/performance/zc.jsp";
			
		}
		
		
		request.setAttribute("dept_id", dept_id);
		return forward;
	}
	
	
	
	
	
	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		this.displaymonth(request, response);
		
		Map searchMap = getParameterMap(request);
		String forward = "";
		
		Map user = (Map)request.getSession().getAttribute("user");
		String deptid = user.get("branchid").toString();
		searchMap.put("dept_id", deptid);
		String tablename = "";
		String total = "";
		Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfo.get("duty_id").toString();
		
		if(duty_id.equals("3"))  //赵总
		{
			Page page=search1(searchMap,pageNo,pageSize);
			
			searchMap.put("dept_id", "0");
			
			request.setAttribute("searchMap", searchMap);
			
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
			forward = "/hr/performance/hr_zcperformanceList.jsp";
		}
		else
		{
			if(deptid.equals("4"))   //软件部
			{
				tablename = "hr_achieve_rjb";
				total = "(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))";
			}
			if(deptid.equals("7"))   //工程部
			{
				tablename = "hr_achieve_gcb";
				total = "(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))";
			}
			if(deptid.equals("6"))   //财务部
			{
				tablename = "hr_achieve_cwb";
				total = "(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0))";
			}
			if(deptid.equals("10"))   //客户部
			{
				tablename = "hr_achieve_khb";
				total = "(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))";
			}
			if(deptid.equals("2"))   //人事
			{
				tablename = "hr_achieve_rsb";
				total = "(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))";
			}
			if(deptid.equals("3"))   //综合部
			{
				tablename = "hr_achieve_zhb";
				total = "(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)))";
			}
			if(deptid.equals("8"))   //技术部
			{
				tablename = "hr_achieve_sjb";
				total = "(100+(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)))";
			}
			
			Page page=search(tablename,total,searchMap,pageNo,pageSize);
			
			
			request.setAttribute("searchMap", searchMap);
			
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
			forward = "/hr/performance/hr_performanceList.jsp";
		}
		
		
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/absence/hr_kaoqinSelect.jsp";
		return forward;
	}
	
	
	public String toedit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String dept_id = request.getParameter("dept_id");
		String forward = "";
		request.setAttribute("dept_id", dept_id);
		
		if(dept_id.equals("4"))  //软件部
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_rjb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			request.setAttribute("state", achievemap.get("state"));
			request.setAttribute("record", map);
			forward = "/hr/performance/rjb.jsp";
		}
		
		if(dept_id.equals("7"))//工程部
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_gcb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			request.setAttribute("state", achievemap.get("state"));
			request.setAttribute("record", map);
			forward = "/hr/performance/gcb.jsp";
		}
		
		if(dept_id.equals("6"))//财务
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_cwb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			request.setAttribute("state", achievemap.get("state"));
			request.setAttribute("record", map);
			forward = "/hr/performance/cwb.jsp";
		}
		
		if(dept_id.equals("3"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name,r.remark from hr_achieve_zhb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			String remark = ""+map.get("remark");
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			request.setAttribute("state", achievemap.get("state"));
			request.setAttribute("record", map);
			if(remark.equals("1"))
			{
				forward = "/hr/performance/zhb1.jsp";
			}
			if(remark.equals("2"))
			{
				forward = "/hr/performance/zhb2.jsp";
			}
			if(remark.equals("3"))
			{
				forward = "/hr/performance/zhb3.jsp";
			}
			
		}
		
		if(dept_id.equals("2"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_rsb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			request.setAttribute("state", achievemap.get("state"));
			request.setAttribute("record", map);
			forward = "/hr/performance/rsb.jsp";
		}
		
		if(dept_id.equals("10"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_khb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			request.setAttribute("state", achievemap.get("state"));
			request.setAttribute("record", map);
			forward = "/hr/performance/khb.jsp";
		}
		
		if(dept_id.equals("8"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_sjb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			request.setAttribute("state", achievemap.get("state"));
			request.setAttribute("record", map);
			forward = "/hr/performance/sjb.jsp";
		}
		
		if(dept_id.equals("0")) //部门经理
		{
			List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))item21,round((((nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))/280)*100),0)item22 from hr_achieve t,hr_achieve_zc r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = 0 and t.id = ? and t.type = '1' order by r.p_id desc", new Object[]{id});
			request.setAttribute("reslist", khlist);
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.id = ? ", new Object[]{id});
			request.setAttribute("state", achievemap.get("state"));
			request.setAttribute("achievemap", achievemap);
			dept_id = "0";
			forward = "/hr/performance/zc.jsp";
		}
		
		return forward;
	}
	
	
	/**
	 * 保存绩效考核成绩
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String save(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String dept_id = request.getParameter("dept_id");
		String forward = "";
		
		if(dept_id.equals("4"))
		{
			Hr_achieve_rjb  achieve_rjb = new Hr_achieve_rjb();
			BeanUtils.populate(achieve_rjb, getParameterMap(request));
			dataBaseControl.updateByBean(achieve_rjb);
			forward = "/hr/performance/Hr_performance!searchPersonList.do?audit_date="+achieve_rjb.getAchieve_date();
		}
		
		if(dept_id.equals("7"))//工程部
		{
			Hr_achieve_gcb  achieve_gcb = new Hr_achieve_gcb();
			BeanUtils.populate(achieve_gcb, getParameterMap(request));
			dataBaseControl.updateByBean(achieve_gcb);
			forward = "/hr/performance/Hr_performance!searchPersonList.do?audit_date="+achieve_gcb.getAchieve_date();
		}
		
		if(dept_id.equals("3"))
		{
			Hr_achieve_zhb  achieve_zhb = new Hr_achieve_zhb();
			BeanUtils.populate(achieve_zhb, getParameterMap(request));
			dataBaseControl.updateByBean(achieve_zhb);
			forward = "/hr/performance/Hr_performance!searchPersonList.do?audit_date="+achieve_zhb.getAchieve_date();
		}
		
		if(dept_id.equals("6"))
		{
			Hr_achieve_cwb  achieve_cwb = new Hr_achieve_cwb();
			BeanUtils.populate(achieve_cwb, getParameterMap(request));
			dataBaseControl.updateByBean(achieve_cwb);
			forward = "/hr/performance/Hr_performance!searchPersonList.do?audit_date="+achieve_cwb.getAchieve_date();
		}
		
		if(dept_id.equals("2"))
		{
			Hr_achieve_rsb  achieve_rsb = new Hr_achieve_rsb();
			BeanUtils.populate(achieve_rsb, getParameterMap(request));
			dataBaseControl.updateByBean(achieve_rsb);
			forward = "/hr/performance/Hr_performance!searchPersonList.do?audit_date="+achieve_rsb.getAchieve_date();
		}
		
		if(dept_id.equals("10"))
		{
			Hr_achieve_khb  achieve_khb = new Hr_achieve_khb();
			BeanUtils.populate(achieve_khb, getParameterMap(request));
			dataBaseControl.updateByBean(achieve_khb);
			forward = "/hr/performance/Hr_performance!searchPersonList.do?audit_date="+achieve_khb.getAchieve_date();
		}
		
		if(dept_id.equals("8"))
		{
			Hr_achieve_sjb  achieve_sjb = new Hr_achieve_sjb();
			BeanUtils.populate(achieve_sjb, getParameterMap(request));
			dataBaseControl.updateByBean(achieve_sjb);
			forward = "/hr/performance/Hr_performance!searchPersonList.do?audit_date="+achieve_sjb.getAchieve_date();
		}
		
		if(dept_id.equals("0"))
		{
			List plist = dataBaseControl.getOutResultSet("select * from hr_base_info t where t.hr_type in ('2','3','6') and  t.duty_id in('2','4','5') and t.id !=87", null);
			
			for(int i = 1 ; i <= plist.size(); i++)
			{
				String id = "id"+i;
				Map map = dataBaseControl.getOneResultSet("select * from hr_achieve_zc t where t.id = ?", new Object[]{request.getParameter(id)});
				Hr_achieve_zc  achieve_zc = new Hr_achieve_zc();
				BeanUtils.populate(achieve_zc, map);
				String item = "item"+i;
				
				achieve_zc.setItem1(request.getParameter(item+"1"));
				achieve_zc.setItem2(request.getParameter(item+"2"));
				achieve_zc.setItem3(request.getParameter(item+"3"));
				achieve_zc.setItem4(request.getParameter(item+"4"));
				achieve_zc.setItem5(request.getParameter(item+"5"));
				achieve_zc.setItem6(request.getParameter(item+"6"));
				achieve_zc.setItem7(request.getParameter(item+"7"));
				achieve_zc.setItem8(request.getParameter(item+"8"));
				
				achieve_zc.setItem9(request.getParameter(item+"9"));
				achieve_zc.setItem10(request.getParameter(item+"10"));
				achieve_zc.setItem11(request.getParameter(item+"11"));
				achieve_zc.setItem12(request.getParameter(item+"12"));
				achieve_zc.setItem13(request.getParameter(item+"13"));
				achieve_zc.setItem14(request.getParameter(item+"15"));
				achieve_zc.setItem15(request.getParameter(item+"15"));
				achieve_zc.setItem16(request.getParameter(item+"16"));
				achieve_zc.setItem17(request.getParameter(item+"17"));
				achieve_zc.setItem18(request.getParameter(item+"18"));
				achieve_zc.setItem19(request.getParameter(item+"19"));
				achieve_zc.setItem20(request.getParameter(item+"20"));
				dataBaseControl.updateByBean(achieve_zc);
			}
			
			forward = "/hr/performance/Hr_performance!searchList.do";
			
		}
		
		
		return forward;
	}
	
	
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String dept_id = request.getParameter("dept_id");
		String forward = "";
		request.setAttribute("dept_id", dept_id);
		
		if(dept_id.equals("4"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_rjb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			
			request.setAttribute("record", map);
			forward = "/hr/performance/rjb.jsp";
		}
		
		if(dept_id.equals("3"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name,r.remark from hr_achieve_zhb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			
			String remark = map.get("remark").toString();
			if(remark.equals("1"))
			{
				forward = "/hr/performance/zhb1.jsp";
			}
			if(remark.equals("2"))
			{
				forward = "/hr/performance/zhb2.jsp";
			}
			if(remark.equals("3"))
			{
				forward = "/hr/performance/zhb3.jsp";
			}
			
			request.setAttribute("record", map);
			
		}
		
		if(dept_id.equals("6"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_cwb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			
			request.setAttribute("record", map);
			forward = "/hr/performance/cwb.jsp";
		}
		
		if(dept_id.equals("2"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_rsb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			
			request.setAttribute("record", map);
			forward = "/hr/performance/rsb.jsp";
		}
		
		if(dept_id.equals("10"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_khb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			
			request.setAttribute("record", map);
			forward = "/hr/performance/khb.jsp";
		}
		
		if(dept_id.equals("8"))
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_sjb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			
			request.setAttribute("record", map);
			forward = "/hr/performance/sjb.jsp";
		}
		
		if(dept_id.equals("0")) //部门经理
		{
			List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))item21,round((((nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))/280)*100),0)item22 from hr_achieve t,hr_achieve_zc r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = 0 and t.id = ? and t.type = '1' order by r.p_id desc", new Object[]{id});
			request.setAttribute("reslist", khlist);	
			
			dept_id = "0";
			forward = "/hr/performance/zc.jsp";
		}
		
		if(dept_id.equals("7"))//工程部
		{
			Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_gcb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
			//Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
			
			request.setAttribute("record", map);
			forward = "/hr/performance/gcb.jsp";
		}
		
		return forward;
	}
	
	private Page search(String tablename,String total,Map searchMap,int pageNo,int pageSize) throws Exception 
	{
		//Map searchMap=getParameterMap(request);
		String sql="select t.state,r.*,b.name,m.branchname, "+ total +" total from hr_achieve t," +tablename + " r,hr_base_info b,mrbranch m where t.id = r.f_id and r.p_id = b.id and b.dept_id = m.id "
		+ "/~ and r.id={id} ~/"
		+ "/~ and t.dept_id={dept_id} ~/"
		+ "/~ and r.p_id={p_id} ~/ "
		+ "/~ and t.audit_date={audit_date}       ~/ "
		+ " order by to_date(r.achieve_date,'yyyy-MM') desc,t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	private Page search1(Map searchMap,int pageNo,int pageSize) throws Exception 
	{
		//Map searchMap=getParameterMap(request);
		String sql="select * from hr_achieve t where t.dept_id = 0 and t.type = 1  "
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.audit_date={audit_date}       ~/ "
		+ " order by t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	/**
	 *人事部查看
	 * @param searchMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	private Page search2(Map searchMap,int pageNo,int pageSize) throws Exception 
	{
		//Map searchMap=getParameterMap(request);
		String sql="select a.* from "+
                   "(select t1.duty_id,a1.id aid, t1.dept_id,t1.id,decode(t1.duty_id,'1','职员','部门经理')duty, t1.name,m1.branchname,a1.achieve_date,(nvl(a1.item1,0)+nvl(a1.item2,0)+nvl(a1.item3,0)+nvl(a1.item4,0)+nvl(a1.item5,0)+nvl(a1.item6,0))total from hr_achieve_cwb a1,hr_base_info t1 ,mrbranch m1,hr_achieve h1 where h1.id = a1.f_id and h1.state = '1' and  a1.p_id = t1.id and t1.dept_id = m1.id "  +
                   "union "+
                   " select t8.duty_id,a8.id aid,t8.dept_id,t8.id,decode(t8.duty_id, '8', '职员', '部门经理') duty,t8.name,m8.branchname,a8.achieve_date,100+(nvl(a8.item1, 0) + nvl(a8.item2, 0) + nvl(a8.item3, 0) +nvl(a8.item4, 0) ) total from hr_achieve_gcb a8,hr_base_info t8,mrbranch m8,hr_achieve h8 where h8.id = a8.f_id and h8.state = '1' and a8.p_id = t8.id and t8.dept_id = m8.id "+
                   "union "+
                   "select t2.duty_id,a2.id aid,t2.dept_id,t2.id,decode(t2.duty_id,'1','职员','部门经理')duty,t2.name,m2.branchname,a2.achieve_date,(100+(nvl(a2.item1,0)+nvl(a2.item2,0)+nvl(a2.item3,0)+nvl(a2.item4,0)))total from hr_achieve_khb a2,hr_base_info t2 ,mrbranch m2 ,hr_achieve h1 where h1.id = a2.f_id and h1.state = '1' and a2.p_id = t2.id and t2.dept_id = m2.id  "+ 
                   "union "+
                   "select t3.duty_id,a3.id aid, t3.dept_id,t3.id,decode(t3.duty_id,'1','职员','部门经理')duty,t3.name,m3.branchname,a3.achieve_date,(100+(nvl(a3.item1,0)+nvl(a3.item2,0)+nvl(a3.item3,0)+nvl(a3.item4,0)))total from hr_achieve_rjb a3,hr_base_info t3 ,mrbranch m3,hr_achieve h1 where h1.id = a3.f_id and h1.state = '1' and a3.p_id = t3.id and t3.dept_id = m3.id  "+ 
                   "union "+
                   "select t4.duty_id,a4.id aid,t4.dept_id,t4.id,decode(t4.duty_id,'1','职员','部门经理')duty,t4.name,m4.branchname,a4.achieve_date,(100+(nvl(a4.item1,0)+nvl(a4.item2,0)+nvl(a4.item3,0)+nvl(a4.item4,0)))total from hr_achieve_rsb a4,hr_base_info t4 ,mrbranch m4,hr_achieve h1 where h1.id = a4.f_id and h1.state = '1' and a4.p_id = t4.id and t4.dept_id = m4.id  "+ 
                   "union "+
                   "select t5.duty_id,a5.id aid,t5.dept_id,t5.id,decode(t5.duty_id,'1','职员','部门经理')duty,t5.name,m5.branchname,a5.achieve_date,(100+(nvl(a5.item1,0)+nvl(a5.item2,0)+nvl(a5.item3,0)))total from hr_achieve_sjb a5,hr_base_info t5 ,mrbranch m5,hr_achieve h1 where h1.id = a5.f_id and h1.state = '1' and a5.p_id = t5.id and t5.dept_id = m5.id  "+  
                   "union "+
                   "select t6.duty_id,a6.id aid,t6.dept_id,t6.id,decode(t6.duty_id,'1','职员','部门经理')duty,t6.name,m6.branchname,a6.achieve_date,(100+(nvl(a6.item1,0)+nvl(a6.item2,0)+nvl(a6.item3,0)+nvl(a6.item4,0)))total from hr_achieve_zhb a6,hr_base_info t6 ,mrbranch m6,hr_achieve h1 where h1.id = a6.f_id and h1.state = '1' and a6.p_id = t6.id and t6.dept_id = m6.id  "+ 
                   "union "+
                   "select t7.duty_id,a7.id aid,t7.dept_id,t7.id,decode(t7.duty_id,'1','职员','部门经理')duty,t7.name,m7.branchname,a7.achieve_date,round((((nvl(a7.item1,0)+nvl(a7.item2,0)+nvl(a7.item3,0)+nvl(a7.item4,0)+nvl(a7.item5,0)+nvl(a7.item6,0)+nvl(a7.item7,0)+nvl(a7.item8,0)+nvl(a7.item9,0)+nvl(a7.item10,0)+nvl(a7.item11,0)+nvl(a7.item12,0)+nvl(a7.item13,0)+nvl(a7.item14,0)+nvl(a7.item15,0)+nvl(a7.item16,0)+nvl(a7.item17,0)+nvl(a7.item18,0)+nvl(a7.item19,0)+nvl(a7.item20,0))/280)*100),0)total from hr_achieve_zc a7,hr_base_info t7 ,mrbranch m7,hr_achieve h1 where h1.id = a7.f_id and h1.state = '1' and a7.p_id = t7.id and t7.dept_id = m7.id)a  where 1=1 "
		           + "/~ and a.dept_id=(select m.id from mrbranch m where m.branchname = {branchname}) ~/"
		           + "/~ and a.id={p_id} ~/"
		           + "/~ and a.achieve_date={audit_date}       ~/ "
		           + " order by a.achieve_date desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	/*
	 * 提交绩效考核表
	 */
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		
		Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.id = ?", new Object[]{id});
		Hr_achieve achieve = new Hr_achieve();
		BeanUtils.populate(achieve, achievemap);
		
		achieve.setState("1");
		
		
		dataBaseControl.updateByBean(achieve);
		
		return "/hr/performance/Hr_performance!searchList.do";
	}
	

	public String allquery(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		this.displaymonth(request, response);
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) ? "1" : request.getParameter("pageno");
        int pageNo=(new Integer(page_no)).intValue();
        int pageSize=20;
		
		Map searchMap = getParameterMap(request);
		
		Page page = search2(searchMap, pageNo, pageSize);
		request.setAttribute("view", "y");
		
		request.setAttribute("searchMap", searchMap);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		
		
		
		return "/hr/performance/hr_hr_performance.jsp";
	}
	
	/**
	 * 员工查询自己的绩效成绩
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String selflook(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		this.displaymonth(request, response);
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) ? "1" : request.getParameter("pageno");
        int pageNo=(new Integer(page_no)).intValue();
        int pageSize=20;
		
        Map user = (Map)request.getSession().getAttribute("user");
        
		Map searchMap = getParameterMap(request);
		searchMap.put("p_id", user.get("base_info_id"));
		
		Page page = search2(searchMap, pageNo, pageSize);
		
		request.setAttribute("searchMap", searchMap);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		
		
		
		return "/hr/performance/hr_selfperformance.jsp";
	}
	
	
	
	public String look(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String duty_id = request.getParameter("duty_id");
		String dept_id = request.getParameter("dept_id");
		String forward = "";
		request.setAttribute("dept_id", dept_id);
		
		if(duty_id.equals("1"))
		{
			if(dept_id.equals("4"))
			{
				Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_rjb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
				
				request.setAttribute("record", map);
				forward = "/hr/performance/rjb.jsp";
			}
			
			if(dept_id.equals("3"))
			{
				Map map = dataBaseControl.getOneResultSet("select t.*,r.name,r.remark from hr_achieve_zhb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
				
				String remark = map.get("remark").toString();
				if(remark.equals("1"))
				{
					forward = "/hr/performance/zhb1.jsp";
				}
				if(remark.equals("2"))
				{
					forward = "/hr/performance/zhb2.jsp";
				}
				if(remark.equals("3"))
				{
					forward = "/hr/performance/zhb3.jsp";
				}
				
				request.setAttribute("record", map);
				
			}
			
			if(dept_id.equals("6"))
			{
				Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_cwb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
				
				request.setAttribute("record", map);
				forward = "/hr/performance/cwb.jsp";
			}
			
			if(dept_id.equals("2"))
			{
				Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_rsb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
				
				request.setAttribute("record", map);
				forward = "/hr/performance/rsb.jsp";
			}
			
			if(dept_id.equals("10"))
			{
				Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_khb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
				
				request.setAttribute("record", map);
				forward = "/hr/performance/khb.jsp";
			}
			
			if(dept_id.equals("8"))
			{
				Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_sjb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
				
				request.setAttribute("record", map);
				forward = "/hr/performance/sjb.jsp";
			}
			
			if(dept_id.equals("7"))
			{
				Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_achieve_gcb t,hr_base_info r where t.p_id = r.id and t.id = ?", new Object[]{id});
				Map achievemap = dataBaseControl.getOneResultSet("select * from hr_achieve t where t.dept_id = ? and t.audit_date = ? and t.type = ?", new Object[]{dept_id,map.get("achieve_date"),"0"});
				
				request.setAttribute("record", map);
				forward = "/hr/performance/gcb.jsp";
			}
			
		}
		else if(duty_id.equals("3"))
		{
				List khlist = dataBaseControl.getOutResultSet("select r.*,t.state,b.name,(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))item21,round((((nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))/280)*100),0)item22 from hr_achieve t,hr_achieve_zc r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = 0 and t.id = ? and t.type = '1' order by r.p_id desc", new Object[]{id});
				request.setAttribute("reslist", khlist);	
				dept_id = "0";
				forward = "/hr/performance/zc.jsp";	
		}
		else
		{
			Map map = dataBaseControl.getOneResultSet("select r.*,t.state,b.name,(nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))item21,round((((nvl(r.item1,0)+nvl(r.item2,0)+nvl(r.item3,0)+nvl(r.item4,0)+nvl(r.item5,0)+nvl(r.item6,0)+nvl(r.item7,0)+nvl(r.item8,0)+nvl(r.item9,0)+nvl(r.item10,0)+nvl(r.item11,0)+nvl(r.item12,0)+nvl(r.item13,0)+nvl(r.item14,0)+nvl(r.item15,0)+nvl(r.item16,0)+nvl(r.item17,0)+nvl(r.item18,0)+nvl(r.item19,0)+nvl(r.item20,0))/280)*100),0)item22 from hr_achieve t,hr_achieve_zc r,hr_base_info b where r.p_id = b.id and t.id = r.f_id and t.dept_id = 0 and r.id = ? and t.type = '1' order by r.p_id desc", new Object[]{id});
			request.setAttribute("kh", map);	
			
			dept_id = "0";
			forward = "/hr/performance/zcselflook.jsp";
		}
		
		
		return forward;
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}