package com.web.hr.absence;

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

import org.apache.commons.beanutils.BeanUtils;

import com.map.Hr_kaoqin;
import com.map.Hr_kaoqin_audit;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.web.BaseAction;

public class Hr_kaoqinAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();
	
	public int getmonth_days(String month,int year) throws Exception{
		int days = 0;
	
		if(month.equals("1")||month.equals("01")||month.equals("3")||month.equals("03")||month.equals("5")||month.equals("05")||month.equals("7")||month.equals("07")||month.equals("8")||month.equals("08")||month.equals("10")||month.equals("12"))
		{
			days = 31;
		}
		
		else if(month.equals("4")||month.equals("04")||month.equals("6")||month.equals("06")||month.equals("9")||month.equals("09")||month.equals("11"))
		{
			days = 30;
		}
		else 
		{
			if((year%4==0&&year%100!=0)||year%400==0)//闰年
			{
				days = 29;
			}
			else
			{
				days = 28;
			}
		}
		return days;
		
	}
	
	public void displaymonth(HttpServletRequest request,HttpServletResponse response) throws Exception{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdate = fmt.format(date);
		
		String nian = sdate.substring(0,4);
		String yue = sdate.substring(5,7);
		//当月
		Map map1 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),1),'yyyy-MM') kqdate from dual", new Object[]{nian+"-"+yue});
		//前一月
		Map map2 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),0),'yyyy-MM') kqdate from dual", new Object[]{nian+"-"+yue});
		//前二月
		Map map3 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),-1),'yyyy-MM') kqdate from dual", new Object[]{nian+"-"+yue});
		//前三月
		Map map6 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),-2),'yyyy-MM') kqdate from dual", new Object[]{nian+"-"+yue});
		
		//下月
		Map map4 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),2),'yyyy-MM') kqdate from dual", new Object[]{nian+"-"+yue});
		
		List list = new ArrayList();
		list.add(map6);
		list.add(map3);
		list.add(map2);
		list.add(map1);
		list.add(map4);
		
		request.setAttribute("datelist", list);
	}
	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.displaymonth(request, response);
		
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map searchMap = getParameterMap(request);
		searchMap.put("dept_id", user.get("branchid"));
		Page page=search(searchMap,pageNo,pageSize);
		
		
		request.setAttribute("searchMap", searchMap);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/absence/hr_kaoqinSelect.jsp";
		return "/hr/kaoqin/hr_kaoqinList.jsp";
	}
	
	/**
	 * 查询本部门有非出勤状态的人员
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String searchPersonList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.displaymonth(request, response);
		String kqdate = request.getParameter("kqdate");
		Map searchMap = new HashMap();
		searchMap.put("kqdate", kqdate);
		request.setAttribute("searchMap", searchMap);
		
		String nian = kqdate.substring(0,4);
		int year = Integer.parseInt(nian);
		int days = 0;
		String month = kqdate.substring(5,7);
		
		days = getmonth_days(month,year);
		
		
		List reslist = new ArrayList();
		Map user = (Map)request.getSession().getAttribute("user");
		Map auditmap = dataBaseControl.getOneResultSet("select * from hr_kaoqin_audit t where t.kaoqin_date = ? and t.dept_id = ?", new Object[]{kqdate,user.get("branchid")});
		request.setAttribute("auditmap", auditmap);
		
		//获得法定节假日
		List vacationlist = dataBaseControl.getOutResultSet("select * from hr_vacation_arrangement t where t.vacation_date >= to_date(?,'yyyy-MM-dd') and t.vacation_date <= to_date(?,'yyyy-MM-dd')", new Object[]{kqdate+"-01",kqdate+"-"+days});
		Map vacationmap = new HashMap();
		for(int v = 0 ; v < vacationlist.size(); v++)
		{
			Map vmap = (Map)vacationlist.get(v);
			vacationmap.put(vmap.get("vacation_date").toString(), v);
		}
		
		List plist = dataBaseControl.getOutResultSet("select * from hr_base_info t where t.dept_id = ? and t.hr_type in('2','3') and t.duty_id = 1 order by t.id ", new Object[]{user.get("branchid")});
		for(int p = 0 ; p < plist.size();p++)
		{
			Map pmap = (Map)plist.toArray()[p];
			boolean attendflag = false;
			
			//查看此人本月的考勤是否都是正常出勤
			List attendlist = dataBaseControl.getOutResultSet("select t.attend_date, t.am_actual_attend,t.pm_actual_attend, decode(sign(to_date(to_char(t.attend_date, 'yyyy-MM-dd') || ' ' ||nvl(t.am_actual_attend, '17:30'),'yyyy-MM-dd hh24:mi') -to_date(to_char(t.attend_date, 'yyyy-MM-dd') || ' ' ||t.am_order_attend,'yyyy-MM-dd hh24:mi')),'-1','1','0','1','2') state from hr_attendance t where t.attend_date >= to_date(?,'yyyy-MM-dd')and t.attend_date <= to_date(?,'yyyy-MM-dd') and t.p_id = ?", new Object[]{kqdate+"-01",kqdate+"-"+days,pmap.get("id")});
			for(int a = 0 ; a < attendlist.size();a++)
			{
				Map amap = (Map)attendlist.get(a);
				String state = amap.get("state").toString();
				String attend_date = amap.get("attend_date").toString();
				if((state.equals("0")||state.equals("2"))&&!vacationmap.containsKey(attend_date)) //非节假日的迟到和旷工
				{
					attendflag = true;
					continue;
				}
			}
			
			if(attendlist==null||attendlist.isEmpty())
			{
				reslist.add(pmap);
				continue;
			}
			
			if(attendflag)
			{
				reslist.add(pmap);
				continue;
			}
			
			//查看此人本月的请假记录
			List absencelist = dataBaseControl.getOutResultSet("select * from hr_absence t where t.p_id= ? and t.absence_state = '3' and ((t.begin_date >= to_date(?,'yyyy-MM-dd') and t.begin_date <= to_date(?,'yyyy-MM-dd'))or(t.end_date >= to_date(?,'yyyy-MM-dd') and t.end_date <= to_date(?,'yyyy-MM-dd'))) ", new Object[]{pmap.get("id"),kqdate+"-01",kqdate+"-"+days,kqdate+"-01",kqdate+"-"+days});
			
			if(absencelist != null && !absencelist.isEmpty())  //此人本月有请假记录
			{
				reslist.add(pmap);
				continue;
			}
			
			//查看此人本月的出差记录
			List travellist = dataBaseControl.getOutResultSet("select t.*  from gm_travel t,gm_travel_sub r where r.travelid(+) = t.id and t.appstate = '2' and  (t.personid = ? or r.suiteid = ?) and ((t.b_time >= to_date(?, 'yyyy-MM-dd') and t.b_time <= to_date(?, 'yyyy-MM-dd')) or (t.e_time >= to_date(?, 'yyyy-MM-dd') and t.e_time <= to_date(?, 'yyyy-MM-dd')))",new Object[]{pmap.get("id"),pmap.get("id"),kqdate+"-01",kqdate+"-"+days,kqdate+"-01",kqdate+"-"+days});
			
			//List travellist = dataBaseControl.getOutResultSet("select * from gm_travel t,gm_travelsub r where r.travelid(+) = t.id and t.appstatus in('5','6') and ((t.travelbegintime >= to_date(?,'yyyy-MM-dd') and t.travelbegintime <= to_date(?,'yyyy-MM-dd'))or(t.travelendtime >= to_date(?,'yyyy-MM-dd') and t.travelendtime <= to_date(?,'yyyy-MM-dd'))) and (t.personid =? or r.personalid = ?)", new Object[]{kqdate+"-01",kqdate+"-"+days,kqdate+"-01",kqdate+"-"+days,pmap.get("id"),pmap.get("id")});
			if(travellist != null && !travellist.isEmpty())  //此人本月有出差记录
			{
				reslist.add(pmap);
				continue;
			}
			
		}
		
		
		if(auditmap == null || auditmap.isEmpty())
		{
			Hr_kaoqin_audit kaoqin_audit = new Hr_kaoqin_audit();
			kaoqin_audit.setDept_id(new Long(user.get("branchid").toString()));
			kaoqin_audit.setCount(new Long(reslist.size()));
			kaoqin_audit.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			kaoqin_audit.setKaoqin_date(kqdate);
			kaoqin_audit.setState("0");
			kaoqin_audit.setP_id(new Long(user.get("base_info_id").toString()));
			dataBaseControl.insertByBean(kaoqin_audit);
		}
		
		
		request.setAttribute("reslist", reslist);
		buildDDL(request);
		
		return "/hr/kaoqin/hr_kaoqinPersonList.jsp";
	}
	
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    
		Page page=search(getParameterMap(request),1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/kaoqin/hr_kaoqinDtl.jsp";
	}
	
	
	private Page searchperson(Map map,int pageNo,int pageSize) throws Exception 
	{
		
		String sql="select * from hr_base_info t  where 1=1 and t.duty_id !=2 " 
		+ "/~ and t.dept_id={dept_id} ~/"
		+ " order by t.id";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,map); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	
	private Page search(Map searchMap,int pageNo,int pageSize) throws Exception 
	{
		//Map searchMap=getParameterMap(request);
		String sql="select t.*,r.name,m.branchname from Hr_kaoqin t,hr_base_info r,mrbranch m where  r.id = t.p_id and t.dept_id = m.id and 1=1 " 
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.dept_id={dept_id} ~/"
		+ "/~ and t.p_id={p_id} ~/ "
		+ "/~ and t.attend_date>=to_date({attend_date1},'yyyy-MM-dd')       ~/ "
		+ "/~ and t.attend_date<=to_date({attend_date2},'yyyy-MM-dd')       ~/ "
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
		return "/hr/absence/hr_kaoqinDtl.jsp";
	}
	
	/**
	 * 跳转考勤统计界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String toedit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String p_id = request.getParameter("p_id");
		String kqdate = request.getParameter("kqdate");
		
		String nian = kqdate.substring(0,4);
		int year = Integer.parseInt(nian);
		int days = 0;
		String month = kqdate.substring(5,7);
		
		
		Map map = dataBaseControl.getOneResultSet("select t.* from hr_base_info t where t.id = ?", new Object[]{p_id});
		request.setAttribute("name", map.get("name"));
		request.setAttribute("kqdate", kqdate);
		request.setAttribute("p_id", map.get("id"));
		
		days = getmonth_days(month,year);
		
		List kqlist = dataBaseControl.getOutResultSet("select * from hr_kaoqin t where t.p_id = ? and t.attend_date >=to_date(?,'yyyy-MM-dd') and t.attend_date <=to_date(?,'yyyy-MM-dd') ", new Object[]{p_id,kqdate+"-01",kqdate+"-"+days});
		
		if(kqlist ==null || kqlist.isEmpty())
		{
			List reslist = new ArrayList();
			Map tmap = new HashMap();
			
			dataBaseControl.beginTransaction();
			for(int i = 1 ; i <= days ; i++)
			{			
				//由日期获得星期
				SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
				DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				Date ddate = dFormat.parse(kqdate+"-"+i);
	    		String day = dateFm.format(ddate);
	    		String types = "";
	    		String typex = "";
				
				
				String ch0 = "0";
				String chs1 = "0";
				String chs2 = "0";
				String chs3 = "0";
				String chs4 = "0";
				String chs5 = "0";
				String chs6 = "0";
				String chs7 = "0";
				String chs8 = "0";
				
				String chx1 = "0";
				String chx2 = "0";
				String chx3 = "0";
				String chx4 = "0";
				String chx5 = "0";
				String chx6 = "0";
				String chx7 = "0";
				String chx8 = "0";
				
				//ch0--节假日
				//上午
				//chs1--出勤；
				//chs2--迟到
				//chs3--病假
				//chs4--事假
				//chs5--带薪假
				//chs6--公差
				//chs7--培训
				//chs8--旷工
				
				//下午
				//chx1--出勤；
				//chx2--迟到
				//chx3--病假
				//chx4--事假
				//chx5--带薪假
				//chx6--公差
				//chx7--培训
				//chx8--旷工
				
				//////////////////////////////////获得节假日
				Map map1 = new HashMap();
				Map map0 = dataBaseControl.getOneResultSet("select * from hr_vacation_arrangement t where t.vacation_date = to_date(?,'yyyy-MM-dd')", new Object[]{kqdate+"-"+i});
				if(map0 != null && !map0.isEmpty())  //节假日情况
				{
					ch0 = "1";
				}
				else //非节假日情况
				{
					////////////////////////////////////////////////查看考勤机记录
					map1 = dataBaseControl.getOneResultSet("select t.am_actual_attend,t.pm_actual_attend, decode(t.am_actual_attend,'','0',decode(sign(to_date(to_char(t.attend_date, 'yyyy-MM-dd') || t.am_actual_attend, 'yyyy-MM-dd hh24:mi') -to_date(to_char(t.attend_date, 'yyyy-MM-dd') || t.am_order_attend, 'yyyy-MM-dd hh24:mi')),'-1','1','0','1','2')) state from hr_attendance t where t.attend_date = to_date(?,'yyyy-MM-dd') and t.p_id = ?", new Object[]{kqdate+"-"+i,map.get("id")});
					//sate=0,没有考勤记录；state=1,出勤;state=2,迟到
					if(map1 == null || map1.isEmpty())
					{
						chs8 = "1";
						chx8 = "1";
						types = "旷工";
						typex = "旷工";
						
					}
					else
					{
						String state = map1.get("state").toString();
						if(state.equals("1"))
						{
							chs1 = "1";
							chx1 = "1";
							types = "出勤";
							typex = "出勤";
						}
						else if(state.equals("0"))
						{
							chs8 = "1";
							chx8 = "1";
							types = "旷工";
							typex = "旷工";
						}
						else
						{
							chs2 = "1";
							chx2 = "0";
							types = "迟到";
							typex = "出勤";
						}
					}
					
					///////////////////////////////////////////////查看请假情况
					///开始日期上午请假情况
					Map mapbs = dataBaseControl.getOneResultSet("select (select r.absence_type from hr_absence_standard r where r.parent_id = 0 start with r.absence_type = t.absence_type connect by prior r.parent_id = r.id) abtype,t.* from hr_absence t where t.absence_state = '5' and t.p_id = ? and t.bam = 1 and t.bpm = 0 and t.begin_date = to_date(?,'yyyy-MM-dd') ", new Object[]{map.get("id"),kqdate+"-"+i});
					///开始日期下午请假情况
					Map mapbx = dataBaseControl.getOneResultSet("select (select r.absence_type from hr_absence_standard r where r.parent_id = 0 start with r.absence_type = t.absence_type connect by prior r.parent_id = r.id) abtype,t.* from hr_absence t where t.absence_state = '5' and t.p_id = ? and t.bam = 0 and t.bpm = 1 and t.begin_date = to_date(?,'yyyy-MM-dd')", new Object[]{map.get("id"),kqdate+"-"+i});
					
					
					///结束日期上午请假情况
					Map mapes = dataBaseControl.getOneResultSet("select (select r.absence_type from hr_absence_standard r where r.parent_id = 0 start with r.absence_type = t.absence_type connect by prior r.parent_id = r.id) abtype,t.* from hr_absence t where t.absence_state = '5' and t.p_id = ? and t.eam = 1 and t.epm = 0 and t.end_date = to_date(?,'yyyy-MM-dd') ", new Object[]{map.get("id"),kqdate+"-"+i});
					///结束日期下午请假情况
					Map mapex = dataBaseControl.getOneResultSet("select (select r.absence_type from hr_absence_standard r where r.parent_id = 0 start with r.absence_type = t.absence_type connect by prior r.parent_id = r.id) abtype,t.* from hr_absence t where t.absence_state = '5' and t.p_id = ? and t.eam = 0 and t.epm = 1 and t.end_date = to_date(?,'yyyy-MM-dd')", new Object[]{map.get("id"),kqdate+"-"+i});
					
					//全天情况
					Map map2 = dataBaseControl.getOneResultSet("select (select r.absence_type from hr_absence_standard r where r.parent_id = 0 start with r.absence_type = t.absence_type connect by prior r.parent_id = r.id) abtype,t.*  from hr_absence t where t.absence_state = '5' and  t.p_id = ? and to_date(?, 'yyyy-MM-dd') > t.begin_date and to_date(?, 'yyyy-MM-dd') < t.end_date",new Object[]{map.get("id"),kqdate+"-"+i,kqdate+"-"+i});
					
					
					if(mapbs != null && !mapbs.isEmpty())  
					{
						if(mapbs.get("abtype").equals("事假"))
						{
							chs4 = "1";
							types = "事假";
							
						}
						if(mapbs.get("abtype").equals("病假"))
						{
							chs3 = "1";
							types = "病假";
							
						}
						if(mapbs.get("abtype").equals("带薪假"))
						{
							chs5 = "1";
							types = "带薪假";
					
						}
						
						if(mapes == null || mapes.isEmpty())  
						{
							if(mapbs.get("abtype").equals("事假"))
							{
								chx4 = "1";
								
								typex = "事假";
							}
							if(mapbs.get("abtype").equals("病假"))
							{
								chx3 = "1";
								
								typex = "病假";
							}
							if(mapbs.get("abtype").equals("带薪假"))
							{
								chx5 = "1";
								
								typex = "带薪假";
							}
						}	
					}
					
					
					if(mapbx != null && !mapbx.isEmpty())  
					{
						if(mapbx.get("abtype").equals("事假"))
						{
							chx4 = "1";
							
							typex = "事假";
						}
						if(mapbx.get("abtype").equals("病假"))
						{
							chx3 = "1";
							
							typex = "病假";
						}
						if(mapbx.get("abtype").equals("带薪假"))
						{
							chx5 = "1";
							
							typex = "带薪假";
						}
					}
					
					if(mapes != null && !mapes.isEmpty())  
					{
						if(mapes.get("abtype").equals("事假"))
						{
							chs4 = "1";
							types = "事假";
							
						}
						if(mapes.get("abtype").equals("病假"))
						{
							chs3 = "1";
							types = "病假";
						
						}
						if(mapes.get("abtype").equals("带薪假"))
						{
							chs5 = "1";
							types = "带薪假";
							
						}
					}
					
					
					if(mapex != null && !mapex.isEmpty())  
					{
						if(mapex.get("abtype").equals("事假"))
						{
							chx4 = "1";
							
							typex = "事假";
						}
						if(mapex.get("abtype").equals("病假"))
						{
							chx3 = "1";
							
							typex = "病假";
						}
						if(mapex.get("abtype").equals("带薪假"))
						{
							chx5 = "1";
							
							typex = "带薪假";
						}
						
						if((mapbs==null||mapbs.isEmpty())&&(mapbx==null||mapbx.isEmpty()))
						{
							if(mapex.get("abtype").equals("事假"))
							{
								chs4 = "1";
								types = "事假";
								
							}
							if(mapex.get("abtype").equals("病假"))
							{
								chs3 = "1";
								types = "病假";
							
							}
							if(mapex.get("abtype").equals("带薪假"))
							{
								chs5 = "1";
								types = "带薪假";
							}
						}
					}
					
					if(map2 != null && !map2.isEmpty())
					{
						if(map2.get("abtype").equals("事假"))
						{
							chs4 = "1";
							chx4 = "1";
							types = "事假";
							typex = "事假";
						}
						if(map2.get("abtype").equals("病假"))
						{
							chs3 = "1";
							chx3 = "1";
							types = "病假";
							typex = "病假";
						}
						if(map2.get("abtype").equals("带薪假"))
						{
							chs5 = "1";
							chx5 = "1";
							types = "带薪假";
							typex = "带薪假";
						}
					}
					
					//////////////////////////查看出差情况
					List travellist = dataBaseControl.getOutResultSet("select t.*  from gm_travel t,gm_travel_sub r where r.travelid(+) = t.id and t.appstate = '2' and  (t.personid = ? or r.suiteid = ?) and to_date(?, 'yyyy-MM-dd') >= t.b_time and to_date(?, 'yyyy-MM-dd') <= t.e_time",new Object[]{map.get("id"),map.get("id"),kqdate+"-"+i,kqdate+"-"+i});
					if(travellist != null && !travellist.isEmpty())
					{
						types = "出差";
						typex = "出差";
					}
					
				}
				
				
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				
				Map qmap = dataBaseControl.getOneResultSet("select * from hr_kaoqin t where t.p_id = ? and t.attend_date = to_date(?,'yyyy-MM-dd')", new Object[]{map.get("id"),kqdate+"-"+i});
				if((qmap == null || qmap.isEmpty()))
				{
					if((!types.equals("出勤")||!typex.equals("出勤"))&&!ch0.equals("1"))
					{
						Hr_kaoqin kaoqin = new Hr_kaoqin();
						kaoqin.setDept_id(new Long(map.get("dept_id").toString()));
						kaoqin.setP_id(new Long(map.get("id").toString()));
						kaoqin.setInut_date(new java.sql.Date((new java.util.Date()).getTime()));
						kaoqin.setAttend_date(new java.sql.Date((fmt.parse(kqdate+"-"+i)).getTime()));
						kaoqin.setZhuangt("0");
						kaoqin.setAm(types);
						kaoqin.setPm(typex);
						dataBaseControl.insertByBean(kaoqin);
					}	
				}
				
				int count1 = 0;
				
				if(day.equals("星期日"))
				{
					tmap.put("ch0", ch0);
					tmap.put("date0", i);
					tmap.put("s0", types);
					tmap.put("x0", typex);
					tmap.put("stime0", map1.get("am_actual_attend"));
					tmap.put("xtime0", map1.get("pm_actual_attend"));
					if(i == 1)
					{
						count1 = 0;
					}
								
				}
				
				if(day.equals("星期一"))
				{
					tmap.put("ch1", ch0);
					tmap.put("date1", i);
					tmap.put("s1", types);
					tmap.put("x1", typex);
					tmap.put("stime1", map1.get("am_actual_attend"));
					tmap.put("xtime1", map1.get("pm_actual_attend"));
					if(i == 1)
					{
						count1 = 1;
					}
					
				}
				
				if(day.equals("星期二"))
				{
					tmap.put("ch2", ch0);
					tmap.put("date2", i);
					tmap.put("s2", types);
					tmap.put("x2", typex);
					tmap.put("stime2", map1.get("am_actual_attend"));
					tmap.put("xtime2", map1.get("pm_actual_attend"));
					if(i == 1)
					{
						count1 = 2;
					}
					
				}
				
				if(day.equals("星期三"))
				{
					tmap.put("ch3", ch0);
					tmap.put("date3", i);
					tmap.put("s3", types);
					tmap.put("x3", typex);
					tmap.put("stime3", map1.get("am_actual_attend"));
					tmap.put("xtime3", map1.get("pm_actual_attend"));
					if(i == 1)
					{
						count1 = 3;
					}
					
				}
				
				if(day.equals("星期四"))
				{
					tmap.put("ch4", ch0);
					tmap.put("date4", i);
					tmap.put("s4", types);
					tmap.put("x4", typex);
					tmap.put("stime4", map1.get("am_actual_attend"));
					tmap.put("xtime4", map1.get("pm_actual_attend"));
					if(i == 1)
					{
						count1 = 4;
					}
					
				}
				
				if(day.equals("星期五"))
				{
					tmap.put("ch5", ch0);
					tmap.put("date5", i);
					tmap.put("s5", types);
					tmap.put("x5", typex);
					tmap.put("stime5", map1.get("am_actual_attend"));
					tmap.put("xtime5", map1.get("pm_actual_attend"));
					if(i == 1)
					{
						count1 = 5;
					}
					
				}
				
				if(day.equals("星期六"))
				{
					tmap.put("ch6", ch0);
					tmap.put("date6", i);
					tmap.put("s6", types);
					tmap.put("x6", typex);
					tmap.put("stime6", map1.get("am_actual_attend"));
					tmap.put("xtime6", map1.get("pm_actual_attend"));
					if(i == 1)
					{
						count1 = 6;
					}
				}
				
				for(int j = 0 ; j < count1 ; j++)
				{
					tmap.put("ch"+j, "");
					tmap.put("date"+j, "");
					tmap.put("s"+j, "");
					tmap.put("x"+j, "");
					tmap.put("stime"+j, "");
					tmap.put("xtime"+j, "");
				}
				
				if(tmap.size()==42)
				{
					reslist.add(tmap);
					tmap = new HashMap();
				}
					
				
			}
			dataBaseControl.endTransaction();
			if(!tmap.isEmpty())
			{
				reslist.add(tmap);
			}
			
			request.setAttribute("reslist", reslist);
		}
		
		else        //aa
		{
			List reslist = new ArrayList();
			Map tmap = new HashMap();
			String ch0 = "";
			
			
		
			
			for(int i = 1 ; i <= days ; i++)
			{			
				//由日期获得星期
				SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
				DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				Date ddate = dFormat.parse(kqdate+"-"+i);
	    		String day = dateFm.format(ddate);
	    		
	    		Map kqmap = dataBaseControl.getOneResultSet("select * from hr_kaoqin t where t.p_id = ? and t.attend_date =to_date(?,'yyyy-MM-dd')", new Object[]{p_id,kqdate+"-"+i});
	    		Map map0 = dataBaseControl.getOneResultSet("select * from hr_vacation_arrangement t where t.vacation_date = to_date(?,'yyyy-MM-dd')", new Object[]{kqdate+"-"+i});
	    		Map map1 = dataBaseControl.getOneResultSet("select t.am_actual_attend,t.pm_actual_attend, decode(t.am_actual_attend,'','0',decode(sign(to_date(to_char(t.attend_date, 'yyyy-MM-dd') || t.am_actual_attend, 'yyyy-MM-dd hh24:mi') -to_date(to_char(t.attend_date, 'yyyy-MM-dd') || t.am_order_attend, 'yyyy-MM-dd hh24:mi')),'-1','1','0','1','2')) state from hr_attendance t where t.attend_date = to_date(?,'yyyy-MM-dd') and t.p_id = ?", new Object[]{kqdate+"-"+i,p_id});
				
	    		
				if(map0 != null && !map0.isEmpty())  //节假日情况
				{
					ch0 = "1";
				}
				else
				{
					ch0 = "0";
				}
	    		
	    		String types = "";
	    		String typex = "";
	    		
	    		if(kqmap==null||kqmap.isEmpty())
	    		{
	    			types = "出勤";
		    		typex = "出勤";
	    		}
	    		else
	    		{
	    			types = ""+kqmap.get("am");
		    		typex = ""+kqmap.get("pm");
	    		}
	    		
	    		
                int count1 = 0;
				
				if(day.equals("星期日"))
				{
					tmap.put("ch0", ch0);
					tmap.put("date0", i);
					tmap.put("s0", types);
					tmap.put("x0", typex);
					tmap.put("stime0", map1.get("am_actual_attend"));
					tmap.put("xtime0", map1.get("pm_actual_attend"));
					
					tmap.put("anote0", kqmap.get("amnote"));
					tmap.put("pnote0", kqmap.get("pmnote"));
					
					
					if(i == 1)
					{
						count1 = 0;
					}
								
				}
				
				if(day.equals("星期一"))
				{
					tmap.put("ch1", ch0);
					tmap.put("date1", i);
					tmap.put("s1", types);
					tmap.put("x1", typex);
					tmap.put("stime1", map1.get("am_actual_attend"));
					tmap.put("xtime1", map1.get("pm_actual_attend"));
					tmap.put("anote1", kqmap.get("amnote"));
					tmap.put("pnote1", kqmap.get("pmnote"));
					if(i == 1)
					{
						count1 = 1;
					}
					
				}
				
				if(day.equals("星期二"))
				{
					tmap.put("ch2", ch0);
					tmap.put("date2", i);
					tmap.put("s2", types);
					tmap.put("x2", typex);
					tmap.put("stime2", map1.get("am_actual_attend"));
					tmap.put("xtime2", map1.get("pm_actual_attend"));
					tmap.put("anote2", kqmap.get("amnote"));
					tmap.put("pnote2", kqmap.get("pmnote"));
					if(i == 1)
					{
						count1 = 2;
					}
					
				}
				
				if(day.equals("星期三"))
				{
					tmap.put("ch3", ch0);
					tmap.put("date3", i);
					tmap.put("s3", types);
					tmap.put("x3", typex);
					tmap.put("stime3", map1.get("am_actual_attend"));
					tmap.put("xtime3", map1.get("pm_actual_attend"));
					tmap.put("anote3", kqmap.get("amnote"));
					tmap.put("pnote3", kqmap.get("pmnote"));
					if(i == 1)
					{
						count1 = 3;
					}
					
				}
				
				if(day.equals("星期四"))
				{
					tmap.put("ch4", ch0);
					tmap.put("date4", i);
					tmap.put("s4", types);
					tmap.put("x4", typex);
					tmap.put("stime4", map1.get("am_actual_attend"));
					tmap.put("xtime4", map1.get("pm_actual_attend"));
					tmap.put("anote4", kqmap.get("amnote"));
					tmap.put("pnote4", kqmap.get("pmnote"));
					if(i == 1)
					{
						count1 = 4;
					}
					
				}
				
				if(day.equals("星期五"))
				{
					tmap.put("ch5", ch0);
					tmap.put("date5", i);
					tmap.put("s5", types);
					tmap.put("x5", typex);
					tmap.put("stime5", map1.get("am_actual_attend"));
					tmap.put("xtime5", map1.get("pm_actual_attend"));
					tmap.put("anote5", kqmap.get("amnote"));
					tmap.put("pnote5", kqmap.get("pmnote"));
					if(i == 1)
					{
						count1 = 5;
					}
					
				}
				
				if(day.equals("星期六"))
				{
					tmap.put("ch6", ch0);
					tmap.put("date6", i);
					tmap.put("s6", types);
					tmap.put("x6", typex);
					tmap.put("stime6", map1.get("am_actual_attend"));
					tmap.put("xtime6", map1.get("pm_actual_attend"));
					tmap.put("anote6", kqmap.get("amnote"));
					tmap.put("pnote6", kqmap.get("pmnote"));
					if(i == 1)
					{
						count1 = 6;
					}
				}
				
				for(int j = 0 ; j < count1 ; j++)
				{
					tmap.put("ch"+j, "");
					tmap.put("date"+j, "");
					tmap.put("s"+j, "");
					tmap.put("x"+j, "");
					tmap.put("stime"+j, "");
					tmap.put("xtime"+j, "");
					tmap.put("anote"+j, kqmap.get("amnote"));
					tmap.put("pnote"+j, kqmap.get("pmnote"));
				}
				
				if(tmap.size()==56)
				{
					reslist.add(tmap);
					tmap = new HashMap();
				}
					
				
			}
			if(!tmap.isEmpty())
			{
				reslist.add(tmap);
			}
			
			request.setAttribute("reslist", reslist);
	    		
		}
		
		
		
		
		return "/hr/kaoqin/hr_kaoqin_reason.jsp";
	}
	
	/**
	 * 添加考勤备注
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addNote(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String p_id = request.getParameter("p_id");
		String type = request.getParameter("type");
		String day = request.getParameter("day");
		String yemo = request.getParameter("yemo");
		
		Map map = dataBaseControl.getOneResultSet("select t.*,r.name from hr_kaoqin t,hr_base_info r where r.id = t.p_id and t.p_id = ? and t.attend_date = to_date(?,'yyyy-MM-dd')", new Object[]{p_id,yemo+"-"+day});
		
		//Hr_kaoqin kaoqin = new Hr_kaoqin();
		//BeanUtils.populate(kaoqin, map);
		request.setAttribute("record", map);
		request.setAttribute("type", type);
		
		
		return "/hr/kaoqin/hr_add_kaoqinNote.jsp";
	}
	
	
	/**
	 * 保存考勤备注
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String saveNote(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		
		
		Map map = dataBaseControl.getOneResultSet("select t.* from hr_kaoqin t where t.id = ?", new Object[]{id});
		
		Hr_kaoqin kaoqin = new Hr_kaoqin();
		BeanUtils.populate(kaoqin, map);
		
		if(type.equals("am"))
			kaoqin.setAmnote(request.getParameter("note"));
		if(type.equals("pm"))
			kaoqin.setPmnote(request.getParameter("note"));
		
		dataBaseControl.updateByBean(kaoqin);
		
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/hr/kaoqin/hr_add_kaoqinNote.jsp";
	}
	
	
	
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Map map = dataBaseControl.getOneResultSet("select t.* from hr_kaoqin t where t.id = ?", new Object[]{id});
			Hr_kaoqin kaoqin = new Hr_kaoqin();
			BeanUtils.populate(kaoqin, map);
			kaoqin.setAmnote(request.getParameter("amnote"));
			kaoqin.setPmnote(request.getParameter("pmnote"));
			dataBaseControl.updateByBean(kaoqin);
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(getParameterMap(request),1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/kaoqin/hr_kaoqinDtl.jsp";
	}
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String kqdate = request.getParameter("kqdate");
		
		String nian = kqdate.substring(0,4);
		int year = Integer.parseInt(nian);
		int days = 0;
		String month = kqdate.substring(5,7);
		
		days = getmonth_days(month,year);
		
		Map user = (Map)request.getSession().getAttribute("user");
		
		Map auditmap = dataBaseControl.getOneResultSet("select * from hr_kaoqin_audit t where t.dept_id = ? and t.kaoqin_date = ?", new Object[]{user.get("branchid"),kqdate});
		Hr_kaoqin_audit kaoqin_audit = new Hr_kaoqin_audit();
		BeanUtils.populate(kaoqin_audit, auditmap);
		
		Map pmap = dataBaseControl.getOneResultSet("select count(distinct(t.p_id))count from hr_kaoqin t where t.dept_id = ? and t.attend_date >= to_date(?,'yyyy-MM-dd') and t.attend_date <= to_date(?,'yyyy-MM-dd')", new Object[]{user.get("branchid"),kqdate+"-01",kqdate+"-"+days});
		int count = kaoqin_audit.getCount().intValue();
		String pcount = pmap.get("count").toString();
		
		if(count != Long.parseLong(pcount))
		{
			this.doResult(response, "此月尚有人未打考勤，不能提交！");
			return null;
		}

		kaoqin_audit.setState("1");
		try {
			dataBaseControl.beginTransaction();
			dataBaseControl.updateByBean(kaoqin_audit);
			dataBaseControl.executeSql("update hr_kaoqin t set t.zhuangt = '1' where t.dept_id = ? and t.attend_date >= to_date(?,'yyyy-MM-dd') and t.attend_date <= to_date(?,'yyyy-MM-dd')", new Object[]{user.get("branchid"),kqdate+"-01",kqdate+"-"+days});
			dataBaseControl.endTransaction();
		} catch (Exception e) {
			dataBaseControl.endTransaction();
		}
		
		
		
		return "/hr/absence/Hr_kaoqin!searchList.do";
	}
	
	
	public String toaudit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.displaymonth(request, response);
		
		
		return "/hr/absence/hr_audit_kaoqinList.jsp";
	}
	
	
	public String audit1(HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.displaymonth(request, response);
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdate = fmt.format(date);
		
		String snian = sdate.substring(0,4);
		String yue = sdate.substring(5,7);
	
		Map map2 = dataBaseControl.getOneResultSet("select to_char(add_months(to_date(?,'yyyy-MM'),-1),'yyyy-MM') kqdate from dual", new Object[]{snian+"-"+yue});
		
		String kqdate = request.getParameter("kqdate")==null?map2.get("kqdate").toString():request.getParameter("kqdate");
       
        Map user = (Map)request.getSession().getAttribute("user");
        Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ? and t.hr_type in('3','2')", new Object[]{user.get("base_info_id")});
        String duty_id = baseinfo.get("duty_id").toString();
        
        String dept_id = "";
		dept_id = user.get("branchid").toString();
		
		if(dept_id.equals("5"))
		{
			dept_id = "10";
		}
		
		Map deptmap = dataBaseControl.getOneResultSet("select * from mrbranch t where t.id = ?", new Object[]{dept_id});
        request.setAttribute("branchname", deptmap.get("branchname"));
        
        Map auditmap = dataBaseControl.getOneResultSet("select * from hr_kaoqin_audit t where t.dept_id = ? and t.kaoqin_date = ?", new Object[]{user.get("branchid"),kqdate});
		

		String nian = kqdate.substring(0,4);
		int year = Integer.parseInt(nian);
		int days = 0;
		String month = kqdate.substring(5,7);
		
		days = getmonth_days(month,year);
		
		List reslist = new ArrayList();
		
		Map smap = new HashMap();
		smap.put("dept_id", dept_id);
		smap.put("id", request.getParameter("p_id"));
		
		String sql = "select * from hr_base_info t where  t.duty_id =1 and t.hr_type in('2','3','6') "
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.dept_id={dept_id} ~/"
		+ " order by t.id ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,smap); 
		List plist=(List)dataBaseControl.getOutResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray());
		
		
		
		
		//List plist = (List)dataBaseControl.getOutResultSet("select * from hr_base_info t where t.dept_id = ? and  t.duty_id =1 and t.hr_type in('2','3','6') order by t.id", new Object[]{dept_id});
		for(int j = 0 ; j < plist.size();j++)
		{
			Map pmap = (Map)plist.toArray()[j];
			Map resmap = new HashMap();
			Map tmap = new HashMap();
			resmap.put("name", pmap.get("name"));
			for(int i = 1 ; i <= days;i++)
			{
				Map vacamap = dataBaseControl.getOneResultSet("select * from hr_vacation_arrangement t where t.vacation_date = to_date(?,'yyyy-MM-dd')", new Object[]{kqdate+"-"+i});
				if(vacamap != null && !vacamap.isEmpty())
				{
					continue;
				}
				
				tmap = dataBaseControl.getOneResultSet("select * from hr_kaoqin t where t.p_id = ? and t.attend_date = to_date(?,'yyyy-MM-dd')", new Object[]{pmap.get("id"),kqdate+"-"+i});
				if(tmap ==null || tmap.isEmpty())  //没有考勤记录，表示出勤
				{
					resmap.put("s"+i, "/");
					resmap.put("x"+i, "/");
				}
				else
				{
					String shangwu = tmap.get("am")+"";
					String xiawu = tmap.get("pm")+"";
					String note1 = tmap.get("amnote")+"";
					String note2 = tmap.get("pmnote")+"";
					resmap.put("snote"+i, note1);
					resmap.put("xnote"+i, note2);
					
					if(shangwu.equals("出勤"))
					{
						resmap.put("s"+i, "/");
					}
					if(xiawu.equals("出勤"))
					{
						resmap.put("x"+i, "/");
					}
					
					if(shangwu.equals("带薪假"))
					{
						resmap.put("s"+i, "¤");
					}
					if(xiawu.equals("带薪假"))
					{
						resmap.put("x"+i, "¤");
					}
					
					if(shangwu.equals("出差"))
					{
						resmap.put("s"+i, "√");
					}
					if(xiawu.equals("出差"))
					{
						resmap.put("x"+i, "√");
					}
					
					if(shangwu.equals("病假"))
					{
						resmap.put("s"+i, "○");
					}
					if(xiawu.equals("病假"))
					{
						resmap.put("x"+i, "○");
					}
					
					if(shangwu.equals("事假"))
					{
						resmap.put("s"+i, "+");
					}
					if(xiawu.equals("事假"))
					{
						resmap.put("x"+i, "+");
					}
					
					if(shangwu.equals("旷工"))
					{
						resmap.put("s"+i, "×");
					}
					if(xiawu.equals("旷工"))
					{
						resmap.put("x"+i, "×");
					}
					
					if(shangwu.equals("培训"))
					{
						resmap.put("s"+i, "◇");
					}
					if(xiawu.equals("培训"))
					{
						resmap.put("x"+i, "◇");
					}
					
					if(shangwu.equals("其他"))
					{
						resmap.put("s"+i, "其");
					}
					if(xiawu.equals("其他"))
					{
						resmap.put("x"+i, "其");
					}
					
					if(shangwu.equals("迟到"))
					{
						resmap.put("s"+i, "-");
					}
					if(xiawu.equals("迟到"))
					{
						resmap.put("x"+i, "-");
					}
					
				}
			}
			reslist.add(resmap);
			
		}
		
		request.setAttribute("reslist", reslist);
		request.setAttribute("kqdate", kqdate);
		request.setAttribute("nian", nian);
		request.setAttribute("yue", month);
		request.setAttribute("days", days);
		request.setAttribute("audit", auditmap.get("state"));
		request.setAttribute("kqid", auditmap.get("id"));	
		
		String forward = "";
		
		
		forward = "/hr/kaoqin/hr_kaoqinbaobiao.jsp";
		
		
		return forward;
	}
	
	
	public String saveaudit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		
		String kqdate = request.getParameter("kqdate");
		
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String nian = kqdate.substring(0,4);
			int year = Integer.parseInt(nian);
			int days = 0;
			String month = kqdate.substring(5,7);
			
			days = getmonth_days(month,year);
			
			
		Map user = (Map)request.getSession().getAttribute("user");
		String state = request.getParameter("state");
		Map auditmap = dataBaseControl.getOneResultSet("select * from hr_kaoqin_audit t where t.dept_id = ? and t.kaoqin_date=?", new Object[]{user.get("branchid"),kqdate});
		
		Hr_kaoqin_audit kaoqin_audit = new Hr_kaoqin_audit();
		BeanUtils.populate(kaoqin_audit, auditmap);
		
		kaoqin_audit.setOpinion(request.getParameter("opinion"));
		kaoqin_audit.setState(state);
		
		dataBaseControl.beginTransaction();
		dataBaseControl.updateByBean(kaoqin_audit);
		dataBaseControl.executeSql("update hr_kaoqin t set t.zhuangt = ? where t.dept_id = ? and t.attend_date >= to_date(?,'yyyy-MM-dd') and t.attend_date <= to_date(?,'yyyy-MM-dd')", new Object[]{state,kaoqin_audit.getDept_id(),kqdate+"-01",kqdate+"-"+days});
		dataBaseControl.endTransaction();
		request.setAttribute("operationSign", "closeDG_refreshW();");
		
		}
		request.setAttribute("kqdate", kqdate);
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/kaoqin/hr_kaoqin_opin.jsp";
	}
	
	
	/*
	 * 查看个人考勤记录
	 */
	public String view(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String kqdate = request.getParameter("kqdate");
        Map user = (Map)request.getSession().getAttribute("user");
		
		String nian = kqdate.substring(0,4);
		int year = Integer.parseInt(nian);
		int days = 0;
		String month = kqdate.substring(5,7);
		
		days = getmonth_days(month,year);
		
		String yue = request.getParameter("yue")==null?"":request.getParameter("yue");
		String p_id = request.getParameter("p_id")==null?"":request.getParameter("p_id");
		
		
		
		//获得被打考勤人员信息
		Map pmap = dataBaseControl.getOneResultSet("select t.*,m.branchname from hr_base_info t,mrbranch m where t.dept_id = m.id and t.id = ?", new Object[]{p_id});
		
		request.setAttribute("dept", pmap.get("branchname"));
		List reslist = new ArrayList();
		Map resmap = new HashMap();
		Map tmap = new HashMap();
		resmap.put("name", pmap.get("name"));
		for(int i = 1 ; i <= days;i++)
		{
				
			tmap = dataBaseControl.getOneResultSet("select * from hr_kaoqin t where t.p_id = ? and t.attend_date = to_date(?,'yyyy-MM-dd')", new Object[]{pmap.get("id"),kqdate+"-"+i});
			if(tmap ==null || tmap.isEmpty())  //没有考勤记录，表示出勤
			{
				resmap.put("s"+i, "/");
				resmap.put("x"+i, "/");
			}
			else
			{
				String shangwu = tmap.get("am")+"";
				String xiawu = tmap.get("pm")+"";
				String note1 = tmap.get("amnote")+"";
				String note2 = tmap.get("pmnote")+"";
				resmap.put("snote"+i, note1);
				resmap.put("xnote"+i, note2);
				
				if(shangwu.equals("出勤"))
				{
					resmap.put("s"+i, "/");
				}
				if(xiawu.equals("出勤"))
				{
					resmap.put("x"+i, "/");
				}
				
				if(shangwu.equals("带薪假"))
				{
					resmap.put("s"+i, "¤");
				}
				if(xiawu.equals("带薪假"))
				{
					resmap.put("x"+i, "¤");
				}
				
				if(shangwu.equals("出差"))
				{
					resmap.put("s"+i, "√");
				}
				if(xiawu.equals("出差"))
				{
					resmap.put("x"+i, "√");
				}
				
				if(shangwu.equals("病假"))
				{
					resmap.put("s"+i, "○");
				}
				if(xiawu.equals("病假"))
				{
					resmap.put("x"+i, "○");
				}
				
				if(shangwu.equals("事假"))
				{
					resmap.put("s"+i, "+");
				}
				if(xiawu.equals("事假"))
				{
					resmap.put("x"+i, "+");
				}
				
				if(shangwu.equals("旷工"))
				{
					resmap.put("s"+i, "×");
				}
				if(xiawu.equals("旷工"))
				{
					resmap.put("x"+i, "×");
				}
				
				if(shangwu.equals("培训"))
				{
					resmap.put("s"+i, "◇");
				}
				if(xiawu.equals("培训"))
				{
					resmap.put("x"+i, "◇");
				}
				
				if(shangwu.equals("其他"))
				{
					resmap.put("s"+i, "其");
				}
				if(xiawu.equals("其他"))
				{
					resmap.put("x"+i, "其");
				}
				
				if(shangwu.equals("迟到"))
				{
					resmap.put("s"+i, "-");
				}
				if(xiawu.equals("迟到"))
				{
					resmap.put("x"+i, "-");
				}
				
			}
		}
		reslist.add(resmap);
		request.setAttribute("nian", nian);
		request.setAttribute("yue", month);
		request.setAttribute("days", days);
		request.setAttribute("reslist", reslist);
		return "/hr/absence/hr_kaoqinbaobiao.jsp";
	}
	
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}
	
	private Page auditsearch(Map searchMap,int pageNo,int pageSize) throws Exception 
	{
		String sql="select distinct to_char(t.attend_date,'yyyy-MM')khdate,t.zhuangt from hr_kaoqin t where 1 =1  " 
		+ "/~ and t.dept_id={dept_id} ~/"
		+ "/~ and to_char(t.attend_date, 'yyyy-MM') = {kqdate} ~/"
		
		+ " order by to_char(t.attend_date,'yyyy-MM') desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String kaoqinaudit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals(""))? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		this.displaymonth(request, response);
		
		Map user = (Map)request.getSession().getAttribute("user");
		String dept_id = "";
		dept_id = user.get("branchid").toString();
		
		if(dept_id.equals("5"))
		{
			dept_id = "10";
		}

		Map searchMap = getParameterMap(request);
		searchMap.put("dept_id", user.get("branchid"));
		
		
		
		Page page=auditsearch(searchMap,pageNo,pageSize);
		
		
		request.setAttribute("searchMap", searchMap);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		return "/hr/kaoqin/hr_audit_kaoqinList.jsp";
	}
	
	public String selfview(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map searchMap = getParameterMap(request);
		searchMap.put("p_id", user.get("base_info_id"));
		Page page=search(searchMap,pageNo,pageSize);
		
		
		request.setAttribute("searchMap", searchMap);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		return "/hr/kaoqin/hr_selfkaoqinList.jsp";
	}

}