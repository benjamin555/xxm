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
	src="<%=basePath%>js/app/miyu/riddle.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/app/miyu/riddle.css">
</head>
<body>
	<input type="hidden" value="<%=basePath%>" id="basePath">
	<input type="hidden" value="0" id="helpCount">
	<div class="container" >
		<div class="jumbotron" style="padding: 10px;">
			<h1>灯谜</h1>
			<div id="msgDiv">
			<s:iterator value="%{actionMessages}" var="msg">
				
				<div class="msg label-warning" style=""><s:property value="msg" />
				</div>
			</s:iterator>
			</div>
			<div>
				<p class="descr">
					您的分数是：<s:property value="score" />
					</br>
					<s:property value="comment" />
				</p>
			</div>
			
			<div class="btn-group">
				<button type="button" id="weixin" class="btn btn-default">分享</button>
			
				<a href="<%=basePath%>miyu/riddle!challenge.action" class="btn btn-default "
					role="button">重新开始挑战</a>
			</div>
			</br>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" >

//需要分享的内容，请放到ready里
WeixinApi.ready(function(Api) {
     var basePath=$("#basePath").val();
     var url = $("#url").val();
    // 微信分享的数据
    var wxData = {
        "appId": "", // 服务号可以填写appId
        "imgUrl" : basePath+"images/moon.jpg",
        "link" : url,
        "desc" : '中秋节猜灯谜。这是我的分数，大家也来试试看!',
        "title" : "中秋猜灯谜"
    };

    // 分享的回调
    var wxCallbacks = {
        
        
        // 分享失败了
        fail : function(resp) {
            // 分享失败了，是不是可以告诉用户：不要紧，可能是网络问题，一会儿再试试？
            alert("分享失败，msg=" + resp.err_msg);
        },
        // 分享成功
        confirm : function(resp) {
            // 分享成功了，我们是不是可以做一些分享统计呢？
            alert("分享成功" );
        }
    };

    // 用户点开右上角popup菜单后，点击分享给好友，会执行下面这个代码
    Api.shareToFriend(wxData, wxCallbacks);

    // 点击分享到朋友圈，会执行下面这个代码
    Api.shareToTimeline(wxData, wxCallbacks);

    // 点击分享到腾讯微博，会执行下面这个代码
    Api.shareToWeibo(wxData, wxCallbacks);

});
$(function(){
	$("#weixin").click(
			function(){
				alert("点击右上角的“...”，把成绩分享给好友吧！");
			}
			

	);
	
});


</script>