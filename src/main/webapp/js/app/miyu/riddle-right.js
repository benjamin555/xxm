$(function(){
	var basePath = $("#basePath").val();
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