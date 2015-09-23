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
<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/jscripts/EditWin.js"></script> --%>
<script type="text/javascript">
//提交
function mySub(flag){
	if(Validator.Validate(document.form1,3)){
		$("#subflag").val(flag);
		saveTable();
	}
	
}

function upType(value){
	if(value=='4'){
		$("#upTd").empty();
		$("#upTd").append("<a href='#' onclick='openDGMax(\"上传附件\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadFiles.do?f_id=${accid}&folder=fiAcc&flag=accessory\")'>"
				+"<img src='<%=request.getContextPath()%>/image/upload.png'/></a><br/>电汇时，必须上传word附件");
	}
	else{
		$("#upTd").empty();
		$("#upTd").append("<a href='#' onclick='openDGMax(\"上传附件\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${accid}&folder=fiAcc&flag=accessory\")'>"
				+"<img src='<%=request.getContextPath()%>/image/upload.png'/></a><br/>电汇时，必须上传word附件");
	}
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/fi/financial/Fi_financial_account!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" id="id" value="${record.id}"/>
	<input type="hidden" name="fiflag" id="fiflag" value="<c:if test="${empty record.id}">0</c:if><c:if test="${not empty record.id}">${record.fiflag}</c:if>"/>
	<input type="hidden" name="subflag" id="subflag" value=""/>
	<input type="hidden" name="print" id="print" value="0"/>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">支付方式:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select name="advance_type" id="advance_type" onchange="upType(this.value)" dataType="Require"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${advance_type}" var="advance_type">
			<option value="${advance_type.key}" <c:if test="${record.advance_type==advance_type.key}">selected</c:if>>${advance_type.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">项目名称:</th>
			<td style="width:35%">
			<input type="text" id="pro_name" name="pro_name" value="${record.pro_name}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="projectid" name="projectid" value="${record.projectid}"  dataType="Integer" require="false" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','projectid','pro_name');" value="选择"/>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">附件:</th>
			<td style="width:35%" id="upTd">
			<br/>电汇时，必须上传word附件
			</td>
			<th align="center" style="width:10%">收款单位:</th>
			<td style="width:35%">
			<input type="text" id="skdw_name" name="skdw_name" value="${record.skdw_name}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" id="skdw" name="skdw" value="${record.skdw}"  dataType="Integer" require="false" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/customer/Customer!searchList.do?pageType=select','skdw','skdw_name');" value="选择"/>
			</td>
            </tr>
		</table>
		
		<table class="dataListTable" style="width:100%;">
			<thead>
				<th style="text-align: center;width:40%">摘要</th>
				<th style="text-align: center;width:10%">支付金额</th>
				<th style="text-align: center;width:10%">单据数</th>
				<th style="text-align: center;width:20%">备注</th></thead>
			<tbody id="mxlb" >
			<c:forEach items="${record_mx}" var="result" varStatus="status">
			<tr>
			<td>
			<select id="paytype" class="ddlNoBorder" style="width:120px;" onchange="paytypeChange(this)"><option value="">---</option>
			<c:forEach items="${paytype}" var="paytype">
			<option value="${paytype.key}" <c:if test="${result.paytype==paytype.key}">selected</c:if>>${paytype.value}</option>
			</c:forEach>
			</select>
			<input type="text" id="other" value="${result.other}" maxLength="20" style="width:160px;" readonly class="textBoxNoBorder"/>
			</td>
			<td>
			<input type="text" id="paymoney" value="${result.paymoney}" maxLength="10" onChange="sumJE();" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<td>
			<input type="text" id="documentsnum" value="${result.documentsnum}" onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<td>
			<input type="text" id="remark" value="${result.remark}"  maxLength="500"  style="width:95%" class="textBoxNoBorder"/>
			<input type="hidden" id="id" value="${result.id}"/></td>
            </tr>
            </c:forEach>
            </tbody>
		</table>
		<table class="table_border" style="width:100%;">
		<tr>
			<th align="center" style="width:15%">金额合计:</th>
			<td style="width:15%">
			<input type="text" name="apply_money" id="apply_money" dataType="Require" maxLength="11" value="${record.apply_money}" readonly="readonly" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">大写:</th>
			<td style="width:60%">
			<input type="text" name="amount_in_words" id="amount_in_words" dataType="Require" value="${record.amount_in_words}" readonly="readonly" class="textBoxNoBorder" style="width:90%"/>
			<input type="hidden" id="mxJson" name="mxJson"></input></td>
            </tr>
		</table>
		<div class="div-button">
			<input type="button" value="存为草稿" onclick="mySub(0)" style="${btnDisplay}" />
			<input type="button" value="直接提交" onclick="mySub(1)" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
	<table class="dataListTable" style="width:100%;display:none;">
		<tbody id="mb" >
		<tr>
		<td>
		<select id="paytype" class="ddlNoBorder" style="width:120px;" onchange="paytypeChange(this)"><option value="">---</option>
		<c:forEach items="${paytype}" var="paytype">
		<option value="${paytype.key}" <c:if test="${record.paytype==paytype.key}">selected</c:if>>${paytype.value}</option>
		</c:forEach>
		</select>
		<input type="text" id="other" value="" style="width:160px;" readonly class="textBoxNoBorder"/>
		</td>
		<td>
		<input type="text" id="paymoney" value="" onChange="sumJE();" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
		<td>
		<input type="text" id="documentsnum" value="" onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
		<td>
		<input type="text" id="remark" value=""  maxLength="500"  style="width:95%" class="textBoxNoBorder"/>
		<input type="hidden" id="id"/></td>
           </tr>
           </tbody>
	</table>
	<script type="text/javascript">
	var mx_row = $("#mxlb tr").length;
	for(i=0;i<(8-mx_row);i++){
		$("#mxlb").append($("#mb tr:eq(0)").clone());
	}
	
	function paytypeChange(obj){
		if($(obj).val()=="12"){
			$(obj).parent().find("#other").removeAttr("readonly");
			$(obj).parent().find("#other").css("border","red 1pt solid");
		}else{
			$(obj).parent().find("#other").val("");
			$(obj).parent().find("#other").attr("readonly","readonly");
			$(obj).parent().find("#other").css("border","0");
		}
	}
	function sumJE()
	{
		var sum=0;
		$("#mxlb tr").each(function(i,n){
			var obj =$(this).find("input[id='paymoney']");
			var z = $(obj).val();
			if(z!=""){
				if (isNaN(z)) {
					// 验证输入的字符是否为数字
					alert("请检查小写金额是否正确");
					return;
				}
				if (z.indexOf(".") != -1){
					var part = String(z).split(".");
					if (part[1].length > 2) {
						alert("小数点之后只能保留两位,系统将自动截段");
						part[1] = part[1].substr(0, 2);
						z = part[0]+'.'+part[1];
						$(obj).val(z);
					}
				}
				sum = sum+(z*100);
			}
		})
		var sum_je = sum/100;
		$("#apply_money").val(sum_je);
		$("#amount_in_words").val(numToChinese(sum_je,1));
	}
	function saveTable(){
		var rowData = '[';
		var is_submit=true;
		$("#mxlb tr").each(function(i,n){
			var z =$(this).find("input[id='paymoney']").val();
			if(z!=""){
				if($(this).find("input[id='documentsnum']").val()==""){alert("请填写单据数。");is_submit=false;}
				if($(this).find("select[id='paytype']").val()==""){alert("请填摘要。");is_submit=false;}
				rowData = rowData + '{"paytype":"'+$(this).find("select[id='paytype']").val()+ '",';
				rowData = rowData + '"other":"'+$(this).find("input[id='other']").val()+ '",';
				rowData = rowData + '"paymoney":"'+$(this).find("input[id='paymoney']").val()+ '",';
				rowData = rowData + '"documentsnum":"'+$(this).find("input[id='documentsnum']").val()+ '",';
				rowData = rowData + '"id":"'+$(this).find("input[id='id']").val()+ '",';
				rowData = rowData + '"remark":"'+$(this).find("input[id='remark']").val()+ '"';
				rowData = rowData + '},';
			}
		});
		rowData = rowData.substring(0, rowData.length -1) + "]";
		$("#mxJson").val(rowData);
		if($("#apply_money").val()==""||$("#apply_money").val()=="0"){
			is_submit=false;
			alert("请填写金额");
			}
		if(is_submit)document.form1.submit();
	}
	/**
 * 将货币数字（阿拉伯数字）(小写)转化成中文(大写）
 * 
 * @param kin
 *            字符型,小数点之后最多保留两位
 * @param unit
 *            数字型，该金额的单位 说明：1.目前本转换仅支持到仟万亿（元） 2.不支持负数
 *            例：numToChinese("1234.06",1000)
 */
function numToChinese(kin, unit) {
	if (isNaN(kin)) {
		// 验证输入的字符是否为数字
		alert("请检查小写金额是否正确");
		return;
	}
	var Num = String(kin * unit);
	for (i = Num.length - 1; i >= 0; i--) {
		Num = Num.replace(",", "");// 替换tomoney()中的“,”
		Num = Num.replace(" ", "");// 替换tomoney()中的空格
	}
	Num = Num.replace("￥", "");// 替换掉可能出现的￥字符
	
	// ---字符处理完毕，开始转换，转换采用前后两部分分别转换---//
	part = String(Num).split(".");
	newchar = "";
	// 小数点前进行转化
	for (i = part[0].length - 1; i >= 0; i--) {
		if (part[0].length > 16) {
			alert("位数过大，无法计算");
			return "";
		}// 若数量超过仟万亿单位，提示
		tmpnewchar = "";
		perchar = part[0].charAt(i);
		switch (perchar) {
		case "0":
			tmpnewchar = "零" + tmpnewchar;
			break;
		case "1":
			tmpnewchar = "壹" + tmpnewchar;
			break;
		case "2":
			tmpnewchar = "贰" + tmpnewchar;
			break;
		case "3":
			tmpnewchar = "叁" + tmpnewchar;
			break;
		case "4":
			tmpnewchar = "肆" + tmpnewchar;
			break;
		case "5":
			tmpnewchar = "伍" + tmpnewchar;
			break;
		case "6":
			tmpnewchar = "陆" + tmpnewchar;
			break;
		case "7":
			tmpnewchar = "柒" + tmpnewchar;
			break;
		case "8":
			tmpnewchar = "捌" + tmpnewchar;
			break;
		case "9":
			tmpnewchar = "玖" + tmpnewchar;
			break;
		}
		switch (part[0].length - i - 1) {
		case 0:
			tmpnewchar = tmpnewchar + "元";
			break;
		case 1:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "拾";
			break;
		case 2:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "佰";
			break;
		case 3:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "仟";
			break;
		case 4:
			tmpnewchar = tmpnewchar + "万";
			break;
		case 5:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "拾";
			break;
		case 6:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "佰";
			break;
		case 7:
			if (perchar != 0)
				tmpnewchar = tmpnewchar + "仟";
			break;
		case 8:
			tmpnewchar = tmpnewchar + "亿";
			break;
		case 9:
			tmpnewchar = tmpnewchar + "拾";
			break;
		case 10:
			tmpnewchar = tmpnewchar + "佰";
			break;
		case 11:
			tmpnewchar = tmpnewchar + "仟";
			break;
		case 12:
			tmpnewchar = tmpnewchar + "万";
			break;
		case 13:
			tmpnewchar = tmpnewchar + "拾";
			break;
		case 14:
			tmpnewchar = tmpnewchar + "佰";
			break;
		case 15:
			tmpnewchar = tmpnewchar + "仟";
			break;
		}
		newchar = tmpnewchar + newchar;
	}
	// 小数点之后进行转化
	if (Num.indexOf(".") != -1) {
		if (part[1].length > 2) {
			//alert("小数点之后只能保留两位,系统将自动截段");
			part[1] = part[1].substr(0, 2);
		}
		for (i = 0; i < part[1].length; i++) {
			tmpnewchar = "";
			perchar = part[1].charAt(i);
			switch (perchar) {
			case "0":
				tmpnewchar = "零" + tmpnewchar;
				break;
			case "1":
				tmpnewchar = "壹" + tmpnewchar;
				break;
			case "2":
				tmpnewchar = "贰" + tmpnewchar;
				break;
			case "3":
				tmpnewchar = "叁" + tmpnewchar;
				break;
			case "4":
				tmpnewchar = "肆" + tmpnewchar;
				break;
			case "5":
				tmpnewchar = "伍" + tmpnewchar;
				break;
			case "6":
				tmpnewchar = "陆" + tmpnewchar;
				break;
			case "7":
				tmpnewchar = "柒" + tmpnewchar;
				break;
			case "8":
				tmpnewchar = "捌" + tmpnewchar;
				break;
			case "9":
				tmpnewchar = "玖" + tmpnewchar;
				break;
			}
			if (i == 0)
				tmpnewchar = tmpnewchar + "角";
			if (i == 1)
				tmpnewchar = tmpnewchar + "分";
			newchar = newchar + tmpnewchar;
		}
	}
	// 替换所有无用汉字
	while (newchar.search("零零") != -1)
		newchar = newchar.replace("零零", "零");
	newchar = newchar.replace("零亿", "亿");
	newchar = newchar.replace("亿万", "亿");
	newchar = newchar.replace("零万", "万");
	newchar = newchar.replace("零元", "元");
	newchar = newchar.replace("零角", "");
	newchar = newchar.replace("零分", "");
	if (newchar.charAt(newchar.length - 1) == "元"
		|| newchar.charAt(newchar.length - 1) == "角")
		newchar = newchar + "整";
	return newchar;
}
	</script>

<script type="text/javascript" defer="defer">
var editMod=$("#editMod").val();
if(editMod=='add'){
	$("#id").val(${accid});
}
if(editMod=='edit'){
	var ad_type = $("#advance_type").val();
	if(ad_type=='4'){
		$("#upTd").empty();
		$("#upTd").append("<a href='#' onclick='openDGMax(\"上传附件\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadFiles.do?f_id=${record.id}&folder=fiAcc&flag=accessory\")'>"
				+"<img src='<%=request.getContextPath()%>/image/upload.png'/></a><br/>电汇时，必须上传word附件");
	}
	else{
		$("#upTd").empty();
		$("#upTd").append("<a href='#' onclick='openDGMax(\"上传附件\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadpic.do?f_id=${record.id}&folder=fiAcc&flag=accessory\")'>"
				+"<img src='<%=request.getContextPath()%>/image/upload.png'/></a><br/>电汇时，必须上传word附件");
	}
}
</script>
      
</body>
</html>
