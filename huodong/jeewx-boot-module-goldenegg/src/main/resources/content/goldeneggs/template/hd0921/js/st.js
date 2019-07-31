/**  
 * 统计js
 * author zzy
 */
var st_config = {"memc":"","memcExpires":"","pass":0};
var st=(function(e){
	var _self = this;
	var config = {};
	/*获取cookie*/
	var _existCookie = function(key){
		var strCookie=document.cookie;
    	var arrCookie=strCookie.split("; ");
    	for(var i=0;i<arrCookie.length;i++){
    		var arr=arrCookie[i].split("=");
   			if(arr[0]==key)return true;
   		}
   		return false;
	};
	var _getCookie = function(key){
		
		var strCookie=document.cookie;
    	var arrCookie=strCookie.split("; ");
    	for(var i=0;i<arrCookie.length;i++){
    		var arr=arrCookie[i].split("=");
   			if(arr[0]==key)return arr[1];
   		}
   		return "";
	};
	var _gCookie = function(key){
		//console.log(key);
		var strCookie=document.cookie;
    	var arrCookie=strCookie.split("; ");
    	for(var i=0;i<arrCookie.length;i++){
    		var arr=arrCookie[i].split("=");
   			if(arr[0]==key)return arr[1];
   		}
   		return "";
	};
	var _setCookie = function(key,value,expire){
		var expdate = new Date();   //初始化时间
		expdate.setTime(expdate.getTime() + expire );   //时间
		document.cookie = key+"="+value+";expires="+expdate.toGMTString()+";path=/";
	};
	var _queryString = function(name){
        var svalue = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]*)(\&?)","i"));
        return svalue ? svalue[1] : svalue;
    }
	
	var __getJSON=function(args){
		var openid =_gCookie("stat_openid");// 'o_gjIjp5ONKYetCUiDo6Dr98QFdo';
		if(!openid || openid == "")
			return;
		var url = args.url+openid;
		//alert(url);
		var script = document.createElement('script');
		script.src = url;
		script.type = "text/javascript";
		document.getElementsByTagName('script')[0].appendChild(script);
		if (!+[1,]) {
				script.onreadystatechange = function() {
					if (this.readyState == 'loaded' || this.readyState == 'complete') {
						
					}
				}
			} else {
				script.onload = function() {
				
				}
			}
	}
	
	var _gJson = function(url,callbackFn){
		//alert(args.callbackFn);
		jq.ajax({
			 async:false,
			 type:'get',
			 url:url,
			 dataType:'jsonp',
			 jsonpCallback:callbackFn,
			 success : function(json){
                 console.log(json);
                 //alert(json[0].name);
             }
		});
		
//		Ajax.send({
//			url:url,
//			async: false,
//			dataType: 'jsonp',
//			jsoncallback:callbackFn
//		});
//		$.ajax({
//			url:url+'&jsonp='+callbackFn,
//			type:'get',
//			async:false,
//			dataType:'jsonp',
//			success:function(){}
//		})
	};
	/***生成channelInfo ***/
	var _createChannelInfo = function(args){
		var channel=_getCookie("stat_channel"),channelInfo="";
		//清空cookie中记录
		_setCookie("stat_channel","",1);
		//判断是否存在channel_expires
		if( args["channel_expires"] && args["channel_expires"] < Math.ceil( Date.parse(new Date())/1000 ) ){
			args["from_channel"] = ""; //清空from_channel
		}

		//判断ocs是否记录channel
		if( args["from_channel"] )
		{
			channelInfo = args["from_channel"];
		}else if( channel ){
			channelInfo = channel;
		}else{
			return false;
		}
		return channelInfo;
	}
	/***生成ActionSeqId***/
	var _createActionSeqId = function( args ){
		var channel=_getCookie("stat_channel");
		var stat_action_seq_id_old=_getCookie("stat_action_seq_id");
		st_config.channel_info = channel;
		if( !channel && stat_action_seq_id_old){
			return _getCookie("stat_action_seq_id");
		}
		//_gJson({'url':args.memcServerPath+"&key=channel_expires_","callbackFn":"_memcTime"});
		//_gJson({'url':args.memcServerPath+"&key=channel_",'callbackFn':"_returnBackMemc"});
		//_gJson(args.memcServerPath+"&key=channel_expires_"+args.stat_openid,"_memcTime");
		//_gJson(args.memcServerPath+"&key=channel_"+args.stat_openid,"_returnBackMemc");
		var from_channel_expires= st_config.memcExpires;
		//console.log(from_channel_expires+'-3');

		var from_channel = st_config.memc;
		//console.log(st_config.memc+'-3');
		var channelInfo = _createChannelInfo({
			"from_channel":from_channel,
			"channel_expires":from_channel_expires
		});

		if( !channelInfo ){
			return _getCookie("stat_action_seq_id");
		}
		 // 密码字符集，可任意添加你需要的字符  
		var chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789',stat_random='',len = chars.length;  
		for ( var i = 0; i < 32; i++ )
		{
			id = Math.ceil( Math.random()*( len-1 ) );
			stat_random += chars[ id ];  
		}
		var stat_weimobid = _getCookie("weimobID");
	   var stat_action_seq_id = stat_random+":"+channelInfo+":"+stat_weimobid;
		_setCookie("stat_action_seq_id", stat_action_seq_id ,3650*24*3600);
		return stat_action_seq_id;
	}
	/**
	 * 页面加载执行
	 * @param openId 
	 * @param pid 
	 * @param bid 
	 * @param webType 类型
	 * @param module 模块
	 * @param statisticServerPath  项目中配置
	 * @param memcServerPath
	 */
	var _triggerEvent = function(args){
		var config = {
			"stat_openid":_getCookie("stat_openid"),
			"stat_pid":_getCookie("stat_pid"),
			"stat_bid":_getCookie("stat_bid"),
			"stat_webtype":_getCookie("stat_webtype"),
			"stat_module":_getCookie("stat_module")
		};
		
		//__getJSON({'url':args.memcServerPath+"&key=channel_expires_"});	
		//__getJSON({'url':args.memcServerPath+"&key=channel_"});	
		//for(;st_config.pass<10;){}

		//action_seq_id
		var stat_action_seq_id = _createActionSeqId( {
			"stat_openid":config.stat_openid,
			"memcServerPath":args.memcServerPath
		});
		//统计
                var stat_weimobID = _getCookie("Weimob_SessionId_" + config.stat_pid);
                if (stat_weimobID == "" || stat_weimobID == undefined) {
		   stat_weimobID = _getCookie("weimobID");
                }
		if (stat_weimobID == "" || stat_weimobID == undefined) {
			stat_weimobID = _getCookie("NweimobID");
		}
		var _args={
			"stat_pid":config.stat_pid,
			"stat_bid":config.stat_bid,
			"stat_action_seq_id":stat_action_seq_id,
			"stat_webtype":config.stat_webtype,
			"stat_module":config.stat_module,
			"stat_action":args.stat_action,
			"stat_optValue":args.stat_optValue,
			"statisticServerPath":args.statisticServerPath
		};
		//console.log(st_config.memcExpires+'-1');
		var arr=[];
		arr[0] = "StatType=qudao";
		arr[1] = "url="+encodeURIComponent(location.href);
		arr[2] = "stat_bid="+_args.stat_bid;
		arr[3] = "stat_pid="+_args.stat_pid;
		arr[4] = "stat_action_seq_id="+_args.stat_action_seq_id;
		arr[5] = "stat_webtype="+_args.stat_webtype;
		arr[6] = "stat_module="+_args.stat_module;
		arr[7] = "stat_time="+Math.ceil( Date.parse(new Date()) / 1000 );
		arr[8] = "stat_action="+_args.stat_action;
		arr[9] = "stat_optValue="+_args.stat_optValue+':'+config.stat_openid+':'+st_config.channel_info;
		arr[10] = "stat_weimobID="+stat_weimobID;
		var str= arr.join("&");
		var wm = document.createElement("script");
		wm.src = args.statisticServerPath + "?" + str;
		console.log(wm.src);
		var s = document.getElementsByTagName("body")[0]; 
		s.appendChild(wm);
	}
	return {	
		push:function(fn,args){
			if(args.is_statistic_on=="off"){
				return false;
			}
			var tryNum = 0;
			
			function timedCount(){
				var bExist=_existCookie("stat_openid");
				if(bExist || tryNum == 6){
					eval('__getJSON').call(this,{'url':args.memcServerPath+"&key=channel_expires_"});	
					eval('__getJSON').call(this,{'url':args.memcServerPath+"&key=channel_"});	
					setTimeout(function(){
					//延迟执行执行
					eval(fn).call(this,args);	
					},2000);
				}else{
					setTimeout(timedCount,500);
				}
				tryNum +=1;
			}
			timedCount();
				
		}
	}	
})();
function _memcTime(data){
	//console.log(data+'-2');
	//alert(data+'-2');
	st_config.memcExpires = data;
}

function _returnBackMemc(data){
		//alert(data+'-1');
		//console.log(data+'-1');
		st_config.memc = data;
}

/***调用案例***/
//注：is_statistic_on on 开  off 关
//页面统计使用案例
//st.push("_triggerEvent",{
//		"is_statistic_on":"on",
//		"statisticServerPath": "http://statistic.dev.weimob.com/wm.js", //统计地址
//		"memcServerPath": "http://121.42.10.197/memc?cmd=get", //缓存地址
//		"stat_action":"loadPage",
//		"stat_optValue":""
//});
