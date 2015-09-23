<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<form id="searchForm" action="<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
			<tr>
				<th>
					申请时间:
				</th>
				<td>
					<input type="text" name="input_date1"
						value="${searchMap.input_date1}" dataType="Date" require="false"
						onfocus="WdatePicker()" style="ime-mode: disabled"
						style="width:30%" class="textBox" />
					至
					<input type="text" name="input_date2"
						value="${searchMap.input_date2}" dataType="Date" require="false"
						onfocus="WdatePicker()" style="ime-mode: disabled"
						style="width:30%" class="textBox" />
				</td>
				<th>
					审批状态：
				</th>
				<td>
					<select name="appstate" class="dropdownlist">
						<option value="">
							---
						</option>
						<c:forEach items="${exp_state}" var="exp_state">
							<option value="${exp_state.key}" <c:if test="${searchMap.appstate==exp_state.key}">selected</c:if>>
								${exp_state.value}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<c:if test="${user.duty=='3'||user.branchid=='6'||user.branchid=='3'}">
				<th align="center" class="myInputTitle" style="width: 10%">
					部门:
				</th>
				<td style="width: 35%">
					<input type="text" name="branchname" id="branchname"
						value="${branchname}" class="textBox" />
					<input type="text" name="dept_id" id="dept_id"
						 class="hidden" />
					<input type="button"
						onclick="openSelect('选择组织机构','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dept_id','branchname');"
						value="选择" />
				</td>
				</c:if>
				<c:if test="${user.duty_id!='1'}">
				<th>
				 姓名：
				</th>
				<td>
					<input type="text" name="name" id="name" value=""  class="textBox" />
					<input type="text" name="pid" id="pid" value="" class="hidden" />
					<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','pid','name');" value="选择" />
				</td>
				</c:if>
			</tr>
		</table>
	</div>
		<div class="div-button">
			<c:if test="${user.duty_id!='3'}">
			<a href="#" style="color: red" onclick="openDG('添加信息','<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!add.do','fi_expenses_planDtl')" class="link">开支申请</a>
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
           <th>申请时间</th>
           <th>预支时间</th>
           <th>申请金额</th>
           <th>综合办购买</th>
           <th>审批状态</th>
           <th>批准金额</th>
		        <th>操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				<td>${status.index+1}</td>
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.dept_name}" /></td>
      <td><c:out value="${result.apply_time}" /></td>
      <td><c:out value="${result.advance_time}" /></td>
      <td><c:out value="${result.total_sum}" /></td>
      <td><c:out value="${buy[result.buy]}" /></td>
      <td>
      <font color="#ff9955"><c:if test="${result.subflag=='0'}">待提交</c:if></font>
      <font color="red"><c:if test="${result.subflag=='1'&&!(result.appstate=='2'||result.appstate=='-1')}">${exp_state[result.appstate]}</c:if></font>
      <font color="green"><c:if test="${result.subflag=='1'&&result.appstate=='2'}">${exp_state[result.appstate]}</c:if></font>
      <font color="#5f809d"><c:if test="${result.subflag=='1'&&result.appstate=='-1'}">${exp_state[result.appstate]}</c:if></font>
      </td>
      <td>
      <c:if test="${empty result.final_sum}"><font color="red">未批准</font></c:if>
      <c:if test="${not empty result.final_sum}"><font color="green">
      <c:out value="${result.final_sum}" /></font></c:if>
      </td>
				<td width="12%">
				<c:if test="${result.nextapproverid==user.base_info_id&&result.subflag=='1'}">
				<a href="#" onclick="openDG('审批','<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!approval.do?id=${result.id}','fi_exppensesApproval')"><font color='red'>审批</font></a>
				</c:if>
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!searchById.do?id=${result.id}','fi_expenses_planDtl')">查看</a>
				<c:if test="${(result.subflag=='0'||result.appstate=='-1')&&result.personid==user.base_info_id}">
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!edit.do?id=${result.id}','fi_expenses_planDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				<a href="<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!sub.do?id=${result.id}" style="color: red" OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
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