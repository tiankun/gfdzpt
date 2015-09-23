package com.web.materielpurchase;

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
import com.map.Gm_purchase;
import com.map.Gm_purchase_audit;
import com.map.Gm_purchase_item;
import com.map.Gm_purchase_item_mo;
import com.map.Gm_purchase_modify;
import com.map.Gm_ration_item;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;
import com.sysFrams.web.NumToRmb;
import com.zsc.Mas;

public class Gm_purchaseAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("type", "selflook");
		request.setAttribute("searchMap", getParameterMap(request));
		
		List reslist = new ArrayList();
		
		List list = (List)page.getThisPageElements();
		
		for(int i = 0 ; i < list.size() ;i++)
		{
			Map tmap = (Map)list.get(i);
			
			List slist = dataBaseControl.getOutResultSet("select * from gm_purchase_modify r where r.ration_apply_id = ? and r.spzt !=5", new Object[]{tmap.get("ration_apply_id")});
			if(slist!=null&&!slist.isEmpty())
			{
				tmap.put("modify", "1");
			}
			
			List qrlist = dataBaseControl.getOutResultSet("select * from gm_purchase_item t where t.purchase_id = ? and t.sqsl>nvl(t.confirmsql,0)", new Object[]{tmap.get("id")});
			if(qrlist != null && !qrlist.isEmpty())
			{
				tmap.put("issure", "0");
			}
			
			reslist.add(tmap);
		}
		
		
		request.setAttribute("record", reslist);
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/materielpurchase/gm_purchaseSelect.jsp";
		return "/materielpurchase/gm_purchaseList.jsp";
	}
	
	public String checkList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=searchCheck(request,pageNo,pageSize);
		
		List reslist = new ArrayList();
		List list = (List)page.getThisPageElements();
		for(int i = 0 ; i < list.size() ;i++)
		{
			Map tmap = (Map)list.get(i);
			
			List slist = dataBaseControl.getOutResultSet("select * from gm_purchase_modify r where r.ration_apply_id = ? and r.spzt !=5", new Object[]{tmap.get("ration_apply_id")});
			if(slist!=null&&!slist.isEmpty())
			{
				tmap.put("modify", "1");
			}
			
			List qrlist = dataBaseControl.getOutResultSet("select * from gm_purchase_item t where t.purchase_id = ? and t.sqsl>nvl(t.confirmsql,0)", new Object[]{tmap.get("id")});
			if(qrlist != null && !qrlist.isEmpty())
			{
				tmap.put("issure", "0");
			}
			
			reslist.add(tmap);
		}
		
		request.setAttribute("record", reslist);
		request.setAttribute("page", page);
		buildDDL(request);
		return "/materielpurchase/checkList.jsp";
	}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		//Map map = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.id = ?", new Object[]{request.getParameter("id")});
		
		//String spzt = map.get("spzt").toString();
		
		
		
		List list = dataBaseControl.getOutResultSet("select t.*,m.model,(t.sqsl*t.price) totalMoney,m.unit,m.parameter,m.name mnate,b.name bname,(select s.name from supplier s where s.id = t.gongys)gys from gm_purchase_item t,materiel m,ma_brand b where t.materiel_id = m.id  and t.brand_id = b.id  and t.purchase_id = ?", new Object[]{request.getParameter("id")});
		request.setAttribute("mlist", list);
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select pa.*, (select b.name from hr_base_info b where b.id = pa.audit_id) person from gm_purchase p, gm_purchase_audit pa where p.id = pa.purchase_id and p.id = ? order by pa.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		return "/materielpurchase/gm_purchaseDtl.jsp";
	}
	
	/*
	 * 配给发起人查看
	 */
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfomap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfomap.get("duty_id").toString();
		
		/*if(duty_id.equals("1"))
		{
			searchMap.put("caigjsr", baseinfomap.get("id"));
		}
		*/
		String sql="select (select count(*) from gm_purchase_modify r where r.ration_apply_id = t.ration_apply_id) count,t.*, b.name applyname,(select h.name from hr_base_info h where h.id = t.caigjsr) executename, m.branchname branchname, p.name proname from gm_purchase t, hr_base_info b, pr_project p,mrbranch m where 1 = 1 and b.id = t.p_id  and t.prj_id = p.id(+) and t.dept_id = m.id "
		   + "/~ and b.name like '%[name]%' ~/ " 
		   + "/~ and m.branchname like '%[branchname]%' ~/ "
		   + "/~ and p.name like '%[proname]%' ~/ "
		   + "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
		   + "/~ and t.dh like '%[dh]%' ~/ "
		   + "/~ and t.caigjsr={caigjsr} ~/ "
		   + "/~ and t.dept_id={dept_id} ~/ "
		   + "/~ and t.prj_id={prj_id} ~/ "
		   
		   + "/~ and t.id={id} ~/ "
		   + "/~ and t.spzt={spzt} ~/ "
		   + "/~ and t.xiaoxje={xiaoxje} ~/ "
			+ " order by t.spzt, t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	/*
	 * 配给查看
	 */
	private Page searchCheck(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		String sql="select (select count(*) from gm_purchase_modify r where r.ration_apply_id = t.ration_apply_id) count,t.*, b.name applyname,(select h.name from hr_base_info h where h.id = t.caigjsr) executename, m.branchname branchname, p.name proname from gm_purchase t, hr_base_info b, pr_project p,mrbranch m where 1 = 1 and b.id = t.p_id  and t.prj_id = p.id(+) and t.dept_id = m.id "
		   + "/~ and b.name like '%[name]%' ~/ " 
		   + "/~ and m.branchname like '%[branchname]%' ~/ "
		   + "/~ and p.name like '%[proname]%' ~/ "
		   + "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
		   + "/~ and t.dh like '%[dh]%' ~/ "
		   + "/~ and t.caigjsr={caigjsr} ~/ "
		   + "/~ and t.dept_id={dept_id} ~/ "
		   + "/~ and t.prj_id={prj_id} ~/ "
		   
		   + "/~ and t.id={id} ~/ "
		   + "/~ and t.spzt={spzt} ~/ "
		   + "/~ and t.xiaoxje={xiaoxje} ~/ "
			+ " order by t.spzt, t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	/*
	 * 配给执行审批查询
	 */
	private Page search2(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		
		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfomap = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfomap.get("duty_id").toString();
		
		if(duty_id.equals("1"))
		{
			searchMap.put("caigjsr", baseinfomap.get("id"));
		}
		else
		{
			String spzt = request.getParameter("spzt")==null?"1":request.getParameter("spzt");
			searchMap.put("spzt", spzt);
		}
		
		String sql="select t.*,(select h.name from hr_base_info h where h.id = t.caigjsr) executename,(select b.name from hr_base_info b where b.id = t.p_id) applyname,(select m.branchname from mrbranch m where m.id = t.dept_id)branchname, (select p.name from pr_project p where t.prj_id = p.id(+)) proname from gm_purchase t where 1=1 /~ and id={id} ~/"
		+ "/~ and t.p_id={p_id} ~/ "
		+ "/~ and t.spzt={spzt} ~/ "
		+ "/~ and t.caigjsr={caigjsr} ~/ "
		+ "/~ and t.dept_id={dept_id} ~/ "
		+ "/~ and t.prj_id={prj_id} ~/ "
		+ "/~ and t.dh={dh} ~/ "
		+ "/~ and t.ration_apply_id={ration_apply_id} ~/ "
		+ "/~ and t.xiaoxje={xiaoxje} ~/ "
		+ " order by t.spzt,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	/*
	 * 配给执行审批查询
	 */
	private Page search3(Map map,int pageNo,int pageSize) throws Exception 
	{
		//Map searchMap=getParameterMap(request);
		
		String sql="select t.*,(select h.name from hr_base_info h where h.id = t.caigjsr) executename,(select b.name from hr_base_info b where b.id = t.p_id) applyname,(select m.branchname from mrbranch m where m.id = t.dept_id)branchname, (select p.name from pr_project p where t.prj_id = p.id(+)) proname from gm_purchase t where 1=1 and t.dept_id = 4" +
		  "/~ and id={id} ~/"
		+ "/~ and t.p_id={p_id} ~/ "
		+ "/~ and t.spzt={spzt} ~/ "
		+ "/~ and t.caigjsr={caigjsr} ~/ "
		+ "/~ and t.dept_id={dept_id} ~/ "
		+ "/~ and t.prj_id={prj_id} ~/ "
		+ "/~ and t.dh={dh} ~/ "
		+ "/~ and t.ration_apply_id={ration_apply_id} ~/ "
		+ "/~ and t.xiaoxje={xiaoxje} ~/ "
		+ " order by t.spzt,t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,map); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	/*
	 * 配给执行审批查询
	 */
	private Page search4(Map map,int pageNo,int pageSize) throws Exception 
	{
		//Map searchMap=getParameterMap(request);
		
		String sql="select t.*,(select h.name from hr_base_info h where h.id = t.caigjsr) executename,(select b.name from hr_base_info b where b.id = t.p_id) applyname,(select m.branchname from mrbranch m where m.id = t.dept_id)branchname, (select p.name from pr_project p where t.prj_id = p.id(+)) proname from gm_purchase t where 1=1 and t.dept_id != 4" +
		  "/~ and id={id} ~/"
		+ "/~ and t.p_id={p_id} ~/ "
		+ "/~ and t.spzt={spzt} ~/ "
		+ "/~ and t.caigjsr={caigjsr} ~/ "
		+ "/~ and t.dept_id={dept_id} ~/ "
		+ "/~ and t.prj_id={prj_id} ~/ "
		+ "/~ and t.dh={dh} ~/ "
		+ "/~ and t.ration_apply_id={ration_apply_id} ~/ "
		+ "/~ and t.xiaoxje={xiaoxje} ~/ "
		+ " order by t.spzt, t.id desc";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,map); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	
	
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.id, max(t.bh) bh,max(t.apply_date) apply_date,max(m.branchname) dept,max(b.name) personname,max(p.name) proname from gm_ration_apply t, gm_ration_item r,mrbranch m,hr_base_info b,pr_project p where t.spzt = '2' and t.id = r.apply_id and r.sqsl > r.hasnum and t.apply_dept = m.id and t.apply_person = b.id and t.prj_id = p.id(+)  "
			+ "/~ and b.name like '%[name]%' ~/ "
			+ "/~ and m.branchname like '%[branchname]%' ~/ "
			+ "/~ and p.name like '%[proname]%' ~/ "
			+ "/~ and t.bh like '%[bh]%' ~/ "
			
		    + " group by t.id order by t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
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
		return "/materielpurchase/gm_ration_applyList.jsp";
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
		return "/materielpurchase/gm_purchaseDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ? ", new Object[]{request.getParameter("id")});
		
        Map map1 = dataBaseControl.getOneResultSet("select * from gm_ration_item t where t.id = ?", new Object[]{map.get("ration_item_id")});
        Gm_ration_item rationitem = new Gm_ration_item();
        BeanUtils.populate(rationitem, map1);
        int hasnum = rationitem.getHasnum() - Integer.parseInt(map.get("sqsl").toString());
        rationitem.setHasnum(hasnum);
        dataBaseControl.updateByBean(rationitem);
        
		dataBaseControl.executeSql("DELETE FROM gm_purchase_item WHERE ID in (?)", new Object[]{request.getParameter("id")});
		
		List list= dataBaseControl.getOutResultSet("select * from gm_purchase_item t where t.purchase_id = ? ", new Object[]{map.get("purchase_id")});
		String flag = "0";
		
		
		if(list == null || list.isEmpty())
		{
			dataBaseControl.executeSql("DELETE FROM gm_purchase WHERE ID in (?)", new Object[]{map.get("purchase_id")});
			flag = "1";
		}
		else
		{
			
		}
		
		
		return "/materielpurchase/Gm_purchase!modify.do?id="+map.get("purchase_id")+"&flag="+flag;
	
	}
	
	private List search2(HttpServletRequest request) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select (p.sqsl-p.hasnum)kcg,m.model,m.unit,m.parameter,p.*,m.name maname,b.name brname,(select pp.name from pr_project pp where pp.id(+) = ap.prj_id )proname,(select b.name from hr_base_info b where b.id = ap.receive_person)recename from gm_ration_item p, materiel m, ma_brand b,gm_ration_apply ap where 1 = 1and p.materiel_id = m.id and p.brand_id = b.id and ap.id = p.apply_id and p.sqsl > p.hasnum " 
				+ "/~ and p.apply_id={id} ~/"
				+ " order by p.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		List list=dataBaseControl.getOutResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray());
		return list;
	}
	
	
	
	public String getmateriel(HttpServletRequest request, HttpServletResponse response)	throws Exception {
	    
		Map map = dataBaseControl.getOneResultSet("select * from gm_ration_apply t where t.id = ?", new Object[]{request.getParameter("id")});

		List reslist = search2(request);
		
		request.setAttribute("ssize", reslist.size());
        
		
		request.setAttribute("searchMap", getParameterMap(request));
        request.setAttribute("record", reslist);
		
		request.setAttribute("info", map);
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String year = fmt.format(date);
		String thisyear = year.substring(2, 4);
		request.setAttribute("thisyear", thisyear);
		
		
		buildDDL(request);
		return "/materielpurchase/ration_itemList.jsp";
	}
	
	/*
	 * 保存配给执行
	 */
	public String sub(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
        
		Gm_purchase purchase = new Gm_purchase();
		BeanUtils.populate(purchase, getParameterMap(request));
		
		
		String[]zi_liaos=request.getParameterValues("fujian");
		
		String fujian = ""; 
	    if(zi_liaos!=null&&zi_liaos.length>0)
	    {
	    	for(int i = 0 ; i < zi_liaos.length;i++)
			{
				String str = zi_liaos[i];
				fujian = fujian + ";" + str;
				
			}
	    }
		
	    purchase.setPath(fujian);

		Map maps = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.ration_apply_id = ? ", new Object[]{purchase.getRation_apply_id()});
		
		if(maps != null && !maps.isEmpty())
		{
			this.doResult(response, "此配给执行单号已使用，请重新填写配给执行单号!");
			return null;
		}
		
		purchase.setCaigjsr(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
		purchase.setIs_finish("0");
		purchase.setSpzt(request.getParameter("spzt"));
		purchase.setApply_date(new java.sql.Date((new java.util.Date()).getTime()));
		
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String year = fmt1.format(date);
		String thisyear = year.substring(2, 4);
		
		String type =request.getParameter("type");
		String str1 = "";
		
		if(type.equals("1"))
		{
			str1 = "科技";
		}
		else
		{
			str1 = "昆明";
		}
	
		//生成配给执行单编号
		String zxd = thisyear+"PZKM";
		String last = "";
		int ordered = 0	;
		
		List list = dataBaseControl.getOutResultSet("select * from gm_purchase t where t.prj_id = ? order by t.ordered desc,t.id desc", new Object[]{purchase.getPrj_id()});
		
		//获得今年的总配给数量
		Map mapt = dataBaseControl.getOneResultSet("select count(distinct t.prj_id)count1 from gm_purchase t where t.dh = ?", new Object[]{"GFD/04-57-"+thisyear});
		
		if(list ==null || list.isEmpty())  //新项目配给
		{
			int count = Integer.parseInt(mapt.get("count1").toString());
			int nextcount = count+1;
			if((nextcount+"").length()==1)
			{
				last = "000"+nextcount;
			}
			else if((nextcount+"").length()==2)
			{
				last = "00"+nextcount;
			}
			else if((nextcount+"").length()==3)
			{
				last = "0"+nextcount;
			}
			else
			{
				last = ""+nextcount;
			}
			
			zxd = zxd + last + str1;
		}
		
		else
		{
			Map mapt1 = (Map)list.get(0);
			String existzxd = (String)mapt1.get("ration_apply_id");
			String maxorder = mapt1.get("ordered").toString();
			int newordered = Integer.parseInt(maxorder)+1;
			
			zxd = existzxd.substring(0, 12)+"-"+newordered;	
			ordered = newordered;
		}
		purchase.setOrdered(new Integer(ordered));
        purchase.setRation_apply_id(zxd);		
		
		Long id = dataBaseControl.getSeq(Gm_purchase.class);
		
		dataBaseControl.beginTransaction();
		
		dataBaseControl.insertByBean(purchase, id);
		
		
		Object[] checkbox = request.getParameterValues("checkbox");
		float total = 0;
		
		if(checkbox.length!=0)
		{
			
			for(int i = 0 ; i < checkbox.length;i++)
			{
				Map map1 = dataBaseControl.getOneResultSet("select * from materiel t where t.id = ? ", new Object[]{checkbox[i].toString()});
				
				Gm_purchase_item purchase_item = new Gm_purchase_item();
				BeanUtils.populate(purchase_item, map1);
				purchase_item.setPurchase_id(new Integer(id+""));
				
				String str = request.getParameter("cgsl")+checkbox[i].toString();
				purchase_item.setMateriel_id(new Integer(checkbox[i].toString()));
				
				String gys = request.getParameter("gongys"+checkbox[i].toString());
				
				if(gys==null||gys.equals(""))
				{
					this.doResult(response, "请填写供应商");
					return null;
				}
				
				purchase_item.setGongys(new Integer(request.getParameter("gongys"+checkbox[i].toString())));
				
				purchase_item.setSqsl(new Integer(request.getParameter("cgsl"+checkbox[i].toString())));
				
				String orderdate = request.getParameter("order_date"+checkbox[i].toString());
				if(orderdate !=null &&!orderdate.equals(""))
				{ 
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					purchase_item.setYaoqdhrq(new java.sql.Date((fmt.parse(orderdate)).getTime()));
				}
				purchase_item.setPrice(new BigDecimal(request.getParameter("price"+checkbox[i].toString())));
				purchase_item.setPrj_id(purchase.getPrj_id());
				
				purchase_item.setYfsl(0);
				purchase_item.setCzy(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
				purchase_item.setCzrq(new java.sql.Date((new java.util.Date()).getTime()));
				purchase_item.setRation_item_id(new Integer(request.getParameter("rationitemid"+checkbox[i].toString())));
				total = total + Float.parseFloat(purchase_item.getSqsl()+"")*Float.parseFloat(purchase_item.getPrice()+"");
				
				dataBaseControl.insertByBean(purchase_item);
				
				
				Map map = dataBaseControl.getOneResultSet("select * from gm_ration_item t where t.id = ?", new Object[]{request.getParameter("rationitemid"+checkbox[i].toString())});
				Gm_ration_item rationitem = new Gm_ration_item();
				BeanUtils.populate(rationitem, map);
				
				int hasnum = rationitem.getHasnum().intValue();
				hasnum = hasnum + Integer.valueOf(request.getParameter("cgsl"+checkbox[i].toString()));
				rationitem.setHasnum(new Integer(hasnum));
				dataBaseControl.updateByBean(rationitem);

			}
			
		}
		
		dataBaseControl.executeSql("update gm_purchase t set t.xiaoxje = ?,t.daxje=? where t.id = ?", new Object[]{total,NumToRmb.toHanStr(total),id});
		dataBaseControl.endTransaction();
		
		
		String state = request.getParameter("spzt");
		String num = "";
		if(state.equals("1"))
		{
			List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept=3 ", null);
			if(slist!=null&&!slist.isEmpty())
			{
				Map smap = (Map)slist.get(0);
				num = smap.get("num").toString();
			}
		}
		String infor=baseinfo.get("name")+"提出配给执行申请，,请您审批！";
		
		PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
		Mas m = (Mas) client.useService(Mas.class);
		m.sendGFDZ(num,infor);
		
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/materielpurchase/ration_itemList.jsp";
	}
	
	
	public String auditsearchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Map user = (Map)request.getSession().getAttribute("user");
		request.setAttribute("dept_id", user.get("branchid"));
		
		Map baseinfo = dataBaseControl.getOneResultSet("select t.*,m.branchname from hr_base_info t,mrbranch m where t.dept_id = m.id and t.id = ?", new Object[]{user.get("base_info_id")});
		String duty_id = baseinfo.get("duty_id").toString();
		Map map = getParameterMap(request);
		if(duty_id.equals("4"))//崔总审批
		{
			request.setAttribute("type", "finalaudit");
			
			
			String spzt = request.getParameter("spzt")==null?"2":request.getParameter("spzt");
			map.put("spzt", spzt);
			
			Page page=search4(map,pageNo,pageSize);
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
		}
		else if(duty_id.equals("3"))//总经理审批软件部
		{
			map = getParameterMap(request);
			String spzt = request.getParameter("spzt")==null?"2":request.getParameter("spzt");
			map.put("spzt", spzt);
			request.setAttribute("type", "finalaudit");
			Page page=search3(map,pageNo,pageSize);
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
		}
		else   //综合办经理审批
		{
			map = getParameterMap(request);
			String spzt = request.getParameter("spzt")==null?"1":request.getParameter("spzt");
			map.put("spzt", spzt);
			request.setAttribute("type", "audit");
			Page page=search2(request,pageNo,pageSize);
			request.setAttribute("record", page.getThisPageElements());
			request.setAttribute("page", page);
		}
		
		request.setAttribute("searchMap", map);
		
		
		request.setAttribute("editMod", "auditsearchList");
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/materielapply/gm_ration_applySelect.jsp";
		return "/materielpurchase/gm_purchase_auditList.jsp";
	}
	
	
	public String audit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
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
		
		
		
        Map map = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.id = ?", new Object[]{request.getParameter("id")});
		
		String spzt = map.get("spzt").toString();
		
		if(spzt.equals("2"))
		{
			List deptlist = dataBaseControl.getOutResultSet("select t.*,b.name from gm_purchase_audit t,hr_base_info b where t.audit_id = b.id and t.purchase_id = ? and t.state = ? order by t.id desc", new Object[]{request.getParameter("id"),spzt});
		    if(deptlist.size()!=0){
		    	request.setAttribute("dept", deptlist.get(0));
		    }
		}
		
		if(spzt.equals("3"))
		{
			List deptlist = dataBaseControl.getOutResultSet("select t.*,b.name from gm_purchase_audit t,hr_base_info b where t.audit_id = b.id and t.purchase_id = ? and t.state = ? order by t.id desc", new Object[]{request.getParameter("id"),2});
			if(deptlist.size()!=0){
		    	request.setAttribute("dept", deptlist.get(0));
		    }
		    
		    List complist = dataBaseControl.getOutResultSet("select t.*,b.name from gm_purchase_audit t,hr_base_info b where t.audit_id = b.id and t.purchase_id = ? and t.state = ? order by t.id desc", new Object[]{request.getParameter("id"),3});
		    if(complist.size()!=0){
		    	request.setAttribute("comp", complist.get(0));
		    }
		}
		
		
		List list = dataBaseControl.getOutResultSet("select t.*,m.model,m.unit,m.parameter,m.name mnate,b.name bname,(select s.name from supplier s where s.id = t.gongys)gys from gm_purchase_item t,materiel m,ma_brand b where t.materiel_id = m.id  and t.brand_id = b.id  and t.purchase_id = ?", new Object[]{request.getParameter("id")});
		request.setAttribute("mlist", list);
		
		//获得审批意见
		List splist = dataBaseControl.getOutResultSet("select pa.*, (select b.name from hr_base_info b where b.id = pa.audit_id) person from gm_purchase p, gm_purchase_audit pa where p.id = pa.purchase_id and p.id = ? order by pa.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		return "/materielpurchase/gm_purchaseDtl.jsp";
	}
	
	
	public String save(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.id = ?", new Object[]{request.getParameter("id")});
		Map user = (Map)request.getSession().getAttribute("user");
		Gm_purchase purchase = new Gm_purchase();
		BeanUtils.populate(purchase, map);
		
		String state = request.getParameter("opinionid");
		
		purchase.setSpzt(request.getParameter("opinionid"));
		purchase.setSpyj(request.getParameter("opinion"));
		purchase.setSpr(new Integer(Integer.valueOf(user.get("base_info_id").toString())));
		purchase.setSprq(new java.sql.Date((new java.util.Date()).getTime()));
		
		dataBaseControl.updateByBean(purchase);
		
		Gm_purchase_audit purchase_audit = new Gm_purchase_audit();
		purchase_audit.setAudit_date(new java.sql.Timestamp((new java.util.Date()).getTime()));
		purchase_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
		purchase_audit.setOpinion(request.getParameter("opinion"));
		purchase_audit.setState(request.getParameter("opinionid"));
		purchase_audit.setPurchase_id(new BigDecimal(request.getParameter("id")));
		dataBaseControl.insertByBean(purchase_audit);
		
		Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{purchase.getP_id()});
        
		String deptid = baseinfo.get("dept_id").toString();
		String num = "";
		String infor = "";
		if(state.equals("2"))
		{
			if(deptid.equals("4"))
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
			
			infor = "您有一个条配给执行申请待审批";
			
		}
		
		else if(state.equals("6"))
		{
			infor = "您的配给执行申请被打回";
			Map zxbaseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{purchase.getCaigjsr()});
			num = zxbaseinfo.get("phone")+"";
		}
		
		else if(state.equals("3"))
		{
			infor = "您的配给执行申请部门经理审批不通过";
			Map zxbaseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{purchase.getCaigjsr()});
			num = zxbaseinfo.get("phone")+"";
			
			//退回配给申请
			List itemlist = dataBaseControl.getOutResultSet("select * from gm_purchase_item t where t.purchase_id = ? order by t.id", new Object[]{request.getParameter("id")});
			
			for(int i = 0 ; i < itemlist.size();i++)
			{
				Map tmap = (Map)itemlist.get(i);
				Map rationmap = dataBaseControl.getOneResultSet("select * from gm_ration_item t where id = ?", new Object[]{tmap.get("ration_item_id")});
				Gm_ration_item ration_item = new Gm_ration_item();
				BeanUtils.populate(ration_item, rationmap);
				String sqsl1 = tmap.get("sqsl").toString();//获得已提交配给执行的采购数量
				String sqsl2 = rationmap.get("hasnum").toString();//获得针对申请者提出已配给执行的数量
				
				int sjsl = Integer.parseInt(sqsl2)-Integer.parseInt(sqsl1);
				ration_item.setHasnum(sjsl);
				dataBaseControl.updateByBean(ration_item);
			}
		}
		
		else if(state.equals("5"))
		{
			infor = "您的配给执行审批通过";
			Map zxbaseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{purchase.getCaigjsr()});
			num = zxbaseinfo.get("phone")+"";
		}
		else
		{
			//退回配给申请
			List itemlist = dataBaseControl.getOutResultSet("select * from gm_purchase_item t where t.purchase_id = ? order by t.id", new Object[]{request.getParameter("id")});
			
			for(int i = 0 ; i < itemlist.size();i++)
			{
				Map tmap = (Map)itemlist.get(i);
				Map rationmap = dataBaseControl.getOneResultSet("select * from gm_ration_item t where id = ?", new Object[]{tmap.get("ration_item_id")});
				Gm_ration_item ration_item = new Gm_ration_item();
				BeanUtils.populate(ration_item, rationmap);
				String sqsl1 = tmap.get("sqsl").toString();//获得已提交配给执行的采购数量
				String sqsl2 = rationmap.get("hasnum").toString();//获得针对申请者提出已配给执行的数量
				
				int sjsl = Integer.parseInt(sqsl2)-Integer.parseInt(sqsl1);
				ration_item.setHasnum(sjsl);
				dataBaseControl.updateByBean(ration_item);
			}
			
			infor = "您的配给执行公司领导审批不通过";
			Map zxbaseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{purchase.getCaigjsr()});
			num = zxbaseinfo.get("phone")+"";
		}
		
		PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
		Mas m = (Mas) client.useService(Mas.class);
		m.sendGFDZ(num,infor);
		
		
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/materielpurchase/gm_purchaseDtl.jsp";
	}
	
	
	
	public String modify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		if(page.getTotalNumberOfElements()>0)
		{
		   request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		}
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		request.setAttribute("type", "modify");
		
		String flag = request.getParameter("flag")==null?"0":request.getParameter("flag");
		if(flag.equals("1"))
		{
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		
		List list = dataBaseControl.getOutResultSet("select t.*,m.model,m.parameter,m.name mnate,b.name bname,s.name gys from gm_purchase_item t,materiel m,ma_brand b,supplier s where t.materiel_id = m.id  and t.brand_id = b.id and t.gongys = s.id(+)  and t.purchase_id = ? order by t.id ", new Object[]{request.getParameter("id")});
		request.setAttribute("mlist", list);
		return "/materielpurchase/gm_purchaseModifyDtl.jsp";
	}
	
	
	/*
	 * 配给变更
	 */
	public String modify1(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.id = ?", new Object[]{request.getParameter("id")});
		
		String spzt = map.get("spzt").toString();
		
		if(spzt.equals("2"))
		{
			List deptlist = dataBaseControl.getOutResultSet("select t.*,b.name from gm_purchase_audit t,hr_base_info b where t.audit_id = b.id and t.purchase_id = ? and t.state = ? order by t.id desc", new Object[]{request.getParameter("id"),spzt});
		    request.setAttribute("dept", deptlist.get(0));
		}
		
		if(spzt.equals("3"))
		{
			List deptlist = dataBaseControl.getOutResultSet("select t.*,b.name from gm_purchase_audit t,hr_base_info b where t.audit_id = b.id and t.purchase_id = ? and t.state = ? order by t.id desc", new Object[]{request.getParameter("id"),2});
		    request.setAttribute("dept", deptlist.get(0));
		    
		    List complist = dataBaseControl.getOutResultSet("select t.*,b.name from gm_purchase_audit t,hr_base_info b where t.audit_id = b.id and t.purchase_id = ? and t.state = ? order by t.id desc", new Object[]{request.getParameter("id"),3});
		    request.setAttribute("comp", complist.get(0));
		}
		
		List list = dataBaseControl.getOutResultSet("select t.*,m.name mnate,b.name bname,(select p.name from supplier p where p.id = t.gongys)gys from gm_purchase_item t,materiel m,ma_brand b where t.materiel_id = m.id  and t.brand_id = b.id  and t.purchase_id = ? order by t.id", new Object[]{request.getParameter("id")});
		request.setAttribute("mlist", list);
		return "/materielpurchasemodify/gm_purchaseDtl.jsp";
	}
	
	/*
	 * 提交变更申请
	 */
	public String sub1(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
		
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.id = ?", new Object[]{request.getParameter("id")});
		
		
		Gm_purchase purchase = new Gm_purchase();
		BeanUtils.populate(purchase, map);
		
		List tlist = dataBaseControl.getOutResultSet("select * from Gm_purchase_modify t where t.modify_ration_id like ?", new Object[]{"%"+purchase.getRation_apply_id()+"%"});
		
		int ordered = tlist.size()+1;
		
		Gm_purchase_modify purchase_modify = new Gm_purchase_modify();
		purchase_modify.setPrj_id(purchase.getPrj_id());
		purchase_modify.setRation_apply_id(purchase.getRation_apply_id());
		purchase_modify.setP_id(new Integer(user.get("base_info_id").toString()));
		purchase_modify.setNote(request.getParameter("note"));
		purchase_modify.setXiaoxje(purchase.getXiaoxje());
		purchase_modify.setModify_date(new java.sql.Date((new java.util.Date()).getTime()));
		purchase_modify.setModify_ration_id(purchase.getRation_apply_id()+"/变更"+ordered);
		purchase_modify.setSpzt("1");
		
		Long id = dataBaseControl.getSeq(Gm_purchase_modify.class);
		
		dataBaseControl.beginTransaction();
		
		
		boolean flag = false;
		
		
		Object[] checkbox = request.getParameterValues("checkbox");
		float total = 0;
		
		if(checkbox.length!=0)
		{
			
			for(int i = 0 ; i < checkbox.length;i++)
			{
				Map map1 = dataBaseControl.getOneResultSet("select r.* from gm_purchase t ,gm_purchase_item r where t.id = r.purchase_id and r.purchase_id = ? and r.materiel_id = ?", new Object[]{request.getParameter("id"),checkbox[i].toString()});
				
				Gm_purchase_item_mo purchase_item_mo = new Gm_purchase_item_mo();
				BeanUtils.populate(purchase_item_mo, map1);
				String bgsl = request.getParameter("bgsl"+checkbox[i].toString());
				
				int modifynum = Integer.valueOf(bgsl) - Integer.valueOf(map1.get("sqsl").toString()); 
				
				
				String bgprice = request.getParameter("bgprice"+checkbox[i].toString());
				String bggys = request.getParameter("bggys"+checkbox[i].toString());

				purchase_item_mo.setModify_id(id);
				purchase_item_mo.setSqsl1(new BigDecimal(request.getParameter("bgsl"+checkbox[i].toString())));
				purchase_item_mo.setPrice1(new BigDecimal(request.getParameter("bgprice"+checkbox[i].toString())));
				purchase_item_mo.setGongys1(new BigDecimal(request.getParameter("bggys"+checkbox[i].toString())));
				
				if(bgsl.equals(purchase_item_mo.getSqsl()+"")&&bgprice.equals(purchase_item_mo.getPrice()+"")&&bggys.equals(purchase_item_mo.getGongys()+""))
				{
					
				}
				else
				{
					dataBaseControl.insertByBean(purchase_item_mo);
					flag = true;
				}
				
					
			}
			
		}
		
		if(flag)
		{
			dataBaseControl.insertByBean(purchase_modify, id);
		}
		
		dataBaseControl.endTransaction();
		
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/materielpurchase/gm_purchaseDtl.jsp";
	}
	
	public String subapply(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.id = ?", new Object[]{request.getParameter("id")});
		Gm_purchase purchase = new Gm_purchase();
		BeanUtils.populate(purchase, map);
		
        String[]zi_liaos=request.getParameterValues("fujian");
		
		String fujian = ""; 
	    if(zi_liaos!=null&&zi_liaos.length>0)
	    {
	    	for(int i = 0 ; i < zi_liaos.length;i++)
			{
				String str = zi_liaos[i];
				fujian = fujian + ";" + str;
				
			}
	    }
		
	    purchase.setPath(fujian);
		
		purchase.setSpzt(request.getParameter("spzt"));
		dataBaseControl.beginTransaction();
		float total = 0;
		
		List list = dataBaseControl.getOutResultSet("select t.* from gm_purchase_item t where t.purchase_id = ? order by t.id ", new Object[]{request.getParameter("id")});
		
		for(int i = 0 ; i < list.size(); i++)
		{
			Map tmap = (Map)list.toArray()[i];
			Gm_purchase_item purchase_item = new Gm_purchase_item();
			BeanUtils.populate(purchase_item, tmap);
			String sqsl = request.getParameter("sqsl"+purchase_item.getMateriel_id());
			
			if(sqsl == null || sqsl.equals(""))
            {
            	this.doResult(response, "申请数量不能为空");
            	return null;
            }
			
            int modifynum = Integer.valueOf(sqsl) - purchase_item.getSqsl();

            purchase_item.setSqsl(new Integer(Integer.parseInt(sqsl)));
            
            String price = request.getParameter("price"+purchase_item.getMateriel_id());
            
            if(price == null || price.equals(""))
            {
            	this.doResult(response, "单价不能为空");
            	return null;
            }
            
            String gongys = request.getParameter("gongys"+purchase_item.getMateriel_id());
            
            if(gongys == null || gongys.equals(""))
            {
            	this.doResult(response, "供应商不能为空");
            	return null;
            }
            
            
            purchase_item.setGongys(new Integer(request.getParameter("gongys"+purchase_item.getMateriel_id())));
            purchase_item.setPrice(new BigDecimal(request.getParameter("price"+purchase_item.getMateriel_id())));
            
            total = total + Float.parseFloat(purchase_item.getSqsl()+"")*Float.parseFloat(purchase_item.getPrice()+"");
			
            
			dataBaseControl.updateByBean(purchase_item);	
			
			Map raitemmap = dataBaseControl.getOneResultSet("select * from gm_ration_item t where t.id = ?", new Object[]{purchase_item.getRation_item_id()});
			Gm_ration_item ratiem = new Gm_ration_item();
			BeanUtils.populate(ratiem, raitemmap);
			int hasnum = ratiem.getHasnum().intValue();
			hasnum = hasnum + modifynum;   
			ratiem.setHasnum(new Integer(hasnum));
			dataBaseControl.updateByBean(ratiem);	
		}
		purchase.setXiaoxje(new BigDecimal(total));
		purchase.setDaxje(NumToRmb.toHanStr(total));
		
		dataBaseControl.updateByBean(purchase);
		
		dataBaseControl.endTransaction();
		request.setAttribute("operationSign", "closeDG_refreshW();");
		return "/materielapply/gm_ration_applyModifyDtl.jsp";
	}
	
	
	/*
	 * 提交
	 */
	public String lastsub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Map map = dataBaseControl.getOneResultSet("select * from gm_purchase t where t.id = ?", new Object[]{request.getParameter("id")});
		

		Gm_purchase purchase = new Gm_purchase();
		BeanUtils.populate(purchase, map);
		purchase.setSpzt("1");
		dataBaseControl.updateByBean(purchase);

		return "/materielpurchase/Gm_purchase!searchList.do";
	}
	
	
	
	/*
	 * 配给执行统计
	 */
	public String tongji(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
			? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		
		Page page=hissearch(request,pageNo,pageSize);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		
		request.setAttribute("searchMap", getParameterMap(request));
		return "/materielpurchase/gm_purchase_tongjList.jsp";
	}
	/////////////////////////////
	public String tongjiDemo (HttpServletRequest request,HttpServletResponse response) throws Exception  
 	{
 		
		String sql="select m.name maname,p.name  proname,r.ration_apply_id,b.name brand,t.sqsl,t.yaoqdhrq,t.price,(t.sqsl*t.price)tprice,r.sprq,s.name  gys from gm_purchase_item t, gm_purchase r, materiel m,pr_project p,ma_brand b,supplier s where t.purchase_id = r.id and t.materiel_id = m.id and p.id(+) = r.prj_id and b.id = t.brand_id and s.id = t.gongys  /~ and t.id={id} ~/"
				+ "/~ and p.name like '%[proname]%' ~/ "
				+ "/~ and s.name like '%[gong]%' ~/ "
				+ "/~ and m.name like '%[maname]%' ~/ "
				+ "/~ and b.name like '%[brandname]%' ~/ "
				+ "/~ and r.ration_apply_id like '%[ration_apply_id]%' ~/ "
				+ "/~ and m.model like '%[model]%' ~/ "
				+ "/~ and t.purchase_id={purchase_id} ~/ "
				+ "/~ and r.sprq>=to_date({app_date1},'yyyy-MM-dd')  ~/ "
				+ "/~ and r.sprq<=to_date({app_date2},'yyyy-MM-dd')   ~/ ";
 		defaultList(request,response,new StringBuffer(sql),"r.id desc"); 
 		return null;
 	}
	 public void total(HttpServletRequest request,HttpServletResponse response) throws Exception {
			String sql="select sum(t.sqsl*t.price) sum,count(*) count  " +
					"from gm_purchase_item t, gm_purchase r, materiel m,pr_project p,ma_brand b,supplier s where t.purchase_id = r.id and t.materiel_id = m.id and p.id(+) = r.prj_id and b.id = t.brand_id and s.id = t.gongys  /~ and t.id={id} ~/ "+
					"/~ and p.name like '%[proname]%' ~/ "
					+ "/~ and s.name like '%[gong]%' ~/ "
					+ "/~ and m.name like '%[maname]%' ~/ "
					+ "/~ and b.name like '%[brandname]%' ~/ "
					+ "/~ and r.ration_apply_id like '%[ration_apply_id]%' ~/ "
					+ "/~ and m.model like '%[model]%' ~/ "
					+ "/~ and t.purchase_id={purchase_id} ~/ "
					+ "/~ and r.sprq>=to_date({app_date1},'yyyy-MM-dd')  ~/ "
					+ "/~ and r.sprq<=to_date({app_date2},'yyyy-MM-dd')   ~/ ";
				
				XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
				Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
				Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
				
				String jsonStr = JSON.toJSONString(map);
				setAjaxInfo(response,jsonStr);
			
			}
	///////////////////////////////
	
	
	private Page hissearch(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select m.name maname,p.name  proname,r.ration_apply_id,b.name brand,t.sqsl,t.yaoqdhrq,t.price,s.name  gys from gm_purchase_item t, gm_purchase r, materiel m,pr_project p,ma_brand b,supplier s where t.purchase_id = r.id and t.materiel_id = m.id and p.id(+) = r.prj_id and b.id = t.brand_id and s.id = t.gongys  /~ and t.id={id} ~/"
		+ "/~ and p.name like '%[proname]%' ~/ "
		+ "/~ and s.name like '%[gong]%' ~/ "
		
		+ "/~ and m.name like '%[maname]%' ~/ "
		+ "/~ and b.name like '%[brandname]%' ~/ "
		+ "/~ and r.ration_apply_id like '%[ration_apply_id]%' ~/ "
		+ "/~ and m.model like '%[model]%' ~/ "
		+ "/~ and t.purchase_id={purchase_id} ~/ ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("purchase_state",codeTableUtil.getCodeMap("purchase_state")); 
	}

}