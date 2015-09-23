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
	<form id="searchForm" action="<%=request.getContextPath()%>/pr/feePlan/Pr_fee_plan!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>计划id:</th>
			<td></td>
			<th>项目:</th>
			<td></td>
            </tr>
            <tr>
			<th>人工费:</th>
			<td>
			<input type="text" name="rgf" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
			<th>安装费:</th>
			<td>
			<input type="text" name="azf" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
            </tr>
            <tr>
			<th>管理费:</th>
			<td>
			<input type="text" name="glf" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
			<th>变更状态:</th>
			<td>
			<select name="ds_mf_status" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${ds_mf_status}" var="ds_mf_status">
			<option value="${ds_mf_status.key}" >${ds_mf_status.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th>操作人:</th>
			<td>
			<input type="text" name="czr" onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
			<th>操作日期:</th>
			<td>
			<input type="text" name="czrq" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/pr/feePlan/Pr_fee_plan!add.do','pr_fee_planDtl')" class="link">添加</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
           <th></th>
           <th>计划id</th>
           <th>项目</th>
           <th>人工费</th>
           <th>安装费</th>
           <th>管理费</th>
           <th>变更原因</th>
           <th>变更状态</th>
           <th>操作人</th>
           <th>操作日期</th>
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td><c:out value="${result.id}" /></td>
      <td><c:out value="${result.plan_id}" /></td>
      <td><c:out value="${result.prj_id}" /></td>
      <td><c:out value="${result.rgf}" /></td>
      <td><c:out value="${result.azf}" /></td>
      <td><c:out value="${result.glf}" /></td>
      <td><c:out value="${result.mf_reason}" /></td>
      <td><c:out value="${ds_mf_status[result.ds_mf_status]}" /></td>
      <td><c:out value="${result.czr}" /></td>
      <td><c:out value="${result.czrq}" /></td>
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/pr/feePlan/Pr_fee_plan!searchById.do?id=${result.id}','pr_fee_planDtl')">查看</a>
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/pr/feePlan/Pr_fee_plan!edit.do?id=${result.id}','pr_fee_planDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/pr/feePlan/Pr_fee_plan!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a></td>
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