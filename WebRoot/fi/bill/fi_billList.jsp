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
	<form id="searchForm" action="<%=request.getContextPath()%>/fi/bill/Fi_bill!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
					<tr>
						<th>
							申请时间:
						</th>
						<td>
							<input type="text" name="input_date1"
								value="${searchMap.input_date1}" dataType="Date" require="false"
								onfocus="WdatePicker()" style="ime-mode: disabled"
								style="width:30%" />
							至
							<input type="text" name="input_date2"
								value="${searchMap.input_date2}" dataType="Date" require="false"
								onfocus="WdatePicker()" style="ime-mode: disabled"
								style="width:30%" />
						</td>
						<th>
							审批状态：
						</th>
						<td>
							<select name="bill_state" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${bill_state}" var="state">
									<option value="${state.key}" <c:if test="${searchMap.bill_state==state.key}">selected</c:if>>
										${state.value}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>
							开票单位:
						</th>
						<td>
							<input type="text" name="unit" value="${searchMap.unit}" />
							
						</td>
						<th>
							开票金额:
						</th>
						<td>
							<input type="text" name="money" value="${searchMap.money}" />
							
						</td>
					</tr>

				</table>
	</div>
		<div class="div-button">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/fi/bill/Fi_bill!add.do','fi_billDtl')" class="link">添加</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			 <th width="5%">序号</th>
             <th width="7%">发票人</th>
             <th width="10%">部门</th>
             <th>客户单位名称</th>
             <th width="10%">开具金额</th>
             
             <th width="14%">审核状态</th>
             <th width="10%">申请时间</th>
             <th width="20%">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				 <td>${status.index+1}</td>
				 <td><c:out value="${result.name}" /></td>
                 <td><c:out value="${result.branchname}" /></td>
                 <td><c:out value="${result.unit}" /></td>
                 <td><c:out value="${result.money}" /></td>
                
                 <td><c:out value="${bill_state[result.bill_state]}" /></td>
                 <td><c:out value="${result.input_date}" /></td>
                 <td>
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/fi/bill/Fi_bill!searchById.do?id=${result.id}&type=view','fi_billDtl')">查看</a>
				<c:if test="${result.bill_state=='0'||result.bill_state=='5'}">
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/fi/bill/Fi_bill!edit.do?id=${result.id}','fi_billDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/fi/bill/Fi_bill!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				<a href="<%=request.getContextPath()%>/fi/bill/Fi_bill!sub.do?id=${result.id}"
								OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
				</td>
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