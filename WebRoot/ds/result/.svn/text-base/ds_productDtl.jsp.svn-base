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

<style>
.selectTable{BORDER-COLLAPSE: collapse;border: 1px solid #eee;line-height:22px;margin:2px auto;}
.selectTable td{border:1px solid #c5ddf1;text-align: center;}
.selectTable th{background:#eee;border:1px solid #bbbec3;height:20px;text-align: center; font:bold 14px/28px simsun;}
</style>

<script type="text/javascript">

var firTime = 0;
		
		function reRun(){
			if(firTime > 0 && (new Date().getTime()-firTime) > 300){				
				getresult();                  
				firTime = 0;
			}
		}		
		setInterval(reRun,100);	
		
	function getresult(){
		var name = $("#name").val();
		var shortcode = $("#shortcode").val();
		var brandname = $("#brandname").val();
		var kindname = $("#kindname").val();
		if(name=="" && shortcode =="" && brandname=="" && kindname=="") {
			$("#selTb").css("display","none");
			return;
		}
	   
		var url = "/gfdzpt/materiel/Materiel!getData.do"; 
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			
			data:{"name":name,
				  "shortcode":shortcode,
				  "brandname":brandname
			},
			cache:false,
			success:function(data){
			   $("#content tbody tr").remove();
				 var row="";
				$.each(data,function(key,value){
					
					row =row + "<tr id='"+value.id+"'>" +
					"<td id='brandname' brand_id='"+value.brand_id+"'>" + value.brandname + "</td>" +
					"<td id='ma_name'>" + value.name + "</td>" +
					"<td id='model'>" + value.model + "</td>" +
					"<td id='unit'>" + value.unit + "</td>" +
					"<td id='parameter'>" + value.parameter + "</td>" +
					"<td id='num'><input type='text' id='num' subid='num' onkeyup=\"if(isNaN(value))execCommand('undo')\" onafterpaste=\"if(isNaN(value))execCommand('undo')\" style='ime-mode:disabled;width:30px' value=''/></td>"+
					"<td id='bid_name'><input type='text' id='bid_name' subid='bid_name' value='"+ value.name +"'/></td>"+
					"<td id='bid_price'><input type='text' id='bid_price' subid='bid_price' onkeyup=\"if(isNaN(value))execCommand('undo')\" onafterpaste=\"if(isNaN(value))execCommand('undo')\" subid='bid_price' style='ime-mode:disabled' value=''/></td>"+
					"<td id='aim_price'><input type='text' id='aim_price' subid='aim_price' onkeyup=\"if(isNaN(value))execCommand('undo')\" onafterpaste=\"if(isNaN(value))execCommand('undo')\" subid='aim_price' style='ime-mode:disabled' value=''/></td>"+
					"<td id='bjbl'><input type='text' id='bjbl' subid='bjbl' onkeyup=\"if(isNaN(value))execCommand('undo')\" onafterpaste=\"if(isNaN(value))execCommand('undo')\" style='ime-mode:disabled' subid='bjbl' value=''/></td>"+
					"<td id='shunxu'><input type='text' id='shunxu' subid='shunxu' onkeyup=\"if(isNaN(value))execCommand('undo')\" onafterpaste=\"if(isNaN(value))execCommand('undo')\" style='ime-mode:disabled' subid='shunxu' value=''/></td></tr>";
			});
				
				$("#content tbody").append(row);
				var top = $("#addTb").offset().top+$("#addTb").height;
				var left= $("#addTb").offset().left;
				$("#selTb").css("left",left);
				$("#selTb").css("top",top);
				$("#selTb").css("display","block");
			},
			error:function(info){
				alert("未找到属性信息");
			}

		});
		}
		
	$(document).ready(function(){
    //當tbd裡所有的input
		$('#tbd :input').live("keyup",function(e){
			//取得自訂屬性的名稱
			var mySubId=$(this).attr('subid');
			//取得該物件的index
			var idx=$('#tbd :input[subid="' + mySubId + '"]').index(this);
			//取得相關物件的個數
			var sidlen=$('#tbd :input[subid="' + mySubId + '"]').length;
			//設定新的index
			var newidx;
			switch (e.which)
			{
				case 38:    //上
					newidx=idx-1;
					$(this).parent().parent().css("background-color","");
					if(idx==0) {
						var tgEmt=$('#shortcode');
						tgEmt.select();
						tgEmt.focus();
					}else{toFocus(newidx,mySubId);}
					break;
				case 40:    //下
					newidx=idx+1;
					$(this).parent().parent().css("background-color","");
					toFocus(newidx,mySubId);
					break;
				case 13:   //回车键
					var tr =$(this).parent().parent().clone();
					var _trid = $(tr).attr("id");
					if($("#tbd1 tr[id='"+_trid+"']").length) 
					{
						alert("请勿重复添加。");
					}else{
						if($(this).val().length < 1){
							alert("请录入数字。");return;
						}
						tr.append($("<td><a onclick='$(this).parent().parent().remove();'>"
						+"<img src='<%=request.getContextPath()%>/image/delete.png'/></a></td>"));
						tr.appendTo("#tbd1");
						$("#selTb").css("display","none");
						$('#shortcode').select();
						$('#shortcode').focus();
					}
					break;
				default:
					newidx=idx;
					var id = $(this).attr("subid");
					if(id=="num"){
						var tmptxt=$(this).val(); 
						 $(this).val(tmptxt.replace(/[^\d]/g,''));
					}
					return;
			}
		});
		$('#addTb .textBox').live("keyup",function(e){			
			switch (e.which)
			{
				case 38:    //上
					break;
				case 40:    //下
					var tgEmt=$('#tbd :input[subid="num"]:eq(0)')
					tgEmt.select();
					tgEmt.focus();
					$(tgEmt).parent().parent().css("background-color","#E6E6FA");
					break;
				default:
					firTime = new Date().getTime();
					return;
			}
		});
		$('#content1 #num').live("keyup",function(e){
			
			var tmptxt=$(this).val(); 
			 $(this).val(tmptxt.replace(/[^\d]/g,''));
					
		});
	})
	function toFocus(newidx,mySubId){
		var tgEmt=$('#tbd :input[subid="' + mySubId + '"]:eq(' + newidx + ')')
		tgEmt.select();
		tgEmt.focus();
		$(tgEmt).parent().parent().css("background-color","#E6E6FA");
	}
	
	function saveTable(){
		var rowData = '[';
		$("#tbd1 tr").each(function(i,n){
			rowData = rowData + '{"materiel_id":"'+$(this).attr("id")+ '",';
			$(this).find("td").each(function(i){
				if($(this).find("input[type='text']").length>0) {
					if($(this).attr("id"))
					rowData = rowData + '"'+$(this).attr("id")+'":"'+$(this).find("input[type='text']").val()+ '",';
				}else{
					if($(this).attr("id"))
					  rowData = rowData + '"'+$(this).attr("id")+'":"'+$(this).html()+ '",';
					if($(this).attr("brand_id"))
					  rowData = rowData + '"brand_id":"'+$(this).attr("brand_id")+ '",';
				}	  
			});
			rowData = rowData.substring(0, rowData.length -1)+ '},';
		});
		rowData = rowData.substring(0, rowData.length -1) + "]";
		var record = $("#record").val();
		if(confirm("确定提交推荐产品清单"))
			{
				if(rowData==']'&&record!=null)
				 {
				   alert("请选择材料");
				   return false;
				 }
			
			document.form1.rowData.value=rowData;
			if(Validator.Validate(document.form1,3)){
				document.form1.submit();
			}
			}
	}
	
function productDel(){
	if(confirm("确定删除吗?")){
		window.location.href="<%=request.getContextPath()%>/ds/result/Ds_product!deleteProduct.do?id="+id;
		return true;
	}
	return false;
}
</script>

</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/ds/result/Ds_product!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="text" name="aim_price" value="${record.aim_price}" class="hidden"/>
	<input type="text" name="cgxj" value="${record.cgxj}" class="hidden"/>
	<input type="text" name="bjbl" value="${record.bjbl}" class="hidden"/>
	<input type="text" name="operator" id="operator" value="${record.operator}" class="hidden"/>
	<input type="text" name="op_time" id="op_time" value="${record.op_time}" class="hidden"/>
	<input type="text" name="table_id" id="table_id" value="${record.table_id}" class="hidden"/>
	<input type="text" name="proj_id" id="proj_id" value="${record.proj_id}" class="hidden"/>
	<input type="text" name="record" id="record" value="${record1}" class="hidden"/>

		<!-- 新增及编辑 -->
		<c:if test="${editMod!='show'}">
		<div class="divMod2" style="margin-top:3px;">
  		<div class="divMod1">添加物资</div>
		<table id="addTb" width="99%">
		  <input type="hidden" name="rowData"/>	
	        <tr>
	          <th width="9%">简码:</th>
			  <td width="19%"><input type="text" name="shortcode" id="shortcode" style="width:95%" value="" class="textBox"/></td>
			  <th width="5%">名称:</th>
			  <td width="23%"><input type="text" id="name" name="name" style="width:95%" value="" class="textBox"/></td>
			  </tr>
			  <tr>
			  <th width="13%">类型:          </th>
	          <td width="14%"><input type="text" name="kind_id" id="kind_id"	value="" class="hidden" />            
	          <input type="text" name="kindname" id="kindname" value="" class="textBox" style="width:95%" />
	          <!-- <input type="button" onClick="openSelect('选择材料类型','/gfdzpt/materiel/Ma_kind!searchList.do?pageType=select','kind_id','kindname');"	value="选择" /> --></td>
	          <th width="3%">品牌:</th>
	          <td width="14%"><input type="text" name="brandname" id="brandname" onChange="gettime();" value="" class="textBox" style="width:95%" />
	          <input type="text" name="brand_id" id="brand_id"	value="" class="hidden" />
	          <!-- <input type="button"	onClick="openSelect('选择材料类型','/gfdzpt/materiel/Ma_brand!searchList.do?pageType=select','brand_id','brandname');"	value="选择" /> --></td>
        	  </tr>
	    </table>
	    <script type="text/javascript">$("#addTb #shortcode").select();$("#addTb #shortcode").focus();</script>
		</div>
	    <div id="selTb" style="height:300px; width:1293px; overflow-y:auto; display:none; overflow-x:hidden; position:absolute; z-index:10;border: 1px solid #000; background-color:#fff;">
	    		<table id="content" class="selectTable" width="100%" >
				<thead>	<th>品牌</th><th>名称</th><th>型号</th><th>单位</th><th>参数</th><th>数量</th><th>投标名称</th><th>投标价</th><th>目标价</th><th>报价比例%</th><th>显示顺序</th>
			   </thead>
			<tbody id="tbd">
			</tbody>
			</table>
	    </div>
		<table id="content1" class="dataListTable" width="100%">
	        <caption style="background:#B8DDF8;border:1px solid #bbbec3;height:20px;text-align: center; font:bold 14px/28px simsun;">已选定物资</caption>
				<thead>	<th>品牌</th><th>名称</th><th>型号</th><th>单位</th><th>参数</th><th>数量</th><th>投标名称</th><th>投标价</th><th>目标价</th><th>报价比例%</th><th>显示顺序</th>
	           <th class="btnCol">操作</th>
			   </thead>
			    <c:if test="${editMod=='edit'}">
			    <c:forEach items="${record1}" var="result" varStatus="status">
				<tr>
				<td><c:out value="${result.brand_name}" /></td>
	      		<td><c:out value="${result.ma_name}" /></td>
	      		<td><c:out value="${result.model}" /></td>
	      		<td><c:out value="${result.unit}" /></td>
	      		<td><c:out value="${result.parameter}" /></td>
	      		<td>
				<input type="text" name="num${result.id}" dataType="Number" id="num" subid="num" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled;width:30px" value="${result.num}"/>
				</td>
	      		<td>
	      		<input type="text" name="bid_name${result.id}" dataType="Require" id="bid_name" subid="bid_name" value="${result.bid_name}"/>
	      		</td>
	      		<td>
	      		<input type="text" name="bid_price${result.id}" dataType="Double" id="bid_price" subid="bid_price" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${result.bid_price}"/>
	      		</td>
	      		<td>
	      		<input type="text" name="aim_price${result.id}" dataType="Double" id="aim_price" subid="aim_price" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${result.aim_price}"/>
	      		</td>
	      		<td>
	      		<input type="text" name="bjbl${result.id}" dataType="Double" id="bjbl" subid="bjbl" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${result.bjbl}"/>
	      		</td>
	      		<td>
	      		<input type="text" name="shunxu${result.id}" dataType="Number" id="shunxu" subid="shunxu" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${result.shunxu}"/>
	      		</td>
	      		<td>
	      		<a onclick="javascript:return productDel(${result.id});">
	      		<img src='<%=request.getContextPath()%>/image/delete.png'/>
	      		</a>
	      		</td>
	      		</tr>
				</c:forEach>
				</c:if>
			<tbody id="tbd1">
	        </tbody>
			</table>
			</c:if>
			
			<!-- 查看 -->
			<c:if test="${editMod=='show'}">
			<div class="divMod2" style="margin-top:3px;">
			<table class="dataListTable" width="100%">
	        <caption style="background:#B8DDF8;border:1px solid #bbbec3;height:20px;text-align: center; font:bold 14px/28px simsun;">已选定物资</caption>
				<thead>	<th>品牌</th><th>名称</th><th>型号</th><th>单位</th><th>参数</th><th>数量</th><th>投标名称</th><th>投标价</th><th>目标价</th><th>报价比例%</th>
			   </thead>
			    <c:forEach items="${record2}" var="result" varStatus="status">
				<tr>
				<td><c:out value="${result.brand_name}" /></td>
	      		<td><c:out value="${result.ma_name}" /></td>
	      		<td><c:out value="${result.model}" /></td>
	      		<td><c:out value="${result.unit}" /></td>
	      		<td><c:out value="${result.parameter}" /></td>
	      		<td><c:out value="${result.num}" /></td>
	      		<td><c:out value="${result.bid_name}" /></td>
	      		<td><c:out value="${result.bid_price}" /></td>
	      		<td><c:out value="${result.aim_price}" /></td>
	      		<td><c:out value="${result.bjbl}" /></td>
	      		</tr>
				</c:forEach>
			</table>
			</div>
			</c:if>
			<!--
            <tr>
			<th align="center" style="width:10%">投标价(元):</th>
			<td style="width:35%">
			<input type="text" name="bid_price" value="${record.bid_price}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">目标价格(元):</th>
			<td style="width:35%">
			<input type="text" name="aim_price" value="${record.aim_price}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">采购询价(元):</th>
			<td style="width:35%">
			<input type="text" name="cgxj" value="${record.cgxj}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">报价比例(%):</th>
			<td style="width:35%">
			<input type="text" name="bjbl" value="${record.bjbl}"  dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">供应商:</th>
			<td style="width:35%">
			<input type="text" name="supplier_name" id="supplier_name" readonly="readonly" value="${record.supplier_name}" class="textBoxNoBorder"/>
			<input type="text" name="supplier_id" id="supplier_id" value="${record.supplier_id}" class="hidden"/>
			<input type="button" onclick="openSelect('选择供应商','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=mulselect','supplier_id','supplier');" value="选择" />
			</td>
			<th align="center" style="width:10%">联系电话:</th>
			<td style="width:35%">
			<input type="text" name="phone" id="phone" value="${record.phone}" readonly="readonly" maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
             -->
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="saveTable()" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
<script type="text/javascript" defer="defer">
var editMod=$("#editMod").attr("value");
if(editMod){
	$("#table_id").attr("value","${table_id}");
	$("#proj_id").attr("value","${proj_id}");
	$("#operator").attr("value","${user.base_info_id}");
	$("#op_time").attr("value","${op_time}");
}
</script>	
</body>
</html>
