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
	</head>




	<body>
		<form id="searchForm"
			action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor_audit!lookpay.do"
			method="post" name="form1">
			<input type="hidden" name="id" value="${id }" />
			<div class="divMod2">

				<table width="99%" class="search_border">
					<tr>
						<th>
							供货商:
						</th>
						<td>
							${gongys}

						</td>

						<th>

						</th>
						<td>


						</td>
					</tr>

					<tr>
						<th>
							配给单:
						</th>
						<td>
							<input type="text" id="ration_apply_id" name="ration_apply_id"
								value="${searchMap.ration_apply_id}" class="textBox" />

						</td>

						<th>
							支付情况
						</th>
						<td>
							<select name="ispay" style="width: 60%">
								<option value="">
									未选择
								</option>
								<option value="0"
									<c:if test="${searchMap.ispay=='0'}">selected</c:if>>
									未付
								</option>
								<option value="1"
									<c:if test="${searchMap.ispay=='1'}">selected</c:if>>
									支付中
								</option>
								<option value="2"
									<c:if test="${searchMap.ispay=='2'}">selected</c:if>>
									已付
								</option>

							</select>

						</td>
					</tr>

				</table>
			</div>
			<div class="div-button">
				<input type="reset" value="重置" class="button" />
				<input type="submit" value="查询" class="button" />
			</div>
			<div class="clear" style="height: 0px;"></div>

			<div>
				<div class="divMod1">
					与材料相关
				</div>
				<table class="dataListTable" width="100%">
					<tr>

						<th width="5%">
							序号
						</th>
						<th width="15%">
							配给单
						</th>
						<th>
							项目
						</th>
						<th>
							材料
						</th>
						<th width="60">
							数量
						</th>
						<th width="60">
							入库单价
						</th>

						<th width="60">
							金额
						</th>

						<th width="60">
							是否付款
						</th>


					</tr>
					<c:forEach items="${record}" var="result" varStatus="status">
						<tr>
							<td>
								<c:out value="${status.index+1}" />
							</td>
							<td>
								<c:out value="${result.ration_apply_id}" />
							</td>

							<td>
							<%-- 	<c:out value="${result.proname}" /> --%>
								<td><c:if test=" ${ empty result.proname}">未关联项目</c:if>
								<c:if test="${not empty result.proname}">${result.proname}</c:if>
								
							</td>
							</td>

							<td>
								<c:out value="${result.maname}" />
							</td>




							<td>
								<c:out value="${result.num}" />
							</td>

							<td>
								<c:out value="${result.price}" />
							</td>
							<td>
								<c:out value="${result.money}" />
							</td>
							<td>
								<c:if test="${result.fk!='已付'}">
									<font color="red">${result.fk}</font>
								</c:if>
								<c:if test="${result.fk=='已付'}">
									<c:out value="${result.fk}" />
								</c:if>
							</td>

						</tr>
					</c:forEach>
				</table>
				<div id="PageUpDnDiv"
					style="PADDING-TOP: 5px; padding-bottom: 5px; text-align: center">

					<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span>
						页</span>
					<a onclick="changePage(1)" class="linkPage">首页</a><a
						onclick="changePage(${page.previousPageNumber})" class="linkPage">上一页</a>
					<a onclick="changePage(${page.nextPageNumber})" class="linkPage">下一页</a>
					<a onclick="changePage(${page.lastPageNumber})" class="linkPage">尾页</a>
				</div>


				<br />
				<div class="divMod1">
					与材料无关
				</div>
				<table class="dataListTable" width="100%">
					<tr>

						<th width="5%">
							序号
						</th>
						<th width="15%">
							申请事由
						</th>
						<th>
							金额
						</th>
						<th>
							附件数
						</th>
						<th>
							申请时间
						</th>


						<th>
							状态
						</th>


					</tr>
					<c:forEach items="${record1}" var="result" varStatus="status">
						<tr>
							<td>
								<c:out value="${status.index+1}" />
							</td>
							<td>
								<c:out value="${result.applyreason}" />
							</td>

							<td>
								<c:out value="${result.money}" />
							</td>

							<td>
								<c:out value="${result.fujnum}" />
							</td>




							<td>
								<c:out value="${result.odate}" />
							</td>

							
							<td><c:out value="${payforstate[result.payforstate]}" /></td>

						</tr>
					</c:forEach>
				</table>
			</div>
			<div id="PageUpDnDiv"
				style="PADDING-TOP: 5px; padding-bottom: 5px; text-align: center">

				<span>共 ${page1.lastPageNumber} 页/第 </span><span id="curPage">${page1.thisPageNumber}</span><span>
					页</span>
				<a onclick="changePage1(1)" class="linkPage">首页</a><a
					onclick="changePage1(${page1.previousPageNumber})" class="linkPage">上一页</a>
				<a onclick="changePage1(${page1.nextPageNumber})" class="linkPage">下一页</a>
				<a onclick="changePage1(${page1.lastPageNumber})" class="linkPage">尾页</a>
			</div>
		</form>
	</body>
	<script type="text/javascript">
function changePage(page) {
	if (page == undefined || page == null)
		page = $("#curPage").text();
	document.getElementById('searchForm').insertAdjacentHTML("beforeEnd",
			"<input type=\"hidden\" name=\"pageno\" value=\"" + page + "\"/>");
	document.getElementById('searchForm').submit();
}
</script>

	<script type="text/javascript">
function changePage1(page) {
	if (page == undefined || page == null)
		page = $("#curPage").text();
	document.getElementById('searchForm').insertAdjacentHTML("beforeEnd",
			"<input type=\"hidden\" name=\"pageno1\" value=\"" + page + "\"/>");
	document.getElementById('searchForm').submit();
}
</script>
</html>