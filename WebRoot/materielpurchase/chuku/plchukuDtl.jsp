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
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
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

	if(document.form1.recename.value=='')
		{
		 alert("请选择材料领用人");
		 return false;
		}
	
	var ids = "";
	var flag = true;

	var obj = document.form1.elements;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			if (obj[i].checked == true) {
				flag = false;
				ids = ids + ";" + obj[i].value;
			}

		}
	}

	if (flag) {
		alert("请至少选择一条记录！");
		return false;
	}
	
	
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			if (obj[i].checked == true) {
				var id = obj[i].id;
				var str1 = "kck"+id;
				var str2 = "ckl"+id;
				
				if(document.getElementById(str1).value<document.getElementById(str2).value)
					{
					   alert("出库量大于入库量");
					   document.getElementById(str2).focus();
					   return false;
					}
				
			}

		}
	}

	
	document.form1.submit();
	
	
}

function selectAll() {
	var obj = document.form1.elements;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			obj[i].checked = true;
		}
	}
}

function unselectAll() {
	var obj = document.form1.elements;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			if (obj[i].checked == true)
				obj[i].checked = false;
			else
				obj[i].checked = true;
		}
	}
}

function chec() {
	if (document.getElementById("check1").checked) {
		selectAll();
	} else {
		unselectAll();
	}
}
</script>

		<script type="text/javascript">
${operationSign}
</script>

		<form name="form1" id="form1"
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!savepilchuku.do"
			method="post">
			<input type="hidden" name="id" value="${record.id}">
			<table class="table_border" style="width: 100%">
				<tr>
					<th align="center" style="width: 10%">
						领用人：
					</th>
					<td style="width: 35%">
						<input type="text" name="recename" id="recename" value=""
							class="textBox" />
						<input type="text" name="receive_person" id="receive_person"
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

			<br />
			<div class="divMod2">
				<div class="divMod1">
					出库材料
				</div>
				<table class="dataListTable" width="100%">
					<tr>
						<th>
							<input type="checkbox" onclick="chec();" id="check1">
						</th>
						<th>
							序号
						</th>

						<th>
							项目
						</th>
						<th>
							配给单
						</th>

						<th>
							材料
						</th>

						<th>
							品牌
						</th>

						<th>
							库存量
						</th>


						<th>
							出库量
						</th>


					</tr>
					<c:forEach items="${reslist}" var="result" varStatus="status">
						<tr>
							<td>
								<input type="checkbox" name="checkbox" value="${result.id}" id="${status.index+1}" checked="checked"/>
							</td>
							<td>
								<c:out value="${status.index+1}" />
							</td>

							<td>
								<c:out value="${result.prname}" />
							</td>
							<td>
								<c:out value="${result.ration_apply_id}" />
							</td>

							<td>
								<c:out value="${result.maname}" />
							</td>

							<td>
								<c:out value="${result.brname}" />
							</td>

							<td>
								<c:out value="${result.kck}" />
							</td>


							<td>
								<input type="text" name="ckl${result.id}" value="${result.kck}" id="ckl${status.index+1}"
									style="width: 60%" />
								<input type="hidden" name="kck${status.index+1}" value="${result.kck}"/> 
							</td>



						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</body>
</html>
