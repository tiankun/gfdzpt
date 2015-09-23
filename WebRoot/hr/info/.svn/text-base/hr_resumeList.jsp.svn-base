<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>	
		<form id="searchForm" action="<%=request.getContextPath()%>/hr/info/Hr_resume!searchList.do?id=${base_info.id}" method="post">
		<div class="divMod2">
		<div class="divMod1" align="center">${base_info.name}的工作履历</div></div>
		<div class="div-button">
			<c:if test="${user.branchid==2&&showFlag!='show'}">
			<a href="#" onclick="openDGMax('添加信息','<%=request.getContextPath()%>/hr/info/Hr_resume!add.do?pid=${base_info.id}','hr_resumeDtl')" class="link">添加</a>
			</c:if>
		</div>
		<div class="clear" style="height:0px;"></div>
		</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			<th>序号</th>
           <th>单位</th>
           <th>部门</th>
           <th>职务</th>
           <th>开始时间</th>
           <th>结束时间</th>
           <th>证明人</th>
           <th>证明人电话</th>
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
			<td>${status.index+1}</td>	
      <td><c:out value="${result.dan_wei}" /></td>
      <td><c:out value="${result.bu_men}" /></td>
      <td><c:out value="${result.zhi_wu}" /></td>
      <td><c:out value="${result.start_time}" /></td>
      <td><c:out value="${result.end_time}" /></td>
      <td><c:out value="${result.zheng_ming}" /></td>
      <td><c:out value="${result.zhengm_dianhua}" /></td>
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/hr/info/Hr_resume!searchById.do?id=${result.id}','hr_resumeDtl')">查看</a>
				<c:if test="${user.branchid==2&&showFlag!='show'}">
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/hr/info/Hr_resume!edit.do?id=${result.id}&pid=${base_info.id}','hr_resumeDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/hr/info/Hr_resume!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				</c:if></td>
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