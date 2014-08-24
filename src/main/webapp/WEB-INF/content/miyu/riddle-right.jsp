<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>中秋猜谜</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/common/bootstrap-header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/app/miyu/riddle-detail.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/app/miyu/riddle.css">

</head>
<body>
	<input type="hidden" value="<%=basePath%>" id="basePath">
	<div class="container">
		<div class="jumbotron">
			<h1><span class="label label-success">恭喜你答对啦</span></h1>
			<br/>
			<label >谜面</label> 
			<p class="descr">
			
				<s:property value="riddle.question" />
			</p>
			<label >谜底</label> 
			<p class="descr" >
			
				<s:property value="riddle.answer" />
			</p>
			<p>share it</p>
			</br>
			
			<div class="btn-group">
			<a href="<%=basePath%>miyu/riddle!show.action"
				class="btn btn-info " role="button">再来一条</a>
			<a href="<%=basePath%>miyu/riddle!input.action"
				class="btn btn-default " role="button">自己建一条</a>
			</div>
			
		</div>
	</div>
</body>
</html>
