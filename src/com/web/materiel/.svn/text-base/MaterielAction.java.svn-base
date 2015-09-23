package com.web.materiel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.map.Materiel;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class MaterielAction extends BaseAction {
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
		
		request.setAttribute("flag", "1");
		
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/materiel/materielSelect.jsp";
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("mulselect")) 
				return "/materiel/materielMulSelect.jsp";
		return "/materiel/materielList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/materiel/materielDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select rownum,t.*,m.name kindname,b.name brandname,decode(sign(length(t.parameter)-15),1,substr(t.parameter, 0, 15) || '...',t.parameter)content from Materiel t,ma_brand b,ma_kind m  where 1=1 and t.kind_id = m.id and t.brand_id = b.id " 
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.kind_id={kind_id} ~/ "
		+ "/~ and t.brand_id={brand_id} ~/ "
		+ "/~ and t.name like '%[name]%' ~/ "
		+ "/~ and t.shortcode  like '%[shortcode]%'  ~/ "
		+ "/~ and t.model  like '%[model]%' ~/ "
		+ "/~ and b.name  = {brandname} ~/ "
		+ "/~ and m.name  = {kindname} ~/ "
		+ " order by t.id ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Materiel materiel = new Materiel();
			BeanUtils.populate(materiel, getParameterMap(request));
			
			Map user = (Map)request.getSession().getAttribute("user");
			materiel.setLrr(new BigDecimal(user.get("base_info_id").toString()));
			int i =dataBaseControl.insertByBean(materiel);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/materiel/materielDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Materiel materiel = new Materiel();
			BeanUtils.populate(materiel, getParameterMap(request));
			
			Map user = (Map)request.getSession().getAttribute("user");
			materiel.setLrr(new BigDecimal(user.get("base_info_id").toString()));
			int i = dataBaseControl.updateByBean(materiel);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/materiel/materielDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Materiel WHERE ID in (?)", new Object[]{s_id});
		
		return "/materiel/Materiel!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}
	
	
	/*
	 * 
	 */
	public String getData(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		String name = request.getParameter("name");
		String shortcode = request.getParameter("shortcode");
		Page page = search(request, 1, 20);
		//request.setAttribute("record", page.getThisPageElements());
		//request.setAttribute("page", page);
		//String str = JSON.toJSONString(page.getThisPageElements());
		request.setAttribute("searchMap", getParameterMap(request));
		setAjaxInfo(response,JSON.toJSONString(page.getThisPageElements()));
		
		//String sql = "select * from fi_bill t where t.unit = ? ";
		//String compname = request.getParameter("compname");
		//String compname1 = new String(compname.getBytes("ISO-8859-1"),"UTF-8");
		
		
		return null;
	}
	
	
	public String choose(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		
		
		
		return "/materiel/materielChoose.jsp";
	}
	
	

}