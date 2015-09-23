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
  var ration_apply_id=$("#ration_apply_id").val();
  var maname=$("#maname").val();
  var dh=$("#dh").val();
  var type=$("#type").val();
  var app_date1=$("#app_date1").val();
  var app_date2=$("#app_date2").val();
	$.ajax({
		   type: "POST",
		   url:'<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!total.do',
		   data: "ration_apply_id="+ration_apply_id+"&maname="+maname+"&dh="+dh+"&app_date1="+app_date1+"&app_date2="+app_date2+"&type="+type, 
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

</script>

</head>
<body>
	<div id="tb" style="padding:0px;height:auto">
		<form name="form1" id="form1" style="margin:0px;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" class="myInputTitle" style="width:10%">配给单</td>
					<td><input type="text" id="ration_apply_id" name="ration_apply_id"
								value="${searchMap.ration_apply_id}" class="textBox" /> 
					</td>
					<td align="center" class="myInputTitle" style="width:10%">物质名称</td>
					<td>
					 <input type="text" id="maname" name="maname" value="${searchMap.maname}" class="textBox" />
					 <input type="hidden" id="materiel_id" name="materiel_id"dataType="Double" require="false" class="hidden" />
					 <input type="button"onclick="openSelect('branch_select','<%=request.getContextPath()%>/materiel/Materiel!searchList.do?pageType=select','materiel_id','maname');" value="选择" />
					</td>
				</tr>
				<tr>
					<td align="center" class="myInputTitle" style="width:10%">出入库单</td>
					<td>
					<input type="text" id="dh" name="dh" value="${searchMap.dh}" />
					</td>
					<td align="center" class="myInputTitle" style="width:10%">项目</td>
					<td>
					<input type="text" id="proname" name="proname" value="${searchMap.proname}" class="textBox" />
					<input type="hidden" id="prj_id" name="prj_id" value="" dataType="Double" require="false" class="hidden" />
					<input type="button"onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','prj_id','proname');"value="选择" />
					</td>
                </tr>
                <tr>
                    <td align="center" style="width:10%">类型</td>
                    <td>
                    <select name="type" id="type" style="width: 60%">
							<option value="">未选择</option>
							<option value="入库" <c:if test="${searchMap.type=='入库'}">selected</c:if>>入库</option>
							<option value="出库" <c:if test="${searchMap.type=='出库'}">selected</c:if>>出库</option>
					</select>
					</td>
					<td align="center" style="width:10%">操作时间</td>
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
				<td style="width:35%"><input type="text" name="total" readonly="readonly"id="total" value=""  style="width:100%"/></td>	
				<th align="center" style="width:10%">记录条数(条)</th>
				<td style="width:35%"><input type="text" name="countnum" readonly="readonly"id="countnum" value=""  style="width:100%"/></td>
	
	</tr>
	
	
	</table>
	</div>
	
	<table id="list" class="easyui-datagrid" 
		data-options="toolbar:'#tb',rownumbers:true,pagination:true,fitColumns:true,nowrap:false,fit:true,striped:true,
		url:'<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!churukqueryDemo.do',pageSize:20">
		<thead>
			<tr>
				<th data-options="field:'proname',width:180,sortable:true">项目</th>
				<th data-options="field:'dh',width:60,sortable:true">单号</th>
				<th data-options="field:'ration_apply_id',width:60,sortable:true">配给单</th>
				<th data-options="field:'name',width:60,sortable:true">材料名称</th>	
                <th data-options="field:'brname',width:60,sortable:true">品牌</th>
                <th data-options="field:'type',width:60,sortable:true">类型</th>
                <th data-options="field:'num',width:30,sortable:true">数量</th>
                <th data-options="field:'price',width:60,sortable:true">入库单价</th>
                <th data-options="field:'money',width:60,sortable:true">金额</th>
				<th data-options="field:'odate',width:60,sortable:true">操作时间</th>
				<th data-options="field:'pname',width:60,sortable:true">操作人</th>
				<th data-options="field:'note',width:60,sortable:true">备注</th>

				
			</tr>
		</thead>
		<div id="win" closed="true" minimizable="false" class="easyui-window"
			title="支付信息" style="width:1000px;height:600px;padding:10px;"></div>
		<div id="PageUpDnDiv"
			style="PADDING-TOP:5px;padding-bottom:5px; text-align:center">
	</table>
	
</body>
</html>
