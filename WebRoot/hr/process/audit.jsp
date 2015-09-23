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
<script charset="UTF-8" src="${pageContext.request.contextPath}/jscripts/kindeditor/kindeditor-min.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/jscripts/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
function notPass(){
	$("#appstate").attr("value","-1");
	if(Validator.Validate(document.form1,3)){
		document.form1.submit();
		}
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/hr/process/Hr_dimission!audit.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="p_id" value="${record.p_id}"/>
	<input type="hidden" name="dept_id" value="${record.dept_id}"/>
	<input type="hidden" name="dm_affirm" value="${record.dm_affirm}"/>
	<input type="hidden" name="gm_affirm" value="${record.gm_affirm}"/>
	<input type="hidden" name="fi_affirm" value="${record.fi_affirm}"/>
	<input type="hidden" name="hr_affirm" value="${record.hr_affirm}"/>
	<input type="hidden" name="dim_time" id="dim_time" value="${record.dim_time}"/>
	<input type="hidden" name="appstate" id="appstate" value="${record.appstate}"/>
		<table class="table_border" style="width:100%">
		
			<tr>
			<th align="center" style="width:10%">离职申请:</th>
			<td style="width:35%" colspan="3">
			${record.dim_apply}
			</td>
			</tr>
			<!-- 部门经理审核 -->
            <c:if test="${user.branchid!=2&&user.duty_id==2&&record.appstate==1}">
            <tr>
            <th align="center" style="width:10%">审批意见:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
            <textarea name="dm_opp" rows ="5" value="${record.dm_opp}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.dm_opp}</textarea>
            </td>
            </tr>
            </c:if>
            <!-- 人事主管审核 -->
			<c:if test="${user.branchid==2&&user.duty_id==2&&record.appstate==2}">
			<tr>
			<th align="center" style="width:10%">所在部门审批意见:</th>
			<td style="width:35%" colspan="3">
			<textarea name="dm_opp" rows ="5" value="${record.dm_opp}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.dm_opp}</textarea>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">人事部审批意见:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
			<textarea name="hr_opp" rows ="5" value="${record.hr_opp}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.hr_opp}</textarea>
            </td>
            </tr>
            </c:if>
            
            <!-- 总经理审核 -->
            <c:if test="${user.duty_id==3&&record.appstate==3}">
            <tr>
			<th align="center" style="width:10%">所在部门审批意见:</th>
			<td style="width:35%" colspan="3">
			<textarea name="dm_opp" rows ="5" value="${record.dm_opp}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.dm_opp}</textarea>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">人事部审批意见:</th>
			<td style="width:35%" colspan="3">
			<textarea name="hr_opp" rows ="5" value="${record.hr_opp}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.hr_opp}</textarea>
            </td>
            </tr>
            <tr>
            <th align="center" style="width:10%">总经理审批意见:<span style="color:Red;">*</span></th>
            <td style="width:35%" colspan="3">
            <textarea name="gem_opp" rows ="5" value="${record.gem_opp}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.gem_opp}</textarea>
            </td>
            </tr>
            </c:if>
		</table>
		<br/>
		<div class="div-button">
			<input type="button" value="通过" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="button" value="不通过" onclick="notPass()"/>
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='dm'){
	$("#appstate").attr("value","2");
}
if(editMod=='hr'){
	$("#appstate").attr("value","3");
}
if(editMod=='gm'){
	$("#appstate").attr("value","4");
}
</script>	
</body>
</html>
