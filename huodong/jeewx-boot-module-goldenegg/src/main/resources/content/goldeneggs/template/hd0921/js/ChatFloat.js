var SocketIp = "120.55.104.98";
var TimeInterval;
var getType = 1;
var ajaxAddress = "http://kf.weimob.com/";

var Factory = {
    create: function () {
        return function () { this.init.apply(this, arguments); }
    }
}

var ChatFloatFactory = Factory.create();

ChatFloatFactory.prototype = {
    init: function () {
        return this;
    },
    setCookie: function (name, value, seconds) {
        var exp = new Date();
        exp.setTime(exp.getTime() + seconds);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    },
    getCookie: function (name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    },
    touches: function (obj) {
        obj.addEventListener("touchmove", function () {
            event.preventDefault();
            obj.style.left = (event.targetTouches[0].pageX - -document.body.scrollLeft - 30) + "px";
            obj.style.top = (event.targetTouches[0].pageY - document.body.scrollTop - 30) + "px";
        }, false)
    },
    AjaxRight: function (t, options, cookobj3g) {
        var e = this;
        var ajax = new Ajax(function (responseText, responseXML) {
            if (responseText) {
                var obj = eval('(' + responseText + ')');
                if (obj) {
                    e.setCookie('_useright_' + options.AId, '{"show":' + obj.succ + ',"aid":' + options.AId + ',"weimobid":"' + obj.weimobid + '","socket":' + obj.socket + ',"openid":"' + options.openid + '"}', 1800 * 1000);
                    if (obj.succ && obj.weimobid && obj.weimobid.length > 0) {
                        t.initialize(options, obj.weimobid, obj.msgcount, obj.socket);                       
                        e.setCookie('_3GUserInfo_' + options.AId, '{"aid":' + options.AId + ',"weimobid":"' + obj.weimobid + '","openid":"' + options.openid + '","headurl":"' + obj.headurl + '","markname":"' + obj.markname + '","nickname":"' + obj.nickname + '"}', 3600 * 24 * 10000 * 1000);
                        Tools.LocationSource(options, options.AId, obj.weimobid, obj.nickname, obj.markname, obj.headurl);
                    }
                }
            }
        }, function (status) { });
        var openid = options.openid ? options.openid : "";
        var weimobid = "";
        if (cookobj3g != null) {
            weimobid = cookobj3g.weimobid;
        }
        ajax.get(ajaxAddress + "Ajax/CustomerChatAjax.aspx?action=userright&AId=" + options.AId + "&openid=" + openid + "&weimobid=" + weimobid, true);
    },
    SocketMessage: function (AId, weimobid) {
        var e = this;
        var ajax = new Ajax(function (responseText, responseXML) {
            if (responseText) {
                e.PollingSuccess(responseText);
            }
        }, function (status) { });
        ajax.get(ajaxAddress + 'Ajax/CustomerChatAjax.aspx?action=GetFloatSocketMessage&weimobid=' + weimobid + "&AId=" + AId, true);
    },
    StartPolling: function (AId, weimobid) {
        getType = 2;
        clearInterval(TimeInterval);
        TimeInterval = setInterval(GetPolling, 10000);
        this.GetPolling(AId, weimobid);
    },
    GetPolling: function (AId, weimobid) {
        var e = this;
        var ajax = new Ajax(function (responseText, responseXML) {
            if (responseText) {
                this.PollingSuccess(responseText);
            }
        }, function (status) { });
        ajax.get(ajaxAddress + 'Ajax/CustomerChatAjax.aspx?action=GetFloatPolling&weimobid=' + weimobid + "&AId=" + AId, true);
    },
    PollingSuccess: function (count) {
        if (parseInt(count) > 0) {
            document.getElementById("CustomerChatFloat").innerHTML = "<span style='width:9px;height:9px;background-color:#e90707;border-radius: 20px;display: block;position: absolute;left: 46px;top: 15px;'></span>";
        }
    },
    loadjscssfile: function (filename, filetype, loadfunction) {
        var fileref;
        if (filetype == "js") {
            fileref = document.createElement('script');
            fileref.setAttribute("type", "text/javascript");
            fileref.setAttribute("src", filename);

        } else if (filetype == "css") {
            fileref = document.createElement('link');
            fileref.setAttribute("rel", "stylesheet");
            fileref.setAttribute("type", "text/css");
            fileref.setAttribute("href", filename);
        }
        if (typeof fileref != "undefined") {
            document.getElementsByTagName("head")[0].appendChild(fileref);
        }
        fileref.onload = fileref.onreadystatechange = function () {
            if (!this.readyState || this.readyState == 'loaded' || this.readyState == 'complete') {
                loadfunction();
            }
        }
    },
    LocationSource: function (options, aid, weimobid, nickname, markname, headurl) {
        var ajax = new Ajax(function (responseText, responseXML) {
            if (responseText) {
                
            }
        }, function (status) { });
        var openid = options.openid ? options.openid : "";
        var param = "AId=" + aid + "&openid=" + openid + "&weimobid=" + weimobid + "&nickname=" + nickname + "&markname=" + markname + "&headurl=" + headurl;
        ajax.get(ajaxAddress + "Ajax/CustomerChatAjax.aspx?action=LocationSource&" + param, true);
    }
}

var Tools = new ChatFloatFactory();

window.ChatFloat = function (options) {
    var e = this;
    if (options && options.AId) {
        //如果是APP的，则结束
        if (window.location.href.indexOf("APP=true") > 0) {
            return;
        }
        if (options.IsTest != undefined && options.IsTest) {
            SocketIp = "121.43.196.115";
            ajaxAddress = "http://112.124.16.233/";
        }

        var cooktext = Tools.getCookie("_3GUserInfo_" + options.AId);
        var cookobj3g = eval('(' + cooktext + ')');
        
        //判断有没有显示的权限
        var strStoreData = Tools.getCookie("_useright_" + options.AId);
        if (strStoreData && strStoreData.length > 0) {
            var objStorage = eval('(' + strStoreData + ')');
            if (objStorage && objStorage.aid == options.AId) {
                if (objStorage.show && objStorage.weimobid) {
                    var socket = 0;
                    if (objStorage.socket) {
                        socket = objStorage.socket;
                    }
                    e.initialize(options, objStorage.weimobid, 0, socket);
                    //Tools.SocketMessage(options.AId, objStorage.weimobid);
                    //写访问记录
                    if (cookobj3g != null) {
                        Tools.LocationSource(options, cookobj3g.aid, cookobj3g.weimobid, cookobj3g.nickname, cookobj3g.markname, cookobj3g.headurl);
                    }
                }
            }
            else {
                Tools.AjaxRight(e, options, cookobj3g);
            }
        }
        else {
            Tools.AjaxRight(e, options, cookobj3g);
        } 
    }
};

ChatFloat.prototype = {
    initialize: function (options, weimobid, msgcount, socket) {
        var e = this;
        var mydiv = window.document.createElement("a");
        var openid = options.openid ? options.openid : "";
        mydiv.href = ajaxAddress + "MobileTalking.aspx?aid=" + options.AId + "&openid=" + openid + "&weimobid=" + weimobid;
        mydiv.setAttribute("id", "CustomerChatFloat");
        mydiv.style.position = "fixed";
        var left = options.left != undefined ? options.left : -1;
        if (left >= 0) {
            mydiv.style.left = left + "px";
        }
        else {
            var right = options.right != undefined ? options.right : 0;
            if (right >= 0) {
                mydiv.style.right = right + "px";
            }
        }
        var top = options.top != undefined ? options.top : -1;
        if (top >= 0) {
            mydiv.style.top = top + "px";
        }
        else {
            var bottom = options.bottom != undefined ? options.bottom : 0;
            if (bottom >= 0) {
                mydiv.style.bottom = bottom + "px";
            }
        }
        mydiv.style.zIndex = 99999;
        mydiv.style.height = (options.height ? options.height : 70) + "px";
        mydiv.style.width = (options.width ? options.width : 65) + "px";
        mydiv.style.minWidth = (options.width ? options.width : 65) + "px";
        mydiv.style.background = "url('http://kf.weimob.com/images/MobileChatFloat.png') no-repeat 0px 0px";
        mydiv.style.backgroundSize = (options.width ? options.width : 65) + "px auto";
        if (msgcount > 0) {
            mydiv.innerHTML = "<span style='width:9px;height:9px;background-color:#e90707;border-radius: 20px;display: block;position: absolute;left: 46px;top: 15px;'></span>";
        }
        window.document.body.appendChild(mydiv);
        if (socket == 1) {
            Tools.loadjscssfile("http://" + SocketIp + ":8889/socket.io/socket.io.js", "js", function () { e.socketconnect(options, weimobid); });
        }
        var draggable = options.draggable != undefined ? options.draggable : true;
        if (draggable) {
            Tools.touches(document.getElementById("CustomerChatFloat"));
        }
    },
    socketconnect: function (options, weimobid) {
        try {
            var socket = io.connect('http://' + SocketIp + ':8889/');
            //连接事件
            socket.on("connect", function () {
                clearInterval(TimeInterval);
                getType = 1;
                socket.emit('paramquery', {
                    aid: options.AId,
                    weimobid: weimobid
                });
                socket.on('dataChange', function (json) {
                    if (json) {
                        if (json.msgCount == 1) {
                            Tools.SocketMessage(options.AId, weimobid);
                        }
                    }
                });
            });
            //重新连接
            socket.on('reconnecting', function (data) {
                
            });
            //重新连接成功
            socket.on('reconnect', function (data) {
                
            });
            socket.on('disconnect', function () { });
        }
        catch (e) {
        }
    }
};

var Ajax = Factory.create();

Ajax.prototype = {
    init: function (successCallback, failureCallback) {
        this.xhr = this.createXMLHttpRequest();
        var xhrTemp = this.xhr;
        var successFunc = null;
        var failFunc = null;

        if (successCallback != null && typeof successCallback == "function") {
            successFunc = successCallback;
        }

        if (failureCallback != null && typeof failureCallback == "function") {
            failFunc = failureCallback;
        }

        this.xhr.onreadystatechange = function () {
            if (xhrTemp.readyState == 4) {
                if (xhrTemp.status == 200) {
                    if (successFunc != null) {
                        successFunc(xhrTemp.responseText, xhrTemp.responseXML);
                    }
                }
                else {
                    if (failFunc != null) {
                        failFunc(xhrTemp.status);
                    }
                }
            }
        }
    },
    get: function (url, async) {
        this.xhr.open("GET", url, async);
        this.xhr.send();
    },
    createXMLHttpRequest: function () {
        if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        }
        else {
            return new ActiveXObject("Microsoft.XMLHTTP");
        }

        throw new Error("Ajax is not supported by the browser!");
    },
    post: function (url, data, async) {
        this.xhr.open("POST", url, async);
        this.xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        this.xhr.send(data);
    }
}