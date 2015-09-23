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
</head>
<style>
.selectTable{BORDER-COLLAPSE: collapse;border: 1px solid #eee;line-height:22px;margin:2px auto;}
.selectTable td{border:1px solid #c5ddf1;text-align: center;}
.selectTable th{background:#eee;border:1px solid #bbbec3;height:20px;text-align: center; font:bold 14px/28px simsun;}
</style>
<script type="text/javascript">
function sub(id)
{
	if(confirm("确定添加?"))
		{
		     if(document.form1.purchase.value =='')
		    	 {
		    	    alert("请填写计划采购月");
		    	    document.form1.purchase.focus();
		    	    return false;
		    	 }
		     
		     if(document.form1.name.value =='')
		    	 {
		    	    alert("请填写计划名称");
		    	    document.form1.name.focus();
		    	    return false;
		    	 }
		     
		     if(document.form1.bh.value =='')
		    	 {
		    	    alert("请填写编号");
		    	    document.form1.bh.focus();
		    	    return false;
		    	 }
		
		 	 var ids = "";
			 var flag = true;
	
			 var obj = document.form1.elements;
			 for (var i=0;i<obj.length;i++){
			 if (obj[i].name == "checkbox"){
			 if (obj[i].checked==true) 
				{
				  flag=false;
				  ids = ids + ";" +obj[i].value;
				}
			
		        }
	          }
	
	      if(flag)
		  {
		    alert("请至少选择一条记录！");
		    return false;
		   }
	      document.form1.pr_plan_state.value=id;
	      document.form1.submit();
		}
}


function selectAll(){
	var obj = document.form1.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "checkbox"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.form1.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "checkbox"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

function chec()
{
	if(document.getElementById("check").checked)
		{
		  selectAll();
		}
	else
		{
		unselectAll();
		}
}
</script>
<script type="text/javascript">${operationSign}</script>
<body>	
	<form action="<%=request.getContextPath()%>/pr/plan/Pr_plan!add.do" method="post" name="form1" id="form1">
	<div align="center">
			<input type="button" name="btn" value="保存不提交" onclick="sub('0');" />
			<input type="button" name="btn" value="保存且提交" onclick="sub('1');" />
			<input type="hidden" name="pr_plan_state"/>
		</div>
	
	<div class="divMod2">
	<div class="divMod1">项目计划信息</div>
		<table width="99%" class="search_border">
		    <input type="hidden" name="prj_id" value="${info.proj_id}"/>
	        <input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
		    <input type="hidden" name="ds_id" value="${dsId}"/>
            <tr>
			<th align="center" style="width:10%">编号:</th>
			<td style="width:35%">
			<input type="text" name="bh" maxLength="20"  style="width:95%" class="textBox"/></td>
			<th align="center" style="width:10%">计划名称:</th>
			<td style="width:35%">
			<input type="text" name="name"  maxLength="200"  style="width:95%" class="textBox"/></td>
            </tr>
            <tr>
			<th align="center" style="width:15%">计划采购月:</th>
			<td style="width:35%">
			<input type="text" name="purchase" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})" class="Wdate"  maxLength="6"  style="width:95%" class="textBox"/></td>
            <th></th>
            <td></td>
            </tr>
		</table>
	</div>
	
		
	
	<div>
		<table class="dataListTable" width="100%">
			<tr>
		   <th>
		   <input type="checkbox" onclick="chec();" id="check">
		   </th>
           <th>序号</th>
           <th>项目</th>
           
           <th>物资名称</th>
           <th>品牌</th>
           <th>设计数量</th>
           <th>计量单位</th>
           <th>已申请数量</th>
           <th>计划采购量</th>
           <th>报审计划说明</th>
           <th>审核计算说明</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
	  <td><input type="checkbox" name="checkbox" value="${result.materiel_id}"/>
       <input type="hidden" name="dsproductid${result.materiel_id}" value="${result.id}"/>
	  
	  </td>		
      <td><c:out value="${status.index+1}" /></td>
      <td><c:out value="${result.proname}" /></td>
  
      <td><c:out value="${result.maname}" /></td>
      <td><c:out value="${result.brname}" /></td>
      <td><c:out value="${result.num}" /></td>
      <td><c:out value="${result.unit}" /></td>
      <td><c:out value="${result.hasnum}" /></td>
      
      <td><input type="text" name="jhsl${result.materiel_id}" value="${result.kcg}" style="width: 50%"/></td>
      <td><input type="text" name="jssm${result.materiel_id}" style="width: 85%" value="${result.jssm}" /></td>
      <td><input type="text" name="shsm${result.materiel_id}" style="width: 85%" value="${result.shsm}" /></td>
      </tr>
			</c:forEach>
		</table>
	</div>
	</form>
	
</body>
</html>