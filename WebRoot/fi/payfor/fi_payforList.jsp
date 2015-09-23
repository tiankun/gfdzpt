<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title></title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/main-in.css" type="text/css"></link>
		<script src="<%=request.getContextPath()%>/jscripts/jquery.js"
			type="text/javascript">
</script>
		<script
			src="<%=request.getContextPath()%>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue"
			type="text/javascript">
</script>
		<script src="<%=request.getContextPath()%>/jscripts/EditItem.js"
			type="text/javascript">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>

<script type="text/javascript">
function sub() {
	
		var ids = "";
		var flag = true;
		var gystemp = "";
		var chongfu = 0;
		var obj = document.form1.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "checkbox") {
				if (obj[i].checked == true) {
					flag = false;
					if(gystemp!=obj[i].gy){
						chongfu += 1;
					}
					gystemp = obj[i].gy;
					ids = ids + ";" + obj[i].value;
				}
			}
		}
		if(!(chongfu=='0'||chongfu=='1')){
			alert("只可选择同一家供应商的配给单！");
			return false;
		}
		if (flag) {
			alert("请至少选择一条记录！");
			return false;
		}
        
		openDG('付款','<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!add.do?ids='+ids.substring(1),'fi_payforDtl');
		
	
}

function selectAll() {
	var obj = document.form1.elements;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			obj[i].checked = true;
		}
	}
}

function unselectAll() {
	var obj = document.form1.elements;
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].name == "checkbox") {
			if (obj[i].checked == true)
				obj[i].checked = false;
			else
				obj[i].checked = true;
		}
	}
}

function chec() {
	if (document.getElementById("check").checked) {
		selectAll();
	} else {
		unselectAll();
	}
}
</script>

	</head>
	<body>
		<form id="searchForm"
			action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!searchList.do"
			method="post" name="form1">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">
		
            <tr>
			<th>项目:</th>
			<td>
			<input type="text" id="proname" name="proname" value="${searchMap.proname}"  class="textBox"/>
			<input type="text" id="prj_id" name="prj_id" value=""  dataType="Double" require="false" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','prj_id','proname');" value="选择"/>
			</td>
			<th>配给单:</th>
			<td>
			<input type="text" value="${searchMap.ration_apply_id}" name="ration_apply_id"  style="width:95%" class="textBox"/></td>
            </tr>
            <tr>
            <th>物资名称:</th>
			<td>
			<input type="text" id="proname" name="maname" value="${searchMap.maname}"   class="textBox"/>
			<input type="text" id="materiel_id" name="materiel_id" value=""  dataType="Double" require="false" class="hidden"/>
			<input type="button" onclick="openSelect('branch_select','<%=request.getContextPath()%>/materiel/Materiel!searchList.do?pageType=select','materiel_id','maname');" value="选择"/>
            </td>
            
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
			
			</td>
			
           </tr>
			
			
			
		</table>
			</div>
			<div class="div-button">
			   <a href="#" onClick="sub();" class="link">付款</a>
				<input type="reset" value="重置" class="button" />
				<input type="submit" value="查询" class="button" />
			</div>
			<div class="clear" style="height: 0px;"></div>

			<div>
				<table class="dataListTable" width="100%">
					<tr>

						<th>
							<input type="checkbox" onclick="chec();" id="check">
						</th>
						<th>
							序号
						</th>
						<th>
							配给单
						</th>
						<th>
							材料
						</th>
						
						<th>
							采购数量
						</th>
						<th>
							入库数量
						</th>
						
						<th>
							入库单价
						</th>
						<th>
						金额
						</th>
						
						<th>
							供应商
						</th>
						<th>
							项目
						</th>
						
						
					</tr>
					<c:forEach items="${record}" var="result" varStatus="status">
						<tr>
						  <td>
                          
							
								<input type="checkbox" name="checkbox"
									value="${result.ioid}" gy="${result.gys}"/>
							
							
							</td>
							<td>
								<c:out value="${status.index+1}" />
							</td>
							<td>
								<c:out value="${result.ration_apply_id}" />
							</td>
							<td>
								<c:out value="${result.maname}" />
							</td>
							<td>
								<c:out value="${result.sqsl}" />
							</td>
							<td>
								<c:out value="${result.ionum}" />
							</td>
							
							<td>
								<c:out value="${result.ioprice}" />
							</td>
							<td>
								<c:out value="${result.iomoney}" />
							</td>
							<td>
								<c:out value="${result.gys}" />
							</td>
						
								
								<td><c:if test=" ${ empty result.pname}">未关联项目</c:if>
								<c:if test="${not empty result.pname}">${result.pname}</c:if>
								
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