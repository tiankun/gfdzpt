package com.web.ds.result;

import java.math.BigDecimal;
import java.text.DateFormat;
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

import com.alibaba.fastjson.JSON;
import com.map.Ds_prc_table;
import com.map.Ds_product;
import com.map.Ds_schedule;
import com.map.Gm_ration_apply;
import com.map.Gm_ration_item;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Ds_productAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	public String searchList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String result_id = request.getParameter("result_id");
		String proj_id = request.getParameter("proj_id");
		
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=search(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("result_id", result_id);
		request.setAttribute("proj_id", proj_id);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/ds/result/ds_productSelect.jsp";
		return "/ds/result/ds_productList.jsp";
	}
	
	public String tableList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String result_id = request.getParameter("result_id");
		String proj_id = request.getParameter("proj_id");
		
		String page_no = (request.getParameter("pageno") == null || request.getParameter("pageno").equals("")) 
				? "1" : request.getParameter("pageno");
		int pageNo=(new Integer(page_no)).intValue();
		int pageSize=20;
		
		Page page=searchTable(request,pageNo,pageSize);
		
		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		request.setAttribute("result_id", result_id);
		request.setAttribute("proj_id", proj_id);
		buildDDL(request);
		if(request.getParameter("pageType") != null && !request.getParameter("pageType").equals("") 
			&& request.getParameter("pageType").equals("select")) 
			return "/ds/result/ds_productSelect.jsp";
		return "/ds/result/ds_productList.jsp";
	}
	
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String table_id = request.getParameter("table_id");
		Page page=search(request,1,100);
		
		ArrayList<Map> record2= (ArrayList)page.getThisPageElements();
		request.setAttribute("record2", record2);
		request.setAttribute("btnDisplay", "display:none");
		request.setAttribute("editMod", "show");
		buildDDL(request);
		return "/ds/result/ds_productDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select dpr.* from (select dp.*," +
				"sp.name supplier_name," +
				"(select hb.name from hr_base_info hb where hb.id = dp.operator) operator_name," +
				"(select mb.name from ma_brand mb where mb.id = dp.brand_id) brand_name " +
				"from Ds_product dp, supplier sp where dp.supplier_id = sp.id(+)) dpr where 1=1 " +
				"/~ and dpr.id={id} ~/"
				+ "/~ and dpr.ma_name like '%[ma_name]%' ~/ " +
				"/~ and dpr.table_id like {table_id} ~/"
				+ "/~ and dpr.model like '%[model]%' ~/ order by dpr.shunxu";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page search1(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select dp.*," +
				"(select mb.name from ma_brand mb where mb.id = dp.brand_id) brand_name" +
				" from Ds_product dp where 1=1 " +
				"/~ and dp.id={id} ~/ order by dp.shunxu";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}
	
	private Page searchTable(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Ds_prc_table where 1=1 " +
				"/~ and id={id} ~/" +
				"/~ and result_id={result_id} ~/" +
				"/~ and table_name like '%[table_name]%' ~/";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
	}

	//表单添加操作
	public String addTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String result_id = request.getParameter("result_id");
		String proj_id = request.getParameter("proj_id");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Ds_prc_table prc = new Ds_prc_table();
			prc.setProj_id(Integer.parseInt(proj_id));
			prc.setResult_id(Integer.parseInt(result_id));
			prc.setTable_name(request.getParameter("table_name"));
			prc.setShunxu(Integer.parseInt(request.getParameter("shunxu")));
			int i =dataBaseControl.insertByBean(prc);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("result_id", result_id);
		request.setAttribute("proj_id", proj_id);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		
		return "/ds/result/ds_prc_tableDtl.jsp";
	}
	
	//表单添加操作
	public String editTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			Ds_prc_table prc = new Ds_prc_table();
			prc.setId(Long.parseLong(request.getParameter("id")));
			prc.setProj_id(Integer.parseInt(request.getParameter("proj_id")));
			prc.setResult_id(Integer.parseInt(request.getParameter("result_id")));
			prc.setTable_name(request.getParameter("table_name"));
			prc.setShunxu(Integer.parseInt(request.getParameter("shunxu")));
			int i =dataBaseControl.updateByBean(prc);			
			if(i==1)request.setAttribute("operationSign", "closeDG_refreshW();");
		}else{
			Page page=searchTable(request,1,1);
			ArrayList<Map> record= (ArrayList)page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}
		buildDDL(request);
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		
		return "/ds/result/ds_prc_tableDtl.jsp";
	}
	
	//产品添加
	public String add(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String table_id = request.getParameter("table_id");
		String proj_id = request.getParameter("proj_id");
		Ds_product product = new Ds_product();
		
		if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
			
			String rowData = request.getParameter("rowData");
			
			List<Ds_product> mapList = JSON.parseArray(rowData, Ds_product.class);
	        for(int i = 0 ; i < mapList.size();i++)
	        {
	        	dataBaseControl.beginTransaction();	//开始事务
	        	product=mapList.get(i);
	        	product.setTable_id(Integer.parseInt(request.getParameter("table_id")));
	        	product.setOperator(Integer.parseInt(request.getParameter("operator")));
	        	product.setOp_time(new java.sql.Date((new java.util.Date()).getTime()));
	        	dataBaseControl.insertByBean(product);
	        	dataBaseControl.endTransaction();//结束事务
	        }
			
			request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String op_time = dFormat.format(today);
		buildDDL(request);
		request.setAttribute("table_id", table_id);
		request.setAttribute("proj_id", proj_id);
		request.setAttribute("op_time", op_time);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/ds/result/ds_productDtl.jsp";
	}
	
	//产品修改
	public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		String table_id = request.getParameter("table_id");
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String op_time = dFormat.format(today);
		Ds_product product = new Ds_product();
		
		List list = dataBaseControl.getOutResultSet("select * from ds_product t where t.table_id=?", new Object[]{table_id});
		if(list.size()==0){
			if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){
				
				//新增
				String rowData = request.getParameter("rowData");
				
				List<Ds_product> mapList = JSON.parseArray(rowData, Ds_product.class);
		        for(int i = 0 ; i < mapList.size();i++)
		        {
		        	dataBaseControl.beginTransaction();	//开始事务
		        	product=mapList.get(i);
		        	product.setTable_id(Integer.parseInt(request.getParameter("table_id")));
		        	product.setOperator(Integer.parseInt(request.getParameter("operator")));
		        	product.setOp_time(new java.sql.Date((new java.util.Date()).getTime()));
		        	dataBaseControl.insertByBean(product);
		        	dataBaseControl.endTransaction();//结束事务
		        }
				
				request.setAttribute("operationSign", "closeDG_refreshW();");
			}
			
			buildDDL(request);
			request.setAttribute("table_id", table_id);
			request.setAttribute("op_time", op_time);
			request.setAttribute("editMod", "add");
			request.setAttribute("IsPostBack", "1");
			return "/ds/result/ds_productDtl.jsp";
		}
		else{
			if(IsPostBack!=null && !IsPostBack.equals("")&&IsPostBack.equals("1")){

				table_id = request.getParameter("table_id");
				list = dataBaseControl.getOutResultSet("select * from ds_product t where t.table_id = ? order by t.id", new Object[]{table_id});
				//修改子项
				for(int i = 0 ; i < list.size(); i++)
				{
					dataBaseControl.beginTransaction();	//开始事务
					Map tmap = (Map)list.toArray()[i];
					product = new Ds_product();
					BeanUtils.populate(product, tmap);
					
					String num = request.getParameter("num"+product.getId());
					String bid_name = request.getParameter("bid_name"+product.getId());
					String bid_price = request.getParameter("bid_price"+product.getId());
					String aim_price = request.getParameter("aim_price"+product.getId());
					String bjbl = request.getParameter("bjbl"+product.getId());
					String shunxu = request.getParameter("shunxu"+product.getId());
					
					product.setNum(Integer.parseInt(num));
					product.setBid_name(bid_name);
					product.setBid_price(BigDecimal.valueOf(Double.parseDouble(bid_price)));
					product.setAim_price(BigDecimal.valueOf(Double.parseDouble(aim_price)));
					product.setBjbl(BigDecimal.valueOf(Double.parseDouble(bjbl)));
					product.setShunxu(Integer.parseInt(shunxu));
					
					dataBaseControl.updateByBean(product);
					dataBaseControl.endTransaction();//结束事务
				}
				
				//修改新增
				String rowData = request.getParameter("rowData");
				if(!rowData.equals("]")){
					List<Ds_product> mapList = JSON.parseArray(rowData, Ds_product.class);
			        for(int i = 0 ; i < mapList.size();i++)
			        {
			        	dataBaseControl.beginTransaction();	//开始事务
			        	product=mapList.get(i);
			        	product.setTable_id(Integer.parseInt(request.getParameter("table_id")));
			        	product.setOperator(Integer.parseInt(request.getParameter("operator")));
			        	product.setOp_time(new java.sql.Date((new java.util.Date()).getTime()));
			        	dataBaseControl.insertByBean(product);
			        	dataBaseControl.endTransaction();//结束事务
			        }
				}
				request.setAttribute("operationSign", "closeDG_refreshW();");
			}else{
				Page page1=search1(request,1,100);
				ArrayList<Map> record1= (ArrayList)page1.getThisPageElements();
				request.setAttribute("record1", record1);
			}
		}
		request.setAttribute("table_id", table_id);
		request.setAttribute("op_time", op_time);
		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/ds/result/ds_productDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Ds_product WHERE ID in (?)", new Object[]{s_id});
		
		return "/ds/result/Ds_product!searchList.do";
	}
	
	public String deleteProduct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map map = dataBaseControl.getOneResultSet("select * from ds_product t where t.id = ? ", new Object[]{request.getParameter("id")});
		dataBaseControl.executeSql("DELETE FROM ds_product WHERE ID in (?)", new Object[]{request.getParameter("id")});
		return "/ds/result/Ds_product!edit.do?id="+map.get("result_id");
	}
	
	public String deleteTable(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String result_id = request.getParameter("id");
		String s_id = "";
		if(result_id!=null){
			List list = dataBaseControl.getOutResultSet("select t.id from ds_prc_table t where t.result_id=?", new Object[]{result_id});
			if(list.size()!=0){
				int size = list.size();  
				for(int i=0;i<list.size();i++){
					Map mapid = (Map) list.get(i);
					s_id=(mapid.get("id")).toString();  
					dataBaseControl.executeSql("DELETE FROM Ds_product WHERE table_id in (?)", new Object[]{s_id});
					dataBaseControl.executeSql("DELETE FROM Ds_prc_table WHERE id in (?)", new Object[]{s_id});
				}
			}
		}else{
			String[] ids=request.getParameterValues("table_id");
			Map map = dataBaseControl.getOneResultSet("select t.result_id from ds_prc_table t where t.id=?", new Object[]{request.getParameter("table_id")});
			result_id = map.get("result_id").toString();
			for(String e:ids) {
				str.append(""+e+",");
			}
			s_id = str.substring(0, str.length()-1);
			dataBaseControl.executeSql("DELETE FROM Ds_product WHERE table_id in (?)", new Object[]{s_id});
			dataBaseControl.executeSql("DELETE FROM Ds_prc_table WHERE id in (?)", new Object[]{s_id});
		}
		return "/ds/result/Ds_product!tableList.do?result_id="+result_id;
	}
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}