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
			<div align="center">
				<br />
				<div align="center">
					<input type="button" name="btn" value="返回人员列表" class="button"
						onclick="javascript:history.back();">
				</div>

				<p align="center">
					<strong><font style="font-size: 15px">设计部职员绩效考核表</font> </strong>
				</p>

				<table cellpadding="0" cellspacing="0" width="700"
					class="dataListTable">
					
					<tr>
						<td  colspan="3" align="center">
							<strong style="color: red;">注：绩效考核标准分值100分，考核时根据月表现酌情加分，扣分。</strong>						</td>
						<td colspan="2" align="center">
							<strong style="color: red;"></strong>	
												</td>
					</tr>

					<tr>
						<td width="700" colspan="5" align="left">
							&nbsp;&nbsp;被考核员工姓名：&nbsp;${record.name}						</td>
					</tr>
					<tr align="center" style="text-align: center;">
						<td width="45" align="center">

							<strong>序号 </strong>

						</td>
						<td width="134" align="center">

							<strong>考核类别 </strong>

						</td>
						<td width="361" align="center">

							<strong>对绩效的考评要点 </strong>

						</td>
						<td width="75" align="center">

							<strong>得分 </strong>

						</td>
						<td width="210" align="center">

							<strong>备注 </strong>

						</td>
					</tr>
					<tr>
						<td width="45">

							1

						</td>
						<td width="134">

							方案质量

						</td>
						<td width="361" valign="top"
							style="text-align: left; padding-left: 3px;">
							1）方案质量一次性达到招标文件或内部设计要求，审核无整改项，不扣分；有亮点，加分1~20分。
							<br />
							2）方案质量不能一次性达到招标文件或内部设计要求，有整改项，则每整改一项扣3分。
							<br />
							3）方案质量不能达到招标文件或内部设计要求，有明显错误，影响投标质量，则每出现一项扣8分。
							<br />
							4）方案质量存在严重缺陷，给公司造成一定损失的，扣50~100分。

						</td>
						<td width="75">
                         <input type="text" name="item1" style="width: 70%;" value="${record.item1}">
						</td>
						<td width="210">

							方案质量审核包括：文字描述水平、预算准确度、设备选用适合度、设计图纸规范度等。

						</td>
					</tr>
					<tr>
						<td width="45">

							2

						</td>
						<td width="134">

							承担工作量

						</td>
						<td width="361" valign="top"
							style="text-align: left; padding-left: 3px;">

							1）独立承担投标项目或为项目负责人，日常工作量正常，不扣分；承接任务多，加班多，加1~10分
							<br />
							2）辅助参与项目，承担工作量一般，扣2分。
							<br />
							3）辅助参与项目，承担工作量较少，扣4分。
							<br />
							4）本月没有参与投标项目或承担内部项目，只做了一些技术支持等，扣8分。

						</td>
						<td width="75">
                       <input type="text" name="item2" style="width: 70%;" value="${record.item2}"> 
						</td>
						<td width="210">

							工作量考核主要针对外部投标项目和内部设计项目的参与程度。

						</td>
					</tr>
					<tr>
						<td width="45">

							3

						</td>
						<td width="134">

							任务划执进度

						</td>
						<td width="361" valign="top"
							style="text-align: left; padding-left: 3px;">

							1）按预期要求完成每一阶段设计任务，不扣分；准时或提前完成几个任务，加1~10分。
							<br />
							2）不能按计划书完成设计任务，但能在批准允许的时间内完成，扣2分。
							<br />
							3）不能按计划书完成设计任务，也未能在批准允许的时间内完成，扣15分。
							<br />
							4）因不能按计划书完成设计任务，并产生了负面影响，扣20~100分。

						</td>
						<td width="75">
                      <input type="text" name="item3" style="width: 70%;" value="${record.item3}"></td>
						<td width="210">

							&nbsp;

						</td>
					</tr>
					<tr>
						<td width="700" colspan="5" style="text-align: left;padding-left: 10px;">

							1．通过以上各项的得分，综合得分是：<font color="red"><strong>${total}</strong></font>分。
							<br />
							2．其他说明：<br/>
                           <textarea rows="3" cols="90" name="note">${record.note}</textarea>
						</td>
					</tr>
					
					<tr>
						<td colspan="5" height="15">
						</td>
					</tr>
					<tr>
						<td colspan="5" height="20">
							<c:if test="${state=='0'}">
								<input type="submit" name="btn" value="提交绩效"  class="button">
							</c:if>			
						</td>
					</tr>
					<tr>
						<td colspan="5" height="10">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
