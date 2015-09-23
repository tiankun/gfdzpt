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
	<form id="searchForm" action="<%=request.getContextPath()%>/gm/travel/Gm_travel!travelList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>申请日期:</th>
			<td>
			<input type="text" name="apptime1" value="${searchMap.apptime1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			至
			<input type="text" name="apptime2" value="${searchMap.apptime2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
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
			<c:forEach items="${travel_appstate}" var="travel_appstate">
			<option value="${travel_appstate.key}" <c:if test="${searchMap.appstate==travel_appstate.key}">selected</c:if>>${travel_appstate.value}</option>
			</c:forEach>
			</select>
			</tr>
		</table>
	</div>
		<div class="div-button">
			<c:if test="${user.duty_id!='3'}">
			<a href="#" style="color: red" onclick="openDG('添加信息','<%=request.getContextPath()%>/gm/travel/Gm_travel!addDtl.do','travelDtl');" class="link">填写出差申请</a>
			</c:if>
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
           <th>部门</th>
           <th>开始时间</th>
           <th>结束时间</th>
           <th>申请时间</th>
           <th>审批状态</th>
		   <th>操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
			<td>${status.index+1}</td>	
      <td><c:out value="${result.person_name}" /></td>
      <td><c:out value="${result.dept_name}" /></td>
      <td><c:out value="${result.b_time}" /></td>
      <td><c:out value="${result.e_time}" /></td>
      <td><c:out value="${result.applicate_time}" /></td>
      <td>
      <font color="#ff9955"><c:if test="${result.subflag=='0'}">待提交</c:if></font>
      <font color="red"><c:if test="${result.subflag=='1'&&!(result.appstate=='2'||result.appstate=='-1')}">${travel_appstate[result.appstate]}</c:if></font>
      <font color="green"><c:if test="${result.subflag=='1'&&result.appstate=='2'}">${travel_appstate[result.appstate]}</c:if></font>
      <font color="#5f809d"><c:if test="${result.subflag=='1'&&result.appstate=='-1'}">${travel_appstate[result.appstate]}</c:if></font>
      </td>
		<td width="15%">
		<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/gm/travel/Gm_travel!showDtl.do?id=${result.id}','travelDtl')">查看</a>
		<c:if test="${(result.subflag=='0'||result.appstate=='-1')&&result.personid==user.base_info_id}">
		<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/gm/travel/Gm_travel!editDtl.do?id=${result.id}','travelDtl')">修改</a>
		<a href="<%=request.getContextPath()%>/gm/travel/Gm_travel!delDtl.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
		<a href="<%=request.getContextPath()%>/gm/travel/Gm_travel!sub.do?id=${result.id}" style="color: red" OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
		</c:if>
		<c:if test="${result.nextapproverid==user.base_info_id&&result.subflag=='1'}">
		<a href="#" onclick="openDG('审批','<%=request.getContextPath()%>/gm/travel/Gm_travel!appr.do?id=${result.id}','appr')" style="color: red">审批</a>
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