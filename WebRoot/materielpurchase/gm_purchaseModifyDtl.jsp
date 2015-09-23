<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title></title>

		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css"
			type="text/css"></link>
		<script src="<%=request.getContextPath()%>/jscripts/jquery.js"
			type="text/javascript">
</script>
		<script
			src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"
			type="text/javascript">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/handlers.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/phprpc_client.js"></script>
	</head>
	<body>
		<script type="text/javascript">
function sub(id) {
	if (confirm("确定提交申请")) {
		document.form1.spzt.value = id;
		document.form1.submit();
	}

}
</script>

<script type="text/javascript">
var divProcessing=document.getElementById("divProcessing");
var swfu;
SWFUpload.onload = function (){
var settings_object = {
upload_url:"<%=request.getContextPath()%>/materielpurchase/uploadFile.jsp",
flash_url:"<%=request.getContextPath()%>/materielpurchase/swfupload.swf",
button_image_url : "<%=request.getContextPath()%>/image/XPButtonUploadText_61x22.png",// Relative to the SWF file
button_placeholder_id : "spanSWFUploadButton",
button_width: 61,
button_height: 22,
file_size_limit:"10 MB",
file_types : "*.*",
file_upload_limit:0,
auto_upload:true,
file_queue_error_handler:fileQueueError,
file_dialog_complete_handler:fileDialogComplete,
upload_start_handler:uploadStartEventHandler,
upload_success_handler:uploadSuccessEventHandler,
upload_complete_handler : uploadComplete
};
swfu = new SWFUpload(settings_object);
};

// uploadStart处理事件。该函数变量在设置对象中指定给了upload_start_handler属性。
var uploadStartEventHandler=function(file){
//alert(file.size);//获取文件信息
	return true;
};
//uploadSuccess处理事件该函数变量在设置对象中指定给了upload_success_handler属性。
var uploadSuccessEventHandler = function (file,server_data) {
var obj=document.getElementById('zpf');
var newTr = obj.insertRow();
var newTd0 = newTr.insertCell();
newTd0.setAttribute("width","60%");
newTd0.setAttribute("align","left"); 
var newTd1 = newTr.insertCell();
newTd0.innerHTML = '<a href="${pageContext.request.contextPath}/upFile/materielpurchase/'+server_data+'" target="_blank">'+server_data+'</a><input type="hidden" name="fujian" value="'+server_data+'">'; 
newTd1.style.cssText="text-align:center"; 
newTd1.innerHTML= '<a href=javascript:void(0) onclick=del("'+server_data+'")>删除</a>';
};
//选取文件后事件
function fileDialogComplete(numFilesSelected,numFilesQueued){
this.startUpload();
}
//上传一个文件返回后事件
function uploadComplete(file) {
this.startUpload();
}
function del(pic){

document.all.zpf.deleteRow(window.event.srcElement.parentElement.parentElement.rowIndex);


}
function mySubmit(){
if(Validator.Validate(document.form1,3)){
document.form1.submit();
}
}



</SCRIPT>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase!subapply.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="opinionid" />
			<input type="hidden" name="spzt" />

			<c:if test="${type=='modify'&&record.spzt!='0'}">
				<div class="divMod2">

					<table class="dataListTable" width="100%">
						<tr>
							<th align="center" style="width: 10%">
								打回原因:
							</th>
							<td colspan="3">
								<textarea rows="3" cols="70" name="opinion">${record.spyj}</textarea>
							</td>
						</tr>

					</table>
				</div>

				<br />

			</c:if>



			<table class="table_border" style="width: 100%">

				<tr>
					<th align="center" style="width: 10%">
						项目:
					</th>
					<td style="width: 35%">
						<input type="text" name="prj_id" value="${record.proname}"
							dataType="Integer" require="false"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						申请部门
					</th>
					<td style="width: 35%">
						${record.branchname}
					</td>
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						配给申请单:
					</th>
					<td style="width: 35%">
						<input type="text" name="ration_apply_id"
							value="${record.ration_apply_id}" dataType="Require"
							style="width: 95%" class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						单号:
					</th>
					<td style="width: 35%">
						<input type="text" name="dh" value="${record.dh}" maxLength="200"
							style="width: 95%" class="textBoxNoBorder" />
					</td>
				</tr>

				<tr>
					<th align="center" style="width: 10%">
						采购申请人:
					</th>
					<td style="width: 35%">
						<input type="text" name="applyname" value="${record.applyname}"
							dataType="Integer" require="false"
							onkeyup="value=value.replace(/[^\d]/g,'')"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						采购执行人:
					</th>
					<td style="width: 35%">
						<input type="text" name="executename"
							value="${record.executename}" maxLength="20" style="width: 95%"
							class="textBoxNoBorder" />
					</td>
				</tr>
				<tr>
					<th align="center" style="width: 10%">
						申请时间:
					</th>
					<td style="width: 35%">
						<input type="text" name="apply_date" value="${record.apply_date}"
							dataType="Date" onfocus="WdatePicker()"
							style="ime-mode: disabled" style="width:95%"
							class="textBoxNoBorder" />
					</td>
					<th align="center" style="width: 10%">
						总金额
					</th>
					<td style="width: 35%">
						${record.xiaoxje}
					</td>
				</tr>
				
				<tr>
						<td height="50" colspan="4">
							<strong>合同扫描件上传：</strong>
							<table width="250px" border="1" cellspacing="0" cellpadding="0"
								id="zpf">
								<c:if test="${fn:length(record.path)>0}">
									<c:set var="zpf_sfs" value='${fn:split(record.path,";")}' />
									<c:forEach items="${zpf_sfs}" var="zpf_sf">
										<tr>
											<td width="60%">
												<a
													href="${pageContext.request.contextPath}/upFile/materielpurchase/${zpf_sf}"
													target="_blank">${zpf_sf}</a>
												<input type="hidden" name="fujian" value="${zpf_sf}">
											</td>
											<td style="text-align: center">

												<a href=javascript:void(0) onclick=del('${zpf_sf}');>删除</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</table>
							<span id="spanSWFUploadButton"></span>
						</td>

					</tr>




			</table>

			<br />
			<div class="divMod2">
				<div class="divMod1">
					所申请材料
				</div>
				<table class="dataListTable" width="100%">
					<tr>


						<th>
							序号
						</th>

						<th>
							物资名称
						</th>
						<th>
							品牌
						</th>
						
						<th>
							规格型号
						</th>

						<th>
							产品参数
						</th>


						<th width="65">
							申请数量
						</th>

						<th>
							单价
						</th>
						<th>
							供货商
						</th>


						<th width="90">
							要求到货日期
						</th>

						<th>
							操作
						</th>


					</tr>
					<c:forEach items="${mlist}" var="result" varStatus="status">
						<tr>

							<td>
								<c:out value="${status.index+1}" />
								<input type="hidden" name="purchaseitemid${result.materiel_id}" value="${result.id}">
							</td>

							<td>
								<c:out value="${result.mnate}" />
							</td>
							<td>
								<c:out value="${result.bname}" />
							</td>

                            <td>
								<c:out value="${result.model}" />
							</td>
							
							<td>
								<c:out value="${result.parameter}" />
							</td>

							<td>
								<input name="sqsl${result.materiel_id}" value="${result.sqsl}"
									style="width: 70%" />
							</td>
							
							<td>
								<input type="text" name="price${result.materiel_id}" value="${result.price}"
									dataType="Require" dataType="Double" require="false"
									onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')"
									style="width: 80%" />
							</td>

							<td>
								<input type="text" name="gong${result.materiel_id}"
									id="gong${result.materiel_id}" readonly="readonly" value="${result.gys}"
									class="textBox" />
								<input type="text" name="gongys${result.materiel_id}"
									id="gongys${result.materiel_id}" value="${result.gongys}"
									class="hidden" />
								<input type="button"
									onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','gongys${result.materiel_id}','gong${result.materiel_id}');"
									value="选择" />
							</td>


							<td>
								<input type="text" name="order_date${result.materiel_id}"
									value="${result.yaoqdhrq}" readonly="readonly" style="ime-mode: disabled"
									style="width:95%" class="textBoxNoBorder" />

							</td>

							<td>
								<a
									href="<%=request.getContextPath()%>/materielpurchase/Gm_purchase!delete.do?id=${result.id}"
									OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
							</td>




						</tr>
					</c:forEach>
				</table>
			</div>



			<br />
			<div class="div-button">
				<input type="button" name="btn" value="保存不提交" onclick="sub('0');" />
				<input type="button" name="btn" value="保存且提交" onclick="sub('1');" />
				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>

			<div id="PageUpDnDiv"
				style="PADDING-TOP: 5px; padding-bottom: 5px; text-align: center">
			</div>
		</form>
	</body>
</html>
