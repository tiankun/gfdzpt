<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sysFrams.db.DataBaseControl"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	List advance_type = DataBaseControl
			.getInstance()
			.getOutResultSet(
					"select t.dmz,t.dmzmc from CODE t where t.dmlb='advance_type'",
					null);
   List advance_appstate = DataBaseControl
			.getInstance()
			.getOutResultSet(
					"select t.dmz,t.dmzmc from CODE t where t.dmlb='advance_appstate'",
					null);
  List apply_reason = DataBaseControl
			.getInstance()
			.getOutResultSet(
					"select t.dmz,t.dmzmc from CODE t where t.dmlb='apply_reason'",
					null);
					
					
%>
<c:set var="advance_type" value="<%=advance_type%>" />
<c:set var="advance_appstate" value="<%=advance_appstate%>" />
<c:set var="apply_reason" value="<%=apply_reason%>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jscripts/easyui/themes/ui-cupertino/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jscripts/easyui/themes/icon.css">
<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/jscripts/myjscripts/jquery-1.8.0.min.js"></script> --%>
<script src="<%=request.getContextPath()%>/jscripts/jquery.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
	type="text/javascript"></script>

<script language="JavaScript"
	src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jscripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jscripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jscripts/easyui/my.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jscripts/EditWin.js"></script>
<script type="text/javascript">

$(document).ready(function total(){
  var advance_type=$("#advance_type").val();
  var apply_reason=$("#apply_reason").val();
  var appstate=$("#appstate").val();
  var apply_date1=$("#apply_date1").val();
  var apply_date2=$("#apply_date2").val();
  var deptid=$("#deptid").val();
  var personname=$("#personname").val();
  var cc={"advance_type":advance_type,"apply_reason":apply_reason,"appstate":appstate,"apply_date1":apply_date1,"apply_date2":apply_date2,"deptid":deptid,"personname":personname};
  
	$.ajax({
		   type: "POST",
		   url:'<%=request.getContextPath()%>/fi/advance/Fi_advance!total.do',
		   data: cc,
		   dataType:"json",
		   success: function(data){
				$("#total").val(data.sum);
				$("#total1").val(data.sum1);
				$("#countnum").val(data.count);
		   }
	});
});


function setparms(){  
	$('#list').datagrid('load',$("#form1").form2json());
	
} 
function qk(val){
if(val=='0'){
return '<span style="color:#008000">已还清</span>';
}else {

return '<span >'+val+'</span>';
}

}

function caozuo(value,row){
	
		return '<a href="javascript:void(0)" plain="true" onclick="show('+row.id+')">查看</a>&nbsp;&nbsp;'

}
function skdw(value){
if(value==null){
return '<span style="color:#ff0000;">未填写收款单位</span>';
}else{
return value;

}
}
function sqyy(value){
if(value=='1'){
return '<span >备用金</span>';
}else if(value=='2'){
return '<span >投标保证金</span>';

}

}
function zzlx(value){
if(value=='1'){
return '<span >支票</span>';
}
if(value=='2'){
return '<span >现金</span>';
}
if(value=='3'){
return '<span >银行担保保函</span>';
}
if(value=='4'){
return '<span >电汇</span>';
}
}
 
function show(id){
	$('#win').empty();
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/advance/Fi_advance!searchById.do?id='+id+'&folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
	$('#win').window('open');
}

</script>

</head>
<body>
	<div id="tb" style="padding:0px;height:auto">
		<form name="form1" id="form1" style="margin:0px;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
				    <td align="center" style="width:10%">申请时间</td>
					<td style="width:35%"><input onClick="WdatePicker()"
						style="width: 35.5%" class="Wdate" id="apply_date1" name="apply_date1"
						style="ime-mode:disabled" /> 到 <input onClick="WdatePicker()"
						style="width: 35.5%" class="Wdate" id="apply_date2" name="apply_date2"
						style="ime-mode:disabled" /></td>
						
					<td align="center" class="myInputTitle" style="width:10%">审批状态</td>
					<td>
						<select name="appstate" id="appstate"class="dropdownlist" style="width:30%"><option value="">---</option>
						<c:forEach items="${advance_appstate}" var="advance_appstate">
						<option value="${advance_appstate.dmz}" <c:if test="${searchMap.appstate==advance_appstate.dmzmc}">selected</c:if>>${advance_appstate.dmzmc}</option>
						</c:forEach>
						</select></td>
				   </tr>
				   <tr>
					<td align="center" class="myInputTitle" style="width:10%">申请原因</td>
					<td>
						<select name="apply_reason" id="apply_reason" style="width:30%" class="dropdownlist"><option value="">---</option>
						<c:forEach items="${apply_reason}" var="apply_reason">
						<option value="${apply_reason.dmz}" <c:if test="${searchMap.apply_reason==apply_reason.dmzmc}">selected</c:if>>${apply_reason.dmzmc}</option>
						</c:forEach>
						</select></td>
					<td align="center" class="myInputTitle" style="width:10%">暂支类型</td>
					 <td>
						<select name="advance_type"  id="advance_type" style="width:30%" class="dropdownlist"><option value="">---</option>
						<c:forEach items="${advance_type}" var="advance_type">
						<option value="${advance_type.dmz}" <c:if test="${searchMap.advance_type==advance_type.dmzmc}">selected</c:if>>${advance_type.dmzmc}</option>
						</c:forEach>
						</select></td>

				</tr>
				<tr>
				<td align="center" class="myInputTitle" style="width:10%">部门</td>
				<td>
					<input type="text" style="width:30%" name="branchname" id="branchname" value="${searchMap.branchname}" readonly="readonly" class="textBox"/>
					<input type="hidden" name="deptid" id="deptid" value="${searchMap.deptid}" class="hidden"/>
					<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','deptid','branchname');" value="选择"/></td>
				<td align="center" class="myInputTitle" style="width:10%">申请人</td>
				<td>
			         <input type="text" name="personname" id="personname" value="${searchMap.personname}" style="width:30%" class="textBox"/></td>
				</tr>
				<tr>
					<td align="center" colspan="4"><input type="button"
						value="　查　询　" onClick="setparms();total();" />&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="reset" value="　重    置　" /></td>
				</tr>
			</table>
		</form>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<tr>
	<th align="center" style="width:10%">申请总金额(元)</th>
				<td style="width:30%"><input type="text" name="total" readonly="readonly"
					id="total" value=""  style="width:100%"/></td>
	<th align="center" style="width:10%">欠款总金额(元)</th>
				<td style="width:30%"><input type="text" name="total1" readonly="readonly"
					id="total1" value=""  style="width:100%"/></td>
					
					
					<th align="center" style="width:10%">记录条数(条)</th>
				    <td style="width:30%"><input type="text" name="countnum" readonly="readonly"
					id="countnum" value=""  style="width:100%"/></td>
	
	</tr>
	
	
	</table>
	</div>
	
	<table id="list" class="easyui-datagrid" title="款项支付审批列表"
		data-options="toolbar:'#tb',rownumbers:true,pagination:true,fitColumns:true,nowrap:false,fit:true,striped:true,
		url:'<%=request.getContextPath()%>/fi/advance/Fi_advance!searchListDemo.do',pageSize:20">
		<thead>
			<tr>
				<th data-options="field:'person_name',width:60,sortable:true">申请人</th>
				<th data-options="field:'dept_name',width:60,sortable:true">部门</th>
				<th data-options="field:'payeename',width:150,sortable:true,formatter:skdw" >收款单位</th>
				<th data-options="field:'apply_time',width:60,sortable:true">申请时间</th>
				<th data-options="field:'apply_reason',width:90,sortable:true,formatter:sqyy">申请原因</th>
				<th data-options="field:'advance_type',width:90,sortable:true,formatter:zzlx">暂支类型</th>
				<th data-options="field:'apply_money',width:90,sortable:true">申请金额</th>
				<th data-options="field:'qk',width:60,sortable:true,formatter:qk">欠款</th>	
                <th data-options="field:'dmzmc',width:90,sortable:true">审核状态</th>
				<th data-options="field:'opt',width:90,align:'center',formatter:caozuo">操作</th>
			</tr>
		</thead>
		<div id="win" closed="true" minimizable="false" class="easyui-window"
			title="支付信息" style="width:1000px;height:600px;padding:10px;"></div>
		<div id="PageUpDnDiv"
			style="PADDING-TOP:5px;padding-bottom:5px; text-align:center">
	</table>
	
</body>
</html>
