package com.web.sysAdmin;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.map.sysuser;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.Encrypt;
import com.sysFrams.util.SoftKey;
import com.sysFrams.util.StrUtils;
import com.sysFrams.util.treeUtil;
public class LoginAction {
	DataBaseControl dataBaseControl = DataBaseControl.getInstance();
	
     public static boolean checkKey(String id,String name, String keyValue) {
		
		boolean result = false;
		if (Encrypt.crypt("&%@*" + id + "|*@~" + name+ "$(}?").equals(keyValue))
			result = true;
	   return result;
		//return true;
	}	
	
	/**
	 * 验证用户的登录
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String logName = request.getParameter("logName");
		String password = request.getParameter("password");
		String image = request.getParameter("code");
		HttpSession session = request.getSession();
		if (image == null || "".equals(image) || image.length() != 4
				|| session.getAttribute("sRand") == null
				|| !session.getAttribute("sRand").equals(image)) {
			// 验证码错误
			request.setAttribute("errorType", "验证码错误!");
			return "/sysAdmin/login.jsp";
		} else {
			if (StrUtils.isBlank(logName) || StrUtils.isBlank(password)) {
				request.setAttribute("errorType", "登录名和密码不能为空!");
				return "/sysAdmin/login.jsp";
			} else {
				List users = (List)dataBaseControl.getOutResultSet(
								"select sys.*,(select duty_id from hr_base_info where id=sys.base_info_id) duty_id," +
								"(select dept_id from hr_base_info where id=sys.base_info_id) dept_id, " +
								"(select pic from hr_base_info where id=sys.base_info_id) photo " + 		
								"from sysUser sys where DelFlag=0 and log_name=? and log_pass=?",
								new Object[] { logName, Encrypt.crypt(password) });
				if (users == null || users.isEmpty()) {
					request.setAttribute("errorType", "登录名和密码错误!");
					request.setAttribute("logName", logName);
					return "/sysAdmin/login.jsp";
				} else if (users.size() == 1) {
					Map user = (Map) users.toArray()[0];
					String KeyID = request.getParameter("KeyID");
					if("是".equals(user.get("flag"))){   //需用加密锁的用户验证
						
						
						
						/*
						 * String
						 * sessionid=session.getAttribute("rnd")==null?"":session
						 * .getAttribute("rnd").toString(); String
						 * return_EncData=request.getParameter("return_EncData");
						 * String usbKey=request.getParameter("usbKey"); //
						 * 验证usbKey登录
						  if (!checkKey(user,
						 * usbKey,sessionid,return_EncData)) {
						 * request.setAttribute("errorType","对不起，密码锁验证失败！"); return
						 * "/admin/index.jsp"; }
						 */
					}
					String str = Encrypt.crypt("&%@*" + user.get("lockid") + "|*@~" + user.get("log_name")+ "$(}?");
					/*
					if (!checkKey(""+user.get("lockid"),""+user.get("log_name"),KeyID)) 
					{
					  request.setAttribute("errorType","对不起，密码锁验证失败！"); 
					  return "/sysAdmin/login.jsp";
					}
					*/
					session.setAttribute("user", user);
					//进入主页面
					//构建系统菜单
					String sql =
							"select ID, FuncName, parentid, concat('"+request.getContextPath()+"',url) url, target, treenodetype from sysFuncDic where publics ='0' "
					         +" and delflag='0' and treenodetype != 'buttonLeaf' and parentid=? and ID in (SELECT FuncID FROM sysFuncRole " 
					        +" where RoleID in (select rolesid from sysUserRoles " 
					        +" where userid in (select id from sysuser where id="+user.get("id").toString()
					        +" and delflag='0')and rolesid not in(select id from sysrolesdic where roletype='3')))  order by indexid";
					String fun =treeUtil.buildLigerUIDivTree(sql,"0","id","funcname","url");
					//fun = "<ul id=\"funtree\">"+fun.substring(4);
					request.setAttribute("funcDicList", fun);
					request.setAttribute("user_name", user.get("user_name"));
					
					getbaseinfo(request, response);
					//getNotice(request, response);
					
					return "/sysAdmin/main.jsp";
				} else {
					throw new Exception("======数据库数据出现异常======");
				}
			}
		}
	}
	
//	private String bulidMenu(HttpServletRequest request,String userid) throws Exception
//	{
//		String sql="select ID, FuncName, parentid, '"+request.getContextPath()+"'||url url, target, treenodetype from sysFuncDic where publics ='0' "
//				     +" and delflag='0' and treenodetype != 'buttonLeaf' and parentid=0 and ID in (SELECT FuncID FROM sysFuncRole " 
//					+" where RoleID in(SELECT rolesid FROM sysUserRoles "
//					+" where rolesid in (select rolesid from sysUserRoles " 
//					+" where userid in (select id from sysuser where id="+userid+" and delflag='0'))))  order by indexid";
//		DataBaseControl dataBaseControl = DataBaseControl.getInstance();
//		StringBuffer script = new StringBuffer();
//		List<Object> list = (List)dataBaseControl.getOutResultSet(sql, new Object[]{});
//		if(list!=null || list.size() > 0){
//			for (Object object : list) {
//				Map item = (Map)object;
//				
//				script=script.append(
//					"<div title=\""+item.get("FuncName").toString()+"\" style=\"text-align:center\">");
//				script = script.append(buildMenu(request,userid,item.get("ID").toString()));
//				script = script.append("</div>");
//			}
//		}
//		return script.toString();
//	}
//	private String buildMenu(HttpServletRequest request,String userid,String parentid) throws Exception
//	{
//		String sql="select ID, FuncName, parentid, '"+request.getContextPath()+"'||url url, treenodetype,img_path from sysFuncDic where publics ='0' "
//			     +" and delflag='0' and treenodetype != 'buttonLeaf' and parentid=? and ID in (SELECT FuncID FROM sysFuncRole " 
//				+" where RoleID in(SELECT rolesid FROM sysUserRoles "
//				+" where rolesid in (select rolesid from sysUserRoles " 
//				+" where userid in (select id from sysuser where id="+userid+" and delflag='0'))))  order by indexid";
//	DataBaseControl dataBaseControl = DataBaseControl.getInstance();
//	StringBuffer script = new StringBuffer();
//	List<Object> list = (List)dataBaseControl.getOutResultSet(sql, new Object[]{parentid});
//	if(list!=null || list.size() > 0){
//		for (Object object : list) {
//			Map item = (Map)object;
//			
//			script=script.append(
//				"<div class=\"leftmenu\"><img src=\""+item.get("img_path")+"\" onclick=\"javascript:f_addTab('"
//						+ item.get("FuncName") + "','" 
//						+ item.get("url") + "')\"/>"+ item.get("FuncName") +"</div>");
//		}
//	}
//	return script.toString();
//	}

	/**
	 * 进行用户的密码修改（当类型为update的实行执行修改，否则进入修改页面）
	 */
	public String modifyPass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		Map user = (Map) session.getAttribute("user");
		if (user == null || user.get("log_name") == null
				|| "".equals(user.get("log_name")))
			return "/admin/timeOut.jsp";
		String type = request.getParameter("type");
		if ("update".equals(type)) {
			String oldPassword = request.getParameter("oldPassword");
			request.setAttribute("ok", "ok");
			if (oldPassword == null || "".equals(oldPassword)
					|| !user.get("log_pass").equals(Encrypt.crypt(oldPassword))) {
				// 原密码不正确
				request.setAttribute("ok", "fail1");
			} else {
				String pass = "";
				String userPassword = request.getParameter("userPassword");
				String checkPassword = request.getParameter("checkPassword");
				// 验证两次密码的统一性
				if (userPassword != null && !"".equals(userPassword)
						&& checkPassword != null && !"".equals(checkPassword)
						&& userPassword.equals(checkPassword)) {
					pass = checkPassword;
				}
				HashMap<String, Object> passMap = new HashMap<String, Object>();
				passMap.put("log_pass", Encrypt.crypt(pass));
				// 修改密码
				if (pass != null && !"".equals(pass) && !"".equals(pass.trim())) {
					BigDecimal id = new BigDecimal(user.get("id").toString());
					dataBaseControl.updateByClass(sysuser.class,passMap, id);
					user.put("log_pass", Encrypt.crypt(pass));
					session.setAttribute("user", user);
				} else {
					request.setAttribute("ok", "fail2");
				}
			}
		}
		return "/sysAdmin/modifypass.jsp";
	}

	/**
	 * 登出系统
	 **/
	public String logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		return "/sysAdmin/login.jsp";
	}
	
	
	
	public void getbaseinfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
		String loginerId = String.valueOf(user.get("base_info_id"));
//		String loginerDeptId = String.valueOf(user.get("dept_id"));
//		String loginerDutyId = String.valueOf(user.get("duty_id"));
		
		Map baseinfo = dataBaseControl.getOneResultSet("select t.*,m.branchname from hr_base_info t,mrbranch m where t.dept_id = m.id and t.id = ?", new Object[]{user.get("base_info_id")});
		request.setAttribute("baseinfo", baseinfo);
		//获得最新动态信息
		Page page = dataBaseControl.getPageResultSet("select t.*,round(sysdate-t.input_date,0)cha from gm_info t order by t.input_date desc, t.id desc", null, 1, 10);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
	}
	
	
	public String getNotice(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map user = (Map)request.getSession().getAttribute("user");
		String loginerId = String.valueOf(user.get("base_info_id"));
		String deptId = user.get("branchid").toString();
		
//		String loginerDeptId = String.valueOf(user.get("dept_id"));
//		String loginerDutyId = String.valueOf(user.get("duty_id"));
		
		Map baseinfo = dataBaseControl.getOneResultSet("select t.*,m.branchname from hr_base_info t,mrbranch m where t.dept_id = m.id and t.id = ?", new Object[]{user.get("base_info_id")});
		request.setAttribute("baseinfo", baseinfo);
		//获得最新动态信息
		Page page = dataBaseControl.getPageResultSet("select t.*,round(sysdate-t.input_date,0)cha from gm_info t order by t.input_date desc, t.id desc", null, 1, 10);
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		//配给执行
		List purexecutemap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 392 and s.id = ?", new Object[]{user.get("id")});//55
		
		
		
		
		if(purexecutemap != null && !purexecutemap.isEmpty())
		{
            String sql = "";
			sql = "select t.id, max(t.bh)bh,max(t.apply_date)apply_date, max((select m.branchname from mrbranch m where t.apply_dept = m.id)) dept, max((select b.name from hr_base_info b where t.apply_person = b.id)) personname, max((select p.name from pr_project p where t.prj_id = p.id(+))) proname from gm_ration_apply t,gm_ration_item r  where t.spzt = '2' and t.id = r.apply_id and r.sqsl > r.hasnum  group by t.id order by t.id desc ";
			
			List countlist = dataBaseControl.getOutResultSet(sql, null);
			int count = countlist.size();
			if(count!=0)
			{
				request.setAttribute("haspurexeNotice", "1");
				request.setAttribute("purexecount", count);	
			}
		}
		
		
		
		
		//配给变更////////////////////////////////////////////////////////////////////
		
        List purmodifymap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 448 and s.id = ?", new Object[]{user.get("id")});
		
		if(purmodifymap != null && !purmodifymap.isEmpty())
		{
			String duty_id = baseinfo.get("duty_id").toString();
			String sql = "";
			if(duty_id.equals("4"))
			{
				sql = "select count(t.id)count from gm_purchase_modify t,gm_purchase r where t.ration_apply_id = r.ration_apply_id and t.spzt = '2' and r.dept_id != 4 ";
			}
			else if(duty_id.equals("3"))
			{
				sql = "select count(t.id)count from gm_purchase_modify t,gm_purchase r where t.ration_apply_id = r.ration_apply_id and t.spzt = '2' and r.dept_id = 4";
			}
			else
			{
				sql = "select count(t.id)count from gm_purchase_modify t where t.spzt = '1' ";
			}
			
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasPurModifyNotice", "1");
				request.setAttribute("purmodifycount", countmap.get("count"));	
			}
		}
		
		
		
//款项支付审批////////////////////////////////////////////////////////////////////
		
        List willpaymap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 498 and s.id = ?", new Object[]{user.get("id")});
		
		if(willpaymap != null && !willpaymap.isEmpty())
		{   Map countmap=null;
			String duty_id = baseinfo.get("duty_id").toString();
			String dept_id = baseinfo.get("dept_id").toString();
			String sql = "";
			
			if(dept_id.equals("3")||dept_id.equals("7")||dept_id.equals("9"))//综合办	,工程部，成都公司
			{
				sql = "select count(t.id)count from fi_payfor t where t.payforstate = '1' and dept_id=? ";
				countmap = dataBaseControl.getOneResultSet(sql,new Object[]{dept_id});
			}
			if(dept_id.equals("6")&&duty_id.equals("1"))//会计
			{
				sql = "select count(t.id)count from fi_payfor t where t.payforstate = '2' ";
				countmap = dataBaseControl.getOneResultSet(sql,null);
			}
			
			if(duty_id.equals("4")||dept_id.equals("5"))//崔总
			{
				sql = "select count(t.id)count from fi_payfor t where t.payforstate = '5' ";
				countmap = dataBaseControl.getOneResultSet(sql,null);
			}
			
			if(dept_id.equals("6")&&duty_id.equals("2"))//财务主管
			{
				sql = "select count(t.id)count from fi_payfor t where t.payforstate = '4' ";
				countmap = dataBaseControl.getOneResultSet(sql,null);
			}
			
			if(duty_id.equals("5")||dept_id.equals("1"))//向总
			{
				sql = "select count(t.id)count from fi_payfor t where t.payforstate in('1','6') and dept_id=? ";
				countmap = dataBaseControl.getOneResultSet(sql,new Object[]{dept_id});
			}
			if(!dept_id.equals("6")&&duty_id.equals("2"))//部门经理
			{
				sql = "select count(t.id)count from fi_payfor t where payforstate='1' and dept_id=? ";
				countmap = dataBaseControl.getOneResultSet(sql,new Object[]{dept_id});
			}
			
			int count = Integer.parseInt(countmap.get("count").toString());
			
			if(count!=0)
			{
				request.setAttribute("haspayforNotice", "1");
				request.setAttribute("payforcount", countmap.get("count"));	
			
			}
		}
		
//款项支付分配付款
		
        List willpayfpmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 492 and s.id = ?", new Object[]{user.get("id")});
		
		if(willpayfpmap != null && !willpayfpmap.isEmpty())
		{
			String duty_id = baseinfo.get("duty_id").toString();
			String dept_id = baseinfo.get("dept_id").toString();
			String sql = "";
			
			
				sql = "select count(t.id)count from fi_payfor t where t.payforstate = '3' and t.p_id =? ";
			
			
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("haspayforfpNotice", "1");
				request.setAttribute("payforfpcount", countmap.get("count"));	
			}
		}
		
		//收发文确认
        List filemap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 535 and s.id = ?", new Object[]{user.get("id")});
		
		if(filemap != null && !filemap.isEmpty())
		{
			String duty_id = baseinfo.get("duty_id").toString();
			String dept_id = baseinfo.get("dept_id").toString();
			String sql = "";
			
			sql = "select count(t.id)count from gm_receive_file t where t.state = '1' and t.p_id =? ";
			
			
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{baseinfo.get("id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasfileNotice", "1");
				request.setAttribute("payfilecount", countmap.get("count"));	
			}
		}
		
		
		//印章借阅审核
        List yinzhangmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 553 and s.id = ?", new Object[]{user.get("id")});
		
		if(yinzhangmap != null && !yinzhangmap.isEmpty())
		{
			String duty_id = baseinfo.get("duty_id").toString();
			String dept_id = baseinfo.get("dept_id").toString();
			String sql = "";
			
			Map countmap = new HashMap();
				
			
			if(duty_id.equals("2"))
			{	
				sql = "select count(t.id)count from gm_seal_borrow t where t.state = '1' and t.dept_id =? ";
				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
			}
			
			if(duty_id.equals("4"))//崔总
			{
//				dept_id = "5";	
//				sql = "select count(t.id)count from gm_seal_borrow t where t.state = '1' and t.dept_id =? ";
//				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
				sql = "select count(t.id)count from gm_seal_borrow t where t.state = '2' ";
				countmap = dataBaseControl.getOneResultSet(sql, null);
				
			}
			if(duty_id.equals("5"))//向总
			{
				dept_id = "9";	
				sql = "select count(t.id)count from gm_seal_borrow t where t.state = '1' and t.dept_id =? ";
				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
				
			}
//			if(duty_id.equals("3"))//赵总
//			{	
//				sql = "select count(t.id)count from gm_seal_borrow t where t.state = '2' ";
//				countmap = dataBaseControl.getOneResultSet(sql, null);
//			}
			int count = 0;
			if(countmap.size()!=0){
				count = Integer.parseInt(countmap.get("count").toString());
			}
			if(count!=0)
			{
				if(!duty_id.equals("3")){
					request.setAttribute("hasyinzhangNotice", "1");
					request.setAttribute("yinzhangcount", countmap.get("count"));
				}
			}
		}
		
		
		
		
//		if(yinzhangmap != null && !yinzhangmap.isEmpty())
//		{
//			String duty_id = baseinfo.get("duty_id").toString();
//			String dept_id = baseinfo.get("dept_id").toString();
//			String sql = "";
//			
//			Map countmap = new HashMap();
//				
//			
//			if(duty_id.equals("2"))
//			{	
//				sql = "select count(t.id)count from gm_seal_borrow t where t.state = '1' and t.dept_id =? ";
//				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
//			}
//			
//			if(duty_id.equals("4"))//崔总
//			{
//				dept_id = "5";	
//				sql = "select count(t.id)count from gm_seal_borrow t where t.state = '1' and t.dept_id =? ";
//				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
//				
//			}
//			if(duty_id.equals("5"))//向总
//			{
//				dept_id = "9";	
//				sql = "select count(t.id)count from gm_seal_borrow t where t.state = '1' and t.dept_id =? ";
//				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
//				
//			}
//			if(duty_id.equals("3"))//赵总
//			{	
//				sql = "select count(t.id)count from gm_seal_borrow t where t.state = '2' ";
//				countmap = dataBaseControl.getOneResultSet(sql, null);
//			}
//				
//			int count = Integer.parseInt(countmap.get("count").toString());
//			if(count!=0)
//			{
//				request.setAttribute("hasyinzhangNotice", "1");
//				request.setAttribute("yinzhangcount", countmap.get("count"));	
//			}
//		}
		
		
		
		
		//获得待办事项的提醒
		
		//配给申请审批////////////////////////////////////////////////////////////////////
		
		List pr_map = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 380 and s.id = ?", new Object[]{user.get("id")});
		
		if(pr_map != null && !pr_map.isEmpty())
		{
			String duty_id = baseinfo.get("duty_id").toString();
			String dept_id = baseinfo.get("dept_id").toString();
			String sql = "";
			Map countmap =new HashMap();
			if(duty_id.equals("4"))//崔总
			{
				sql = "select count(t.id)count from gm_ration_apply t where (t.spzt='5' and t.apply_dept !=4) or (t.spzt='1' and t.apply_dept='10') ";
				countmap = dataBaseControl.getOneResultSet(sql, null);
				
			}
			if(duty_id.equals("5"))//李存
			{
				sql = "select count(t.id)count from gm_ration_apply t where t.spzt='1' and t.apply_dept='9' ";
				countmap = dataBaseControl.getOneResultSet(sql, null);
				
			}
			if(duty_id.equals("3"))//赵总
			{
				sql = "select count(t.id)count from gm_ration_apply t where t.spzt='5' and t.apply_dept in(4,5) ";
				countmap = dataBaseControl.getOneResultSet(sql, null);
			}
			if(duty_id.equals("2"))//部门经理
			{
				sql = "select count(t.id)count from gm_ration_apply t where t.spzt='1' and t.apply_dept=?";
				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
			}
			
			if(duty_id.equals("1"))//库管
			{
				sql = "select count(t.id)count from gm_ration_apply t where t.spzt='6'and t.apply_dept=? ";
				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
			}
			
			if(countmap.size()!=0){
				int count = Integer.parseInt(countmap.get("count").toString());
				if(count!=0)
				{
					request.setAttribute("hasPrApplyNotice", "1");
					request.setAttribute("prapplycount", countmap.get("count"));	
				}
			}
		}
		
		//财务报帐
		List<Map<String,Object>>  financialActMap = dataBaseControl.getOutResultSet("select count(gt.id) notiNum from fi_financial_account gt where 1=1  and gt.personid!=gt.nextapproverid " +
				"and trim(gt.appstate)!=(select co.dmz from (select * from code where dmlb='financial_account_status' order by plsx desc) co where rownum<2) " + 
				"and gt.nextapproverid in (select id from hr_base_info hbi where 1=1 and hbi.id=?)", new Object[]{loginerId});
		request.setAttribute("financialActNotiNum", getNotiNum(financialActMap));
		
        //项目计划////////////////////////////////////////////////////////////////////
		List pr_planmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 428 and s.id = ?", new Object[]{user.get("id")});
		if(pr_planmap != null && !pr_planmap.isEmpty())
		{
			String sql = "select count(t.id)count from pr_plan t where  t.pr_plan_state='1' ";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasPrPlanApplyNotice", "1");
				request.setAttribute("prplanapplycount", countmap.get("count"));	
			}
		}
		
		
//配给执行////////////////////////////////////////////////////////////////////
		
		List gm_purchasemap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 433 and s.id = ?", new Object[]{user.get("id")});
		
		if(gm_purchasemap != null && !gm_purchasemap.isEmpty())
		{   Map countmap=null;
			String duty_id = baseinfo.get("duty_id").toString();
			String dept_id = baseinfo.get("dept_id").toString();
			String sql = "";
			if(duty_id.equals("4"))
			{
				sql = "select count(t.id)count from gm_purchase t where t.spzt = '2' and t.dept_id != 4  ";
				countmap = dataBaseControl.getOneResultSet(sql, null);
			}
			else if(duty_id.equals("3"))
			{
				sql = "select count(t.id)count from gm_purchase t where t.spzt = '2' and  t.dept_id = 4 ";
				countmap = dataBaseControl.getOneResultSet(sql,null);
			}
			else
			{
				sql = "select count(t.id)count from gm_purchase t where t.spzt = '1' and t.dept_id=?";
				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
			}
			 
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasPurchaseApplyNotice", "1");
				request.setAttribute("purchaseapplycount", countmap.get("count"));	
			}
		}
		
		
		
		
		//请假//////////////////////////////////////////////////////////////////////////////
		List absencemap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 82 and s.id = ?", new Object[]{user.get("id")});
		if(absencemap != null && !absencemap.isEmpty())
		{   
			String duty_id = baseinfo.get("duty_id").toString();
			request.setAttribute("hasAbsenceNotice", "1");
			String sql=null;
			if(duty_id.equals("4"))   //崔总
			{
				 sql="select count(t.id)count from hr_absence t where t.absence_state in('1','2') and (t.dept_id in (select s1.dataid from sys_data_scope s1 where s1.roleid in (select ss1.rolesid from sysuserroles ss1 where ss1.userid = ? ) and s1.data_scope_type = 0) or t.p_id in (select s2.dataid from sys_data_scope s2 where s2.roleid in (select ss2.rolesid from sysuserroles ss2 where ss2.userid = ? ) and s2.data_scope_type = 1))"; 
				
			}
			else if(duty_id.equals("3"))//赵总
			{
				 sql="select count(t.id)count from hr_absence t where t.absence_state in('2') and (t.dept_id in (select s1.dataid from sys_data_scope s1 where s1.roleid in (select ss1.rolesid from sysuserroles ss1 where ss1.userid = ? ) and s1.data_scope_type = 0) or t.p_id in (select s2.dataid from sys_data_scope s2 where s2.roleid in (select ss2.rolesid from sysuserroles ss2 where ss2.userid = ? ) and s2.data_scope_type = 1))"; 
				
			}
			else
			{
				 sql="select count(t.id)count from hr_absence t where t.absence_state in('1') and (t.dept_id in (select s1.dataid from sys_data_scope s1 where s1.roleid in (select ss1.rolesid from sysuserroles ss1 where ss1.userid = ? ) and s1.data_scope_type = 0) or t.p_id in (select s2.dataid from sys_data_scope s2 where s2.roleid in (select ss2.rolesid from sysuserroles ss2 where ss2.userid = ? ) and s2.data_scope_type = 1))"; 
				
			}
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("id"),user.get("id")});
			request.setAttribute("absencecount", countmap.get("count"));
		}
		
		//用车////////////////////////////////////////////////////////////////////////////////
		//(1)部门经理审批
		List carapplymap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 187 and s.id = ?", new Object[]{user.get("id")});
		if(carapplymap != null && !carapplymap.isEmpty())
		{
			String dept_id = baseinfo.get("dept_id").toString();
			request.setAttribute("hasCarapplyNotice", "1");
			
			String sql="select count(t.id)count from gm_carapply t where t.carapply_state in('1') and t.dept_id = ?"; 
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
			request.setAttribute("carapplycount", countmap.get("count"));
			
		}
		//(2)综合办审批
		List carapplymap1 = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 212 and s.id = ?", new Object[]{user.get("id")});
		if(carapplymap1 != null && !carapplymap1.isEmpty())
		{
			request.setAttribute("haszhbCarapplyNotice", "1");
			String sql="select count(t.id)count from gm_carapply t where t.carapply_state in('2') "; 
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			request.setAttribute("zhbcarapplycount", countmap.get("count"));
			
		}
		//(3)驾驶员填写用车完成
		List carapplymap2 = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 194 and s.id = ?", new Object[]{user.get("id")});
		if(carapplymap2 != null && !carapplymap2.isEmpty())
		{
			request.setAttribute("hasdriverCarapplyNotice", "1");
			String sql="select count(t.id)count from gm_carapply t where t.carapply_state in('4') and t.driver = ? "; 
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{baseinfo.get("id")});
			request.setAttribute("drivecarapplycount", countmap.get("count"));
			
		}
		//(4)用车者确认用车里程(拉人)
		List carapplymap3 = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 177 and s.id = ?", new Object[]{user.get("id")});
		if(carapplymap3 != null && !carapplymap3.isEmpty())
		{
			String sql="select count(t.id)count from gm_carapply t where t.carapply_state in('6') and t.p_id = ? and t.usetype = '1'"; 
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{baseinfo.get("id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hassureCarapplyNotice", "1");
				request.setAttribute("surecarapplycount", countmap.get("count"));	
			}
			
		}
		
		
		//(4)用车者确认用车里程(拉货)
		List carapplymap4 = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 468 and s.id = ?", new Object[]{user.get("id")});
		if(carapplymap4 != null && !carapplymap4.isEmpty())
		{
			String sql="select count(t.id)count from gm_carapply t where t.carapply_state in('6') and t.p_id = ? and t.usetype = '0'"; 
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{baseinfo.get("id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hassureCarapplyHuNotice", "1");
				request.setAttribute("surehucarapplycount", countmap.get("count"));	
			}
			
		}
		
		
		//考勤///////////////////////////////////////////////////////////////////////////////////////////////
		List kaoqinmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 135 and s.id = ?", new Object[]{user.get("id")});
		if(kaoqinmap != null && !kaoqinmap.isEmpty())
		{
			String sql = "select count(t.id)count from hr_kaoqin_audit t where t.state='1' and t.dept_id=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{baseinfo.get("dept_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasKaoqinNotice", "1");
				request.setAttribute("kaoqincount", countmap.get("count"));	
			}
		}
		
		
		//开具发票////////////////////////////////////////////////////////////////////
		//(1)部门经理审批
		List billmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 206 and s.id = ?", new Object[]{user.get("id")});
		if(billmap != null && !billmap.isEmpty())
		{
			String deptid = baseinfo.get("dept_id").toString();
			
			if(deptid.equals("5"))
			{
				deptid = "10";
			}
			
			String sql = "select count(t.id)count from fi_bill t where t.bill_state='1' and t.dept_id=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{deptid});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasBillNotice", "1");
				request.setAttribute("billcount", countmap.get("count"));	
			}
		}
		
		//(2)财务主管审批
		List billmap1 = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 213 and s.id = ?", new Object[]{user.get("id")});
		if(billmap1 != null && !billmap1.isEmpty())
		{   
		    String dept_id = baseinfo.get("dept_id").toString();
			String sql = "select count(t.id)count from fi_bill t where t.bill_state='2' ";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hascwBillNotice", "1");
				request.setAttribute("cwbillcount", countmap.get("count"));	
			}
		}
		//(3)开票
		List billmap2 = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 210 and s.id = ?", new Object[]{user.get("id")});
		if(billmap2 != null && !billmap2.isEmpty())
		{   String dept_id = baseinfo.get("dept_id").toString();
			String sql = "select count(t.id)count from fi_bill t where t.bill_state='6' and dept_id=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{dept_id});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("haskpBillNotice", "1");
				request.setAttribute("kpbillcount", countmap.get("count"));	
			}
		}
		
		
		
		//---------人事流程提醒----------
		//---应聘流程---
		//面试提醒
		List applyHr = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 103 and s.id = ?", new Object[]{user.get("id")}); 
		if(applyHr != null && !applyHr.isEmpty())
		{
			String sql = "select count(hb.id)count from hr_base_info hb where hb.hr_type='0' and hb.dept_id ="+deptId;
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasAppHrNotice", "1");
				request.setAttribute("AppHrCount", countmap.get("count"));	
			}
		}
		
		//人事
		List applyHrmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 125 and s.id = ?", new Object[]{user.get("id")}); 
		if(applyHrmap != null && !applyHrmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='1' and ho.hr_state='0' and ho.istwh='0'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasApplyHrNotice", "1");
				request.setAttribute("ApplyHrCount", countmap.get("count"));	
			}
		}
		//部门经理
		List applyDmmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 125 and s.id = ?", new Object[]{user.get("id")}); 
		
		if(applyDmmap != null && !applyDmmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='1' and ho.hr_state='1' and ho.istwh='0' and ho.p_id in (select hb.id from hr_base_info hb where hb.dept_id=? and hb.hr_type in (0,1))";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{deptId});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasApplyDmNotice", "1");
				request.setAttribute("ApplyDmCount", countmap.get("count"));	
			}
		}
		//总经理
		List applyGmmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 125 and s.id = ?", new Object[]{user.get("id")}); 
		if(applyGmmap != null && !applyGmmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='1' and ho.hr_state='2' and ho.istwh='0'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasApplyGmNotice", "1");
				request.setAttribute("ApplyGmCount", countmap.get("count"));	
			}
		}
		
		//---入职流程---
		//人事
		List checkHrmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 148 and s.id = ?", new Object[]{user.get("id")}); 
		if(checkHrmap != null && !checkHrmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='2' and ho.hr_state='5' and ho.istwh='0'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasCheckHrNotice", "1");
				request.setAttribute("TryCheckCount", countmap.get("count"));	
			}
		}
		
		List tryHrmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 148 and s.id = ?", new Object[]{user.get("id")}); 
		if(tryHrmap != null && !tryHrmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='2' and ho.hr_state='0' and ho.istwh='0'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasTryHrNotice", "1");
				request.setAttribute("TryHrCount", countmap.get("count"));	
			}
		}
		//部门经理
		List tryDmmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 148 and s.id = ?", new Object[]{user.get("id")}); 
		if(tryDmmap != null && !tryDmmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='2' and ho.hr_state='1' and ho.istwh='0' and ho.p_id in (select hb.id from hr_base_info hb where hb.dept_id=?)";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{deptId});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasTryDmNotice", "1");
				request.setAttribute("TryDmCount", countmap.get("count"));	
			}
		}
		//总经理
		List tryGmmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 148 and s.id = ?", new Object[]{user.get("id")}); 
		if(tryGmmap != null && !tryGmmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='2' and ho.hr_state='2' and ho.istwh='0'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasTryGmNotice", "1");
				request.setAttribute("TryGmCount", countmap.get("count"));	
			}
		}
		
		//---转正流程---
		//转正申请
		List regularmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 156 and s.id = ?", new Object[]{user.get("id")}); 
		if(regularmap != null && !regularmap.isEmpty())
		{
			String sql = "select t.jr_time,round(months_between(sysdate,t.jr_time),0)cha from hr_base_info t where t.id=?";
			Map jrMap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			BigDecimal cha = (BigDecimal) jrMap.get("cha");
			int monthcha = cha.intValue();
			if(monthcha>=3){
				Map countmap = dataBaseControl.getOneResultSet("select count(id)count from hr_base_info t where t.hr_type=6 and t.id=?", new Object[]{user.get("base_info_id")});
				int count = Integer.parseInt(countmap.get("count").toString());
				if(count!=0){
					request.setAttribute("hasRegularApplyNotice", "1");
				}
			}
		}
		
		//转正考评职员
		List evalmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 157 and s.id = ?", new Object[]{user.get("id")}); 
		if(evalmap != null && !evalmap.isEmpty())
		{
			String sql = "select count(he.id)count from hr_evaluation he where he.ep_id=? and he.ev_time is null";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasEvalNotice", "1");
				request.setAttribute("EvalCount", countmap.get("count"));	
			}
		}
		
		//转正考评人事
		List evalmap2 = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 157 and s.id = ?", new Object[]{user.get("id")}); 
		if(evalmap2 != null && !evalmap2.isEmpty())
		{
			String sql = "select count(he.id)count from hr_evaluation he where he.s_total is null and he.ev_time is not null";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasEvalHrNotice", "1");
				request.setAttribute("EvalHrCount", countmap.get("count"));	
			}
		}
		
		//延长试用期
		List ycsymap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 148 and s.id = ?", new Object[]{user.get("id")}); 
		if(ycsymap != null && !ycsymap.isEmpty())
		{
			String sql = "select count(ha.id)count from hr_apply ha where ha.ycsyq is not null and ha.appstate='0'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasYcsyNotice", "1");
				request.setAttribute("YcsyCount", countmap.get("count"));	
			}
		}
		
		//转正审批
		//人事
		List regularHrmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 155 and s.id = ?", new Object[]{user.get("id")}); 
		if(regularHrmap != null && !regularHrmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='6' and ho.hr_state='0'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasRegularHrNotice", "1");
				request.setAttribute("RegularHrCount", countmap.get("count"));	
			}
		}
		
		//部门经理
		List regularDmmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 155 and s.id = ?", new Object[]{user.get("id")}); 
		if(regularDmmap != null && !regularDmmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='6' and ho.hr_state='1' and ho.p_id in (select hb.id from hr_base_info hb where hb.dept_id=?)";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{deptId});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasRegularDmNotice", "1");
				request.setAttribute("RegularDmCount", countmap.get("count"));	
			}
		}
		
		//总经理
		List regularGmmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 155 and s.id = ?", new Object[]{user.get("id")}); 
		if(regularGmmap != null && !regularGmmap.isEmpty())
		{
			String sql = "select count(ho.id)count from hr_opinion ho where ho.hr_type='6' and ho.hr_state='2'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasRegularGmNotice", "1");
				request.setAttribute("RegularGmCount", countmap.get("count"));	
			}
		}
		
		//离职提醒
		//部门经理审批
		List dimDmOpp = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 482 and s.id = ?", new Object[]{user.get("id")}); 
		if(dimDmOpp != null && !dimDmOpp.isEmpty())
		{
			String sql = "select count(hd.id)count from hr_dimission hd where hd.appstate='1' and hd.dept_id=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{deptId});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasdimDmOppNotice", "1");
				request.setAttribute("dimDmOppCount", countmap.get("count"));	
			}
		}
				
		//工作交接
		List dimGmFi = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 482 and s.id = ?", new Object[]{user.get("id")}); 
		if(dimGmFi != null && !dimGmFi.isEmpty())
		{
			String sql = "select count(hd.id)count from hr_dimission hd where hd.appstate='4'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasdimGmFiNotice", "1");
				request.setAttribute("dimGmFiCount", countmap.get("count"));	
			}
		}
//		//财务
//		List dimFi = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 482 and s.id = ?", new Object[]{user.get("id")}); 
//		if(dimFi != null && !dimFi.isEmpty())
//		{
//			String sql = "select count(id)count from hr_dimission hd where hd.appstate='1'";
//			Map countmap = dataBaseControl.getOneResultSet(sql, null);
//			int count = Integer.parseInt(countmap.get("count").toString());
//			if(count!=0)
//			{
//				request.setAttribute("hasdimFiNotice", "1");
//				request.setAttribute("dimFiCount", countmap.get("count"));	
//			}
//		}
		//人事审批
		List dimHrOpp = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 482 and s.id = ?", new Object[]{user.get("id")}); 
		if(dimHrOpp != null && !dimHrOpp.isEmpty())
		{
			String sql = "select count(hd.id)count from hr_dimission hd where hd.appstate='2'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasdimHrOppNotice", "1");
				request.setAttribute("dimHrOppCount", countmap.get("count"));	
			}
		}
		
		//人事确认
		List dimHr = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 482 and s.id = ?", new Object[]{user.get("id")}); 
		if(dimHr != null && !dimHr.isEmpty())
		{
			String sql = "select count(hd.id)count from hr_dimission hd where hd.appstate='5'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasdimHrNotice", "1");
				request.setAttribute("dimHrCount", countmap.get("count"));	
			}
		}
		
		//总经理
		List dimGem = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 486 and s.id = ?", new Object[]{user.get("id")}); 
		if(dimGem != null && !dimGem.isEmpty())
		{
			String sql = "select count(hd.id)count from hr_dimission hd where hd.appstate='3'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasdimGemNotice", "1");
				request.setAttribute("dimGemCount", countmap.get("count"));	
			}
		}
		
		//费用开支计划审批
		//部门审批
		List expDmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 292 and s.id = ?", new Object[]{user.get("id")}); 
		if(expDmList.size() != 0)
		{
			String sql = "select count(gt.id)count from fi_expenses_plan gt where gt.appstate='0' and gt.subflag='1' and gt.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasExpDmNotice", "1");
				request.setAttribute("ExpDmCount", countmap.get("count"));	
			}
		}
		
		//领导审批
		List expGmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 292 and s.id = ?", new Object[]{user.get("id")}); 
		if(expGmList.size() != 0)
		{
			String sql = "select count(gt.id)count from fi_expenses_plan gt where gt.appstate='1' and gt.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasExpGmNotice", "1");
				request.setAttribute("ExpGmCount", countmap.get("count"));	
			}
		}
		
		/**
		//任务书审批
		List dsTaskmap = (List)dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 430 and s.id = ?", new Object[]{user.get("id")}); 
		if(dsTaskmap != null && !dsTaskmap.isEmpty())
		{
			String sql="";
			Map countmap=null;
			if((user.get("duty_id")).equals("4")||user.get("duty_id")=="4"){
				sql = "select count(id)count from ds_dstask dt where dt.launch_deptid='10' and dt.appstate='1'";
				countmap = dataBaseControl.getOneResultSet(sql, null);
			}
			else{
				sql = "select count(id)count from ds_dstask dt where dt.launch_deptid=? and dt.appstate='1'";
				countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("branchid")});
			}
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasDsTaskNotice", "1");
				request.setAttribute("DsTaskCount", countmap.get("count"));	
			}
		}
		
		//任务书接件
		List dsFzrList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 464 and s.id = ?", new Object[]{user.get("id")}); 
		if(dsFzrList.size() != 0)
		{
			String sql = "select count(id)count from ds_dstask dd where dd.task_state='2'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasDsFzrNotice", "1");
				request.setAttribute("DsFzrCount", countmap.get("count"));	
			}
		}
		
		//策划书编制
		List dsSchAddList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 324 and s.id = ?", new Object[]{user.get("id")}); 
		if(dsSchAddList.size() != 0)
		{
			String sql = "select count(id)count from ds_dstask dd where dd.task_state='3' and dd.flag='1' and dd.design_fzr=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasDsSchAddNotice", "1");
				request.setAttribute("DsSchAddCount", countmap.get("count"));	
			}
		}
		
		//策划书审批
		List dsSchemeList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 432 and s.id = ?", new Object[]{user.get("id")}); 
		if(dsSchemeList.size() != 0)
		{
			String sql = "select count(id)count from ds_scheme ds where ds.appstate='1'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasDsSchemeNotice", "1");
				request.setAttribute("DsSchemeCount", countmap.get("count"));	
			}
		}
		
		//输出清单审批
		List dsResultList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 432 and s.id = ?", new Object[]{user.get("id")}); 
		if(dsResultList.size() != 0)
		{
			String sql = "select count(id)count from ds_result ds where ds.appstate='1'";
			Map countmap = dataBaseControl.getOneResultSet(sql, null);
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasDsResultNotice", "1");
				request.setAttribute("DsResultCount", countmap.get("count"));	
			}
		}
		*/
		/*出差审批*/
		//部门审批
		List travelDmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 560 and s.id = ?", new Object[]{user.get("id")}); 
		if(travelDmList.size() != 0)
		{
			String sql = "select count(gt.id)count from gm_travel gt where gt.appstate='0' and gt.subflag='1' and gt.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasTravelDmNotice", "1");
				request.setAttribute("TravelDmCount", countmap.get("count"));	
			}
		}
		
		//领导审批
		List travelGmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 560 and s.id = ?", new Object[]{user.get("id")}); 
		if(travelGmList.size() != 0)
		{
			String sql = "select count(gt.id)count from gm_travel gt where gt.appstate='1' and gt.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasTravelGmNotice", "1");
				request.setAttribute("TravelGmCount", countmap.get("count"));	
			}
		}
		
		/*出差报账审批*/
		//部门审批
		List travelaccDmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 296 and s.id = ?", new Object[]{user.get("id")}); 
		if(travelaccDmList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_travelacc ft where ft.appstate='0' and ft.subflag='1' and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasTravelaccDmNotice", "1");
				request.setAttribute("TravelaccDmCount", countmap.get("count"));	
			}
		}
		//会计审批
		List travelaccAcList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 296 and s.id = ?", new Object[]{user.get("id")}); 
		if(travelaccAcList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_travelacc ft where ft.appstate='1' and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasTravelaccAcNotice", "1");
				request.setAttribute("TravelaccAcCount", countmap.get("count"));	
			}
		}
		//财务经理审批
		List travelaccAdList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 296 and s.id = ?", new Object[]{user.get("id")}); 
		if(travelaccAdList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_travelacc ft where ft.appstate in ('2','0') and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasTravelaccAdNotice", "1");
				request.setAttribute("TravelaccAdCount", countmap.get("count"));	
			}
		}
		//领导审批
		List travelaccGmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 296 and s.id = ?", new Object[]{user.get("id")}); 
		if(travelaccGmList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_travelacc ft where ft.appstate='3' and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasTravelaccGmNotice", "1");
				request.setAttribute("TravelaccGmCount", countmap.get("count"));	
			}
		}
		
		/*财务报账审批*/
		//部门审批
		List fiaccDmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 303 and s.id = ?", new Object[]{user.get("id")}); 
		if(fiaccDmList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_financial_account ft where ft.appstate='0' and ft.subflag='1' and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasFiaccDmNotice", "1");
				request.setAttribute("FiaccDmCount", countmap.get("count"));	
			}
		}
		//会计审批
		List fiaccAcList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 303 and s.id = ?", new Object[]{user.get("id")}); 
		if(fiaccAcList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_financial_account ft where ft.appstate='1' and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasFiaccAcNotice", "1");
				request.setAttribute("FiaccAcCount", countmap.get("count"));	
			}
		}
		//财务经理审批
		List fiaccAdList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 303 and s.id = ?", new Object[]{user.get("id")}); 
		if(fiaccAdList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_financial_account ft where ft.appstate in ('2','0') and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasFiaccAdNotice", "1");
				request.setAttribute("FiaccAdCount", countmap.get("count"));	
			}
		}
		//领导审批
		List fiaccGmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 303 and s.id = ?", new Object[]{user.get("id")}); 
		if(fiaccGmList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_financial_account ft where ft.appstate='3' and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasFiaccGmNotice", "1");
				request.setAttribute("FiaccGmCount", countmap.get("count"));	
			}
		}
		
		/*暂支申请审批*/
		//部门审批
		List fiadvDmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 297 and s.id = ?", new Object[]{user.get("id")}); 
		if(fiadvDmList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_advance ft where ft.appstate='0' and ft.subflag='1' and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasFiadvDmNotice", "1");
				request.setAttribute("FiadvDmCount", countmap.get("count"));	
			}
		}
		//财务经理审批
		List fiadvAdList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 297 and s.id = ?", new Object[]{user.get("id")}); 
		if(fiadvAdList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_advance ft where ft.appstate in ('1','0') and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasFiadvAdNotice", "1");
				request.setAttribute("FiadvAdCount", countmap.get("count"));	
			}
		}
		//领导审批
		List fiadvGmList = dataBaseControl.getOutResultSet("select * from sysuser s, sysuserroles r, sysfuncrole f where s.id = r.userid and r.rolesid = f.roleid and f.funcid = 297 and s.id = ?", new Object[]{user.get("id")}); 
		if(fiadvGmList.size() != 0)
		{
			String sql = "select count(ft.id)count from fi_advance ft where ft.appstate='2' and ft.nextapproverid=?";
			Map countmap = dataBaseControl.getOneResultSet(sql, new Object[]{user.get("base_info_id")});
			int count = Integer.parseInt(countmap.get("count").toString());
			if(count!=0)
			{
				request.setAttribute("hasFiadvGmNotice", "1");
				request.setAttribute("FiadvGmCount", countmap.get("count"));	
			}
		}
		
		return "/sysAdmin/desktop.jsp";
	}

	/**
	 * 功能：获取 提醒审批数量   
	 * @参数： @param travelReqMap
	 * @参数： @return 提醒审批数量   
	 * @return Object   
	 * @throws
	 */
	private int getNotiNum(List<Map<String, Object>> travelReqMap) {
		if(travelReqMap == null || travelReqMap.get(0) == null || travelReqMap.get(0).get("notinum") == null)
			return -1;
		int notiNum = Integer.valueOf(String.valueOf(travelReqMap.get(0).get("notinum"))); 
		return notiNum > 0 ? notiNum : -1;
	}

	/**
	 * 判断钥匙盘和用户相符性
	 * 
	 * @param user
	 *            登录的用户
	 * @param keyValue
	 *            钥匙盘获取的值
	 * @return 相符：true
	 */
	private boolean checkKey(Map user, String keyValue, String sessionid,String inStr) {
		SoftKey mysoftkey = new SoftKey();
		boolean result = false;
		String outstring = mysoftkey.StrEnc(sessionid,
				"1234567890ABCDEF1234567890ABCDEF");
		if (outstring.equals(inStr))
			if (Encrypt.crypt(
					"&%@*" + user.get("id") + "|*@~" + user.get("log_name")
							+ "$(}?").equals(keyValue))
				result = true;
		return result;
	}
	
	

}
