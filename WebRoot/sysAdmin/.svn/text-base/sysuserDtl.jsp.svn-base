<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Insert title here</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/Validator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<script type="text/javascript">
${operationSign}
</script>

	<form name="form1" action="<%=request.getContextPath()%>/sysAdmin/sysuser!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>	
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">登录名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="log_name" value="${record.log_name}"  dataType="Require"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">真实名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="user_name" name="user_name" value="${record.user_name}" dataType="Require"  maxLength="50" class="textBoxNoBorder"/>
			<input type="text" id="base_info_id" name="base_info_id" value="${record.base_info_id}" class="hidden"/>
			<input type="button" onclick="openSelect('hrbaseinfo_select','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','base_info_id','user_name');" value="选择"/>
			</td>
            </tr>
            <tr>
            <th align="center" class="myInputTitle" style="width:10%">登录密码:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="password" name="log_pass" value="${record.log_pass}"  dataType="Require"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">创建日期:</th>
			<td style="width:35%">
			<input type="text" name="cdate" value="${record.cdate}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">有效期:</th>
			<td style="width:35%">
			<input type="text" name="exp_date" value="${record.exp_date}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">所属机构:</th>
			<td style="width:35%">
			<input type="text" id="branchname" name="branchname" value="${record.branchname}" class="textBoxNoBorder"/>
			<input type="text" id="branchid" name="branchid" value="${record.branchid}" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','branchid','branchname');" value="选择"/>
			</td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">用户类别:</th>
			<td style="width:35%">
			<select name="usertype" class="ddlNoBorder"><option value=""></option>
			<c:forEach items="${usertype}" var="usertype">
			<option value="${usertype.key}" <c:if test="${record.usertype==usertype.key}">selected</c:if>>${usertype.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">是否需要用锁登录:</th>
			<td style="width:35%">
			<select name="flag" class="ddlNoBorder"><option value=""></option>
			<c:forEach items="${flag}" var="flag">
			<option value="${flag.key}" <c:if test="${record.flag==flag.key}">selected</c:if>>${flag.value}</option>
			</c:forEach>
			</select></td>
			</tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">删除标志:</th>
			<td style="width:35%">
			<select name="delflag" class="ddlNoBorder"><option value=""></option>
			<c:forEach items="${delflag}" var="delflag">
			<option value="${delflag.key}" <c:if test="${record.delflag==delflag.key}">selected</c:if>>${delflag.value}</option>
			</c:forEach>
			</select></td>
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
</body>
</html>
