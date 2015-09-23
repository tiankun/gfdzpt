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
		alert("尚未上传图片");
		window.close();
	}
}
</script>
</meta></head>
<body onload="nopic();">
	<table border="1" width="100%" class="table_border">
		<c:if test="${picsize!='0'}">
			<tr>
				<td id="td1" colspan="3" style="padding-top: 10px;">
					已上传照片
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%" border="1" class="table_border" style="border-color: black;">
						<c:forEach var="record" items="${piclist}">
							<tr bgcolor="#FFFFFF">
								<td align="center" width="33%">
									<a style="cursor: hand;"
										onClick="window.open('${pageContext.request.contextPath}/sysAdmin/Sys_uppic!displayphoto.do?id=${record.id1}&folder=${folder}',
										'newwindow','top=50, left=200,toolbar=no,menubar=no,scrollbars=yes,height=700,width=800,resizable=no,location=no, status=no')">
									<IMG
										src="<%=request.getContextPath()%>/uploads/${folder}/${record.photo1}"
										width=300 height=300>
								</td>
								<td align="center" width="33%">
									<c:if test="${record.photo2!='none'}">
									<a style="cursor: hand;"
										onClick="window.open('${pageContext.request.contextPath}/sysAdmin/ys_uppic!displayphoto.do?id=${record.id2}&folder=${folder}',
										'newwindow','top=50, left=200,toolbar=no,menubar=no,scrollbars=yes,height=700,width=800,resizable=no,location=no, status=no')">
									<IMG
										src="<%=request.getContextPath()%>/uploads/${folder}/${record.photo2}"
										width=300 height=300>
									</c:if>
								</td>
								<td align="center" width="33%">
									<c:if test="${record.photo3!='none'}">
									<a style="cursor: hand;"
										onClick="window.open('${pageContext.request.contextPath}/sysAdmin/Sys_uppic!displayphoto.do?id=${record.id3}&folder=${folder}',
										'newwindow','top=50, left=200,toolbar=no,menubar=no,scrollbars=yes,height=700,width=800,resizable=no,location=no, status=no')">
									<IMG
										src="<%=request.getContextPath()%>/uploads/${folder}/${record.photo3}"
										width=300 height=300>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</c:if>
	</table>
	<div class="div-button">
		<input type="button" value="关闭" onclick="javascript:closeDG();"/>
	</div>
</body>
</html>