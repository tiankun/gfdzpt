package com.web.sysAdmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.util.treeUtil;
import com.sysFrams.web.BaseAction;
import com.alibaba.fastjson.*;

public class sysfuncdicAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		/*Map searchMap=getParameterMap(request);
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
							? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("searchMap", searchMap);
		buildDDL(request);*/
		return "/sysAdmin/sysfuncdicList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
        return "/sysAdmin/sysfuncdicDtl.jsp";
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
		return "/sysAdmin/sysfuncdicDtl.jsp";
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
		return "/sysAdmin/sysfuncdicDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM sysfuncdic WHERE ID in (?)", new Object[]{s_id});
		
		return "/sysAdmin/sysfuncdic!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("delflag",codeTableUtil.getCodeMap("delflag")); 
		request.setAttribute("publics",codeTableUtil.getCodeMap("publics")); 
		request.setAttribute("treenodetype",codeTableUtil.getCodeMap("funlx")); 
		request.setAttribute("qylc",codeTableUtil.getCodeMap("qylc")); 
		request.setAttribute("isauthorize",codeTableUtil.getCodeMap("isauthorize")); 
	}
	
	public String gettree(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		if(searchMap ==null || searchMap.size()<1 ){
			searchMap.put("parentid", "0");
		}else if(searchMap.get("name")==null||searchMap.get("name").equals("")){
			searchMap.put("parentid", "0");
		}
		   String sql = "select t.id,t.parentid,t.funcname,nvl(t.url,'')url,t.publics,t.indexid,"
				   +"(select dmzmc from code where dmlb='funlx' and dmz=t.treenodetype)treenodetype,"
				   +"(select dmzmc from code where dmlb='delflag' and dmz=t.delflag)delflag "
				   +"from sysfuncdic t where 1=1 /~ and t.parentid={parentid} ~/ "
				   +"/~ and t.funcname like '%[name]%'  ~/";
		   //XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		   String treejson = treeUtil.buildMyTreeGrid(sql,searchMap);
		   //DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		   //List<Object> list = (List)dataBaseControl.getOutResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray());
		   //setAjaxInfo(response,JSON.toJSONString(list));
		   treejson="["+treejson+"]";
		   response.setCharacterEncoding("utf-8");
		   response.getWriter().print(treejson);
		   
		   return null;
		}

}
