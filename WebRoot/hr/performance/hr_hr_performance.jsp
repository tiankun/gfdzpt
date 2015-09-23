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
			action="<%=request.getContextPath()%>/hr/performance/Hr_performance!allquery.do" name="form1"
			method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">

					<tr>
						
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
						<th>
							姓名:
						</th>
						<td>
							<input type="text" name="name" id="name"
								value="${searchMap.name}"  class="textBox" />
							<input type="text" name="p_id" id="p_id"
								value="" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
								value="选择" />
						</td>
					</tr>
					<c:if test="${view=='y'}">
					<tr>
						<th align="center" class="myInputTitle" style="width: 10%">
							所属机构:
						</th>
						<td style="width: 35%">
							<input type="text" name="branchname" id="branchname"
								value="${searchMap.branchname}" class="textBox" />
							<input type="text" name="dept_id" id="dept_id"
								value="" class="hidden" />
							<input type="button"
								onclick="openSelect('选择组织机构','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dept_id','branchname');"
								value="选择" />
						</td>
						<th>
							
						</th>
						<td>
							
						</td>
					</tr>
					</c:if>
					

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
				    <th width="5%">
				                   序号
				    </th>

					<th>
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
				<c:forEach items="${record}" var="result" varStatus="status">
					<tr>
                        <td>
							${status.index+1 }
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
								href="<%=request.getContextPath()%>/hr/performance/Hr_performance!look.do?id=${result.aid}&dept_id=${result.dept_id}&duty_id=${result.duty_id}"
								>查看</a>
								
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