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

function audit() {
	
	document.form1.submit();
}
</script>
	</head>
	<body>
		<form id="searchForm"
			action="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!kaoqinaudit.do"
			name="form1" method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">
					<tr>
						<th>
							考勤时间:
						</th>
						<td>
							<select name="kqdate" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${datelist}" var="dat">
									<option value="${dat.kqdate}"
										<c:if test="${kqdate==dat.kqdate}">selected</c:if>>
										${dat.kqdate}
									</option>
								</c:forEach>
							</select>
						</td>
						<th></th>
						<td>
						</td>
					</tr>

				</table>
			</div>
			<div class="div-button">
				<a target="_self" onclick="audit();" style="cursor: hand;"
					class="link">查询</a>

				<a href="#"
					onclick="openDG('审核考勤','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!saveaudit.do?kqdate=${kqdate}','hr_absenceDtl')"
					class="link">审核</a>


			</div>

		</form>

		<div>
			<table class="dataListTable" width="100%">
				<tr>
					<th width="5%">
						序号
					</th>
					<th>
						考核日期
					</th>
					<th>
						状态
					</th>
					<th class="btnCol">
						操作
					</th>
				</tr>
				<c:forEach items="${record}" var="result" varStatus="s">
					<tr>
						<td>
							${s.index+1}
						</td>

						<td>
							<c:out value="${result.khdate}" />
						</td>
						<td>
							<c:if test="${result.zhuangt==''||result.zhuangt==null||result.zhuangt=='0'}">未上报</c:if>
							<c:if test="${result.zhuangt=='1'}">待部门经理审核</c:if>
							<c:if test="${result.zhuangt=='2'}">审核通过</c:if>
							<c:if test="${result.zhuangt=='3'}">打回</c:if>
						</td>

						<td class="btnCol">
							<a href="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!audit1.do?kqdate=${result.khdate}" target="_blank">查看</a>
							<c:if test="${result.zhuangt=='1'}">
								<a href="#" onClick="openDG('审核考勤','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!saveaudit.do?kqdate=${result.khdate}','hr_absenceDtl')" >审核</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="PageUpDnDiv"
			style="PADDING-TOP: 5px; padding-bottom: 5px; text-align: center">
			<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span>
				页</span>
			<a onclick="changePage(1)" class="linkPage">首页</a><a
				onclick="changePage(${page.previousPageNumber})" class="linkPage">上一页</a>
			<a onclick="changePage(${page.nextPageNumber})" class="linkPage">下一页</a>
			<a onclick="changePage(${page.lastPageNumber})" class="linkPage">尾页</a>
		</div>
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

</html>