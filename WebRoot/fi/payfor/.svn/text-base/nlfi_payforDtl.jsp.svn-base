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
<script src="<%= request.getContextPath() %>/jscripts/AltWindow/lhgdialog.min.js?self=true&skin=blue" type="text/javascript"></script>
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
	
	 <c:forEach var="res" begin="1" end="${ssize}" varStatus="s">
	   if(document.getElementById("ck${s.index}").checked)
		{
		  if(document.form1.rea${s.index}.value=='')
			  {
			     alert("请填写申请事由");
			     document.form1.rea${s.index}.focus();
			     return false;
			  }
		  
		  if(document.form1.money${s.index}.value=='')
			  {
			     alert("请填写申请金额");
			     document.form1.money${s.index}.focus();
			     return false;
			  }
			  
		}
	</c:forEach>
	
	
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
	
	
}
///////////////////////////////////////附件查看
function show(){
    $("#upTd").empty();
	$("#upTd").append("<a href='#' onclick='openDGMax(\"查看\",\"<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${accid}&folder=fiAcc&flag=accessory&mark=show&oldname=file\")'>"
			+"<img src='<%=request.getContextPath()%>/image/fileopen.png'/></a>");
}
//////////////////////////////////////////
</script>
	</head>
	<body>

		<script type="text/javascript">
${operationSign}</script>

		<form name="form1"
			action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!edit.do"
			method="post">
			<input type="hidden" name="editMod" value="${editMod}" />
			<input type="hidden" name="IsPostBack" value="${IsPostBack}" />
			<input type="hidden" name="id" value="${info.id}" />
			<input type="hidden" name="state" />

			<table class="table_border" style="width: 100%">

				<tr>
					<th align="center" style="width: 10%">
						收款单位:
					</th>
					<td style="width: 35%">
						<input type="text" name="gong" id="gong" readonly="readonly"
							value="${info.name}" class="textBox" />
						<input type="text" name="gongys" id="gongys" value="${info.sid}"
							class="hidden" />
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
							<option <c:if test="${info.pjdtype=='科技'}">selected</c:if>>
								科技
							</option>
							<option <c:if test="${info.pjdtype=='昆明'}">selected</c:if>>
								昆明
							</option>
							<option <c:if test="${info.pjdtype=='工程-内部'}">selected</c:if>>
								工程-内部
							</option>
							<option <c:if test="${info.pjdtype=='工程-外部'}">selected</c:if>>
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
									<c:if test="${info.paytype==paytype.key}">selected</c:if>>
									${paytype.value}
								</option>
							</c:forEach>
						</select>
					</td>
					<th align="center" style="width: 10%">
						申请人:
					</th>
					<td style="width: 35%">
						${info.pname}
					</td>
				</tr>
				<tr>
					<th align="center" style="width: 15%">
						申请金额:
					</th>
					<td width="35%" align="left">
						${info.money}
					</td>
					<th align="center" style="width: 10%">付款单位:</th>
					<td>
					${info.kjtype}
					</td>
				</tr>
				<!-- /////////////////////////////////////////附件查看 -->
				<tr>
					<th align="center" style="width:10%">附件列表:</th>
					<td colspan="3" id="upTd">
					<a href="#" onclick="openDGMax('查看','<%=request.getContextPath()%>/sysAdmin/Sys_uppic!display.do?f_id=${accid}&folder=fiAcc&flag=accessory&mark=show&oldname=file')">
					<img src="<%=request.getContextPath()%>/image/fileopen.png"/>
					</a>
		    		<!-- <div id="_container">  </div> -->
					</td>
               </tr>
          <!-- //////////////////////////////////////// -->
			</table>

			<br />
			<div class="divMod2">
				<div class="divMod1">
					支付明细
				</div>
				<table class="dataListTable" width="100%">
					<tr>



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

						<c:if test="${(info.payforstate=='3'||info.payforstate=='0')&&info.p_id==applyid}">
							<th width="8%">
								操作
							</th>
						</c:if>



					</tr>

					<c:forEach var="res" items="${slist}" varStatus="s">
						<tr>

							<td>
								${s.index+1}
							</td>

							<td>
								<input type="text" name="rea${res.id}" style="width: 98%"
									value="${res.applyreason}">
							</td>
							<td>
								<input type="text" name="money${res.id}" style="width: 98%"
									value="${res.money}">
							</td>

							<td>
								<input type="text" name="fuj${res.id}" style="width: 90%"
									value="${res.fujnum}">
							</td>
							<c:if test="${(info.payforstate=='3'||info.payforstate=='0')&&info.p_id==applyid}">
								<td>
									<a
										href="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor!delete.do?id=${res.id}&type=${type}"
										OnClick="javascript:return confirm('你确认要删除吗?');">删除</a>
								</td>
							</c:if>
							
						</tr>

					</c:forEach>

					<c:forEach var="res" begin="1" end="${ssize}" varStatus="s">

						<tr>
							<td>
								<input type="checkbox" name="ck${s.index}" id="ck${s.index}" onclick="open${s.index}();"
									value="1">
							</td>
							
							<td>
								<input type="text" name="rea${s.index}" style="width: 98%">
							</td>
							<td>
								<input type="text" name="money${s.index}" style="width: 98%"
									onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')"
									style="ime-mode:disabled">
							</td>

							<td>
								<input type="text" name="fuj${s.index}" style="width: 90%"
									onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')"
									style="ime-mode:disabled">
							</td>
							<td>
							</td>
						</tr>

					</c:forEach>

				</table>
			</div>
			<br />
			<div class="div-button">
				<c:if test="${type=='modify'}">
					<input type="button" value="保存不提交" onclick="sub1('0');" />

					<input type="button" value="保存且提交" onclick="sub1('1');" />
				</c:if>

				<c:if test="${type=='fenpei'}">
					<input type="button" value="提交" onclick="sub1('4');" />

				</c:if>

				<input type="reset" value="重置" />
				<input type="button" value="关闭" onclick="javascript:closeDG();" />
			</div>
			
			
			<script type="text/javascript">
			 <c:forEach var="res" begin="1" end="${ssize}" varStatus="s">
			document.getElementById("rea${s.index}").disabled='disabled';
		  document.getElementById("money${s.index}").disabled='disabled';
		  document.getElementById("fuj${s.index}").disabled='disabled';
		  </c:forEach>
		  </script>
		  
		  <div class="divMod2">
				<div class="divMod1">
					审批历史
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
								<c:out value="${result.pname}" />
							</td>
							<td>
								<c:out value="${payforstate[result.state]}" />
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
