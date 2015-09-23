﻿<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script type="text/javascript" src="../jscripts/TreeGrid.js"></script>

</head>
<body>	
	<form action="<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select" method="post">
		<table width="100%" class="border_bottom">
		
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">名称:</th>
			<td style="width:35%">
			<input type="text" name="branchname" value="${searchMap.branchname}" maxLength="100"  style="width:75%" class="textBox"/></td>
			<th align="center" class="myInputTitle" style="width:10%">缩写:</th>
			<td style="width:35%">
			<input type="text" name="simplecode" value="${searchMap.simplecode}" maxLength="20"  style="width:75%" class="textBox"/></td>
            </tr>
		</table>
		<div class="div-button">
			<input type="reset"	value="重置" class="button"/>
			<input type="button" value="查询" class="button" onclick="loadData();"/>
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table id='table1' cellspacing=0 cellpadding=0 width='100%' class='TreeGrid'>
	    <thead class="header">
	    <th data-options="dataField:'id',dataAlign:'center',width:'20'">id</th>
	    <th data-options="dataField:'branchname',dataAlign:'left',width:'200'">机构名称</th>
	    <th data-options="dataField:'simplecode',dataAlign:'left',width:'200'">机构缩写</th>
	    <th data-options="dataField:'dcode',dataAlign:'center',width:'100'">机构编码</th>
	    <th data-options="dataField:'branchtype',dataAlign:'center',width:'60',code:'branchtype'">机构类型</th>
	    <th data-options="dataField:'delflag',dataAlign:'center',width:'60',code:'delflag'">删除标志</th>
	    <th data-options="dataField:'address',dataAlign:'center',width:'60'" width="60">地址</th>
	    <th data-options="dataField:'name',dataAlign:'center',width:'100',handler:'customLook'">查看</th>
	    </thead>
	    <tbody></tbody>  
	    </table>
	</div>
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	</div>
	<script type="text/javascript">
var config = {
			renderTo: "table1",
			indentation: "20",
			folderOpenIcon: "../image/treegrid/folderOpen.gif",
			folderCloseIcon: "../image/treegrid/folderClose.gif",
			defaultLeafIcon: "../image/treegrid/defaultLeaf.gif",
			hoverRowBackground: "true",
			folderColumnIndex: "1",
			itemClick: ""
		};
var treeGrid = new TreeGrid(config);
loadData();
function customLook(row, _cols){
	return '<a href="#" onclick="retSelect(\''+row.id+'\',\''+row.branchname+'\');">选择</a>';
}

function loadData()
{
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath() %>/sysAdmin/mrbranch!gettree.do",
			dataType:"json",
			data:{name:$("#funcname").val()},
			cache:false,
			success:function(data){
				//var jsonData = eval('(' + data +')');
				treeGrid.removeAllRow()
				treeGrid.loadJson(data);
			}
		});

}
</script>
</body>
</html>
