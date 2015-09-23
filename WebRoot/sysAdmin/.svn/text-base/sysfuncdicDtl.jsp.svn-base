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

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/sysAdmin/sysfuncdic!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>		
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">功能名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="funcname" value="${record.funcname}"  dataType="Require"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">功能描述:</th>
			<td style="width:35%">
			<input type="text" name="description" value="${record.description}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">父功能id:</th>
			<td style="width:35%">
			<input type="text" name="parentid" value="${record.parentid}"  dataType="Require" dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">路径:</th>
			<td style="width:35%">
			<input type="text" name="url" value="${record.url}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">删除标志:</th>
			<td style="width:35%">
			<select name="delflag" class="ddlNoBorder"  dataType="Require"><option value="">未选择</option>
			<c:forEach items="${delflag}" var="delflag">
			<option value="${delflag.key}" <c:if test="${record.delflag==delflag.key}">selected</c:if>>${delflag.value}</option>
			</c:forEach>
			</select></td>
			</tr>
            
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">排序号:</th>
			<td style="width:35%">
			<input type="text" name="indexid" value="${record.indexid}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
            
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">是否公开:</th>
			<td style="width:35%">
			<select name="publics" class="ddlNoBorder" dataType="Require"><option value="">未选择</option>
			<c:forEach items="${publics}" var="publics">
			<option value="${publics.key}" <c:if test="${record.publics==publics.key}">selected</c:if>>${publics.value}</option>
			</c:forEach>
			</select></td>
			</tr>
			<tr>
			<th align="center" class="myInputTitle" style="width:10%">功能节点类型:</th>
			<td style="width:35%">
			<select name="treenodetype" class="ddlNoBorder" dataType="Require"><option value="">未选择</option>
			<c:forEach items="${treenodetype}" var="treenodetype">
			<option value="${treenodetype.key}" <c:if test="${record.treenodetype==treenodetype.key}">selected</c:if>>${treenodetype.value}</option>
			</c:forEach>
			</select></td>
			</tr>
			
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">目标:</th>
			<td style="width:35%">
			<input type="text" name="target" value="${record.target}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">图片路径:</th>
			<td style="width:35%">
			<input type="text" name="img_path" value="${record.img_path}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">是否启用流程:</th>
			<td style="width:35%">
			<select name="qylc" class="ddlNoBorder"><option value="">未选择</option>
			<c:forEach items="${qylc}" var="qylc">
			<option value="${qylc.key}" <c:if test="${record.qylc==qylc.key}">selected</c:if>>${qylc.value}</option>
			</c:forEach>
			</select></td>
			</tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">是否需要授权:</th>
			<td style="width:35%">
			<select name="isauthorize" class="ddlNoBorder"><option value="">未选择</option>
			<c:forEach items="${isauthorize}" var="isauthorize">
			<option value="${isauthorize.key}" <c:if test="${record.isauthorize==isauthorize.key}">selected</c:if>>${isauthorize.value}</option>
			</c:forEach>
			</select></td>
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:api.close();"/>
		</div>
	</form>
</body>
</html>
