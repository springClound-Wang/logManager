$(function () {
	//console.log("该浏览器内核为："+navigator.userAgent);
	if(navigator.userAgent.toLowerCase().indexOf('se 2.x') > -1) {
		show();
	}
});

function show(){
	$('html').css("overflow","hidden");
	$('body').css("overflow","hidden");
    var hidediv = "<div style='position:absolute;left:0px;top:0px;background-color:#000;width:100%;filter:alpha(opacity=60);opacity:0.6;z-Index:2;height:100%;'></div> ";
    $('body').append(hidediv);
    alert("对不起！该网站暂不支持搜狗浏览器访问，请选用其他浏览器！");
}