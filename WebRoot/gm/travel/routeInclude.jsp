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
function routefund(){
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
	var pid = window.parent.$("#personid").val();
	rtime = rtime.split("-");
	rtime = new Date(rtime[1] + '-' + rtime[2] + '-' + rtime[0]);
	ltime = ltime.split("-");
	ltime = new Date(ltime[1] + '-' + ltime[2] + '-' + ltime[0]);
	var routedays = parseInt(Math.abs(ltime - rtime) / 1000 / 60 / 60 /24);
	if(routedays!=null){
		if(routedays==0){
			if(city=='2832'){
				$("#r_fund").attr("value",(0).toFixed(2));
			}else{
				$("#r_fund").attr("value",(15*(subc+1)).toFixed(2));
			}
		}else{
			$.ajax({
				type: "POST",
				   url: "<%=request.getContextPath()%>/fi/travel/SysArea!getAreaLv.do",
				   data: "id="+dis+"&duty="+duty+"&vehi="+vehi+"&subids="+subIds+"&pid="+pid,
				   success: function(data){
					   var total = (data*routedays).toFixed(2);
					   total = isNaN(total) ? "" : total;
					   $("#r_fund").attr("value",total);
				   }
			});
		}
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
	<td width="15%"><select style="width:95%" id="province" name="province" class="ddlNoBorder"><option>--请选择省--</option></select></td>
	<td width="15%"><select style="width:95%" id="city" name="city" class="ddlNoBorder"><option>--请选择州市--</option></select></td>
	<td width="15%"><select style="width:95%" id="district" name="district" onchange="routefund()" dataType="Integer" msg="请选择出发地 : 省、市、县" class="ddlNoBorder"><option>--请选择区县--</option></select></td>
	<td width="15%">
	<select dataType="Require" name="vehicles" id="vehicles" class="ddlNoBorder" onchange="routefund()" style="width: 95%;">
	<option value="">---</option>
	<c:forEach items="${vehicleTool}" var="vehicleTool">
	<option value="${vehicleTool.key}">${vehicleTool.value}</option>
	</c:forEach>
	</select>
	</td>
	<td width="10%"><input type="text" id="r_time" name="r_time" onchange="routefund()" value="" dataType="Date" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'l_time\',{d:0});}',onpicked:cDayFunc,firstDayOfWeek:1,disabledDates:['^19']})" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder" /></td>
	<td width="10%"><input type="text" id="l_time" name="l_time" onchange="routefund()" value="" dataType="Date" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'r_time\',{d:0});}',onpicked:cDayFunc,firstDayOfWeek:1,disabledDates:['^19']})" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder" /></td>
	<td width="10%"><input type="text" id="r_fund" name="r_fund" onchange="routefund()" value="" readonly="readonly" class="textBoxNoBorder" style="width:95%;background: #EEEEEE;color:red;"/></td>
</tr>
</table>
<script type="text/javascript" defer="defer">
function cDayFunc(){                                                                                                   
          var begDateArr = $("#r_time" ).val().split( /(-|:|(\ +))/g );
          var endDateArr = $("#l_time" ).val().split( /(-|:|(\ +))/g );
          var beginDate = new Date(begDateArr[0], begDateArr[1] - 1, begDateArr[2]);
          var endDate = new Date(endDateArr[0], endDateArr[1] - 1, endDateArr[2]);
          if(!isNaN(beginDate) && !isNaN(endDate)){
              if (beginDate > endDate) {
                 alert( '结束时间不能 【  小于或等于 】开始时间！！！' );
              } else {                                                         
                 //$("#traveldays" ).val((endDate-beginDate)/(24000*3600));
              }                                     
          }                                         
}      
</script>
</body>
</html>