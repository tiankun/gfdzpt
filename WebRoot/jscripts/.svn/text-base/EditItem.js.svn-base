// JavaScript Document

function openDG(Title, url,W,H) {
    var dg = new $.dialog({ title: Title, lock: true, width: W, height: H, content: 'url:' + url}); 
    if(W=="" && H=="")
    dg.max();
   //dg.ShowDialog();
}
function openDGMax(Title, url) {
	var dg = new $.dialog({ title: Title, lock: true, content: 'url:' + url}); 
	dg.max();
}
function openDG(Title, url) {
	var dg = new $.dialog({ title: Title, lock: true, width: "1000px", height: "500px", content: 'url:' + url}); 
}

function openDG(Title, url,_id) {
	var dg = new $.dialog({ title: Title, lock: true, width: "1000px", height: "500px", content: 'url:' + url,id:_id});
	if($('#dgid').length>0)//if(document.getElementById("dgid"))
	{
		document.getElementById("dgid").value = _id;
	}else
	{
		document.getElementById('PageUpDnDiv').insertAdjacentHTML("beforeEnd",
		        "<input type=\"text\" id=\"dgid\" value=\""+_id+"\" style=\"display:none\"/>");
	}
}

function closeDG(){
	var api = frameElement.api;
	api.close();
}

function closeDG_refreshW(){
	var api = frameElement.api, W = api.opener;
	alert("保存成功。");
	//var p_url = W.location.href;
    //p_url = p_url.replace('#', '');
    //W.location.replace = p_url;
	//W.location.reload();
	W.changePage();
 	api.close();
 	
}

function openChild(_id,_url){
	var api = frameElement.api, W = api.opener;
	var childDG = W.$.dialog({ id:_id, title:'选择数据',content:'url:' + _url,width: '800px', height: '500px'});
}

function openSelect(_id,_url,_rk,_rv){
	var api = frameElement.api;
	if(api)
	{
		W = api.opener;
		var childDG = W.$.dialog({ id:_id, title:'选择数据',content:'url:' + _url,width: '800px', height: '500px'});
	
		if(W.document.getElementById("rk"))
		{
			W.document.getElementById("rk").value = _rk;
		}else
		{
			W.document.getElementById('PageUpDnDiv').insertAdjacentHTML("beforeEnd",
		        "<input type=\"text\" id=\"rk\" value=\""+_rk+"\" style=\"display:none\"/>");
		}
		if(W.document.getElementById("rv"))
		{
			W.document.getElementById("rv").value = _rv;
		}else
		{
			W.document.getElementById('PageUpDnDiv').insertAdjacentHTML("beforeEnd",
			        "<input type=\"text\" id=\"rv\" value=\""+_rv+"\" style=\"display:none\"/>");
		}	
	}else
	{
		openDG("选择数据", _url);
		if(document.getElementById("rk"))
		{
			document.getElementById("rk").value = _rk;
		}else
		{
			document.getElementById('PageUpDnDiv').insertAdjacentHTML("beforeEnd",
		        "<input type=\"text\" id=\"rk\" value=\""+_rk+"\" style=\"display:none\"/>");
		}
		if(document.getElementById("rv"))
		{
			document.getElementById("rv").value = _rv;
		}else
		{
			document.getElementById('PageUpDnDiv').insertAdjacentHTML("beforeEnd",
			        "<input type=\"text\" id=\"rv\" value=\""+_rv+"\" style=\"display:none\"/>");
		}
	}
}

function retValue(_key,_value,_aid,_recKey,_recValue)
{
	var api = frameElement.api, W = api.opener;
	var dtl = api.get(_aid);
	var sv,sK;
	if( dtl){
		sv =dtl.document.getElementById(_recValue);
		if(sv) sv.value = _value;
		sk = dtl.document.getElementById(_recKey);
		if(sk) sk.value = _key;
	    //api.get('userDtl').BB();
	    //W.document.getElementById('txt5').value;
	    api.close();
	}else{
		sv =W.document.getElementById(_recValue);
		if(sv) sv.value = _value;
		sk = W.document.getElementById(_recKey);
		if(sk) sk.value = _key;	
		api.close();
	}	
}

function retSelect(_key,_value,handler)
{
	var api = frameElement.api, W = api.opener;
	var dgid = W.document.getElementById("dgid");
	var rk = W.document.getElementById("rk");
	var rv = W.document.getElementById("rv");
	var sv,sK;
	if( api.get(dgid.value)){
		var dtl = api.get(dgid.value);
		sv =dtl.document.getElementById(rv.value);
		if(sv) sv.value = _value;
		sk = dtl.document.getElementById(rk.value);
		if(sk) sk.value = _key;
	    api.close();
	}else{
		sv =W.document.getElementById(rv.value);
		if(sv) sv.value = _value;
		sk = W.document.getElementById(rk.value);
		if(sk) sk.value = _key;	
		api.close();
	}
	if(handler){
		eval(handler);
	}
}

function createInput(type,id,value)
{
	var el = document.createElement(type);
	el.type=type;
	el.name=id;
	el.value = value;
	return el;
}

	function request(paras)
    { 
        var url = location.href; 
        var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
        var paraObj = {} 
        for (i=0; j=paraString[i]; i++){ 
        paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
        } 
        var returnValue = paraObj[paras.toLowerCase()]; 
        if(typeof(returnValue)=="undefined"){ 
        return ""; 
        }else{ 
        return returnValue; 
        }
}

