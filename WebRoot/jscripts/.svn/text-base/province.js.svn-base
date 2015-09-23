// 根据省选市
function Jtrim(str)
{

        var i = 0;
        var len = str.length;
        if ( str == "" ) return( str );
        j = len -1;
        flagbegin = true;
        flagend = true;
        while (( flagbegin == true) && (i< len))
        {
           if ( str.charAt(i) == " " )
                {
                  i=i+1;
                  flagbegin=true;
                }
                else
                {
                        flagbegin=false;
                }
        }

        while  ((flagend== true) && (j>=0))
        {
            if (str.charAt(j)==" ")
                {
                        j=j-1;
                        flagend=true;
                }
                else
                {
                        flagend=false;
                }
        }

        if ( i > j ) return ("");

        trimstr = str.substring(i,j+1);
        return trimstr;
}
function selectprovince(ee)
 {
    if(ee=="安徽") 
	  {
	       str='<select name="city" id="city" >';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="合肥" >合肥</option>';
              str=str+'<option value="滁州">滁州</option>';
              str=str+'<option value="蚌埠">蚌埠</option>';
              str=str+'<option value="阜阳">阜阳</option>';
              str=str+'<option value="芜湖">芜湖</option>';
              str=str+'<option value="淮南">淮南</option>';
              str=str+'<option value="马鞍山">马鞍山</option>';
              str=str+'<option value="安庆">安庆</option>';
              str=str+'<option value="宿州">宿州</option>';
              str=str+'<option value="屯溪">屯溪</option>';
              str=str+'<option value="毫州">毫州</option>';
			  str=str+'<option value="淮北">淮北</option>';
			  str=str+'<option value="巢湖">巢湖</option>';
			  str=str+'<option value="黄山">黄山</option>';
			  str=str+'<option value="六安">六安</option>';
			  str=str+'<option value="铜陵">铜陵</option>';
			  str=str+'<option value="池州">池州</option>';
			  str=str+'<option value="宣城">宣城</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="北京") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="北京" selected>北京</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="福建") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="福州" >福州</option>';
              str=str+'<option value="南平">南平</option>';
              str=str+'<option value="厦门">厦门</option>';
              str=str+'<option value="宁德">宁德</option>';
              str=str+'<option value="莆田">莆田</option>';
              str=str+'<option value="泉州">泉州</option>';
              str=str+'<option value="漳州">漳州</option>';
              str=str+'<option value="龙岩">龙岩</option>';
              str=str+'<option value="三明">三明</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="甘肃") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="兰州" >兰州</option>';
              str=str+'<option value="嘉峪关">嘉峪关</option>';
              str=str+'<option value="敦煌">敦煌</option>';
              str=str+'<option value="金昌">金昌</option>';
              str=str+'<option value="白银">白银</option>';
              str=str+'<option value="天水">天水</option>';
              str=str+'<option value="酒泉">酒泉</option>';
              str=str+'<option value="张掖">张掖</option>';
              str=str+'<option value="武威">武威</option>';
              str=str+'<option value="庆阳">庆阳</option>';
			  str=str+'<option value="平凉">平凉</option>';
			  str=str+'<option value="定西">定西</option>';
			  str=str+'<option value="陇南">陇南</option>';
			  str=str+'<option value="临夏">临夏</option>';
			  str=str+'<option value="甘南">甘南</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="广东") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="广州">广州</option>';
              str=str+'<option value="清远">清远</option>';
              str=str+'<option value="韶关">韶关</option>';
              str=str+'<option value="河源">河源</option>';
              str=str+'<option value="梅州">梅州</option>';
              str=str+'<option value="潮州">潮州</option>';
              str=str+'<option value="汕头">汕头</option>';
              str=str+'<option value="揭阳">揭阳</option>';
              str=str+'<option value="汕尾">汕尾</option>';
              str=str+'<option value="惠州">惠州</option>';
			  str=str+'<option value="东莞">东莞</option>';
			  str=str+'<option value="深圳">深圳</option>';
			  str=str+'<option value="珠海">珠海</option>';
			  str=str+'<option value="中山">中山</option>';
			  str=str+'<option value="江门">江门</option>';
			  str=str+'<option value="佛山">佛山</option>';
			  str=str+'<option value="肇庆">肇庆</option>';
			  str=str+'<option value="云浮">云浮</option>';
			  str=str+'<option value="阳江">阳江</option>';
			  str=str+'<option value="茂名">茂名</option>';
			  str=str+'<option value="湛江">湛江</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="广西") 
	  {
			str='<select name="city" id="city">';
			str=str+'<option value="南宁">南宁</option>';
			str=str+'<option value="防城港">防城港</option>';
			str=str+'<option value="柳州">柳州</option>';
			str=str+'<option value="桂林">桂林</option>';
			str=str+'<option value="梧州">梧州</option>';
			str=str+'<option value="贺州地区">贺州地区</option>';
			str=str+'<option value="玉林">玉林</option>';
			str=str+'<option value="贵港">贵港</option>';
			str=str+'<option value="百色地区">百色地区</option>';
			str=str+'<option value="钦州">钦州</option>';
			str=str+'<option value="河池地区">河池地区</option>';
			str=str+'<option value="北海">北海</option>';
			str=str+'<option value="崇左">崇左</option>';
			str=str+'</select>';
			
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="贵州") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="贵阳" >贵阳</option>';
              str=str+'<option value="遵义">遵义</option>';
              str=str+'<option value="安顺">安顺</option>';
              str=str+'<option value="都匀">都匀</option>';
              str=str+'<option value="六盘水">六盘水</option>';
              str=str+'<option value="铜仁">铜仁</option>';
              str=str+'<option value="凯里市">凯里市</option>';
              str=str+'<option value="兴义">兴义</option>';
              str=str+'<option value="毕节">毕节</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="海南") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="海口">海口</option>';
              str=str+'<option value="三亚">三亚</option>';
              str=str+'<option value="儋州">儋州</option>';
              str=str+'<option value="琼海">琼海</option>';
              str=str+'<option value="琼山">琼山</option>';
              str=str+'<option value="通什">通什</option>';
              str=str+'<option value="文昌">文昌</option>';
              str=str+'<option value="万宁">万宁</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="河北") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="石家庄">石家庄</option>';
              str=str+'<option value="邯郸">邯郸</option>';
              str=str+'<option value="保定">保定</option>';
              str=str+'<option value="张家口">张家口</option>';
              str=str+'<option value="承德">承德</option>';
              str=str+'<option value="唐山">唐山</option>';
              str=str+'<option value="廊坊">廊坊</option>';
              str=str+'<option value="沧州">沧州</option>';
              str=str+'<option value="衡水">衡水</option>';
              str=str+'<option value="邢台">邢台</option>';
			  str=str+'<option value="唐山">唐山</option>';
			  str=str+'<option value="秦皇岛">秦皇岛</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="河南") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="郑州">郑州</option>';
              str=str+'<option value="商丘">商丘</option>';
              str=str+'<option value="安阳">安阳</option>';
              str=str+'<option value="新乡">新乡</option>';
              str=str+'<option value="许昌">许昌</option>';
              str=str+'<option value="平顶山">平顶山</option>';
              str=str+'<option value="南阳">南阳</option>';
              str=str+'<option value="开封">开封</option>';
              str=str+'<option value="洛阳">洛阳</option>';
              str=str+'<option value="焦作">焦作</option>';
			  str=str+'<option value="濮阳">濮阳</option>';
			  str=str+'<option value="驻马店">驻马店</option>';
			  str=str+'<option value="信阳">信阳</option>';
			  str=str+'<option value="周口">周口</option>';
			  str=str+'<option value="济源">济源</option>';
			  str=str+'<option value="鹤壁">鹤壁</option>';
			  str=str+'<option value="三门峡">三门峡</option>';
			  str=str+'<option value="漯河">漯河</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="黑龙江") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="哈尔滨">哈尔滨</option>';
              str=str+'<option value="齐齐哈尔">齐齐哈尔</option>';
              str=str+'<option value="牡丹江">牡丹江</option>';
              str=str+'<option value="佳木斯">佳木斯</option>';
              str=str+'<option value="绥化">绥化</option>';
              str=str+'<option value="黑河">黑河</option>';
              str=str+'<option value="大兴安岭">大兴安岭</option>';
              str=str+'<option value="伊春">伊春</option>';
              str=str+'<option value="大庆">大庆</option>';
              str=str+'<option value="鸡西">鸡西</option>';
			  str=str+'<option value="鹤岗">鹤岗</option>';
			  str=str+'<option value="双鸭山">双鸭山</option>';
			  str=str+'<option value="七台河">七台河</option>';
			  str=str+'<option value="乌苏里江">乌苏里江</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="湖北") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="武汉">武汉</option>';
              str=str+'<option value="襄樊">襄樊</option>';
              str=str+'<option value="孝感">孝感</option>';
              str=str+'<option value="黄石">黄石</option>';
              str=str+'<option value="咸宁">咸宁</option>';
              str=str+'<option value="潜江">潜江</option>';
              str=str+'<option value="宜昌">宜昌</option>';
              str=str+'<option value="仙桃">仙桃</option>';
              str=str+'<option value="十堰">十堰</option>';
              str=str+'<option value="荆门">荆门</option>';
			  str=str+'<option value="天门">天门</option>';
			  str=str+'<option value="随州">随州</option>';
			  str=str+'<option value="鄂州">鄂州</option>';
			  str=str+'<option value="黄冈">黄冈</option>';
			  str=str+'<option value="荆州">荆州</option>';
			  str=str+'<option value="恩施">恩施</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="湖南") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="长沙">长沙</option>';
              str=str+'<option value="岳阳">岳阳</option>';
              str=str+'<option value="湘潭">湘潭</option>';
              str=str+'<option value="株洲">株洲</option>';
              str=str+'<option value="衡阳">衡阳</option>';
              str=str+'<option value="常德">常德</option>';
              str=str+'<option value="郴州">郴州</option>';
              str=str+'<option value="益阳">益阳</option>';
              str=str+'<option value="邵阳">邵阳</option>';
              str=str+'<option value="张家界">张家界</option>';
			  str=str+'<option value="怀化">怀化</option>';
			  str=str+'<option value="娄底">娄底</option>';
			  str=str+'<option value="永州">永州</option>';
			  str=str+'<option value="湘西">湘西</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="吉林") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="长春">长春</option>';
              str=str+'<option value="吉林">吉林</option>';
			str=str+'<option value="延边">延边</option>';
              str=str+'<option value="四平">四平</option>';
              str=str+'<option value="通化">通化</option>';
              str=str+'<option value="白山">白山</option>';
              str=str+'<option value="辽源">辽源</option>';
              str=str+'<option value="松原">松原</option>';
              str=str+'<option value="白城">白城</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="江苏") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="南京">南京</option>';
              str=str+'<option value="无锡">无锡</option>';
              str=str+'<option value="镇江">镇江</option>';
              str=str+'<option value="苏州">苏州</option>';
              str=str+'<option value="南通">南通</option>';
              str=str+'<option value="扬州">扬州</option>';
              str=str+'<option value="盐城">盐城</option>';
              str=str+'<option value="徐州">徐州</option>';
              str=str+'<option value="淮安">淮安</option>';
              str=str+'<option value="连云港">连云港</option>';
			  str=str+'<option value="常州">常州</option>';
			  str=str+'<option value="宿迁">宿迁</option>';
			  str=str+'<option value="泰州">泰州</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="江西") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="南昌">南昌</option>';
              str=str+'<option value="九江">九江</option>';
              str=str+'<option value="上饶">上饶</option>';
              str=str+'<option value="抚州">抚州</option>';
              str=str+'<option value="宜春">宜春</option>';
              str=str+'<option value="吉安">吉安</option>';
              str=str+'<option value="赣州">赣州</option>';
              str=str+'<option value="景德镇">景德镇</option>';
              str=str+'<option value="萍乡">萍乡</option>';
              str=str+'<option value="新余">新余</option>';
			  str=str+'<option value="鹰潭">鹰潭</option>';
			  str=str+'<option value="侯马">侯马</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="辽宁") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="沈阳">沈阳</option>';
              str=str+'<option value="铁岭">铁岭</option>';
              str=str+'<option value="大连">大连</option>';
              str=str+'<option value="鞍山">鞍山</option>';
              str=str+'<option value="抚顺">抚顺</option>';
              str=str+'<option value="本溪">本溪</option>';
              str=str+'<option value="丹东">丹东</option>';
			  str=str+'<option value="锦州">锦州</option>';
              str=str+'<option value="营口">营口</option>';
              str=str+'<option value="辽阳">辽阳</option>';
              str=str+'<option value="盘锦">盘锦</option>';
			  str=str+'<option value="葫芦岛">葫芦岛</option>';
			  str=str+'<option value="朝阳">朝阳</option>';
			  str=str+'<option value="阜新">阜新</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="内蒙古") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="呼和浩特">呼和浩特</option>';
              str=str+'<option value="包头">包头</option>';
              str=str+'<option value="呼仑贝尔">呼仑贝尔</option>';
              str=str+'<option value="赤峰">赤峰</option>';
              str=str+'<option value="兴安盟">兴安盟</option>';
              str=str+'<option value="通辽市">通辽市</option>';
              str=str+'<option value="锡林郭勒">锡林郭勒</option>';
              str=str+'<option value="乌兰察布">乌兰察布</option>';
              str=str+'<option value="巴彦卓尔盟">巴彦卓尔盟</option>';
              str=str+'<option value="阿拉善盟">阿拉善盟</option>';
			  str=str+'<option value="伊克昭盟">伊克昭盟</option>';
			  str=str+'<option value="鄂尔多斯">鄂尔多斯</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="宁夏") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="银川">银川</option>';
              str=str+'<option value="石嘴山市">石嘴山市</option>';
              str=str+'<option value="吴忠市">吴忠市</option>';
              str=str+'<option value="固原">固原</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="青海") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="西宁">西宁</option>';
              str=str+'<option value="海西州">海西州</option>';
              str=str+'<option value="海北州">海北州</option>';
              str=str+'<option value="海南州">海南州</option>';
              str=str+'<option value="海东地区">海东地区</option>';
              str=str+'<option value="黄南州">黄南州</option>';
              str=str+'<option value="果洛州">果洛州</option>';
              str=str+'<option value="玉树州">玉树州</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="山东") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="济南">济南</option>';
              str=str+'<option value="青岛">青岛</option>';
              str=str+'<option value="淄博">淄博</option>';
              str=str+'<option value="德州">德州</option>';
              str=str+'<option value="烟台">烟台</option>';
              str=str+'<option value="潍坊">潍坊</option>';
              str=str+'<option value="济宁">济宁</option>';
              str=str+'<option value="泰安">泰安</option>';
              str=str+'<option value="临沂">临沂</option>';
              str=str+'<option value="东营">东营</option>';
			  str=str+'<option value="威海">威海</option>';
			  str=str+'<option value="日照">日照</option>';
			  str=str+'<option value="莱芜">莱芜</option>';
			  str=str+'<option value="滨州">滨州</option>';
			  str=str+'<option value="菏泽">菏泽</option>';
			  str=str+'<option value="聊城">聊城</option>';
			  str=str+'<option value="枣庄">枣庄</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="山西") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="太原">太原</option>';
              str=str+'<option value="朔州">朔州</option>';
              str=str+'<option value="忻州">忻州</option>';
              str=str+'<option value="大同">大同</option>';
              str=str+'<option value="阳泉">阳泉</option>';
              str=str+'<option value="晋中">晋中</option>';
              str=str+'<option value="长治">长治</option>';
              str=str+'<option value="晋城">晋城</option>';
              str=str+'<option value="临汾">临汾</option>';
              str=str+'<option value="运城">运城</option>';
			  str=str+'<option value="吕梁地区">吕梁地区</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="陕西") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="西安">西安</option>';
              str=str+'<option value="咸阳">咸阳</option>';
              str=str+'<option value="延安">延安</option>';
              str=str+'<option value="榆林">榆林</option>';
              str=str+'<option value="渭南">渭南</option>';
              str=str+'<option value="商洛地区">商洛地区</option>';
              str=str+'<option value="安康">安康</option>';
              str=str+'<option value="汉中">汉中</option>';
              str=str+'<option value="宝鸡">宝鸡</option>';
              str=str+'<option value="定边">定边</option>';
			  str=str+'<option value="铜川">铜川</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="上海") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="上海" selected>上海</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="四川") 
	  {
			str='<select name="city" id="city">';
			str=str+'<option value="" selected>请选择</option>';
			str=str+'<option value="成都">成都</option>';
			str=str+'<option value="攀枝花">攀枝花</option>';
			str=str+'<option value="自贡">自贡</option>';
			str=str+'<option value="绵阳">绵阳</option>';
			str=str+'<option value="南充">南充</option>';
			str=str+'<option value="达州">达州</option>';
			str=str+'<option value="遂宁">遂宁</option>';
			str=str+'<option value="广安">广安</option>';
			str=str+'<option value="巴中">巴中</option>';
			str=str+'<option value="泸州">泸州</option>';
			str=str+'<option value="宜宾">宜宾</option>';
			str=str+'<option value="内江">内江</option>';
			str=str+'<option value="乐山">乐山</option>';
			str=str+'<option value="雅安">雅安</option>';
			str=str+'<option value="凉山彝族自治州">凉山彝族自治州</option>';
			str=str+'<option value="甘孜藏族自治州">甘孜藏族自治州</option>';
			str=str+'<option value="阿坝藏族羌族自治州">阿坝藏族羌族自治州</option>';
			str=str+'<option value="德阳">德阳</option>';
			str=str+'<option value="广元">广元</option>';
			str=str+'<option value="眉山">眉山</option>';
			str=str+'<option value="资阳">资阳</option>';
			str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="天津") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="天津" selected>天津</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="新疆") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="乌鲁木齐">乌鲁木齐</option>';
              str=str+'<option value="克拉玛依">克拉玛依</option>';
              str=str+'<option value="石河子">石河子</option>';
              str=str+'<option value="阿克苏">阿克苏</option>';
              str=str+'<option value="石田市">石田市</option>';
              str=str+'<option value="吐鲁番">吐鲁番</option>';
              str=str+'<option value="哈密">哈密</option>';
              str=str+'<option value="阿图什">阿图什</option>';
              str=str+'<option value="伊宁市">伊宁市</option>';
              str=str+'<option value="博乐市">博乐市</option>';
			  str=str+'<option value="昌吉市">昌吉市</option>';
			  str=str+'<option value="巴音郭楞">巴音郭楞</option>';
			  str=str+'<option value="塔城地区">塔城地区</option>';
			  str=str+'<option value="阿勒泰">阿勒泰</option>';
			  str=str+'<option value="喀什">喀什</option>';
			  str=str+'<option value="伊犁">伊犁</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="西藏") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="拉萨">拉萨</option>';
              str=str+'<option value="阿里">阿里</option>';
              str=str+'<option value="昌都">昌都</option>';
              str=str+'<option value="林芝">林芝</option>';
              str=str+'<option value="那曲">那曲</option>';
              str=str+'<option value="日喀则">日喀则</option>';
              str=str+'<option value="山南">山南</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="云南") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="昆明">昆明</option>';
              str=str+'<option value="大理">大理</option>';
              str=str+'<option value="西双版纳">西双版纳</option>';
              str=str+'<option value="曲靖">曲靖</option>';
              str=str+'<option value="玉溪">玉溪</option>';
              str=str+'<option value="楚雄">楚雄</option>';
              str=str+'<option value="思茅">思茅</option>';
              str=str+'<option value="丽江">丽江</option>';
              str=str+'<option value="中甸">中甸</option>';
              str=str+'<option value="潞西">潞西</option>';
			  str=str+'<option value="文山">文山</option>';
			  str=str+'<option value="个旧">个旧</option>';
			  str=str+'<option value="泸水">泸水</option>';
			  str=str+'<option value="临沧">临沧</option>';
			  str=str+'<option value="保山">保山</option>';
			  str=str+'<option value="邵通">邵通</option>';
			  str=str+'<option value="红河">红河</option>';
			  
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="浙江") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
	          str=str+'<option value="杭州">杭州</option>';
              str=str+'<option value="衢州">衢州</option>';
              str=str+'<option value="嘉兴">嘉兴</option>';
              str=str+'<option value="宁波">宁波</option>';
              str=str+'<option value="台州">台州</option>';
              str=str+'<option value="绍兴">绍兴</option>';
              str=str+'<option value="温州">温州</option>';
              str=str+'<option value="丽水">丽水</option>';
              str=str+'<option value="金华">金华</option>';
              str=str+'<option value="舟山">舟山</option>';
			  str=str+'<option value="义乌">义乌</option>';
			  str=str+'<option value="临海">临海</option>';
			  str=str+'<option value="湖州">湖州</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="重庆") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="重庆" selected>重庆</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="香港") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="香港" selected>香港</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="台湾") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="" selected>请选择</option>';
			  str=str+'<option value="台北">台北</option>';
			  str=str+'<option value="高雄">高雄</option>';
			  str=str+'<option value="台中">台中</option>';
			  str=str+'<option value="台南">台南</option>';
			  str=str+'<option value="澎湖">澎湖</option>';
			  str=str+'<option value="南投">南投</option>';
			  str=str+'<option value="金门">金门</option>'; 
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	  else if(ee=="澳门") 
	  {
	       str='<select name="city" id="city">';
	          str=str+'<option value="澳门" selected>澳门</option>';
	     str=str+'</select>';
       window.city_area.innerHTML=str;
	  }
	 else
	  {
	   str='<input type=text name="city" size="15" class="input_border">'
       window.city_area.innerHTML=str;
	  }
	
		  
 }