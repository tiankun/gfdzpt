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

	<form name="form1" action="<%=request.getContextPath()%>/fi/travel/Fi_travelacc!${editMod}.do" method="post">
	<input type="hidden" name="editMod" id="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
		<div class="divMod2">
		<div class="divMod1">基本信息</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">申请人:</th>
			<td style="width:35%">${recordAcc.person_name}</td>
			<th align="center" style="width:10%">所在部门:</th>
			<td style="width:35%">${recordAcc.dept_name}</td>
			</tr>
            <tr>
			<th align="center" style="width:10%">出差申请:</th>
			<td style="width:35%">
			<c:if test="${not empty recordAcc.b_time}">${recordAcc.b_time} 出差</c:if>
			<c:if test="${empty recordAcc.b_time}"><font color="red">未关联申请</font></c:if>
			</td>
			<th align="center" style="width:10%">项目名称:</th>
			<td style="width:35%">
			<c:if test="${not empty recordAcc.proj_name}">${recordAcc.proj_name}</c:if>
			<c:if test="${empty recordAcc.proj_name}"><font color="red">未关联项目</font></c:if>
			</td>
            </tr>
            <tr>
			<th align="center" style="width:10%">付款单位:</th>
			<td style="width:35%">
			<c:if test="${not empty recordAcc.fukuan_unit}">${fukuan[recordAcc.fukuan_unit]}</c:if>
			<c:if test="${empty recordAcc.fukuan_unit}"><font color="red">尚未填写</font></c:if>
			</td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
            </tr>
            <!-- 
            <tr>
			<th align="center" style="width:10%">发票标识:</th>
			<td style="width:35%">
			<input type="text" name="pyt" value="${record.pyt}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%"></th>
            <td style="width:35%"></td>
            </tr>
             -->
		</table>
		</div>
		</br>
		<!-- 随行人员 -->
		<c:if test="${not empty recordSub}">
		<div class="divMod2">
		<div class="divMod1">随行人员</div>
		<table class="dataListTable" width="100%">
		<tr>
		<th>序号</th>
	    <th>姓名</th>
	    <th>部门</th>
	    </tr>
	    <c:forEach items="${recordSub}" var="result" varStatus="status">
		<tr>
		<td>${status.index+1}</td>
		<td width="45%">${result.person_name}</td>
		<td width="45%">${result.dept_name}</td>
		</tr>
		</c:forEach>
	    </table>
		</div>
		</br>
		</c:if>
		
		<div class="divMod2">
		<div class="divMod1">明细</div>
		<table class="dataListTable" style="width:100%">
		<tr>
		<th width="15%">出差地</th>
		<th width="9%">交通工具</th>
        <th width="10%">到达时间</th>
        <th width="10%">离开时间</th>
        <th width="9%">报销标准</th>
        <th width="8%">住宿方式</th>
        <th width="7%">附件数</th>
        <th width="7%">交通费</th>
        <th width="10%">市内交通费</th>
        <th width="7%">住宿费</th>
        <th width="7%">出差补贴</th>
        </tr>
        <c:forEach items="${recordDtl}" var="result" varStatus="status">
		<tr>
        <td><c:out value="${result.areaname}" /></td>
        <td><c:out value="${result.vehicle}" /></td>
        <td><c:out value="${result.r_time}" /></td>
        <td><c:out value="${result.l_time}" /></td>
        <td style="color: red;background: #EEEEEE"">
        <fmt:formatNumber type="number" value="${result.r_fund}" pattern="#.##" minFractionDigits="2"/>
        </td>
        <td><c:out value="${stayType[result.stay_type]}" /></td>
        <td><c:out value="${result.accessory}" /></td>
        <td>
        <fmt:formatNumber type="number" value="${result.trans_cost}" pattern="#.##" minFractionDigits="2"/>
        </td>
        <td>
        <fmt:formatNumber type="number" value="${result.citytrans_cost}" pattern="#.##" minFractionDigits="2"/>
        </td>
        <td>
        <fmt:formatNumber type="number" value="${result.hotel_cost}" pattern="#.##" minFractionDigits="2"/>
        </td>
        <td>
        <fmt:formatNumber type="number" value="${result.buzhu}" pattern="#.##" minFractionDigits="2"/>
        </td>
        </tr>
        </c:forEach>
		</table>
		<table class="table_border" style="width:100%">
		<tr>
		<th align="center" style="width:10%">报销总金额:</th>
		<td style="width:35%;background: #EEEEEE">
		<input type="text" name="totalcost" id="totalcost" value="<fmt:formatNumber type="number" value="${recordAcc.totalcost}" pattern="#.##" minFractionDigits="2"/>" readonly="readonly" dataType="Double" require="false"  onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" style="width:95%;background: #EEEEEE;color:red;" class="textBoxNoBorder"/>
        <!-- <input type="button" onclick="calTotal()" value="计算"> --></td>
        <th align="center" style="width:10%">人民币大写:</th>
		<td style="width:35%;background: #EEEEEE">
		<input type="text" id="costchn" name="costchn" value="${recordAcc.costchn}" readonly="readonly" style="width:95%;background: #EEEEEE;color:red;" class="textBoxNoBorder"/>
		</td>
        </tr>
        </table>
		</div>
		
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
		
		<!-- 作废记录 -->
		<c:if test="${not empty recordAcc.zf_reason}">
		<div class="divMod2">
		<div class="divMod1">作废记录</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">作废人:</th>
			<td style="width:35%">${recordAcc.zf_pname}</td>
			<th align="center" style="width:10%">作废时间:</th>
			<td style="width:35%">${recordAcc.zf_time}</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">作废原因:</th>
			<td colspan="3">
			<textarea rows ="3" maxLength="500" style="width:98%;border: 0px">${recordAcc.zf_reason}</textarea>
			</td>
			</tr>
		</table>
		</div>
		</c:if>
		</br>
		
		<div class="div-button">
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
		</div>
	</form>
</body>
</html>