var msgHtml = '<div class="msg label-warning" ></div>';
$(function(){
	var basePath = $("#basePath").val();
	$("#iKnownBtn").click(function(){
		$("#answerDiv").show("slow");
		
	});
	//提示次数
	var tips = $(":hidden[name='tip']");
	$("#tipBtn").click(function(){
		var tipCount=Number($("#usedTipCount").val());
		
		if(tipCount>=tips.length){
			addMsg("没有提示了");
			$(this).attr("disabled",true);
			return;
		}else{
			var i = tipCount;
			var t = "提示"+(i+1)+":";
			addMsg(t+$(tips[i]).val());
		}
		
		tipCount++;
		$("#usedTipCount").val(tipCount);
		
	});
	
	
	$("#thatsit").click(function(){
		var realAnswer = $("#realAnswer").val();
		var userAnswer = $("#userAnswer").val();
		if($.trim(realAnswer)==$.trim(userAnswer)){
			$("#rdForm").submit();
		}else{
			addMsg("答案不对，再想想");
			scroll(0,0);
		}
		
	});
	
	$("#zanBtn").click(function(){

		var zanUrl = basePath + "miyu/riddle!zan.action";
		var para = {
			"riddle.id" : $("#riddleId").val()
		};
		$.getJSON(zanUrl, para, function(json) {
			if (json.result == "pass") {
				$("#zan").text(parseInt($("#zan").text()) + 1);
			}

		});
		$(this).attr("disabled",true);
	});
	
	
});