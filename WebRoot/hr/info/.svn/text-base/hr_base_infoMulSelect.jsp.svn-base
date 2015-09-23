<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/CalendarWebControl.js"></script>
</head>
<script type="text/javascript">

var parId = $(window.parent.document).find("#selectedIDS");
var ids ='';

function view(id) {
	document.form1.action = '<%=request.getContextPath()%>/renshi/Base_info!getbaseinfo.do?id=' + id;
	document.form1.target = '_self';
	document.form1.submit();
}
function select(id,name,depart){
	var pdiv = $(window.parent.document).find("#show");
	var htm = '<li><label id="lname" class="lname">' +  name + '</label><label id="lopt" class="lopt"><img src="<%=request.getContextPath()%>/image/cross.png" onclick="delli(this)"/></label></li>';
<%-- 	var htm = '<li><label id="lname" class="lname">' + 'ID:' + id + ' 姓名:' + name + '</label><label id="lopt" class="lopt"><img src="<%=request.getContextPath()%>/image/cross.png" onclick="delli(this)"/></label></li>'; --%>
	var pul = $.trim($(pdiv).find("ul").text());
	if(pul.indexOf($.trim($(htm).text())) >= 0){
		alert("已经添加过了！");
		return;
	}
		ids = $(parId).val();
		ids+=id+",";	
		$(parId).val(ids);
		$(pdiv).find("ul").append(htm);	
	
}
/*设置父窗口中iframe的高度*/
function frameHeight(){  
       var newHeight = document.body.scrollHeight + 20 + "px";
       window.parent.document.getElementById("framebody").style.height = newHeight;
       //以上firefox通过，但是ie6必须加上下面这句，不然iframe高度是改了，但是可见区域没有改
       window.parent.document.getElementById("framebody").style.height = newHeight;
} 
window.onload=frameHeight;
</script>
<body style="border:0" >	
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=mulSelect" method="post" name="form1" id="form1">
		<table width="100%" class="border_bottom">
            <tr>
			<th align="center" class="myInputTitle" style="width:10%">姓名:</th>
			<td style="width:35%">
			<input type="text" name="name" value="${searchMap.name}" maxLength="50"  style="width:75%" class="textBox"/></td>
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
			<th>序号</th>
		   <th>姓名</th>
           <th>性别</th>
           <th>部门</th>
     <!--       <th>职务</th>
           <th>工作年限</th>
           <th>联系电话</th>
           <th>进入公司时间</th>
           <th>工作状态</th> -->
           <th class="btnCol">操作</th>
		   </tr>
			<c:forEach items="${record}" var="result" varStatus="status" >
	<tr>	
      <td>${status.index+1}</td>
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${sex[result.sex]}" /></td>
      <td><c:out value="${mrbranch[result.dept_id]}" /></td>
      <%-- <td><c:out value="${duty_id[result.duty_id]}" /></td>
      
      <td><c:out value="${result.work_year}" /></td>
     
      <td><c:out value="${result.PHONE}" /></td>;
     
      <td><c:out value="${result.jr_time}" /></td>
      <td><c:out value="${hr_type[result.hr_type]}" /></td>
      --%>
	  <td class="btnCol">
			<a href="#" onclick="select('${result.id}','${result.name}','${dept_id[result.dept_id]}');">选择</a>
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