package com.web.materielpurchase;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.map.Gm_materiel_inout;
import com.map.Gm_purchase;
import com.map.Gm_purchase_audit;
import com.map.Gm_purchase_item;
import com.map.Gm_purchase_item_mo;
import com.map.Gm_purchase_modify;
import com.map.Gm_purchasemodify_audit;
import com.map.Gm_purconfirm_one;
import com.map.Gm_ration_item;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.web.NumToRmb;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Gm_purchase_modifyAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		request.setAttribute("searchMap", getParameterMap(request));
		
		
		Map user = (Map)request.getSession().getAttribute("user");
		request.setAttribute("dept_id", user.get("branchid"));
		
		Map baseinfo = dataBaseControl.getOneResultSet("select t.*,m.branchname from hr_base_info t,mrbranch m where t.dept_id = m.id and t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfo.get("duty_id").toString();
		if(duty_id.equals("4"))//崔总审批
		{
			request.setAttribute("type", "finalaudit");
			Page page=search1(request,pageNo,pageSize);
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
		}
		else if(duty_id.equals("3"))//总经理审批软件部
		{
			request.setAttribute("type", "finalaudit");
			Page page=search2(request,pageNo,pageSize);
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
		}
		else   //综合办经理审批
		{
			request.setAttribute("type", "audit");
			Page page=search(request,pageNo,pageSize);
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
		}
		
		
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/materielpurchase/gm_purchase_modifySelect.jsp";
		return "/materielpurchasemodify/gm_purchase_modifyList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		Map map = dataBaseControl.getOneResultSet("select t.*, (select p.name from pr_project p where t.prj_id = p.id) prjname,(select b.name from hr_base_info b where b.id = t.p_id) pname,decode(t.spzt,'1','待部门经理审批','2','待崔总审批','4','打回','审批通过')state from Gm_purchase_modify t where t.id = ?", new Object[]{request.getParameter("id")});
		request.setAttribute("modify", map);
		
		Map map1 = dataBaseControl.getOneResultSet("select t.*,(select p.name from pr_project p where t.prj_id = p.id)pname,(select b.name from hr_base_info b where b.id = t.p_id)exename,(select m.branchname from mrbranch m where m.id = t.dept_id) brname from gm_purchase t where t.ration_apply_id = ?", new Object[]{map.get("ration_apply_id")});
		request.setAttribute("info", map1);
		
		List modifylist = dataBaseControl.getOutResultSet("select t.*,(select m.name from materiel m where m.id = t.materiel_id)mname,(select mb.name from ma_brand mb where mb.id = t.brand_id)bname,(select sp.name from supplier sp where sp.id = t.gongys) gys,(select sp.name from supplier sp where sp.id = t.gongys1) gys1 from gm_purchase_item_mo t where t.modify_id = ? order by t.id ", new Object[]{map.get("id")});
		request.setAttribute("modifymateriel", modifylist);
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select pa.*,(select b.name from hr_base_info b where b.id = pa.audit_id) person from gm_purchase_modify p, gm_purchasemodify_audit pa where p.id = pa.purchasemodify_id and p.id = ? order by pa.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		return "/materielpurchasemodify/gm_purchase_modifyDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*, (select p.name from pr_project p where t.prj_id = p.id) prjname,(select b.name from hr_base_info b where b.id = t.p_id) pname from Gm_purchase_modify t where 1=1 " 
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.spzt={spzt} ~/"
		+ "/~ and t.prj_id={prj_id} ~/ "
		+ "/~ and t.ration_apply_id={ration_apply_id} ~/ "
		+ "/~ and t.modify_ration_id={modify_ration_id} ~/ "
		+ "/~ and t.modify_date>=to_date({modify_date1},'yyyy-MM-dd') ~/ "
		+ "/~ and t.modify_date<=to_date({modify_date2},'yyyy-MM-dd') ~/ "
		+ " order by t.spzt,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	// 崔总
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*,(select p.name from pr_project p where t.prj_id = p.id) prjname,(select b.name from hr_base_info b where b.id = t.p_id) pname from gm_purchase_modify t, gm_purchase r where t.ration_apply_id = r.ration_apply_id and r.dept_id != 4 " 
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.spzt={spzt} ~/"
		+ "/~ and t.prj_id={prj_id} ~/ "
		+ "/~ and t.ration_apply_id={ration_apply_id} ~/ "
		+ "/~ and t.modify_ration_id={modify_ration_id} ~/ "
		+ "/~ and t.modify_date>=to_date({modify_date1},'yyyy-MM-dd') ~/ "
		+ "/~ and t.modify_date<=to_date({modify_date2},'yyyy-MM-dd') ~/ "
		+ " order by t.spzt,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	//赵总
	private Page search2(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*,(select p.name from pr_project p where t.prj_id = p.id) prjname,(select b.name from hr_base_info b where b.id = t.p_id) pname from gm_purchase_modify t, gm_purchase r where t.ration_apply_id = r.ration_apply_id and r.dept_id = 4 " 
		+ "/~ and t.id={id} ~/"
		+ "/~ and t.spzt={spzt} ~/"
		+ "/~ and t.prj_id={prj_id} ~/ "
		+ "/~ and t.ration_apply_id={ration_apply_id} ~/ "
		+ "/~ and t.modify_ration_id={modify_ration_id} ~/ "
		+ "/~ and t.modify_date>=to_date({modify_date1},'yyyy-MM-dd') ~/ "
		+ "/~ and t.modify_date<=to_date({modify_date2},'yyyy-MM-dd') ~/ "
		+ " order by t.spzt,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	/*
	 * 跳转到变更审批界面
	 */
	public String audit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		Map map = dataBaseControl.getOneResultSet("select t.*, (select p.name from pr_project p where t.prj_id = p.id) prjname,(select b.name from hr_base_info b where b.id = t.p_id) pname,decode(t.spzt,'1','待部门经理审批','2','待崔总审批','4','打回','审批通过')state from Gm_purchase_modify t where t.id = ?", new Object[]{request.getParameter("id")});
		request.setAttribute("modify", map);
		
		Map map1 = dataBaseControl.getOneResultSet("select t.*,(select p.name from pr_project p where t.prj_id = p.id)pname,(select b.name from hr_base_info b where b.id = t.p_id)exename,(select m.branchname from mrbranch m where m.id = t.dept_id) brname from gm_purchase t where t.ration_apply_id = ?", new Object[]{map.get("ration_apply_id")});
		request.setAttribute("info", map1);
		
		List modifylist = dataBaseControl.getOutResultSet("select t.*,(select m.name from materiel m where m.id = t.materiel_id)mname,(select mb.name from ma_brand mb where mb.id = t.brand_id)bname,(select sp.name from supplier sp where sp.id = t.gongys) gys,(select sp.name from supplier sp where sp.id = t.gongys1) gys1 from gm_purchase_item_mo t where t.modify_id = ? order by t.id ", new Object[]{map.get("id")});
		request.setAttribute("modifymateriel", modifylist);
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = dataBaseControl.getOneResultSet("select t.*,m.branchname from hr_base_info t,mrbranch m where t.dept_id = m.id and t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfo.get("duty_id").toString();
		if(duty_id.equals("4"))//崔总审批
		{
			request.setAttribute("type", "finalaudit");
			
		}
		else if(duty_id.equals("3"))//总经理审批软件部
		{
			request.setAttribute("type", "finalaudit");
			
		}
		else   //综合办经理审批
		{
			request.setAttribute("type", "audit");
			
		}
		
		buildDDL(request);
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select pa.*,(select b.name from hr_base_info b where b.id = pa.audit_id) person from gm_purchase_modify p, gm_purchasemodify_audit pa where p.id = pa.purchasemodify_id and p.id = ? order by pa.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		return "/materielpurchasemodify/gm_purchase_modifyDtl.jsp";
	}
	
	/*
	 * 保存变更审批结论
	 */
	public String save(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_modify t where t.id = ?", new Object[]{request.getParameter("id")});
		Map user = (Map)request.getSession().getAttribute("user");
		Gm_purchase_modify purchasemodify = new Gm_purchase_modify();
		BeanUtils.populate(purchasemodify, map);
		
		String state = request.getParameter("opinionid");
		
		
		purchasemodify.setSpzt(request.getParameter("opinionid"));
		purchasemodify.setSpyj(request.getParameter("opinion"));
		purchasemodify.setSpr(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
		purchasemodify.setSprq(new java.sql.Date((new java.util.Date()).getTime()));
		
		
		
		Gm_purchasemodify_audit purchasemodify_audit = new Gm_purchasemodify_audit();
		purchasemodify_audit.setAudit_date(new java.sql.Date((new java.util.Date()).getTime()));
		purchasemodify_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
		purchasemodify_audit.setOpinion(request.getParameter("opinion"));
		purchasemodify_audit.setState(request.getParameter("opinionid"));
		purchasemodify_audit.setPurchasemodify_id(new BigDecimal(request.getParameter("id")));
		
		dataBaseControl.beginTransaction();
		
		dataBaseControl.updateByBean(purchasemodify);
		dataBaseControl.insertByBean(purchasemodify_audit);
		
		
		if(state.equals("5"))
		{
			List molist = dataBaseControl.getOutResultSet("select t.ration_apply_id,r.* from gm_purchase_modify t,gm_purchase_item_mo r where t.id = r.modify_id and t.id = ? order by r.id", new Object[]{request.getParameter("id")});
			float total = 0;
			String ration_apply_id = "";
			for(int i = 0 ; i < molist.size();i++)
			{
				Map map1 = (Map)molist.get(i);
				int modifynum = Integer.valueOf(map1.get("sqsl1").toString())-Integer.valueOf(map1.get("sqsl").toString());
				
				Map tmap = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.ration_apply_id = ?", new Object[]{map1.get("ration_apply_id")});
				ration_apply_id = map1.get("ration_apply_id").toString();
				
				Map puritem = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.purchase_id = ? and t.materiel_id = ?", new Object[]{tmap.get("id"),map1.get("materiel_id")});
				
				dataBaseControl.executeSql("update gm_purchase_item t set t.sqsl = ?,t.gongys = ?,t.price = ? where t.purchase_id = ? and t.materiel_id = ?", new Object[]{map1.get("sqsl1"),map1.get("gongys1"),map1.get("price1"),tmap.get("id"),map1.get("materiel_id")});
				
				Map raitemmap = dataBaseControl.getOneResultSet("select * from gm_ration_item t where t.id = ?", new Object[]{puritem.get("ration_item_id")});
				Gm_ration_item ratiem = new Gm_ration_item();
				BeanUtils.populate(ratiem, raitemmap);
				int hasnum = ratiem.getHasnum().intValue();
				hasnum = hasnum + modifynum;   
				ratiem.setHasnum(new Integer(hasnum));
				dataBaseControl.updateByBean(ratiem);
				
				total = total + Float.parseFloat(map1.get("sqsl1")+"")*Float.parseFloat(map1.get("price1")+"");
			}
			
			dataBaseControl.executeSql("update gm_purchase t set t.xiaoxje = ?,t.daxje=? where t.ration_apply_id = ?", new Object[]{total,NumToRmb.toHanStr(total),ration_apply_id});
			
		}
		
		
		dataBaseControl.endTransaction();
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/materielpurchase/gm_purchaseDtl.jsp";
	}
	
	
	/*
	 * 查看变更记录
	 */
	public String modifylook(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		List reslist = new ArrayList();
		List plist = dataBaseControl.getOutResultSet("select decode(p.spzt,'1','待部门经理审批','2','待崔总审批','4','打回','审批通过') state,t.apply_date,t.xiaoxje,t.daxje,t.dh,(select p.name from pr_project p where t.prj_id = p.id(+)) proname,(select b.name from hr_base_info b where b.id = t.p_id)applyname,(select h.name from hr_base_info h where h.id = t.caigjsr) executename,(select m.branchname from mrbranch m where m.id = t.dept_id) branchname,p.* from gm_purchase t,gm_purchase_modify p where t.ration_apply_id = p.ration_apply_id and t.id = ? order by p.id desc", new Object[]{id});
		
		if(plist != null && !plist.isEmpty())
		{
			request.setAttribute("record", plist.get(0));
		}
		
		for(int i = 0 ; i < plist.size(); i++)
		{
			Map map = (Map)plist.get(i);
			List materielist = dataBaseControl.getOutResultSet("select t.*,(select m.name from materiel m where m.id = t.materiel_id)mname,(select mb.name from ma_brand mb where mb.id = t.brand_id)bname,(select sp.name from supplier sp where sp.id = t.gongys) gys,(select sp.name from supplier sp where sp.id = t.gongys1) gys1 from gm_purchase_item_mo t where t.modify_id = ? order by t.id ", new Object[]{map.get("id")});
			map.put("malist", materielist);
			reslist.add(map);
		}
		
		request.setAttribute("pmlist", reslist);
		return "/materielpurchasemodify/gm_purchaseModifylook.jsp";
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("purchase_state",codeTableUtil.getCodeMap("purchase_state")); 
	}
	
	
	/*
	 * 查询可入库的材料
	 */
	public String rukquery(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		? "1" : request.getParameter("pageno");
        int pageNo=(new Integer(page_no)).intValue();
        int pageSize=20;

        Page page=ruksearch(request,pageNo,pageSize);

        request.setAttribute("record", page.getThisPageElements());
        request.setAttribute("page", page);
        request.setAttribute("searchMap", getParameterMap(request));
		return "/materielpurchase/ruku/rukuquery.jsp";
	}
	
	private Page ruksearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select (r.sqsl - r.rksl) krk,s.name  gyname,p.name  prname,m.name  maname,m.model,b.name  brname,t.ration_apply_id,r.* from gm_purchase t, gm_purchase_item r,supplier s,pr_project p,materiel m,ma_brand b where t.id = r.purchase_id and t.spzt = '5' and r.sqsl > r.rksl and s.id(+) = r.gongys and p.id(+) = t.prj_id and m.id = r.materiel_id and b.id(+) = r.brand_id and r.state!='0'  "
			+ "/~ and p.name like '%[proname]%' ~/ "
			+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
			+ "/~ and m.name like '%[maname]%' ~/ "
			+ "/~ and b.name like '%[brandname]%' ~/ "
			+ "/~ and s.name like '%[gong]%' ~/ "
			+ "/~ and r.materiel_id={materiel_id} ~/ "
			+ "/~ and r.prj_id={prj_id} ~/ "
			
			+ " order by t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	/*
	 * 入库材料
	 */
	public String ruku(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map user = (Map)request.getSession().getAttribute("user");
		
		
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{id});
		Gm_purchase_item purchase_item = new Gm_purchase_item();
		
		Gm_materiel_inout materiel_inout =new Gm_materiel_inout();
		BeanUtils.populate(materiel_inout, map);
		BeanUtils.populate(purchase_item, map);
		float money = 0;
		
		materiel_inout.setPurchase_item_id(new BigDecimal(id));
		materiel_inout.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
		materiel_inout.setType("入库");
		
		if(request.getParameter("krk"+id).equals(""))
		{
			this.doResult(response, "请输入入库数量");
			return null;
		}
		
		if(request.getParameter("price"+id).equals(""))
		{
			this.doResult(response, "请输入入库单价");
			return null;
		}
		
		materiel_inout.setNum(new BigDecimal(request.getParameter("krk"+id).equals("")?"0":request.getParameter("krk"+id)));
		materiel_inout.setOperson(new BigDecimal(""+user.get("base_info_id")));
		materiel_inout.setPrice(new BigDecimal(request.getParameter("price"+id)));
		money =Float.valueOf(materiel_inout.getNum()+"")*Float.valueOf(materiel_inout.getPrice().toString());
		materiel_inout.setMoney(new BigDecimal(money));
		
		
		int ruknum = purchase_item.getRksl();
		ruknum = ruknum + Integer.valueOf(request.getParameter("krk"+id).equals("")?"0":request.getParameter("krk"+id));
		purchase_item.setRksl(ruknum);
		
		
		dataBaseControl.beginTransaction();
		dataBaseControl.insertByBean(materiel_inout);
		dataBaseControl.updateByBean(purchase_item);
		
		dataBaseControl.endTransaction();
		
		return "/materielpurchase/Gm_purchase_modify!rukquery.do";
	}
	
	/*
	 * 批量入库材料
	 */
	public String plruku(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Object[] checkbox = request.getParameterValues("checkbox");
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String sdate = fmt.format(date);
		
		
		if(checkbox.length!=0)
		{
			dataBaseControl.beginTransaction();
			Map user = (Map)request.getSession().getAttribute("user");
			for(int i = 0 ; i < checkbox.length;i++)
			{
				Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{checkbox[i]});
				Gm_purchase_item purchase_item = new Gm_purchase_item();
				
				Gm_materiel_inout materiel_inout =new Gm_materiel_inout();
				BeanUtils.populate(materiel_inout, map);
				BeanUtils.populate(purchase_item, map);
				
				materiel_inout.setPurchase_item_id(new BigDecimal(checkbox[i].toString()));
				materiel_inout.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
				materiel_inout.setType("入库");
				materiel_inout.setOperson(new BigDecimal(""+user.get("base_info_id")));
				
				if(request.getParameter("krk"+checkbox[i]).equals(""))
				{
					this.doResult(response, "请输入入库数量");
					return null;
				}
				
				if(request.getParameter("price"+checkbox[i]).equals(""))
				{
					this.doResult(response, "请输入入库单价");
					return null;
				}
				materiel_inout.setPrice(new BigDecimal(request.getParameter("price"+checkbox[i])));
				materiel_inout.setNum(new BigDecimal(request.getParameter("krk"+checkbox[i]).equals("")?"0":request.getParameter("krk"+checkbox[i])));
				
				
				//自动生成入库单
				List elist = dataBaseControl.getOutResultSet("select * from gm_materiel_inout t where t.odate = to_date(?,'yyyy-MM-dd')", new Object[]{sdate});
				
				String dh = "RK"+sdate+"-"+(elist.size()+1);
				materiel_inout.setDh(dh);
				
				float money = 0 ;
				money =Float.valueOf(materiel_inout.getNum()+"")*Float.valueOf(materiel_inout.getPrice().toString());
				materiel_inout.setMoney(new BigDecimal(money));
				
				int ruknum = purchase_item.getRksl();
				ruknum = ruknum + Integer.valueOf(request.getParameter("krk"+checkbox[i]).equals("")?"0":request.getParameter("krk"+checkbox[i]));
				purchase_item.setRksl(ruknum);
	
				dataBaseControl.insertByBean(materiel_inout);
				dataBaseControl.updateByBean(purchase_item);
			}
			dataBaseControl.endTransaction();
			
		}
		
		return "/materielpurchase/Gm_purchase_modify!rukquery.do";
	}
	
	/*
	 * 查询可入库的材料
	 */
	public String chukquery(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals(""))? "1" : request.getParameter("pageno");
        int pageNo=(new Integer(page_no)).intValue();
        int pageSize=20;

        Page page=chuksearch(request,pageNo,pageSize);

        request.setAttribute("record", page.getThisPageElements());
        request.setAttribute("page", page);
        request.setAttribute("searchMap", getParameterMap(request));
		return "/materielpurchase/chuku/chukuquery.jsp";
	}
	
	private Page chuksearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select (r.rksl - r.cksl) kck,s.name gyname,p.name prname,m.name maname,b.name brname,t.ration_apply_id,r.* from gm_purchase t,gm_purchase_item r,supplier s,pr_project p,materiel m,ma_brand b where t.id = r.purchase_id and t.spzt = '5' and r.rksl > r.cksl and s.id(+) = r.gongys and p.id(+) = t.prj_id and m.id = r.materiel_id and b.id = r.brand_id   "
			+ "/~ and p.name like '%[proname]%' ~/ " 
			+ "/~ and m.name like '%[maname]%' ~/ "
			+ "/~ and s.name like '%[gong]%' ~/ "
			+ "/~ and b.name like '%[brandname]%' ~/ "
			+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
			+ " order by t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	/*
	 * 出库材料
	 */
	public String chuku(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		Map map = dataBaseControl.getOneResultSet("select (r.rksl-r.cksl)kck,(select s.name from supplier s where s.id(+) = r.gongys)gyname, (select p.name from pr_project p where p.id(+)=t.prj_id)prname,(select m.name from materiel m where m.id = r.materiel_id)maname,(select b.name from ma_brand b where b.id = r.brand_id)brname,t.ration_apply_id,r.* from gm_purchase t,gm_purchase_item r where t.id = r.purchase_id and r.id = ?", new Object[]{id});
		request.setAttribute("record", map);
		
		return "/materielpurchase/chuku/chukuDtl.jsp";
	}
	
	/*
	 * 保存出库数量
	 */
	public String savechuku(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
        Map user = (Map)request.getSession().getAttribute("user");
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String sdate = fmt.format(date);
		
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{id});
		Gm_purchase_item purchase_item = new Gm_purchase_item();
		
		Gm_materiel_inout materiel_inout =new Gm_materiel_inout();
		BeanUtils.populate(materiel_inout, map);
		BeanUtils.populate(purchase_item, map);
		
		materiel_inout.setPurchase_item_id(new BigDecimal(id));
		materiel_inout.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
		materiel_inout.setType("出库");
		materiel_inout.setNum(new BigDecimal(request.getParameter("kck").equals("")?"0":request.getParameter("kck")));
		materiel_inout.setOperson(new BigDecimal(""+user.get("base_info_id")));
		materiel_inout.setNote(request.getParameter("note"));
		materiel_inout.setLyr(new Integer(request.getParameter("receive_person")==null?"0":request.getParameter("receive_person")));
		
		//自动生成入库单
		List elist = dataBaseControl.getOutResultSet("select * from gm_materiel_inout t where t.odate = to_date(?,'yyyy-MM-dd')", new Object[]{sdate});
		
		String dh = "CK"+sdate+"-"+(elist.size()+1);
		materiel_inout.setDh(dh);
		
		int chuknum = purchase_item.getCksl();
		chuknum = chuknum + Integer.valueOf(request.getParameter("kck").equals("")?"0":request.getParameter("kck"));
		purchase_item.setCksl(chuknum);
		
		
		dataBaseControl.beginTransaction();
		dataBaseControl.insertByBean(materiel_inout);
		dataBaseControl.updateByBean(purchase_item);
		
		dataBaseControl.endTransaction();
		
		request.setAttribute("operationSign", "closeDG_refreshW();");
		
		return "/materielpurchase/chuku/chukuDtl.jsp";
	}
	
	/*
	 * 查询可入库的材料
	 */
	public String kucunquery(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		? "1" : request.getParameter("pageno");
        int pageNo=(new Integer(page_no)).intValue();
        int pageSize=20;

        Page page=kucunsearch(request,pageNo,pageSize);

        request.setAttribute("record", page.getThisPageElements());
        request.setAttribute("page", page);
        request.setAttribute("searchMap", getParameterMap(request));
		return "/materielpurchase/kucun/kucunquery.jsp";
	}
	
	
	private Page kucunsearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select r.materiel_id,(sum(r.rksl)-sum(r.cksl))kucun,max(m.name)maname,max(b.name)brname from gm_purchase t, gm_purchase_item r,materiel m,ma_brand b where t.id = r.purchase_id and r.materiel_id = m.id and r.brand_id = b.id and t.spzt = '5'    "
			+ "/~ and r.materiel_id={materiel_id} ~/ "
			+ "/~ and r.brand_id={brand_id} ~/ "
			+ "/~ and m.kind_id={kind_id} ~/ "
			+ " group by r.materiel_id ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String lookdetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String materiel_id = request.getParameter("materiel_id");
		
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		? "1" : request.getParameter("pageno");
        int pageNo=(new Integer(page_no)).intValue();
        int pageSize=20;
        
        Map map = getParameterMap(request);
        map.put("materiel_id", materiel_id);

        Page page=detailsearch(map,pageNo,pageSize);

        request.setAttribute("record", page.getThisPageElements());
        request.setAttribute("page", page);
        request.setAttribute("searchMap", getParameterMap(request));
		return "/materielpurchase/kucun/kucundetail.jsp";
	}
	
	private Page detailsearch(Map searchMap,int pageNo,int pageSize) throws Exception 
	{
		
		String sql="select t.ration_apply_id,o.dh,o.num,o.note,o.price,o.money, b.name brname,m.name,o.type,o.odate,(select hb.name from hr_base_info hb where hb.id = o.operson)pname,p.name proname from gm_purchase t, gm_purchase_item r, materiel m, ma_brand b,gm_materiel_inout o,pr_project p where t.id = r.purchase_id and r.id = o.purchase_item_id and r.materiel_id = m.id and r.brand_id = b.id  and t.spzt = '5' and t.prj_id = p.id(+)   "
			+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
			+ "/~ and o.dh like '%[dh]%' ~/ "
			+ "/~ and p.name like '%[proname]%' ~/ "
			+ "/~ and m.name like '%[maname]%' ~/ "
			+ "/~ and o.type = {type} ~/ "
			
			+ " order by o.id ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	public String churukquery(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		? "1" : request.getParameter("pageno");
        int pageNo=(new Integer(page_no)).intValue();
        int pageSize=20;
        
        Map map = new HashMap();
        

        Page page=detailsearch(getParameterMap(request),pageNo,pageSize);

        request.setAttribute("record", page.getThisPageElements());
        request.setAttribute("page", page);
        request.setAttribute("searchMap", getParameterMap(request));
		return "/materielpurchase/kucun/churudetail.jsp";
	}
	//////////////////////////出入库查询统计
	public String churukqueryDemo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		String sql="select t.ration_apply_id,o.dh,o.num,o.note,o.price,o.money, b.name brname,m.name,o.type,o.odate,(select hb.name from hr_base_info hb where hb.id = o.operson)pname,p.name proname from gm_purchase t, gm_purchase_item r, materiel m, ma_brand b,gm_materiel_inout o,pr_project p where t.id = r.purchase_id and r.id = o.purchase_item_id and r.materiel_id = m.id and r.brand_id = b.id  and t.spzt = '5' and t.prj_id = p.id(+)   "
				+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
				+ "/~ and o.dh like '%[dh]%' ~/ "
				+ "/~ and p.name like '%[proname]%' ~/ "
				+ "/~ and m.name like '%[maname]%' ~/ "
				+ "/~ and o.type = {type} ~/ "
				+ "/~ and o.odate>=to_date({app_date1},'yyyy-MM-dd')  ~/ "
				+ "/~ and o.odate<=to_date({app_date2},'yyyy-MM-dd')   ~/ ";
			defaultList(request,response,new StringBuffer(sql),"o.id desc"); 
			
			return null;
		
		}
	public void total(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String sql="select sum(o.money)sum,count(*)count from gm_purchase t, gm_purchase_item r, materiel m, ma_brand b,gm_materiel_inout o,pr_project p where t.id = r.purchase_id and r.id = o.purchase_item_id and r.materiel_id = m.id and r.brand_id = b.id  and t.spzt = '5' and t.prj_id = p.id(+)   "
				+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
				+ "/~ and o.dh like '%[dh]%' ~/ "
				+ "/~ and p.name like '%[proname]%' ~/ "
				+ "/~ and m.name like '%[maname]%' ~/ "
				+ "/~ and o.type = {type} ~/ "
				+ "/~ and o.odate>=to_date({app_date1},'yyyy-MM-dd')  ~/ "
				+ "/~ and o.odate<=to_date({app_date2},'yyyy-MM-dd')   ~/ ";
			
			XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
			Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
			Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
			
			String jsonStr = JSON.toJSONString(map);
			setAjaxInfo(response,jsonStr);
		
		}
	///////////////////////  
	
	
	/**
	 * 批量出库
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String pilck(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String ids = request.getParameter("ids");
		List reslist = new ArrayList();
		
		String [] id = ids.substring(1).split(";");
		//String ids = "";
		
		
		for(int i = 0 ; i < id.length;i++)
		{
				Map map = dataBaseControl.getOneResultSet("select (r.rksl-r.cksl)kck,(select s.name from supplier s where s.id(+) = r.gongys)gyname, (select p.name from pr_project p where p.id(+)=t.prj_id)prname,(select m.name from materiel m where m.id = r.materiel_id)maname,(select b.name from ma_brand b where b.id = r.brand_id)brname,t.ration_apply_id,r.* from gm_purchase t,gm_purchase_item r where t.id = r.purchase_id and r.id = ?", new Object[]{id[i]});
				reslist.add(map);
		}
		
		request.setAttribute("reslist", reslist);
			
		return "/materielpurchase/chuku/plchukuDtl.jsp";
	}
	
	
	/*
	 * 保存出库数量
	 */
	public String savepilchuku(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
        Map user = (Map)request.getSession().getAttribute("user");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String sdate = fmt.format(date);
 
        Object[] checkbox = request.getParameterValues("checkbox");
        if(checkbox.length!=0)
		{
        	dataBaseControl.beginTransaction();
        	for(int i = 0 ; i < checkbox.length;i++)
        	{
        		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{checkbox[i]});
        		Gm_purchase_item purchase_item = new Gm_purchase_item();
        		
        		Gm_materiel_inout materiel_inout =new Gm_materiel_inout();
        		BeanUtils.populate(materiel_inout, map);
        		BeanUtils.populate(purchase_item, map);
        		
        		materiel_inout.setPurchase_item_id(new BigDecimal(checkbox[i].toString()));
        		materiel_inout.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
        		materiel_inout.setType("出库");
        		materiel_inout.setNum(new BigDecimal(request.getParameter("ckl"+checkbox[i]).equals("")?"0":request.getParameter("ckl"+checkbox[i])));
        		materiel_inout.setOperson(new BigDecimal(""+user.get("base_info_id")));
        		materiel_inout.setNote(request.getParameter("note"));
        		materiel_inout.setLyr(new Integer(request.getParameter("receive_person")==null?"0":request.getParameter("receive_person")));
        		
        		//自动生成入库单
        		List elist = dataBaseControl.getOutResultSet("select * from gm_materiel_inout t where t.odate = to_date(?,'yyyy-MM-dd')", new Object[]{sdate});
        		
        		String dh = "CK"+sdate+"-"+(elist.size()+1);
        		materiel_inout.setDh(dh);
        		
        		int chuknum = purchase_item.getCksl();
        		chuknum = chuknum + Integer.valueOf(request.getParameter("ckl"+checkbox[i]).equals("")?"0":request.getParameter("ckl"+checkbox[i]));
        		purchase_item.setCksl(chuknum);
        		
        		
        		
        		dataBaseControl.insertByBean(materiel_inout);
        		dataBaseControl.updateByBean(purchase_item);
        		
        	}
        	dataBaseControl.endTransaction();
		}
        
        
		request.setAttribute("operationSign", "closeDG_refreshW();");
		
		return "/materielpurchase/chuku/plchukuDtl.jsp";
	}
	
	/**
	 * 到货确认
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String purconfirm(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		    ? "1" : request.getParameter("pageno");
             int pageNo=(new Integer(page_no)).intValue();
             int pageSize=20;

             Page page=confirmsearch(request,pageNo,pageSize);
             
             request.setAttribute("page", page);
             request.setAttribute("record", page.getThisPageElements());
             request.setAttribute("searchMap", getParameterMap(request));
			
		return "/materielpurchase/confrimList.jsp";
	}
	
	
	private Page confirmsearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfomap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfomap.get("duty_id").toString();
		searchMap.put("p_id", baseinfomap.get("id"));
		
		String sql="select p.spzt,pi.sqsl-nvl(pi.confirmsql,0)kqr, nvl(pi.confirmsql,0)consql,pi.*,po.name proname,t.bh,m.name maname,mb.name bname,(select b.name from hr_base_info b where b.id = p.caigjsr) ename from gm_purchase p,gm_purchase_item pi,gm_ration_apply  t,gm_ration_item r,pr_project po,materiel m,ma_brand mb where p.id = pi.purchase_id and pi.ration_item_id = r.id and r.apply_id = t.id  and p.prj_id = po.id(+) and m.id = pi.materiel_id and pi.brand_id = mb.id(+) and pi.sqsl > nvl(pi.confirmsql,0) and pi.state != '0' "
			+ "/~ and p.p_id={p_id} ~/ "
			+ "/~ and po.name like '%[proname]%' ~/ "
			+ "/~ and t.bh like '%[dh]%' ~/ "
			+ "/~ and p.ration_apply_id like '%[ration_apply_id]%' ~/ "
			+ " order by p.id";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	/**
	 * 保存确认结果
	 */
	public String saveconfirm(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Object[] checkbox = request.getParameterValues("checkbox");
		
		
		if(checkbox.length>0)
		{
			dataBaseControl.beginTransaction();
			for(int i = 0 ; i < checkbox.length;i++)
			{
				Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{checkbox[i]});
				Gm_purchase_item gm_purchase_item = new Gm_purchase_item();
				BeanUtils.populate(gm_purchase_item, map);
				
				int num = Integer.parseInt(request.getParameter("qr"+gm_purchase_item.getId()));//获得此次确认的数量
				int confirmnum = (gm_purchase_item.getConfirmsql()==null||gm_purchase_item.getConfirmsql().equals(""))?0:gm_purchase_item.getConfirmsql();  //获得已经确认的数量
				confirmnum = confirmnum + num;
				gm_purchase_item.setConfirmsql(confirmnum);
				dataBaseControl.updateByBean(gm_purchase_item);
				
				Gm_purconfirm_one gm_purconfirm_one = new Gm_purconfirm_one(); 
				gm_purconfirm_one.setApplyitem_id(new Integer(gm_purchase_item.getId()+""));
				gm_purconfirm_one.setNum(num);
				gm_purconfirm_one.setOdate(new java.sql.Timestamp((new java.util.Date()).getTime()));
				dataBaseControl.insertByBean(gm_purconfirm_one);
			}
			dataBaseControl.endTransaction();
		}
			
		return "/materielpurchase/Gm_purchase_modify!purconfirm.do";
	}
	
	/**
	 * 延期采购查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String delayquery(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		    ? "1" : request.getParameter("pageno");
             int pageNo=(new Integer(page_no)).intValue();
             int pageSize=20;

             Page page=delaysearch(request,pageNo,pageSize);
             
             request.setAttribute("page", page);
             request.setAttribute("record", page.getThisPageElements());
             request.setAttribute("searchMap", getParameterMap(request));
			
		return "/materielpurchase/delayList.jsp";
	}
	
	
	private Page delaysearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		String state = request.getParameter("state")==null?"":request.getParameter("state");
		
		if(state.equals("0"))//即将到期
		{
			searchMap.put("day1", "-5");
			searchMap.put("day2", "0");
		}
		
		if(state.equals("1"))//超期
		{
			searchMap.put("day1", "1");
		}
		
		if(state.equals(""))
		{
			searchMap.put("day1", "-5");
		}
		
		String sql="select t.ration_apply_id,r.*,m.name maname,p.name proname,(select b.name from hr_base_info b where b.id = t.caigjsr)ename,decode(sign(sysdate - r.yaoqdhrq),1,1,0)chaoqi  from gm_purchase t, gm_purchase_item r, materiel m, pr_project p where t.id = r.purchase_id and r.materiel_id = m.id  and t.prj_id = p.id(+) and r.sqsl > nvl(r.confirmsql,0)  "
			+ "/~ and p.name like '%[proname]%' ~/ "
			+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
			+ "/~ and sysdate-r.yaoqdhrq >= {day1} ~/ "
			+ "/~ and sysdate-r.yaoqdhrq <= {day2} ~/ "
			+ " order by r.yaoqdhrq,t.id";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	/*
	 * 取消采购
	 */
	public String cancelpur(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{id});
		request.setAttribute("record", map);
		
		return "/materielpurchase/cancelpurDtl.jsp";
	}
	
	/*
	 * 保存取消采购原因
	 */
	public String savecancel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{id});
		Gm_purchase_item gm_purchase_item = new Gm_purchase_item();
		BeanUtils.populate(gm_purchase_item, map);
		gm_purchase_item.setReason(request.getParameter("reason"));
		gm_purchase_item.setState("0");
		
		dataBaseControl.beginTransaction();
		
		dataBaseControl.updateByBean(gm_purchase_item);
		
		
		Map countmap = dataBaseControl.getOneResultSet("select sum(t.sqsl*t.price)money  from gm_purchase_item t where t.purchase_id = ? and t.state !='0'", new Object[]{gm_purchase_item.getPurchase_id()});
		
		Map tmap = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.id = ?", new Object[]{gm_purchase_item.getPurchase_id()});
		Gm_purchase gm_purchase = new Gm_purchase();
		BeanUtils.populate(gm_purchase, tmap);
		gm_purchase.setXiaoxje(new BigDecimal(countmap.get("money").toString()));
		gm_purchase.setDaxje(NumToRmb.toHanStr(Double.parseDouble(countmap.get("money").toString())));
		
		dataBaseControl.updateByBean(gm_purchase);
		dataBaseControl.endTransaction();
		
		

		request.setAttribute("operationSign", "closeDG_refreshW();");
		
		
		return "/materielpurchase/cancelpurDtl.jsp";
	}
	

}