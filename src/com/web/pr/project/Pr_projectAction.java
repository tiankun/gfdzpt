package com.web.pr.project;

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
import com.map.Pr_project;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;

public class Pr_projectAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map map = getParameterMap(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
				&& request.getParameter("pageType").equals("select"))
		{
			map.put("state", "1");
		}
		request.setAttribute("pageType",request.getParameter("pageType"));
		Page page=search(map,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("searchMap", getParameterMap(request));
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/pr/project/pr_projectSelect.jsp";
		return "/pr/project/pr_projectList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(getParameterMap(request),1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		request.setAttribute("pageType", request.getParameter("pageType"));
		buildDDL(request);
		return "/pr/project/pr_projectDtl.jsp";
	}
	private Page search(Map searchMap,int pageNo,int pageSize) throws Exception 
	{
		//Map searchMap=getParameterMap(request);
		String sql="select t.*,(select b.name from hr_base_info b where t.sale_manage = b.id(+))sname,(select r.name from hr_base_info r where t.pr_manage = r.id(+))ename from pr_project t  where  1=1 /~ and t.id={id} ~/"
		+ "/~ and t.name like '%[name]%'  ~/ "
		+ "/~ and t.pr_manage={p_id} ~/ "
		+ "/~ and t.pr_owner={pr_owner} ~/ "
		+ "/~ and t.pr_step={pr_step} ~/ "
		+ "/~ and t.state={state} ~/ "
		+ " order by t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Pr_project project = new Pr_project();
			BeanUtils.populate(project, getParameterMap(request));
			String pr_memberIds = request.getParameter("pr_memberIds");
			String spr_memberIds = request.getParameter("spr_memberIds");
			project.setSale_manage(new Long(request.getParameter("sale_manage")==null?"0":request.getParameter("sale_manage")));
			project.setPr_manage(new BigDecimal((request.getParameter("p_id")==null||request.getParameter("p_id").equals(""))?"0":request.getParameter("p_id")));
			project.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
			Map user = (Map)request.getSession().getAttribute("user");
			project.setInput_pid(new BigDecimal(user.get("base_info_id").toString()));
			String names = "";
			String snames = "";
			
			if(pr_memberIds!= null&&!pr_memberIds.equals(""))
			{
				List<Map> memberIdList = JSON.parseArray(pr_memberIds, Map.class);
			    for(int i = 0 ; i < memberIdList.size();i++)
			    {
			    	Map tempmap = (Map)memberIdList.get(i);
			    	Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{tempmap.get("ID")});
			    	names = names + ";" + baseinfo.get("name");
			    }
			    project.setPr_member(names.equals("")?"":names.substring(1));
			}
		    
			if(spr_memberIds!= null&&!spr_memberIds.equals(""))
			{
				List<Map> smemberIdList = JSON.parseArray(spr_memberIds, Map.class);
			    for(int i = 0 ; i < smemberIdList.size();i++)
			    {
			    	Map tempmap = (Map)smemberIdList.get(i);
			    	Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{tempmap.get("ID")});
			    	snames = snames + ";" + baseinfo.get("name");
			    }
			    project.setSale_members(snames.equals("")?"":snames.substring(1));
			    
			    
			}
		    
		    
		    
		    project.setState("0");
			dataBaseControl.insertByBean(project);			
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/pr/project/pr_projectDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			Pr_project project = new Pr_project();
			Map tmap = dataBaseControl.getOneResultSet("select * from pr_project t where t.id = ?", new Object[]{id});
			String state = tmap.get("state").toString();
			String snames = "";
			BeanUtils.populate(project, tmap);
			if(state.equals("0"))
			{
				
				String pr_memberIds = request.getParameter("pr_memberIds");
				
				if(!request.getParameter("p_id").equals(""))
				{
					project.setPr_manage(new BigDecimal(request.getParameter("p_id")));
				}
				if(!request.getParameter("sale_manage").equals(""))
				{
					project.setSale_manage(new Long(request.getParameter("sale_manage")));
				}
				
				project.setInput_date(new java.sql.Date((new java.util.Date()).getTime()));
				Map user = (Map)request.getSession().getAttribute("user");
				project.setInput_pid(new BigDecimal(user.get("base_info_id").toString()));
				String names = "";
				if(pr_memberIds!= null&&!pr_memberIds.equals(""))
				{
					List<Map> memberIdList = JSON.parseArray(pr_memberIds, Map.class);
				    for(int i = 0 ; i < memberIdList.size();i++)
				    {
				    	Map tempmap = (Map)memberIdList.get(i);
				    	Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{tempmap.get("ID")});
				    	names = names + ";" + baseinfo.get("name");
				    }
				    project.setPr_member(names.equals("")?"":names.substring(1));
				    project.setState("0");
				}
				
				String spr_memberIds = request.getParameter("spr_memberIds");
				
				if(spr_memberIds!= null&&!spr_memberIds.equals(""))
				{
					List<Map> smemberIdList = JSON.parseArray(spr_memberIds, Map.class);
				    for(int i = 0 ; i < smemberIdList.size();i++)
				    {
				    	Map tempmap = (Map)smemberIdList.get(i);
				    	Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{tempmap.get("ID")});
				    	snames = snames + ";" + baseinfo.get("name");
				    }
				    project.setSale_members(snames.equals("")?"":snames.substring(1));
				    
				    
				}
				project.setPr_note(request.getParameter("pr_note"));
			    
			}
			else
			{
				if(!request.getParameter("p_id").equals(""))
				{
					project.setPr_manage(new BigDecimal(request.getParameter("p_id")));
				}
				
				String pr_memberIds = request.getParameter("pr_memberIds");
				String names = "";
				if(pr_memberIds!= null&&!pr_memberIds.equals(""))
				{
					List<Map> memberIdList = JSON.parseArray(pr_memberIds, Map.class);
				    for(int i = 0 ; i < memberIdList.size();i++)
				    {
				    	Map tempmap = (Map)memberIdList.get(i);
				    	Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{tempmap.get("ID")});
				    	names = names + ";" + baseinfo.get("name");
				    }
				    project.setPr_member(names.equals("")?"":names.substring(1));
				    
				}
				project.setPr_note(request.getParameter("pr_note"));
				project.setPr_step(request.getParameter("pr_step"));
			}   
			int i = dataBaseControl.updateByBean(project);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(getParameterMap(request),1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/pr/project/pr_projectDtl.jsp";
	}
	
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		dataBaseControl.executeSql("update pr_project t set t.state = '1' where t.id = ?", new Object[]{id});
		
		return "/pr/project/Pr_project!searchList.do";
	}
	
	
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Pr_project WHERE ID in (?)", new Object[]{s_id});
		
		return "/pr/project/Pr_project!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("proj_phase",codeTableUtil.getCodeMap("proj_phase")); 
	}

}