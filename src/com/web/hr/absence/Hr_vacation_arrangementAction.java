package com.web.hr.absence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.map.Hr_vacation_arrangement;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;


public class Hr_vacation_arrangementAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();
	
	public String searchList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map searchMap=getParameterMap(request);
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
							? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("searchMap", searchMap);
		
		return "/hr/absence/vacationset.jsp";
	}
	
	/**
	 * 批量将一年的周六周天设为法定假日
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String alladd(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/absence/vacationDtl.jsp";
	}
	
	
	/**
	 * 将周六周天设为法定假日，保存进数据库
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String saveyear(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String syear = request.getParameter("year");
		int year = Integer.parseInt(syear);
		int days = 0;
		
		
		dataBaseControl.executeSql("delete from hr_vacation_arrangement t where to_char(t.vacation_date,'yyyy') = ? ", new Object[]{syear});
		
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 1 ; i < 13 ; i++ )
        {
        	String month = "" + i ;
        	if(month.equals("1")||month.equals("3")||month.equals("5")||month.equals("7")||month.equals("8")||month.equals("10")||month.equals("12"))
    		{
    			days = 31;
    		}
    		
    		else if(month.equals("4")||month.equals("6")||month.equals("9")||month.equals("11"))
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
        	
        	for(int j = 1 ; j <= days ; j++ )
        	{
        		String date = syear + "-" + month + "-" + j;
        		Date ddate = dFormat.parse(date);
        		String day = dateFm.format(ddate);
        		if(day.equals("星期六")||day.equals("星期日"))
        		{
        			Hr_vacation_arrangement vacation_arrangement = new Hr_vacation_arrangement();
        			vacation_arrangement.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
        			vacation_arrangement.setNote(day);
        			vacation_arrangement.setVacation_date(new java.sql.Date((dFormat.parse(date)).getTime()));
        			dataBaseControl.insertByBean(vacation_arrangement);
        		}
        		
        	}	
        }
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/hr/absence/vacationDtl.jsp";
	}
	
	/**
	 * 添加单条法定假日
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map map = getParameterMap(request);
			Hr_vacation_arrangement vacation_arrangement = new Hr_vacation_arrangement();
			BeanUtils.populate(vacation_arrangement, map);
			int i =dataBaseControl.insertByBean(vacation_arrangement);
			codeTableUtil.cleanCodeMap();
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/absence/hr_vacation_arrangementDtl.jsp";
	}
	
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map map = getParameterMap(request);
			Hr_vacation_arrangement vacation_arrangement = new Hr_vacation_arrangement();
			BeanUtils.populate(vacation_arrangement, map);
			int i = dataBaseControl.updateByBean(vacation_arrangement);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		return "/hr/absence/hr_vacation_arrangementDtl.jsp";
	}
	
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		//buildDDL(request);
		return "/hr/absence/hr_vacation_arrangementDtl.jsp";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Hr_vacation_arrangement WHERE ID in (?)", new Object[]{s_id});
		
		return "/hr/absence/Hr_vacation_arrangement!searchList.do";
	}
	
	
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		String sql= "select * from hr_vacation_arrangement where 1=1  " 
			      + "/~ and id = {id} ~/"
				  + "/~ and vacation_date >= to_date({vacationDate1},'yyyy-MM-dd') ~/"
				  + "/~ and vacation_date <= to_date({vacationDate2},'yyyy-MM-dd') ~/"
		          + " order by id desc"; 
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql, getParameterMap(request));
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(), pageNo,pageSize);
		return page;
	}
	
	
	

}
