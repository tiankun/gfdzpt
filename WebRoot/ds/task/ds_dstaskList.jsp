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
	<form id="searchForm" action="<%=request.getContextPath()%>/ds/task/Ds_dstask!searchList.do" method="post">
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
			<th>发起日期:</th>
			<td>
			<input type="text" name="launch_time1" value="${searchMap.launch_time1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			至
			<input type="text" name="launch_time2" value="${searchMap.launch_time2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			</td>
			<th>交付日期:</th>
			<td>
			<input type="text" name="delivery_time1" value="${searchMap.delivery_time1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			至
			<input type="text" name="delivery_time2" value="${searchMap.delivery_time2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			</td>
            </tr>
            <tr>
			<!-- <th>审批状态:</th>
			<td>
			<input type="text" name="appstate" style="width:95%" class="textBox"/></td>
			 -->
			<th>审批日期:</th>
			<td>
			<input type="text" name="apptime1" value="${searchMap.apptime1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			至
			<input type="text" name="apptime2" value="${searchMap.apptime2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			</td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/ds/task/Ds_dstask!add.do','ds_dstaskDtl')" class="link">任务书编制</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
		   <th>序号</th>
           <th>项目名称</th>
           <th>设计类型</th>
           <th>任务名称</th>
           <th>发起人</th>
           <th>发起日期</th>
           <th>交付日期</th>
           <th>审批状态</th>
           <th>任务状态</th>
		        <th width="15%">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
	  <td><c:out value="${status.index+1}" /></td>		
      <td><c:out value="${result.proj_name}" /></td>
      <td><c:out value="${ds_type[result.ds_type]}" /></td>
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.p_name}" /></td>
      <td><c:out value="${result.launch_time}" /></td>
      <td><c:out value="${result.delivery_time}" /></td>
      <td>
      	<c:if test="${result.appstate=='0'}"><font color="red">
	      <c:out value="待提交" /></font>
	      </c:if>
	      <c:if test="${result.appstate=='1'}"><font color="red">
	      <c:out value="待审核" /></font>
	      </c:if>
	      <c:if test="${result.appstate=='-1'}"><font color="green">
	      <c:out value="${appstate[result.appstate]}" /></font>
	      </c:if>
	      <c:if test="${!(result.appstate=='0'||result.appstate=='1'||result.appstate=='-1')}">
	      <c:out value="${appstate[result.appstate]}" />
	      </c:if>
      </td>
      <td>
      	  <c:if test="${result.task_state=='3'}"><font color="#ff9955">
	      <c:out value="${task_state[result.task_state]}" /></font>
	      </c:if>
	      <c:if test="${result.task_state=='5'}"><font color="green">
	      <c:out value="${task_state[result.task_state]}" /></font>
	      </c:if>
	      <c:if test="${!(result.task_state=='3'||result.task_state=='5')}">
      	  <c:out value="${task_state[result.task_state]}" />
      	  </c:if>
      </td>
				<td width="15%">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/ds/task/Ds_dstask!searchById.do?id=${result.id}&flag=0','ds_dstaskDtl')">查看</a>
				<c:if test="${result.flag=='0'&&(result.appstate=='-3'||result.appstate=='0')&&result.launch_personid==user.base_info_id}">
				<a href="<%=request.getContextPath()%>/ds/task/Ds_dstask!sub.do?id=${result.id}" OnClick="javascript:return confirm('你确认要提交吗?');"><font color="red">提交</font></a>
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/ds/task/Ds_dstask!edit.do?id=${result.id}','ds_dstaskDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/ds/task/Ds_dstask!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				</c:if>
				</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	<span>共 ${page.lastPageNumber} 页</span>
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