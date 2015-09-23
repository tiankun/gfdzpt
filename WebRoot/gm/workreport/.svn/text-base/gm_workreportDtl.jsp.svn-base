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

function sub()
{
	document.form1.action='<%=request.getContextPath()%>/gm/workreport/Gm_workreport!audit.do';
	document.form1.submit();
}


function sub1(id) {
	if (!Validator.Validate(document.form1, 3)) {
		
		return false;
	} else {
		document.form1.state.value = id;
		document.form1.submit();
	}

}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/gm/workreport/Gm_workreport!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="state" />
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:15%">上报日期:</th>
			<td style="width:35%">
			${record.input_date}</td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%">
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">汇报内容:</th>
			<td colspan="3">
			<textarea id="editor_id" name="content"
								style="width: 620px; height: 350px;">${record.content}</textarea>
			</td>
			</tr>
			
			<c:if test="${type=='audit'}">
			<tr>
			<th align="center" style="width:10%">意见:</th>
			<td colspan="3">
			<textarea  name="opinion" cols="70" rows="3" >${record.opinion}</textarea>
			</td>
			</tr>
			</c:if>
            
		</table>
		<br/>
		<div class="div-button">
		   
			
			<c:if test="${type!='audit'}">
			    <input type="button" value="保存不提交" onclick="sub1('0');"
					style="${btnDisplay}" />
			    
				<input type="button" value="保存且提交" onclick="sub1('1');"
					style="${btnDisplay}" />
					
				</c:if>
			
			
			<c:if test="${type=='audit'&&record.isread=='1'}">
			<input	type="button" value="提交" onclick="sub();" style="${btnDisplay}" />
			</c:if>
			
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
</body>
</html>
