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
	<form id="searchForm" action="<%=request.getContextPath()%>/pr/plan/Pr_plan!${skip}.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>项目:</th>
			<td>
			<input type="text" id="proname" name="proname" value="${searchMap.proname}"  class="textBox"/>
			<input type="text" id="prj_id" name="prj_id"  dataType="Double" require="false" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','prj_id','proname');" value="选择"/>
			</td>
			<th>采购月:</th>
			<td>
			<input type="text" name="purchase" value="${searchMap.purchase}"  class="textBox" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})" class="Wdate"/></td>
            </tr>
            <tr>
			<th>编号:</th>
			<td>
			<input type="text" name="bh" value="${searchMap.bh}" style="width:95%" class="textBox"/></td>
			<th>计划名称:</th>
			<td>
			<input type="text" name="name" value="${searchMap.name}" style="width:95%" class="textBox"/></td>
            </tr>
            <tr>
			<th>编制时间:</th>
			<td>
			<input type="text" name="made_date" value="${searchMap.made_date}" onfocus="WdatePicker()" style="ime-mode:disabled"  /></td>
			<th>编制人员:</th>
			<td>
			<input type="text" name="pname" id="pname"
								value="${searchMap.pname}"  class="textBox" />
							<input type="text" name="p_id" id="p_id"
								value="${searchMap.p_id}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','pname');"
								value="选择" />
			
			</td>
            </tr>
           
            <tr>
			<th>审批状态:</th>
			<td>
			<select name="pr_plan_state" class="dropdownlist"><option value="">---</option>
			<option value="1">待部门经理审批</option>
			<option value="2">审批通过</option>
			<option value="3">打回</option>
			</select></td>
			<th></th>
			<td>
			</td>
			</tr>
			
		</table>
	</div>
		<div class="div-button">
			<%--<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/pr/plan/Pr_plan!addfromDS.do','pr_planDtl')" class="link">从设计添加</a>
			--%><a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/pr/plan/addMaterielNew.jsp','pr_planDtl')" class="link">添加</a>
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
           <th>计划采购月</th>
           <th>编号</th>
           <th>计划名称</th>
           <th>编制时间</th>
           <th>编制人员</th>
           <th>审批状态</th>
           
           
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td><c:out value="${status.index+1}" /></td>
      <td><c:out value="${result.proname}" /></td>
      <td><c:out value="${result.purchase}" /></td>
      <td><c:out value="${result.bh}" /></td>
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.made_date}" /></td>
      <td><c:out value="${result.madeperson}" /></td>
      <td><c:out value="${result.state}" /></td>
      
      
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/pr/plan/Pr_plan!searchById.do?id=${result.id}','pr_planDtl')">查看</a>
				<c:if test="${result.pr_plan_state=='1'&&type=='audit'}">
				<a href="#" onclick="openDG('审批','<%=request.getContextPath()%>/pr/plan/Pr_plan!searchById.do?id=${result.id}&flag=check','pr_planDtl')">审批</a>
				</c:if>
				<c:if test="${result.made_person==user.base_info_id&&(result.pr_plan_state=='3'||result.pr_plan_state=='0')}">
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/pr/plan/Pr_plan!edit.do?id=${result.id}','pr_planDtl')">修改</a>
				</c:if>
				
				<c:if test="${result.made_person==user.base_info_id&&result.pr_plan_state=='0'}">
				<a href="<%=request.getContextPath()%>/pr/plan/Pr_plan!lastsub.do?id=${result.id}" OnClick="javascript:return confirm('你确认要提交吗?');">提交</a>
				
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