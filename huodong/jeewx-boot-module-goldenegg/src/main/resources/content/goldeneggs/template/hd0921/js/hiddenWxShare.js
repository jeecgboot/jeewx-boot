/**
 * 隐藏微信分享按钮
 */
function onBridgeReady(){
	WeixinJSBridge.call('hideOptionMenu');
}
if (typeof WeixinJSBridge == "undefined"){
	if( document.addEventListener ){
	    document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	}else if (document.attachEvent){
	    document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	    document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	}
}else{
	onBridgeReady();
}