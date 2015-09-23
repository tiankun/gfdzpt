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
function routeClick(COUNT,IDpro,IDcity,IDdis){
	initProv(COUNT,IDpro,IDcity,IDdis);
}

//交通工具点击
function vehiClick(COUNT,vehiID){
	$("#vehiTD"+COUNT).empty();
	$("#vehiTD"+COUNT).append("<select dataType='Require' name='vehicles"+COUNT+"' id='vehicles"+COUNT+"' class='ddlNoBorder' onchange='dtlfund("+COUNT+")' style='width: 95%;'>"
			   +"<option value=''>---</option>"
			   +"<c:forEach items='${vehicleTool}' var='vehicleTool'>"
			   +"<option value='${vehicleTool.key}'>${vehicleTool.value}</option>"
			   +"</c:forEach>"						   
			   +"</select>");
}

//路线选择
function pChoose(COUNT){
	$("#district"+COUNT).empty();
	$("#district"+COUNT).append("<option>--请选择区县--</option>");
	var pr = $("#province"+COUNT).val();
	initProv(COUNT,pr,-1,-1);
}

//路线选择
function dChoose(COUNT){
	var ci = $("#city"+COUNT).val();
	initDistrict(COUNT,ci,-1);
}

/*初始化省数据*/
function initProv(COUNT,provinceID,cityID,districtID){
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/fi/travel/SysArea!getProvince.do",
		cache:false,
		dataType:'json',
		success:function(data){
			//$("#proTD"+COUNT)[0].length=1;
			$("#proTD"+COUNT).empty();
			$("#proTD"+COUNT).append("<select style='width:95%' id='province"+COUNT+"' name='province"+COUNT+"' class='ddlNoBorder' onchange='pChoose("+COUNT+")'></select>");
			$.each(data,function(index,item){
				if(provinceID>0 && item.id==provinceID){
					$("#province"+COUNT).append("<option value='"+item.id+"' selected>"+item.name+"</option>");
					initCity(COUNT,provinceID,cityID,districtID);
				}else{
					$("#province"+COUNT).append("<option value='"+item.id+"'>"+item.name+"</option>");
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
function initCity(COUNT,provinceID,cityID,districtID){
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/fi/travel/SysArea!getCity.do",
		data:"id="+provinceID,
		cache:false,
		dataType:'json',
		success:function(data){					
			$("#cityTD"+COUNT)[0].length=1;
			$("#disTD"+COUNT)[0].length=1;
			$("#cityTD"+COUNT).empty();
			$("#cityTD"+COUNT).append("<select style='width:95%' id='city"+COUNT+"' name='city"+COUNT+"' class='ddlNoBorder' onchange='dChoose("+COUNT+")'></select>");
			$.each(data,function(index,item){
					if(cityID>0 && item.id==cityID){
						$("#city"+COUNT).append("<option value='"+item.id+"' selected>"+item.name+"</option>");
						initDistrict(COUNT,cityID,districtID);
					}else{
						$("#city"+COUNT).append("<option value='"+item.id+"'>"+item.name+"</option>");
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
function initDistrict(COUNT,cityID,districtID){
	$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/fi/travel/SysArea!getCity.do",
			data:"id="+cityID,
			cache:false,
			dataType:'json',
			success:function(data){		
				$("#disTD"+COUNT)[0].length=1;	
				$("#disTD"+COUNT).empty();
				$("#disTD"+COUNT).append("<select style='width:95%' id='district"+COUNT+"' name='district"+COUNT+"'  onchange='dtlfund("+COUNT+")' class='ddlNoBorder'></select>");
				$.each(data,function(index,item){
					if(districtID>0 && districtID==item.id){
						$("#district"+COUNT).append("<option value='"+item.id+"' selected>"+item.name+"</option>");
					}else{
						$("#district"+COUNT).append("<option value='"+item.id+"'>"+item.name+"</option>");
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

//选择出差申请
$(function(){
	$("#travel").bind("propertychange",function(){
		var data = $(this).val();
		var obj = eval('('+ data +')');
		$("#travel_time").val(obj.b_time+" 出差");
		$("#deptid").val(obj.deptid);
		$("#travelid").val(obj.id);
		$("#p_id").val(obj.personid);
		accDtl(obj.id);
	});
})

//选择出差申请 自动填入对应出差明细
function accDtl(travelid){
	$.ajax({
		type: "POST",
		   url: "<%=request.getContextPath()%>/fi/travel/Fi_travelacc!getSub.do",
		   data: "travelid="+travelid,
		   success: function(data){
			   $("#subIds").val(data);
		   }
	});
	$.ajax({
		type: "POST",
		   url: "<%=request.getContextPath()%>/fi/travel/Fi_travelacc!getSubs.do",
		   data: "travelid="+travelid,
		   success: function(data){
			   $("#sub").empty();
			   var jsonStr = eval('(' + data +')');
			   $.each(jsonStr,function(index,item){
				   $("#sub").append(" "+item.name+" ");
			   });
		   }
	});
	$.ajax({
		type: "POST",
		   url: "<%=request.getContextPath()%>/fi/travel/Fi_travelacc!getAccDtl.do",
		   data: "travelid="+travelid,
		   success: function(data){
			   $("#dtlTR").empty();
			   var jsonStr = eval('(' + data +')');
			   var count = 0
			   $.each(jsonStr,function(index,item){
				   count++;
				   $("#dtlTR").append("<tr id='routeTR"+index+"'>"
						   //省
						   +"<td id='proTD"+index+"'><input type='hidden' id='province"+index+"' name='province"+index+"' value='"+item.province+"'/>"
						   +"<div onclick='routeClick("+index+","+item.province+","+item.city+","+item.district+")'>"+item.proname+"</div></td>"
						   //市
						   +"<td id='cityTD"+index+"'><input type='hidden' id='city"+index+"' name='city"+index+"' value='"+item.city+"'/>"
						   +"<div onclick='routeClick("+index+","+item.province+","+item.city+","+item.district+")'>"+item.cityname+"</div></td>"
						   //县区
						   +"<td id='disTD"+index+"'><input type='hidden' id='district"+index+"' name='district"+index+"' value='"+item.district+"'/>"
						   +"<div onclick='routeClick("+index+","+item.province+","+item.city+","+item.district+")'>"+item.disname+"</div></td>"
						   //交通工具
						   +"<td id='vehiTD"+index+"'><input type='hidden' id='vehicles"+index+"' name='vehicles"+index+"' value='"+item.vehicles+"'/>"
						   +"<div onclick='vehiClick("+index+","+item.vehicles+")'>"+item.vehicle+"</div></td>"
						   //到达时间
						   +"<td><input type='text' id='r_time"+index+"' name='r_time"+index+"' value='"+item.r_time+"' onchange='dtlfund("+index+")' dataType='Date' onfocus='WdatePicker()' style='ime-mode:disabled'  style='width:95%' class='textBoxNoBorder' /></td>"
						   //离开时间
						   +"<td><input type='text' id='l_time"+index+"' name='l_time"+index+"' value='"+item.l_time+"' onchange='dtlfund("+index+")' dataType='Date' onfocus='WdatePicker()' style='ime-mode:disabled'  style='width:95%' class='textBoxNoBorder' /></td>"
						   //住宿方式
						   +"<td><select dataType='Require' name='stay_type"+index+"' id='stay_type"+index+"' class='ddlNoBorder' onchange='dtlfund("+index+")' style='width: 95%;'>"
						   +"<option value='''>---</option>"
						   +"<c:forEach items='${stayType}' var='stayType'>"
						   +"<option value='${stayType.key}'>${stayType.value}</option>"
						   +"</c:forEach>"
						   +"</select></td>"
						   //报销标准
						   +"<td><input type='text' id='rfundTemp"+index+"' name='rfundTemp"+index+"' value='"+item.r_fund+"' readonly='readonly' style='width:95%;color:red;background: #EEEEEE' class='textBoxNoBorder' /></td>"
						   +"<input type='hidden' id='r_fund"+index+"' name='r_fund"+index+"' value='"+item.r_fund+"'/>"
						   //附件数
						   +"<td><input type='text' name='accessory"+index+"' value='0' maxLength='3' id='accessory"+index+"' dataType='Integer' onkeyup='if(isNaN(value))execCommand(\"undo\")' onafterpaste='if(isNaN(value))execCommand(\"undo\")'  style='ime-mode:disabled' style='width:95%' class='textBoxNoBorder'/></td>"
						   //交通费
						   +"<td><input type='text' name='trans_cost"+index+"' value='0' maxLength='10' id='trans_cost"+index+"' dataType='Double' onchange='calAcc("+index+")' onkeyup='if(isNaN(value))execCommand(\"undo\")' onafterpaste='if(isNaN(value))execCommand(\"undo\")'  style='ime-mode:disabled' style='width:95%' class='textBoxNoBorder'/></td>"
						   //市内交通费
						   +"<td><input type='text' name='citytrans_cost"+index+"' value='0' maxLength='10' id='citytrans_cost"+index+"' dataType='Double' onchange='calAcc("+index+")' onkeyup='if(isNaN(value))execCommand(\"undo\")' onafterpaste='if(isNaN(value))execCommand(\"undo\")'  style='ime-mode:disabled' style='width:95%' class='textBoxNoBorder'/></td>"
						   //住宿费
						   +"<td><input type='text' name='hotel_cost"+index+"' value='0' maxLength='10' id='hotel_cost"+index+"' dataType='Double' onchange='calAcc("+index+")' onkeyup='if(isNaN(value))execCommand(\"undo\")' onafterpaste='if(isNaN(value))execCommand(\"undo\")'  style='ime-mode:disabled' style='width:95%' class='textBoxNoBorder'/></td>"
						   //出差补贴
						   +"<td><input type='text' name='buzhu"+index+"' value='0' maxLength='10' id='buzhu"+index+"' dataType='Double' onchange='calAcc("+index+")' onkeyup='if(isNaN(value))execCommand(\"undo\")' onafterpaste='if(isNaN(value))execCommand(\"undo\")'  style='ime-mode:disabled' style='width:95%' class='textBoxNoBorder'/></td>"
						   //删除按钮
						   +"<td><a id='delIframe"+index+"' onclick='javascript:return routeDel("+index+");'><img src='<%=request.getContextPath()%>/image/delete.png'/></a></td></tr>"
						   +"<input type='hidden' id='realacc"+index+"' name='realacc"+index+"' value=''/>"
						   +"<input type='hidden' id='dtlId"+index+"' name='dtlId"+index+"' value='"+index+"'/>");
				   });
			   $("#count").attr("value",count);
			   }
		   });
	}

//删除原有路线
function routeDel(trId){
	if(confirm("确定删除吗?")){
		$("#routeTR"+trId+"").remove();
		return true;
	}
	return false;
}

//添加报账详情
function addDtl(){
	var addcount = parseInt($("#addcount").val())+1;
	$("#dtlTable").append("<tr id='dtlTR"+addcount+"'><td colspan='13'><iframe name='dtlIframe"+addcount+"' id='dtlIframe"+addcount+"' width='100%' height='25px'"
						 +"frameborder='0' scrolling='no' src='accDtlInclude.jsp'" 
						 +"marginwidth='0' marginheight='0'></iframe></td><td><a id='delIframe"+addcount+"' onclick='javascript:return dtldel("+addcount+");'><img src='<%=request.getContextPath()%>/image/delete.png'/></a></td></tr>");
	$("#addcount").val(addcount);
}
//删除添加的报账详情
function dtldel(frameId){
	if(confirm("确定删除吗?")){
		$("#dtlTR"+frameId+"").remove();
		return true;
	}
	return false;
}

//计算报销标准
function dtlfund(C){
	var dis = $("#district"+C).val();
	var city = $("#city"+C).val();
	var duty = '${user.duty_id}';
	var arealv = null;
	var rtime = $("#r_time"+C).val();
	var ltime = $("#l_time"+C).val();
	var vehi = $("#vehicles"+C).val();
	var subIds = $("#subIds").val();
	var subc = 0;
	if(subIds!=""){
		var subc = (subIds.split(",")).length;
	}
	var pid = $("#p_id").val();
	rtime = rtime.split("-");
	rtime = new Date(rtime[1] + '-' + rtime[2] + '-' + rtime[0]);
	ltime = ltime.split("-");
	ltime = new Date(ltime[1] + '-' + ltime[2] + '-' + ltime[0]);
	var routedays = parseInt(Math.abs(ltime - rtime) / 1000 / 60 / 60 /24);
	var stayType = $("#stay_type"+C).val();
	var stayWeight = 0;
	if(stayType=='1'||stayType=='4'){
		stayWeight = 1;
	}
	if(stayType=='2'){
		stayWeight = 0.6;
	}
	if(stayType=='3'){
		stayWeight = 0.3;
	}
	if(stayWeight!=null&&routedays!=null){
		if(routedays==0){//当天走当天回
			if(city=='2832'){//不出昆明
				$("#r_fund"+C).val((0).toFixed(2));
				$("#rfundTemp"+C).val((0).toFixed(2));
			}else{//出昆明
				$("#r_fund"+C).val((15*(subc+1)).toFixed(2));
				$("#rfundTemp"+C).val((15*(subc+1)).toFixed(2));
			}
		}else{
			$.ajax({
				type: "POST",
				   url: "<%=request.getContextPath()%>/fi/travel/SysArea!getAreaLv.do",
				   data: "id="+dis+"&duty="+duty+"&vehi="+vehi+"&subids="+subIds+"&pid="+pid,
				   success: function(data){
					   var total = (data*routedays*stayWeight).toFixed(2);
					   total = isNaN(total) ? "" : total;
					   $("#r_fund"+C).val(total);
					   $("#rfundTemp"+C).val(total);
					   calAcc(C);
				   }
			});
		}
	}
}

function calAcc(countI){
	var rcost = 0;
	var realacc = 0;
	var rfund = parseFloat($("#r_fund"+countI).val());
	var citytrans_cost = parseFloat($("#citytrans_cost"+countI).val());
	var hotel_cost = parseFloat($("#hotel_cost"+countI).val());
	var buzhu = parseFloat($("#buzhu"+countI).val());
	var trans_cost = parseFloat($("#trans_cost"+countI).val());
	//时间
	var rtime = $("#r_time"+countI).val();
	var ltime = $("#l_time"+countI).val();
	rtime = rtime.split("-");
	rtime = new Date(rtime[1] + '-' + rtime[2] + '-' + rtime[0]);
	ltime = ltime.split("-");
	ltime = new Date(ltime[1] + '-' + ltime[2] + '-' + ltime[0]);
	var routedays = parseInt(Math.abs(ltime - rtime) / 1000 / 60 / 60 /24);
	rcost = citytrans_cost + hotel_cost + buzhu;
	var duty = '${user.duty_id}';
	if(duty=='3'||duty=='4'||duty=='5'){//领导实报实销
		realacc = rcost+trans_cost;
	}
	else{
		if(routedays==0){//当天走当天回
			realacc = rfund+trans_cost;
		}else{
			if(rcost>=rfund){
				realacc = rfund+trans_cost;
			}
			else{
				realacc = rcost+trans_cost;
			}
		}
	}
	if(realacc!=null){
		$("#realacc"+countI).val(realacc);
		//计算总报销金额
		calTotal();
	}
}

function calTotal(){
	var totalMoney = 0;
	var totalAdd = 0;
	if($("#travelid").val()){
		$("input[name^='realacc']").each(function(){
			 var curData = parseFloat($(this).val());
			 if(curData){
			 	totalMoney+=curData;
			 }
		 });
	}
	if($("#addcount").val()!=0){
		$("iframe[name^='dtlIframe']").contents().find("#realacc").each(function(){
			 var addData = parseFloat($(this).val());
			 if(addData){
				 totalAdd+=addData;
			 }
		 });
	}
	$("#totalcost").attr("value",(totalAdd+totalMoney).toFixed(2));
	//alert($("#totalcost").val());
	convert((totalAdd+totalMoney).toFixed(2));
}

//提交
function mySub(flag){
	if(Validator.Validate(document.form1,3)){
		$("#subflag").val(flag);
		var c = $("#addcount").val();
		var dtlc = $("#count").val();
		var r_time = null;
		var l_time = null;
		var vehicle = null;
		var province = null;
		var city = null;
		var district = null;
		var r_fund = null;
		var accessory = null;
		var trans_cost = null;
		var citytrans_cost = null;
		var hotel_cost = null;
		var parentid = "${parentid}"
		var	realacc = null;
		var rt = null;
		if($("#travelid").val()){
			for(i=dtlc-1;i>=0;i--){
				r_time = $("#r_time"+i).val();
				l_time = $("#l_time"+i).val();
				vehicles = $("#vehicles"+i).val();
				province = $("#province"+i).val();
				city = $("#city"+i).val();
				district = $("#district"+i).val();
				r_fund = $("#r_fund"+i).val();
				accessory = $("#accessory"+i).val();
				trans_cost = $("#trans_cost"+i).val();
				citytrans_cost = $("#citytrans_cost"+i).val();
				hotel_cost = $("#hotel_cost"+i).val();
				buzhu = $("#buzhu"+i).val();
				stay_type = $("#stay_type"+i).val();
				realacc = $("#realacc"+i).val();
				rt = "{\"r_time\":\""+r_time+"\",\"l_time\":\""+l_time+"\",\"province\":\""+province+"\",\"vehicles\":\""+vehicles+"\",\"city\":\""+city+"\",\"district\":\""+district+"\",\"r_fund\":\""+r_fund+"\",\"accessory\":\""+accessory+"\",\"trans_cost\":\""+trans_cost+"\",\"citytrans_cost\":\""+citytrans_cost+"\",\"hotel_cost\":\""+hotel_cost+"\",\"stay_type\":\""+stay_type+"\",\"realacc\":\""+realacc+"\",\"buzhu\":\""+buzhu+"\"}";
				$("#accSave1").append('<input type="hidden" id="rtSave'+i+'" name="rtSave" value="'+rt+'"/>');
				$("#rtSave"+i).val(rt);
			}
		}
		if($("#addcount").val()!='0'){
			for(i=c;i>0;i--){
				r_time = $("#dtlIframe"+i).contents().find("#r_time").val();
				l_time = $("#dtlIframe"+i).contents().find("#l_time").val();
				vehicles = $("#dtlIframe"+i).contents().find("#vehicles").val();
				province = $("#dtlIframe"+i).contents().find("#province").val();
				city = $("#dtlIframe"+i).contents().find("#city").val();
				district = $("#dtlIframe"+i).contents().find("#district").val();
				r_fund = $("#dtlIframe"+i).contents().find("#r_fund").val();
				accessory = $("#dtlIframe"+i).contents().find("#accessory").val();
				trans_cost = $("#dtlIframe"+i).contents().find("#trans_cost").val();
				citytrans_cost = $("#dtlIframe"+i).contents().find("#citytrans_cost").val();
				hotel_cost = $("#dtlIframe"+i).contents().find("#hotel_cost").val();
				buzhu = $("#dtlIframe"+i).contents().find("#buzhu").val();
				stay_type = $("#dtlIframe"+i).contents().find("#stay_type").val();
				realacc = $("#dtlIframe"+i).contents().find("#realacc").val();
				rt = "{\"r_time\":\""+r_time+"\",\"l_time\":\""+l_time+"\",\"province\":\""+province+"\",\"vehicles\":\""+vehicles+"\",\"city\":\""+city+"\",\"district\":\""+district+"\",\"r_fund\":\""+r_fund+"\",\"accessory\":\""+accessory+"\",\"trans_cost\":\""+trans_cost+"\",\"citytrans_cost\":\""+citytrans_cost+"\",\"hotel_cost\":\""+hotel_cost+"\",\"stay_type\":\""+stay_type+"\",\"realacc\":\""+realacc+"\",\"buzhu\":\""+buzhu+"\"}";
				$("#accSave2").append('<input type="hidden" id="rtSave1000'+i+'" name="rtSave" value="'+rt+'"/>');
				$("#rtSave1000"+i).val(rt);
			}
		}
		calTotal();
		document.form1.submit();
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

function allFund(){
	//当前页面计算
	$("input[name^='dtlId']").each(function(){
		dtlfund($(this).val());
	});
	//iframe计算
	var addcount = parseInt($("#addcount").val());
	if(addcount!=0){
		for(i=addcount;i>0;i--){
			window.frames["dtlIframe"+addcount].dtlfund();
		}
	}
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/fi/travel/Fi_travelacc!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${recordAcc.id}"/>
	<input type="hidden" name="deptid" id="deptid" value="${recordAcc.deptid}"/>
	<input type="hidden" name="travelid" id="travelid" value="${recordAcc.travelid}"/>
	<input type="hidden" name="p_id" id="p_id" value="${recordAcc.p_id}"/>
	<input type="hidden" name="nextapproverid" id="nextapproverid" value="${recordAcc.nextapproverid}"/>
	<input type="hidden" name="appstate" id="appstate" value="${recordAcc.appstate}"/>
	<input type="hidden" id="reim_time" name="reim_time" value="${recordAcc.reim_time}"/>
	<input type="hidden" id="count" name="count" value="0"/>
	<input type="hidden" id="addcount" name="addcount" value="0"/>
	<input type="hidden" id="print" name="print" value="0"/>
	<input type="hidden" id="travel" name="travel" value=""/>
	<input type="hidden" id="subflag" name="subflag" value="${recordAcc.subflag}"/>
		<div id="accSave1"></div>
		<div id="accSave2"></div>
		<div class="divMod2">
		<div class="divMod1">基本信息</div>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">出差申请:</th>
			<td style="width:35%">
			<input type="text" id="travel_time" value="${recordAcc.b_time}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="button" onclick="openSelect('travel_select','<%=request.getContextPath()%>/gm/travel/Gm_travel!travelList.do?pageType=mulselect','travelid','travel');" value="选择"/>
			</td>
			<th align="center" style="width:10%">项目名称:</th>
			<td style="width:35%">
			<input type="text" id="proj_name" name="proj_name" value="${recordAcc.proj_name}" readonly="readonly" class="textBoxNoBorder"/>
			<input type="text" name="proj_id" class="hidden" value="${recordAcc.proj_id}" onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','proj_id','proj_name');" value="选择"/>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">随行人员:</th>
			<td colspan="3">
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/hr/info/hr_base_infoMulSelect_frame.jsp','subIds','sub');"  value="选择" style="float:left;margin-top:23px;height:23px"/>
			<textarea rows="4" cols="50" style="height:100%;width:90%;border:1px solid #CCC;word-wrap: break-word;" class="textBoxNoBorder" id="sub" name="sub"  readonly="readonly" ></textarea>
			<input type="text" onpropertychange="allFund();" id="subIds" name="subids" value='${recordAcc.subids}' class="hidden"/>								  						  
			</td>
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
		</br>
		<div class="divMod2">
		<div class="divMod1">明细</div>
		<table class="dataListTable" style="width:100%" id="dtlTable">
		<tr>
		<th width="7%">省</th>
		<th width="7%">市</th>
		<th width="7%">县</th>
		<th width="7%">交通工具</th>
        <th width="7%">到达时间</th>
        <th width="7%">离开时间</th>
        <th width="7%">住宿方式</th>
        <th width="7%">报销标准</th>
        <th width="7%">附件数</th>
        <th width="7%">交通费</th>
        <th width="7%">市内交通</th>
        <th width="7%">住宿费</th>
        <th width="7%">出差补贴</th>
        <th width="4%">操作</th>
        </tr>
        <tr id="dtlTR"></tr>
		</table>
		<table class="table_border" style="width:100%">
		<tr>
		<th align="center" style="width:10%">报销总金额:</th>
		<td style="width:35%;background: #EEEEEE">
		<input type="text" name="totalcost" id="totalcost" value="${recordAcc.totalcost}" readonly="readonly" dataType="Double" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" style="width:95%;background: #EEEEEE;color:red;" class="textBoxNoBorder"/>
        <!-- <input type="button" onclick="calTotal()" value="计算"> --></td>
        <th align="center" style="width:10%">人民币大写:</th>
		<td style="width:35%;background: #EEEEEE">
		<input type="text" id="costchn" name="costchn" value="${recordAcc.costchn}" dataType="Require" readonly="readonly" style="width:95%;background: #EEEEEE;color:red;" class="textBoxNoBorder"/>
		</td>
        </tr>
        </table>
		</div>
		<img src="<%=request.getContextPath()%>/image/addDtl.png" onclick="addDtl()"/>
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
	$("#deptid").attr("value","${user.branchid}");
	$("#p_id").attr("value","${user.base_info_id}");
	$("#reim_time").attr("value","${reim_time}");
}
</script>
</body>
</html>
