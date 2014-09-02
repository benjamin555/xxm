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
<script type="text/javascript" src="<%=basePath%>js/app/miyu/riddle-right.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/app/miyu/riddle.css">

</head>
<body>
	<input type="hidden" value="<%=basePath%>" id="basePath">
	<s:hidden name="id" id="riddleId" value="%{riddle.id}"></s:hidden>
	<div class="container">
		<div class="jumbotron">
			<h1><span class="label label-success">恭喜你答对啦,出题者造吗</span></h1>
			<br/>
			<label >谜面</label> 
			<p class="descr">
			
				<s:property value="riddle.question" />
			</p>
			<label >谜底</label> 
			<p class="descr" >
			
				<s:property value="riddle.answer" />
			</p>
			
			<p class="descr" >
				用了<s:property value="#parameters.usedTipCount" />次提示
			</p>
			
			<p>
			<button type="button" id="zanBtn" class="btn btn-default btn-lg">
			  <span id="zan"
						class="glyphicon glyphicon-thumbs-up"><s:property
								value="riddle.zan" />
					</span>
			</button>
			</p>
			</br>
			
			<div class="btn-group">
			<a href="javascript:void(0);" onclick="history.go(-1);"
				class="btn btn-info " role="button">返回</a>
			<a href="<%=basePath%>miyu/riddle!input.action"
				class="btn btn-default " role="button">自己建一条</a>
			</div>
			<%@include file="/common/footer.jsp"%>
		</div>
	</div>
</body>
</html>
