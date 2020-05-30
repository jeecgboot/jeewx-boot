$(function(){
  $(".moveModule_1").each(function(){
	  $(this).width($(this).parent().width());
	var obj = $(this),
	h = parseInt(obj.height()),
	w = parseInt(obj.width()),
	li = obj.find(".show_piclist dd"),
	ul = obj.find(".show_piclist dl"),
	limr = parseInt(li.css("margin-right")),
	spage = obj.find(".show_pagelist span"),
	next = obj.find(".move_next"),
	prev = obj.find(".move_prev"),
	i = 0,
	sum = li.length;
	li.width(w);
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