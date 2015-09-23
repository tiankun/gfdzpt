<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>官房电子工作平台</title>

		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<style type="text/css">
.input {
	border-right: #c1d2d5 1px solid;
	border-top: #c1d2d5 1px solid;
	border-left: #c1d2d5 1px solid;
	width: 120px;
	line-height: 18px;
	border-bottom: #c1d2d5 1px solid;
	height: 23px;
}

.error {
	color: #902d2d;
}

.title {
	font-weight: bolder;
	font-size: 22px;
	color: #707b81;
	font-family: "黑体";
}

.btn_log {
	border: 0;
	background-image: url(../image/login/lgnBtn.gif);
	width: 141px;
	height: 34px;
}

.btn3_mouseout {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #C3DAF5 );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}
</style>
	</head>
	<body scroll="no"
		style="background-image: url(../image/login/bg.gif); margin: 0; font-size: 12px; overflow: hidden;">
		<embed id="s_simnew31" type="application/npsyunew3-plugin"
			hidden="true">
		</embed>

		<SCRIPT LANGUAGE=javascript>

var digitArray = new Array('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f');

function toHex( n ) {

        var result = ''
        var start = true;

        for ( var i=32; i>0; ) {
                i -= 4;
                var digit = ( n >> i ) & 0xf;

                if (!start || digit != 0) {
                        start = false;
                        result += digitArray[digit];
                }
        }

        return ( result == '' ? '0' : result );
}


</SCRIPT>

		<div style="margin: 200px auto; width: 910px; position: relative;">
			<div style="background: url(../image/login/login_03.gif) repeat-x; position: absolute; top: 0; left: 0px; width: 390px; height: 259px;">
			
			</div>
			<div
				style="background: url(../image/login/login_02.gif) repeat-x; position: absolute; top: 0; left: 390px; width: 520px; height: 259px;">
				<form name="form1"
					action="<%=request.getContextPath()%>/sysAdmin/Login!login.do"
					onSubmit="return checkForm()" method="post" style="margin: 0;">
					<input name="KeyID" type="hidden" id="KeyID">
					<table cellspacing="0" cellpadding="0" width="320px" border="0"
						style="margin-top: 40px;">
						<tr>
							<td width="80" height="34" align="right"
								style="font-weight: bold">
								<img height="14" src="../image/login_dot1.gif" width="14"
									align="absMiddle">
								&nbsp;用户名：
							</td>
							<td align="left" width="190">
								<input type="text" name="logName" id="logName"
									style="width: 140px;" class="input">
							</td>
						</tr>
						<tr>
							<td width="80" height="34" align="right"
								style="font-weight: bold">
								<img height="14" src="../image/login_dot2.gif" width="14"
									align="absMiddle">
								&nbsp;密 码：
							</td>
							<td align="left">
								<input type="password" name="password" style="width: 140px;"
									class="input" id="password">
							</td>
						</tr>
						
						<tr>
							<td width="80" height="34" align="right"
								style="font-weight: bold">
								<img height="14" src="../image/quicksearch.gif" width="14"
									align="absMiddle">
								&nbsp;验证码：
							</td>
							<td align="left">
								<input type="text" name="code" maxLength=4 size=10
									style="width: 70px;" class="input" />
								<img id="yzmImg" onclick="changeImg()"
									src="<%=request.getContextPath()%>/sysAdmin/image.jsp"
									alt="看不清，点击刷新" title="看不清，点击刷新" align="absMiddle" height="22px"
									style="cursor: hand;">
							</td>
						</tr>
						<tr>
							<td height="20" align="left" colspan="2"
								style="font-weight: bold">

								登录本系统前请先安装
								<b>&nbsp;&nbsp; <a
									href="<%=request.getContextPath()%>/qudong/SetUp.exe">密码锁驱动程序</a>
									<a
									href="<%=request.getContextPath()%>/qudong/install_lodop32.exe">打印驱动</a>
								</b>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="padding-top: 13px; padding-left: 70px;"
								height="80px">
								<input type="submit" value="" class="btn_log"
									onclick="return checkForm()">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div
				style="background: url(../image/login/login_05.gif) repeat-x; position: absolute; top: 0; left: 910px; width: 390px; height: 259px;"></div>
		</div>
	</body>
	<script language="javascript" type="text/JavaScript">
function changeImg() {
	var img = document.form1.yzmImg;
	var url = "<%=request.getContextPath()%>/sysAdmin/image.jsp?t="
			+ Math.random();
	img.src = url;
}
function checkForm() {
	<%--
	try {
		var DevicePath, mylen, ret;
		var s_simnew31;
		//创建插件或控件
		if (navigator.userAgent.indexOf("MSIE") > 0
				&& !navigator.userAgent.indexOf("opera") > -1) {
			s_simnew31 = new ActiveXObject("Syunew3A.s_simnew3");
		} else {
			s_simnew31 = document.getElementById('s_simnew31');
		}
		DevicePath = s_simnew31.FindPort(0);//'来查找加密锁，0是指查找默认端口的锁
		if (s_simnew31.LastError != 0) {
			window.alert("未发现加密锁，请插入加密锁");
			return false;
		} else {
			//'读取锁的ID
			form1.KeyID.value = s_simnew31.YReadString(50, 40, "FFFFFFFF", "FFFFFFFF", DevicePath);
			if (s_simnew31.LastError != 0) {
				window.alert("获取用户名错误,错误码是" + s_simnew31.LastError.toString());
				return;
			}
			return true;
		}
	}

	catch (e) {
		alert(e.name + ": " + e.message + "。可能是没有安装相应的控件或插件");
		return false;
	}
--%>
	if (document.form1.logName.value == '') {
		alert("请输入用户名");
		document.form1.logName.focus();
		return false;
	}
	if (document.form1.password.value == '') {
		alert("请输入密码");
		document.form1.password.focus();
		return false;
	}
	if (document.form1.code.value == '') {
		alert("请输入验证码");
		document.form1.code.focus();
		return false;
	}
	return true;
}
</script>

	<%
		String errorType = (String) request.getAttribute("errorType");
		if (errorType != null && !"".equals(errorType)) {
	%>
	<SCRIPT language="javascript">
alert('<%=errorType%>');
</SCRIPT>
	<%
		}
	%>
	<script type="text/javascript">
document.form1.logName.focus();</script>
</html>
