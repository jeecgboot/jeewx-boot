// JavaScript Document
//µ¼º½_function
$(function(){
	var NMore = $("#NMore"),nav = $("#nav"),Nzz = $("#Nzz");
	if(nav.length){
		var topH = nav.offset().top+nav.height();
		if(NMore.hasClass("NMore_1002")){
				NMore.css("top",0);
		}else{
				NMore.css("top",topH);
		}
	}
	$("#nav .navMoreBut,#fixed_nav_but").click(function(){
		var state = NMore.attr("state");
		if(state== "open"){
			cNav(NMore);
		}else{
			oNav(NMore);
		}
		return false;
	});
	function cNav(obj){
		obj.attr("state","close");
		if(obj.hasClass("NMore_1002")){
			Nzz.css({"display":"none"});
		}
	}
	function oNav(obj){
		if(obj.hasClass("NMore_1002")){
			var w = $(document).width();
			var h = $(document).height()
			Nzz.css({"display":"block"});
		}
		obj.attr("state","open");
	}
	
	Nzz.click(function(){
		if(NMore.attr("state") == 'open'){
			cNav(NMore);
		}
	});
	NMore.click(function(){
		if(NMore.attr("state") == 'open'){
			cNav(NMore);
		}
	});
	$("#back_top").click(function(){
		$(document).find("body").animate({"scrollTop":0},1000);
	});
});
