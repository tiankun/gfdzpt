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
		<form id="searchForm" action="<%=request.getContextPath()%>/hr/process/Hr_apply!searchList.do" method="post">
		<div class="divMod2">
		<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>申请日期:</th>
			<td>
            <input type="text" name="apply_time1" value="${searchMap.apply_time1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			至
			<input type="text" name="apply_time2" value="${searchMap.apply_time2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
            </td>
            <c:if test="${user.branchid=='1'||user.branchid=='2'}">
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
			<c:if test="${hr_type=='6'}">
			<a href="#" style="color: red" onclick="openDGMax('转正申请','<%=request.getContextPath()%>/hr/process/Hr_apply!add.do','hr_applyDtl')" class="link">转正申请</a>
			</c:if>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
		<table class="dataListTable" width="100%">
			<tr>
			<th width="5%">序号</th>
           <th width="5%">人员</th>
           <th width="5%">部门</th>
           <th width="10%">申请提交时间</th>
           <th width="10%">审核状态</th>
		        <th width="15%">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				<td>${status.index+1}</td>
      <td width="5%"><c:out value="${result.name}" /></td>
      <td width="5%"><c:out value="${result.dept_name}" /></td>
      <td width="10%"><c:out value="${result.apply_time}" /></td>
      <td width="10%">
      <font color="<c:if test="${result.appstate!='3'}">red</c:if><c:if test="${result.appstate=='3'}">green</c:if>">
      <c:if test="${result.appstate=='6'}">延长试用</c:if>
      <c:if test="${result.appstate!='6'}">
      <c:out value="${hr_state[result.appstate]}" />
      </font>
      </c:if>
      </td>
				<td width="15%">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/hr/process/Hr_apply!searchById.do?id=${result.id}','hr_applyDtl')">查看申请</a>
				<c:if test="${user.branchid==2||user.duty_id==2||user.duty_id==3}">
				<c:if test="${result.appstate!=3}">
				<a href="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.p_id}&showFlag=show&flag=hr_apply">人员信息</a>
				</c:if>
				<c:if test="${result.appstate==3}">
				<a href="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.p_id}&showFlag=show&flag=hr_apply">人员信息</a>
				</c:if>
				</c:if>
				<c:if test="${user.branchid==2&&user.duty_id==1&&result.appstate==5}"><!-- 人事职员统计 -->
				<a style="color: red" href="#" onclick="openDGMax('查看考评信息','<%=request.getContextPath()%>/hr/process/Hr_evaluation!searchList.do?id=${result.p_id}')">考评信息</a>
				<a style="color: green" href="<%=request.getContextPath()%>/hr/process/Hr_opinion!toHrm.do?p_id=${result.p_id}&id=${result.id}&hr_state=0" onclick="javascript:return confirm('你确认要上报吗?');">上报</a>
				</c:if>
				<c:if test="${user.branchid==2&&user.duty_id==2&&result.appstate==0}"><!-- 人事经理审批 -->
				<c:if test="${empty result.ycsyq}">
				<a style="color: green" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editRegular.do?p_id=${result.p_id}&id=${result.id}&hr_state=2')">通过</a>
				<a style="color: red" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editRegular.do?p_id=${result.p_id}&id=${result.id}&hr_type=5&hr_state=4')">未通过</a>
				</c:if>
				<c:if test="${not empty result.ycsyq}">
				<a style="color: #ff9955" href="#" onclick="openDG('延长试用','<%=request.getContextPath()%>/hr/process/Hr_opinion!editTry.do?id=${result.id}&p_id=${result.p_id}&flag=regular')">修改试用信息</a>
				</c:if>
				</c:if>
				<c:if test="${user.branchid!=2&&user.duty_id==2&&result.appstate==1}"><!-- 部门经理审批 -->
				<c:if test="${empty result.ycsyq}">
				<a style="color: green" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editRegular.do?p_id=${result.p_id}&id=${result.id}&hr_state=5')">通过</a>
				<a style="color: red" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editRegular.do?p_id=${result.p_id}&id=${result.id}&hr_type=5&hr_state=4')">未通过</a>
				<a style="color: #ff9955" href="#" onclick="openDG('延长试用','<%=request.getContextPath()%>/hr/process/Hr_opinion!ycsy.do?id=${result.id}')">延长试用</a>
				</c:if></c:if>
				<c:if test="${user.duty_id==3&&result.appstate==2}"><!-- 总经理审批 -->
				<a style="color: green" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editRegular.do?p_id=${result.p_id}&id=${result.id}&hr_type=2&hr_state=3')">通过</a>
				<a style="color: red" href="#" onclick="openDG('填写意见','<%=request.getContextPath()%>/hr/process/Hr_opinion!editRegular.do?p_id=${result.p_id}&id=${result.id}&hr_type=5&hr_state=4')">未通过</a>
				</c:if>
				<c:if test="${user.branchid!=2&&user.duty_id==2&&result.appstate==5}"><!-- 部门经理选择填写考评人员 -->
				<a style="color: red" href="javascript:openDG('选择考评表填写人员','<%=request.getContextPath()%>/hr/process/Hr_evaluation!allotStaff.do?pid=${result.p_id}&dept_id=${user.branchid}')">选择考评表填写人员</a>
				</c:if>	
				</tr>
			</c:forEach>
		</table>
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