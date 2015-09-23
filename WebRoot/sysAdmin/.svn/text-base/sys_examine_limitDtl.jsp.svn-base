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

	<form name="form1" action="<%=request.getContextPath()%>/sysAdmin/Sys_examine_limit!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">角色id:</th>
			<td style="width:35%">
			<input type="text" id="rolename" name="rolename" value="${record.rolename}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="roleid" name="roleid" value="${record.roleid}"  dataType="Integer" require="false" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/sysAdmin/sysrolesdic!searchList.do?pageType=select','roleid','rolename');" value="选择"/></td>
			<th align="center" style="width:10%">审批额度类型:</th>
			<td style="width:35%">
			<select name="examine_limit_type"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${examine_limit_type}" var="examine_limit_type">
			<option value="${examine_limit_type.key}" <c:if test="${record.examine_limit_type==examine_limit_type.key}">selected</c:if>>${examine_limit_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">最小额度:</th>
			<td style="width:35%">
			<input type="text" name="min" value="${record.min}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">最大额度:</th>
			<td style="width:35%">
			<input type="text" name="max" value="${record.max}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">计量单位:</th>
			<td style="width:35%">
			<input type="text" name="unit" value="${record.unit}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">有效标志:</th>
			<td style="width:35%">
			<select name="yxbz"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${yxbz}" var="yxbz">
			<option value="${yxbz.key}" <c:if test="${record.yxbz==yxbz.key}">selected</c:if>>${yxbz.value}</option>
			</c:forEach>
			</select></td>
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
