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
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/Validator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/sysAdmin/Sys_data_scope!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">角色:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="RoleName" name="RoleName" value="${record.RoleName}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="roleid" name="roleid" value="${record.roleid}"  dataType="Integer" class="hidden"/>
			<input type="button" onclick="openSelect('role_select','<%=request.getContextPath()%>/sysAdmin/sysrolesdic!searchList.do?pageType=select','roleid','RoleName');" value="选择"/></td>
			</tr>
            
            <tr>
			<th align="center" style="width:10%">范围类型:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select id="data_scope_type" name="data_scope_type" dataType="Require"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${data_scope_type}" var="data_scope_type">
			<option value="${data_scope_type.key}" <c:if test="${record.data_scope_type==data_scope_type.key}">selected</c:if>>${data_scope_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">数据范围:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="DataName" name="DataName" value="${record.DataName}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="dataid" name="dataid" value="${record.dataid}"  dataType="Integer" class="hidden"/>
			<input type="button" onclick="checkType()" value="选择"/>
			</td>
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
	<script type="text/javascript">
	function checkType(){
		//var ind = document.getElementById("data_scope_type").selectedIndex;
		var type = $('#data_scope_type').val();//document.getElementById("data_scope_type").options[ind].value;
		if(type==undefined||type=='')alert('请选择数据范围类型。');
		else{
			if(type=='0') openSelect('role_select','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dataid','DataName');
			if(type=='1')openSelect('user_select','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','dataid','DataName');
		}
	}
	</script>
</body>
</html>
