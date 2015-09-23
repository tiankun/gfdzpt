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
function cardVal(cardType){
	if(cardType=='0'){
		$("#card_num").attr("dataType","IdCard");
	}
	else{
		$("#card_num").attr("dataType","");
	}
}

function cardVerify(cardNum){
	$.ajax({
		   type: "POST",
		   url: '<%=request.getContextPath()%>/hr/info/Hr_base_info!cardVerify.do',
		   data:"cardNum="+cardNum,
		   success: function(data){
			   if(data=="此证件号已存在!"){
				   alert(data);
				   $("#card_num").focus();
				   $("#cardEx").empty();
				   $("#cardEx").append("<img src='<%=request.getContextPath()%>/image/delete.png'/>");
			   }
			   else{
				   $("#cardEx").empty();
			   }
		   }
	});
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/hr/info/Hr_base_info!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" id="id" value="${record.id}"/>
	<input type="hidden" name="hr_type" id="hr_type" value="${record.hr_type}"/>
	<input type="hidden" name="lr_time" id="lr_time" value="${record.lr_time}"/>
	<input type="hidden" name="subflag" id="subflag" value="${record.subflag}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">姓名:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  dataType="Require"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">性别:</th>
			<td style="width:35%">
            <select name="sex"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${sex}" var="sex">
			<option value="${sex.key}" <c:if test="${record.sex==sex.key}">selected</c:if>>${sex.value}</option>
			</c:forEach>
			</select>
            </tr>
            <tr>
			<th align="center" style="width:10%">民族:</th>
			<td style="width:35%">
			<input type="text" name="nation" value="${record.nation}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">政治面貌:</th>
			<td style="width:35%">
			<select name="zheng_zhi"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${zheng_zhi}" var="zheng_zhi">
			<option value="${zheng_zhi.key}" <c:if test="${record.zheng_zhi==zheng_zhi.key}">selected</c:if>>${zheng_zhi.value}</option>
			</c:forEach>
			</select>
            </tr>
            <tr>
			<th align="center" style="width:10%">证件类型:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select name="card_type" id="card_type" onchange="cardVal(this.value)" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${card_type}" var="card_type">
			<option value="${card_type.key}" <c:if test="${record.card_type==card_type.key}">selected</c:if>>${card_type.value}</option>
			</c:forEach>
			</select>
			<th align="center" style="width:10%">证件号码:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="card_num" id="card_num" value="${record.card_num}" dataType="IdCard" maxLength="50" onchange="cardVerify(this.value);" style="width:70%" class="textBoxNoBorder"/>
			<span id="cardEx"></span>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">应聘岗位:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="position" value="${record.position}" dataType="Require" maxLength="50" style="width:95%" class="textBoxNoBorder"/></td>
			</td>
			<th align="center" style="width:10%">部门:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="dept_name" id="dept_name" value="${record.dept_name}" readonly="readonly" class="textBox"/>
			<input type="text" dataType="Require" name="dept_id" id="dept_id" value="${record.dept_id}" class="hidden"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dept_id','dept_name');" value="选择"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">出生日期:</th>
			<td style="width:35%">
			<input type="text" name="birthday" value="${record.birthday}"  dataType="Date" require="false" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">职级:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select name="duty_id" dataType="Require" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${duty_id}" var="duty_id">
			<option value="${duty_id.key}" <c:if test="${record.duty_id==duty_id.key}">selected</c:if>>${duty_id.value}</option>
			</c:forEach>
			</select>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">家庭电话:</th>
			<td style="width:35%">
			<input type="text" name="home_numb" value="${record.home_numb}" dataType="Phone" require="false" maxLength="15"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">移动电话:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="phone" value="${record.phone}" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="ime-mode:disabled" maxLength="11"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">婚姻状况:</th>
			<td style="width:35%">
			<select name="marriage"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${marriage}" var="marriage">
			<option value="${marriage.key}" <c:if test="${record.marriage==marriage.key}">selected</c:if>>${marriage.value}</option>
			</c:forEach>
			</select>
			<th align="center" style="width:10%">户口类型:</th>
			<td style="width:35%">
            <select name="hu_kou"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${hu_kou}" var="hu_kou">
			<option value="${hu_kou.key}" <c:if test="${record.hu_kou==hu_kou.key}">selected</c:if>>${hu_kou.value}</option>
			</c:forEach>
			</select>
            </tr>
            <tr>
			<th align="center" style="width:10%">户籍地:</th>
			<td style="width:35%">
			<input type="text" name="huji_name" id="huji_name" value="${record.huji_name}" readonly="readonly" class="textBox"/>
			<input type="text" name="hu_ji" id="hu_ji" value="${record.hu_ji}" class="hidden"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/Sys_area!searchList.do?pageType=select','hu_ji','huji_name');" value="选择"/></td>
            <th align="center" style="width:10%">住址:</th>
			<td style="width:35%">
			<input type="text" name="addr" value="${record.addr}"  maxLength="500"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">英语水平:</th>
			<td style="width:35%">
			<input type="text" name="english" value="${record.english}"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">邮箱:</th>
			<td style="width:35%"><input type="text" name="email" value="${record.email}" dataType="Email" require="false" maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">证件照:</th>
			<td style="width:35%">
			<c:if test="${editMod=='add'}">
			<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${pid}&folder=Hr_baseinfo&flag=staff_card_pic')">
			</c:if>
			<c:if test="${editMod=='edit'}">
			<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${record.id}&folder=Hr_baseinfo&flag=staff_card_pic')">
			</c:if>
			<img src="<%=request.getContextPath()%>/image/upload.png"/>
			</a>
			</td>
			<th align="center" style="width:10%">照片:</th>
			<td style="width:35%">
			<c:if test="${editMod=='add'}">
			<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${pid}&folder=Hr_baseinfo&flag=staff_pic')">
			</c:if>
			<c:if test="${editMod=='edit'}">
			<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${record.id}&folder=Hr_baseinfo&flag=staff_pic')">
			</c:if>
			<img src="<%=request.getContextPath()%>/image/upload.png"/>
			</a>
			</td>
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="保存" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置"  style="${btnDisplay}"/>
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='add'){
	$("#id").val("${pid}");
	$("#hr_type").attr("value","0");
	$("#lr_time").attr("value","${lr_time}");
}
</script>	
</body>
</html>
