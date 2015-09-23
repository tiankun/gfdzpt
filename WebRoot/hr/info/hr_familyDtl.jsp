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

	<form name="form1" action="<%=request.getContextPath()%>/hr/info/Hr_family!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		<input type="hidden" name="p_id" value="${pid}"/>
            <tr>
			<th align="center" style="width:10%">姓名:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  dataType="Require"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">称谓:</th>
			<td style="width:35%">
			<input type="text" name="cheng_wei" value="${record.cheng_wei}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">出生日期:</th>
			<td style="width:35%">
			<input type="text" name="birthday" value="${record.birthday}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
           	<th align="center" style="width:10%">住址:</th>
			<td style="width:35%">
			<input type="text" name="zhu_zhi" value="${record.zhu_zhi}"  maxLength="500"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">联系电话:</th>
			<td style="width:35%">
			<input type="text" name="phone" value="${record.phone}" maxLength="17" require="false" style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">单位:</th>
			<td style="width:35%">
			<input type="text" name="dan_wei" value="${record.dan_wei}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">部门:</th>
			<td style="width:35%">
			<input type="text" name="bu_men" value="${record.bu_men}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">职务:</th>
			<td style="width:35%">
			<input type="text" name="zhi_wu" value="${record.zhi_wu}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">备注:</th>
			<td style="width:35%" colspan="3">
			<textarea rows="5" name="remark" value="${record.remark}"  maxLength="1000"  style="width:99%;border: 0px"/>${record.remark}</textarea></td>
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
