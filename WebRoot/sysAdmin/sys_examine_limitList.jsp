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
	<form id="searchForm" action="<%=request.getContextPath()%>/sysAdmin/Sys_examine_limit!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>角色id:</th>
			<td>
			<input type="text" name="rolename" id="rolename" value="" readonly="readonly" class="textBox"/>
			<input type="text" name="roleid" id="roleid" value="" class="hidden"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/sysrolesdic!searchList.do?pageType=select','roleid','rolename');" value="选择"/></td>
			<th>审批额度类型:</th>
			<td>
			<select name="examine_limit_type" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${examine_limit_type}" var="examine_limit_type">
			<option value="${examine_limit_type.key}" >${examine_limit_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/sysAdmin/Sys_examine_limit!add.do','sys_examine_limitDtl')" class="link">添加</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
           <th></th>
           <th>角色id</th>
           <th>审批额度类型</th>
           <th>最小额度</th>
           <th>最大额度</th>
           <th>计量单位</th>
           <th>有效标志</th>
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td><c:out value="${result.id}" /></td>
      <td><c:out value="${result.roleid}" /></td>
      <td><c:out value="${examine_limit_type[result.examine_limit_type]}" /></td>
      <td><c:out value="${result.min}" /></td>
      <td><c:out value="${result.max}" /></td>
      <td><c:out value="${result.unit}" /></td>
      <td><c:out value="${yxbz[result.yxbz]}" /></td>
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/sysAdmin/Sys_examine_limit!searchById.do?id=${result.id}','sys_examine_limitDtl')">查看</a>
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/sysAdmin/Sys_examine_limit!edit.do?id=${result.id}','sys_examine_limitDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/sysAdmin/Sys_examine_limit!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a></td>
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