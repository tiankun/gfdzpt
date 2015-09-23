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
	<script type="text/javascript">
function sub(id) {
	if (!Validator.Validate(document.form1, 3)) {
		alert("带红色星号的选项必须填写！");
		return false;
	} else {
		document.form1.state.value = id;
		document.form1.submit();
	}

}

function sub1(id) {
	if (id == '0' && document.form1.opinion.value == '') {

		alert("请填写不同意原因！");
		return false;
	} else {
		document.form1.opinionid.value = id;
		document.form1.state.value = id;
		document.form1.submit();
	}

}
</script>
	<body>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/hr/absence/Hr_absence!${editMod}.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="type" value="${type}" />
			<input type="hidden" name="state" />
			<input type="hidden" name="opinionid" />

			<table class="table_border" style="width: 100%">


				<tr>
					<th align="center" style="width: 15%">
						请假类型:
						<span style="color: Red;">*</span>
					</th>
					<td style="width: 35%">
						<input type="text" id="absence_type" dataType="Require"
							name="absence_type" value="${record.absence_type}"
							readonly="readonly" class="textBoxNoBorder" />
						<input type="text" id="iid" name="iid"
							value="${record.absence_type}" class="hidden" />
						<input type="button"
							onclick="openSelect('branch_select','<%=request.getContextPath()%>/hr/absence/Hr_absence_standard!searchList.do?pageType=select','iid','absence_type');"
							value="选择" />
					</td>
					<th align="center" style="width: 15%">
						<c:if test="${record.days!=''&&record.days!=null}">请假天数</c:if>
					</th>
					<td style="width: 35%">
						${record.days}
					</td>
				</tr>
				<tr>
					<th align="center" style="width: 15%">
						开始时间:
						<span style="color: Red;">*</span>
					</th>
					<td style="width: 35%">
						<input type="text" name="begin_date" dataType="Require" id="d4311"
							value="${record.begin_date}" dataType="Date" require="false"
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')}'})"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 15%">
						区间:
					</th>
					<td style="width: 35%">
						<input type="radio" name="sqj" value="1" maxLength="2"
							<c:if test="${record.bam=='1'}">checked</c:if> />
						上午&nbsp;&nbsp;
						<input type="radio" name="sqj" value="0" maxLength="2"
							<c:if test="${record.bpm=='1'}">checked</c:if> />
						下午
					</td>
				</tr>
				<tr>
					<th align="center" style="width: 15%">
						结束时间:
						<span style="color: Red;">*</span>
					</th>
					<td style="width: 35%">
						<input type="text" name="end_date" dataType="Require" id="d4312"
							value="${record.end_date}" dataType="Date" require="false"
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}'})"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 15%">
						区间:
					</th>
					<td style="width: 35%">
						<input type="radio" name="eqj" value="1" maxLength="2"
							<c:if test="${record.eam=='1'}">checked</c:if> />
						上午&nbsp;&nbsp;
						<input type="radio" name="eqj" value="0" maxLength="2"
							<c:if test="${record.epm=='1'}">checked</c:if> />
						下午
					</td>
				</tr>
				<tr>
					<th align="center" style="width: 15%">
						请假原因:
						<span style="color: Red;">*</span>
					</th>
					<td colspan="3" height="50">
						<textarea rows="4" cols="70" dataType="Require" name="reason"
							style="border-width: 0px;">${record.reason}</textarea>
					</td>
				</tr>
				<c:if test="${type=='audit'}">
					<tr>
						<th align="center" style="width: 15%">
							审批意见:

						</th>
						<td colspan="3" height="50">
							<textarea rows="4" cols="70" name="opinion"
								style="border-width: 0px;"></textarea>
						</td>
					</tr>
				</c:if>


			</table>
			<br />
			<div class="div-button">
				<c:if
					test="${record.absence_state==''||record.absence_state==null||record.absence_state=='0'}">
					<input type="button" value="保存不提交" onclick="sub('0');"
						style="${btnDisplay}" />

					<input type="button" value="保存且提交" onclick="sub('1');"
						style="${btnDisplay}" />

				</c:if>

				<c:if test="${record.absence_state=='1'||record.absence_state=='2'}">
					<input type="button" value="同意" onclick="sub1('1');"
						style="${btnDisplay}" />
					<input type="button" value="不同意 " onclick="sub1('0');"
						style="${btnDisplay}" />
				</c:if>


				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>

			<br />
			<div class="divMod2">
				<div class="divMod1">
					审批意见历史
				</div>
				<table class="dataListTable" width="100%">
					<tr>


						<th>
							序号
						</th>

						<th>
							审批人
						</th>
						<th>
							审批结果
						</th>


						<th>
							审批时间
						</th>

						<th>
							审批意见
						</th>



					</tr>
					<c:forEach items="${splist}" var="result" varStatus="status">
						<tr>

							<td>
								<c:out value="${status.index+1}" />

							</td>

							<td>
								<c:out value="${result.person}" />
							</td>
							<td>
								<c:out value="${absence_state[result.state]}" />
							</td>



							<td>
								<c:out value="${result.audit_date}" />
							</td>
							<td>
								<c:out value="${result.opinion}" />
							</td>




						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</body>
</html>
