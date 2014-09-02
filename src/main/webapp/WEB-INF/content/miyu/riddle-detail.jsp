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
<script type="text/javascript" src="<%=basePath%>js/app/miyu/riddle.js"></script>
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
	<!-- tips -->
		<s:iterator value="riddle.tips"  var="t" status="status">
			<s:hidden name="tip" value="%{#t.content}"></s:hidden>
		</s:iterator>
		
	
		<div class="jumbotron">
			<h3>复制链接，把题分享给好友们吧</h3>
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
			<form role="form" action="<%=basePath%>miyu/riddle!showRight.action"
				method="get" id="rdForm">
				<s:hidden name="id" value="%{riddle.id}"></s:hidden>
				<s:hidden id="usedTipCount" name="usedTipCount" value="0"></s:hidden>
				<s:hidden id="realAnswer" value="%{riddle.answer}"></s:hidden>
				<div class="row" style="display: none;" id="answerDiv">
					<!-- /.col-lg-6 -->
					<div class="col-lg-6">
						<div class="input-group">
							<input type="text" class="form-control"  id="userAnswer"
								placeholder="请输入谜底..."> <span class="input-group-btn">
								<button class="btn btn-default" id="thatsit" type="button">就它了</button> </span>
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
			</div>
			</br>
			</br>
			<div class="btn-group">
				<button type="button" id="tipBtn" class="btn btn-primary ">提示</button>
			</div>
			
			
			<%@include file="/common/footer.jsp"%>
		</div>
	</div>
</body>
</html>
