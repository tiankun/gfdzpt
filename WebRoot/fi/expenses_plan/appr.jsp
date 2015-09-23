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
<link rel="stylesheet" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/easyUI132/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/easyUI132/jquery.easyui.min.js"></script>
<script type="text/javascript">
function audit(state){
	$("#appstate").attr("value",state);
	if(Validator.Validate(document.form1,3)){
		document.form1.submit();
	}
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

		<div class="divMod2">
		<div class="divMod1">计划信息</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">申请人:</th>
			<td style="width:35%">${recordExp.name}</td>
			<th align="center" style="width:10%">所在部门:</th>
			<td style="width:35%">${recordExp.dept_name}</td>
			</tr>
            <tr>
			<th align="center" style="width:10%">预支时间:</th>
			<td style="width:35%">${recordExp.advance_time}</td>
            <th align="center" style="width:10%">综合办代购:</th>
			<td style="width:35%">${buy[recordExp.buy]}</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">申请金额:</th>
			<td style="width:35%;background: #EEEEEE;color: red">${recordExp.total_sum}</td>
			<th align="center" style="width:10%">金额大写:</th>
			<td style="width:35%;background: #EEEEEE;color: red">${recordExp.upper_chn}</td>
            </tr>
		</table>
		<table class="dataListTable" width="100%">
			<tr>
			<th>申请事项及说明:</th>
			<th>预计金额:</th>
			</tr>
			<c:forEach items="${recordItem}" var="result" varStatus="status">
			<tr>
			<td><c:out value="${result.item}" /></td>
      		<td><c:out value="${result.plan_money}" /></td>
			</tr>
			</c:forEach>
		</table>
		</div>
		<c:if test="${not empty recordApp}">
		<div class="divMod2">
		<div class="divMod1">审批记录</div>
		<table class="dataListTable" width="100%">
			<tr>
        		<th align="center" class="myInputTitle" style="width:8%;">审批人</th>
		        <th  align="center" class="myInputTitle" style="width:40%">审批人意见</th>
		        <th align="center" class="myInputTitle" style="width:15%">审批时间</th>
        	</tr>
        	<c:forEach items="${recordApp}" var="appRecord">
			<tr>
			<td><c:out value="${appRecord.name}" /></td>
            <td width="40%">${appRecord.appopinion}</td>
            <td>${appRecord.apptime}</td>
			</tr>
			</c:forEach>
		</table>
		</div>
		</c:if>
		
		</br>
		<div class="divMod2">
		<div class="divMod1">审批意见</div>
		<form name="form1" id="form1" action="<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!approval.do" method="post">
		<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
		<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
		<input type="hidden" name="id" value="${recordExp.id}"/>
		<input type="hidden" name="exp_id" value="${recordExp.id}"/>
		<input type="hidden" name="accdept" value="${recordExp.departid}"/>
		<input type="hidden" name="dept_id" id="dept_id" value=""/>
		<input type="hidden" name="auditid" id="auditid" value=""/>
		<input type="hidden" name="apptime" id="apptime" value=""/>
		<input type="hidden" name="appstate" id="appstate" value=""/>
		<table class="table_border" width="100%">
			<tr>
			<th align="center" style="width:10%">审批意见:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
			<textarea rows="3" name="appopinion" value="" dataType="Require" maxLength="200" style="width:98%;height:90%" class="textBoxNoBorder"></textarea>
			</td>
			</tr>
            <c:if test="${!(user.duty_id==1||user.duty_id==2)}">
            <tr>
			<th align="center" style="width:10%">批准金额:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="final_sum" id="final_sum" value="${recordExp.final_sum}" dataType="Double" maxLength="10" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
            </tr>
            </c:if>
		</table></div>
		<div class="div-button">
			<input type="button" value="通过" onclick="audit(1)" style="${btnDisplay}" />
			<input type="button" value="不通过" onclick="audit(-2)" style="${btnDisplay}" />
			<input type="button" value="打回" onclick="audit(-1)" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
$("#auditid").attr("value","${user.base_info_id}");
$("#dept_id").attr("value","${user.branchid}");
$("#apptime").attr("value","${apptime}");
$("#final_sum").val("${recordExp.total_sum}");
</script>	
</body>
</html>
