package com.web.gm.sealborrow;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.map.Gm_seal_borrow;
import com.map.Gm_sealborrow_audit;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Gm_seal_borrowAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map map = getParameterMap(request);
		
		Map user = (Map)request.getSession().getAttribute("user");
		map.put("dept_id", user.get("branchid"));
		
		Page page=search(map,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/sealborrow/gm_seal_borrowSelect.jsp";
		return "/gm/sealborrow/gm_seal_borrowList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(getParameterMap(request),1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select r.*,(select b.name from hr_base_info b where b.id = r.audit_id) person from gm_seal_borrow t, gm_sealborrow_audit r where t.id = r.apply_id and t.id = ? order by r.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		return "/gm/sealborrow/gm_seal_borrowDtl.jsp";
	}
	private Page search(Map map,int pageNo,int pageSize) throws Exception 
	{
		//Map searchMap=getParameterMap(request);
		String sql="select t.*,(select b.name from hr_base_info b where b.id = t.p_id)pname from Gm_seal_borrow t where 1=1 /~ and t.id={id} ~/"
		+ "/~ and t.bdate >= to_date({bdate1},'yyyy-MM-dd') ~/"
		+ "/~ and t.bdate <= to_date({bdate2},'yyyy-MM-dd') ~/"
		+ "/~ and t.bitem={bitem} ~/ "
		+ "/~ and t.dept_id={dept_id} ~/ "
		+ " order by t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,map); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map user = (Map)request.getSession().getAttribute("user");
			Gm_seal_borrow seal_borrow = new Gm_seal_borrow();
			Map map = getParameterMap(request);
			BeanUtils.populate(seal_borrow, map);
			
			String isoriginal = request.getParameter("isoriginal")==null?"0":request.getParameter("isoriginal");
			String isprint = request.getParameter("isprint")==null?"0":request.getParameter("isprint");
			String stamp = request.getParameter("stamp")==null?"0":request.getParameter("stamp");
			
			seal_borrow.setBdate(new java.sql.Date((new java.util.Date()).getTime()));
			seal_borrow.setP_id(new BigDecimal(user.get("base_info_id").toString()));
			seal_borrow.setIsoriginal(isoriginal);
			seal_borrow.setIsprint(isprint);
			seal_borrow.setStamp(stamp);
			seal_borrow.setDept_id(new Integer(user.get("branchid").toString()));
			
			Map baseinfoMap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
			String duty_id = baseinfoMap.get("duty_id").toString();
			
			String state = request.getParameter("state");
			
			
			if(duty_id.equals("2")||duty_id.equals("5")||duty_id.equals("4"))
			{
				state = "2";
			}
			
			if(user.get("branchid").toString().equals("10")){	//客户部直接由崔总审批
				state = "2";
			}
			
			seal_borrow.setState(state);
			dataBaseControl.insertByBean(seal_borrow);			
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/gm/sealborrow/gm_seal_borrowDtl.jsp";
	}
	
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map map = dataBaseControl.getOneResultSet("select * from gm_seal_borrow t where t.id = ?", new Object[]{request.getParameter("id")});
			Gm_seal_borrow gm_seal_borrow = new Gm_seal_borrow();
			BeanUtils.populate(gm_seal_borrow, map);
			
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			
			gm_seal_borrow.setPdate(new java.sql.Date((fmt.parse(request.getParameter("pdate")).getTime())));
			gm_seal_borrow.setBitem(request.getParameter("bitem"));
			gm_seal_borrow.setUsereason(request.getParameter("usereason"));
			
			String isoriginal = request.getParameter("isoriginal")==null?"0":request.getParameter("isoriginal");
			String isprint = request.getParameter("isprint")==null?"0":request.getParameter("isprint");
			String stamp = request.getParameter("stamp")==null?"0":request.getParameter("stamp");
			
			gm_seal_borrow.setIsoriginal(isoriginal);
			gm_seal_borrow.setIsprint(isprint);
			gm_seal_borrow.setStamp(stamp);
			Map user = (Map)request.getSession().getAttribute("user");
			Map baseinfoMap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
			String duty_id = baseinfoMap.get("duty_id").toString();
			
			String state = request.getParameter("state");
			
			
			if(duty_id.equals("2")||duty_id.equals("5")||duty_id.equals("4"))
			{
				state = "2";
			}
			
			gm_seal_borrow.setState(state);
			gm_seal_borrow.setNum(new BigDecimal(request.getParameter("num")));
			gm_seal_borrow.setNote(request.getParameter("note"));
			
			dataBaseControl.updateByBean(gm_seal_borrow);
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(getParameterMap(request),1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/gm/sealborrow/gm_seal_borrowDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Gm_seal_borrow WHERE ID in (?)", new Object[]{s_id});
		
		return "/gm/sealborrow/Gm_seal_borrow!searchList.do";
	}
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		dataBaseControl.executeSql("update gm_seal_borrow t set t.state = '1' where t.id = ?", new Object[]{id});
		
		buildDDL(request);
		return "/gm/sealborrow/Gm_seal_borrow!searchList.do";
	}
	
	
	public String auditsearchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map map = getParameterMap(request);
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfoMap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfoMap.get("duty_id").toString();
		String dept_id = baseinfoMap.get("dept_id").toString();
		
		if(duty_id.equals("2")&&!dept_id.equals("3"))
		{
			map.put("dept_id", baseinfoMap.get("dept_id"));
		}
		if(duty_id.equals("4"))//崔总
		{
			request.setAttribute("compmanage", "1");
		}
		if(duty_id.equals("5"))//向总
		{
			map.put("dept_id", "9");
		}
		if(duty_id.equals("3"))//赵总
		{
			request.setAttribute("compmanage", "2");
		}
		
		if(dept_id.equals("3"))
		{
			request.setAttribute("retu", "1");
		}
		
		
		Page page=search(map,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/sealborrow/gm_seal_borrowSelect.jsp";
		return "/gm/sealborrow/gm_seal_borrowAuditList.jsp";
	}
	
	public String audit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map map = dataBaseControl.getOneResultSet("select * from gm_seal_borrow t where t.id = ?", new Object[]{request.getParameter("id")});
			Gm_seal_borrow gm_seal_borrow = new Gm_seal_borrow();
			BeanUtils.populate(gm_seal_borrow, map);
			
			String type = request.getParameter("type");
			String state = request.getParameter("state");
			String sstate = "";
			
			if(type.equals("deptaudit"))
			{
					
				String isoriginal = gm_seal_borrow.getIsoriginal();
				
				if(state.equals("1"))
				{
					if(isoriginal.equals("1"))
					{
						sstate = "2";
						gm_seal_borrow.setState("2");
					}
					else
					{
						sstate = "4";
						gm_seal_borrow.setState("4");
					}
					
				}
				else
				{
					sstate = "3";
					gm_seal_borrow.setState("3");
				}
			}
			else if(type.equals("finalaudit"))
			{
				if(state.equals("1"))
				{
					sstate = "4";
					gm_seal_borrow.setState("4");
				}
				else
				{
					sstate = "5";
					gm_seal_borrow.setState("5");
				}
			}
			
			else
			{
				sstate = "6";
				gm_seal_borrow.setState("6");
				
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				gm_seal_borrow.setRdate(new java.sql.Date((fmt.parse(request.getParameter("rdate")).getTime())));
			}
			
			
			dataBaseControl.updateByBean(gm_seal_borrow);
			
			if(type.equals("deptaudit")||type.equals("finalaudit"))
			{
				Map user = (Map)request.getSession().getAttribute("user");
				
				Gm_sealborrow_audit sealborrow_audit = new Gm_sealborrow_audit();
				sealborrow_audit.setApply_id(gm_seal_borrow.getId());
				sealborrow_audit.setAudit_date(new java.sql.Date((new java.util.Date()).getTime()));
				sealborrow_audit.setOpinion(request.getParameter("opinion"));
				sealborrow_audit.setState(sstate);
				sealborrow_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
				dataBaseControl.insertByBean(sealborrow_audit);
			}
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(getParameterMap(request),1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "audit");
		request.setAttribute("IsPostBack", "1");
		
		request.setAttribute("type", request.getParameter("type"));
		
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select r.*,(select b.name from hr_base_info b where b.id = r.audit_id) person from gm_seal_borrow t, gm_sealborrow_audit r where t.id = r.apply_id and t.id = ? order by r.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		buildDDL(request);
		return "/gm/sealborrow/gm_seal_borrowauditDtl.jsp";
	}
	
	
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("bitem",codeTableUtil.getCodeMap("bitem")); 
		request.setAttribute("yzstate",codeTableUtil.getCodeMap("yzstate")); 
	}

}