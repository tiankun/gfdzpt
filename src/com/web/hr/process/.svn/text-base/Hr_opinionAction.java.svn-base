package com.web.hr.process;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.phprpc.PHPRPC_Client;

import com.map.Hr_apply;
import com.map.Hr_test;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.DateUtil;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Hr_opinionAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/process/hr_opinionSelect.jsp";
		return "/hr/process/hr_opinionList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/process/hr_opinionDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Hr_opinion where 1=1 /~ and p_id={p_id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Hr_opinion where 1=1 and hr_type='1' /~ and p_id={p_id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page search2(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Hr_opinion where 1=1 and hr_type='2' /~ and p_id={p_id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page search3(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Hr_opinion where 1=1 and hr_type='6' /~ and p_id={p_id} ~/";
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
		return "/hr/process/hr_opinionDtl.jsp";
	}
	
	//应聘审批
	public String editApply(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String hr_state = request.getParameter("hr_state")==null?"":request.getParameter("hr_state");
		String p_id = request.getParameter("p_id")==null?"":request.getParameter("p_id");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			dataBaseControl.beginTransaction();
			int i = dataBaseControl.updateByBean(getMapObject(request));
			//更改任职状态
			if(hr_state.equals("3")||hr_state.equals("4")){
				String type;
				type = hr_state.equals("3")?"2":"5";
				dataBaseControl.executeSql("update Hr_Base_Info t set t.hr_type=? where t.id=?", new Object[]{type,p_id});
			}
			dataBaseControl.endTransaction();
			//发短信
			String num = "";
			List plist;
			if(hr_state.equals("1")||hr_state.equals("2")){
				if(hr_state.equals("1")){
					plist = dataBaseControl.getOutResultSet("select * from message t " +
					"where t.rolename = '1' " +
					"and t.dept = (select hb.dept_id from hr_base_info hb where hb.id=?)", new Object[]{p_id});
					if(plist != null && !plist.isEmpty())
					{
						num = ""+((Map)plist.toArray()[0]).get("num");
					}
				}
				else{
					plist = dataBaseControl.getOutResultSet("select * from message where rolename='5'", null);
					if(plist != null && !plist.isEmpty())
					{
						num = ""+((Map)plist.toArray()[0]).get("num");
					}
				}
			}
			
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			
			String infor="您有一条应聘审批请求，请您审批！";
			m.sendGFDZ(num,infor);
			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		else{
			Page page=search1(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String e_date = dFormat.format(today);
		
		request.setAttribute("e_date", e_date);
		request.setAttribute("editMod", "editApply");
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("hr_state", hr_state);
		//buildDDL(request);
		return "/hr/process/hr_opinionDtl.jsp";
	}
	
	//入职审批
	public String editTry(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String p_id = request.getParameter("p_id")==null?"":request.getParameter("p_id");
		String id = request.getParameter("id")==null?request.getParameter("apply_id"):request.getParameter("id");
		String entry_pay = request.getParameter("entry_pay")==null?"":request.getParameter("entry_pay");
		String entry_date = request.getParameter("entry_date")==null?"":request.getParameter("entry_date");
		String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
		String IsPostBack = request.getParameter("IsPostBack");
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			dataBaseControl.beginTransaction();
			int i = dataBaseControl.executeSql("update Hr_test t set t.entry_pay=?,t.entry_date=to_date(?,'yyyy-mm-dd') where t.p_id=? ", new Object[]{entry_pay,entry_date,p_id});
			if(flag.equals("regular")){
				dataBaseControl.executeSql("update hr_apply t set t.appstate='6' where t.id=?" , new Object[]{id});
				dataBaseControl.executeSql("delete from hr_opinion t where t.p_id=? and t.hr_type='6' " , new Object[]{p_id});
			}
			if(i==1){
				dataBaseControl.executeSql("update Hr_opinion t set t.hr_state='2' where t.p_id=? and t.hr_type='2' ", new Object[]{p_id});
				request.setAttribute("operationSign", "closeDG_refreshW();");
			}
			dataBaseControl.endTransaction();
		}
		else{
			Map map=dataBaseControl.getOneResultSet("select * from hr_test where p_id=? ", new Object[]{p_id});
			request.setAttribute("record", map);
		}
		
		request.setAttribute("apply_id", id);
		request.setAttribute("flag", flag);
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("p_id", p_id);
		
		return "/hr/process/editTry.jsp";
	}
	
	//确认
	public String affirm(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String p_id = request.getParameter("p_id")==null?"":request.getParameter("p_id");
		dataBaseControl.beginTransaction();
		int i = dataBaseControl.executeSql("update Hr_opinion t set t.hr_state='3' where t.p_id=? and t.hr_type='2' ", new Object[]{p_id});
		if(i==1)
			dataBaseControl.executeSql("update Hr_base_info t set t.hr_type='6' where t.id=?", new Object[]{p_id});
		dataBaseControl.endTransaction();
		return "/hr/info/Hr_base_info!entryList.do";
	}
	//暂时停用
	//入职审批
//	public String editTry(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		String hr_state = request.getParameter("hr_state")==null?"":request.getParameter("hr_state");
//		String p_id = request.getParameter("p_id")==null?"":request.getParameter("p_id");
//		String IsPostBack = request.getParameter("IsPostBack");
//		if(hr_state.equals("1")){
//			int i = dataBaseControl.executeSql("update Hr_opinion t set t.hr_state=? where t.p_id=? and t.hr_type='2' ", new Object[]{hr_state,p_id});
//			return "/hr/info/Hr_base_info!entryList.do";
//		}
//		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
//			int i = dataBaseControl.updateByBean(getMapObject(request));
//			
//			//发短信
//			String num = "";
//			List plist;
//			if(hr_state.equals("0")||hr_state.equals("1")||hr_state.equals("2")){
//				if(hr_state.equals("0")){
//					plist = dataBaseControl.getOutResultSet("select * from sys_message t where t.id=19", null);
//					if(plist != null && !plist.isEmpty())
//					{
//						num = ""+((Map)plist.toArray()[0]).get("mobile_num");
//					}
//				}
//				else if(hr_state.equals("1")){
//					plist = dataBaseControl.getOutResultSet("select * from sys_message t " +
//							"where t.function_name = '人事流程审批' " +
//							"and t.step = '部门审批' " +
//							"and t.dept_id = (select hb.dept_id from hr_base_info hb where hb.id=?)", new Object[]{p_id});
//					if(plist != null && !plist.isEmpty())
//					{
//						num = ""+((Map)plist.toArray()[0]).get("mobile_num");
//					}
//				}
//				else{
//					plist = dataBaseControl.getOutResultSet("select * from sys_message t where t.id=20", null);
//					if(plist != null && !plist.isEmpty())
//					{
//						num = ""+((Map)plist.toArray()[0]).get("mobile_num");
//					}
//				}
//			}
//			
//			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
//			Mas m = (Mas) client.useService(Mas.class);
//			
//			String infor="您有一条入职审批请求，请您审批！";
//			m.sendGFDZ(num,infor);
//			//短信结束
//			
//			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
//			if(hr_state.equals("3")||hr_state.equals("4")){
//				String type;
//				type = hr_state.equals("3")?"6":"5";
//				dataBaseControl.executeSql("update Hr_Base_Info t set t.hr_type=? where t.id=?", new Object[]{type,p_id});
//			}
//		}
//		else{
//			Page page=search2(request,1,1);
//			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
//			request.setAttribute("record", record.get(0));
//		}
//		
//		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date today = new Date();
//		String e_date = dFormat.format(today);
//		
//		request.setAttribute("e_date", e_date);
//		request.setAttribute("editMod", "editTry");
//		request.setAttribute("IsPostBack", "1");
//		request.setAttribute("hr_state", hr_state);
//		//buildDDL(request);
//		return "/hr/process/hr_opinionDtl.jsp";
//	}
		
	//转正审批
	public String editRegular(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String hr_state = request.getParameter("hr_state")==null?"":request.getParameter("hr_state");
		String p_id = request.getParameter("p_id")==null?"":request.getParameter("p_id");
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String apply_id = request.getParameter("apply_id")==null?"":request.getParameter("apply_id");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			dataBaseControl.beginTransaction();
			int i = dataBaseControl.updateByBean(getMapObject(request));
			dataBaseControl.executeSql("update hr_apply t set t.appstate=? where t.id=?", new Object[]{hr_state,apply_id});
			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
			if(hr_state.equals("3")||hr_state.equals("4")){
				String type;
				type = hr_state.equals("3")?"3":"5";
				dataBaseControl.executeSql("update Hr_Base_Info t set t.hr_type=? where t.id=?", new Object[]{type,p_id});
			}
			dataBaseControl.endTransaction();
			//发短信
			String num = "";
			List plist;
			if(hr_state.equals("2")){
				plist = dataBaseControl.getOutResultSet("select * from message where rolename='5'", null);
				if(plist != null && !plist.isEmpty())
				{
					num = ""+((Map)plist.toArray()[0]).get("num");
				}
			}
			
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			
			String infor="您有一条转正审批请求，请您审批！";
			m.sendGFDZ(num,infor);
			//短信结束
		}
		else{
			Page page=search3(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String e_date = dFormat.format(today);
		
		request.setAttribute("apply_id", id);
		request.setAttribute("e_date", e_date);
		request.setAttribute("editMod", "editRegular");
		request.setAttribute("IsPostBack", "1");
		request.setAttribute("hr_state", hr_state);
		//buildDDL(request);
		return "/hr/process/hr_opinionDtl.jsp";
	}	
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Hr_opinion WHERE ID in (?)", new Object[]{s_id});
		
		return "/hr/process/Hr_opinion!searchList.do";
	}
	
	//转入人才库
	public String toTwh(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		dataBaseControl.beginTransaction();
		dataBaseControl.executeSql("update hr_base_info t set t.hr_type=5 where t.id=?", new Object[]{id});
		dataBaseControl.executeSql("update hr_opinion t set t.istwh=1 where t.p_id=?", new Object[]{id});
		dataBaseControl.endTransaction();
		
		return "/hr/info/Hr_base_info!twhList.do";
	}
	
	//转回应聘流程
	public String toTest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		dataBaseControl.executeSql("update hr_base_info t set t.hr_type=0 where t.id=?", new Object[]{id});
		
		return "/hr/info/Hr_base_info!testList.do";
	}
	
	//上报人事经理
	public String toHrm(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String p_id = request.getParameter("p_id")==null?"":request.getParameter("p_id");
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String hr_state = request.getParameter("hr_state")==null?"":request.getParameter("hr_state");
		
		dataBaseControl.beginTransaction();
		dataBaseControl.executeSql("update Hr_opinion t set t.hr_state=? where t.p_id=? and t.hr_type=6", new Object[]{hr_state,p_id});
		dataBaseControl.executeSql("update Hr_apply t set t.appstate=? where t.id=?", new Object[]{hr_state,id});
		dataBaseControl.endTransaction();
		
		String num="";
		List plist = dataBaseControl.getOutResultSet("select * from message t where t.rolename='1' and t.dept='2'", null);
		if(plist != null && !plist.isEmpty())
		{
			num = ""+((Map)plist.toArray()[0]).get("num");
		}
		PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
		Mas m = (Mas) client.useService(Mas.class);
		
		String infor="您有一条转正审批请求，请您审批！";
		m.sendGFDZ(num,infor);
		
		return "/hr/process/Hr_apply!searchList.do";
	}
	
	//延长试用
	public String ycsy(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?request.getParameter("apply_id"):request.getParameter("id");
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map map = dataBaseControl.getOneResultSet("select t.text from hr_apply t where t.id=?", new Object[]{id});
			String text = map.get("text").toString();
			String ycsyq = request.getParameter("ycsyq")==null?"":request.getParameter("ycsyq");
			String ycopp = request.getParameter("ycopp")==null?"":request.getParameter("ycopp");
			
			dataBaseControl.beginTransaction();
			int i = dataBaseControl.executeSql("update hr_apply t set t.ycsyq=to_date(?,'yyyy-mm-dd'),t.ycopp=?,t.text=? where t.id=?", new Object[]{ycsyq,ycopp,text,id});
			dataBaseControl.executeSql("update hr_apply t set t.appstate='0' where t.id=?", new Object[]{id});
			dataBaseControl.endTransaction();
			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		else{
			Map apply = dataBaseControl.getOneResultSet("select * from hr_apply where id=?", new Object[]{id});
			request.setAttribute("record", apply);
		}
		request.setAttribute("apply_id", id);
		request.setAttribute("IsPostBack", "1");
		return "/hr/process/ycsyDtl.jsp";
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("hr_state",codeTableUtil.getCodeMap("hr_state")); 
		request.setAttribute("hr_type",codeTableUtil.getCodeMap("hr_type")); 
	}

}