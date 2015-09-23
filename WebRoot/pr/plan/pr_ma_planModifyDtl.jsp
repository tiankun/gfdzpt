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
<body>
<script type="text/javascript">
function sub(id)
{
	if(confirm("确定提交申请"))
		{
		document.form1.pr_plan_state.value = id;
		document.form1.submit();
		}
	
	
}


</script>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/pr/plan/Pr_plan!subapply.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="opinionid" />
	<input type="hidden" name="pr_plan_state"/>
	
	<c:if test="${type=='modify'&&record.pr_plan_state!='0'}">
	<div class="divMod2">
	       
		<table class="dataListTable" width="100%">
			<tr>
			<th align="center" style="width:10%">打回原因:</th>
			<td colspan="3">
			  <textarea rows="3" cols="70" name="opinion">${record.opinion}</textarea>
			</td>
			</tr>
		
		</table>
	</div>
	
	<br/>
	     
	</c:if>
	
	
	
		<table class="table_border" style="width:100%">
		    <tr>
			<th align="center" style="width:10%">项目:</th>
			<td colspan="3">
			<input type="text" name="name" value="${record.proname}"   require="false"  onkeyup="value=value.replace(/[^\d]/g,'')" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			
			</tr>
		
            <tr>
			<th align="center" style="width:10%">计划编号:</th>
			<td style="width:35%">
			<input type="text" name="name" value="${record.name}"   style="width:95%" class="textBorder"/></td>
			<th align="center" style="width:10%">编号:</th>
			<td style="width:35%">
			<input type="text" name="bh" value="${record.bh}"  dataType="Require"  maxLength="20"  style="width:95%" class="textBorder"/></td>
			</tr>
            <tr>
            <th align="center" style="width:10%">采购月:</th>
			<td style="width:35%">
			<input type="text" id="purchase" value="${record.purchase}" name="purchase" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})" class="Wdate"/>
			</td>
            <th align="center" style="width:10%">申请时间:</th>
			<td style="width:35%">
			${record.made_date}</td>
            </tr>
            <tr>
					 <th>
					  备注：
					</th>
					  <td colspan="3">
					   <textarea rows="3" style="width: 98%" name="note" >${record.note}</textarea>
					  </td>
					</tr>
            
            
            
		</table>
		
		<br/>
		<div class="divMod2">
	       <div class="divMod1">计划材料</div>
		<table class="dataListTable" width="100%">
			<tr>
		
		   
           <th>序号</th>
          
           <th>物资名称</th>
           <th>品牌</th>
           <th width="65">申请数量</th>
           
           
           
           <th>操作</th>
           
		       
			</tr>
			<c:forEach items="${mlist}" var="result" varStatus="status">
				<tr>
	  		
      <td><c:out value="${status.index+1}" /></td>
     
      <td><c:out value="${result.mnate}" /></td>
      <td><c:out value="${result.bname}" /></td>
      
      
      <td ><input name="sqsl${result.materiel_id}" value="${result.sqsl}" style="width: 70%"/></td>
      
      <td>
     <a href="<%=request.getContextPath()%>/pr/plan/Pr_plan!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a></td>
				
  
      
      
      </tr>
			</c:forEach>
		</table>
	</div>
		
		
		
		<br/>
		<div class="div-button">
		    <input type="button" name="btn" value="保存不提交" onclick="sub('0');" />
			<input type="button" name="btn" value="保存且提交" onclick="sub('1');" />
		   
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
</body>
</html>
