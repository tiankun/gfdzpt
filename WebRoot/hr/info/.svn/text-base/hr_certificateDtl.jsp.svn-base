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

	<form name="form1" action="<%=request.getContextPath()%>/hr/info/Hr_certificate!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
			<input type="hidden" name="p_id" value="${pid}"/>
            <tr>
			<th align="center" style="width:10%">证书分类:</th>
			<td style="width:35%">
			<select name="cert_type"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${cert_type}" var="cert_type">
			<option value="${cert_type.key}" <c:if test="${record.cert_type==cert_type.key}">selected</c:if>>${cert_type.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">发证部门:</th>
			<td style="width:35%">
			<input type="text" name="fa_zheng" value="${record.fa_zheng}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">主页显示:</th>
			<td style="width:35%">
			<select name="diaplay"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${diaplay}" var="diaplay">
			<option value="${diaplay.key}" <c:if test="${record.diaplay==diaplay.key}">selected</c:if>>${diaplay.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">证书名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="ming_cheng" dataType="Require" value="${record.ming_cheng}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">证书级别:</th>
			<td style="width:35%">
			<select name="cert_level"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${cert_level}" var="cert_level">
			<option value="${cert_level.key}" <c:if test="${record.cert_level==cert_level.key}">selected</c:if>>${cert_level.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">证书专业:</th>
			<td style="width:35%">
			<input type="text" name="zhuang_ye" value="${record.zhuang_ye}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">证书类型:</th>
			<td style="width:35%">
			<select name="major_type"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${major_type}" var="major_type">
			<option value="${major_type.key}" <c:if test="${record.major_type==major_type.key}">selected</c:if>>${major_type.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">证书编号:</th>
			<td style="width:35%">
			<input type="text" name="bian_hao" value="${record.bian_hao}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">认定时间:</th>
			<td style="width:35%">
			<input type="text" name="rd_time" value="${record.rd_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">有效期起:</th>
			<td style="width:35%">
			<input type="text" name="xq_s_time" value="${record.xq_s_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">有效期止:</th>
			<td style="width:35%">
			<input type="text" name="xq_e_time" value="${record.xq_e_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">保管部门:</th>
			<td style="width:35%">
			<input type="text" name="bao_guan" value="${record.bao_guan}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">到期提醒:</th>
			<td style="width:35%">
			<select name="ti_xing"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${ti_xing}" var="ti_xing">
			<option value="${ti_xing.key}" <c:if test="${record.ti_xing==ti_xing.key}">selected</c:if>>${ti_xing.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">年检提醒:</th>
			<td style="width:35%">
            <select name="nian_jian"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${nian_jian}" var="nian_jian">
			<option value="${nian_jian.key}" <c:if test="${record.nian_jian==nian_jian.key}">selected</c:if>>${nian_jian.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">扫描件:</th>
			<td style="width:90%" colspan="3">
				<c:if test="${editMod!='show'}">
				<c:if test="${editMod=='add'}">
					<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${pid}&folder=Hr_certificate&flag=zheng_jian')">
				</c:if>
				<c:if test="${editMod=='edit'}">
					<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${record.p_id}&folder=Hr_certificate&flag=zheng_jian')">
				</c:if>	
					<img src="<%=request.getContextPath()%>/image/upload.png"/>
					</a>
				</c:if>
				<c:if test="${editMod=='show'}">
					<a href="#" onclick="openDGMax('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.p_id}&folder=Hr_certificate&flag=zheng_jian&mark=show')">
					<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
					</a>
				</c:if>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">备注:</th>
			<td style="width:35%" colspan="3">
			<textarea name="remark" value="${record.remark}" rows ="5" maxLength="1000"  style="width:95%"/>${record.remark}</textarea></td>
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
