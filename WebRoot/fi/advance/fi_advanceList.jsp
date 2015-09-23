<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<form id="searchForm" action="<%=request.getContextPath()%>/fi/advance/Fi_advance!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
            <!-- 
			<th>收款单位:</th>
						<td style="width:35%"><input type="text" id="companyName" name="companyName"  class="textBox"/> 
						<input type="text" id="collecting_company" name="collecting_company" class="hidden" /> 
						<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','collecting_company','companyName');"
						value="选择" />
						</td>
			-->
			<th>申请时间:</th>
			<td>
			<input type="text" name="apply_date1" value="${searchMap.apply_date1}" onfocus="WdatePicker()" style="ime-mode:disabled" style="width:30%" class="textBox"/>
           	 到
           	<input type="text" name="apply_date2" value="${searchMap.apply_date2}" onfocus="WdatePicker()" style="ime-mode:disabled" style="width:30%" class="textBox"/>
            </td>
            <th>审批状态:</th>
			<td>
			<select name="appstate" class="dropdownlist" style="width:30%"><option value="">---</option>
			<c:forEach items="${advance_appstate}" var="advance_appstate">
			<option value="${advance_appstate.key}" <c:if test="${searchMap.appstate==advance_appstate.key}">selected</c:if>>${advance_appstate.value}</option>
			</c:forEach>
			</select>
            </tr>
            <tr>
			<th>申请原因:</th>
			<td>
			<select name="apply_reason" style="width:30%" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${apply_reason}" var="apply_reason">
			<option value="${apply_reason.key}" <c:if test="${searchMap.apply_reason==apply_reason.key}">selected</c:if>>${apply_reason.value}</option>
			</c:forEach>
			</select></td>
			<th>借支类型:</th>
			<td>
			<select name="advance_type" style="width:30%" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${advance_type}" var="advance_type">
			<option value="${advance_type.key}" <c:if test="${searchMap.advance_type==advance_type.key}">selected</c:if>>${advance_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			
			<th>部门:</th>
			<td>
			<input type="text" style="width:30%" name="branchname" id="branchname" value="${searchMap.branchname}" readonly="readonly" class="textBox"/>
			<input type="text" name="deptid" id="deptid" value="${searchMap.deptid}" class="hidden"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','deptid','branchname');" value="选择"/></td>
            <th>申请人:</th>
			<td>
			<input type="text" name="personname" value="${searchMap.personname}" style="width:30%" class="textBox"/></td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" style="color: red" onclick="openDG('添加信息','<%=request.getContextPath()%>/fi/advance/Fi_advance!add.do','fi_advance_applyDtl')" class="link">暂支申请</a>
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
           <th>收款单位</th>
           <th>申请时间</th>
           <th>申请原因</th>          
           <th>借支类型</th>
           <th>申请金额</th>          
           <th><font color="red">欠款</font></th>
           <th>审批状态</th>           
		        <th >操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td><c:out value="${status.count}" /></td>
      <td><c:out value="${result.person_name}" /></td>
      <td><c:out value="${result.dept_name}" /></td>
      <td>
      <c:if test="${not empty result.payeename}"><c:out value="${result.payeename}" /></c:if>
      <c:if test="${empty result.payeename}"><font color="red">未填写收款单位</font></c:if>
      </td>
      <td><c:out value="${result.apply_time}" /></td>
      <td><c:out value="${apply_reason[result.apply_reason]}" /></td>
      <td><c:out value="${advance_type[result.advance_type]}" /></td>
      <td>
      <font color="red"><fmt:formatNumber type="number" value="${result.apply_money}" pattern="#.##" minFractionDigits="2"/></font>
      </td>
      <td>
      <c:if test="${result.apply_money!=result.back_money}"><font color="red"><fmt:formatNumber type="number" value="${result.apply_money-result.back_money}" pattern="#.##" minFractionDigits="2"/></font></c:if>
      <c:if test="${result.apply_money==result.back_money}"><font color="green">已还清</font></c:if>
      </td>
      <td>
      <font color="#ff9955"><c:if test="${result.subflag=='0'}">待提交</c:if></font>
      <font color="red"><c:if test="${result.subflag=='1'&&!(result.appstate=='3'||result.appstate=='-1')}">${advance_appstate[result.appstate]}</c:if></font>
      <font color="green"><c:if test="${result.subflag=='1'&&result.appstate=='3'}">${advance_appstate[result.appstate]}</c:if></font>
      <font color="#5f809d"><c:if test="${result.subflag=='1'&&result.appstate=='-1'}">${advance_appstate[result.appstate]}</c:if></font>
      </td>
				<td width="12%">
								
				<%-- 
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/fi/advance/Fi_advance_apply!edit.do?id=${result.id}','fi_advance_applyDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/fi/advance/Fi_advance_apply!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a> --%>
				
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/fi/advance/Fi_advance!searchById.do?id=${result.id}','fi_advance_applyDtl')">查看</a>
				<c:if test="${(result.subflag=='0'||result.appstate=='-1')&&result.pid==user.base_info_id}">
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/fi/advance/Fi_advance!edit.do?id=${result.id}','fi_travelaccDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/fi/advance/Fi_advance!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				<a href="<%=request.getContextPath()%>/fi/advance/Fi_advance!sub.do?id=${result.id}" style="color: red" OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
				</c:if>
				<c:if test="${result.nextapproverid==user.base_info_id&&result.subflag=='1'}">
				<a href="#" onclick="openDG('审批','<%=request.getContextPath()%>/fi/advance/Fi_advance!appr.do?id=${result.id}','appr')" style="color: red">审批</a>
				</c:if>
				<c:if test="${result.appstate=='3'&&cpaStr!='0'&&result.apply_money!=result.back_money}">
				<a href="#" onclick="openDG('还款','<%=request.getContextPath()%>/fi/advance/Fi_advance!back.do?id=${result.id}','back')" style="color: red">还款</a>
				</c:if>
				<c:if test="${result.appstate=='3'}">
				<c:if test="${user.branchid=='6'}">
				<a href="#" onclick="openDG('作废','<%=request.getContextPath()%>/fi/advance/Fi_advance!invalid.do?id=${result.id}','zuofei')" style="color: red" OnClick="javascript:return confirm('你确认要作废吗?');">作废</a>
				</c:if>
				<a href="#" onclick="openDG('打印','<%=request.getContextPath()%>/fi/advance/Fi_advance!print.do?id=${result.id}','print')" 
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