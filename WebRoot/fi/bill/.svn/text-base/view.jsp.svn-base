<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>在线编辑</title>

		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/default.css" type="text/css"></link>
		<script language="javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
<script src="${pageContext.request.contextPath}/jscripts/jquery.js">
</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/jscripts/Validator.js"></script>

		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/swfupload.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/handlers.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/jscripts/phprpc_client.js">
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






function ssub(id) {
	if (!Validator.Validate(document.form1, 3)) {
		
		return false;
	} else {
		document.form1.state.value = id;
		document.form1.action = '${pageContext.request.contextPath}/fi/bill/Fi_bill!edit.do';
		document.form1.submit();
	}

}


</SCRIPT>



	</head>
	<script type="text/javascript">
${operationSign}
</script>
	<body topmargin="0" style="background-color: white;" marginwidth="0"
		marginheight="0" leftmargin="0" rightmargin="0">
		<form
			
			method="post" name="form1">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input name="id" type="hidden" value="${bill.id}">
			<input name="p_id" type="hidden" value="${bill.p_id}">
			<input name="dept_id" type="hidden" value="${bill.dept_id}">
			<input name="ftype" type="hidden" value="${bill.ftype}">
			<input name="stype" type="hidden" value="${bill.stype}">
			<input type="hidden" name="state" />
			<table class="table_border" style="width: 100%">

				<tr>
					<th align="center" style="width: 10%">
						发票类型:
					</th>
					<td colspan="3">
						<strong style="font-size: 15px;">大类</strong>&nbsp;&nbsp;&nbsp;
						<c:if test="${bill.ftype=='1'}">工程类</c:if>
						<c:if test="${bill.ftype=='2'}">收据类</c:if>
						<c:if test="${bill.ftype=='3'}">其他类</c:if>
						<strong style="font-size: 15px;">小类</strong>&nbsp;&nbsp;&nbsp;
						${bill.stype}
					</td>
				</tr>

				<tr>
					<th width="10%" align="center" height="35">
						<strong>客户单位</strong>
					</th>
					<td width="35%">
						<input type="text" name="unit" value="${bill.unit}" id="compname"
							style="width: 90%" />
					</td>
					<th width="10%" align="center">
						<strong>发票内容</strong>
					</th>
					<td width="35%">
						<input type="text" name="content" value="${bill.content}"
							style="width: 90%" />
					</td>

				</tr>
				<tr>
					<th width="10%" align="center" height="35">
						<strong>开具金额</strong>
					</th>
					<td width="35%">
						<input type="text" name="money" value="${bill.money}"
							style="width: 90%" />
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


            <c:if test="${bill.ftype=='3'}">
			<table class="table_border1" style="width: 100%">

				<tr>
					<td>
					<table width="100%"  align="center">
							<tr >
								<td align="center" height="30" >
									<strong>增值税发票开票信息</strong>
									<c:if test="${bill.bill_state==''||bill.bill_state==null}">
									<input type="button" name="btn" value="获取客户属性"
										onclick="getshux();" />
									</c:if>
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
												<input type="text" name="shibh" id="shibh" value="${bill.shibh}">
											</td>
											<td  width="10%" align="center">
												<strong>地址</strong>

											</td>
											<td  width="23%">
												<input type="text" name="addr" id="addr" value="${bill.addr}">

											</td>
											<td  width="10%" align="center">
												<strong>电话</strong>

											</td>
											<td  width="23%">
												<input type="text" name="phone" id="phone" value="${bill.phone}">

											</td>
										</tr>

										<tr >
											<td  align="center" height="30">
												<strong>开户行</strong>
											</td>
											<td >
												<input type="text" name="kaihh" id="kaihh" value="${bill.kaihh}">

											</td>
											<td  align="center">
												<strong>账号</strong>

											</td>
											<td >
												<input type="text" name="account" id="account" value="${bill.account}">

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
           </c:if>
           
           
           
            
           
           <div class="div-button">
                <c:if test="${bill.bill_state=='0'}">
				  <input type="button" value="保存不提交" onclick="ssub('0');"
					style="${btnDisplay}" />
			    
				  <input type="button" value="保存且提交" onclick="ssub('1');"
					style="${btnDisplay}" />
					</c:if>
				
				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>
			
			
			<br/>
			
			
			
     
		</form>
	</body>
</html>