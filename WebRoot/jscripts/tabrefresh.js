var onlyOpenTitle="系统主页";
	function expand(el)
	{
	   var	childObj = document.getElementById("child" + el);

		if (childObj.style.display == 'none')
		{
			childObj.style.display = 'block';
		}
		else
		{
			childObj.style.display = 'none';
		}
		return;
	}

	function addTab(title, url) {
		if ($('#tabs').tabs('exists', title)) {
			$('#tabs').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
			$('#tabs').tabs('add', {
				title : title,
				content : content,
				closable : true,
				tools:'#tab-tools'
			});
		}
		tabClose();
	}
	
	
	//选择TAB时刷新内容
	$(function(){
	   tabClose();
	   tabCloseEven();
     })
	
	
	function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}

	
function closeTab(action)
{
	
    var alltabs = $('#tabs').tabs('tabs');
    var currentTab =$('#tabs').tabs('getSelected');
    
    var allTabtitle = [];
	$.each(alltabs,function(i,n){
		allTabtitle.push($(n).panel('options').title);
	})
    
    
    switch (action) {
	    case "tabupdate":
	        var iframe = $(currentTab.panel('options').content);
	        var src = iframe.attr('src');
	        $('#tabs').tabs('update', {
	            tab: currentTab,
	            options: {
	                content: createFrame(src)
	            }
	        })
	        break;
        case "close":
        	var subtitle = currentTab.panel('options').title;
        	if(subtitle != onlyOpenTitle)
        		{
        		  $('#tabs').tabs('close',subtitle);
        		}
        	
            break;
        case "closeall":
            $.each(allTabtitle, function (i, n) {
                if (n != onlyOpenTitle){
                    $('#tabs').tabs('close', n);
				}
            });
            break;
        case "closeother":
            var currtab_title = currentTab.panel('options').title;
            $.each(allTabtitle, function (i, n) {
                if (n != currtab_title && n != onlyOpenTitle)
				{
                    $('#tabs').tabs('close', n);
				}
            });
            break;
        case "closeright":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

            if (tabIndex == alltabs.length - 1){
                alert('亲，后边没有啦 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i > tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
					}
                }
            });

            break;
        case "closeleft":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
            if (tabIndex == 1) {
                alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i < tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
					}
                }
            });

            break;
        case "exit":
            $('#closeMenu').menu('hide');
            break;
    }
}


//绑定右键菜单事件
function tabCloseEven() {
    $('#mm').menu({
        onClick: function (item) {
            closeTab(item.id);
        }
    });
    return false;
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

	