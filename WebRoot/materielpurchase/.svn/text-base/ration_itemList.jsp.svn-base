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
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>

		
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/handlers.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/phprpc_client.js"></script>


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

	</head>
	<script type="text/javascript">
function sub(id) {
	

		if (!Validator.Validate(document.form1, 3)) {
			return false;
		}

		var ids = "";
		var flag = true;

		var obj = document.form1.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "checkbox") {
				if (obj[i].checked == true) {
					flag = false;
					ids = ids + ";" + obj[i].value;
				}

			}
		}
		
		if (flag) {
			alert("请至少选择一条记录！");
			return false;
		}

		<c:forEach var="res" begin="1" end="${ssize}" varStatus="s">
	 if(document.getElementById("ck${s.index}").checked)
		{
		  if(document.getElementById("sl${s.index}").value=='')
			  {
			     alert("请填写采购数量");
			     document.getElementById("sl${s.index}").focus();
			     return false;
			  }
		  
		  if(document.getElementById("dj${s.index}").value=='')
			  {
			     alert("请填写采购单价");
			     document.getElementById("dj${s.index}").focus();
			     return false;
			  }
		  
		  if(document.getElementById("gy${s.index}").value=='')
			  {
			     alert("请选择供应商");
			     document.getElementById("gy${s.index}").focus();
			     return false;
			  }
			}  
		
	</c:forEach>
		
		
	if (confirm("确定提交?")) {
		document.form1.spzt.value=id;
		document.form1.submit();
}
		
	
}

function selectAll() {
	var obj = document.form1.elements;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			obj[i].checked = true;
		}
	}
}

function unselectAll() {
	var obj = document.form1.elements;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			if (obj[i].checked == true)
				obj[i].checked = false;
			else
				obj[i].checked = true;
		}
	}
}

function chec() {
	if (document.getElementById("check").checked) {
		selectAll();
	} else {
		unselectAll();
	}
}
</script>
	<script type="text/javascript">
${operationSign}</script>
	<body>
		<form
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase!sub.do"
			method="post" name="form1" id="form1">

			<div align="center">
				<input type="button" name="btn" value="保存不提交" onclick="sub('0');" />
			<input type="button" name="btn" value="保存且提交" onclick="sub('1');" />
			<input type="hidden" name="spzt"/>
			</div>
			<div class="divMod2">
				<div class="divMod1">
					配给申请信息
				</div>
				<table width="99%" class="search_border">
					<input type="hidden" name="prj_id" value="${info.prj_id}" />
					<input type="hidden" name="apply_id" value="${info.id}" />
					<input type="hidden" name="p_id" value="${info.apply_person}" />
					<input type="hidden" name="dept_id" value="${info.apply_dept}" />

					<tr>
						<th>
							编号:
						</th>
						<td>

							<input type="text" name="dh" id="dh" readonly="readonly"
								value="GFD/04-57-${thisyear}" class="textBox" />

						</td>
						<th>
							类型:
						</th>
						<td>

							<input type="radio" name="type" value="1">
							科技&nbsp;&nbsp;
							<input type="radio" name="type" value="0" dataType="Group">
							昆明

						</td>
					</tr>
					
					<tr>
						<th>
							申请人备注:
						</th>
						<td colspan="3">
							<textarea rows="3" style="width: 98%;" name="applynote" readonly="readonly">${info.note}</textarea>



						</td>
					</tr>

					<tr>
						<th>
							备注:
						</th>
						<td colspan="3">
							<textarea rows="3" style="width: 98%;" name="note"></textarea>



						</td>
					</tr>

					<tr>
						<td height="50" colspan="4">
							<strong>合同扫描件上传：</strong>
							<table width="250px" border="1" cellspacing="0" cellpadding="0"
								id="zpf">
								<c:if test="${fn:length(info.path)>0}">
									<c:set var="zpf_sfs" value='${fn:split(info.path,";")}' />
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
			</div>




			<div>


				<table class="dataListTable" width="100%">
					<tr>

						<th>
							<input type="checkbox" onclick="chec();" id="check">
						</th>
						<th width="5">
							序号
						</th>
						<th>
							项目
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

						<th>单位</th>
						<th width="40">
							申请数量
						</th>
						

						<th width="40">
							采购数量
						</th>

						<th>
							单价
						</th>
						<th>
							供货商
						</th>

						<th>
							要求日期
						</th>



					</tr>
					<c:forEach items="${record}" var="result" varStatus="status">
						<tr>
							<td>
								<input type="checkbox" name="checkbox" id="ck${status.index+1}"
									value="${result.materiel_id}" />
								<input type="hidden" name="rationitemid${result.materiel_id}" value="${result.id}"/>
							</td>
							<td>
								<c:out value="${status.index+1}" />
							</td>
							<td>
								<c:out value="${result.proname}" />
							</td>

							<td>
								<c:out value="${result.maname}" />
							</td>
							<td>
								<c:out value="${result.brname}" />
							</td>
							
							<td>
								<c:out value="${result.model}" />
							</td>
							<td>
								<c:out value="${result.parameter}" />
							</td>
							
							<td><c:out value="${result.unit}" /></td>
							<td>
								<c:out value="${result.sqsl}" />
							</td>
							
							<td>
								<input type="text" value="${result.kcg}" id="sl${status.index+1}"
									name="cgsl${result.materiel_id}" dataType="Require"
									dataType="Double" require="false"
									onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')"
									style="width: 70%" />

							</td>
							<td>
								<input type="text" name="price${result.materiel_id}"  id="dj${status.index+1}"
									dataType="Require" dataType="Double" require="false"
									onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')"
									style="width: 70%" />
							</td>

							<td>
								<input type="text" name="gong${result.materiel_id}"  id="gy${status.index+1}" style="width: 80%"
									id="gong${result.materiel_id}" readonly="readonly" value=""
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
									readonly="readonly" style="width: 83%"
									value="${result.order_date}"  />
							</td>

						</tr>
					</c:forEach>
				</table>
			</div>


		</form>

	</body>
</html>