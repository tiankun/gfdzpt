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

	<form name="form1" action="<%=request.getContextPath()%>/customer/Customer!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">客户名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  dataType="Require"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">地址:</th>
			<td style="width:35%">
			<input type="text" name="address" value="${record.address}"  maxLength="400"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">联系人:</th>
			<td style="width:35%">
			<input type="text" name="lxr" value="${record.lxr}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">联系电话:</th>
			<td style="width:35%">
			<input type="text" name="lxdh" value="${record.lxdh}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">客户等级:</th>
			<td style="width:35%">
			<select name="khdj" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${khdj}" var="khdj">
									<option value="${khdj.key}" <c:if test="${record.khdj==khdj.key}">selected</c:if>  >
										${khdj.value}
									</option>
								</c:forEach>
			</select>
			</td>
			<th align="center" style="width:10%">纳税人识别号:</th>
			<td style="width:35%">
			<input type="text" name="nsrsbh" value="${record.nsrsbh}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">开户行:</th>
			<td style="width:35%">
			<input type="text" name="khh" value="${record.khh}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">账号:</th>
			<td style="width:35%">
			<input type="text" name="zh" value="${record.zh}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">备注:</th>
			<td colspan="3">
			<textarea rows="3" cols="70" name="note">${record.note}</textarea>
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
</body>
</html>
