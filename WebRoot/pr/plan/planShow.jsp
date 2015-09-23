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
	if (id == '3' && document.form1.opinion.value == '') {
		alert("请输入不同意原因");
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
			action="<%=request.getContextPath()%>/pr/plan/Pr_plan!check.do"
			method="post">
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="opinionid" />

			<c:if test="${flag=='check'}">
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



            <c:if test="${record.pr_plan_state=='3'}">
	<div class="divMod2">
	       
		<table class="dataListTable" width="100%">
			<tr>
			<th align="center" style="width:10%">打回原因:</th>
			<td colspan="3">
			  <textarea rows="3" cols="70" name="opinion">${record.opinion}</textarea>
			</td>
			</tr>
		
		</table>
	</div>
	
	<br/>
	     
	</c:if>

			<table class="table_border" style="width: 100%">
				<tr>
					<th align="center" style="width: 10%">
						项目:
					</th>
					<td colspan="3">
						<input type="text" name="name" value="${record.proname}"
							dataType="Integer" require="false"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						计划编号:
					</th>
					<td style="width: 35%">
						<input type="text" name="name" value="${record.name}"
							dataType="Integer" require="false"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						编号:
					</th>
					<td style="width: 35%">
						<input type="text" name="bh" value="${record.bh}"
							dataType="Require" maxLength="20" style="width: 95%"
							class="textBoxNoBorder" />
					</td>
				</tr>
				<tr>
					<th align="center" style="width: 10%">
						采购月:
					</th>
					<td style="width: 35%">
						<input type="text" name="purchase" value="${record.purchase}"
							dataType="Integer" require="false"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						申请时间:
					</th>
					<td style="width: 35%">
						<input type="text" name="made_date" value="${record.made_date}"
							dataType="Date" onfocus="WdatePicker()"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
				</tr>
				
				<tr>
					 <th>
					  备注：
					</th>
					  <td colspan="3">
					   <textarea rows="3" style="width: 98%" name="note" readonly="readonly">${record.note}</textarea>
					  </td>
					</tr>



			</table>

			<br />
			<div class="divMod2">
				<div class="divMod1">
					计划材料
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
							申请数量
						</th>




					</tr>
					<c:forEach items="${maList}" var="result" varStatus="status">
						<tr>
							<td>
								<c:out value="${status.index+1}" />
							</td>
							<td>
								<c:out value="${result.ma_name}" />
							</td>
							<td>
								<c:out value="${result.br_name}" />
							</td>
							<td>
								<c:out value="${result.sqsl}" />
							</td>

						</tr>
					</c:forEach>
				</table>
			</div>



			<br />
			<div class="div-button">
				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>
		</form>
	</body>
</html>
