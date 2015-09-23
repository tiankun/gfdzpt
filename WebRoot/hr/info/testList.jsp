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
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/info/Hr_base_info!testList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>姓名:</th>
			<td>
			<input type="text" name="name" value="${searchMap.name}" style="width:95%" class="textBox"/></td>
			<th>应聘岗位:</th>
			<td><input type="text" name="position" value="${searchMap.position}" style="width:95%" class="textBox"/></td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<c:if test="${user.branchid==2}">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/hr/info/Hr_base_info!add.do','hr_base_infoDtl')" class="link">添加</a>
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
           <th>姓名</th>
           <th>性别</th>
           <th>学历</th>
           <th>专业</th>
           <th>应聘岗位</th>
           <th>出生日期</th>
           <th>政治面貌</th>
           <th>移动电话</th>
           <th>审核状态</th>
		        <th width="250px">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				<td>${status.index+1}</td>	
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${sex[result.sex]}" /></td>
      <td><c:if test="${empty result.degree}"><font color="red">尚未填写</font></c:if>
      <c:if test="${not empty result.degree}">
      <c:out value="${degree[result.degree]}" />
      </c:if>
      </td>
      <td>
      <c:if test="${empty result.major}"><font color="red">尚未填写</font></c:if>
      <c:if test="${not empty result.major}">
      <c:out value="${result.major}" />
      </c:if></td>
      <td><c:out value="${result.position}" /></td>
      <td><c:out value="${result.birthday}" /></td>
      <td><c:out value="${zheng_zhi[result.zheng_zhi]}" /></td>
      <td><c:out value="${result.phone}" /></td>
      <td>
      <c:if test="${empty result.hr_state}">
      	待面试
      </c:if>
      <c:if test="${not empty result.hr_state}">
      <c:out value="${hr_state[result.hr_state]}" />
      </c:if>
      </td>
				<td width="250px">
				<c:if test="${result.hr_type==0&&user.branchid==2&&result.subflag=='0'}">
				<a style="color: #ff9955" href="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.id}&hr_type=${result.hr_type}&showFlag=edit&flag=test">填写详情</a>
				<a style="color: red" href="#" onclick="openDG('基本信息修改','<%=request.getContextPath()%>/hr/info/Hr_base_info!edit.do?id=${result.id}','hr_base_infoDtl')">基本信息修改</a>
				<a style="color: red" href="<%=request.getContextPath()%>/hr/info/Hr_base_info!sub.do?id=${result.id}" OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
				</c:if>
				<a href="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.id}&hr_type=${result.hr_type}&showFlag=show&flag=test">详情查看</a>
				<c:if test="${result.hr_type==0&&user.branchid==2&&result.subflag=='1'}">
				<a style="color: green" href="#" onclick="openDGMax('初试','<%=request.getContextPath()%>/hr/process/Hr_test!edit.do?id=${result.id}','hr_ptestDtl')">初试</a>
				<a style="color: red" href="#" onclick="openDG('初试提交','<%=request.getContextPath()%>/hr/process/Hr_test!csSub.do?id=${result.id}')">初试提交</a>
				</c:if>
				<c:if test="${user.branchid==2&&user.duty_id==2}">
				<a style="color: red" href="<%=request.getContextPath()%>/hr/process/Hr_opinion!toTwh.do?id=${result.id}" onclick="confirm('您是否确认要将此人转入人才库?')">转入人才库</a>
				</c:if>
				<c:if test="${result.hr_type==1}">
					<c:if test="${user.branchid!=2&&user.duty_id==2&&result.hr_state==1}">
					<a style="color: green" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editApply.do?p_id=${result.id}&hr_state=2')">通过</a>
					<a style="color: red" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editApply.do?p_id=${result.id}&hr_type=5&hr_state=4')">未通过</a>
					</c:if>
					<c:if test="${user.duty_id==3&&result.hr_state==2}">
					<a style="color: green" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editApply.do?p_id=${result.id}&hr_type=2&hr_state=3')">通过</a>
					<a style="color: red" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editApply.do?p_id=${result.id}&hr_type=5&hr_state=4')">未通过</a>
					</c:if>
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