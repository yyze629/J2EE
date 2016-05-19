/**
 * 调用父类的请求方法
 * @param type
 * @param data
 */
function screenRequestInFrame(type,data){
	window.parent.screenRequest(type,data);
}
function test(){
	var type = $("#type").val();
	var data = $("#data").val();
	$("#test").append(getCurentTime()+"[Request]:");
	$("#test").append("\n");
	$("#test").append("=====type="+type+",data="+data);
	$("#test").append("\n");
	screenRequestInFrame(type,data);
}

function getCurentTime()
{ 
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();          //秒
    var ms = now.getMilliseconds();     //豪秒
    
    var clock = year + "-";
    if(month < 10){
    	clock += "0";
    }
    clock += month + "-";
    if(day < 10){
    	clock += "0";
    }
    clock += day + " ";
    if(hh < 10){
    	clock += "0";
    }
    clock += hh + ":";
    if (mm < 10){
    	clock += '0'; 
    }
    clock += mm+":";
    if (ss < 10){
    	clock += '0'; 
    }
    clock += ss;
    clock += ("."+ms);
    return(clock);
}
function getJsonString(obj){
	return JSON.stringify(obj);
}
function isEmpty(obj){
	if(obj==undefined || obj==null || obj==""){
		return true;
	}else{
		return false;
	}
}
//异步发送ajax请求跳转到url地址页面
function ajaxAsyncToPageByData(url, id, data){
	if(isEmpty(data)){
		data = "";
	}
	$.ajax({
		type: "POST",
	    async: true,
	    url: url,
	    data: data,
		success: function(html){
			$("#"+id).html(html);
		}
	});
}
//同步发送ajax请求跳转到url地址页面
function ajaxToPageByData(url, id, data){
	if(isEmpty(data)){
		data = "";
	}
	$.ajax({
		type: "POST",
		async: false,
		url: url,
		data: data,
		success: function(html){
			$("#" + id).html(html);
		}
	});
}
//获取cookie
function getCookie(name){ 
	   var strCookie=document.cookie; 
	   var arrCookie=strCookie.split("; "); 
	   for(var i=0;i<arrCookie.length;i++){ 
		   var arr=arrCookie[i].split("="); 
		   if(arr[0]==name)return arr[1]; 
	   } 
	   return 0; 
}
function setCookieValue(name,value){
	document.cookie=name+"="+type;
}
function initFancyBox(className,width,height,showCloseButton){
	if(isEmpty(showCloseButton)){
		showCloseButton = true;
	}
	$("."+className).fancybox({
		'width'				: width,
		'height'			: height,
		'padding'			: 0,
		'autoScale'			: true,
		'autoDimensions'    : true,
		'transitionIn'		: 'yes',
		'transitionOut'		: 'yes',
		'overlayColor'		: 'grey',
		'href'				: $(this).attr('href'),
		'type'				:'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton':true
	});
}
function initFancyBoxInline(className,showCloseButton){
	if(isEmpty(showCloseButton)){
		showCloseButton = true;
	}
	$("."+className).fancybox({
		'padding'			: 0,
		'autoScale'			: true,
		'autoDimensions'    : true,
		'transitionIn'		: 'yes',
		'transitionOut'		: 'yes',
		'overlayColor'		: 'grey',
		'href'				: $(this).attr('href'),
		'type'				:'inline',
		'hideOnOverlayClick': false,
		'showCloseButton':true
	});
}
function getScaleUrl(url,name){
	var index = url.lastIndexOf(".");
	var start = url.substring(0,index);
	var end = url.substring(index);
	return start + "_" + name + end;
}
//会员立即购推荐商品
function custAddGoods(obj){
	//会员添加推荐商品通知内屏
	var goodsJson = $(obj).siblings("input[name='custAddGoodsJSON']").val();
	if(isEmpty(goodsJson)){
		var obj3 = new Object();
		screenRequest("A03",getJsonString(obj3));
	}else{
	 	screenRequest("A03",goodsJson);
	}
}