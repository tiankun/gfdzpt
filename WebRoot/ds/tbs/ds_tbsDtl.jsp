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

	<form name="form1" action="<%=request.getContextPath()%>/ds/tbs/Ds_tbs!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">策划书:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="sc_code" id="sc_code" value="${record.sc_code}" class="textBoxNoBorder" />
			<input type="text" name="scheme_id" id="scheme_id" class="hidden" />
			<input type="button" onclick="openSelect('选择策划书','<%=request.getContextPath()%>/ds/scheme/Ds_scheme!searchList.do?pageType=select','scheme_id','sc_code');" value="选择" />
			</td>
			<th align="center" style="width:10%">完成日期:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="finish_time" value="${record.finish_time}"  dataType="Date"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">设计人:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="p_name" id="p_name" value="${record.p_name}" class="textBoxNoBorder" />
			<input type="text" name="designer_id" id="designer_id" class="hidden" />
			<input type="button" onclick="openSelect('选择人员','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','designer_id','p_name');" value="选择" />
			</td>
			<th align="center" style="width:10%">配合部门:</th>
			<td style="width:35%">
			<input type="text" name="branchname" id="branchname" value="${record.branchname}" class="textBoxNoBorder" />
			<input type="text" name="ph_deptid" id="ph_deptid" class="hidden" />
			<input type="button" onclick="openSelect('选择组织机构','<%=request.getContextPath()%>/sysAdmin/mrbranch!searchList.do?pageType=select','ph_deptid','branchname');" value="选择" />
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">任务说明:<span style="color:Red;">*</span></th>
			<td style="width:35%" colspan="3">
			<textarea name="task" rows="5" value="${record.task}" dataType="Require"  maxLength="1000"  style="width:98%"/>${record.task}</textarea>
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
