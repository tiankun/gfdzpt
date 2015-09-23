package com.web.hr.info;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.phprpc.PHPRPC_Client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.map.Hr_base_info;
import com.map.Hr_opinion;
import com.map.Hr_test;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.DateUtil;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Hr_base_infoAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("searchMap", searchMap);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/info/hr_base_infoSelect.jsp";
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("mulSelect")) 
			return "/hr/info/hr_base_infoMulSelect.jsp";		
		return "/hr/info/hr_base_infoList.jsp";
	}
	public String getJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageNo") == null || request.getParameter("pageNo").equals("")) 
				? "1" : request.getParameter("pageNo");
		int pageNo=(new Integer(page_no)).intValue();
		Page page = search(request, pageNo, 20);
		String js = "{page:{allPage:"+page.getLastPageNumber()+
				",current:"+page.getThisPageNumber()+
				",allRecode:"+page.getTotalNumberOfElements()
				+"},rows:"+JSON.toJSONString(page.getThisPageElements())+"}";
		setAjaxInfo(response,js);
		return null;
	}
	
	/**
	 * 以下为人事生命周期全过程管理
	 */
	
	/**
	 * 应聘管理
	 */
	//应聘人员列表
	public String testList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String dept_id = branchid.toString();
		if(!(dept_id.equals("2")||dept_id.equals("1"))){
			searchMap.put("dept_id", dept_id);
		}
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
							? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		String sql="select hb.*,ht.position positiont," +
				"(select max(hd.degree) from hr_degree hd where hb.id=hd.p_id and hd.xian_shi='1') degree," +
				"(select max(hd.major) from hr_degree hd where hb.id=hd.p_id and hd.xian_shi='1') major," +
				"(select ho.hr_state from hr_opinion ho where hb.id=ho.p_id and hr_type = 1) hr_state " +
				"from HR_Base_info hb,hr_test ht " +
				"where 1=1 and hb.id=ht.p_id(+) and (hb.hr_type = 0 or hb.hr_type = 1) " +
				"/~ and hb.name like '%[name]%' ~/" +
				"/~ and hb.sex like '%[sex]%' ~/" +
				"/~ and hb.zheng_zhi like '%[zheng_zhi]%' ~/" +
				"/~ and ht.position like '%[position]%' ~/" +
				"/~ and hb.dept_id like '%[dept_id]%' ~/";
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap);
		Page page=(Page) dataBaseControl.getPageResultSet(xsql.getXsql(),
				xsql.getAcceptedFilters().values().toArray(), pageNo, pageSize);
		//Page page=search1(request,pageNo,pageSize);
		System.out.println(xsql.getXsql()+"…………………………………………………………………………………………");
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("searchMap", searchMap);
		buildDDL(request);
		
		return "/hr/info/testList.jsp";
	}
	
	//新增应聘人员  这里是流程的开始
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String pos = getParameterMap(request).get("position");
		Long pid=0l;
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			pid=Long.parseLong(request.getParameter("id"));
			dataBaseControl.beginTransaction();
			int i =dataBaseControl.insertByBean(getMapObject(request),pid);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			Long id=dataBaseControl.getSeq(Hr_test.class);
			
			//在新增人员时，同时新增面试岗位信息
			dataBaseControl.executeSql("insert into hr_test (id,p_id,position) values(?,?,?)", new Object[]{id,pid,pos});
			dataBaseControl.endTransaction();
		}else{
			pid=dataBaseControl.getSeq(Hr_base_info.class);
			request.setAttribute("pid", pid);
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String lr_time = dFormat.format(today);
		
		buildDDL(request);
		request.setAttribute("pid", pid);
		request.setAttribute("lr_time", lr_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/info/hr_base_infoDtl.jsp";
	}
	
	//修改应聘人员信息
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String pos = getParameterMap(request).get("position");
		String id = getParameterMap(request).get("id");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			dataBaseControl.beginTransaction();
			int i = dataBaseControl.updateByBean(getMapObject(request));
			dataBaseControl.executeSql("update hr_test set position=? where p_id=?", new Object[]{pos,id});
			dataBaseControl.endTransaction();
			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search1(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/info/hr_base_infoDtl.jsp";
	}
	
	//查看人员信息
	public String view(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   String id = request.getParameter("id")==null?"":request.getParameter("id");
	   String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
	   String hr_type = request.getParameter("hr_type")==null?"":request.getParameter("hr_type");
	   if(id.equals(""))
	   {
		   this.setAjaxInfo(response, "未找到此人员信息");
		   return null;
	   }
	   Page page=search1(request,1,1);
	   request.setAttribute("base_info", ((ArrayList)page.getThisPageElements()).get(0));
	   this.buildDDL(request);
	   request.setAttribute("hr_type",hr_type);
	   
	   return "/hr/info/viewstaff.jsp";
	}	

	
	
	/**
	 * 入职管理
	 */
	//入职人员列表
	public String entryList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String dept_id = branchid.toString();
		if(!(dept_id.equals("2")||dept_id.equals("1"))){
			searchMap.put("dept_id", dept_id);
		}
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
							? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		String sql="select hb.*,(select branchname from mrbranch where id=hb.dept_id) dept_name," +
				"(select hr_state from hr_opinion where hb.id=p_id and hr_type=2) hr_state," +
				"(select entry_pay from hr_test where hb.id=p_id) entry_pay " +
				"from HR_Base_info hb " +
				"where 1=1 and hb.hr_type = 2 " +
				"/~ and hb.name like '%[name]%' ~/" +
				"/~ and hb.sex like '%[sex]%' ~/" +
				"/~ and hb.zheng_zhi like '%[zheng_zhi]%' ~/" +
				"/~ and hb.position like '%[position]%' ~/" +
				"/~ and hb.dept_id like '%[dept_id]%' ~/";
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap);
		Page page=(Page) dataBaseControl.getPageResultSet(xsql.getXsql(),
				xsql.getAcceptedFilters().values().toArray(), pageNo, pageSize);
		//Page page=search1(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("searchMap", searchMap);
		buildDDL(request);
		
		return "/hr/info/entryList.jsp";
	}
	
	//入职人员详细信息填报
	public String editDtl(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			dataBaseControl.beginTransaction();
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			Long oid=dataBaseControl.getSeq(Hr_opinion.class);
			//在详细信息填报后，同时新增审核意见信息
			dataBaseControl.executeSql("insert into hr_opinion (id,p_id,hr_state,hr_type) values(?,?,?,?)", new Object[]{oid,id,"5","2"});
			dataBaseControl.endTransaction();
		}else{
			Page page=search1(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/info/infoDtl.jsp";
	}
		
	/**
	 * 员工管理
	 */
	//员工列表
	public String staffList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String dept_id = branchid.toString();
		if(!(dept_id.equals("2")||dept_id.equals("1"))){
			searchMap.put("dept_id", dept_id);
		}
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
							? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		String sql="select hb.*,(select branchname from mrbranch where id=hb.dept_id) dept_name," +
				"(select max(hd.degree) from hr_degree hd where hb.id=hd.p_id and hd.xian_shi='1') degree," +
				"(select max(hd.major) from hr_degree hd where hb.id=hd.p_id and hd.xian_shi='1') major," +
				"(select hr_state from hr_opinion where p_id=hb.id and hr_type=6) hr_state," +
				"(select c.dmzmc from code c where c.dmz=hb.hr_type and c.dmlb='hr_type')rzzt from HR_Base_info hb " +
				"where 1=1 " +
				"/~ and hb.name like '%[name]%' ~/" +
				"/~ and hb.hr_type={hr_type} ~/" +
				"/~ and hb.sex like '%[sex]%' ~/" +
				"/~ and hb.position like '%[position]%' ~/" +
				"/~ and hb.dept_id like '%[dept_id]%' ~/ order by hb.hr_type asc, hb.dept_id desc nulls last";
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap);
		Page page=(Page) dataBaseControl.getPageResultSet(xsql.getXsql(),
				xsql.getAcceptedFilters().values().toArray(), pageNo, pageSize);
		//Page page=search1(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("searchMap", searchMap);
		buildDDL(request);
		
		return "/hr/info/staffList.jsp";
	}
	
	//已有员工录入
	public String addStaff(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		Long idLong = 0l;
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			idLong = Long.parseLong(request.getParameter("id"));
			int i =dataBaseControl.insertByBean(getMapObject(request),idLong);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			idLong = dataBaseControl.getSeq(Hr_base_info.class);
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String lr_time = dFormat.format(today);
		
		request.setAttribute("pid", idLong);
		
		buildDDL(request);
		request.setAttribute("lr_time", lr_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/info/addStaff.jsp";
	}
	
	
	//员工信息修改入口
	public String editStaff(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search1(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/info/infoStaffDtl.jsp";
	}
	
	/**
	 *	人才库管理
	 */
	//人才库列表  Talents Warehouse List
	public String twhList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		Map myuser=(Map)request.getSession().getAttribute("user");
		BigDecimal branchid = (BigDecimal) myuser.get("branchid");
		String dept_id = branchid.toString();
		if(!(dept_id.equals("2")||dept_id.equals("1"))){
			searchMap.put("dept_id", dept_id);
		}
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
							? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		String sql="select hb.*,(select branchname from mrbranch where id=hb.dept_id) dept_name," +
				"(select max(hd.degree) from hr_degree hd where hb.id=hd.p_id and hd.xian_shi='1') degree," +
				"(select max(hd.major) from hr_degree hd where hb.id=hd.p_id and hd.xian_shi='1') major," +
				"(select hr_state from hr_opinion where p_id=hb.id and hr_type=1) hr_state " +
				"from HR_Base_info hb " +
				"where 1=1 and hb.hr_type=5 " +
				"/~ and hb.name like '%[name]%' ~/" +
				"/~ and hb.sex like '%[sex]%' ~/" +
				"/~ and hb.position like '%[position]%' ~/" +
				"/~ and hb.dept_id like '%[dept_id]%' ~/";
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap);
		Page page=(Page) dataBaseControl.getPageResultSet(xsql.getXsql(),
				xsql.getAcceptedFilters().values().toArray(), pageNo, pageSize);
		//Page page=search1(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("searchMap", searchMap);
		buildDDL(request);
		
		return "/hr/info/twhList.jsp";
	}	
		
	
	/**
	 *	公用功能 
	 */
	//人员全信息查看	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		Map map = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ? ", new Object[]{id});
		String showFlag = request.getParameter("showFlag");
		String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
		if(map.size()==0){
			return "/hr/info/rep.jsp";
		}
		String hr_type = map.get("hr_type").toString();
		
		Page page=search1(request,1,1);

		request.setAttribute("base_info", map);
		//request.setAttribute("showFlag", showFlag);
		request.setAttribute("id", id);
		//request.setAttribute("flag", flag);
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		request.setAttribute("hr_type", hr_type);
		//buildDDL(request);
		return "/hr/info/baseinfo.jsp";
	}
	
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*,m.branchname from Hr_base_info t,mrbranch m where t.dept_id = m.id and 1=1 and t.hr_type in('2','3','6') /~ and t.id={id} ~/"
		+ "/~ and t.name like '%[name]%' ~/ "
		+ "/~ and t.dept_id={dept_id} ~/ "
		+ "/~ and t.birthday={birthday} ~/ "
		+ "/~ and t.jr_time={jr_time} ~/ " +
		"/~ and t.hr_type={hr_type} ~/ " +
		"/~ and t.sex like '%[sex]%' ~/" +
		"/~ and t.zheng_zhi like '%[zheng_zhi]%' ~/"+
		"  order by t.dept_id,t.id ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select hb.*,(select branchname from mrbranch where id=hb.dept_id) dept_name," +
				"(select name from sys_area where id=hb.hu_ji) huji_name " +
				" from HR_Base_info hb where 1=1 /~ and hb.id={id} ~/"
				+ "/~ and hb.name={name} ~/ "
				+ "/~ and hb.dept_id={dept_id} ~/ "
				+ "/~ and hb.birthday={birthday} ~/ "
				+ "/~ and hb.jr_time={jr_time} ~/ " +
				"/~ and hb.hr_type={hr_type} ~/ " +
				"/~ and hb.sex like '%[sex]%' ~/" +
				"/~ and hb.zheng_zhi like '%[zheng_zhi]%' ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		dataBaseControl.executeSql("update hr_base_info t set t.subflag='1' where t.id=?", new Object[]{id});
		
		return "/hr/info/Hr_base_info!testList.do";
	}
	
	public String totest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String hr_state = request.getParameter("hr_state")==null?"":request.getParameter("hr_state");
		dataBaseControl.executeSql("update Hr_opinion t set t.hr_state=? where t.p_id=? and t.hr_type=6", new Object[]{hr_state,id});
		
		return "/hr/process/Hr_apply!searchList.do";
	}
	
	//级联删除----业务上不合理 暂时停用
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		
		dataBaseControl.beginTransaction();
		dataBaseControl.executeSql("DELETE FROM Hr_base_info WHERE ID in (?)", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Hr_family WHERE p_id in (?)", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Hr_degree WHERE p_id in (?)", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Hr_resume WHERE p_id in (?)", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Hr_reward WHERE p_id in (?)", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Hr_certificate WHERE p_id in (?)", new Object[]{s_id});
		dataBaseControl.executeSql("DELETE FROM Hr_pchange WHERE p_id in (?)", new Object[]{s_id});
		dataBaseControl.endTransaction();
		
		return "/hr/info/Hr_base_info!testList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		Map<String,Object> mrbranch = getMrbranchMap();
		request.setAttribute("mrbranch",mrbranch); 
		request.setAttribute("dept_id",codeTableUtil.getCodeMap("dept_id")); 
		request.setAttribute("sex",codeTableUtil.getCodeMap("sex"));
		request.setAttribute("zheng_zhi",codeTableUtil.getCodeMap("zheng_zhi"));
		request.setAttribute("hr_type",codeTableUtil.getCodeMap("hr_type"));
		request.setAttribute("hr_state",codeTableUtil.getCodeMap("hr_state"));
		request.setAttribute("marriage",codeTableUtil.getCodeMap("marriage"));
		request.setAttribute("card_type",codeTableUtil.getCodeMap("card_type"));
		request.setAttribute("hu_kou",codeTableUtil.getCodeMap("hu_kou"));
		request.setAttribute("duty_id",codeTableUtil.getCodeMap("duty_id")); 
		request.setAttribute("degree",codeTableUtil.getCodeMap("degree"));
	}

	/*获取部门Map*/
	@SuppressWarnings("static-access")
	private Map<String, Object> getMrbranchMap() throws Exception{
		Map<String,Object> mrbranch = new HashMap<String,Object>();
		String sql = "select t.id,t.branchname from MRBRANCH t order by t.id  ";		
		List<Map<String,Object>> mrbranchList = dataBaseControl.getOutResultSet(sql, null);
		
		JSONArray jsonArr = JSONArray.parseArray(JSON.toJSONString(mrbranchList));
		for (Object object : jsonArr) {
			String id = jsonArr.parseObject(object.toString()).getString("id");
			String value = jsonArr.parseObject(object.toString()).getString("branchname");
			mrbranch.put(id, value);
		}
		return mrbranch;
	}
	
	public void cardVerify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String cardNum = request.getParameter("cardNum")==null?"":request.getParameter("cardNum");
		Map map = dataBaseControl.getOneResultSet("select t.id from hr_base_info t where t.card_num=?", new Object[]{cardNum});
		String msg = "";
		if(map.size()!=0){
			msg = "此证件号已存在!";
		}
		else{
			msg = "";
		}
		
		PrintWriter writer =response.getWriter();
		writer.write(msg);
		writer.flush();
		writer.close();
	}

}