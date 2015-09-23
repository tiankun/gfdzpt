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
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/process/Hr_evaluation!searchList.do?id=${base_info.id}" method="post">
	<div class="divMod2">
	<div class="divMod1" align="center">${base_info.name}的转正考评信息</div>
		<table width="99%" class="search_border">
		
		</table>
	</div>
		<div class="div-button">
		</div>
		<div class="clear" style="height:0px;"></div>
		</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			<th>序号</th>
           <th>被考评人</th>
           <th>考评人</th>
           <th>岗位</th>
           <th>考评时间</th>
           <th>成绩得分</th>
           <th>成绩评分</th>
           <th>态度得分</th>
           <th>态度评分</th>
           <th>能力得分</th>
           <th>能力评分</th>
           <th>个人评分</th>
           <th>综合评分</th>
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td>${status.index+1}</td>
      <td><c:out value="${result.evd_named}" /></td>
      <td><c:out value="${result.ev_named}" /></td>
      <td><c:out value="${result.positiond}" /></td>
      <td><c:out value="${result.ev_time}" /></td>
      <td><c:out value="${result.p_performance}" /></td>
      <td><c:out value="${result.s_performance}" /></td>
      <td><c:out value="${result.p_attitude}" /></td>
      <td><c:out value="${result.s_attitude}" /></td>
      <td><c:out value="${result.p_capability}" /></td>
      <td><c:out value="${result.s_capability}" /></td>
      <td><c:out value="${result.s_personal}" /></td>
      <td><c:out value="${result.s_total}" /></td>
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/hr/process/Hr_evaluation!searchById.do?id=${result.id}','hr_evaluationDtl')">查看</a>
				</tr>
			</c:forEach>
		</table>
	</div>
	</br>
	<div class="divMod2">
	<div class="divMod1" align="center">${base_info.name}的考评统计</div>
		<table class="table_border" style="width:100%">
		<tr>
		<th align="center" style="width:10%">总分：</th>
		<td style="width:35%">${recordTotal.sumtotal}</td>
		<th align="center" style="width:10%">平均分：</th>
		<td style="width:35%">${recordTotal.avgtotal}</td>
		</tr>
		</table>
	</div>
	<div class="div-button">
	<c:if test="${showFlag!='show'}">
		<input type="button" value="关闭" onclick="javascript:closeDG();"/>
	</c:if>
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