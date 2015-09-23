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
	<body>
		<form id="searchForm"
			action="<%=request.getContextPath()%>/gm/workreport/Gm_workreport!searchList.do"
			method="post">
			<div class="divMod2">

				<table width="99%" class="search_border">

				</table>
			</div>
			<div class="div-button">
				<a href="#"
					onclick="openDG('添加信息','<%=request.getContextPath()%>/gm/workreport/Gm_workreport!add.do','gm_workreportDtl')"
					class="link">添加</a>
				<input type="reset" value="重置" class="button" />

			</div>
			<div class="clear" style="height: 0px;"></div>
		</form>
		<div>
			<table class="dataListTable" width="100%">
				<tr>

					<th width="5%">
						序号
					</th>
					<th width="20%">
						周数
					</th>
					<th width="20%">
						汇报人
					</th>
					<th width="20%">
						填报日期
					</th>
					<th width="10%">
						是否审批
					</th>
					<th class="btnCol1" width="25%">
						操作
					</th>
				</tr>
				<c:forEach items="${record}" var="result" varStatus="status">
					<tr>

						<td>
							<c:out value="${status.index+1}" />
						</td>
						<td>
							<c:out value="${result.zhou}" />
						</td>
						<td>
							<c:out value="${result.name}" />
						</td>
						<td>
							<c:out value="${result.input_date}" />
						</td>
						<td>
							<c:out value="${result.state}" />
						</td>
						<td class="btnCol1">
							<a href="#"
								onclick="openDG('查看详细信息','<%=request.getContextPath()%>/gm/workreport/Gm_workreport!searchById.do?id=${result.id}','gm_workreportDtl')">查看</a>


							<c:if test="${result.isread=='0'}">
								<a href="#"
									onclick="openDG('修改详细信息','<%=request.getContextPath()%>/gm/workreport/Gm_workreport!edit.do?id=${result.id}','gm_workreportDtl')">修改</a>
								<a
									href="<%=request.getContextPath()%>/gm/workreport/Gm_workreport!delete.do?id=${result.id}"
									OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
								<a
									href="<%=request.getContextPath()%>/gm/workreport/Gm_workreport!sub.do?id=${result.id}"
									OnClick="javascript:return confirm('你确认要提交吗?');"><font color="red">提交</font></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	
	<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span> 页</span>
	<a onclick="changePage(1)" class="linkPage">首页</a><a onclick="changePage(${page.previousPageNumber})" class="linkPage">上一页</a>
	<a onclick="changePage(${page.nextPageNumber})" class="linkPage">下一页</a>
	<a onclick="changePage(${page.lastPageNumber})" class="linkPage">尾页</a>
	</div>
</body>
<script type="text/javascript">
	function changePage(page){
		if(page==undefined||page==null) page = $("#curPage").text();
		document.getElementById('searchForm').insertAdjacentHTML("beforeEnd",
	        "<input type=\"hidden\" name=\"pageno\" value=\""+page+"\"/>");
		document.getElementById('searchForm').submit();
	}
</script>
</html>