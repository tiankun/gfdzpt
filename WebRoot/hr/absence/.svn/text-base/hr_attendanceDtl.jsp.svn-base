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

	<form name="form1" action="<%=request.getContextPath()%>/hr/absence/Hr_attendance!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">早上要求签到时间:</th>
			<td style="width:35%">
			<input type="text" name="am_order_attend" value="${record.am_order_attend}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">下午实际签到时间:</th>
			<td style="width:35%">
			<input type="text" name="pm_actual_attend" value="${record.pm_actual_attend}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">下午要求签到时间:</th>
			<td style="width:35%">
			<input type="text" name="pm_order_attend" value="${record.pm_order_attend}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">导入人员:</th>
			<td style="width:35%">
			<input type="text" name="input_person" value="${record.input_person}"  maxLength="22"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">人员ID:</th>
			<td style="width:35%">
			<input type="text" id="name" name="name" value="${record.name}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="p_id" name="p_id" value="${record.p_id}" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/hr/absence/hr_base_info!searchList.do?pageType=select','p_id','name');" value="选择"/></td>
			<th align="center" style="width:10%">部门id:</th>
			<td style="width:35%">
			<input type="text" name="dept_id" value="${record.dept_id}"  maxLength="22"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">考勤开始日期:</th>
			<td style="width:35%">
			<input type="text" name="attend_date" value="${record.attend_date}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">早上实际签到时间:</th>
			<td style="width:35%">
			<input type="text" name="am_actual_attend" value="${record.am_actual_attend}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">导入时间:</th>
			<td style="width:35%">
			<input type="text" name="input_date" value="${record.input_date}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td><th></th><td></td>
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
