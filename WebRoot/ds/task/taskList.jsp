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
<link rel="stylesheet" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/easyUI132/jquery.easyui.min.js"></script>
</head>
<body>	
	<div id="accordion" class="easyui-accordion" style="height: 650px">  
    <div title="任务书列表" iconCls="icon-search" style="overflow:auto;padding:10px;">
	<form id="searchForm" action="<%=request.getContextPath()%>/ds/task/Ds_dstask!taskList.do" method="post">
		<div class="divMod2">
		<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>项目名称:</th>
			<td>
			<input type="text" name="proj_name" value="${searchMap.proj_name}" style="width:95%" class="textBox"/></td>
			<th>设计类型:</th>
			<td>
			<select name="ds_type" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${ds_type}" var="ds_type">
			<option value="${ds_type.key}" <c:if test="${searchMap.ds_type==ds_type.key}">selected</c:if>>${ds_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th>交付日期:</th>
			<td>
			<input type="text" name="delivery_time1" value="${searchMap.delivery_time1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			至
			<input type="text" name="delivery_time2" value="${searchMap.delivery_time2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			</td>
            </tr>
		</table>
		</div>
		<div class="div-button">
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
		<table class="dataListTable" width="100%">
			<tr>
		   <th>序号</th>
           <th>项目名称</th>
           <th>设计类型</th>
           <th>任务名称</th>
           <th>发起部门</th>
           <th>发起人</th>
           <th>发起日期</th>
           <th>交付日期</th>
           <th>任务状态</th>
		        <th width="110px">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
	  <td><c:out value="${status.index+1}" /></td>		
      <td><c:out value="${result.proj_name}" /></td>
      <td><c:out value="${ds_type[result.ds_type]}" /></td>
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.dept_name}" /></td>
      <td><c:out value="${result.p_name}" /></td>
      <td><c:out value="${result.launch_time}" /></td>
      <td><c:out value="${result.delivery_time}" /></td>
      <td>
      	  <c:if test="${result.flag=='0'}">
	      <c:out value="未接件" />
	      </c:if>
	      <c:if test="${result.flag=='1'}">
	      <c:out value="已接件" />
	      </c:if>
	      <c:if test="${result.flag=='2'}">
	      <c:out value="已退件" />
	      </c:if>
	      <c:if test="${result.flag=='3'}">
	      <c:out value="已策划" />
	      </c:if>
      </td>
				<td width="110px">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/ds/task/Ds_dstask!searchById.do?id=${result.id}&flag=1','ds_dstaskDtl')">查看</a>
				<c:if test="${result.flag=='1'&&result.design_fzr==user.base_info_id}">
				<a href="#" onclick="openDG('编制策划书','<%=request.getContextPath()%>/ds/scheme/Ds_scheme!add.do?id=${result.id}','ds_schemeDtl')">编制策划书</a>
				</c:if>
				</td>
				</tr>
			</c:forEach>
		</table>
			<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	<span>共 ${page.lastPageNumber} 页</span>
	<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span> 页</span>
	<a onclick="changePage(${page.previousPageNumber})" class="linkPage">上一页</a>
	<a onclick="changePage(${page.nextPageNumber})" class="linkPage">下一页</a>
	<a onclick="changePage(${page.lastPageNumber})" class="linkPage">尾页</a>
	</div>
		</div>
		<div title="策划书列表" id="schemeList" iconCls="icon-edit" style="overflow:auto;padding:10px;">
		<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/ds/scheme/Ds_scheme!searchList.do' frameborder=0></iframe>
		</div>
		</div>
<script type="text/javascript">
	function changePage(page){
		if(page==undefined||page==null) page = $("#curPage").text();
		document.getElementById('searchForm').insertAdjacentHTML("beforeEnd",
	        "<input type=\"hidden\" name=\"pageno\" value=\""+page+"\"/>");
		document.getElementById('searchForm').submit();
	}
</script>	
</body>
</html>