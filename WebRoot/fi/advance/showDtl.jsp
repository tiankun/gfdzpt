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
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<script type="text/javascript">${operationSign}</script>

		<div class="divMod2">
		<div class="divMod1">基本信息</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">申请人:</th>
			<td style="width:35%">${recordAd.person_name}</td>
			<th align="center" style="width:10%">所在部门:</th>
			<td style="width:35%">${recordAd.dept_name}</td>
			</tr>
            <tr>
			<th align="center" style="width:10%">收款单位:</th>
			<td style="width:35%">
			<c:if test="${not empty recordAd.payeename}">${fukuan[recordAd.payeename]}</c:if>
			<c:if test="${empty recordAd.payeename}"><font color="red">尚未关联收款单位</font></c:if>
			</td>
			<th align="center" style="width:10%">借支类型:</th>
			<td style="width:35%">${advance_type[recordAd.advance_type]}</td>
			</tr>
            <tr>
            <th align="center" style="width:10%">申请原因:</th>
			<td style="width:35%">${apply_reason[recordAd.apply_reason]}</td>
			<th align="center" style="width:10%">欠款:</th>
			<td style="width:35%">
			<c:if test="${recordAd.apply_money!=recordAd.back_money}"><font color="red"><fmt:formatNumber type="number" value="${recordAd.apply_money-recordAd.back_money}" pattern="#.##" minFractionDigits="2"/></font></c:if>
			<c:if test="${recordAd.apply_money==recordAd.back_money}"><font color="green">已还清</font></c:if>
			</td>
            </tr>
            <tr>
            <th align="center" style="width:10%">暂支金额:</th>
			<td style="width:35%"><fmt:formatNumber type="number" value="${recordAd.apply_money}" pattern="#.##" minFractionDigits="2"/></td>
			<th align="center" style="width:10%">大写:</th>
			<td style="width:35%">${recordAd.chn}</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">付款单位:</th>
			<td style="width:35%">
			<c:if test="${not empty recordAd.fukuan_unit}">${fukuan[recordAd.fukuan_unit]}</c:if>
			<c:if test="${empty recordAd.fukuan_unit}"><font color="red">尚未填写</font></c:if>
			</td>
			<th align="center" style="width:10%">附件列表:</th>
			<td style="width:35%" id="upTd">
			<a href="#" onclick="openDGMax('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${recordAd.id}&folder=fiAcc&flag=accessory&mark=show&oldname=file')">
					<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
		    </a>
	
			</td>
            </tr>
            <tr>
            <th align="center" style="width:10%">备注:</th>
            <td style="width:35%" colspan="3">
            <textarea name="remark" rows ="3" value="${recordAd.remark}" maxLength="500"  style="width:98%;border: 0px">${recordAd.remark}</textarea>
            </td>
            </tr>
		</table>
		</div>
		
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
		
		<!-- 还款记录 -->
		<c:if test="${not empty recordBak}">
		<div class="divMod2">
		<div class="divMod1">还款记录</div>
		<table class="dataListTable" style="width:100%">
		<th>序号</th>
        <th>还款金额</th>
        <th>还款时间</th>
        <th>备注</th>
        </tr>
        <c:forEach items="${recordBak}" var="result" varStatus="status">
		<tr>
		<td width="10%">${status.index+1}</td>
		<td width="20%">${result.backed_money}</td>
		<td width="20%">${result.back_date}</td>
		<td width="50%">${result.remark}</td>
		</tr>
		</c:forEach>
		</table>
		</div>
		</c:if>
		
		<!-- 作废记录 -->
		<c:if test="${not empty recordAd.zf_reason}">
		<div class="divMod2">
		<div class="divMod1">作废记录</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">作废人:</th>
			<td style="width:35%">${recordAd.zf_person}</td>
			<th align="center" style="width:10%">作废时间:</th>
			<td style="width:35%">${recordAd.zf_time}</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">作废原因:</th>
			<td>
			<textarea rows ="3" maxLength="500" style="width:98%;border: 0px">${recordAd.zf_reason}</textarea>
			</td>
			</tr>
		</table>
		</div>
		</c:if>
		</br>
		
		<div class="div-button">
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
</body>
</html>
