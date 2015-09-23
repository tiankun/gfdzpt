﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title></title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css"
			type="text/css"></link>
		<script src="<%=request.getContextPath()%>/jscripts/jquery.js"
			type="text/javascript">
</script>
		<script
			src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"
			type="text/javascript">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>
	</head>
	<body>
		<form id="searchForm"
			action="<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do"
			method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">

					<tr>
						<th>
							项目名称:
						</th>
						<td>
							<input type="text" name="name" style="width: 95%" class="textBox" value="${searchMap.name}"/>
						</td>
						<th>
							项目业主:
						</th>
						<td>
							<input type="text" name="pr_owner" style="width: 95%" value="${searchMap.pr_owner}"
								class="textBox" />
						</td>
					</tr>
					<tr>
						<th>
							项目经理:
						</th>
						<td>
						    <input type="text" name="pr_manage" id="pr_manage"  readonly
								value="${searchMap.pr_manage}"  class="textBox" />
							<input type="text" name="p_id" id="p_id"
								value="" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','pr_manage');"
								value="选择" />
						
						
						</td>
						<th>
							项目阶段:
						</th>
						<td>
							<select name="proj_phase" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${proj_phase}" var="proj_phase">
									<option value="${proj_phase.key}" <c:if test="${searchMap.pr_step==proj_phase.key}">selected</c:if>  >
										${proj_phase.value}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</div>
			<div class="div-button">
				<a href="#"
					onclick="openDG('添加信息','<%=request.getContextPath()%>/pr/project/Pr_project!add.do','pr_projectDtl')"
					class="link">添加</a>
				<input type="reset" value="重置" class="button" />
				<input type="submit" value="查询" class="button" />
			</div>
			<div class="clear" style="height: 0px;"></div>
		</form>
		<div>
			<table class="dataListTable" width="100%">
				<tr>

					<th>序号</th>
					<th>
						项目名称
					</th>
					<th>
						项目业主
					</th>
					<th>
						销售经理
					</th>
					
					<th class="btnCol">
						操作
					</th>
				</tr>
				<c:forEach items="${record}" var="result" varStatus="status">
					<tr>
						<td>
							<c:out value="${status.index+1}" />
						</td>

						<td>
							<c:out value="${result.name}" />
						</td>
						<td>
							<c:out value="${result.pr_owner}" />
						</td>

						<td>
							<c:out value="${result.sname}" />
						</td>

						<td class="btnCol">
							<a href="#"
								onclick="openDG('查看详细信息','<%=request.getContextPath()%>/pr/project/Pr_project!searchById.do?id=${result.id}&pageType=viewDtl','pr_projectDtl')">查看</a>
							<a href="#"
								onclick="openDG('修改详细信息','<%=request.getContextPath()%>/pr/project/Pr_project!edit.do?id=${result.id}','pr_projectDtl')">修改</a>
							<c:if test="${result.state=='0'}">
							<a
								href="<%=request.getContextPath()%>/pr/project/Pr_project!delete.do?id=${result.id}"
								OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
							<a
								href="<%=request.getContextPath()%>/pr/project/Pr_project!sub.do?id=${result.id}"
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