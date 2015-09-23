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
				dosubmit(s,"addStaff");
			}
		}
		function getChk2(){
			var s = '';
			var rows = $('#dg2').datagrid('getSelections');
			for(var i=0; i<rows.length; i++){
				if (s != '') s += ',';
				var row = rows[i];
				s +=row.ep_id;
			}
			dosubmit(s,"delStaff");
		}
		function dosubmit(id,type)
		{	
			var p_id = document.getElementById("p_id").value;
			var dept_id = document.getElementById("dept_id").value;
			var fm = document.createElement("form");
			document.body.appendChild(fm);
			//传入考评人员ID
			var it = document.createElement("input");
			it.type="text";
			it.name="ep_id";
			it.value=id;
			fm.appendChild(it);
			//传入被考评人员ID
			var it2=document.createElement("input");
			it2.type="text";
			it2.name="p_id";
			it2.value=p_id;
			fm.appendChild(it2);
			//传入部门ID
			var it3=document.createElement("input");
			it3.type="text";
			it3.name="dept_id";
			it3.value=dept_id;
			fm.appendChild(it3);
			fm.action = "<%=request.getContextPath()%>/hr/process/Hr_evaluation!"+type+".do";		
			fm.submit();			
		}
	</script>
  </head>
  
  <body class="easyui-layout" style="margin:10px auto;width:800px;">
  	<input type="hidden" id="p_id" name="p_id" value="${pid}"/>
  	<input type="hidden" id="dept_id" name="dept_id" value="${dept_id}"/>
    <div data-options="region:'west',split:true,title:'备选人员'" style="width:320px;padding-top:10px;">
	
	<table id="dg1" class="easyui-datagrid" style="width:300px;"
			data-options="rownumbers:true,url:'<%=request.getContextPath()%>/hr/process/Hr_evaluation!getDg1.do?pid=${pid}&dept_id=${dept_id}'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'name',width:200">人员姓名</th>
			</tr>
		</thead>
	</table>

	</div>
	<div data-options="region:'center'">
	<a href="#" class="easyui-linkbutton" onclick="getChk1();" style="left: 35px; top: 160px; position:absolute;">添加 －－＞</a>
	<a href="#" class="easyui-linkbutton" onclick="getChk2();" style="left: 35px; top: 220px; position:absolute;">＜－－删除</a>
	
	</div>
	<div data-options="region:'east',split:true,title:'已分配人员'" style="width:320px;padding-top:10px;">
	
	<table id="dg2" class="easyui-datagrid" style="width:300px;"
			data-options="rownumbers:true,url:'<%=request.getContextPath()%>/hr/process/Hr_evaluation!getDg2.do?pid=${pid}'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'ev_name',width:200">人员姓名</th>
			</tr>
		</thead>
	</table>
	</div>
  </body>
</html>
