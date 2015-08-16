/*
 * Author: Junying Li (vani.li@dbschenker.com)
 * @Date: 2015-8-6
 * @Description: This JS file controlls for web analysis activities 
 * @Version: v-0.01
 *  一看用户来源：确定用户从哪里来，
	二看关键词：确定用户怎么来，用的哪个搜索引擎
	三看受访页面及页面间的联系：寻找用户感兴趣的内容
	四看停留时间：全站停留时间与页面停留时间、寻找用户感兴趣的内容
	五跳出页面：寻找用户不感兴趣的内容
	六看用户操作系统、浏览器及版本：看看用户属于哪个层次
 */
//JS ready functions
//Define global variables
//Regist namespace, to protect that all the variables have no name-space conficts.
var path;
var remoteServerPath = "http://localhost:8080/zeus/rest/"
Namespace = new Object();
Namespace.register = function(fullNS) {
	var nsArray = fullNS.split('.');
	var sEval = "";
	var sNS = "";
	for (var i = 0; i < nsArray.length; i++) {
		if (i != 0)
			sNS += ".";
		sNS += nsArray[i];

		sEval += "if (typeof(" + sNS + ") == 'undefined') " + sNS
				+ " = new Object();";
	}
	if (sEval != "")
		eval(sEval);
}
Namespace.register("schenker_wa");

schenker_wa.start = new Date();
schenker_wa.end;
schenker_wa.state;

//document ready function
schenker_wa.fn = ready = window.ready = function(fn) {
	if (document.addEventListener) { //None IE
		document.addEventListener("DOMContentLoaded", function() {
			document.removeEventListener("DOMContentLoaded", arguments.callee,
					false);
			//functions from here
			fn();
		}, false);
	} else if (document.attachEvent) {
		document.attachEvent("onreadystatechange", function() { //IE
			if (document.readyState === "complete") {
				document.detachEvent("onreadystatechange", arguments.callee);
				//functions from here
				fn();
			}
		});
	}
}
document.onmousedown = function(e) {
	if (!e) {
		var e = window.event;
	}
	//获取事件点击元素  
	var targ = e.target;
	//获取元素名称  
	var tname = targ.tagName;
	if (tname != 'HTML') {
		//获取用户点击元素
		var eleInfo = targ.id+" "+targ.innerText+" "+targ.tagName;
		ajaxFunction(remoteServerPath+"clickEle/"+path+"/"+eleInfo);
	}
}
schenker_wa.fn(function() {
	//获取当前页面路径
	path = window.location.href.split(".")[0];
	var firstSlashPos = path.lastIndexOf("/");
	path = path.substring(firstSlashPos);
	ajaxFunction(remoteServerPath+"visitPath/"+path);
	//访问次数加1

	//Get Browser version
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua
			.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua
			.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua
			.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua
			.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	//获取用户浏览器类型
		 var broserVersion;
		 if (Sys.ie) broserVersion = "IE:"+Sys.ie; 
		 if (Sys.firefox) broserVersion = "FileFox:"+Sys.firefox;
		 if (Sys.chrome) broserVersion = "Chrome:"+Sys.chrome;
		 if (Sys.opera) broserVersion = "Opera:"+Sys.opera;
		 if (Sys.safari) broserVersion = "Safari:"+Sys.safari;
		 ajaxFunction(remoteServerPath+"broswer/"+path+"/"+broserVersion);
});

//on leaving page function
window.onbeforeunload = function() {
	//record user how long user stays for this page
	schenker_wa.end = new Date();
	schenker_wa.state = schenker_wa.end.getTime() - schenker_wa.start.getTime();
	//获取用户访问页面的时长
	ajaxFunction(remoteServerPath+"visitTime/"+path+"/"+schenker_wa.state);
//	window.console.log("User stay:" + schenker_wa.state + "ms");
};

//Ajax function
function ajaxFunction(url) {
	var xmlHttp;
	try {
		// Firefox, Opera 8.0+, Safari
		xmlHttp = new XMLHttpRequest(); // 实例化对象
	} catch (e) {
		// Internet Explorer
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Not Support AJAX！");
				return false;
			}
		}
	}
	xmlHttp.onreadystatechange = function() {
	}
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}
