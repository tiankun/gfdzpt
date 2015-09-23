<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<style type="text/css">
	*{
		maring:0;
		padding:0;
		list-style-type:none;
	}
	body{
		font-family:Verdana, Geneva, sans-serif,微软雅黑;
		font-size:12px;
		color: #333333;
	}

	.lname{
		float:left;
		width:60%;			
	}
	.lopt{
		float:left;
		margin-top:7px;
		width:15%;		
	}
	li{
		line-height:28px;
		margin:1px auto;	
		width:32%; 
		float:left; 
		display:block;
	}
	ul{
		margin-top:-10px;
		width:100%;			#overflow-y:auto; #添加滚动
	}
	#show{
		width:100%;		
		maring:0 auto;
		border:1px solid #ebebeb;
		float:left;
	}
	#show h3{
		height: 28px;
		line-height: 20px;
		font-weight: 200;
		color: #000000;  #height:3%;		
		border-bottom:1.5px solid #acacee;
	}
	#subbtn{
		margin-left:80px;width:95px;height:27px;margin-bottom:2px;
	}
</style>
<script type="text/javascript">
function delli(item){
		var curitem = $(item).parents().parents();	
		var index = $(curitem).index()-1;
		var ids = $("#selectedIDS").val();
		var idsArr = ids.split(",");
		idsArr.splice((index-1),1);
		idsArr = idsArr.join(",");
		$("#selectedIDS").val(idsArr);
		$("#show ul li:eq("+ (index-1)+")").remove();
	}
function selectNames(){
	var val = '';
	if($.trim($("#chooseul").text())!=''){
		$("#chooseul li").each(function(){
			if($.trim($(this).text())!=''){
				var index = $(this).index();
				if(index>0&&index%2==0)
					val += "\r\n"
				val += $.trim($(this).text()) + "\t";
			}
		});
	}	
	return val;
}	
function selectIds(){
	var val = '';
	var ids = $.trim($("#selectedIDS").val());
	if(ids){
		ids = ids.substring(0,ids.length-1);
		var idsArr = ids.split(",");
		val+="["
		$(idsArr).each(function(index,item){
			val+='{"ID":"'+item+'"},'					
		});
		val = val.substring(0,val.length-1) + "]";
	}
	return val;	 
}	

</script>	
</head>
<body >
<div id="show">
	<h3>已选择人员列表：<input id="subbtn" type="button" value="完成选择" onClick="retSelect(selectIds(),selectNames());" /></h3>
	<div style="overflow-y:auto">
		<ul id="chooseul">
	       
	    </ul>
	    <input type="hidden" id="selectedIDS"/>      
    </div>         
</div>
<div id="frame">
	<IFRAME NAME="content_frame" width='100%' height="100%" marginwidth='0' marginheight='0' SRC="<%=request.getContextPath() %>/hr/info/Hr_base_info!searchList.do?pageType=mulSelect"
 frameborder="0" scrolling="yes" id="framebody"></IFRAME>
</div>
</body>
</html>