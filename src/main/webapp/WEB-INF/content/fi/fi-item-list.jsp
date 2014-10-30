<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<script src='https://cdn.firebase.com/js/client/1.1.1/firebase.js'></script>

  </head>
  
  <body>
	<div>
		<form class="form-horizontal" role="form" id="queryForm" action="fi/fi-item!list.action">
			<div class="row" id="answerDiv">
				<!-- /.col-lg-6 -->
				<div class="col-lg-6">
					<div class="input-group">
						<input type="month" class="form-control" id="month" name="month"
							placeholder="日期" value="<s:property value='month' />"><span class="input-group-btn">
							<button id="queryBtn" class="btn btn-default" type="button">查询</button>
						</span>
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->
		</form>
	</div>
	<div id='fiItem'>
		<form class="form-inline" id="itemForm" role="form" action="fi/fi-item!save.action" method="post">
			<table id="fiItemTable" class="table table-striped table-bordered">
				<tr>
					<th>日期</th>
					<th>摘要</th>
					<th>收入</th>
					<th>支出</th>
					<th>余额</th>
					<th>操作</th>
				</tr>
				<tr>
					<td><input type="date" class="form-control" id="dat"
						name="dat" placeholder="日期">
					</td>
					<td><input type="text" class="form-control" id="description"
						name="description" placeholder="摘要">
					</td>
					<td><input type="number" class="form-control" id="income"
						name="income" placeholder="收入">
					</td>
					<td><input type="number" class="form-control" id="output"
						name="output" placeholder="支出">
					</td>
					<td><input type="number" class="form-control" id="rest"
						name="rest" readonly />
					</td>
					<td><button type="button" id="add" class="btn btn-default">新增</button>
					</td>
				</tr>
				
				<s:iterator value="items" var="i">
					<tr class="item"><td><s:property value="#i.dat" /></td><td><s:property value="#i.description" /></td><td class="income"><s:property value="#i.income" /></td><td class="output"><s:property value="#i.output" /></td><td class="rest"><s:property value="#i.rest" /> </td><td></td></tr>
				</s:iterator>
				
				
				
				<tr class="danger" id="sumTr">
					<td></td>
					<td>本月合计</td>
					<td id="sumIn"></td>
					<td id="sumOut"></td>
					<td id="sumRest"></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		
		$(function(){
			
			//设置合计行
			
			$("#sumIn").text( getGincome());
			$("#sumOut").text( getGoutput());
			$("#sumRest").text( getGrest());
			
			
			$("#income,#output").change(function() {
				var income = Number($("#income").val());
				var output = Number($("#output").val());
				var rs = getGrest() + income - output;
				$("#rest").val(rs);
			});
			
			$("#queryBtn").click(function(){
				$("#queryForm").submit();
			});
			
			
			$("#add").click(function() {
				$("#itemForm").submit();
			});
		});
		
		/**
		*获取余额
		*/
		function getGrest(){
			return Number($(".rest:last").text());
		}
		function getGincome(){
			var g =0;
			
			$(".income").each(function(){
				  var i = Number($(this).text());
				  g+=i;
			}); 
			return g;
		}
		function getGoutput(){
			var g =0;
			$(".output").each(function(){
				  var i = Number($(this).text());
				  g+=i;
			});
			return g;
		}
		
		
		
		
	</script>
</body>
</html>
