<%@ page contentType="text/html; charset=GBK" errorPage=""%>
<%
			String ok = (String)request.getAttribute("ok");
			if ("ok".equals(ok)) {
				out.print("<script language='javascript'>alert('修改密码成功！')</script>");
			}else if ("fail1".equals(ok)) {
				out.print("<script language='javascript'>alert('原密码不正确，修改密码失败！')</script>");
			}else if ("fail12".equals(ok)) {
				out.print("<script language='javascript'>alert('两次密码不统一，修改密码失败！')</script>");
			}				
%>
<html>
	<head>
		<title>修改用户密码</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<script language="javascript">
        function validte(){
	              if(document.ModifySysUser.oldPassword.value==""){
					 alert("请输入您的原密码");
					 return false;
				 }
		
	              if(document.ModifySysUser.userPassword.value==""){
					 alert("请输入新密码");
					 return false;
				 }
                  if(document.ModifySysUser.checkPassword.value!=
                   document.ModifySysUser.userPassword.value){
                    alert("两次密码值不相同!");
                    return false;
                 }
                 document.ModifySysUser.action="${pageContext.request.contextPath}/sysAdmin/Login!modifyPass.do";
                 document.ModifySysUser.submit();
				 return true;
        }
</script>
	<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
}
-->
    </style></head>
	<body>
		<center>
		<br><br>
			<form method="POST" name="ModifySysUser">

				<TABLE align=center border=1 borderColorDark=#ffffff borderColorLight=rgb(191,231,178) cellPadding=0 cellSpacing=0 width="39%">
					<TR  class="topbg">
						<TD height=29 colSpan=2 align=center class="topbg"><strong>
						 <font color="black">修改用户密码信息</font> 
						</strong></TD>
					</TR>
					<tr >
						<td width="36%" height="24" align="center" class="tabField">
							用户名：
						</td>
					  <td width="64%">
							&nbsp;${user.log_name}
                            <input name="type" type="hidden" value="update">
</td>
					</tr>
					<tr >
						<td height="24" align="center" class="tabField">
							旧密码：
						</td>
						<td >
							&nbsp;<input name="oldPassword" type="password" id="oldPassword">
						</td>
					</tr>					
					<tr>
						<td height="24" align="center" class="tabField">
							新密码：
						</td>
						<td >
							&nbsp;<input name="userPassword" type="password" id="userPassword">
						</td>
					</tr>
					<tr>
						<td height="24" align="center" class="tabField">
							确认密码：
						</td>
						<td>
							&nbsp;<input type="password" name="checkPassword" />
						</td>
					</tr>
					<tr >
						<td HEIGHT="29"  colspan="2" align="center" class="tabField">
							<input type="button" name="Submit" value=" 保　存 " onClick="return validte();">
							&nbsp;
							<input type="reset" value=" 清 除 ">
						</td>
					</tr>
			  </table>
			</form>
		</center>
	</body>
</html>
