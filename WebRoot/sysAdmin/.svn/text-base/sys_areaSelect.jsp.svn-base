<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/CalendarWebControl.js"></script>

</head>
<body>	
	<form id="searchForm" action="<%=request.getContextPath()%>/sysAdmin/Sys_area!searchList.do?pageType=select" method="post">
		<table width="100%" class="border_bottom">
		
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">名称:</th>
			<td style="width:35%">
			<input type="text" name="name" style="width:95%" class="textBox"/></td>
			<th align="center" class="myInputTitle" style="width:10%">区位码:</th>
			<td style="width:35%">
			<input type="text" name="id_code" style="width:95%" class="textBox"/></td>
            </tr>
            <!-- <tr>
			<th align="center" class="myInputTitle" style="width:10%">区域级别:</th>
			<td style="width:35%">
			<select name="arealevel" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${arealevel}" var="arealevel">
			<option value="${arealevel.key}" >${arealevel.value}</option>
			</c:forEach>
			</select></td><th></th><td></td>
            </tr> -->
		</table>
		<div class="div-button">
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
           <th>名称</th>
           <th>区位码</th>
           <th>全称</th>
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.id_code}" /></td>
      <td><c:out value="${result.fullname}" /></td>
				<td class="btnCol">
				<a href="#" onclick="retSelect('${result.id}','${result.name}');">选择</a>
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
