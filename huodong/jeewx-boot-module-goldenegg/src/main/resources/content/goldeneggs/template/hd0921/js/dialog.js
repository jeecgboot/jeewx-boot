define(function (require, exports, module) {

    var $$ = require("./zepto");
    var Register = require("./register");

    var CLS_REGISTER = 'input-wrap';
    var CLS_HEAD = 'h-wrap';
    var CLS_HEAD2 = 'h-wrap2';
    var SN_NAME = (APP.snName || 'SN码') + ': ';

    /**
     * 对话框
     * @param container：对话框容器
     * @constructor
     */
    function Dialog(container) {
        this.container = $(container);
        this.rendered = false;
        this.currentType = 0;

        this.render();
    }

    // 对话框类型
    Dialog.TYPE = {
        register: 1, // 抽奖开始前填写用户信息
        take: 2, // 领取奖品
        takeOK: 3, // 领取成功
        again: 4, // 再砸一次
        noDayTimes: 5, // 天次数用完
        noAllTimes: 6, // 总次数用完
        exchange: 7, // 兑奖
        exchangeOK: 8, // 兑奖OK
        weixinOK: 9 // 微信红包领取OK
    };

    Dialog.prototype = {
        constructor: Dialog,
        // 渲染
        render: function () {
            var _self = this;
            if (!_self.rendered) {
                _self._init();

                _self.rendered = true;
            }
        },
        // 初始化
        _init: function () {
            var _self = this,
                container = _self.container;
            _self.head = container.find('.' + CLS_HEAD);
            _self.body = container.find('section');
            _self.foot = container.find('footer');

            // 抽奖开始前填写用户信息
            if (APP.uid <= 0 && APP.regFirst) {
                _self.renderUI(Dialog.TYPE.register);
            }
        },
        // 初始化注册
        _initRegister: function (callback) {
            var _self = this,
                container = _self.container;
            // 显示注册字段
            container.find("." + CLS_REGISTER).show();

            new Register(container, function (data) {
                data = data || {};
                if (data.success && data.uid) {
                    APP.uid = data.uid;
                    APP.point = data.point;
                    callback && callback();
                } else if (data.error) {
                    alert(data.error);
                }
            });
        },
        // 绑定事件
        _bindUI: function (options) {
            var _self = this;

            switch (_self.currentType) {
                case Dialog.TYPE.register:
                    _self._initRegister(function () {
                        _self.hide();
                        // 隐藏注册字段
                        _self.container.find("." + CLS_REGISTER).hide();
                    });
                    break;
                case Dialog.TYPE.take:
                	if (false) {
                    //if (!APP.name) {
                        _self._initRegister(function () {
                            _self._handleTake(options);
                        });
                    } else {
                        // 兑奖
                        $('#J_SubmitBtn').off("click").on("click", function () {
                        	//TODO 先注掉，不处理
                            //_self.renderUI(Dialog.TYPE.exchange, options);
                        	var tel=$("#u_mobile").val();
            				var name=$("#u_name").val();
            				var adr=$("#u_address").val();
            				if(name==''){
            					alert("请输入姓名");
            					return
            				}
            				if(adr==''){
            					alert("请输入地址");
            					return
            				}
            				if(tel==''){
            					alert("请输入手机号码");
            					return
            				}
            				var regu=/^[1][3-8]+\d{9}$/;
            				var re=new RegExp(regu);
            				if(!re.test(tel)){
            					alert("请输入正确手机号码");
            					return
            				}
            				var jwid=$("#jwid").val();
            				var actId=$("#actId").val();
            				var openid=$("#openid").val();
            				var code=$("#code").val();
            				$.ajax({
            		       		url:basePath+"/goldeneggs/new/saveGoldEggPrize.do",
            		       		method:"POST",
            		       		dataType:"JSON",
            		       		data:{mobile:tel,username:name,address:adr,code:code},
            		       		success:function(data){
            		       		   if(data.success){
            		       		   		alert("提交成功，谢谢参与！");
            		       			   	url=basePath+"/goldeneggs/new/toGoldenegg.do";
            				       location.href= url + "?actId=" + actId + "&jwid=" + jwid + "&openid=" +openid;
            		       		   }else{
            		       		   		alert(data.msg);
            		       		   }
            		       		}
            		       });
                        	
                        });
                    }
                    // 关闭
                    $('#J_CloseBtn').off("click").on("click", function () {
                        _self.refresh();
                    });
                    break;
                case Dialog.TYPE.weixinOK:
                case Dialog.TYPE.exchangeOK:
                case Dialog.TYPE.takeOK:
                    // 刷新
                    $('#J_CloseBtn').off("click").on("click", function () {
                        _self.refresh();
                    });
                    break;
                case Dialog.TYPE.again:
                case Dialog.TYPE.noDayTimes:
                case Dialog.TYPE.noAllTimes:
                    // 关闭
                    $('#J_CloseBtn').off("click").on("click", function () {
                        _self.hide();
                        options.callback && options.callback();
                    });
                    break;
                case Dialog.TYPE.exchange:
                    $('#J_CloseBtn').off("click").on("click", function () {
                        _self.refresh();
                    });
                    $('#J_SubmitBtn').off("click").on("click", function () {
                        //_self._handleExchange(options);
                    });
            }

            // 奖品属性链接
            $('[data-role="link"]', _self.container).off("click").on("click", function (e) {
                var btn = $(e.currentTarget);
                _self.handleUrlBtn({c_url: btn.attr("data-url"), c_attr: btn.attr("data-attr"), snid: btn.attr("data-id")});
            });
        },
        /**
         * 渲染内容
         * @param type：对话框类型
         * @param options：对话框内容配置项
         */
        renderUI: function (type, options) {
            var _self = this,
                options = options || {},
                headCls = CLS_HEAD,
                headHTML = '',
                bodyHTML = '',
                footHTML = '';
            _self.currentType = type;
            switch (_self.currentType) {
                case Dialog.TYPE.register:
                    headHTML = '<img src="' + APP.imgs.logo + '">亲，填写信息抽奖哟~~~~';
                    bodyHTML = $('#J_Register').html();
                    footHTML = '<a href="javascript:;" id="J_SubmitBtn" class="btn-yes">开始抽奖</a>';
                    break;
                case Dialog.TYPE.take:
                    if (APP.name) {
                        if (options.c_url || !APP.onmobile) { // 奖品属性有url或非手机端兑奖
                            headHTML = '<p class="prize-shop-wrap"><img src="' + APP.imgs.logo + '"><span>'+ (options.c_attr == 3 ? '微信红包' : options.title) +'</span></p>';
                            bodyHTML = '<div class="prize-top">' + (options.c_attr == 3 ? options.title : SN_NAME + options.sn) + '</div>';
                            bodyHTML += '<div class="prize-wrap"><ul><li>' + options.c_type + '</li><li><img src="' + options.c_img + '"/></li><li>' + options.c_name + '</li></ul>';
                            if (options.c_url) {
                                footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-no">关闭</a>'+ _self._initUlrBtnHtml(options);
                            } else {
                                footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-yes">知道啦!!</a>';
                            }
                        } else {
                            headHTML = '<p>' + options.title + '</p>';
                            bodyHTML = '<div class="prize-wrap"><ul><li>' + options.c_type + '</li><li><img src="' + options.c_img + '"/></li><li>' + options.c_name + '</li></ul></div>';
                            footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-no">关闭</a><a href="javascript:;" id="J_SubmitBtn" class="btn-yes">立即兑奖</a>';
                        }
                    } else {
                        headHTML = '<p>' + options.title + '</p>';
                        if(options.c_img!=null && options.c_img!='' && options.c_img.indexOf("content")==-1){
                        	options.c_img=basePath+'/upload/img/goldeneggs'+ options.c_img;
                        }else{
                        	options.c_img=basePath+'/content/goldeneggs/template/hd0921/img/default_image.png';
                        }
                        bodyHTML = '<div class="prize-wrap"><ul><li>' + options.c_type + '</li><li><img src="' + options.c_img + '"/></li><li>' + options.c_name + '</li></ul><hr/></div>';
                        bodyHTML += $('#J_Register').html();
                        footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-no">关闭</a><a href="javascript:;" id="J_SubmitBtn" class="btn-yes">' + ((options.c_attr == 2 || options.c_attr == 3) ? '领取 ' : '领取') +'</a>';
                    }
                    break;
                case Dialog.TYPE.takeOK:
                    headHTML = '<p>领取成功</p>';
                    bodyHTML = '<div class="take-ok-wrap"><img src="' + APP.imgs.logo + '"><span>'+ SN_NAME + options.sn + '</span></div>';
                    footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-yes">知道啦</a>';
                    break;
                case Dialog.TYPE.again:
                    headCls += ' ' + CLS_HEAD2;
                    headHTML = '<p class="hit-again-wrap"><i></i>' + options.title + '</p>';
                    footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-yes">再砸一次</a>';
                    break;
                case Dialog.TYPE.noDayTimes:
                case Dialog.TYPE.weixinOK:
                	console.log(shareFlag);
                	console.log(extraLuckyDraw);
                    if(shareFlag1=='1' && extraLuckyDraw=='0'){
                    	headCls += ' ' + CLS_HEAD2;
                    	headHTML = '<p class="noday-times-wrap"><span>' + 
                    	options.title +'<br><font size="3px;" color="red"><strong>分享可额外获得1次抽奖机会</strong></font>'
                    }else{
                    	headCls += ' ' + CLS_HEAD2;
                    	headHTML = '<p class="noday-times-wrap"><span>' + 
                    	options.title 
                    }
                    + '</span></p>';
                    footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-yes">知道啦</a>';
                    break;
                case Dialog.TYPE.noAllTimes:
                    headCls += ' ' + CLS_HEAD2;
                    headHTML = '<p class="noall-times-wrap"><i></i><span>' + options.title + '</span></p>';
                    footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-yes">知道啦</a>';
                    break;
                case Dialog.TYPE.exchange:
                    headHTML = '<p>'+ SN_NAME + options.sn + '</p>';
                    bodyHTML = '<div class="exchange-wrap"><i></i><input type="text" name="snpwd" placeholder="兑奖密码" required/></div>';
                    footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-no">取消</a><a href="javascript:;" id="J_SubmitBtn" class="btn-yes">兑奖</a>';
                    break;
                case Dialog.TYPE.exchangeOK:
                    headHTML = '<p class="prize-shop-wrap"><img src="' + APP.imgs.logo + '"><span>'+ SN_NAME + options.sn +'</span></p>';
                    footHTML = '<a href="javascript:;" id="J_CloseBtn" class="btn-no">取消</a>'+ _self._initUlrBtnHtml(options);
                    break;
            }

            _self.head.html(headHTML).attr("class", headCls);
            _self.body.html(bodyHTML);
            _self.foot.html(footHTML);

            _self._bindUI(options);
            _self.show();
        },
        // 处理领取奖品
        _handleTake: function (options) {
            var _self = this,
                options = options || {};

            // 领取
            $.post(APP.urls.take, {uid: APP.uid, snid: options.snid}, function (data) {
                data = data || {};
                if (data.success) {
                    if (options.c_url) {
                        if (options.c_attr == 3) { // 领取微信红包
                            _self._renderWeixinOK();
                        } else {
                            _self.renderUI(Dialog.TYPE.exchangeOK, options);
                        }
                    } else if(APP.onmobile) {
                        _self.renderUI(Dialog.TYPE.exchange, options);
                    } else {
                        _self.renderUI(Dialog.TYPE.takeOK, {sn: options.sn});
                    }
                } else if (data.error) {
                    alert(data.error);
                }

            }, 'json');
        },
        // 处理兑奖
        _handleExchange: function (options) {
            var _self = this,
                options = options || {},
                jInput = _self.container.find("input[name='snpwd']"),
                snpwd = jInput.val();
            if (snpwd == "") {
                alert(jInput.attr("placeholder") + "不能为空！");
                return false;
            }

            // 兑奖
            $.post(APP.urls.exchange, {uid: APP.uid, snid: options.snid, snpwd: snpwd}, function (data) {
                data = data || {};
                if (data.success) {
                    _self.refresh();
                } else if (data.error) {
                    alert(data.error);
                }
            }, 'json');
        },
        // 处理奖品属性有链接的按钮
        handleUrlBtn: function (options) {
            var _self = this,
                options = options || {},
                url = options.c_url;

            if (options.c_attr == 3) {
                // 领取微信红包
                $.post(url, {uid: APP.uid, snid: options.snid}, function (data) {
                    data = data || {};
                    if (data.success) {
                        _self._renderWeixinOK();
                    } else if (data.error) {
                        alert(data.error);
                    }
                }, 'json');
            } else if (options.c_attr == 1 || options.c_attr == 2) {
                // 旺铺代金券和微盟红包，要走销毁SN码步骤
                jQiery.post(url, {uid: APP.uid, snid: options.snid}, function (data) {
                    data = data || {};
                    if (data.success) {
                        location.href = data.url;
                    } else if (data.error) {
                        alert(data.error);
                    }
                }, 'json');
            }
        },
        // 渲染微信红包提示
        _renderWeixinOK: function() {
            this.renderUI(Dialog.TYPE.weixinOK, {title: "领取成功，系统已经派发给您，<br/>请及时在公众号内查看"});
        },
        // 组装奖品属性有链接的按钮HTML
        _initUlrBtnHtml: function (options) {
            var options = options || {},
                snid = options.snid || '',
                url = options.c_url || '',
                attr = options.c_attr || 0,
                htmlArr = [];
            htmlArr.push('<a href="javascript:;" data-role="link"');
            htmlArr.push(' data-id="'+ snid +'"');
            htmlArr.push(' data-url="'+ url +'"');
            htmlArr.push(' data-attr="'+ attr +'"');
            htmlArr.push(' class="btn-yes">');
            switch(attr) {
                case 1:
                    htmlArr.push('立即使用');
                    break;
                case 2:
                    htmlArr.push('查看红包');
                    break;
                case 3:
                    htmlArr.push('领取红包');
                    break;
            }
            htmlArr.push('</a>');
            return htmlArr.join('');
        },
        // 显示
        show: function () {
            this.container.show();
        },
        // 隐藏
        hide: function () {
            this.container.hide();
        },
        // 重新加载
        refresh: function () {
            var cc=window.location.href;
            window.NEWSHREF=cc.split('&time=')[0];
            window.location.href=window.NEWSHREF+'&time='+Math.random();
        }
    };

    module.exports = Dialog;
});
