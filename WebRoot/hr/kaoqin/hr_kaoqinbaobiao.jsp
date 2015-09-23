<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>官房电子办公平台</title>
       <link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
        <script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
	</head>



	<body style="background-color: white; border: 0;">
	<div>
	 <c:if test="${imp=='yes'}">
	 <form action="<%=request.getContextPath()%>/hr/absence/Hr_kaoqin_audit!imp.do">
	  <input type="hidden" name="kqdate" value="${kqdate}">
	  <input type="hidden" name="dept_id" value="${dept_id}">
	  <input type="submit" name="btn" value="导出" class="button"/>
	  </form>
	  </c:if>
	</div>

		<table border="0" cellpadding="0" cellspacing="0" width="1005" class="dataListTable">
  <tr>
    <td colspan="35" width="1005" align="center" height="40"><font style="font-size: 14px;font-weight: bold;">考勤表<font color="red"><c:if test="${audit=='0'}">(未上报)</c:if><c:if test="${audit=='1'}">(待部门经理审核)</c:if><c:if test="${audit=='2'}">(审核通过)</c:if><c:if test="${audit=='3'}">(打回)</c:if></font></font></td>
    
  </tr>
  <tr>
    <td colspan="3" width="130" align="center" height="30"><font style="font-size: 14px;font-weight: bold;">部门：${branchname}&nbsp;&nbsp;&nbsp;</font></td>
    <td colspan="31" width="875"><font style="font-size: 14px;font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    ${nian }年&nbsp;&nbsp;&nbsp; ${yue }月&nbsp;&nbsp;&nbsp; 01日&mdash;&mdash;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${nian }年&nbsp;&nbsp;&nbsp; ${yue }月&nbsp;&nbsp;&nbsp;&nbsp; ${days }日</font></td>
    <td>　</td>
   
  </tr>
  <tr>
    <td rowspan="2" width="30" align="center" height="60">序号</td>
    <td rowspan="2" width="60" align="center">姓名</td>
    <td rowspan="2" width="40" align="center">日期</td>
    <td colspan="31" width="775" align="center">　</td>
    <td rowspan="2" width="100" align="center">备 注</td>
    
  </tr>
  <tr>
    <td height="30" width="25" align="center">1</td>
    <td width="25" align="center">2</td>
    <td width="25" align="center">3</td>
    <td width="25" align="center">4</td>
    <td width="25" align="center">5</td>
    <td width="25" align="center">6</td>
    <td width="25" align="center">7</td>
    <td width="25" align="center">8</td>
    <td width="25" align="center">9</td>
    <td width="25" align="center">10</td>
    <td width="25" align="center">11</td>
    <td width="25" align="center">12</td>
    <td width="25" align="center">13</td>
    <td width="25" align="center">14</td>
    <td width="25" align="center">15</td>
    <td width="25" align="center">16</td>
    <td width="25" align="center">17</td>
    <td width="25" align="center">18</td>
    <td width="25" align="center">19</td>
    <td width="25" align="center">20</td>
    <td width="25" align="center">21</td>
    <td width="25" align="center">22</td>
    <td width="25" align="center">23</td>
    <td width="25" align="center">24</td>
    <td width="25" align="center">25</td>
    <td width="25" align="center">26</td>
    <td width="25" align="center">27</td>
    <td width="25" align="center">28</td>
    <td width="25" align="center">29</td>
    <td width="25" align="center">30</td>
    <td width="25" align="center">31</td>
    
  </tr>
  
  <c:forEach items="${reslist}" var="record" varStatus="s" >
  <tr>
    <td rowspan="2" align="center">${s.index+1}</td>
    <td rowspan="2">${record.name }　</td>
    <td height="30" align="center">上午</td>
    <td title="${record.snote1}"><c:if test="${record.s1!='/'}"><font color="red" style="font-weight: bolder;">${record.s1}</font></c:if><c:if test="${record.s1=='/'}">${record.s1}</c:if></td>
    <td title="${record.snote2}"><c:if test="${record.s2!='/'}"><font color="red" style="font-weight: bolder;">${record.s2}</font></c:if><c:if test="${record.s2=='/'}">${record.s2}</c:if>　</td>
    <td title="${record.snote3}"><c:if test="${record.s3!='/'}"><font color="red" style="font-weight: bolder;">${record.s3}</font></c:if><c:if test="${record.s3=='/'}">${record.s3}</c:if>　</td>
    <td title="${record.snote4}"><c:if test="${record.s4!='/'}"><font color="red" style="font-weight: bolder;">${record.s4}</font></c:if><c:if test="${record.s4=='/'}">${record.s4}</c:if>　</td>
    <td title="${record.snote5}"><c:if test="${record.s5!='/'}"><font color="red" style="font-weight: bolder;">${record.s5}</font></c:if><c:if test="${record.s5=='/'}">${record.s5}</c:if>　</td>
    <td title="${record.snote6}"><c:if test="${record.s6!='/'}"><font color="red" style="font-weight: bolder;">${record.s6}</font></c:if><c:if test="${record.s6=='/'}">${record.s6}</c:if></td>
    <td title="${record.snote7}"><c:if test="${record.s7!='/'}"><font color="red" style="font-weight: bolder;">${record.s7}</font></c:if><c:if test="${record.s7=='/'}">${record.s7}</c:if>　</td>
    <td title="${record.snote8}"><c:if test="${record.s8!='/'}"><font color="red" style="font-weight: bolder;">${record.s8}</font></c:if><c:if test="${record.s8=='/'}">${record.s8}</c:if>　</td>
    <td title="${record.snote9}"><c:if test="${record.s9!='/'}"><font color="red" style="font-weight: bolder;">${record.s9}</font></c:if><c:if test="${record.s9=='/'}">${record.s9}</c:if>　</td>
    <td title="${record.snote10}"><c:if test="${record.s10!='/'}"><font color="red" style="font-weight: bolder;">${record.s10}</font></c:if><c:if test="${record.s10=='/'}">${record.s10}</c:if>　</td>
    <td title="${record.snote11}"><c:if test="${record.s11!='/'}"><font color="red" style="font-weight: bolder;">${record.s11}</font></c:if><c:if test="${record.s11=='/'}">${record.s11}</c:if>　</td>
    <td title="${record.snote12}"><c:if test="${record.s12!='/'}"><font color="red" style="font-weight: bolder;">${record.s12}</font></c:if><c:if test="${record.s12=='/'}">${record.s12}</c:if>　</td>
    <td title="${record.snote13}"><c:if test="${record.s13!='/'}"><font color="red" style="font-weight: bolder;">${record.s13}</font></c:if><c:if test="${record.s13=='/'}">${record.s13}</c:if>　</td>
    <td title="${record.snote14}"><c:if test="${record.s14!='/'}"><font color="red" style="font-weight: bolder;">${record.s14}</font></c:if><c:if test="${record.s14=='/'}">${record.s14}</c:if>　</td>
    <td title="${record.snote15}"><c:if test="${record.s15!='/'}"><font color="red" style="font-weight: bolder;">${record.s15}</font></c:if><c:if test="${record.s15=='/'}">${record.s15}</c:if>　</td>
    <td title="${record.snote16}"><c:if test="${record.s16!='/'}"><font color="red" style="font-weight: bolder;">${record.s16}</font></c:if><c:if test="${record.s16=='/'}">${record.s16}</c:if>　</td>
    <td title="${record.snote17}"><c:if test="${record.s17!='/'}"><font color="red" style="font-weight: bolder;">${record.s17}</font></c:if><c:if test="${record.s17=='/'}">${record.s17}</c:if>　</td>
    <td title="${record.snote18}"><c:if test="${record.s18!='/'}"><font color="red" style="font-weight: bolder;">${record.s18}</font></c:if><c:if test="${record.s18=='/'}">${record.s18}</c:if>　</td>
    <td title="${record.snote19}"><c:if test="${record.s19!='/'}"><font color="red" style="font-weight: bolder;">${record.s19}</font></c:if><c:if test="${record.s19=='/'}">${record.s19}</c:if>　</td>
    <td title="${record.snote20}"><c:if test="${record.s20!='/'}"><font color="red" style="font-weight: bolder;">${record.s20}</font></c:if><c:if test="${record.s20=='/'}">${record.s20}</c:if>　</td>
    <td title="${record.snote21}"><c:if test="${record.s21!='/'}"><font color="red" style="font-weight: bolder;">${record.s21}</font></c:if><c:if test="${record.s21=='/'}">${record.s21}</c:if>　</td>
    <td title="${record.snote22}"><c:if test="${record.s22!='/'}"><font color="red" style="font-weight: bolder;">${record.s22}</font></c:if><c:if test="${record.s22=='/'}">${record.s22}</c:if>　</td>
    <td title="${record.snote23}"><c:if test="${record.s23!='/'}"><font color="red" style="font-weight: bolder;">${record.s23}</font></c:if><c:if test="${record.s23=='/'}">${record.s23}</c:if>　</td>
    <td title="${record.snote24}"><c:if test="${record.s24!='/'}"><font color="red" style="font-weight: bolder;">${record.s24}</font></c:if><c:if test="${record.s24=='/'}">${record.s24}</c:if>　</td>
    <td title="${record.snote25}"><c:if test="${record.s25!='/'}"><font color="red" style="font-weight: bolder;">${record.s25}</font></c:if><c:if test="${record.s25=='/'}">${record.s25}</c:if>　</td>
    <td title="${record.snote26}"><c:if test="${record.s26!='/'}"><font color="red" style="font-weight: bolder;">${record.s26}</font></c:if><c:if test="${record.s26=='/'}">${record.s26}</c:if>　</td>
    <td title="${record.snote27}"><c:if test="${record.s27!='/'}"><font color="red" style="font-weight: bolder;">${record.s27}</font></c:if><c:if test="${record.s27=='/'}">${record.s27}</c:if>　</td>
    <td title="${record.snote28}"><c:if test="${record.s28!='/'}"><font color="red" style="font-weight: bolder;">${record.s28}</font></c:if><c:if test="${record.s28=='/'}">${record.s28}</c:if>　</td>
    <td title="${record.snote29}"><c:if test="${record.s29!='/'}"><font color="red" style="font-weight: bolder;">${record.s29}</font></c:if><c:if test="${record.s29=='/'}">${record.s29}</c:if>　</td>
    <td title="${record.snote30}"><c:if test="${record.s30!='/'}"><font color="red" style="font-weight: bolder;">${record.s30}</font></c:if><c:if test="${record.s30=='/'}">${record.s30}</c:if>　</td>
    <td title="${record.snote31}"><c:if test="${record.s31!='/'}"><font color="red" style="font-weight: bolder;">${record.s31}</font></c:if><c:if test="${record.s31=='/'}">${record.s31}</c:if>　</td>
    <td rowspan="2" width="54">　</td>
    
  </tr>
  <tr>
    <td align="center" height="30">下午</td>
    <td title="${record.xnote1}"><c:if test="${record.x1!='/'}"><font color="red" style="font-weight: bolder;">${record.x1}</font></c:if><c:if test="${record.x1=='/'}">${record.x1}</c:if></td>
    <td title="${record.xnote2}"><c:if test="${record.x2!='/'}"><font color="red" style="font-weight: bolder;">${record.x2}</font></c:if><c:if test="${record.x2=='/'}">${record.x2}</c:if>　</td>
    <td title="${record.xnote3}"><c:if test="${record.x3!='/'}"><font color="red" style="font-weight: bolder;">${record.x3}</font></c:if><c:if test="${record.x3=='/'}">${record.x3}</c:if>　</td>
    <td title="${record.xnote4}"><c:if test="${record.x4!='/'}"><font color="red" style="font-weight: bolder;">${record.x4}</font></c:if><c:if test="${record.x4=='/'}">${record.x4}</c:if>　</td>
    <td title="${record.xnote5}"><c:if test="${record.x5!='/'}"><font color="red" style="font-weight: bolder;">${record.x5}</font></c:if><c:if test="${record.x5=='/'}">${record.x5}</c:if>　</td>
    <td title="${record.xnote6}"><c:if test="${record.x6!='/'}"><font color="red" style="font-weight: bolder;">${record.x6}</font></c:if><c:if test="${record.x6=='/'}">${record.x6}</c:if></td>
    <td title="${record.xnote7}"><c:if test="${record.x7!='/'}"><font color="red" style="font-weight: bolder;">${record.x7}</font></c:if><c:if test="${record.x7=='/'}">${record.x7}</c:if>　</td>
    <td title="${record.xnote8}"><c:if test="${record.x8!='/'}"><font color="red" style="font-weight: bolder;">${record.x8}</font></c:if><c:if test="${record.x8=='/'}">${record.x8}</c:if>　</td>
    <td title="${record.xnote9}"><c:if test="${record.x9!='/'}"><font color="red" style="font-weight: bolder;">${record.x9}</font></c:if><c:if test="${record.x9=='/'}">${record.x9}</c:if>　</td>
    <td title="${record.xnote10}"><c:if test="${record.x10!='/'}"><font color="red" style="font-weight: bolder;">${record.x10}</font></c:if><c:if test="${record.x10=='/'}">${record.x10}</c:if>　</td>
    <td title="${record.xnote11}"><c:if test="${record.x11!='/'}"><font color="red" style="font-weight: bolder;">${record.x11}</font></c:if><c:if test="${record.x11=='/'}">${record.x11}</c:if>　</td>
    <td title="${record.xnote12}"><c:if test="${record.x12!='/'}"><font color="red" style="font-weight: bolder;">${record.x12}</font></c:if><c:if test="${record.x12=='/'}">${record.x12}</c:if>　</td>
    <td title="${record.xnote13}"><c:if test="${record.x13!='/'}"><font color="red" style="font-weight: bolder;">${record.x13}</font></c:if><c:if test="${record.x13=='/'}">${record.x13}</c:if>　</td>
    <td title="${record.xnote14}"><c:if test="${record.x14!='/'}"><font color="red" style="font-weight: bolder;">${record.x14}</font></c:if><c:if test="${record.x14=='/'}">${record.x14}</c:if>　</td>
    <td title="${record.xnote15}"><c:if test="${record.x15!='/'}"><font color="red" style="font-weight: bolder;">${record.x15}</font></c:if><c:if test="${record.x15=='/'}">${record.x15}</c:if>　</td>
    <td title="${record.xnote16}"><c:if test="${record.x16!='/'}"><font color="red" style="font-weight: bolder;">${record.x16}</font></c:if><c:if test="${record.x16=='/'}">${record.x16}</c:if>　</td>
    <td title="${record.xnote17}"><c:if test="${record.x17!='/'}"><font color="red" style="font-weight: bolder;">${record.x17}</font></c:if><c:if test="${record.x17=='/'}">${record.x17}</c:if>　</td>
    <td title="${record.xnote18}"><c:if test="${record.x18!='/'}"><font color="red" style="font-weight: bolder;">${record.x18}</font></c:if><c:if test="${record.x18=='/'}">${record.x18}</c:if>　</td>
    <td title="${record.xnote19}"><c:if test="${record.x19!='/'}"><font color="red" style="font-weight: bolder;">${record.x19}</font></c:if><c:if test="${record.x19=='/'}">${record.x19}</c:if>　</td>
    <td title="${record.xnote20}"><c:if test="${record.x20!='/'}"><font color="red" style="font-weight: bolder;">${record.x20}</font></c:if><c:if test="${record.x20=='/'}">${record.x20}</c:if>　</td>
    <td title="${record.xnote21}"><c:if test="${record.x21!='/'}"><font color="red" style="font-weight: bolder;">${record.x21}</font></c:if><c:if test="${record.x21=='/'}">${record.x21}</c:if>　</td>
    <td title="${record.xnote22}"><c:if test="${record.x22!='/'}"><font color="red" style="font-weight: bolder;">${record.x22}</font></c:if><c:if test="${record.x22=='/'}">${record.x22}</c:if>　</td>
    <td title="${record.xnote23}"><c:if test="${record.x23!='/'}"><font color="red" style="font-weight: bolder;">${record.x23}</font></c:if><c:if test="${record.x23=='/'}">${record.x23}</c:if>　</td>
    <td title="${record.xnote24}"><c:if test="${record.x24!='/'}"><font color="red" style="font-weight: bolder;">${record.x24}</font></c:if><c:if test="${record.x24=='/'}">${record.x24}</c:if>　</td>
    <td title="${record.xnote25}"><c:if test="${record.x25!='/'}"><font color="red" style="font-weight: bolder;">${record.x25}</font></c:if><c:if test="${record.x25=='/'}">${record.x25}</c:if>　</td>
    <td title="${record.xnote26}"><c:if test="${record.x26!='/'}"><font color="red" style="font-weight: bolder;">${record.x26}</font></c:if><c:if test="${record.x26=='/'}">${record.x26}</c:if>　</td>
    <td title="${record.xnote27}"><c:if test="${record.x27!='/'}"><font color="red" style="font-weight: bolder;">${record.x27}</font></c:if><c:if test="${record.x27=='/'}">${record.x27}</c:if>　</td>
    <td title="${record.xnote28}"><c:if test="${record.x28!='/'}"><font color="red" style="font-weight: bolder;">${record.x28}</font></c:if><c:if test="${record.x28=='/'}">${record.x28}</c:if>　</td>
    <td title="${record.xnote29}"><c:if test="${record.x29!='/'}"><font color="red" style="font-weight: bolder;">${record.x29}</font></c:if><c:if test="${record.x29=='/'}">${record.x29}</c:if>　</td>
    <td title="${record.xnote30}"><c:if test="${record.x30!='/'}"><font color="red" style="font-weight: bolder;">${record.x30}</font></c:if><c:if test="${record.x30=='/'}">${record.x30}</c:if>　</td>
    <td title="${record.xnote31}"><c:if test="${record.x31!='/'}"><font color="red" style="font-weight: bolder;">${record.x31}</font></c:if><c:if test="${record.x31=='/'}">${record.x31}</c:if>　</td>
    
  </tr>
  </c:forEach>
  <tr>
    <td height="30">　</td>
    <td colspan="34" width="1005" align="left"><font style="font-size: 14px;font-weight: bold;">符号代表：出勤：/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 旷工：&times;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 病假：○&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 公差：&radic;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 事假：+&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 培训：◇&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;带薪假：¤&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;迟到：-</font></td>
    
  </tr>
  
  <tr >
    <td colspan="35" width="1005" height="30">
    </td>
  </tr>
  
  
  <tr style="display: none;">
    <td colspan="35" width="1005" height="30">
    &nbsp;&nbsp;&nbsp;    部门负责人审核：<c:if test="${zhuangt==2}">${deptmanage}</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    考勤员：${kqname}
    </td>
    
  </tr>
  <tr style="display: none;">
    <td align="right" colspan="35" width="1005" height="30"><div align="right">&nbsp;&nbsp;&nbsp;制表：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    审核：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审定：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
    
  </tr>
 
</table>
	
	</body>
</html>
