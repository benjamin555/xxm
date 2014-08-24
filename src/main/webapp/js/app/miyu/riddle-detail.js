$(function(){
	
	$("#iKnownBtn").click(function(){
		$("#answerDiv").show("slow");
		
	});
	
	$("#changer").click(function(){
		var realAnswer = $("#realAnswer").val();
		var span="";
		if(realAnswer.length==1){
			 span = '<span class="label label-warning" >答案就一个字，人家帮不了你</span>';
		}else if(realAnswer.length>1){
			var cha = realAnswer.substring(0,1);
			 span = '<span class="label label-warning" >给你个字吧：'+cha+'</span>';
		}else{
			 span = '<span class="label label-warning" >出错了</span>';
		}
		$("#msgDiv").append(span);
		scroll(0,0);
	});
	
});