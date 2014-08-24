$(function() {
	var basePath = $("#basePath").val();
	$("#readNextBtn").click(function() {
		var url = basePath + "node!readNext.action";
		var para = {
			"node.id" : $("#nodeId").val()
		};
		$.getJSON(url, para, function(json) {

			updateInfo(json);

		});
	});
	$("#readPrevBtn").click(function() {
		var url = basePath + "node!readPrev.action";
		var para = {
			"node.parentNode.id" : $("#parentNodeId").val()
		};
		$.getJSON(url, para, function(json) {
			updateInfo(json);

		});
	});

	$("#readNextSiblingBtn").click(function() {
		var url = basePath + "node!readNextSibling.action";
		var para = {
			"node.id" : $("#nodeId").val()
		};
		$.getJSON(url, para, function(json) {
			updateInfo(json);
		});
	});
	$("#readPrevSiblingBtn").click(function() {
		var url = basePath + "node!readPrevSibling.action";
		var para = {
			"node.id" : $("#nodeId").val()
		};
		$.getJSON(url, para, function(json) {
			updateInfo(json);
		});
	});

	$("#zanBtn").click(function() {
		var zanUrl = basePath + "node!zan.action";
		var para = {
			"node.id" : $("#nodeId").val()
		};
		$.getJSON(zanUrl, para, function(json) {
			if (json.result == "pass") {
				$("#zan").text(parseInt($("#zan").text()) + 1);
			}

		});
	});

});
function updateInfo(json) {

	if (json!=null&&"fail"==json.result  ) {
		$("#msg").text(json.msg).fadeIn("slow");
		setInterval("clearMsg()", 3000);
		return;
	}
	updateNode(json);
}

function updateNode(node) {

	$("#content").text(node.content);
	$("#zan").text(node.zan);
	$("#nodeId").val(node.id);
	if (node.parentNode != null) {
		$("#parentNodeId").val(node.parentNode.id);
	} else {
		$("#parentNodeId").val("");
	}
}

function clearMsg() {
	$("#msg").fadeOut("slow");

}