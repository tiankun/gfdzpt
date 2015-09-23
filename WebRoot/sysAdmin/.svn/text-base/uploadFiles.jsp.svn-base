<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/swfobject.js" charset="UTF-8"></script>
	<script type="text/javascript" src="../jscripts/status.js"></script>
	<script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
	<script type="text/javascript" src="../jscripts/swfobject.js"></script>
	<script type="text/javascript" src="../jscripts/uploadify/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="../jscripts/uploadify/jquery.uploadify.min.js"></script>
	<link rel="stylesheet" href="../jscripts/uploadify/uploadify.css" type="text/css"></link>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script type="text/javascript">
$(document).ready(function() {
	var fileNames = $("#uploadify_pic").val();
    $("#uploadify").uploadify({
    	'height'        : 27, 
    	'width'         : 80,  
    	'fileSizeLimit' : '20000K',
    	'buttonText'	: '浏 览',
        'swf'           : '${pageContext.request.contextPath}/jscripts/uploadify/uploadify.swf',
        'uploader'      : '${pageContext.request.contextPath}/UploadifyServlet?f_id=${f_id}&flag=${flag}&folder=${folder}&oldname=${oldname}',
        'auto'          : false,
        'fileTypeDesc'	: '所有文件',
        'uploadLimit'	: 5,
        'onUploadSuccess':function(file,data,response){
            	//当上传一次,成功后毕执行该方法     -->
            	fileNames += data + ";";
            	$("#uploadify_file").val(fileNames)
        },
        'onQueueComplete':function(queueData){
        	//当列队上传完毕执行该方法
        	document.form1.action = '<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${f_id}&flag=${flag}&folder=${folder}&oldname=file';
        	document.form1.target = "_self";
        	document.form1.submit();
        }
    });
});

function del(id)
{
	if(window.confirm("确定删除此文件?"))
		{
		   document.form1.action = '<%=request.getContextPath()%>/sysAdmin/Sys_uppic!del.do?f_id=${f_id}&id='+id+'&flag=${flag}&folder=${folder}&oldname=file';
           document.form1.submit();
		}
}
</script>
</meta></head>
<body>
    <form name="form1" method="post">
	<table border="1" class="dataListTable" width="100%">
		<tr>
			<td width="50%">
				文件上传<font color="red">(最多一次上传5个文件，单个文件20M以内)</font>
			</td>
			<td width="50%">
				<div id="fileQueue"></div>
				<input type="hidden" name="uploadify" id="uploadify" />
				<a href="javascript:$('#uploadify').uploadify('upload','*')"><font color="blue">开始上传</font> </a>&nbsp;
				<a href="javascript:$('#uploadify').uploadify('cancel', '*')"><font color="red">取消所有上传</font> </a>
				<input type="hidden" Id="uploadify_file" name="uploadify_file" value="" />
			</td>
		</tr>
	</table>
	<div class="divMod2">
	<div class="divMod1">已上传文件</div>
	<table border="0" width="100%" class="dataListTable">
		<c:if test="${picsize!='0'}">
				<tr>
					<th align="center" width="10%">
					</th>
					<th align="center" width="80%">
						文件名
					</th>
					<th align="center" width="10%">
						操作
					</th>
				</tr>
			<c:forEach var="record" items="${filelist}">
				<tr>
					<td width="10%">
					<img height="38px" width="38px" src="<%=request.getContextPath()%>/image/filetype/${record.ext}.png"/>
					</td>
					<td width="80%">
						<a href="<%=request.getContextPath()%>/uploads/${folder}/${record.pic}">${record.name}</a>
					</td>
					<td width="10%">
						<a href="#" onClick="del(${record.id});">
					    <img src="<%=request.getContextPath()%>/image/delete.png"/>
					    </a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	</div>
	<div class="div-button">
		<input type="button" value="确定" onclick="javascript:closeDG();"/>
	</div>
	</form>
</body>
</html>