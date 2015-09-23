package com.web.archives;

import java.util.ArrayList;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Hr_base_infoAction extends BaseAction {
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
			return "/archives/hr_base_infoSelect.jsp";
		return "/archives/hr_base_infoList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/archives/hr_base_infoDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Hr_base_info where 1=1 /~ and id={id} ~/"
		+ "/~ and name={name} ~/ "
		+ "/~ and work_year={work_year} ~/ "
		+ "/~ and sheng_ri={sheng_ri} ~/ "
		+ "/~ and zhuang_tai={zhuang_tai} ~/ "
		+ "/~ and xue_li={xue_li} ~/ ";
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
		return "/archives/hr_base_infoDtl.jsp";
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
		return "/archives/hr_base_infoDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Hr_base_info WHERE ID in (?)", new Object[]{s_id});
		
		return "/archives/hr_base_info!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("sex",codeTableUtil.getCodeMap("sex")); 
		request.setAttribute("duty_id",codeTableUtil.getCodeMap("duty_id")); 
		request.setAttribute("min_zu",codeTableUtil.getCodeMap("min_zu")); 
		request.setAttribute("zheng_jian",codeTableUtil.getCodeMap("zheng_jian")); 
		request.setAttribute("zheng_zhi",codeTableUtil.getCodeMap("zheng_zhi")); 
		request.setAttribute("hun_yin",codeTableUtil.getCodeMap("hun_yin")); 
		request.setAttribute("zhuang_tai",codeTableUtil.getCodeMap("zhuang_tai")); 
		request.setAttribute("hu_kou",codeTableUtil.getCodeMap("hu_kou")); 
		request.setAttribute("xue_li",codeTableUtil.getCodeMap("xue_li")); 
	}

}