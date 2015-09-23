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

//计算成绩得分
function performanceCal(){
	p_quality=$("#p_quality").attr("value");
	p_quantity=$("#p_quantity").attr("value");
	p_df=parseInt(p_quality)+parseInt(p_quantity);
	
	if(p_quality&&p_quantity){
		$("#p_performance").attr("value",p_df/2);
		$("#r_performance").attr("value","30%");
		$("#s_performance").attr("value",(p_df/2*0.3).toFixed(2));
	}
	totalCal();
}

//计算工作态度得分
function attitudeCal(){
	p_discipline=$("#p_discipline").attr("value");
	p_collective=$("#p_collective").attr("value");
	p_positivity=$("#p_positivity").attr("value");
	p_responsibility=$("#p_responsibility").attr("value");
	a_df=parseInt(p_discipline)+parseInt(p_collective)+parseInt(p_positivity)+parseInt(p_responsibility);
	
	if(p_discipline&&p_collective&&p_positivity&&p_responsibility){
		$("#p_attitude").attr("value",a_df/4);
		$("#r_attitude").attr("value","30%");
		$("#s_attitude").attr("value",(a_df/4*0.3).toFixed(2));
	}
	totalCal();
}

//计算能力得分
function capabilityCal(){
	p_knowledge=$("#p_knowledge").attr("value");
	p_judge=$("#p_judge").attr("value");
	p_plan=$("#p_plan").attr("value");
	p_relationships=$("#p_relationships").attr("value");
	c_df=parseInt(p_knowledge)+parseInt(p_judge)+parseInt(p_plan)+parseInt(p_relationships);
	
	if(p_knowledge&&p_judge&&p_plan&&p_relationships){
		$("#p_capability").attr("value",c_df/4);
		$("#r_capability").attr("value","40%");
		$("#s_capability").attr("value",(c_df/4*0.4).toFixed(2));
	}
	totalCal();
}

//计算总分
function totalCal(){
	if(p_df&&a_df&&c_df){
		$("#s_personal").attr("value",(p_df+a_df+c_df)/10);
		$("#s_total").attr("value",(p_df/2*0.3+a_df*0.3/4+c_df/4*0.4).toFixed(2));
	}
}

//输入验证
function lessThanEqualsToTen(id,value){
	if(value>10){
		alert("请输入小于10的数字！");
		$("#"+id+"").val("");
	}
}

function sub(){
	performanceCal();
	attitudeCal();
	capabilityCal();
	totalCal();
	if(Validator.Validate(document.form1,3)){document.form1.submit();}
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/hr/process/Hr_evaluation!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<div class="divMod1" align="center">员工转正考评表</div>
		<table width="100%" border="1" class="table_border">
		<input type="hidden" name="p_id" value="${record.p_id}"/>
		<input type="hidden" name="ep_id" value="${record.ep_id}"/>
		<input type="hidden" name="dept_id" value="${record.dept_id}"/>
		  <tr height="40px">
		    <th align="center">被考评人姓名：</th>
		    <td>
			<input type="text" name="evd_name" value="${record.evd_named}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>	
			</td>
		    <th align="center">岗位：</th>
		    <td>
				<input type="text" name="position" value="${record.positiond}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/>
			</td>
		    <th align="center">考评时间：</th>
		    <td colspan="2">
				<input type="text" name="ev_time" id="ev_time" value="${record.ev_time}" readonly="readonly" dataType="Date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/>
			</td>
		  </tr>
		  <tr height="40px">
		    <th align="center">类别：</th>
		    <th align="center">评价因素：</th>
		    <th align="center">单项打分：</th>
		   	<th align="center">打分依据及说明：</th>
		    <th align="center">大项得分：</th>
		    <th align="center">因素比例：</th>
		    <th align="center">大项评分：</th>
		  </tr>
		  <tr height="40px">
		    <th align="center" rowspan="2">成绩评价：</th>
		    <th align="center">工作质量：</th>
		    <td><input type="text" name="p_quality" id="p_quality" value="${record.p_quality}" onchange="lessThanEqualsToTen(this.id,this.value);performanceCal()" maxLength="2" style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_quality" value="${record.e_quality}"  maxLength="200" dataType="Require" style="width:95%" class="textBoxNoBorder"/></td>
		    <td rowspan="2"><input type="text" name="p_performance" readonly="readonly" id="p_performance" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_performance}" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td rowspan="2"><input type="text" name="r_performance" readonly="readonly" id="r_performance" value="${record.r_performance}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td rowspan="2"><input type="text" name="s_performance" readonly="readonly" id="s_performance" value="${record.s_performance}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center">工作数量：</th>
		    <td><input type="text" name="p_quantity" id="p_quantity" onchange="lessThanEqualsToTen(this.id,this.value);performanceCal()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_quantity}" maxLength="2" style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_quantity" value="${record.e_quantity}"  maxLength="200" dataType="Require" style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center" rowspan="4">工作态度评价：</th>
		    <th align="center">纪律性：</th>
		    <td><input type="text" name="p_discipline" id="p_discipline" onchange="lessThanEqualsToTen(this.id,this.value);attitudeCal()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_discipline}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_discipline" value="${record.e_discipline}"  maxLength="200" dataType="Require" style="width:95%" class="textBoxNoBorder"/></td>
		    <td rowspan="4"><input type="text" name="p_attitude" readonly="readonly" id="p_attitude" value="${record.p_attitude}" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td rowspan="4"><input type="text" name="r_attitude" readonly="readonly" id="r_attitude" value="${record.r_attitude}" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td rowspan="4"><input type="text" name="s_attitude" readonly="readonly" id="s_attitude" value="${record.s_attitude}" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center">集体观念：</th>
		    <td><input type="text" name="p_collective" id="p_collective" onchange="lessThanEqualsToTen(this.id,this.value);attitudeCal()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_collective}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_collective" value="${record.e_collective}" dataType="Require" maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center">积极性：</th>
		    <td><input type="text" name="p_positivity" id="p_positivity" onchange="lessThanEqualsToTen(this.id,this.value);attitudeCal()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_positivity}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_positivity" value="${record.e_positivity}" dataType="Require" maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center">责任感：</th>
		    <td><input type="text" name="p_responsibility" id="p_responsibility" onchange="lessThanEqualsToTen(this.id,this.value);attitudeCal()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_responsibility}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_responsibility" value="${record.e_responsibility}" dataType="Require" maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center" rowspan="4">能力评价：</th>
		    <th align="center">知识技能学习能力：</th>
		    <td><input type="text" name="p_knowledge" id="p_knowledge" onchange="lessThanEqualsToTen(this.id,this.value);capabilityCal()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_knowledge}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_knowledge" value="${record.e_knowledge}" dataType="Require" maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td rowspan="4"><input type="text" name="p_capability" readonly="readonly" id="p_capability" value="${record.p_capability}" onchange="totalCal()" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td rowspan="4"><input type="text" name="r_capability" readonly="readonly" id="r_capability" value="${record.r_capability}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td rowspan="4"><input type="text" name="s_capability" readonly="readonly" id="s_capability" value="${record.s_capability}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center">理解判断决断能力：</th>
		    <td><input type="text" name="p_judge" id="p_judge" onchange="lessThanEqualsToTen(this.id,this.value);capabilityCal()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_judge}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_judge" value="${record.e_judge}" dataType="Require" maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center">计划开发能力：</th>
		    <td><input type="text" name="p_plan" id="p_plan" onchange="lessThanEqualsToTen(this.id,this.value);capabilityCal()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_plan}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_plan" value="${record.e_plan}" dataType="Require" maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center">人际关系协调沟通：</th>
		    <td><input type="text" name="p_relationships" id="p_relationships" onchange="lessThanEqualsToTen(this.id,this.value);capabilityCal()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.p_relationships}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <td><input type="text" name="e_relationships" value="${record.e_relationships}" dataType="Require" maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <th align="center">个人评分：</th>
		    <td colspan="2"><input type="text" name="s_personal" readonly="readonly" id="s_personal" value="${record.s_personal}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		    <th align="center">综合评分</th>
		    <td colspan="3"><input type="text" name="s_total" readonly="readonly" id="s_total" value="${record.s_total}"  maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		  <tr height="40px">
		    <td colspan="7">注：每项为10分满分；打分应有相应说明。其中成绩评价30%、工作态度评价30%、能力评价40%；</td>
		  </tr>
		  <tr height="40px">
		    <td colspan="5">&nbsp;</td>
		    <th align="center">考评员签名：</th>
		    <td><input type="text" name="ev_name" value="${record.ev_named}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
		  </tr>
		</table>
		<br/>
		<div class="div-button">
			<input type="button" value="提交" onclick="sub()" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='edit'){
	$("#ev_time").attr("value","${ev_time}");
}
</script>
</body>
</html>
