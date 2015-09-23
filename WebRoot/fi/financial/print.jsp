<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title></title>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
<script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
<script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script src="<%= request.getContextPath() %>/jscripts/LodopFuncs.js" type="text/javascript"></script>
<script type="text/javascript">
function printCount(){
	document.form1.submit();
}
</script>
<script type="text/javascript">${operationSign}</script>
</head>
<body>
<form name="form1" action="<%=request.getContextPath()%>/fi/financial/Fi_financial_account!print.do" method="post">
<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>
<input type="hidden" name="id" value="${record.id}"/>
</form>
<div id="printDiv">
	<h4 align="left">${fukuan[record.fukuan_unit]}
	<br/>
	<c:if test="${record.print==0}">正本</c:if>
	<c:if test="${record.print!=0}">副本,本次是第${record.print+1}次打印</c:if>
	</h4>
	<h2 align="center">官房电子费用报销单</h2>
	<div align="center">申请日期：${record.apply_date}</div>
	<table style="width:100%" style="font-size: 14px" border="1px" bordercolor="black" cellpadding="0" cellspacing="0">
           <tr>
		<th align="center" width="25%" colspan="3">部门</th>
		<th align="center" width="25%" colspan="3">${record.dept_name}</th>
		<th align="center" width="25%" colspan="3">申请人</th>
		<th align="center" width="25%" colspan="3">${record.pname}</th>
		</tr>
        <tr>
		<th align="center" colspan="3">项目名称</th>
		<th align="center" colspan="3">
		<c:if test="${not empty record.proj_name}">${record.pro_name}</c:if>
   		<c:if test="${empty record.pro_name}">未关联项目</c:if>
   		</th>
        <th align="center" colspan="3">支付方式</th>
		<th align="center" colspan="3">${advance_type[record.advance_type]}</th>
		</tr>
        <tr>
        <th align="center" colspan="4">摘要</th>
        <th align="center" colspan="4">金额</th>
        <th align="center" colspan="4">附单据数</th>
        </tr>
        <c:forEach items="${record_mx}" var="result" varStatus="status">
        <tr style="font-size: 14px">
        <td align="center" colspan="4">${paytype[result.paytype]}<c:if test="${result.paytype=='12'}">:</c:if> ${result.other}</td>
        <td align="center" colspan="4"><fmt:formatNumber type="number" value="${result.paymoney}" pattern="#.##" minFractionDigits="2"/></td>
        <td align="center" colspan="4">${result.documentsnum}</td>
        </tr>
        </c:forEach>
        <tr>
		<th align="center" width="25%" colspan="6">人民币（大写）： ${record.amount_in_words}</th>
		<th align="center" width="25%" colspan="6">总金额 ：<fmt:formatNumber type="number" value="${record.apply_money}" pattern="#.##" minFractionDigits="2"/></th>
		</tr>
		<tr>
		<th colspan="3"><div align="left">公司领导审批:&nbsp;&nbsp;${gmMap.appopinion}</div><br><div align="right">${gmMap.name}&nbsp;${gmMap.apptime}</div></th>
		<th colspan="3"><div align="left">部门经理审批:&nbsp;&nbsp;${dmMap.appopinion}</div><br><div align="right">${dmMap.name}&nbsp;${dmMap.apptime}</div></th>
		<th colspan="3"><div align="left">财务经理审批:&nbsp;&nbsp;${fmMap.appopinion}</div><br><div align="right">${fmMap.name}&nbsp;${fmMap.apptime}</div></th>
		<th colspan="3"><div align="left">会计审批:&nbsp;&nbsp;${cpaMap.appopinion}</div><br><div align="right">${cpaMap.name}&nbsp;${cpaMap.apptime}</div></th>
		</tr>
		<tr>
		<th colspan="12"><div id="urldate"></div></th>
		</tr>
	</table>
</div>
<br/>
<div align="center"><input type="button" value="打印" onclick="printFile('printDiv');printCount()"/></div>
<script type="text/javascript" defer="defer">
var myDate = new Date();
var url = window.location.href;
var date = myDate.toLocaleString();
$("#urldate").append("<div align='left'>"+url+"</div><div align='right'>打印时间："+date+"</div>");
</script>
</body>
</html>
