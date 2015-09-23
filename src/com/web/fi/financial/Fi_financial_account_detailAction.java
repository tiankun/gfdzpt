package com.web.fi.financial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.map.Fi_TravelExpenseDetail;
import com.map.Fi_fina_account_dtl;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;
import com.web.common.BuildDDLUtil;

public class Fi_financial_account_detailAction extends BaseAction {
	private DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	/*刷新 报帐明细*/
	public String financialAccountSubDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if(id!= null && !"".equals(id)){
			String isAppr = request.getParameter("isAppr");
			if(isAppr != null)
				request.setAttribute("isAppr", isAppr);
			
			String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
			? "1" : request.getParameter("pageno");
			int pageNo=(new Integer(page_no)).intValue();
			int pageSize=20;
			
			Map<String,String> params = new HashMap<String,String>();
			params.put("financialaccountid", id);
			String subDetailSql = "select * from Fi_fina_account_dtl ffad where 1=1 /~ and financialaccountid={financialaccountid} ~/ order by ffad.id";
			
			XsqlFilterResult xsql = new XsqlBuilder().generateSql(subDetailSql,params); 
			Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
			if(page != null && page.getThisPageElements() != null&& page.getThisPageElements().size() > 0){
				String jsonStr = JSON.toJSONString(page.getThisPageElements());
				List<Map> finAccDtlList = JSON.parseObject(jsonStr, new TypeReference<List<Map>>(){});
				
				Fi_fina_account_dtl fi_fina_account_dtl= null;
//				String sqlArea = "select sa.id,sa.fullname from sys_area sa where 1=1 and sa.id = ?";
				String sqlStand = "select co.dmz,co.dmzmc from code co where 1=1 and co.dmz = ? and co.dmlb = 'PayType' order by co.plsx";
				
				Map<Object,Object> subDataMap = null;
				
				for (Map finAccDtl : finAccDtlList) {
					fi_fina_account_dtl = new Fi_fina_account_dtl();
					fi_fina_account_dtl = JSON.parseObject(JSON.toJSONString(finAccDtl), Fi_fina_account_dtl.class);
					
					Object payTypeID = fi_fina_account_dtl.getPaytype();
					subDataMap = BuildDDLUtil.getInstance().getDataMap(new Object[]{payTypeID},new String[]{sqlStand,"dmz","dmzmc"});
					Object valName = subDataMap.get(String.valueOf(payTypeID));
					if(valName != null)
						finAccDtl.put("payTypeName",valName);
					
/*					Object endPlaceId = fi_TravelExpenseDetail.getEndplace();
					subDataMap = BuildDDLUtil.getInstance().getDataMap(new Object[]{endPlaceId},new String[]{sqlArea,"id","fullname"});
					Object endValName = subDataMap.get(String.valueOf(endPlaceId));
					if(endValName != null)
						finAccDtl.put("endPlaceName",endValName);
					
					Object standId = fi_TravelExpenseDetail.getSubstandardsid();
					subDataMap = BuildDDLUtil.getInstance().getDataMap(new Object[]{standId},new String[]{sqlStand,"id","travel_substandards"});
					Object standValName = subDataMap.get(String.valueOf(standId));
					if(standValName != null)
						finAccDtl.put("standValName",standValName);*/
				}
				request.setAttribute("finDetail", finAccDtlList);
			}
			
/*			String sql = "select fte.*,ba.name,mr.branchname from FI_TRAVELEXPENSEACCOUNT fte , " +
					"hr_base_info ba,mrbranch mr where fte.departid = mr.id and fte.personid = ba.id and fte.id=? order by fte.id";
			List<Map<String,Object>> listMap = dataBaseControl.getOutResultSet(sql, new Object[]{id});
			if(listMap!=null && listMap.size() == 1){
				Map<String,Object> travelExpAccountMap = listMap.get(0);
				request.setAttribute("record", travelExpAccountMap);
			}*/
		}
		
		return "/fi/financial/fi_financial_account_detail.jsp";
	}
	
	/*ajax保存财务报帐明细*/
	public void saveDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String detailData = request.getParameter("detailData");
		String result = "";
		if(detailData != null && !"".equals(detailData)){
			Map<String,String> map = JSON.parseObject(detailData, new TypeReference<Map<String,String>>(){});
			
			if(map != null && map.get("parentID")!=null){
				String parentID = map.get("parentID");
				if(parentID != null && !"".equals(parentID.trim())){
					Fi_fina_account_dtl financialAccountDetail  = JSON.parseObject(detailData, Fi_fina_account_dtl.class); 
					if(financialAccountDetail != null){
						financialAccountDetail.setAccid(Long.valueOf(parentID));
						dataBaseControl.insertByBean(financialAccountDetail, dataBaseControl.getSeq(Fi_fina_account_dtl.class));
						
						String sql = "select sum(paymoney) paymoneyTotal from fi_fina_account_dtl where financialaccountid=?";
						Map<String,Object> totalMap = dataBaseControl.getOneResultSet(sql,  new Object[]{parentID});
						if(totalMap != null && totalMap.get("paymoneytotal") != null){
							result = JSON.toJSONString(totalMap);
						}
					}
				}
			}
						
		}
		setAjaxInfo(response, result);
	}
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/fi/financial/fi_financial_account_detailSelect.jsp";
		return "/fi/financial/fi_financial_account_detailList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/fi/financial/fi_financial_account_detailDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Fi_financial_account_detail where 1=1 /~ and id={id} ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			int i =dataBaseControl.insertByBean(getMapObject(request));			
//			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/fi/financial/fi_financial_accountDtl.jsp";
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
		return "/fi/financial/fi_financial_account_detailDtl.jsp";
	}
	public void delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Fi_fina_account_dtl WHERE ID in (?)", new Object[]{s_id});
	}
	
	@SuppressWarnings("static-access")
	private void buildDDL(HttpServletRequest request) throws Exception{

	}

}