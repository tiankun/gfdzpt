<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title></title>
<link href="../jscripts/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
<script src="../jscripts/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script src="../jscripts/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script type="text/javascript">
function f_overrideTab(tabid, text, url){
	tabid = new Date().getTime();
	parent.f_addTab(tabid, text, url);
}
</script>
<style type="text/css">
body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}

#pageloading {
	position: absolute;
	left: 0px;
	top: 0px;
	background: white url('loading.gif') no-repeat center;
	
	height: 100%;
	z-index: 0;
}

.l-link {
	display: block;
	line-height: 22px;
	height: 22px;
	padding-left: 20px;
	margin: 4px;
	background-color: #eee;
	border: windowtext 1pt solid;
	TEXT-DECORATION: none;
	cursor: pointer
}

.l-link-over {
	background: #FFEEAC;
	border: 1px solid #DB9F00;
}

.btn_top {
	BORDER: #2C59AA 1px solid;
	PADDING: 2px;
	FONT-SIZE: 12px;
	width: 60px;
	TEXT-DECORATION: none;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
	background: #C3DAF5;
	CURSOR: pointer;
	COLOR: black;
	position: absolute;
	text-align: center;
}

.funlist {
	
}

a{
	cursor:pointer;
	line-height: 23px;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: #homeEE;	
	text-decoration: none;	
}

a:active {
	color: black;
	text-decoration: none;
}
.news{
	width:100%;
	height:200px;
}
.news li{
	line-height:28px;
	margin:1px auto;	
	width:49%; 
	float:left; 
	display:block;
}
</style>
	</head>
	<body>
	<table width="100%" align="center" >
						<tr>
							<td height="10">

							</td>
						</tr>

						<tr>
							<td style="padding-left: 25px;">
								<img alt="" src="<%=request.getContextPath()%>/image/smile.jpg">
								&nbsp;&nbsp;
								<strong
									style="padding-bottom: 10px; font-weight: bolder; font-size: 15px;">${baseinfo.name}：您好，欢迎登录官房电子办公平台！</strong>

							</td>

						</tr>

						<tr>
							<td>
								<img alt="" src="<%=request.getContextPath()%>/image/m1.gif"
									width="100%">
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
														<img style="border: 1px silver solid; float: left;height:134px;width:100px" alt=""
															src="<%=request.getContextPath()%>/photo/${user.photo}">
													</td>
													<td>
														<table style="line-height: 20px;">

															<tr>
																<td width="300">
																	<strong style="font-weight: bolder; font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;姓名：${baseinfo.name}</strong>
																</td>

															</tr>
															<tr>
																<td height="10">
																</td>
															</tr>
															<tr>
																<td>
																	<strong style="font-weight: bolder; font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;部门：${baseinfo.branchname}</strong>
																</td>

															</tr>
															<tr>
																<td height="10">
																</td>
															</tr>
															<tr>
																<td>
																	<strong style="font-weight: bolder; font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;职务：<c:if
																			test="${baseinfo.duty_id=='1'}">职员</c:if>
																		<c:if test="${baseinfo.duty_id=='2'}">部门经理</c:if>
																		<c:if test="${baseinfo.duty_id=='3'}">总经理</c:if>
																		<c:if test="${baseinfo.duty_id=='4'}">部门经理</c:if>
																		<c:if test="${baseinfo.duty_id=='5'}">部门经理</c:if>
																	</strong>
																</td>

															</tr>
															<tr>
																<td height="10">
																</td>
															</tr>
															<tr>
																<td>
																	<strong style="font-weight: bolder; font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;工作时间：${baseinfo.work_year}</strong>
																</td>

															</tr>
														</table>
													</td>
												</tr>
											</table>

										</td>
										<td>
											<img src="<%=request.getContextPath()%>/image/m3.jpg"
												height="100" />
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
								<img alt="" src="<%=request.getContextPath()%>/image/m1.gif"
									width="100%">
							</td>
						</tr>
						
						<tr>
							<td style="padding-left: 25px; padding-top: 5px;">
								<img alt="" src="<%=request.getContextPath()%>/image/m4.gif">
								&nbsp;&nbsp;
								<strong
									style="padding-bottom: 10px; font-weight: bolder; font-size: 15px;">待办事项</strong>

							</td>
						</tr>
						<tr>
			<td height="180px" style="padding-left: 25px; padding-top: 5px; " valign="top" >
				<table>
				   <c:if test="${haspurexeNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','配给执行','<%= request.getContextPath() %>/materielpurchase/Gm_purchase!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${purexecount}&nbsp;&nbsp;条配给执行待采购！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>
				
				
					<c:if test="${hasyinzhangNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','印章借阅审批','<%= request.getContextPath() %>/gm/sealborrow/Gm_seal_borrow!auditsearchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${yinzhangcount}&nbsp;&nbsp;印章借阅待审批！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>



					<c:if test="${haspayforNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','款项支付审批','<%= request.getContextPath() %>/fi/fi_payfor/Fi_payfor_audit!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${payforcount}&nbsp;&nbsp;款项支付待审批！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>


					<c:if test="${haspayforfpNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','支付管理','<%= request.getContextPath() %>/fi/fi_payfor/Fi_payfor!paysearchList.do?payforstate=3')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${payforfpcount}&nbsp;&nbsp;款项支付待分配付款！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>


					<c:if test="${hasPurModifyNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','配给变更审批','<%= request.getContextPath() %>/materielpurchase/Gm_purchase_modify!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${purmodifycount}&nbsp;&nbsp;配给变更待审批！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>



					<c:if test="${financialActNotiNum>0}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','财务报帐管理','<%= request.getContextPath() %>/fi/financial/Fi_financial_account!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${financialActNotiNum}&nbsp;&nbsp;条 财务报帐 待审批！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>
					<c:if test="${advanceAppNotiNum>0}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','暂支管理','<%= request.getContextPath() %>/fi/advance/Fi_advance_apply!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${advanceAppNotiNum}&nbsp;&nbsp;条 暂支申请 待审批！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>

					<c:if test="${hasPrApplyNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','配给申请审批','<%= request.getContextPath() %>/materielapply/Gm_ration_apply!auditsearchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${prapplycount}&nbsp;&nbsp;配给申请待审批！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>

					
					<c:if test="${hasPrPlanApplyNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','项目计划审批','<%= request.getContextPath() %>/pr/plan/Pr_plan!auditsearchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${prplanapplycount}&nbsp;&nbsp;项目计划待审批！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>
					  

					<c:if test="${hasPurchaseApplyNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','配给执行审批','<%= request.getContextPath() %>/materielpurchase/Gm_purchase!auditsearchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${purchaseapplycount}&nbsp;&nbsp;配给执行待审批！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>




					<c:if test="${(hasAbsenceNotice=='1'&&absencecount!='0')}">
						<tr>
							<td width="250"><c:if
									test="${absencecount!='0'&&absencecount!=''&&absencecount!=null}">
									<a
										onclick="f_overrideTab('home','请假审批','<%= request.getContextPath() %>/hr/absence/Hr_absence!auditList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${absencecount}&nbsp;&nbsp;条请假申请待审批！</strong> </a>
								</c:if></td>
							<td></td>
						</tr>
					</c:if>


					<c:if
						test="${(hasCarapplyNotice=='1'&&carapplycount!='0')||(haszhbCarapplyNotice=='1'&&zhbcarapplycount!='0')}">
						<tr>
							<td><c:if
									test="${carapplycount!='0'&&carapplycount!=''&&carapplycount!=null}">
									<a
										onclick="f_overrideTab('home','用车审批','<%= request.getContextPath() %>/gm/carapply/Gm_carapply_audit!searchList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${carapplycount}&nbsp;&nbsp;条用车申请待审批！</strong> </a>
								</c:if> <c:if
									test="${zhbcarapplycount!='0'&&zhbcarapplycount!=''&&zhbcarapplycount!=null}">
									<a
										onclick="f_overrideTab('home','用车审批','<%= request.getContextPath() %>/gm/carapply/Gm_carapply_audit!zhbsearchList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${zhbcarapplycount}&nbsp;&nbsp;条用车申请待审批！</strong> </a>
								</c:if></td>
							<td></td>
						</tr>

					</c:if>







					<c:if
						test="${(hasdriverCarapplyNotice=='1'&&drivecarapplycount!='0')||hassureCarapplyNotice=='1'}">
						<tr>
							<td width="250"><c:if
									test="${drivecarapplycount!='0'&&drivecarapplycount!=''&&drivecarapplycount!=null}">
									<a
										onclick="f_overrideTab('home','用车填写(驾驶员)','<%= request.getContextPath() %>/gm/carapply/Gm_carapply_audit!accomplish.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${drivecarapplycount}&nbsp;&nbsp;条用车情况待填写！</strong> </a>
								</c:if> <c:if test="${hassureCarapplyNotice=='1'}">
									<a
										onclick="f_overrideTab('home','用车申请','<%= request.getContextPath() %>/gm/carapply/Gm_carapply!searchList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${surecarapplycount}&nbsp;&nbsp;条用车情况待核对！</strong> </a>
								</c:if></td>
							<td></td>
						</tr>

					</c:if>


					<c:if test="${hassureCarapplyHuNotice=='1'}">
						<tr>
							<td><c:if test="${hassureCarapplyHuNotice=='1'}">
									<a
										onclick="f_overrideTab('home','考勤审批','<%= request.getContextPath() %>/gm/carapply/Gm_carapply!searchList1.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${surehucarapplycount}&nbsp;&nbsp;条用车申请（运送材料）待确认！</strong>
									</a>
								</c:if></td>

							<td></td>
						</tr>
					</c:if>


					<c:if test="${hasKaoqinNotice=='1'}">
						<tr>
							<td><c:if test="${hasKaoqinNotice=='1'}">
									<a
										onclick="f_overrideTab('home','考勤审批','<%= request.getContextPath() %>/hr/absence/Hr_kaoqin!kaoqinaudit.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${kaoqincount}&nbsp;&nbsp;条考勤记录待审批！</strong> </a>
								</c:if></td>
							<td></td>
						</tr>
					</c:if>



					<c:if
						test="${hasBillNotice=='1'||hascwBillNotice=='1'||haskpBillNotice=='1'}">
						<tr>
							<td width="250"><c:if test="${hasBillNotice=='1'}">
									<a
										onclick="f_overrideTab('home','发票审批','<%= request.getContextPath() %>/fi/bill/Fi_bill_audit!searchList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${billcount}&nbsp;&nbsp;条发票申请待审批！</strong> </a>
								</c:if> <c:if test="${hascwBillNotice=='1'}">
									<a
										onclick="f_overrideTab('home','发票审批(财务主管)','<%= request.getContextPath() %>/fi/bill/Fi_bill_audit!searchcwList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${cwbillcount}&nbsp;&nbsp;条发票申请待审批！</strong> </a>
								</c:if> <c:if test="${haskpBillNotice=='1'}">
									<a
										onclick="f_overrideTab('home','财务开票','<%= request.getContextPath() %>/fi/bill/Fi_bill_audit!searchkpList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${kpbillcount}&nbsp;&nbsp;条待开票记录！</strong> </a>
								</c:if></td>
							<td></td>
						</tr>

					</c:if>

					<!-- 待面试提醒 -->
					<c:if test="${hasAppHrNotice=='1'&&AppHrCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','面试提醒','<%= request.getContextPath() %>/hr/info/Hr_base_info!testList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${AppHrCount}&nbsp;&nbsp;条 待面试 记录！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>

					<!-- 应聘审核提醒 -->
					<c:if
						test="${(hasApplyHrNotice=='1'&&ApplyHrCount!='0')||(hasApplyDmNotice=='1'&&ApplyDmCount!='0')||(hasApplyGmNotice=='1'&&ApplyGmCount!='0')}">
						<tr>
							<td width="250"><c:if
									test="${hasApplyHrNotice=='1'&&ApplyHrCount!='0'&&user.branchid=='2'&&user.duty_id=='2'}">
									<a
										onclick="f_overrideTab('home','面试审核','<%= request.getContextPath() %>/hr/info/Hr_base_info!testList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${ApplyHrCount}&nbsp;&nbsp;条 面试待审核 记录！</strong> </a>
								</c:if> <c:if
									test="${hasApplyDmNotice=='1'&&ApplyDmCount!='0'&&user.branchid!='2'&&user.duty_id=='2'}">
									<a
										onclick="f_overrideTab('home','面试审核','<%= request.getContextPath() %>/hr/info/Hr_base_info!testList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${ApplyDmCount}&nbsp;&nbsp;条 面试待审核 记录！</strong> </a>
								</c:if> <c:if
									test="${hasApplyGmNotice=='1'&&ApplyGmCount!='0'&&user.duty_id=='3'}">
									<a
										onclick="f_overrideTab('home','面试审核','<%= request.getContextPath() %>/hr/info/Hr_base_info!testList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${ApplyGmCount}&nbsp;&nbsp;条 面试待审核 记录！</strong> </a>
								</c:if></td>
							<td></td>
						</tr>
					</c:if>

					<!-- 入职审核提醒 -->
					<c:if
						test="${(hasTryHrNotice=='1'&&TryHrCount!='0')||(hasTryDmNotice=='1'&&TryDmCount!='0')||(hasTryGmNotice=='1'&&TryGmCount!='0')||(hasCheckHrNotice=='1'&&TryCheckCount!='0')}">
						<tr>
							<td width="250"><c:if
									test="${hasCheckHrNotice=='1'&&TryCheckCount!='0'&&user.branchid=='2'&&user.duty_id=='2'}">
									<a
										onclick="f_overrideTab('home','入职确认','<%= request.getContextPath() %>/hr/info/Hr_base_info!entryList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${TryCheckCount}&nbsp;&nbsp;条 入职 待确认记录！</strong> </a>
								</c:if> <c:if
									test="${hasTryHrNotice=='1'&&TryHrCount!='0'&&user.branchid=='2'&&user.duty_id=='2'}">
									<a
										onclick="f_overrideTab('home','入职审核','<%= request.getContextPath() %>/hr/info/Hr_base_info!entryList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${TryHrCount}&nbsp;&nbsp;条员工 入职 意见待填写！</strong> </a>
								</c:if> <c:if
									test="${hasTryDmNotice=='1'&&TryDmCount!='0'&&user.branchid!='2'&&user.duty_id=='2'}">
									<a
										onclick="f_overrideTab('home','入职审核','<%= request.getContextPath() %>/hr/info/Hr_base_info!entryList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${TryDmCount}&nbsp;&nbsp;条 入职 待审批记录！</strong> </a>
								</c:if> <c:if
									test="${hasTryGmNotice=='1'&&TryGmCount!='0'&&user.duty_id=='3'}">
									<a
										onclick="f_overrideTab('home','入职审核','<%= request.getContextPath() %>/hr/info/Hr_base_info!entryList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${TryGmCount}&nbsp;&nbsp;条 入职 待审批记录！</strong> </a>
								</c:if></td>
							<td></td>
						</tr>
					</c:if>

					<!-- 可转正申请提醒 -->
					<c:if test="${hasRegularApplyNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','转正申请','<%= request.getContextPath() %>/hr/process/Hr_apply!add.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您可以提交转正申请了！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>


					<!-- 收发文确认 -->
					<c:if test="${hasfileNotice=='1'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','转正考评','<%= request.getContextPath() %>/gm/filereceive/Gm_receive_file!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${payfilecount}&nbsp;&nbsp;条 收发文待确认！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>


					<!-- 转正考评填写提醒 -->
					<c:if test="${hasEvalNotice=='1'&&EvalCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','转正考评','<%= request.getContextPath() %>/hr/process/Hr_evaluation!eva_selfList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${EvalCount}&nbsp;&nbsp;条 转正考评 待填写！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>

					<!-- 延长试用提醒 -->
					<c:if test="${hasYcsyNotice=='1'&&YcsyCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','转正申请','<%= request.getContextPath() %>/hr/process/Hr_apply!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${YcsyCount}&nbsp;&nbsp;条 延长试用工作 待确认！</strong> </a></td>
							<td></td>
						</tr>
					</c:if>

					<!-- 人事转正考评填写提醒 -->
					<!--
				<c:if test="${hasEvalHrNotice=='1'&&EvalHrCount!='0'}">
					<tr>
						<td width="250">
							<a
								onclick="f_overrideTab('home','转正考评','<%= request.getContextPath() %>/hr/process/Hr_apply!searchList.do')"><strong
								style="font-weight: bolder;">
									<img alt="" src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
									您有&nbsp;&nbsp;${EvalHrCount}&nbsp;&nbsp;条 转正考评 待确认！</strong>
							</a>
						</td>
						<td></td>
					</tr>
				</c:if>
				 -->

					<!-- 转正审核提醒 -->
					<c:if
						test="${(hasRegularHrNotice=='1'&&RegularHrCount!='0')||(hasRegularDmNotice=='1'&&RegularDmCount!='0')||(hasRegularGmNotice=='1'&&RegularGmCount!='0')}">
						<tr>
							<td width="250"><c:if
									test="${hasRegularHrNotice=='1'&&RegularHrCount!='0'&&user.branchid=='2'&&user.duty_id=='2'}">
									<a
										onclick="f_overrideTab('home','转正审核','<%= request.getContextPath() %>/hr/process/Hr_apply!searchList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${RegularHrCount}&nbsp;&nbsp;条 转正 待审批记录！</strong> </a>
								</c:if> <c:if
									test="${hasRegularDmNotice=='1'&&RegularDmCount!='0'&&user.branchid!='2'&&user.duty_id=='2'}">
									<a
										onclick="f_overrideTab('home','转正审核','<%= request.getContextPath() %>/hr/process/Hr_apply!searchList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${RegularDmCount}&nbsp;&nbsp;条 转正 待审批记录！</strong> </a>
								</c:if> <c:if
									test="${hasRegularGmNotice=='1'&&RegularGmCount!='0'&&user.duty_id=='3'}">
									<a
										onclick="f_overrideTab('home','转正审核','<%= request.getContextPath() %>/hr/process/Hr_apply!searchList.do')"><strong
										style="font-weight: bolder;"> <img alt=""
											src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
											您有&nbsp;&nbsp;${RegularGmCount}&nbsp;&nbsp;条 转正 待审批记录！</strong> </a>
								</c:if></td>
							<td></td>
						</tr>
					</c:if>

					<!-- 离职确认提醒 -->
					<!-- 部门经理确审批 -->
					<c:if test="${hasdimDmOppNotice=='1'&&dimDmOppCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','离职确认','<%= request.getContextPath() %>/hr/process/Hr_dimission!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${dimDmOppCount}&nbsp;&nbsp;条 离职申请 待审批！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 综合财务经理确认 -->
					<c:if test="${hasdimGmFiNotice=='1'&&dimGmFiCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','离职确认','<%= request.getContextPath() %>/hr/process/Hr_dimission!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${dimGmFiCount}&nbsp;&nbsp;条 离职交接 待确认！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 人事部经理审批 -->
					<c:if test="${hasdimHrOppNotice=='1'&&dimHrOppCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','离职确认','<%= request.getContextPath() %>/hr/process/Hr_dimission!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${dimHrOppCount}&nbsp;&nbsp;条 离职申请 待审批！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 人事部经理确认 -->
					<c:if test="${hasdimHrNotice=='1'&&dimHrCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','离职确认','<%= request.getContextPath() %>/hr/process/Hr_dimission!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${dimHrCount}&nbsp;&nbsp;条 离职申请 待确认！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 总经理确认 -->
					<c:if test="${hasdimGemNotice=='1'&&dimGemCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','离职确认','<%= request.getContextPath() %>/hr/process/Hr_dimission!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${dimGemCount}&nbsp;&nbsp;条 离职申请 待审批！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 费用开支审批 -->
					<!-- 部门经理审批 -->
					<c:if test="${hasExpDmNotice=='1'&&ExpDmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','费用开支计划审核','<%= request.getContextPath() %>/fi/expenses_plan/Fi_expenses_plan!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${ExpDmCount}&nbsp;&nbsp;条 费用开支计划 待审批！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 总经理审核 -->
					<c:if test="${hasExpGmNotice=='1'&&ExpGmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','费用开支计划审核','<%= request.getContextPath() %>/fi/expenses_plan/Fi_expenses_plan!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${ExpGmCount}&nbsp;&nbsp;条 费用开支计划 待审批！</strong> </a></td>
						</tr>
					</c:if>
					
					<%--	工程设计部分暂时注释
					<!-- 设计任务书审批 -->
					<c:if test="${hasDsTaskNotice=='1'&&DsTaskCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','设计任务书审核','<%= request.getContextPath() %>/ds/task/Ds_dstask!appList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${DsTaskCount}&nbsp;&nbsp;条 设计任务书 待审核！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 任务书接件 -->
					<c:if test="${hasDsFzrNotice=='1'&&DsFzrCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','任务书接件','<%= request.getContextPath() %>/ds/task/Ds_dstask!allotList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${DsFzrCount}&nbsp;&nbsp;条 设计任务书 待接件！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 策划书编制 -->
					<c:if test="${hasDsSchAddNotice=='1'&&DsSchAddCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','策划书编制','<%= request.getContextPath() %>/ds/scheme/Ds_scheme!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${DsSchAddCount}&nbsp;&nbsp;条 策划书 待编制！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 策划书审批 -->
					<c:if test="${hasDsSchemeNotice=='1'&&DsSchemeCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','策划书审核','<%= request.getContextPath() %>/ds/scheme/Ds_scheme!appList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${DsSchemeCount}&nbsp;&nbsp;条 策划书 待审核！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 输出清单审批 -->
					<c:if test="${hasDsResultNotice=='1'&&DsResultCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','输出清单审核','<%= request.getContextPath() %>/ds/result/Ds_result!appList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${DsResultCount}&nbsp;&nbsp;条 设计输出清单 待审核！</strong> </a></td>
						</tr>
					</c:if>
					--%>

					<!-- 出差审批-->
					<!-- 部门经理审核 -->
					<c:if test="${hasTravelDmNotice=='1'&&TravelDmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','出差申请审核','<%= request.getContextPath() %>/gm/travel/Gm_travel!travelList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${TravelDmCount}&nbsp;&nbsp;条 出差申请 待审批！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 总经理审核 -->
					<c:if test="${hasTravelGmNotice=='1'&&TravelGmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','出差申请审核','<%= request.getContextPath() %>/gm/travel/Gm_travel!travelList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${TravelGmCount}&nbsp;&nbsp;条 出差申请 待审批！</strong> </a></td>
						</tr>
					</c:if>

					<!-- 出差报账审批-->
					<!-- 部门经理审核 -->
					<c:if test="${!(user.branchid=='6'&&user.duty_id=='2')}"><!-- 财务经理屏蔽此角色提醒 -->
					<c:if test="${hasTravelaccDmNotice=='1'&&TravelaccDmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','出差报账审批','<%= request.getContextPath() %>/fi/travel/Fi_travelacc!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${TravelaccDmCount}&nbsp;&nbsp;条 出差报账 待审批！</strong> </a></td>
						</tr>
					</c:if>
					</c:if>
					<!-- 会计审核 -->
					<c:if test="${hasTravelaccAcNotice=='1'&&TravelaccAcCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','出差报账审批','<%= request.getContextPath() %>/fi/travel/Fi_travelacc!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${TravelaccAcCount}&nbsp;&nbsp;条 出差报账 待审批！</strong> </a></td>
						</tr>
					</c:if>
					<!-- 财务经理审核 -->
					<c:if test="${user.branchid=='6'&&user.duty_id=='2'}"><!-- 部门经理屏蔽此角色提醒 -->
					<c:if test="${hasTravelaccAdNotice=='1'&&TravelaccAdCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','出差报账审批','<%= request.getContextPath() %>/fi/travel/Fi_travelacc!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${TravelaccAdCount}&nbsp;&nbsp;条 出差报账 待审批！</strong> </a></td>
						</tr>
					</c:if></c:if>
					<!-- 领导审核 -->
					<c:if test="${hasTravelaccGmNotice=='1'&&TravelaccGmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','出差报账审批','<%= request.getContextPath() %>/fi/travel/Fi_travelacc!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${TravelaccGmCount}&nbsp;&nbsp;条 出差报账 待审批！</strong> </a></td>
						</tr>
					</c:if>


					<!-- 财务报账审批-->
					<!-- 部门经理审核 -->
					<c:if test="${!(user.branchid=='6'&&user.duty_id=='2')}"><!-- 财务经理屏蔽此角色提醒 -->
					<c:if test="${hasFiaccDmNotice=='1'&&FiaccDmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','财务报账审批','<%= request.getContextPath() %>/fi/financial/Fi_financial_account!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${FiaccDmCount}&nbsp;&nbsp;条 财务报账 待审批！</strong> </a></td>
						</tr>
					</c:if></c:if>
					<!-- 会计审核 -->
					<c:if test="${hasFiaccAcNotice=='1'&&FiaccAcCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','财务报账审批','<%= request.getContextPath() %>/fi/financial/Fi_financial_account!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${FiaccAcCount}&nbsp;&nbsp;条 财务报账 待审批！</strong> </a></td>
						</tr>
					</c:if>
					<!-- 财务经理审核 -->
					<c:if test="${user.branchid=='6'&&user.duty_id=='2'}"><!-- 部门经理屏蔽此角色提醒 -->
					<c:if test="${hasFiaccAdNotice=='1'&&FiaccAdCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','财务报账审批','<%= request.getContextPath() %>/fi/financial/Fi_financial_account!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${FiaccAdCount}&nbsp;&nbsp;条 财务报账 待审批！</strong> </a></td>
						</tr>
					</c:if></c:if>
					<!-- 领导审核 -->
					<c:if test="${hasFiaccGmNotice=='1'&&FiaccGmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','财务报账审批','<%= request.getContextPath() %>/fi/financial/Fi_financial_account!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${FiaccGmCount}&nbsp;&nbsp;条 财务报账 待审批！</strong> </a></td>
						</tr>
					</c:if>


					<!-- 暂支审批-->
					<!-- 部门经理审核 -->
					<c:if test="${!(user.branchid=='6'&&user.duty_id=='2')}"><!-- 财务经理屏蔽此角色提醒 -->
					<c:if test="${hasFiadvDmNotice=='1'&&FiadvDmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','暂支申请审批','<%= request.getContextPath() %>/fi/advance/Fi_advance!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${FiadvDmCount}&nbsp;&nbsp;条 暂支申请 待审批！</strong> </a></td>
						</tr>
					</c:if></c:if>
					<!-- 财务经理审核 -->
					<c:if test="${user.branchid=='6'&&user.duty_id=='2'}"><!-- 部门经理屏蔽此角色提醒 -->
					<c:if test="${hasFiadvAdNotice=='1'&&FiadvAdCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','暂支申请审批','<%= request.getContextPath() %>/fi/advance/Fi_advance!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${FiadvAdCount}&nbsp;&nbsp;条 暂支申请 待审批！</strong> </a></td>
						</tr>
					</c:if></c:if>
					<!-- 领导审核 -->
					<c:if test="${hasFiadvGmNotice=='1'&&FiadvGmCount!='0'}">
						<tr>
							<td width="250"><a
								onclick="f_overrideTab('home','暂支申请审批','<%= request.getContextPath() %>/fi/advance/Fi_advance!searchList.do')"><strong
									style="font-weight: bolder;"> <img alt=""
										src="<%=request.getContextPath()%>/image/m6.jpg">&nbsp;&nbsp;
										您有&nbsp;&nbsp;${FiadvGmCount}&nbsp;&nbsp;条 暂支申请 待审批！</strong> </a></td>
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
								<img alt="" src="<%=request.getContextPath()%>/image/m1.gif"
									width="100%">
							</td>
						</tr>



						<tr>
							<td style="padding-left: 25px; padding-top: 5px;">
								<img alt="" src="<%=request.getContextPath()%>/image/m5.jpg">
								<strong
									style="padding-bottom: 10px; font-weight: bolder; font-size: 15px;">最新信息动态</strong>
								&nbsp;&nbsp;&nbsp;
								<font
									style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold;">
									<a
									onclick="f_overrideTab('home','新闻发布','<%= request.getContextPath() %>/gm/info/Gm_info!searchList.do')"><strong
										style="font-weight: bolder;"> <font color="red">+更多</font>
									</strong>
								</a> </font>
							</td>
						</tr>

						<tr>
							<td width="100%" height="200"
								style="padding-left: 25px; padding-top: 5px;" valign="top">
								
								<div class="news">
									<ul class="newsul">
										<c:forEach items="${record}" var="res">
											<li class="newtitle">
												<span style="width:20px"><img alt=""src="<%=request.getContextPath()%>/image/m6.jpg"/></span>											
													<a
														href="<%=request.getContextPath()%>/gm/info/Gm_info!view.do?id=${res.id}"
														target="_blank" title="${res.title}"> ${res.title} </a>
													&nbsp;&nbsp;
													<c:if
														test="${res.cha=='0'||res.cha=='1'||res.cha=='2'||res.cha=='3'}">
														<img src="<%=request.getContextPath()%>/image/new.gif">
													</c:if>
												</li>										
										</c:forEach>
									</ul>									
								</div>
							</td>
						</tr>

					</table>
	<table style="font-size: 13.5px">
			  	
	</table>
	</body>
	</html>
		