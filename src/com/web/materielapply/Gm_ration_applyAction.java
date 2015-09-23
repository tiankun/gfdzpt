package com.web.materielapply;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.phprpc.PHPRPC_Client;

import com.alibaba.fastjson.JSON;
import com.map.Bgyp;
import com.map.Gm_ration_apply;
import com.map.Gm_ration_apply_audit;
import com.map.Gm_ration_item;
import com.map.Pr_ma_plan;
import com.map.Xmcl;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;
import com.zsc.Mas;

public class Gm_ration_applyAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		request.setAttribute("dept_id", user.get("branchid"));
		
		Page page=search(request,pageNo,pageSize);
		request.setAttribute("editMod", "searchList");
		
		request.setAttribute("type", "selflook");
		
		//request.setAttribute("searchMap", getParameterMap(request));
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/materielapply/gm_ration_applySelect.jsp";
		return "/materielapply/gm_ration_applyList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=looksearch(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		List list = dataBaseControl.getOutResultSet("select t.*,m.name mnate,b.name bname,m.model,m.parameter,(select nvl(sum(r.sqsl),0) from gm_ration_apply gr, gm_ration_item r where gr.id = r.apply_id and r.pr_ma_plan_id = t.pr_ma_plan_id and gr.spzt='2'  )ysq,(select q.sqsl from pr_ma_plan q where q.id(+) = t.pr_ma_plan_id)psqsl from gm_ration_item t, materiel m, ma_brand b where t.materiel_id = m.id and t.brand_id = b.id and t.apply_id = ?", new Object[]{request.getParameter("id")});
		request.setAttribute("mlist", list);
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select pa.*,(select b.name from hr_base_info b where b.id = pa.audit_id) person from gm_ration_apply p, gm_ration_apply_audit pa  where p.id = pa.purchaseapply_id  and p.id = ? order by pa.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		return "/materielapply/gm_ration_applyDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		searchMap.put("apply_dept", user.get("branchid"));
		
		String duty_id = baseinfo.get("duty_id").toString();
		String dept_id = baseinfo.get("dept_id").toString();
		
		searchMap.put("apply_person", user.get("base_info_id"));
		
		
		request.setAttribute("searchMap", searchMap);
		
		String sql="select p.*, l.name planname, r.name proname, r.pr_manage,b.name revename,(select hb.name  from  hr_base_info hb where hb.id = r.pr_manage)item_manage1,(select gb.name from hr_base_info gb where gb.id = p.apply_person)apply_name from gm_ration_apply p, pr_plan l, pr_project r,hr_base_info b  where 1 = 1   and p.prj_id = r.id(+) and p.item_id = l.id(+) and p.receive_person = b.id  /~ and p.id={id} ~/"
		+ "/~ and r.name like '%[proname]%' ~/ "
		+ "/~ and p.spzt={spzt} ~/ "
		+ "/~ and p.bh like '%[bh]%'  ~/ "
		+ "/~ and p.apply_dept={apply_dept} ~/ "
		+ "/~ and p.apply_person={apply_person} ~/"
		+ "/~ and p.item_id={item_id} ~/ "
		+ " order by p.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	private Page looksearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		request.setAttribute("searchMap", searchMap);
		
		String sql="select p.*,(select c.dmzmc from code c where c.dmlb = 'purchasetype' and c.dmz = p.cgtype) cgsqtype, l.name planname, r.name proname, r.pr_manage,b.name revename,(select hb.name  from  hr_base_info hb where hb.id = r.pr_manage)item_manage1,(select gb.name from hr_base_info gb where gb.id = p.apply_person)apply_name from gm_ration_apply p, pr_plan l, pr_project r,hr_base_info b  where 1 = 1   and p.prj_id = r.id(+) and p.item_id = l.id(+) and p.receive_person = b.id(+)  /~ and p.id={id} ~/"
		+ "/~ and r.name like '%[proname]%' ~/ "
		+ "/~ and p.spzt={spzt} ~/ "
		+ "/~ and p.bh like '%[bh]%'  ~/ "
		+ "/~ and p.apply_dept={apply_dept} ~/ "
		+ "/~ and p.apply_person={apply_person} ~/"
		+ "/~ and p.item_id={item_id} ~/ "
		+ " order by p.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	
	
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.id,max(p.name)proname,max(t.purchase)purchase,max(t.bh)bh,max(t.name)name,max((select b.name from hr_base_info b where b.id = t.made_person))madeperson,max(t.made_date)made_date from pr_plan t,pr_ma_plan r,pr_project p where t.id = r.plan_id and t.prj_id = p.id and t.pr_plan_state = '2' and r.sqsl > r.hasnum "
		+ "/~ and t.id={id} ~/ "
		+ "/~ and t.bh like '%[bh]%' ~/ "
		+ "/~ and t.name like '%[item_name]%' ~/ "
		+ "/~ and p.name like '%[name]%' ~/ "
		+ "/~ and t.pr_plan_state = {pr_plan_state} ~/"
		+ " group by t.id order by t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	private List search2(HttpServletRequest request) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select (p.sqsl-p.hasnum)kcg,p.*,l.name planname,m.name maname,r.name proname,b.name brname,r.pr_manage from pr_ma_plan  p ,pr_plan l,materiel m,pr_project r,ma_brand b  where 1=1 and p.prj_id = r.id(+) and p.plan_id = l.id and p.materiel_id = m.id and   p.brand_id = b.id and p.sqsl > p.hasnum  /~ and p.plan_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		List list=dataBaseControl.getOutResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray());
		return list;
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
		
		//获得采购类型
		List list = dataBaseControl.getOutResultSet("select * from code t where t.dmlb = 'purchasetype' order by t.id", null);
		request.setAttribute("purchasetype", list);
		
		return "/materielapply/gm_ration_applyDtl1.jsp";
	}
	
	
	
	public String addfromitem(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
			? "1" : request.getParameter("pageno");
          int pageNo=(new Integer(page_no)).intValue();
          int pageSize=20;

          Map user = (Map)request.getSession().getAttribute("user");
          request.setAttribute("dept_id", user.get("branchid"));
          
          request.setAttribute("searchMap", getParameterMap(request));

          Page page=search1(request,pageNo,pageSize);

          request.setAttribute("record", page.getThisPageElements());
          request.setAttribute("page", page);
          buildDDL(request);
          if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
        		  && request.getParameter("pageType").equals("select")) 
        	  return "/materielapply/gm_ration_applySelect.jsp";
          
		return "/materielapply/gm_item_ration_applyList.jsp";
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
		return "/materielapply/gm_ration_applyDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from gm_ration_item t where t.id = ? ", new Object[]{request.getParameter("id")});
		
		String pr_ma_plan_id = map.get("pr_ma_plan_id")==null?"":map.get("pr_ma_plan_id").toString();
		
		
		if(!pr_ma_plan_id.equals(""))
		{
			Map map1 = dataBaseControl.getOneResultSet("select * from pr_ma_plan t where t.id = ?", new Object[]{map.get("pr_ma_plan_id")});
			
			Pr_ma_plan maplan = new Pr_ma_plan();
			BeanUtils.populate(maplan, map1);
			
			int hasnum = maplan.getHasnum() - Integer.parseInt(map.get("sqsl").toString());
			maplan.setHasnum(hasnum);
	        dataBaseControl.updateByBean(maplan);
			
		}
		
		dataBaseControl.executeSql("DELETE FROM gm_ration_item WHERE ID in (?)", new Object[]{request.getParameter("id")});
		
		String forward = "";
		
		List malist = dataBaseControl.getOutResultSet("select * from gm_ration_item t where t.apply_id = ?", new Object[]{map.get("apply_id")});
		if(malist ==null ||malist.isEmpty())
		{
			dataBaseControl.executeSql("DELETE FROM gm_ration_apply WHERE ID in (?)", new Object[]{map.get("apply_id")});
			
			request.setAttribute("operationSign", "closeDG_refreshW();");
			forward = "/materielapply/gm_ration_applyModifyDtl.jsp";
		}
		else
		{
			forward = "/materielapply/Gm_ration_apply!modify.do?id="+map.get("apply_id");
		}
		
		return forward;
	}
	
	
	public String getmateriel(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		List reslist = search2(request);
		
	
		request.setAttribute("searchMap", getParameterMap(request));
        request.setAttribute("record", reslist);
		
		if(reslist!=null && !reslist.isEmpty())
		{
			request.setAttribute("info", reslist.get(0));
		}
		
		buildDDL(request);
		return "/materielapply/pr_ma_planList.jsp";
	}
	
	/**
	 * 保存从计划添加的材料
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String sub(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
		
		Gm_ration_apply ration_apply = new Gm_ration_apply();
		BeanUtils.populate(ration_apply, getParameterMap(request));
		
        String sql = "select SEQ_XMCL.NEXTVAL sql from dual";
	    
	    List seqlist = dataBaseControl.getOutResultSet(sql, null);
	    
	    Date date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String bh = "";
	    
	    if(seqlist!=null&&!seqlist.isEmpty())
	    {
	    	Map seqmap = (Map)seqlist.get(0);
	    	Long id = new Long(seqmap.get("sql").toString());
	    	bh = "XMCL-"+fmt.format(date)+ "-" + id;
	    }
		
	    ration_apply.setIsplan("1");
	    ration_apply.setCgtype("2");
	    ration_apply.setBh(bh);
		ration_apply.setApply_date(new java.sql.Date((new java.util.Date()).getTime()));
		ration_apply.setApply_dept(new Integer(Integer.valueOf(user.get("branchid").toString())));
		ration_apply.setApply_person(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
		ration_apply.setSpzt(request.getParameter("spzt"));
		ration_apply.setIs_finish("0");
		
		dataBaseControl.beginTransaction();
		
		Long id = dataBaseControl.getSeq(Gm_ration_apply.class);
		
		dataBaseControl.insertByBean(ration_apply,id);
		
	
		Object[] checkbox = request.getParameterValues("checkbox");
		String order_date1 = request.getParameter("order_date1")==null?"":request.getParameter("order_date1");
		
		
		if(checkbox.length!=0)
		{
			
			for(int i = 0 ; i < checkbox.length;i++)
			{
				Map map1 = dataBaseControl.getOneResultSet("select * from pr_ma_plan t where t.id = ? ", new Object[]{request.getParameter("prmaplan"+checkbox[i].toString())});
				Pr_ma_plan maplan = new Pr_ma_plan();
				BeanUtils.populate(maplan, map1);
				
				Gm_ration_item ration_item = new Gm_ration_item();
				BeanUtils.populate(ration_item, map1);
				ration_item.setApply_id(id);
				ration_item.setSqsl(new Integer(Integer.valueOf(request.getParameter("plan"+checkbox[i].toString())==null?"0":request.getParameter("plan"+checkbox[i].toString()))));
				ration_item.setHasnum(0);
				ration_item.setPurchase_num(new Integer(0));
				ration_item.setCzy(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
				ration_item.setCzrq(new java.sql.Date((new java.util.Date()).getTime()));
				String orderdate = request.getParameter("order_date"+checkbox[i].toString());
				if(orderdate.equals("")&&!order_date1.equals(""))
	        	{
	        		ration_item.setOrder_date(new java.sql.Date((format.parse(order_date1)).getTime()));
	        	}
				ration_item.setPr_ma_plan_id(new Long(request.getParameter("prmaplan"+checkbox[i].toString())));
				dataBaseControl.insertByBean(ration_item);
				
				int hasnum = maplan.getHasnum().intValue();
				hasnum = hasnum + Integer.valueOf(request.getParameter("plan"+checkbox[i].toString()));
				maplan.setHasnum(hasnum);
				dataBaseControl.updateByBean(maplan);	
				
			}
			
		}
		dataBaseControl.endTransaction();
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/materielapply/pr_ma_planList.jsp";
	}
	
	public String auditsearchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		request.setAttribute("dept_id", user.get("branchid"));
		
		Map baseinfomap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?	", new Object[]{user.get("base_info_id")});
		
		request.setAttribute("type", "audit");
		request.setAttribute("info", baseinfomap);
		
		Page page=auditsearch(request,pageNo,pageSize);
		
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("editMod", "auditsearchList");
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/materielapply/gm_ration_applySelect.jsp";
		return "/materielapply/gm_ration_applyList.jsp";
	}
	
	
	private Page auditsearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		
		String duty_id = baseinfo.get("duty_id").toString();
		String dept_id = baseinfo.get("dept_id").toString();
		
		
		if(duty_id.equals("2"))
		{
			searchMap.put("spzt", request.getParameter("spzt")==null?"1":request.getParameter("spzt"));
		}
		
		if(duty_id.equals("3"))
		{
			searchMap.put("spzt", request.getParameter("spzt")==null?"5":request.getParameter("spzt"));
		}
		
		request.setAttribute("searchMap", searchMap);
		
		String sql="select p.*, l.name planname, r.name proname, r.pr_manage,b.name revename," +
				"(select hb.name  from  hr_base_info hb where hb.id = r.pr_manage)item_manage1," +
				"(select gb.name from hr_base_info gb where gb.id = p.apply_person)apply_name" +
				" from gm_ration_apply p, pr_plan l, pr_project r,hr_base_info b  where 1 = 1   " +
				"and p.prj_id = r.id(+)" +
				" and p.item_id = l.id(+)" +
				" and p.receive_person = b.id(+)  /~ and p.id={id} ~/"
		+ "/~ and p.prj_id={prj_id} ~/ "
		+ "/~ and p.spzt={spzt} ~/ "
		+ "/~ and p.bh like '%[bh]%'  ~/ "
		+ "/~ and p.apply_dept={apply_dept} ~/ "
		+ "/~ and p.apply_person={apply_person} ~/"
		+ "/~ and p.item_id={item_id} ~/ "
		+ " order by p.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String audit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=looksearch(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		request.setAttribute("type", "aduit");
		
		List list = dataBaseControl.getOutResultSet("select t.*,m.name mnate,b.name bname,m.model,m.parameter,(select nvl(sum(r.sqsl),0) from gm_ration_apply gr, gm_ration_item r where gr.id = r.apply_id and r.pr_ma_plan_id = t.pr_ma_plan_id and gr.spzt='2'  )ysq,(select q.sqsl from pr_ma_plan q where q.id(+) = t.pr_ma_plan_id)psqsl from gm_ration_item t, materiel m, ma_brand b where t.materiel_id = m.id and t.brand_id = b.id and t.apply_id = ?", new Object[]{request.getParameter("id")});
		request.setAttribute("mlist", list);
		
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select pa.*,(select b.name from hr_base_info b where b.id = pa.audit_id) person from gm_ration_apply p, gm_ration_apply_audit pa  where p.id = pa.purchaseapply_id  and p.id = ? order by pa.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		return "/materielapply/gm_ration_applyDtl.jsp";
	}
	
	/*
	 * 保存审批意见
	 */
	public String save(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from gm_ration_apply t where t.id = ?", new Object[]{request.getParameter("id")});
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfomap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?	", new Object[]{user.get("base_info_id")});
		
		dataBaseControl.beginTransaction();

		String isplan = map.get("isplan")==null?"":map.get("isplan").toString();
		
		Gm_ration_apply ration_apply = new Gm_ration_apply();
		BeanUtils.populate(ration_apply, map);
		String state = request.getParameter("opinionid");
		
		
		String dept = ration_apply.getApply_dept().toString();
		String cgtype = ration_apply.getCgtype();
		String duty_id = baseinfomap.get("duty_id").toString();
		
		if(dept.equals("7")&&cgtype.equals("2")&&!duty_id.equals("1"))
		{
			if(state.equals("2"))
			{
				state = "6";
			}
			
		}
		
		if(state.equals("4")&&!isplan.equals("0"))//来自于计划
		{
			List malist =dataBaseControl.getOutResultSet("select * from gm_ration_item t where t.apply_id = ?", new Object[]{request.getParameter("id")});
			
			for(int i = 0 ; i < malist.size();i++)
			{
				Map tmap = (Map)malist.get(i);

				Map map1 = dataBaseControl.getOneResultSet("select * from pr_ma_plan t where t.id = ?", new Object[]{tmap.get("pr_ma_plan_id")});
				
				Pr_ma_plan maplan = new Pr_ma_plan();
				BeanUtils.populate(maplan, map1);
				
				int hasnum = maplan.getHasnum() - Integer.parseInt(tmap.get("sqsl").toString());
				maplan.setHasnum(hasnum);
		        dataBaseControl.updateByBean(maplan);
			}
		}
		
		
		
		ration_apply.setSpzt(state);
		ration_apply.setSpyj(request.getParameter("opinion"));
		ration_apply.setSpr(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
		ration_apply.setSprq(new java.sql.Date((new java.util.Date()).getTime()));
		
		dataBaseControl.updateByBean(ration_apply);
		
		
		Gm_ration_apply_audit gm_ration_apply_audit = new Gm_ration_apply_audit();
		gm_ration_apply_audit.setAudit_date(new java.sql.Timestamp((new java.util.Date()).getTime()));
		gm_ration_apply_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
		gm_ration_apply_audit.setOpinion(request.getParameter("opinion"));
		gm_ration_apply_audit.setState(request.getParameter("opinionid"));
		gm_ration_apply_audit.setPurchaseapply_id(new BigDecimal(request.getParameter("id")));
		dataBaseControl.insertByBean(gm_ration_apply_audit);
		
		dataBaseControl.endTransaction();
		
		Map applymap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{ration_apply.getApply_person()});
		
		String num = applymap.get("phone")+"";
		
		
		String infor="";
		if(state.equals("2"))
		{
			infor="您的配给申请已经审核通过";
		}
		else if(state.equals("3"))
		{
			infor="您的配给申请被打回";
		}
		else
		{
			infor="您的配给申请审核未通过";
		}
		//发短信通知
		PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
		Mas m = (Mas) client.useService(Mas.class);
		
		m.sendGFDZ(num,infor);
		
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/materielapply/gm_ration_applyDtl.jsp";
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("purchaseapply_state",codeTableUtil.getCodeMap("purchaseapply_state")); 
	}
	
	public String modify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		request.setAttribute("type", "modify");
		
		List list = dataBaseControl.getOutResultSet("select t.*,m.name mnate,b.name bname,m.model,m.parameter from gm_ration_item t,materiel m,ma_brand b where t.materiel_id = m.id  and t.brand_id = b.id  and t.apply_id = ? order by t.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("mlist", list);
		return "/materielapply/gm_ration_applyModifyDtl.jsp";
	}
	
	
	public String subapply(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map map = dataBaseControl.getOneResultSet("select * from gm_ration_apply t where t.id = ?", new Object[]{request.getParameter("id")});
		

		Gm_ration_apply ration_apply = new Gm_ration_apply();
		BeanUtils.populate(ration_apply, map);
		
		ration_apply.setBh(request.getParameter("bh"));
		ration_apply.setReceive_person(new Integer(Integer.valueOf(request.getParameter("receive_person"))));
		ration_apply.setRecv_call(request.getParameter("recv_call"));
		ration_apply.setSpzt(request.getParameter("spzt"));
		
	
		dataBaseControl.beginTransaction();
		dataBaseControl.updateByBean(ration_apply);
		
		List list = dataBaseControl.getOutResultSet("select t.* from gm_ration_item t where t.apply_id = ? order by t.id ", new Object[]{request.getParameter("id")});
		
		for(int i = 0 ; i < list.size(); i++)
		{
			Map tmap = (Map)list.toArray()[i];
			Gm_ration_item ration_item = new Gm_ration_item();
			BeanUtils.populate(ration_item, tmap);
			String sqsl = request.getParameter("sqsl"+ration_item.getId());
            
			if(sqsl==null||sqsl.equals(""))
			{
				this.doResult(response, "申请数量不能为空");
				return null;
			}
			
			int modifynum = Integer.valueOf(sqsl) - ration_item.getSqsl();
			
			String orderdate = request.getParameter("order_date"+ration_item.getId());
			if(orderdate !=null &&!orderdate.equals(""))
			{ 
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				ration_item.setOrder_date(new java.sql.Date((fmt.parse(orderdate)).getTime()));
			}
			
			ration_item.setSqsl(new Integer(Integer.parseInt(sqsl)));
			dataBaseControl.updateByBean(ration_item);	
			
			
			
		}
		
		dataBaseControl.endTransaction();
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/materielapply/gm_ration_applyModifyDtl.jsp";
	}
	
	
	/**
	 * 保存新增材料（非项目添加）
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String saveNewMa(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map user = (Map)request.getSession().getAttribute("user");
        Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfo.get("duty_id").toString();
		String dept_id = baseinfo.get("dept_id").toString();
		
        Date date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Gm_ration_apply ration_apply = new Gm_ration_apply();
		BeanUtils.populate(ration_apply, getParameterMap(request));
		
		dataBaseControl.beginTransaction();
		String cgtype = request.getParameter("cgtype");
		String bh = "";
		
	    Map typemap = dataBaseControl.getOneResultSet("select * from code t where t.dmlb = 'purchasetype' and t.dmz = ?", new Object[]{cgtype});
		
	    String type = typemap.get("dmlbmc").toString();
	    String sql = "select SEQ_"+type+".NEXTVAL sql from dual";
	    
	    List seqlist = dataBaseControl.getOutResultSet(sql, null);
	    
	    if(seqlist!=null&&!seqlist.isEmpty())
	    {
	    	Map seqmap = (Map)seqlist.get(0);
	    	Long id = new Long(seqmap.get("sql").toString());
	    	bh = type+"-"+fmt.format(date)+ "-" + id;
	    }
		
		
		ration_apply.setApply_date(new java.sql.Date((new java.util.Date()).getTime()));
		ration_apply.setApply_dept(new Integer(Integer.valueOf(user.get("branchid").toString())));
		ration_apply.setApply_person(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
		
		if(duty_id.equals("1"))
		{
			ration_apply.setSpzt("1");
		}
		else
		{
			
			ration_apply.setSpzt("5");
			
		}
		
		
		ration_apply.setBh(bh);
		ration_apply.setIs_finish("0");
		Long id = dataBaseControl.getSeq(Gm_ration_apply.class);
		dataBaseControl.insertByBean(ration_apply,id);
		
		String rowData = request.getParameter("rowData");
		String order_date1 = request.getParameter("order_date1")==null?"":request.getParameter("order_date1");
		
		List<Gm_ration_item> mapList = JSON.parseArray(rowData, Gm_ration_item.class);
        for(int i = 0 ; i < mapList.size();i++)
        {
        	Gm_ration_item ration_item = (Gm_ration_item)mapList.get(i);
        	
        	String orderdate = ration_item.getOrder_date()==null?"":ration_item.getOrder_date().toString();
        	if(orderdate.equals("")&&!order_date1.equals(""))
        	{
        		ration_item.setOrder_date(new java.sql.Date((format.parse(order_date1)).getTime()));
        	}
        	
        	ration_item.setApply_id(new Long(id+""));
        	
        	ration_item.setPurchase_num(new Integer(0));
			ration_item.setCzy(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
			ration_item.setCzrq(new java.sql.Date((new java.util.Date()).getTime()));
        	dataBaseControl.insertByBean(ration_item);
        }
		
		dataBaseControl.endTransaction();
		
		String num = "";
		if(duty_id.equals("1"))
		{
			if(dept_id.equals("10"))
			{
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 10", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
			else
			{
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = ?", new Object[]{dept_id});
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
			}
		}
		
		else  //部门经理提交购买申请
		{
		    if(dept_id.equals("4")||duty_id.equals("4"))
		    {
		    	List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 5 ", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
		    }
		    else
		    {
		    	List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 4 ", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
				}
		    }
		}
		
		
		//发短信通知
		PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
		Mas m = (Mas) client.useService(Mas.class);
		String infor=baseinfo.get("name")+"提出配给申请，请您审批！";
		m.sendGFDZ(num,infor);
        
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/materielapply/gm_ration_applyDtl1.jsp";
	}
	
	
	/*
	 * 提交
	 */
	public String lastsub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map map = dataBaseControl.getOneResultSet("select * from gm_ration_apply t where t.id = ?", new Object[]{request.getParameter("id")});
		

		Gm_ration_apply ration_apply = new Gm_ration_apply();
		BeanUtils.populate(ration_apply, map);
		ration_apply.setSpzt("1");
		
		dataBaseControl.updateByBean(ration_apply);
		
		return "/materielapply/Gm_ration_apply!searchList.do";
	}
	

}


