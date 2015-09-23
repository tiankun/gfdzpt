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
			src="<%=request.getContextPath()%>/jscripts/Validator.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js">
</script>


	</head>

	<style>
.selectTable {
	BORDER-COLLAPSE: collapse;
	border: 1px solid #eee;
	line-height: 22px;
	margin: 2px auto;
}

.selectTable td {
	border: 1px solid #c5ddf1;
	text-align: center;
}

.selectTable th {
	background: #eee;
	border: 1px solid #bbbec3;
	height: 20px;
	text-align: center;
	font: bold 14px/ 28px simsun;
}
</style>
	<script type="text/javascript">
function sub() {
	if (confirm("确定添加?")) {
		if (document.form1.name.value == '') {
			alert("请选择收货人");
			document.form1.name.focus();
			return false;
		}

		if (document.form1.recv_call.value == '') {
			alert("请填写收货人电话");
			document.form1.recv_call.focus();
			return false;
		}

		if (document.form1.bh.value == '') {
			alert("请填写编号");
			document.form1.bh.focus();
			return false;
		}

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

		document.form1.submit();
	}
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

var firTime = 0;

function reRun() {
	if (firTime > 0 && (new Date().getTime() - firTime) > 300) {
		getresult();
		firTime = 0;
	}
}
setInterval(reRun, 100);

function getresult() {
	var name = $("#name").val();
	var shortcode = $("#shortcode").val();
	var brandname = $("#brandname").val();
	var kindname = $("#kindname").val();
	if (name == "" && shortcode == "" && brandname == "" && kindname == "") {
		$("#selTb").css("display", "none");
		return;
	}

	var url = "/gfdzpt/materiel/Materiel!getData.do";
	$
			.ajax( {
				type : "POST",
				url : url,
				dataType : "json",

				data : {
					"name" : name,
					"shortcode" : shortcode,
					"brandname" : brandname
				},
				cache : false,
				success : function(data) {
					$("#content tbody tr").remove();
					var row = "";
					$
							.each(
									data,
									function(key, value) {

										row = row
												+ "<tr id='"
												+ value.id
												+ "'>"
												+ "<td id='kindname' kind_id='"
												+ value.kind_id
												+ "'>"
												+ value.kindname
												+ "</td>"
												+ "<td id='brandname' brand_id='"
												+ value.brand_id
												+ "'>"
												+ value.brandname
												+ "</td>"
												+ "<td id='name'>"
												+ value.name
												+ "</td>"
												+ "<td >"
												+ value.model
												+ "</td>"
												+ "<td id='unit'>"
												+ value.unit
												+ "</td>"
												+ "<td  title='"+value.parameter+"'>"
												+ value.content
												+ "</td>"
												+ "<td id='sqsl'><input type='text' id='Cnum' subid='Cnum' style='ime-mode:disabled;width:90%;' value=''/></td>"
												+ "<td id='note'><input type='text' style='width:90%;' subid='note' value=''/></td></tr>";
									});

					$("#content tbody").append(row);
					var top = $("#addTb").offset().top + $("#addTb").height;
					var left = $("#addTb").offset().left;
					$("#selTb").css("left", left);
					$("#selTb").css("top", top);
					$("#selTb").css("display", "block");
				},
				error : function(info) {
					alert("未找到属性信息");
				}

			});
}

$(document)
		.ready(function() {
			//當tbd裡所有的input
				$('#tbd :input')
						.live("keyup", function(e) {
							//取得自訂屬性的名稱
								var mySubId = $(this).attr('subid');
								//取得該物件的index
								var idx = $(
										'#tbd :input[subid="' + mySubId + '"]')
										.index(this);
								//取得相關物件的個數
								var sidlen = $('#tbd :input[subid="' + mySubId + '"]').length;
								//設定新的index
								var newidx;
								switch (e.which) {
								case 38: //上
									newidx = idx - 1;
									$(this).parent().parent().css(
											"background-color", "");
									if (idx == 0) {
										var tgEmt = $('#shortcode');
										tgEmt.select();
										tgEmt.focus();
									} else {
										toFocus(newidx, mySubId);
									}
									break;
								case 40: //下
									newidx = idx + 1;
									$(this).parent().parent().css(
											"background-color", "");
									toFocus(newidx, mySubId);
									break;
								case 13: //回车键
									var tr = $(this).parent().parent().clone();
									var _trid = $(tr).attr("id");
									if ($("#tbd1 tr[id='" + _trid + "']").length) {
										alert("请勿重复添加。");
									} else {
										if ($(this).val().length < 1) {
											alert("请录入数字。");
											return;
										}
										tr
												.append($("<td><input type='button' value='删除' OnClick='$(this).parent().parent().remove();'/></td>"));
										tr.appendTo("#tbd1");
										$("#selTb").css("display", "none");
										$('#shortcode').select();
										$('#shortcode').focus();
									}
									break;
								default:
									newidx = idx;
									var id = $(this).attr("subid");
									if (id == "Cnum") {
										var tmptxt = $(this).val();
										$(this).val(
												tmptxt.replace(/[^\d]/g, ''));
									}
									return;
								}
							});
				$('#addTb .textBox').live("keyup", function(e) {
					switch (e.which) {
					case 38: //上
							break;
						case 40: //下
							var tgEmt = $('#tbd :input[subid="Cnum"]:eq(0)')
							tgEmt.select();
							tgEmt.focus();
							$(tgEmt).parent().parent().css("background-color",
									"#E6E6FA");
							break;
						default:
							firTime = new Date().getTime();
							return;
						}
					});
				$('#content1 #Cnum').live("keyup", function(e) {

					var tmptxt = $(this).val();
					$(this).val(tmptxt.replace(/[^\d]/g, ''));

				});
			})
function toFocus(newidx, mySubId) {
	var tgEmt = $('#tbd :input[subid="' + mySubId + '"]:eq(' + newidx + ')')
	tgEmt.select();
	tgEmt.focus();
	$(tgEmt).parent().parent().css("background-color", "#E6E6FA");
}

function saveTable(id) {
	var rowData = '[';
	$("#tbd1 tr").each(
			function(i, n) {
				rowData = rowData + '{"materiel_id":"' + $(this).attr("id")
						+ '",';
				$(this).find("td").each(
						function(i) {
							if ($(this).find("input[type='text']").length > 0) {
								if ($(this).attr("id"))
									rowData = rowData
											+ '"'
											+ $(this).attr("id")
											+ '":"'
											+ $(this)
													.find("input[type='text']")
													.val() + '",';
							} else {
								if ($(this).attr("brand_id"))
									rowData = rowData + '"brand_id":"'
											+ $(this).attr("brand_id") + '",';
							}
						});
				rowData = rowData.substring(0, rowData.length - 1) + '},';
			});
	rowData = rowData.substring(0, rowData.length - 1) + "]";
	if (confirm("确定提交物料采购申请")) {
		if (rowData == ']') {
			alert("请选择材料");
			return false;
		}

		if (document.form1.bh.value == '') {
			alert("请输入编号");
			document.form1.bh.focus();
			return false;
		}
		
		if (document.form1.proname.value == '') {
			alert("请选择项目");
			
			return false;
		}

		//alert(rowData);
		document.form1.rowData.value = rowData;
		document.form1.pr_plan_state.value = id;
		document.form1.submit();
	}

}

function changeFirTime() {
	firTime = new Date().getTime();
}
</script>
	<script type="text/javascript">
${operationSign}</script>
	<body>
		<form
			action="<%=request.getContextPath()%>/pr/plan/Pr_plan!saveplan.do"
			method="post" name="form1" id="form1">
            <input type="hidden" name="pr_plan_state" >
			<div class="divMod2">
				<div class="divMod1">
					项目计划信息
				</div>
				<table width="99%" class="search_border">
					
					<tr>
						<th align="center" style="width: 10%">
							编号:
						</th>
						<td style="width: 35%">
							<input type="text" name="bh" maxLength="20" style="width: 95%"
								class="textBox" />
						</td>
						<th align="center" style="width: 10%">
							计划名称:
						</th>
						<td style="width: 35%">
							<input type="text" name="name"  style="width: 95%"
								class="textBox" />
						</td>
					</tr>
					<tr>
						<th align="center" style="width: 15%">
							计划采购月:
						</th>
						<td style="width: 35%">
						    <input type="text" id="purchase" name="purchase" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})" class="Wdate"/>

						</td>
						<th>
							项目:
						</th>
						<td>
							<input type="text" id="proname" name="proname"
								value="${searchMap.proname}" readonly="readonly" class="textBox" />
							<input type="text" id="prj_id" name="prj_id"
								value="${searchMap.prj_id}" dataType="Double" require="false"
								class="hidden" />
							<input type="button"
								onclick="openSelect('branch_select','<%=request.getContextPath()%>/pr/project/Pr_project!searchList.do?pageType=select','prj_id','proname');"
								value="选择" />
						</td>
					</tr>
					<tr>
					 <th>
					  备注：
					</th>
					  <td colspan="3">
					   <textarea rows="3" style="width: 98%" name="note"></textarea>
					  </td>
					</tr>
				</table>
			</div>

			<div class="divMod2" style="margin-top: 3px;">
				<div class="divMod1">
					添加物资
				</div>
				<table id="addTb" width="99%">
					<input type="hidden" name="rowData" />
					<tr>
						<th width="9%">
							简码:
						</th>
						<td width="19%">
							<input type="text" name="shortcode" id="shortcode"
								style="width: 95%" value="" class="textBox" />
						</td>
						<th width="5%">
							名称:
						</th>
						<td width="23%">
							<input type="text" id="name" name="name" style="width: 95%"
								value="" class="textBox" />
						</td>
					</tr>
					<tr>
						<th width="13%">
							类型:
						</th>
						<td width="14%">
							<input type="text" name="kind_id" id="kind_id" value=""
								class="hidden" />
							<input type="text" name="kindname" id="kindname" value=""
								class="textBox" />
							<input type="button"
								onClick="openSelect('选择材料类型','/gfdzpt/materiel/Ma_kind!searchList.do?pageType=select&detailname=pr_planDtl','kind_id','kindname');"
								value="选择" />
						</td>
						<th width="3%">
							品牌:
						</th>
						<td width="14%">
							<input type="text" name="brandname" id="brandname"
								onChange="gettime();" value="" class="textBox" />
							<input type="text" name="brand_id" id="brand_id" value=""
								class="hidden" />
							<input type="button"
								onClick="openSelect('选择材料类型','/gfdzpt/materiel/Ma_brand!searchList.do?pageType=select&detailname=pr_planDtl','brand_id','brandname');"
								value="选择" />
						</td>
					</tr>
				</table>
				<script type="text/javascript">
$("#addTb #shortcode").select();
$("#addTb #shortcode").focus();</script>
			</div>


			<div id="selTb"
				style="height: 200px; width: 96%; overflow-y: auto; display: none; overflow-x: hidden; position: absolute; z-index: 10; border: 1px solid #000; background-color: #fff;">
				<table id="content" class="selectTable" width="100%">
					<thead>
						<th width="10%">
							类型
						</th>
						<th width="10%">
							品牌
						</th>
						<th width="10%">
							名称
						</th>
						<th width="10%">
							规格型号
						</th>
						<th width="5%">
							计量单位
						</th>
						<th width="20%">
							产品参数
						</th>
						<th width="8%">
							数量
						</th>
						<th width="10%">
							说明
						</th>
					</thead>
					<tbody id="tbd">
					</tbody>
				</table>
			</div>

			<table id="content1" class="dataListTable" width="100%">
				<caption
					style="background: #B8DDF8; border: 1px solid #bbbec3; height: 20px; text-align: center; font: bold 14px/ 28px simsun;">
					已选定物资
				</caption>
				<thead>
					<th>
						类型
					</th>
					<th>
						品牌
					</th>
					<th>
						名称
					</th>
					<th>
						规格型号
					</th>
					<th>
						计量单位
					</th>
					<th>
						产品参数
					</th>
					<th>
						数量
					</th>
					<th>
						说明
					</th>
					<th class="btnCol">
						操作
					</th>
				</thead>
				<tbody id="tbd1">
				</tbody>
			</table>
			<div align="center">
				<input type="button" value="保存且上报" onClick="saveTable('1');" />
				<input type="button" value="保存不上报" onClick="saveTable('0');" />
			</div>
		</form>

	</body>
</html>