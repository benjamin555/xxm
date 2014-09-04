<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div  align="right">
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="100632831" charset="utf-8"></script>
<span id="qqLoginBtn"></span>
<script type="text/javascript">
	QC.Login({
		btnId:"qqLoginBtn"	//插入按钮的节点id
	});
	
	
	/*QC.api("get_user_info", paras)
	.success(function(s){//成功回调
		console.log("获取用户信息成功！当前用户昵称为："+s.data.nickname);
	})
	.error(function(f){//失败回调
		console.log("获取用户信息失败！");
	})
	.complete(function(c){//完成请求回调
		console.log("获取用户信息完成！");
	});
	
	
	//从页面收集openapi必要的参数
	var paras = {
		images:"http://y.photo.qq.com/img?s=OnbP8BwOF&l=y.jpg",
		title:"#QQ互联JSSDK测试#我是标题啊标题",
		url:"http://connect.qq.com/",
		comment:"我是评论：转发原因",
		summary:"我是摘要：内容说明"
	};

	//用jssdk调用openapi
	QC.api("add_share", paras)
		.success(function(s){//请自行改写成功回调
			console.log("分享成功，请到空间内查看！");
		})
		.error(function(f){//请自行改写失败回调
			console.log("分享失败！");
		})
		.complete(function(c){//请自行改写完成请求回调
			console.log("分享完成！");
		});
	
	var paras = {content : "#QQ互联JSSDK测试#曾经沧海难为水，除却巫山不是云。"};

	QC.api("add_t", paras)
		.success(function(s){//成功回调
			console.log("发送微博成功，请到腾讯微博内查看！");
		})
		.error(function(f){//失败回调
			console.log("发送微博失败！");
		})
		.complete(function(c){//完成请求回调
			console.log("发送微博完成！");
		});
	
	
	if(QC.Login.check()){//如果已登录
		QC.Login.getMe(function(openId, accessToken){
			console.log(["当前登录用户的", "openId为："+openId, "accessToken为："+accessToken].join("\n"));
		});
		//这里可以调用自己的保存接口
		//...
	}*/
	
</script>

 </div>



