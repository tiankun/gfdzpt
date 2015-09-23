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
	if(id=='4')
		{
		  var flag = true;

			var obj = document.form1.elements;
			for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "checkbox") {
				if (obj[i].checked == true) {
					flag = false;
					
				}

				}
			}
		
		if (flag) {
			alert("请至少选择一条记录！");
			return false;
		}
		}
	
	
	if (confirm("确定提交")) {
		document.form1.opinionid.value = id;
		document.form1.submit();
	}

}

function subs() {
	if (confirm("确定作废")) {
		document.form1.action = '<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!zuofei.do';
		document.form1.submit();
	}

}

function add()
{
	var total = 0;
	var obj = document.form1.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "checkbox") {
				if (obj[i].checked == true) {
					var str = 'money'+obj[i].id;
					
					var money = document.getElementById(str).value;
					
					total = total + parseFloat(money);
				}

			}
		}
		document.form1.total.value = total;
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
	
	var total = 0;
	var obj = document.form1.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "checkbox") {
				if (obj[i].checked == true) {
					var str = 'money'+obj[i].id;
					
					var money = document.getElementById(str).value;
					
					total = total + parseFloat(money);
				}

			}
		}
		document.form1.total.value = total;
}
</script>

	</head>
	<body>
		<script type="text/javascript">
${operationSign}</script>
		<form
			action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!sub1.do"
			method="post" name="form1">
			<input type="hidden" name="opinionid">
			<input type="hidden" name="payforid" value="${record.id}">
			<input type="hidden" name="id" value="${record.id}">
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />

			<c:if test="${record.payforstate=='8'||record.payforstate=='10'}">
				<div class="divMod2">
					<div class="divMod1">
						审批意见
					</div>
					<table class="dataListTable" width="100%">
						<tr>
							<th align="center" style="width: 10%">
								原因:
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="opinion">${record.reason}</textarea>
							</td>
						</tr>



					</table>
				</div>

				<br />

			</c:if>
			
			<c:if test="${record.payforstate=='9'}">
				<div class="divMod2">
					
					<table class="dataListTable" width="100%">
						<tr>
							<th align="center" style="width: 10%">
								作废原因:
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="opinion">${record.zfreason}</textarea>
							</td>
						</tr>



					</table>
				</div>

				<br />

			</c:if>


			<c:if test="${record.payforstate=='7'&&look!='1'}">
				<div class="div-button">
					<a href="#" onClick="subs('9');" class="link">作废</a>

				</div>
				<div class="divMod2">
					<div class="divMod1">
						作废
					</div>
					<table class="dataListTable" width="100%">
						<tr>
							<th align="center" style="width: 10%">
								作废原因:
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="zfreason"></textarea>
							</td>
						</tr>



					</table>
				</div>

				<br />

			</c:if>


			<c:if test="${type=='modify'}">

				<c:if test="${record.payforstate!='3'}">

					<div class="div-button">
						<a href="#" onClick="sub('0');" class="link">保存不提交</a>
						<a href="#" onClick="sub('1');" class="link">保存且提交</a>
					</div>

				</c:if>


				<c:if test="${record.payforstate=='3'}">

					<div class="div-button">
						<a href="#" onClick="sub('4');" class="link">分配付款</a>
					</div>

				</c:if>


			</c:if>

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
								<input type="button" value="同意" onclick="sub('2');" />
								<input type="button" value="不同意" onclick="sub('3');" />
							</td>
						</tr>

					</table>
				</div>

				<br />

			</c:if>


			<div class="divMod2">

				<table class="dataListTable" width="100%">
					<tr>
						<th align="center" style="width: 15%">
							付款方式:
						</th>
						<td width="35%" align="left">
							<select name="paytype" class="dropdownlist">
								<c:forEach items="${paytype}" var="paytype">
									<option value="${paytype.key}"
										<c:if test="${record.paytype==paytype.key}">selected</c:if>>
										${paytype.value}
									</option>
								</c:forEach>
							</select>
						</td>
						<th align="center" style="width: 10%">付款单位:</th>
						<td >
							${record.kjtype}
						</td>
					</tr>
					<tr>
						<th align="center" style="width: 15%">总价:</th>
						<td width="35%" align="left">
							${record.money}
						</td>
						<th align="center" style="width: 10%">人民币大写:</th>
						<td >
							${record.bmoney}
						</td>
					</tr>
					<tr>
					<th align="center" style="width: 15%">收款单位:</th>
						<td width="35%" align="left">
							${sk}
						</td>
						<th align="center" style="width: 10%">
							备注:
						</th>
						<td colspan="3">
							<textarea rows="3" cols="70" name="note">${record.note}</textarea>
						</td>
					</tr>
				</table>
			</div>

			<div class="clear" style="height: 0px;"></div>

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
						<%--<c:if test="${type=='modify'}">
							<th width="70">
								操作
							</th>
						</c:if>

					--%></tr>
					<c:forEach items="${reslist}" var="result" varStatus="status">
						<tr>
							<td>

								<input type="checkbox" name="checkbox"  onclick="add();" id="${status.index+1}"  <c:if test="${record.payforstate!='3'}">checked</c:if> 
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
								${result.num}
							</td>

							<td>
								<c:out value="${result.money}" />
								<input type="hidden" id="money${status.index+1}" value="${result.money}">
							</td>
							
							<td>
								${result.fujnum}
							</td>
							
							<%--<c:if test="${type=='modify'}">
								<td>
									<a
										href="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!delete.do?id=${result.id}"
										OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
								</td>
							</c:if>


						--%></tr>
						
						
					</c:forEach>
					<c:if test="${record.payforstate=='3'&&look!='1'}">
					<tr>
						  <td colspan="7" style="font-weight: bold;">合计：</td>
						  <td style="font-weight: bold;" align="center"><input type="text" name="total" value="0" readonly="readonly" class="textBoxNoBorder" style="width: 90%"/></td>
						</tr>
					</c:if>
				</table>
			</div>
			<br/>
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
		</form>
	</body>
</html>