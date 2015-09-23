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
//添加进度计划
function addSchedule(){
	var count = parseInt($("#count").val())+1;
	$("#schedule").append("<tr id='schedule_tr"+count+"'>"
						+"<td id='name_td"+count+"'>"
						+"<input type='text' name='task_name"+count+"' id='task_name"+count+"'  style='width: 95%'value='' class='textBoxNoBorder' />"
						+"</td>"
						+"<td id='ftask_td"+count+"'>"
						+"<input type='text' name='ftask_name"+count+"' style='width: 95%' id='ftask_name"+count+"' value='' class='textBoxNoBorder'/>"
						+"</td>"
						+"<td id='resource_td"+count+"'>"
						+"<input type='text' name='sc_res"+count+"' id='sc_res"+count+"' value=''  maxLength='500'  style='width:95%' class='textBoxNoBorder'/>"
						+"</td>"
						+"<td id='start_time_td"+count+"'>"
						+"<input type='text' name='start_time"+count+"' id='start_time"+count+"' value=''  dataType='Date'  onfocus='WdatePicker()' style='ime-mode:disabled'  style='width:95%' class='textBoxNoBorder'/>"
						+"</td>"
						+"<td id='end_time_td"+count+"'>"
						+"<input type='text' name='end_time"+count+"' id='end_time"+count+"' value=''  dataType='Date'  onfocus='WdatePicker()' style='ime-mode:disabled'  style='width:95%' class='textBoxNoBorder'/>"
						+"</td>"
						+"<td id='btn_td"+count+"'>"
				 		+"<img src='<%=request.getContextPath()%>/image/delete.png' onclick='deltr(this);return false;'/>"
				 		+"</td>"+"</tr>");

	$("#count").attr("value",count);
}

//删除进度计划
function deltr(obj){
	 $(obj).parent().parent().remove(); //删除当前行
	 sum();
}

function sub(id)
{
	if(confirm("确定提交申请"))
		{
		document.form1.submit();
		}
}

function scheduledel(id){
	if(confirm("确定删除吗?")){
		window.location.href="<%=request.getContextPath()%>/ds/result/Ds_schedule!deleteSch.do?id="+id;
		return true;
	}
	return false;
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" id="form1" action="<%=request.getContextPath()%>/ds/result/Ds_schedule!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="text" name="result_id" id="result_id" value="${record.result_id}" class="hidden"/>
	<input type="text" name="proj_id" id="proj_id" value="${record.proj_id}" class="hidden"/>
	<input type="text" name="ds_type" id="ds_type" value="${record.ds_type}" class="hidden"/>
	<input type="text" name="operator" id="operator" value="${record.operator}" class="hidden"/>
	<input type="text" name="op_time" id="op_time" value="${record.op_time}" class="hidden"/>
	<input type="text" name="f_task" id="f_task" value="${record.f_task}" class="hidden"/>
		
		<!-- 添加页面 -->
		<c:if test="${editMod=='add'}">
        <table class="dataListTable" style="width:100%" id="schedule">
		<img src="<%=request.getContextPath()%>/image/add.png" onclick="addSchedule()"/>
		<tr>
			<th style="width:20%">任务名:</th>
			<th style="width:20%">前置任务</th>
			<th style="width:20%">资源：</th>
			<th style="width:15%">开始时间:</th>
			<th style="width:15%">结束时间:</th>
			<th style="width:15%">操作:</th>
			<input type="hidden" id="count" name="count" value="1"/>
		</tr>
		<tr id="schedule_tr1">
			<td id="name_td1">
			<input type="text" name="task_name1" id="task_name1" style="width:95%" value="${record.task_name}" class="textBoxNoBorder" />
			</td>
			<td id="ftask_td1">
			<input type="text" name="ftask_name1" id="ftask_name1" readonly="readonly" value="无" class="hidden"/>
			</td>
			<td id="resource_td1">
			<input type="text" name="sc_res1" id="sc_res1" value="${record.sc_res}"  maxLength="500"  style="width:95%" class="textBoxNoBorder"/>
			</td>
            <td id="start_time_td1">
            <input type="text" name="start_time1" id="start_time1" value="${record.start_time}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/>
            </td>
            <td id="end_time_td1">
            <input type="text" name="end_time1" id="end_time1" value="${record.end_time}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/>
            </td>
            <td></td>
        </tr>
		</table>
		</c:if>
		
		<!-- 修改页面 -->
		<c:if test="${editMod=='edit'}">
		<table class="dataListTable" style="width:100%" id="schedule">
		<img src="<%=request.getContextPath()%>/image/add.png" onclick="addSchedule()"/>
			<tr>
			<th style="width:20%">任务名:</th>
			<th style="width:20%">前置任务</th>
			<th style="width:20%">资源：</th>
			<th style="width:15%">开始时间:</th>
			<th style="width:15%">结束时间:</th>
			<th style="width:15%">操作:</th>
			<input type="hidden" id="count" name="count" value="0"/>
			</tr>
			<c:forEach items="${record1}" var="result" varStatus="status">
			<tr>
			<td>
			<input type="text" name="task_name${result.id}" id="task_name" style="width:95%" value="${result.task_name}" class="textBoxNoBorder" />
			</td>
			<td>
			<input type="text" name="ftask_name${result.id}" id="ftask_name" value="${result.ftask_name}" 
			<c:if test="${result.ftask_name=='无'}">
			class="hidden"
			</c:if>
			<c:if test="${result.ftask_name!='无'}">
			class="textBoxNoBorder"
			</c:if>
			/>
			</td>
			<td>
			<input type="text" name="sc_res${result.id}" id="sc_res" value="${result.sc_res}"  maxLength="500"  style="width:95%" class="textBoxNoBorder"/>
			</td>
            <td>
            <input type="text" name="start_time${result.id}" id="start_time" value="${result.start_time}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/>
            </td>
            <td>
            <input type="text" name="end_time${result.id}" id="end_time" value="${result.end_time}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/>
            </td>
            <td>
            <a onclick="javascript:return scheduledel(${result.id});">
      		<img src='<%=request.getContextPath()%>/image/delete.png'/>
      		</a>
            </td>
	        </tr>
	        </c:forEach>
		</table>
		</c:if>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="sub()" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod){
	$("#operator").attr("value","${user.base_info_id}");
	$("#op_time").attr("value","${op_time}");
	$("#result_id").attr("value","${result_id}");
	$("#proj_id").attr("value","${proj_id}");
	$("#ds_type").attr("value","${ds_type}");
}
</script>	
</body>
</html>
