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

<script type="text/javascript">
function sub()
{
	document.form1.submit();
}
</script>


<body>	
	<form id="searchForm" action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!lookdetail.do" method="post" name="form1">
	<input type="hidden" name="materiel_id" value="${searchMap.materiel_id }"/>
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            
            <tr>
            <th>配给单:</th>
			<td>
			<input type="text" id="ration_apply_id" name="ration_apply_id" value="${searchMap.ration_apply_id}"  class="textBox"/>
			
            </td>
            
			<th></th>
			<td>
			
			
			</td>
			</tr>
			
			
		</table>
	</div>
		<div class="div-button">
			<input type="reset"	value="重置" class="button"/>
			<input type="button" value="查询" class="button" onclick="sub();"/>
		</div>
		<div class="clear" style="height:0px;"></div>
	
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
			<th width="40">序号</th>
		<th>单号</th>
		<th>配给单</th>
           <th>材料名称</th>
           <th>品牌</th>
           <th width="60">类型</th>
           
           <th width="60">数量</th>
           <th width="60">入库单价</th>
           
           <th width="60">金额</th>
           
           <th width="60">操作时间</th>
           <th width="60">操作人</th>
           <th >备注</th>
           
		        
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>	
		 <td><c:out value="${status.index+1}" /></td>	
		<td><c:out value="${result.dh}" /></td>
		<td><c:out value="${result.ration_apply_id}" /></td> 
		 
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.brname}" /></td>
      
      <td><c:out value="${result.type}" /></td>
      <td><c:out value="${result.num}" /></td>
      
      <td><c:out value="${result.price}" /></td>
      <td><c:out value="${result.money}" /></td>
      
      
      <td><c:out value="${result.odate}" /></td>
      <td><c:out value="${result.pname}" /></td>
      <td><c:out value="${result.note}" /></td>
      
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