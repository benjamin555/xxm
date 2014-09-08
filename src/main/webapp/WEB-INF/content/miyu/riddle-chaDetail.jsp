<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	int perScore = cn.sp.xm.miyu.action.RiddleAction.getPerScore();
	String url = cn.sp.web.utils.ServletUtils.getRequestUrl(request);
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
	src="<%=basePath%>js/app/miyu/riddle-chaDetail.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/app/miyu/riddle.css">
</head>
<body>
	<input type="hidden" value="<%=basePath%>" id="basePath">
	<input type="hidden" value="0" id="helpCount">
	<input type="hidden" value="<%=perScore%>" id="perScore">
	<input type="hidden" value="<%=url%>" id="url">
	<div class="container">
		<div class="jumbotron" style="padding: 10px;">
			
			
			<h1>灯谜</h1>
			<div id="msgDiv">
				<s:iterator value="%{actionMessages}" var="msg">
					<div class="msg label-warning" style="">
						<s:property value="msg" />
					</div>
				</s:iterator>
			</div>
			<label>谜面</label>
			<p class="descr ">
				
						<s:property value="riddle.question" />
					
			</p>
			<form role="form"
				action="<%=basePath%>miyu/riddle!challengeNext.action" method="post"
				id="rdForm" style="margin-bottom: 2em;">
				<s:hidden name="id" value="%{riddle.id}"></s:hidden>
				<s:hidden id="realAnswer" value="%{riddle.answer}"></s:hidden>
				<s:hidden name="hasShare" value="%{#parameters.hasShare}"></s:hidden>
				<!-- 减分 -->
				<input type="hidden" value="0" name="deduction" id="deduction">
				<input type="hidden" value="false" name="pass" id="pass">
				<div class="row" style="display: none;" id="answerDiv">
					<!-- /.col-lg-6 -->
					<div class="col-lg-6">
						<div class="input-group">
							<input type="text" class="form-control" id="userAnswer"
								placeholder="请输入谜底..."> <span class="input-group-btn">
								<button id="thatsit" class="btn btn-default" type="button">就它了</button>
							</span>
						</div>
						<!-- /input-group -->
					</div>
					<!-- /.col-lg-6 -->
				</div>
				<!-- /.row -->
				<div style="display: none;" id="skipDiv">
					<label class="label label-warning" style="margin-right: 1em;">直接跳过本题（没分给哦）</label>
					</br> </br>
					<button id="skipBtn" class="btn btn-default btn-lg" type="button">确认跳过</button>
				</div>
			</form>
			<div class="btn-group" style="margin-bottom: 2em;">
				<button type="button" id="iKnownBtn" class="btn btn-primary  btn-lg">我知道啦</button>
				<a href="<%=basePath%>miyu/riddle!challenge.action?hasShare=<s:property value="#parameters.hasShare"/>"
					class="btn btn-default btn-lg" role="button">重新开始</a>
			</div>
			</br>
			<div class="btn-group tip-group" style="margin-bottom: 3em;">
				<button type="button" id="changer" class="btn btn-primary btn-lg">&nbsp;&nbsp;&nbsp;嫦娥&nbsp;&nbsp;&nbsp;</button>
				<button type="button" id="houyi" class="btn btn-primary btn-lg">&nbsp;&nbsp;&nbsp;后羿&nbsp;&nbsp;&nbsp;</button>
			</div>
			<div class="btn-group " style="margin-bottom: 3em;">
				<button type="button" id="pengmeng" class="btn btn-primary btn-lg">&nbsp;&nbsp;&nbsp;蓬蒙&nbsp;&nbsp;&nbsp;</button>
			</div>
			<%@include file="/common/footer.jsp"%>
			
		</div>
	</div>
</body>
</html>
<script type="text/javascript">



</script>
