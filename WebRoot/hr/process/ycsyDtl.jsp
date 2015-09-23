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

	<form name="form1" action="<%=request.getContextPath()%>/hr/process/Hr_opinion!ycsy.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="p_id" value="${record.p_id}"/>
	<input type="hidden" name="dept_id" value="${record.dept_id}"/>
	<input type="hidden" name="apply_time" value="${record.apply_time}"/>
		<table class="table_border" style="width:100%">
		
			<tr>
			<th align="center" style="width:10%">延长试用:<span style="color: red">&nbsp;*</span></th>
			<td style="width:35%">
			<input type="text" name="ycsyq" value="${record.ycsyq}"  dataType="Date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/>
			</td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">意见:<span style="color: red">&nbsp;*</span></th>
			<td style="width:35%" colspan="3">
			<textarea name="ycopp" value="${record.ycopp}" rows="5" style="width: 99%;border: 0px" dataType="Require"  maxLength="500">${record.ycopp}</textarea>
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
</body>
</html>
