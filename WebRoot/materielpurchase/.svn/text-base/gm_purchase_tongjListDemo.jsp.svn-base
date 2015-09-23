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
  var proname=$("#proname").val();
  var ration_apply_id=$("#ration_apply_id").val();
  var maname=$("#maname").val();
  var brandname=$("#brandname").val();
  var gong=$("#gong").val();
  var model=$("#model").val();
  var app_date1=$("#app_date1").val();
  var app_date2=$("#app_date2").val();
	$.ajax({
		   type: "POST",
		   url:'<%=request.getContextPath()%>/materielpurchase/Gm_purchase!total.do',
		   data: "proname="+proname+"&ration_apply_id="+ration_apply_id+"&maname="+maname+"&brandname="+brandname+"&gong="+gong+"&model="+model+"&app_date1="+app_date1+"&app_date2"+app_date2, 
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

 function buy(value,row){
 if(value=='1'){
      return '<a>是</a>';
   }else{
       return '<a>否</a>';
     }
 
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

</script>

</head>
<body>
	<div id="tb" style="padding:0px;height:auto">
		<form name="form1" id="form1" style="margin:0px;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" style="width:10%">项目</td>
					<td>
			            <input type="text" id="proname" name="proname" value="${searchMap.proname}"  class="textBox"/>
			            <input type="hidden" id="prj_id" name="prj_id" value=""  dataType="Double" require="false" class="hidden"/>
			            <input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','prj_id','proname');" value="选择"/>
			       </td>

					<td align="center" class="myInputTitle" style="width:10%">配给单</td>
					<td>
			          <input type="text" value="${searchMap.ration_apply_id}" id="ration_apply_id" name="ration_apply_id"  style="width:95%" class="textBox"/>
			        </td>
				</tr>

				<tr>
					<td align="center" class="myInputTitle" style="width:10%">物质名称</td>
					<td>
							<input type="text" id="maname" name="maname" value="${searchMap.maname}" class="textBox"/>
							<input type="hidden" id="materiel_id" name="materiel_id" value=""  dataType="Double" require="false" class="hidden"/>
							<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/materiel/Materiel!searchList.do?pageType=select','materiel_id','maname');" value="选择"/>
				    </td>
				    <td align="center" class="myInputTitle" style="width:10%">品牌</td>
				    <td>
			                <input type="text" name="brandname" id="brandname" value="${searchMap.brandname}" class="textBox" />
							<input type="hidden" name="brand_id" id="brand_id" value="" class="hidden" />
							<input type="button" onclick="openSelect('选择材料品牌','<%=request.getContextPath()%>/materiel/Ma_brand!searchList.do?pageType=select','brand_id','brandname');" value="选择" />
			
			       </td>
				</tr>
				<tr>
				<td align="center" class="myInputTitle" style="width:10%">供应商</td>
				<td>
	              <input type="text" name="gong" id="gong"  value="${searchMap.gong}" class="textBox" />
				  <input type="hidden" name="gongys"id="gongys" value="" class="hidden" />
			      <input type="button"onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','gongys','gong');" value="选择" />
			   </td>
				
				<td align="center" class="myInputTitle" style="width:10%">型号</td>
				<td>
			        <input type="text" name="model" id="model" value="${searchMap.model}" class="textBox" />
			   </td>
				</tr>
				<tr>
					<td align="center" style="width:10%">审批时间</td>
					<td style="width:35%"><input onClick="WdatePicker()"
						style="width: 35.5%" class="Wdate" id="app_date1" name="app_date1"
						style="ime-mode:disabled" /> --- <input onClick="WdatePicker()"
						style="width: 35.5%" class="Wdate" id="app_date2" name="app_date2"
						style="ime-mode:disabled" /></td>
				</tr>

				<tr>
					<td align="center" colspan="4">
			           <input type="button" value="　查　询　" onClick="setparms();total();" />&nbsp;&nbsp;&nbsp;&nbsp;
					   <input type="reset" value="　重    置　" /></td>
				</tr>
			</table>
		</form>
		<table width="100%" border="1" cellspacing="0" cellpadding="0">
			<tr>
				<th align="center" style="width:10%">统计总金额(元)</th>
				<td style="width:35%"><input type="text" name="total"
					readonly="readonly" id="total" value="" style="width:100%" /></td>


				<th align="center" style="width:10%">记录条数(条)</th>
				<td style="width:35%"><input type="text" name="countnum"
					readonly="readonly" id="countnum" value="" style="width:100%" /></td>

			</tr>


		</table>
	</div>

	<table id="list" class="easyui-datagrid"
		data-options="toolbar:'#tb',rownumbers:true,pagination:true,fitColumns:true,nowrap:false,fit:true,striped:true,pageList:[10,20,30,50,99999],
		url:'<%=request.getContextPath()%>/materielpurchase/Gm_purchase!tongjiDemo.do',pageSize:20">
		<thead>
			<tr >
				<th data-options="field:'proname',width:90,sortable:true">项目</th>
				<th data-options="field:'ration_apply_id',width:60,sortable:true">配给单</th>
				<th data-options="field:'maname',width:60,sortable:true">物质名称</th>
				<th data-options="field:'brand',width:30,sortable:true">品牌</th>
				<th data-options="field:'sqsl',width:30,sortable:true">采购数量</th>
				<th data-options="field:'price',width:30,sortable:true">采购价</th>
				<th data-options="field:'tprice',width:30,sortable:true">价格</th>
				<th data-options="field:'yaoqdhrq',width:30,sortable:true">到货日期</th>
				<th data-options="field:'sprq',width:30,sortable:true">审批日期</th>
				<th data-options="field:'gys',width:60,sortable:true">供应商</th>
				

			</tr>
		</thead>
		<div id="win" closed="true" minimizable="false" class="easyui-window"
			title="支付信息" style="width:1000px;height:600px;padding:10px;"></div>
		<div id="PageUpDnDiv"
			style="PADDING-TOP:5px;padding-bottom:5px; text-align:center">
	</table>

</body>
</html>
