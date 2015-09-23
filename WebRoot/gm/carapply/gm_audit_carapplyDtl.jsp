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
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
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
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>
	</head>

	<script type="text/javascript">
function sub() {
	var flag = false;
	//alert(document.form1.carapply_state[1].checked);

	if (document.form1.carapply_state[1].checked) {
		if (document.form1.opinion.value == '') {
			alert("请输入不同意原因");
			document.form1.opinion.focus();
			return false;
		}
	}
	document.form1.submit();
}


function sub1() {
	if (document.form1.carapply_state.value =='') {
		if (document.form1.carapply_state.value == '') {
			alert("请选择审核意见");
			document.form1.carapply_state.focus();
			return false;
		}
	}

	if (document.form1.carapply_state.value =='0') {
		if (document.form1.opinion.value == '') {
			alert("请输入不同意原因");
			document.form1.opinion.focus();
			return false;
		}
	}
	
	if (document.form1.carapply_state.value =='1') {
		if (document.form1.driver.value == '') {
			alert("请选择驾驶员！");
			document.form1.driver.focus();
			return false;
		}
		if (document.form1.car_num.value == '') {
			alert("请选择车辆！");
			document.form1.car_num.focus();
			return false;
		}
	}
	
	document.form1.submit();
}



function sub2() {
	
		if (document.form1.act_sdate.value == '') {
			alert("请填写实际用车起始时间！");
			document.form1.act_sdate.focus();
			return false;
		}
		if (document.form1.act_edate.value == '') {
			alert("请填写实际用车结束时间");
			document.form1.act_edate.focus();
			return false;
		}
		if (document.form1.length.value == '') {
			alert("请填写形式里程");
			document.form1.length.focus();
			return false;
		}
	
	document.form1.submit();
}


function dis(id) {
	if (document.form1.carapply_state.value == '1') {
		var jsy = document.getElementById("jsy");
		jsy.style.display = '';
		

	} else {
		var jsy = document.getElementById("jsy");
		jsy.style.display = 'none';
		
	}

}
</script>

	<body>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/gm/carapply/Gm_carapply_audit!${editMod}.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<table class="table_border" style="width: 100%">
			    <tr>
			       <th align="center" style="width: 10%">
				           申请人：
				   </th>
				   <td style="width: 35%">
				   &nbsp;${record.name}
				   </td>
				   <th align="center" style="width: 10%">
				           用车类型：
				   </th>
				   <td style="width: 35%">
				   <c:if test="${usetype=='1'||record.usetype=='1'}">
				   <input type="radio" name="usetype" value="1"  checked="checked">外出办事&nbsp;&nbsp;
				   </c:if>
				   
				   <c:if test="${usetype=='0'||record.usetype=='0'}">
				   <input type="radio" name="usetype" value="0"  checked="checked">运送材料&nbsp;&nbsp;
				   </c:if>
				   </td>
				</tr>
			
				<tr>
					<th align="center" style="width: 10%">
						计划起始时间:
						<span style="color: Red;">*</span>
					</th>
					<td style="width: 35%">
						<input type="text" value="${record.plan_sdate}" name="plan_sdate" class="textBoxNoBorder"
							onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />


					</td>
					<th align="center" style="width: 10%">
						计划结束时间:
						<span style="color: Red;">*</span>
					</th>
					<td style="width: 35%">
						<input type="text" value="${record.plan_edate}" name="plan_edate" class="textBoxNoBorder"
							onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />

					</td>
				</tr>
				<tr>
					<th align="center" style="width: 10%">
						用车原因:
					</th>
					<td colspan="3">
						<textarea rows="4" cols="70" name="reason" >${record.reason}</textarea>

					</td>
				</tr>

				<tr>

					<th align="center" style="width: 10%">
						行车路线:
					</th>

					<td colspan="3">
						<textarea rows="4" cols="70" name="road" >${record.road}</textarea>

					</td>
				</tr>

				<c:if test="${record.carapply_state=='1'}">
					<tr>

						<th align="center" style="width: 10%">
							审批结果:
						</th>

						<td colspan="3">
							<input type="radio" name="carapply_state" value="1"
								checked="checked">
							同意&nbsp;&nbsp;&nbsp;
							<input type="radio" name="carapply_state" value="0">
							不同意
						</td>
					</tr>

					<tr>

						<th align="center" style="width: 10%">
							审批意见:
						</th>

						<td colspan="3">
							<textarea rows="4" cols="70" name="opinion">${record.opinion}</textarea>

						</td>
					</tr>

				</c:if>

				<c:if test="${record.carapply_state=='2'}">
					
					<tr>
						<th align="center" style="width: 10%">
							综合办审批意见
						</th>
						<td colspan="3">
							<select name="carapply_state" style="width: 40%" onChange="dis();">
								<option>
									未选择
								</option>
								<option value="1">
									同意
								</option>
								<option value="0">
									不同意
								</option>
							</select>

						</td>
					</tr>
					<tr style="display: none;" id="jsy">
						<th align="center" style="width: 10%">
							驾驶员:
						</th>
						<td style="width: 35%">
							<select name="driver" style="width: 50%;">
								<option value="">
									未选择
								</option>
								<c:forEach var="res" items="${dlist}">
									<option value="${res.id}"
										<c:if test="${record.driver==res.id}">selected</c:if>>
										${res.name}
									</option>
								</c:forEach>

							</select>
						</td>
						<th align="center" style="width: 10%">
                                                                      车牌号:
						</th>
						<td style="width: 35%">
                          <select name="car_num" style="width: 50%;">
								<option value="">
									未选择
								</option>
								<c:forEach var="res" items="${clist}">
									<option value="${res.dmz}"
										<c:if test="${record.car_num==res.dmz}">selected</c:if>>
										${res.dmz}
									</option>
								</c:forEach>

							</select>

						</td>
					</tr>
					
					
					<tr>

						<th align="center" style="width: 10%">
							审批意见:
						</th>

						<td colspan="3">
							<textarea rows="4" cols="70" name="opinion" ></textarea>

						</td>
					</tr>
					
					
					
				</c:if>
				<c:if test="${record.carapply_state=='4'}">
					<tr>
						<th align="center" style="width: 10%">
							实际用车起始时间:
						</th>
						<td style="width: 35%">
						    <input type="text" value="${record.act_sdate}" name="act_sdate" class="textBoxNoBorder"
							onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />
						    
							
						</td>
						<th align="center" style="width: 10%">
							实际用车结束时间:
						</th>
						<td style="width: 35%">
						<input type="text" value="${record.act_edate}" name="act_edate" class="textBoxNoBorder"
							onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />
						 
						</td>
					</tr>
					<tr>
						<th align="center" style="width: 10%">
							行驶里程:
						</th>
						<td style="width: 35%">
							<input type="text" name="length" value="${record.length}"
								maxLength="5" style="width: 95%" class="textBoxNoBorder" />
						</td>
						<th align="center" style="width: 10%">
							
						</th>
						<td style="width: 35%">
							
						</td>
					</tr>
					
				</c:if>
			</table>
			<br />
			<div class="div-button">
			   <c:if test="${record.carapply_state=='1'}">
				<input type="button" value="提交" onclick="sub();"
					style="${btnDisplay}" /></c:if>
			   <c:if test="${record.carapply_state=='2'}">
				<input type="button" value="提交" onclick="sub1();"
					style="${btnDisplay}" /></c:if>
			   
			   <c:if test="${record.carapply_state=='4'}">
				<input type="button" value="提交" onclick="sub2();"
					style="${btnDisplay}" /></c:if>		
			
					
				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>
			
			
			<div class="divMod2">
				<div class="divMod1">
					审批意见历史
				</div>
				<table class="dataListTable" width="100%">
					<tr>


						<th>
							序号
						</th>

						<th>
							审批人
						</th>
						<th>
							审批结果
						</th>
						<th >
							审批时间
						</th>

						<th>
							审批意见
						</th>
					</tr>
					<c:forEach items="${splist}" var="result" varStatus="status">
						<tr>

							<td>
								<c:out value="${status.index+1}" />								
							</td>
							<td>
								<c:out value="${result.person}" />
							</td>
							<td>
								<c:out value="${carapply_state[result.state]}" />
							</td>
							<td>
								<c:out value="${result.audit_date}" />
							</td>
							<td>
								<c:out value="${result.opinion}" />
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</form>
	</body>
</html>
