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

	<form name="form1" action="<%=request.getContextPath()%>/hr/info/Hr_reward!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
			<input type="hidden" name="p_id" value="${pid}"/>
            <tr>
			<th align="center" style="width:10%">类型:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select name="reward_type"  dataType="Require"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${reward_type}" var="reward_type">
			<option value="${reward_type.key}" <c:if test="${record.reward_type==reward_type.key}">selected</c:if>>${reward_type.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">时间:</th>
			<td style="width:35%">
			<input type="text" name="reward_time" value="${record.reward_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">名称:</th>
			<td style="width:35%">
			<input type="text" name="ming_chen" value="${record.ming_chen}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">单位:</th>
			<td style="width:35%">
			<input type="text" name="dan_wei" value="${record.dan_wei}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">相关资料:</th>
			<td style="width:35%">
			<input type="text" name="zi_liao" value="${record.zi_liao}"  maxLength="500"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">备注:</th>
			<td style="width:35%">
			<input type="text" name="remark" value="${record.remark}"  maxLength="2000"  style="width:95%" class="textBoxNoBorder"/></td>
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
