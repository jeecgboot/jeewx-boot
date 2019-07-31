function getCookie(key){
	var strCookie=document.cookie;
	var arrCookie=strCookie.split("; ");
	for(var i=0;i<arrCookie.length;i++){
		var arr=arrCookie[i].split("=");
		if(arr[0]==key)return arr[1];
	}
	return "";
}
/** 
* 针对分享链接channel处理
*/
function channelHandle( url ,target){
	var share_weimob_id=getCookie("stat_weimobid");
	//if( share_weimob_id != null && share_weimob_id!="" &&  typeof(share_weimob_id) != "undefined" ){
		url = handleUrl(url,"channel=share"+encodeURIComponent("^#^")+share_weimob_id+"_"+target);
	//}
	//url = handleUrl(url,"share_weimob_id=",share_weimob_id+'_'+target);
	return url;
}

function handleUrl(url,value){
	//window.location = url;
	var myUrl = parseURL(url);
	var urlParams;
	var match,
        pl     = /\+/g,  // Regex for replacing addition symbol with a space
        search = /([^&=]+)=?([^&]*)/g,
        decode = function (s) { return decodeURIComponent(s.replace(pl, " ")); },
        //query  = window.location.search.substring(1);
    urlParams = {};
    while (match = search.exec(myUrl.query))
       urlParams[decode(match[1])] = decode(match[2]);
	var url = myUrl.protocol+"://"+myUrl.host+myUrl.path;

	var arr=[],i=0;
	for(var key in urlParams){
		if(key=="channel"){
			continue;
		}
		arr[i]= key+"="+encodeURIComponent(urlParams[key]);
		i++;
	}
	if(i > 0){
		var queryString = arr.join("&");
		//console.log(queryString);
		url +="?"+queryString+"&"+value;
	}else{
		url +="?"+value;
	}
	return url;
}

function parseURL(url) {
    var a =  document.createElement('a');
    a.href = url;
    return {
        source: url,
        protocol: a.protocol.replace(':',''),
        host: a.hostname,
        port: a.port,
        query: a.search.substring(1),
        params: (function(){
            var ret = {},
                seg = a.search.replace(/^\?/,'').split('&'),
                len = seg.length, i = 0, s;
            for (;i<len;i++) {
                if (!seg[i]) { continue; }
                s = seg[i].split('=');
                ret[s[0]] = s[1];
            }
            return ret;
        })(),
        file: (a.pathname.match(/\/([^\/?#]+)$/i) || [,''])[1],
        hash: a.hash.replace('#',''),
        path: a.pathname.replace(/^([^\/])/,'/$1'),
        relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [,''])[1],
        segments: a.pathname.replace(/^\//,'').split('/')
    };
}

function loadChangeUrlChannel(){
	var link=window.shareData.timeLineLink,url = channelHandle(link,"fc");
	window.shareData.timeLineLink = url;
	var link=window.shareData.sendFriendLink,url = channelHandle(link,"f");
	window.shareData.sendFriendLink = url;
}

//注：请定义全局变量is_channel_on on 开  off 关
var is_channel_on="on";
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	//alert(is_channel_on);
	if(is_channel_on=="on"){
		loadChangeUrlChannel();
	}
});