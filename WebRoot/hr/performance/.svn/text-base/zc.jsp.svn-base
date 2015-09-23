<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>官房电子办公平台</title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/default.css" type="text/css"></link>

	</head>
<script type="text/javascript">
function sub()
{
	if(confirm("确定提交绩效考核表"))
		{
		  document.form1.action="<%=request.getContextPath()%>/hr/performance/Hr_performance!sub.do";
		  document.form1.submit();
		}
}
</script>


	<body style="background-color: white;">
		<form name="form1" id="form1" method="post"
			action="<%=request.getContextPath()%>/hr/performance/Hr_performance!save.do">
			<input type="hidden" name="audit_date" value="${achievemap.audit_date}">
			<input type="hidden" name="f_id" value="${achievemap.id}">
			<input type="hidden" name="id" value="${achievemap.id}">
			<input type="hidden" name="dept_id" value="${dept_id}">
			
			<div align="center">
				<br />
				<div align="center">
					<input type="button" name="btn" value="返回" class="button"
						onclick="javascript:history.back();">
					<c:if test="${achievemap.state=='0'}">
							<input type="button" onclick="sub();" name="btn" value="上报人事"  class="button">	
							<input type="submit" name="btn" value="提交绩效"  class="button">
					</c:if>		
				</div>

				<p align="center">
					<strong><font style="font-size: 15px">公司部门经理绩效考核表</font> </strong>
				</p>
				<table width="100%" border="0" class="dataListTable" cellpadding="0" cellspacing="0">
					<tr>
						<td height="40" align="center" width="5%">
							序号
						</td>
						<td height="40" align="center" width="10%">
							&nbsp;
						</td>
						<td height="40" align="center" width="28%">
							&nbsp;
						</td>
						
						<c:forEach var="res" items="${reslist}">
						  <td height="40" align="center" width="45">
							${res.name}
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							1
						</td>
						<td rowspan="5" align="center">
							业务工作
						</td>
						<td height="40" align="center">
							承担的工作量饱满度
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="hidden" name="id${s.count}" value="${res.id}"/>
						   <input type="text" name="item${s.count }1" value="${res.item1}" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" class="textBoxNoBorder"  style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							2
						</td>
						<td height="40" align="center">
							正确理解工作指示和方针，制订适当的实施计划
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						  
						   <input type="text" name="item${s.count }2" value="${res.item2}" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" class="textBoxNoBorder" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							3
						</td>
						<td height="40" align="center">
							按照部下的能力和个性合理分配工作
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }3" value="${res.item3}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							4
						</td>
						<td height="40" align="center">
							及时与有关部门 进行必要的工作联系
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						  
						   <input type="text" name="item${s.count }4" value="${res.item4}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							5
						</td>
						<td height="40" align="center">
							完成工作的品质
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						 
						   <input type="text" name="item${s.count }5" value="${res.item5}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							6
						</td>
						<td rowspan="3" align="center">
							管理监督
						</td>
						<td height="40" align="center">
							在人事关系方面处理公正，部下没有不满或怨言
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }6" value="${res.item6}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							7
						</td>
						<td height="40" align="center">
							在工作中始终保持协作态度，顺利推动工作
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }7" value="${res.item7}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							8
						</td>
						<td height="40" align="center">
							善于放手让部下去工作，鼓励他们乐于协作的精神
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						  
						   <input type="text" name="item${s.count }8" value="${res.item8}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							9
						</td>
						<td rowspan="5" align="center">
							指导协调
						</td>
						<td height="40" align="center">
							与相关部门的协调能力
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }9" value="${res.item9}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							10
						</td>
						<td height="40" align="center">
							注意进行目标管理，使工作协调进行
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						
						   <input type="text" name="item${s.count }10" value="${res.item10}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							11
						</td>
						<td height="40" align="center">
							经常注意保持提高部下的劳动积极性
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						  
						   <input type="text" name="item${s.count }11" value="${res.item11}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							12
						</td>
						<td height="40" align="center">
							积极训练、教育部下，提高他们的技能和素质
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }12" value="${res.item12}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							13
						</td>
						<td height="40" align="center">
							主动努力改善工作和提高效率
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						  
						   <input type="text" name="item${s.count }13" value="${res.item13}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							14
						</td>
						<td rowspan="3" align="center">
							工作效果
						</td>
						<td height="40" align="center">
							工作成绩达到预期目标或计划要求
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }14" value="${res.item14}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							15
						</td>
						<td height="40" align="center">
							工作方法正确，时间和费用使用得合理有效
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }15" value="${res.item15}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							16
						</td>
						<td height="40" align="center">
							工作总结汇报准确真实
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }16" value="${res.item16}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							17
						</td>
						<td rowspan="4" align="center">
							勤务态度
						</td>
						<td height="40" align="center">
							劳动纪律
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }17" value="${res.item17}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							18
						</td>
						<td height="40" align="center">
							对新工作表现出积极态度
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }18" value="${res.item18}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							19
						</td>
						<td height="40" align="center">
							对部下的过失勇于承担责任
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						   <input type="text" name="item${s.count }19" value="${res.item19}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							20
						</td>
						<td height="40" align="center">
							把工作放在第一位，努力工作
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						  
						   <input type="text" name="item${s.count }20" value="${res.item20}" class="textBoxNoBorder" dataType="Double"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" style="width: 45px;">
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							21
						</td>
						<td height="40" align="center">
							&nbsp;
						</td>
						<td height="40" align="center">
							以上合计
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						  ${res.item21}
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" align="center">
							22
						</td>
						<td height="40" align="center">
							&nbsp;
						</td>
						<td height="40" align="center">
							最终考评分
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						   
						  ${res.item22}
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" colspan="5" align="center">
							评价迟度：优14、良12、中10、可8、差6；
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						  
						  </td>
						</c:forEach>
					</tr>
					<tr>
						<td height="40" colspan="5" align="center">
							
						</td>
						<c:forEach var="res" items="${reslist}" varStatus="s">
						  <td height="40" align="center">
						  
						  </td>
						</c:forEach>
					</tr>
				</table>

			</div>
		</form>
	</body>
</html>
