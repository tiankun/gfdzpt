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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css">
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
		   success: function(data){
		    var jsondata = eval('(' + data + ')'); 
				
				$("#total").val(jsondata.sum)
				$("#countnum").val(jsondata.count)
			   	
		   }
	});
 });


function setparms(){  
	$('#list').datagrid('load',$("#form1").form2json());
	
} 
function caozuo(val){

if(val=='未付'){
		return '<span style="color:#ff0000;">'+val+'</span>';
	}
	else if(val=='支付中'){
		return '<span style="color:#ff0000;">'+val+'</span>';
	}
	else if(val=='已付'){
		return '<span>'+val+'</span>';
	}
	
	
}

</script>

</head>
<body>
	<div  background="#ff00ff"   id="tb" style="padding:0px;height:auto ">
		<form name="form1" id="form1" style="margin:0px;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" class="myInputTitle" style="width:10%">配给单</td>
		            <td style="width:35%"><input type="text" name="ration_apply_id" style="width:90%"/></td>
		            
					<td align="center" class="myInputTitle" style="width:10%">支付情况</td>
					<td> 
			<select name="ispay" style="width: 60%">
			  <option value="">未选择</option>
			  <option value="0"  <c:if test="${searchMap.ispay=='0'}">selected</c:if>>未付</option>
			  <option value="1" <c:if test="${searchMap.ispay=='1'}">selected</c:if>>支付中 </option>
			  <option value="2" <c:if test="${searchMap.ispay=='2'}">selected</c:if>>已付</option>
			  
			</select>
				</tr>
				<tr>
					<td align="center" class="myInputTitle" style="width:10%">供应商</td>
					<td>
			            <input type="text" name="gong"
									id="gong"  value="${searchMap.gong}"
									class="textBox" />
								<!-- <input type="text" name="gongys"
									id="gongys" value=""
									class="hidden" /> -->
								<input type="button"
									onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','gongys','gong');"
									value="选择" /></td>
            
			
				</tr>


				<tr>
					<td align="center" colspan="4"><input type="button"
						value="　查　询　" onClick="setparms();" />&nbsp;&nbsp;&nbsp;&nbsp; <input
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
	
	<table id="list" class="easyui-datagrid" title="查询条件"
		data-options="toolbar:'#tb',rownumbers:true,pagination:true,fitColumns:true,nowrap:false,fit:true,striped:true,
		url:'<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!detailsearchDemo.do',pageSize:20">
		<thead>
			<tr>
				<th data-options="field:'ration_apply_id',width:90,sortable:true">配给单</th>
				<th data-options="field:'name',width:90,sortable:true">材料名称</th>
				<th data-options="field:'gongys',width:200,sortable:true">供应商</th>
				<th
					data-options="field:'brname',width:90,sortable:true",
					>品牌</th>
               <th data-options="field:'num',width:60,sortable:true">数量</th>
               <th data-options="field:'price',width:60,sortable:true">入库单价</th>
                <th data-options="field:'money',width:60,sortable:true">金额</th>
               <th data-options="field:'odate',width:60,sortable:true">操作时间</th>
				<th  
					data-options="field:'fk',width:90,align:'center',formatter:caozuo ">是否付款</th>
			</tr>
		</thead>
		<div id="win" closed="true" minimizable="false" class="easyui-window"
			title="支付信息" style="width:1000px;height:600px;padding:10px;"></div>
		<div id="PageUpDnDiv"
			style="PADDING-TOP:5px;padding-bottom:5px; text-align:center">
	</table>
	
</body>
</html>
