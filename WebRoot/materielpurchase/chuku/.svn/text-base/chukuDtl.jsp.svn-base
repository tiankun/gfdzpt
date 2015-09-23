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
	var kcl = ${record.kck};
	var ckl = document.form1.kck.value;
	if($('#receive_person').val()==''){
		alert('请填写领用人');
		return false;
	}
	if(parseInt(kcl)<parseInt(ckl))
		{
		  alert("出库数量大于库存数量");
		  return false;
		}
	document.form1.submit();
}


</script>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!savechuku.do"
			method="post">
			<input type="hidden" name="id" value="${record.id}">
			<table class="table_border" style="width: 100%">

				<tr>
					<th align="center" style="width: 10%">
						项目:
					</th>
					<td style="width: 35%">
						${record.prname}
					</td>
					<th align="center" style="width: 10%">
						配给单：
					</th>
					<td style="width: 35%">
						${record.ration_apply_id}
					</td>
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						材料:
					</th>
					<td style="width: 35%">
						${record.maname}
					</td>
					<th align="center" style="width: 10%">
						品牌:
					</th>
					<td style="width: 35%">
						${record.brname}
					</td>
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						供应商:
					</th>
					<td style="width: 35%">
						${record.gyname}
					</td>
					<th align="center" style="width: 10%">
						库存量:
					</th>
					<td style="width: 35%">
						${record.kck}
					</td>

				</tr>

				<tr>
					<th align="center" style="width: 10%">
						出库量:
					</th>
					<td style="width: 35%">
						<input type="text" name="kck" value="${record.kck}"
							style="width: 80%;" />
					</td>
					<th align="center" style="width: 10%">
						领用人：
					</th>
					<td style="width: 35%">
						<input type="text" name="recename" id="recename" readonly
							value="" class="textBox" />
						<input type="text" name="receive_person" id="receive_person" dataType="Require"
							value="" class="hidden" />
						<input type="button"
							onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','receive_person','recename');"
							value="选择" />
					</td>

				</tr>

				<tr>
					<th align="center" style="width: 10%">
						备注:
					</th>
					<td colspan="3">
						<textarea rows="3" cols="70" name="note"></textarea>
					</td>


				</tr>

			</table>


			<div class="div-button">
				<input type="button" value="出库" onclick="sub();" />
				<input type="reset" value="重置" />
				<input type="button" value="关闭" onClick="javascript:closeDG();" />
			</div>
		</form>
	</body>
</html>
