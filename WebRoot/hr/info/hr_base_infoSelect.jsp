<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/ligerUI/js/core/base.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/ligerUI/js/plugins/ligerTree.js"></script>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/jscripts/ligerUI/skins/Aqua/css/ligerui-tree.css" type="text/css"></link>
<link rel="stylesheet" href="../../jscripts/DataTable/DataGrid.css" type="text/css"></link>
<script type="text/javascript" src="../../jscripts/DataTable/DataGrid.js"></script>
<SCRIPT type="text/javascript">
		var config = {
			renderTo: "table1",
			hoverRowBackground: "true",
			itemClick: ""
		};
		var dataGrid = new DataGrid(config);
		var _deptId ="";
		function loadd(_id){
			_deptId=_id;
			dataGrid.loadUrl("<%=request.getContextPath()%>/hr/info/Hr_base_info!getJsonData.do",
			{dept_id:_deptId,pageNo:1},"");
		}
		function changePage(page){
			dataGrid.loadUrl("<%=request.getContextPath()%>/hr/info/Hr_base_info!getJsonData.do",
			{"name":$("#name").val(),dept_id:_deptId,pageNo:page},"");
		}
		function searchPage(){
			dataGrid.loadUrl("<%=request.getContextPath()%>/hr/info/Hr_base_info!getJsonData.do",
			{"name":$("#name").val(),pageNo:1},"");
		}
		function customLook(row, _cols){
			var a ="<a onclick='retSelect(\""+row["id"]+"\",\""+row["name"]+"\")' style='color:blue;'>选择</a>";
			return '<a href="#" onclick="retSelect(\''+row.id+'\',\''+row.name+'\');">选择</a>';
		}
		changePage(1);
		$(function ()
        {
			$("#tree").ligerTree({ 
				checkbox : false,
				nodeWidth : 120,
				url:"<%=request.getContextPath()%>/sysAdmin/mrbranch!getTreeJson.do?parentid=0",
				onClick : function(node) {
				loadd(node.data.id);
			}});
		});
	</SCRIPT>

</head>
<body>	
	<TABLE width="100%" border=0 align=left>
	<TR>
		<TD width=200px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
			<ul id="tree" style="width:200px; overflow:auto;"></ul>
		</TD>
		<TD width="100%" align=left valign=top>
	        
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>姓名:</th>
			<td>
			<input type="text" id="name" style="width:95%" class="textBox"/></td>
			<th></th>
			<td>
			</td>
            </tr>
            
		</table>
	</div>
		<div class="div-button">
			<input type="button" value="查询" class="button" onclick="searchPage(1);" />
		</div>
		<div class="clear" style="height:0px;"></div>
	<div>
		<table id='table1' cellspacing=0 cellpadding=0 width='100%' class='DataGrid'>
		    <thead>
		    <th data-options="dataField:'name',dataAlign:'left',width:'100'">姓名</th>
		    <th data-options="dataField:'branchname',dataAlign:'center',width:'100'">部门</th>
		    <th data-options="dataField:'id',dataAlign:'center',width:'100',handler:'customLook'">选择</th>
		    </thead>
		    <tbody></tbody>
		  
		    </table>
	</div>
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	
	</div>
	</TD>
	</TR>
</TABLE>
</body>
</html>