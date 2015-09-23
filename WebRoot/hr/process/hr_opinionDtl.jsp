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

	<form name="form1" action="<%=request.getContextPath()%>/hr/process/Hr_opinion!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="p_id" value="${record.p_id}"/>
	<input type="hidden" name="e_date" value="${e_date}"/>
	<input type="hidden" name="hr_state" value="${hr_state}"/>
	<input type="hidden" name="hr_type" value="${record.hr_type}"/>
	<input type="hidden" name="apply_id" id="apply_id" value=""/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">审批意见:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
			<!-- 人事主管审核 -->
			<c:if test="${user.branchid==2&&user.duty_id==2&&record.hr_state==0}">
			<textarea name="hr_opinion" rows ="5" value="${record.hr_opinion}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.hr_opinion}</textarea>
            <input type="hidden" name=dm_opinion value="${record.dm_opinion}"/>
            <input type="hidden" name=gm_opinion value="${record.gm_opinion}"/>
            </c:if>
            <!-- 部门经理审核 -->
            <c:if test="${user.branchid!=2&&user.duty_id==2&&record.hr_state==1}">
            <textarea name="dm_opinion" rows ="5" value="${record.dm_opinion}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.dm_opinion}</textarea>
            <input type="hidden" name=hr_opinion value="${record.hr_opinion}"/>
            <input type="hidden" name=gm_opinion value="${record.gm_opinion}"/>
            </c:if>
            <!-- 总经理审核 -->
            <c:if test="${user.duty_id==3&&record.hr_state==2}">
            <textarea name="gm_opinion" rows ="5" value="${record.gm_opinion}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.gm_opinion}</textarea>
            <input type="hidden" name=hr_opinion value="${record.hr_opinion}"/>
            <input type="hidden" name=dm_opinion value="${record.dm_opinion}"/>
            </c:if>
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
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='editRegular'){
	$("#apply_id").attr("value","${apply_id}");
}
</script>
</body>
</html>
