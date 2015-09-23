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

	<form name="form1" id="searchForm" action="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!saveNote.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="type" value="${type}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">姓名:</th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">考勤日期:</th>
			<td style="width:35%">
			<input type="text" name="date" value="${record.attend_date}"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">区间:</th>
			<td style="width:35%">
			<c:if test="${type=='am'}"><input type="text" name="date1" value="上午"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></c:if>
			<c:if test="${type=='pm'}"><input type="text" name="date2" value="下午"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></c:if>
			</td>
			<th align="center" style="width:10%">出勤情况:</th>
			<td style="width:35%">
			<c:if test="${type=='am'}"><input type="text" name="qk1" value="${record.am}"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></c:if>
			<c:if test="${type=='pm'}"><input type="text" name="qk2" value="${record.pm}"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></c:if>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">说明:</th>
			<td colspan="3">
			 <textarea rows="3" cols="70" name="note"><c:if test="${type=='am'}">${record.amnote}</c:if><c:if test="${type=='pm'}">${record.pmnote}</c:if></textarea>
			</td>
			</tr>
            
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
		
		<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	
	</div>
		
	</form>
	
</body>
</html>
