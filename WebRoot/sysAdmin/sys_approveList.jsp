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
	<form id="searchForm" action="<%=request.getContextPath()%>/sysAdmin/Sys_approve!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>授权人ID:</th>
			<td>
			<input type="text" name="name" id="name" value="" readonly="readonly" class="textBox"/>
			<input type="text" name="licensorid" id="licensorid" value="" class="hidden"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr.info/hr_base_info!searchList.do?pageType=select','licensorid','name');" value="选择"/></td>
			<th>审批部门ID:</th>
			<td>
			<input type="text" name="branchname" id="branchname" value="" readonly="readonly" class="textBox"/>
			<input type="text" name="approval_departid" id="approval_departid" value="" class="hidden"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','approval_departid','branchname');" value="选择"/></td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/sysAdmin/Sys_approve!add.do','sys_approveDtl')" class="link">添加</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
           <th>ID</th>
           <th>授权人ID</th>
           <th>审批部门ID</th>
           <th>审批目标:部门集合</th>
           <th>审批目标:员工集合</th>
           <th>审批等级</th>
           <th>优先等级</th>
           <th>是否审批部门</th>
           <th>是否是申请人结束审批</th>
           <th>是否启用</th>
           <th>组ID</th>
           <th>备注</th>
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr height="30px">
				
      <td><c:out value="${result.id}" /></td>
      <td><c:out value="${result.licensorid}" /></td>
      <td><c:out value="${result.approval_departid}" /></td>
      <td width="150px"><c:out value="${result.approvaldeparts}" /></td>
      <td width="150px"><c:out value="${result.approvalpersons}" /></td>
      <td><c:out value="${result.approval_rating}" /></td>
      <td><c:out value="${result.precedence_level}" /></td>
      <td><c:out value="${result.isapproval_deaprt}" /></td>
      <td><c:out value="${result.isselfapproval}" /></td>
      <td><c:out value="${result.enable}" /></td>
      <td><c:out value="${result.aroupid}" /></td>
      <td><c:out value="${result.remark}" /></td>
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/sysAdmin/Sys_approve!searchById.do?id=${result.id}','sys_approveDtl')">查看</a>
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/sysAdmin/Sys_approve!edit.do?id=${result.id}','sys_approveDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/sysAdmin/Sys_approve!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span> 页</span>
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