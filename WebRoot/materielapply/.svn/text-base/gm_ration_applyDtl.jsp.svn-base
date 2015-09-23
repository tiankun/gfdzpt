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
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>
	</head>
	<body>
		<script type="text/javascript">
function sub(id) {
	if (document.form1.opinion.value == '') {
		alert("请输入审批意见");
		document.form1.opinion.focus();
		return false;
	}
	document.form1.opinionid.value = id;
	document.form1.submit();
}
</script>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/materielapply/Gm_ration_apply!save.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="opinionid" />

			<c:if test="${type=='aduit'}">
				<div class="divMod2">
					<div class="divMod1">
						审批
					</div>
					<table class="dataListTable" width="100%">
						<tr>
							<th align="center" style="width: 10%">
								审批意见:
							</th>
							<td colspan="3" align="left">
								<textarea rows="3" cols="70" name="opinion"></textarea>
							</td>
						</tr>

					</table>
				</div>

				<br />

			</c:if>
			
			
			<div class="div-button">
			    <c:if test="${type=='aduit'}">
			    <input type="button" value="同意" onclick="sub('2');" />
				<input type="button" value="不同意" onclick="sub('4');" />
				<input type="button" value="打回" onclick="sub('3');" />
			    </c:if>
				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>





			<table class="table_border" style="width: 100%">

				<tr>
					<th align="center" style="width: 10%">
						项目:
					</th>
					<td style="width: 35%">
						<input type="text" name="prj_id" value="${record.proname}"
							dataType="Integer" require="false"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						申请单号:
					</th>
					<td style="width: 35%">
						<input type="text" name="bh" value="${record.bh}"
							dataType="Require" maxLength="20" style="width: 95%"
							class="textBoxNoBorder" />
					</td>
				</tr>



				<tr>
					<th align="center" style="width: 10%">
						收货人:
					</th>
					<td style="width: 35%">
						<input type="text" name="receive_person"
							value="${record.revename}" dataType="Integer" require="false"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						电话:
					</th>
					<td style="width: 35%">
						<input type="text" name="recv_call" value="${record.recv_call}"
							maxLength="20" style="width: 95%" class="textBoxNoBorder" />
					</td>
				</tr>
				<tr>
					<th align="center" style="width: 10%">
						申请时间:
					</th>
					<td style="width: 35%">
						<input type="text" name="apply_date" value="${record.apply_date}"
							dataType="Date" onfocus="WdatePicker()"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						采购类型:
					</th>
					<td style="width: 35%">
						${record.cgsqtype}
					</td>

				</tr>
				<tr>
					<th align="center" style="width: 10%">
						备注:

					</th>
					<td colspan="3">
						<textarea rows="3" style="width: 98%" name="note">${record.note}</textarea>

					</td>

				</tr>



			</table>

			<br />
			<div class="divMod2">
				<div class="divMod1">
					所申请材料
				</div>
				<table class="dataListTable" width="100%">
					<tr>


						<th>
							序号
						</th>

						<th>
							物资名称
						</th>
						<th>
							品牌
						</th>
						
						<th>
							规格型号
						</th>
						
						<th>
							产品参数
						</th>
						
						<th>
							申请数量
						</th>


						<th>
							要求到货日期
						</th>
						<c:if test="${record.isplan=='1'}">
						<th>
						计划数量
						</th>
						<th>
						已申请数量
						</th>
</c:if>

					</tr>
					<c:forEach items="${mlist}" var="result" varStatus="status">
						<tr>

							<td>
								<c:out value="${status.index+1}" />
							</td>

							<td>
								<c:out value="${result.mnate}" />
							</td>
							<td>
								<c:out value="${result.bname}" />
							</td>
							
							<td>
								<c:out value="${result.model}" />
							</td>
							
							<td>
								<c:out value="${result.parameter}" />
							</td>

							<td>
								<c:out value="${result.sqsl}" />
							</td>


							<td>
								<c:out value="${result.order_date}" />
							</td>
<c:if test="${record.isplan=='1'}">

<td>
								<c:out value="${result.psqsl}" />
							</td>


							<td>
								<c:out value="${result.ysq}" />
							</td>


</c:if>


						</tr>
					</c:forEach>
				</table>
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
								<c:out value="${purchaseapply_state[result.state]}" />
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
