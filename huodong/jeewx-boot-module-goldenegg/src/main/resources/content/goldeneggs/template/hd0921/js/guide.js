(function (require, exports, module) {

    var $ = require('content/goldeneggs/template/hd0921/js/zepto');

    /**
     * 引导页
     * @param type：引导页类型
     * @constructor
     */
    function Guide(type) {
        this.type = type || "share";
        this.container = $('#J_'+ this.type +'Page');
        this.rendered = false;

        this.render();
    }

    Guide.prototype = {
        constructor: Guide,
        // 渲染
        render: function() {
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
            if (!_self.container.length) {
                _self.container = $('<div id="J_'+ _self.type +'Page"></div>');
                $(document.body).append(_self.container);
            }
            _self.container.attr("class", "ui-guide ui-guide-"+ _self.type);
        },
        // 绑定事件
        _bindUI: function() {
            var _self = this,
                container = _self.container;

            container.click(function () {
                container.hide();
            });
        },
        // 显示
        show: function () {
            this.container.show();
        }
    };

    module.exports = Guide;
});
