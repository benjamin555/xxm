var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset, generatedCount = 0;

/**
 * 下拉刷新 （自定义实现此方法）
 * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
 */
function pullDownAction() {
	setTimeout(function() { // <-- Simulate network congestion, remove setTimeout from production!
		var el, li, i;
		el = document.getElementById('thelist');

		var ju = $('<div class="jumbotron cb"></div>');
		$(el).prepend(ju);

		myScroll.refresh(); //数据加载完成后，调用界面更新方法   Remember to refresh when contents are loaded (ie: on ajax completion)
	}, 1000); // <-- Simulate network congestion, remove setTimeout from production!
}

/**
 * 初始化iScroll控件
 */
function loaded() {
	pullDownEl = document.getElementById('pullDown');
	pullDownOffset = pullDownEl.offsetHeight;

	myScroll = new iScroll(
			'wrapper',
			{
				scrollbarClass : 'myScrollbar', /* 重要样式 */
				useTransition : false, /* 此属性不知用意，本人从true改为false */
				topOffset : pullDownOffset,
				onRefresh : function() {
					if (pullDownEl.className.match('loading')) {
						pullDownEl.className = '';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
					}
				},
				onScrollMove : function() {
					
					
					if (this.y > 5 && !pullDownEl.className.match('flip')) {
						pullDownEl.className = 'flip';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
						this.minScrollY = 0;
					} else if (this.y < 5 && pullDownEl.className.match('flip')) {
						pullDownEl.className = '';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
						this.minScrollY = -pullDownOffset;
					}
				},
				onScrollEnd : function() {
					if (pullDownEl.className.match('flip')) {
						pullDownEl.className = 'loading';
						pullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中...';
						pullDownAction(); // Execute custom function (ajax call?)
					} 
				}
			});

	setTimeout(function() {
		document.getElementById('wrapper').style.left = '0';
	}, 800);
}

//初始化绑定iScroll控件 
document.addEventListener('touchmove', function(e) {
	e.preventDefault();
}, false);
document.addEventListener('DOMContentLoaded', loaded, false);