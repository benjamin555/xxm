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
<title>tttttt</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/common/bootstrap-header.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>js/app/miyu/riddle-detail.js"></script>
</head>
<body>
	<input type="hidden" value="<%=basePath%>" id="basePath">
	<div class="container">
		<div class="jumbotron">
			<form role="form" action="<%=basePath%>miyu/riddle!importExcel.action"  method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="exampleInputFile">File input</label> <input name="dataExcel" type="file"
						id="exampleInputFile" / >
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
	</div>
</body>
</html>
