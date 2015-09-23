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

<script language="javascript">

function sub1(id) {
	if (document.form1.gong.value=='') {
		alert("请选择收款单位");
		return false;
	} 
	
	
	
	if (document.form1.paytype.value=='') {
		alert("请选择付款方式");
		document.form1.paytype.focus();
		return false;
	} 
	
	if (!document.getElementById("ck1").checked&&!document.getElementById("ck2").checked&&!document.getElementById("ck3").checked&&!document.getElementById("ck4").checked&&!document.getElementById("ck5").checked) {
		alert("至少填写一条支付明细");
		
		return false;
	} 
	
	if(document.getElementById("ck1").checked)
		{
		  if(document.form1.rea1.value=='')
			  {
			     alert("请填写申请事由");
			     document.form1.rea1.focus();
			     return false;
			  }
		  
		  if(document.form1.money1.value=='')
			  {
			     alert("请填写申请金额");
			     document.form1.money1.focus();
			     return false;
			  }
			  
		}
	if(document.getElementById("ck2").checked)
		{
		  if(document.form1.rea2.value=='')
			  {
			     alert("请填写申请事由");
			     document.form1.rea2.focus();
			     return false;
			  }
		  
		  if(document.form1.money2.value=='')
			  {
			     alert("请填写申请金额");
			     document.form1.money2.focus();
			     return false;
			  }
			  
		}
	
	if(document.getElementById("ck3").checked)
		{
		  if(document.form1.rea3.value=='')
			  {
			     alert("请填写申请事由");
			     document.form1.rea3.focus();
			     return false;
			  }
		  
		  if(document.form1.money3.value=='')
			  {
			     alert("请填写申请金额");
			     document.form1.money3.focus();
			     return false;
			  }
			  
		}
	
	if(document.getElementById("ck3").checked)
		{
		  if(document.form1.rea3.value=='')
			  {
			     alert("请填写申请事由");
			     document.form1.rea3.focus();
			     return false;
			  }
		  
		  if(document.form1.money3.value=='')
			  {
			     alert("请填写申请金额");
			     document.form1.money3.focus();
			     return false;
			  }
			  
		}
	
	if(document.getElementById("ck5").checked)
		{
		  if(document.form1.rea5.value=='')
			  {
			     alert("请填写申请事由");
			     document.form1.rea5.focus();
			     return false;
			  }
		  
		  if(document.form1.money5.value=='')
			  {
			     alert("请填写申请金额");
			     document.form1.money5.focus();
			     return false;
			  }
			  
		}
	
		document.form1.state.value = id;
		document.form1.submit();
	

}

function open1()
{
	if(document.getElementById("ck1").checked)
		{
		  document.getElementById("rea1").disabled='';
	      document.getElementById("money1").disabled='';
	      document.getElementById("fuj1").disabled='';
	      
	     }
	else
		{
		  document.form1.rea1.value='';
		  document.form1.money1.value='';
		  document.form1.fuj1.value='';
		 
		  document.getElementById("rea1").disabled='disabled';
		  document.getElementById("money1").disabled='disabled';
		  document.getElementById("fuj1").disabled='disabled';
		 
		}
	
	
}

function open2()
{
	if(document.getElementById("ck2").checked)
		{
		  document.getElementById("rea2").disabled='';
	      document.getElementById("money2").disabled='';
	      document.getElementById("fuj2").disabled='';
	      
	     }
	else
		{
		  document.form1.rea2.value='';
		  document.form1.money2.value='';
		  document.form1.fuj2.value='';
		  
		  document.getElementById("rea2").disabled='disabled';
		  document.getElementById("money2").disabled='disabled';
		  document.getElementById("fuj2").disabled='disabled';
		 
		}
	
	
}

function open3()
{
	if(document.getElementById("ck3").checked)
		{
		  document.getElementById("rea3").disabled='';
	      document.getElementById("money3").disabled='';
	      document.getElementById("fuj3").disabled='';
	      
	     }
	else
		{
		  document.form1.rea3.value='';
		  document.form1.money3.value='';
		  document.form1.fuj3.value='';
		  
		  document.getElementById("rea3").disabled='disabled';
		  document.getElementById("money3").disabled='disabled';
		  document.getElementById("fuj3").disabled='disabled';
		 
		}
	
	
}

function open4()
{
	if(document.getElementById("ck4").checked)
		{
		  document.getElementById("rea4").disabled='';
	      document.getElementById("money4").disabled='';
	      document.getElementById("fuj4").disabled='';
	      
	     }
	else
		{
		  document.form1.rea4.value='';
		  document.form1.money4.value='';
		  document.form1.fuj4.value='';
		  
		  document.getElementById("rea4").disabled='disabled';
		  document.getElementById("money4").disabled='disabled';
		  document.getElementById("fuj4").disabled='disabled';
		 
		}
	
	
}

function open5()
{
	if(document.getElementById("ck5").checked)
		{
		  document.getElementById("rea5").disabled='';
	      document.getElementById("money5").disabled='';
	      document.getElementById("fuj5").disabled='';
	      
	     }
	else
		{
		  document.form1.rea5.value='';
		  document.form1.money5.value='';
		  document.form1.fuj5.value='';
		  
		  document.getElementById("rea5").disabled='disabled';
		  document.getElementById("money5").disabled='disabled';
		  document.getElementById("fuj5").disabled='disabled';
		 
		}
	
	
}       /* 文件上传 */
    function upload(){
        $("#upTd").empty();
		$("#upTd").append("<a href='#' onclick='openDGMax(\"上传附件\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!uploadFiles.do?f_id=${accid}&folder=fiAcc&flag=accessory\")'>"
				+"<img src='<%=request.getContextPath()%>/image/upload.png'/></a>");
    }
</script>
	</head>
	<body>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!${editMod}.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${record.id}" />
			<input type="hidden" name="state" />
			<table class="table_border" style="width: 100%">

				<tr>
					<th align="center" style="width: 10%">
						收款单位:
					</th>
					<td style="width: 35%">
						<input type="text" name="gong" id="gong" readonly="readonly"
							value="${searchMap.gong}" class="textBox" />
						<input type="text" name="gongys" id="gongys"
							value="${searchMap.gongys}" class="hidden" />
						<input type="button"
							onclick="openSelect('选择数据来源','<%=request.getContextPath()%>/supplier/Supplier!searchList.do?pageType=select','gongys','gong');"
							value="选择" />
					</td>
					<th align="center" style="width: 10%">
						配给类型:
					</th>
					<td style="width: 35%">
						<select name="pjdtype" style="width: 80%;">
							<option>
								未选择
							</option>
							<option value="科技">
								科技
							</option>
							<option value="昆明">
								昆明
							</option>
							<option value="工程-内部">
								工程-内部
							</option>
							<option value="工程-外部">
								工程-外部
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th align="center" style="width: 15%">
						付款方式:
					</th>
					<td width="35%" align="left">
						<select name="paytype" class="dropdownlist">
							<c:forEach items="${paytype}" var="paytype">
								<option value="${paytype.key}"
									<c:if test="${searchmap.paytype==paytype.key}">selected</c:if>>
									${paytype.value}
								</option>
							</c:forEach>
						</select>
					</td>
					<th align="center" style="width: 10%">上传附件：</th>
					<td style="width: 35%" id="upTd">
					<input type="button" value="上传附件" onclick="upload();">
					</td>
				</tr>

			</table>

			<br />
			<div class="divMod2">
				<div class="divMod1">
					支付明细
				</div>
				<table class="dataListTable" width="100%">
					<tr>
					
                        <th width="5%">
                        </th>

						<th width="5%">
							序号
						</th>

						<th>
							申请事由
						</th>
						<th width="15%">
							金额
						</th>
						<th width="8%">
							附件数
						</th>



					</tr>

					<tr>
					    <td>
					      <input type="checkbox" name="ck1" id="ck1" onclick="open1();" value="1">
					    </td>
						<td>
							1
						</td>

						<td>
							<input type="text" name="rea1" style="width: 98%">
						</td>
						<td>
							<input type="text" name="money1" style="width: 98%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>

						<td>
							<input type="text" name="fuj1" style="width: 90%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>
					</tr>
					
					<tr>
					    <td>
					      <input type="checkbox" name="ck2" id="ck2" onclick="open2();" value="1">
					    </td>
						<td>
							2
						</td>

						<td>
							<input type="text" name="rea2" style="width: 98%">
						</td>
						<td>
							<input type="text" name="money2" style="width: 98%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>

						<td>
							<input type="text" name="fuj2" style="width: 90%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>
					</tr>
					
					
					<tr>
					    <td>
					      <input type="checkbox" name="ck3" id="ck3" onclick="open3();" value="1">
					    </td>
						<td>
							3
						</td>

						<td>
							<input type="text" name="rea3" style="width: 98%">
						</td>
						<td>
							<input type="text" name="money3" style="width: 98%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>

						<td>
							<input type="text" name="fuj3" style="width: 90%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>
					</tr>
					
					<tr>
					    <td>
					      <input type="checkbox" name="ck4" id="ck4" onclick="open4();" value="1">
					    </td>
						<td>
							4
						</td>

						<td>
							<input type="text" name="rea4" style="width: 98%">
						</td>
						<td>
							<input type="text" name="money4" style="width: 98%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>

						<td>
							<input type="text" name="fuj4" style="width: 90%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>
					</tr>
					
					<tr>
					    <td>
					      <input type="checkbox" name="ck5" id="ck5" onclick="open5();" value="1">
					    </td>
						<td>
							5
						</td>

						<td>
							<input type="text" name="rea5" style="width: 98%">
						</td>
						<td>
							<input type="text" name="money5" style="width: 98%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>

						<td>
							<input type="text" name="fuj5" style="width: 90%"   onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  style="ime-mode:disabled">
						</td>
					</tr>
					
					

				</table>
			</div>
			<br />
			<div class="div-button">
				<input type="button" value="保存不提交" onclick="sub1('0');"
					style="${btnDisplay}" />
			    
				<input type="button" value="保存且提交" onclick="sub1('1');"
					style="${btnDisplay}" />
					
				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>
			
			<script type="text/javascript">
			document.getElementById("rea1").disabled='disabled';
		  document.getElementById("money1").disabled='disabled';
		  document.getElementById("fuj1").disabled='disabled';
		  
		  document.getElementById("rea2").disabled='disabled';
		  document.getElementById("money2").disabled='disabled';
		  document.getElementById("fuj2").disabled='disabled';
		  
		  document.getElementById("rea3").disabled='disabled';
		  document.getElementById("money3").disabled='disabled';
		  document.getElementById("fuj3").disabled='disabled';
		  
		  document.getElementById("rea4").disabled='disabled';
		  document.getElementById("money4").disabled='disabled';
		  document.getElementById("fuj4").disabled='disabled';
		  
		  document.getElementById("rea5").disabled='disabled';
		  document.getElementById("money5").disabled='disabled';
		  document.getElementById("fuj5").disabled='disabled';
			</script>
			
		</form>
	</body>
</html>
