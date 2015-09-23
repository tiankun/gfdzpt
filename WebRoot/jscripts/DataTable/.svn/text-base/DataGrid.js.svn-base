/**
 * @author 
 * @version 1.0
 * @link http:
 */
DataGrid = function(_config){
	_config = _config || {};
	
	var s = "";
	var rownum = 0;
	var __root;
	
	var __selectedData = null;
	var __selectedId = null;
	var __selectedIndex = null;
	
	//显示数据行
	drowData = function(date){
		var rows = date;
		var cols = [];  
			$("#"+_config.renderTo+" thead th").each(function (key, value) {  
				var option=$(this).attr("data-options");
				if(typeof option != 'undefined' && option != null )
					cols.push(eval('(' + "{"+option+"}" +')'));//DataGrid.str2json("{"+option+"}"));  
			}); 
		drowRowData(rows, cols, 1);
	}
	
	//局部变量i、j必须要用 var 来声明，否则，后续的数据无法正常显示
	drowRowData = function(_rows, _cols, _level){
		for(var i=0;i<_rows.length;i++){
			var id = "_" + i; //行id
			var row = _rows[i];
			
			s += "<tr id='TR" + id + "' data=\"" + DataGrid.json2str(row) + "\" rowIndex='" + rownum++ + "'>";
			for(var j=0;j<_cols.length;j++){
				var col = _cols[j];
				s += "<td align='" + (col.dataAlign || "left") + "'>";

				//单元格内容
				if(col.handler){
					s += (eval(col.handler + ".call(new Object(), row, _cols)") || "") + "</td>";
				}else{
					s += (row[col.dataField] || "") + "</td>";
				}
			}
			s += "</tr>";
		}
	}
	
	//主函数
	loadData=function(_Jdata){
		_Jdata = _Jdata || {};
		drowData(_Jdata);
		__root = jQuery("#"+_config.renderTo+" tbody");
		$("#"+_config.renderTo+" tbody tr").remove(); 
		__root.append(s);
		init();
		s="";
	}
	//主函数,
	this.loadJson=function(_Jdata){
		loadData(_Jdata);
	}
	//主函数,请求url，请求json参数，回调函数
	this.loadUrl=function(_url,_Qdata,_fun){
		$.ajax({
			type:"POST",
			url:_url,
			//dataType:"json",
			data:_Qdata,
			cache:false,
			success:function(data){
				var d = eval("("+data+")");
				loadData(d.rows);
				pageButton(d.page);
			},
			error:function(info){
				alert("查询出错。");
				}
		});
	}
	//
	init = function(){
		__root = jQuery("#"+_config.renderTo+" tbody");
		
		//将单击事件绑定到tr标签
		__root.find("tr").bind("click", function(){
			__root.find("tr").removeClass("row_active");
			jQuery(this).addClass("row_active");
			
			//获取当前行的数据
			__selectedData = this.data || this.getAttribute("data");
			__selectedId = this.id || this.getAttribute("id");
			__selectedIndex = this.rownum || this.getAttribute("rowIndex");

			//行记录单击后触发的事件
			if(_config.itemClick){
				eval(_config.itemClick + "(__selectedId, __selectedIndex, DataGrid.str2json(__selectedData))");
			}
		});
		//将单击事件绑定到th标签
		jQuery("#"+_config.renderTo+" thead th").bind("click", function(){
			var tmp = $(this).attr("class");
			jQuery("#"+_config.renderTo+" thead").find("th").each(function (key, value) {
				$(this).removeClass();
			});
			if(tmp =="th_arrow_up"){
				$(this).attr("class","th_arrow_down");
			}else{
				$(this).attr("class","th_arrow_up");
			}
			return false;
		});
	}

	//取得当前选中的行记录
	this.getSelectedItem = function(){
		return new DataGridItem(__root, __selectedId, __selectedIndex, DataGrid.str2json(__selectedData));
	}
	//
	this.removeAllRow = function(){
		$("#"+_config.renderTo+" tbody tr").remove(); 
	}
	pageButton = function(_page){
		if(typeof _page == 'object' && _page != null && _page.allPage >0){
			var btn='<span id="pageCurrent">第'+_page.current+'页</span>'+
				    '<span id="pageAll">共'+_page.allPage+'页</span>'+
					'<a onclick="changePage(1)" class="linkPage">首页</a>'+
					((_page.current>1)? first = '<a onclick="changePage('+(_page.current - 1) +')" class="linkPage">上一页</a>' :'')+ 
					((_page.current < _page.allPage)? next = '<a onclick="changePage('+(_page.current + 1) +')" class="linkPage">下一页</a>':'') +
					'<a onclick="changePage('+_page.allPage+')" class="linkPage">尾页</a>';
			$("#PageUpDnDiv").html(btn);
		}
	}
};

//公共静态变量

//将json对象转换成字符串
DataGrid.json2str = function(obj){
	var arr = [];

	var fmt = function(s){
		if(typeof s == 'object' && s != null){
			if(s.length){
				var _substr = "";
				for(var x=0;x<s.length;x++){
					if(x>0) _substr += ", ";
					_substr += DataGrid.json2str(s[x]);
				}
				return "[" + _substr + "]";
			}else{
				return DataGrid.json2str(s);
			}
		}
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}

	for(var i in obj){
		if(typeof obj[i] != 'object'){ //暂时不包括子数据
			arr.push(i + ":" + fmt(obj[i]));
		}
	}

	return '{' + arr.join(', ') + '}';
}

DataGrid.str2json = function(s){
	var json = null;
	if(jQuery.browser.msie){
		json = eval("(" + s + ")");
	}else{
		json = new Function("return " + s)();
	}
	return json;
}

//数据行对象
function DataGridItem (_root, _rowId, _rowIndex, _rowData){
	var __root = _root;
	
	this.id = _rowId;
	this.index = _rowIndex;
	this.data = _rowData;
};