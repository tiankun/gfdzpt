<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript">
function audit(state){
	$("#appstate").attr("value",state);
	if(Validator.Validate(document.form1,3)){
		document.form1.submit();
	}
}

function spec(){
	$("#totalcost").val('${realcost}');
	$("#cpaflag").val(1);
	convert(${realcost});
}

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
       alert("Empty input!"); 
       return ""; 
   } 
   if (currencyDigits.match(/[^,.\d]/) != null) { 
       alert("Invalid characters in the input string!"); 
       return ""; 
   } 
   if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) { 
       alert("Illegal format of digit number!"); 
       return ""; 
   } 
    
//Normalize the format of input digits: 
   currencyDigits = currencyDigits.replace(/,/g, "");    // Remove comma delimiters. 
   currencyDigits = currencyDigits.replace(/^0+/, "");    // Trim zeros at the beginning. 
   // Assert the number is not greater than the maximum number. 
   if (Number(currencyDigits) > MAXIMUM_NUMBER) { 
       alert("Too large a number to convert!"); 
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
   $("#costchn").attr("value",outputCharacters);
}
</script>
</head>
<body>
<form name="form1" action="<%=request.getContextPath()%>/fi/travel/Fi_travelacc!${editMod}.do" method="post">
<script type="text/javascript">${operationSign}</script>

		<div class="divMod2">
		<div class="divMod1">基本信息</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">申请人:</th>
			<td style="width:35%">${recordAcc.person_name}</td>
			<th align="center" style="width:10%">所在部门:</th>
			<td style="width:35%">${recordAcc.dept_name}</td>
			</tr>
            <tr>
			<th align="center" style="width:10%">出差申请:</th>
			<td style="width:35%">${recordAcc.b_time} 出差</td>
			<th align="center" style="width:10%">项目名称:</th>
			<td style="width:35%">
			<c:if test="${not empty recordAcc.proj_name}">${recordAcc.proj_name}</c:if>
			<c:if test="${empty recordAcc.proj_name}"><font color="red">未关联项目</font></c:if>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">付款单位:</th>
			<td style="width:35%">
			<c:if test="${not empty recordAcc.fukuan_unit}">${fukuan[recordAcc.fukuan_unit]}</c:if>
			<c:if test="${empty recordAcc.fukuan_unit}"><font color="red">尚未填写</font></c:if>
			</td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
            </tr>
            <!-- 
            <tr>
			<th align="center" style="width:10%">发票标识:</th>
			<td style="width:35%">
			<input type="text" name="pyt" value="${record.pyt}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%"></th>
            <td style="width:35%"></td>
            </tr>
             -->
		</table>
		</div>
		
		<!-- 随行人员 -->
		<c:if test="${not empty recordSub}">
		<div class="divMod2">
		<div class="divMod1">随行人员</div>
		<table class="dataListTable" width="100%">
		<tr>
		<th>序号</th>
	    <th>姓名</th>
	    <th>部门</th>
	    </tr>
	    <c:forEach items="${recordSub}" var="result" varStatus="status">
		<tr>
		<td>${status.index+1}</td>
		<td width="45%">${result.person_name}</td>
		<td width="45%">${result.dept_name}</td>
		</tr>
		</c:forEach>
	    </table>
		</div>
		</c:if>
		
		<div class="divMod2">
		<div class="divMod1">明细</div>
		<table class="dataListTable" style="width:100%">
		<tr>
		<th width="15%">出差地</th>
		<th width="9%">交通工具</th>
        <th width="10%">到达时间</th>
        <th width="10%">离开时间</th>
        <th width="9%">报销标准</th>
        <th width="8%">住宿方式</th>
        <th width="7%">附件数</th>
        <th width="7%">交通费</th>
        <th width="10%">市内交通费</th>
        <th width="7%">住宿费</th>
        <th width="7%">出差补贴</th>
        </tr>
        <c:forEach items="${recordDtl}" var="result" varStatus="status">
		<tr>
        <td><c:out value="${result.areaname}" /></td>
        <td><c:out value="${result.vehicle}" /></td>
        <td><c:out value="${result.r_time}" /></td>
        <td><c:out value="${result.l_time}" /></td>
        <td style="color: red;background: #EEEEEE"">
        <fmt:formatNumber type="number" value="${result.r_fund}" pattern="#.##" minFractionDigits="2"/>
        </td>
        <td><c:out value="${stayType[result.stay_type]}" /></td>
        <td><c:out value="${result.accessory}" /></td>
        <td>
        <fmt:formatNumber type="number" value="${result.trans_cost}" pattern="#.##" minFractionDigits="2"/>
        </td>
        <td>
        <fmt:formatNumber type="number" value="${result.citytrans_cost}" pattern="#.##" minFractionDigits="2"/>
        </td>
        <td>
        <fmt:formatNumber type="number" value="${result.hotel_cost}" pattern="#.##" minFractionDigits="2"/>
        </td>
        <td>
        <fmt:formatNumber type="number" value="${result.buzhu}" pattern="#.##" minFractionDigits="2"/>
        </td>
        </tr>
        </c:forEach>
		</table>
		<table class="table_border" style="width:100%">
		<tr>
		<th align="center" style="width:10%">报销总金额:</th>
		<td style="width:35%;background: #EEEEEE">
		<input type="text" name="totalcost" id="totalcost" value="${recordAcc.totalcost}" readonly="readonly" dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" style="width:95%;background: #EEEEEE;color:red;" class="textBoxNoBorder"/>
        <!-- <input type="button" onclick="calTotal()" value="计算"> --></td>
        <th align="center" style="width:10%">人民币大写:</th>
		<td style="width:35%;background: #EEEEEE">
		<input type="text" id="costchn" name="costchn" value="${recordAcc.costchn}" readonly="readonly" style="width:95%;background: #EEEEEE;color:red;" class="textBoxNoBorder"/>
		</td>
        </tr>
        </table>
		</div>
		<!-- 审批意见记录 -->
		<c:if test="${not empty recordApp}">
		<div class="divMod2">
		<div class="divMod1">审批记录</div>
		<table class="dataListTable" style="width:100%">
		<tr>
		<th>序号</th>
        <th>审批人</th>
        <th>审批时间</th>
        <th>审批意见</th>
        </tr>
        <c:forEach items="${recordApp}" var="result" varStatus="status">
		<tr>
		<td>${status.index+1}</td>
		<td width="20%">${result.person_name}</td>
		<td width="25%">${result.apptime}</td>
		<td width="45%">${result.appopinion}</td>
		</tr>
		</c:forEach>
		</table>
		</div>
		</c:if>
		<br/>

	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="accid" value="${recordAcc.id}"/>
	<input type="hidden" name="accdept" value="${recordAcc.deptid}"/>
	<input type="hidden" name="departid" id="departid" value=""/>
	<input type="hidden" name="auditid" id="auditid" value=""/>
	<input type="hidden" name="apptime" id="apptime" value=""/>
	<input type="hidden" name="appstate" id="appstate" value=""/>
	<input type="hidden" name="cpaflag" id="cpaflag" value="0"/>
	<c:if test="${cpaFlag=='cpaFlag'}">
	<input type="button" value="领导特批" onclick="spec()" style="${btnDisplay};color:red;" />
	</c:if>
	<div class="divMod2">
	<div class="divMod1">审批意见</div>
	<table class="table_border" style="width:100%">
	<c:if test="${fiFlag=='fiFlag'}">
	<tr>
	<th align="center" style="width:10%">付款单位:</th>
	<td style="width:35%">
	<select name="fukuan_unit" dataType="Require" class="ddlNoBorder" ><option value="">---</option>
	<c:forEach items="${fukuan}" var="fukuan">
	<option value="${fukuan.key}" <c:if test="${recordAcc.fukuan_unit==fukuan.key}">selected</c:if>>${fukuan.value}</option>
	</c:forEach>
	</select>
	</td>
	<th align="center" style="width:10%"></th>
	<td style="width:35%"></td>
	</tr>
	</c:if>
	<tr>
    <th align="center" style="width:10%">审批意见:<span style="color:Red;">*</span></th>
	<td style="width:35%" colspan="3">
	<textarea rows="3" cols="70" name="appopinion" value="" dataType="Require" maxLength="200" style="width:99%;border: 0px"/></textarea></td>
    </tr>
	</table>
	</div>
	
	<div class="div-button">
		<input type="button" value="通过" onclick="audit(1)" style="${btnDisplay}" />
		<input type="button" value="不通过" onclick="audit(-2)" style="${btnDisplay}" />
		<input type="button" value="打回" onclick="audit(-1)" style="${btnDisplay}" />
		<input type="reset" value="重置"/>
		<input type="button" value="关闭" onclick="javascript:closeDG();"/>
	</div>
	</form>
<script type="text/javascript" defer="defer">
$("#auditid").attr("value","${user.base_info_id}");
$("#departid").attr("value","${user.branchid}");
$("#apptime").attr("value","${apptime}");
</script>
</body>
</html>