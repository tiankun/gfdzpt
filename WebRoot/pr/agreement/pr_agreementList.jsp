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
	<form id="searchForm" action="<%=request.getContextPath()%>/pr/agreement/Pr_agreement!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>项目信息:</th>
			<td>
			<input type="text" name="name" id="name" value="" readonly="readonly" class="textBox"/>
			<input type="text" name="item_id" id="item_id" value="" class="hidden"/>
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','item_id','name');" value="选择"/></td>
			<th>合同编码:</th>
			<td>
			<input type="text" name="agree_code" style="width:85%" class="textBox"/></td>
            </tr>
            <tr>
			<th>合同名称:</th>
			<td>
			<input type="text" name="agree_name" style="width:85%" class="textBox"/></td>
			<th>合同类型:</th>
			<td>
			<select name="agree_type" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${agree_type}" var="agree_type">
									<option value="${agree_type.key}" <c:if test="${searchMap.agree_type==agree_type.key}">selected</c:if>  >
										${agree_type.value}
									</option>
								</c:forEach>
			</select>
			</tr>
            <tr>
			<th>客户(甲方):</th>
			<td>
			<input type="text" name="custom_unitname" style="width:85%" class="textBox"/></td>
			<th>签字时间:</th>
			<td>
			<input type="text" name="sign_date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:85%" class="textBox"/></td>
            </tr>
            <tr>
			<th>合同金额:</th>
			<td>
			<input type="text" name="agree_money" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:85%" class="textBox"/></td>
			<th>合同状态</th>
			<td>
			  <select name="agree_state" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${agree_state}" var="agree_state">
									<option value="${agree_state.key}" <c:if test="${searchMap.agree_state==agree_state.key}">selected</c:if>  >
										${agree_state.value}
									</option>
								</c:forEach>
			</select>
			</td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/pr/agreement/Pr_agreement!add.do','pr_agreementDtl')" class="link">添加</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
           <th>序号</th>
           <th>项目</th>
           <th>合同名称</th>
           <th>合同编码</th>
           <th>合同类型</th>
           <th>客户名称（甲方）</th>
           
           <th>合同金额</th>
           <th>合同状态</th>
           <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
	  <td><c:out value="${status.index+1}" /></td>		
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.agree_name}" /></td>
      <td><c:out value="${result.agree_code}" /></td>
      <td><c:out value="${agree_type[result.agree_type]}" /></td>
      <td><c:out value="${result.custom_unitname}" /></td>
    
      <td><c:out value="${result.agree_money}" /></td>
       <td><c:out value="${agree_state[result.agree_state]}" /></td>
      
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/pr/agreement/Pr_agreement!searchById.do?id=${result.id}','pr_agreementDtl')">查看</a>
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/pr/agreement/Pr_agreement!edit.do?id=${result.id}','pr_agreementDtl')">修改</a>
				<c:if test="${result.state=='0'}">
				<a href="<%=request.getContextPath()%>/pr/agreement/Pr_agreement!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				<a href="<%=request.getContextPath()%>/pr/agreement/Pr_agreement!sub.do?id=${result.id}"
								OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
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