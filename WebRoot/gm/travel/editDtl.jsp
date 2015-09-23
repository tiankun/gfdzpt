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
<script type="text/javascript" src="../../jscripts/easyUI132/jquery.json-2.4.min.js"></script>
<script type="text/javascript">
$(function(){
	var routData ='${recordTravel.id}'; 
	if(!routData)
		initProv(-1,-1,-1);
	
	$("#province").change(function(){
		var id = $("#province").val();
		if(id>0){
			initCity(id,-1,-1);
		}else{
			$("#city")[0].length=1;	
			$("#district")[0].length=1;
		}
	});	
	
	$("#city").change(function(){
		var id = $("#city").val();
		if(id>0){
			initDistrict(id,-1);
		}else{
			$("#district")[0].length=1;	
		}
	});
	
	$("#district").change(function(){
		var id = $("#district").val();
		if(id>0){
			var prov = $("#province option:selected");
			var cit = $("#city option:selected");
			var coun = $("#district option:selected");
			
			var selPlace = "[";
			selPlace += "{\"name\":\"province\",\"ID\":\""+$(prov).val()+"\",\"palce\":\"" +$(prov).text() +"\"},";
			selPlace += "{\"name\":\"city\",\"ID\":\""+$(cit).val()+"\",\"place\":\"" +$(cit).text() +"\"},";
			selPlace += "{\"name\":\"district\",\"ID\":\""+$(coun).val()+"\",\"place\":\"" +$(coun).text() +"\"}]";
			$("#travelroute").val(selPlace);
		}
	});	
	initOldData();
})


//路线点击
function routeClick(ID,IDpro,IDcity,IDdis){
	initProv(ID,IDpro,IDcity,IDdis);
}

//路线选择
function pChoose(ID){
	$("#district"+ID).empty();
	$("#district"+ID).append("<option>--请选择区县--</option>");
	var pr = $("#province"+ID).val();
	initProv(ID,pr,-1,-1);
}

//路线选择
function dChoose(ID){
	var ci = $("#city"+ID).val();
	initDistrict(ID,ci,-1);
}

/*初始化省数据*/
function initProv(ID,provinceID,cityID,districtID){
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/fi/travel/SysArea!getProvince.do",
		cache:false,
		dataType:'json',
		success:function(data){
			$("#proTD"+ID)[0].length=1;
			$("#proTD"+ID).empty();
			$("#proTD"+ID).append("<select style='width:95%' id='province"+ID+"' name='province"+ID+"' class='ddlNoBorder' onchange='pChoose("+ID+")'></select>");
			$.each(data,function(index,item){
				if(provinceID>0 && item.id==provinceID){
					$("#province"+ID).append("<option value='"+item.id+"' selected>"+item.name+"</option>");
					initCity(ID,provinceID,cityID,districtID);
				}else{
					$("#province"+ID).append("<option value='"+item.id+"'>"+item.name+"</option>");
				}	
			});
		},
		error:function(info){
			alert('获取省出错！');
		},
		complete:function(){
			//if(idVal>0)
		//		$("#province option[value='"+idVal+"']").attr("selected","true");
		}							
	});			
}
/*初始化州市数据*/
function initCity(ID,provinceID,cityID,districtID){
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/fi/travel/SysArea!getCity.do",
		data:"id="+provinceID,
		cache:false,
		dataType:'json',
		success:function(data){					
			$("#cityTD"+ID)[0].length=1;
			$("#disTD"+ID)[0].length=1;
			$("#cityTD"+ID).empty();
			$("#cityTD"+ID).append("<select style='width:95%' id='city"+ID+"' name='city"+ID+"' class='ddlNoBorder' onchange='dChoose("+ID+")'></select>");
			$.each(data,function(index,item){
					if(cityID>0 && item.id==cityID){
						$("#city"+ID).append("<option value='"+item.id+"' selected>"+item.name+"</option>");
						initDistrict(ID,cityID,districtID);
					}else{
						$("#city"+ID).append("<option value='"+item.id+"'>"+item.name+"</option>");
					}	
			});
		},
		error:function(info){
			alert('获取州/市出错！');
		},
		complete:function(){
		//	if(idVal > 0)
		//		$("#city option[value='"+idVal+"']").attr("selected",true);
		}						
	});
}
/*初始化区县数据*/
function initDistrict(ID,cityID,districtID){
	$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/fi/travel/SysArea!getCity.do",
			data:"id="+cityID,
			cache:false,
			dataType:'json',
			success:function(data){		
				$("#disTD"+ID)[0].length=1;	
				$("#disTD"+ID).empty();
				$("#disTD"+ID).append("<select style='width:95%' id='district"+ID+"' name='district"+ID+"' onchange='routefund("+ID+")' class='ddlNoBorder'></select>");
				$.each(data,function(index,item){
					if(districtID>0 && districtID==item.id){
						$("#district"+ID).append("<option value='"+item.id+"' selected>"+item.name+"</option>");
					}else{
						$("#district"+ID).append("<option value='"+item.id+"'>"+item.name+"</option>");
					}					
				});
			},
			error:function(info){
				alert('获取区/县出错！');
			},
			complete:function(){
				//if(idVal > 0)
					//$("#district option[value='"+idVal+"']").attr("selected",true);
			}
		});
}

/*查看、修改功能 让省市县三联动加载预设值*/
function initOldData(){
	var routData ='${record.travelroute}'; 
		if(routData.length>0){
			routData = eval("("+routData+")");
			var provinceID ='';
			var cityID='';
			var districtID='';
			 
			/*  此种方式会引发加载   省市县不对位
			$.each(routData,function(index,item){
				if('province'==item.name){
					initProv(item.ID);
					provID = item.ID;
				}	
				if('city'==item.name){
					initCity(provID,item.ID);
					provID = item.ID;
				}	
				if('district'==item.name){
					initdistrict(provID,item.ID);
				}											
			}); */
			
			$.each(routData,function(index,item){
				if('province'==item.name)
					provinceID = item.ID;
				if('city'==item.name)
					cityID = item.ID;						
				if('district'==item.name)
					districtID = item.ID;
			});
			initProv(provinceID,cityID,districtID);
		}
}

//添加路线
function addRoute(){
	var count = parseInt($("#count").val())+1;
	$("#routeTable").append("<tr id='routeTR"+count+"'><td colspan='7'><iframe name='routeIframe"+count+"' id='routeIframe"+count+"' width='100%' height='25px'"
						 +"frameborder='0' scrolling='no' src='routeInclude.jsp'" 
						 +"marginwidth='0' marginheight='0'></iframe></td><td><a id='delIframe"+count+"' onclick='javascript:return addDel("+count+");'><img src='<%=request.getContextPath()%>/image/delete.png'/></a></td></tr>");
	$("#count").attr("value",count);
}

//删除路线
function addDel(frameId){
	if(confirm("确定删除吗?")){
		$("#routeTR"+frameId+"").remove();
		return true;
	}
	return false;
}

function travelday(){
	var b_time = $("#b_time").val();
	var e_time = $("#e_time").val();
	b_time = b_time.split("-");
	b_time = new Date(b_time[1] + '-' + b_time[2] + '-' + b_time[0]);
	e_time = e_time.split("-");
	e_time = new Date(e_time[1] + '-' + e_time[2] + '-' + e_time[0]);
	var traveldays = parseInt(Math.abs(e_time - b_time) / 1000 / 60 / 60 /24);
	$("#traveldays").attr("value",traveldays);
}

//计算报销标准
function routefund(ID){
	var dis = $("#district"+ID).val();
	var city = $("#city"+ID).val();
	var duty = '${user.duty_id}';
	var arealv = null;
	var rtime = $("#r_time"+ID).val();
	var ltime = $("#l_time"+ID).val();
	var vehi = $("#vehicles"+ID).val();
	var subIds = $("#subIds").val();
	var subc = 0;
	if(subIds!=""){
		var subc = (subIds.split(",")).length;
	}
	var pid = $("#personid").val();
	rtime = rtime.split("-");
	rtime = new Date(rtime[1] + '-' + rtime[2] + '-' + rtime[0]);
	ltime = ltime.split("-");
	ltime = new Date(ltime[1] + '-' + ltime[2] + '-' + ltime[0]);
	var routedays = parseInt(Math.abs(ltime - rtime) / 1000 / 60 / 60 /24);
	if(routedays!=null){
		if(routedays==0){
			if(city=='2832'){
				$("#r_fund"+ID).attr("value",(0).toFixed(2));
			}else{
				$("#r_fund"+ID).attr("value",(15*(subc+1)).toFixed(2));
			}
		}else{
			$.ajax({
				type: "POST",
				   url: "<%=request.getContextPath()%>/fi/travel/SysArea!getAreaLv.do",
				   data: "id="+dis+"&duty="+duty+"&vehi="+vehi+"&subids="+subIds+"&pid="+pid,
				   success: function(data){
					   var total = (data*routedays).toFixed(2);
					   total = isNaN(total) ? "" : total;
					   $("#r_fund"+ID).attr("value",total);
				   }
			});
		}
	}
}

//改变交通工具
function vehichange(ID){
	$("#vehi"+ID).val($("#vehicles"+ID).val());
}

function mySub(flag){
	if(Validator.Validate(document.form1,3)){
		$("#subflag").attr("value",flag);
		var c = $("#count").val();
		var id = null;
		var r_time = null;
		var l_time = null;
		var vehicle = null;
		var province = null;
		var city = null;
		var district = null;
		var r_fund = null;
		var id = $("#id").val();
		var rt = null;
		var routeC = '${routeC}';
		for(i=c;i>1;i--){
			id = "";
			r_time = $("#routeIframe"+i).contents().find("#r_time").attr("value");
			l_time = $("#routeIframe"+i).contents().find("#l_time").attr("value");
			vehicles = $("#routeIframe"+i).contents().find("#vehicles").attr("value");
			province = $("#routeIframe"+i).contents().find("#province").attr("value");
			city = $("#routeIframe"+i).contents().find("#city").attr("value");
			district = $("#routeIframe"+i).contents().find("#district").attr("value");
			r_fund = $("#routeIframe"+i).contents().find("#r_fund").attr("value");
			rt = "{\"id\":\""+id+"\",\"r_time\":\""+r_time+"\",\"l_time\":\""+l_time+"\",\"province\":\""+province+"\",\"vehicles\":\""+vehicles+"\",\"city\":\""+city+"\",\"district\":\""+district+"\",\"r_fund\":\""+r_fund+"\"}";
			$("#routeSave").append('<input type="hidden" id="rtSave'+i+'" name="rtSave" value="'+rt+'"/>');
			$("#rtSave"+i).val(rt);
		}
		for(i=routeC;i>0;i--){
			id = $("input[name='id"+i+"']").val();
			r_time = $("input[name='r_time"+i+"']").val();
			l_time = $("input[name='l_time"+i+"']").val();
			vehicles = $("input[name='vehi"+i+"']").val();
			province = $("input[name='province"+i+"']").val();
			city = $("input[name='city"+i+"']").val();
			district = $("input[name='district"+i+"']").val();
			r_fund = $("input[name='r_fund"+i+"']").val();
			rt = "{\"id\":\""+id+"\",\"r_time\":\""+r_time+"\",\"l_time\":\""+l_time+"\",\"province\":\""+province+"\",\"vehicles\":\""+vehicles+"\",\"city\":\""+city+"\",\"district\":\""+district+"\",\"r_fund\":\""+r_fund+"\"}";
			$("#routeSave").append('<input type="hidden" id="rtSave2000'+i+'" name="rtSave" value="'+rt+'"/>');
			$("#rtSave2000"+i).val(rt);
		}
		document.form1.submit();
	}
}

function routedel(id){
	if(confirm("确定删除吗?")){
		window.location.href="<%=request.getContextPath()%>/gm/travel/Gm_travel!delRoute.do?id="+id;
		return true;
	}
	return false;
}

function advSel(radio){
	if(radio=='1'){
		$("#advTH").empty();
		$("#advTD").empty();
		$("#advTH").append("暂支金额:<span style='color:Red;'>*</span>");
		$("#advTD").append("<input type='text' name='apply_money' dataType='Double' onchange='convert(this.value)' onkeyup='if(isNaN(value))execCommand(\"undo\")' onafterpaste='if(isNaN(value))execCommand(\"undo\")'  style='ime-mode:disabled'  style='width:95%' class='textBoxNoBorder'/>"
							+"<input type='hidden' id='chn' name='chn'/>");
	}else{
		$("#advTH").empty();
		$("#advTD").empty();
	}
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

function allFund(){
	//iframe计算
	var count = parseInt($("#count").val());
	if(count!=1){
		for(i=count;i>1;i--){
			window.frames["routeIframe"+count].routefund();
		}
	}
	//当前页面计算
	$("input[name^='routeId']").each(function(){
		routefund($(this).val());
	});
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

<form name="form1" action="<%=request.getContextPath()%>/gm/travel/Gm_travel!${editMod}Dtl.do" method="post">
<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
<input type="hidden" id="id" name="id" value="${recordTravel.id}"/>
<input type="text" name="applicate_time" id="applicate_time" value="${recordTravel.applicate_time}" class="hidden"/>
<input type="text" name="traveldays" id="traveldays" value="${recordTravel.traveldays}" class="hidden"/>
<input type="hidden" id="count" name="count" value="1"/>
<input type="hidden" id="duty_id" name="duty_id" value="${user.duty_id}"/>
<input type="hidden" id="subflag" name="subflag" value="${recordTravel.subflag}"/>
<input type="hidden" id="appstate" name="appstate" value="${recordTravel.appstate}"/>
<input type="hidden" id="accflag" name="accflag" value="${recordTravel.accflag}"/>
<input type="hidden" id="nextapproverid" name="nextapproverid" value="${recordTravel.nextapproverid}"/>
	<div id="routeSave"></div>
	<div class="divMod2">
	<div class="divMod1">基本信息</div>
	<table class="table_border" style="width:100%">
	
           <tr>
		<th align="center" style="width:10%">出差人:</th>
		<td style="width:35%">
		<input type="text" id="person_name" readonly="readonly" value="${recordTravel.person_name}" class="textBoxNoBorder" style="width:95%"/>
		<input type="text" name="personid" id="personid" value="${recordTravel.personid}" class="hidden" />
		</td>
		<th align="center" style="width:10%">所在部门:</th>
		<td style="width:35%">
		<input type="text" id="dept_name" readonly="readonly" value="${recordTravel.dept_name}" class="textBoxNoBorder" style="width:95%"/>
		<input type="text" name="deptid" id="deptid" value="${recordTravel.deptid}" class="hidden"/>
		</td>
           </tr>
           <c:if test="${editMod!='show'}">
           <tr>
		<th align="center" style="width:10%">随行人员:<c:if test="${editMod!='show'}"><span style="color:Red;">*</span></c:if></th>
		<td colspan="3">
		<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/hr/info/hr_base_infoMulSelect_frame.jsp','subIds','sub');"  value="选择" style="float:left;margin-top:23px;height:23px"/>
		<textarea rows="4" cols="50" style="height:100%;width:90%;border:1px solid #CCC;word-wrap: break-word;" class="textBoxNoBorder" id="sub" name="sub"  readonly="readonly" ><c:forEach items="${recordSub}" var="result" varStatus="status"> ${result.person_name} </c:forEach>
		</textarea>
		<input type="text" onpropertychange="allFund();" id="subIds" name="subids" value='${recordTravel.subids}' class="hidden"/>								  						  
		</td>
           </tr>
           </c:if>
           <tr>
		<th align="center" style="width:10%">出发时间:<c:if test="${editMod!='show'}"><span style="color:Red;">*</span></c:if></th>
		<td style="width:35%">
		<input type="text" id="b_time" name="b_time" value="${recordTravel.b_time}"  dataType="Date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder" />
		</td>
		<th align="center" style="width:10%">返回时间:<c:if test="${editMod!='show'}"><span style="color:Red;">*</span></c:if></th>
		<td style="width:35%">
		<input type="text" id="e_time" name="e_time" value="${recordTravel.e_time}" onchange="travelday()" dataType="Date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/>
		</td>
        </tr>
        <tr>
        <th align="center" style="width:10%">出差事由:<c:if test="${editMod!='show'}"><span style="color:Red;">*</span></c:if></th>
		<td style="width:35%" colspan="3">
		<textarea rows="3" cols="70" name="reason" value="${recordTravel.reason}" dataType="Require" maxLength="1000"  style="width:99%;border: 0px"/>${recordTravel.reason}</textarea></td>
           </tr>
           <tr>
            <th align="center" style="width:10%">暂支申请:<c:if test="${editMod!='show'}"><span style="color:Red;">*</span></c:if></th>
			<td style="width:35%">&nbsp;&nbsp;&nbsp;&nbsp;
			<label><input type="radio" id="adv1" name="adv" value="1" <c:if test="${not empty recordAdv}">checked</c:if> onclick="advSel(this.value)"/>是</label>&nbsp;&nbsp;&nbsp;&nbsp;
			<label><input type="radio" id="adv2" name="adv" value="-1" <c:if test="${empty recordAdv}">checked</c:if> onclick="advSel(this.value)"/>否</label>
			</td>
			<c:if test="${empty recordAdv}">
			<th id="advTH" align="center" style="width:10%"></th>
			<td style="width:35%" id="advTD"></td>
			</c:if>
			<c:if test="${not empty recordAdv}">
			<th id="advTH" align="center" style="width:10%">暂支金额</th>
			<td style="width:35%">
			<input type="text" name="apply_money" value="${recordAdv.apply_money}" maxLength="10" dataType="Double" onchange="convert(this.value)" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/>
			<input type="hidden" id="chn" name="chn" value="${recordAdv.chn}"/>
			</td>
			</c:if>
            </tr>
	</table>
	</div>
	<div class="divMod2">
	<div class="divMod1">出差路线</div>
	<table class="dataListTable" style="width:100%" id="routeTable">
	<tr>
	<th>省</th>
	<th>市</th>
	<th>县</th>
	<th>交通工具</th>
    <th>到达时间</th>
    <th>离开时间</th>
    <th>报销标准</th>
    <th>操作</th>
	</tr>
	<c:forEach items="${recordRoute}" var="result" varStatus="status">
	<tr>
	<td id="proTD${result.id}" width="15%">
	<input type="hidden" id="id${result.id}" name="id${status.index+1}" value="${result.id}"/>
	<input type="hidden" id="province${result.id}" name="province${status.index+1}" value="${result.province}"/>
	<div onclick="routeClick(${result.id},${result.province},${result.city},${result.district})">${result.proname}</div>
	</td>
	<td id="cityTD${result.id}" width="15%">
	<input type="hidden" id="city${result.id}" name="city${status.index+1}" value="${result.city}"/>
	<div onclick="routeClick(${result.id},${result.province},${result.city},${result.district})">${result.cityname}</div>
	</td>
	<td id="disTD${result.id}" width="15%">
	<input type="hidden" id="district${result.id}" name="district${status.index+1}" value="${result.district}"/>
	<div onclick="routeClick(${result.id},${result.province},${result.city},${result.district})">${result.disname}</div>
	</td>
	<td>
	<select dataType="Require" name="vehicles${status.index+1}" id="vehicles${result.id}" class="ddlNoBorder" onchange="routefund(${result.id}),vehichange(${result.id})" style="width: 95%;">
	<option value="">---</option>
	<c:forEach items="${vehicleTool}" var="vehicleTool">
	<option value="${vehicleTool.key}" <c:if test="${result.vehicles==vehicleTool.key}">selected</c:if>>${vehicleTool.value}</option>
	</c:forEach>
	</select>
	<input type="hidden" id="vehi${result.id}" name="vehi${status.index+1}" value="${result.vehicles}"/>
	</td>
	<td width="10%"><input type="text" id="r_time${result.id}" name="r_time${status.index+1}" onchange="routefund(${result.id})" value="${result.r_time}" dataType="Date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder" /></td>
	<td width="10%"><input type="text" id="l_time${result.id}" name="l_time${status.index+1}" onchange="routefund(${result.id})" value="${result.l_time}" dataType="Date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder" /></td>
	<td width="10%"><input type="text" id="r_fund${result.id}" name="r_fund${status.index+1}" value="${result.r_fund}" readonly="readonly" class="textBoxNoBorder" style="width:95%;background: #EEEEEE;color:red;"/></td>
	<td width="10%">
	<a onclick="javascript:return routedel(${result.id});">
 	<img src="<%=request.getContextPath()%>/image/delete.png"/>
 	</a>
 	</td>
	</tr>
	<input type="hidden" id="routeId${result.id}" name="routeId${result.id}" value="${result.id}"/>
	</c:forEach>
	</table>
	</div>
	<img src="<%=request.getContextPath()%>/image/addRoute.png" onclick="addRoute()"/>
	
	<div class="div-button">
		<input	type="button" value="存为草稿" onclick="mySub(0)" style="${btnDisplay}" />
		<input	type="button" value="直接提交" onclick="mySub(1)" style="${btnDisplay}" />
		<input type="reset" value="重置"/>
		<input type="button" value="关闭" onclick="javascript:closeDG();"/>
	</div>
</form>
<script type="text/javascript">
document.getElementById('subIds').watch("value",
   function (id, oldval, newval) {
		allFund();
   });
</script>
</body>
</html>