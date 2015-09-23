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




<body>	
	<form id="searchForm" action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!kucunquery.do" method="post" name="form1">
	<input type="hidden" name="id"/>
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            
            <tr>
            <th>物资名称:</th>
			<td>
			<input type="text" id="proname" name="maname" value="${searchMap.maname}" readonly="readonly" class="textBox"/>
			<input type="text" id="materiel_id" name="materiel_id" value="${searchMap.materiel_id}"  dataType="Double" require="false" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/materiel/Materiel!searchList.do?pageType=select','materiel_id','maname');" value="选择"/>
            </td>
            
			<th>品牌:</th>
			<td>
			<input type="text" name="brandname" id="brandname" 
								value="${searchMap.brandname}" class="textBox" />
							<input type="text" name="brand_id" id="brand_id"
								value="${searchMap.brand_id}" class="hidden" />
							<input type="button"
								onclick="openSelect('选择材料品牌','<%=request.getContextPath()%>/materiel/Ma_brand!searchList.do?pageType=select','brand_id','brandname');"
								value="选择" />
			
			</td>
			</tr>
			
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
			<th></th>
			<td>
			</td>
            </tr>
			
			
			
            
			
			
			
		</table>
	</div>
		<div class="div-button">
			<input type="reset"	value="重置" class="button"/>
			<input type="submit" value="查询" class="button" />
		</div>
		<div class="clear" style="height:0px;"></div>
	
	<div>
		<table class="dataListTable" width="100%">
			<tr>
			
			<th width="40">序号</th>
           <th>材料名称</th>
           <th>品牌</th>
           <th width="60">库存量</th>
		        <th  width="40">操作</th>
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>	
		 <td><c:out value="${status.index+1}" /></td>	
		 
		 
      <td><c:out value="${result.maname}" /></td>
      <td><c:out value="${result.brname}" /></td>
      
      <td><c:out value="${result.kucun}" /></td>
      
     
      
     <td >
				<a href="#" onClick="openDG('查看详细信息','<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!lookdetail.do?materiel_id=${result.materiel_id}','materielDtl')">详细</a>
				
				</td>
				</tr>
			</c:forEach>
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
	</form>
</html>