package com.web.pr.plan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.map.Ds_product;
import com.map.Gm_ration_apply;
import com.map.Gm_ration_item;
import com.map.Pr_ma_plan;
import com.map.Pr_plan;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;

public class Pr_planAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	/*
	 *搜索 
	 */
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		
		request.setAttribute("searchMap", getParameterMap(request));
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/pr/plan/pr_planSelect.jsp";
		request.setAttribute("flag", request.getParameter("flag"));
		request.setAttribute("skip", "searchList");
		return "/pr/plan/pr_planList.jsp";
	}
	
	/*
	 * 查看
	 */
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		List maList = dataBaseControl.getOutResultSet("select t1.*,t2.name ma_name,t3.name br_name from PR_PLAN t," +
				"PR_MA_PLAN t1,materiel t2,ma_brand t3 where t.id=? and t.id=t1.PLAN_ID and t1.BRAND_ID=t3.id " +
				"and t1.MATERIEL_ID=t2.id", new Object[]{request.getParameter("id")});
		request.setAttribute("maList", maList);
		request.setAttribute("flag", request.getParameter("flag"));
		buildDDL(request);
		return "/pr/plan/planShow.jsp";
	}
	
	/*
	 * 审批
	 */
	public String check(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
		
		String opinionid = request.getParameter("opinionid");
		
			dataBaseControl.executeSql("update PR_PLAN t set t.PR_PLAN_STATE=?,t.OPINION=?,t.AUDIT_PERSON=?,t.AUDIT_DATE=?   where t.id=?",
					new Object[]{request.getParameter("opinionid"),request.getParameter("opinion"),user.get("base_info_id").toString(),
					new java.sql.Date((new java.util.Date()).getTime()),request.getParameter("id")});
		request.setAttribute("operationSign", "closeDG_refreshW();");
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/pr/plan/planShow.jsp";
	}
	
	
	/*
	 * 新增--普通
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		Map user = (Map)request.getSession().getAttribute("user");
		Pr_plan prPlan = new Pr_plan();

        Map maps = dataBaseControl.getOneResultSet("select * from PR_PLAN t where t.bh = ?", new Object[]{request.getParameter("bh")});
		
		if(maps != null && !maps.isEmpty())
		{
			this.doResult(response, "此编号已使用，请重新填写编号!");
			return null;
		}

		BeanUtils.populate(prPlan, getParameterMap(request));
		prPlan.setPr_plan_state(request.getParameter("pr_plan_state"));
		prPlan.setMade_date(new java.sql.Date((new java.util.Date()).getTime()));
		prPlan.setMade_person(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(prPlan);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		
		//Map map1 = dataBaseControl.getOneResultSet("select * from PR_PLAN t where t.bh = ? and t.ds_id = ?", new Object[]{prPlan.getBh(),prPlan.getDs_id()});
		Pr_ma_plan maPlanTemp = new Pr_ma_plan();
		//BeanUtils.populate(maPlanTemp, map1);
		Object[] checkbox = request.getParameterValues("checkbox");
		
		if(checkbox.length!=0)
		{
			
			for(int i = 0 ; i < checkbox.length;i++)
			{
		//		Map map2 = dataBaseControl.getOneResultSet("select * from DS_PRODUCT t where t.RESULT_ID = ? and t.materiel_id = ?", new Object[]{prPlan.getDs_id(),checkbox[i].toString()});
				Ds_product ds = new Ds_product();
			//	BeanUtils.populate(ds, map2);
				
				Pr_ma_plan maPlan = new Pr_ma_plan();
				maPlan.setPrj_id(new Integer(request.getParameter("prj_id")));
				maPlan.setPlan_id(maPlanTemp.getId());
				maPlan.setBrand_id(ds.getBrand_id());
				//maPlan.setMateriel_id(ds.getMateriel_id());
				//maPlan.setJhsl(Integer.valueOf(request.getParameter("jhsl"+checkbox[i].toString())));
				/*
				maPlan.setJssm(request.getParameter("jssm"+checkbox[i].toString()));
				maPlan.setShsm(request.getParameter("shsm"+checkbox[i].toString()));
				maPlan.setMbj(ds.getAim_price());
				maPlan.setCgxj(ds.getCgxj());
				maPlan.setCzr(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
				maPlan.setCzrq(new java.sql.Date((new java.util.Date()).getTime()));
				maPlan.setDs_product_id(new Integer(request.getParameter("dsproductid"+checkbox[i].toString())));
				*/
				dataBaseControl.insertByBean(maPlan);
				
					int hasnum = ds.getHasnum().intValue();
					hasnum = hasnum + Integer.valueOf(request.getParameter("jhsl"+checkbox[i].toString()));
					ds.setHasnum(new Integer(hasnum));
					dataBaseControl.updateByBean(ds);
					
				
				
			}
			
		}
		request.setAttribute("operationSign", "closeDG_refreshW();");
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/pr/plan/pr_materielList.jsp";
	}
	
	
	/*
	 * 新增计划（2014-7-21）
	 */
	public String saveplan(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		Map user = (Map)request.getSession().getAttribute("user");
		Pr_plan prPlan = new Pr_plan();
		
		dataBaseControl.beginTransaction();

        Map maps = dataBaseControl.getOneResultSet("select * from PR_PLAN t where t.bh = ?", new Object[]{request.getParameter("bh")});
		
		if(maps != null && !maps.isEmpty())
		{
			this.doResult(response, "此编号已使用，请重新填写编号!");
			return null;
		}

		BeanUtils.populate(prPlan, getParameterMap(request));
		prPlan.setPr_plan_state(request.getParameter("pr_plan_state"));
		prPlan.setMade_date(new java.sql.Date((new java.util.Date()).getTime()));
		prPlan.setMade_person(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
		
		Long id = dataBaseControl.getSeq(Pr_plan.class);
		
		dataBaseControl.insertByBean(prPlan,id);			
		
		String rowData = request.getParameter("rowData");
		
		List<Pr_ma_plan> mapList = JSON.parseArray(rowData, Pr_ma_plan.class);
		
        for(int i = 0 ; i < mapList.size();i++)
        {
        	Pr_ma_plan pr_ma = (Pr_ma_plan)mapList.get(i);
        	pr_ma.setPlan_id(new Integer(id+""));
        	pr_ma.setHasnum(0);
        	pr_ma.setPrj_id(new Integer(request.getParameter("prj_id")));
            dataBaseControl.insertByBean(pr_ma);
        }
		
		dataBaseControl.endTransaction();
		request.setAttribute("operationSign", "closeDG_refreshW();");
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/pr/plan/addMaterielNew.jsp";
	}
	

	/*
	 * 新增--从设计成果
	 */
	public String addfromDS(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
			? "1" : request.getParameter("pageno");
          int pageNo=(new Integer(page_no)).intValue();
          int pageSize=20;

          Map user = (Map)request.getSession().getAttribute("user");
          request.setAttribute("dept_id", user.get("branchid"));
          
          request.setAttribute("searchMap", getParameterMap(request));

          Page page=searchDS(request,pageNo,pageSize);

          request.setAttribute("record", page.getThisPageElements());
          request.setAttribute("page", page);
          buildDDL(request);
          
		return "/pr/plan/pr_planDtl_DS.jsp";
	}

	/*
	 * 设计成果材料清单
	 */
	public String getMateriel(HttpServletRequest request, HttpServletResponse response)	throws Exception {

		String IsPostBack = request.getParameter("IsPostBack");
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
			? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=50;
		
		List reslist = searchMateriel(request);
		if(reslist != null && !reslist.isEmpty())
		{
			request.setAttribute("info", reslist.get(0));
		}
		
//		List llist = new ArrayList(); 
//		
//		for(int i = 0 ; i < reslist.size();i++)
//		{
//			Map tmap = (Map)reslist.toArray()[i];
//			String num = ""+tmap.get("num");
//			
//			Map countmap = dataBaseControl.getOneResultSet("select decode(sum(t.spsl), '', 0, sum(t.spsl)) ycg from pr_ma_plan t,pr_plan p where t.plan_id = p.id   and t.materiel_id = ?  and t.prj_id = ? ", new Object[]{tmap.get("materiel_id"),tmap.get("proj_id")});
//			String ycg = countmap.get("ycg").toString();
//			
//			tmap.put("ycg", ycg);
//			
//			
//			if(Integer.parseInt(num)>Integer.parseInt(ycg))
//			{
//				tmap.put("kcg", Integer.parseInt(num)-Integer.parseInt(ycg));
//				llist.add(tmap);
//			}
//		}
//		
//		
		
		request.setAttribute("searchMap", getParameterMap(request));
        request.setAttribute("record", reslist);
		request.setAttribute("proId", request.getParameter("proId"));
		request.setAttribute("dsId", request.getParameter("id"));
		request.setAttribute("IsPostBack", "1");
		return "/pr/plan/pr_materielList.jsp";
	}
	
	/*
	 * 编辑
	 */
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flag = request.getParameter("flag")==null?"0":request.getParameter("flag");
		if(flag.equals("1"))
		{
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		else
		{
			Page page=search(request,1,1);
			
			int size = page.getTotalNumberOfElements();
			if(size!=0)
			{
				request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
			}
		}
		
		
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		request.setAttribute("type", "modify");
		
		
		
		
		List list = dataBaseControl.getOutResultSet("select t.*,m.name mnate,b.name bname from pr_ma_plan t,materiel m,ma_brand b where t.materiel_id = m.id  and t.brand_id = b.id  and t.plan_id = ? order by t.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("mlist", list);
		return "/pr/plan/pr_ma_planModifyDtl.jsp";
	}
	
	
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map map = dataBaseControl.getOneResultSet("select * from pr_ma_plan t where t.id = ? ", new Object[]{request.getParameter("id")});
		
        
		dataBaseControl.executeSql("DELETE FROM pr_ma_plan WHERE ID in (?)", new Object[]{request.getParameter("id")});
		
		List list= dataBaseControl.getOutResultSet("select * from pr_ma_plan t where t.plan_id = ? ", new Object[]{map.get("plan_id")});
		String flag = "0";
		
		
		if(list == null || list.isEmpty())
		{
			dataBaseControl.executeSql("DELETE FROM pr_plan WHERE ID in (?)", new Object[]{map.get("plan_id")});
			flag = "1";
		}
		else
		{
			
		}
		
		
		return "/pr/plan/Pr_plan!edit.do?id="+map.get("plan_id")+"&flag="+flag;
	
	}
	
	/*
	 * 取字典数据
	 */
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("pr_plan_state",codeTableUtil.getCodeMap("pr_plan_state")); 
	}
	

	/*
	 * 按条件查询--计划
	 */
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t1.*,t2.name as pro_name,(select b.name from hr_base_info b where t1.made_person = b.id)madeperson,(select p.name from pr_project p where p.id = t1.prj_id)proname,decode(t1.pr_plan_state,'0','未提交','1','待部门经理审批','2','审批通过','打回')state from Pr_plan t1,pr_project t2 where 1=1 and t1.prj_id=t2.id"
		+ " /~ and t1.id={id} ~/"
		+ " /~ and t1.prj_id={prj_id} ~/"
		+ "/~ and t2.pro_name like '%[pro_name]%' ~/ "
		+ "/~ and t1.purchase={purchase} ~/ "
		+ "/~ and t1.bh={bh} ~/ "
		+ "/~ and t1.name={name} ~/ "
		+ "/~ and t1.made_date=to_date({made_date},'yyyy-MM-dd') ~/ "
		+ "/~ and t1.made_person={made_person} ~/ "
		+ "/~ and t1.pr_plan_state={pr_plan_state} ~/ "
		+ "/~ and t1.audit_person={audit_person} ~/ "
		+ "/~ and t1.audit_date={audit_date} ~/ ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	/*
	 * 按条件查询--设计
	 */
	private Page searchDS(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		searchMap.put("pr_plan_state", "-1");
		String sql="select t.id, max(t.name)name, max(t.num)num, max(t.compile_time)compile_time, max(p.name) pro_name,max(t.proj_id)proj_id from DS_RESULT t, pr_project p ,ds_product d where t.PROJ_ID = p.id  and t.id = d.result_id and t.appstate = -1 and d.num >d.hasnum "
		+ "/~ and t.NAME like '%[cg_name]%' ~/ "
		+ "/~ and t.num like '%[num]%' ~/ "
		+ "/~ and p.name like  '%[pro_name]%' ~/"
		+ "/~ and t.proj_id =  {prj_id} ~/"
		+ "/~ and t.appstate =  {pr_plan_state} ~/"
		+ " group by t.id order by t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	/*
	 * 按条件查询--设计材料
	 */
	private List searchMateriel(HttpServletRequest request) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select (p.num-p.hasnum)kcg,p.*,l.name result_name,m.name maname, r.name proname,b.name brname, r.pr_manage from DS_PRODUCT p, DS_RESULT l, materiel m, pr_project r, ma_brand b where 1 = 1 and p.PROJ_ID = r.id and p.RESULT_ID = l.id and p.materiel_id = m.id and p.brand_id = b.id and p.num > p.hasnum " +
				"  /~ and p.RESULT_ID={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		List list=dataBaseControl.getOutResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray());
		return list;
	}
	
	
	public String auditsearchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		
		request.setAttribute("type", "audit");
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/pr/plan/pr_planSelect.jsp";
		request.setAttribute("flag", request.getParameter("flag"));
		request.setAttribute("skip", "auditsearchList");
		
		return "/pr/plan/pr_planList.jsp";
	}
	
	
	/*
	 * 保存修改后的结果
	 */
	public String subapply(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map map = dataBaseControl.getOneResultSet("select * from pr_plan t where t.id = ?", new Object[]{request.getParameter("id")});
		

		Pr_plan prplan = new Pr_plan();
		BeanUtils.populate(prplan, map);
		prplan.setName(request.getParameter("name"));
		prplan.setBh(request.getParameter("bh"));
		prplan.setPurchase(request.getParameter("purchase"));
		prplan.setPr_plan_state(request.getParameter("pr_plan_state"));
		prplan.setOpinion(""); 
		prplan.setNote(request.getParameter("note"));
		
		prplan.setMade_date(new java.sql.Date((new java.util.Date()).getTime()));
		
		
		dataBaseControl.beginTransaction();
		
		dataBaseControl.updateByBean(prplan);
		
		List list = dataBaseControl.getOutResultSet("select t.* from pr_ma_plan t where t.plan_id = ? order by t.id ", new Object[]{request.getParameter("id")});
		
		for(int i = 0 ; i < list.size(); i++)
		{
			Map tmap = (Map)list.toArray()[i];
			Pr_ma_plan ma_plan = new Pr_ma_plan();
			
			BeanUtils.populate(ma_plan, tmap);
			
			String sqsl = request.getParameter("sqsl"+ma_plan.getMateriel_id());
			ma_plan.setSqsl(new Integer(Integer.parseInt(sqsl)));
			
			
			dataBaseControl.updateByBean(ma_plan);	
			
		}
		dataBaseControl.endTransaction();
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/pr/plan/pr_ma_planModifyDtl.jsp";
	}
	
	
	/*
	 * 提交
	 */
	public String lastsub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map map = dataBaseControl.getOneResultSet("select * from pr_plan t where t.id = ?", new Object[]{request.getParameter("id")});
		Pr_plan prplan = new Pr_plan();
		BeanUtils.populate(prplan, map);
		prplan.setPr_plan_state("1");
			
		dataBaseControl.updateByBean(prplan);
		
		return "/pr/plan/Pr_plan!searchList.do";
	}
	
}