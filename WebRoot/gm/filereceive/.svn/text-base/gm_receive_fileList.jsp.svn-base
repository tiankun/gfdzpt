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
	<form id="searchForm" action="<%=request.getContextPath()%>/gm/filereceive/Gm_receive_file!searchList.do" method="post">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>文件名称:</th>
			<td>
			<input type="text" name="filename" style="width:95%" class="textBox" value="${searchMap.filename}"/></td>
			<th>文件编号:</th>
			<td>
			<input type="text" name="filenum" style="width:95%" class="textBox" value="${searchMap.filenum}"/></td>
            </tr>
            <tr>
			<th>收发件人:</th>
			<td>
			<input type="text" name="name" id="name"
								value="${searchMap.name}"  class="textBox" />
							<input type="text" name="p_id" id="p_id"
								value="${searchMap.p_id}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
								value="选择" />
            </td>
			<th>收发日期:</th>
			<td>
			<input type="text" name="rdate" value="${searchMap.rdate}" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBox"/></td>
            </tr>
		</table>
	</div>
		<div class="div-button">
		<c:if test="${deptid=='3'}">
		<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/gm/filereceive/Gm_receive_file!add.do','gm_receive_fileDtl')" class="link">添加</a>
		</c:if>
			
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
           <th>序号</th>
           <th>文件名称</th>
           <th>文件编号</th>
           <th>收/发件份数</th>
           <th>收发件人</th>
           <th>收发日期</th>
           <th>状态</th>
           
		        <th class="btnCol">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td>${status.index+1}</td>
      <td><c:out value="${result.filename}" /></td>
      <td><c:out value="${result.filenum}" /></td>
      <td><c:out value="${result.num}" /></td>
      <td><c:out value="${result.bname}" /></td>
      <td><c:out value="${result.rdate}" /></td>
      <td><c:out value="${result.sstate}" /></td>
     
				<td class="btnCol">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/gm/filereceive/Gm_receive_file!searchById.do?id=${result.id}','gm_receive_fileDtl')">查看</a>
				<c:if test="${result.state=='1'&&deptid=='3'}">
				<a href="<%=request.getContextPath()%>/gm/filereceive/Gm_receive_file!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
				</c:if>
				
				<c:if test="${result.state=='1'&&deptid!='3'}">
				<a href="<%=request.getContextPath()%>/gm/filereceive/Gm_receive_file!sub.do?id=${result.id}" OnClick="javascript:return confirm('你确认接收吗?');">确认接收</a>
				</c:if>
				
				</td>
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