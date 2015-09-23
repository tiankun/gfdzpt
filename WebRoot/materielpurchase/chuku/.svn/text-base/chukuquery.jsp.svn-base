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
			href="<%=request.getContextPath()%>/css/main-in.css"
			type="text/css"></link>
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

		var obj = document.form1.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "checkbox") {
				if (obj[i].checked == true) {
					flag = false;
					ids = ids + ";" + obj[i].value;
				}

			}
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

function pil(){
	var ids = "";
    var flag = true;
	
	var obj = document.searchForm.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "checkbox"){
			if (obj[i].checked==true) 
				{
				  flag=false;
				  ids = ids + ";" +obj[i].value;
				}
			
		}
	}
	
	 if(flag)
		{
		  alert("请至少选择一条记录！");
		  return false;
		}
     openDG('批量出库','<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!pilck.do?ids='+ids,'gm_carapplyDtl')
}
</script>

	</head>




	<body>
		<form id="searchForm"
			action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!chukquery.do"
			method="post" name="form1">
			<input type="hidden" name="id" />
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">

					<tr>
						<th>
							项目:
						</th>
						<td>
							<input type="text" id="proname" name="proname"
								value="${searchMap.proname}" class="textBox" />
							<input type="text" id="prj_id" name="prj_id" dataType="Double"
								require="false" class="hidden" />
							<input type="button"
								onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','prj_id','proname');"
								value="选择" />
						</td>
						<th>
							配给单:
						</th>
						<td>
							<input type="text" value="${searchMap.ration_apply_id}"
								name="ration_apply_id" style="width: 95%" class="textBox" />
						</td>
					</tr>
					<tr>
						<th>
							物资名称:
						</th>
						<td>
							<input type="text" name="maname" value="${searchMap.maname}"
								class="textBox" />
							<input type="text" id="materiel_id" name="materiel_id" value=""
								dataType="Double" require="false" class="hidden" />
							<input type="button"
								onclick="openSelect('branch_select','<%=request.getContextPath()%>/materiel/Materiel!searchList.do?pageType=select','materiel_id','maname');"
								value="选择" />
						</td>

						<th>
							品牌:
						</th>
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



					</tr>
					<tr>
						<th>
							供货商:
						</th>
						<td>
							<input type="text" name="gong" id="gong"
								value="${searchMap.gong}" class="textBox" />
							<input type="text" name="gongys" id="gongys" value=""
								class="hidden" />
							<input type="button"
								onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','gongys','gong');"
								value="选择" />
						</td>

						<th></th>
						<td>


						</td>
					</tr>



				</table>
			</div>
			<div class="div-button">
              <a href="#" class="link" onclick="pil();">出库</a>
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
						<th width="40">
							序号
						</th>
						<th>
							项目
						</th>
						<th>
							配给申请单
						</th>
						<th>
							材料名称
						</th>
						<th>
							品牌
						</th>
						<th>
							供货商
						</th>
						<th width="60">
							库存量
						</th>

						<th width="80">
							已出库数量
						</th>

						<th width="40">
							操作
						</th>
					</tr>
					<c:forEach items="${record}" var="result" varStatus="status">
						<tr>
							<td>
                            <input type="checkbox" name="checkbox" value="${result.id}" />
							</td>


							<td>
								<c:out value="${status.index+1}" />
							</td>
							<td>
								${result.prname}
							</td>
							<td>
								<c:out value="${result.ration_apply_id}" />
							</td>
							<td>
								<c:out value="${result.maname}" />
							</td>
							<td>
								<c:out value="${result.brname}" />
							</td>
							<td>
								<c:out value="${result.gyname}" />
							</td>
							<td>
								<c:out value="${result.kck}" />
							</td>
							<td>
								<c:out value="${result.cksl}" />
							</td>



							<td>
								<a href="#"
									onClick="openDG('出库','<%=request.getContextPath()%>/materielpurchase/Gm_purchase_modify!chuku.do?id=${result.id}','gm_purchaseDtl')">出库</a>

							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div id="PageUpDnDiv"
				style="PADDING-TOP: 5px; padding-bottom: 5px; text-align: center">
				<span>共 ${page.lastPageNumber} 页/第 </span><span id="curPage">${page.thisPageNumber}</span><span>
					页</span>
				<a onclick="changePage(1)" class="linkPage">首页</a><a
					onclick="changePage(${page.previousPageNumber})" class="linkPage">上一页</a>
				<a onclick="changePage(${page.nextPageNumber})" class="linkPage">下一页</a>
				<a onclick="changePage(${page.lastPageNumber})" class="linkPage">尾页</a>
			</div>
	</body>
	<script type="text/javascript">
function changePage(page) {
	if (page == undefined || page == null)
		page = $("#curPage").text();
	document.getElementById('searchForm').insertAdjacentHTML("beforeEnd",
			"<input type=\"hidden\" name=\"pageno\" value=\"" + page + "\"/>");
	document.getElementById('searchForm').submit();
}
</script>
	</form>
</html>