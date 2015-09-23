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
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/CalendarWebControl.js"></script>
</head>
<body>

<script type="text/javascript">
${operationSign}
</script>

	<form name="form1" action="<%=request.getContextPath()%>/sysAdmin/mrbranch!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>		
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">机构名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="branchname" value="${record.branchname}"  dataType="Require"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" class="myInputTitle" style="width:10%">机构缩写:</th>
			<td style="width:35%">
			<input type="text" name="simplecode" value="${record.simplecode}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">上级机构:</th>
			<td style="width:35%">
			<input type="text" id="parentbranchname" name="parentbranchname" value="${record.parentbranchname}" class="textBoxNoBorder"/>
			<input type="text" id="parentid" name="parentid" value="${record.parentid}"  dataType="Double" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','parentid','parentbranchname');" value="选择"/>
			</td>
			<th align="center" class="myInputTitle" style="width:10%">机构类型:</th>
			<td style="width:35%">
			<select name="branchtype" class="ddlNoBorder"><option value="">未选择</option>
			<c:forEach items="${branchtype}" var="branchtype">
			<option value="${branchtype.key}" <c:if test="${record.branchtype==branchtype.key}">selected</c:if>>${branchtype.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">删除标志:</th>
			<td style="width:35%">
			<select name="delflag" class="ddlNoBorder"><option value="">未选择</option>
			<c:forEach items="${delflag}" var="delflag">
			<option value="${delflag.key}" <c:if test="${record.delflag==delflag.key}">selected</c:if>>${delflag.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" class="myInputTitle" style="width:10%">地址:</th>
			<td style="width:35%">
			<input type="text" name="address" value="${record.address}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">所在省:</th>
			<td style="width:35%">
			<input type="text" name="dcode" value="${record.dcode}"  maxLength="18"  style="width:95%" class="textBoxNoBorder"/></td><td></td><td></td>
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
