<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
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
<script type="text/javascript" src="../../jscripts/tabMenu/tabMenu.js"></script>
<link rel="stylesheet" href="../../jscripts/tabMenu/tabMenu.css" type="text/css"></link>
</head>

<body>
	<!-- 人员基本信息 -->
	<div id="tabMenu" >
		<ul id="TabPage1" style="left: 0px; top: 0px">
			<c:if test="${showFlag=='show'}"><!-- 查看时显示 -->
			<li id="Tab1" class="Selected" onclick="javascript:switchTab('TabPage1','Tab1');">基本信息</li>
			</c:if>
			<li id="Tab2" onclick="javascript:switchTab('TabPage1','Tab2');">家庭信息</li>
			<li id="Tab3" onclick="javascript:switchTab('TabPage1','Tab3');">学历信息</li>
			<li id="Tab4" onclick="javascript:switchTab('TabPage1','Tab4');">工作履历</li>
			<c:if test="${hr_type!=0}">
			 <li id="Tab5" onclick="javascript:switchTab('TabPage1','Tab5');">人员评价</li>
			</c:if>
			<c:if test="${!(hr_type==0||hr_type==1||hr_type==2)}">
			<li id="Tab6" onclick="javascript:switchTab('TabPage1','Tab6');">转正考评</li>
			<li id="Tab7" onclick="javascript:switchTab('TabPage1','Tab7');">证书信息</li>
			<li id="Tab8" onclick="javascript:switchTab('TabPage1','Tab8');">奖惩信息</li>
			<li id="Tab9" onclick="javascript:switchTab('TabPage1','Tab9');">合同信息</li>
			<li id="Tab10" onclick="javascript:switchTab('TabPage1','Tab10');">内部变动</li>
			</c:if>
		</ul>
		<div id="cnt">
			<div id="dTab1" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/info/Hr_base_info!view.do?id=${id}&flag=staff' frameborder=0></iframe>
			</div>
			<div id="dTab2" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/info/Hr_family!searchList.do?id=${id}&showFlag=${showFlag}' frameborder=0></iframe>
			</div>
			<div id="dTab3" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/info/Hr_degree!searchList.do?id=${id}&showFlag=${showFlag}' frameborder=0></iframe>
			</div>
			<div id="dTab4" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/info/Hr_resume!searchList.do?id=${id}&showFlag=${showFlag}' frameborder=0></iframe>
			</div>
			<c:if test="${hr_type!=0}">
			<div id="dTab5" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/process/Hr_test!searchById.do?id=${id}&showFlag=${showFlag}' frameborder=0></iframe>
			</div>
			</c:if>
			<c:if test="${!(hr_type==0||hr_type==1||hr_type==2)}">
			<div id="dTab6" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/process/Hr_evaluation!searchList.do?id=${id}&showFlag=${showFlag}' frameborder=0></iframe>
			</div>
			<div id="dTab7" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/info/Hr_certificate!searchList.do?id=${id}&showFlag=${showFlag}' frameborder=0></iframe>
			</div>
			<div id="dTab8" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/info/Hr_reward!searchList.do?id=${id}&showFlag=${showFlag}' frameborder=0></iframe>
			</div>
			<div id="dTab9" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/info/Hr_compact!searchList.do?id=${id}&showFlag=${showFlag}' frameborder=0></iframe>
			</div>
			<div id="dTab10" class="Box">
			<iframe id="allList" name="allList" style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT:100%" src='<%=request.getContextPath()%>/hr/info/Hr_pchange!searchList.do?id=${id}&showFlag=${showFlag}' frameborder=0></iframe>
			</div>
			</c:if>
		</div>
	</div>
</br>
<div style="height: 60px" align="center" class="div-button">
<c:if test="${!(flag=='hr_apply'||flag=='hr_dimission')}"><input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/hr/info/Hr_base_info!${flag}List.do'" value="返回列表" class="button"/></c:if>
<c:if test="${flag=='hr_apply'}"><input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/hr/process/Hr_apply!searchList.do'" value="返回列表" class="button"/></c:if>
<c:if test="${flag=='hr_dimission'}"><input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/hr/process/Hr_dimission!searchList.do'" value="返回列表" class="button"/></c:if>	
</div>
</body>

</html>

