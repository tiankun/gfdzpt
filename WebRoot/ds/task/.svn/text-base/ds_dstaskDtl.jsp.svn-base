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
function sub(sub_type){
	$("#appstate").attr("value",sub_type);
	if(Validator.Validate(document.form1,3)){
		document.form1.submit();
		}
}

function codeType(workCode){
	$.ajax({
		   type: "POST",
		   url: '<%=request.getContextPath()%>/sysAdmin/codeCreate!getCodeNum.do',
		   data:"codeType=RW&workCode="+workCode,
		   success: function(data){
			   $("#dstask_num").attr("value",data);
		   }
	});
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
	<input type="hidden" id="task_state" name="task_state" value="${record.task_state}"/>
		<c:if test="${empty editMod}">
		<div class="divMod2">
		<div class="divMod1">任务书详情</div>
		</c:if>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">项目名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="proj_name" name="proj_name" value="${record.proj_name}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" name="proj_id" class="hidden" value="${record.proj_id}"  dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"/>
			<c:if test="${not empty editMod}">
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','proj_id','proj_name');" value="选择"/>
			</c:if></td>
			<th align="center" style="width:10%">设计类型:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select dataType="Require" name="ds_type" class="ddlNoBorder" onchange='codeType(this.value)'>
			<option value="">---</option>
			<c:forEach items="${ds_type}" var="ds_type">
			<option value="${ds_type.key}" <c:if test="${record.ds_type==ds_type.key}">selected</c:if>>${ds_type.value}</option>
			</c:forEach>
			</select>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">任务名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  dataType="Require"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">任务书编号:</th>
			<td style="width:35%">
			<input type="text" name="dstask_num" id="dstask_num" readonly="readonly" value="${record.dstask_num}" style="width:95%" class="textBoxNoBorder"/>
			</td>
			</tr>
            <tr>
			<th align="center" style="width:10%">发起部门:</th>
			<td style="width:35%">
			<input type="text" readonly="readonly" id="launch_deptname" value="${record.dept_name}" class="textBoxNoBorder"/>
			<input type="text" id="launch_deptid" name="launch_deptid" value="${record.launch_deptid}"  dataType="Integer" require="false"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="hidden"/></td>
			<th align="center" style="width:10%">发起人:</th>
			<td style="width:35%">
			<input type="text" readonly="readonly" id="launch_personname" value="${record.p_name}" class="textBoxNoBorder"/>
			<input type="text" id="launch_personid" name="launch_personid" value="${record.launch_personid}"  dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="hidden"/></td>
			</tr>
            <tr>
			<th align="center" style="width:10%">发起日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="launch_time" value="${record.launch_time}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">交付日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="delivery_time" value="${record.delivery_time}" dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">任务书负责人:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="task_fzrname" value="${record.task_fzrname}" dataType="Require"  maxLength="50" class="textBoxNoBorder"/>
			<input type="text" id="task_fzr" name="task_fzr" value="${record.task_fzr}" class="hidden"/>
			<c:if test="${not empty editMod}">
			<input type="button" onclick="openSelect('hrbaseinfo_select','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','task_fzr','task_fzrname');" value="选择"/>
			</c:if>
			</td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">任务内容:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
			<textarea rows="3" cols="70" name="content" value="${record.content}" dataType="Require" maxLength="1000"  style="width:99%;border: 0px"/>${record.content}</textarea></td>
            </tr>
        </table>
        </div>
        </br>
        <c:if test="${not empty record.appopinion}">
        <div class="divMod2">
        <div class="divMod1">审批情况</div>
		<table class="table_border" style="width:100%">
            <tr>
			<th align="center" style="width:10%">审批状态:</th>
			<td style="width:35%">
			<input type="text" name="appstate" value="${appstate[record.appstate]}" readonly="readonly" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">审批意见:</th>
			<td style="width:35%">
			<input type="text" name="appopinion" value="${record.appopinion}" readonly="readonly" maxLength="500"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">审批人:</th>
			<td style="width:35%">
			<input type="text" name="approver_name" readonly="readonly" id="approver_name" readonly="readonly" value="${record.approver_name}" class="textBoxNoBorder"/>
			<th align="center" style="width:10%">审批日期:</th>
			<td style="width:35%">
			<input type="text" name="apptime" value="${record.apptime}" readonly="readonly" dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
		</table>
		</div>
		<br/>
		</c:if>
		<c:if test="${not empty record.fzr}">
		<div class="divMod2">
		<div class="divMod1">设计负责人</div>
		<table class="table_border" style="width:100%">
			<tr>
				<th align="center" style="width:10%">设计负责人:</th>
				<td style="width:35%">${record.fzr}
				</td>
				<th align="center" style="width:10%"></th>
				<td style="width:35%"></td>
			</tr>
		</table>
		</div>
		</c:if>
		<div class="div-button">
			<input	type="button" value="保存且提交" onclick="sub(1)" style="${btnDisplay}" />
			<input	type="button" value="保存但不提交" onclick="sub(0)" style="${btnDisplay}" />
			<input type="reset" value="重置" style="${btnDisplay}"/>
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='add'){
	$("#launch_deptname").attr("value","${dept_name}");
	$("#launch_deptid").attr("value","${user.branchid}");
	$("#launch_personname").attr("value","${user.user_name}");
	$("#launch_personid").attr("value","${user.base_info_id}");
	$("#flag").attr("value","0");
	$("#task_state").attr("value","1")
}
</script>	
</body>
</html>
