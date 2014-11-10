<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/common/bootstrap-header.jsp"%>
</head>
<body>
	<div>
		<form class="form-horizontal" role="form" id="queryForm"
			action="fi/fi-item!search.action" method="post">
			<div class="form-group">
				<label class="col-md-1 control-label"
					for="searchMap.flt_i_and_eqS_description">描述</label>
				<div class="col-md-3">
					<input type="text" class="form-control"
						name="searchMap.flt_i_and_eqS_description" placeholder="描述"
						value="<s:property value='searchMap.flt_i_and_eqS_description' />">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-1 control-label"
					for="searchMap.flt_i_and_geN_income">收入大于</label>
				<div class="col-md-3">
					<input type="text" class="form-control"
						name="searchMap.flt_i_and_geN_income" placeholder="收入"
						value="<s:property value='searchMap.flt_i_and_geN_income' />">
				</div>
				
				<label class="col-md-1 control-label"
					for="searchMap.flt_i_and_geN_output">支出大于</label>
				<div class="col-md-3">
					<input type="text" class="form-control"
						name="searchMap.flt_i_and_geN_output" placeholder="支出"
						value="<s:property value='searchMap.flt_i_and_geN_output' />">
				</div>
			</div>
			<div class="form-group">
			<div class="col-md-1">
			
			</div>
			<button id="queryBtn" class="btn btn-default" type="button">查询</button>
			<a href="fi/fi-item!list.action" class="btn btn-primary" >财务列表</a>
			</div>
		</form>
	</div>
	<div id='fiItem'>
		<form class="form-inline" id="itemForm" role="form"
			action="fi/fi-item!save.action" method="post">
			<input type="hidden" name="monthStr"
				value="<s:property value='monthStr' />">
			<table id="fiItemTable" class="table table-striped table-bordered">
				<tr>
					<th>日期</th>
					<th>摘要</th>
					<th>收入</th>
					<th>支出</th>
					<th>余额</th>
					<th>经手人</th>
				</tr>
				<s:iterator value="items" var="i">
					<tr class="item">
						<td><s:property value="#i.dat" />
						</td>
						<td><s:property
									value="#i.description" />
						</td>
						<td class="income"><s:property
									value="#i.income" />
						</td>
						<td class="output"><s:property
									value="#i.output" />
						</td>
						<td class="rest"></td>
						<td><s:property
									value="#i.handler" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</form>
	</div>
	<script>
		$(function() {


			$("#queryBtn").click(function() {
				$("#queryForm").submit();
			});
		});
	</script>
</body>
</html>
