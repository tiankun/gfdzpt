var tabid='#tabs';//主页tabs的id
var notClose = '系统主页';//不允许删除的tabs名称
var loading='页面加载中，请稍待。。。';
var checkFalse='权限验证失败，请重新登录后再试！';
/*form内参数转json格式字符串*/     
$.fn.form2json = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
}
/**
 * @requires jQuery,EasyUI
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e, field) {
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
		var fields = grid.datagrid('getColumnFields');
		for ( var i = 0; i < fields.length; i++) {
			var fildOption = grid.datagrid('getColumnOption', fields[i]);
			if (!fildOption.hidden) {
				$('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			} else {
				$('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu({
			onClick : function(item) {
				var field = $(item.target).attr('field');
				if (item.iconCls == 'icon-ok') {
					grid.datagrid('hideColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					grid.datagrid('showColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			headerContextMenu.menu('show', {left : e.pageX,top : e.pageY});
			}
		});
	}
	headerContextMenu.menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/*删除指定表格，是否批量删除，地址，删除id（非批量删除有用）,删除成功回调函数*/
function batchDelete(tabid,isBatchDelete,url,id,susses) {
	if (isBatchDelete) {
		var rows = $(tabid).datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('－提示－', '是否确定要删除选中项?',
			function(r) {
				if (r) {
                   var date = "";
					for (var i = 0; i < rows.length; i++) {
					    date = date+id+"="+rows[i].id;
                        if(i!=(rows.length-1))
                        	date = date+"&";
					}
					$.getJSON(url,date,
					function(data) {
						$.messager.alert('－消息－', '数据删除成功！', 'info');
						susses();
					});
				}
			});
		} else $.messager.alert('－消息－', '请选择至少一条记录才能进行删除！', 'info');
	} else {
		$.messager.confirm('－提示－', '是否确定要删除此条信息?',
		function(r) {
			if (r) {
				$.getJSON(url,
				function(data) {
					$.messager.alert('－消息－', '数据删除成功！', 'info');
					susses();
				});
			}
		});
	}
}

/*通过外部页面创建弹出对话框*/
$.fn.myDialog = function(href, title, height, width) {
	var content = '<iframe scrolling="no" frameborder="0"  src="' + href + '" style="width:100%;height:100%;"></iframe>';
	this.dialog( {
		modal : true,
		closed : false,
		cache : false,
		title : title,
		height : height,
		width : width,
		content : content
	});
}
//全局的ajax访问，处理ajax请求sesion超时   
$.ajaxSetup( {
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	cache : false,
	complete : function(XHR, TS) {
		var resText = XHR.responseText;
		if (resText != null && resText.indexOf("sessionState:0") > 0) {
			alert(checkFalse);
		}
	}
});
//datagrid的自适应宽度高度的问题
$.fn.extend({
resizeDataGrid : function(heightMargin, widthMargin, minHeight, minWidth) {
  var height = $(document.body).height() - heightMargin;
  var width = $(document.body).width() - widthMargin;

  height = height < minHeight ? minHeight : height;
  width = width < minWidth ? minWidth : width;
  $(this).datagrid('resize', {
   height : height,
   width : width
  });
 }
});
/** 
 * 增加iframe模式的标签页   
 * @param {[type]} jq     [description]   
 * @param {[type]} params [description]   
 */ 
$.extend($.fn.tabs.methods, {      
    addIframeTab:function(jq,params){    
        return jq.each(function(){    
            if(params.tab.href){    
                delete params.tab.href;    
            }    
            $(this).tabs('add',params.tab);    
            var opts = $(this).tabs('options');    
            var $tabBody = $(this).tabs('getTab',params.tab.title).panel('body');    
            $tabBody.css({'overflow':'hidden','position':'relative'});    
            var $mask = $('<div style="position:absolute;z-index:2;width:100%;height:100%;background:#ccc;z-index:1000;opacity:0.3;filter:alpha(opacity=30);"><div>').appendTo($tabBody);    
            var $maskMessage = $('<div class="mask-message" style="z-index:3;width:auto;height:16px;line-height:16px;position:absolute;top:50%;left:50%;margin-top:-20px;margin-left:-92px;border:2px solid #95B8E7;padding: 12px 5px 10px 30px;background: #ffffff url(\'../scripts/easyui/themes/default/images/loading.gif\') no-repeat scroll 5px center;">' + (params.iframe.message || loading) + '</div>').appendTo($tabBody);    
            var $containterMask = $('<div style="position:absolute;width:100%;height:100%;z-index:1;background:#fff;"></div>').appendTo($tabBody);    
            var $containter = $('<div style="position:absolute;width:100%;height:100%;z-index:0;"></div>').appendTo($tabBody);    
   
            var iframe = document.createElement("iframe");    
            iframe.src = params.iframe.src;    
            iframe.frameBorder = params.iframe.frameBorder || 0;    
            iframe.height = params.iframe.height || '100%';    
            iframe.width = params.iframe.width || '100%';    
            if (iframe.attachEvent){    
                iframe.attachEvent("onload", function(){    
                    $([$mask[0],$maskMessage[0]]).fadeOut(params.iframe.delay || 'slow',function(){    
                        $(this).remove();    
                        if($(this).hasClass('mask-message')){    
                            $containterMask.fadeOut(params.iframe.delay || 'slow',function(){    
                                $(this).remove();    
                            });    
                        }    
                    });    
                });    
            } else {    
                iframe.onload = function(){    
                    $([$mask[0],$maskMessage[0]]).fadeOut(params.iframe.delay || 'slow',function(){    
                        $(this).remove();    
                        if($(this).hasClass('mask-message')){    
                            $containterMask.fadeOut(params.iframe.delay || 'slow',function(){    
                                $(this).remove();    
                            });    
                        }    
                    });    
                };    
            }    
            $containter[0].appendChild(iframe);    
        });    
    }    
}); 
//刷新tabs
function refreshTab(){  
  var refresh_tab =$(tabid).tabs('getSelected');  
  if(refresh_tab && refresh_tab.find('iframe').length > 0){  
  var _refresh_ifram = refresh_tab.find('iframe')[0];  
  var refresh_url = _refresh_ifram.src;  
  _refresh_ifram.contentWindow.location.href=refresh_url;  
  }else
	  $(tabid).tabs('update', {
    tab: $(tabid).tabs('getSelected'),options: {}
}); 
} 

//tabs右键操作
$(function() {
	//刷新
	$("#m-refresh").click(function() {
        refreshTab();  
		});
	//关闭所有
	$("#m-closeall").click(function() {
		$(".tabs li").each(function(i, n) {
			var title = $(n).text();
			if (notClose!= title)
				$(tabid).tabs('close', title);
		});
	});
	//除当前之外关闭所有
	$("#m-closeother").click(function() {
		var currTab = $(tabid).tabs('getSelected');
		currTitle = currTab.panel('options').title;
		$(".tabs li").each(function(i, n) {
			var title = $(n).text();
			if (currTitle != title&&notClose!= title) {
				$(tabid).tabs('close', title);
			}
		});
	});
	//关闭当前
	$("#m-close").click(function() {
		var currTab = $(tabid).tabs('getSelected');
		currTitle = currTab.panel('options').title;
		if(notClose!= currTitle)
			$(tabid).tabs('close', currTitle);
	});
});

//绑定快捷菜单事件
$(function() {
	/*为选项卡绑定右键*/
	$(".tabs li").live('contextmenu', function(e) {
		/* 选中当前触发事件的选项卡 */
		var subtitle = $(this).text();
		$(tabid).tabs('select', subtitle);
		//显示快捷菜单
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
			return false;
		});
});