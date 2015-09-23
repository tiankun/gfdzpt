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
	<form id="searchForm" action="<%=request.getContextPath()%>/fi/financial/Fi_financial_account!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
            <tr>
            <th>申请人:</th>
			<td>
			<input type="text" name="personname" id="personname" value="${searchMap.personname}" class="textBox"/>
			</td>
            <!-- 
			<th>项目名称:</th>
			<td>
			<input type="text" name="name" id="name" value="" readonly="readonly" class="textBox"/>
			<input type="text" name="projectid" id="projectid" value="" class="hidden"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/customer/customer!searchList.do?pageType=select','projectid','name');" value="选择"/></td>
			 -->
			<th>支付方式:</th>
			<td>
			<select name="advance_type" class="dropdownlist" style="width:30%"><option value="">---</option>
			<c:forEach items="${advance_type}" var="advance_type">
			<option value="${advance_type.key}" <c:if test="${searchMap.advance_type==advance_type.key}">selected</c:if>>${advance_type.value}</option>
			</c:forEach>
			</select></td>
			</tr>
			<tr>
			<th>部门:</th>
			<td>
			<input type="text" name="branchname" id="branchname" value="${searchMap.branchname}" readonly="readonly" class="textBox"/>
			<input type="text" name="departid" id="departid" value="${searchMap.departid}" class="hidden"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','departid','branchname');" value="选择"/></td>
			<th>审批状态:</th>
			<td>
			<select name="appstate" class="dropdownlist" style="width:30%"><option value="">---</option>
			<c:forEach items="${fiacc_state}" var="fiacc_state">
			<option value="${fiacc_state.key}" <c:if test="${searchMap.appstate==fiacc_state.key}">selected</c:if>>${fiacc_state.value}</option>
			</c:forEach>
			</select>
			</tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" style="color: red" onclick="openDG('添加信息','<%=request.getContextPath()%>/fi/financial/Fi_financial_account!add.do','fi_financial_accountDtl')" class="link">财务报账</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
		   <th>申请人</th>
           <th>申请时间</th>
           <th>申请金额</th>
           <th>支付方式</th>
           <th>项目名称</th>
           <th>审批状态</th>
		        <th>操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
	  <td><c:out value="${result.pname}" /></td>		
      <td><c:out value="${result.apply_date}" /></td>
      <td><c:out value="${result.apply_money}" /></td>
      <td><c:out value="${advance_type[result.advance_type]}" /></td>
      <td style="color: red"><c:if test="${empty result.pro_name}">未关联项目</c:if>
      <c:if test="${not empty result.pro_name}"><c:out value="${result.pro_name}" /></c:if>
      </td>
      <td>
      <font color="#ff9955"><c:if test="${result.subflag=='0'}">待提交</c:if></font>
      <font color="red"><c:if test="${result.subflag=='1'&&!(result.appstate=='4'||result.appstate=='-1')}">${fiacc_state[result.appstate]}</c:if></font>
      <font color="green"><c:if test="${result.subflag=='1'&&result.appstate=='4'}">${fiacc_state[result.appstate]}</c:if></font>
      <font color="#5f809d"><c:if test="${result.subflag=='1'&&result.appstate=='-1'}">${fiacc_state[result.appstate]}</c:if></font>
      </td>
				<td width="12%">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/fi/financial/Fi_financial_account!searchById.do?id=${result.id}','fi_financial_accountDtl')">查看</a>
				<c:if test="${(result.subflag=='0'||result.appstate=='-1')&&result.personid==user.base_info_id}">
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/fi/financial/Fi_financial_account!edit.do?id=${result.id}','fi_financial_accountDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/fi/financial/Fi_financial_account!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				<a href="<%=request.getContextPath()%>/fi/financial/Fi_financial_account!sub.do?id=${result.id}" style="color: red" OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
				</c:if>
				<c:if test="${result.nextapproverid==user.base_info_id&&result.subflag=='1'}">
				<a href="#" onclick="openDG('审批','<%=request.getContextPath()%>/fi/financial/Fi_financial_account!appr.do?id=${result.id}','appr')" style="color: red">审批</a>
				</c:if>
				<c:if test="${result.appstate=='4' }">
				<c:if test="${user.branchid=='6'}">
				<a href="#" onclick="openDG('作废','<%=request.getContextPath()%>/fi/financial/Fi_financial_account!invalid.do?id=${result.id}','zuofei')" style="color: red" OnClick="javascript:return confirm('你确认要作废吗?');">作废</a>
				</c:if>
				<a href="#" onclick="openDG('打印','<%=request.getContextPath()%>/fi/financial/Fi_financial_account!print.do?id=${result.id}','print')" 
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