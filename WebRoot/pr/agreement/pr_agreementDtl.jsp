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
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/pr/agreement/Pr_agreement!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">项目信息:</th>
			<td style="width:35%">
			<input type="text" id="name" name="name" value="${record.name}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="item_id" name="item_id" value="${record.item_id}"  dataType="Double" require="false" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','item_id','name');" value="选择"/></td>
			<th align="center" style="width:10%">合同编码:</th>
			<td style="width:35%">
			<input type="text" name="agree_code" value="${record.agree_code}"  maxLength="12"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">合同名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="agree_name" value="${record.agree_name}"  dataType="Require"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">合同类型:</th>
			<td style="width:35%">
			<select name="agree_type" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${agree_type}" var="agree_type">
									<option value="${agree_type.key}" <c:if test="${record.agree_type==agree_type.key}">selected</c:if>  >
										${agree_type.value}
									</option>
								</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">合同性质:</th>
			<td style="width:35%">
			<select name="agree_property" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${agree_property}" var="agree_property">
									<option value="${agree_property.key}" <c:if test="${record.agree_property==agree_property.key}">selected</c:if>  >
										${agree_property.value}
									</option>
								</c:forEach>
			</select>
			</td>
			<th align="center" style="width:10%">客户（甲方）:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="name" name="custom_unitname" value="${record.custom_unitname}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="custom_id" name="custom_id" value="${record.custom_id}"  dataType="Double" require="false" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/customer/Customer!searchList.do?pageType=select','custom_id','custom_unitname');" value="选择"/>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">合同开始时间:</th>
			<td style="width:35%">
			<input type="text" name="begin_date" value="${record.begin_date}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            
			<th align="center" style="width:10%">合同结束时间:</th>
			<td style="width:35%">
			<input type="text" name="end_date" value="${record.end_date}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">客户签字人:</th>
			<td style="width:35%">
			<input type="text" name="custom_name" value="${record.custom_name}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">签字时间:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="sign_date" value="${record.sign_date}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            
            <tr>
			<th align="center" style="width:10%">联系人:</th>
			<td style="width:35%">
			<input type="text" name="contactor" value="${record.contactor}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">联系电话:</th>
			<td style="width:35%">
			<input type="text" name="contact_phone" value="${record.contact_phone}"  maxLength="30"  style="width:95%" class="textBoxNoBorder"/></td>
			
            </tr>
            <tr>
			<th align="center" style="width:10%">合同金额:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="agree_money" value="${record.agree_money}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">预付款:</th>
			<td style="width:35%">
			<input type="text" name="advance_payment" value="${record.advance_payment}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            
            </tr>
            <tr>
              <th>合同状态</th>
			<td>
			  <select name="agree_state" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${agree_state}" var="agree_state">
									<option value="${agree_state.key}" <c:if test="${record.agree_state==agree_state.key}">selected</c:if>  >
										${agree_state.value}
									</option>
								</c:forEach>
			</select>
			</td>
			<th>
			
			</th>
			<td></td>
            </tr>
            
            <tr>
              <th>合同备注</th>
			<td colspan="3">
			 <textarea rows="3" cols="70" name="note">${record.note}</textarea>
			  </td>
            </tr>
           
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
</body>
</html>
