package com.web.sysAdmin;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

/**
 * BigDecimal f_id --- 信息表主键
 * String flag --- 上传文件的区分标识
 * String folder --- 自定义上传文件夹名
 * 查看图片时，加入 mark=show
 * 
 * 示例：
 * js:
		<script type="text/javascript">
		 function uploadpic(folder,flag){
		   $editMod = $("#editMod").attr("value");
		 	if($editMod){
		 		window.open("<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=主键&folder="+folder+"&flag="+flag,"_blank", "height=600, width=1000, left=300, top=100, toolbar =no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no");
		 	}
		 	else{
		 		window.open("<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=主键&folder="+folder+"&flag="+flag+"&mark=show","_blank", "height=600, width=1000, left=300, top=100, toolbar =no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no");
		 	}
		 }
	 	</script>
 * html:
 		<img 
			<c:if test="${not empty editMod}">
				src="<%=request.getContextPath()%>/image/upload.png" 
			</c:if>
			<c:if test="${empty editMod}">
				src="<%=request.getContextPath()%>/image/picopen.png" 
			</c:if>
				onclick="uploadpic('文件夹名','区分标识')"/>
		</img>
 * 
 * @author 廖望舒
 *
 */



public class Sys_uppicAction extends BaseAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	
	//上传扫描件
		public String uploadpic(HttpServletRequest request,HttpServletResponse response) throws Exception {
			String f_id = request.getParameter("f_id");
			String flag = request.getParameter("flag");
			String folder = request.getParameter("folder");
			
			List piclist = new ArrayList();
			piclist = (List)dataBaseControl.getOutResultSet("select * from sys_uppic t where t.f_id = ? and t.flag = ? order by t.id ", new Object[]{f_id,flag});
			
			List reslist = new ArrayList();
			reslist = getDeleteList(piclist);
			
			request.setAttribute("f_id", f_id);
			request.setAttribute("flag", flag);
			request.setAttribute("folder", folder);
			request.setAttribute("piclist", reslist);
			request.setAttribute("picsize", piclist.size());
			
			String forward = "";
			
			forward = "/sysAdmin/uploadpic.jsp";
			
			return forward;
		}
		
		//上传文件
		public String uploadFiles(HttpServletRequest request,HttpServletResponse response) throws Exception {
			String f_id = request.getParameter("f_id");
			String flag = request.getParameter("flag");
			String folder = request.getParameter("folder");
			
			List filelist = new ArrayList();
			filelist = (List)dataBaseControl.getOutResultSet("select * from sys_uppic t where t.f_id = ? and t.flag = ? order by t.id ", new Object[]{f_id,flag});
			
			request.setAttribute("oldname", "file");
			request.setAttribute("f_id", f_id);
			request.setAttribute("flag", flag);
			request.setAttribute("folder", folder);
			request.setAttribute("filelist", filelist);
			request.setAttribute("filesize", filelist.size());
			
			String forward = "";
			
			forward = "/sysAdmin/uploadFiles.jsp";
			
			return forward;
		}
		
		//显示已上传的文件
		public String display(HttpServletRequest request,HttpServletResponse response) throws Exception {
			String f_id = request.getParameter("f_id")==null?"":request.getParameter("f_id");
			String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
			String mark = request.getParameter("mark")==null?"":request.getParameter("mark");
			String oldname = request.getParameter("oldname")==null?"":request.getParameter("oldname");
			String folder = request.getParameter("folder")==null?"":request.getParameter("folder");
			
			if(f_id.equals(""))
			{
				this.doResult(response, "未找到相关记录");
				return null;
			}
			
			List filelist = new ArrayList();
			filelist = (List)dataBaseControl.getOutResultSet("select * from sys_uppic t where t.f_id = ? and t.flag = ? order by t.id ", new Object[]{f_id,flag});
			
			List reslist = new ArrayList();
			reslist = getDeleteList(filelist);
			
			if(oldname.equals("file")){
				request.setAttribute("filelist", filelist);
			}
			request.setAttribute("piclist", reslist);
			request.setAttribute("picsize", filelist.size());
			
			request.setAttribute("f_id", f_id);
			request.setAttribute("flag", flag);
			request.setAttribute("folder", folder);
	        //Map personinfor = (Map)dataBaseControl.getOneResultSet("select t.*,round((sysdate - t.congssj)/365,0)nianx,substr(t.yuany,2)reason  from t_sgt_person t where t.id = ?", new Object[]{examine_id});
			
			//request.setAttribute("person", personinfor);
			
			//判断展现哪个页面
			//显示文件
			if(oldname.equals("file")){
				if(("show").equals(mark)){
					return "/sysAdmin/showFiles.jsp";
				}
			
				return "/sysAdmin/uploadFiles.jsp";
			}
			//显示图片
			else{
				if(("show").equals(mark)){
					return "/sysAdmin/showpic.jsp";
				}
			
				return "/sysAdmin/uploadpic.jsp";
			}
		}
		
		
		//删除图片
		public String del(HttpServletRequest request,HttpServletResponse response) throws Exception {
			String id = request.getParameter("id")==null?"":request.getParameter("id");
			String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
			String f_id = request.getParameter("f_id")==null?"":request.getParameter("f_id");
			String oldname = request.getParameter("oldname")==null?"":request.getParameter("oldname");
			String folder = request.getParameter("folder")==null?"":request.getParameter("folder");
			if(id.equals(""))
			{
				this.doResult(response, "未找到相关记录");
				return null;
			}
			
			Map map = dataBaseControl.getOneResultSet("select * from sys_uppic t where t.id = ?", new Object[]{id});
			
			String photopath = request.getSession().getServletContext().getRealPath("/")+"/uploads/"+folder+"/"+map.get("pic");
			File file = new File(photopath);
			if (file.exists()) {
				file.delete();
			}
			
			dataBaseControl.executeSql("delete from sys_uppic t where t.id = ?", new Object[]{id});
			
			
			//Map personinfor = (Map)dataBaseControl.getOneResultSet("select t.*,round((sysdate - t.congssj)/365,0)nianx,substr(t.yuany,2)reason  from t_sgt_person t where t.id = ?", new Object[]{personid});
			//request.setAttribute("person", personinfor);
			
			List piclist = new ArrayList();
			piclist = (List)dataBaseControl.getOutResultSet("select * from sys_uppic t where t.f_id = ? and t.flag = ? order by t.id ", new Object[]{f_id,flag});
			
			List reslist = new ArrayList();
			reslist = getDeleteList(piclist);
			
			if(oldname.equals("file")){
				request.setAttribute("filelist", piclist);
			}
			request.setAttribute("piclist", reslist);
			request.setAttribute("picsize", piclist.size());
			request.setAttribute("f_id", f_id);
			request.setAttribute("flag", flag);
			request.setAttribute("folder", folder);
			
			if(oldname.equals("file")){
				return "/sysAdmin/uploadFiles.jsp";
			}
			return "/sysAdmin/uploadpic.jsp";
		}
		
		/**
		 * 后台查看大图片
		 **/
		public String displayphoto(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			String id = request.getParameter("id")==null?"":request.getParameter("id");
			String folder = request.getParameter("folder")==null?"":request.getParameter("folder");
			
			if(id.equals(""))
			{
				this.doResult(response, "未找到相关图片");
				return null;
			}

			Map map = dataBaseControl.getOneResultSet("select * from sys_uppic t where t.id = ?", new Object[]{id});
			request.setAttribute("photo", map);
			request.setAttribute("folder", folder);

			return "/sysAdmin/displayphoto.jsp";
		}
		
	public List getDeleteList(List list) {
		List resultList = new ArrayList();
		try {
			Map mapa = new HashMap();
			Map mapb = new HashMap();
			Map mapc = new HashMap();

			Map mapa1 = new HashMap();
			Map mapb1 = new HashMap();
			Map mapc1 = new HashMap();

			int i = 1;
			int count = 0;
			for(int k = 0 ; k < list.size(); k++){
				Map tem = (Map) list.toArray()[k];

				if (i % 3 == 1) {
					mapa.put(i, tem.get("pic"));
					mapa1.put(i, tem.get("id"));
				}
				if (i % 3 == 2) {
					mapb.put(i - 1, tem.get("pic"));
					mapb1.put(i - 1, tem.get("id"));
				}
				if (i % 3 == 0) {
					mapc.put(i - 2, tem.get("pic"));
					mapc1.put(i - 2, tem.get("id"));
				}
				i++;
				count++;
			}

			int numa = mapa.size();

			if (numa != 0 && count % 3 == 0) // 照片张数为3的倍数的情况
			{
				int k = 0;
				for (int j = 0; j < numa; j++) {
					Map map = new HashMap();
					map.put("photo1", mapa.get(3 * k + 1));
					map.put("photo2", mapb.get(3 * k + 1));
					map.put("photo3", mapc.get(3 * k + 1));
					map.put("id1", mapa1.get(3 * k + 1));
					map.put("id2", mapb1.get(3 * k + 1));
					map.put("id3", mapc1.get(3 * k + 1));
					resultList.add(map);
					k++;
				}
			}

			if (numa != 0 && count % 3 == 1) {
				int k = 0;
				for (int j = 0; j < numa - 1; j++) {
					Map map = new HashMap();
					map.put("photo1", mapa.get(3 * k + 1));
					map.put("photo2", mapb.get(3 * k + 1));
					map.put("photo3", mapc.get(3 * k + 1));
					
					map.put("id1", mapa1.get(3 * k + 1));
					map.put("id2", mapb1.get(3 * k + 1));
					map.put("id3", mapc1.get(3 * k + 1));
					resultList.add(map);
					k++;
				}
				Map map1 = new HashMap();
				map1.put("photo1", mapa.get(3 * k + 1));
				map1.put("photo2", "none");
				map1.put("photo3", "none");
				map1.put("id1", mapa1.get(3 * k + 1));
				map1.put("id2", "");
				map1.put("id3", "");

				resultList.add(map1);
			}

			if (numa != 0 && count % 3 == 2) {
				int k = 0;
				for (int j = 0; j < numa - 1; j++) {
					Map map = new HashMap();
					map.put("photo1", mapa.get(3 * k + 1));
					map.put("photo2", mapb.get(3 * k + 1));
					map.put("photo3", mapc.get(3 * k + 1));
					
					map.put("id1", mapa1.get(3 * k + 1));
					map.put("id2", mapb1.get(3 * k + 1));
					map.put("id3", mapc1.get(3 * k + 1));
					resultList.add(map);
					k++;
				}
				Map map1 = new HashMap();
				map1.put("photo1", mapa.get(3 * k + 1));
				map1.put("photo2", mapb.get(3 * k + 1));
				map1.put("photo3", "none");
				
				map1.put("id1", mapa1.get(3 * k + 1));
				map1.put("id2", mapb1.get(3 * k + 1));
				map1.put("id3", "");
				resultList.add(map1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;

	}	
	
	private void buildDDL(HttpServletRequest request) throws Exception{
		
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
			return "/sysAdmin/sys_uppicSelect.jsp";
		return "/sysAdmin/sys_uppicList.jsp";
	}
	public String searchById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page=search(request,1,1);
		
		request.setAttribute("record", ((ArrayList)page.getThisPageElements()).get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/sysAdmin/sys_uppicDtl.jsp";
	}
	private Page search(HttpServletRequest request,int pageNo,int pageSize) throws Exception 
	{
		Map searchMap=getParameterMap(request);
		String sql="select * from Sys_uppic where 1=1 /~ and id={id} ~/";
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
		return "/sysAdmin/sys_uppicDtl.jsp";
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
		return "/sysAdmin/sys_uppicDtl.jsp";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		StringBuffer str=new StringBuffer();
		String[] ids=request.getParameterValues("id");
		for(String e:ids) {
			str.append(""+e+",");
		}
		String s_id = str.substring(0, str.length()-1);
		dataBaseControl.executeSql("DELETE FROM Sys_uppic WHERE ID in (?)", new Object[]{s_id});
		
		return "/sysAdmin/Sys_uppic!searchList.do";
	}
}