<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title></title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
		<script src="<%=request.getContextPath()%>/jscripts/jquery.js"
			type="text/javascript">
</script>
		<script
			src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"
			type="text/javascript">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>

		<script type="text/javascript">
function tongji() {
	if (document.form1.kqdate.value == '') {
		alert("请选择考勤时间!");
		document.form1.kqdate.focus();
		return false;
	}
	document.form1.action = '<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!searchPersonList.do';
	document.form1.target ='_self';
	document.form1.submit();
}

function sub()
{
	if(confirm("确定提交此月考勤记录"))
		{
		  document.form2.submit();
		}
}

function baobiao()
{
	if (document.form1.kqdate.value == '') {
		alert("请选择考勤时间!");
		document.form1.kqdate.focus();
		return false;
	}
	document.form1.action = '<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!audit1.do';
	document.form1.target ='_blank';
	document.form1.submit();
}
</script>
	</head>
	<body>
		<form
			action="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!searchList.do"
			name="form1" method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">

					<tr>
						<th>
							姓名:
						</th>
						<td>
							<input type="text" name="name" id="name"
								value="${searchMap.name}" readonly="readonly" class="textBox" />
							<input type="text" name="p_id" id="p_id"
								value="${searchMap.p_id}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/hr/info/Hr_base_info!searchList.do?pageType=select','p_id','name');"
								value="选择" />
						</td>
						<th>
							时间区间:
						</th>
						<td>
							<input type="text" name="attend_date1"
								value="${searchMap.attend_date1}" onfocus="WdatePicker()"
								style="ime-mode: disabled" style="width:40%" class="textBox" />
							至
							<input type="text" name="attend_date2"
								value="${searchMap.attend_date2}" onfocus="WdatePicker()"
								style="ime-mode: disabled" style="width:40%" class="textBox" />
						</td>
					</tr>
					<tr>
						<th>
							考勤时间:
						</th>
						<td>
							<select name="kqdate" class="dropdownlist">
								<option value="">
									---
								</option>
								<c:forEach items="${datelist}" var="dat">
									<option value="${dat.kqdate}"
										<c:if test="${searchMap.kqdate==dat.kqdate}">selected</c:if>>
										${dat.kqdate}
									</option>
								</c:forEach>
							</select>
						</td>
						<th></th>
						<td>
						</td>
					</tr>

				</table>
			</div>
			<div class="div-button">
				<a style="cursor: hand;" target="_blank" 
					 onclick="baobiao();" 
					target="_blank" class="link">考勤报表</a>

				<a style="cursor: hand;"  onclick="tongji();" href="#" target="_self" class="link">考勤统计</a>

				<input type="reset" value="重置" class="button" />
				<input type="submit" value="查询" class="button" />
			</div>
			<div class="clear" style="height: 0px;"></div>
		</form>
		<div>
			<table class="dataListTable" width="100%">
			    <tr>
			      <td colspan="4">
			     <strong><font color="red">备注：仅列出您部门本月有未正常出勤人员信息，正常出勤的不予列出</font></strong>
			      </td>
			    </tr>
				<tr>
				    <th width="5%">
						序号
					</th>
					<th>
						姓名
					</th>
					<th>
						性别
					</th>
					<th>
						出生日期
					</th>
					<th class="btnCol">
						操作
					</th>
				</tr>
				<c:forEach items="${reslist}" var="result" varStatus="s">
					<tr>

                        <td>
							${s.index+1}
						</td>
						<td>
							<c:out value="${result.name}" />
						</td>
						<td>
							<c:if test="${result.sex=='0'}">男</c:if>
							<c:if test="${result.sex=='1'}">女</c:if>
						</td>
						<td>
							<c:out value="${result.birthday}" />
						</td>

						<td class="btnCol">
						
							<a
								href="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!audit1.do?p_id=${result.id}&kqdate=${searchMap.kqdate}"
								target="_blank">查看</a>
							
							<c:if test="${auditmap.state != '2'&&auditmap.state!='1'}">
								<a
									href="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!toedit.do?p_id=${result.id}&kqdate=${searchMap.kqdate}">考勤统计</a>
							</c:if>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" height="10">
					</td>
				</tr>

				<tr>
					<td colspan="4" height="10">
						<form name="form2" id="form2" method="post"
							action="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin!sub.do">
							<input type="hidden" name="kqdate" value="${searchMap.kqdate}" />
							<c:if test="${auditmap.state != '2'&&auditmap.state!='1'}">
								<input type="button" value="提交考勤记录" class="button"
									onclick="sub();" />
							</c:if>
						</form>
					</td>
				</tr>

				<tr>
					<td colspan="4" height="10">
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>