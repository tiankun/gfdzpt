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
	<form action="<%=request.getContextPath()%>/customer/Customer!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>客户名称:</th>
			<td>
			<input type="text" value="${searchMap.name}" name="name" style="width:95%" class="textBox"/></td>
			<th>联系人:</th>
			<td>
			<input type="text" value="${searchMap.lxr}" name="lxr" style="width:95%" class="textBox"/></td>
            </tr>
		</table>
	</div>
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
           <th>客户名称</th>
           <th>地址</th>
           <th>联系人</th>
           <th>联系电话</th>
           <th>客户等级</th>
           
          
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
	  <td><c:out value="${status.index+1}" /></td>		
      
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.address}" /></td>
      <td><c:out value="${result.lxr}" /></td>
      <td><c:out value="${result.lxdh}" /></td>
      <td><c:out value="${khdj[result.khdj]}" /></td>
      
				<td class="btnCol">
				<a href="#" onclick="retSelect('${result.id}','${result.name}');">选择</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	<span>共 ${page.lastPageNumber} 页</span>
	<span>第 ${page.thisPageNumber} 页</span>
	<a href="<%=request.getContextPath()%>/customer/Customer!searchList.do?pageno=1" class="linkPage">首页</a>
	<a href="<%=request.getContextPath()%>/customer/Customer!searchList.do?pageno=${page.previousPageNumber}" class="linkPage">上一页</a>
	<a href="<%=request.getContextPath()%>/customer/Customer!searchList.do?pageno=${page.nextPageNumber}" class="linkPage">下一页</a>
	<a href="<%=request.getContextPath()%>/customer/Customer!searchList.do?pageno=${page.lastPageNumber}" class="linkPage">尾页</a>
	</div>
</body>
</html>