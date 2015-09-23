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
			action="<%=request.getContextPath()%>/gm/workreport/Gm_workreport!auditlist.do"
			method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">

					<tr>
						<th>
							姓名:
						</th>
						<td>
							<input type="text" name="name" id="name"
								value="${searchmap.name}" class="textBox" />
							<input type="text" name="p_id" id="p_id" value="" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
								value="选择" />
						</td>
						<th>
							上报日期:
						</th>
						<td>
							<input type="text" name="attend_date1"
								value="${searchmap.attend_date1}" onfocus="WdatePicker()"
								style="ime-mode: disabled" style="width:40%" class="textBox" />
							至
							<input type="text" name="attend_date2"
								value="${searchmap.attend_date2}" onfocus="WdatePicker()"
								style="ime-mode: disabled" style="width:40%" class="textBox" />
						</td>
					</tr>

					<tr>
						<th>
							是否审批:
						</th>
						<td>
							<select name="isread" style="width: 60%">
							  <option value="">未选择</option>
							  <option value="2" <c:if test="${searchmap.isread=='2'}">selected</c:if>  >是</option>
							  <option value="1" <c:if test="${searchmap.isread=='1'}">selected</c:if>>否</option>
							</select>
						</td>
						<th></th>
						<td>

						</td>
					</tr>
				</table>
			</div>
			<div class="div-button">
				<input type="reset" value="重置" class="button" />
				<input type="submit" value="查询" class="button" />
			</div>
			<div class="clear" style="height: 0px;"></div>
		</form>
		<div>
			<table class="dataListTable" width="100%">
				<tr>

					<th width="10%">
						序号
					</th>
					<th width="15%">
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
						<td class="btnCol">
							<a href="#"
								onclick="openDG('查看','<%=request.getContextPath()%>/gm/workreport/Gm_workreport!audit.do?id=${result.id}','gm_workreportDtl')">
								<c:if test="${result.isread=='1'}">
									<font color="red">审批</font>
								</c:if> <c:if test="${result.isread=='2'}">查看</c:if> </a>



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