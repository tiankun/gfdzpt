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
function sub(id) {
	if ((id == '4' || id == '3') && document.form1.opinion.value == '') {
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

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase!save.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="opinionid" />







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
						申请人：
					</th>
					<td style="width: 35%">
						${record.applyname}&nbsp;
						<strong>/</strong>&nbsp;${record.branchname}

					</td>
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						配给单:
					</th>
					<td style="width: 35%">
						<input type="text" name="ration_apply_id"
							value="${record.ration_apply_id}" dataType="Require"
							style="width: 95%" class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						编号:
					</th>
					<td style="width: 35%">
						<input type="text" name="dh" value="${record.dh}" maxLength="200"
							style="width: 95%" class="textBoxNoBorder" />
					</td>
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						采购人:
					</th>
					<td style="width: 35%">
						<input type="text" name="executename"
							value="${record.executename}" maxLength="20" style="width: 95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						申请时间:
					</th>
					<td style="width: 35%">
						<input type="text" name="applyname" value="${record.apply_date}"
							dataType="Integer" require="false"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>

				</tr>

				<tr>
					<th align="center" style="width: 10%">
						金额(小):
					</th>
					<td style="width: 35%">
						${record.xiaoxje}
					</td>
					<th align="center" style="width: 10%">
						金额(大):
					</th>
					<td style="width: 35%">
						${record.daxje}
					</td>

				</tr>

				<c:if test="${record.applynote!=null&&record.applynote!=''}">
					<tr>
						<th>
							申请人备注:
						</th>
						<td colspan="3">
							<textarea rows="3" style="width: 98%;" name="applynote"
								readonly="readonly">${record.applynote}</textarea>



						</td>
					</tr>
				</c:if>

				<c:if test="${record.note!=null&&record.note!=''}">
					<tr>
						<th>
							采购人备注:
						</th>
						<td colspan="3">
							<textarea rows="3" style="width: 98%;" name="note">${record.note}</textarea>
						</td>
					</tr>
				</c:if>

				<tr>
					<td height="50" colspan="4">
						<strong>合同扫描件上传：</strong>
						<table width="250px" border="1" cellspacing="0" cellpadding="0"
							id="zpf">
							<c:if test="${fn:length(record.path)>0}">
								<c:set var="zpf_sfs" value='${fn:split(record.path,";")}' />
								<c:forEach items="${zpf_sfs}" var="zpf_sf">
									<tr>
										<td width="60%">
											<a
												href="${pageContext.request.contextPath}/upFile/materielpurchase/${zpf_sf}"
												target="_blank">${zpf_sf}</a>
											<input type="hidden" name="fujian" value="${zpf_sf}">
										</td>

									</tr>
								</c:forEach>
							</c:if>
						</table>
						<span id="spanSWFUploadButton"></span>
					</td>

				</tr>



			</table>

			<c:if test="${type=='audit'||type=='finalaudit'}">
				<br />
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

					</table>
				</div>
			</c:if>


			<div class="div-button">
				<c:if test="${type=='audit'}">
					<input type="button" value="同意" onClick="sub('2');" />
					<input type="button" value="打回" onClick="sub('6');" />
					<input type="button" value="不同意" onClick="sub('3');" />
				</c:if>

				<c:if test="${type=='finalaudit'}">
					<input type="button" value="同意" onClick="sub('5');" />
					<input type="button" value="打回" onClick="sub('6');" />
					<input type="button" value="不同意" onClick="sub('4');" />
				</c:if>

				<input type="reset" value="重置" />
				<input type="button" value="关闭" onClick="javascript:closeDG();" />
			</div>

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
						<th>单位</th>
						<th>
							申请采购数量
						</th>
						<th>
							采购单价
						</th>
						<th>
							价格
						</th>
						<th>
							供应商
						</th>




						<th>
							要求到货日期
						</th>
						
						<th>
							确认收货数量
						</th>
						
						<th>
							状态
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
							<td><c:out value="${result.unit}" /></td>

							<td>
								<c:out value="${result.sqsl}" />
							</td>
							<td>
								<c:out value="${result.price}" />
							</td>
							<td>
								<c:out value="${result.totalMoney}" />
							</td>
							<td>
								<c:out value="${result.gys}" />
							</td>



							<td>
								<c:out value="${result.yaoqdhrq}" />
							</td>
							
							<td>
								<c:out value="${result.confirmsql}" />
							</td>
							
							<td title="${result.reason}">
								<c:if test="${result.state=='0'}"><font color="red">取消购买</font></c:if>
								<c:if test="${result.state!='0'}">正常</c:if>
							</td>



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
