(function(require, exports, module){
	(function(win, doc){
		/** Author:Eric_wu, Email:wkf39988@gmail.com **/
		"use strict"
		win["_alert"] = win.alert;
		win["_confirm"] = window.confirm;
		//
		var common = {
			zIndex:1000,
			getZindex: function(){return this.zIndex+=100;},
			dialog:{}
		},
		sTpl = function(tpl, data){
			return data&&tpl.replace(/{(.*?)}/gi, function($1,$2){
				return ($2 in data)?data[$2]:$2;
			});
		};

		/**
			基础弹框类
		**/
		function _dialog(){
			var that = this;
			var arg0 = arguments[0];
			that.arg = (arg0 && (typeof arg0 == "object") )?arg0:{"str":arg0};
			that._mask = [];
			that.TPL = '<div class="widget_wrap" style="z-index:{zIndex2};">{dialog by Anjey_wu wkf39988@gmail.com}</div>';
			that.TPL_MASK = '<div class="widget_mask" style="z-index:{zIndex};"></div>';
			that._body = that.TPL;
			that.type = "dialog";
			that.zIndex = common.getZindex();
			that.id = "widget_"+that.type+"_"+that.zIndex;
			that.classes = "";
			return that;
		}
		_dialog.prototype = {
			constructor: _dialog,
			config: function(args){
				var that = this;
				if(args && ("object" == typeof args)){
					for(var arg in args){
						that[arg] = args[arg];
					}
				}
				that.arg.zIndex = that.zIndex;
				that.arg.zIndex2 = that.zIndex+50;
				that._body = sTpl(that.TPL_MASK+that.TPL, that.arg);
				return that;
			},
			init: function(){
				
			},
			handleEvent:function(evt){
				var that = this;
				that.clickFn(evt);
				return that;
			},
			open: function(){
				var that = this;
				if(!doc.body){
					win.addEventListener("DOMContentLoaded", function(){
						that.open();
					}, false);
					return that;
				}
				if(!(common.dialog[that.id])){
					that.widget = document.createElement("div");
					that.widget.setAttribute("data-role", "widget");
					that.widget.setAttribute("data-widget", "widget_"+that.type);
					that.widget.setAttribute("id", that.id);
					that.widget.style.zIndex = that.zIndex;
					that.widget.setAttribute("class", that.classes);
					that.widget.innerHTML = that._body;
					that.widget.addEventListener("click", that, false);
					doc.body.appendChild(that.widget);
					common.dialog[that.id] = that;
					that._mask.push(that.id);
				}
				that.widget.classList.add("on");
				that.widget.addEventListener("touchmove", function(e){
					e.preventDefault();
				});
				return that;
			},
			close: function(){
				var that = this;
				that.widget.classList.remove("on");
				return that;
			},
			destroy: function(){
				var that = this;
				that.widget.parentNode.removeChild(that.widget);
				delete common.dialog[that.id];
				that = undefined;
				return that;
			},
			clickFn: function(evt){
				var that = this;
				if(that.callBack && that.hasOwnProperty("callBack")){
					that._callBack = that.callBack;
					delete that.callBack;
					that.clickFn = function(evt){
						//var _that = this;
						that._callBack.call(that, evt)&&that.callBack.call(that, evt);
						//return _that;
					}
				}else{
					that.clickFn = function(evt){
						//var _that = this;
						that.callBack.call(that, evt);
						//return _that;
					}
				}
				that.clickFn(evt);
				return that;
			},
			callBack: function(evt){
				var that = this,ele = null;
				if(evt && (ele = evt.target) && ("BUTTON" == ele.tagName) ){
					that.destroy();
				}
				return that;
			}
		}

		/**
			loading 控件
		**/
		function _loading(str){
			var that = this;
			_dialog.call(that);
			that.TPL = '<div class="widget_wrap" style="z-index:{zIndex2};"><div><span></span></div></div>';
			that.type = "loading";
			that.classes = "";
			that.zIndex = common.getZindex();
			that.id = "widget_"+that.type+"_"+that.zIndex;
			return that;
		}
		_loading.prototype = new _dialog();
		_loading.prototype.constructor = _loading;

		/**
			alert 控件
		**/
		function _alert(str){
			var that = this;
			_dialog.call(that);
			that.arg = {"str":str};
			that.TPL = '<div class="widget_wrap" style="z-index:{zIndex2};">\
							<div class="widget_header"></div>\
							<div class="widget_body">{str}</div>\
							<div class="widget_footer">\
								<ul>\
									<li><button type="button">确定</button></li>\
								</ul>\
							</div>\
						</div>';
			that.type = "alert";
			that.classes = "alert";
			that.zIndex = common.getZindex();
			that.id = "widget_"+that.type+"_"+that.zIndex;
			return that;
		}
		_alert.prototype = new _dialog();
		_alert.prototype.constructor = _alert;


		/**
			confirm 控件
		**/
		function _confirm(str){
			var that = this;
			_dialog.call(that);
			that.arg = {"str":str};
			that.TPL = '<div class="widget_wrap" style="z-index:{zIndex2};" >\
				<div class="widget_header"></div>\
				<div class="widget_body">{str}</div>\
				<div class="widget_footer">\
					<ul>\
						<li><button type="button">取消</button></li>\
						<li><button type="button">确定</button></li>\
					</ul>\
				</div>\
			</div>';
			that.type = "confirm";
			that.classes = "";
			that.zIndex = common.getZindex();
			that.id = "widget_"+that.type+"_"+that.zIndex;
			return that;
		}
		_confirm.prototype = new _dialog();
		_confirm.prototype.constructor = _confirm;

		/**
			tip 控件
		**/
		function _tip(str){
			var that = this;
			_dialog.call(that);
			that.arg = {"str":str};
			that.TPL = '<div class="widget_wrap" style="z-index:{zIndex2};"><div class="widget_body">{str}</div></div>';
			that.type = "tip";
			that.classes = "";
			that.zIndex = common.getZindex();
			that.id = "widget_"+that.type+"_"+that.zIndex;
			that.t = 1500;
			return that;
		}
		_tip.prototype = new _dialog();
		_tip.prototype.delay = function(){
			var that = this;
			if(that.delay && !that.hasOwnProperty("delay")){
				that.delay = function(){
					that.destroy();
				};
			}
			setTimeout(function(){
				that.delay();
			}, that.t);
			return that;
		}
		_tip.prototype.constructor = _tip;


		/****/
		win.dialog = function(str, args){return new _dialog(args).config(args);};
		win.alert = function(str, args){return new _alert(str, args).config(args).open();};
		win.confirm = function(str, args){return new _confirm(str, args).config(args).open();};
		win.loading = function(str, args){return new _loading(str, args).config(args).open();};
		win.tip = function(str, args){return new _tip(str, args).config(args).open().delay();};
	})(window, document);
});