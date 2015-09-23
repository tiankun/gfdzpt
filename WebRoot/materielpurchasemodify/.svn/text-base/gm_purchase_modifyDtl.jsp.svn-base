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

	<script type="text/javascript">
function sub(id) {
	if ((id == '4'||id == '3') && document.form1.opinion.value == '') {
		alert("请输入不同意原因");
		document.form1.opinion.focus();
		return false;
	}
	
	if (id == '6' && document.form1.opinion.value == '') {
		alert("请输入打回原因");
		document.form1.opinion.focus();
		return false;
	}
	document.form1.opinionid.value = id;
	document.form1.submit();
}
</script>

	<body>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!save.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="opinionid" />

			<c:if test="${type=='audit'}">
				<div class="divMod2">
					<div class="divMod1">
						审批
					</div>
					<table class="dataListTable" width="100%">
						<tr>
							<th align="center" style="width: 10%">
								审批意见:
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="opinion"></textarea>
							</td>
						</tr>

						<tr>

							<td colspan="4">
								<input type="button" value="同意" onClick="sub('2');" />
								<input type="button" value="打回" onClick="sub('6');" />
								<input type="button" value="不同意" onClick="sub('3');" />
							</td>
						</tr>

					</table>
				</div>

				<br />

			</c:if>


			<c:if test="${type=='finalaudit'}">
				<div class="divMod2">
					<div class="divMod1">
						审批
					</div>
					<table class="dataListTable" width="100%">
						<tr>
							<th align="center" style="width: 10%">
								审批意见:
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="opinion"></textarea>
							</td>
						</tr>

						<tr>

							<td colspan="4">
							    
								<input type="button" value="同意" onClick="sub('5');" />
								<input type="button" value="打回" onClick="sub('6');" />
								<input type="button" value="不同意" onClick="sub('4');" />
							</td>
						</tr>

					</table>
				</div>

				<br />

			</c:if>



			<table class="table_border" style="width: 100%">

				<tr>
					<th align="center" style="width: 10%">
						项目:
					</th>
					<td style="width: 35%">
						<input type="text" name="prj_id" value="${info.pname}"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						部门/申请人:
					</th>
					<td style="width: 35%">
						${info.brname}
						<strong>/</strong>${info.exename}
					</td>
				</tr>
				<tr>
					<th align="center" style="width: 10%">
						配给执行号:
					</th>
					<td style="width: 35%">
						${modify.ration_apply_id}
					</td>
					<th align="center" style="width: 10%">
						配给变更号:
					</th>
					<td style="width: 35%">
						${modify.modify_ration_id}
					</td>
				</tr>


				<tr>
					<th align="center" style="width: 10%">
						变更人:
					</th>
					<td style="width: 35%">
						${modify.pname}
					</td>
					<th align="center" style="width: 10%">
						变更申请时间:
					</th>
					<td style="width: 35%">
						${modify.modify_date}
					</td>
				</tr>

				<tr>
					<th>
						变更原因:
					</th>
					<td colspan="3">
						<textarea rows="3" cols="70">${modify.note}</textarea>
					</td>
				</tr>

			</table>


			<br />
			<div class="divMod2">
				<div class="divMod1">
					变更材料
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
							数量
						</th>
						<th>
							单价
						</th>
						<th>
							供应商
						</th>







					</tr>
					<c:forEach items="${modifymateriel}" var="result"
						varStatus="status">
						<tr>


							<td>
								<c:out value="${status.index+1}" />
							</td>

							<td>
								<c:out value="${result.mname}" />
							</td>
							<td>
								<c:out value="${result.bname}" />
							</td>

							<td>
								<c:if test="${result.sqsl==result.sqsl1}">
      ${result.sqsl}
      </c:if>
								<c:if test="${result.sqsl!=result.sqsl1}">
      ${result.sqsl} <font color="red"
										style="font-weight: bolder; background-color: orange;">--></font> ${result.sqsl1}
      </c:if>

							</td>
							<td>
								<c:if test="${result.price==result.price1}">
      ${result.price}
      </c:if>
								<c:if test="${result.price!=result.price1}">
      ${result.price} <font color="red"
										style="font-weight: bolder; background-color: orange;">--></font> ${result.price1}
      </c:if>
							</td>
							<td>
								<c:if test="${result.gys==result.gys1}">
      ${result.gys}
      </c:if>
								<c:if test="${result.gys!=result.gys1}">
      ${result.gys} <font color="red"
										style="font-weight: bolder; background-color: orange;">--></font> ${result.gys1}
      </c:if>
							</td>


						</tr>
					</c:forEach>


				</table>
			</div>




			<div class="div-button">

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
								<c:out value="${purchase_state[result.state]}" />
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
