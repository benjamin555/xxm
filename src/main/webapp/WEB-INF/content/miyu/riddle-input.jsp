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
</head>
<body>
	<input type="hidden" value="<%=basePath%>" id="basePath">
	<div class="container">
		<div class="jumbotron">
			<form role="form" action="<%=basePath%>miyu/riddle!save.action" method="post">
				<div class="form-group">
					<label for="question">谜面</label> 
					<textarea placeholder="菜种多了（打一歌星）" required rows="7"  class="form-control" name="question" >菜种多了（打一歌星）</textarea>
				</div>
				<div class="form-group">
					<label for="answer">谜底</label> <input required  name="answer" value="费翔"
						type="text" class="form-control" id="answer" placeholder="费翔"
						>
				</div>
				<div class="form-group">
					<label for="tip1">提示1</label> <input   name="tips.content" value="男的"
						type="text" class="form-control"  placeholder="男的"
						>
				</div>
				<div class="tip2">
					<label for="answer">提示2</label> <input   name="tips.content" value="台湾的"
						type="text" class="form-control"   placeholder="台湾的"
						>
				</div>
				<div class="form-group">
					<label for="tip3">提示3</label> <input   name="tips.content" value="故乡的云"
						type="text" class="form-control"  placeholder="故乡的云"
						>
				</div>
				<button type="submit" class="btn btn-default">提交</button>
			</form>
			<%@include file="/common/footer.jsp"%>
		</div>
	</div>
</body>
</html>
