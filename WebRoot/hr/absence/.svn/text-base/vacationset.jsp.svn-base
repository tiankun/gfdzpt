<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main-in.css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
  <script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/Validator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
</head>

<script type="text/javascript">

var operationSign = "${operationSign}";
if(operationSign =="ok"){ 
	alert("保存成功。");
}

</script>

<body>
	<form id="searchForm" action="<%=request.getContextPath()%>/hr/absence/Hr_vacation_arrangement!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>节假日:</th>
			<td >
			<input type="text" name="vacationDate1" value="${searchMap.vacationDate1}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" />
			至
			<input type="text" name="vacationDate2" value="${searchMap.vacationDate2}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" />
			</td>
			<th></th>
			<td></td>
            </tr>
		</table>
	</div>
		<div class="div-button">
		    <a href="#" onclick="openDG('设置周六周天为法定假日','<%=request.getContextPath()%>/hr/absence/Hr_vacation_arrangement!alladd.do','vacationDtl')" class="link">批量添加</a>
			<a href="#" onclick="openDG('添加代码信息','<%=request.getContextPath()%>/hr/absence/Hr_vacation_arrangement!add.do','vacationDtl')" class="link">添加</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	
		<table class="dataListTable" width="100%">
			<tr>
			 <th width="20%">日期</th>
             <th width="50%">备注</th>
             <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				<td><c:out value="${result.vacation_date}" /></td>
                <td><c:out value="${result.note}" /></td>
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/hr/absence/Hr_vacation_arrangement!searchById.do?id=${result.id}','hr_vacation_arrangementDtl')">查看</a>
				<a href="#" onclick="openDG('修改详细信息','<%=request.getContextPath()%>/hr/absence/Hr_vacation_arrangement!edit.do?id=${result.id}','hr_vacation_arrangementDtl')">修改</a>
				<a href="<%=request.getContextPath()%>/hr/absence/Hr_vacation_arrangement!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	<span>共 ${page.lastPageNumber} 页</span>
	<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span> 页</span>
	<a onclick="changePage(${page.previousPageNumber})" class="linkPage">上一页</a>
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
