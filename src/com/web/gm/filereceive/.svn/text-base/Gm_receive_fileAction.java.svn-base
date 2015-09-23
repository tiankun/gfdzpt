package com.web.gm.filereceive;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.map.Gm_receive_file;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Gm_receive_fileAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		request.setAttribute("searchMap", getParameterMap(request));
		
        Map user = (Map)request.getSession().getAttribute("user");
		
		String deptid = user.get("branchid").toString();
		request.setAttribute("deptid", deptid);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/gm/filereceive/gm_receive_fileSelect.jsp";
		return "/gm/filereceive/gm_receive_fileList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/gm/filereceive/gm_receive_fileDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		Map user = (Map)request.getSession().getAttribute("user");
		
		String deptid = user.get("branchid").toString();
		if(!deptid.equals("3"))
		{
			searchMap.put("p_id", user.get("base_info_id"));
		}
		
		
		String sql="select t.*,decode(t.state,'1','未接收','已接收')sstate,(select b.name from hr_base_info b where b.id = t.p_id)bname from Gm_receive_file t where 1=1 /~ and t.id={id} ~/"
		+ "/~ and t.filename={filename} ~/ "
		+ "/~ and t.filenum={filenum} ~/ "
		+ "/~ and t.p_id={p_id} ~/ "
		+ "/~ and t.rdate=to_date({rdate},'yyyy-MM-dd') ~/ ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map user =(Map)request.getSession().getAttribute("user");
			Gm_receive_file gm_receive_file = new Gm_receive_file();
			BeanUtils.populate(gm_receive_file, getParameterMap(request));
			gm_receive_file.setLp_id(new BigDecimal(user.get("base_info_id").toString()));
			gm_receive_file.setState("1");
			gm_receive_file.setLdate(new java.sql.Date((new java.util.Date()).getTime()));
			
			int i =dataBaseControl.insertByBean(gm_receive_file);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/gm/filereceive/gm_receive_fileDtl.jsp";
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
		return "/gm/filereceive/gm_receive_fileDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Gm_receive_file WHERE ID in (?)", new Object[]{s_id});
		
		return "/gm/filereceive/Gm_receive_file!searchList.do";
	}
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		dataBaseControl.executeSql("update gm_receive_file t set t.state = '2' where t.id = ?", new Object[]{request.getParameter("id")});
		
		
		return "/gm/filereceive/Gm_receive_file!searchList.do";
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}