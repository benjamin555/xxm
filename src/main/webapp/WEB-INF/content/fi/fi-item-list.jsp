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
  	
  	<input type="hidden" id="monthSumId" value="<s:property value='monthSum.id' />">
  	<input type="hidden" id="init" value="<s:property value='init' />">
	<div>
		<form class="form-horizontal" role="form" id="queryForm" action="fi/fi-item!list.action">
			<div class="row" id="answerDiv">
				<!-- /.col-lg-6 -->
				<div class="col-lg-6">
					<div class="input-group">
						<input type="month" class="form-control" id="month" name="monthStr"
							placeholder="日期" value="<s:property value='monthStr' />"><span class="input-group-btn">
							<button id="queryBtn" class="btn btn-default" type="button">转到</button>
						</span>
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->
		</form>
		
	</div>
	<div> <h1>期初值：<s:property value="init" /> <span><a   href="fi/fi-item!exportExcel.action?monthSum.id=<s:property value='monthSum.id' />" class="btn btn-primary" >导出</a></span> </h1> </div>
	<div id='fiItem'>
		<form class="form-inline" id="itemForm" role="form" action="fi/fi-item!save.action" method="post">
			<input type="hidden" name="monthStr" value="<s:property value='monthStr' />">
			<table id="fiItemTable" class="table table-striped table-bordered">
				<tr>
					<th>日期</th>
					<th>摘要</th>
					<th>收入</th>
					<th>支出</th>
					<th>余额</th>
					<th>经手人</th>
					<th>操作</th>
				</tr>
				<tr>
					<td>
					<div  class="form-group" id="datDiv"><input type="date" class="form-control" id="dat"
						name="dat" placeholder="日期" required value="<s:property value='defaultDate' />" ></div>
					</td>
					<td><input type="text" class="form-control" id="description"
						name="description" placeholder="摘要" required>
					</td>
					<td><input type="number" min="0" class="form-control" id="income"
						name="income" placeholder="收入" step="0.01" min="0" >
					</td>
					<td><input type="number" class="form-control" id="output"
						name="output" placeholder="支出" step="0.01" min="0" >
					</td>
					<td>
					</td>
					<td><input type="text" class="form-control" 
						name="handler" placeholder="经手人" required >
					</td>
					<td>
					<input type="submit"  id="subBtn" class="btn btn-default"></input>
					</td>
				</tr>
				
				<s:iterator value="items" var="i">
					<tr class="item">
					<td><s:property value="#i.dat" /></td>
					<td><s:property value="#i.description" /></td>
					<td class="income"><s:property value="#i.income" /></td>
					<td class="output"><s:property value="#i.output" /></td>
					<td class="rest"></td><td><s:property value="#i.handler" /></td>
					<td><a href="fi/fi-item!delete.action?id=<s:property value="#i.id" />" class="btn btn-primary" role="button">删除</a></td>
					</tr>
				</s:iterator>
				
				<tr class="danger" id="sumTr">
					<td></td>
					<td>本月合计</td>
					<td id="sumIn"><s:property  value="monthSum.income" /> </td>
					<td id="sumOut"><s:property  value="monthSum.output" /></td>
					<td id="sumRest"><s:property  value="monthSum.rest" /></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		
		$(function(){
			
			//计算余额列
			var lastRe=Number($("#init").val());
			$("tr.item>td.rest").each(function(i){
				var income= Number($($(this).siblings(".income").get(0)).text()); 
				var output= Number($($(this).siblings(".output").get(0)).text());
				var re = lastRe+income -output;
				$(this).text(re);
				lastRe=re;
			});
			
			$("#exportBtn").click(function(){
				var monthSumId = $("#monthSumId").val();
				$("#queryForm").attr("action","fi/fi-item!exportExcel.action?monthSum.id="+monthSumId)
				$("#queryForm").submit();	
			});
			
			
			
			$("#queryBtn").click(function(){
				$("#queryForm").submit();
			});
			$("#itemForm").submit(function(){
				if($("#dat").val().indexOf($("#month").val())<0){
					$("#datDiv").addClass("has-error")
								.focus()
								.append('<label class="control-label" for="inputError1">不是当前月份</label>'); 
					;
					return false;
				}
				
			});
			
			$("#add").click(function() {
				if($("#dat").val().indexOf($("#month").val())<0){
					$("#datDiv").addClass("has-error")
								.focus()
								.append('<label class="control-label" for="inputError1">不是当前月份</label>'); 
					;
					return;
				}
				$("#itemForm").submit();
			});
		});
		
		
		
		
		
	</script>
</body>
</html>
