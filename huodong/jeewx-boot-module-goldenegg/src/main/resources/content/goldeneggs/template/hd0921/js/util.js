define(function (require, exports, module) {

    /**
     * 工具类
     */
    var Util = {};

    /**
     * 模板渲染
     * @param tpl
     * @param json
     * @param fn
     * @returns {string}
     */
    Util.template = function (tpl, json, fn) {
        var res = [], reg = /{(.+?)}/g, json2 = {}, index = 0;
        for (var el in json) {
            if (typeof fn === "function") {
                json2 = fn.call(this, el, json[el], index++) || {};
            }
            res.push(
                tpl.replace(reg, function ($1, $2) {
                    return ($2 in json2) ? json2[$2] : (undefined === json[el][$2] ? json[el] : json[el][$2]);
                })
            );
        }
        return res.join('');
    };

    /**
     * 数组去重
     * @param arr 数组
     * @param callback 根据元素的指定key比较去重，默认以元素本身为key
     * @returns {Array}
     */
    Util.unique = function (arr, callback) {
        if (!Util.isArray) {
            return arr;
        }

        var obj = {},
            ret = [],
            item,
            key,
            len = arr.length;

        for (var i = 0; i < len; i++) {
            item = arr[i];
            key = item;
            if (Util.isFunction(callback)) {
                key = callback(item);
            }

            if (obj[key] !== 1) {
                obj[key] = 1;
                ret.push(item);
            }
        }

        return ret;
    };

    Util.isObject = isType("Object");
    Util.isString = isType("String");
    Util.isArray = Array.isArray || isType("Array");
    Util.isFunction = isType("Function");

    // 类型判断
    function isType(type) {
        return function (obj) {
            return {}.toString.call(obj) == "[object " + type + "]";
        }
    }

    module.exports = Util;
});
	