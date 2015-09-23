package com.web.sysAdmin;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.util.treeUtil;
import com.sysFrams.web.BaseAction;

public class sysrolesdicAction extends BaseAction {
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
				return "/sysAdmin/sysrolesdicSelect.jsp";
		return "/sysAdmin/sysrolesdicList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
        return "/sysAdmin/sysrolesdicDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Page page=dataBaseControl.getPageByBean(getMapObject(request),pageNo,pageSize);
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
		return "/sysAdmin/sysrolesdicDtl.jsp";
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
		return "/sysAdmin/sysrolesdicDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM sysrolesdic WHERE ID in (?)", new Object[]{s_id});
		
		return "/sysAdmin/sysrolesdic!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("roletype",codeTableUtil.getCodeMap("roletype")); 
		request.setAttribute("delflag",codeTableUtil.getCodeMap("delflag")); 
	}
	//进入权限分配功能
	public String allotFunRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		request.setAttribute("roleid", id);
		return "/sysAdmin/allotFunRole.jsp";
	}
	public String getTree1(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String roleid = request.getParameter("rid");
		String sql="select ID,FuncName || (select '-'||dmzmc from code where dmlb='funlx' and dmz=treenodetype) FuncName, parentid, url, target, treenodetype from sysFuncDic "
				+" where publics ='0' and parentId=? and delflag='0' "
				+" and (ID not in (SELECT FuncID FROM sysFuncrole  where roleid ="+roleid
				+")or (treenodetype='Catalog')  or (treenodetype='Leaf')) order by indexid";
		String funcDicList = treeUtil.buildTreeJson(sql, "0","id","funcname");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(funcDicList);
        return null;
	}
	public String getTree2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String roleid = request.getParameter("rid");
		String sql="select ID,FuncName || (select '-'||dmzmc from code where dmlb='funlx' and dmz=treenodetype) FuncName, parentid, url, target, treenodetype from sysFuncDic "
				+" where publics ='0' and parentId=? and delflag='0' "
				+"and ID in (SELECT FuncID FROM sysFuncrole where roleid ="+roleid+")  order by indexid";
		String rightFuncDicList = treeUtil.buildTreeJson(sql, "0","id","funcname");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(rightFuncDicList);
        return null;
	}
	
	public String addfun(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String funid = request.getParameter("fid");
		String roleid = request.getParameter("rid");
		String sql_select = "select id from sysfuncrole where funcid = ? and roleid = ?";
		if(funid!=null){
			String[] fid = funid.split(",");
			for (String _fid : fid) {
				int i = dataBaseControl.getResultSetSize(sql_select, new Object[]{_fid,roleid});
				if(i>0) continue;
				String sql = "insert into sysfuncrole values(seq_sysfuncrole.nextval,?,?)";
				dataBaseControl.executeSql(sql, new Object[]{_fid,roleid});
			}			
		}
		request.setAttribute("roleid", roleid);
		return "/sysAdmin/allotFunRole.jsp";
	}
	
	public String delfun(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String funid = request.getParameter("fid");
		String roleid = request.getParameter("rid");
		if(funid!=null){
			String[] fid = funid.split(",");
			for (String _fid : fid) {
				String sql = "delete from sysfuncrole where funcid=? and roleid=? ";
				dataBaseControl.executeSql(sql, new Object[]{_fid,roleid});
			}			
		}
		request.setAttribute("roleid", roleid);
		return "/sysAdmin/allotFunRole.jsp";
	}
}
