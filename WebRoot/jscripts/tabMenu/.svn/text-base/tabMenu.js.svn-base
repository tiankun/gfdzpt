function switchTab(tabpage,selObj){
var oItem = document.getElementById(tabpage).getElementsByTagName("li"); 
    for(var i=0; i<oItem.length; i++){
        var x = oItem[i];    
        x.className = "";
    }
    document.getElementById(selObj).className = "Selected"; 
	var dvs=document.getElementById("cnt").getElementsByTagName("div");
	for (var i=0;i<dvs.length;i++){
	    dvs[i].id.indexOf("dTab")> -1 ;
	    dvs[i].style.display = "none";
	}
	document.getElementById('d'+selObj).style.display = "block";
}