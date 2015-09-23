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
<script type="text/javascript">
function mySubmit(){
	if(Validator.Validate(document.form1,3))
	{
		document.form1.submit();
	}
}
</script>

</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/hr/process/Hr_test!csSub.do" method="post">
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="pid" value="${record.p_id}"/>
		<div class="divMod2" style="margin-top:3px;">
		<div class="divMod1" align="center">初试信息</div>
		<table class="table_border" style="width:100%">
			<th align="center" style="width:10%">初试结果:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
			<select name="result" dataType="Require" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${result}" var="result">
			<option value="${result.key}" <c:if test="${record.result==result.key}">selected</c:if>>${result.value}</option>
			</c:forEach>
			</select>
			</td>
		</table>
		</div>
		<div class="div-button">
			<input	type="button" value="提交" style="${btnDisplay}" onclick="mySubmit()"/>
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
</body>
</html>
