var msgHtml = '<div class="msg label-warning" ></div>';
$(function(){
	
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
	
	
});