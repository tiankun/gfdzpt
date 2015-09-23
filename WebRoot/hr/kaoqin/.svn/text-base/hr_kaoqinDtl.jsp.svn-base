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

	<form name="form1" action="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">部门:</th>
			<td style="width:35%">
			<input type="text" name="dept_id" value="${record.branchname}" readonly="readonly"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">姓名:</th>
			<td style="width:35%">
			<input type="text" id="name" name="name" value="${record.name}" readonly="readonly" class="textBoxNoBorder"/>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">录入时间:</th>
			<td style="width:35%">
			<input type="text" name="inut_date" value="${record.inut_date}" readonly="readonly"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">考勤时间:</th>
			<td style="width:35%">
			<input type="text" name="attend_date" value="${record.attend_date}" readonly="readonly"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            
            <tr>
			<th align="center" style="width:10%">上午:</th>
			<td style="width:35%">
			<input type="text" name="am" value="${record.am}" readonly="readonly"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">下午:</th>
			<td style="width:35%">
			<input type="text" name="pm" value="${record.pm}" readonly="readonly"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">上午说明:</th>
			<td style="width:35%">
			<input type="text" name="amnote" value="${record.amnote}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">下午说明:</th>
			<td style="width:35%">
			<input type="text" name="pmnote" value="${record.pmnote}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
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
