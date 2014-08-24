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
<title>关于我们</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/common/bootstrap-header.jsp"%>
<style type="text/css">

.descr {
	background-color: #5bc0de;
	padding: .2em .6em .3em;
	font-size: 80%;
	font-weight: bold;
	color: #ffffff;
	vertical-align: baseline;
	border-radius: .25em;
}
</style>
</head>
<body>
	<input type="hidden"  value="<%=basePath%>" id="basePath">
	<div class="container">
		<div class="jumbotron" style="padding: 10px;">
		<div class="descr" >
			对应用有什么建议可以联系下述email:
			</br>
			 <a href="mailto:a114807134@163.com"><strong>a114807134@163.com</strong></a>

			 </br>
			  </br>
			 <a href="<%=basePath%>miyu/riddle!challenge.action" >返回首页</a>
		</div>
		
		</div>
	</div>
</body>
</html>
