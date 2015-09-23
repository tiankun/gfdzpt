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


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jscripts/easyUI132/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jscripts/easyUI132/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/easyUI132/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jscripts/easyUI132/jquery.easyui.min.js"></script>
<script type="text/javascript">
function cnToSpell(cn){
	$.ajax({
		   type: "POST",
		   url: '<%=request.getContextPath()%>/sysAdmin/CnToSp!getFirst.do',
		   data:"cnString="+cn,
		   success: function(data){
			   $("#shortcode").val(data);
		   }
	});
}
function dosubmit(){
	var s =$('#kind_id').combotree('tree').tree('getSelected');
	if(s==null || s==""){alert("类型不能为空。");return false;}
	if($("#brand_id").val()==""){alert("品牌必须选择。");return false;}
	if($("#name").val()==""){alert("名称不能为空。");return false;}
	//return true;
	$("#form1")[0].submit();
}
</script>



</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" id="form1" action="<%=request.getContextPath()%>/materiel/Materiel!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">类型:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input name="kind_id" id="kind_id" class="easyui-combotree" value="${record.kind_id}" data-options="url:'${pageContext.request.contextPath}/materiel/Ma_kind!getParentTree.do'" style="width:200px;">
            </td>
			<th align="center" style="width:10%">品牌:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			
			<input type="text" name="brandname" id="brandname"
								value="${record.brandname}" class="textBox" />
							<input type="text" name="brand_id" id="brand_id"
								value="${record.brand_id}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择材料类型','<%=request.getContextPath()%>/materiel/Ma_brand!searchList.do?pageType=select','brand_id','brandname');"
								value="选择" />
			
            </td>
            </tr>
            <tr>
			<th align="center" style="width:10%">名称:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="name" id="name" value="${record.name}"   onchange="cnToSpell(this.value)"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">简码:</th>
			<td style="width:35%">
			<input type="text" name="shortcode" id="shortcode" value="${record.shortcode}"    style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">规格型号:</th>
			<td style="width:35%">
			<input type="text" name="model" value="${record.model}"   style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">计量单位:</th>
			<td style="width:35%">
			<input type="text" name="unit" value="${record.unit}"    style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">产品参数:</th>
			<td style="width:35%">
			<input type="text" name="parameter" value="${record.parameter}"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">有效标志:</th>
			<td style="width:35%">
			<select name="yxbz">
			 <option value="">未选择</option>
			 <option value="1" <c:if test="${record.yxbz=='1'}">selected </c:if>  >有效</option>
			 <option value="0" <c:if test="${record.yxbz=='0'}">selected </c:if>>无效</option>
			</select>
			</td>
			</tr>
            <tr>
			<th align="center" style="width:10%">备注:</th>
			<td colspan="3">
			<textarea rows="3" cols="70" name="note">${record.note}</textarea>
			</td>
            </tr>
		</table>
		<br/>
		<div class="div-button">
			<input	type="button" value="提交" onclick="dosubmit();" style="${btnDisplay}" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
</body>
</html>
