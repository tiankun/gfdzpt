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
<form name="form1" action="<%=request.getContextPath()%>/fi/travel/Fi_travelacc!print.do" method="post">
<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>
<input type="hidden" name="id" value="${recordAcc.id}"/>
</form>
<div id="printDiv">
	<h4 align="left">${fukuan[recordAcc.fukuan_unit]}
	<br/>
	<c:if test="${recordAcc.print==0}">正本</c:if>
	<c:if test="${recordAcc.print!=0}">副本,本次是第${recordAcc.print+1}次打印</c:if>
	</h4>
	<h2 align="center">官房电子差旅费报销单</h2>
	<div align="center">申请日期：${recordAcc.reim_time}</div>
	<table style="width:100%" style="font-size: 14px" border="1px" bordercolor="black" cellpadding="0" cellspacing="0">
           <tr>
		<th align="center" width="25%" colspan="3">部门</th>
		<th align="center" width="25%" colspan="3">${recordAcc.dept_name}</th>
		<th align="center" width="25%" colspan="3">申请人</th>
		<th align="center" width="25%" colspan="3">${recordAcc.person_name}</th>
		</tr>
        <tr>
		<th align="center" colspan="3">项目名称</th>
		<th align="center" colspan="3">
		<c:if test="${not empty recordAcc.proj_name}">${recordAcc.proj_name}</c:if>
   		<c:if test="${empty recordAcc.proj_name}">未关联项目</c:if>
   		</th>
        <th align="center" colspan="3">出差事由</th>
		<th align="center" colspan="3">${recordAcc.gtreason}</th>
		</tr>
        <tr>
        <th align="center" colspan="3">出差地</th>
        <th align="center">到达时间</th>
        <th align="center">离开时间</th>
        <th align="center">人数</th>
        <th align="center">附件数</th>
        <th align="center">交通费</th>
        <th align="center">住宿费</th>
        <th align="center">市内交通</th>
        <th align="center">出差补贴</th>
        </tr>
        <c:forEach items="${recordDtl}" var="result" varStatus="status">
        <tr>
        <th align="center" colspan="3">${result.areaname}</th>
        <th align="center">${result.r_time}</th>
        <th align="center">${result.l_time}</th>
        <th align="center">${subCount}</th>
        <th align="center">${result.accessory}</th>
        <th align="center"><fmt:formatNumber type="number" value="${result.trans_cost}" pattern="#.##" minFractionDigits="2"/></th>
        <th align="center"><fmt:formatNumber type="number" value="${result.hotel_cost}" pattern="#.##" minFractionDigits="2"/></th>
        <th align="center"><fmt:formatNumber type="number" value="${result.citytrans_cost}" pattern="#.##" minFractionDigits="2"/></th>
        <th align="center"><fmt:formatNumber type="number" value="${result.buzhu}" pattern="#.##" minFractionDigits="2"/></th>
        </tr>
        </c:forEach>
        <tr>
		<th align="center" width="25%" colspan="6">人民币（大写）： ${recordAcc.costchn}</th>
		<th align="center" width="25%" colspan="6">总金额 ：<fmt:formatNumber type="number" value="${recordAcc.totalcost}" pattern="#.##" minFractionDigits="2"/></th>
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
