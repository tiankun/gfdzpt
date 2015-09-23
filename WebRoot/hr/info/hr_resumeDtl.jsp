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

	<form name="form1" action="<%=request.getContextPath()%>/hr/info/Hr_resume!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		<input type="hidden" name="p_id" value="${pid}"/>
		
            <tr>
			<th align="center" style="width:10%">单位:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="dan_wei" value="${record.dan_wei}"  dataType="Require"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">离职原因:</th>
			<td style="width:35%">
			<input type="text" name="li_zhi" value="${record.li_zhi}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">部门:</th>
			<td style="width:35%">
			<input type="text" name="bu_men" value="${record.bu_men}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">职务:</th>
			<td style="width:35%">
			<input type="text" name="zhi_wu" value="${record.zhi_wu}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">开始时间:</th>
			<td style="width:35%">
			<input type="text" name="start_time" value="${record.start_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">结束时间:</th>
			<td style="width:35%">
			<input type="text" name="end_time" value="${record.end_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">证明人:</th>
			<td style="width:35%">
			<input type="text" name="zheng_ming" value="${record.zheng_ming}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">证明人电话:</th>
			<td style="width:35%">
			<input type="text" name="zhengm_dianhua" value="${record.zhengm_dianhua}"  maxLength="17"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">备注:</th>
			<td style="width:35%" colspan="3">
			<textarea rows="5" name="remark" value="${record.remark}" style="width:95%"/>${record.remark}</textarea></td>
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
