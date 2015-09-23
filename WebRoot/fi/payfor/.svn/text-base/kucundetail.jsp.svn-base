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
	<form id="searchForm" action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!materielinquery.do" method="post" name="form1">
	<input type="hidden" name="materiel_id" value="${searchMap.materiel_id }"/>
	<div class="divMod2">
	<div class="divMod1">查询条件</div>
		<table width="99%" class="search_border">
		
            
            <tr>
            <th>配给单:</th>
			<td>
			<input type="text" id="ration_apply_id" name="ration_apply_id" value="${searchMap.ration_apply_id}"  class="textBox"/>
			
            </td>
            
			<th>支付情况</th>
			<td> 
			<select name="ispay" style="width: 60%">
			  <option value="">未选择</option>
			  <option value="0"  <c:if test="${searchMap.ispay=='0'}">selected</c:if>>未付</option>
			  <option value="1" <c:if test="${searchMap.ispay=='1'}">selected</c:if>>支付中</option>
			  <option value="2" <c:if test="${searchMap.ispay=='2'}">selected</c:if>>已付</option>
			  
			</select>
			
			</td>
			</tr>
			
			<tr>
            <th>供货商:</th>
			<td>
			<input type="text" name="gong"
									id="gong"  value="${searchMap.gong}"
									class="textBox" />
								<input type="text" name="gongys"
									id="gongys" value=""
									class="hidden" />
								<input type="button"
									onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','gongys','gong');"
									value="选择" /></td>
            
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
			
			<th width="5%">序号</th>
			<th width="15%">配给单</th>
           <th>材料名称</th>
           <th>供货商</th>
           <th>品牌</th>
           
           
           <th width="60">数量</th>
           <th width="60">入库单价</th>
           
           <th width="60">金额</th>
           
           <th width="10%">操作时间</th>
          
           <th width="60">是否付款</th>
           
		        
			</tr>
			<c:forEach items="${record}" var="result" varStatus="status">
				<tr>	
		 <td><c:out value="${status.index+1}" /></td>	
		 <td><c:out value="${result.ration_apply_id}" /></td> 
		 
      <td><c:out value="${result.name}" /></td>
      
      <td><c:out value="${result.gongys}" /></td>
      
      <td><c:out value="${result.brname}" /></td>
      
      
      <td><c:out value="${result.num}" /></td>
      
      <td><c:out value="${result.price}" /></td>
      <td><c:out value="${result.money}" /></td>
      
      
      <td><c:out value="${result.odate}" /></td>
      <td>
      <c:if test="${result.fk!='已付'}"><font color="red">${result.fk}</font></c:if>
      <c:if test="${result.fk=='已付'}"><c:out value="${result.fk}" /></c:if>
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
	</form>
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