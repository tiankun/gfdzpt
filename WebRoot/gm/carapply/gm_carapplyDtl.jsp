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
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>
<script type="text/javascript">
function check()
{
	if(confirm("确定提交"))
		{
		  document.form1.action = '<%=request.getContextPath()%>/gm/carapply/Gm_carapply!check.do';
	      document.form1.submit();
		}
	
}

function sub(id) {
	if (!Validator.Validate(document.form1, 3)) {
		
		return false;
	} else {
		document.form1.state.value = id;
		document.form1.submit();
	}

}
</script>
	</head>
	<body>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/gm/carapply/Gm_carapply!${editMod}.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="state" />
			<table class="table_border" style="width: 100%">
				<tr>
					<th align="center" style="width: 10%">
						计划起始时间:
						<span style="color: Red;">*</span>
					</th>
					<td style="width: 35%">
						<input type="text" id="d4311" value="${record.plan_sdate}" name="plan_sdate" dataType="Require"
							onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'d4312\')||\'2060-12-31\'}'})" />


					</td>
					<th align="center" style="width: 10%">
						计划结束时间:
						<span style="color: Red;">*</span>
					</th>
					<td style="width: 35%">
						<input type="text"  id="d4312" value="${record.plan_edate}" name="plan_edate" dataType="Require"
							onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2060-12-31'})" />

					</td>
				</tr>
				<tr>
				   <th>
				           用车类型：
				   </th>
				   <td colspan="3">
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
						用车原因:
					</th>
					<td colspan="3">
						<textarea rows="4" cols="70" name="reason">${record.reason}</textarea>

					</td>
				</tr>

				<tr>

					<th align="center" style="width: 10%">
						行车路线:
					</th>

					<td colspan="3">
						<textarea rows="4" cols="70" name="road">${record.road}</textarea>

					</td>
				</tr>
                

				<c:if test="${record.carapply_state=='6'||record.carapply_state=='7'}">
					<tr>
						<th align="center" style="width: 10%">
							实际用车起始时间:
						</th>
						<td style="width: 35%">
							<input type="text" value="${record.act_sdate}" name="act_sdate"
								class="textBoxNoBorder"
								onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />


						</td>
						<th align="center" style="width: 10%">
							实际用车结束时间:
						</th>
						<td style="width: 35%">
							<input type="text" value="${record.act_edate}" name="act_edate"
								class="textBoxNoBorder"
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
			   <c:if test="${record.carapply_state==''||record.carapply_state==null||record.carapply_state=='0'}">
			    <input type="button" value="保存不提交" onclick="sub('0');"
					style="${btnDisplay}" />
			    
				<input type="button" value="保存且提交" onclick="sub('1');"
					style="${btnDisplay}" />
					
				</c:if>
			
			
			
			   <c:if test="${record.carapply_state!='0'&&record.carapply_state==''&&record.carapply_state==null}">
				<input type="button" value="提交"
					onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}"
					style="${btnDisplay}" />
			  </c:if>
			  <c:if test="${record.carapply_state=='6'}">
				<input type="button" value="确认"
					onclick="check();"
					style="${btnDisplay}" />
			  </c:if>
				
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>
			
			
			<br/>
		
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
