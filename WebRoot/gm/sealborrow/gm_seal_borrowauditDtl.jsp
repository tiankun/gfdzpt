<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title></title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
<script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
<script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/Validator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
</head>
<script type="text/javascript">
function sub(id){
	if(id=='0' && document.form1.opinion.value == '')
		{
		  alert("请填写不同意原因");
		  document.form1.opinion.focus();
		  return false;
		}
	if(id=='6' && document.form1.rdate.value == '')
		{
		  alert("请填写归还时间");
		  document.form1.rdate.focus();
		  return false;
		}
	
	document.form1.state.value = id;
	document.form1.submit();
}
</script>
<body>


<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/gm/sealborrow/Gm_seal_borrow!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="state" />
	<input type="hidden" name="type" value="${type}" />
		<table class="table_border" style="width:100%">
		
            
            <tr>
			<th align="center" style="width:10%">使用日期:</th>
			<td style="width:35%">
			<input type="text" name="pdate" value="${record.pdate}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">借用项目:</th>
			<td style="width:35%">
			<select name="bitem" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${bitem}" var="state">
									<option value="${state.key}" <c:if test="${record.bitem==state.key}">selected</c:if>>
										${state.value}
									</option>
								</c:forEach>
							</select>
            </td>
            </tr>
            <tr>
			<th align="center" style="width:10%">借用用途:</th>
			<td colspan="3">
			<textarea rows="3" cols="70" name="usereason">${record.usereason}</textarea>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">类型:</th>
			<td style="width:35%">
			<input name="isoriginal" value="1" type="checkbox" <c:if test="${record.isoriginal=='1'}">checked</c:if>>原件&nbsp;&nbsp;
			<input name="isprint" value="1" type="checkbox" <c:if test="${record.isprint=='1'}">checked</c:if>>复印及加盖公章&nbsp;&nbsp;
			<input name="stamp" value="1" type="checkbox" <c:if test="${record.stamp=='1'}">checked</c:if>>盖章
			
			</td>
			<th align="center" style="width:10%;">份数:</th>
			<td style="width:35%">
			<input type="text" name="num" value="${record.num}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            
			</tr>
            <tr>
			</tr>
            <tr>
			<th align="center" style="width:10%">备注:</th>
			<td colspan="3">
			<textarea rows="3" cols="70" name="note">${record.note}</textarea>
			</td>
			
            </tr>
            
            <c:if test="${type=='deptaudit'||type=='finalaudit'}">
            
            <tr>
			<th align="center" style="width:10%">审批意见:</th>
			<td colspan="3">
			<textarea rows="3" cols="70" name="opinion"></textarea>
			</td>
			
            </tr>
            </c:if>
            
            <c:if test="${type=='zhb'}">
            
            <tr>
			<th align="center" style="width:10%">归还日期:</th>
			<td style="width:35%">
			<input type="text" name="rdate" value="${record.rdate}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%">
			
            </td>
			
            </tr>
            </c:if>
            
		</table>
		
		<c:if test="${type=='deptaudit'||type=='finalaudit'}">
		
        <br/>
		<div class="div-button">
		   <input type="button" value="同意" onclick="sub('1');" style="${btnDisplay}" />
		   <input type="button" value="不同意" onclick="sub('0');" style="${btnDisplay}" />
		
		</div>
		</c:if>
		
		<c:if test="${type=='zhb'}">
		
        <br/>
		<div class="div-button">
		   <input type="button" value="确定" onclick="sub('6');" style="${btnDisplay}" />
		
		</div>
		</c:if>
		
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
								<c:out value="${yzstate[result.state]}" />
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
