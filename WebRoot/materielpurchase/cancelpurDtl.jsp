<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title></title>

		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css"
			type="text/css"></link>
		<script src="<%=request.getContextPath()%>/jscripts/jquery.js"
			type="text/javascript">
</script>
		<script
			src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"
			type="text/javascript">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>
	</head>
	<body>
		<script type="text/javascript">
function sub() {
	if(document.form1.reason.value=='')
		{
	    alert("请输入取消采购原因");
	    document.form1.reason.focus();
		}
	document.form1.submit();
}


</script>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!savecancel.do"
			method="post">
			<input type="hidden" name="id" value="${record.id}">
			<table class="table_border" style="width: 100%">

				

				<tr>
					<th align="center" style="width: 10%">
						取消原因:
					</th>
					<td colspan="3">
						<textarea rows="3" cols="70" name="reason"></textarea>
					</td>


				</tr>

			</table>


			<div class="div-button">
				<input type="button" value="提交" onclick="sub();" />
				<input type="reset" value="重置" />
				<input type="button" value="关闭" onClick="javascript:closeDG();" />
			</div>
		</form>
	</body>
</html>
