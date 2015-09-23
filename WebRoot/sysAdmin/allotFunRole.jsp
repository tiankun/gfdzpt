<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>My JSP 'allotFunRole.jsp' starting page</title>
    
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/icon.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
	<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/easyUI132/jquery.easyui.min.js"></script>
	<script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
	<script type="text/javascript">
		function getChecked1(){
			var nodes = $('#tree1').tree('getChecked');
			var s = '';
			for(var i=0; i<nodes.length; i++){
				if (s != '') s += ',';
				s += nodes[i].id;
				//多级寻找父节点id
				var parent = $('#tree1').tree('getParent',nodes[i].target);
				while(true){
					if(parent!=null){
					s += ','+parent.id;
					parent = $('#tree1').tree('getParent',parent.target);
					}
					else{
					break;
					}
				}
			}
			if(s!='') dosubmit(s,"addfun");			
		}
		function getChecked2(){
			var nodes = $('#tree2').tree('getChecked');
			if(nodes!=''){
				var s = '';
				for(var i=0; i<nodes.length; i++){
					if (s != '') s += ',';
					s += nodes[i].id;
				}
				dosubmit(s,"delfun");
			}
		}
		function dosubmit(id,type)
		{	var rid = document.getElementById("roleid").value;
			var fm = document.createElement("form");
			document.body.appendChild(fm);
			var it = document.createElement("input");
			it.type="text";
			it.name="fid";
			it.value=id;
			fm.appendChild(it);
			var it2=document.createElement("input");
			it2.type="text";
			it2.name="rid";
			it2.value=rid;
			fm.appendChild(it2);
			fm.action = "<%=request.getContextPath()%>/sysAdmin/sysrolesdic!"+type+".do";		
			fm.submit();			
		}
	</script>

  </head>
  
  <body class="easyui-layout" style="margin:10px auto;width:800px;">
  	<input type="hidden" id="roleid" name="roleid" value="${roleid}"/>
    <div data-options="region:'west',split:true,title:'备选的功能'" style="width:320px;padding-top:10px;">
	<ul id="tree1" style="width:90%;margin-bottom:10px;" class="easyui-tree" data-options="url:'<%=request.getContextPath()%>/sysAdmin/sysrolesdic!getTree1.do?rid=${roleid}',animate:true,checkbox:true"></ul>
	</div>
	
	<div data-options="region:'center'">
	<a href="#" class="easyui-linkbutton" onclick="getChecked1();" style="left: 35px; top: 160px; position:absolute;">添加 －－＞</a>
	<a href="#" class="easyui-linkbutton" onclick="getChecked2();" style="left: 35px; top: 220px; position:absolute;">＜－－删除</a>
	</div>
	
	<div data-options="region:'east',split:true,title:'已分配的功能'" style="width:320px;padding-top:10px;">
	<ul id="tree2" style="width:90%;margin-bottom:10px;" class="easyui-tree" data-options="url:'<%=request.getContextPath()%>/sysAdmin/sysrolesdic!getTree2.do?rid=${roleid}',animate:true,checkbox:true"></ul>
	</div>
  </body>
</html>
