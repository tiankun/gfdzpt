<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<form id="searchForm" action="<%=request.getContextPath()%>/fi/travel/Fi_travelacc!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		<tr>
		<th>报销日期:</th>
		<td>
		<input type="text" name="reim_time1" value="${searchMap.reim_time1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
		至
		<input type="text" name="reim_time2" value="${searchMap.reim_time2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
		</td>
		<th>申请人:</th>
		<td>
		<input type="text" name="personname" value="${searchMap.personname}" style="width:30%" class="textBox"/>
		</td>
		</tr>
		<tr>
		<th>审批状态:</th>
		<td>
		<select name="appstate" class="dropdownlist" style="width:30%"><option value="">---</option>
		<c:forEach items="${travelacc_appstate}" var="travelacc_appstate">
		<option value="${travelacc_appstate.key}" <c:if test="${searchMap.appstate==travelacc_appstate.key}">selected</c:if>>${travelacc_appstate.value}</option>
		</c:forEach>
		</select>
		</tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" style="color: red" onclick="openDG('出差报账','<%=request.getContextPath()%>/fi/travel/Fi_travelacc!add.do','fi_travelaccDtl')" class="link">报账</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
           <th>部门</th>
           <th>出差人</th>
           <th>报销日期</th>
           <th>项目名称</th>
           <th>报销总金额</th>
           <th>审批状态</th>
           <!-- <th>发票标识</th> -->
		        <th class="100px">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td><c:out value="${result.dept_name}" /></td>
      <td><c:out value="${result.person_name}" /></td>
      <td><c:out value="${result.reim_time}" /></td>
      <td>
      <c:if test="${empty result.proj_name}"><font color="red">未关联项目</font></c:if>
      <c:if test="${not empty result.proj_name}"><font color="green"><c:out value="${result.proj_name}" /></font></c:if>
      </td>
      <td><font color="red">
      <fmt:formatNumber type="number" value="${result.totalcost}" pattern="#.##" minFractionDigits="2"/>
      </font></td>
      <td>
      <font color="#ff9955"><c:if test="${result.subflag=='0'}">待提交</c:if></font>
      <font color="red"><c:if test="${result.subflag=='1'&&!(result.appstate=='4'||result.appstate=='-1')}">${travelacc_appstate[result.appstate]}</c:if></font>
      <font color="green"><c:if test="${result.subflag=='1'&&result.appstate=='4'}">${travelacc_appstate[result.appstate]}</c:if></font>
      <font color="#5f809d"><c:if test="${result.subflag=='1'&&result.appstate=='-1'}">${travelacc_appstate[result.appstate]}</c:if></font>
      </td>
      <!-- <td><c:out value="${result.pyt}" /></td> -->
				<td class="100px">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/fi/travel/Fi_travelacc!showDtl.do?id=${result.id}','fi_travelaccDtl')">查看</a>
				<c:if test="${(result.subflag=='0'||result.appstate=='-1')&&result.p_id==user.base_info_id}">
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/fi/travel/Fi_travelacc!edit.do?id=${result.id}','fi_travelaccDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/fi/travel/Fi_travelacc!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				<a href="<%=request.getContextPath()%>/fi/travel/Fi_travelacc!sub.do?id=${result.id}" style="color: red" OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
				</c:if>
				<c:if test="${result.nextapproverid==user.base_info_id&&result.subflag=='1'}">
				<a href="#" onclick="openDG('审批','<%=request.getContextPath()%>/fi/travel/Fi_travelacc!appr.do?id=${result.id}','appr')" style="color: red">审批</a>
				</c:if>
				<c:if test="${result.appstate=='4'}">
				<c:if test="${user.branchid=='6'}">
				<a href="#" onclick="openDG('作废','<%=request.getContextPath()%>/fi/travel/Fi_travelacc!invalid.do?id=${result.id}','zuofei')" style="color: red" OnClick="javascript:return confirm('你确认要作废吗?');">作废</a>
				</c:if>
				<a href="#" onclick="openDG('打印','<%=request.getContextPath()%>/fi/travel/Fi_travelacc!print.do?id=${result.id}','print')" 
				style="color: <c:if test="${result.print=='0'}">green</c:if>
							  <c:if test="${result.print!='0'}">red</c:if>">打印</a>
				</c:if>
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