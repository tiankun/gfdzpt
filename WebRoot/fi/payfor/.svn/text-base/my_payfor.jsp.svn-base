<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sysFrams.db.DataBaseControl"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	List payforstate = DataBaseControl
			.getInstance()
			.getOutResultSet(
					"select t.dmz,t.dmzmc from CODE t where t.dmlb='payforstate'",
					null);
%>
<c:set var="payforstate" value="<%=payforstate%>" />
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
  var p_id=$("#p_id").val();
  var gongys=$("#gongys").val();
  var payforstate=$("#payforstate").val();
  var app_date1=$("#app_date1").val();
  var app_date2=$("#app_date2").val();
	$.ajax({
		   type: "POST",
		   url:'<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor_audit!total.do',
		   data: "p_id="+p_id+"&gongys="+gongys+"&payforstate="+payforstate+"&app_date1="+app_date1+"&app_date2="+app_date2, 
		   dataType:"json",
		   success: function(data){
				$("#total").val(data.sum);
				$("#countnum").val(data.count);
		   }
	});
});


function setparms(){  
	$('#list').datagrid('load',$("#form1").form2json());
	
} 


function caozuo(value,row){
	
		return '<a href="javascript:void(0)" plain="true" onclick="show('+row.id+')">查看</a>&nbsp;&nbsp;'
		
	if(payforstate=='2'){
	return '<a href="javascript:void(0)" plain="true" onclick="approval('+row.id+')">审批</a>&nbsp;&nbsp;'
	}
	if(payforstate=='7'){
	return  '<a href="javascript:void(0)" plain="true" onclick="zuofei('+row.id+')">作废</a>&nbsp;&nbsp;'
	if(print=='0'){
	 return '<a href="javascript:void(0)" plain="true" onclick="print('+row.id+')"><font color="#00ff00">打印</font></a>&nbsp;&nbsp;'
	}if(print!='0'){
	 return '<a href="javascript:void(0)" plain="true" onclick="print('+row.id+')"><font color="#ff0000">打印</font></a>&nbsp;&nbsp;'
	 }
	}
	
}

 
function show(id){
	$('#win').empty();
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor_audit!searchById.do?id='+id+'&folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
	$('#win').window('open');
}
function approval(id){
	$('#win').empty();
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor_audit!edit.do?f_id='+id+'&type=kuaiji &folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
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
</script>

</head>
<body>
	<div id="tb" style="padding:0px;height:auto">
		<form name="form1" id="form1" style="margin:0px;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" class="myInputTitle" style="width:10%">姓名</td>
					<td><input type="text" name="name" id="name"
						value="${searchMap.name}" class="textBox" /> <input type="hidden"
						name="p_id" id="p_id" value=""/> <input
						type="button"
						onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
						value="选择" /></td>
					<td align="center" class="myInputTitle" style="width:10%">供应商</td>
					<td><input type="text" name="gong" id="gong"
						readonly="readonly" value="${searchMap.gong}" class="textBox" />
						<input type="hidden" name="gongys" id="gongys"
						value="${searchMap.gongys}"/> <input
						type="button"
						onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','gongys','gong');"
						value="选择" />
					</td>
				</tr>
				<tr>
					<td align="center" class="myInputTitle" style="width:10%">审核状态:</td>
					<td><select id="payforstate" name="payforstate" class="dropdownlist"><option
								value="">---</option>
							<c:forEach items="${payforstate}" var="payforstate">
								<option value="${payforstate.dmz}">${payforstate.dmzmc}</option>
							</c:forEach>
					</select></td>

					<td align="center" style="width:10%">申请时间</td>
					<td style="width:35%"><input onClick="WdatePicker()"
						style="width: 35.5%" class="Wdate" id="app_date1" name="app_date1"
						style="ime-mode:disabled" /> --- <input onClick="WdatePicker()"
						style="width: 35.5%" class="Wdate" id="app_date2" name="app_date2"
						style="ime-mode:disabled" /></td>
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
	<th align="center" style="width:10%">统计总金额(元)</th>
				<td style="width:35%"><input type="text" name="total" readonly="readonly"
					id="total" value=""  style="width:100%"/></td>
					
					
					<th align="center" style="width:10%">记录条数(条)</th>
				<td style="width:35%"><input type="text" name="countnum" readonly="readonly"
					id="countnum" value=""  style="width:100%"/></td>
	
	</tr>
	
	
	</table>
	</div>
	
	<table id="list" class="easyui-datagrid" title="款项支付审批列表"
		data-options="toolbar:'#tb',rownumbers:true,pagination:true,fitColumns:true,nowrap:false,fit:true,striped:true,
		url:'<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor_audit!lookList.do',pageSize:20">
		<thead>
			<tr>
				<th data-options="field:'name',width:60,sortable:true">申请人</th>
				<th data-options="field:'odate',width:90,sortable:true">申请时间</th>
				<th data-options="field:'money',width:90,sortable:true">总金额(元)</th>
				<th
					data-options="field:'sname',width:140,sortable:true">供应商</th>
					
               <th data-options="field:'dmzmc',width:90,sortable:true">审核状态</th>
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
