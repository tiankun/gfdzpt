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

	<form name="form1" action="<%=request.getContextPath()%>/pr/feePlan/Pr_fee_plan!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">计划id:</th>
			<td style="width:35%"></td>
			<th align="center" style="width:10%">项目:</th>
			<td style="width:35%"></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">人工费:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="rgf" value="${record.rgf}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">安装费:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="azf" value="${record.azf}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">管理费:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="glf" value="${record.glf}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">变更原因:</th>
			<td style="width:35%">
			<input type="text" name="mf_reason" value="${record.mf_reason}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">变更状态:</th>
			<td style="width:35%">
			<select name="ds_mf_status"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${ds_mf_status}" var="ds_mf_status">
			<option value="${ds_mf_status.key}" <c:if test="${record.ds_mf_status==ds_mf_status.key}">selected</c:if>>${ds_mf_status.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">操作人:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="czr" value="${record.czr}"  dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">操作日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="czrq" value="${record.czrq}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td><th></th><td></td>
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
</body>
</html>
