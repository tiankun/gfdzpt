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
	/*获取交通工具*/
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/gm/travel/Gm_travel!getVehicles.do",
		cache:false,
		dataType:'json',
		success:function(data){
			$.each(data,function(index,item){
				$("#vehicles").append("<option value='"+item.dmz+"'>"+item.dmzmc+"</option>");
			});
		}
	});
	
	/*获取住宿方式*/
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/fi/travel/Fi_travelacc!getStayType.do",
		cache:false,
		dataType:'json',
		success:function(data){
			$.each(data,function(index,item){
				$("#stay_type").append("<option value='"+item.dmz+"'>"+item.dmzmc+"</option>");
			});
		}
	});
	
	var routData ='${record.travelroute}'; 
	if(routData.length<1)
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

//计算报销标准
function dtlfund(){
	var dis = $("#district").val();
	var duty = '${user.duty_id}';
	var city = $("#city").val();
	var arealv = null;
	var rtime = $("#r_time").val();
	var ltime = $("#l_time").val();
	var vehi = $("#vehicles").val();
	var subIds = window.parent.$("#subIds").val();
	var subc = 0;
	if(subIds!=""){
		var subc = (subIds.split(",")).length;
	}
	var pid = window.parent.$("#p_id").val();
	rtime = rtime.split("-");
	rtime = new Date(rtime[1] + '-' + rtime[2] + '-' + rtime[0]);
	ltime = ltime.split("-");
	ltime = new Date(ltime[1] + '-' + ltime[2] + '-' + ltime[0]);
	var routedays = parseInt(Math.abs(ltime - rtime) / 1000 / 60 / 60 /24);
	var stayType = $("#stay_type").val();
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
		if(routedays==0){
			if(city=='2832'){
				$("#r_fund").val((0).toFixed(2));
				$("#rfundTemp").val((0).toFixed(2));
			}else{
				$("#r_fund").val((15).toFixed(2));
				$("#rfundTemp").val((15*(subc+1)).toFixed(2));
			}
		}else{
			$.ajax({
				type: "POST",
				   url: "<%=request.getContextPath()%>/fi/travel/SysArea!getAreaLv.do",
				   data: "id="+dis+"&duty="+duty+"&vehi="+vehi+"&subids="+subIds+"&pid="+pid,
				   success: function(data){
					   var total = (data*routedays*stayWeight).toFixed(2);
					   total = isNaN(total) ? "" : total;
					   $("#r_fund").attr("value",total);
					   $("#rfundTemp").attr("value",total);
					   calAcc();
				   }
			});
		}
	}
}

function calAcc(){
	var rcost = 0;
	var realacc = 0;
	var rfund = parseFloat($("#r_fund").val());
	var citytrans_cost = parseFloat($("#citytrans_cost").val());
	var hotel_cost = parseFloat($("#hotel_cost").val());
	var buzhu = parseFloat($("#buzhu").val());
	var trans_cost = parseFloat($("#trans_cost").val());
	rcost = citytrans_cost + hotel_cost + buzhu;
	var rtime = $("#r_time").val();
	var ltime = $("#l_time").val();
	rtime = rtime.split("-");
	rtime = new Date(rtime[1] + '-' + rtime[2] + '-' + rtime[0]);
	ltime = ltime.split("-");
	ltime = new Date(ltime[1] + '-' + ltime[2] + '-' + ltime[0]);
	var routedays = parseInt(Math.abs(ltime - rtime) / 1000 / 60 / 60 /24);
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
		$("#realacc").val(realacc);
		//计算总报销金额
		window.parent.calTotal();
	}
}

/*初始化省数据*/
function initProv(idVal,cityID,districtID){
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/fi/travel/SysArea!getProvince.do",
		cache:false,
		dataType:'json',
		success:function(data){
			$("#province")[0].length=1;
			$.each(data,function(index,item){
				if(idVal>0 && item.id==idVal){
					$("#province").append("<option value='"+item.id+"' selected>"+item.name+"</option>");
					initCity(idVal,cityID,districtID);
				}else{
					$("#province").append("<option value='"+item.id+"'>"+item.name+"</option>");
				}	
			});
				$("#province").attr("style","width:95%");
				$("#city").attr("style","width:95%");
				$("#district").attr("style","width:95%");		
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
function initCity(id,idVal,districtID){
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/fi/travel/SysArea!getCity.do",
		data:"id="+id,
		cache:false,
		dataType:'json',
		success:function(data){					
			$("#city")[0].length=1;
			$("#district")[0].length=1;		
			$.each(data,function(index,item){
					if(idVal>0 && item.id==idVal){
						$("#city").append("<option value='"+item.id+"' selected>"+item.name+"</option>");
						initDistrict(idVal,districtID);
					}else{
						$("#city").append("<option value='"+item.id+"'>"+item.name+"</option>");
					}	
			});
				$("#city").attr("style","width:95%");
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
function initDistrict(id,idVal){
	$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/fi/travel/SysArea!getCity.do",
			data:"id="+id,
			cache:false,
			dataType:'json',
			success:function(data){					
				$("#district")[0].length=1;	
				$.each(data,function(index,item){
					if(idVal>0 && item.id==idVal)
						$("#district").append("<option value='"+item.id+"' selected>"+item.name+"</option>");
					else						
						$("#district").append("<option value='"+item.id+"'>"+item.name+"</option>");		
				});
					$("#district").attr("style","width:95%");	
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

</script>
</head>
<body style="margin: 0px">
<table class="table_border" style="width:100%">
<tr>
	<td width="7%"><select style="width:95%" id="province" name="province" class="ddlNoBorder"><option>--请选择省--</option></select></td>
	<td width="7%"><select style="width:95%" id="city" name="city" class="ddlNoBorder"><option>--请选择州市--</option></select></td>
	<td width="7%"><select style="width:95%" id="district" name="district" onchange="dtlfund()" dataType="Integer" msg="请选择出发地 : 省、市、县" class="ddlNoBorder"><option>--请选择区县--</option></select></td>
	<td width="7%">
	<select dataType="Require" name="vehicles" id="vehicles" class="ddlNoBorder" onchange='dtlfund()' style="width: 95%;">
	<option value="">---</option>
	<c:forEach items="${vehicleTool}" var="vehicleTool">
	<option value="${vehicleTool.key}">${vehicleTool.value}</option>
	</c:forEach>
	</select>
	</td>
	<td width="7%"><input type="text" id="r_time" name="r_time" onchange="dtlfund()" value="" dataType="Date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder" /></td>
	<td width="7%"><input type="text" id="l_time" name="l_time" onchange="dtlfund()" value="" dataType="Date" onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder" /></td>
	<td width="7%">
	<select dataType="Require" name="stay_type" id="stay_type" onchange="dtlfund()" class="ddlNoBorder" style="width: 95%;">
	<option value="">---</option>
	<c:forEach items="${stayType}" var="stayType">
	<option value="${stayType.key}">${stayType.value}</option>
	</c:forEach>
	</select>
	</td>
	<td width="7%"><input type="text" id="rfundTemp" name="rfundTemp" value="" readonly="readonly" style="width:95%;color:red;background: #EEEEEE" class="textBoxNoBorder" /></td>
	<input type="hidden" id="r_fund" name="r_fund" value=""/></td>
	<td width="7%"><input type="text" name="accessory" id="accessory" dataType="Integer" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" style="width:95%" class="textBoxNoBorder"/></td>
	<td width="7%"><input type="text" name="trans_cost" id="trans_cost" dataType="Double" onchange="calAcc()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" style="width:95%" class="textBoxNoBorder"/></td>
	<td width="7%"><input type="text" name="citytrans_cost" id="citytrans_cost" dataType="Double" onchange="calAcc()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" style="width:95%" class="textBoxNoBorder"/></td>
	<td width="7%"><input type="text" name="hotel_cost" id="hotel_cost" dataType="Double" onchange="calAcc()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" style="width:95%" class="textBoxNoBorder"/></td>
	<td width="7%"><input type="text" name="buzhu" id="buzhu" dataType="Double" onchange="calAcc()" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" style="width:95%" class="textBoxNoBorder"/></td>
	<input type="hidden" id="realacc" name="realacc" value=""/>
</tr>
</table>
</body>
</html>