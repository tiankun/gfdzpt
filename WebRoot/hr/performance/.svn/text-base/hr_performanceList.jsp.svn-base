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

<script type="text/javascript">
function kaohe()
{
	if(document.form1.audit_date.value=='')
		{
		alert("请选择考核时间!");
		document.form1.audit_date.focus();
		return false;
		}
	document.form1.action = '<%=request.getContextPath()%>/hr/performance/Hr_performance!searchPersonList.do';
	document.form1.submit();
}


</script>
	</head>
	<body>
		<form
			action="<%=request.getContextPath()%>/hr/performance/Hr_performance!searchList.do" name="form1"
			method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">

					<tr>
						<th>
							姓名:
						</th>
						<td>
							<input type="text" name="name" id="name"
								value="${searchMap.name}" class="textBox" />
							<input type="text" name="p_id" id="p_id"
								value="" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
								value="选择" />
						</td>
						<th>
							考核时间:
						</th>
						<td>
							<select name="audit_date" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${datelist}" var="dat">
									<option value="${dat.khdate}" <c:if test="${searchMap.audit_date==dat.khdate}">selected</c:if>>
										${dat.khdate}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					

				</table>
			</div>
			<div class="div-button">
				
				
				<a onclick="kaohe();" style="cursor: hand;" 
					
					target="_self" class="link">绩效考核</a>
					
				<input type="reset" value="重置" class="button" />
				<input type="submit" value="查询" class="button" />
			</div>
			<div class="clear" style="height: 0px;"></div>
		</form>
		<div>
			<table class="dataListTable" width="100%">
				<tr>
                    <th width="5%">
						序号
					</th>
					<th >
						姓名
					</th>
					<th>
						部门
					</th>
					<th>
						考核时间
					</th>
					<th>
						考核成绩
					</th>
					


					<th class="btnCol">
						操作
					</th>
				</tr>
				<c:forEach items="${record}" var="result" varStatus="s">
					<tr>
                        <td>
                           ${s.index+1}
                        </td>
						<td>
							<c:out value="${result.name}" />
						</td>
						<td>
							<c:out value="${result.branchname}" />
						</td>
						<td>
							<c:out value="${result.achieve_date}" />
						</td>
						<td >
							<c:out value="${result.total}" />
						</td>
						

						<td class="btnCol">
							<a
								href="<%=request.getContextPath()%>/hr/performance/Hr_performance!searchById.do?id=${result.id}&dept_id=${searchMap.dept_id}"
								>查看</a>
								<c:if test="${result.state!='1'}">
							<a href="<%=request.getContextPath()%>/hr/performance/Hr_performance!toedit.do?id=${result.id}&dept_id=${searchMap.dept_id}">修改</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="PageUpDnDiv"
			style="PADDING-TOP: 5px; padding-bottom: 5px; text-align: center">
			<span>共 ${page.lastPageNumber} 页</span>
			<span>第 ${page.thisPageNumber} 页</span>
			<a
				href="<%=request.getContextPath()%>/hr/performance/Hr_performance!searchList.do?pageno=1&audit_date=${searchMap.audit_date}&p_id=${searchMap.p_id}"
				class="linkPage">首页</a>
			<a
				href="<%=request.getContextPath()%>/hr/performance/Hr_performance!searchList.do?pageno=${page.previousPageNumber}&audit_date=${searchMap.audit_date}&p_id=${searchMap.p_id}"
				class="linkPage">上一页</a>
			<a
				href="<%=request.getContextPath()%>/hr/performance/Hr_performance!searchList.do?pageno=${page.nextPageNumber}&audit_date=${searchMap.audit_date}&p_id=${searchMap.p_id}"
				class="linkPage">下一页</a>
			<a
				href="<%=request.getContextPath()%>/hr/performance/Hr_performance!searchList.do?pageno=${page.lastPageNumber}&audit_date=${searchMap.audit_date}&p_id=${searchMap.p_id}"
				class="linkPage">尾页</a>
		</div>
	</body>
</html>