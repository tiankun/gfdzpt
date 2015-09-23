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

	<form name="form1" action="<%=request.getContextPath()%>/sysAdmin/Sys_message!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
		    <tr>
			<th align="center" style="width:10%">代码类别 (注：同一功能的不同步骤代码类别应相同):<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="code" value="${record.code}"  dataType="Require"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%">
			</td>
            </tr>
		    
            <tr>
			<th align="center" style="width:10%">功能名:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="function_name" value="${record.function_name}"  dataType="Require"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">步骤:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="step" value="${record.step}"  dataType="Require"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">部门:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="branchname" id="branchname" dataType="Require"
								value="${record.branchname}" class="textBoxNoBorder" />
							<input type="text" name="dept_id" id="dept_id"
								value="${record.dept_id}" class="hidden" />
							<input type="button" 
								onclick="openSelect('选择组织机构','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dept_id','branchname');"
								value="选择" /></td>
			<th align="center" style="width:10%">步骤描述:</th>
			<td style="width:35%">
			<input type="text" name="step_discribe" value="${record.step_discribe}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">手机号码(注：多个号码可用<font color="red">半角逗号</font>隔开):<span style="color:Red;">*</span></th>
			<td colspan="3">
			<input type="text" name="mobile_num" value="${record.mobile_num}"  dataType="Require"  maxLength="30"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			 <th align="center" style="width:10%">短信内容:</th>
			<td colspan="3">
			<textarea rows="3" cols="70" name="message"  >${record.message}</textarea>
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
