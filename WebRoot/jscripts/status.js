//����״̬����ʾ����
var boodschap = "����֧�֣��ٷ����ӿƼ����޹�˾���绰��0871-5630498-6713"; 
//����ҳ�������ʾ����
var titleWord="����ʡ���������ѯҵ������Ϣ��";
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