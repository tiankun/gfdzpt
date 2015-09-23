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
					<strong><font style="font-size: 15px">综合部职员绩效考核表</font> </strong>
				</p>
				<table cellpadding="0" cellspacing="0" width="700" style="font-size: 16px;"
					class="dataListTable">
					<tr>
						<td colspan="3" align="center">
							<strong style="color: red;">注：绩效考核采用扣分制,每月默认满分为100分。</strong>	
												</td>
												<td colspan="2" align="center">
							<strong style="color: red;">岗位：驾驶员</strong>	
												</td>
					</tr>

					<tr>
						<td width="700" colspan="5" align="left">
							&nbsp;&nbsp;被考核员工姓名：&nbsp;${record.name}						</td>
						<td width="700" colspan="2" align="left">
							&nbsp;&nbsp;考核日期：&nbsp;${record.achieve_date}						</td>
					</tr>

					<tr style="font-weight: bold;">
						<td width="700" height="30" >
							<p align="center" >
								序号							</p>						</td>
						<td width="579">
							<p align="center">
								栏目							</p>						</td>
						<td width="451">
							<p align="center">
								权重							</p>						</td>
						<td width="148">
							<p align="center">
								分值							</p>						</td>
						<td width="74">
							<p align="center">
								得分							</p>						</td>
					</tr>
					<tr>
						<td width="700" rowspan="4" >
							<p align="center">
								1							</p>						</td>
						<td width="579" rowspan="4">
							<p align="center">
								工作完成情况	(30分)						</p>						</td>
						<td width="451" height="30">
							<p align="left">
							  按时完成							</p>					  </td>
						<td width="148">
							<p align="center">
								0							</p>						</td>
						<td width="74" rowspan="4">
							<input type="text" name="item1" style="width: 70%" value="${record.item1}">						</td>
					</tr>
					<tr>
						<td width="451" height="30">
							<p align="left">
							  不按时完成							</p>					  </td>
						<td width="148">
							<p align="center">
								-5~10							</p>						</td>
					</tr>
					
					<tr>
						<td width="451" height="30">
							<p align="left">
							  延时24小时以上							</p>					  </td>
						<td width="148">
							<p align="center">
								-10~20						</p>						</td>
					</tr>
					<tr>
						<td width="451" height="30">
							<p align="left">
							  无故推脱领导交办的其他事项</p>					  </td>
						<td width="148">
							<p align="center">
								-1~20</p>					  </td>
					</tr>
					<tr>
						<td width="700" rowspan="6">
							<p align="center">
								2							</p>						</td>
						<td width="579" rowspan="6">
							<p align="center">
								工作质量情况	(40分)							</p>						</td>
						<td width="451" height="30">
							<p align="left">
								交通违规一次扣10分</p>					  </td>
						<td width="148">
							<p align="center">
								-10							</p>						</td>
						<td width="74" rowspan="6">
							<input type="text" name="item2" style="width: 70%" value="${record.item2}">						</td>
					</tr>
					<tr>
						<td width="451" height="30">
							<p align="left">
								安全事故一次扣10~40</p>					  </td>
						<td width="148">
							<p align="center">
								-10~40</p>					  </td>
					</tr>
					<tr>
						<td width="451" height="30">
							<p align="left">
								不按任务指示及时出车，遇突发事件回车不汇报</p>					  </td>
						<td width="148">
							<p align="center">
								-1~10</p>					  </td>
					</tr>
					<tr>
						<td width="451" height="30">
							<p align="left">
								不能保持车辆内部整洁，不及时清理杂物及洗车</p>					  </td>
						<td width="148">
							<p align="center">
								-1~10</p>					  </td>
					</tr>
					<tr>
						<td width="451" height="30">
							<p align="left">
								不注意对车辆保养维修，发现问题不及时汇报</p>					  </td>
						<td width="148">
							<p align="center">
								-5~40</p>					  </td>
					</tr>
					<tr>
						<td width="451" height="30">
							<p align="left">
								发现有私自出车和无故在外逗留，或者费用虚报现象</p>					  </td>
						<td width="148">
							<p align="center">
								-10~40</p>					  </td>
					</tr>
					<tr>
						<td width="700" rowspan="3">
							<p align="center">
								3</p>						</td>
						<td width="579" rowspan="3">
							<p align="center">
								工作量(30分)								</p>						</td>
						<td width="451" height="30">
							<p align="left">
						工作非常饱和</p>						</td>
						<td width="148">
							<p align="center">
								加1~10							</p>						</td>
						<td width="74" rowspan="3">
							<input type="text" name="item3" style="width: 70%" value="${record.item3}">						</td>
					</tr>
					<tr>
						<td width="451" height="30">
							<p align="left">
						工作饱和</p>						</td>
						<td width="148">
							<p align="center">
								0							</p>						</td>
					</tr>
					<tr>
						<td width="451" height="30">
							<p align="left">
						工作不饱和</p>						</td>
						<td width="148">
							<p align="center">
								-1~10							</p>						</td>
					</tr>
					<tr>
						<td width="74">
							<p align="center">
								4
							</p>
						</td>
						<td width="128" height="30">
							<p align="center">
								服务质量
							</p>
						</td>
						<td width="451">
							<p align="left">
								投诉一条扣5分
							</p>
						</td>
						<td width="74">
							<p align="center">
								N&times;-5
							</p>
						</td>
						<td width="74">
							<input type="text" name="item4" style="width: 70%" value="${record.item4}">
						</td>
					</tr>
					<tr>
						<td width="74">
							<p align="center">
								5
							</p>
						</td>
						<td width="128" height="30">
							<p align="center">
								其他事项
							</p>
						</td>
						<td width="451">
							<p align="left">
								如有备注说明
							</p>
						</td>
						<td width="74">
							<p align="center">
								加1~20或-1~20
							</p>
						</td>
						<td width="74">
							<input type="text" name="item5" style="width: 70%" value="${record.item5}">
						</td>
					</tr>
					
					<tr>
						<td width="700">
							<p align="center">
								6							</p>						</td>
						<td width="579" colspan="2" height="30">
							<p align="center">
								合计得分							</p>						</td>
						<td width="148" colspan="2">
							<p align="center">							</p>						</td>
					</tr>
					<tr>
						<td height="30">						</td>
						<td>
							情况说明						</td>
						<td colspan="3">
						 <textarea rows="3" cols="75" name="note1">${record.note1}</textarea>						</td>
					</tr>
					<tr>
						<td height="30">						</td>
						<td>
							批准意见						</td>
						<td colspan="3">
						<textarea rows="3" cols="75" name="note2">${record.note2}</textarea>						</td>
					</tr>
					

					<tr>
						<td colspan="5" height="40" >
							<c:if test="${state=='0'}">
								<input type="submit" name="btn" value="提交绩效"  class="button">
							</c:if>						</td>
					</tr>
					<tr>
						<td colspan="5" height="20">						</td>
					</tr>
				</table>

			</div>
		</form>
	</body>
</html>
