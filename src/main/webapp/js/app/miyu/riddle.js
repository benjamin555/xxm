var msgHtml = '<div class="msg label-warning" ></div>';
var spanHtml = '<span class="msg label-warning" ></span>';
var btnHtml = '<button type="button" class="btn btn-info houyiBtn" style=" margin-bottom:.2em; margin-right:.2em;"></button>';
 
//遮挡
var msgModalHtml= '<span class="msg label-warning" style="position: relative;left: -6;"></span>';

function addBtns(msgs){
	for(var i =0;i<msgs.length;i++){
		var span=$(btnHtml);
		span = span.text(msgs[i]);
		$("#msgDiv").append(span);
	}
	
}
function addMsg(msg){
		var span=$(msgHtml);
		span = span.text(msg);
		$("#msgDiv").append(span);
}





function addDeduction(d){
	var r = Number($("#deduction").val());
	d = Number(d)
	$("#deduction").val(r+d);
}


function disabledAllTipBtn(){
	$(".tip-group>button").attr("disabled","disabled");
}
function disabledOtherTipBtn(excep){
	$(".tip-group>button").attr("disabled","disabled");
	$(excep).removeAttr("disabled");
}


Array.prototype.insertAt=function(index,obj){ 
	this.splice(index,0,obj); 
} 

$.random = function(n1,n2){
    return Math.round(Math.random() * (Math.max(n1,n2)-Math.min(n1,n2) + 1),0);
}
function isLucky(){
	var r = $.random(1,3);
	switch (r){
		case 1:
			return false;
		default:
			return true
	}
	return true
}



// 需要分享的内容，请放到ready里
WeixinApi.ready(function(Api) {
     var basePath=$("#basePath").val();
     var url = window.location.href;
    // 微信分享的数据
    var wxData = {
        "appId": "", // 服务号可以填写appId
        "imgUrl" : basePath+"images/moon.jpg",
        "link" : url,
        "desc" : '中秋猜灯谜！我猜我猜我猜猜猜!',
        "title" : "快来猜灯谜吧"
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
        }
    };

    // 用户点开右上角popup菜单后，点击分享给好友，会执行下面这个代码
    Api.shareToFriend(wxData, wxCallbacks);

    // 点击分享到朋友圈，会执行下面这个代码
    Api.shareToTimeline(wxData, wxCallbacks);

    // 点击分享到腾讯微博，会执行下面这个代码
    Api.shareToWeibo(wxData, wxCallbacks);

});