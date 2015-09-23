<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
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
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>

		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>

	</head>
	<script type="text/javascript">
function sub(id) {
	if (confirm("确定添加?")) {
		if (document.form1.name.value == '') {
			alert("请选择收货人");
			document.form1.name.focus();
			return false;
		}

		if (document.form1.recv_call.value == '') {
			alert("请填写收货人电话");
			document.form1.recv_call.focus();
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
		document.form1.spzt.value = id;
		document.form1.submit();
	}
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
	if (document.getElementById("check").checked) {
		selectAll();
	} else {
		unselectAll();
	}
}
</script>
	<script type="text/javascript">
${operationSign}</script>
	<body>
		<form
			action="<%=request.getContextPath()%>/materielapply/Gm_ration_apply!sub.do"
			method="post" name="form1" id="form1">

			<div align="center">
				<input type="button" name="btn" value="保存不提交" onclick="sub('0');" />
				<input type="button" name="btn" value="保存且提交" onclick="sub('1');" />
				<input type="hidden" name="spzt" />
			</div>


			<div class="divMod2">
				<div class="divMod1">
					配给申请信息
				</div>
				<table width="99%" class="search_border">
					<input type="hidden" name="prj_id" value="${info.prj_id}" />
					<input type="hidden" name="item_id" value="${info.plan_id}" />
					<input type="hidden" name="item_manage" value="${info.pr_manage}" />
					<tr>
						<th>
							收货人:
							<span style="color: Red;">*</span>
						</th>
						<td>

							<input type="text" name="name" id="name"
								value="${searchMap.name}" class="textBox"  readonly="readonly"/>
							<input type="text" name="receive_person" id="receive_person"
								value="${searchMap.receive_person}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','receive_person','name');"
								value="选择" />

						</td>
						<th>
							电话:
						</th>
						<td>

							<input type="text" name="recv_call" id="recv_call"
								value="${searchMap.recv_call}" class="textBox" />

						</td>
					</tr>

					<tr>
						

						<th align="center" style="width: 10%">
							到货日期:
							
						</th>
						<td colspan="3">
							<input type="text" name="order_date1" readonly="readonly"
								onFocus="WdatePicker()" />

						</td>
					</tr>



					<tr>
						<th align="center" style="width: 10%">
							备注:

						</th>
						<td colspan="3">
							<textarea rows="3" style="width: 98%" name="note"></textarea>

						</td>

					</tr>

				</table>
			</div>


			<div>


				<table class="dataListTable" width="100%">
					<tr>

						<th>
							<input type="checkbox" onclick="chec();" id="check">

						</th>
						<th>
							序号
						</th>
						<th>
							项目
						</th>
						<th>
							计划
						</th>
						<th>
							物资名称
						</th>
						<th>
							品牌
						</th>
						<th width="65">
							计划数量
						</th>

						<th width="80">
							已申请数量
						</th>

						<th width="80">
							申请量
						</th>





						<th>
							要求日期
						</th>


					</tr>
					<c:forEach items="${record}" var="result" varStatus="status">
						<tr>
							<td>
								<input type="checkbox" name="checkbox"
									value="${result.materiel_id}" />
								<input type="hidden" name="prmaplan${result.materiel_id}"
									value="${result.id}" />
							</td>
							<td>
								<c:out value="${status.index+1}" />
							</td>
							<td>
								<c:out value="${result.proname}" />
							</td>
							<td>
								<c:out value="${result.planname}" />
							</td>
							<td>
								<c:out value="${result.maname}" />
							</td>
							<td>
								<c:out value="${result.brname}" />
							</td>
							<td>
								<c:out value="${result.sqsl}" />
							</td>
							<td>
								<c:out value="${result.hasnum}" />
							</td>
							<td>
								<input type="text" name="plan${result.materiel_id}"
									value="${result.kcg}" style="width: 50%" />
							</td>





							<td>
								<input type="text" name="order_date${result.materiel_id}"
									readonly="readonly" style="width: 90%"
									value="${result.order_date}" onFocus="WdatePicker()" />
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>


		</form>

	</body>
</html>