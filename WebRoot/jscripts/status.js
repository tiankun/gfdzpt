//设置状态栏显示文字
var boodschap = "技术支持：官房电子科技有限公司；电话：0871-5630498-6713"; 
//设置页面标题显示文字
var titleWord="云南省勘察设计咨询业管理信息网";
function dgstatus(){
	window.status = boodschap;
	clearTimeout();
	timerID= setTimeout("dgstatus();", 500);
}
dgstatus();
function gowin(url){
	window.open(url, 'main');
}

function gosubwin(gourl){
	location=gourl;
}

function checkSpance(str){
	var re = new RegExp("["+ str +"]");
	if(re.test(' ')){
		return false;
	}
	return true;
}
document.title=titleWord;