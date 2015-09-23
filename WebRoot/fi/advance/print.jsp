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
<form name="form1" action="<%=request.getContextPath()%>/fi/advance/Fi_advance!print.do" method="post">
<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>
<input type="hidden" name="id" value="${recordAd.id}"/>
</form>
<div id="printDiv">
	<h4 align="left">${fukuan[recordAd.fukuan_unit]}
	<br/>
	<c:if test="${recordAd.print==0}">正本</c:if>
	<c:if test="${recordAd.print!=0}">副本,本次是第${recordAd.print+1}次打印</c:if>
	</h4>
	<h2 align="center">官房电子暂支申请单</h2>
	<div align="center">申请日期：${recordAd.apply_time}</div>
	<table style="width:100%" style="font-size: 14px" border="1px" bordercolor="black" cellpadding="0" cellspacing="0">
           <tr>
		<th align="center" width="25%" colspan="3">部门</th>
		<th align="center" width="25%" colspan="3">${recordAd.dept_name}</th>
		<th align="center" width="25%" colspan="3">申请人</th>
		<th align="center" width="25%" colspan="3">${recordAd.person_name}</th>
		</tr>
           <tr>
		<th align="center" colspan="3">付何单位（收款人）</th>
		<th align="center" colspan="9">
		<c:if test="${not empty recordAd.payeename}">${recordAd.payeename}</c:if>
     		<c:if test="${empty recordAd.payeename}">未填写收款单位</c:if>
     		</th>
		</tr>
           <tr>
           <th align="center" colspan="3">申请事由</th>
		<th align="center" colspan="9">${apply_reason[recordAd.apply_reason]}</th>
		</tr>
           <tr>
           <th align="center" colspan="3">暂支金额</th>
		<th align="right" colspan="9">
		<fmt:formatNumber type="number" value="${recordAd.apply_money}" pattern="#.##" minFractionDigits="2"/></th>
		</tr>
           <tr>
		<th align="center" colspan="3">合计（大写）</th>
		<th align="left" colspan="9">${recordAd.chn}</th>
           </tr>
           <tr>
		<th align="center" colspan="3">付款方式</th>
		<th align="center" colspan="9">支票：<c:if test="${recordAd.advance_type=='1'}">√</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		现金：<c:if test="${recordAd.advance_type=='2'}">√</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		银行担保保函：<c:if test="${recordAd.advance_type=='3'}">√</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		电汇：<c:if test="${recordAd.advance_type=='4'}">√</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</th>
		</tr>
		<tr>
		<th colspan="4"><div align="left">公司领导审批:&nbsp;&nbsp;${gmMap.appopinion}</div><br/><div align="right">${gmMap.name}&nbsp;${gmMap.apptime}</div></th>
		<th colspan="4"><div align="left">部门经理审批:&nbsp;&nbsp;${dmMap.appopinion}</div><br/><div align="right">${dmMap.name}&nbsp;${dmMap.apptime}</div></th>
		<th colspan="4"><div align="left">财务经理审批:&nbsp;&nbsp;>${fmMap.appopinion}</div><br/><div align="right">${fmMap.name}&nbsp;${fmMap.apptime}</div></th>
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
