<%@ page contentType="text/html;charset=GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<script language="JavaScript" src="${pageContext.request.contextPath}/scripts/status.js"></script>
<script type="text/javascript">	
//关闭窗口
function alsub(){
	alert("您已经提交过了！");
	window.location.href="<%=request.getContextPath()%>/hr/process/Hr_apply!searchList.do";
}
</script>     
<head>
</head>
<body onload="alsub();">
</body>
</html>