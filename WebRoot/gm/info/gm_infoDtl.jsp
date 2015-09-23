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
</head>

<script language="javascript">
var editor;
KindEditor.ready(function(K) {
			editor = K.create('textarea[name="content"]',
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

function check()
{
	if(document.form1.title.value=='')
		{
		  alert("请填写标题！");
		  document.form1.title.focus();
		  return false;
		}
	if(document.form1.content.value=='')
		{
		  alert("请填写内容！");
		  
		  return false;
		}
	
	document.form1.submit();
}
</script>

<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/gm/info/Gm_info!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">标题:</th>
			<td style="width:35%">
			<input type="text" name="title" value="${record.title}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">所属模块:</th>
			<td style="width:35%">
			<input type="text" name="moduleid" value="${record.moduleid}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/>
			</td>
			
            </tr>
            
            <tr>
			<th align="center" style="width:10%">内容:</th>
			<td colspan="3">
			<textarea id="editor_id" name="content"
								style="width: 620px; height: 350px;">${record.content}</textarea>
			</td>
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="check();" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
</body>
</html>
