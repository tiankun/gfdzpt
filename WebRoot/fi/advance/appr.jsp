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
			<th align="center" style="width:10%">申请人:</th>
			<td style="width:35%">${recordAd.person_name}</td>
			<th align="center" style="width:10%">所在部门:</th>
			<td style="width:35%">${recordAd.dept_name}</td>
		</tr>
        <tr>
		<th align="center" style="width:10%">收款单位:</th>
		<td style="width:35%">
		<c:if test="${not empty recordAd.payeename}">${fukuan[recordAd.payeename]}</c:if>
		<c:if test="${empty recordAd.payeename}"><font color="red">尚未关联收款单位</font></c:if>
		</td>
		<th align="center" style="width:10%">借支类型:</th>
		<td style="width:35%">${advance_type[recordAd.advance_type]}</td>
		</tr>
           <tr>
           <th align="center" style="width:10%">申请原因:</th>
		<td style="width:35%">${apply_reason[recordAd.apply_reason]}</td>
		<th align="center" style="width:10%">付款单位:</th>
			<td style="width:35%">
			<c:if test="${not empty recordAd.fukuan_unit}">${fukuan[recordAd.fukuan_unit]}</c:if>
			<c:if test="${empty recordAd.fukuan_unit}"><font color="red">尚未填写</font></c:if>
			</td>
           </tr>
           <tr>
           <th align="center" style="width:10%">暂支金额:</th>
		<td style="width:35%"><font color="red">${recordAd.apply_money}</font></td>
		<th align="center" style="width:10%">大写:</th>
		<td style="width:35%"><font color="red">${recordAd.chn}</font></td>
           </tr>
           <tr>
           <th align="center" style="width:10%">备注:</th>
           <td style="width:35%" >
           <textarea name="remark" rows ="3" value="${recordAd.remark}" maxLength="500"  style="width:98%;border: 0px">${recordAd.remark}</textarea>
           </td>
           
           <th align="center" style="width:10%">附件列表:</th>
			<td style="width:35%" id="upTd">
			<a href="#" onclick="openDGMax('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${recordAd.id}&folder=fiAcc&flag=accessory&mark=show&oldname=file')">
					<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
		    </a>
	
			</td>
           </tr>
	</table>
	</div>
	<br/>
	<!-- 审批意见记录 -->
	<c:if test="${not empty recordApp}">
	<div class="divMod2">
	<div class="divMod1">审批记录</div>
	<table class="dataListTable" style="width:100%">
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
	</br>
	</c:if>
	
	<form name="form1" action="<%=request.getContextPath()%>/fi/advance/Fi_advance!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${recordAd.id}"/>
	<input type="hidden" name="accid" value="${recordAd.id}"/>
	<input type="hidden" name="accdept" value="${recordAd.deptid}"/>
	<input type="hidden" name="deptid" id="deptid" value=""/>
	<input type="hidden" name="auditid" id="auditid" value=""/>
	<input type="hidden" name="apptime" id="apptime" value=""/>
	<input type="hidden" name="appstate" id="appstate" value=""/>
	
	<div class="divMod2">
	<div class="divMod1">审批意见</div>
	<table class="table_border" style="width:100%">
	<c:if test="${fiFlag=='fiFlag'}">
	<tr>
	<th align="center" style="width:10%">付款单位:</th>
	<td style="width:35%">
	<select name="fukuan_unit" dataType="Require" class="ddlNoBorder" ><option value="">---</option>
	<c:forEach items="${fukuan}" var="fukuan">
	<option value="${fukuan.key}">${fukuan.value}</option>
	</c:forEach>
	</select>
	</td>
	<th align="center" style="width:10%"></th>
	<td style="width:35%"></td>
	</tr>
	</c:if>
	<tr>
	   <th align="center" style="width:10%">审批意见:<span style="color:Red;">*</span></th>
	<td style="width:35%" colspan="3">
	<textarea rows="3" cols="70" name="appopinion" value="" dataType="Require" maxLength="200"  style="width:99%;border: 0px"/></textarea></td>
	   </tr>
	</table>
	</div>
	
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
$("#deptid").attr("value","${user.branchid}");
$("#apptime").attr("value","${apptime}");
</script>
</body>
</html>
