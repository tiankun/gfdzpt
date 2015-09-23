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
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/info/Hr_base_info!entryList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>姓名:</th>
			<td>
			<input type="text" name="name" value="${searchMap.name}" style="width:95%" class="textBox"/></td>
			<th>岗位:</th>
			<td><input type="text" name="position" value="${searchMap.position}" style="width:95%" class="textBox"/></td>
            </tr>
            <!-- 
            <tr>
			<th>出生日期:</th>
			<td>
			<input type="text" name="birthday" onfocus="WdatePicker()" value="${searchMap.birthday}" style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
            </tr>
             -->
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
           <th>姓名</th>
           <th>证件号码</th>
           <th>性别</th>
           <th>部门</th>
           <th>岗位</th>
           <th>出生日期</th>
           <th>移动电话</th>
           <th>试用工资</th>
           <th>审核状态</th>
		        <th>操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
			<td>${status.index+1}</td>	
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.card_num}" /></td>
      <td><c:out value="${sex[result.sex]}" /></td>
      <td><c:out value="${result.dept_name}" /></td>
      <td><c:out value="${result.position}" /></td>
      <td><c:out value="${result.birthday}" /></td>
      <td><c:out value="${result.phone}" /></td>
      <td><font color="red"><c:if test="${empty result.entry_pay}">尚未确认</c:if>
      <c:if test="${not empty result.entry_pay}">
      <c:out value="${result.entry_pay}" /></c:if>
      </font></td>
      <td>
      <c:if test="${empty result.hr_state}">
      	基本信息待填写
      </c:if>
      <c:if test="${result.hr_state=='2'}">
      	待总经理确认
      </c:if>
      <c:if test="${not empty result.hr_state}">
      <c:if test="${result.hr_state!='2'}">
      <c:out value="${hr_state[result.hr_state]}" />
      </c:if></c:if>
      </td>
				<td width="20%">
				<a style="color: green" href="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.id}&hr_type=${result.hr_type}&showFlag=show&flag=entry">详情查看</a>
				<c:if test="${user.branchid==2&&user.duty_id==1}">
				<c:if test="${empty result.hr_state}">
				<a style="color: #ff9955" href="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.id}&hr_type=${result.hr_type}&showFlag=edit&flag=entry">填写详情</a>
				<a style="color: red" href="#" onclick="openDGMax('修改详细信息','<%=request.getContextPath()%>/hr/info/Hr_base_info!editDtl.do?id=${result.id}','hr_base_infoDtl')">填写基本信息</a>
				</c:if>
				</c:if>
				<c:if test="${result.hr_type==2}">
					<c:if test="${user.branchid==2&&user.duty_id==2&&result.hr_state==5}">
					<a style="color: red" href="#" onclick="openDG('填写试用信息','<%=request.getContextPath()%>/hr/process/Hr_opinion!editTry.do?p_id=${result.id}')">试用</a>
					<a style="color: red" href="<%=request.getContextPath()%>/hr/process/Hr_opinion!toTwh.do?p_id=${result.id}" onclick="confirm('您是否确认要将此人转入人才库?')">转入人才库</a>
					</c:if>
					<c:if test="${user.duty_id==3&&result.hr_state==2}">
					<a style="color: red" href="<%=request.getContextPath()%>/hr/process/Hr_opinion!affirm.do?p_id=${result.id}" onclick="confirm('您是否要确认?')">确认</a>
					</c:if>
					<c:if test="${user.branchid==2&&user.duty_id==2}">
					<a style="color: red" href="<%=request.getContextPath()%>/hr/process/Hr_opinion!toTwh.do?id=${result.id}" onclick="confirm('您是否确认要将此人转入人才库?')">转入人才库</a>
					</c:if>
					<!-- 
					<c:if test="${user.branchid==2&&user.duty_id==2&&result.hr_state==0}">
					<a style="color: green" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editTry.do?p_id=${result.id}&hr_state=2')">填写意见</a>
					<a style="color: red" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editTry.do?p_id=${result.id}&hr_type=5&hr_state=4')">未通过</a>
					</c:if>
					<c:if test="${user.branchid!=2&&user.duty_id==2&&result.hr_state==1}">
					<a style="color: green" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editTry.do?p_id=${result.id}&hr_state=0')">通过</a>
					<a style="color: red" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editTry.do?p_id=${result.id}&hr_type=5&hr_state=4')">未通过</a>
					</c:if>
					<c:if test="${user.duty_id==3&&result.hr_state==2}">
					<a style="color: green" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editTry.do?p_id=${result.id}&hr_type=3&hr_state=3')">通过</a>
					<a style="color: red" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editTry.do?p_id=${result.id}&hr_type=5&hr_state=4')">未通过</a>
					</c:if>
					 -->
				</c:if>
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