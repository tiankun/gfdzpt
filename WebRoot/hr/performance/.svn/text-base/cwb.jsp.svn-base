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



	<body style="background-color: white;">
		<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/hr/performance/Hr_performance!save.do">
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="p_id" value="${record.p_id}" />
			<input type="hidden" name="input_date" value="${record.input_date}" />
			<input type="hidden" name="achieve_date" value="${record.achieve_date}" />
			<input type="hidden" name="f_id" value="${record.f_id}" />
			<input type="hidden" name="dept_id" value="${dept_id}" />
			<div >
				<br />
				<div align="left">
					<input type="button" name="btn" value="返回人员列表" class="button"
						onclick="javascript:history.back();">
				</div>

				<p align="center">
					<strong><font style="font-size: 15px">财务部职员绩效考核表</font> </strong>
				</p>

				<table cellpadding="0" cellspacing="0" width="700" cellpadding="0" cellspacing="0"
					class="dataListTable">
					
					
					<tr>
						<td colspan="4" align="left">
							&nbsp;&nbsp;被考核员工姓名：&nbsp;${record.name}

						</td>
						
					</tr>
					<tr>
						<td colspan="2" width="500">
							<strong>日常工作考核内容及评价依据</strong>
						</td>
						<td width="100">
							分值
						</td>
						<td width="100">
							本月得分
						</td>

					</tr>
					<tr>
						<td rowspan="5" width="140" align="center">
							&nbsp;&nbsp;&nbsp;工作业绩（40%）
						</td>
						<td>
						 <p align="left" style="padding-left: 5px;">
							能及时准确规范的完成工作任务工作效率高
							</p>
						</td>
						<td>
							40
						</td>
						<td rowspan="5">
							<input type="text" name="item1" 
								value="${record.item1}" />
						</td>

					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							能胜任工作，效率较高</p>
						</td>
						<td>
							35-30
						</td>


					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							工作不误期，表现符合标准</p>
						</td>
						<td>
							25-20
						</td>


					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							勉强胜任工作，表现一般</p>
						</td>
						<td>
							15-10
						</td>


					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							工作效率低，有差错或相关业务部门投诉</p>
						</td>
						<td>
							5-0
						</td>


					</tr>
					<tr>
						<td rowspan="4"  align="center">
							工作态度及责任感（30%）
						</td>
						<td>
						 <p align="left" style="padding-left: 5px;">
							具有较强责任心，对突发的工作有积极的工作心态，无投诉</p>
						</td>
						<td>
							30
						</td>
						<td rowspan="4">
							<input type="text" name="item2" 
								value="${record.item2}" />
						</td>

					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							尚有责任心，能如期完成工作任务</p>
						</td>
						<td>
							25-20
						</td>

					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							责任心不强，需督促才能完成</p>
						</td>
						<td>
							15-10
						</td>

					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							无责任心，在督促下也不能完成</p>
						</td>
						<td>
							5-0
						</td>

					</tr>
					<tr>
						<td rowspan="3"  align="center">
							沟通及协作（20%）
						</td>
						<td>
						 <p align="left" style="padding-left: 5px;">
							有团队意识及团结协作精神</p>
						</td>
						<td>
							20
						</td>
						<td rowspan="3">
							<input type="text" name="item3" 
								value="${record.item3}" />
						</td>

					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							尚能与人合作，完成工作任务</p>
						</td>
						<td>
							15-10
						</td>

					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							协调不善或无协调能力工作较难开展</p>
						</td>
						<td>
							5-0
						</td>

					</tr>
					<tr>
						<td rowspan="3"  align="center">
							纪律性（10%）
						</td>
						<td>
						 <p align="left" style="padding-left: 5px;">
							自觉遵守维护公司各项规章制度</p>
						</td>
						<td>
							10
						</td>
						<td rowspan="3">
							<input type="text" name="item4" 
								value="${record.item4}" />
						</td>

					</tr>
					<tr>
						<td> <p align="left" style="padding-left: 5px;">
							尚能遵守公司制度，但需人督促</p>
						</td>
						<td>
							8-5
						</td>

					</tr>
					<tr>
						<td> <p align="left" style="padding-left: 5px;">
							纪律性差，违反公司制度</p>
						</td>
						<td>
							3-0
						</td>

					</tr>
					<tr>
						<td colspan="2" width="426">
							<strong>其他调整事项考核内容及评价依据</strong>
						</td>
						<td>
							分值
						</td>
						<td>
						</td>

					</tr>
					<tr>
						<td rowspan="3" width="70">
							加分项
						</td>
						<td>
						 <p align="left" style="padding-left: 5px;">
							通过自身努力，降低公司税负</p>
						</td>
						<td>
							10
						</td>
						<td rowspan="3">
							<input type="text" name="item5" 
								value="${record.item5}" />
						</td>

					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							以积极的心态应对突发的工作，完成情况较好</p>
						</td>
						<td>
							10
						</td>

					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							积极开动脑筋，收回项目应收帐款有效规避坏帐的</p>
						</td>
						<td>
							10
						</td>

					</tr>
					<tr>
						<td rowspan="2" width="70">
							减分项
						</td>
						<td>
						 <p align="left" style="padding-left: 5px;">
							因个人原因，出现重大差错，导致公司损失的</p>
						</td>
						<td>
							-10
						</td>
						<td rowspan="2">
							<input type="text" name="item6" 
								value="${record.item6}" />
						</td>

					</tr>
					<tr>
						<td>
						 <p align="left" style="padding-left: 5px;">
							经落实的相关业务部门投诉记录/次</p>
						</td>
						<td>
							-10
						</td>

					</tr>
					<tr>
						<td colspan="2">
							<strong>本月综合考评得分</strong>
						</td>
						<td>
						</td>
						<td align="center">
							<font color="red" style="font-weight: bold; font-size: 20px;">${total}<font />
						</td>

					</tr>
					<tr>
						<td width="70">
							<strong>总体评价及改进建议</strong>
						</td>
						<td colspan="3" align="left">
							<textarea rows="3" cols="70" name="note1">${record.note1}</textarea>

						</td>


					</tr>
					
					<tr>
						<td>
							备注
						</td>

						<td colspan="3" align="left">
							<input name="note2" type="text"  style="width: 90%;"
								value="${record.note2}" />
						</td>

					</tr>
					<tr>
						<td colspan="4" >

							1、本表作为公司财务部员工的月度绩效考评，按照当月综合考核得分计算核发月度绩效工资。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、此表均围绕财务人员及时、准确等为主要指标对岗位职责的履行进行评比，岗位职责此表不在一一赘诉。

						</td>

					</tr>
					<tr>
						<td colspan="4" height="15">
						</td>
					</tr>
					<tr>
						<td colspan="4" height="20">
							<c:if test="${state=='0'}">
								<input type="submit" name="btn" value="提交绩效"  class="button">
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="4" height="10">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
