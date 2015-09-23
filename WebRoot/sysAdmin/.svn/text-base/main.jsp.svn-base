<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>官房电子办公平台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link href="../jscripts/ligerUI/skins/Aqua/css/ligerui-all.css"
			rel="stylesheet" type="text/css" />
		<script src="../jscripts/jquery.js" type="text/javascript">
</script>
		<script src="../jscripts/ligerUI/js/ligerui.min.js"
			type="text/javascript">
</script>
		<script src="../jscripts/ligerUI/js/plugins/ligerTab.js"
			type="text/javascript">
</script>
		<script type="text/javascript">
var tab = null;
var accordion = null;
var tree = null;
var funTabid="home";
$(function() {
	//布局
	$("#layout1").ligerLayout( {
		leftWidth : 190,
		height : '100%',
		onHeightChanged : f_heightChanged
	});

	var height = $(".l-layout-center").height();

	//Tab
	$("#framecenter").ligerTab( {
		onBeforeSelectTabItem: function (tabid)
        {
        	if(tabid==funTabid)
            $("#framecenter").ligerGetTabManager().reload(tabid)
        },
        onAfterRemoveTabItem: function (tabid)
        {
        	$("#framecenter").ligerGetTabManager().reload(funTabid)
        },
		height : height
	});

	//面板
	$("#accordion1").ligerAccordion( {
		height : height - 24,
		speed : null
	});

	//树
	$(".funlist").ligerTree( {
		checkbox : false,
		slide : false,
		nodeWidth : 120,
		attribute : [ 'nodename', 'url' ],
		onClick : function(node) {
			if (!node.data.url)
				return;
			var tabid = $(node.target).attr("tabid");
			if (!tabid) {
				tabid = new Date().getTime();
				$(node.target).attr("tabid", tabid)
			}
			//f_overrideTab(tabid, node.data.text, node.data.url);
			f_addTab(tabid, node.data.text, node.data.url);
	}
	});

	tab = $("#framecenter").ligerGetTabManager();
	accordion = $("#accordion1").ligerGetAccordionManager();
	tree = $("#funtree").ligerGetTreeManager();
	$("#pageloading").hide();
	//tree.collapseAll();   
	f_overrideTab('home','我的主页','<%=request.getContextPath() %>/sysAdmin/Login!getNotice.do');             
});
function f_heightChanged(options) {
	if (tab)
		tab.addHeight(options.diff);
	if (accordion && options.middleHeight - 24 > 0)
		accordion.setHeight(options.middleHeight - 24);
}
function f_addTab(tabid, text, url) {
	tab.addTabItem( {
		tabid : tabid,
		text : text,
		url : url
	});
}

function f_overrideTab(tabid, text, url) {
	if(tab.isTabItemExist(funTabid)==false){
		f_addTab(funTabid, text, url);
	}
	
	tab.selectTabItem(funTabid);
	//var currentTabid = tab.getSelectedTabItemID();
	var newid = new Date().getTime();
	tab.overrideTabItem(funTabid, {tabid : newid,url : url,text : text});
	funTabid=newid;
	//tab.overrideSelectedTabItem({ url: value });
}
</script>
		<style type="text/css">

body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}

#pageloading {
	position: absolute;
	left: 0px;
	top: 0px;
	background: white url('loading.gif') no-repeat center;
	width: 100%;
	height: 100%;
	z-index: 0;
}

.l-link {
	display: block;
	line-height: 22px;
	height: 22px;
	padding-left: 20px;
	margin: 4px;
	background-color: #eee;
	border: windowtext 1pt solid;
	TEXT-DECORATION: none;
	cursor: pointer
}

.l-link-over {
	background: #FFEEAC;
	border: 1px solid #DB9F00;
}

.btn_top {
	BORDER: #2C59AA 1px solid;
	PADDING: 2px;
	FONT-SIZE: 12px;
	width: 60px;
	TEXT-DECORATION: none;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
	background: #C3DAF5;
	CURSOR: pointer;
	COLOR: black;
	position: absolute;
	text-align: center;
}

.funlist {
	
}

a{
	cursor:pointer;
	line-height: 23px;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: #0000EE;	
	text-decoration: none;	
}

a:active {
	color: black;
	text-decoration: none;
}
.news{
	width:100%;
	height:200px;
}
.news li{
	line-height:28px;
	margin:1px auto;	
	width:49%; 
	float:left; 
	display:block;
}
</style>

	</head>
	<body style="padding: 0px; overflow-y: scroll;">

		<div id="pageloading"></div>
		<div id="layout1" style="width: 99.6%">
			<div position="top"
				style="background: #01b5ea url(../image/banner.jpg) no-repeat; color: White; height: 100px;">
				<div style="margin-top: 10px; margin-left: 10px">
					<span 
						style="font-size: 14px; font-weight: bold; position: absolute; right: 10px; top: 2px;"></span>
					<a onclick="f_overrideTab('0000','修改密码','<%=request.getContextPath()%>/sysAdmin/Login!modifyPass.do')"
						class="btn_top" style="right: 90px; bottom: 50px;">修改密码</a>
					<a href="<%=request.getContextPath()%>/sysAdmin/Login!logout.do"
						class="btn_top" style="right: 10px; bottom: 50px;">安全退出</a>
				</div>
			</div>

			<div position="left" title="功能菜单" id="accordion1">
				${funcDicList}
			</div>

			<div position="center" id="framecenter" >

				<div tabid="home" title="我的主页">
					
				</div>
				<!-- <div tabid="home1" title="业务功能" style="height: 300px"></div> -->
			</div>

		</div>
	</body>
</html>
