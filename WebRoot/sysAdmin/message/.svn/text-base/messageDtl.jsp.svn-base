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

	<form name="form1"  action="<%=request.getContextPath()%>/sysAdmin/Message!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">姓名:</th>
			<td style="width:35%">
			<input type="text" name="name" id="name"
								value="${record.name}"  class="textBox" />
							<input type="text" name="pid" id="pid"
								value="" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','pid','name');"
								value="选择" />
			</td>
            <th align="center" style="width:10%">角色:</th>
			<td style="width:35%">
			<select name="rolename" style="width: 70%">
			    <option value="">未选择
			    </option>
			    <c:forEach var="res" items="${messagerole}">
			      <option value="${res.key}" <c:if test="${record.rolename==res.key}">selected</c:if>>
										${res.value}
									</option>
			    </c:forEach>
			  </select>
			</td>
			
            </tr>
            <tr>
			<th align="center" style="width:10%">部门:</th>
			<td style="width:35%">
			<select name="dept" style="width: 70%">
			    <option value="">未选择
			    </option>
			    <c:forEach var="res" items="${dept}">
			      <option value="${res.id}"  <c:if test="${res.id==record.dept}">selected</c:if>>${res.branchname}</option>
			    </c:forEach>
			  </select>
			</td>
			<th align="center" style="width:10%">手机号码:</th>
			<td style="width:35%">
			<input type="text" name="num" value="${record.num}"  maxLength="15"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">备注:</th>
			<td style="width:35%">
			<input type="text" name="note" value="${record.note}"  maxLength="500"  style="width:95%" class="textBoxNoBorder"/></td><th></th><td></td>
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
