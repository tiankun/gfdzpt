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

	<form name="form1" action="<%=request.getContextPath()%>/hr/info/Hr_degree!${editMod}.do" method="post">
	<input type="hidden" id="editMod" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		<input type="hidden" name="p_id" value="${pid}"/>
            <tr>
            <th align="center" style="width:10%">学校名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="school" value="${record.school}" dataType="Require"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">所学专业:</th>
			<td style="width:35%">
			<input type="text" name="major" value="${record.major}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
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
			<th align="center" style="width:10%">学历类型:</th>
			<td style="width:35%">
            <select name="degree_type"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${degree_type}" var="degree_type">
			<option value="${degree_type.key}" <c:if test="${record.degree_type==degree_type.key}">selected</c:if>>${degree_type.value}</option>
			</c:forEach>
			</select>
			</td>
            <th align="center" style="width:10%">学位:</th>
			<td style="width:35%">
            <select name="xue_wei"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${xue_wei}" var="xue_wei">
			<option value="${xue_wei.key}" <c:if test="${record.xue_wei==xue_wei.key}">selected</c:if>>${xue_wei.value}</option>
			</c:forEach>
			</select>
			</td>
            </tr>
            <tr>
            <th align="center" style="width:10%">学历:</th>
			<td style="width:35%">
            <select name="degree"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${degree}" var="degree">
			<option value="${degree.key}" <c:if test="${record.degree==degree.key}">selected</c:if>>${degree.value}</option>
			</c:forEach>
			</select>
			</td>
			<th align="center" style="width:10%">毕业/结业:</th>
			<td style="width:35%">
            <select name="bi_ye"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${bi_ye}" var="bi_ye">
			<option value="${bi_ye.key}" <c:if test="${record.bi_ye==bi_ye.key}">selected</c:if>>${bi_ye.value}</option>
			</c:forEach>
			</select>
			</td>
            </tr>
            <tr>
            <th align="center" style="width:10%">备注:</th>
			<td style="width:35%">
			<input type="text" name="remark" value="${record.remark}"  maxLength="1000"  style="width:95%" class="textBoxNoBorder"/></td>
				
			<th align="center" style="width:10%">证书编号:</th>
			<td style="width:35%">
			<input type="text" name="bian_hao" value="${record.bian_hao}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">是否主页显示:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select name="xian_shi" dataType="Require" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${xian_shi}" var="xian_shi">
			<option value="${xian_shi.key}" <c:if test="${record.xian_shi==xian_shi.key}">selected</c:if>>${xian_shi.value}</option>
			</c:forEach>
			</select>
			</td>
			<th align="center" style="width:10%">扫描件:</th>
			<td style="width:35%">
				<c:if test="${editMod!='show'}">
				<c:if test="${editMod=='add'}">
					<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${pid}&folder=Hr_degree&flag=degree_pic')">
				</c:if>
				<c:if test="${editMod=='edit'}">
					<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${record.p_id}&folder=Hr_degree&flag=degree_pic')">
				</c:if>	
					<img src="<%=request.getContextPath()%>/image/upload.png"/>
					</a>
				</c:if>
				<c:if test="${editMod=='show'}">
					<a href="#" onclick="openDGMax('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.p_id}&folder=Hr_degree&flag=degree_pic&mark=show')">
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
