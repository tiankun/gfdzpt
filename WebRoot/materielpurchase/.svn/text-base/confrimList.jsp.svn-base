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
function sub() {

	var ids = "";
	var flag = true;

	var obj = document.form2.elements;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			if (obj[i].checked == true) {
				flag = false;
				ids = ids + ";" + obj[i].value;

				var id = obj[i].id;
				var maxkqr = "maxkqr" + id;
				var qr = "qr" + id;
				if (document.getElementById(qr).value == '') {
					alert("请填写确认数量");
					return false;
				}

				if (parseFloat(document.getElementById(qr).value) > parseFloat(document
						.getElementById(maxkqr).value)) {
					alert("确认数量大于采购数量");
					return false;
				}

			}

		}
	}

	if (flag) {
		alert("请至少选择一条记录！");
		return false;
	}

	if (confirm("确定提交?")) {

		
		
		document.form2.submit();
	}

}

function selectAll() {
	var obj = document.form2.elements;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			obj[i].checked = true;
		}
	}
}

function unselectAll() {
	var obj = document.form2.elements;
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
}

function query() {
	document.form1.submit();
}
</script>
	</head>
	<body>
		<form id="searchForm" name="form1"
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!purconfirm.do"
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
							申请单号:
						</th>
						<td>
							<input type="text" name="dh" style="width: 95%" class="textBox"
								value="${searchMap.dh}" />
						</td>
					</tr>
					<tr>
						<th>
							配给单号:
						</th>
						<td>
							<input type="text" name="ration_apply_id"
								value="${searchMap.ration_apply_id}" style="ime-mode: disabled"
								style="width:95%" class="textBox" />
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
			<input type="button" value="到货确认" class="button" onclick="sub();" />
		</div>
		<div class="clear" style="height: 0px;"></div>

      <form id="form2" name="form2" action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!saveconfirm.do"
			method="post">
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
						项目
					</th>
					<th>
						配给申请单
					</th>
					<th>
						采购执行人
					</th>
					<th>
						材料
					</th>

					<th>
						品牌
					</th>
					<th>
						采购数量
					</th>

					<th>
						已收货数量
					</th>

					<th>
						确认数量
					</th>
					
					<th>
						操作
					</th>


				</tr>
				<c:forEach items="${record}" var="result" varStatus="status">
					<tr>
						<td>
						 <c:if test="${result.spzt=='5'}">
							<input type="checkbox" name="checkbox" id="${status.index+1}"
								value="${result.id}" />
						 </c:if>
						</td>
						<td>
							<c:out value="${status.index+1}" />
						</td>
						<td>
							<c:out value="${result.proname}" />
						</td>
						<td>
							<c:out value="${result.bh}" />
						</td>
						<td>
							<c:out value="${result.ename}" />
						</td>
						<td>
							<c:out value="${result.maname}" />
						</td>

						<td>
							<c:out value="${result.bname}" />
						</td>
						<td>
							<c:out value="${result.sqsl}" />
						</td>

						<td>
							<c:out value="${result.consql}" />
						</td>

						<td>
							<input type="text" value="${result.kqr}" name="qr${result.id}"
								dataType="Double" onkeyup="if(isNaN(value))execCommand('undo')"
								onafterpaste="if(isNaN(value))execCommand('undo')"
								id="qr${status.index+1}" style="width: 50%">
							<input type="hidden" name="maxkqr" id="maxkqr${status.index+1}"
								value="${result.kqr}">
						</td>
						
						<td>
							<a href="#"
									onClick="openDG('取消采购','<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!cancelpur.do?id=${result.id}','gm_purchaseDtl')">取消采购</a>
						</td>



					</tr>
				</c:forEach>
			</table>
		</div>
		</form>
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