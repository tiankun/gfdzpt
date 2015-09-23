<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<style type="text/css">
.wordstyle {
	font-family: "宋体";
	font-size: 12px;
	font-variant: normal;
}
</style>
		<title>扫描件</title>
	</head>
	<body topmargin="0" leftmargin="0" rightmargin="0"
		style="overflow: scroll; overflow-x: hidden">
		<table width="100%" border="1" cellpadding="0" height="100%"
			cellspacing="0" class="wordstyle">
			<tr>
				<td align="center" style="padding-top: 5px;">
				   <img alt="" src="<%=request.getContextPath()%>/uploads/${folder}/${photo.pic}">
				</td>

			</tr>

			

		</table>
	</body>
</html>
