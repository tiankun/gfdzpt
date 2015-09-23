<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title></title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
		<script src="<%=request.getContextPath()%>/jscripts/jquery.js"
			type="text/javascript">
</script>
		<script
			src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>

		<script type="text/javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>

		<script type="text/javascript">


function query() {
	document.form1.submit();
}
</script>
	</head>
	<body>
		<form id="searchForm" name="form1"
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!delayquery.do"
			method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">
					<tr>
						<th>
							项目:
						</th>
						<td>
							<input type="text" id="proname" name="proname"
								value="${searchMap.proname}" class="textBox" />
							<input type="text" id="prj_id" name="prj_id" value=""
								dataType="Double" require="false" class="hidden" />
							<input type="button"
								onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','prj_id','proname');"
								value="选择" />
						</td>
						<th>
							配给单号:
						</th>
						<td>
							<input type="text" name="ration_apply_id"
								value="${searchMap.ration_apply_id}" style="ime-mode: disabled"
								style="width:95%" class="textBox" />
						</td>
					</tr>
					
					<tr>
						<th>
							状态:
						</th>
						<td>
							<select name="state" style="width: 60%;">
							  <option value="">未选择</option>
							  <option value="1" <c:if test="${searchMap.state=='1'}">selected</c:if>>延期</option>
							  <option value="0" <c:if test="${searchMap.state=='0'}">selected</c:if>>即将到期</option>
							</select>
						</td>
						<th>
							
						</th>
						<td>
							
						</td>
					</tr>

				</table>
			</div>
		</form>
		<div class="div-button">
			<input type="reset" value="重置" class="button" />
			<input type="button" value="查询" class="button" onclick="query();"/>
			
		</div>
		<div class="clear" style="height: 0px;"></div>

      
		<div>
			<table class="dataListTable" width="100%">
				<tr>
					<th>
						序号
					</th>
					<th>
						项目
					</th>
					<th>
						配给执行单
					</th>
					<th>
						采购执行人
					</th>
					<th>
						材料
					</th>

					
					<th>
						采购数量
					</th>

					<th>
						已收货数量
					</th>
					<th>
						要求到货时间
					</th>

					


				</tr>
				<c:forEach items="${record}" var="result" varStatus="status">
					<tr <c:if test="${result.chaoqi=='1'}">style="color: red;"</c:if>>
						
						<td>
							<c:out value="${status.index+1}" />
						</td>
						<td>
							<c:out value="${result.proname}" />
						</td>
						<td>
							<c:out value="${result.ration_apply_id}" />
						</td>
						<td>
							<c:out value="${result.ename}" />
						</td>
						<td>
							<c:out value="${result.maname}" />
						</td>

						
						<td>
							<c:out value="${result.sqsl}" />
						</td>

						<td>
							<c:out value="${result.consql}" />
						</td>
						<td>
							<c:out value="${result.yaoqdhrq}" />
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