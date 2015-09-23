<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title></title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
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
			action="<%=request.getContextPath()%>/materielapply/Gm_ration_apply!${editMod}.do"
			method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">

					<tr>
						<th width="15%">
							项目:
						</th>
						<td width="35%">
							<input type="text" id="proname" name="proname"
								value="${searchMap.proname}" class="textBox" />
							<input type="text" id="prj_id" name="prj_id" value=""
								dataType="Double" require="false" class="hidden" />
							<input type="button"
								onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','prj_id','proname');"
								value="选择" />
						</td>
						<th width="15%">
							申请单号:
						</th>
						<td>
							<input type="text" name="bh" style="width: 95%" class="textBox"
								value="${searchMap.bh}" />
						</td>
					</tr>
					<tr>

						<%--<th>计划单:</th>
			<td>
			<input type="text" name="item_id" style="width:95%" value="${searchMap.item_id}" class="textBox"/></td>
			--%>

						<th>
							审批状态:
						</th>
						<td>
							<select name="spzt" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${purchaseapply_state}" var="state">
									<option value="${state.key}"
										<c:if test="${searchMap.spzt==state.key}">selected</c:if>>
										${state.value}
									</option>
								</c:forEach>
							</select>
						</td>




					</tr>



				</table>
			</div>
			<div class="div-button">
				<c:if test="${type!='audit'}">
					
		    <a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/materielapply/Gm_ration_apply!addfromitem.do','gm_ration_applyDtl')" class="link">从计划添加</a>
		 
					<a href="#"
						onclick="openDG('添加信息','<%=request.getContextPath()%>/materielapply/Gm_ration_apply!add.do','gm_ration_applyDtl')"
						class="link">添加</a>
				</c:if>
				<input type="reset" value="重置" class="button" />
				<input type="submit" value="查询" class="button" />
			</div>
			<div class="clear" style="height: 0px;"></div>
		</form>
		<div>
			<table class="dataListTable" width="100%">
				<tr>

					<th>
						序号
					</th>
					<th>
						项目
					</th>
					<%--<th>
						计划单
					</th>
					--%>
					<th>
						申请单号
					</th>
					<th>
						申请人
					</th>

					<th>
						申请时间
					</th>
					
					<th>
						来自计划
					</th>

					<th>
						审批状态
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
							<c:out value="${result.proname}" />
						</td>
						<%--<td>
							<c:out value="${result.planname}" />
						</td>
						--%>
						<td>
							<c:out value="${result.bh}" />
						</td>
						<td>
							<c:out value="${result.apply_name}" />
						</td>

						<td>
							<c:out value="${result.apply_date}" />
						</td>
						
						<td>
							<c:if test="${result.isplan=='1'}"><font color="red">是</font></c:if>
							<c:if test="${result.isplan=='0'}">否</c:if>
						</td>

						<td>
							<c:out value="${purchaseapply_state[result.spzt]}" />

						</td>

						<td class="btnCol">
							<a href="#"
								onclick="openDG('查看详细信息','<%=request.getContextPath()%>/materielapply/Gm_ration_apply!searchById.do?id=${result.id}','gm_ration_applyDtl')">查看</a>
							
							<c:if test="${type=='audit'}">
							   <c:if test="${result.spzt=='1'&&(dept_id==result.apply_dept||(result.apply_dept=='10'&&dept_id=='5'))}">
							      <a href="#"
									onclick="openDG('查看详细信息','<%=request.getContextPath()%>/materielapply/Gm_ration_apply!audit.do?id=${result.id}','gm_ration_applyDtl')">审批</a>
							   </c:if>
							   
							   <c:if test="${result.spzt=='5'&&(result.apply_dept=='4'||result.apply_dept=='5')&&dept_id=='1'}">
							      <a href="#"
									onclick="openDG('查看详细信息','<%=request.getContextPath()%>/materielapply/Gm_ration_apply!audit.do?id=${result.id}','gm_ration_applyDtl')">审批</a>
							   </c:if>
							   
							   <c:if test="${result.spzt=='5'&&result.apply_dept!='4'&&dept_id=='5'}">
							      <a href="#"
									onclick="openDG('查看详细信息','<%=request.getContextPath()%>/materielapply/Gm_ration_apply!audit.do?id=${result.id}','gm_ration_applyDtl')">审批</a>
							   </c:if>
							   
							   <c:if test="${result.spzt=='6'&&result.apply_dept=='7'&&info.duty_id=='1'}">
							      <a href="#"
									onclick="openDG('查看详细信息','<%=request.getContextPath()%>/materielapply/Gm_ration_apply!audit.do?id=${result.id}','gm_ration_applyDtl')">审批</a>
							   </c:if>
							
							</c:if>
							
							

							<c:if
								test="${(result.spzt=='3'||result.spzt=='0')&&type!='audit'}">
								<a href="#"
									onclick="openDG('修改详细信息','<%=request.getContextPath()%>/materielapply/Gm_ration_apply!modify.do?id=${result.id}','gm_ration_applyDtl')">修改</a>
							</c:if>

							<c:if test="${type == 'selflook'&&result.spzt=='0'}">
								<a
									href="<%=request.getContextPath()%>/materielapply/Gm_ration_apply!lastsub.do?id=${result.id}"
									OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>

							</c:if>

						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="PageUpDnDiv"
			style="PADDING-TOP: 5px; padding-bottom: 5px; text-align: center">

			<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span>
				页</span>
			<a onclick="changePage(1)" class="linkPage">首页</a><a
				onclick="changePage(${page.previousPageNumber})" class="linkPage">上一页</a>
			<a onclick="changePage(${page.nextPageNumber})" class="linkPage">下一页</a>
			<a onclick="changePage(${page.lastPageNumber})" class="linkPage">尾页</a>
		</div>
	</body>
	<script type="text/javascript">
function changePage(page) {
	if (page == undefined || page == null)
		page = $("#curPage").text();
	document.getElementById('searchForm').insertAdjacentHTML("beforeEnd",
			"<input type=\"hidden\" name=\"pageno\" value=\"" + page + "\"/>");
	document.getElementById('searchForm').submit();
}
</script>
</html>