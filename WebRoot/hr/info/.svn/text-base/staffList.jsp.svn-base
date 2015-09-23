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
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/info/Hr_base_info!staffList.do?flag='init'" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>姓名:</th>
			<td>
			<input type="text" name="name" value="${searchMap.name}" style="width:95%" class="textBox"/></td>
			<th align="center" class="myInputTitle" style="width: 10%">
				部门:
			</th>
			<td style="width: 35%">
				<input type="text" name="branchname" id="branchname"
					value="${searchMap.branchname}" class="textBox" />
				<input type="text" name="dept_id" id="dept_id"
					 class="hidden" />
				<input type="button"
					onclick="openSelect('选择组织机构','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dept_id','branchname');"
					value="选择" />
			</td>
            </tr>
            <tr>
            <th>岗位:</th>
			<td><input type="text" name="position" value="${searchMap.position}" style="width:95%" class="textBox"/></td>
            <th>任职状态:</th>
			<td>
			<select name="hr_type" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${hr_type}" var="hr_type">
			<option value="${hr_type.key}" <c:if test="${searchMap.hr_type==hr_type.key}">selected</c:if>>${hr_type.value}</option>
			</c:forEach>
			</select>
			</td>
			</tr>
		</table>
	</div>
		<div class="div-button">
			<c:if test="${user.branchid==2}">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/hr/info/Hr_base_info!addStaff.do','hr_base_infoDtl')" class="link">人员录入</a>
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
           <th>证件号码</th>
           <th>性别</th>
           <th>部门</th>
           <th>岗位</th>
           <th>任职状态</th>
           <th>出生日期</th>
           <th>政治面貌</th>
           <th>婚姻状况</th>
           <th>移动电话</th>
		        <th width="180px">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
			<td>${status.index+1}</td>	
	      <td><c:out value="${result.name}" /></td>
	      <td><c:out value="${result.card_num}" /></td>
	      <td><c:out value="${sex[result.sex]}" /></td>
	      <td><c:out value="${result.dept_name}" /></td>
	      <td><c:out value="${result.position}" /></td>
	      <td><c:out value="${result.rzzt}" /></td>
	      <td><c:out value="${result.birthday}" /></td>
	      <td><c:out value="${zheng_zhi[result.zheng_zhi]}" /></td>
	      <td><c:out value="${marriage[result.marriage]}" /></td>
	      <td><c:out value="${result.phone}" /></td>
				<td width="180px">
				<c:if test="${user.branchid==2&&result.hr_type!=4}">
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/hr/info/Hr_base_info!editStaff.do?id=${result.id}','infoStaffDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.id}&hr_type=${result.hr_type}&flag=staff">详情修改</a>
				</c:if>
				
				<a href="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.id}&hr_type=${result.hr_type}&flag=staff&showFlag=show">查看</a>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span> 页</span>
	<a onclick="changePage(1)" class="linkPage">首页</a>
	<a onclick="changePage(${page.previousPageNumber})" class="linkPage">上一页</a>
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