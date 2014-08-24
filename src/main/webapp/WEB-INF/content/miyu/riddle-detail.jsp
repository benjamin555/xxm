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
<link rel="stylesheet" href="<%=basePath%>css/app/miyu/riddle.css">
</head>
<body>
	<input type="hidden" value="<%=basePath%>" id="basePath">
<ul class="nav nav-pills" role="tablist">
	<li role="presentation" class="active"><a
		href="<%=basePath%>miyu/riddle!show.action">主页</a>
	</li>
	<li role="presentation" ><a
		href="<%=basePath%>miyu/riddle!challenge.action">来10条挑战下</a>
	</li>
</ul>
	<div class="container">
		<div class="jumbotron">
			<h1>灯谜</h1>
			<div id="msgDiv">
			<s:iterator value="%{actionMessages}" var="msg">
				<span class="label label-warning"><s:property value="msg" />
				</span>
			</s:iterator>
			</div>
			<br /> <label>谜面</label>
			<p class="descr">
				<s:property value="riddle.question" />
			</p>
			<p>share it</p>
			<form role="form" action="<%=basePath%>miyu/riddle!check.action"
				method="post">
				<s:hidden name="id" value="%{riddle.id}"></s:hidden>
				<s:hidden id="realAnswer" value="%{riddle.answer}"></s:hidden>
				<div class="row" style="display: none;" id="answerDiv">
					<!-- /.col-lg-6 -->
					<div class="col-lg-6">
						<div class="input-group">
							<input type="text" class="form-control" name="answer"
								placeholder="请输入谜底..."> <span class="input-group-btn">
								<button class="btn btn-default" type="submit">就它了</button> </span>
						</div>
						<!-- /input-group -->
					</div>
					<!-- /.col-lg-6 -->
				</div>
				<!-- /.row -->
			</form>
			</br>
			<div class="btn-group">
				<button type="button" id="iKnownBtn" class="btn btn-primary ">哈我知道谜底啦</button>
				<a href="<%=basePath%>miyu/riddle!show.action" class="btn btn-info "
					role="button">换一条</a> <a
					href="<%=basePath%>miyu/riddle!input.action"
					class="btn btn-default " role="button">自己创建一条</a>
			</div>
			</br>
			<div class="btn-group">
				<button type="button" id="changer" class="btn btn-primary ">嫦娥仙子</button>
			</div>
		</div>
	</div>
</body>
</html>
