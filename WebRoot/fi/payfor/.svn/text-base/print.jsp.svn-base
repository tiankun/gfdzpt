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
<form name="form1" action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!print.do" method="post">
<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>
<input type="hidden" name="id" value="${main.id}"/>
</form>
<div id="printDiv">
	<h4 align="left">${main.kjtype}
	<br/>
	<c:if test="${main.print==0}">正本</c:if>
	<c:if test="${main.print!=0}">副本,本次是第${recordAcc.print+1}次打印</c:if>
	</h4>
	<h2 align="center">官房电子款项支付单</h2>
	<div align="center">申请日期：${main.odate}</div>
	<table style="width:100%" style="font-size: 14px" border="1px" bordercolor="black" cellpadding="0" cellspacing="0">
           <tr>
		<th align="center" style="width: 12.5%" colspan="2">部门</th>
		<th align="center" style="width: 12.5%" colspan="2">${main.dept_name}</th>
		<th align="center" style="width: 12.5%" colspan="2">申请人</th>
		<th align="center" style="width: 12.5%" colspan="2">${main.pname}</th>
		<th align="center" style="width: 25%" colspan="2">收款单位</th>
		<th align="center" style="width: 25%" colspan="2">${main.name}</th>
		</tr>
        <tr>
        <th align="center" colspan="8">申请事由</th>
        <th align="center" colspan="2">附件数</th>
        <th align="center" colspan="2">预计金额</th>
        </tr>
        <c:forEach items="${itemlist}" var="result" varStatus="status">
        <tr>
        <th align="center" colspan="8">${result.applyreason}</th>
        <th align="center" colspan="2">${result.fujnum}</th>
        <th align="center" colspan="2"><fmt:formatNumber type="number" value="${result.money}" pattern="#.##" minFractionDigits="2"/></th>
        </tr>
        </c:forEach>
        <tr>
		<th align="center" colspan="10">合计人民币（大写）：&nbsp;&nbsp;${main.bmoney}</th>
		<th align="center" colspan="2">总金额 ：<fmt:formatNumber type="number" value="${main.money}" pattern="#.##" minFractionDigits="2"/></th>
		</tr>
		<tr>
		<th align="center" colspan="3">付款方式</th>
		<th align="center" colspan="9">支票：<c:if test="${main.paytype=='1'}">√</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		现金：<c:if test="${main.paytype=='2'}">√</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		电汇：<c:if test="${main.paytype=='3'}">√</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		银行承兑汇票：<c:if test="${main.paytype=='4'}">√</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</th>
		</tr>
		<tr>
		<th colspan="4"><div align="left">公司领导审批:&nbsp;&nbsp;${gmMap.opinion}</div><br><div align="right">${gmMap.name}&nbsp;${gmMap.audit_date}</div></th>
		<th colspan="4"><div align="left">部门经理审批:&nbsp;&nbsp;${dmMap.opinion}</div><br><div align="right">${dmMap.name}&nbsp;${dmMap.audit_date}</div></th>
		<th colspan="2"><div align="left">财务经理审批:&nbsp;&nbsp;${fmMap.opinion}</div><br><div align="right">${fmMap.name}&nbsp;${fmMap.audit_date}</div></th>
		<th colspan="2"><div align="left">会计审批:&nbsp;&nbsp;${cpaMap.opinion}</div><br><div align="right">${cpaMap.name}&nbsp;${cpaMap.audit_date}</div></th>
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
