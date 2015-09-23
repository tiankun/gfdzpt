<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>官房电子办公平台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link href="../jscripts/ligerUI/skins/Aqua/css/ligerui-all.css"
			rel="stylesheet" type="text/css" />
		<script src="../jscripts/jquery.js" type="text/javascript">
</script>
		<script src="../jscripts/ligerUI/js/ligerui.min.js"
			type="text/javascript">
</script>
		<script src="../jscripts/ligerUI/js/plugins/ligerTab.js"
			type="text/javascript">
</script>
		<script type="text/javascript">

</script>
		<%
			Map user = (Map) request.getSession().getAttribute("user");
		%>
		<style type="text/css">
body,html {
	height: 100%;
}

body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}
</style>
<script type="text/javascript">
            function f_overrideTab(tabid,text, url)
            {
            	var tab = null;
            	tab = $("#framecenter").ligerGetTabManager();
            	tab.selectTabItem("home1");
                var currentTabid=tab.getSelectedTabItemID();
                tab.overrideTabItem(currentTabid, { tabid: tabid, url: url, text: text });
            }
     </script> 
	</head>
	<body style="padding: 0px;overflow-y: scroll;" >
		<table width="100%" align="center">
			<tr>
				<td height="10">

				</td>
			</tr>

			<tr>
				<td style="padding-left: 25px;">
					<img alt="" src="<%=request.getContextPath()%>/image/m2.jpg">
					&nbsp;&nbsp;
					<strong
						style="padding-bottom: 10px; font-weight: bolder; font-size: 15px;"><%=user.get("user_name")%>：您好，欢迎登陆官房电子办公平台！</strong>

				</td>

			</tr>

			<tr>
				<td>
					<img alt="" src="<%=request.getContextPath()%>/image/m1.gif" width="100%">
				</td>
			</tr>

			<tr>
				<td>
					<table width="100%">
						<tr>
							<td style="padding-left: 25px; padding-top: 25px;" width="320">
								<table width="320">
									<tr>
										<td>
											<img style="border: 1px silver solid; float: left;" alt=""
												src="<%=request.getContextPath()%>/image/p.jpg">
										</td>
										<td>
											<table style="line-height: 20px;">

												<tr>
													<td width="300">
														<strong style="font-weight: bolder;font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;姓名：${baseinfo.name}</strong>
													</td>
													
												</tr>
												<tr>
													<td height="10" >
													</td>
												</tr>
												<tr>
													<td>
														<strong style="font-weight: bolder;font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;部门：${baseinfo.branchname}</strong>
													</td>
													
												</tr>
												<tr>
													<td height="10" >
													</td>
												</tr>
												<tr>
													<td>
														<strong style="font-weight: bolder;font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;职务：<c:if test="${baseinfo.duty_id=='1'}">职员</c:if><c:if test="${baseinfo.duty_id=='2'}">部门经理</c:if><c:if test="${baseinfo.duty_id=='3'}">总经理</c:if><c:if test="${baseinfo.duty_id=='4'}">部门经理</c:if><c:if test="${baseinfo.duty_id=='5'}">部门经理</c:if></strong>
													</td>
													
												</tr>
												<tr>
													<td height="10" >
													</td>
												</tr>
												<tr>
													<td>
														<strong style="font-weight: bolder;font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;工作时间：${baseinfo.work_year}</strong>
													</td>
													
												</tr>
											</table>
										</td>
									</tr>
								</table>

							</td>
							<td>
                              <img src="<%=request.getContextPath() %>/image/m3.jpg" height="100"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td height="10">
					
				</td>
			</tr>
			
			<tr>
				<td>
					<img alt="" src="<%=request.getContextPath()%>/image/m1.gif" width="100%">
				</td>
			</tr>
			
			<tr>
			   <td style="padding-left: 25px;padding-top: 5px;">
			     <img alt="" src="<%=request.getContextPath()%>/image/m4.gif">
					&nbsp;&nbsp;
					<strong
						style="padding-bottom: 10px; font-weight: bolder; font-size: 15px;">待办事项</strong>
			     
			   </td>
			</tr>
			
			<tr>
			   <td width="100%" height="100" valign="top" style="padding-left: 25px;">
			     <table width="100%" height="50">
			       <c:if test="${hasAbsenceNotice=='1'}">
			       <tr>
			         <td>
			                         <a onclick="f_overrideTab('0000','请假审批','/hr/absence/Hr_absence!auditList.do')" ><strong style="font-weight: bolder;"> 您有&nbsp;&nbsp;${absencecount}&nbsp;&nbsp;条请假申请待审批！</strong></a>
			         </td>
			       </tr>
			       </c:if>
			     
			     </table>
			   </td>
			</tr>
			
			<tr>
				<td height="10">
					
				</td>
			</tr>
			
			<tr>
				<td>
					<img alt="" src="<%=request.getContextPath()%>/image/m1.gif" width="100%">
				</td>
			</tr>
			
			
			
			<tr>
				 <td style="padding-left: 25px;padding-top: 5px;">
					<img alt="" src="<%=request.getContextPath()%>/image/m5.jpg">
					<strong
						style="padding-bottom: 10px; font-weight: bolder; font-size: 15px;">最新信息动态</strong>
				</td>
			</tr>
			
			<tr>
			   <td width="100%" height="100">
			     <table width="100%" height="50">
			     
			     </table>
			   </td>
			</tr>
			
		</table>
	</body>
</html>
