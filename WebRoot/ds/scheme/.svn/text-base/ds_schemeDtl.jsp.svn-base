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
<script type="text/javascript" src="../../jscripts/easyUI132/jquery.json-2.4.min.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/jscripts/kindeditor/kindeditor-min.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/jscripts/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
//任务分解
function addTbs(){
	 var count = parseInt($("#count").val())+1;
	 $("#tbs").append("<tr id='tbs_tr"+count+"'>"
					+"<td id='designer_td"+count+"'>"
					+"<select name='designer_id' id='designer_id"+count+"' class='dropdownlist'><option value=''>---</option>"
					+"<c:forEach items='${designerList}' var='designerList'>"
					+"<option value='${designerList.key}' <c:if test='${record.designer_id==designerList.key}'>selected</c:if>>${designerList.value}</option>"
					+"</c:forEach></select>"
					+"</td>"
					+"<td id='dept_td"+count+"'>"
					+"<select name='ph_deptid' id='ph_deptid"+count+"' class='dropdownlist'>"
					+"<option value='10' <c:if test='${record.ph_deptid==\"10\"}'>selected</c:if>>客户部</option>"
					+"<option value='7' <c:if test='${record.ph_deptid==\"7\"}'>selected</c:if>>工程部</option></select>"
					+"</td>"
			        +"<td id='task_td"+count+"'>"
			        +"<input type='text' style='width: 95%' name='task' id='task"+count+" value='' maxLength='1000' class='textBoxNoBorder'/>"
			        +"</td>"
			        +"<td id='finish_time_td"+count+"'>"
		            +"<input type='text' style='width: 95%' name='finish_time' id='finish_time"+count+"' onfocus='WdatePicker()' style='ime-mode:disabled' value='' maxLength='1000' class='textBoxNoBorder'/>"
		            +"</td>"
			        +"<td id='btn_td"+count+"'>"
			 		+"<img src='<%=request.getContextPath()%>/image/delete.png' onclick='deltr(this);return false;'/>"
			 		+"</td>"+"</tr>");
	 
	 $("#count").attr("value",count);
}

//添加编辑任务分解
function addeditTbs(){
	 var count = parseInt($("#count").val())+1;
	 $("#tbs").append("<tr id='tbs_tr"+count+"'>"
					+"<td id='designer_td"+count+"'>"
					+"<select name='designer_id"+count+"' id='designer_id"+count+"' class='dropdownlist'><option value=''>---</option>"
					+"<c:forEach items='${designerList}' var='designerList'>"
					+"<option value='${designerList.key}' <c:if test='${record.designer_id==designerList.key}'>selected</c:if>>${designerList.value}</option>"
					+"</c:forEach></select>"
					+"</td>"
					+"<td id='dept_td"+count+"'>"
					+"<select name='ph_deptid"+count+"' id='ph_deptid"+count+"' class='dropdownlist'>"
					+"<option value='10' <c:if test='${record.ph_deptid==\"10\"}'>selected</c:if>>客户部</option>"
					+"<option value='7' <c:if test='${record.ph_deptid==\"7\"}'>selected</c:if>>工程部</option></select>"
					+"</td>"
			        +"<td id='task_td"+count+"'>"
			        +"<input type='text' style='width: 95%' name='task"+count+"' id='task"+count+" value='' maxLength='1000' class='textBoxNoBorder'/>"
			        +"</td>"
			        +"<td id='finish_time_td1"+count+"'>"
		            +"<input type='text' style='width: 95%' name='finish_time"+count+"' id='finish_time"+count+"' onfocus='WdatePicker()' style='ime-mode:disabled' value='' maxLength='1000' class='textBoxNoBorder'/>"
		            +"</td>"
			        +"<td id='btn_td"+count+"'>"
			 		+"<img src='<%=request.getContextPath()%>/image/delete.png' onclick='deltr(this);return false;'/>"
			 		+"</td>"+"</tr>");
	 
	 $("#count").attr("value",count);
}

//删除任务分解
function deltr(obj){
	 $(obj).parent().parent().remove(); //删除当前行
	 sum();
}

//提交
function dosubmit(appstate){
	 var param = $.toJSON($("#form1").serializeObject());
	 if(confirm("确定提交么？")){
		 if(document.form1.gist.value=='')
		 {
		   alert("请填写设计依据");
		   document.form1.gist.focus();
		   return false;
		 }
		 if(document.form1.designer_id1.value=='')
		 {
		   alert("请确定设计人");
		   document.form1.designer_id1.focus();
		   return false;
		 }
		 $("#appstate").attr("value",appstate);
		 $.ajax({
			   type: "POST",
			   url: "<%=request.getContextPath()%>/ds/scheme/Ds_scheme!add.do",
			   data: {"param":param},
			   success: function(){
				   closeDG_refreshW();
			   }
			 });
	 }
}

$.fn.serializeObject = function() {     
    var o = {};     
    var a = this.serializeArray(); 
    
    $.each(a, function() {  
   	 //
      if (o[this.name]) {     
        if (!o[this.name].push) {
       	
          o[this.name] = [ o[this.name] ];     
        }              
        o[this.name].push(this.value || '');     
      } else {
        	o[this.name] = this.value || '';
      }     
    });     
    return o;
};

function sub(appstate)
{
	if(confirm("确定提交修改？"))
		{
		$("#appstate").attr("value",appstate);
		document.form1.submit();
		}
}

function tbsdel(id){
	if(confirm("确定删除吗?")){
		window.location.href="<%=request.getContextPath()%>/ds/scheme/Ds_scheme!deleteTbs.do?id="+id;
		return true;
	}
	return false;
}

$(function(){
	//选择任务书
	$("#dstask").bind("propertychange",function(){
		var data = $(this).val();
		var obj = eval('('+ data +')');
		$("#proj_name").val(obj.proj_name);
		$("#proj_id").val(obj.proj_id);
		$("#task_code").val(obj.dstask_num);
		$("#ds_type").val(obj.ds_type);
		$("#ds_typeh").val(obj.ds_type);
		$("#deadline").val(obj.delivery_time);
		var ds_type=obj.ds_type;
		codeType(ds_type);
	});
})

function codeType(workCode){
	$.ajax({
		   type: "POST",
		   url: '<%=request.getContextPath()%>/sysAdmin/codeCreate!getCodeNum.do',
		   data:"codeType=CH&workCode="+workCode,
		   success: function(data){
			   $("#sc_num").attr("value",data);
		   }
	});
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

	<form name="form1" id="form1" action="<%=request.getContextPath()%>/ds/scheme/Ds_scheme!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="flag" id="flag" value="${record.flag}"/>
	<input type="hidden" name="appstate" id="appstate" value="${record.appstate}"/>
	<input type="hidden" id="ds_typeh" name="ds_type" value="${record.ds_type}"/>
	<input type="hidden" id="appopinion" name="appopinion" value="${record.appopinion}"/>
	<input type="hidden" id="dstask" name="dstask" value=""/>
		<div class="divMod2">
		<div class="divMod1">策划书详情</div>
		<table class="table_border" style="width:100%">
		
            <tr>
            <th align="center" style="width:10%">任务书:</th>
			<td style="width:35%">
			<input type="text" id="task_code" value="${record.task_code}" class="textBoxNoBorder"/>
			<input type="hidden" name="task_id" value="${record.task_id}" id="task_id"/>
			<c:if test="${not empty editMod}">
			<input type="button" onclick="openSelect('dstask_select','<%=request.getContextPath()%>/ds/task/Ds_dstask!taskList.do?pageType=mulselect','task_id','dstask');" value="选择"/></td>
			</c:if>
			</td>
			<th align="center" style="width:10%">项目名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" id="proj_name" value="${record.proj_name}" class="textBoxNoBorder"/>
			<input type="hidden" name="proj_id" value="${record.proj_id}" id="proj_id"/></td>
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
			<input type="text" name="sc_num" id="sc_num" value="${record.sc_num}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">要求时限:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="deadline" value="${record.deadline}" id="deadline" dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">编制人:</th>
			<td style="width:35%">
			<input type="text" id="compiler" value="${record.compiler}" class="textBoxNoBorder"/>
			<input type="hidden" name="compiler_id" id="compiler_id" value="${record.compiler_id}" /></td>
			<th align="center" style="width:10%">编制日期:</th>
			<td style="width:35%">
			<input type="text" name="compile_time" id="compile_time" value="${record.compile_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
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
			<c:if test="${not empty editMod}">
			<c:if test="${editMod=='add'}">
				<a href="#" onclick="openDG('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadFiles.do?f_id=${sc_id}&folder=Ds_scheme&flag=gist')">
			</c:if>
			<c:if test="${editMod=='edit'}">
				<a href="#" onclick="openDG('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadFiles.do?f_id=${record.id}&folder=Ds_scheme&flag=gist')">
			</c:if>	
				<img src="<%=request.getContextPath()%>/image/upload.png"/>
				</a>
			</c:if>	
			<c:if test="${empty editMod}">
				<a href="#" onclick="openDG('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.id}&folder=Ds_scheme&flag=gist&mark=show&oldname=file')">
				<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
				</a>
			</c:if>
			</td>
			</tr>
		</table>
		</div>
		</br>
		
		<c:if test="${not empty record.appopinion}">
		<div class="divMod2">
		<div class="divMod1">审批情况</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">审批状态:</th>
			<td style="width:35%">
			<input type="text" name="appstate" value="${appstate[record.appstate]}" readonly="readonly" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">审批意见:</th>
			<td style="width:35%">
			<input type="text" name="appopinion" value="${record.appopinion}" readonly="readonly" maxLength="500"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">审批人:</th>
			<td style="width:35%">
			<input type="text" name="approver_name" readonly="readonly" id="approver_name" readonly="readonly" value="${record.approver_name}" class="textBoxNoBorder"/>
			<th align="center" style="width:10%">审批日期:</th>
			<td style="width:35%">
			<input type="text" name="apptime" value="${record.apptime}" readonly="readonly" dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
		</table></div>
		</c:if>
		</br>
		
		<!-- 添加页面 -->
		<c:if test="${editMod=='add'}">
		<img src="<%=request.getContextPath()%>/image/add.png" onclick="addTbs()"/>
		<div class="divMod2">
		<div class="divMod1">任务分解</div>
		<table class="dataListTable" style="width:100%" id="tbs">
			<tr>
			<th style="width:20%">设计人:</th>
			<th style="width:20%">配合部门:</th>
			<th style="width:35%">任务说明:</th>
			<th style="width:15%">完成日期:</th>
			<th style="width:10%">操作:</th>
			<input type="hidden" id="count" name="count" value="1"/>
			</tr>
            <tr id="tbs_tr1">
			<td id="designer_td1">
			<select name="designer_id" id="designer_id1" class="dropdownlist"><option value="">---</option>
			<c:forEach items="${designerList}" var="designerList">
			<option value="${designerList.key}" <c:if test="${record.designer_id==designerList.key}">selected</c:if>>${designerList.value}</option>
			</c:forEach>
			</select>
			<!-- 
			<input type="text" style="width: 56%" name="p_name" id="p_name1" value="${record.p_name}" class="textBoxNoBorder" />
			<input type="text" name="designer_id" id="designer_id1" class="hidden" />
			<input type="button" onclick="openSelect('选择人员','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','designer_id1','p_name1');" value="选择" />
			 -->
			</td>
			<td id="dept_td1">
			<select name="ph_deptid" id="ph_deptid1" class="dropdownlist">
			<option value="10" <c:if test="${record.ph_deptid=='10'}">selected</c:if>>客户部</option>
			<option value="7" <c:if test="${record.ph_deptid=='7'}">selected</c:if>>工程部</option>
			</select>
			<!-- 
			<input type="text" style="width: 56%" name="branchname" id="branchname1" value="${record.branchname}" class="textBoxNoBorder" />
			<input type="text" name="ph_deptid" id="ph_deptid1" class="hidden" />
			<input type="button" onclick="openSelect('选择组织机构','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','ph_deptid1','branchname1');" value="选择" />
			 -->
			</td>
            <td id="task_td1">
            <input type="text" style="width: 95%" name="task" id="task1" value="${record.task}" maxLength="1000" class="textBoxNoBorder"/>
            </td>
            <td id="finish_time_td1">
            <input type="text" style="width: 95%" name="finish_time" id="finish_time1" onfocus="WdatePicker()" style="ime-mode:disabled" value="${record.task}" maxLength="1000" class="textBoxNoBorder"/>
            </td>
            <td>
            </td>
            </tr>
		</table>
		</c:if></div>
		</br>
		
		<!-- 查看页面 -->
		<c:if test="${empty editMod}">
		<div class="divMod2">
		<div class="divMod1">任务分解</div>
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
		</c:if></div>
		</br>
		
		<!-- 编辑页面 -->
		<c:if test="${editMod=='edit'}">
		<div class="divMod2">
		<div class="divMod1">任务分解</div>
		<table class="dataListTable" width="100%" id="tbs">
		<img src="<%=request.getContextPath()%>/image/add.png" onclick="addeditTbs()"/>
			<tr>
			<th>设计人:</th>
			<th>配合部门:</th>
			<th>任务说明:</th>
			<th>完成日期:</th>
			<th>操作:</th>
			<input type="hidden" id="count" name="count" value="0"/>
			</tr>
			<c:forEach items="${record1}" var="result" varStatus="status">
				<tr>
					<td>
					<select name="designer_id${result.id}" id="designer_id" class="dropdownlist"><option value="">---</option>
					<c:forEach items="${designerList}" var="designerList">
					<option value="${designerList.key}" <c:if test="${result.designer_id==designerList.key}">selected</c:if>>${designerList.value}</option>
					</c:forEach>
					</select>
					<!-- 
					<input type="text" style="width: 56%" name="p_name" id="p_name${result.id}" value="${result.p_name}" class="textBoxNoBorder" />
					<input type="text" name="designer_id${result.id}" value="${result.designer_id}" id="designer_id" class="hidden" />
					<input type="button" onclick="openSelect('选择人员','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','designer_id${result.id}','p_name${result.id}');" value="选择" />
					 -->
					</td>
					<td>
					<select name="ph_deptid${result.id}" id="ph_deptid" class="dropdownlist">
					<option value="10" <c:if test="${result.ph_deptid=='10'}">selected</c:if>>客户部</option>
					<option value="7" <c:if test="${result.ph_deptid=='7'}">selected</c:if>>工程部</option>
					</select>
					<!--
					<input type="text" style="width: 56%" name="branchname" id="branchname${result.id}" value="${result.branchname}" class="textBoxNoBorder" />
					<input type="text" name="ph_deptid${result.id}" value="${result.ph_deptid}" id="ph_deptid" class="hidden" />
					<input type="button" onclick="openSelect('选择组织机构','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','ph_deptid${result.id}','branchname${result.id}');" value="选择" />
		      		-->
		      		</td>
		      		<td>
		      		<input type="text" style="width: 95%" name="task${result.id}" id="task" value="${result.task}" maxLength="1000" class="textBoxNoBorder"/>
		      		</td>
		      		<td>
		      		<input type="text" style="width: 95%" name="finish_time${result.id}" id="finish_time" onfocus="WdatePicker()" style="ime-mode:disabled" value="${result.finish_time}" maxLength="1000" class="textBoxNoBorder"/>
		      		</td>
		      		<td>
		      		<a onclick="javascript:return tbsdel(${result.id});">
		      		<img src='<%=request.getContextPath()%>/image/delete.png'/>
		      		</a>
		      		</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
		</div>
		</br>
		
		<div class="div-button">
			<c:if test="${editMod=='add'}">
			<input	type="button" value="保存且提交" onclick="dosubmit(1)" style="${btnDisplay}" />
			<input	type="button" value="保存但不提交" onclick="dosubmit(0)" style="${btnDisplay}" />
			</c:if>
			<c:if test="${editMod=='edit'}">
			<input	type="button" value="保存且提交" onclick="sub(1)" style="${btnDisplay}" />
			<input	type="button" value="保存但不提交" onclick="sub(0)" style="${btnDisplay}" />
			</c:if>
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='add'){
//	$("#proj_id").attr("value","${task.proj_id}");
//	$("#proj_name").attr("value","${task.proj_name}");
//	$("#task_id").attr("value","${task.id}");
//	$("#task_code").attr("value","${task.dstask_num}");
//	$("#ds_type").attr("value","${task.ds_type}");
//	$("#ds_typeh").attr("value","${task.ds_type}");
//	$("#deadline").attr("value","${task.delivery_time}");
	$("#compiler_id").attr("value","${user.base_info_id}");
	$("#compiler").attr("value","${user.user_name}");
	$("#flag").attr("value","0");
	$("#compile_time").attr("value","${compile_time}");
}
</script>
</body>
</html>
