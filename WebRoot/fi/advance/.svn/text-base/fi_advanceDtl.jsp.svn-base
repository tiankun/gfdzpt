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
<script src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/Validator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

//人民币大小写自动转换
function convert(currencyDigits) { 
//Constants: 
   var MAXIMUM_NUMBER = 99999999999.99; 
   // Predefine the radix characters and currency symbols for output: 
   var CN_ZERO = "零"; 
   var CN_ONE = "壹"; 
   var CN_TWO = "贰"; 
   var CN_THREE = "叁"; 
   var CN_FOUR = "肆"; 
   var CN_FIVE = "伍"; 
   var CN_SIX = "陆"; 
   var CN_SEVEN = "柒"; 
   var CN_EIGHT = "捌"; 
   var CN_NINE = "玖"; 
   var CN_TEN = "拾"; 
   var CN_HUNDRED = "佰"; 
   var CN_THOUSAND = "仟"; 
   var CN_TEN_THOUSAND = "万"; 
   var CN_HUNDRED_MILLION = "亿"; 
   var CN_SYMBOL = "人民币"; 
   var CN_DOLLAR = "元"; 
   var CN_TEN_CENT = "角"; 
   var CN_CENT = "分"; 
   var CN_INTEGER = "整"; 
    
//Variables: 
   var integral;    // Represent integral part of digit number. 
   var decimal;    // Represent decimal part of digit number. 
   var outputCharacters;    // The output result. 
   var parts; 
   var digits, radices, bigRadices, decimals; 
   var zeroCount; 
   var i, p, d; 
   var quotient, modulus; 
    
//Validate input string: 
   currencyDigits = currencyDigits.toString(); 
   if (currencyDigits == "") { 
       alert("未输入数字!"); 
       return ""; 
   } 
   if (currencyDigits.match(/[^,.\d]/) != null) { 
       alert("含有非法字符!"); 
       return ""; 
   } 
   if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) { 
       alert("数字格式非法!"); 
       return ""; 
   } 
    
//Normalize the format of input digits: 
   currencyDigits = currencyDigits.replace(/,/g, "");    // Remove comma delimiters. 
   currencyDigits = currencyDigits.replace(/^0+/, "");    // Trim zeros at the beginning. 
   // Assert the number is not greater than the maximum number. 
   if (Number(currencyDigits) > MAXIMUM_NUMBER) { 
       alert("数字太大!"); 
       return ""; 
   } 
    
//Process the coversion from currency digits to characters: 
   // Separate integral and decimal parts before processing coversion: 
   parts = currencyDigits.split("."); 
   if (parts.length > 1) { 
       integral = parts[0]; 
       decimal = parts[1]; 
       // Cut down redundant decimal digits that are after the second. 
       decimal = decimal.substr(0, 2); 
   } 
   else { 
       integral = parts[0]; 
       decimal = ""; 
   } 
   // Prepare the characters corresponding to the digits: 
   digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE); 
   radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND); 
   bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION); 
   decimals = new Array(CN_TEN_CENT, CN_CENT); 
   // Start processing: 
   outputCharacters = ""; 
   // Process integral part if it is larger than 0: 
   if (Number(integral) > 0) { 
       zeroCount = 0; 
       for (i = 0; i < integral.length; i++) { 
           p = integral.length - i - 1; 
           d = integral.substr(i, 1); 
           quotient = p / 4; 
           modulus = p % 4; 
           if (d == "0") { 
               zeroCount++; 
           } 
           else { 
               if (zeroCount > 0) 
               { 
                   outputCharacters += digits[0]; 
               } 
               zeroCount = 0; 
               outputCharacters += digits[Number(d)] + radices[modulus]; 
           } 
           if (modulus == 0 && zeroCount < 4) { 
               outputCharacters += bigRadices[quotient]; 
           } 
       } 
       outputCharacters += CN_DOLLAR; 
   } 
   // Process decimal part if there is: 
   if (decimal != "") { 
       for (i = 0; i < decimal.length; i++) { 
           d = decimal.substr(i, 1); 
           if (d != "0") { 
               outputCharacters += digits[Number(d)] + decimals[i]; 
           } 
       } 
   } 
   // Confirm and return the final output string: 
   if (outputCharacters == "") { 
       outputCharacters = CN_ZERO + CN_DOLLAR; 
   } 
   if (decimal == "") { 
       outputCharacters += CN_INTEGER; 
   }
   $("#chn").val(outputCharacters);
}

function payeeClear(){
	$("#payeeid").val("");
}

function mySub(flag){
	if(Validator.Validate(document.form1,3)){
		$("#subflag").val(flag);
		document.form1.submit();
	}
}
 /* 文件上传 */
function upload(){
	var f_id = $("#id").val();
    $("#upTd").empty();
	$("#upTd").append("<a href='#' onclick='openDGMax(\"上传附件\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadFiles.do?f_id="+f_id+"&folder=fiAcc&flag=accessory\")'>"
			+"<img src='<%=request.getContextPath()%>/image/upload.png'/></a>");
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/fi/advance/Fi_advance!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" id="id" value="<c:if test="${editMod=='add'}">${accid}</c:if><c:if test="${editMod=='edit'}">${record.id}</c:if>"/>
	<input type="hidden" id="subflag" name="subflag" value="${record.subflag}"/>
	<input type="hidden" id="pid" name="pid" value="${record.pid}"/>
	<input type="hidden" id="deptid" name="deptid" value="${record.deptid}"/>
	<input type="hidden" id="back_money" name="back_money" value="${record.back_money}"/>
	<input type="hidden" id="apply_time" name="apply_time" value="${record.apply_time}"/>
	<input type="hidden" id="nextapproverid" name="nextapproverid" value="${record.nextapproverid}"/>
	<input type="hidden" id="travelid" name="travelid" value="${record.travelid}"/>
	<input type="hidden" name="fiflag" id="fiflag" value="<c:if test="${empty record.id}">0</c:if><c:if test="${not empty record.id}">${record.fiflag}</c:if>"/>
	<input type="hidden" id="print" name="print" value="0"/>
		<div class="divMod2">
		<div class="divMod1">基本信息</div>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">收款单位:</th>
			<td style="width:35%">
			<input type="text" id="payeename" name="payeename" value="${record.payeename}" onchange="payeeClear()" class="textBoxNoBorder" /> 
			<input type="text" id="payeeid" name="payeeid" value="${record.payeeid}" class="hidden" /> 
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','payeeid','payeename');" value="选择" />
			</td>
			<th align="center" style="width:10%">借支类型:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select name="advance_type"  dataType="Require"  class="ddlNoBorder" ><option value="">---</option>
			<c:forEach items="${advance_type}" var="advance_type">
			<option value="${advance_type.key}" <c:if test="${record.advance_type==advance_type.key}">selected</c:if>>${advance_type.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">申请原因:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<select name="apply_reason"  dataType="Require"  class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${apply_reason}" var="apply_reason">
			<option value="${apply_reason.key}" <c:if test="${record.apply_reason==apply_reason.key}">selected</c:if>>${apply_reason.value}</option>
			</c:forEach>
			</select></td>
			<th align="center" style="width:10%">上传附件</th>
			<td style="width:35%" id="upTd">
			<input type="button" value="上传附件" onclick="upload();">
			</td>
            </tr>
            <tr>
            <th align="center" style="width:10%">暂支金额:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="apply_money" value="${record.apply_money}" maxLength="10" onchange="convert(this.value)" dataType="Double" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/>
			</td>
			<th align="center" style="width:10%">大写:</th>
			<td style="width:35%;background: #EEEEEE">
			<input type="text" name="chn" id="chn" value="${record.chn}" style="width:95%;background: #EEEEEE;color:red;" readonly="readonly" class="textBoxNoBorder"/>
            </td>
            </tr>
            <tr>
            <th align="center" style="width:10%">备注:</th>
            <td style="width:35%" colspan="3">
            <textarea name="remark" rows ="3" value="${record.remark}" maxLength="500"  style="width:98%;border: 0px">${record.remark}</textarea>
            </td>
            </tr>
		</table>
		</div>
		<br/>
		<div class="div-button">
			<input type="button" value="存为草稿" onclick="mySub(0)" style="${btnDisplay}" />
			<input type="button" value="直接提交" onclick="mySub(1)" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='add'){
	$("#deptid").val("${user.branchid}");
	$("#pid").val("${user.base_info_id}");
	$("#apply_time").val("${apply_time}");
}
</script>
</body>
</html>
