/**
 * 公用的js函数文件
 */

/**
 * 伪造http referer信息
 * 用 document.all 来判断当前的浏览器是否是IE， 如果是的话就生成一个link，
 * 然后自动执行 onclick 事件，如果不是的话就用JS 跳转。这样在处理页面就可以得到 HTTP_REFERER
 * @param url
 */
function referURL(url){
    var isIe=(document.all)?true:false;
    //console.info("isIe:"+isIe);
    if(isIe) {
        var linka = document.createElement('a');
        linka.href=url;
        document.body.appendChild(linka);
        linka.click();
    }
}
/**
 * 获取get请求参数
 * @param name
 * @returns
 */
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var search=window.location.search;
    if(search!=null && search!=""){
    	var r = search.substr(1).match(reg);
    	if(r!=null){
    		return  unescape(r[2]);
    	}
    }
    return null;
}
/**
 * 获取菜单uri
 * @returns
 */
function getCallback(){
	var pathname = window.location.pathname;
	var param=GetQueryString("callback");
	//console.log("pathname:"+pathname);
	//console.log("param:"+param);
	if(param!=null && param != ""){
		return param;
	}else{
		return pathname;
	}
}
/**
 * 唯一标识 指定长度和基数
 */
function generateUUID(len, radix) {
    var timeData = new Date().getTime();
    var chars = ('0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz').split('');
    var uuid = [], i;
    radix = radix || chars.length;

    if (len) {
        // Compact form
        for (i = 0; i < len; i++) uuid[i] = chars[0 | (Math.random()*radix)];
    } else {
        // rfc4122, version 4 form
        var r;

        // rfc4122 requires these characters
        uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
        uuid[14] = '4';

        // Fill in random data.  At i==19 set the high bits of clock sequence as
        // per rfc4122, sec. 4.1.5
        for (i = 0; i < 36; i++) {
            if (!uuid[i]) {
                r = 0 | (Math.random()*16);
                uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
            }
        }
    }
    return uuid.join('');
}

/**
 * GUID是一种由算法生成的二进制长度为128位的数字标识符。
 * GUID 的格式为“xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx”，
 * 其中的 x 是 0-9 或 a-f 范围内的一个32位十六进制数。在理想情况下，任何计算机和计算机集群都不会生成两个相同的GUID。
 * @returns {string}
 */
function uuid() {
    var d = new Date().getTime();
    //var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var uuid = 'xxxx-yxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    return uuid;
};
//货币格式化
//Extend the default Number object with a formatMoney() method:
//usage: someVar.formatMoney(decimalPlaces, symbol, thousandsSeparator, decimalSeparator)
//defaults: (2, "$", ",", ".")
Number.prototype.formatMoney = function (places, symbol, thousand, decimal) {
 places = !isNaN(places = Math.abs(places)) ? places : 2;
 symbol = symbol !== undefined ? symbol : "$";
 thousand = thousand || ",";
 decimal = decimal || ".";
 var number = this,
     negative = number < 0 ? "-" : "",
     i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
     j = (j = i.length) > 3 ? j % 3 : 0;
 return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
};

function provCityChg(type) {
	if (type == "1") {
		var pid = trim($('#iaddr_prov').val());
		provCityShow("2", pid);
	} else if (type == "2") {
		var cid = trim($('#iaddr_city').val());
		provCityShow("3", cid);
	}
}
//省市联动:
function provCityShow(type, pid) {
	$.ajax({
				url  : "/infor/getProCityArea",
				type : "POST",
				data : {
					"type" : +type,
					"id" : +pid
				},
				async : false,
				dataType : "JSON",
				success : function(datalist) {
					if (type == '1') {
						document.getElementById("iaddr_prov").length = 0;
						document.getElementById("iaddr_prov").options[0] = new Option(
								"-省份-", "");
						document.getElementById("iaddr_city").length = 0;
						document.getElementById("iaddr_city").options[0] = new Option(
								"-城市-", "");
					} else if (type == '2') {
						document.getElementById("iaddr_city").length = 0;
						document.getElementById("iaddr_city").options[0] = new Option(
								"-城市-", "");
					}
					// 3:
					document.getElementById("iaddr_county").length = 0;
					document.getElementById("iaddr_county").options[0] = new Option(
							"-地区-", "");

					if (datalist != null && datalist.length > 0) {
						if (type == '1') {
							for (var i = 0; i < datalist.length; i++) {
								document.getElementById("iaddr_prov").options[document
										.getElementById("iaddr_prov").length] = new Option(
										datalist[i].name, datalist[i].id);
							}
						} else if (type == '2') {
							for (var i = 0; i < datalist.length; i++) {
								document.getElementById("iaddr_city").options[document
										.getElementById("iaddr_city").length] = new Option(
										datalist[i].name, datalist[i].id);
							}
						} else if (type == '3') {
							for (var i = 0; i < datalist.length; i++) {
								document.getElementById("iaddr_county").options[document
										.getElementById("iaddr_county").length] = new Option(
										datalist[i].name, datalist[i].id);
							}
						}
					} // if;
				}

			});

}

//回显
function provCityMo(sv, cv, qv) {
	if (sv != "") {
		var ap = document.getElementById("iaddr_prov");
		for (var i = 0; i < ap.length; i++) {
			if (ap[i].value == sv) {
				ap[i].selected = true;
			}
		}
		// 城市:
		if (cv != "") {
			provCityShow("2", sv); // 首先实例化;
			var ac = document.getElementById("iaddr_city");
			for (var i = 0; i < ac.length; i++) {
				if (ac[i].value == cv) {
					ac[i].selected = true;
				}
			}
			// 市区:
			if (qv != "") {
				provCityShow("3", cv);
				var aq = document.getElementById("iaddr_county");
				for (var i = 0; i < aq.length; i++) {
					if (aq[i].value == qv) {
						aq[i].selected = true;
					}
				}
			}
		}
	}
}
/**
 * 判断是否登录，没登录刷新当前页，促使Shiro拦截后跳转登录页
 * @param result	ajax请求返回的值
 * @returns {如果没登录，刷新当前页}
 */
function isLogin(result){
	if(result && result.code && result.code == '1101'){
		window.location.reload(true);//刷新当前页
	}
	return true;//返回true
}
/**
 * 统一处理后台响应
 * @param result	ajax请求返回的值
 * @returns {如果没登录，刷新当前页}
 */
function handlerResponse(result){
	if(result && result.code && result.code == '1101'){
		window.location.reload(true);//刷新当前页
	}else if(result && result.code && result.code == '3000'){
		//后台异常
		layer.alert("操作异常，请您稍后再试",function(){
			layer.closeAll();
		});
	}else{
		return true;//返回true
	}
}
//去利易达官网
function toLiyida(){
    window.open("//www.liyida.com");
}
//去除所有空格
function removeAllSpace(str) {
    return str.replace(/\s+/g, "");
}
