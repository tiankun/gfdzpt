<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>在线编辑</title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/default.css" type="text/css"></link>
		<script language="javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>

<script
			src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"
			type="text/javascript">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>

		<script src="${pageContext.request.contextPath}/jscripts/jquery.js">
</script>

		<script language="javascript">
function sub1() {
	if (document.form1.carapply_state.value == '') {
		if (document.form1.carapply_state.value == '') {
			alert("请选择审核意见");
			document.form1.carapply_state.focus();
			return false;
		}
	}

	if (document.form1.carapply_state.value == '0') {
		if (document.form1.opinion.value == '') {
			alert("请输入不同意原因");
			document.form1.opinion.focus();
			return false;
		}
	}

	if (document.form1.carapply_state.value == '1') {
		if (document.form1.driver.value == '') {
			alert("请选择驾驶员！");
			document.form1.driver.focus();
			return false;
		}
		if (document.form1.car_num.value == '') {
			alert("请选择车辆！");
			document.form1.car_num.focus();
			return false;
		}
	}

	document.form1.submit();
}

function dis(id) {
	if (document.form1.carapply_state.value == '1') {
		var jsy = document.getElementById("jsy");
		jsy.style.display = '';
		var cph = document.getElementById("cph");
		cph.style.display = '';

	} else {
		var jsy = document.getElementById("jsy");
		jsy.style.display = 'none';
		var cph = document.getElementById("cph");
		cph.style.display = 'none';
	}

}
</script>
		<script type="text/javascript">
${operationSign}</script>
	</head>
	<body>
		<form
			action="${pageContext.request.contextPath}/gm/carapply/Gm_carapply_audit!mulsave.do"
			method="post" name="form1">
			<input name="idss" type="hidden" value="${idss}">

			<table class="table_border" style="width: 100%">
				<c:forEach var="record" items="${res}">
					<tr>
						<td colspan="4" align="center" height="30">
							<strong style="color: red;">${record.name}的用车申请</strong>
						</td>
					</tr>
					<tr>
						<th align="center" style="width: 10%">
							计划起始时间:
							<span style="color: Red;">*</span>
						</th>
						<td style="width: 35%">
							<input type="text" value="${record.plan_sdate}" name="plan_sdate"
								onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />


						</td>
						<th align="center" style="width: 10%">
							计划结束时间:
							<span style="color: Red;">*</span>
						</th>
						<td style="width: 35%">
							<input type="text" value="${record.plan_edate}" name="plan_edate"
								onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />

						</td>
					</tr>
					<tr>
						<th align="center" style="width: 10%">
							用车原因:
						</th>
						<td colspan="3">
							<textarea rows="4" cols="70" name="reason">${record.reason}</textarea>

						</td>
					</tr>

					<tr>

						<th align="center" style="width: 10%">
							行车路线:
						</th>

						<td colspan="3">
							<textarea rows="4" cols="70" name="road">${record.road}</textarea>

						</td>
					</tr>
				</c:forEach>


				<tr>
					<th align="center" style="width: 10%">
						部门经理审批意见
						</td>
						<td colspan="3">
							&nbsp;同意
						</td>
				</tr>
				<tr>
					<th align="center" style="width: 10%">
						综合办审批意见
					</th>
					<td colspan="3">
						<select name="carapply_state" style="width: 40%" onChange="dis();">
							<option>
								未选择
							</option>
							<option value="1">
								同意
							</option>
							<option value="0">
								不同意
							</option>
						</select>

					</td>
				</tr>
				<tr style="display: none;" id="jsy">
					<th align="center" style="width: 10%">
						驾驶员:
					</th>
					<td style="width: 35%">
						<select name="driver" style="width: 50%;">
							<option value="">
								未选择
							</option>
							<c:forEach var="res" items="${dlist}">
								<option value="${res.id}"
									<c:if test="${record.driver==res.id}">selected</c:if>>
									${res.name}
								</option>
							</c:forEach>

						</select>
					</td>
					<th align="center" style="width: 10%">

					</th>
					<td style="width: 35%">


					</td>
				</tr>
				<tr style="display: none;" id="cph">
					<th align="center" style="width: 10%">
						车牌号:
					</th>
					<td style="width: 35%">
						<select name="car_num" style="width: 50%;">
							<option value="">
								未选择
							</option>
							<c:forEach var="res" items="${clist}">
								<option value="${res.dmz}"
									<c:if test="${record.car_num==res.dmz}">selected</c:if>>
									${res.dmz}
								</option>
							</c:forEach>

						</select>

					</td>
					<th align="center" style="width: 10%">

					</th>
					<td style="width: 35%">


					</td>
				</tr>

				<tr>

					<th align="center" style="width: 10%">
						审批意见:
					</th>

					<td colspan="3">
						<textarea rows="4" cols="70" name="opinion">${record.opinion}</textarea>

					</td>
				</tr>






			</table>

			<div class="div-button">


				<input type="button" value="提交" onclick="sub1();"
					style="${btnDisplay}" />

				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>












		</form>
	</body>
</html>