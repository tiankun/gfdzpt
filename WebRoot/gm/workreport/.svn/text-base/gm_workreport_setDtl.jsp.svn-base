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

	<form name="form1" action="<%=request.getContextPath()%>/gm/workreport/Gm_workreport_set!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		    <tr>
			<th align="center" style="width:10%">工作总结提交人:</th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">部门:</th>
			<td style="width:35%">
			<input type="text" name="branchname" value="${record.branchname}"   style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
		
            <tr>
			<th align="center" style="width:10%">审批人:<span style="color:Red;">*</span></th>
			<td colspan="3">
			<c:if test="${pageType!='viewDtl'}"> 
				<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/hr/info/hr_base_infoMulSelect_frame.jsp','pr_memberIds','pr_member');"  value="选择" style="float:left;margin-top:28px;height:28px"/>
				<%--<input type="text" id="pr_memberIds" name="pr_memberIds" value="${pr_memberIds}" class="hidden"/>
			--%></c:if>																																
			<textarea rows="3" cols="50" style="height:100%;border:1px solid #CCC;word-wrap: break-word;
			<c:choose>
				<c:when test="${pageType=='viewDtl'}"> width:79.8%; </c:when>
			<c:otherwise> width:72.3%;</c:otherwise>
							</c:choose>"  class="textBoxNoBorder" id="pr_member" name="pr_member"  readonly="readonly" >${pr_member}</textarea>
							<input type="text" id="pr_memberIds" name="pr_memberIds" value='${pr_memberIds}' class="hidden"/>								  						  
		   
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
