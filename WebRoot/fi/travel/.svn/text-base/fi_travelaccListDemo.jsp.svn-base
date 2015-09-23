<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sysFrams.db.DataBaseControl"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	List appstate = DataBaseControl.getInstance().getOutResultSet("select t.dmz,t.dmzmc from CODE t where t.dmlb='fiacc_state'",null);
	List travelacc_appstate = DataBaseControl.getInstance().getOutResultSet("select t.dmz,t.dmzmc from CODE t where t.dmlb='travelacc_appstate'",null);			
%>
<c:set var="appstate" value="<%=appstate%>" />
<c:set var="travelacc_appstate" value="<%=travelacc_appstate%>" />
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
  var personname=$("#name").val();
  var mid=$("#mid").val();
  var appstate=$("#appstate").val();
  var app_date1=$("#app_date1").val();
  var app_date2=$("#app_date2").val();
	$.ajax({
		   type: "POST",
		   url:'<%=request.getContextPath()%>/fi/travel/Fi_travelacc!total.do',
		   data: "personname="+personname+"&appstate="+appstate+"&app_date1="+app_date1+"&app_date2="+app_date2+"&mid="+mid, 
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
function removeSelected(isBatchDelete,url,id) {
	batchDelete("#list",isBatchDelete,url,id,setparms);
}

function caozuo(value,row){
      	
	
     return '<a href="javascript:void(0)" plain="true" onclick="show('+row.id+')">查看</a>&nbsp;&nbsp;'
	

}

function submit(id){
   
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/financial/Fi_financial_account!sub.do?id='+id+'&folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
	if(confirm("您确定要提交么？")){
		$.ajax({
				type: "POST",
				   url: "<%=request.getContextPath()%>/fi/financial/Fi_financial_account!subDemo.do",
				   data: "id="+id,
				   success: function(data){
					   if(data==1){
						   alert("提交成功！");
						   location.reload();
					   }
				   }
		   });
	}
	


}
function show(id){
    $('#win').empty();
	$('#win').append('<iframe src="<%=request.getContextPath()%>/fi/travel/Fi_travelacc!showDtl.do?id='+id+'&folder=Budget&flag=4&mark=show&oldname=file" width="100%" height="100%" frameborder=0></iframe>');
	$('#win').window('open');



}

function proname(value,row){
if(value==null){
return'<a><font color="#ff0000"><center>未关联项目</center></font></a>'
}
return'<a><font color="#008000"><center>'+value+'</center></font></a>'
}
function spzt(value,row){
if(value=='0'){
return'<a><font color="#ff0000"><center>待部门经理审批</center></font></a>';
}
if(value=='1'){
return'<a><font color="#ff0000"><center>待会计领导审批</center></font></a>';
}
if(value=='2'){
return'<a><font color="#ff0000"><center>待财务经理审批</center></font></a>';
}
if(value=='3'){
return'<a><font color="#ff0000"><center>待公司领导审批</center></font></a>';
}
if(value=='4'){
return'<a><font color="#00ff00"><center>审批通过</center></font></a>';
}
if(value=='-1'){
return'<a><font color="#0000ff"><center>打回</center></font></a>';
}
if(value=='-2'){
return'<a><font color="#ff0000"><center>不通过</center></font></a>';
}
}


</script>

</head>
<body>
	<div id="tb" style="padding:0px;height:auto">
		<form name="form1" id="form1" style="margin:0px;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
				<td align="center" style="width:10%">报销日期</td>
					<td style="width:35%"><input onClick="WdatePicker()"
						style="width: 35.5%" class="Wdate" id="app_date1" name="app_date1"
						style="ime-mode:disabled" /> --- <input onClick="WdatePicker()"
						style="width: 35.5%" class="Wdate" id="app_date2" name="app_date2"
						style="ime-mode:disabled" /></td>
						
					<td align="center" class="myInputTitle" style="width:10%">申请人</td>
					<td><input type="text" name="name" id="name"
						value="${searchMap.name}" class="textBox" /> <input type="hidden"
						name="p_id" id="p_id" value=""/> <input
						type="button"
						onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
						value="选择" /></td>
					</tr>
					
					<tr>
					
				
					<td align="center" class="myInputTitle" style="width:10%">审核状态:</td>
					<td><select id="appstate" name="appstate" class="dropdownlist"><option
								value="">---</option>
							<c:forEach items="${travelacc_appstate}" var="travelacc_appstate">
								<option value="${travelacc_appstate.dmz}">${travelacc_appstate.dmzmc}</option>
							</c:forEach>
					</select></td>
					<td align="center" class="myInputTitle" style="width:10%">部门</td>
					<td><input type="text" name="branchname" id="branchname"
						readonly="readonly" value="${searchMap.branchname}"
						class="textBox" /> <input type="hidden" name="mid" id="mid"
						value="${searchMap.departid}" /> <input type="button"
						onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','mid','branchname');"
						value="选择" />
                    </tr>
                    
				<tr>
					<td align="center" colspan="6">
					<input type="button"  value="　报账　"href="#" style="color: red" onclick="openDG('添加信息','<%=request.getContextPath()%>/fi/travel/Fi_travelacc!add.do','fi_financial_accountDtl')" class="link"></input>
					<input type="button"
						value="　查　询　" onClick="setparms();total();" />&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="reset" value="　重    置　" /></td>
				</tr>
			</table>
		</form>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<tr>
	<th align="center" style="width:10%">报销总额</th>
				<td style="width:35%"><input type="text" name="total" readonly="readonly"
					id="total" value=""  style="width:100%"/></td>
					
					
					<th align="center" style="width:10%">记录条数(条)</th>
				<td style="width:35%"><input type="text" name="countnum" readonly="readonly"
					id="countnum" value=""  style="width:100%"/></td>
	
	</tr>
	
	
	</table>
	</div>
	
	<table id="list" class="easyui-datagrid" 
		data-options="toolbar:'#tb',rownumbers:true,pagination:true,fitColumns:true,nowrap:false,fit:true,striped:true,
		url:'<%=request.getContextPath()%>/fi/travel/Fi_travelacc!searchListDemo.do',pageSize:20">
		<thead>
			<tr>
				<th data-options="field:'dept_name',width:60,sortable:true">部门</th>
				<th data-options="field:'person_name',width:60,sortable:true">出差人</th>
				<th data-options="field:'reim_time',width:60,sortable:true">报销日期</th>
                <th data-options="field:'proj_name',width:180,sortable:true,formatter:proname">项目名称</th>
                <th data-options="field:'totalcost',width:90,sortable:true">报销总金额</th>
                 <th data-options="field:'spzt',width:180,sortable:true">审批状态</th>
				<th data-options="field:'opt',width:90,align:'center',formatter:caozuo">操作</th>

				
			</tr>
		</thead>
		<div id="win" closed="true" minimizable="false" class="easyui-window"
			title="详细信息" style="width:1000px;height:600px;padding:10px;"></div>
			<div id="fj" closed="true" minimizable="false" class="easyui-window"
			title="上传附件" style="width:1000px;height:600px;padding:10px;"></div>
		<div id="PageUpDnDiv"
			style="PADDING-TOP:5px;padding-bottom:5px; text-align:center">
	</table>
	
</body>
</html>
