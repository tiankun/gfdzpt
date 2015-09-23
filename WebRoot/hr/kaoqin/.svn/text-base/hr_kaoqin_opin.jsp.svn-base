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


	<form name="form1" action="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!saveaudit.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>		
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">考核时间:</th>
			<td style="width:35%">
			<input type="text" name="kqdate" value="${kqdate}"  dataType="Require"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" class="myInputTitle" style="width:10%"></th>
			<td style="width:35%">
			</td>
			</tr>
			<tr>
			 <th> 
			  考核意见:
			  
			 </th>
			 <td colspan="3">
			  <input type="radio" name="state" value="2" checked="checked">同意&nbsp;&nbsp;<input type="radio" name="state" value="3">打回
			 </td>
			</tr>
			
			<tr>
			 <th> 
			  考核意见:
			  
			 </th>
			 <td colspan="3">
			   <textarea rows="3" cols="70" name="opinion"></textarea>
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
