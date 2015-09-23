<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>My JSP 'allotUserRole.jsp' starting page</title>
    
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/icon.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
	<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/easyUI132/jquery.easyui.min.js"></script>
	<script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
	<script type="text/javascript">
		function getChk1(){
			var s = '';
			var rows = $('#dg1').datagrid('getSelections');
			if(rows!=''){
				for(var i=0; i<rows.length; i++){
					if (s != '') s += ',';
					var row = rows[i];
					s +=row.id;
				}
				dosubmit(s,"addrole");
			}
		}
		function getChk2(){
			var s = '';
			var rows = $('#dg2').datagrid('getSelections');
			for(var i=0; i<rows.length; i++){
				if (s != '') s += ',';
				var row = rows[i];
				s +=row.id;
			}
			dosubmit(s,"delrole");
		}
		function dosubmit(id,type)
		{	var uid = document.getElementById("userid").value;
			var fm = document.createElement("form");
			document.body.appendChild(fm);
			var it = document.createElement("input");
			it.type="text";
			it.name="rid";
			it.value=id;
			fm.appendChild(it);
			var it2=document.createElement("input");
			it2.type="text";
			it2.name="uid";
			it2.value=uid;
			fm.appendChild(it2);
			fm.action = "<%=request.getContextPath()%>/sysAdmin/sysuser!"+type+".do";		
			fm.submit();			
		}
	</script>
  </head>
  
  <body class="easyui-layout" style="margin:10px auto;width:800px;">
  	<input type="hidden" id="userid" name="userid" value="${userid}"/>
    <div data-options="region:'west',split:true,title:'备选角色'" style="width:320px;padding-top:10px;">
	
	<table id="dg1" class="easyui-datagrid" style="width:300px;"
			data-options="rownumbers:true,url:'<%=request.getContextPath()%>/sysAdmin/sysuser!getDg1.do?uid=${userid}'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:60">角色编号</th>
				<th data-options="field:'rolename',width:200">角色名称</th>
			</tr>
		</thead>
	</table>

	</div>
	<div data-options="region:'center'">
	<a href="#" class="easyui-linkbutton" onclick="getChk1();" style="left: 35px; top: 160px; position:absolute;">添加 －－＞</a>
	<a href="#" class="easyui-linkbutton" onclick="getChk2();" style="left: 35px; top: 220px; position:absolute;">＜－－删除</a>
	
	</div>
	<div data-options="region:'east',split:true,title:'已分配角色'" style="width:320px;padding-top:10px;">
	
	<table id="dg2" class="easyui-datagrid" style="width:300px;"
			data-options="rownumbers:true,url:'<%=request.getContextPath()%>/sysAdmin/sysuser!getDg2.do?uid=${userid}'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:60">角色编号</th>
				<th data-options="field:'rolename',width:200">角色名称</th>
			</tr>
		</thead>
	</table>
	</div>
  </body>
</html>
