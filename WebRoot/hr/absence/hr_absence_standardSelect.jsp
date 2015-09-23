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
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>
	</head>
	<STYLE>
.dataListTable1 {
	BORDER-COLLAPSE: collapse;
	border: 1px solid #1771B7;
	line-height: 22px;
	margin: 2px auto;
}

.dataListTable1 td {
	border: 1px solid #c5ddf1;
	text-align: left;
}

.dataListTable1 th {
	background: #B8DDF8;
	border: 1px solid #bbbec3;
	height: 20px;
	text-align: center;
	font: bold 14px/ 28px simsun;
}

.dataListTable1 tr:hover {
	background: #f1f1f1;
	cursor: pointer;
}

.dataListTable1 .btnCol {
	width: 100px;
}
</STYLE>

	<body>
		<form id="searchForm"
			action="<%=request.getContextPath()%>/hr/Hr_absence_standard!searchList.do"
			method="post">
			<div class="divMod2">


			</div>


		</form>
		<div>
			<table class="dataListTable1" width="100%">
				<tr>


					<th>
						请假类型
					</th>
					<th>
						允许请假天数
					</th>

					<th>
						说明
					</th>

					<th class="btnCol">
						操作
					</th>
				</tr>
				<c:forEach items="${record}" var="result" varStatus="status">
					<tr>


						<td align="left">
							<c:if test="${result.code=='2'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
							<c:if test="${result.code=='3'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
							<c:out value="${result.absence_type}" />
						</td>
						<td>
							<c:out value="${result.days}" />
						</td>

						<td>
							<c:out value="${result.note}" />
						</td>

						<td class="btnCol">
							<a href="#"
								onclick="retSelect('${result.id}','${result.absence_type}');">选择</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		

	</body>

</html>