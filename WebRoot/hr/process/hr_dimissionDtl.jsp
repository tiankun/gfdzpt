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
var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="dim_apply"]',
					{
						uploadJson : '${pageContext.request.contextPath}/jscripts/kindeditor/jsp/upload_json.jsp',
						allowFileManager : false,
						afterCreate : function() { 
                                             this.sync(); 
                                                        }, 
                              afterBlur:function(){ 
                                            this.sync(); 
                                             }    
					});
});

</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/hr/process/Hr_dimission!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${record.dept_id}"/>
	<input type="hidden" name="p_id" id="p_id" value="${record.p_id}"/>
	<input type="hidden" name="dim_time" id="dim_time" value="${record.dim_time}"/>
	<input type="hidden" name="appstate" id="appstate" value="${record.appstate}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">离职申请:</th>
			<td style="width:35%" colspan="3">
			<c:if test="${editMod=='add'}">
			<textarea name="dim_apply" value="${record.dim_apply}" dataType="Require" maxLength="2000" style="width: 1200px; height: 500px;"/>
			${record.dim_apply}
			</textarea>
			</c:if>
			<c:if test="${editMod!='add'}">
			${record.dim_apply}
			</c:if>
			</td>
			</tr>
			<c:if test="${editMod!='add'}">
			<c:if test="${not empty record.dm_opp}">
			<tr>
			<th align="center" style="width:10%">所在部门审批意见:</th>
			<td style="width:35%" colspan="3">
			<textarea name="dm_opp" rows ="5" value="${record.dm_opp}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.dm_opp}</textarea>
			</td>
			</tr>
			</c:if>
			<c:if test="${not empty record.hr_opp}">
			<tr>
			<th align="center" style="width:10%">人事部审批意见:</th>
			<td style="width:35%" colspan="3">
			<textarea name="hr_opp" rows ="5" value="${record.hr_opp}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.hr_opp}</textarea>
			</td>
			</tr>
			</c:if>
			<c:if test="${not empty record.gem_opp}">
			<tr>
			<th align="center" style="width:10%">总经理审批意见:</th>
			<td style="width:35%" colspan="3">
			<textarea name="gem_opp" rows ="5" value="${record.gem_opp}"  dataType="Require"  maxLength="500"  style="width:98%;border: 0px">${record.gem_opp}</textarea>
			</td>
			</tr>
			</c:if>
			<c:if test="${not empty record.gem_opp&&not empty record.hr_opp&&not empty record.dm_opp}">
			<tr>
			<td style="width:35%" colspan="4">
			<div align="center">
			综合部确认&nbsp;<input type="checkbox" disabled="disabled" <c:if test="${record.gm_affirm=='1'}"> checked="checked" </c:if>/>
			&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;财务部确认&nbsp;<input type="checkbox" disabled="disabled" <c:if test="${record.fi_affirm=='1'}"> checked="checked" </c:if>/>
			&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;所在部门确认&nbsp;<input type="checkbox" disabled="disabled" <c:if test="${record.dm_affirm=='1'}"> checked="checked" </c:if>/>
			</div>
			</td>
			</tr>
			</c:if>
			</c:if>
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
if(editMod=='add'){
	$("#p_id").attr("value","${user.base_info_id}");
	$("#dept_id").attr("value","${user.branchid}");
	$("#dim_time").attr("value","${dim_time}");
	$("#appstate").attr("value","1");
}
</script>
</body>
</html>
