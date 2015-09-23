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
	<form id="searchForm" action="<%=request.getContextPath()%>/sysAdmin/sysuser!searchList.do?pageType=select" method="post">
		<table width="100%" class="border_bottom">
		
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">登录名称:</th>
			<td style="width:35%">
			<input type="text" name="log_name" value="${searchMap.log_name}" maxLength="50"  style="width:75%" class="textBox"/></td>
			<th align="center" class="myInputTitle" style="width:10%">用户实际名称:</th>
			<td style="width:35%">
			<input type="text" name="user_name" value="${searchMap.user_name}" maxLength="50"  style="width:75%" class="textBox"/></td>
            </tr>
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">所属机构:</th>
			<td style="width:35%">
			<input type="text" name="branchname" id="branchname" value="${searchMap.branchname}" class="textBox"/>
			<input type="text" name="branchid" id="branchid" value="${searchMap.branchid}" class="hidden"/>
			<input type="button" onclick="openSelect('选择组织机构','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','branchid','branchname');" value="选择"/>
			</td>
			<th align="center" class="myInputTitle" style="width:10%">状态:</th>
			<td style="width:35%">
			<select name="userstatus" class="ddlNoBorder"><option value="">未选择</option>
			<c:forEach items="${userstatus}" var="userstatus">
			<option value="${userstatus.key}" <c:if test="${searchMap.userstatus==userstatus.key}">selected</c:if>>${userstatus.value}</option>
			</c:forEach>
			</select></td>
            </tr>
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
			
           <th>登录名称</th>
           <th>用户实际名称</th>
           <th>创建日期</th>
           <th>有效期</th>
           <th>所属机构</th>
           <th>用户类别</th>
           <th>是否需要用锁登录</th>
           <th>删除标志</th>
		        <th width="200px">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td>${result.log_name}</td>
      <td><c:out value="${result.user_name}" /></td>
      <td><c:out value="${result.cdate}" /></td>
      <td><c:out value="${result.exp_date}" /></td>
      <td><c:out value="${result.branchname}" /></td>
      <td><c:out value="${usertype[result.usertype]}" /></td>
      <td><c:out value="${flag[result.flag]}" /></td>
      <td>${delflag[result.delflag]}</td>
				<td width="200px">
				<a href="#" onclick="retSelect('${result.id}','${result.user_name}');">选择</a></td>
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
