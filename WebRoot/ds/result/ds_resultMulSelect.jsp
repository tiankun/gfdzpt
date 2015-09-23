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
<script type="text/javascript">
	function mulData(data){
		//去空格
		var nospace= data.replace(new RegExp(" ","g"),"");
		//"="转":"
		var newdata = nospace.substring(1,nospace.length-1);
		var data1 = newdata.split(",");
		var alldata = '{';
		$.each(data1,function(key,value){
			var data2 = value.split("=");
			alldata = alldata+"\"" + data2[0] +"\":\"" + data2[1] + "\"," ; 
		})
		alldata = alldata.substring(0,alldata.length-1) + '}';
		return alldata;
	}
</script>
</head>
<body>	
	<form id="searchForm" action="<%=request.getContextPath()%>/ds/result/Ds_result!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>项目:</th>
			<td>
			<input type="text" name="proj_name" value="${searchMap.proj_name}" style="width:95%" class="textBox"/></td>
			<th>设计类型:</th>
			<td>
			<select name="ds_type" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${ds_type}" var="ds_type">
			<option value="${ds_type.key}" <c:if test="${searchMap.ds_type==ds_type.key}">selected</c:if>>${ds_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th>编码:</th>
			<td>
			<input type="text" name="code" value="${search.code}" class="textBox" style="width:95%"/></td>
			<th>编制日期:</th>
			<td>
            <input type="text" name="compile_time1" value="${search.compile_time1}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
			至
			<input type="text" name="compile_time2" value="${search.compile_time2}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:30%" class="textBox"/>
            </td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			<th>序号</th>
           <th>项目名称</th>
           <th>设计类型</th>
           <th>编号</th>
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				<td>${status.index+1}</td>
      <td><c:out value="${result.proj_name}" /></td>
      <td><c:out value="${ds_type[result.ds_type]}" /></td>
      <td><c:out value="${result.num}" /></td>
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/ds/result/Ds_result!searchById.do?id=${result.id}','ds_resultDtl')">查看</a>
				<a href="#" onclick="retSelect('${result.id}',mulData('${result}'));">选择</a></td>
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