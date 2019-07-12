(function($){
  $.fn.extend({
	"moveModule" : function(options){
	  return this.each(function(){
		var defaultO = {
		  axis : "top",
		  speed : "slow",
		  type : "flow",
		  hand : false
		};
		var O = $.extend(defaultO,options);
		var speed = 100;
		if(O.type == "flow")
		{
		  switch(O.speed){
			case "slowly":
			  speed = 150;
			break;
			case "slow":
			  speed = 100;
			break;
			case "normal":
			  speed = 60;
			break;
			case "quick":
			  speed = 30;
			break;
			case "quickly":
			  speed = 5;
			break;
		  }
		}else if(O.type == "single")
		{
		  switch(O.speed){
			case "slowly":
			  speed = 5000;
			break;
			case "slow":
			  speed = 4000;
			break;
			case "normal":
			  speed = 3000;
			break;
			case "quick":
			  speed = 2000;
			break;
			case "quickly":
			  speed = 1000;
			break;
		  }
		}
		var _this = $(this),
			lilength = _this.find("li").length,
			movepx = 0,
			times=null,
			thisPar = $(this).parent();
			if(lilength >= 2){
				// thisPar.height(_this.find("li:first").height()*(lilength-1));
				thisPar.height(_this.find("li:first").outerHeight()*(lilength));
			}else{
				// thisPar.height(h);
				thisPar.height(_this.find("li:first").outerHeight());
			}
		var	thisParH = thisPar.height(),
			thisParW = thisPar.width(),
			firstChild = _this.children().first();
		if(O.axis == "top" || O.axis == "bottom"){
		 var thisH = _this.height();
		}else if(O.axis == "left" || O.axis == "right"){
		  if(_this.hasClass("proListmodule_1")){
			firstChild.children().width(thisParW);
		  }
		  var thisW = firstChild.width();
		  _this.width(firstChild.width()*2+10);
		  }
		  var clone = $(firstChild.clone());
		//向上
		if(O.axis == "top")
		{
		  movepx = 0;
		  _this.append(clone);
		  times=setInterval(moveT,speed);
		  _this.bind("mouseout",function(){
			  times = setInterval(moveT,speed);
		  });
		  _this.bind("mouseover",function(){
			clearInterval(times);
		  });
		}
		//向下
		if(O.axis == "bottom")
		{
		  movepx = -(thisH+(thisH - thisParH));
		  _this.css({"margin-top":-thisH}).append(clone);
			times=setInterval(moveB,speed);
			_this.bind("mouseout",function(){
			  times = setInterval(moveB,speed);
			});
		  _this.bind("mouseover",function(){
			  clearInterval(times);
		  });
		}
		//向左
		if(O.axis == "left")
		{
		  movepx = 0;
		  _this.css({"margin-left":0}).append(clone);
			times=setInterval(moveL,speed);
			_this.bind("mouseout",function(){
			  times = setInterval(moveL,speed);
			});
		  _this.bind("mouseover",function(){
			  clearInterval(times);
		  });
		}
		//向右
		if(O.axis == "right")
		{
		  movepx = -(thisW);
		  _this.css({"margin-left":movepx}).append(clone);
			times=setInterval(moveR,speed);
			_this.bind("mouseout",function(){
			  times = setInterval(moveR,speed);
			});
		  _this.bind("mouseover",function(){
			  clearInterval(times);
		  });
		}
		//下移动函数
		function moveB()
		{
		  if(thisH!=firstChild.height()){
			thisH = firstChild.height();
		  }
		  var mt = parseInt(_this.css("margin-top"));
		  var itemH = firstChild.children().outerHeight();
		  if(O.type=="flow")
		  {
			if(mt<0){
			  _this.css("margin-top",movepx);
			  movepx++;
			}else{
			  movepx=-thisH;
			  _this.css("margin-top",movepx);
			}
		  }else if(O.type == "single"){
			if(mt<0){
				_this.animate({"margin-top":mt+itemH},500);
			}else{
				_this.css("margin-top",-thisH);
				_this.animate({"margin-top":-(thisH-itemH)},500);
			}
		  }
		}		
		//上移动函数
		function moveT()
		{
		  if(thisH!=firstChild.height()){
			thisH = firstChild.height();
		  }
		  var itemH = firstChild.children().outerHeight();
		  var mt = Math.abs(parseInt(_this.css("margin-top")));
		  if(O.type == "single"){
			if(mt<thisH){
				_this.animate({"margin-top":-(mt+itemH)},500);
			}else{
				_this.css("margin-top",0);
				_this.animate({"margin-top":-(itemH)},500);
			}
		  }else if(O.type == "flow"){
			if(mt<thisH){
			  _this.css("margin-top",-movepx);
			  movepx++;
			}else{
			  movepx=0;
			  _this.css("margin-top",-movepx);
			}			  
		  }
		}
		//左移动
		function moveL()
		{
		  if(thisParW!=_this.parent().width()){
			if(_this.hasClass("proListmodule_1")){
			  thisParW = _this.parent().width();
			  firstChild.children().width(thisParW);
			  clone.remove();
			  clone = $(firstChild.clone());
			  _this.css({"margin-left":0}).append(clone);
			  thisW = firstChild.width();
			  _this.width(firstChild.width()*2);
			}
		  }
		  var itemW = firstChild.children().outerWidth();
		  var ml = Math.abs(parseInt(_this.css("margin-left")));
		  if(O.type == "single"){
			if(ml<thisW){
			  _this.animate({"margin-left":-(ml+itemW)},500);
			}else{
			  _this.css("margin-left",0);
			  _this.animate({"margin-left":-(itemW)},500);
			}
		  }else if(O.type == "flow"){
			if(ml<thisW){
			  _this.css("margin-left",-movepx);
			  movepx++;
			}else{
			  movepx=0;
			  _this.css("margin-left",-movepx);
			}			  
		  }
		}

		//右移动
		function moveR()
		{
		  if(thisParW!=_this.parent().width()){
			if(_this.hasClass("proListmodule_1")){
			  thisParW = _this.parent().width();
			  firstChild.children().width(thisParW);
			  clone.remove();
			  clone = $(firstChild.clone());
			  _this.css({"margin-left":-firstChild.width()}).append(clone);
			  thisW = firstChild.width();
			  _this.width(firstChild.width()*2);
			}
		  }
		  var itemW = firstChild.children().outerWidth();
		  var ml = parseInt(_this.css("margin-left"));
		  if(O.type == "single"){
			if(ml<0){
			  _this.animate({"margin-left":ml+itemW},500);
			}else{
			  _this.css("margin-left",-thisW);
			  _this.animate({"margin-left":-(thisW-itemW)},500);
			}
		  }else if(O.type == "flow"){
			if(ml<0){
			  _this.css("margin-left",movepx);
			  movepx++;
			}else{
			  movepx=0;
			  _this.css("margin-left",movepx);
			}			  
		  }
		}
		
	  });
	}
  });
})(jQuery)

//banner图js
$(function(){
  $(".moveModule_1").each(function(){
	  $(this).width($(this).parent().width());
	var obj = $(this),
	h = obj.height(),
	w = parseInt(obj.width()),
	li = obj.find(".show_piclist dd"),
	ul = obj.find(".show_piclist dl"),
	limr = parseInt(li.css("margin-right")),
	spage = obj.find(".show_pagelist span"),
	next = obj.find(".move_next"),
	prev = obj.find(".move_prev"),
	i = 0,
	sum = li.length;
	/*obj.find(".show_piclist").css({"width":w+"px","height":h+"px","overflow":"hidden"});
	obj.find(".show_piclist dd").css({"width":w+"px","height":h+"px","overflow":"hidden"});*/
	function move(c){
	  if(spage.length>1){
		spage.removeClass("cur");
		obj.find(".show_pagelist span:eq("+c+")").addClass("cur");
	  }
	  ul.animate({"margin-left":-(w+limr)*c},1000);		
	}
	var times = setInterval(function(){
	  if(i<sum-1){
		i++;
	  }else{
		i=0;
	  }
	  move(i);
	},5000);
	obj.bind("mouseenter",function(){
		clearInterval(times);
	  });
	obj.bind("mouseleave",function(){
	  times = setInterval(function(){
		if(i<sum-1){
		  i++;
		}else{
		  i=0;
		}
		move(i);
	  },5000);
	  });
	  spage.bind("click",function(){//alert($(this).index());
		$(this).addClass("cur");
		$(this).siblings().removeClass("cur");
		i = $(this).index();
		move(i);
	  });
	  if(next.length>0 || prev.length>0){
		  obj.mouseenter(function(){
			 next.animate({"opacity":1},1000);
			 prev.animate({"opacity":1},1000);
		  });
		  obj.mouseleave(function(){
			 next.animate({"opacity":0},1000);
			 prev.animate({"opacity":0},1000);			 
		  });
		next.css({"top":(h/2)-20}).bind("click",function(){
		  if(ul.is(':animated') == false){
			if(i<sum-1){
			  i++;
			}else{
			  i=0;
			}
			move(i);
		  }
		});
		prev.css({"top":(h/2)-20}).bind("click",function(){
		  if(ul.is(':animated') == false){
			if(i>0){
			  i--;
			}else{
			  i=sum-1;
			}
			move(i);
		  }
		});
	  }
	  move(i);
  });
});