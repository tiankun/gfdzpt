package com.web.supplier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.map.Supplier;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class SupplierAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		request.setAttribute("searchMap", getParameterMap(request));
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/supplier/supplierSelect.jsp";
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("mulselect")) 
				return "/supplier/supplierMulSelect.jsp";
		return "/supplier/supplierList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/supplier/supplierDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from supplier where 1=1 and yxbz='1'  /~ and id={id} ~/"
		+ "/~ and name like '%[name]%' ~/ "
		+ "/~ and lxr like '%[lxr]%' ~/ ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Supplier cus = new Supplier();
			BeanUtils.populate(cus, getParameterMap(request));
			cus.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			Map user = (Map)request.getSession().getAttribute("user");
			cus.setInput_pid(new BigDecimal(user.get("base_info_id").toString()));
			cus.setYxbz("1");
			int i =dataBaseControl.insertByBean(cus);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/supplier/supplierDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Supplier cus = new Supplier();
			BeanUtils.populate(cus, getParameterMap(request));
			cus.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			Map user = (Map)request.getSession().getAttribute("user");
			cus.setInput_pid(new BigDecimal(user.get("base_info_id").toString()));
			cus.setYxbz("1");
			int i = dataBaseControl.updateByBean(cus);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/supplier/supplierDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		dataBaseControl.executeSql("update supplier t set t.yxbz='0' WHERE t.ID in (?)", new Object[]{request.getParameter("id")});
		
		return "/supplier/Supplier!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("khdj",codeTableUtil.getCodeMap("khdj")); 
	}
	
	
	public String looksearchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		request.setAttribute("searchMap", getParameterMap(request));
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/supplier/supplierSelect.jsp";
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("mulselect")) 
				return "/supplier/supplierMulSelect.jsp";
		return "/supplier/looksupplierList.jsp";
	}
	
	public String looksearchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/supplier/supplierDtl.jsp";
	}

}