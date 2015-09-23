<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main-in.css" type="text/css"></link>
<script src="<%= request.getContextPath() %>/jscripts/jquery.js" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
  <script src="<%= request.getContextPath() %>/jscripts/EditItem.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>



</head>

<script type="text/javascript">
		var firTime = 0;
		
		
		$(function(){
			$("#name").keyup(function(){
				firTime = new Date().getTime();				
			})
			$("#model").keyup(function(){
				firTime = new Date().getTime();				
			})
			$("#shortcode").keyup(function(){
				firTime = new Date().getTime();				
			})
		})
		
		
		
		$(function(){
			$("#kindname").bind("propertychange",function(){
				//alert("aaa");
				firTime = new Date().getTime();				
			})
		})
		
		$(function(){
			$("#brandname").bind("propertychange",function(){
				//alert("aaa");
				firTime = new Date().getTime();				
			})
		})
		
		
		function reRun(){
			if(firTime > 0 && (new Date().getTime()-firTime) > 300){
				
				getresult();
                  
				firTime = 0;
			}
		}
		
		setInterval(reRun,100);	
		
		
		
function getresult(){
	            var name = document.form1.name.value;
	            var shortcode = document.form1.shortcode.value;
	            var model = document.form1.model.value;
	            var brandname = document.form1.brandname.value;
	            var kindname = document.form1.kindname.value;
	            
	           
	            var url = "<%=request.getContextPath()%>/materiel/Materiel!getData.do"; 
				$.ajax({
					type:"POST",
					url:url,
					dataType:"json",
					
					data:{"name":name,
					      "shortcode":shortcode,
					      "model":model,
					      "brandname":brandname,
					      "kindname":kindname
					
					
				},
					cache:false,
					success:function(data){
					   $("#content tbody tr").remove();
						 var row="";
						 
						 //alert(data.length);
						 
						 
						$.each(data,function(key,value){
							
							row =row + "<tr>" +
								"<td>" + value.rownum + "</td>" + 
								"<td>" + value.kindname + "</td>" +
								"<td>" + value.brandname + "</td>" +
								"<td>" + value.name + "</td>" +
								"<td>" + value.shortcode + "</td>" +
								"<td>" + value.model + "</td>" +
								"<td>" + value.unit + "</td>" +
								"<td>" + value.parameter + "</td>" +
								"<td><a href='#' onclick=\"openDG('查看详细信息','<%=request.getContextPath()%>/materiel/Materiel!searchById.do?id=" + value.id + "','materielDtl')\">查看</a>" +
								" <a href='#' onclick=\"openDG('修改详细信息','<%=request.getContextPath()%>/materiel/Materiel!edit.do?id=" + value.id + "','materielDtl')\">修改</a>"+
								" <%-- <a href='<%=request.getContextPath()%>/materiel/Materiel!delete.do?id=" + value.id + "' OnClick=\"javascript:return confirm('你确认要删除吗?');\">删除</a> --%></td></tr>";
								
							
						});
						
						$("#content tbody").append(row);
					},
					error:function(info){
						alert("未找到属性信息");
					}

				});
		}


function fanye()
{
	document.form1.action = '<%=request.getContextPath()%>/materiel/Materiel!searchList.do';
	document.form1.submit();
}
		
		
		
	</script>

<body>	
	<form id="searchForm" action="<%=request.getContextPath()%>/materiel/Materiel!searchList.do" method="post" name="form1" id="">
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            <tr>
			<th>类型:</th>
			<td>
			
			<input type="text" name="kindname" id="kindname"
								value="${searchMap.kindname}" class="textBox" />
							<input type="text" name="kind_id" id="kind_id"
								value="${searchMap.kind_id}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择材料类型','<%=request.getContextPath()%>/materiel/Ma_kind!searchList.do?pageType=select','kind_id','kindname');"
								value="选择" />
			
			</td>
			<th>品牌:</th>
			<td>
			
			<input type="text" name="brandname" id="brandname" onchange="gettime();"
								value="${searchMap.brandname}" class="textBox" />
							<input type="text" name="brand_id" id="brand_id"
								value="${searchMap.brand_id}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择材料类型','<%=request.getContextPath()%>/materiel/Ma_brand!searchList.do?pageType=select','brand_id','brandname');"
								value="选择" />
			</td>
            </tr>
            <tr>
			<th>名称:</th>
			<td>
			<input type="text" id="name" name="name" style="width:95%" value="${searchMap.name}" class="textBox"/></td>
			<th>简码:</th>
			<td>
			<input type="text" name="shortcode" id="shortcode" style="width:95%" value="${searchMap.shortcode}" class="textBox"/></td>
            </tr>
            <tr>
			<th>规格型号:</th>
			<td>
			<input type="text" name="model" id="model" style="width:95%" value="${searchMap.model}" class="textBox"/></td><th></th><td></td>
            </tr>
		</table>
	</div>
		<div class="div-button">
			<a href="#" onclick="openDG('添加信息','<%=request.getContextPath()%>/materiel/Materiel!add.do','materielDtl')" class="link">添加</a>
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
			
		</div>
		<div class="clear" style="height:0px;"></div>
	</form>
	<div>
		<table id="content" class="dataListTable" width="100%">
			<thead>
			
           <th>序号</th>
           <th>类型</th>
           <th>品牌</th>
           <th>名称</th>
           <th>简码</th>
           <th>规格型号</th>
           <th>计量单位</th>
           <th>产品参数</th>
           
           
           
		        <th class="btnCol">操作</th>
		        </thead>
		
		<tbody>
		
		<c:if test="${flag=='1'}">
		
		<c:forEach items="${record}" var="result" varStatus="status">
				<tr>
				
      <td><c:out value="${status.index+1}" /></td>
      <td><c:out value="${result.kindname}" /></td>
      <td><c:out value="${result.brandname}" /></td>
      <td><c:out value="${result.name}" /></td>
      <td><c:out value="${result.shortcode}" /></td>
      <td><c:out value="${result.model}" /></td>
      <td><c:out value="${result.unit}" /></td>
      <td><c:out value="${result.parameter}" /></td>
      
      
				<td class="btnCol">
				<a href="#" onClick="openDG('查看详细信息','<%=request.getContextPath()%>/materiel/Materiel!searchById.do?id=${result.id}','materielDtl')">查看</a>
				<a href="#" onClick="openDG('修改详细信息','<%=request.getContextPath()%>/materiel/Materiel!edit.do?id=${result.id}','materielDtl')">修改</a>
				<%--<a href="<%=request.getContextPath()%>/materiel/Materiel!delete.do?id=${result.id}" OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>--%>
				</td>
				</tr>
			</c:forEach>
		</c:if>
		
		</tbody>
		</table>
	</div>
	<div id="PageUpDnDiv" style="PADDING-TOP:5px;padding-bottom:5px; text-align:center" >
	
	<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span> 页</span>
	<a onclick="changePage(1)" class="linkPage">首页</a><a onclick="changePage(${page.previousPageNumber})" class="linkPage">上一页</a>
	<a onclick="changePage(${page.nextPageNumber})" class="linkPage">下一页</a>
	<a onclick="changePage(${page.lastPageNumber})" class="linkPage">尾页</a>
	</div>
</body>
<script type="text/javascript">
	function changePage(page){
		if(page==undefined||page==null) page = $("#curPage").text();
		document.getElementById('searchForm').insertAdjacentHTML("beforeEnd",
	        "<input type=\"hidden\" name=\"pageno\" value=\""+page+"\"/>");
		document.getElementById('searchForm').submit();
	}
</script>
</html>