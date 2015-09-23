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
<script type="text/javascript">
function acceptFZR(){
	var fzr = $("#design_fzr").val();
	if(!fzr){
		alert("请选择负责人");
		document.form1.design_fzr.focus();
		return;
	}
	if(confirm("确认选择负责人么？"))
		document.form1.submit();
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/ds/task/Ds_dstask!${editMod}.do" method="post">
	<input type="hidden" id="editMod" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" id="flag" name="flag" value="${record.flag}"/>
	<input type="hidden" id="appstate" name="appstate" value="${record.appstate}"/>
	<input type="hidden" id="ds_type" name="ds_type" value="${record.ds_type}"/>
	<input type="hidden" id="task_state" name="task_state" value="${record.task_state}"/>
		<div class="divMod2">
		<div class="divMod1">任务书详情</div>
		<table class="table_border" style="width:100%">
            <tr>
			<th align="center" style="width:10%">项目名称:</th>
			<td style="width:35%">
			<input type="text" id="name" name="name" value="${record.name}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" name="proj_id" class="hidden" value="${record.proj_id}"  readonly="readonly" dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"/>
			<th align="center" style="width:10%">设计类型:</th>
			<td style="width:35%">
			<select disabled="disabled" readonly="readonly" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${ds_type}" var="ds_type">
			<option value="${ds_type.key}" <c:if test="${record.ds_type==ds_type.key}">selected</c:if>>${ds_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">任务名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  readonly="readonly" dataType="Require"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">任务书编号:</th>
			<td style="width:35%">
			<input type="text" name="dstask_num" id="dstask_num" readonly="readonly" value="${record.dstask_num}" style="width:95%" class="textBoxNoBorder"/>
			</td>
			</tr>
            <tr>
			<th align="center" style="width:10%">发起部门:</th>
			<td style="width:35%">
			<input type="text" readonly="readonly" id="launch_deptname"  readonly="readonly" value="${record.dept_name}" class="textBoxNoBorder"/>
			<input type="text" id="launch_deptid" name="launch_deptid"  readonly="readonly" value="${record.launch_deptid}"  dataType="Integer" require="false"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="hidden"/></td>
			<th align="center" style="width:10%">发起人:</th>
			<td style="width:35%">
			<input type="text" readonly="readonly" id="launch_personname" readonly="readonly" value="${record.p_name}" class="textBoxNoBorder"/>
			<input type="text" id="launch_personid" name="launch_personid" readonly="readonly" value="${record.launch_personid}"  dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="hidden"/></td>
			</tr>
            <tr>
			<th align="center" style="width:10%">发起日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="launch_time" value="${record.launch_time}"  readonly="readonly" dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">交付日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="delivery_time" value="${record.delivery_time}"  readonly="readonly" dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">任务内容:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
			<textarea rows="3" cols="70" name="content" value="${record.content}"  readonly="readonly" dataType="Require" maxLength="1000"  style="width:99%;border: 0px"/>${record.content}</textarea></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">任务书负责人:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="task_fzrname" value="${record.task_fzrname}" dataType="Require"  maxLength="50" class="textBoxNoBorder"/>
			<input type="text" id="task_fzr" name="task_fzr" value="${record.task_fzr}" class="hidden"/>
			</td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
            </tr>
        </table>
        </div>
        </br>
        <div class="divMod2">
		<div class="divMod1">审批</div>
		<table class="table_border" style="width:100%">
            <tr>
			<th align="center" style="width:10%">审批人:</th>
			<td style="width:35%">
			<input type="text" name="approver_name" readonly="readonly" id="approver_name" readonly="readonly" value="${record.approver_name}" class="textBoxNoBorder"/>
			<input type="text" name="approver_id" readonly="readonly" id="approver_id" value="${record.approver_id}"  dataType="Integer" require="false"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="hidden"/></td>
			<th align="center" style="width:10%">审批日期:</th>
			<td style="width:35%">
			<input type="text" name="apptime" readonly="readonly" id="apptime" value="${record.apptime}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">审批意见:</th>
			<td style="width:35%" colspan="3">
			<textarea rows="3" name="appopinion" readonly="readonly" value="${record.appopinion}"  maxLength="500"  style="width:99%;border: 0px"/>${record.appopinion}</textarea></td>
            </tr>
		</table>
		</div>
		<br/>
		<div class="divMod2">
		<div class="divMod1">选择设计负责人</div>
		<table class="table_border" style="width:100%">
			<tr>
				<th align="center" style="width:10%">设计负责人:</th>
				<td style="width:35%">
					<select name="design_fzr" id="design_fzr" class="dropdownlist"><option value="">---</option>
					<c:forEach items="${designerList}" var="designerList">
					<option value="${designerList.key}" <c:if test="${record.design_fzr==designerList.key}">selected</c:if>>${designerList.value}</option>
					</c:forEach>
					</select>
				</td>
				<th align="center" style="width:10%"></th>
				<td style="width:35%"></td>
			</tr>
		</table>
		</div>
		<div class="div-button">
		<!-- accept() -->
			<input type="button" value="提交" onclick="javascript:acceptFZR();" style="${btnDisplay}" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='accept'){
	$("#task_state").attr("value","3");
	$("#flag").attr("value","1");
}
</script>	
</body>
</html>
