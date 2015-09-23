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
<script type="text/javascript">
//选择成果清单
$(function(){
	$("#result").bind("propertychange",function(){
		var data = $(this).val();
		var obj = eval('('+ data +')');
		$("#proj_name").val(obj.proj_name);
		$("#proj_id").val(obj.proj_id);
		$("#result_id").val(obj.result_id);
		$("#result_num").val(obj.num);
		$("#ds_type").val(obj.ds_type);
		$("#num").val(obj.num);
	});
})
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/ds/result/Ds_accreditation!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="text" name="result" id="result" value="" class="hidden"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">输出清单:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="result_num" id="result_num" readonly="readonly" value="${record.result_num}" class="textBoxNoBorder"/>
			<input type="text" name="result_id" id="result_id" value="${record.result_id}"  dataType="Integer" class="hidden"/>
            <c:if test="${not empty editMod}">
            <input type="button" onclick="openSelect('选择输出清单','<%=request.getContextPath()%>/ds/result/Ds_result!searchList.do?pageType=mulselect','result_id','result');" value="选择" />
			</c:if>
			</td>
			<th align="center" style="width:10%">项目名称:</th>
			<td style="width:35%">
			<input type="text" name="proj_name" id="proj_name" readonly="readonly" value="${record.proj_name}" class="textBoxNoBorder"/>
			<input type="text" name="proj_id" id="proj_id" value="${record.proj_id}" dataType="Integer" require="false" class="hidden"/></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">评审日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="accr_time" value="${record.accr_time}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">参与人员:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="whodo" value="${record.whodo}"  dataType="Require"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">评审内容:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
			<textarea name="details" value="${record.details}" dataType="Require" rows="5" maxLength="1000"  style="width:99%;border: 0px"/>${record.details}</textarea></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">审核人:</th>
			<td style="width:35%">
			<input type="text" name="approver_name" id="approver_name" readonly="readonly" value="${record.approver_name}" class="textBoxNoBorder"/>
			<input type="text" name="approver_id" id="approver_id" value="${record.approver_id}" class="hidden"/>
            </td>
			<th align="center" style="width:10%">审核日期:</th>
			<td style="width:35%">
			<input type="text" name="apptime" id="apptime" value="${record.apptime}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<tr>
			<th align="center" style="width:10%">审核意见:</th>
			<td style="width:35%" colspan="3">
			<textarea name="appopinion" value="${record.appopinion}" rows="5" maxLength="500"  style="width:99%;border: 0px"/>${record.appopinion}</textarea></td>
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" style="${btnDisplay}" />
			<input type="reset" value="重置" style="${btnDisplay}" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='add'){
	$("#approver_id").attr("value","${user.base_info_id}");
	$("#approver_name").attr("value","${user.user_name}");
	$("#apptime").attr("value","${apptime}");
}
</script>	
</body>
</html>
