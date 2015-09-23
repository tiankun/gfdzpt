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
	if (id == '4' && document.form1.opinion.value == '') {
		alert("请输入不同意原因");
		document.form1.opinion.focus();
		return false;
	}
	document.form1.opinionid.value = id;
	document.form1.submit();
}

$(document).ready(function(){
	$('.titlediv').toggle(function(){
		$('#dest'+this.id).css("display","block");},
		function(){
		$('#dest'+this.id).css("display","none");	
		
		
		
	});
});
</script>



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
						申请部门/申请人：
					</th>
					<td style="width: 35%">
						${record.branchname}
						<strong>/</strong>${record.applyname}
					</td>
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						配给申请单:
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
						采购执行人:
					</th>
					<td style="width: 35%">
						<input type="text" name="executename"
							value="${record.executename}" maxLength="20" style="width: 95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						编制时间:
					</th>
					<td style="width: 35%">
						<input type="text" name="applyname" value="${record.apply_date}"
							dataType="Integer" require="false"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>

				</tr>
				<c:if test="${record.spzt=='2'}">
					<tr>
						<th align="center" style="width: 10%">
							部门经理:
						</th>
						<td style="width: 35%">
							<input type="text" name="executename" value="${dept.name}"
								maxLength="20" style="width: 95%" class="textBoxNoBorder" />
						</td>
						<th align="center" style="width: 10%">
							审批时间:
						</th>
						<td style="width: 35%">
							<input type="text" name="applyname" value="${dept.audit_date}"
								dataType="Integer" require="false"
								onkeyup="value=value.replace(/[^\d]/g,'')"
								style="ime-mode: disabled" style="width:95%"
								class="textBoxNoBorder" />
						</td>

					</tr>
				</c:if>


				<c:if test="${record.spzt=='3'}">
					<tr>
						<th align="center" style="width: 10%">
							部门经理:
						</th>
						<td style="width: 35%">
							<input type="text" name="executename" value="${dept.name}"
								maxLength="20" style="width: 95%" class="textBoxNoBorder" />
						</td>
						<th align="center" style="width: 10%">
							审批时间:
						</th>
						<td style="width: 35%">
							<input type="text" name="applyname" value="${dept.audit_date}"
								dataType="Integer" require="false"
								onkeyup="value=value.replace(/[^\d]/g,'')"
								style="ime-mode: disabled" style="width:95%"
								class="textBoxNoBorder" />
						</td>

					</tr>

					<tr>
						<th align="center" style="width: 10%">
							公司经理:
						</th>
						<td style="width: 35%">
							<input type="text" name="executename" value="${comp.name}"
								maxLength="20" style="width: 95%" class="textBoxNoBorder" />
						</td>
						<th align="center" style="width: 10%">
							审批时间:
						</th>
						<td style="width: 35%">
							<input type="text" name="applyname" value="${comp.audit_date}"
								dataType="Integer" require="false"
								onkeyup="value=value.replace(/[^\d]/g,'')"
								style="ime-mode: disabled" style="width:95%"
								class="textBoxNoBorder" />
						</td>

					</tr>
				</c:if>



				<tr>
					<th align="center" style="width: 10%">
						总金额(小写):
					</th>
					<td style="width: 35%">
						${record.xiaoxje}
					</td>
					<th align="center" style="width: 10%">
						总金额(大写):
					</th>
					<td style="width: 35%">
						${record.daxje}
					</td>

				</tr>

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

			<br />
			<div class="divMod2">
				<div class="divMod1">
					变更历史
				</div>
				<c:forEach items="${pmlist}" var="result" varStatus="status">
					<div class="titlediv" id="${status.index+1}"
						style="height: 20px; font-weight: bolder; background-color: orange; padding-left: 20px;">
						变更单号：${result.modify_ration_id}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 申请日期：${result.modify_date}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						审批状态：${result.state}

					</div>
					<table id="dest${status.index+1}" class="dataListTable"  width="100%" style="display: none;">
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
						<c:forEach items="${result.malist}" var="res"
							varStatus="status">
							<tr>
								<td>
									<c:out value="${res.index+1}" />
								</td>

								<td>
									<c:out value="${res.mname}" />
								</td>
								<td>
									<c:out value="${res.bname}" />
								</td>

								<td>
									<c:if test="${res.sqsl==res.sqsl1}">
      ${res.sqsl}
      </c:if>
									<c:if test="${res.sqsl!=res.sqsl1}">
      ${res.sqsl} <font color="red"
											style="font-weight: bolder; background-color: orange;">--></font> ${res.sqsl1}
      </c:if>

								</td>
								<td>
									<c:if test="${res.price==res.price1}">
      ${res.price}
      </c:if>
									<c:if test="${res.price!=res.price1}">
      ${res.price} <font color="red"
											style="font-weight: bolder; background-color: orange;">--></font> ${res.price1}
      </c:if>
								</td>
								<td>
									<c:if test="${res.gys==res.gys1}">
      ${res.gys}
      </c:if>
									<c:if test="${res.gys!=res.gys1}">
      ${res.gys} <font color="red"
											style="font-weight: bolder; background-color: orange;">--></font> ${res.gys1}
      </c:if>
								</td>
								


							</tr>
						</c:forEach>


					</table>

				</c:forEach>




			</div>



			<br />
			<div class="div-button">
				
				<input type="button" value="关闭" onClick="javascript:closeDG();" />
			</div>
		</form>
	</body>
</html>
