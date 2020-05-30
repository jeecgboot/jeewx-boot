$(function(){
  //头部 会员登录，注册（ev_t_top_user） 搜索（ev_t_top_search） 导航（ev_t_top_menu）是否显示
 $(".ev_t_top_user").click(function(){
   var data_user = $(this).attr("data-user");
	if(data_user == 0){
	  if($('.ev_t_top_user_div').css('display') == 'none'){
		$(".ev_t_top_user_div").show();
		$(".ev_t_top_user_a").show();
		$(".ev_t_top_search_div").hide();
		$(".ev_t_top_search_a").hide();
	  }else{
		$(".ev_t_top_user_div").hide();
		$(".ev_t_top_user_a").hide();
	  }
	}
  });

  $(".ev_t_top_search").click(function(){
	if($('.ev_t_top_search_div').css('display') == 'none'){
	  $(".ev_t_top_search_div").show();
	  $(".ev_t_top_search_a").show();
	  $(".ev_t_top_user_div").hide();
	  $(".ev_t_top_user_a").hide();
	}else{
	  $(".ev_t_top_search_div").hide();
	  $(".ev_t_top_search_a").hide();
	}
  });
  $(".ev_t_top_menu").click(function(){
	  var h = $(document).height();
	  $(".ev_t_bg_kuang").height(h).show();
  });
  $(".ev_t_bg_kuang").click(function(){
	  $(".ev_t_bg_kuang").hide();
  });

  //页面输出类型

  //分享
  $(document).on("click",".ev_t_train_xin",function(){
	  var that       = $(this);
	  var username  = $('#username').val();
	  var channel_id = $('#channel_id').val();
	  var doc_id     = that.attr("data-doc-id");
	  var type       = $('#channel_type').val();
	  var title      = that.attr("data-title");
	  var timestamp  = Date.parse(new Date());

  });
  //类别
 $(".ev_t_train .ev_t_product_tit_sx").click(function(){
	if($('.ev_t_product_c2').css("display") == "none"){
	  $(".ev_t_product_c2").show();
	  $(".ev_t_product_c3").hide();
	  $(".ev_t_product_c3_bg").hide();
	  $(".ev_t_train .ev_t_product_tit_sx").addClass("ev_t_product_tit_open");
	}else{
	  $(".ev_t_train .ev_t_product_tit_sx").removeClass("ev_t_product_tit_open");
	  $(".ev_t_product_c2").hide();
	  $(".ev_t_product_c3").hide();
	  $(".ev_t_product_c3_bg").hide();
	}
  });
 $(".ev_t_product_c2").on({
	click : function(){
	  var index = $(this).index();
	  if(index != 0){
		$(".ev_t_product_c3_bg").show();
		$(".ev_t_product_c3").show();
		$(".ev_t_product_c3 ul:eq("+(index-1)+")").show().siblings().hide();
	  }
	}
  },"li");
  $(".ev_t_product_c2 .ev_t_product_c_div").each(function(){
	$(this).click(function(){
	  var shuzi = $(this).parent().index();
	  $(this).parent().addClass("ev_t_product_cur").siblings().removeClass("ev_t_product_cur");
	  if($(this).parent().attr("class") == "ev_t_product_cur"){
		$(".ev_t_product_c3 i").addClass("ev_t_product_c_i");
		var iheight = 12+(shuzi-1)*$(this).parent().outerHeight();
		$(".ev_t_product_c_i").css("top",iheight+"px");
	  }else{
		$(".ev_t_product_c3  i").removeClass("ev_t_product_c_i");
	  }

	});
  });
  $(".ev_t_product_c2 .ev_t_product_c_both").each(function(){
	$(this).click(function(){
	  $(this).parent().addClass("ev_t_product_cur").siblings().removeClass("ev_t_product_cur");
	  $(".ev_t_product_c3_bg").hide();
	  $(".ev_t_product_c3").hide();
	  $(".ev_t_product_c2").hide();
	});
  });
  $(".ev_t_product_c3 .ev_t_product_c_div").each(function(){
	$(this).click(function(){
	  $(this).parent().addClass("ev_t_product_cur").siblings().removeClass("ev_t_product_cur");
	  if($(this).parent().attr("class") == "ev_t_product_cur"){
		$(".ev_t_product_c3_bg").hide();
		$(".ev_t_product_c3").hide();
		$(".ev_t_product_c2").hide();
		$(".ev_t_product_tit_sx").removeClass("ev_t_product_tit_open");
	  }
	});
  });
  $(".ev_t_product_c3 .ev_t_product_c_both").each(function(){
	$(this).click(function(){
	  $(this).parent().addClass("ev_t_product_cur").siblings().removeClass("ev_t_product_cur");
	  if($(this).parent().attr("class") == "ev_t_product_cur"){
		$(".ev_t_product_c3_bg").hide();
		$(".ev_t_product_c3").hide();
		$(".ev_t_product_c2").hide();
	  }
	});
  });
  $(".ev_t_product_c3_bg").click(function(){
	$(".ev_t_product_c3_bg").hide();
	$(".ev_t_product_c3").hide();
  });
  //文章详细页，首页
  $('.ev_t_product_pj_t .ev_t_product_more').click(function(){
	  var _this = $(".ev_t_product_pj_t  .ev_t_product_more_div");
	  if(_this.css("display") == "none"){
		_this.show();
	  }else{
		_this.hide();
	  }
  });
  //产品排序
 $(".ev_t_product .ev_t_product_tit_px").click(function(){
	  if($('.ev_t_product_c').css("display") == "none"){
		$(".ev_t_product_c").show();
		$(".ev_t_product_c2").hide();
		$(".ev_t_product_shaixuan").hide();
		$(".ev_t_product .ev_t_product_tit_sx").removeClass("ev_t_product_tit_open");
		$(".ev_t_product .ev_t_product_tit_px").addClass("ev_t_product_tit_open");
	  }else{
		$(".ev_t_product_c").hide();
		$(".ev_t_product .ev_t_product_tit_px").removeClass("ev_t_product_tit_open");
	  }
	});
	$(".ev_t_product .ev_t_product_tit_sx").click(function(){
	  if($('.ev_t_product_shaixuan').css("display") == "none"){
		$(".ev_t_product_shaixuan").show();
		$(".ev_t_product_c").hide();
		$(".ev_t_product_c2").hide();
		$(".ev_t_product_c3").hide();
		$(".ev_t_product_c3_bg").hide();
		$(".ev_t_product .ev_t_product_tit_px").removeClass("ev_t_product_tit_open");
		$(".ev_t_product .ev_t_product_tit_sx").addClass("ev_t_product_tit_open");
	  }else{
		$(".ev_t_product .ev_t_product_shaixuan").hide();
		$(".ev_t_product .ev_t_product_tit_sx").removeClass("ev_t_product_tit_open");
	  }
	});

	$(".ev_t_product_shaixuan").on({click : function(){
		$(".ev_t_product_c2").show();
		$(".ev_t_product_shaixuan").hide();
		$(".ev_t_product_c3").hide();
		$(".ev_t_product_c3_bg").hide();
		var that = $(this),id = that.data("id");
		$(".ev_t_product_c2").find("ul").each(function(){
		  if($(this).data("id")==id){
			$(this).show()
		  }else{
			$(this).hide()
		  }
		})
	  }
	},"li");

	$(".ev_t_product_c2").on({
	  click : function(){
		var index = $(this).index();
		if(index != 0){
		  $(".ev_t_product_c3_bg").show();
		  $(".ev_t_product_c3").show();
		  $(".ev_t_product_c3 ul:eq("+(index-1)+")").show().siblings().hide();
		  $(".ev_t_product_c_i").css("display","block");
		}
	  }
	},"li")

	$(".ev_t_product_c2 .ev_t_product_c_both").each(function(){
	  $(this).click(function(){
		$(this).parent().addClass("ev_t_product_cur").siblings().removeClass("ev_t_product_cur");
		$(".ev_t_product_c3_bg").hide();
		$(".ev_t_product_c3").hide();
		$(".ev_t_product_c2").hide();
		$(".ev_t_product_shaixuan").show();
	  });
	});

	$(".ev_t_product_c2 .ev_t_product_c_div").each(function(){
	  $(this).click(function(){
		var shuzi = $(this).parent().index();
		$(this).parent().addClass("ev_t_product_cur").siblings().removeClass("ev_t_product_cur");
		if($(this).parent().attr("class") == "ev_t_product_cur"){
		  $(".ev_t_product_c3 i").addClass("ev_t_product_c_i");
		  var iheight = 12+(shuzi-1)*$(this).parent().outerHeight();
		  $(".ev_t_product_c_i").css("top",iheight+"px");
		}else{
		  $(".ev_t_product_c3  i").removeClass("ev_t_product_c_i");
		}

	  });
	});

	$(".ev_t_product_c3 .ev_t_product_c_div").each(function(){
	  $(this).click(function(){
		$(this).parent().addClass("ev_t_product_cur").siblings().removeClass("ev_t_product_cur");
		if($(this).parent().attr("class") == "ev_t_product_cur"){
		  $(".ev_t_product_c3_bg").hide();
		  $(".ev_t_product_c3").hide();
		  $(".ev_t_product_c2").hide();
		  $(".ev_t_product_shaixuan").show();
		}
	  });
	});
	$(".ev_t_product_c3 .ev_t_product_c_both").each(function(){
	  $(this).click(function(){
		$(this).parent().addClass("ev_t_product_cur").siblings().removeClass("ev_t_product_cur");
		if($(this).parent().attr("class") == "ev_t_product_cur"){
		  $(".ev_t_product_c3_bg").hide();
		  $(".ev_t_product_c3").hide();
		  $(".ev_t_product_c2").hide();
		  $(".ev_t_product_shaixuan").show();
		}
	  });
	});
	$(".ev_t_product_c3_bg").click(function(){
	  $(".ev_t_product_c3_bg").hide();
	  $(".ev_t_product_c3").hide();
	});
	$(".ev_t_product_qd").click(function(){
	  $(".ev_t_product_shaixuan").hide();
	  $(".ev_t_product_tit_sx").removeClass("ev_t_product_tit_open");
	});
	//收藏
	$(document).on("click", ".ev_t_product_xq_fx .ev_t_product_xq_zan , #detailNavCollect,#detailNavCollectNew,.ev_t_train_xin" ,function(){
		var href       = window.location.href;
		var doc_id     = $(this).attr("data-doc-id");
		var username   = $('#username').val();
		var _this      = $(this);
		if(username){
		  var channel_id = $('#channel_id').val();
		  var type       = $('#channel_type').val();
		  var title      = $(this).attr("data-title");
		}else{
		  var title      = $(this).attr("data-title");
		  var type       = $(this).attr("data-type");
		  var channel_id = $(this).attr("data-channel-id");
		  var username   = $(this).attr("data-username");
		}
		var timestamp  = Date.parse(new Date());
		var url        = 'dom/user_collect_add.php@timestamp='+timestamp;
		var data       = {
						  'title'     :title,
						  'type'      :type,
						  'doc_id'    :doc_id,
						  'channel_id':channel_id,
						  'username'  :username,
						  'wap'       :1
						};
		$.ajax({
			'url' : url,
			type: "POST",
			async: false,
			cache: false,
			data:data,
			success: function(data) {
			  if(data == 1){
				showAllzz("收藏成功！");
				if(_this.attr('class') == 'ev_t_train_xin'){
				  var num  = _this.find('span').html();
				  num = isNaN(parseInt(num)) ? 1 : parseInt(num)+1;
				  _this.find('span').html(num);
				}
				
				if(_this.attr('id')=='detailNavCollectNew'){
					  _this.find('img').attr('src','images/wap/pro_sc_1.png')
				}
				return false;
			  }else if(data == 2){
				var loginUrl    = 'dom/denglu.php@username='+username+'&wap=1';
				var registerUrl = 'dom/zhuce.php@username='+username+'&wap=1';
				showAllzz('收藏失败，您还没有登录，登录后请您重新收藏！',{'登录':loginUrl,'注册':registerUrl,'关闭':'###'});
				return false;
			  }else if(data == 3){
				showAllzz("取消收藏，成功！");
				return false;
			  }else if(data == 4){
				showAllzz("参数错误！");
				return false;
			  }
			}
		  })
		  return false;
	});

});

$(function(){
  //分享
	$(document).on('click',"#productFenxiang , #detailNavShare , #footShareButton",function(){
	   fengxiang();
	});
	//判断头部列表数量
	var Hli_length = $(".alert_header_list li").length,
		li_w = 100/Hli_length;
	if(Hli_length != 6){
	  $(".alert_header_list ul li").width(li_w+'%');
	}
  //头部列表
	$(".ev_t_product_more").click(function(){
	  var header_list = $(".alert_header_list").css("display"),
		header_select = $(".ev_t_top_search_div").css("display");
	  if(header_select == "block"){
		$(".ev_t_top_search_div").hide();
		$(".ev_t_top_search_a").hide();
	  }
	  if(header_list == "none"){
		$(".alert_header_list").show();
	  }else{
		$(".alert_header_list").hide();
	  }
	  //弹出搜索
	  $(".header_search").click(function(){
		$(".alert_header_list").hide();
		$(".ev_t_top_search_div").show();
		$(".ev_t_top_search_a").show();
    $(".alert_zz").show();
    if($(".allzz").length){
      $(".allzz").click();
    }
    })
    //关闭弹窗
    $(".alert_zz").click(function(){
      $(".ev_t_top_search_div").hide();
      $(".ev_t_top_search_a").hide();
      $(".alert_zz").hide();
    })
	});

	//tab切换
	$(".article_nav_tab li").click(function(){
			$(this).siblings().removeClass("cur").children().removeClass("this_bo_color");
			$(this).addClass("cur").children().addClass("this_bo_color");
			$(".ev_t_product_xq_cp .ev_t_product_xq_c > div:eq("+$(this).index()+")").show().siblings().hide();
	});

	//返回顶部
	$("#return_top").hide();
	$(window).scroll(function(){
	  if($(window).scrollTop()>10){
		$("#return_top").show();
	  }else{
		$("#return_top").hide();
	  }
	});
	$("#return_top").click(function(){
	  $("html,body").animate({scrollTop:0},500);
	});

	$(".tab_div ul li").click(function(){
	  $(this).addClass("this_color").siblings().removeClass("this_color");
	  $("#tab_con .term_con").eq($(this).index()).show().siblings().hide();
	});
	$("#footer_server").click(function(){
			var this_ = $(this),s = $("#server_btn_hide");
			var f = function(){
				this_.data('show',0);
				s.hide();
			};
			if(this_.data("show") != 1){
				this_.data("show",1);
				s.show();
				$("body").on("click",f);
			}else{
				f();
				$("body").off('click',f);
			}
			return false;
	});

	/*$(".main").click(function(){
	  $("#server_btn_hide").hide();
	});*/
	$(".ev_t_product_xq_c a").click(function(){
	  if($(this).parent().hasClass("tab_type_two")){
		$(this).addClass("this_color").siblings().removeClass("this_color");
	  }else{
		$(this).addClass("ev_t_cur").siblings().removeClass("ev_t_cur");
	  }
	});
	$(".footer_collect").click(function(){
	  if($(this).find("img").css("margin-top") == '0px'){
		$(this).find("img").css("margin-top","-23px");
	  }else{
		$(this).find("img").css("margin-top","0");
	  }
	});
	// 关闭表单弹窗
	$(".alt_c_close").click(function(){
	  $(".alt_content_table").hide();
	});

});

//点击隐藏窗口
function close_tit(a){
   var isAppAndWxDownload = readCookie(user_name+'_appAndWxDownload');
	if (!isAppAndWxDownload) {
	  writeCookie(user_name+'_appAndWxDownload',1, 2592000);
	}
	$(a).parents(".alt_tit_content").hide();
	$(a).parents().siblings(".alt_tit_bg").hide();
}


function search_website() {
  var keyword = $.trim($('.ev_t_top_search_input #top_keyword').val());
  if (!keyword) {
	showAllzz("关键字不能为空");
	return false;
  }
  $('.ev_t_top_search_a #search_form').submit();
}

/*************************登录验证JS****************************/
var iLoginErrorCount = 0,isSendMsg = 0;

function remove_error_msg() {
  $('.ev_empy').show();
  if($("#error_msg")[0]){
	$("#error_msg").html("");
  }
}

function tab_list(id,name)
{
	  $("."+id).addClass("cur").siblings().removeClass("cur");
	  $("."+id).find("a").addClass("this_font_color").end().siblings().find("a").removeClass("this_font_color");;
	  $("."+name).show().siblings().hide();
	  if(id =='pc_tit'){
		$(".phone_tit img").removeClass().addClass("phone_bg");
		$(".pc_tit img").attr("src","images/wap/login_user2.png");
		$('#loginType').val(0);
	  }else{
		$(".phone_tit img").removeClass().addClass("this_color");
		$(".pc_tit img").attr("src","images/wap/login_user.jpg");
		$('#loginType').val(1);
	  }
}

/***********************发送手机验证码***************************/
function send_mob_code(type,obj){
  var this_ = $('#'+obj);
  if(this_.attr("data-get")=="true"){
	var user_id   = this_.data('userid');
	var mobile    = $.trim($("#mobile").val());
	$('#mobile').trigger('blur');
	if (public.yzMobile(mobile)) {
		if(type && mobile && user_id){
		  this_.attr("data-get","false");
		  this_.html("发送中...");
		  var url = "dom/ajax_zhuce_code.php@type="+type+"&userid="+user_id+"&mobile="+mobile+"&username="+user_name;
		  $.post(url, function(data){
		  if (data == 1) {
		  		showAllzz('此手机号码已绑定，请重新输入');
		  		return false;
		  	}
			if(data == 'success'){
			  change_miao(this_);
			}else{
			  showAllzz("网络繁忙，请稍后重试");
			  this_.html('获取验证码');
			  this_.attr("data-get","true");
			  this_.removeClass("butFalse");
			}
		  });
		}
	}

  }else{
	return false;
  }
}

function change_miao(obj){
  obj.addClass("butFalse");
  var a = 60;
  var time = setInterval(function(){
  	isSendMsg = 1;
	obj.html(a+"秒后可重新获取");
	if(a == 0){
	  clearInterval(time);
	  obj.html('获取验证码');
	  obj.attr("data-get","true");
	  obj.removeClass("butFalse");
	  var new_html='<span>已向您的手机发送了免费的验证码短信。没有收到？您可以：<a onclick="send_mob_code(2,\'send_miao\')" class="hqyzm" id="hqyzm_id" href="javascript:;">重新获取验证码</a></span>';
	  $("#mobileCodeError").html(new_html);
	}
	a--;
  },1000);
}

$(function(){
	$("#loginBut").on('click', function() {
	  var loginType = parseInt($.trim($('#loginType').val()));
	  remove_error_msg();
	  iLoginErrorCount = 0;
	  if (!loginType){
		$('#login_name').trigger('blur');
		$('#login_pwd').trigger('blur');
	  } else {
		$('#mobile').trigger('blur');
		$('#mobile_code').trigger('blur');
	  	if (!isSendMsg && !iLoginErrorCount) {
	  		showAllzz('请获取手机验证码');
	  		return false;
	  	}
	  }

	  if (parseInt(iLoginErrorCount) > 0) {
		return false;
	  } else {
		$("#myfrom").submit();
	  }
	});

	$('#login_name').on('blur',function(){
	  remove_error_msg();
	  var username = $.trim($('#login_name').val());

	  if (username.length == 0) {
		$("#login_name_msg").html('<span class="caution">账号不能为空！</span>');
		iLoginErrorCount++;
		return false;
	  }else if(public.yzUsername(username)==false){
		$("#login_name_msg").html('<span class="error">账号为6-20个字符(字母/数字/下划线)！</span>');
		iLoginErrorCount++;
		return false
	  } else {
		$("#login_name_msg").html('');
	  }
	});

	$('#login_pwd').on('blur',function(){
	  remove_error_msg();
	  var password = $.trim($('#login_pwd').val());

	  if (password.length == 0) {
		$("#login_pwd_msg").html('<span class="caution">密码不能为空！</span>');
		iLoginErrorCount++;
		return false;
	  }else if(public.yzUsername(password)==false){
		$("#login_pwd_msg").html('<span class="error">密码为6-20个字符(字母/数字/下划线)！</span>');
		iLoginErrorCount++;
		return false
	  } else {
		$("#login_pwd_msg").html('');
	  }
	});

	$('#mobile').on('blur',function(){
	  remove_error_msg();
	  var mobile = $.trim($('#mobile').val());
	  var mobileErro = $('#mobileError');
	  if (!mobile) {
		mobileErro.html('<span class="error">手机号不能为空！</span>');
		iLoginErrorCount++;
		return false;
	  } else if(public.yzMobile(mobile)==false){
		mobileErro.html('<span class="error">手机号为11位数字！</span>');
		iLoginErrorCount++;
		return false
	  } else {
		mobileErro.html('');
	  }
	});

	$('#mobile_code').on('blur',function(){
	  remove_error_msg();
	  var mobile_code = $.trim($('#mobile_code').val());
	  var mobileCodeError = $('#mobileCodeError');
	  if (!mobile_code) {
		mobileCodeError.html('<span class="error">验证码不能为空！</span>');
		iLoginErrorCount++;
		return false;
	  }  else {
		mobileCodeError.html('');
	  }
	});
});
function movePicModule_left(id){
	var movePicShow = $("#"+id).find(".movePicShow");
	var scroll_x = movePicShow.scrollLeft();
	var li_w = movePicShow.find("ul li").width();
	if(scroll_x == 0){
		movePicShow.find("ul li:last").insertBefore(movePicShow.find("ul li:first"));
		movePicShow.scrollLeft(li_w);
	}
	movePicShow.animate({scrollLeft:0},function(){
		movePicShow.find("ul li:last").insertBefore(movePicShow.find("ul li:first"));
		movePicShow.scrollLeft(li_w);
	});
}
function movePicModule_right(id){
	var movePicShow = $("#"+id).find(".movePicShow");
	var scroll_x = movePicShow.scrollLeft();
	var li_w = movePicShow.find("ul li").width();
	if(scroll_x == li_w){
		movePicShow.find("ul li:first").insertAfter(movePicShow.find("ul li:last"));
		movePicShow.scrollLeft(0);
	}
	movePicShow.animate({scrollLeft:li_w},function(){
		movePicShow.find("ul li:first").insertAfter(movePicShow.find("ul li:last"));
		movePicShow.scrollLeft(0);
	});
}
