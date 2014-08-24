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
<title>My JSP 'read.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/common/bootstrap-header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/app/node-read.js"></script>
</head>
<body>
	<input type="hidden"  value="<%=basePath%>" id="basePath">
	<s:hidden id="nodeId" value="%{node.id}"></s:hidden>
	<s:hidden id="parentNodeId" value="%{node.parentNode.id}"></s:hidden>
	<div class="container">
		<a href="<%=basePath%>miyu/riddle!show.action"
				class="btn btn-default btn-lg " role="button">riddle</a>
		<div class="jumbotron">
			<div align="center" style="margin-bottom: 20px;">
				<ul class="nav nav-pills nav-stacked" role="tablist">
					<li role="presentation" class="active"><a id="readPrevBtn" href="javascript:void(0)"><span
							class="glyphicon glyphicon-chevron-up"></span> </a>
					</li>
				</ul>
			</div>
			<span class="label label-warning" id="msg" style="display:none;"></span>
			<div  id="content" style="margin-top: 10px;margin-bottom: 10px;">
				<s:property value="node.content" />
				在路上捡到一个钱包，里面有5000多块钱，我想失主一定很着急， 我的脑子里顿时跳出来两个小人，
				一个说：“反正没人看到，拿去买个手机吧。” “ 不行不行！”另一个阻止道：“还是买个电脑吧。”
			</div>
			<ul class="pager">
				<li class="previous"><a id="readPrevSiblingBtn" href="javascript:void(0)"><span
						class="glyphicon glyphicon-chevron-left"></span> </a></li>
				<li><a id="zanBtn" href="javascript:void(0)"><span id="zan" class="glyphicon glyphicon-thumbs-up"><s:property value="node.zan" /></span>
				</a></li>
				<li class="next"><a id="readNextSiblingBtn" href="javascript:void(0)"><span
						class="glyphicon glyphicon-chevron-right"></span> </a></li>
			</ul>
			<div align="center">
				<ul class="nav nav-pills nav-stacked" role="tablist">
					<li role="presentation" class="active"><a id="readNextBtn" href="javascript:void(0)" ><span
							class="glyphicon glyphicon-chevron-down"></span> </a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
