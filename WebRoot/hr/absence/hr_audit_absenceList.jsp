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
    
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/absence/Hr_absence!auditList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>请假类型:</th>
			<td>
			<input type="text" name="absence_type" id="absence_type" value="${searchmap.absence_type}"  class="textBox"/>
			<input type="button" onClick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/absence/Hr_absence_standard!searchList.do?pageType=select','id','absence_type');" value="选择"/>
		    </td>
			<th>审核状态:</th>
			<td>
			<select name="absence_state" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${absence_state}" var="absence_state">
			<option value="${absence_state.key}" <c:if test="${searchmap.absence_state==absence_state.key}">selected</c:if>>${absence_state.value}</option>
			</c:forEach>
			</select>
			</tr>
            <tr>
            <th>开始时间:</th>
			<td>
			<input type="text" value="${searchmap.begin_date}" name="begin_date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
            <th>结束时间:</th>
			<td>
			<input type="text" value="${searchmap.end_date}" name="end_date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
			
            </tr>
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
		   <th width="5%">序号</th>
           <th>姓名</th>
           <th>请假类型</th>
           <th>开始时间</th>
           <th>结束时间</th>
           <th>请假原因</th>
           <th>申请时间</th>
           <th>审核状态</th>
           <th>请假天数</th>
           <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="s">
				<tr>
				<td>${s.index+1}</td>
		   <td><c:out value="${result.name}" /></td>
           <td><c:out value="${result.absence_type}" /></td>
           <td><c:out value="${result.begin_date}" /></td>
           <td><c:out value="${result.end_date}" /></td>
           <td><c:out value="${result.reason}" /></td>
           <td><c:out value="${result.input_date}" /></td>
           <td><c:out value="${absence_state[result.absence_state]}" /></td>
           <td><c:out value="${result.days}" /></td>
           <td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/hr/absence/Hr_absence!searchById.do?id=${result.id}','hr_absenceDtl')">查看</a>
				<c:if test="${result.absence_state == '2'}">
				<a href="#" onclick="openDG('请假审批','<%=request.getContextPath()%>/hr/absence/Hr_absence!edit.do?id=${result.id}&type=audit','hr_absenceDtl')">审批</a>
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