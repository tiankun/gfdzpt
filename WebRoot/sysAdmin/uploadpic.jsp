<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/swfobject.js" charset="UTF-8"></script>
	<script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
	<script type="text/javascript" src="../jscripts/status.js"></script>
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
    	'fileSizeLimit' : '4000K',
    	'buttonText'	: '浏 览',
        'swf'           : '${pageContext.request.contextPath}/jscripts/uploadify/uploadify.swf',
        'uploader'      : '${pageContext.request.contextPath}/UploadifyServlet?f_id=${f_id}&flag=${flag}&folder=${folder}',
        'auto'          : false,
        'fileTypeExts'  : '*.jpeg;*.jpg',
        'fileTypeDesc'	: '所有*.jpg',
        'uploadLimit'	: 5,
        'onUploadSuccess':function(file,data,response){
            	//当上传一次,成功后毕执行该方法     -->
            	fileNames += data + ";";
            	$("#uploadify_pic").val(fileNames)
        },
        'onQueueComplete':function(queueData){
        	//当列队上传完毕执行该方法
        	document.form1.action = '<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${f_id}&flag=${flag}&folder=${folder}';
        	document.form1.target = "_self";
        	document.form1.submit();
        }
    });
});

function del(id)
{
	if(window.confirm("确定删除此扫描件?"))
		{
		   document.form1.action = '<%=request.getContextPath()%>/sysAdmin/Sys_uppic!del.do?f_id=${f_id}&id='+id+'&flag=${flag}&folder=${folder}';
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
				图片上传<font color="red">(最多上传5张图片，单张扫描件4M以内)</font>
			</td>
			<td width="50%">
				<div id="fileQueue"></div>
				<input type="hidden" name="uploadify" id="uploadify" />
				<a href="javascript:$('#uploadify').uploadify('upload','*')"><font color="blue">开始上传</font> </a>&nbsp;
				<a href="javascript:$('#uploadify').uploadify('cancel', '*')"><font color="red">取消所有上传</font> </a>
				<input type="hidden" Id="uploadify_pic" name="uploadify_pic" value="" />
			</td>
		</tr>
	</table>
	<table border="0" width="100%">
		<c:if test="${picsize!='0'}">
			<tr>
				<td id="td1" colspan="3" style="padding-top: 10px;">
					已上传照片
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%" border="0" style="border-color: black;">
						<c:forEach var="record" items="${piclist}">
							<tr bgcolor="#FFFFFF">
								<td align="center" width="33%">
									<a style="cursor: hand;"
										onClick="window.open('${pageContext.request.contextPath}/sysAdmin/Sys_uppic!displayphoto.do?id=${record.id1}&folder=${folder}',
										'newwindow','top=50, left=200,toolbar=no,menubar=no,scrollbars=yes,height=700,width=800,resizable=no,location=no, status=no')">
									<IMG
										src="<%=request.getContextPath()%>/uploads/${folder}/${record.photo1}"
										width=300 height=300>
								</td>
								<td align="center" width="33%">
									<c:if test="${record.photo2!='none'}">
									<a style="cursor: hand;"
										onClick="window.open('${pageContext.request.contextPath}/sysAdmin/Sys_uppic!displayphoto.do?id=${record.id2}&folder=${folder}',
										'newwindow','top=50, left=200,toolbar=no,menubar=no,scrollbars=yes,height=700,width=800,resizable=no,location=no, status=no')">
									<IMG
										src="<%=request.getContextPath()%>/uploads/${folder}/${record.photo2}"
										width=300 height=300>
									</c:if>
								</td>
								<td align="center" width="33%">
									<c:if test="${record.photo3!='none'}">
									<a style="cursor: hand;"
										onClick="window.open('${pageContext.request.contextPath}/sysAdmin/Sys_uppic!displayphoto.do?id=${record.id3}&folder=${folder}',
										'newwindow','top=50, left=200,toolbar=no,menubar=no,scrollbars=yes,height=700,width=800,resizable=no,location=no, status=no')">
									<IMG
										src="<%=request.getContextPath()%>/uploads/${folder}/${record.photo3}"
										width=300 height=300>
									</c:if>
								</td>
							</tr>
							<tr>
								<td align="center" height="40">
									<a href="#" onClick="del(${record.id1});">
								    <img src="<%=request.getContextPath()%>/image/delete.png"/>
								    </a>
								</td>
								<td align="center" >
									<c:if test="${record.photo2!='none'}">
									<a href="#" onClick="del(${record.id2});">
								    <img src="<%=request.getContextPath()%>/image/delete.png"/>
								    </a>
									</c:if>
								</td>
								<td align="center">
									<c:if test="${record.photo3!='none'}">
									<a href="#" onClick="del(${record.id3});">
									<img src="<%=request.getContextPath()%>/image/delete.png"/>
								    </a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</c:if>
				<tr>
					<td colspan="3" height="10">
					</td>
				</tr>
			</table>
			<div class="div-button">
			<input type="button" value="确定" onclick="javascript:closeDG();"/>
			</div>
		</form>
	</body>
</html>