package com.web.sysAdmin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.map.sysuser;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.Encrypt;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;

public class sysuserAction extends BaseAction {
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
				return "/sysAdmin/sysuserSelect.jsp";
		return "/sysAdmin/sysuserList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
        return "/sysAdmin/sysuserDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		if((searchMap.get("delflag")==null || searchMap.get("delflag").equals("")) && searchMap.get("id")==null)
			searchMap.put("delflag", "0");
		String sql="select id,log_name,user_name,log_pass,cdate,exp_date,branchid,BASE_INFO_id,usertype,flag,delflag," +
				"(select branchname from mrbranch where id = sysuser.branchid) branchname from sysuser where 1=1 "
				+ "/~ and log_name like '%[log_name]%' ~/"
				+ "/~ and id={id} ~/"
				+ "/~ and user_name like '%[user_name]%' ~/"
				+ "/~ and branchid={branchid} ~/"
				+ "/~ and delflag={delflag} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			sysuser user = (sysuser) getMapObject(request);
			user.setLog_pass(Encrypt.crypt(user.getLog_pass()));
			int i =dataBaseControl.insertByBean(user);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/sysAdmin/sysuserDtl.jsp";
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
		return "/sysAdmin/sysuserDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM sysuser WHERE ID in (?)", new Object[]{s_id});
		
		return "/sysAdmin/sysuser!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("usertype",codeTableUtil.getCodeMap("usertype")); 
		request.setAttribute("flag",codeTableUtil.getCodeMap("flag")); 
		request.setAttribute("delflag",codeTableUtil.getCodeMap("delflag")); 
	}
	//进入角色分配功能
		public String allotUserRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String id = request.getParameter("id");
			request.setAttribute("userid", id);
			return "/sysAdmin/allotUserRole.jsp";
		}
		public String getDg1(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String userid = request.getParameter("uid");
			String sql="select id,rolename,roletype from sysrolesdic where delflag=0 order by id";					
			Collection col = dataBaseControl.getOutResultSet(sql, new Object[]{});
			
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(JSON.toJSONString(col));
	        return null;
		}
		public String getDg2(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String userid = request.getParameter("uid");
			String sql="select id,rolename from sysrolesdic where id in (select rolesid from sysuserroles where userid="+userid+") order by id";
			Collection col = dataBaseControl.getOutResultSet(sql, new Object[]{});
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(JSON.toJSONString(col));
	        return null;
		}
		
		public String addrole(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String roleid = request.getParameter("rid");
			String userid = request.getParameter("uid");
			String sql_select = "select * from sysuserroles where userid=? and rolesid=?";
			if(roleid!=null){
				String[] rid = roleid.split(",");
				for (String _rid : rid) {
					int i = dataBaseControl.getResultSetSize(sql_select, new Object[]{userid,_rid});
					if(i>0) continue;
					String sql = "insert into sysuserroles values(seq_sysuserroles.nextval,?,?)";
					dataBaseControl.executeSql(sql, new Object[]{userid,_rid});
				}			
			}
			request.setAttribute("userid", userid);
			return "/sysAdmin/allotUserRole.jsp";
		}
		
		public String delrole(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String roleid = request.getParameter("rid");
			String userid = request.getParameter("uid");
			if(roleid!=null){
				String[] rid = roleid.split(",");
				for (String _rid : rid) {
					String sql = "delete from sysuserroles where userid=? and rolesid =? ";
					dataBaseControl.executeSql(sql, new Object[]{userid,_rid});
				}			
			}
			request.setAttribute("userid", userid);
			return "/sysAdmin/allotUserRole.jsp";
		}

}
