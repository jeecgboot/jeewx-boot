define(function (require, exports, module) {

    var $ = require("content/goldeneggs/template/hd0921/js/zepto");
    var winDialog = require("content/goldeneggs/template/hd0921/js/winDialog");
    var Guide = require("content/goldeneggs/template/hd0921/js/guide");
    var Player = require("content/goldeneggs/template/hd0921/js/player");
    var Util = require("content/goldeneggs/template/hd0921/js/util");
    var Dialog = require("content/goldeneggs/template/hd0921/js/dialog");

      var CLS_ON = 'on';
    var CLS_LUCK = 'luck';
    var CLS_SNOWER = 'snower';

    var TPL_COIN = '<div style="top: {top}; left: {left}; -webkit-animation: fade {t1} {t2}, drop {t1} {t2};">\
				<img src="{url}" style="-webkit-animation: counterclockwiseSpinAndFlip {t5};width:{width}; max-width:{maxHeight}">\
				</div>';

    var gPlayerTop = 0;
    var timer = null;
    var TIMEOUT_DIALOG = 2000;

    /**
     * 砸金蛋
     * @constructor 
     */
    function Egg() {
        this.rendered = false;
        this.mustFollow = APP.mustFollow && !APP.followed;

        this.render();
    }

    Egg.prototype = {
        constructor: Egg,
        // 渲染
        render: function () {
            var _self = this;
            if (!_self.rendered) {
                _self._init();
                _self._bindUI();
				 
                _self.rendered = true;
            }
        },
        // 初始化
        _init: function () {
            var _self = this,
                adHeight = 0,
                classes = ['one', 'two', 'three'];

            // 播放器
            _self.player = jQuery('#J_Player');
            // 广告
            _self.ad = jQuery(".ad");
            // 金蛋
            _self.shape = jQuery("#J_Shape");
            // 延迟transform，解决QQ浏览器经常变形问题。
            _self.shape.children().each(function(index, el) {
            	jQuery(el).addClass(classes[index]);
            });
            // 锤子
            _self.hit = jQuery("#J_Hit");
            // 锤子音乐
            _self.hitAudio = jQuery('#J_HitAudio')[0];

            // 音乐播放
            if (_self.player.length) {
                _self.player = new Player(_self.player.selector);
                gPlayerTop = parseInt(_self.player.container.css("top"));
            }
            // 对话框
            _self.dialog = new Dialog("#J_Dialog");

            adHeight = _self.ad.height();
            if (adHeight > 0) {
                _self.player.container && _self.player.container.css("top", adHeight);
            }

            // 关注引导页Mask
            if (_self.mustFollow) {
                _self.followMask = _self.createDomByClass("ui-mask");
                _self.followPage = new Guide("follow"); // 关注页
                var ad=jQuery('.ad');
                jQuery('.ui-mask').append(ad);
                ad.on('click',function(e){e.stopPropagation();});
            }

        },
        // 绑定事件
        _bindUI: function () {
            var _self = this;

            // 砸蛋
            _self.pass=new pass(function () {
            // _self.shape.delegate("span", "click", function (e) {
                var point = parseFloat(APP.point) || 0;
                if (point > 0) { // 消耗积分提示
                    confirm("兑换将消耗" + point + "积分，你确定兑换？", {
                        clickFn: function(conEvent) {
                            this.destroy();
                            // 确认按钮
                            if (jQuery(conEvent.target).parent().index() == 1) {
                                _self._handleEggClick();
                            }
                        }
                    });
                } else {
                	//点击之前的判断逻辑
                	dialog = _self.dialog;
                    jQuery.post(APP.urls.take, {uid: APP.uid}, function (data) {
                   	console.log(data);
                       data = data || {};
                       if(!data.success){
                    	   //判断开始结束时间
                    	   if(data.obj=='5'){
                    		   jQuery("#actTimeTitle").show();
                    		   _self.pass.reset();
                    		   return;
                    	   }
                    	   //判断是否关注
                    	   if(data.attributes.whetherSubscribe=='1' || data.attributes.whetherSubscribe=='' ||data.attributes.whetherSubscribe== null){
	                       	switch (data.obj) {
	                       	case '3': // 天次数用完
	                       		// egg.removeClass(CLS_ON);
	                       		setTimeout(function() {
	                       			dialog.renderUI(Dialog.TYPE.noDayTimes, data.attributes);
	                       			// 重置参与次数
	                       		 _self.pass.reset();
	                       		}, 500);
	                       		break;
	                       	case '4': // 总次数用完
	                       		setTimeout(function() {
	                       			dialog.renderUI(Dialog.TYPE.noAllTimes, data.attributes);
	                       			// 重置参与次数
	                       		 _self.pass.reset();
	                       		}, 500);
	                       		break;
	                       	default: // 异常提示
	                       	setTimeout(function() {
	                       		dialog.renderUI(Dialog.TYPE.noDayTimes, {title: data.error});
	                       	   _self.pass.reset();
	                       	}, 500);
	                       	}
                    	 }else{
                    		   jQuery("#subscript").show();
                    		   _self.pass.reset();
                    	 }
                       }else{
                       	 _self._handleEggClick();
                       }
                   }, 'json');
                }
            });

            // 广告关闭
            _self.ad.find(".ad-close").click(function () {
                _self.ad.hide();
                _self.player.container && _self.player.container.css("top", gPlayerTop);
                // 记忆广告关闭状态
                sessionStorage.setItem("adClosed", true);
            });

            // 关注
            if (_self.followMask) {
                _self.followMask.click(function () {
                    _self.followPage.show();
                });
            }

            // 中奖纪录
            jQuery('.win-table').delegate("input", "click", function (e) {
				 
                _self._handleWinTable(e.currentTarget);
            });
        },
        _handleEggClick: function() {
            var _self = this;
            if (_self.shape.find('.'+ CLS_ON).length) {
                return false;
            }

            // var egg = jQuery(e.currentTarget);
            // 中和移动设备的声音延迟播放效果
            _self._handleEgg();
            setTimeout(function() {
                // egg.toggleClass(CLS_ON);
                _self.hitAudio && _self.hitAudio.play();
            }, 800);

            // 显示锤子
            // _self.hit.addClass(CLS_ON).css({left: e.pageX, top: e.pageY});
            // // 隐藏锤子
            // timer && clearTimeout(timer);
            // timer = setTimeout(function () {
            //     _self.hit.removeClass(CLS_ON);
            // }, 1500);
        },
        // 处理砸蛋
        _handleEgg: function () {
        	
            var _self = this,
                // egg = jQuery(egg),
                dialog = _self.dialog;
            jQuery.post(APP.urls.win, {uid: APP.uid}, function (data) {
            	console.log(data);
            	console.log(data.attributes);
                data = data || {};
                switch (data.obj) {
                    case '1': // 中奖
                        _self.pass.play(true);
                        // egg.toggleClass(CLS_LUCK);
                        //_self._fallCoin();
                        jQuery("#code").val(data.attributes.code);
                        jQuery("#commonTitle").text("");
                        setTimeout(function() {
                            dialog.renderUI(Dialog.TYPE.take, data.attributes);
                            _self._resetCountNew();
                        }, TIMEOUT_DIALOG);
                        break;
                    case '2': // 未中奖
                        data.callback = function () {
                            // egg.removeClass(CLS_ON);
                        };
                        _self.pass.play(false);
                        setTimeout(function() {
                            dialog.renderUI(Dialog.TYPE.again, data.attributes);
                            // 重置参与次数
                            _self._resetCount(data.remainCount);
                            _self._resetCountNew();
                            _self.pass.reset();
                        }, TIMEOUT_DIALOG);
                        break;
                    case '3': // 天次数用完
                        // egg.removeClass(CLS_ON);
                        _self.hit.removeClass(CLS_ON);
                        _self.pass.play(false);
                        setTimeout(function() {
                            dialog.renderUI(Dialog.TYPE.noDayTimes, data.attributes);
                            // 重置参与次数
                            _self.pass.reset();
                            _self._resetCount(data.remainCount);

                        }, TIMEOUT_DIALOG);
                        break;
                    case '4': // 总次数用完
                        // egg.removeClass(CLS_ON);
                        _self.hit.removeClass(CLS_ON);
                        _self.pass.play(false);
                        setTimeout(function() {
                            dialog.renderUI(Dialog.TYPE.noAllTimes, data.attributes);
                            // 重置参与次数
                            _self.pass.reset();
                            _self._resetCount(data.remainCount);
                        }, TIMEOUT_DIALOG);
                        break;
                    default: // 异常提示
                        // egg.removeClass(CLS_ON);
                        _self.hit.removeClass(CLS_ON);
                        _self.pass.play(false);
                        setTimeout(function() {
                            dialog.renderUI(Dialog.TYPE.noDayTimes, {title: data.error});
                        }, TIMEOUT_DIALOG);
                }
            }, 'json');
        },
        // 重置参与次数
        _resetCount: function (num) {
            if (num || num == 0) {
                jQuery('#J_Times').text((+num)||0);
            }
        },
        // 重置参与次数
        _resetCountNew: function () {
        	var awardsNumCurr=jQuery('#awardsNumCurr').text();
        	var remainNumDayCurr=jQuery('#remainNumDayCurr').text();
        		jQuery('#awardsNumCurr').text(awardsNumCurr-1);
        		jQuery('#remainNumDayCurr').text(remainNumDayCurr-1);
        },
        // 处理中奖记录
        _handleWinTable: function (btn) {
            var _self = this,
                dialog = _self.dialog;

            var btn = jQuery(btn),
                role = btn.attr("data-role"),
                tr = btn.parent().parent(),
                sn = tr.attr("data-code"),
                snid = tr.attr("data-id"),
                url;

            switch (role) {
                case 'exchange': // 兑奖
                    dialog.renderUI(Dialog.TYPE.exchange, {sn: sn, snid: snid});
                    break;
                case 'take': // 领取
                    jQuery.post(APP.urls.win, {uid: APP.uid, snid: snid}, function (data) {
                        data = data || {};
                        if (1 == data.success) { // 中奖
                            dialog.renderUI(Dialog.TYPE.take, data);
                        }
                    }, 'json');
                    break;
                case 'link': // 奖品属性链接
                    if (APP.uid <= 0) {
                        jQuery.post(APP.urls.win, {uid: APP.uid, snid: snid}, function (data) {
                            data = data || {};
                            if (1 == data.success) { // 中奖
                                dialog.renderUI(Dialog.TYPE.take, data);
                            }
                        }, 'json');
                    } else {
                        dialog.handleUrlBtn({c_url: btn.attr("data-url"), c_attr: btn.attr("data-attr"), snid: snid});
                    }
                    break;
                case 'look': // 查看
                    dialog.renderUI(Dialog.TYPE.takeOK, {sn: sn});
                    break;
				 
            }
        },
        // 飘银元
        _fallCoin: function () {
            var snowsDom = jQuery('.' + CLS_SNOWER);
            var img = APP.imgs.coin;
            var num = Math.random() * 8 + 7 | 0;
            var snows = Array(num).join(",").split(",");
            var snowsHTML = "";

            if (!snowsDom.length) {
                snowsHTML = Util.template(TPL_COIN, snows, function () {
                    return {
                        top: "-30px",
                        left: Math.random() * 100 + "%",
                        t1: Math.random() * (8 - 3) + 2 + "s",
                        t2: Math.random() * 2 + "s",
                        t5: Math.random() * (8 - 3) + 2 + "s",
                        url: img,
                        width: Math.round(Math.random() * (38 - 10) + 10) + "px",
                        maxHeight: "43px"
                    };
                });
                snowsDom = jQuery('<div class="' + CLS_SNOWER + '">' + snowsHTML + '</div>');
                $(document.body).append(snowsDom);
            } else {
                snowsDom.show();
            }
        },
        // 创建指定样式名的DOM
        createDomByClass: function (sClass) {
            var jDom = jQuery('.' + sClass);
            if (!jDom.length) {
                jDom = jQuery('<div class="' + sClass + '"></div>');
                jQuery(document.body).append(jDom);
            }

            return jDom;
        }
    };

    module.exports = Egg;
});