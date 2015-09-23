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

	<form name="form1" action="<%=request.getContextPath()%>/hr/info/Hr_compact!${editMod}.do" method="post">
	<input type="hidden" id="editMod" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
			<input type="hidden" name="p_id" value="${pid}"/>
            <tr>
			<th align="center" style="width:10%">编号:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="bian_hao" value="${record.bian_hao}" dataType="Require"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
           	<th align="center" style="width:10%">类型:</th>
			<td style="width:35%">
			<select name="compact_type"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${compact_type}" var="compact_type">
			<option value="${compact_type.key}" <c:if test="${record.compact_type==compact_type.key}">selected</c:if>>${compact_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">签订方式:</th>
			<td style="width:35%">
			<select name="fang_shi"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${fang_shi}" var="fang_shi">
			<option value="${fang_shi.key}" <c:if test="${record.fang_shi==fang_shi.key}">selected</c:if>>${fang_shi.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">签订日期:</th>
			<td style="width:35%">
			<input type="text" name="qd_time" value="${record.qd_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">开始时间:</th>
			<td style="width:35%">
			<input type="text" name="start_time" value="${record.start_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">结束时间:</th>
			<td style="width:35%">
			<input type="text" name="end_time" value="${record.end_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">试用开始时间:</th>
			<td style="width:35%">
			<input type="text" name="sy_st_time" value="${record.sy_st_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">试用结束时间:</th>
			<td style="width:35%">
			<input type="text" name="sy_ed_time" value="${record.sy_ed_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
            <tr>
			<th align="center" style="width:10%">补偿金额:</th>
			<td style="width:35%">
			<input type="text" name="zz_buchang" value="${record.zz_buchang}"  maxLength="22"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">违约金额:</th>
			<td style="width:35%">
			<input type="text" name="zz_weiyue" value="${record.zz_weiyue}"  maxLength="22"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">合同解除/终止时间:</th>
			<td style="width:35%">
			<input type="text" name="zz_shijian" value="${record.zz_shijian}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">备注:</th>
			<td style="width:35%">
			<input type="text" name="remark" value="${record.remark}"  maxLength="4000"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">扫描件:</th>
			<td style="width:90%" colspan="3">
				<c:if test="${editMod!='show'}">
				<c:if test="${editMod=='add'}">
					<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${pid}&folder=Hr_compact&flag=compact_pic')">
				</c:if>
				<c:if test="${editMod=='edit'}">
					<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${record.p_id}&folder=Hr_compact&flag=compact_pic')">
				</c:if>	
					<img src="<%=request.getContextPath()%>/image/upload.png"/>
					</a>
				</c:if>
				<c:if test="${editMod=='show'}">
					<a href="#" onclick="openDGMax('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.p_id}&folder=Hr_compact&flag=compact_pic&mark=show')">
					<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
					</a>
				</c:if>
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
