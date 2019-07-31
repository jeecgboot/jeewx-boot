(function (require, exports, module) {

    var $ = require('content/goldeneggs/template/hd0921/js/zepto');
    var CLS_ON = 'on';

    /**
     * 播放器
     * @param container：播放器容器
     * @param autoPlay：是否自动播放，默认true
     * @constructor
     */
    function Player(container, autoPlay) {
        this.container = $(container);
        this.autoPlay = !!autoPlay || true;
        this.on = false;
        this.rendered = false;

        this.render();
    }

    Player.prototype = {
        constructor: Player,
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
            var _self = this;
            _self.button = _self.container.find(".player-btn");
            _self.player = _self.container.find(".player")[0];

            if (_self.autoPlay) {
                _self.play();
            }
        },
        // 绑定事件
        _bindUI: function () {
            var _self = this;

            _self.button.click(function () {
                if (_self.on) {
                    _self.pause();
                } else {
                    _self.play();
                }
            });

            // 微信自动播放
            $(document).on("WeixinJSBridgeReady", function() {
                _self.play();
            });
        },
        // 播放
        play: function () {
            var _self = this;
            _self.player.play();
            _self.button.addClass(CLS_ON);
            _self.on = true;
        },
        // 暂停
        pause: function () {
            var _self = this;
            _self.player.pause();
            _self.button.removeClass(CLS_ON);
            _self.on = false;
        }
    };

    module.exports = Player;
});
