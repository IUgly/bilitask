$(function(){	
	var lunbo1 = $('.div7 .lunbo1');
	var imgCon = $('.div7 .lunbo1 li4');
	imgCon.not(imgCon.eq(0)).hide();
	var num = $('.div7 .lunbo2');
	var len = lunbo1.find('li4').length;
	var page = '', index = 0;
	for (var i = 0; i < len; i++){
		if (i===) {
			page += '<li class=li4>' + (i + 1) + '</li>';
		} else{
			page += '<li>' + (i + 1) + '</li>';
		}
	}
	lunbo2.html(page);
	function showPic(index){
		imgCon.eq(index).show().siblings("li4").hide();
		lunbo2.find("li4").eq(index).addClass('li4').removeClass("li4");
	}
	$('.lunbo2 li4').mouseover(function(){
		index = $(this).index();
		showPic(index);
	})
	$('.div7').hover(function (){
		clearInterval(window.timer);
	},function(){
		window.timer = setInterval(function () {
			showPic(index);
			index++;
			if (index === len) {
				index = 0;
			}
		},2000);
	}).trigger('mouseleave');
});