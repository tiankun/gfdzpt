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
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase!auditsearchList.do"
			method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">

					<tr>
						<th>
							申请人:
						</th>
						<td>
							<input type="text" name="name" id="name"
								value="${searchMap.revename}" class="textBox" />
							<input type="text" name="p_id" id="p_id"
								value="${searchMap.receive_person}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
								value="选择" />
						</td>
						<th>
							部门:
						</th>
						<td>
							<input type="text" name="branchname" id="branchname"
								value="${searchMap.branchname}" class="textBox" />
							<input type="text" name="dept_id" id="dept_id"
								value="${searchMap.dept_id}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择组织机构','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dept_id','branchname');"
								value="选择" />
						</td>
					</tr>
					<tr>
						<th>
							项目:
						</th>
						<td>
							<input type="text" id="proname" name="proname"
								value="${searchMap.proname}" readonly="readonly" class="textBox" />
							<input type="text" id="prj_id" name="prj_id"
								value="${searchMap.prj_id}" dataType="Double" require="false"
								class="hidden" />
							<input type="button"
								onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','prj_id','proname');"
								value="选择" />
						</td>
						<th>
							编号:
						</th>
						<td>
							<input type="text" name="dh" style="width: 95%" class="textBox" />
						</td>
					</tr>
					<tr>
						<th>
							配给单号:
						</th>
						<td>
							<input type="text" name="ration_apply_id" style="width:95%" class="textBox" />
						</td>
						<th>
							金额:
						</th>
						<td>
							<input type="text" name="xiaoxje"
								onkeyup="if(isNaN(value))execCommand('undo')"
								onafterpaste="if(isNaN(value))execCommand('undo')"
								style="ime-mode: disabled" style="width:95%" class="textBox" />
						</td>
					</tr>

					<tr>

						
							<th>
								审批状态:
							</th>
							<td>
								<select name="spzt" class="dropdownlist">
									<option value="">
										---
									</option>
									<c:forEach items="${purchase_state}" var="state">
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
						配给申请单
					</th>
					<th>
						采购申请人
					</th>
					<th>
						采购执行人
					</th>
					<th>
						部门
					</th>

					<th>
						项目
					</th>
					<th>
						编号
					</th>

					<th>
						金额
					</th>
					<th>
						状态
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
							<c:out value="${result.ration_apply_id}" />
						</td>
						<td>
							<c:out value="${result.applyname}" />
						</td>
						<td>
							<c:out value="${result.executename}" />
						</td>
						<td>
							<c:out value="${result.branchname}" />
						</td>

						<td>
							<c:out value="${result.proname}" />
						</td>
						<td>
							<c:out value="${result.dh}" />
						</td>

						<td>
							<c:out value="${result.xiaoxje}" />
						</td>
						<td>
							<c:out value="${purchase_state[result.spzt]}" />
						</td>

						<td class="btnCol">
							<a href="#"
								onclick="openDG('查看详细信息','<%=request.getContextPath()%>/materielpurchase/Gm_purchase!searchById.do?id=${result.id}','gm_purchaseDtl')">查看</a>
							<c:if test="${result.spzt=='1'&&type=='audit'}">
								<a href="#"
									onClick="openDG('查看详细信息','<%=request.getContextPath()%>/materielpurchase/Gm_purchase!audit.do?id=${result.id}','gm_purchaseDtl')">审批</a>
							</c:if>

							

							<c:if test="${result.spzt=='2'&&type=='finalaudit'}">
								<a href="#"
									onClick="openDG('查看详细信息','<%=request.getContextPath()%>/materielpurchase/Gm_purchase!audit.do?id=${result.id}','gm_purchaseDtl')">审批</a>
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