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
<script type="text/javascript" src="../../jscripts/easyUI132/jquery.json-2.4.min.js"></script>
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
	<div class="divMod1">基本信息</div>
	<table class="table_border" style="width:100%">
	<tr>
		<th align="center" style="width:10%">出差人:</th>
		<td style="width:35%">${recordTravel.person_name}
		</td>
		<th align="center" style="width:10%">所在部门:</th>
		<td style="width:35%">${recordTravel.dept_name}
		</td>
        </tr>
        <tr>
		<th align="center" style="width:10%">出发时间:</th>
		<td style="width:35%">${recordTravel.b_time}
		</td>
		<th align="center" style="width:10%">返回时间:</th>
		<td style="width:35%">${recordTravel.e_time}
		</td>
        </tr>
        <tr>
        <th align="center" style="width:10%">出差事由:</th>
		<td style="width:35%" colspan="3">
		<textarea rows="3" cols="70" name="reason" value="${recordTravel.reason}" dataType="Require" maxLength="1000"  style="width:99%;border: 0px"/>${recordTravel.reason}</textarea></td>
        </tr>
	</table>
	</div>
	
	<div class="divMod2">
	<div class="divMod1">出差路线</div>
	<table class="dataListTable" style="width:100%">
	<tr>
	<th>序号</th>
	<th>目的地</th>
	<th>交通工具</th>
    <th>到达时间</th>
    <th>离开时间</th>
    <th>报销标准</th>
	</tr>
	<c:forEach items="${recordRoute}" var="result" varStatus="status">
	<tr>
	<td>${status.index+1}</td>
	<td>${result.areaname}</td>
	<td>${result.vehicle}</td>
	<td>${result.r_time}</td>
	<td>${result.l_time}</td>
	<td>${result.r_fund}</td>
	</tr>
	</c:forEach>
	</table>
	</div>
	
	<!-- 随行人员 -->
	<c:if test="${not empty recordSub}">
	<div class="divMod2">
	<div class="divMod1">随行人员</div>
	<table class="dataListTable" width="100%">
	<tr>
	<th>序号</th>
    <th>姓名</th>
    <th>部门</th>
    </tr>
    <c:forEach items="${recordSub}" var="result" varStatus="status">
	<tr>
	<td>${status.index+1}</td>
	<td width="45%">${result.person_name}</td>
	<td width="45%">${result.dept_name}</td>
	</tr>
	</c:forEach>
    </table>
	</div>
	</c:if>
	
	
	<!-- 审批意见 -->
	<c:if test="${not empty recordApp}">
	<div class="divMod2">
	<div class="divMod1">审批记录</div>
	<table class="dataListTable" width="100%">
	<tr>
	<th>序号</th>
       <th>审批人</th>
       <th>审批时间</th>
       <th>审批意见</th>
       </tr>
       <c:forEach items="${recordApp}" var="result" varStatus="status">
	<tr>
	<td>${status.index+1}</td>
	<td width="20%">${result.person_name}</td>
	<td width="25%">${result.apptime}</td>
	<td width="45%">${result.appopinion}</td>
	</tr>
	</c:forEach>
    </table>
	</div>
	</c:if>
	
	<form name="form1" action="<%=request.getContextPath()%>/gm/travel/Gm_travel!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="travelid" value="${recordTravel.id}"/>
	<input type="hidden" name="departid" id="departid" value=""/>
	<input type="hidden" name="auditid" id="auditid" value=""/>
	<input type="hidden" name="apptime" id="apptime" value=""/>
	<input type="hidden" name="appstate" id="appstate" value=""/>
	
	<div class="divMod2">
	<div class="divMod1">审批意见</div>
	<table class="table_border" style="width:100%">
	<tr>
    <th align="center" style="width:10%">审批意见:<span style="color:Red;">*</span></th>
	<td style="width:35%" colspan="3">
	<textarea rows="3" cols="70" name="appopinion" value="" dataType="Require" maxLength="200"  style="width:99%;border: 0px"/>${record.appopinion}</textarea></td>
    </tr>
	</table>
	</div>
	
	<div class="div-button">
		<input type="button" value="通过" onclick="audit(1)" style="${btnDisplay}" />
		<input type="button" value="不通过" onclick="audit(-2)" style="${btnDisplay}" />
		<input type="button" value="打回" onclick="audit(-1)" style="${btnDisplay}" />
		<input type="reset" value="重置"/>
		<input type="button" value="关闭" onclick="javascript:closeDG();"/>
	</div>
	</form>
<script type="text/javascript" defer="defer">
$("#auditid").attr("value","${user.base_info_id}");
$("#departid").attr("value","${user.branchid}");
$("#apptime").attr("value","${apptime}");
</script>
</body>
</html>