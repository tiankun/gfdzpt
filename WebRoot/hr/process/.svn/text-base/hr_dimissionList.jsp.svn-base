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
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/process/Hr_dimission!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		<tr>
		<th>申请日期:</th>
		<td>
        <input type="text" name="dim_time1" value="${searchMap.dim_time1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
		至
		<input type="text" name="dim_time2" value="${searchMap.dim_time2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
        </td>
        <c:if test="${user.branchid=='1'||user.branchid=='2'||user.branchid=='3'||user.branchid=='6'}">
		<th>部门:</th>
		<td>
		<input type="text" name="dept_name" id="dept_name" value="${searchMap.dept_name}" style="width:50%" readonly="readonly" class="textBox"/>
		<input type="text" dataType="Require" name="dept_id" id="dept_id" value="${searchMap.dept_id}" class="hidden"/>
		<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','dept_id','dept_name');" value="选择"/>
		</td>
        </c:if>
        </tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" style="color: red" onclick="openDGMax('离职申请','<%=request.getContextPath()%>/hr/process/Hr_dimission!add.do','hr_dimissionDtl')" class="link">离职申请</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			<th>序号</th>
		   <th>离职人员</th>
		   <th>申请时间</th>
           <th>审核状态</th>
		        <th width="180px">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				<td>${status.index+1}</td>	
				<td><c:out value="${p_name[result.p_id]}" /></td>	
				<td><c:out value="${result.dim_time}" /></td>	
			    <td>
			    <c:if test="${result.appstate=='-1'}"><font color="gray"></c:if>
			    <c:if test="${result.appstate!='-1'||result.appstate!='6'}"><font color="red"></c:if>
			    <c:if test="${result.appstate=='6'}"><font color="green"></c:if>
			    ${appstate[result.appstate]}
			    </font>
			    </td>
				<td width="180px">
				<c:if test="${user.branchid==2||user.duty_id==3||result.dept_id==user.branchid}">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/hr/process/Hr_dimission!searchById.do?id=${result.id}','hr_dimissionDtl')">查看申请</a>
				</c:if>
				<c:if test="${(user.branchid==2||user.duty_id==3)}">
				<a href="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.p_id}&showFlag=show&flag=hr_dimission">人员信息</a>
				</c:if>
				<!-- 
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/hr/process/Hr_dimission!edit.do?id=${result.id}','hr_dimissionDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/hr/process/Hr_dimission!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a></td>
				 -->
				<!-- 工作移交 -->
				<c:if test="${((user.branchid=='3'&&empty result.gm_affirm)||(user.branchid=='6'&&empty result.fi_affirm)||(user.branchid==result.dept_id&&empty result.dm_affirm))&&result.appstate=='4'}">
				<a style="color: red" href="<%=request.getContextPath()%>/hr/process/Hr_dimission!affirm.do?id=${result.id}&dept_id=${user.branchid}" onclick="javascript:return confirm('您确认已经移交完毕吗?');">确认</a>
				</c:if>
				<c:if test="${user.branchid=='2'&&result.appstate=='5'}"><!-- 人事确认离职通过 -->
				<a style="color: red" href="<%=request.getContextPath()%>/hr/process/Hr_dimission!affirm.do?id=${result.id}&dept_id=${user.branchid}" onclick="javascript:return confirm('您确认要通过吗?');">确认</a>
				</c:if>
				<c:if test="${user.branchid=='2'&&user.duty_id=='2'&&result.appstate=='2'}"><!-- 人事经理审批 -->
				<a style="color: red" href="#" onclick="openDG('审批','<%=request.getContextPath()%>/hr/process/Hr_dimission!audit.do?id=${result.id}','hr_dimissionDtl')">审批</a>
				</c:if>
				<c:if test="${user.duty_id=='2'&&result.appstate=='1'&&result.dept_id==user.branchid}"><!-- 部门经理审批 -->
				<a style="color: red" href="#" onclick="openDG('审批','<%=request.getContextPath()%>/hr/process/Hr_dimission!audit.do?id=${result.id}','hr_dimissionDtl')">审批</a>
				</c:if>
				<c:if test="${user.duty_id=='3'&&result.appstate=='3'}"><!-- 总经理审批 -->
				<a style="color: red" href="#" onclick="openDG('审批','<%=request.getContextPath()%>/hr/process/Hr_dimission!audit.do?id=${result.id}','hr_dimissionDtl')">审批</a>
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