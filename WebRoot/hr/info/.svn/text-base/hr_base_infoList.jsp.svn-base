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
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>姓名:</th>
			<td>
			<input type="text" name="name" style="width:95%" class="textBox"/></td>
			<th>部门id:</th>
			<td>
			<select name="dept_id" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${dept_id}" var="dept_id">
			<option value="${dept_id.key}" >${dept_id.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th>出生日期:</th>
			<td>
			<input type="text" name="birthday" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
			<th>进入公司时间:</th>
			<td>
			<input type="text" name="jr_time" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<c:if test="${user.branchid==2&&user.duty_id==1}">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/hr/info/Hr_base_info!add.do','hr_base_infoDtl')" class="link">添加</a>
			</c:if>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
           <th>离职时间</th>
           <th>登陆id(对应于sysuser的id)</th>
           <th>英语水平</th>
           <th>主键</th>
           <th>姓名</th>
           <th>性别</th>
           <th>部门id</th>
           <th>岗位</th>
           <th>邮箱</th>
           <th>曾用名</th>
           <th>工作年限</th>
           <th>民族</th>
           <th>出生日期</th>
           <th>证件类型</th>
           <th>证件号码</th>
           <th>政治面貌</th>
           <th>婚姻状况</th>
           <th>家庭电话</th>
           <th>进入公司时间</th>
           <th>录入时间</th>
           <th>任职状态</th>
           <th>户口类型</th>
           <th>户籍地</th>
           <th>住址</th>
           <th>爱好</th>
           <th>照片</th>
           <th>证件照</th>
           <th>备注(值为1表示驾驶员)</th>
           <th>移动电话</th>
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td><c:out value="${result.lz_time}" /></td>
      <td><c:out value="${result.dengl_id}" /></td>
      <td><c:out value="${result.english}" /></td>
      <td><c:out value="${result.id}" /></td>
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.sex}" /></td>
      <td><c:out value="${dept_id[result.dept_id]}" /></td>
      <td><c:out value="${result.duty_id}" /></td>
      <td><c:out value="${result.email}" /></td>
      <td><c:out value="${result.old_name}" /></td>
      <td><c:out value="${result.work_year}" /></td>
      <td><c:out value="${result.nation}" /></td>
      <td><c:out value="${result.birthday}" /></td>
      <td><c:out value="${result.card_type}" /></td>
      <td><c:out value="${result.card_num}" /></td>
      <td><c:out value="${result.zheng_zhi}" /></td>
      <td><c:out value="${result.marriage}" /></td>
      <td><c:out value="${result.home_numb}" /></td>
      <td><c:out value="${result.jr_time}" /></td>
      <td><c:out value="${result.lr_time}" /></td>
      <td><c:out value="${result.hr_type}" /></td>
      <td><c:out value="${result.hu_kou}" /></td>
      <td><c:out value="${result.hu_ji}" /></td>
      <td><c:out value="${result.addr}" /></td>
      <td><c:out value="${result.habbit}" /></td>
      <td><c:out value="${result.pic}" /></td>
      <td><c:out value="${result.card_pic}" /></td>
      <td><c:out value="${result.remark}" /></td>
      <td><c:out value="${result.phone}" /></td>
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchById.do?id=${result.id}','hr_base_infoDtl')">查看</a>
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/hr/info/Hr_base_info!edit.do?id=${result.id}','hr_base_infoDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/hr/Hr_base_info!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a></td>
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