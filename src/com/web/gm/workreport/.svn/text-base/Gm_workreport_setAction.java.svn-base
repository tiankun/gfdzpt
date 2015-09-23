package com.web.gm.workreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.map.Gm_workreport_set;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Gm_workreport_setAction extends BaseAction {
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
			return "/gm/workreport/gm_workreport_setSelect.jsp";
		return "/gm/workreport/hr_base_infoList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/gm/workreport/gm_workreport_setDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*,m.branchname from hr_base_info t,mrbranch m where t.dept_id = m.id and t.hr_type in('2','3') and t.dept_id != '-1' " 
				+ "/~ and t.id={id} ~/"
				+ "/~ and t.dept_id={dept_id} ~/"
				+ "/~ and t.name={name} ~/"
		        + " order by to_number(t.dept_id),t.id";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String set(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String id = request.getParameter("id");
		String IsPostBack = request.getParameter("IsPostBack");
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			dataBaseControl.executeSql("delete from gm_workreport_set t where t.report_person = ?", new Object[]{id});
			String pr_memberIds = request.getParameter("pr_memberIds");
			List<Map> memberIdList = JSON.parseArray(pr_memberIds, Map.class);
		    for(int i = 0 ; i < memberIdList.size();i++)
		    {
		    	Gm_workreport_set workreport_set = new Gm_workreport_set();
				workreport_set.setReport_person(new BigDecimal(id));
		    	Map tempmap = (Map)memberIdList.get(i);
		    	workreport_set.setAudit_person(new BigDecimal(tempmap.get("ID").toString()));
		    	dataBaseControl.insertByBean(workreport_set);
		    }
		    
		    request.setAttribute("operationSign", "closeDG_refreshW();");
		    
		}
		else
		{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
			
			List setlist = dataBaseControl.getOutResultSet("select r.* from gm_workreport_set t,hr_base_info r where t.report_person = ? and t.audit_person = r.id", new Object[]{id});
			String auditname = "";
			String pr_memberIds = "[";
			String str = "";
			
			
			if(setlist!=null && !setlist.isEmpty())
			{
				
				for(int i = 0 ; i < setlist.size();i++)
				{
					Map pmap = (Map)setlist.toArray()[i];
					auditname = auditname + ";" + pmap.get("name"); 	
					str = str +"," + "{\"ID\":\""+pmap.get("id")+"\"}";
				}
				pr_memberIds = pr_memberIds + str.substring(1)+"]";
				request.setAttribute("pr_member", auditname.equals("")?"":auditname.substring(1));
				request.setAttribute("pr_memberIds",pr_memberIds);
			}
			
		}
		
		request.setAttribute("editMod", "set");
		request.setAttribute("IsPostBack", "1");
		
		return "/gm/workreport/gm_workreport_setDtl.jsp";
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
		return "/gm/workreport/gm_workreport_setDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Gm_workreport_set WHERE ID in (?)", new Object[]{s_id});
		
		return "/gm/workreport/Gm_workreport_set!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}