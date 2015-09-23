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
	<form id="searchForm" action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!paysearchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th width="10%">申请时间:</th>
			<td colspan="3">
			<input type="text" value="${searchmap.begin_date}" name="begin_date" onfocus="WdatePicker()"   style="width:60" class="textBox"/>至
			<input type="text" value="${searchmap.end_date}" name="end_date" onfocus="WdatePicker()"   style="width:60" class="textBox"/>
			
			</td>
            
			</tr>
            <tr>
            <th>金额:</th>
			<td>
			<input type="text" value="${searchmap.money}" name="money"   style="width:95%" class="textBox"/></td>
           <th>审核状态:</th>
			<td>
			<select name="payforstate" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${payforstate}" var="payforstate">
			<option value="${payforstate.key}" <c:if test="${searchmap.payforstate==payforstate.key}">selected</c:if>>${payforstate.value}</option>
			</c:forEach>
			</select>
			</td>
            </tr>
		</table>
	</div>
		<div class="div-button">
		    <a href="#" onClick="openDG('添加信息','<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!addpayfor.do','fi_payforDtl')" class="link">添加</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
           <th>序号</th>
           <th>申请人</th>
           <th>申请时间</th>
           <th>总金额</th>
           <th>项目</th>
           <th>审批阶段</th>
           
           
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td>${status.index+1}</td>
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.odate}" /></td>
      <td><c:out value="${result.money}" /></td>
      <td><c:if test="${empty result.pname}">未关联项目</c:if>
      <c:if test="${not empty result.pname}"><c:out value="${result.pname}" /></c:if>
      </td>
       <td><c:out value="${payforstate[result.payforstate]}" /></td>
      
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!searchById.do?id=${result.id}','fi_payforDtl')">查看</a>
				
				<c:if test="${result.payforstate=='0'||result.payforstate=='8'}">
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!edit.do?id=${result.id}&type=modify','fi_payforDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!sub.do?id=${result.id}" OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
				
				</c:if>
				
				<c:if test="${result.payforstate=='3'}">
				<a href="#" onclick="openDG('分配付款','<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!edit.do?id=${result.id}&type=fenpei','fi_payforDtl')">分配付款</a>
				
				
				</c:if>
				<c:if test="${result.payforstate=='7'}">
				<a href="#" onclick="openDG('打印','<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!print.do?id=${result.id}','print')" 
				style="color: <c:if test="${result.print=='0'}">green</c:if>
							  <c:if test="${result.print!='0'}">red</c:if>">打印</a>
				</c:if>
				
				</td>
				</tr>
			</c:forEach>
		</table>
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