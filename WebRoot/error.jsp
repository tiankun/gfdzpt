<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" language="java"%>
<%@ page isErrorPage="true" %> 
<html>
<HEAD>
<title>十分抱歉，您要查看的网页当前已过期，或已被更名或删除！</title>
<STYLE type=text/css></STYLE>
<LINK type=text/css rel=stylesheet>
<STYLE type=text/css>
BODY {
	FONT-SIZE: 9pt; COLOR: #842b00; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: none
}
TABLE {
	FONT-SIZE: 9pt; COLOR: #842b00; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: none
}
TD {
	FONT-SIZE: 9pt; COLOR: #842b00; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: none
}
BODY {
	SCROLLBAR-HIGHLIGHT-COLOR: buttonface; SCROLLBAR-SHADOW-COLOR: buttonface; SCROLLBAR-3DLIGHT-COLOR: buttonhighlight; SCROLLBAR-TRACK-COLOR: #eeeeee; BACKGROUND-COLOR: #ffffff
}
A {
	FONT-SIZE: 9pt; COLOR: #842b00; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: none
}
A:hover {
	FONT-SIZE: 9pt; COLOR: #0188d2; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: underline
}
H1 {
	FONT-SIZE: 9pt; FONT-FAMILY: "Tahoma", "宋体"
}
.style1 {font-size: 10pt}
</STYLE>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false">
<TABLE cellSpacing=0 cellPadding=0 width=540 align=center border=0>
  <TR>
    <TD height=245 colspan="2" vAlign=top>
      <DIV align=center><BR><IMG height=250 src="${pageContext.request.contextPath}/image/error.png" width=800/><BR>
      <BR>
      </DIV></TD></TR>
  <TR>
    <TD height=95 colspan="2"><HR noShade SIZE=0>
      <H2>系统消息:<span id="err_msg">无法找到该页，请刷新或返回之前页面</span></H2>
      <p>解决方法:</p>
      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、登录超时，请重新登录系统；</p>
      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、无访问权限，请联系管理员。</p>
      <HR noShade SIZE=0></TD>
  <TR>
    <TD width="264" height="46" align="right">
      <TABLE cellSpacing=0 cellPadding=0 width=94 border=0>
        <TR>
          <TD width=7 height="25" background="${pageContext.request.contextPath}/images/left.gif"></TD>
          <TD width="79" background="${pageContext.request.contextPath}/images/bgE.gif">
            <DIV align=center><FONT class=p6><A 
            href="javascript:history.go(-1)" class="style1">返回</A></FONT> </DIV></TD>
          <TD width=7 background="${pageContext.request.contextPath}/images/right.gif">&nbsp;</TD>
      </TR></TABLE>&nbsp;&nbsp;&nbsp;&nbsp;</TD>
	<TD width="276" height="46" >
</BODY>
<script type="text/javascript">
	var err = getQueryString("err");
	if (err==1) {
		document.getElementById("err_msg").innerText=("登录过期,请重新登录。");
	}else if(err==3){
		document.getElementById("err_msg").innerText=("对不起，您没有访问该功能的权限。");
	}else if(err==-1){
		document.getElementById("err_msg").innerText=("您需要登录系统才可访问系统。");
	}else if(err==2){
		document.getElementById("err_msg").innerText=("对不起，您访问的功能不存在。");
	}else if(err==4){
		document.getElementById("err_msg").innerText=("系统发生意外错误，请联系系统管理员。");
	}
	else {
	}

function getQueryString(name) {
	if (location.href.indexOf("?") == -1
			|| location.href.indexOf(name + '=') == -1) {
		return '';
	}
	// 获取链接中参数部分
	var queryString = location.href.substring(location.href.indexOf("?") + 1);
	// 分离参数对 ?key=value&key2=value2
	var parameters = queryString.split("&");
	var pos, paraName, paraValue;
	for ( var i = 0; i < parameters.length; i++) {
		// 获取等号位置
		pos = parameters[i].indexOf('=');
		if (pos == -1) {
			continue;
		}
		// 获取name 和 value
		paraName = parameters[i].substring(0, pos);
		paraValue = parameters[i].substring(pos + 1);
		// 如果查询的name等于当前name，就返回当前值，同时，将链接中的+号还原成空格
		if (paraName == name) {
			return unescape(paraValue.replace(/\+/g, " "));
		}
	}
	return '';
};
</script>
</html>