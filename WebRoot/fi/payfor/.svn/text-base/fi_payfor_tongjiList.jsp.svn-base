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
	</head>
	<body>
		<form id="searchForm"
			action="<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor_audit!tongji.do"
			method="post">
			<div class="divMod2">
				<div class="divMod1">
					查询条件
				</div>
				<table width="99%" class="search_border">
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
						<th>

						</th>
						<td>

						</td>
					</tr>
				</table>
			</div>
			<div class="div-button">
				<input type="reset" value="重置" class="button" />
				<input type="submit" value="查询" class="button" />
			</div>
			<div class="clear" style="height: 0px;"></div>
		</form>
		<div class="divMod2">
			
			<table class="dataListTable" width="100%">
				<tr>

					<th width="5%">
						序号
					</th>
					<th>
						供应商
					</th>

					<th>
						总金额
					</th>

					<th>
						已付金额
					</th>
					<th>
						未付金额
					</th>
				</tr>
				<c:forEach var="res" items="${reslist}" varStatus="s">
					<tr>
                        <td>
                         ${s.index+1 }
                        </td>
						<td>
							<a href="#" onclick="openDG('查看详细信息','<%=request.getContextPath()%>/fi/fi_payfor/Fi_payfor_audit!lookpay.do?id=${res.gysid}','fi_payforDtl')">${res.name}</a>

						</td>
						<td>
							${res.total}
						</td>
						<td>
							${res.pay}
						</td>
						<td>
							${res.nopay}
						</td>
					</tr>
				</c:forEach>



			</table>
		</div>

	</body>

</html>