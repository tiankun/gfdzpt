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

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jscripts/easyUI132/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jscripts/easyUI132/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/easyUI132/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/easyUI132/jquery.easyui.min.js"></script>
<script type="text/javascript">
function cnToSpell(cn){
	$.ajax({
		   type: "POST",
		   url: '<%=request.getContextPath()%>/sysAdmin/CnToSp!getFirst.do',
		   data:"cnString="+cn,
		   success: function(data){
			   $("#shortcode").val(data);
		   }
	});
}
</script>
</head>




<body>



<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/materiel/Ma_kind!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"  dataType="Require"  maxLength="100" onchange="cnToSpell(this.value)" style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">上级:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input name="parent_id" id="parent_id" class="easyui-combotree" value="${record.id}" data-options="url:'${pageContext.request.contextPath}/materiel/Ma_kind!getParentTree.do'" style="width:200px;">
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">简码:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="shortcode" id="shortcode" value="${record.shortcode}"  dataType="Require"  maxLength="45"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">链接:</th>
			<td style="width:35%">
			<input type="text" name="url" value="${record.url}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">节点类型:</th>
			<td style="width:35%">
			<input type="text" name="ma_kind_type" value="${record.ma_kind_type}"  maxLength="1"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">图片路径:</th>
			<td style="width:35%">
			<input type="text" name="img_path" value="${record.img_path}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">有效标志:</th>
			<td style="width:35%">
			<input type="text" name="yxbz" value="${record.yxbz}"  maxLength="1"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">备注:</th>
			<td style="width:35%">
			<input type="text" name="note" value="${record.note}"  maxLength="4000"  style="width:95%" class="textBoxNoBorder"/></td>
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
