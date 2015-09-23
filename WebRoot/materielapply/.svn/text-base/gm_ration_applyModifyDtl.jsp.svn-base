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
	<body>
		<script type="text/javascript">
function sub(id) {
	if (confirm("确定提交申请")) {
		document.form1.spzt.value = id;
		document.form1.submit();
	}

}
</script>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/materielapply/Gm_ration_apply!subapply.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="spzt" />

			<c:if test="${type=='modify'&&record.spzt!='0'}">
				<div class="divMod2">

					<table class="dataListTable" width="100%">
						<tr>
							<th align="center" style="width: 10%">
								打回原因:
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="opinion">${record.spyj}</textarea>
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
						<input type="text" name="prj_id" value="${record.proname}"
							readonly="readonly" style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						项目经理:
					</th>
					<td style="width: 35%">
						<input type="text" name="item_manage"
							value="${record.item_manage1}" readonly="readonly"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						编号:
					</th>
					<td style="width: 35%">
						<input type="text" name="bh" value="${record.bh}"
							dataType="Require" maxLength="20" style="width: 95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						计划单:
					</th>
					<td style="width: 35%">
						<input type="text" name="item_id" value="${record.planname}"
							maxLength="200" style="width: 95%" class="textBoxNoBorder" />
					</td>
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						收货人:
					</th>
					<td style="width: 35%">
						<input type="text" name="name" id="name"
							value="${record.revename}" class="textBox" />
						<input type="text" name="receive_person" id="receive_person"
							value="${record.receive_person}" class="hidden" />
						<input type="button"
							onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','receive_person','name');"
							value="选择" />
					</td>
					<th align="center" style="width: 10%">
						电话:
					</th>
					<td style="width: 35%">
						<input type="text" name="recv_call" value="${record.recv_call}"
							maxLength="20" style="width: 95%" class="textBoxNoBorder" />
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

						<th width="65">
							申请数量
						</th>


						<th width="90">
							要求到货日期
						</th>

						<th>
							操作
						</th>


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
								<input name="sqsl${result.id}" value="${result.sqsl}" dataType="Double" onkeyup="if(isNaN(value))execCommand('undo')"
							onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 70%" />
							</td>


							<td>
								<input type="text" name="order_date${result.id}"
									value="${result.order_date}" dataType="Date"
									onfocus="WdatePicker()" style="ime-mode: disabled"
									style="width:95%" class="textBoxNoBorder" />

							</td>

							<td>
								<a
									href="<%=request.getContextPath()%>/materielapply/Gm_ration_apply!delete.do?id=${result.id}"
									OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
							</td>




						</tr>
					</c:forEach>
				</table>
			</div>



			<br />
			<div class="div-button">
				<input type="button" name="btn" value="保存不提交" onclick="sub('0');" />
				<input type="button" name="btn" value="保存且提交" onclick="sub('1');" />

				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>
		</form>
	</body>
</html>
