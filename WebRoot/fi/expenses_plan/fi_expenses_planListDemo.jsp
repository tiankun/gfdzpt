<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sysFrams.db.DataBaseControl"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	List exp_status = DataBaseControl
			.getInstance()
			.getOutResultSet(
					"select t.dmz,t.dmzmc from CODE t where t.dmlb='exp_status'",
					null);
					
					
%>
<c:set var="exp_status" value="<%=exp_status%>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/default.css">
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
  var mid=$("#mid").val();
  var pid=$("#p_id").val();
  var appstate=$("#appstate").val();
  var app_date1=$("#app_date1").val();
  var app_date2=$("#app_date2").val();
	$.ajax({
		   type: "POST",
		   url:'<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!total.do',
		   data: "mid="+mid+"&appstate="+appstate+"&app_date1="+app_date1+"&app_date2="+app_date2+"&pid="+pid, 
		   dataType:"json",
		   success: function(data){
				$("#total").val(data.sum);
				$("#countnum").val(data.count);
				$("#total1").val(data.sum1);
		   }
	});
});


function setparms(){  
	$('#list').datagrid('load',$("#form1").form2json());
	
} 


function caozuo(value,row){
	
		return '<a href="javascript:void(0)" plain="true" onclick="show('+row.id+')">查看</a>&nbsp;&nbsp;'
		
	if(row.subflag=='0'){
	return '<a href="javascript:void(0)" plain="true" onclick="approval('+row.id+')">审批</a>&nbsp;&nbsp;'+
	       '<a href="javascript:void(0)" plain="true" onclick="show('+row.id+')">查看</a>&nbsp;&nbsp;'
	}
	if(row.subflag=='1'&&(row.appstate=='2'||row.appstate=='-1')){
	return  '<a href="javascript:void(0)" plain="true" onclick="zuofei('+row.id+')">作废</a>&nbsp;&nbsp;'
	if(print=='0'){
	 return '<a href="javascript:void(0)" plain="true" onclick="print('+row.id+')"><font color="#00ff00">打印</font></a>&nbsp;&nbsp;'
	}if(print!='0'){
	 return '<a href="javascript:void(0)" plain="true" onclick="print('+row.id+')"><font color="#ff0000">打印</font></a>&nbsp;&nbsp;'
	 }
	}
	
}

 function buy(value,row){
 if(value=='1'){
return '<a>是</a>';
}else{
return '<a>否</a>';
}
 
 }
function show(id){
	$('#win').empty();
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!searchById.do?id='+id+'&folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
	$('#win').window('open');
}
function approval(id){
	$('#win').empty();
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!approval.do?id='+id+'&folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
	$('#win').window('open');
}
function zuofei(id){
	$('#win').empty();
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!zuofei.do?f_id='+id+'&folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
	$('#win').window('open');
}
function print(id){
	$('#win').empty();
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!print.do?f_id='+id+'&folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
	$('#win').window('open');
}
function kzsq(id){
	$('#win').empty();
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!add.do?folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
	$('#win').window('open');
}

function spzt(value,row){

if(row.subflag=='0'){
return '<a><font color="#ff9955">待提交</font></a>';
} 
if(row.subflag=='1'&&!(row.appstate=='2'||row.appstate=='-1')){
return '<a><font color="#ff0000">'+value+'</font></a>';
} 
if(row.subflag=='1'&& row.appstate=='2'){
return '<a><font color="#008000">'+value+'</font></a>';
} 
if(row.subflag=='1'&& row.appstate=='-1'){
return '<a><font color="#5f809d">'+value+'</font></a>';
}

}
function pzje(value,row){
if(value==null){
return '<a><font color="#ff0000">未批准</font></a>';
}
if(value!=''){
return '<a><font color="#008000">'+value+'</font></a>';
}

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
						style="width: 35.5%" class="Wdate" id="app_date1"
						name="app_date1" style="ime-mode:disabled" /> --- <input
						onClick="WdatePicker()" style="width: 35.5%" class="Wdate"
						id="app_date2" name="app_date2" style="ime-mode:disabled" />
					</td>

					<td align="center" class="myInputTitle" style="width:10%">审核状态:</td>
					<td><select id="appstate" name="appstate" class="dropdownlist"><option
								value="">---</option>
							<c:forEach items="${exp_status}" var="exp_status">
								<option value="${exp_status.dmz}">${exp_status.dmzmc}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td align="center" class="myInputTitle" style="width:10%">部门</td>
					<td><input type="text" name="branchname" id="branchname"
						readonly="readonly" value="${searchMap.branchname}"
						class="textBox" /> <input type="hidden" name="mid" id="mid"
						value="${searchMap.departid}" /> <input type="button"
						onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','mid','branchname');"
						value="选择" />
						
						<td align="center" class="myInputTitle" style="width:10%">申请人</td>
						<td><input type="text" name="name" id="name"
						value="${searchMap.name}" class="textBox" /> <input type="hidden"
						name="p_id" id="p_id" value=""/> <input
						type="button"
						onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
						value="选择" /></td>
				</tr>

				<tr>
					<td align="center" colspan="4">
					<c:if test="${user.duty_id!='3'}">
			             <input href="#" type="button" value="开支申请"style="color: red" onclick="openDG('添加信息','<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!add.do','fi_expenses_planDtl')" class="link"></input>
			       </c:if>
			           <input type="button" value="　查　询　" onClick="setparms();total();" />&nbsp;&nbsp;&nbsp;&nbsp;
					   <input type="reset" value="　重    置　" /></td>
				</tr>
			</table>
		</form>
		<table width="100%" border="1" cellspacing="0" cellpadding="0">
			<tr>
				<th align="center" style="width:10%">统计申请总金额(元)</th>
				<td style="width:30%"><input type="text" name="total"
					readonly="readonly" id="total" value="" style="width:100%" /></td>
					
				<th align="center" style="width:10%">统计批准总金额(元)</th>
				<td style="width:30%"><input type="text" name="total1"
					readonly="readonly" id="total1" value="" style="width:100%" /></td>
             <!-- </tr>
             <tr> -->
				<th align="center" style="width:10%">记录条数(条)</th>
				<td style="width:30%"><input type="text" name="countnum"
					readonly="readonly" id="countnum" value="" style="width:100%" /></td>

			</tr>


		</table>
	</div>

	<table id="list" class="easyui-datagrid"
		data-options="toolbar:'#tb',rownumbers:true,pagination:true,fitColumns:true,nowrap:false,fit:true,striped:true,
		url:'<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!searchListDemo.do',pageSize:20">
		<thead>
			<tr >
				<th data-options="field:'name',width:30,sortable:true">申请人</th>
				<th data-options="field:'branchname',width:60,sortable:true">部门</th>
				<th data-options="field:'apply_time',width:60,sortable:true">申请时间</th>
				<th data-options="field:'advance_time',width:60,sortable:true">预支时间</th>
				<th data-options="field:'total_sum',width:60,sortable:true">申请金额</th>
				<th data-options="field:'buy',width:30,sortable:true,formatter:buy">综合办购买</th>
				<th
					data-options="field:'dmzmc',width:60,sortable:true,formatter:spzt">审批状态</th>
				<th
					data-options="field:'final_sum',width:60,sortable:true,formatter:pzje">批准金额</th>
				<th
					data-options="field:'opt',width:90,align:'center',formatter:caozuo">操作</th>


			</tr>
		</thead>
		<div id="win" closed="true" minimizable="false" class="easyui-window"
			title="支付信息" style="width:1000px;height:600px;padding:10px;"></div>
		<div id="PageUpDnDiv"
			style="PADDING-TOP:5px;padding-bottom:5px; text-align:center">
	</table>

</body>
</html>
