(function (require, exports, module) {

    var $ = require('content/goldeneggs/template/hd0921/js/zepto');
    var CLS_WAIT = 'waiting';

    /**
     * 注册页
     * @param container：注册页容器
     * @param callback：注册的回调函数
     * @constructor
     */
    function Register(container, callback) {
        this.container = $(container);
        this.callback = callback;
        this.rendered = false;

        this.render();
    }

    Register.prototype = {
        constructor: Register,
        // 渲染
        render: function() {
            var _self = this;
            if (!_self.rendered) {
                _self._bindUI();

                _self.rendered = true;
            }
        },
        // 绑定事件
        _bindUI: function() {
            var _self = this;

            // 验证码
            $('#J_VerifyBtn').click(function () {
                _self._handleVerify(this);
            });

            // 提交
            $('#J_SubmitBtn').click(function () {
                if (_self.verify()) {
                    _self._handleSave();
                }
            });
        },
        // 处理获取验证码
        _handleVerify: function (target) {
            var _self = this,
                mobVal = $("#J_InputMoblie").val(),
                jInput = $(target),
                timeout = 59,
                oTimer;

            if (jInput.hasClass(CLS_WAIT)) {
                return false;
            }

            jInput.addClass(CLS_WAIT);

            oTimer = setInterval(function () {
                if (timeout) {
                    jInput.text(timeout + '秒后重发');
                    timeout--;
                } else {
                    clearInterval(oTimer);
                    jInput.removeClass(CLS_WAIT).text('获取验证码');
                }
            }, 1000);

            // 发送验证码，需要判断手机号唯一性
            $.post(APP.urls.verify, {mobile: mobVal}, function (data) {
                // 发送失败json格式： {"success": false, "error": "该手机号码已经被注册!"}
                if (data && data.error) {
                    _self.callback(data);
                }
            }, 'json');
        },
        // 处理保存表单
        _handleSave: function() {
            var _self = this,
                inputData = _self.container.find("input");
            if (_self.saveLocked) { // 防止重复提交
                return;
            }
            _self.saveLocked = true;

            // 提交用户信息
            $.post(APP.urls.user, inputData, function(data) {
                _self.callback(data);
                _self.saveLocked = false;
                var cs=location.href.split('&randomsdata=')[0];
                location.href=cs+'&randomsdata='+parseInt(Math.random*10000);
            },'json');
        },
        // 验证
        verify: function() {
            var _self = this,
                result = true;
            // 必填项验证
            _self.container.find("input[required]").each(function () {
                var jInput = $(this);
                if (jInput.val() == "") {
                    _self.callback({error: jInput.attr("placeholder") + "不能为空！"});
                    result = false;
                    return false;
                } else if (jInput.attr("id") == "J_InputMoblie" && !_self._isMobile(jInput.val())) {
                    _self.callback({error: "请输入正确的手机号！"});
                    result = false;
                    return false;
                }
            });

            return result;
        },
        // 验证手机号
        _isMobile: function (val) {
            return /^\d{5,20}$/.test(val);
        },
        hide: function() {
            this.container.hide();
        }
    };

    module.exports = Register;
});
