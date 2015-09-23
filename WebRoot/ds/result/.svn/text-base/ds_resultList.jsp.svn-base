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
	<form id="searchForm" action="<%=request.getContextPath()%>/ds/result/Ds_result!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>项目:</th>
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
			<th>清单编号:</th>
			<td>
			<input type="text" name="sc_num" value="${search.sc_num}" class="textBox" style="width:95%"/></td>
			<th>编制日期:</th>
			<td>
            <input type="text" name="compile_time1" value="${search.compile_time1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			至
			<input type="text" name="compile_time2" value="${search.compile_time2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
            </td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" style="color: red" onclick="openDG('添加信息','<%=request.getContextPath()%>/ds/result/Ds_result!add.do','ds_resultDtl')" class="link">输出清单编制</a>
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
           <th>清单编号</th>
           <th>审批状态</th>
		        <th>操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
			<td>${status.index+1}</td>	
      <td><c:out value="${result.proj_name}" /></td>
      <td><c:out value="${result.num}" /></td>
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
				<td width="40%">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/ds/result/Ds_result!searchById.do?id=${result.id}','ds_resultDtl')">查看</a>
				<c:if test="${result.appstate=='0'||result.appstate=='-3'}">
				<a href="#" onclick="openDGMax('推荐产品','<%=request.getContextPath()%>/ds/result/Ds_product!tableList.do?result_id=${result.id}&proj_id=${result.proj_id}')"><font color="#ff9955">推荐产品</font></a>
				<a href="#" onclick="openDG('进度计划','<%=request.getContextPath()%>/ds/result/Ds_schedule!edit.do?id=${result.id}&proj_id=${result.proj_id}&ds_type=${result.ds_type}','ds_scheduleDtl')"><font color="green">进度计划</font></a>
				<a href="<%=request.getContextPath()%>/ds/result/Ds_result!sub.do?id=${result.id}" OnClick="javascript:return confirm('你确认要提交吗?');"><font color="red">提交</font></a>
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/ds/result/Ds_result!edit.do?id=${result.id}','ds_resultDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/ds/result/Ds_result!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				</c:if>
				</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	<span>共 ${page.lastPageNumber} 页</span>
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