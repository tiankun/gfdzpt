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
<script charset="UTF-8" src="${pageContext.request.contextPath}/jscripts/kindeditor/kindeditor-min.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/jscripts/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
function appr(appstate){
	if(confirm("确定提交审核意见？")){
		$("#appstate").attr("value",appstate);
		document.form1.submit();
		}
}

var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="gist"]',
			{
				uploadJson : '${pageContext.request.contextPath}/jscripts/kindeditor/jsp/upload_json.jsp',
				allowFileManager : false,
				afterCreate : function() { 
                                           this.sync(); 
                                                      }, 
                            afterBlur:function(){ 
                                          this.sync(); 
                                           }    
			});
});
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="key"]',
			{
				uploadJson : '${pageContext.request.contextPath}/jscripts/kindeditor/jsp/upload_json.jsp',
				allowFileManager : false,
				afterCreate : function() { 
                                           this.sync(); 
                                                      }, 
                            afterBlur:function(){ 
                                          this.sync(); 
                                           }    
			});
});
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/ds/scheme/Ds_scheme!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="flag" id="flag" value="${record.flag}"/>
	<input type="hidden" name="appstate" id="appstate" value="${record.appstate}"/>
	<input type="hidden" id="ds_typeh" name="ds_type" value="${record.ds_type}"/>
		<div class="divMod2">
		<div class="divMod1">策划书详情</div>
		<table class="table_border" style="width:100%">
		
            <tr>
            <th align="center" style="width:10%">任务书:</th>
			<td style="width:35%">
			<input type="text" id="task_code" readonly="readonly" value="${record.task_code}" class="textBoxNoBorder"/>
			<input type="hidden" name="task_id" readonly="readonly" value="${record.task_id}" id="task_id"/>
			</td>
			<th align="center" style="width:10%">项目名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="proj_name" readonly="readonly" value="${record.proj_name}" class="textBoxNoBorder"/>
			<input type="hidden" name="proj_id" readonly="readonly" value="${record.proj_id}" id="proj_id"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">设计类型:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select id="ds_type" dataType="Require" disabled="disabled" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${ds_type}" var="ds_type">
			<option value="${ds_type.key}" <c:if test="${record.ds_type==ds_type.key}">selected</c:if>>${ds_type.value}</option>
			</c:forEach>
			</select></td>
			<!-- 
			<th align="center" style="width:10%">策划书编码:</th>
			<td style="width:35%">
			<input type="text" name="sc_code" id="sc_code" value="${record.sc_code}" maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
             -->
			<th align="center" style="width:10%">策划书编号:</th>
			<td style="width:35%">
			<input type="text" name="sc_num" id="sc_num" readonly="readonly" value="${record.sc_num}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">要求时限:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="deadline" readonly="readonly" value="${record.deadline}" id="deadline" dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">编制人:</th>
			<td style="width:35%">
			<input type="text" id="compiler" readonly="readonly" value="${record.compiler}" class="textBoxNoBorder"/>
			<input type="hidden" name="compiler_id" readonly="readonly" id="compiler_id" value="${record.compiler_id}" /></td>
			<th align="center" style="width:10%">编制日期:</th>
			<td style="width:35%">
			<input type="text" name="compile_time" readonly="readonly" id="compile_time" value="${record.compile_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">设计依据:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
			<c:if test="${editMod=='add'}">
			<textarea name="gist" value="${record.gist}"  dataType="Require"  maxLength="4000"  style="width: 650px; height: 300px;"/>${record.gist}</textarea></td>
			</c:if>
			<c:if test="${editMod!='add'}">
			${record.gist}
			</c:if>
			</tr>
			<tr>
			<th align="center" style="width:10%">设计要点:</th>
			<td style="width:35%" colspan="3">
			<c:if test="${editMod=='add'}">
			<textarea name="key" value="${record.key}"  maxLength="4000"  style="width: 650px; height: 300px;"/>${record.key}</textarea></td>
            </c:if>
			<c:if test="${editMod!='add'}">
			${record.key}
			</c:if>
            </tr>
			<tr>
			<th align="center" style="width:10%">备注:</th>
			<td style="width:35%">
			<textarea name="remark" rows="3" value="${record.remark}"  maxLength="4000"  style="width:99%;border: 0px"/>${record.remark}</textarea></td>
			<th align="center" style="width:10%">设计依据附件:</th>
			<td style="width:35%">
				<a href="#" onclick="openDG('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.id}&folder=Ds_scheme&flag=gist&mark=show&oldname=file')">
				<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
				</a>
			</td>
			</tr>
		</table>
		</div>
		<br/>
		<div class="divMod2">
		<div class="divMod1">任务分解</div></div>
		<table class="dataListTable" width="100%">
			<tr>
			<th>设计人:</th>
			<th>配合部门:</th>
			<th>任务说明:</th>
			<th>完成日期:</th>
			</tr>
			<c:forEach items="${record1}" var="result" varStatus="status">
				<tr>
					<td><c:out value="${result.p_name}" /></td>
		      		<td><c:out value="${result.branchname}" /></td>
		      		<td><c:out value="${result.task}" /></td>
		      		<td><c:out value="${result.finish_time}" /></td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<br/>
		<div class="divMod2">
		<div class="divMod1">审批情况</div>
		<table class="table_border" style="width:100%">
            <tr>
            <th align="center" style="width:10%">审批人:</th>
			<td style="width:35%">
			<input type="text" name="approver_name" readonly="readonly" id="approver_name" readonly="readonly" value="${record.approver_name}" class="textBoxNoBorder"/>
			<input type="text" name="approver_id" readonly="readonly" id="approver_id" value="${record.approver_id}"  dataType="Integer" require="false"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="hidden"/>
			<th align="center" style="width:10%">审批日期:</th>
			<td style="width:35%">
			<input type="text" name="apptime" id="apptime" readonly="readonly" value="${record.apptime}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">审批意见:</th>
			<td style="width:35%" colspan="3">
			<textarea rows="3" name="appopinion" value="${record.appopinion}"  maxLength="500"  style="width:99%"/>${record.appopinion}</textarea></td>
            </tr>
		</table>
		</div>
		<br/>
		<div class="div-button">
			<input	type="button" value="通过" onclick="appr(-1)" style="${btnDisplay}" />
			<input	type="button" value="打回" onclick="appr(-3)" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='appr'){
	$("#approver_id").attr("value","${user.base_info_id}");
	$("#approver_name").attr("value","${user.user_name}");
	$("#apptime").attr("value","${apptime}");
}
</script>
</body>
</html>
