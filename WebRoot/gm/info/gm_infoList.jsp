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
<STYLE type=text/css>

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}

a:active {
	color: black;
	text-decoration: none;
}


</STYLE>
	</head>
	<body>
		<form id="searchForm"
			action="<%=request.getContextPath()%>/gm/info/Gm_info!searchList.do"
			method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">

					<tr>
						<th>
							标题:
						</th>
						<td>
							<input type="text" name="title" style="width: 95%" value="${searchMap.title}"
								class="textBox" />
						</td>
						<th>
							录入时间:
						</th>
						<td>
							<input type="text" name="input_date" onfocus="WdatePicker()" value="${searchMap.input_date}"
								style="ime-mode: disabled" style="width:95%" class="textBox" />
						</td>
					</tr>
				</table>
			</div>
			<div class="div-button">
			   
				<c:if test="${role==1}">
				<a href="#"
					onclick="openDG('添加信息','<%=request.getContextPath()%>/gm/info/Gm_info!add.do','gm_infoDtl')"
					class="link">添加</a>
				</c:if>
				
				<input type="reset" value="重置" class="button" />
				<input type="submit" value="查询" class="button" />
			</div>
			<div class="clear" style="height: 0px;"></div>
		</form>
		<div>
			<table class="dataListTable" width="100%">
				<tr>

					<th>
						序号
					</th>
					<th>
						标题
					</th>

					<th>
						录入时间
					</th>
					<th>
						所属模块
					</th>

					<th class="btnCol">
						操作
					</th>
				</tr>
				<c:forEach items="${record}" var="result" varStatus="status">
					<tr>

						<td>
							<c:out value="${status.index+1}" />
						</td>
						<td>
							<a href="<%=request.getContextPath()%>/gm/info/Gm_info!view.do?id=${result.id}" target="_blank" title="${result.title}">
							<c:out value="${result.title}" />
							</a>
						</td>

						<td>
							<c:out value="${result.input_date}" />
						</td>
						<td>
							<c:out value="${result.moduleid}" />
						</td>

						<td class="btnCol">
							<a href="<%=request.getContextPath()%>/gm/info/Gm_info!view.do?id=${result.id}"
								target="_blank">查看</a>
								
							<a href="#"
								onclick="openDG('修改详细信息','<%=request.getContextPath()%>/gm/info/Gm_info!edit.do?id=${result.id}','gm_infoDtl')">修改</a>
							<a
								href="<%=request.getContextPath()%>/gm/info/Gm_info!delete.do?id=${result.id}"
								OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
								
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