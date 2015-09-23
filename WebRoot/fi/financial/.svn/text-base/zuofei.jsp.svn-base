<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript"src="<%=request.getContextPath()%>/jscripts/EditWin.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function mySub(){
	document.form1.submit();
}
</script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

		<div class="divMod2">
		<div class="divMod1">报账细节</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">申请人:</th>
			<td style="width:35%">${record.pname}</td>
			<th align="center" style="width:10%">项目名称:</th>
			<td style="width:35%">
			<c:if test="${not empty record.pro_name}">${fukuan[record.pro_name]}</c:if>
			<c:if test="${empty record.pro_name}"><font color="red">尚未关联项目</font></c:if>
			</td>
            </tr>
            <tr>
            <th align="center" style="width:10%">支付方式:</th>
			<td style="width:35%">${advance_type[record.advance_type]}</td>
			<th align="center" style="width:10%">收款单位:</th>
			<td style="width:35%">
			<c:if test="${not empty record.skdw_name}">${fukuan[record.skdw_name]}</c:if>
			<c:if test="${empty record.skdw_name}"><font color="red">尚未关联收款单位</font></c:if>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">付款单位:</th>
			<td style="width:35%">
			<c:if test="${not empty record.fukuan_unit}">${fukuan[record.fukuan_unit]}</c:if>
			<c:if test="${empty record.fukuan_unit}"><font color="red">尚未填写</font></c:if>
			</td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
            </tr>
			<tr>
			<th align="center" style="width:10%">附件列表:</th>
			<td colspan="3" id="upTd">
			<a href="#" onclick="openDGMax('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.p_id}&folder=Hr_degree&flag=degree_pic&mark=show')">
			<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
			</a>
    		<!-- <div id="_container">  </div> -->
			</td>
            </tr>
		</table>
		
		<table class="dataListTable" style="width:100%;">
			<thead>
				<th style="text-align: center;width:40%">摘要</th>
				<th style="text-align: center;width:10%">支付金额</th>
				<th style="text-align: center;width:10%">单据数</th>
				<th style="text-align: center;width:20%">备注</th></thead>
			<tbody id="mxlb" >
			<c:forEach items="${record_mx}" var="result" varStatus="status">
			<tr>
			<td>${paytype[result.paytype]} <c:if test="${result.paytype=='12'}">:</c:if> ${result.other}</td>
			<td>
			<fmt:formatNumber type="number" value="${result.paymoney}" pattern="#.##" minFractionDigits="2"/>
			</td>
			<td>${result.documentsnum}</td>
			<td>${result.remark}</td>
            </tr>
            </c:forEach>
            </tbody>
		</table>
		<table class="table_border" style="width:100%;">
		<tr>
			<th align="center" style="width:15%">金额合计:</th>
			<td style="width:15%">
			<fmt:formatNumber type="number" value="${record.apply_money}" pattern="#.##" minFractionDigits="2"/>
			</td>
			<th align="center" style="width:10%">大写:</th>
			<td style="width:60%">${record.amount_in_words}</td>
            </tr>
		</table>
		</div>
		</br>
		<!-- 审批意见记录 -->
		<c:if test="${not empty recordApp}">
		<div class="divMod2">
		<div class="divMod1">审批记录</div>
		<table class="dataListTable" style="width:100%">
		<tr>
		<th>序号</th>
        <th>审批人</th>
        <th>审批时间</th>
        <th>审批意见</th>
        </tr>
        <c:forEach items="${recordApp}" var="result" varStatus="status">
		<tr>
		<td>${status.index+1}</td>
		<td width="20%">${result.person_name}</td>
		<td width="25%">${result.apptime}</td>
		<td width="45%">${result.appopinion}</td>
		</tr>
		</c:forEach>
		</table>
		</div>
		</c:if>
		
		<form name="form1" action="<%=request.getContextPath()%>/fi/financial/Fi_financial_account!invalid.do" method="post">
		<div class="divMod2">
		<div class="divMod1">作废</div>
		<table class="table_border" style="width:100%">
		<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>
		<input type="hidden" id="id" name="id" value="${record.id}"/>
		<input type="hidden" id="pid" name="pid" value="${user.base_info_id}"/>
		<tr>
        <th align="center" style="width:10%">作废原因:</th>
        <td style="width:35%" colspan="3">
        <textarea name="zf_reason" rows ="3" maxLength="500" style="width:98%;border: 0px"></textarea>
        </td>
        </tr>
		</table>
		</div>
		
		<div class="div-button">
			<input type="button" value="提交" onclick="mySub();"/>
			<!-- <input type="button" value="关闭" onclick="javascript:closeDG();"/> -->
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
		</form>
		
<script type="text/javascript" defer="defer">
if(${record.advance_type}=='4'){
	$("#upTd").empty();
	$("#upTd").append("<a href='#' onclick='openDGMax(\"查看\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.id}&folder=fiAcc&flag=accessory&mark=show&oldname=file\")'>"
			+"<img src='<%=request.getContextPath()%>/image/fileopen.png'/></a>");
}
else{
	$("#upTd").empty();
	$("#upTd").append("<a href='#' onclick='openDGMax(\"查看\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${record.id}&folder=fiAcc&flag=accessory&mark=show\")'>"
			+"<img src='<%=request.getContextPath()%>/image/fileopen.png'/></a>");
}
</script>		
</body>
</html>
