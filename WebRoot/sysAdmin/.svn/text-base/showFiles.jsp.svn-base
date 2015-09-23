<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
	<script type="text/javascript" src="../jscripts/status.js"></script>
	<script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
	<script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
<script type="text/javascript">	
//关闭窗口
function nopic(){
	if(${picsize}=='0'){
		alert("尚未上传文件");
		closeDG();
	}
}
</script>
</meta></head>
<body onload="nopic();">
	<div class="divMod2">
	<div class="divMod1">已上传文件</div>
	<table border="0" width="100%" class="dataListTable">
		<c:if test="${picsize!='0'}">
				<tr>
					<th align="center" width="10%">
					</th>
					<th align="center" width="80%">
						文件名
					</th>
					<th align="center" width="10%">
						操作
					</th>
				</tr>
			<c:forEach var="record" items="${filelist}">
				<tr>
					<td width="10%">
					<img height="38px" width="38px" src="<%=request.getContextPath()%>/image/filetype/${record.ext}.png"/>
					</td>
					<td width="80%">
						<a href="<%=request.getContextPath()%>/uploads/${folder}/${record.pic}">${record.name}</a>
					</td>
					<td width="10%">
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	</div>
	<div class="div-button">
		<input type="button" value="关闭" onclick="javascript:closeDG();"/>
	</div>
</body>
</html>