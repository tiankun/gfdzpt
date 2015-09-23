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

	<form name="form1" action="<%=request.getContextPath()%>/gm/filereceive/Gm_receive_file!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">文件名称:</th>
			<td style="width:35%">
			<input type="text" name="filename" value="${record.filename}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">文件编号:</th>
			<td style="width:35%">
			<input type="text" name="filenum" value="${record.filenum}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            
            <tr>
			<th align="center" style="width:10%">收发日期:</th>
			<td style="width:35%">
			<input type="text" name="rdate" value="${record.rdate}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">类型:</th>
			<td style="width:35%">
			<select name="type" style="width: 60%;">
			  <option value="收件">收件</option>
			  <option value="发件">发件</option>
			</select>
			
			</td>
            </tr>
            
            <tr>
			<th align="center" style="width:10%">份数:</th>
			<td style="width:35%">
			<input type="text" name="num" value="${record.num}"  maxLength="5"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">收发件人:</th>
			<td style="width:35%">
			<input type="text" name="name" id="name"
								value="${record.bname}"  class="textBox" />
							<input type="text" name="p_id" id="p_id"
								value="${record.p_id}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
								value="选择" />
            </td>
            </tr>
            <tr>
			<th align="center" style="width:10%">备注:</th>
			<td colspan="3">
			<textarea rows="3" cols="70" name="note">${record.note}</textarea>
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
