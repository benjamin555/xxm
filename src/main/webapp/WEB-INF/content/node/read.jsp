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
<title>My JSP 'read.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/common/bootstrap-header.jsp"%>
<script type="application/javascript"
	src="<%=basePath %>js/iscroll-4.2.5.js"></script>
<script type="text/javascript" src="<%=basePath %>js/app/node/read.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/app/node/read.css">
<style type="text/css" >
#pullDown .pullDownIcon {
	display: block;
	float: left;
	width: 40px;
	height: 40px;
	background: url(<%=basePath%>images/pull-icon@2x.png) 0 0 no-repeat;
	-webkit-background-size: 40px 80px;
	background-size: 40px 80px;
	-webkit-transition-property: -webkit-transform;
	-webkit-transition-duration: 250ms;
}
</style>
</head>
<body>
	<input type="hidden" value="<%=basePath%>" id="basePath">
	<s:hidden id="nodeId" value="%{node.id}"></s:hidden>
	<s:hidden id="parentNodeId" value="%{node.parentNode.id}"></s:hidden>
	<div id="wrapper" class="container" style="padding-top: 30px;">
		<div id="scroller" >
			<div id="pullDown">
				<span class="pullDownIcon"></span><span class="pullDownLabel">下拉刷新...</span>
			</div>
			<div id="thelist" >
				<div class="jumbotron cb"  >
				<s:property value="node.content" />
				在路上捡到一个钱包，里面有5000多块钱，我想失主一定很着急， 我的脑子里顿时跳出来两个小人，
				一个说：“反正没人看到，拿去买个手机吧。” “ 不行不行！”另一个阻止道：“还是买个电脑吧。”
				</div>
			</div>
		</div>
	</div>
</body>
</html>
