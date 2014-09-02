$(function(){
	$("#iKnownBtn").click(function(){
		$("#answerDiv").show("slow");
		$("#userAnswer").focus();
	});
	
	$("#changer").click(function(){
		var realAnswer = $("#realAnswer").val();
		var span=$(msgHtml);
		if(realAnswer.length==1){
			var cha = realAnswer.substring(0,1);
			
			if(isLucky()){
				 span = span.text("免费送你半个字吧："+cha);
			}else{
				 span = span.text("(扣2分)给你半个字吧："+cha);
				 addDeduction(2);
			}
			 span.append(msgModalHtml);
			 
			 disabledAllTipBtn();
		}else if(realAnswer.length>1){
			var cha = realAnswer.substring(0,1);
			var helpCount = $("#helpCount").val();
			if(helpCount==0){
				if(isLucky()){
					 span = span.text("免费送你半个字吧："+cha);
				}else{
					 span = span.text("(扣2分)给你半个字吧："+cha);
					 addDeduction(2);
				}
				span.append(msgModalHtml);
				$("#helpCount").val(helpCount+1)
				 disabledOtherTipBtn(this);
			}else{
				if(isLucky()){
					 span = span.text("免费再送你半个字吧："+cha);
				}else{
					span = span.text("(扣2分)再给你半个字吧："+cha);
					addDeduction(2);
				}
				disabledAllTipBtn();
			}
			 
		}else{
			 span = span.text("出错了");
		}
		$("#msgDiv").append(span);
		scroll(0,0);
	});
	
	
	$("#thatsit").click(function(){
		var realAnswer = $("#realAnswer").val();
		var userAnswer = $("#userAnswer").val();
		var span = $(msgHtml);
		if($.trim(realAnswer)==$.trim(userAnswer)){
			$("#pass").val(true);
			$("#rdForm").submit();
		}else{
			span=span.text("(扣3分)答案不对，再想想");
			addDeduction(3);
			$("#msgDiv").append(span);
			scroll(0,0);
		}
		
	});
	
	
	$("#pengmeng").click(function(){
		$("#skipDiv").show("slow");
		
	});
	
	$("#skipBtn").click(function(){
		var perScore = $("#perScore").val();
		$("#deduction").val(perScore);
		$("#pass").val(true);
		
		$("#rdForm").unbind("submit") 
		$("#rdForm").submit();
	});
	
	
	$("#houyi").click(function(){
		
		//获取十个相同字数的答案,将答案插入其中
		var realAnswer = $("#realAnswer").val();
		var len = realAnswer.length;
		
		
		var basePath= $("#basePath").val();
		var url = basePath+"miyu/riddle!getAnsersByLen.action"
		$.getJSON(url, { "len": len },function(json){
			answers=json;
			if(answers.length<3){
				addMsg("我帮不了你。");
				return;
			}
			var pos = $.random(0,answers.length-1);
			answers.insertAt(pos,realAnswer)
			//罗列到指定消息区域
			if(isLucky()){
				addMsg("(扣1分)从下面几个答案中挑一个吧:");
				addDeduction(1);
			}else{
				addMsg("(扣3分)从下面几个答案中挑一个吧:");
				addDeduction(3);
			}
			
			
			addBtns(answers);
			scroll(0,0);
			disabledAllTipBtn();
			
			$(".houyiBtn").click(function(){
				var answer = $(this).text();
				$("#userAnswer").val(answer);
				$("#thatsit").click();
			});	
		} ); 
		
		
	});
	
	
	
	
	$("#weixin").click(
			function(){
				alert("点击右上角的“...”，发送到朋友圈求助吧！");
			}
			
	
	);
	
	
	$("#rdForm").submit(function(){
		var realAnswer = $("#realAnswer").val();
		var userAnswer = $("#userAnswer").val();
		var span = $(msgHtml);
		if($.trim(realAnswer)==$.trim(userAnswer)){
			$("#pass").val(true);
			return true;
		}else{
			span=span.text("(扣3分)答案不对，再想想");
			addDeduction(3);
			$("#msgDiv").append(span);
			scroll(0,0);
			return false;
		}
		
	});
	
	
	
});