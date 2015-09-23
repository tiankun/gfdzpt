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

	<form name="form1" action="<%=request.getContextPath()%>/ds/result/Ds_cost!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="text" name="result" id="result" value="" class="hidden"/>
	<input type="text" name="result_id" id="result_id" value="${record.result_id}" class="hidden"/>
	<input type="text" name="proj_id" id="proj_id" value="${record.proj_id}" class="hidden"/>
	<input type="text" name="operator" id="operator" value="${record.operator}" class="hidden"/>
	<input type="text" name="op_time" id="op_time" value="${record.op_time}" class="hidden"/>
	<input type="text" name="ds_type" id="ds_type" value="${record.ds_type}" class="hidden"/>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">人工费:<span style="color:Red;">*</span></th>
			<td style="width:20%">
			<input type="text" name="labor_cost" value="${record.labor_cost}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            <!-- 
            <th align="center" style="width:10%">目标人工费:</th>
			<td style="width:35%">
			<input type="text" name="aim_lc" value="${record.aim_lc}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
             -->
			<th align="center" style="width:10%">安装费:<span style="color:Red;">*</span></th>
			<td style="width:20%">
			<input type="text" name="install_cost" value="${record.install_cost}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<!-- 
			<th align="center" style="width:10%">目标安装费:</th>
			<td style="width:35%">
			<input type="text" name="aim_ic" value="${record.aim_ic}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            -->
			<th align="center" style="width:10%">管理费:<span style="color:Red;">*</span></th>
			<td style="width:20%">
			<input type="text" name="manage_cost" value="${record.manage_cost}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<!-- 
			<th align="center" style="width:10%">目标管理费:</th>
			<td style="width:35%">
			<input type="text" name="aim_mc" value="${record.aim_mc}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            -->
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='add'){
	$("#result_id").attr("value","${result_id}");
	$("#proj_id").attr("value","${proj_id}");
	$("#ds_type").attr("value","${ds_type}");
	$("#operator").attr("value","${user.base_info_id}");
	$("#op_time").attr("value","${op_time}");
}
</script>	
</body>
</html>
