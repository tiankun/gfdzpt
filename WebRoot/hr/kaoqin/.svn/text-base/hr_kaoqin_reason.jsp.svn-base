<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title></title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css"
			type="text/css"></link>
		<script src="<%=request.getContextPath()%>/jscripts/jquery.js"
			type="text/javascript">
</script>
		<script
			src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"
			type="text/javascript">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>

	</head>
	<body>
		<div>
		<br/>
		<div align="left">
		<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!searchPersonList.do">
	    <input type="hidden" name="kqdate" value="${kqdate}"/>
	    <input type="submit" value="返回人员列表" class="button"  onclick="sub();"/>
	    </form>
	    </div>
			<table width="100%">
				<tr>
					<td colspan="4" align="center">
						<strong style="font-size: 15px;"><font color="red">${name
								}</font>(${kqdate})考勤统计</strong>
					</td>
				</tr>

				<tr>
					<td colspan="4" height="10">
					</td>
				</tr>

				<tr>
					<td colspan="4" width="100%">
						<table    width="100%">
							<tr align="center">
								<td>
									星期日
								</td>
								<td>
									星期一
								</td>
								<td>
									星期二
								</td>
								<td>
									星期三
								</td>
								<td>
									星期四
								</td>
								<td>
									星期五
								</td>
								<td>
									星期六
								</td>
							</tr>

							<tr>
							  <td colspan="7" height="7">
							  </td>
							</tr>
							
							<c:forEach items="${reslist}" var="res">
							  <tr>
								<td style="border: 1px; <c:if test="${res.ch0=='1' }"> background-color: silver;</c:if>"  >
									<table width="100%"  class="dataListTable" >
										<tr>
											<td colspan="2" height="10">
												${res.date0 }&nbsp;
											</td>
										</tr>
										<tr>
											<td width="50%" height="10">
												上午
											</td>
											<td width="50%">
												下午
											</td>
										</tr>
										<tr>
											<td height="50" >
											    <c:if test="${res.s0 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=am&day=${res.date0 }&yemo=${kqdate }','hr_kaoqinDtl')">
												   <c:if test="${res.anote0 != null && res.anote0 !=''}">${res.s0 } <br /> ${res.stime0 }</c:if>
												   <c:if test="${res.anote0 == null || res.anote0 ==''}">	<font color="red"> ${res.s0 } <br /> ${res.stime0 } </font> </c:if>
													
													</a>
												</c:if>
											
											<td>
											    <c:if test="${res.x0 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=pm&day=${res.date0 }&yemo=${kqdate }','hr_kaoqinDtl')">
													
													<c:if test="${res.pnote0 != null && res.pnote0 !=''}">${res.x0 } <br /> ${res.xtime0 }</c:if>
												   <c:if test="${res.pnote0 == null || res.pnote0 ==''}">	<font color="red"> ${res.x0 } <br /> ${res.xtime0 } </font> </c:if>
													
													</a>
												</c:if>

											</td>
										</tr>
										
									</table>
								</td>

								<td style="border: 1px; <c:if test="${res.ch1=='1' }"> background-color: silver;</c:if>"  >
									<table width="100%"  class="dataListTable" >
										<tr>
											<td colspan="2" height="10">
												${res.date1 }&nbsp;
											</td>
										</tr>
										<tr>
											<td width="50%" height="10">
												上午
											</td>
											<td width="50%">
												下午
											</td>
										</tr>
										<tr>
											<td height="50">
											    <c:if test="${res.s1 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=am&day=${res.date1 }&yemo=${kqdate }','hr_kaoqinDtl')">
													 <c:if test="${res.anote1 != null && res.anote1 !=''}">${res.s1 }<br /> ${res.stime1 }</c:if>
												   <c:if test="${res.anote1 == null || res.anote1 ==''}">	<font color="red"> ${res.s1 } <br /> ${res.stime1 } </font> </c:if>
													
													
													</a>
												</c:if>
											</td>
											<td>
											    <c:if test="${res.x1 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=pm&day=${res.date1 }&yemo=${kqdate }','hr_kaoqinDtl')">
													<c:if test="${res.pnote1 != null && res.pnote1 !=''}">${res.x1 } <br /> ${res.xtime1 }</c:if>
												   <c:if test="${res.pnote1 == null || res.pnote1 ==''}">	<font color="red"> ${res.x1 } <br /> ${res.xtime1 } </font> </c:if>
													 </a>
												</c:if>

											</td>
										</tr>
										
									</table>
								</td>
								
								<td style="border: 1px; <c:if test="${res.ch2=='1' }"> background-color: silver;</c:if>"  >
									<table width="100%"  class="dataListTable" >
										<tr>
											<td colspan="2" height="10">
												${res.date2 }&nbsp;
											</td>
										</tr>
										<tr>
											<td width="50%" height="10">
												上午
											</td>
											<td width="50%">
												下午
											</td>
										</tr>
										<tr>
											<td height="50">
											    <c:if test="${res.s2 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=am&day=${res.date2 }&yemo=${kqdate }','hr_kaoqinDtl')">
													 <c:if test="${res.anote2 != null && res.anote2 !=''}">${res.s2 }<br /> ${res.stime2 }</c:if>
												   <c:if test="${res.anote2 == null || res.anote2 ==''}">	<font color="red"> ${res.s2 } <br /> ${res.stime2 } </font> </c:if>
													 
													</a>
												</c:if>
											</td>
											<td>
											    <c:if test="${res.x2 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=pm&day=${res.date2 }&yemo=${kqdate }','hr_kaoqinDtl')">
													<c:if test="${res.pnote2 != null && res.pnote2 !=''}">${res.x2 } <br /> ${res.xtime2 }</c:if>
												   <c:if test="${res.pnote2 == null || res.pnote2 ==''}">	<font color="red"> ${res.x2 } <br /> ${res.xtime2 } </font> </c:if>
													 </a>
												</c:if>

											</td>
										</tr>
										
									</table>
								</td>
								
								<td style="border: 1px; <c:if test="${res.ch3=='1' }"> background-color: silver;</c:if>"  >
									<table width="100%"  class="dataListTable" >
										<tr>
											<td colspan="2" height="10">
												${res.date3 }&nbsp;
											</td>
										</tr>
										<tr>
											<td width="50%" height="10">
												上午
											</td>
											<td width="50%">
												下午
											</td>
										</tr>
										<tr>
											<td height="50">
											    <c:if test="${res.s3 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=am&day=${res.date3 }&yemo=${kqdate }','hr_kaoqinDtl')">
													 <c:if test="${res.anote3 != null && res.anote3 !=''}">${res.s3 }<br /> ${res.stime3 }</c:if>
												   <c:if test="${res.anote3 == null || res.anote3 ==''}">	<font color="red"> ${res.s3 } <br /> ${res.stime3 } </font> </c:if>
												   </a>
													
												</c:if>
											</td>
											<td>
											    <c:if test="${res.x3 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=pm&day=${res.date3 }&yemo=${kqdate }','hr_kaoqinDtl')">
													<c:if test="${res.pnote3 != null && res.pnote3 !=''}">${res.x3 } <br /> ${res.xtime3 }</c:if>
												   <c:if test="${res.pnote3 == null || res.pnote3 ==''}">	<font color="red"> ${res.x3 } <br /> ${res.xtime3 } </font> </c:if>
													 </a>
												</c:if>

											</td>
										</tr>
										
									</table>
								</td>
								
								<td style="border: 1px; <c:if test="${res.ch4=='1' }"> background-color: silver;</c:if>"  >
									<table width="100%"  class="dataListTable" >
										<tr>
											<td colspan="2" height="10">
												${res.date4 }&nbsp;
											</td>
										</tr>
										<tr>
											<td width="50%" height="10">
												上午
											</td>
											<td width="50%">
												下午
											</td>
										</tr>
										<tr>
											<td height="50">
											    <c:if test="${res.s4 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=am&day=${res.date4 }&yemo=${kqdate }','hr_kaoqinDtl')">
													 <c:if test="${res.anote4 != null && res.anote4 !=''}">${res.s4 }<br /> ${res.stime4 }</c:if>
												   <c:if test="${res.anote4 == null || res.anote4 ==''}">	<font color="red"> ${res.s4 } <br /> ${res.stime4 } </font> </c:if>
													 </a>
												</c:if>
											</td>
											<td>
											    <c:if test="${res.x4 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=pm&day=${res.date4 }&yemo=${kqdate }','hr_kaoqinDtl')">
													<c:if test="${res.pnote4 != null && res.pnote4 !=''}">${res.x4 } <br /> ${res.xtime4 }</c:if>
												   <c:if test="${res.pnote4 == null || res.pnote4 ==''}">	<font color="red"> ${res.x4 } <br /> ${res.xtime4 } </font> </c:if>
													 </a>
												</c:if>

											</td>
										</tr>
										
									</table>
								</td>
								
								<td style="border: 1px; <c:if test="${res.ch5=='1' }"> background-color: silver;</c:if>"  >
									<table width="100%"  class="dataListTable" >
										<tr>
											<td colspan="2" height="10">
												${res.date5 }&nbsp;
											</td>
										</tr>
										<tr>
											<td width="50%" height="10">
												上午
											</td>
											<td width="50%">
												下午
											</td>
										</tr>
										<tr>
											<td height="50">
											    <c:if test="${res.s5 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=am&day=${res.date5 }&yemo=${kqdate }','hr_kaoqinDtl')">
													 <c:if test="${res.anote5 != null && res.anote5 !=''}">${res.s5 }<br /> ${res.stime5 }</c:if>
												   <c:if test="${res.anote5 == null || res.anote5 ==''}">	<font color="red"> ${res.s5 } <br /> ${res.stime5 } </font> </c:if>
													 </a>
												</c:if>
											</td>
											<td>
											    <c:if test="${res.x5 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=pm&day=${res.date5 }&yemo=${kqdate }','hr_kaoqinDtl')">
													<c:if test="${res.pnote5 != null && res.pnote5 !=''}">${res.x5 } <br /> ${res.xtime5 }</c:if>
												   <c:if test="${res.pnote5 == null || res.pnote5 ==''}">	<font color="red"> ${res.x5 } <br /> ${res.xtime5 } </font> </c:if>
													 </a>
												</c:if>

											</td>
										</tr>
										
									</table>
								</td>
								
								<td style="border: 1px; <c:if test="${res.ch6=='1' }"> background-color: silver;</c:if>"  >
									<table width="100%"  class="dataListTable" >
										<tr>
											<td colspan="2" height="10">
												${res.date6 }&nbsp;
											</td>
										</tr>
										<tr>
											<td width="50%" height="10">
												上午
											</td>
											<td width="50%">
												下午
											</td>
										</tr>
										<tr>
											<td height="50">
											    <c:if test="${res.s6 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=am&day=${res.date6 }&yemo=${kqdate }','hr_kaoqinDtl')">
													 <c:if test="${res.anote6 != null && res.anote6 !=''}">${res.s6 }<br /> ${res.stime6 }</c:if>
												   <c:if test="${res.anote6 == null || res.anote6 ==''}">	<font color="red"> ${res.s6 } <br /> ${res.stime6 } </font> </c:if>
													 </a>
												</c:if>
											</td>
											<td>
											    <c:if test="${res.x6 != '出勤'}">
												<a href="#"
													onclick="openDG('添加备注信息','<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!addNote.do?p_id=${p_id}&type=pm&day=${res.date6 }&yemo=${kqdate }','hr_kaoqinDtl')">
													<c:if test="${res.pnote6 != null && res.pnote6 !=''}">${res.x6 } <br /> ${res.xtime6 }</c:if>
												   <c:if test="${res.pnote6 == null || res.pnote6 ==''}">	<font color="red"> ${res.x6 } <br /> ${res.xtime6 } </font> </c:if>
													 </a>
												</c:if>

											</td>
										</tr>
										
									</table>
								</td>


							</tr>
							
							
							<tr>
							  <td colspan="7" height="7">
							  </td>
							</tr>
							</c:forEach>
				
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
			</table>
		</div>

	</body>
	
	<script type="text/javascript">
	function changePage(page){
		//var api = frameElement.api, W = api.opener;
		location.reload();
	}
</script>
</html>