<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
<script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
<script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
</head>

<body>
<div style="display:none;position:absolute" id="divpic">
<table  align="center" border="1" cellpadding="0" cellspacing="0"><tr><td><img width="120" height="150" id="pic1"></td></tr></table></div>
<div style="display:none;position:absolute" id="divshenfen">
<table  align="center" border="1" cellpadding="0" cellspacing="0"><tr><td><img width="425" height="258" id="pic2"></td></tr></table></div>

<form action="" method="post" name="form1">
	<%@ page contentType="text/html;charset=GBK" %>

<table border="1" width="100%" class="table_border">
	<div class="divMod2">
	<div class="divMod1" align="center">${base_info.name}的基本信息</div>
	<tr>
		<th align="center" style="width:10%">姓名：</th>	
		<td width="35%" >
		${base_info.name}
		</td>
		<th align="center" style="width:10%">曾用名：</th>	
		<td width="35%" >
		${base_info.old_name}
		</td>
	</tr>
	<tr>
		<th align="center" style="width:10%">性别：</th>	
		<td width="35%" >
		${sex[base_info.sex]}
		</td>
		<th align="center" style="width:10%">兴趣：</th>	
		<td width="35%" >
		${base_info.habbit}
		</td>
	</tr>
	<tr>
		<th align="center" style="width:10%">民族：</th>	
		<td width="35%" >
		${base_info.nation}
		</td>
		<th align="center" style="width:10%">政治面貌：</th>	
		<td width="35%" >
		${zheng_zhi[base_info.zheng_zhi]}
		</td>
	</tr>
	<tr>
		<th align="center" style="width:10%">证件类型：</th>	
		<td width="35%" >
		${card_type[base_info.card_type]}
		</td>
		<th align="center" style="width:10%">证件号码：</th>	
		<td width="35%" >
		${base_info.card_num}
		</td>
	</tr>
	<tr>
		<th align="center" style="width:10%">应聘岗位：</th>	
		<td width="35%" >
		${base_info.position}
		</td>
		<th align="center" style="width:10%">部门：</th>	
		<td width="35%" >
		${base_info.dept_name}
		</td>
	</tr>
	<tr>
		<th align="center" style="width:10%">出生日期：</th>	
		<td width="35%" >
		${base_info.birthday}
		</td>
		<th align="center" style="width:10%">邮箱：</th>	
		<td width="35%" >
		${base_info.email}
		</td>
	</tr>
	<tr>
		<th align="center" style="width:10%">家庭电话：</th>	
		<td width="35%" >
		${base_info.home_numb}
		</td>
		<th align="center" style="width:10%">移动电话：</th>	
		<td width="35%" >
		${base_info.phone}
		</td>
	</tr>
	<tr>
		<th align="center" style="width:10%">婚姻状况：</th>	
		<td width="35%" >
		${marriage[base_info.marriage]}
		</td>
		<th align="center" style="width:10%">户口类型：</th>	
		<td width="35%" >
		${hu_kou[base_info.hu_kou]}
		</td>
	</tr>
	<tr>
		<th align="center" style="width:10%">户籍：</th>	
		<td width="35%" >
		${base_info.huji_name}
		</td>
		<th align="center" style="width:10%">地址：</th>	
		<td width="35%" >
		${base_info.addr}
		</td>
	</tr>
	<tr>
		<th align="center" style="width:10%">英语水平：</th>	
		<td width="35%" >
		${base_info.english}
		</td>
		<th align="center" style="width:10%">进入公司时间:</th>
		<td style="width:35%">${base_info.jr_time}</td>
	</tr>
	<tr>
        <th align="center" style="width:10%">证件照:</th>
		<td style="width:35%">
		<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${base_info.id}&folder=Hr_baseinfo&flag=staff_card_pic&mark=show')">
		<img src="<%=request.getContextPath()%>/image/picopen.png"/>
		</a>
		</td>
		<th align="center" style="width:10%">照片:</th>
		<td style="width:35%">
		<a href="#" onclick="openDGMax('上传附件','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${base_info.id}&folder=Hr_baseinfo&flag=staff_pic&mark=show')">
		<c:if test="${not empty base_info.pic}">
		<img src="<%=request.getContextPath()%>/photo/${base_info.pic}" width="120px" height="180px"/>
		</c:if>
		<c:if test="${empty base_info.pic}">
		<img src="<%=request.getContextPath()%>/image/picopen.png"/>
		</c:if>
		</a>
		</td>
    </tr>
	<tr>
		<th align="center" style="width:10%">备注:</th>
		<td style="width:35%" colspan="3">
		<textarea rows ="5" style="width:95%">${base_info.remark}</textarea>
		</td>
	</tr>
</table>
<br/>
<table border="1" width="100%" class="table_border">
	<div class="divMod2">
	</div>
	<!--
	<div align="center" class="div-button">
		<a href="<%=request.getContextPath()%>/hr/info/Hr_base_info!twhList.do" class="link" target="_parent"/>返回列表</a>
	</div>
	-->
</div>
</table>	
</form>

</body>
</html>