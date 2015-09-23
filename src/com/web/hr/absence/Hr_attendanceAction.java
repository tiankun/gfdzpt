package com.web.hr.absence;

import java.io.File;
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

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.MyFileUpload;

import com.map.Hr_attendance;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Hr_attendanceAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		
		
		Page page=search(request,pageNo,pageSize);
		
		Map map =getParameterMap(request);
		
		Map basemap=dataBaseControl.getOneResultSet("select * from hr_base_info b where b.id = ?", new Object[]{map.get("p_id")});
		
		map.put("name", basemap.get("name"));
		request.setAttribute("searchMap", map);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/absence/hr_attendanceSelect.jsp";
		return "/hr/absence/hr_attendanceList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/absence/hr_attendanceDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*,m.branchname,b.name from hr_attendance t, mrbranch m, hr_base_info b where t.p_id = b.id and t.dept_id = m.id  " 
		+ "/~ and t.id={id} ~/"
		+ "/~ and b.name={name} ~/"
		+ "/~ and t.p_id={p_id} ~/ "
		+ "/~ and t.dept_id={dept_id} ~/ "
		+ "/~ and attend_date>= to_date({attend_date1},'yyyy-MM-dd') ~/ "
		+ "/~ and attend_date<= to_date({attend_date2},'yyyy-MM-dd') ~/ ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	/**
	 * 批量导入考勤信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String alladd(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(getMapObject(request));			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/absence/hr_all_attendanceDtl.jsp";
	}
	
	
	public String upload(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		MyFileUpload up = new MyFileUpload();
		up.setHeaderEncoding("GBK");
		up.setSizeMax(1 * 1024 *1024);
		up.setSizeThreshold(4);
		up.upload(request);
		
		String IsPostBack = up.getParameter("IsPostBack");
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("operationSign", "closeDG_refreshW();");

		Object[] files = up.getFiles();
		FileItem fi1 = null;
		fi1 = (FileItem) files[0];
		
		String filepath = request.getSession().getServletContext().getRealPath(
		"/")
		+ File.separatorChar
		+ "upFile"
		+ File.separatorChar
		+ "hr"
		+ File.separatorChar
		+ "absenceattendance";
        File dir = new File(filepath);
         if (!dir.exists())
	         dir.mkdirs();
         
         Date today = new Date();

        String filename = today.getTime() + ".xls";

        File newfile = new File(filepath + File.separatorChar + filename);
        fi1.write(newfile);
        
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        Map user = (Map)request.getSession().getAttribute("user");
        
        Workbook book = Workbook.getWorkbook(new File(filepath
				+ File.separatorChar + filename));
        
        Sheet sheet = book.getSheet(0);
        
        
        String ppname = sheet.getCell(1, 1).getContents();
		Map delmap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.name = ?", new Object[]{ppname});
		
        
        
        
        String attendancedate = sheet.getCell(2, 1).getContents();
        String nian = attendancedate.substring(0, 4);
        int year = Integer.parseInt(nian);
        String month = attendancedate.substring(attendancedate.indexOf("/")+1, attendancedate.lastIndexOf("/"));
        String days = "";
        
        if(month.equals("1")||month.equals("3")||month.equals("5")||month.equals("7")||month.equals("8")||month.equals("10")||month.equals("12"))
		{
			days = "31";
		}
		
		else if(month.equals("4")||month.equals("6")||month.equals("9")||month.equals("11"))
		{
			days = "30";
		}
		else 
		{
			if((year%4==0&&year%100!=0)||year%400==0)//闰年
			{
				days = "29";
			}
			else
			{
				days = "28";
			}
		}
        
        List elist = dataBaseControl.getOutResultSet("select * from hr_attendance t where t.dept_id = ? and t.attend_date >= to_date(?,'yyyy-MM-dd') and t.attend_date <= to_date(?,'yyyy-MM-dd')", new Object[]{delmap.get("dept_id"),nian+"-"+month+"-01",nian+"-"+month+"-"+days});
        
        dataBaseControl.beginTransaction();
        if(elist != null && !elist.isEmpty())
        {
        	 dataBaseControl.executeSql("delete from hr_attendance t where t.dept_id = ? and t.attend_date >= to_date(?,'yyyy-MM-dd') and t.attend_date <= to_date(?,'yyyy-MM-dd')", new Object[]{delmap.get("dept_id"),nian+"-"+month+"-01",nian+"-"+month+"-"+days});	
        }
       
		int tiaoshu = 0;
		
		for (int i = 1; i < sheet.getRows(); i++) {
			Map map = new HashMap();
			Hr_attendance attendance = new Hr_attendance();
			String deptname = sheet.getCell(0, i).getContents();
			String pname = sheet.getCell(1, i).getContents();
			
			map = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.name = ?", new Object[]{pname});
			if(!map.isEmpty()){
				attendance.setDept_id(new Long(map.get("dept_id").toString()));
				attendance.setP_id(new Long(map.get("id").toString())); 
				attendance.setAttend_date(new java.sql.Date((fmt.parse(sheet.getCell(2, i).getContents())).getTime()));
				attendance.setAm_order_attend(sheet.getCell(3, i).getContents());
				attendance.setPm_order_attend(sheet.getCell(4, i).getContents());
				attendance.setAm_actual_attend(sheet.getCell(5, i).getContents());
				attendance.setPm_actual_attend(sheet.getCell(6, i).getContents());
				attendance.setInput_person(new Long(user.get("base_info_id").toString()));
				attendance.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
				dataBaseControl.insertByBean(attendance);
				tiaoshu++;
			}
			
		}
		dataBaseControl.endTransaction();
		
		return "/hr/absence/hr_all_attendanceDtl.jsp";
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
		return "/hr/absence/hr_attendanceDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/absence/hr_attendanceDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Hr_attendance WHERE ID in (?)", new Object[]{s_id});
		
		return "/hr/absence/Hr_attendance!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}