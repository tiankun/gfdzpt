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

	<form name="form1" action="<%=request.getContextPath()%>/sysAdmin/Sys_approve!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">授权人ID:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="name" name="name" value="${record.name}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="licensorid" name="licensorid" value="${record.licensorid}"  dataType="Integer" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','licensorid','name');" value="选择"/></td>
			<th align="center" style="width:10%">审批部门ID:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="branchname" name="branchname" value="${record.branchname}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="approval_departid" name="approval_departid" value="${record.approval_departid}"  dataType="Integer" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','approval_departid','branchname');" value="选择"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">审批目标:部门集合:</th>
			<td style="width:35%">
			<input type="text" name="approvaldeparts" value="${record.approvaldeparts}"  maxLength="500"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">审批目标:员工集合:</th>
			<td style="width:35%">
			<input type="text" name="approvalpersons" value="${record.approvalpersons}"  maxLength="500"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">审批等级:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="approval_rating" value="${record.approval_rating}"  dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">优先等级:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="precedence_level" value="${record.precedence_level}"  dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">是否审批部门:</th>
			<td style="width:35%">
			<input type="text" name="isapproval_deaprt" value="${record.isapproval_deaprt}"  maxLength="1"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">是否是申请人结束审批:</th>
			<td style="width:35%">
			<input type="text" name="isselfapproval" value="${record.isselfapproval}"  maxLength="1"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">是否启用:</th>
			<td style="width:35%">
			<input type="text" name="enable" value="${record.enable}"  maxLength="1"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">组ID:</th>
			<td style="width:35%">
			<input type="text" name="aroupid" value="${record.aroupid}"  dataType="Integer" require="false"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">备注:</th>
			<td style="width:35%">
			<input type="text" name="remark" value="${record.remark}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td><th></th><td></td>
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
