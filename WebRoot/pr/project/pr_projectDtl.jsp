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

	<form name="form1" action="<%=request.getContextPath()%>/pr/project/Pr_project!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="pr_step" id="pr_step" value="${record.pr_step}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">项目名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  dataType="Require"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">项目业主:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="pr_owner" value="${record.pr_owner}"  dataType="Require"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            
            <tr>
			<th align="center" style="width:10%">销售经理:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="sale_manage_name" id="sale_manage_name" class="textBoxNoBorder" value="${record.sname}" />
			<input type="text" name="sale_manage" id="sale_manage" class="hidden" />
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','sale_manage','sale_manage_name');"
								value="选择" />
			</td>
			<th>
			</th>
			<td>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">销售人员:</th>
			<td colspan="3">
			<c:if test="${pageType!='viewDtl'}"> 
				<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/hr/info/hr_base_infoMulSelect_frame.jsp','spr_memberIds','spr_member');"  value="选择" style="float:left;margin-top:28px;height:28px"/>
			</c:if>																																
			<textarea rows="5" cols="50" style="height:100%;border:1px solid #CCC;word-wrap: break-word;
			<c:choose>
				<c:when test="${pageType=='viewDtl'}"> width:79.8%; </c:when>
			<c:otherwise> width:72.3%;</c:otherwise>
							</c:choose>"  class="textBoxNoBorder" id="spr_member" name="spr_member"  readonly="readonly" >${record.sale_members}</textarea>
							<input type="text" id="spr_memberIds" name="spr_memberIds" value="" class="hidden"/>								  						  
		   
			</td>
            </tr>
            
            
            <tr>
			<th align="center" style="width:10%">项目经理:</th>
			<td style="width:35%">
			<input type="text" name="pr_manage" id="pr_manage" class="textBoxNoBorder" value="${record.ename}" />
			<input type="text" name="p_id" id="p_id" class="hidden" />
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','pr_manage');"
								value="选择" />
			</td>
			<th>
			</th>
			<td>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">项目成员:</th>
			<td colspan="3">
			<c:if test="${pageType!='viewDtl'}"> 
				<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/hr/info/hr_base_infoMulSelect_frame.jsp','pr_memberIds','pr_member');"  value="选择" style="float:left;margin-top:28px;height:28px"/>
			</c:if>																																
			<textarea rows="5" cols="50" style="height:100%;border:1px solid #CCC;word-wrap: break-word;
			<c:choose>
				<c:when test="${pageType=='viewDtl'}"> width:79.8%; </c:when>
			<c:otherwise> width:72.3%;</c:otherwise>
							</c:choose>"  class="textBoxNoBorder" id="pr_member" name="pr_member"  readonly="readonly" >${record.pr_member}</textarea>
							<input type="text" id="pr_memberIds" name="pr_memberIds" value="${record.personalid}" class="hidden"/>								  						  
		   
			</td>
            </tr>
            
            
            <!--
            <tr>
			<th align="center" style="width:10%">开始日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="start_date" value="${record.start_date}"  dataType="Date"  require="true"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">结束日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="end_date" value="${record.end_date}"  dataType="Date" require="true"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
             
            <tr>
			<th align="center" style="width:10%">项目经理:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="pr_manage" id="pr_manage" class="textBoxNoBorder" value="${record.name1}" />
			<input type="text" name="p_id" id="p_id" class="hidden" />
			<input type="button" onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','pr_manage');"
								value="选择" />
			</td>
			<th>
			</th>
			<td>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">项目成员:<span style="color:Red;">*</span></th>
			<td colspan="3">
			<c:if test="${pageType!='viewDtl'}"> 
				<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/hr/info/hr_base_infoMulSelect_frame.jsp','pr_memberIds','pr_member');"  value="选择" style="float:left;margin-top:28px;height:28px"/>
			</c:if>																																
			<textarea rows="5" cols="50" style="height:100%;border:1px solid #CCC;word-wrap: break-word;
			<c:choose>
				<c:when test="${pageType=='viewDtl'}"> width:79.8%; </c:when>
			<c:otherwise> width:72.3%;</c:otherwise>
							</c:choose>"  class="textBoxNoBorder" id="pr_member" name="pr_member"  readonly="readonly" >${record.pr_member}</textarea>
							<input type="text" id="pr_memberIds" name="pr_memberIds" value="${record.personalid}" class="hidden"/>								  						  
		   
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">项目阶段:</th>
			<td style="width:35%">
			<select name="proj_phase"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${proj_phase}" var="proj_phase">
			<option value="${proj_phase.key}" <c:if test="${record.pr_step==proj_phase.key}">selected</c:if>>${proj_phase.value}</option>
			</c:forEach>
			</select></td>
			<th>
			</th>
			<td>
			</td>
			</tr>
			 -->
			<tr>
			<th align="center" style="width:10%">项目备注:</th>
			<td colspan="3">
			<textarea rows="3" style="width: 99%" cols="70" name="pr_note">${record.pr_note}</textarea>
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
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='add'||editMod=='edit'){
	$("#pr_step").attr("value","1");
}
</script>
</body>
</html>
