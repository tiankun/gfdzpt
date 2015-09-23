package com.web.fi.fi_payfor;

import java.io.PrintWriter;
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
import com.map.Fi_payfor;
import com.map.Fi_payfor_audit;
import com.map.Gm_materiel_inout;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.CnToSpell;
import com.sysFrams.util.Constant;
import com.sysFrams.util.codeTableUtil;
import com.zsc.Mas;

public class Fi_payfor_auditAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;		
		Map user = (Map)request.getSession().getAttribute("user");
		Map baseinfo = (Map)dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{user.get("base_info_id")});
		String deptid = user.get("branchid").toString();
		String dutyid = baseinfo.get("duty_id").toString();
		String sk=request.getParameter("gong");
		Map map2=(Map)dataBaseControl.getOneResultSet("select s.* from supplier s where s.name = ?", new Object[]{sk});
		
		Map map = getParameterMap(request);
		String forward = "";
		
		if(deptid.equals("3")||deptid.equals("7"))//综合部/工程部
		{
			String payforstate = request.getParameter("payforstate")==null?"1":request.getParameter("payforstate");
			map.put("payforstate", payforstate);
			map.put("dept_id", deptid);
			forward = "/fi/payfor/fi_payfor_auditList.jsp";
		}
		
		if(deptid.equals("6")&&dutyid.equals("1"))//财务部会计
		{
			String payforstate = request.getParameter("payforstate")==null?"2":request.getParameter("payforstate");
			map.put("payforstate", payforstate);
			forward = "/fi/payfor/fi_payfor_kjauditList.jsp";
			
		}
		
		if(deptid.equals("6")&&dutyid.equals("2"))//财务主管
		{
			String payforstate = request.getParameter("payforstate")==null?"4":request.getParameter("payforstate");
			map.put("payforstate", payforstate);
			forward = "/fi/payfor/fi_payfor_cwzgauditList.jsp";
		}
		
		if(deptid.equals("5"))//崔总
		{
			String payforstate = request.getParameter("payforstate")==null?"5":request.getParameter("payforstate");
			map.put("payforstate", payforstate);
			forward = "/fi/payfor/fi_payfor_cyauditList.jsp";
		}
		
		if(deptid.equals("9"))//成都
		{
			//map.put("payforstate1", "1");
			//map.put("payforstate2", "6");
			map.put("dept_id", deptid);
			forward = "/fi/payfor/fi_payfor_xpzList.jsp";
		}
		
		request.setAttribute("searchmap", map);
		Page page=search(map,pageNo,pageSize);
		buildDDL(request);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/fi_payfor/fi_payfor_auditSelect.jsp";
		return forward;
	
	}
	public String lookList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String sql="select t.*,m.branchname,b.name ,s.name sname,c.dmzmc from fi_payfor t,mrbranch m,hr_base_info b ,supplier s,code c where 1=1 and t.p_id = b.id and t.dept_id = m.id and t.sk_id=s.id and c.dmz=t.payforstate and c.dmlb='payforstate'" 
				+ "/~ and t.odate>=to_date({app_date1},'yyyy-MM-dd')  ~/ "
				+ "/~ and t.odate<=to_date({app_date2},'yyyy-MM-dd')   ~/ "
				+ "/~ and t.money={money} ~/ "
				+ "/~ and t.p_id={p_id} ~/ "
				+ "/~ and t.sk_id={gongys} ~/ "
				+ "/~ and t.id={id} ~/ "
				+ "/~ and t.dept_id={dept_id} ~/ "
				+ "/~ and t.payforstate={payforstate} ~/ "
				+ "/~ and (t.payforstate={payforstate1} or t.payforstate={payforstate2}) ~/ ";
	
		defaultList(request,response,new StringBuffer(sql),"t.id desc"); 
		
		return null;
	
	}
public void total(HttpServletRequest request,HttpServletResponse response) throws Exception {

	String sql="select sum(t.money) sum,count(*) count from fi_payfor t,mrbranch m,hr_base_info b where 1=1 and t.p_id = b.id and t.dept_id = m.id " 
				+ "/~ and t.money={money} ~/ "
				+ "/~ and t.p_id={p_id} ~/ "
				+ "/~ and t.id={id} ~/ "
				+ "/~ and t.sk_id={gongys} ~/ "
				+ "/~ and t.dept_id={dept_id} ~/ "
				+ "/~ and t.payforstate={payforstate} ~/ "
				+ "/~ and (t.payforstate={payforstate1} or t.payforstate={payforstate2}) ~/ "
				+ "/~ and t.odate>=to_date({app_date1},'yyyy-MM-dd')  ~/ "
				+ "/~ and t.odate<=to_date({app_date2},'yyyy-MM-dd')   ~/ ";
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),1,1);
		Map map = (Map) ((ArrayList)page.getThisPageElements()).get(0);
		
		String jsonStr = JSON.toJSONString(map);
		setAjaxInfo(response,jsonStr);
	
	}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(getParameterMap(request),1,1);
        Map map1 = dataBaseControl.getOneResultSet("select t.* from fi_payfor t where t.id = ?", new Object[]{request.getParameter("id")});
		String type = ""+map1.get("type");
		request.setAttribute("accid", map1.get("id"));
		if(map1.get("sk_id")!=null){
			
			Map map3 = dataBaseControl.getOneResultSet("select s.* from supplier s where s.id = ?", new Object[]{map1.get("sk_id")});
			String sk=map3.get("name").toString();
			request.setAttribute("sk", sk);
		}
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		String forward = "";
		
		request.setAttribute("look", "1");
		
		if(!type.equals("1"))  //与材料相关
		{
			List list = dataBaseControl.getOutResultSet("select t.*,c.prj_id,(select p.name from pr_project p where p.id=c.prj_id)pname,r.ration_apply_id,(select m.name from materiel m where m.id = t.materiel_id) maname,(select s.name from supplier s where s.id = t.gongys) gys from fi_payfor_item t,gm_purchase r,gm_purchase_item c where t.f_id = ? and t.purchase_item_id = c.id and c.purchase_id = r.id order by t.id ", new Object[]{request.getParameter("id")});
			request.setAttribute("reslist", list);
			forward = "/fi/payfor/fi_payforpayDtl.jsp";
		}
		else   //与材料无关
		{
			Map map2 = dataBaseControl.getOneResultSet("select t.*,s.name,s.id sid,(select b.name from hr_base_info b where t.p_id = b.id)pname from fi_payfor t,supplier s where t.id = ? and t.note = s.id", new Object[]{request.getParameter("id")});
			request.setAttribute("info", map2);
			
			Map user = (Map)request.getSession().getAttribute("user");
			
			request.setAttribute("applyid", user.get("base_info_id"));
			
			List slist = dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? order by t.id", new Object[]{map2.get("id")});
			request.setAttribute("slist", slist);
			forward = "/fi/payfor/nlfi_payforDtl.jsp";
			
		}

		List splist = dataBaseControl.getOutResultSet("select t.*,(select b.name from hr_base_info b where b.id = t.audit_id)pname from fi_payfor_audit t where t.apply_id = ? order by t.id ",new Object[]{request.getParameter("id")});
		request.setAttribute("splist", splist);
		buildDDL(request);
		return forward;
	}
	private Page search(Map map,int pageNo,int pageSize) throws Exception 
	{
		
		String sql="select t.*,m.branchname,b.name,(select p.name from pr_project p where p.id=t.pro_id)pname from fi_payfor t,mrbranch m,hr_base_info b where 1=1 and t.p_id = b.id and t.dept_id = m.id" 
		+ "/~ and t.money={money} ~/ "
		+ "/~ and t.p_id={p_id} ~/ "
		+ "/~ and t.id={id} ~/ "
		+"/~ and t.sk_id={gongys} ~/ "
		+ "/~ and t.dept_id={dept_id} ~/ "
		+ "/~ and t.payforstate={payforstate} ~/ "
		+ "/~ and (t.payforstate={payforstate1} or t.payforstate={payforstate2}) ~/ "
		+ "/~ and t.odate>=to_date({input_date1},'yyyy-MM-dd')  ~/ "
		+ "/~ and t.odate<=to_date({input_date2},'yyyy-MM-dd')   ~/ "
		+ " order by t.id desc ";
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,map); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		
		return page;
	}

	public String audit(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(getMapObject(request));			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/fi/fi_payfor/fi_payfor_auditDtl.jsp";
	}
	
	/*
	 * 审批款项支付
	 */
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		request.setAttribute("type", request.getParameter("type"));
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Map map = dataBaseControl.getOneResultSet("select * from fi_payfor t where t.id = ?", new Object[]{request.getParameter("id")});
			if(map.get("sk_id")!=null){
				
				Map map3 = dataBaseControl.getOneResultSet("select s.name from supplier s where s.id = ?", new Object[]{map.get("sk_id")});
				request.setAttribute("sk", map3.get("name"));
			}
			Fi_payfor fi_payfor = new Fi_payfor();
			BeanUtils.populate(fi_payfor, map);
			fi_payfor.setPayforstate(request.getParameter("opinionid"));
			//成都分公司和工程部不要分配付款
			if(map.get("dept_id").toString().equals("9")&&map.get("payforstate").toString().equals("2")&&request.getParameter("opinionid").equals("3")||map.get("dept_id").toString().equals("7")&&map.get("payforstate").toString().equals("2")&&request.getParameter("opinionid").equals("3")){
				fi_payfor.setPayforstate("4");
			}
			String kjtype = request.getParameter("kjtype")==null?"":request.getParameter("kjtype");
			if(!kjtype.equals(""))
			{
				fi_payfor.setKjtype(kjtype);
			}
			
			fi_payfor.setReason(request.getParameter("opinion"));
			dataBaseControl.updateByBean(fi_payfor);
			
			//dataBaseControl.executeSql("update fi_payfor t set t.payforstate = ?,t.reason = ?  where t.id = ?", new Object[]{request.getParameter("opinionid"),request.getParameter("opinion"),request.getParameter("id")});
			Fi_payfor_audit fi_payfor_audit = new Fi_payfor_audit();
			Map user = (Map)request.getSession().getAttribute("user");
			fi_payfor_audit.setApply_id(new BigDecimal(request.getParameter("id")));
			fi_payfor_audit.setAudit_date(new java.sql.Timestamp((new java.util.Date()).getTime()));
			fi_payfor_audit.setOpinion(request.getParameter("opinion"));
			fi_payfor_audit.setAudit_id(new BigDecimal(user.get("base_info_id").toString()));
			fi_payfor_audit.setState(request.getParameter("opinionid"));
			if(map.get("dept_id").toString().equals("9")&&map.get("payforstate").toString().equals("2")&&request.getParameter("opinionid").equals("3")||map.get("dept_id").toString().equals("7")&&map.get("payforstate").toString().equals("2")&&request.getParameter("opinionid").equals("3")){
				fi_payfor_audit.setState("4");
			}
			dataBaseControl.insertByBean(fi_payfor_audit);
			
			String state = request.getParameter("opinionid");
			String num = "";
			String infor = "";
			if(state.equals("2")) //会计
			{
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 3 ", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
					infor = "您有一条款项支付申请待审批";
				}
			}
			
			if(state.equals("3")) //待分配付款
			{
				Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{fi_payfor.getP_id()});
				
				num = baseinfo.get("phone").toString();
				infor = "您有一条款项支付待分配付款,付款金额是"+fi_payfor.getMoney();
				
			}
			
			if(state.equals("4")) //财务主管审批
			{
				//Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{fi_payfor.getP_id()});
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 2 ", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
					infor = "您有一条款项支付申请待审批";
				}
			}
			
			if(state.equals("5")) //崔总审批
			{
				//Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{fi_payfor.getP_id()});
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 4 ", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
					infor = "您有一条款项支付申请待审批";
				}
			}
			
			if(state.equals("6")) //向总审批
			{
				//Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{fi_payfor.getP_id()});
				List slist = dataBaseControl.getOutResultSet("select t.* from message t where t.rolename = 1 and t.dept=9 ", null);
				if(slist!=null&&!slist.isEmpty())
				{
					Map smap = (Map)slist.get(0);
					num = smap.get("num").toString();
					infor = "您有一条款项支付申请待审批";
				}
			}
			
			if(state.equals("7")) //审批通过
			{
				Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{fi_payfor.getP_id()});
				num = baseinfo.get("phone").toString();
				infor = "您的款项支付申请审批通过";
				
				
				List mlist = dataBaseControl.getOutResultSet("select m.* from fi_payfor t,fi_payfor_item f,gm_materiel_inout m where t.id = f.f_id and f.inout_id = m.id and t.id = ?", new Object[]{fi_payfor.getId()});
				for(int i = 0 ; i < mlist.size();i++)
				{
					Map tmap = (Map)mlist.get(i);
					Gm_materiel_inout inout = new Gm_materiel_inout();
					BeanUtils.populate(inout, tmap);
					inout.setIspay("2");
					dataBaseControl.updateByBean(inout);
				}
				
			}
			
			if(state.equals("8")) //打回
			{
				Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{fi_payfor.getP_id()});
				num = baseinfo.get("phone").toString();
				infor = "您的款项支付申请被打回";
				
			}
			
			if(state.equals("10")) //不通过
			{   try {
				Map baseinfo = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ?", new Object[]{fi_payfor.getP_id()});
				num = baseinfo.get("phone").toString();
				infor = "您的款项支付申请审核不通过";
				
				//将材料设置为为付款状态
				String  iid=request.getParameter("id");
				List itemlist = dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? order by t.id", new Object[]{request.getParameter("id")});
				
				if(itemlist!=null&&!itemlist.isEmpty())
				{
					for(int i = 0 ; i < itemlist.size();i++)
					{
						Map tmap = (Map)itemlist.get(i);
						
						Map iomap = (Map)dataBaseControl.getOneResultSet("select * from gm_materiel_inout t where t.id = ?", new Object[]{tmap.get("inout_id")});
						Gm_materiel_inout iot = new Gm_materiel_inout();
						
						BeanUtils.populate(iot, iomap);
						
						iot.setIspay("0");
						dataBaseControl.updateByBean(iot);
						
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
				
				
			}
			
			PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
			Mas m = (Mas) client.useService(Mas.class);
			m.sendGFDZ(num,infor);
			
			
			
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		else
		{
			Map map1 = dataBaseControl.getOneResultSet("select t.* from fi_payfor t where t.id = ?", new Object[]{request.getParameter("id")});
			String type = ""+map1.get("type");
			if(map1.get("sk_id")!=null){
				Map map3 = dataBaseControl.getOneResultSet("select s.name from supplier s where s.id = ?", new Object[]{map1.get("sk_id")});
				request.setAttribute("sk", map3.get("name"));
			}
			request.setAttribute("deptid", map1.get("dept_id"));
			buildDDL(request);
			
			if(!type.equals("1"))
			{
				Page page=search(getParameterMap(request),1,1);
				
		        if(page.getTotalNumberOfElements()>0)
				{
				request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
				}
				request.setAttribute("btnDisplay", "display:none");
				request.setAttribute("ma", "0");
				List list = dataBaseControl.getOutResultSet("select t.*,c.prj_id,(select p.name from pr_project p where p.id=c.prj_id)pname,r.ration_apply_id,(select m.name from materiel m where m.id = t.materiel_id) maname,(select s.name from supplier s where s.id = t.gongys) gys from fi_payfor_item t,gm_purchase r,gm_purchase_item c where t.f_id = ? and t.purchase_item_id = c.id and c.purchase_id = r.id order by t.id", new Object[]{request.getParameter("id")});
				
				request.setAttribute("reslist", list);
			}
			else
			{
				Map map2 = dataBaseControl.getOneResultSet("select t.*,s.name,s.id sid,(select b.name from hr_base_info b where t.p_id = b.id)pname from fi_payfor t,supplier s where t.id = ? and t.note = s.id", new Object[]{request.getParameter("id")});
				request.setAttribute("record", map2);
				request.setAttribute("ma", "1");
				List slist = dataBaseControl.getOutResultSet("select * from fi_payfor_item t where t.f_id = ? order by t.id", new Object[]{map2.get("id")});
				request.setAttribute("slist", slist);
			}
			
			
			
			//获得审批意见
			buildDDL(request);
			List splist = dataBaseControl.getOutResultSet("select t.*,(select b.name from hr_base_info b where b.id = t.audit_id)pname from fi_payfor_audit t where t.apply_id = ? order by t.id ",new Object[]{request.getParameter("id")});
			request.setAttribute("splist", splist);
		}
		request.setAttribute("IsPostBack", "1");
		return "/fi/payfor/fi_auditpayforpayDtl.jsp";
	}
	
	
	public String sub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		
		return "/fi/payfor/fi_auditpayforpayDtl.jsp";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Fi_payfor_audit WHERE ID in (?)", new Object[]{s_id});
		
		return "/fi/fi_payfor/Fi_payfor_audit!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		request.setAttribute("paytype",codeTableUtil.getCodeMap("paytype")); 
		request.setAttribute("payforstate",codeTableUtil.getCodeMap("payforstate")); 
		
	}
	
	/*
	 * 款项支付统计查询
	 */
	
	public String tongji(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String sql = "select distinct s.id,s.name from gm_purchase_item t,supplier s,gm_purchase r where t.gongys = s.id and t.purchase_id = r.id and r.spzt ='5' "
				   + "/~ and s.name like '%[gong]%' ~/ ";
		
		request.setAttribute("searchMap", getParameterMap(request));
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
		List gyslist = dataBaseControl.getOutResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray());
		
		List reslist = new ArrayList();
		
		for(int i = 0 ; i < gyslist.size();i++)
		{
			Map rmap = new HashMap();
			Map tmap = (Map)gyslist.get(i);
			//查看是否有与材料无关的款项支付
			//与材料无关的全部金额
			Map nmmap = dataBaseControl.getOneResultSet("select nvl(sum(r.money),0)total from fi_payfor t, fi_payfor_item r where t.id = r.f_id  and t.payforstate not in(9,10) and t.type ='1' and r.gongys = ?", new Object[]{tmap.get("id")});
			//与材料无关的已付金额
			Map nmmap1 = dataBaseControl.getOneResultSet("select nvl(sum(r.money),0)total from fi_payfor t, fi_payfor_item r where t.id = r.f_id  and t.payforstate =7 and t.type ='1' and r.gongys = ?", new Object[]{tmap.get("id")});
			//与材料无关的未付金额
			Map nmmap2 = dataBaseControl.getOneResultSet("select nvl(sum(r.money),0)total from fi_payfor t, fi_payfor_item r where t.id = r.f_id  and t.payforstate not in(7,9,10) and t.type ='1' and r.gongys = ?", new Object[]{tmap.get("id")});
			
			//与材料相关的全部金额
			Map map = dataBaseControl.getOneResultSet("select nvl(sum(io.money),0)totalmoney from gm_purchase_item t, supplier s, gm_purchase r, gm_materiel_inout io  where t.gongys = s.id and t.purchase_id = r.id and r.spzt = '5' and io.purchase_item_id = t.id and io.type = '入库' and s.id = ?", new Object[]{tmap.get("id")});
			//与材料相关的已付金额
			Map map1 = dataBaseControl.getOneResultSet("select nvl(sum(io.money),0)totalmoney from gm_purchase_item t, supplier s, gm_purchase r, gm_materiel_inout io  where t.gongys = s.id and t.purchase_id = r.id and r.spzt = '5' and io.purchase_item_id = t.id and io.type = '入库' and s.id = ? and io.ispay = '2'", new Object[]{tmap.get("id")});
			//与材料相关的未付金额
			Map map2 = dataBaseControl.getOneResultSet("select nvl(sum(io.money),0)totalmoney from gm_purchase_item t, supplier s, gm_purchase r, gm_materiel_inout io  where t.gongys = s.id and t.purchase_id = r.id and r.spzt = '5' and io.purchase_item_id = t.id and io.type = '入库' and s.id = ? and io.ispay != '2'", new Object[]{tmap.get("id")});	
			rmap.put("name", tmap.get("name"));
			rmap.put("total", Float.parseFloat(map.get("totalmoney").toString())+Float.parseFloat(nmmap.get("total").toString()));
			
			
			
			rmap.put("pay", Float.parseFloat(map1.get("totalmoney").toString())+Float.parseFloat(nmmap1.get("total").toString()));
			rmap.put("nopay", Float.parseFloat(map2.get("totalmoney").toString())+Float.parseFloat(nmmap2.get("total").toString()));
			rmap.put("gysid", tmap.get("id"));
			
			reslist.add(rmap);
			
		}
		
		request.setAttribute("reslist", reslist);
		return "/fi/payfor/fi_payfor_tongjiList.jsp";
	}
	
	
	public String lookpay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
		  ? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		String sql = "select decode(io.ispay,'0','未付','1','支付中','已付')fk,r.ration_apply_id,m.name maname,p.name proname,io.* from gm_purchase_item t, supplier s, gm_purchase r, gm_materiel_inout io,pr_project p,materiel m where t.gongys = s.id and t.purchase_id = r.id and r.spzt = '5' and io.purchase_item_id = t.id and io.type = '入库' and r.prj_id = p.id(+) and t.materiel_id = m.id  "
			    + "/~ and r.ration_apply_id like '%[ration_apply_id]%' ~/ "
			    + "/~ and io.ispay ={ispay} ~/ "
			    + "/~ and s.id ={id} ~/ "
			    + " order by io.ispay";
		
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,getParameterMap(request)); 
		Page page = dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(), pageNo, pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		
		request.setAttribute("id", request.getParameter("id"));
		
		Map map = dataBaseControl.getOneResultSet("select * from supplier s where s.id = ?", new Object[]{request.getParameter("id")});
		
		request.setAttribute("gongys", map.get("name"));
		
		
		request.setAttribute("searchMap", getParameterMap(request));
		
		
		//查询与材料无关的数据
		String page_no1 = (request.getParameter("pageno1") == null || request.getParameter("pageno1").equals("")) 
		  ? "1" : request.getParameter("pageno1");
		int pageNo1=(new Integer(page_no)).intValue();
		int pageSize1=20;
		
		Page page1 = dataBaseControl.getPageResultSet("select r.*,t.payforstate from fi_payfor t, fi_payfor_item r where t.id = r.f_id and t.payforstate not in (9, 10) and t.type = '1' and r.gongys = ?", request.getParameterValues("id"), pageNo1, pageSize1);
		request.setAttribute("record1", page1.getThisPageElements());
		request.setAttribute("page1", page1);
		
		buildDDL(request);
		
		
		return "/fi/payfor/fi_payfor_tongjidetailList.jsp";
	}
	
	
	private Page tongjisearch(Map map,int pageNo,int pageSize) throws Exception 
	{
		
		String sql="select t.*,m.branchname,b.name from fi_payfor t,mrbranch m,hr_base_info b where 1=1 and t.p_id = b.id and t.dept_id = m.id  " 
		+ "/~ and t.money={money} ~/ "
		+ "/~ and t.id={id} ~/ "
		+ "/~ and t.dept_id={dept_id} ~/ "
		+ "/~ and t.payforstate={payforstate} ~/ "
		+ "/~ and (t.payforstate={payforstate1} or t.payforstate={payforstate2}) ~/ "
		+ "/~ and t.odate>=to_date({begin_date},'yyyy-MM-dd')  ~/ "
		+ "/~ and t.odate<=to_date({end_date},'yyyy-MM-dd')   ~/ "
		+ " order by t.id desc ";
				
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,map); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
}