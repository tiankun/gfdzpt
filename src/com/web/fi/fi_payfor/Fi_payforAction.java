package com.web.fi.fi_payfor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.phprpc.PHPRPC_Client;

import com.alibaba.fastjson.JSON;
import com.map.Fi_financial_account;
import com.map.Fi_payfor;
import com.map.Fi_payfor_audit;
import com.map.Fi_payfor_item;
import com.map.Gm_materiel_inout;
import com.map.Gm_purchase;
import com.map.Gm_purchase_item;
import com.map.Gm_purchase_item_mo;
import com.map.Gm_purchase_modify;
import com.map.sysfuncdic;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;
import com.sysFrams.web.NumToRmb;
import com.zsc.Mas;

public class Fi_payforAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	private static int sk_id;
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
			return "/fi/fi_payfor/fi_payforSelect.jsp";
		return "/fi/payfor/fi_payforList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search1(request,1,1);
		
		request.setAttribute("look", "1");
		Map map1 = dataBaseControl.getOneResultSet("select t.* from fi_payfor t where t.id = ?", new Object[]{request.getParameter("id")});
		String type = ""+map1.get("type");
		request.setAttribute("accid", map1.get("id"));
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		String forward = "";
		
		if(!type.equals("1"))  //与材料相关
		{
			List list = dataBaseControl.getOutResultSet("select t.*,c.prj_id,(select p.name from pr_project p where p.id=c.prj_id)pname,r.ration_apply_id,(select m.name from materiel m where m.id = t.materiel_id) maname,(select s.name from supplier s where s.id = t.gongys) gys from fi_payfor_item t,gm_purchase r,gm_purchase_item c where t.f_id = ? and t.purchase_item_id = c.id and c.purchase_id = r.id order by t.id ", new Object[]{request.getParameter("id")});
			request.setAttribute("reslist", list);
			
			
			forward = "/fi/payfor/fi_payforpayDtl.jsp";
		}
		else   //与材料无关
		{
			Map map2 = dataBaseControl.getOneResultSet("select t.*,s.name,s.id sid," +
					"(select b.name from hr_base_info b where t.p_id = b.id)pname " +
					"from fi_payfor t,supplier s where t.id = ? and t.note = s.id", new Object[]{request.getParameter("id")});
			request.setAttribute("info", map2);
			
			List slist = dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? order by t.id", new Object[]{map2.get("id")});
			request.setAttribute("slist", slist);
			forward = "/fi/payfor/nlfi_payforDtl.jsp";
			
		}
		
		List splist = dataBaseControl.getOutResultSet("select t.*," +
				"(select b.name from hr_base_info b where b.id = t.audit_id)pname " +
				"from fi_payfor_audit t where t.apply_id = ? order by t.id ",new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		buildDDL(request);
		return forward;
	}
	
	//按恭元伟代码继续编码，实属无奈...
	//打印---仅与材料无关部分
	public String print(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String idString = request.getParameter("id");
		String IsPostBack = request.getParameter("IsPostBack");
		request.setAttribute("IsPostBack", "1");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int count = Integer.parseInt((dataBaseControl.getOneResultSet("select print from fi_payfor where id=?", new Object[]{idString})).get("print").toString());
			count++;
			dataBaseControl.executeSql("update fi_payfor set print=? where id=?", new Object[]{count,idString});
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		//获取类型，是否与材料有关
		Map typeMap = dataBaseControl.getOneResultSet("select t.type from fi_payfor t where t.id = ?", new Object[]{idString});
		String type = null;
		if(typeMap.size()!=0){
			type= ""+typeMap.get("type");
		}
		Map mainMap = null;
		List itemlist = null;
		if(type.equals("1")){
			mainMap = dataBaseControl.getOneResultSet("select t.*,s.name,s.id sid," +
					"(select mb.branchname from mrbranch mb where mb.id=t.dept_id) dept_name," +
					"(select b.name from hr_base_info b where t.p_id = b.id) pname " +
					"from fi_payfor t,supplier s where t.id = ? and t.note = s.id", new Object[]{idString});
			//明细清单
			itemlist = dataBaseControl.getOutResultSet("select t.applyreason,t.fujnum,t.money from fi_payfor_item t where t.f_id = ? order by t.id", new Object[]{idString});
		}else{
			List mainList = dataBaseControl.getTopResultSet("select t.*," +
					"(select s.name from supplier s where s.id = gi.gongys) name," +
					"(select mb.branchname from mrbranch mb where mb.id=t.dept_id) dept_name, " +
					"(select b.name from hr_base_info b where t.p_id = b.id) pname  " +
					"from fi_payfor t,fi_payfor_item pi,gm_purchase_item gi where t.id = ? " +
					"and t.id = pi.f_id and pi.purchase_item_id = gi.id",  new Object[]{idString} ,1);
			if(mainList.size()!=0){
				mainMap=(Map) mainList.get(0);
			}
			itemlist = dataBaseControl.getOutResultSet("select " +
					"(select m.name from materiel m where m.id = t.materiel_id) applyreason," +
					"t.fujnum,t.money from fi_payfor_item t where t.f_id = ? order by t.id", new Object[]{idString});
		}
		//主表map
		request.setAttribute("main", mainMap);
		request.setAttribute("itemlist", itemlist);
			
		//审批意见
		//部门经理
		List dmList = dataBaseControl.getTopResultSet("select hb.name,t.opinion,t.audit_date from fi_payfor_audit t,hr_base_info hb,fi_payfor fp where fp.id=t.apply_id(+) and t.audit_id = hb.id(+) and hb.dept_id = fp.dept_id and fp.id=? order by t.id desc", new Object[]{idString}, 1);
		//会计
		List cpaList = dataBaseControl.getTopResultSet("select hb.name,t.opinion,t.audit_date from fi_payfor_audit t,hr_base_info hb,fi_payfor fp where fp.id=t.apply_id(+) and t.audit_id=hb.id(+) and hb.dept_id=6 and hb.duty_id=1 and t.apply_id=? order by t.id desc", new Object[]{idString}, 1);
		//财务经理
		List fmList = dataBaseControl.getTopResultSet("select hb.name,t.opinion,t.audit_date from fi_payfor_audit t,hr_base_info hb,fi_payfor fp where fp.id=t.apply_id(+) and t.audit_id=hb.id(+) and hb.dept_id=6 and hb.duty_id=2 and t.apply_id=? order by t.id desc", new Object[]{idString}, 1);
		//公司领导
		List gmList = dataBaseControl.getTopResultSet("select hb.name,t.opinion,t.audit_date from fi_payfor_audit t,hr_base_info hb where t.audit_id=hb.id(+) and hb.duty_id!=1 and hb.duty_id!=2 and t.apply_id=? order by t.id desc", new Object[]{idString}, 1);
		
		Map dmMap = null;
		Map cpaMap = null;
		Map fmMap = null;
		Map gmMap = null;
		if(dmList.size()!=0){
			dmMap = (Map) dmList.get(0);
		}
		if(cpaList.size()!=0){
			cpaMap = (Map) cpaList.get(0);
		}
		if(fmList.size()!=0){
			fmMap = (Map) fmList.get(0);
		}
		if(gmList.size()!=0){
			gmMap = (Map) gmList.get(0);
		}

		request.setAttribute("dmMap", dmMap);
		request.setAttribute("fmMap", fmMap);
		request.setAttribute("gmMap", gmMap);
		request.setAttribute("cpaMap", cpaMap);
		
		buildDDL(request);
		
		return "/fi/payfor/print.jsp";
	}
	
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select io.id ioid,t.ration_apply_id,(r.rksl - r.yfsl) kzf," +
				"r.id,r.purchase_id,r.brand_id,r.materiel_id,r.sqsl,r.cksl,r.yaoqdhrq,r.price,r.gongys,r.customer_id,r.rksl,r.yfsl,r.fujmc,r.ds_mf_status,r.czy,r.czrq,r.note,r.ration_item_id,r.confirmsql,r.state,r.reason," +
				"s.name gys,m.name maname,io.price ioprice,io.money iomoney," +
				"io.num ionum,p.name pname,t.prj_id " +
				"from gm_purchase t, gm_purchase_item r, gm_materiel_inout io,supplier s,materiel m,pr_project p " +
				"where t.id = r.purchase_id and r.id = io.purchase_item_id and io.type = '入库' and io.ispay = '0' and s.id = r.gongys and m.id = r.materiel_id and p.id = t.prj_id " 
			+ "/~ and p.name like '%[proname]%' ~/ " 
			+ "/~ and m.name like '%[maname]%' ~/ "
			+ "/~ and s.name like '%[gong]%' ~/ "
			+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
			+ "/~ and r.materiel_id={materiel_id} ~/ ";
				
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	/*
	 * 跳转到新增界面
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String ids = request.getParameter("ids");
		
		String []id = ids.split(";");
		
        
        List reslist = new ArrayList();
        
        float money = 0;
       
        String sk=null;
		if(id.length!=0)
		{
			
			Map user = (Map)request.getSession().getAttribute("user");
			for(int i = 0 ; i < id.length;i++)
			{
				Map map = dataBaseControl.getOneResultSet("select t.ration_apply_id,t.prj_id prjid,p.name pname,p.id,(r.rksl - r.yfsl) kzf,(select s.name from supplier s where s.id = r.gongys) gys,(select s.id from supplier s where s.id = r.gongys) sk_id,r.*,(select m.name from materiel m where m.id = r.materiel_id) maname,io.price ioprice,io.money iomoney,io.num ionum,io.id ioid from gm_purchase t, gm_purchase_item r,gm_materiel_inout io,pr_project p where t.id = r.purchase_id and r.id = io.purchase_item_id and p.id=t.prj_id and io.id = ? ", new Object[]{id[i]});
				money = money + Float.parseFloat(map.get("iomoney")==null?"0":map.get("iomoney").toString());
				sk_id=Integer.parseInt(map.get("sk_id").toString());
				sk=map.get("gys").toString();
				reslist.add(map);
				
			}
			
			
		}
		buildDDL(request);
		request.setAttribute("sk", sk);
		request.setAttribute("money", money);
		request.setAttribute("record", reslist);
		return "/fi/payfor/fi_payforDtl.jsp";
	}
	
	/*
	 * 保存新增款项支付
	 */
	public String save(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
	    Object[] checkbox = request.getParameterValues("checkbox");
	    String[] danju = request.getParameterValues("danju");
	    String  mapro=null;
		if(checkbox.length!=0)
		{
			Long id = dataBaseControl.getSeq(Fi_payfor.class);
			dataBaseControl.beginTransaction();
			for(int i = 0 ; i < checkbox.length;i++)
			{
				float money = 0;
				
				Map iomap = (Map)dataBaseControl.getOneResultSet("select * from gm_materiel_inout t where t.id = ?", new Object[]{checkbox[i]});
				Gm_materiel_inout inout = new Gm_materiel_inout();
				BeanUtils.populate(inout, iomap);
				
				if(request.getParameter("opinionid").equals("1"))
				{
					inout.setIspay("1");
					dataBaseControl.updateByBean(inout);
				}
				
				
				Map map = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{iomap.get("purchase_item_id")});
				Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
				Gm_purchase_item purchase_item = new Gm_purchase_item();
				BeanUtils.populate(purchase_item, map);
				mapro=map.get("prj_id").toString();
				fi_payfor_item.setInout_id(new Integer(checkbox[i].toString()));
				fi_payfor_item.setPro_id(Integer.parseInt(map.get("prj_id").toString()));
				fi_payfor_item.setPurchase_item_id(new Integer(purchase_item.getId()+""));
				fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
				fi_payfor_item.setMateriel_id(purchase_item.getMateriel_id());
				fi_payfor_item.setNum(new Integer(iomap.get("num").toString()));
				fi_payfor_item.setPrice(new BigDecimal(iomap.get("price").toString()));
				
				money =Float.valueOf(fi_payfor_item.getNum())*Float.valueOf(fi_payfor_item.getPrice().toString());
				  
				fi_payfor_item.setMoney(new BigDecimal(money));
				fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
				fi_payfor_item.setGongys(purchase_item.getGongys());
				fi_payfor_item.setF_id(new Integer(id+""));
				fi_payfor_item.setFujnum(danju[i]);
				
				dataBaseControl.insertByBean(fi_payfor_item);
				
				int hasnum = purchase_item.getYfsl();
				hasnum = hasnum + new Integer(iomap.get("num").toString());
				purchase_item.setYfsl(hasnum);
				dataBaseControl.updateByBean(purchase_item);
			}
			
			Fi_payfor fi_payfor = new Fi_payfor();
			fi_payfor.setP_id(new BigDecimal(user.get("base_info_id").toString()));
			fi_payfor.setDept_id(new BigDecimal(user.get("branchid").toString()));
			fi_payfor.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
			fi_payfor.setPayforstate(request.getParameter("opinionid"));
			fi_payfor.setMoney(new BigDecimal(request.getParameter("editTotal")));
			fi_payfor.setPaytype(request.getParameter("paytype"));
			fi_payfor.setNote(request.getParameter("note"));
			fi_payfor.setBmoney(NumToRmb.toHanStr(Double.parseDouble(request.getParameter("editTotal"))));
			fi_payfor.setSk_id(new BigDecimal(sk_id));
			fi_payfor.setPro_id(Integer.parseInt(mapro));
			dataBaseControl.insertByBean(fi_payfor, id);
			dataBaseControl.endTransaction();
			
			String num = "";
			String infor = "您有一条款项支付申请待审批";
			List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 3 ", null);
			if(slist!=null&&!slist.isEmpty())
			{
				Map smap = (Map)slist.get(0);
				num = smap.get("num").toString();
			}
			
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			m.sendGFDZ(num,infor);
			
			request.setAttribute("operationSign", "closeDG_refreshW();");
			
		}else{
			Long id = dataBaseControl.getSeq(Fi_payfor.class);
			request.setAttribute("accid", id);
			
			
		}
		
		return "/fi/payfor/fi_payforDtl.jsp";
	}
	
	
	
	public String paysearchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		? "1" : request.getParameter("pageno");
        int pageNo=(new Integer(page_no)).intValue();
        int pageSize=20;
        
        

        Page page=search1(request,pageNo,pageSize);
        request.setAttribute("searchmap", getParameterMap(request));

        request.setAttribute("record", page.getThisPageElements());
        request.setAttribute("page", page);
        buildDDL(request);
		
		return "/fi/payfor/fi_payforpayList.jsp";
	}
	
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		
		String sql="select t.*,m.branchname,b.name,(select p.name from pr_project p where p.id=t.pro_id)pname from fi_payfor t,mrbranch  m,hr_base_info b where 1 = 1 and t.p_id = b.id and t.dept_id = m.id " 
		+ "/~ and t.money={money} ~/ "
		+ "/~ and t.id={id} ~/ "
		+ "/~ and t.p_id={p_id} ~/ "
		+ "/~ and t.payforstate={payforstate} ~/ "
		+ "/~ and t.odate>=to_date({begin_date},'yyyy-MM-dd')  ~/ "
		+ "/~ and t.odate<=to_date({end_date},'yyyy-MM-dd')   ~/ "
		+ " order by t.id desc ";
				
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	/*
	 * 修改时删除子项
	 */
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map map = dataBaseControl.getOneResultSet("select * from fi_payfor_item t where t.id = ? ", new Object[]{request.getParameter("id")});
        Map payformap = dataBaseControl.getOneResultSet("select t.* from fi_payfor t where t.id = ?", new Object[]{map.get("f_id")});
        
        String type = ""+payformap.get("type");
        
        if(!type.equals("1"))
        {
        	Map map1 = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{map.get("purchase_item_id")});

            Gm_purchase_item gm_purchase_item =new Gm_purchase_item();

            BeanUtils.populate(gm_purchase_item, map1);
            
            int hasnum = gm_purchase_item.getYfsl() - Integer.parseInt(map.get("num").toString());

            gm_purchase_item.setYfsl(hasnum);
            
            dataBaseControl.updateByBean(gm_purchase_item);
            
            Map map2 = dataBaseControl.getOneResultSet("select * from gm_materiel_inout t where t.id = ?", new Object[]{map.get("inout_id")});
            Gm_materiel_inout gm_materiel_inout = new Gm_materiel_inout();
            BeanUtils.populate(gm_materiel_inout, map2);
            gm_materiel_inout.setIspay("0");
            dataBaseControl.updateByBean(gm_materiel_inout);
        }
        
        
		dataBaseControl.executeSql("DELETE FROM fi_payfor_item WHERE ID in (?)", new Object[]{request.getParameter("id")});
		
		List list= dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? ", new Object[]{map.get("f_id")});
		String flag = "0";
		
		
		if(list == null || list.isEmpty())
		{
			dataBaseControl.executeSql("DELETE FROM fi_payfor WHERE ID in (?)", new Object[]{map.get("f_id")});
			flag = "1";
		}
		else
		{
			
		}
		
		
		return "/fi/fi_payfor/Fi_payfor!edit.do?id="+map.get("f_id")+"&flag="+flag+"&type="+request.getParameter("type");
	
	}
	
	/*
	 * 修改款项支付
	 */
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String forward = "";
		request.setAttribute("type", request.getParameter("type"));
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			
			dataBaseControl.beginTransaction();
			
			List slist = dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? order by t.id ", new Object[]{id});
			double total = 0;
			for(int i = 0 ; i < slist.size();i++)
			{
				Map map = (Map)slist.get(i);
				Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
				BeanUtils.populate(fi_payfor_item, map);
				
				fi_payfor_item.setApplyreason(request.getParameter("rea"+fi_payfor_item.getId()));
				fi_payfor_item.setMoney(new BigDecimal(request.getParameter("money"+fi_payfor_item.getId())));
				fi_payfor_item.setFujnum(request.getParameter("fuj"+fi_payfor_item.getId()));
				total = total + Double.valueOf(request.getParameter("money"+fi_payfor_item.getId()));
				dataBaseControl.updateByBean(fi_payfor_item);
				
			}
			
			Map user = (Map)request.getSession().getAttribute("user");
			
			String ck1 = request.getParameter("ck1")==null?"0":request.getParameter("ck1");
			String ck2 = request.getParameter("ck2")==null?"0":request.getParameter("ck2");
			String ck3 = request.getParameter("ck3")==null?"0":request.getParameter("ck3");
			String ck4 = request.getParameter("ck4")==null?"0":request.getParameter("ck4");
			String ck5 = request.getParameter("ck5")==null?"0":request.getParameter("ck5");
			
			if(ck1.equals("1"))
			{
				Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
				fi_payfor_item.setApplyreason(request.getParameter("rea1"));
				fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money1")==null||request.getParameter("money1").equals(""))?"0":request.getParameter("money1")));
				fi_payfor_item.setFujnum(request.getParameter("fuj1"));
				fi_payfor_item.setF_id(new Integer(id+""));
				fi_payfor_item.setPurchase_item_id(new Integer(0));
				fi_payfor_item.setMateriel_id(0);
				fi_payfor_item.setNum(new Integer(0));
				fi_payfor_item.setPrice(new BigDecimal(0));
				fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
				fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
				total = total + Double.valueOf((request.getParameter("money1")==null||request.getParameter("money1").equals(""))?"0":request.getParameter("money1"));
				fi_payfor_item.setGongys(0);
				fi_payfor_item.setInout_id(0);
				dataBaseControl.insertByBean(fi_payfor_item);
				
			}
			
			if(ck2.equals("1"))
			{
				Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
				fi_payfor_item.setApplyreason(request.getParameter("rea2"));
				fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money2")==null||request.getParameter("money2").equals(""))?"0":request.getParameter("money2")));
				fi_payfor_item.setFujnum(request.getParameter("fuj2"));
				fi_payfor_item.setF_id(new Integer(id+""));
				fi_payfor_item.setPurchase_item_id(new Integer(0));
				fi_payfor_item.setMateriel_id(0);
				fi_payfor_item.setNum(new Integer(0));
				fi_payfor_item.setPrice(new BigDecimal(0));
				fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
				fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
				total = total + Double.valueOf((request.getParameter("money2")==null||request.getParameter("money2").equals(""))?"0":request.getParameter("money2"));
				fi_payfor_item.setGongys(0);
				fi_payfor_item.setInout_id(0);
				dataBaseControl.insertByBean(fi_payfor_item);
				
			}
			
			if(ck3.equals("1"))
			{
				Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
				fi_payfor_item.setApplyreason(request.getParameter("rea3"));
				fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money3")==null||request.getParameter("money3").equals(""))?"0":request.getParameter("money3")));
				fi_payfor_item.setFujnum(request.getParameter("fuj3"));
				fi_payfor_item.setF_id(new Integer(id+""));
				fi_payfor_item.setPurchase_item_id(new Integer(0));
				fi_payfor_item.setMateriel_id(0);
				fi_payfor_item.setNum(new Integer(0));
				fi_payfor_item.setPrice(new BigDecimal(0));
				fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
				fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
				total = total + Double.valueOf((request.getParameter("money3")==null||request.getParameter("money3").equals(""))?"0":request.getParameter("money3"));
				fi_payfor_item.setGongys(0);
				fi_payfor_item.setInout_id(0);
				dataBaseControl.insertByBean(fi_payfor_item);
				
			}
			
			if(ck4.equals("1"))
			{
				Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
				fi_payfor_item.setApplyreason(request.getParameter("rea4"));
				fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money4")==null||request.getParameter("money4").equals(""))?"0":request.getParameter("money4")));
				fi_payfor_item.setFujnum(request.getParameter("fuj4"));
				fi_payfor_item.setF_id(new Integer(id+""));
				fi_payfor_item.setPurchase_item_id(new Integer(0));
				fi_payfor_item.setMateriel_id(0);
				fi_payfor_item.setNum(new Integer(0));
				fi_payfor_item.setPrice(new BigDecimal(0));
				fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
				fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
				total = total + Double.valueOf((request.getParameter("money4")==null||request.getParameter("money4").equals(""))?"0":request.getParameter("money4"));
				fi_payfor_item.setGongys(0);
				fi_payfor_item.setInout_id(0);
				dataBaseControl.insertByBean(fi_payfor_item);
				
			}
			
			if(ck5.equals("1"))
			{
				Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
				fi_payfor_item.setApplyreason(request.getParameter("rea5"));
				fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money5")==null||request.getParameter("money5").equals(""))?"0":request.getParameter("money5")));
				fi_payfor_item.setFujnum(request.getParameter("fuj5"));
				fi_payfor_item.setF_id(new Integer(id+""));
				fi_payfor_item.setPurchase_item_id(new Integer(0));
				fi_payfor_item.setMateriel_id(0);
				fi_payfor_item.setNum(new Integer(0));
				fi_payfor_item.setPrice(new BigDecimal(0));
				fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
				fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
				total = total + Double.valueOf((request.getParameter("money5")==null||request.getParameter("money5").equals(""))?"0":request.getParameter("money5"));
				fi_payfor_item.setGongys(0);
				fi_payfor_item.setInout_id(0);
				dataBaseControl.insertByBean(fi_payfor_item);
				
			}
			
			Map map1 = dataBaseControl.getOneResultSet("select t.* from fi_payfor t where t.id = ?", new Object[]{id});
			request.setAttribute("accid", map1.get("id"));
			Fi_payfor fi_payfor = new Fi_payfor();
			BeanUtils.populate(fi_payfor, map1);
			
			fi_payfor.setPayforstate(request.getParameter("state"));
			fi_payfor.setMoney(new BigDecimal(total+""));
			fi_payfor.setPaytype(request.getParameter("paytype"));
			fi_payfor.setPjdtype(request.getParameter("pjdtype"));
			fi_payfor.setBmoney(NumToRmb.toHanStr(total));
			fi_payfor.setNote(request.getParameter("gongys"));
			dataBaseControl.updateByBean(fi_payfor);
			dataBaseControl.endTransaction();
			forward = "/fi/payfor/nlfi_payforDtl.jsp";
			request.setAttribute("operationSign", "closeDG_refreshW();");
			
		}
		else
		{
			Map map1 = dataBaseControl.getOneResultSet("select t.* from fi_payfor t where t.id = ?", new Object[]{request.getParameter("id")});
			String type = ""+map1.get("type");
			request.setAttribute("accid", map1.get("id"));
			
			
			buildDDL(request);
			
			if(!type.equals("1"))  //与材料相关
			{
				Page page=search1(request,1,1);		
				if(page.getTotalNumberOfElements()>0)
				{
					request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
				}
				request.setAttribute("btnDisplay", "display:none");
			
				String flag = request.getParameter("flag")==null?"0":request.getParameter("flag");
				if(flag.equals("1"))
				{
					request.setAttribute("operationSign", "closeDG_refreshW();");
				}
			
				request.setAttribute("type", "modify");
				
				List list = dataBaseControl.getOutResultSet("select t.*,c.prj_id,(select p.name from pr_project p where p.id=c.prj_id)pname,r.ration_apply_id,(select m.name from materiel m where m.id = t.materiel_id) maname,(select s.name from supplier s where s.id = t.gongys) gys from fi_payfor_item t,gm_purchase r,gm_purchase_item c where t.f_id = ? and t.purchase_item_id = c.id and c.purchase_id = r.id order by t.id", new Object[]{request.getParameter("id")});
				request.setAttribute("reslist", list);
				
				request.setAttribute("ssize", list.size());
				
				forward = "/fi/payfor/fi_payforpayDtl.jsp";
			}
			else //与材料无关
			{
				Map map2 = dataBaseControl.getOneResultSet("select t.*,s.name,s.id sid,(select b.name from hr_base_info b where t.p_id = b.id)pname from fi_payfor t,supplier s where t.id = ? and t.note = s.id", new Object[]{request.getParameter("id")});
				request.setAttribute("info", map2);
				
				Map user = (Map)request.getSession().getAttribute("user");
				request.setAttribute("applyid", user.get("base_info_id"));
				
				List slist = dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? order by t.id", new Object[]{map2.get("id")});
				request.setAttribute("slist", slist);
				request.setAttribute("ssize", 5-slist.size());
				
				forward = "/fi/payfor/nlfi_payforDtl.jsp";
			}
		}
		
		List splist = dataBaseControl.getOutResultSet("select t.*,(select b.name from hr_base_info b where b.id = t.audit_id)pname from fi_payfor_audit t where t.apply_id = ? order by t.id ",new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		return forward;
	}
	
	/*
	 * 作废
	 */
	public String zuofei(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map map = dataBaseControl.getOneResultSet("select * from fi_payfor t where t.id = ?", new Object[]{request.getParameter("payforid")});
			Fi_payfor fi_payfor = new Fi_payfor();
			BeanUtils.populate(fi_payfor, map);
			fi_payfor.setPayforstate("9");
			fi_payfor.setZfreason(request.getParameter("zfreason"));
			
			dataBaseControl.updateByBean(fi_payfor);
			
			
			
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		else
		{
			
			Page page=search1(request,1,1);
			
	        if(page.getTotalNumberOfElements()>0)
			{
			request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
			}
			request.setAttribute("btnDisplay", "display:none");
			
			
			buildDDL(request);
			
			
			
			List list = dataBaseControl.getOutResultSet("select t.*,r.ration_apply_id,(select m.name from materiel m where m.id = t.materiel_id) maname,(select s.name from supplier s where s.id = t.gongys) gys from fi_payfor_item t,gm_purchase r,gm_purchase_item c where t.f_id = ? and t.purchase_item_id = c.id and c.purchase_id = r.id order by t.id ", new Object[]{request.getParameter("id")});
			request.setAttribute("reslist", list);
			
		}
		request.setAttribute("IsPostBack", "1");
		return "/fi/payfor/fi_payforpayDtl.jsp";
	}
	
	
	
	/*
	 * 提交修改后的款项支付
	 */
	public String sub1(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
		Map map = dataBaseControl.getOneResultSet("select * from fi_payfor t where t.id = ?", new Object[]{request.getParameter("payforid")});
		
		List itemlist = (List)dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? order by t.id", new Object[]{request.getParameter("payforid")});
		
		Object[] checkbox = request.getParameterValues("checkbox");
		
		Fi_payfor fi_payfor = new Fi_payfor();
		BeanUtils.populate(fi_payfor, map);
		
		if(checkbox.length!=0)
		{
			dataBaseControl.beginTransaction();
			float total = 0 ;
			
			for(int j = 0 ; j < itemlist.size();j++)
			{
				boolean flag = false;
				Map tmap = (Map)itemlist.get(j);
				
				String id = tmap.get("id").toString();
				
				
				for(int i = 0 ; i < checkbox.length;i++)
				{
					String nid = checkbox[i].toString();
					if(id.equals(nid))  //分配付款时仍然保留
					{
						flag = true;
					}
				}
				
				if(flag)//分配付款保留的
				{
					float money = 0;
					Map map1 = dataBaseControl.getOneResultSet("select * from fi_payfor_item t where t.id = ?", new Object[]{id});
					Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
					BeanUtils.populate(fi_payfor_item, map1);
					
					fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
					//fi_payfor_item.setNum(new Integer(request.getParameter("num"+checkbox[i].toString()).equals("")?"0":request.getParameter("num"+checkbox[i].toString())));
					money = Float.valueOf(fi_payfor_item.getMoney()+"") ;
					total = total + money;
					fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
					dataBaseControl.updateByBean(fi_payfor_item);
				}
				
				else//此次分配付款未勾选的，删除记录，并将其设置为"未付状态"
				{
					Map map1 = dataBaseControl.getOneResultSet("select * from gm_purchase_item t where t.id = ?", new Object[]{tmap.get("purchase_item_id")});

		            Gm_purchase_item gm_purchase_item =new Gm_purchase_item();

		            BeanUtils.populate(gm_purchase_item, map1);
		            
		            int hasnum = gm_purchase_item.getYfsl() - Integer.parseInt(tmap.get("num").toString());

		            gm_purchase_item.setYfsl(hasnum);
		            
		            dataBaseControl.updateByBean(gm_purchase_item);
		            
		            Map map2 = dataBaseControl.getOneResultSet("select * from gm_materiel_inout t where t.id = ?", new Object[]{tmap.get("inout_id")});
		            Gm_materiel_inout gm_materiel_inout = new Gm_materiel_inout();
		            BeanUtils.populate(gm_materiel_inout, map2);
		            gm_materiel_inout.setIspay("0");
		            dataBaseControl.updateByBean(gm_materiel_inout);
		            
		            dataBaseControl.executeSql("DELETE FROM fi_payfor_item WHERE ID in (?)", new Object[]{tmap.get("id")});
		    		
				}
				
			}
			
			
			
			fi_payfor.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
			fi_payfor.setPayforstate(request.getParameter("opinionid"));
			
			fi_payfor.setMoney(new BigDecimal(map.get("money").toString()));
			fi_payfor.setPaytype(request.getParameter("paytype"));
			fi_payfor.setNote(request.getParameter("note"));
			fi_payfor.setBmoney(NumToRmb.toHanStr(Double.parseDouble(map.get("money").toString())));
			
			dataBaseControl.updateByBean(fi_payfor);
			
			dataBaseControl.endTransaction();
			request.setAttribute("operationSign", "closeDG_refreshW();");
			
			///////////////////////分配付款后的短信提醒（财务主管）
			String  num=null;
			String infor=null;
			List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 2 ", null);
			if(slist!=null&&!slist.isEmpty())
			{
				Map smap = (Map)slist.get(0);
				num = smap.get("num").toString();
				infor = "您有一条款项支付申请待审批";
			}
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			m.sendGFDZ(num,infor);
			
		}
		
		return "/fi/payfor/fi_payforpayDtl.jsp";
	}
	
	/*
	 * 提交款项支付
	 */
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map1 = dataBaseControl.getOneResultSet("select *from fi_payfor t where t.id = ?", new Object[]{request.getParameter("id")});
		String type = ""+map1.get("type");
		
		
		dataBaseControl.beginTransaction();
		dataBaseControl.executeSql("update fi_payfor t set t.payforstate = '1' where t.id = ?", new Object[]{request.getParameter("id")});
		if(!type.equals("1"))
		{
			List payforitemlist =dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ?", new Object[]{request.getParameter("id")});
			
			for(int i = 0 ; i < payforitemlist.size();i++)
			{
				Map map = (Map)payforitemlist.get(i);
				Map iomap = (Map)dataBaseControl.getOneResultSet("select * from gm_materiel_inout t where t.id = ?", new Object[]{map.get("inout_id")});
				
				Gm_materiel_inout gm_materiel_inout = new Gm_materiel_inout();
				BeanUtils.populate(gm_materiel_inout, iomap);
				gm_materiel_inout.setIspay("1");
				dataBaseControl.updateByBean(gm_materiel_inout);
				
			}
		}
		
		dataBaseControl.endTransaction();
		return "/fi/fi_payfor/Fi_payfor!paysearchList.do";
	}  
	
	
	/*
	 * 入库查询
	 */
      public String materielinquery(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
  		
  		 String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
  		       ? "1" : request.getParameter("pageno");
          int pageNo=(new Integer(page_no)).intValue();
          int pageSize=20;
          
          Map map = new HashMap();

          Page page=detailsearch(getParameterMap(request),pageNo,pageSize);

          request.setAttribute("record", page.getThisPageElements());
          request.setAttribute("page", page);
          request.setAttribute("searchMap", getParameterMap(request));
          return "/fi/payfor/kucundetail.jsp";
	}
	
      
      private Page detailsearch(Map searchMap,int pageNo,int pageSize) throws Exception 
  	{
  		
  		String sql="select decode(o.ispay, '1', '支付中', '2', '已付', '未付') fk, t.ration_apply_id,o.num,o.note,o.price,o.money, b.name brname,m.name,o.type,o.odate,(select hb.name from hr_base_info hb where hb.id = o.operson)pname,s.name gongys from gm_purchase t, gm_purchase_item r, materiel m, ma_brand b,gm_materiel_inout o,supplier s where t.id = r.purchase_id and r.id = o.purchase_item_id and r.materiel_id = m.id and r.brand_id = b.id  and t.spzt = '5' and o.type='入库' and s.id(+) = r.gongys   "
  			+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
  			+ "/~ and r.materiel_id={materiel_id} ~/ "
  			+ "/~ and o.ispay={ispay} ~/ "
  			+ "/~ and s.name like '%[gong]%'  ~/ "
  			+ " order by t.ration_apply_id desc,o.id desc ";
  		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
  		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
  		return page;
  	}
      
     
      public String detailsearchDemo (HttpServletRequest request,HttpServletResponse response) throws Exception  
    	{
    		
    		String sql="select decode(o.ispay, '1', '支付中', '2', '已付', '未付') fk, t.ration_apply_id,o.num,o.note,o.price,o.money, b.name brname,m.name,o.type,o.odate,(select hb.name from hr_base_info hb where hb.id = o.operson)pname,s.name gongys from gm_purchase t, gm_purchase_item r, materiel m, ma_brand b,gm_materiel_inout o,supplier s where t.id = r.purchase_id and r.id = o.purchase_item_id and r.materiel_id = m.id and r.brand_id = b.id  and t.spzt = '5' and o.type='入库' and s.id(+) = r.gongys   "
    			+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
    			+ "/~ and r.materiel_id={materiel_id} ~/ "
    			+ "/~ and o.ispay={ispay} ~/ "
    			+ "/~ and s.name like '%[gong]%'  ~/ "
    			+ " order by t.ration_apply_id desc";
    		defaultList(request,response,new StringBuffer(sql),"o.id desc"); 
    		return null;
    	}
      public void total(HttpServletRequest request,HttpServletResponse response) throws Exception {

    	  String sql="select sum(o.price) sump,sum(o.num) num,sum(o.money) sum,count(*) count  from gm_purchase t, gm_purchase_item r, materiel m, ma_brand b,gm_materiel_inout o,supplier s where t.id = r.purchase_id and r.id = o.purchase_item_id and r.materiel_id = m.id and r.brand_id = b.id  and t.spzt = '5' and o.type='入库' and s.id(+) = r.gongys   "
      			+ "/~ and t.ration_apply_id like '%[ration_apply_id]%' ~/ "
      			+ "/~ and r.materiel_id={materiel_id} ~/ "
      			+ "/~ and o.ispay={ispay} ~/ "
      			+ "/~ and s.name like '%[gong]%'  ~/ "
      			+ " order by t.ration_apply_id desc";
    			
    			XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
    			Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
    			Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
    			
    			String jsonStr = JSON.toJSONString(map);
    			setAjaxInfo(response,jsonStr);
    		
    		}
      
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("paytype",codeTableUtil.getCodeMap("paytype")); 
		request.setAttribute("payforstate",codeTableUtil.getCodeMap("payforstate")); 
	}
	
	
	/*
	 * 和材料无关的款项支付
	 */
	public String addpayfor(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			
			try {
				Long id = dataBaseControl.getSeq(Fi_payfor.class);
				dataBaseControl.beginTransaction();
				double total = 0 ;
				
				Map user = (Map)request.getSession().getAttribute("user");
				
				String ck1 = request.getParameter("ck1")==null?"0":request.getParameter("ck1");
				String ck2 = request.getParameter("ck2")==null?"0":request.getParameter("ck2");
				String ck3 = request.getParameter("ck3")==null?"0":request.getParameter("ck3");
				String ck4 = request.getParameter("ck4")==null?"0":request.getParameter("ck4");
				String ck5 = request.getParameter("ck5")==null?"0":request.getParameter("ck5");
				
				if(ck1.equals("1"))
				{
					Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
					fi_payfor_item.setApplyreason(request.getParameter("rea1"));
					fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money1")==null||request.getParameter("money1").equals(""))?"0":request.getParameter("money1")));
					fi_payfor_item.setFujnum(request.getParameter("fuj1"));
					fi_payfor_item.setF_id(new Integer(id+""));
					fi_payfor_item.setPurchase_item_id(new Integer(0));
					fi_payfor_item.setMateriel_id(0);
					fi_payfor_item.setNum(new Integer(0));
					fi_payfor_item.setPrice(new BigDecimal(0));
					fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
					fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
					total = total + Double.valueOf((request.getParameter("money1")==null||request.getParameter("money1").equals(""))?"0":request.getParameter("money1"));
					fi_payfor_item.setGongys(new Integer(request.getParameter("gongys")));
					fi_payfor_item.setInout_id(0);
					dataBaseControl.insertByBean(fi_payfor_item);
					
				}
				
				if(ck2.equals("1"))
				{
					Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
					fi_payfor_item.setApplyreason(request.getParameter("rea2"));
					fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money2")==null||request.getParameter("money2").equals(""))?"0":request.getParameter("money2")));
					fi_payfor_item.setFujnum(request.getParameter("fuj2"));
					fi_payfor_item.setF_id(new Integer(id+""));
					fi_payfor_item.setPurchase_item_id(new Integer(0));
					fi_payfor_item.setMateriel_id(0);
					fi_payfor_item.setNum(new Integer(0));
					fi_payfor_item.setPrice(new BigDecimal(0));
					fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
					fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
					total = total + Double.valueOf((request.getParameter("money2")==null||request.getParameter("money2").equals(""))?"0":request.getParameter("money2"));
					fi_payfor_item.setGongys(new Integer(request.getParameter("gongys")));
					fi_payfor_item.setInout_id(0);
					dataBaseControl.insertByBean(fi_payfor_item);
					
				}
				
				if(ck3.equals("1"))
				{
					Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
					fi_payfor_item.setApplyreason(request.getParameter("rea3"));
					fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money3")==null||request.getParameter("money3").equals(""))?"0":request.getParameter("money3")));
					fi_payfor_item.setFujnum(request.getParameter("fuj3"));
					fi_payfor_item.setF_id(new Integer(id+""));
					fi_payfor_item.setPurchase_item_id(new Integer(0));
					fi_payfor_item.setMateriel_id(0);
					fi_payfor_item.setNum(new Integer(0));
					fi_payfor_item.setPrice(new BigDecimal(0));
					fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
					fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
					total = total + Double.valueOf((request.getParameter("money3")==null||request.getParameter("money3").equals(""))?"0":request.getParameter("money3"));
					fi_payfor_item.setGongys(new Integer(request.getParameter("gongys")));
					fi_payfor_item.setInout_id(0);
					dataBaseControl.insertByBean(fi_payfor_item);
					
				}
				
				if(ck4.equals("1"))
				{
					Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
					fi_payfor_item.setApplyreason(request.getParameter("rea4"));
					fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money4")==null||request.getParameter("money4").equals(""))?"0":request.getParameter("money4")));
					fi_payfor_item.setFujnum(request.getParameter("fuj4"));
					fi_payfor_item.setF_id(new Integer(id+""));
					fi_payfor_item.setPurchase_item_id(new Integer(0));
					fi_payfor_item.setMateriel_id(0);
					fi_payfor_item.setNum(new Integer(0));
					fi_payfor_item.setPrice(new BigDecimal(0));
					fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
					fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
					total = total + Double.valueOf((request.getParameter("money4")==null||request.getParameter("money4").equals(""))?"0":request.getParameter("money4"));
					fi_payfor_item.setGongys(new Integer(request.getParameter("gongys")));
					fi_payfor_item.setInout_id(0);
					dataBaseControl.insertByBean(fi_payfor_item);
					
				}
				
				if(ck5.equals("1"))
				{
					Fi_payfor_item fi_payfor_item = new Fi_payfor_item();
					fi_payfor_item.setApplyreason(request.getParameter("rea5"));
					fi_payfor_item.setMoney(new BigDecimal((request.getParameter("money5")==null||request.getParameter("money5").equals(""))?"0":request.getParameter("money5")));
					fi_payfor_item.setFujnum(request.getParameter("fuj5"));
					fi_payfor_item.setF_id(new Integer(id+""));
					fi_payfor_item.setPurchase_item_id(new Integer(0));
					fi_payfor_item.setMateriel_id(0);
					fi_payfor_item.setNum(new Integer(0));
					fi_payfor_item.setPrice(new BigDecimal(0));
					fi_payfor_item.setPerson(new Integer(user.get("base_info_id").toString()));
					fi_payfor_item.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
					total = total + Double.valueOf((request.getParameter("money5")==null||request.getParameter("money5").equals(""))?"0":request.getParameter("money5"));
					fi_payfor_item.setGongys(new Integer(request.getParameter("gongys")));
					fi_payfor_item.setInout_id(0);
					dataBaseControl.insertByBean(fi_payfor_item);
					
				}
				
				
				
					Fi_payfor fi_payfor = new Fi_payfor();
					fi_payfor.setP_id(new BigDecimal(user.get("base_info_id").toString()));
					fi_payfor.setDept_id(new BigDecimal(user.get("branchid").toString()));
					fi_payfor.setOdate(new java.sql.Date((new java.util.Date()).getTime()));
					fi_payfor.setPayforstate(request.getParameter("state"));
					fi_payfor.setMoney(new BigDecimal(total+""));
					fi_payfor.setPaytype(request.getParameter("paytype"));
					fi_payfor.setNote(request.getParameter("note"));
					fi_payfor.setNote(request.getParameter("gongys"));
					fi_payfor.setBmoney(NumToRmb.toHanStr(total));
					fi_payfor.setSk_id(new BigDecimal(request.getParameter("gongys")));
					fi_payfor.setPjdtype(request.getParameter("pjdtype"));
					fi_payfor.setType("1");
					
					dataBaseControl.insertByBean(fi_payfor, id);
			} catch (Exception e) {
				System.out.println(e);
			} finally{
				dataBaseControl.endTransaction();
			}
			
                request.setAttribute("operationSign", "closeDG_refreshW();");
                
                String num = "";
    			String infor = "您有一条款项支付申请待审批";
    			List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept = 3 ", null);
    			if(slist!=null&&!slist.isEmpty())
    			{
    				Map smap = (Map)slist.get(0);
    				num = smap.get("num").toString();
    			}
    			
    			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
    			Mas m = (Mas) client.useService(Mas.class);
    			m.sendGFDZ(num,infor);
		}else{
			Long id = dataBaseControl.getSeq(Fi_payfor.class);
			request.setAttribute("accid", id+1);//当插入数据之后id就+1了，所有查看的是id+1的那条数据
		}
		
		buildDDL(request);
		request.setAttribute("editMod", "addpayfor");
		request.setAttribute("IsPostBack", "1");
		return "/fi/payfor/nfi_payforDtl.jsp";
	}
	
	
	public String del(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map map = dataBaseControl.getOneResultSet("select * from fi_payfor_item t where t.id = ? ", new Object[]{request.getParameter("id")});
		
        
		dataBaseControl.executeSql("DELETE FROM fi_payfor_item WHERE ID in (?)", new Object[]{request.getParameter("id")});
		
		
		
		List list= dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? ", new Object[]{map.get("f_id")});
		String flag = "0";
		if(list == null || list.isEmpty())
		{
			dataBaseControl.executeSql("DELETE FROM fi_payfor WHERE ID in (?)", new Object[]{map.get("f_id")});
			flag = "1";
		}
		else
		{
			
		}
		
		
		return "/fi/fi_payfor/Fi_payfor!edit.do?id="+map.get("f_id")+"&flag="+flag;
	
	}
	
	
	public String tongji(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map map = dataBaseControl.getOneResultSet("select * from fi_payfor_item t where t.id = ? ", new Object[]{request.getParameter("id")});
		
        
		dataBaseControl.executeSql("DELETE FROM fi_payfor_item WHERE ID in (?)", new Object[]{request.getParameter("id")});
		
		
		
		List list= dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? ", new Object[]{map.get("f_id")});
		String flag = "0";
		if(list == null || list.isEmpty())
		{
			dataBaseControl.executeSql("DELETE FROM fi_payfor WHERE ID in (?)", new Object[]{map.get("f_id")});
			flag = "1";
		}
		else
		{
			
		}
		
		
		return "/fi/fi_payfor/Fi_payfor!edit.do?id="+map.get("f_id")+"&flag="+flag;
	
	}
	
	

}