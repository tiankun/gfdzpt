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
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/Validator.js"></script>

		<script type="text/javascript">
function sub(id) {
	if(Validator.Validate(document.form1,3)){
		if(confirm("确定提交"))
		{
		document.form1.opinionid.value = id;
		document.form1.submit();
		}
	}
}
</script>

	</head>
	<body>
	<script type="text/javascript">${operationSign}</script>
		<form
			action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!save.do"
			method="post" name="form1">
			<input type="hidden" name="opinionid">

			<div class="div-button">
				
					<a href="#"
					onClick="sub('1');"
					class="link">提交</a>
			</div>
			
			
			<div class="divMod2">
				
				<table class="dataListTable" width="100%">
				    <tr>
						<th align="center" style="width: 15%">
							付款方式:
						</th>
						<td width="35%" align="left" >
							<select name="paytype" class="dropdownlist">
			<c:forEach items="${paytype}" var="paytype">
			<option value="${paytype.key}" <c:if test="${searchmap.paytype==paytype.key}">selected</c:if>>${paytype.value}</option>
			</c:forEach>
			</select>
						</td>
						<th align="center" style="width: 15%">
							付款金额:
						</th>
						<td align="left">
						<input type="text" name="editTotal" value="${money}"/>元
						</td>
						
						
					</tr>
					<tr>
					<th align="center" style="width: 15%">
							收款单位:
						</th>
						<td align="left">
						<input type="text" name="sk" value="${sk}"/>
						</td>
						
						<th align="center" style="width: 10%">
							备注:
						</th>
						<td colspan="3">
							<textarea rows="3" cols="70" name="note"></textarea>
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
							材料
						</th>
						<th>
							采购数量
						</th>
						<th>
							入库数量
						</th>
						
						<th>
							入库单价
						</th>
						
						<th>
							金额
						</th>
						
						<th>
							供应商
						</th>
						<th>
							项目
						</th>
						
						<th>
							单据数
						</th>
						


					</tr>
					<c:forEach items="${record}" var="result" varStatus="status">
						<tr>
                            <td>
                           
								<input type="checkbox" name="checkbox" checked="checked"  readonly="readonly"
									value="${result.ioid}" />
							
							</td>

							<td>
							  
								<c:out value="${status.index+1}" />
							</td>
							<td>
								<c:out value="${result.ration_apply_id}" />
							</td>
							<td>
								<c:out value="${result.maname}" />
							</td>
							<td>
								<c:out value="${result.sqsl}" />
							</td>
							<td>
								<c:out value="${result.ionum}" />
							</td>
							
							<td>
								<c:out value="${result.ioprice}" />
							</td>
							
							<td>
								<c:out value="${result.iomoney}" />
							</td>
							
							<td>
								<c:out value="${result.gys}" />
							</td>
							<td><c:if test=" ${ empty result.pname}">未关联项目</c:if>
								<c:if test="${not empty result.pname}">${result.pname}</c:if>
								
							</td>
							
							<td>
								<input type="text" name="danju" value="${result.danju}"  maxLength="3" dataType="Number" msg="请填写单据数！" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" style="width:95%" class="textBoxNoBorder"/>
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