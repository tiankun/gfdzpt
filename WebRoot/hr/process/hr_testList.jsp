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
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/process/Hr_ptest!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1" align="center">${base_info.name}的初试信息</div>
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
			
           <th>希望待遇</th>
           <th>待遇底线</th>
           <th>应聘岗位</th>
           <th>优势特点</th>
           <th>到岗时间</th>
           <th>性格</th>
           <th>形象气质</th>
           <th>态度举止</th>
           <th>处理事务能力</th>
           <th>表达能力</th>
           <th>抗压能力</th>
           <th>吃苦能力</th>
           <th>结果</th>
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td><c:out value="${result.want_pay}" /></td>
      <td><c:out value="${result.pay_underline}" /></td>
      <td><c:out value="${result.position}" /></td>
      <td><c:out value="${result.superiority}" /></td>
      <td><c:out value="${result.pos_time}" /></td>
      <td><c:out value="${character[result.character]}" /></td>
      <td><c:out value="${form[result.form]}" /></td>
      <td><c:out value="${manner[result.manner]}" /></td>
      <td><c:out value="${result.deal_ab}" /></td>
      <td><c:out value="${result.exp_ab}" /></td>
      <td><c:out value="${result.anti_pres}" /></td>
      <td><c:out value="${result.willpower}" /></td>
      <td><c:out value="${result[result.result]}" /></td>
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/hr/process/Hr_ptest!searchById.do?id=${result.id}','hr_ptestDtl')">查看</a>
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/hr/process/Hr_ptest!edit.do?id=${result.id}','hr_ptestDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/hr/process/Hr_ptest!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a></td>
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