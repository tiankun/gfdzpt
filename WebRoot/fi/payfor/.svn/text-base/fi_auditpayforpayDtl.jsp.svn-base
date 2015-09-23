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
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>

		<script type="text/javascript">
function sub(id) {
	if (document.form1.opinion.value == '') {
		alert("请输入审批意见");
		return false;
	}
	document.form1.opinionid.value = id;
	document.form1.submit();
}
///////////////////////////////////////附件查看
function show(){
    $("#upTd").empty();
	$("#upTd").append("<a href='#' onclick='openDGMax(\"查看\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${accid}&folder=fiAcc&flag=accessory&mark=show&oldname=file\")'>"
			+"<img src='<%=request.getContextPath()%>/image/fileopen.png'/></a>");
}
//////////////////////////////////////////
</script>

	</head>
	<body>
		<script type="text/javascript">
${operationSign}</script>
		<form
			action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor_audit!edit.do"
			method="post" name="form1">
			<input type="hidden" name="opinionid">
			<input type="hidden" name="id" value="${record.id}">
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />

			<c:if test="${ma=='0'}">

				<div class="divMod2">

					<table class="dataListTable" width="100%">
						<tr>
							<th align="center" style="width: 15%">
								付款方式:
							</th>
							<td width="35%" align="left">
								<select name="paytype" class="dropdownlist" disabled="disabled">
									<c:forEach items="${paytype}" var="paytype">
										<option value="${paytype.key}"
											<c:if test="${record.paytype==paytype.key}">selected</c:if>>
											${paytype.value}
										</option>
									</c:forEach>
								</select>
							</td>
							<th align="center" style="width: 15%">
								付款金额:
							</th>
							<td align="left">
								${record.money}&nbsp;元
							</td>


						</tr>
						<c:if test="${record.kjtype!=null&&record.kjtype!=''}">
						<tr>
							<th align="center" style="width: 15%">
								类型:
							</th>
							<td width="35%" align="left">
								${record.kjtype}
							</td>
							<th align="center" style="width: 15%">
							
							</th>
							<td align="left">
								
							</td>


						</tr>
						</c:if>
						
						<tr>
						<th align="center" style="width: 15%">
								收款单位:
							</th>
							<td width="35%" align="left">
								${sk}
							</td>
							<th align="center" style="width: 10%">
								备注:
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="note" readonly="readonly">${record.note}</textarea>
							</td>
						</tr>


					</table>
				</div>

				<div class="clear" style="height: 0px;"></div>

				<div>
					<table class="dataListTable" width="100%">
						<tr>

							<th>
							</th>

							<th>
								序号
							</th>
							<th>
								配给单
							</th>
							<th>
								项目
							</th>
							<th>
								材料
							</th>
							

							<th>
								单价
							</th>
							<th>
								供应商
							</th>
							<th width="70">
								支付数量
							</th>

							<th width="70">
								金额
							</th>
							<th>
								单据数
							</th>
							<c:if test="${type=='modify'}">
								<th width="70">
									操作
								</th>
							</c:if>

						</tr>
						<c:forEach items="${reslist}" var="result" varStatus="status">
							<tr>
								<td>

									<input type="checkbox" name="checkbox" checked="checked"
										readonly="readonly" value="${result.id}" />

								</td>

								<td>

									<c:out value="${status.index+1}" />
								</td>
								<td>
									<c:out value="${result.ration_apply_id}" />
								</td>
								<td>
									<%-- <c:out value="${result.pname}" /> --%>
									<c:if test="${empty result.pname}">未关联项目</c:if>
                                    <c:if test="${not empty result.pname}"><c:out value="${result.pname}" /></c:if>
								</td>
								<td>
									<c:out value="${result.maname}" />
								</td>


								<td>
									<c:out value="${result.price}" />
								</td>
								<td>
									<c:out value="${result.gys}" />
								</td>

								<td>
								    <c:out value="${result.num}" />
									
								</td>

								<td>
									<c:out value="${result.money}" />
								</td>
								
								<td>
									<c:out value="${result.fujnum}" />
								</td>
	
								<c:if test="${type=='modify'}">
									<td>
										<a
											href="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!delete.do?id=${result.id}"
											OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
									</td>
								</c:if>


							</tr>
						</c:forEach>
					</table>
				</div>

			</c:if>

			<c:if test="${ma=='1'}">
				<table class="table_border"
					style="width: 100%; border: 2px silver solid;">

					<tr>
						<th align="center" style="width: 10%">
							收款单位:
						</th>
						<td style="width: 35%">
							${record.name}
						</td>
						<th align="center" style="width: 10%">
							配给类型:
						</th>
						<td style="width: 35%">
							${record.pjdtype}
						</td>
					</tr>
					<tr>
						<th align="center" style="width: 15%">
							付款方式:
						</th>
						<td width="35%" align="left">
							<select name="paytype" class="dropdownlist" disabled="disabled">
								<c:forEach items="${paytype}" var="paytype">
									<option value="${paytype.key}"
										<c:if test="${record.paytype==paytype.key}">selected</c:if>>
										${paytype.value}
									</option>
								</c:forEach>
							</select>
						</td>
						<th align="center" style="width: 10%">
							申请人:
						</th>
						<td style="width: 35%">
							${record.pname}
						</td>
					</tr>
					<!-- /////////////////////////////////////////附件查看 -->
				<tr>
					<th align="center" style="width:10%">附件列表:</th>
					<td colspan="3" id="upTd">
					<a href="#" onclick="openDGMax('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.id}&folder=fiAcc&flag=accessory&mark=show&oldname=file')">
					<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
					</a>
		    		<!-- <div id="_container">  </div> -->
					</td>
               </tr>
          <!-- //////////////////////////////////////// -->
					<tr>
						<td colspan="4">
							<div class="divMod2">
								<div class="divMod1">
									支付明细
								</div>
								<table class="dataListTable" width="100%">
									<tr>



										<th width="5%">
											序号
										</th>

										<th>
											申请事由
										</th>
										<th width="15%">
											金额
										</th>
										<th width="8%">
											附件数
										</th>



									</tr>

									<c:forEach var="res" items="${slist}" varStatus="s">
										<tr>

											<td>
												${s.index+1}
											</td>

											<td>
												<input type="text" name="rea${res.id}" style="width: 98%" readonly="readonly"
													value="${res.applyreason}">
											</td>
											<td>
												<input type="text" name="money${res.id}" style="width: 98%" readonly="readonly"
													value="${res.money}">
											</td>

											<td>
												<input type="text" name="fuj${res.id}" style="width: 90%" readonly="readonly"
													value="${res.fujnum}">
											</td>
										</tr>

									</c:forEach>
								</table>
							</div>
						</td>
					</tr>


				</table>

			</c:if>


			<br />

			<c:if
				test="${type=='audit'||type=='kuaiji'||type=='caiwzg'||type=='xpzaudit'||type=='cuiy'}">
				<div class="divMod2">
					<div class="divMod1">
						审批
					</div>
					<table class="dataListTable" width="100%">
						<c:if test="${type=='kuaiji'}">
							<tr>

								<td colspan="4" align="left">
									请选择：
									<select name="kjtype">
										<option value="KJ">
											KJ
										</option>
										<option value="KMG">
											KMG
										</option>
										<option value="KM">
											KM
										</option>
									</select>
								</td>
							</tr>
						</c:if>

						<tr>
							<th align="center" style="width: 10%">
								意见:
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="opinion"></textarea>
							</td>
						</tr>

						<tr>

							<td colspan="4">
								<c:if test="${type=='audit'}">
									<input type="button" value="同意" onclick="sub('2');" />
									<input type="button" value="不同意" onclick="sub('10');" />
									<input type="button" value="打回" onclick="sub('8');" />
								</c:if>

								<c:if test="${type=='kuaiji'}">
									<input type="button" value="同意" onclick="sub('3');" />

									<input type="button" value="打回" onclick="sub('8');" />

									<input type="button" value="不同意" onclick="sub('10');" />
								</c:if>

								<c:if test="${type=='caiwzg'}">
									<c:if test="${deptid!='9'}">
										<input type="button" value="同意" onclick="sub('5');" />
									</c:if>

									<c:if test="${deptid=='9'}">
										<input type="button" value="同意" onclick="sub('6');" />
									</c:if>

									<input type="button" value="打回" onclick="sub('8');" />

									<input type="button" value="不同意" onclick="sub('10');" />

								</c:if>

								<c:if test="${type=='xpzaudit'}">
									<input type="button" value="同意" onclick="sub('7');" />
									<input type="button" value="不同意" onclick="sub('10');" />
									<input type="button" value="打回" onclick="sub('8');" />
								</c:if>

								<c:if test="${type=='cuiy'}">

									<input type="button" value="同意" onclick="sub('7');" />
									<input type="button" value="不同意" onclick="sub('10');" />
									<input type="button" value="打回" onclick="sub('8');" />
								</c:if>
								
								<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
							</td>
						</tr>

					</table>
				</div>

				<br />

			</c:if>

			<div class="divMod2">
				<div class="divMod1">
					审批历史
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
								<c:out value="${result.pname}" />
							</td>
							<td>
								<c:out value="${payforstate[result.state]}" />
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




			<div id="PageUpDnDiv"
				style="PADDING-TOP: 5px; padding-bottom: 5px; text-align: center">

			</div>
		</form>
	</body>
</html>