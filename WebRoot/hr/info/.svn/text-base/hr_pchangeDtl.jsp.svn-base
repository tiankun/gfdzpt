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
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/hr/info/Hr_pchange!${editMod}.do" method="post">
	<input type="hidden" id="editMod" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="p_id" id="p_id" value="${record.p_id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">原单位:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="unit_o" id="unit_o" value="${record.unit_o}"  dataType="Require"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">现单位:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="unit_n" value="${record.unit_n}"  dataType="Require"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">原部门:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="deptname_o" value="${record.deptname_o}" maxLength="30" style="width:70%" class="textBoxNoBorder"/>
			<input type="hidden" id="dept_o" name="dept_o" dataType="Require" value="${record.dept_o}" maxLength="2"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dept_o','deptname_o');" value="选择"/></td>
			<th align="center" style="width:10%">现部门:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="deptname_n" value="${record.deptname_n}" maxLength="30" style="width:70%" class="textBoxNoBorder"/>
			<input type="hidden" id="dept_n" name="dept_n" value="${record.dept_n}" dataType="Require"  maxLength="2"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dept_n','deptname_n');" value="选择"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">原岗位:</th>
			<td style="width:35%">
			<input type="text" name="position_o" id="position_o" value="${record.position_o}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">现岗位:</th>
			<td style="width:35%">
			<input type="text" name="position_n" value="${record.position_n}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">原行政级别:</th>
			<td style="width:35%">
			<select name="dept_id_o" id="dept_id_o" dataType="Require" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${dept_id_o}" var="dept_id_o">
			<option value="${dept_id_o.key}" <c:if test="${record.dept_id_o==dept_id_o.key}">selected</c:if>>${dept_id_o.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">现行政级别:</th>
			<td style="width:35%">
			<select name="dept_id_n" dataType="Require"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${dept_id_n}" var="dept_id_n">
			<option value="${dept_id_n.key}" <c:if test="${record.dept_id_n==dept_id_n.key}">selected</c:if>>${dept_id_n.value}</option>
			</c:forEach>
			</select></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">原状态:</th>
			<td style="width:35%">
			<select name="hr_type_o" id="hr_type_o" dataType="Require" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${hr_type_o}" var="hr_type_o">
			<option value="${hr_type_o.key}" <c:if test="${record.hr_type_o==hr_type_o.key}">selected</c:if>>${hr_type_o.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">现状态:</th>
			<td style="width:35%">
			<select name="hr_type_n" dataType="Require"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${hr_type_n}" var="hr_type_n">
			<option value="${hr_type_n.key}" <c:if test="${record.hr_type_n==hr_type_n.key}">selected</c:if>>${hr_type_n.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">变动类型:</th>
			<td style="width:35%">
			<input type="text" name="pchag_type" value="${record.pchag_type}"  maxLength="30"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">变动时间:</th>
			<td style="width:35%">
			<input type="text" name="pchag_time" value="${record.pchag_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
            <tr>
			<th align="center" style="width:10%">扫描件:</th>
			<td style="width:90%" colspan="3">
				<c:if test="${editMod!='show'}">
				<c:if test="${editMod=='add'}">
					<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${pid}&folder=Hr_pchange&flag=pchange_pic')">
				</c:if>
				<c:if test="${editMod=='edit'}">
					<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${record.p_id}&folder=Hr_pchange&flag=pchange_pic')">
				</c:if>	
					<img src="<%=request.getContextPath()%>/image/upload.png"/>
					</a>
				</c:if>
				<c:if test="${editMod=='show'}">
					<a href="#" onclick="openDGMax('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.p_id}&folder=Hr_pchange&flag=pchange_pic&mark=show')">
					<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
					</a>
				</c:if>
            </td>
            </tr>
            <tr>
			<th align="center" style="width:10%">说明(管理员添加/非纳入人员变更):</th>
			<td style="width:35%" colspan="3">
			<textarea name="mark" value="${record.mark}" rows="5" maxLength="50"  style="width:99%;border: 0px"/>${record.mark}</textarea>
            </td>
            </tr>
            <tr>
            <th align="center" style="width:10%">具体内容说明:</th>
			<td style="width:35%" colspan="3">
			<textarea name="remark" value="${record.remark}" rows="5" maxLength="1000"  style="width:99%;border: 0px"/>${record.remark}</textarea>
            </td>
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='add'){
	$("#deptname_o").val("${baseinfo.branchname}");
	$("#p_id").val("${pid}");
	$("#dept_o").val("${baseinfo.dept_id}");
	$("#position_o").val("${baseinfo.position}");
	$("#dept_id_o").val("${baseinfo.duty_id}");
	$("#hr_type_o").val("${baseinfo.hr_type}");
	
}
</script>	
</body>
</html>
