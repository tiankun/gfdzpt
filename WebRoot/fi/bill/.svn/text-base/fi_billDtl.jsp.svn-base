<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title></title>

		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/jquery.autocomplete1.css" />
		<script src="<%=request.getContextPath()%>/jscripts/jquery.js"
			type="text/javascript">
</script>

		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/jquery.autocomplete1.js">
</script>

		<script
			src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"
			type="text/javascript">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/swfupload.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/handlers.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/phprpc_client.js">
</script>


		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>


		<script type="text/javascript">
var divProcessing = document.getElementById("divProcessing");
var swfu;
SWFUpload.onload = function() {
	var settings_object = {
		upload_url : "<%=request.getContextPath()%>/fi/bill/uploadFile.jsp",
		flash_url : "<%=request.getContextPath()%>/fi/bill/swfupload.swf",
		button_image_url : "<%=request.getContextPath()%>/image/XPButtonUploadText_61x22.png",// Relative to the SWF file
		button_placeholder_id : "spanSWFUploadButton",
		button_width : 61,
		button_height : 22,
		file_size_limit : "1 MB",
		file_types : "*.*",
		file_upload_limit : 0,
		auto_upload : true,
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_start_handler : uploadStartEventHandler,
		upload_success_handler : uploadSuccessEventHandler,
		upload_complete_handler : uploadComplete
	};
	swfu = new SWFUpload(settings_object);
};

// uploadStart处理事件。该函数变量在设置对象中指定给了upload_start_handler属性。
var uploadStartEventHandler = function(file) {
	//alert(file.size);//获取文件信息
	return true;
};
//uploadSuccess处理事件该函数变量在设置对象中指定给了upload_success_handler属性。
var uploadSuccessEventHandler = function(file, server_data) {
	var obj = document.getElementById('zpf');
	var newTr = obj.insertRow();
	var newTd0 = newTr.insertCell();
	newTd0.setAttribute("width", "60%");
	newTd0.setAttribute("align", "left");
	var newTd1 = newTr.insertCell();
	newTd0.innerHTML = '<a href="${pageContext.request.contextPath}/upFile/fi/bill/'
			+ server_data
			+ '" target="_blank">'
			+ server_data
			+ '</a><input type="hidden" name="fujian" value="'
			+ server_data
			+ '">';
	newTd1.style.cssText = "text-align:center";
	newTd1.innerHTML = '<a href=javascript:void(0) onclick=del("' + server_data + '")>删除</a>';
};
//选取文件后事件
function fileDialogComplete(numFilesSelected, numFilesQueued) {
	this.startUpload();
}
//上传一个文件返回后事件
function uploadComplete(file) {
	this.startUpload();
}
function del(pic) {

	document.all.zpf
			.deleteRow(window.event.srcElement.parentElement.parentElement.rowIndex);

}
function mySubmit() {
	if (Validator.Validate(document.form1, 3)) {
		document.form1.submit();
	}
}
</SCRIPT>

		<script language="javascript">
//选择类别
var onecount;
onecount = 0;

subcat = new Array();<c:forEach var="stype" items="${stypelist}" varStatus="s">
subcat[${s.index}] = new Array("${stype.name}","${stype.ftype}","${stype.name}");

<c:set var="i" value="${s.count}"/>
</c:forEach>

onecount=${i};

function changeSheng(ftype)
    {
	if(ftype == '3')
		{
		var qit = document.getElementById("qit");
		qit.style.display = '';
		}
	else
		{
		var qit = document.getElementById("qit");
		qit.style.display = 'none';
		}
	
	
    document.form1.stype.length = 0; 
    var ftype=ftype;
    var i;
    for (i=0;i < onecount; i++)
        {
            if (subcat[i][1] == ftype)
            { 
            document.form1.stype.options[document.form1.stype.length] = new Option(subcat[i][0], subcat[i][2]);
            }        
        }
        
    }
		
		
function getshux(){
	            var compname = document.form1.unit.value;
	           
	            var url = "<%=request.getContextPath()%>/fi/bill/Fi_bill!getCompData.do"; 
				$.ajax({
					type:"POST",
					url:url,
					dataType:"json",
					
					data:{"compname":compname},
					cache:false,
					success:function(data){
					 if(data)
						 {
						   var shibh = "";
						   var addr = "";
						   var phone = "";
						   var kaihh = "";
						   var account = "";
						 
						   shibh =  data.shibh;
						   addr =  data.addr;
								phone =  data.phone;
								kaihh =  data.kaihh;
								account =  data.account;
								
							 $("#shibh").val(shibh);
							 $("#addr").val(addr);
							 $("#phone").val(phone);
							 $("#kaihh").val(kaihh);
							 $("#account").val(account);
						
					  }
					},
					error:function(info){
						alert("未找到属性信息");
					}

				});
		}




function sub1(id) {
	if (!Validator.Validate(document.form1, 3)) {
		
		return false;
	} else {
		document.form1.state.value = id;
		document.form1.action = '${pageContext.request.contextPath}/fi/bill/Fi_bill!add.do';
		document.form1.submit();
	}

}
</script>

		<script type="text/javascript">
<%List listd = (List) request.getAttribute("unitlist");

			if (listd != null && !listd.isEmpty()) {

				String temp = "";
				for (int i = 0; i < listd.size(); i++) {
					Map map = (Map) listd.toArray()[i];
					temp = temp + "," + "\"" + map.get("unit") + "\"";
				}
				temp = "[" + temp.substring(1) + "]";%>      
   var customarray = <%=temp%>;
   var bian = "";
  
<%}%>
</script>
		<script type="text/javascript">
$(document).ready(function() {
	$("#compname").autocomplete(customarray, {
		width : 230,
		matchContains : true,
		scrollHeight : 150,
		minChars : 0,
		max : 10
	});

});
</script>


	</head>
	<body>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1" id="form1"
			action="<%=request.getContextPath()%>/fi/bill/Fi_bill!${editMod}.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="state" />
			<table class="table_border" style="width: 100%">

				<tr>
					<th align="center" style="width: 10%">
						发票类型
					</th>
					<td colspan="3">
						<strong style="font-size: 15px;">大类</strong>&nbsp;&nbsp;&nbsp;
						<select name="ftype" style="width: 17%"
							onchange="changeSheng(document.form1.ftype.options[document.form1.ftype.selectedIndex].value);">
							<option value="">
								未选择
							</option>
							<c:forEach var="ftypelist" items="${ftypelist}">
								<option value="${ftypelist.code}">
									${ftypelist.name}
								</option>
							</c:forEach>
						</select>

						<strong style="font-size: 15px;">小类</strong>&nbsp;&nbsp;&nbsp;
						<select name="stype" style="width: 37%" dataType="Require">
							<option value="">
								未选择
							</option>
						</select>
					</td>
				</tr>

				<tr>
					<th width="10%" align="center" height="35">
						<strong>客户单位</strong>
					</th>
					<td width="35%">
						<input type="text" name="unit" value="${bill.unit}" id="compname"
							style="width: 97%" />
					</td>
					<th width="10%" align="center">
						<strong>发票内容</strong>
					</th>
					<td width="35%">
						<input type="text" name="content" value="${bill.content}"
							style="width: 97%" />
					</td>

				</tr>
				<tr>
					<th width="10%" align="center" height="35">
						<strong>开具金额</strong>
					</th>
					<td width="35%">
						<input type="text" name="money" value="${bill.money}"
							dataType="Double" onkeyup="if(isNaN(value))execCommand('undo')"
							onafterpaste="if(isNaN(value))execCommand('undo')"
							style="ime-mode: disabled" style="width: 97%" />
					</td>
					<th width="10%" align="center">
						<strong>计划收款时间</strong>
					</th>
					<td width="35%">
						<input type="text" name="plan_date" readonly="readonly"
							value="${bill.plan_date}" onFocus="WdatePicker()" />
					</td>

				</tr>
			</table>
			<table class="table_border1"  style="width: 100%">

				<tr id="qit" style="display: none;">
					<td >
						<table width="100%"  align="center">
							<tr >
								<td align="center" height="30" >
									<strong>增值税发票开票信息</strong>
									<input type="button" name="btn" value="获取客户属性"
										onclick="getshux();" />
								</td>
							</tr>
							<tr  align="center">
								<td>
									<table width="100%" >
										<tr >
											<td  width="10%" align="center" height="30">
												<strong>识别号</strong>

											</td>
											<td  width="23%">
												<input type="text" name="shibh" id="shibh">
											</td>
											<td  width="10%" align="center">
												<strong>地址</strong>

											</td>
											<td  width="23%">
												<input type="text" name="addr" id="addr">

											</td>
											<td  width="10%" align="center">
												<strong>电话</strong>

											</td>
											<td  width="23%">
												<input type="text" name="phone" id="phone">

											</td>
										</tr>

										<tr >
											<td  align="center" height="30">
												<strong>开户行</strong>
											</td>
											<td >
												<input type="text" name="kaihh" id="kaihh">

											</td>
											<td  align="center">
												<strong>账号</strong>

											</td>
											<td >
												<input type="text" name="account" id="account">

											</td>
											<td colspan="2" >
											</td>
										</tr>
										<tr>
										    <td><strong>附件</strong></td>
											<td height="35" colspan="5" align="left">
												
												<table width="260px"  cellspacing="0"
													cellpadding="0" id="zpf">
													<c:if test="${fn:length(bill.path)>0}">
														<c:set var="zpf_sfs" value='${fn:split(bill.path,";")}' />
														<c:forEach items="${zpf_sfs}" var="zpf_sf">
															<tr>
																<td width="60%">
																	<a
																		href="${pageContext.request.contextPath}/upFile/fi/bill/${zpf_sf}"
																		target="_blank">${zpf_sf}</a>
																	<input type="hidden" name="fujian" value="${zpf_sf}">
																</td>
																<c:if test="${bill.bill_state=='0'}">
																	<td style="text-align: center">

																		<a href=javascript:void(0) onclick=del('${zpf_sf}');>删除</a>
																	</td>
																</c:if>
															</tr>
														</c:forEach>
													</c:if>
												</table>
												<span id="spanSWFUploadButton"></span>
											</td>

										</tr>
									</table>
								</td>
							</tr>
						</table>

						
					</td>
				</tr>


			</table>
			<br />
			<div class="div-button">

				<input type="button" value="保存不提交" onclick="sub1('0');"
					style="${btnDisplay}" />

				<input type="button" value="保存且提交" onclick="sub1('1');"
					style="${btnDisplay}" />




				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>
		</form>
	</body>
</html>
