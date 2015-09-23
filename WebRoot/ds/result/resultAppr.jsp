<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld"  prefix="fn"%>
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
<link rel="stylesheet" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/easyUI132/jquery.easyui.min.js"></script>
<script type="text/javascript">
function appr(appstate){
	if(confirm("确定提交审核意见？")){
		$("#appstate").attr("value",appstate);
		document.form1.submit();
		}
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/ds/result/Ds_result!${editMod}.do" method="post">
	<input type="hidden" id="editMod" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="scheme" id="scheme" value=""/>
	<input type="hidden" id="appstate" name="appstate" value="${record.appstate}"/>
	<input type="hidden" id="ds_type" name="ds_type" value="${record.ds_type}"/>
	<input type="hidden" id="js_deptid" name="js_deptid" value="${record.js_deptid}"/>
		<!-- tab开始 -->
		<div style="margin:10px 0;"></div>
		<div class="easyui-tabs" style="width:780px">
		
		<!-- 设计成果清单 -->
		<!--  -->
		<!--  -->
		<div title="设计成果清单" style="padding:10px">
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">项目名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="proj_name" name="proj_name" readonly="readonly" value="${record.proj_name}" class="textBoxNoBorder"/>
			<input type="text" name="proj_id" id="proj_id" class="hidden" value="${record.proj_id}"  dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"/>
			<th align="center" style="width:10%">策划书编号:</th>
			<td style="width:35%">
			<input type="text" id="sc_code" name="sc_code" readonly="readonly" value="${record.proj_name}" class="textBoxNoBorder"/>
			<input type="text" name="scheme_id" id="scheme_id" class="hidden" value="${record.scheme_id}"  dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"/>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">清单编号:</th>
			<td style="width:35%">
			<input type="text" name="num" id="" value="${record.num}"  readonly="readonly" maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">设计类型:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select dataType="Require" readonly="readonly" disabled="disabled" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${ds_type}" var="ds_type">
			<option value="${ds_type.key}" <c:if test="${record.ds_type==ds_type.key}">selected</c:if>>${ds_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">接收部门:</th>
			<td style="width:35%">
			<label><input type="checkbox" disabled="disabled" id="js_deptid1" <c:if test="${fn:contains(record.js_deptid,'7')}">
												checked="checked"
											 	</c:if>
						  value="7"/>工程部</label>
			<label><input type="checkbox" disabled="disabled" id="js_deptid2" <c:if test="${fn:contains(record.js_deptid,'10')}">
												checked="checked"
										 		</c:if>
						  value="10"/>客户部</label>
			</td>
			<th align="center" style="width:10%">编制人:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="compiler_name" name="compiler_name" readonly="readonly" value="${record.compiler_name}" class="textBoxNoBorder"/>
			<input type="text" name="compiler_id" id="compiler_id" readonly="readonly" value="${record.compiler_id}"  dataType="Integer"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="hidden"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">编制日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="compile_time" id="compile_time" readonly="readonly" value="${record.compile_time}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">管理费:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="manage_cost" value="${record.manage_cost}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">人工费:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="labor_cost" value="${record.labor_cost}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">安装费:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="install_cost" value="${record.install_cost}"  dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
            <tr>
			<th align="center" style="width:10%">审批人:</th>
			<td style="width:35%">
			<input type="text" name="approver_name" readonly="readonly" id="approver_name" readonly="readonly" value="${record.approver_name}" class="textBoxNoBorder"/>
			<input type="text" name="approver_id" readonly="readonly" id="approver_id" value="${record.approver_id}"  dataType="Integer" require="false"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="hidden"/></td>
			<th align="center" style="width:10%">审批日期:</th>
			<td style="width:35%">
			<input type="text" name="apptime" readonly="readonly" id="apptime" value="${record.apptime}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">审批意见:</th>
			<td style="width:35%" colspan="3">
			<textarea rows="3" name="appopinion" value="${record.appopinion}"  maxLength="500"  style="width:99%;border: 0px"/>${record.appopinion}</textarea></td>
            </tr>
		</table>
		</div>
		
		<!-- 推荐产品清单 -->
		<!--  -->
		<!--  -->
		<div title="推荐产品清单" style="padding:10px">
		<table class="dataListTable" style="width:100%">
			<tr>
			<th width="5%">序号</th>
           	<th>表单名</th>
           	<th width="5%">顺序</th>
		        <th width="200px">操作</th>
			</tr>
			<c:forEach items="${record1}" var="result" varStatus="status">
				<tr>
			<td>${status.index+1}</td>		
      		<td><c:out value="${result.table_name}" /></td>
      		<td><c:out value="${result.shunxu}" /></td>
				<td width="200px">
				<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/ds/result/Ds_product!searchById.do?table_id=${result.id}','ds_productDtl')">查看</a>
				</tr>
			</c:forEach>
		</table>
		</div>
		
		<!-- 进度计划 -->
		<!--  -->
		<!--  -->
		<div title="进度计划" style="padding:10px">
			<table class="dataListTable" style="width:100%">
			<tr>
			<th style="width:20%">任务名:</th>
			<th style="width:20%">前置任务:</th>
			<th style="width:20%">资源:</th>
			<th style="width:15%">开始时间:</th>
			<th style="width:15%">结束时间:</th>
			</tr>
			<c:forEach items="${record3}" var="result" varStatus="status">
			<tr>
			<td><c:out value="${result.task_name}" /></td>
      		<td><c:out value="${result.ftask_name}" /></td>
      		<td><c:out value="${result.sc_res}" /></td>
      		<td><c:out value="${result.start_time}" /></td>
      		<td><c:out value="${result.end_time}" /></td>
			</c:forEach>
			</tr>
			</table>
		</div>
		
		<!-- tab结束 -->
		</div>
		<br/>
		<div class="div-button">
			<input	type="button" value="通过" onclick="appr(-1)" style="${btnDisplay}" />
			<input	type="button" value="打回" onclick="appr(-3)" style="${btnDisplay}" />
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
