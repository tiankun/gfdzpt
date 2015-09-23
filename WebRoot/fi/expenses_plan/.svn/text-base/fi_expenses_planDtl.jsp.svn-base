<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title></title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
<script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/Validator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%= request.getContextPath() %>/jscripts/easyUI132/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/easyUI132/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/easyUI132/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../jscripts/easyUI132/jquery.json-2.4.min.js"></script>
<script type="text/javascript">

//添加开支项目
function addItem(){
	 var count = parseInt($("#count").val())+1;
	 $("#items").append("<tr id='item_tr"+count+"'>"
			 		+"<td id='item_td"+count+"'>"
			 		+"<input type='text' name='item' id='item"+count+"' maxLength='200' value='' style='width:95%' class='textBoxNoBorder'/>"
			 		+"</td>"
			 		+"<td id='money_td"+count+"'>"
			 		+"<input type='text' name='plan_money' id='plan_money"+count+"' value='' maxLength='10' onchange='sum();' onkeyup='if(isNaN(value))execCommand(\"undo\")' onafterpaste='if(isNaN(value))execCommand(\"undo\")' style='ime-mode:disabled' dataType='Double' style='width:95%' class='textBoxNoBorder'/>"
			 		+"</td>"
			 		+"<td id='btn_td"+count+"'>"
			 		+"<img src='<%=request.getContextPath()%>/image/delete.png' onclick='deltr(this);return false;'/>"
			 		+"</td>"+"</tr>");
	 $("#count").attr("value",count);
}

//编辑添加开支项目
function addeditItem(){
	 var count = parseInt($("#count").val())+1;
	 $("#items").append("<tr id='item_tr"+count+"'>"
			 		+"<td id='item_td"+count+"'>"
			 		+"<input type='text' name='item"+count+"' id='item"+count+"' maxLength='200' value='' style='width:95%' class='textBoxNoBorder'/>"
			 		+"</td>"
			 		+"<td id='money_td"+count+"'>"
			 		+"<input type='text' name='plan_money"+count+"' id='plan_money"+count+"' value='' maxLength='10' onchange='sum();' onkeyup='if(isNaN(value))execCommand(\"undo\")' onafterpaste='if(isNaN(value))execCommand(\"undo\")' style='ime-mode:disabled' dataType='Double' style='width:95%' class='textBoxNoBorder'/>"
			 		+"</td>"
			 		+"<td id='btn_td"+count+"'>"
			 		+"<img src='<%=request.getContextPath()%>/image/delete.png' onclick='deltr(this);return false;'/>"
			 		+"</td>"+"</tr>");
	 $("#count").attr("value",count);
}
 
 //删除开支项目
 function deltr(obj){
	 $(obj).parent().parent().remove(); //删除当前行
	 sum();
}
 
 //自动计算总价
 function sum(){
	 var totalMoney = 0;
	 $("#items input[name^='plan_money']").each(function(){
		 var curData = parseFloat($(this).val());
		 if(curData){
		 	totalMoney+=curData;
		 }
	 });
	 $("#total_sum").attr("value",totalMoney);
	 convert(totalMoney);
 }
 
 //人民币大小写自动转换
 function convert(currencyDigits) { 
// Constants: 
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
     
// Variables: 
    var integral;    // Represent integral part of digit number. 
    var decimal;    // Represent decimal part of digit number. 
    var outputCharacters;    // The output result. 
    var parts; 
    var digits, radices, bigRadices, decimals; 
    var zeroCount; 
    var i, p, d; 
    var quotient, modulus; 
     
// Validate input string: 
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
     
// Normalize the format of input digits: 
    currencyDigits = currencyDigits.replace(/,/g, "");    // Remove comma delimiters. 
    currencyDigits = currencyDigits.replace(/^0+/, "");    // Trim zeros at the beginning. 
    // Assert the number is not greater than the maximum number. 
    if (Number(currencyDigits) > MAXIMUM_NUMBER) { 
        alert("Too large a number to convert!"); 
        return ""; 
    } 
     
// Process the coversion from currency digits to characters: 
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
    $("#upper_chn").attr("value",outputCharacters);
}
 
 function dosubmit(flag){
	 if(Validator.Validate(document.form1,3)){
		 $("#subflag").val(flag);
		 var param = $.toJSON($("#form1").serializeObject());
		 $.ajax({
			   type: "POST",
			   url: "<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!add.do",
			   data: {"param":param},
			   success: function(){
				   closeDG_refreshW();
			   }
			 });
	 }
 }
 
 $.fn.serializeObject = function() {     
     var o = {};     
     var a = this.serializeArray(); 
     
     $.each(a, function() {  
    	 //
       if (o[this.name]) {     
         if (!o[this.name].push) {
        	
           o[this.name] = [ o[this.name] ];     
         }              
         o[this.name].push(this.value || '');     
       } else {
         	o[this.name] = this.value || '';
       }     
     });     
     return o;
};

function sub(flag)
{
	$("#subflag").val(flag);
	if(confirm("确定提交申请"))
		{
		document.form1.submit();
		}
}

function sumdel(id){
	if(confirm("确定删除吗?")){
		//sum();
		//var total_sum=$("#total_sum").val();
		//var upper_chn=$("#upper_chn").val();
		window.location.href="<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!deleteItem.do?id="+id;
		return true;
	}
	return false;
}
 </script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" id="form1" action="<%=request.getContextPath()%>/fi/expenses_plan/Fi_expenses_plan!${editMod}.do" method="post">
	<input type="hidden" id="editMod" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" id="id" value="${recordExp.id}"/>
	<input type="hidden" id="personid" name="personid" value="${recordExp.personid}"/>
	<input type="hidden" id="departid" name="departid" value="${recordExp.departid}"/>
	<input type="hidden" id="apply_time" name="apply_time" value="${recordExp.apply_time}"/>
	<input type="hidden" name="print_status" value="${recordExp.print_status}"/>
	<input type="hidden" name="advance_time" value="${recordExp.advance_time}"/>
	<input type="hidden" name="remark" value="${recordExp.remark}"/>
	<input type="hidden" name="appstate" id="appstate" value="${recordExp.appstate}"/>
	<input type="hidden" name="appview" value="${recordExp.appview}"/>
	<input type="hidden" name="subflag" id="subflag" value="${recordExp.subflag}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">预支时间:</th>
			<td style="width:35%">
			<input type="text" name="advance_time" value="${recordExp.advance_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">综合办代购:</th>
			<td style="width:35%">
			<select name="buy" dataType="Require" class="ddlNoBorder"><option value="">---</option>
			<c:forEach items="${buy}" var="buy">
			<option value="${buy.key}" <c:if test="${recordExp.buy==buy.key}">selected</c:if>>${buy.value}</option>
			</c:forEach>
			</select></td>
            </tr>
            
            <c:if test="">
            <tr>
			<th align="center" style="width:10%">批准金额:</th>
			<td style="width:35%">
			<input type="text" name="final_sum" value="${recordExp.final_sum}" dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            </c:if>
		</table>
		
		<!-- 添加页面 -->
		<c:if test="${editMod=='add'}">
		<table class="dataListTable" style="width:100%" id="items">
		<img src="<%=request.getContextPath()%>/image/addDtl.png" onclick="addItem()"/>
			<tr>
			<th style="width:45%">申请事项及说明:</th>
			<th style="width:45%">预计金额:</th>
			<th style="width:10%">操作:</th>
			<input type="hidden" id="count" name="count" value="1"/>
			</tr>
            <tr id="item_tr1">
			<td id="item_td1">
			<input type="text" name="item" id="item1" value="" dataType="Require" maxLength="200"  style="width:95%" class="textBoxNoBorder"/>
			</td>
			<td id="money_td1">
			<input type="text" name="plan_money" id="plan_money1" value="" maxLength="10" onchange="sum()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" dataType="Double" style="width:95%" class="textBoxNoBorder"/>
			</td>
            <td id="btn_td1">
            <!-- <img src="<%=request.getContextPath()%>/image/submit.png" onclick=""/> -->
            <!-- <img src="<%=request.getContextPath()%>/image/delete.png" onclick=""/> -->
            </td>
            </tr>
		</table>
		</c:if>
		
		<!-- 查看页面 -->
		<c:if test="${editMod=='show'}">
		<table class="dataListTable" width="100%">
			<tr>
			<th>申请事项及说明:</th>
			<th>预计金额:</th>
			</tr>
			<c:forEach items="${recordItem}" var="result" varStatus="status">
			<tr>
			<td><c:out value="${result.item}" /></td>
      		<td><c:out value="${result.plan_money}" /></td>
			</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty recordApp}">
		<table class="dataListTable" width="100%">
			<tr>
        		<th align="center" class="myInputTitle" style="width:8%;">审批人</th>
		        <th  align="center" class="myInputTitle" style="width:40%">审批人意见</th>
		        <th align="center" class="myInputTitle" style="width:15%">审批时间</th>
        	</tr>
        	<c:forEach items="${recordApp}" var="appRecord">
			<tr>
			<td><c:out value="${appRecord.name}" /></td>
            <td width="40%">${appRecord.appopinion}</td>
            <td>${appRecord.apptime}</td>
			</tr>
			</c:forEach>
		</table>
		</c:if>
		</c:if>
		
		<!-- 编辑页面 -->
		<c:if test="${editMod=='edit'}">
		<table class="dataListTable" style="width:100%" id="items">
		<img src="<%=request.getContextPath()%>/image/addDtl.png" onclick="addeditItem()"/>
			<tr>
			<th style="width:45%">申请事项及说明:</th>
			<th style="width:45%">预计金额:</th>
			<th style="width:10%">操作:</th>
			<input type="hidden" id="count" name="count" value="0"/>
			</tr>
            <c:forEach items="${recordItem}" var="result" varStatus="status">
			<tr>
			<td>
			<input type="text" name="item${result.id}" value="${result.item}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/>
			</td>
      		<td>
      		<input type="text" name="plan_money${result.id}" value="${result.plan_money}" maxLength="10" onchange="sum()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" dataType="Double" style="width:95%" class="textBoxNoBorder"/>
      		</td>
      		<td>
      		<a onclick="javascript:return sumdel(${result.id});">
      		<img src='<%=request.getContextPath()%>/image/delete.png'/>
      		</a>
      		</td>
			</tr>
			</c:forEach>
		</table>
		</c:if>
		<br/><br/>
		
		<table class="table_border" style="width:100%">
		<tr>
			<th align="center" style="width:10%">申请金额:</th>
			<td style="width:35%;background: #EEEEEE">
			<input type="text" name="total_sum" id="total_sum" value="${recordExp.total_sum}" onchange="convert(this.value)" readonly="readonly" dataType="Double"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%;background: #EEEEEE;color:red;" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">金额大写:</th>
			<td style="width:35%;background: #EEEEEE">
			<input type="text" name="upper_chn" id="upper_chn" value="${recordExp.upper_chn}" readonly="readonly" dataType="Require"  maxLength="1000"  style="width:95%;background: #EEEEEE;color:red;" class="textBoxNoBorder"/></td>
            </tr>
		</table>
		
		<div class="div-button">
			<c:if test="${editMod=='add'}">
			<input type="button" value="存为草稿" onclick="dosubmit(0);" style="${btnDisplay}" />
			<input type="button" value="直接提交" onclick="dosubmit(1);" style="${btnDisplay}" />
			</c:if>
			<c:if test="${editMod=='edit'}">
			<input type="button" value="存为草稿" onclick="sub(0);" style="${btnDisplay}" />
			<input type="button" value="直接提交" onclick="sub(1);" style="${btnDisplay}" />
			</c:if>
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod=='add'){
	$("#personid").attr("value","${user.base_info_id}");
	$("#departid").attr("value","${user.branchid}");
	$("#apply_time").attr("value","${apply_time}");
	$("#appstate").attr("value","1");
}
if(editMod=='edit'){
	sum();
}
</script>	
</body>
</html>
