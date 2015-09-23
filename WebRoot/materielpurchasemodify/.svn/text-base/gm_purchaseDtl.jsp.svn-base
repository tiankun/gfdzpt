<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c-1_0-rt.tld"  prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
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
<script type="text/javascript" src="<%= request.getContextPath() %>/jscripts/Validator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/jscripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<script type="text/javascript">
function sub() {
	

		if (!Validator.Validate(document.form1, 3)) {
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
if (confirm("确定提交?")) {
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

function chec() {
	if (document.getElementById("check").checked) {
		selectAll();
	} else {
		unselectAll();
	}
}
</script>

<script type="text/javascript">${operationSign}</script>

	<form name="form1" action="<%=request.getContextPath()%>/materielpurchase/Gm_purchase!sub1.do" method="post">
	<input type="hidden" name="editMod" value="${editMod}"/>
	<input type="hidden" name="IsPostBack" value="${IsPostBack}"/>	
	<input type="hidden" name="id" value="${record.id}"/>
	<input type="hidden" name="opinionid" />
	
	
	
	
	

		<table class="table_border" style="width:100%">
		
            <tr>
			<th align="center" style="width:10%">项目:</th>
			<td style="width:35%">
			<input type="text" name="prj_id" value="${record.proname}"   style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">申请部门/申请人：</th>
			<td style="width:35%">
			${record.branchname}<strong>/</strong>${record.applyname}
			</td>
			</tr>
            
            <tr>
			<th align="center" style="width:10%">配给执行单:</th>
			<td style="width:35%">
			<input type="text" name="ration_apply_id" value="${record.ration_apply_id}"  dataType="Require"   style="width:95%" class="textBoxNoBorder"/></td>
            <th align="center" style="width:10%">编号:</th>
			<td style="width:35%">
			<input type="text" name="dh" value="${record.dh}"  maxLength="200"  style="width:95%" class="textBoxNoBorder"/></td>
            </tr>
            
            <tr>
			<th align="center" style="width:10%">采购执行人:</th>
			<td style="width:35%">
			<input type="text" name="executename" value="${record.executename}"  maxLength="20"  style="width:95%" class="textBoxNoBorder"/></td>
			<th align="center" style="width:10%">编制时间:</th>
			<td style="width:35%">
			<input type="text" name="applyname" value="${record.apply_date}"   style="ime-mode:disabled"  style="width:95%" class="textBoxNoBorder"/></td>
			
            </tr>
            
            <tr>
            <th align="center" style="width:10%">总金额(小写):</th>
			<td style="width:35%">
			${record.xiaoxje}
			</td>
			<th align="center" style="width:10%">总金额(大写):</th>
			<td style="width:35%">
			${record.daxje}
			</td>
			
            </tr>
            
            <tr>
						<td height="50" colspan="4">
							<strong>合同扫描件上传：</strong>
							<table width="250px" border="1" cellspacing="0" cellpadding="0"
								id="zpf">
								<c:if test="${fn:length(record.path)>0}">
									<c:set var="zpf_sfs" value='${fn:split(record.path,";")}' />
									<c:forEach items="${zpf_sfs}" var="zpf_sf">
										<tr>
											<td width="60%">
												<a
													href="${pageContext.request.contextPath}/upFile/materielpurchase/${zpf_sf}"
													target="_blank">${zpf_sf}</a>
												<input type="hidden" name="fujian" value="${zpf_sf}">
											</td>
											
										</tr>
									</c:forEach>
								</c:if>
							</table>
							<span id="spanSWFUploadButton"></span>
						</td>

					</tr>
            
            
            
		</table>
		
		<br/>
		<div class="divMod2">
	       <div class="divMod1">已批准材料</div>
		<table class="dataListTable" width="100%">
			<tr>
		
		   <th></th>
           <th>序号</th>
          
           <th>物资名称</th>
           <th>品牌</th>
           <th>申请采购数量</th>
           <th>采购单价</th>
           <th>供应商</th>
           
          
          
           
           <th>要求到货日期</th>
           
		       
			</tr>
			<c:forEach items="${mlist}" var="result" varStatus="status">
				<tr>
	 <td><input type="checkbox" name="checkbox"
									value="${result.materiel_id}" /></td>
	  		
      <td><c:out value="${status.index+1}" /></td>
     
      <td><c:out value="${result.mnate}" /></td>
      <td><c:out value="${result.bname}" /></td>
      
      <td>
      <input type="text" name="bgsl${result.materiel_id}" value="${result.sqsl}" style="width: 40%"/>
      
      </td>
      <td>
      <input type="text" name="bgprice${result.materiel_id}" value="${result.price}" style="width: 40%"/>
      </td>
      <td>
      <input type="text" name="gongys${result.materiel_id}"
									id="gongys${result.materiel_id}" readonly="readonly" value="${result.gys}"
									class="textBox" />
								<input type="text" name="bggys${result.materiel_id}"
									id="bggys${result.materiel_id}" value="${result.gongys}"
									class="hidden" />
								<input type="button"
									onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','bggys${result.materiel_id}','gongys${result.materiel_id}');"
									value="选择" />
     </td>
     
      
     
      <td><c:out value="${result.yaoqdhrq}" /></td>
  
      
      
      </tr>
			</c:forEach>
			
			<tr>
			  <td colspan="8">
			    备注：<br/><textarea rows="3" cols="70" name="note"></textarea>
			  </td>
			</tr>
		</table>
	</div>
		
		
		
		<br/>
		<div class="div-button">
		    <input type="button" name="btn" value="提交变更" onclick="sub();" />
			<input type="reset" value="重置" />
			<input type="button" value="关闭" onClick="javascript:closeDG();"/>
		</div>
		
		<br />

			<div class="divMod2">
				<div class="divMod1">
					审批意见历史
				</div>
				<table class="dataListTable" width="100%">
					<tr>


						<th>
							序号
						</th>

						<th>
							审批人
						</th>
						<th>
							审批结果
						</th>


						<th>
							审批时间
						</th>

						<th>
							审批意见
						</th>



					</tr>
					<c:forEach items="${splist}" var="result" varStatus="status">
						<tr>

							<td>
								<c:out value="${status.index+1}" />

							</td>

							<td>
								<c:out value="${result.person}" />
							</td>
							<td>
								<c:out value="${purchase_state[result.state]}" />
							</td>



							<td>
								<c:out value="${result.audit_date}" />
							</td>
							<td>
								<c:out value="${result.opinion}" />
							</td>




						</tr>
					</c:forEach>
				</table>
			</div>
	</form>
</body>
</html>
