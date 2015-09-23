package com.web.sysAdmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class MessageAction extends BaseAction {
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
		
		//获得部门
		List deptlist = dataBaseControl.getOutResultSet("select * from mrbranch m  order by m.id ", null);
		request.setAttribute("dept", deptlist);
		
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/sysAdmin/message/messageSelect.jsp";
		return "/sysAdmin/message/messageList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		//获得部门
		List deptlist = dataBaseControl.getOutResultSet("select * from mrbranch m  order by m.id ", null);
		request.setAttribute("dept", deptlist);
		
		return "/sysAdmin/message/messageDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*,m.branchname from Message t,mrbranch m where 1=1 and t.dept=m.id /~ and t.id={id} ~/"
		+ "/~ and name={name} ~/ "
		+ "/~ and dept={dept} ~/ ";
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
		
		List deptlist = dataBaseControl.getOutResultSet("select * from mrbranch m  order by m.id ", null);
		request.setAttribute("dept", deptlist);
		
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/sysAdmin/message/messageDtl.jsp";
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
		//获得部门
		List deptlist = dataBaseControl.getOutResultSet("select * from mrbranch m  order by m.id ", null);
		request.setAttribute("dept", deptlist);
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/sysAdmin/message/messageDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Message WHERE ID in (?)", new Object[]{s_id});
		
		return "/sysAdmin/Message!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("messagerole",codeTableUtil.getCodeMap("messagerole")); 
	}

}