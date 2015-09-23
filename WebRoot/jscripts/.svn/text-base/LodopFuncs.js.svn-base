function getLodop(oOBJECT,oEMBED){
/**************************
  本函数根据浏览器类型决定采用哪个对象作为控件实例：
  IE系列、IE内核系列的浏览器采用oOBJECT，
  其它浏览器(Firefox系列、Chrome系列、Opera系列、Safari系列等)采用oEMBED。
**************************/
        var strHtml1="<br><font color='#FF00FF'>打印控件未安装!请到首页安装,安装后请刷新页面或重新进入。</font>";
        var strHtml2="<br><font color='#FF00FF'>打印控件需要升级!请到首页升级,升级后请重新进入。</font>";
        var strHtml3="<br><br><font color='#FF00FF'>注意：<br>1：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它;<br>2：如果浏览器表现出停滞不动等异常，建议关闭其“plugin-container”(网上搜关闭方法)功能;</font>";
        var LODOP=oEMBED;		
	try{		     
	     if (navigator.appVersion.indexOf("MSIE")>=0) LODOP=oOBJECT;

	     if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
		 if (navigator.userAgent.indexOf('Firefox')>=0)
  	         document.documentElement.innerHTML=strHtml3+document.documentElement.innerHTML;
		 if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml1); else
		 document.documentElement.innerHTML=strHtml1+document.documentElement.innerHTML;
		 return LODOP; 
	     } else if (LODOP.VERSION<"6.0.5.8") {
		 if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml2); else
		 document.documentElement.innerHTML=strHtml2+document.documentElement.innerHTML; 
		 return LODOP;
	     }
	     //*****如下空白位置适合调用统一功能:*********	     

		LODOP.SET_LICENSES("北京中电亿商网络技术有限责任公司", "653726081798577778794959892839", "", "");
	     //*******************************************
	     return LODOP; 
	}catch(err){
	     document.documentElement.innerHTML="Error:"+strHtml1+document.documentElement.innerHTML;
	     return LODOP; 
	}
}
var writeObject="<object id='LODOP_OB' classid='clsid:2105C259-1E0C-4534-8141-A753534CB4CA' width=0 height=0> "
	+"<embed id='LODOP_EM' TYPE='application/x-print-lodop' width=0 height=0 PLUGINSPAGE='install_lodop.exe'></embed>"
    +"</object> "
document.write(writeObject);


//声明为全局变量  
function SaveAsFile(printId,fileName){
    var LODOP;
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INIT("");
	LODOP.ADD_PRINT_TABLE(100,20,1200,80,document.getElementById(printId).innerHTML);
//		LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//快速生成（无表格样式,数据量较大时或许用到）
	LODOP.SAVE_TO_FILE(fileName);
};

function printFile(printId){
    var LODOP;
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INIT("");
	LODOP.ADD_PRINT_HTML(20,0,"100%","100%",document.getElementById(printId).innerHTML);
	//LODOP.PREVIEW();
	LODOP.PRINT();
//	LODOP.PRINT_INIT("");
//	LODOP.ADD_PRINT_TABLE(100,20,1200,80,document.getElementById(printId).innerHTML);
//		LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//快速生成（无表格样式,数据量较大时或许用到）
};