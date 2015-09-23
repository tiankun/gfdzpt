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
function cnToSpell(cn){
	$.ajax({
		   type: "POST",
		   url: '<%=request.getContextPath()%>/sysAdmin/CnToSp!getFirst.do',
		   data:"cnString="+cn,
		   success: function(data){
			   $("#shortcode").val(data);
		   }
	});
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/materiel/Ma_brand!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  dataType="Require"  maxLength="45" onchange="cnToSpell(this.value)" style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">简码:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="shortcode" id="shortcode" value="${record.shortcode}"  dataType="Require"  maxLength="45"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">有效标志:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select name="yxbz" dataType="Require" style="width: 35%;">
			  <option value="">--</option>
			  <option value="1" <c:if test="${record.yxbz=='1'}">selected</c:if>>有效</option>
			  <option value="0" <c:if test="${record.yxbz=='0'}">selected</c:if>>无效</option>
			  
			</select>
			
			</td>
			<th align="center" style="width:10%">备注:</th>
			<td style="width:35%">
			<input type="text" name="note" value="${record.note}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
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
