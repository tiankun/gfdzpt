package com.web.materiel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.util.treeUtil;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Ma_kindAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();
	
	
	public String getParentTree(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		String parentCode=request.getParameter("parentCode");
//		String parent_id="0";
//		Map item=null;
//		if (parentCode!=null&&!parentCode.equals("")&&parentCode.length()<=6) {
//			String codeTemp="";
//			for (int i = 0; i < 6-parentCode.length(); i++) {
//				codeTemp+="0";
//			}
//			List listParent=(List)dataBaseControl.getOutResultSet("select tp.* from T_CODE_AREA tp where tp.id_code=?",new Object[]{parentCode+codeTemp});
//			if (listParent!=null&&!listParent.isEmpty()) {
//				parent_id=((Map)listParent.get(0)).get("id").toString();
//				item=(Map)listParent.get(0);
//			}
//		}
		String funcDicList = treeUtil.buildTreeJson("select t.id,t.name from ma_kind t where t.parent_id = ?","0","id","id","name","closed");
//		if (item!=null) {
//			funcDicList="[{\"id\":"+item.get("id_code")+",\"text\":\""+item.get("name")+"\",\"children\":"+funcDicList+"}]";
//		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(funcDicList);
        return null;
	}
	
	
	public String gettree(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map searchMap=getParameterMap(request);
		
		
		if(searchMap ==null || searchMap.size()<1 ){
			searchMap.put("parentid", "0");
		}else if(searchMap.get("name")==null||searchMap.get("name").equals("")){
			searchMap.put("parentid", "0");
		}
		
		
		   String sql = "select * from ma_kind t where 1=1 /~ and t.parent_id={parentid} ~/"
			   +"/~ and t.name like '%[name]%'  ~/";
		   //XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		   String treejson = treeUtil.buildMyTreeGrid(sql,searchMap);
		   //DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		   //List<Object> list = (List)dataBaseControl.getOutResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray());
		   //setAjaxInfo(response,JSON.toJSONString(list));
		   treejson="["+treejson+"]";
		   response.setCharacterEncoding("utf-8");
		   response.getWriter().print(treejson);
		   
		   return null;
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
		{
			//List list = dataBaseControl.getOutResultSet("select level,t.* from ma_kind t start with id = 1 connect by prior id = t.parent_id ", null);
			//request.setAttribute("record", list);
			request.setAttribute("detailname", request.getParameter("detailname"));
			return "/materiel/kind/ma_kindSelect.jsp";
		}
		
		
		
		return "/materiel/kind/ma_kindList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/materiel/kind/ma_kindDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select t.*,(select m.name from ma_kind m where m.id = t.parent_id)fname from Ma_kind t where 1=1 and id!=1 /~ and id={id} ~/"
		+ "/~ and name like '%[name]%' ~/ "
		+ "/~ and shortcode={shortcode} ~/ "
		+ " order by t.parent_id";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,searchMap); 
		Page page=dataBaseControl.getPageResultSet(xsql.getXsql(), xsql.getAcceptedFilters().values().toArray(),pageNo,pageSize);
		return page;
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
		return "/materiel/kind/ma_kindDtl.jsp";
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
		return "/materiel/kind/ma_kindDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Ma_kind WHERE ID in (?)", new Object[]{s_id});
		
		return "/materiel/Ma_kind!searchList.do";
	}
	private void buildDDL(HttpServletRequest request) throws Exception{
		
	}

}