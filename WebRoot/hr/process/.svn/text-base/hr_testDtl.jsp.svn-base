<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld"  prefix="fn"%>
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
<script type="text/javascript">
function getChkVal(){
	var val = '{';
	var nameOld = '';
	$("input:checked").each(function(){
		var curName = $(this).attr("name");
		if(nameOld!='' && nameOld != curName){
			val = val.substring(0,val.length-1);
			val+= '],';
		}
		if(nameOld != curName){
			val+= '"'+curName+'":[';  
		}
		val+= '"'+$(this).val()+'",';
		if(nameOld != curName){				
			nameOld = curName;
		}
	});
	val = val.substring(0,val.length-1);
	val+=']}';	
	$("#chkval").val(val);
	return mySubmit();
}

function mySubmit(){
	if(Validator.Validate(document.form1,3))
	{
		document.form1.submit();
	}
}
</script>

</head>
<body>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/hr/process/Hr_test!${editMod}.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
		<div class="divMod2" style="margin-top:3px;">
		<div class="divMod1" align="center">初试信息</div>
		<table class="table_border" style="width:100%">
			<input type="hidden" name="p_id" value="${record.p_id}"/>
            <tr>
            <th align="center" style="width:10%">应聘岗位:<span style="color:Red;">*</span></th>
			<td style="width:35%">
			<input type="text" name="position" value="${record.position}"  dataType="Require"  maxLength="40"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">对岗位的认识:</th>
			<td style="width:35%">
			<input type="text" name="about_pst" value="${record.about_pst}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
            <th align="center" style="width:10%">希望待遇:</th>
			<td style="width:35%">
			<input type="text" name="want_pay" value="${record.want_pay}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">待遇底线:</th>
			<td style="width:35%">
			<input type="text" name="pay_underline" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" value="${record.pay_underline}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">现工作单位:</th>
			<td style="width:35%">
			<input type="text" name="unit_now" value="${record.unit_now}"  maxLength="100"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">现职业状况:</th>
			<td style="width:35%">
			<input type="text" name="u_status" value="${record.u_status}"  maxLength="10"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">离职原因:</th>
			<td style="width:35%">
			<input type="text" name="reason_l" value="${record.reason_l}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">到岗时间:</th>
			<td style="width:35%">
			<input type="text" name="pos_time" value="${record.pos_time}"  dataType="Date" require="false"  onfocus="WdatePicker()" style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">优势特点:</th>
			<td style="width:35%">
			<input type="text" name="superiority" value="${record.superiority}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">声音:</th>
			<td style="width:35%">
			<input type="text" name="voice" value="${record.voice}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">性格:</th>
			<td style="width:35%" colspan="3">
			<c:forEach items="${character}" var="character">
			<label><input type="checkbox" id="character${character.key}" 
				<c:if test="${fn:contains(record.character,character.key)}">
					checked="checked"
				</c:if>
			name="character" value="${character.key}"/>
			<font
				<c:if test="${fn:contains(record.character,character.key)}">
					color="blue" size="3" 
				</c:if>>
				<c:if test="${fn:contains(record.character,character.key)}"><B></c:if>
					${character.value}
				<c:if test="${fn:contains(record.character,character.key)}"></B></c:if>	
			</font>
			</lable>
			</c:forEach>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%" colspan="3">
			<input type="text" name="o_character" value="${record.o_character}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">语言:</th>
			<td style="width:35%" colspan="3">
			<c:forEach items="${language}" var="language">
			<label><input type="checkbox" 
				<c:if test="${fn:contains(record.language,language.key)}">
					checked="checked"
				</c:if>
			name="language" value="${language.key}"/>
			<font
				<c:if test="${fn:contains(record.language,language.key)}">
					color="blue" size="3" 
				</c:if>>
				<c:if test="${fn:contains(record.language,language.key)}"><B></c:if>
				${language.value}
				<c:if test="${fn:contains(record.language,language.key)}"></B></c:if>	
			</font>
			</lable>
			</c:forEach>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%" colspan="3">
			<input type="text" name="o_language" value="${record.o_language}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">形象气质:</th>
			<td style="width:35%" colspan="3">
			<c:forEach items="${form}" var="form">
			<label><input type="checkbox" 
				<c:if test="${fn:contains(record.form,form.key)}">
					checked="checked"
				</c:if>
			name="form" value="${form.key}"/>
			<font
				<c:if test="${fn:contains(record.form,form.key)}">
					color="blue" size="3" 
				</c:if>>
				<c:if test="${fn:contains(record.form,form.key)}"><B></c:if>
				${form.value}
				<c:if test="${fn:contains(record.form,form.key)}"></B></c:if>	
			</font>
			</lable>
			</c:forEach>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%" colspan="3">
			<input type="text" name="o_form" value="${record.o_form}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
            <tr>
			<th align="center" style="width:10%">态度举止:</th>
			<td style="width:35%" colspan="3">
			<c:forEach items="${manner}" var="manner">
			<label><input type="checkbox" 
				<c:if test="${fn:contains(record.manner,manner.key)}">
					checked="checked"
				</c:if>
			name="manner" value="${manner.key}"/>
			<font
				<c:if test="${fn:contains(record.manner,manner.key)}">
					color="blue" size="3" 
				</c:if>>
				<c:if test="${fn:contains(record.manner,manner.key)}"><B></c:if>
				${manner.value}
				<c:if test="${fn:contains(record.manner,manner.key)}"></B></c:if>
			</font>
			</lable>
			</c:forEach>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%" colspan="3">
			<input type="text" name="o_manner" value="${record.o_manner}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">行走坐立细节描述:</th>
			<td style="width:35%" colspan="3">
			<c:forEach items="${behavior}" var="behavior">
			<label><input type="checkbox" 
				<c:if test="${fn:contains(record.behavior,behavior.key)}">
					checked="checked"
				</c:if>
			name="behavior" value="${behavior.key}"/>
			<font
				<c:if test="${fn:contains(record.behavior,behavior.key)}">
					color="blue" size="3" 
				</c:if>>
				<c:if test="${fn:contains(record.behavior,behavior.key)}"><B></c:if>
				${behavior.value}
				<c:if test="${fn:contains(record.behavior,behavior.key)}"></B></c:if>
			</font>
			</lable>
			</c:forEach>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%" colspan="3">
			<input type="text" name="o_behavior" value="${record.o_behavior}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
			</tr>
			<tr>
			<th align="center" style="width:10%">表达方式:</th>
			<td style="width:35%" colspan="3">
			<c:forEach items="${expression}" var="expression">
			<label><input type="checkbox" 
				<c:if test="${fn:contains(record.expression,expression.key)}">
					checked="checked"
				</c:if>
			name="expression" value="${expression.key}"/>
			<font
				<c:if test="${fn:contains(record.expression,expression.key)}">
					color="blue" size="3" 
				</c:if>>
				<c:if test="${fn:contains(record.expression,expression.key)}"><B></c:if>
				${expression.value}
				<c:if test="${fn:contains(record.expression,expression.key)}"></B></c:if>
			</font>
			</lable>
			</c:forEach>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%" colspan="3">
			<input type="text" name="o_expression" value="${record.o_expression}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">问题处理能力:</th>
			<td style="width:35%" colspan="3">
			<c:forEach items="${deal_ab}" var="deal_ab">
			<label><input type="checkbox" 
				<c:if test="${fn:contains(record.deal_ab,deal_ab.key)}">
					checked="checked"
				</c:if>
			name="deal_ab" value="${deal_ab.key}"/>
			<font
				<c:if test="${fn:contains(record.deal_ab,deal_ab.key)}">
					color="blue" size="3" 
				</c:if>>
				<c:if test="${fn:contains(record.deal_ab,deal_ab.key)}"><B></c:if>
				${deal_ab.value}
				<c:if test="${fn:contains(record.deal_ab,deal_ab.key)}"></B></c:if>
			</font>
			</lable>
			</c:forEach>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%" colspan="3">
			<input type="text" name="o_deal_ab" value="${record.o_deal_ab}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">倾听能力:</th>
			<td style="width:35%" colspan="3">
			<c:forEach items="${listen}" var="listen">
			<label><input type="checkbox" 
				<c:if test="${fn:contains(record.listen,listen.key)}">
					checked="checked"
				</c:if>
			name="listen" value="${listen.key}"/>
			<font
				<c:if test="${fn:contains(record.listen,listen.key)}">
					color="blue" size="3" 
				</c:if>>
				<c:if test="${fn:contains(record.listen,listen.key)}"><B></c:if>
				${listen.value}
				<c:if test="${fn:contains(record.listen,listen.key)}"></B></c:if>
			</font>
			</lable>
			</c:forEach>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%" colspan="3">
			<input type="text" name="o_listen" value="${record.o_listen}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">思维深度:</th>
			<td style="width:35%" colspan="3">
			<c:forEach items="${thinking}" var="thinking">
			<label><input type="checkbox" 
				<c:if test="${fn:contains(record.thinking,thinking.key)}">
					checked="checked"
				</c:if>
			name="thinking" value="${thinking.key}"/>
			<font
				<c:if test="${fn:contains(record.thinking,thinking.key)}">
					color="blue" size="3" 
				</c:if>>
				<c:if test="${fn:contains(record.thinking,thinking.key)}"><B></c:if>
				${thinking.value}
				<c:if test="${fn:contains(record.thinking,thinking.key)}"></B></c:if>
			</font>
			</lable>
			</c:forEach>
			</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%" colspan="3">
			<input type="text" name="o_thinking" value="${record.o_thinking}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">公司比重(%):</th>
			<td style="width:35%">
			<input type="text" name="u_scale" value="${record.u_scale}" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">个人比重(%):</th>
			<td style="width:35%">
			<input type="text" name="p_scale" value="${record.p_scale}" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">薪酬比重(%):</th>
			<td style="width:35%">
			<input type="text" name="m_scale" value="${record.m_scale}" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">人际比重(%):</th>
			<td style="width:35%">
			<input type="text" name="r_scale" value="${record.r_scale}" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">其他:</th>
			<td style="width:35%">
			<input type="text" name="other" value="${record.other}"  maxLength="50"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">其他比重(%):</th>
			<td style="width:35%">
			<input type="text" name="o_scale" value="${record.o_scale}" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled" maxLength="2"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">抗压能力:</th>
			<td style="width:35%">
			<input type="text" name="anti_pres" value="${record.anti_pres}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">解压方式:</th>
			<td style="width:35%">
			<input type="text" name="extract_way" value="${record.extract_way}"  maxLength="40"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">吃苦能力:</th>
			<td style="width:35%">
			<input type="text" name="willpower" value="${record.willpower}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">其他描述:</th>
			<td style="width:35%">
			<input type="text" name="back" value="${record.back}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            <tr>
			<th align="center" style="width:10%">考题方案评价:</th>
			<td style="width:35%">
			<input type="text" name="examine" value="${record.examine}"  maxLength="300"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%"></th>
			<td style="width:35%"></td>
            </tr>
		</table></div>
		<c:if test="${record.a_type==1}">
		<div class="divMod2" style="margin-top:3px;">
		<div class="divMod1" align="center">复试审核信息</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">人事主管意见:</th>
			<td style="width:35%">${record.a_hr}</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">部门经理意见:</th>
			<td style="width:35%">${record.a_dm}</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">总经理意见:</th>
			<td style="width:35%">${record.a_gm}</td>
			</tr>
		</table></div>
		</c:if>
		<c:if test="${record.t_type==2}">
		<div class="divMod2" style="margin-top:3px;">
		<div class="divMod1" align="center">入职信息</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">试用工资:</th>
			<td style="width:35%">${record.entry_pay}</td>
			<th align="center" style="width:10%">试用期至:</th>
			<td style="width:35%">${record.entry_date}</td>
			</tr>
			<!-- 
			<tr>
			<th align="center" style="width:10%">人事主管意见:</th>
			<td style="width:35%">${record.t_hr}</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">部门经理意见:</th>
			<td style="width:35%">${record.t_dm}</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">总经理意见:</th>
			<td style="width:35%">${record.t_gm}</td>
			</tr>
			 -->
		</table></div>
		</c:if>
		<c:if test="${record.e_type==6}">
		<div class="divMod2" style="margin-top:3px;">
		<div class="divMod1" align="center">转正审核信息</div>
		<table class="table_border" style="width:100%">
			<tr>
			<th align="center" style="width:10%">人事主管意见:</th>
			<td style="width:35%">${record.e_hr}</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">部门经理意见:</th>
			<td style="width:35%">${record.e_dm}</td>
			</tr>
			<tr>
			<th align="center" style="width:10%">总经理意见:</th>
			<td style="width:35%">${record.e_gm}</td>
			</tr>
		</table>
		</div>
		</c:if>
		<div class="div-button">
			<input	type="button" value="提交" style="${btnDisplay}" onclick="getChkVal()"/>
			<!-- onclick="if(Validator.Validate(document.form1,3)){document.form1.submit();}" -->
			<c:if test="${showFlag!='show'}">
			<input type="button" value="关闭" onclick="javascript:closeDG();"/>
			</c:if>
		</div>
		<input type="hidden" id="chkval" name="chkval"/>
	</form>
</body>
</html>
