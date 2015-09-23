<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>在线编辑</title>

		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
		<<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/default.css" type="text/css"></link>
		<script language="javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>

		<script src="${pageContext.request.contextPath}/jscripts/jquery.js">
</script>

		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>

		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/swfupload.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/handlers.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/phprpc_client.js">
</script>
		<script type="text/javascript">
function sub1(id) {
	if (document.form1.opinion.value == '') {
		alert("请输入审批意见");
		document.form1.opinion.focus();
		return false;
	}
	document.form1.state.value = id;
	document.form1.aciton = '${pageContext.request.contextPath}/fi/bill/Fi_bill_audit!edit.do';
	document.form1.submit();
}

function sub2(id) {
	if (document.form1.opinion.value == '') {
		alert("请输入审批意见");
		document.form1.opinion.focus();
		return false;
	}
	document.form1.state.value = id;
	document.form1.aciton = '${pageContext.request.contextPath}/fi/bill/Fi_bill_audit!cwedit.do';
	document.form1.submit();
}

function sub3() {

	if (document.form1.act_date.value == '') {
		alert("请输入实际收款时间");
		document.form1.act_date.focus();
		return false;
	}

if (parseFloat(document.form1.receive_money.value)>parseFloat(${bill.nreceive_money})) {
			alert("收款金额超过支票金额");
			document.form1.receive_money.focus();
			return false;
		}
	
	document.form1.aciton = '${pageContext.request.contextPath}/fi/bill/Fi_bill_audit!kpedit.do';
	document.form1.submit();
}

function huank()
{
	
	if (document.form1.receive_money.value == '') {
			alert("请输入收款金额");
			document.form1.receive_money.focus();
			return false;
		}
	
	if (parseFloat(document.form1.receive_money.value)>parseFloat(${bill.nreceive_money})) {
			alert("收款金额超过支票金额");
			document.form1.receive_money.focus();
			return false;
		}
	
	document.form1.action = '${pageContext.request.contextPath}/fi/bill/Fi_bill_audit!hkedit.do';
	document.form1.submit();
}

function sub4() {
	
	if (document.form1.reason.value == '') {
			alert("请输入作废原因");
			document.form1.reason.focus();
			return false;
		}
	
	document.form1.aciton = '${pageContext.request.contextPath}/fi/bill/Fi_bill_audit!zfedit.do';
	document.form1.submit();
}
</script>
	</head>
	<script type="text/javascript">
${operationSign}
</script>
	<body>
		<form method="post" name="form1">
			<input name="id" type="hidden" value="${bill.id}">
			<input name="state" type="hidden">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<table border="0" style="width: 100%; padding-left: 5px;"
				align="center">
				<tr>
					<td colspan="4" height="10">
					</td>
				</tr>

				<tr class="kssh">
					<th align="center" style="width: 10%" class="kssh" height="30">
						发票类型
					</th>
					<td class="kssh">
						&nbsp;
						<c:if test="${bill.ftype=='1'}">工程类</c:if>
						<c:if test="${bill.ftype=='2'}">收据类</c:if>
						<c:if test="${bill.ftype=='3'}">其他类</c:if>
						&nbsp;&nbsp;&nbsp; ${bill.stype}
					</td>
					<th width="10%" align="center" height="30" class="kssh">
						<strong>开票单位</strong>
					</th>
					<td width="35%" class="kssh">
						&nbsp;${fukuan_unit[bill.fukuan_unit]}
					</td>
				</tr>

				<tr class="kssh">
					<th width="10%" align="center" height="30" class="kssh">
						<strong>客户单位</strong>
					</th>
					<td width="35%" class="kssh">
						&nbsp;${bill.unit}
					</td>
					<th width="10%" align="center" class="kssh">
						<strong>发票内容</strong>
					</th>
					<td width="35%" class="kssh">
						&nbsp;${bill.content}
					</td>

				</tr>
				<tr>
					<th width="10%" align="center" height="30" class="kssh">
						<strong>开具金额</strong>
					</th>
					<td width="35%" class="kssh">
						&nbsp;${bill.money}
					</td>
					<th width="10%" align="center" class="kssh">
						<strong>计划收款时间</strong>
					</th>
					<td width="35%" class="kssh">
						&nbsp;${bill.plan_date}
					</td>

				</tr>
				
				<tr>
					<th width="10%" align="center" height="30" class="kssh">
						<strong>发票号</strong>
					</th>
					<td width="35%" class="kssh">
						&nbsp;${bill.faph}
					</td>
					<th width="10%" align="center" class="kssh">
						<strong>地税国税</strong>
					</th>
					<td width="35%" class="kssh">
						&nbsp;${bill.disgsh}
					</td>

				</tr>
				
			</table>


			<c:if test="${bill.ftype=='3'}">
				<table width="100%">
					<tr class="kssh">
						<td align="center" height="30" class="kssh">
							<strong>增值税发票开票信息</strong>

						</td>
					</tr>
					<tr class="kssh" align="center">
						<td class="kssh" align="center">
							<table width="100%" align="center">
								<tr class="kssh">
									<td class="kssh" width="10%" align="center" height="30">
										<strong>识别号</strong>

									</td>
									<td class="kssh" width="23%">
										&nbsp;${bill.shibh}
									</td>
									<td class="kssh" width="10%" align="center">
										<strong>地址</strong>

									</td>
									<td class="kssh" width="23%">
										&nbsp;${bill.addr}

									</td>
									<td class="kssh" width="10%" align="center">
										<strong>电话</strong>

									</td>
									<td class="kssh" width="23%">
										&nbsp;${bill.phone}

									</td>
								</tr>

								<tr class="kssh">
									<td class="kssh" align="center" height="30">
										<strong>开户行</strong>
									</td>
									<td class="kssh">
										&nbsp;${bill.kaihh}

									</td>
									<td class="kssh" align="center">
										<strong>账号</strong>

									</td>
									<td class="kssh">
										&nbsp;${bill.account}

									</td>
									<td colspan="2" class="kssh">
									</td>
								</tr>
								<tr>
									<td height="50" colspan="3">
										<strong>附件上传</strong>
										<table width="250px" border="1" cellspacing="0"
											cellpadding="0" id="zpf">
											<c:if test="${fn:length(bill.path)>0}">
												<c:set var="zpf_sfs" value='${fn:split(bill.path,";")}' />
												<c:forEach items="${zpf_sfs}" var="zpf_sf">
													<tr>
														<td width="60%">
															<a
																href="${pageContext.request.contextPath}/upFile/fi/bill/${zpf_sf}"
																target="_blank">${zpf_sf}</a>
															<input type="hidden" name="fujian" value="${zpf_sf}">
														</td>
														<c:if test="${bill.bill_state=='0'}">
															<td style="text-align: center">

																<a href=javascript:void(0) onclick=del('${zpf_sf}');>删除</a>
															</td>
														</c:if>
													</tr>
												</c:forEach>
											</c:if>
										</table>
										<span id="spanSWFUploadButton"></span>
									</td>

								</tr>
							</table>
						</td>
					</tr>
				</table>
			</c:if>





			<c:if test="${bill.bill_state=='7'}">
				<table class="table_border" style="width: 100%">

					<tr>
						<th width="15%" align="center" height="35">
							<font style="font-size: 12px;">实际收款时间</font>
						</th>
						<td colspan="3">
							${bill.act_date}
						</td>
					</tr>
				</table>
			</c:if>

			<c:if test="${bill.bill_state=='8'}">
				<table class="table_border" style="width: 100%">

					<tr>
						<th width="15%" align="center" height="35">
							实际收款时间
						</th>
						<td colspan="3">
							${bill.act_date}
						</td>
					</tr>
					<tr>
						<th width="15%" align="center" height="35">
							作废原因
						</th>
						<td colspan="3">
							<textarea rows="3" cols="70" name="opinion">${bill.reason}</textarea>
						</td>
					</tr>
				</table>
			</c:if>

			<c:if test="${type=='audit'}">
				<c:if test="${bill.bill_state=='1'}">

					<table class="table_border" style="width: 100%">

						<tr>
							<th width="15%" align="center" height="35">
								审批意见
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="opinion"></textarea>
							</td>
						</tr>
					</table>
				</c:if>

				<c:if test="${bill.bill_state=='2'}">

					<table class="table_border" style="width: 100%">
						<tr>
							<th width="15%" align="center" height="35">
								审批意见
							</th>
							<td>
								<textarea rows="3" cols="70" name="opinion"></textarea>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${bill.bill_state=='2'||bill.bill_state=='6'}">
					<table class="table_border" style="width: 100%">
						<tr>
							<th width="15%" align="center" height="35">
								付款单位
							</th>
							<td>
								<select name="fukuan_unit" dataType="Require" class="ddlNoBorder" ><option value="">---</option>
								<c:forEach items="${fukuan_unit}" var="fukuan">
								<option value="${fukuan.key}" <c:if test="${bill.fukuan_unit==fukuan.key}">selected</c:if>>${fukuan.value}</option>
								</c:forEach>
								</select>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${bill.bill_state=='6'}">
					<table class="table_border" style="width: 100%">
						<tr>
							<th width="15%" align="center" height="35">
								<strong>实际收款时间</strong>
							</th>
							<td >
								<input type="text" name="act_date" readonly="readonly"
									value="${bill.act_date}" onFocus="WdatePicker()" />
							</td>
							<th width="15%" align="center" height="35">
								<strong>收款金额</strong>
							</th>
							<td>
								<input type="text" name="receive_money"
									value="${bill.nreceive_money}" dataType="Double"
									onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')"
									style="ime-mode: disabled" />
							</td>
						</tr>

						<tr>
							<th width="15%" align="center" height="35">
								<strong>发票号</strong>
							</th>
							<td >
								<input type="text" name="faph" 
									value="${bill.faph}" />
							</td>
							<th width="15%" align="center" height="35">
								<strong>地税国税</strong>
							</th>
							<td>
								<input type="text" name="disgsh"
									value="${bill.disgsh}"  />
							</td>
						</tr>

					</table>
				</c:if>

				<c:if test="${bill.bill_state=='7'}">
					<table class="table_border" style="width: 100%">

						<c:if test="${ttype=='1'}">
							<tr>
							    <th width="15%" align="center" height="35">
									<strong>已收金额</strong>
								</th>
								<td width="30%">
									&nbsp;${bill.receive_money}
								</td>
								<th width="15%" align="center" height="35">
									<strong>收款金额</strong>
								</th>
								<td >
									<input type="text" name="receive_money"
										value="${bill.nreceive_money}" dataType="Double"
										onkeyup="if(isNaN(value))execCommand('undo')"
										onafterpaste="if(isNaN(value))execCommand('undo')"
										style="ime-mode: disabled" />
								</td>
							</tr>
						</c:if>
						<c:if test="${ttype!='1'}">
							<tr>
								<th width="15%" align="center" height="35">
									<strong>作废原因</strong>
								</th>
								<td colspan="3">
									<textarea rows="3" cols="70" name="reason">${bill.reason}</textarea>
								</td>
							</tr>
						</c:if>

					</table>
				</c:if>



			</c:if>



			<div class="div-button">
				<c:if test="${bill.bill_state=='1'&&type=='audit'}">
					<input type="button" value="同意" onclick="sub1('1');"
						style="${btnDisplay}" />
					<input type="button" value="不同意" onclick="sub1('0');"
						style="${btnDisplay}" />
				</c:if>

				<c:if test="${bill.bill_state=='2'&&type=='audit'}">
					<input type="button" value="同意" onclick="sub2('1');"
						style="${btnDisplay}" />
					<input type="button" value="不同意" onclick="sub2('0');"
						style="${btnDisplay}" />
				</c:if>

				<c:if test="${bill.bill_state=='6'&&type=='audit'}">
					<input type="button" value="开票" onclick="sub3();"
						style="${btnDisplay}" />

				</c:if>

				<c:if test="${bill.bill_state=='7'}">
					<c:if test="${ttype!='1'}">
						<input type="button" value="作废" onclick="sub4();"
							style="${btnDisplay}" />
					</c:if>
					<c:if test="${ttype=='1'}">
						<input type="button" value="还款" onclick="huank();"
							style="${btnDisplay}" />
					</c:if>
				</c:if>

				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>
            
            <c:if test="${hk=='1'}">
            <br/>
            
            <div class="divMod2">
				<div class="divMod1">
					收款历史
				</div>
				<table class="dataListTable" width="100%">
					<tr>


						<th>
							序号
						</th>

						<th>
							收款金额
						</th>
						<th>
							收款时间
						</th>


					</tr>
					<c:forEach items="${hklist}" var="result" varStatus="status">
						<tr>

							<td>
								<c:out value="${status.index+1}" />

							</td>

							<td>
								<c:out value="${result.money}" />
							</td>
						
							<td>
								<c:out value="${result.rdate}" />
							</td>
							

						</tr>
					</c:forEach>
				</table>
			</div>
			
			</c:if>

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
								<c:out value="${bill_state[result.bill_state]}" />
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