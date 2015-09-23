package com.web.hr.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.map.Hr_opinion;
import com.map.Hr_test;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.sysFrams.web.BaseAction;

public class Hr_testAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		Map map = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id = ? ", new Object[]{id});
		request.setAttribute("base_info", map);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/hr/process/hr_testSelect.jsp";
		return "/hr/process/hr_testList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		String showFlag = request.getParameter("showFlag")==null?"":request.getParameter("showFlag");
		Map map = dataBaseControl.getOneResultSet("select * from hr_base_info t where t.id=?", new Object[]{id});
		String hr_type = (String) map.get("hr_type");
		Page page = null;
		Page page1 = null;
		Page page2 = null;
		Page page3 = null;
		Map record = new HashMap();
		if(hr_type=="0"||hr_type.equals("0")){
			page=search(request,1,1);
		}
		else{
			page=search(request,1,1);
			page1=search1(request,1,1);
			page2=search2(request,1,1);
			page3=search3(request,1,1);
			ArrayList<Map> recordList= (ArrayList)page.getThisPageElements();
			if(recordList.size()!=0){
			record = (Map)recordList.toArray()[0];
			}
			else{
				return "/hr/info/notinput.jsp";
			}
			ArrayList<Map> record1= (ArrayList)page1.getThisPageElements();
			if(record1.size()!=0){
				record.putAll(record1.get(0));
			}
			ArrayList<Map> record2= (ArrayList)page2.getThisPageElements();
			if(record2.size()!=0){
				record.putAll(record2.get(0));
			}
			ArrayList<Map> record3= (ArrayList)page3.getThisPageElements();
			if(record3.size()!=0){
				record.putAll(record3.get(0));
			}
		}
		if(page.getThisPageElements().size()==0){
			return "/hr/info/rep.jsp";
		}
		else{
		request.setAttribute("showFlag", showFlag);
		request.setAttribute("record", record);
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/process/hr_testDtl.jsp";
		}
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Hr_test where 1=1 /~ and p_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select ho.hr_type a_type,ho.hr_opinion a_hr,ho.dm_opinion a_dm,ho.gm_opinion a_gm " +
				" from Hr_test ht,hr_opinion ho where 1=1 and ho.p_id=ht.p_id and ho.hr_type=1 /~ and ht.p_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page search2(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select ho.hr_type t_type,ho.hr_opinion t_hr,ho.dm_opinion t_dm,ho.gm_opinion t_gm " +
				" from Hr_test ht,hr_opinion ho where 1=1 and ho.p_id=ht.p_id and ho.hr_type=2 /~ and ht.p_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	private Page search3(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select ho.hr_type e_type,ho.hr_opinion e_hr,ho.dm_opinion e_dm,ho.gm_opinion e_gm " +
				" from Hr_test ht,hr_opinion ho where 1=1 and ho.p_id=ht.p_id and ho.hr_type=6 /~ and ht.p_id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	
	//面试信息填报
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Hr_test hrTest =  (Hr_test) getMapObject(request);
			Map param = getParameterMap(request);
			String obj = (String) param.get("chkval");
			if(!obj.equals("]}")){
				String character = JSONObject.parseObject(obj).getString("character")==null?"":JSONObject.parseObject(obj).getString("character");
				String language = JSONObject.parseObject(obj).getString("language")==null?"":JSONObject.parseObject(obj).getString("language");
				String manner = JSONObject.parseObject(obj).getString("manner")==null?"":JSONObject.parseObject(obj).getString("manner");
				String behavior = JSONObject.parseObject(obj).getString("behavior")==null?"":JSONObject.parseObject(obj).getString("behavior");
				String expression = JSONObject.parseObject(obj).getString("expression")==null?"":JSONObject.parseObject(obj).getString("expression");
				String deal_ab = JSONObject.parseObject(obj).getString("deal_ab")==null?"":JSONObject.parseObject(obj).getString("deal_ab");
				String listen = JSONObject.parseObject(obj).getString("listen")==null?"":JSONObject.parseObject(obj).getString("listen");
				String thinking = JSONObject.parseObject(obj).getString("thinking")==null?"":JSONObject.parseObject(obj).getString("thinking");
				String form = JSONObject.parseObject(obj).getString("form")==null?"":JSONObject.parseObject(obj).getString("form");
				
				hrTest.setCharacter(character);
				hrTest.setLanguage(language);
				hrTest.setManner(manner);
				hrTest.setBehavior(behavior);
				hrTest.setExpression(expression);
				hrTest.setDeal_ab(deal_ab);
				hrTest.setListen(listen);
				hrTest.setThinking(thinking);
				hrTest.setForm(form);
			}
			int i = dataBaseControl.updateByBean(hrTest);
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/process/hr_testDtl.jsp";
	}
	
	//面试信息填报
	public String csSub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			String id = request.getParameter("id");
			String pid = request.getParameter("pid");
			String result = request.getParameter("result");
			
			dataBaseControl.beginTransaction();
			//将结果写入初试表
			dataBaseControl.executeSql("update hr_test t set t.result=? where t.id=?", new Object[]{id,result});
			
			//将意见写入意见表
			Long oid = dataBaseControl.getSeq(Hr_opinion.class);
			String opinion = (dataBaseControl.getOneResultSet("select dmzmc from code where dmlb='result' and dmz=?", new Object[]{result})).get("dmzmc").toString();
			dataBaseControl.executeSql("delete from hr_opinion t where t.p_id=? and t.hr_type=1", new Object[]{pid});
			
			//更新主表任职状态
			String istwh = "";//是否人才库
			if(result.trim().equals("1")){
				dataBaseControl.executeSql("update Hr_Base_Info t set t.hr_type = '1' where t.id=?", new Object[]{pid});
				istwh="0";
			}
			else{
				dataBaseControl.executeSql("update Hr_Base_Info t set t.hr_type = '5' where t.id=?", new Object[]{pid});
				istwh="1";
			}
			
			int i=dataBaseControl.executeSql("insert into hr_opinion (id,p_id,hr_state,hr_type,hr_opinion,istwh) values(?,?,?,?,?,?)", new Object[]{oid,pid,"1","1",opinion,istwh});
			
			
			dataBaseControl.endTransaction();
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=search(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/process/hr_csSub.jsp";
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Hr_test WHERE ID in (?)", new Object[]{s_id});
		
		return "/hr/process/Hr_test!searchList.do";
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
		request.setAttribute("character",codeTableUtil.getCodeMap("character")); 
		request.setAttribute("form",codeTableUtil.getCodeMap("form")); 
		request.setAttribute("manner",codeTableUtil.getCodeMap("manner")); 
		request.setAttribute("expression",codeTableUtil.getCodeMap("expression")); 
		request.setAttribute("result",codeTableUtil.getCodeMap("result")); 
		request.setAttribute("language",codeTableUtil.getCodeMap("language"));
		request.setAttribute("behavior",codeTableUtil.getCodeMap("behavior"));
		request.setAttribute("deal_ab",codeTableUtil.getCodeMap("deal_ab"));
		request.setAttribute("listen",codeTableUtil.getCodeMap("listen"));
		request.setAttribute("thinking",codeTableUtil.getCodeMap("thinking"));
	}

}