var iGuestBookErrorCount = 0;

/**
	发布消息操作
*/
$("#guestBook").on("click", function() {
	if ((iGuestBook != 1 && iUserUserId) || iGuestBook == 1) {
		$("#addGuestBook").slideDown(500,function(){
			var win_h = $('body').height();
			var alt_h = $('#addGuestBook').outerHeight();
			if(win_h > alt_h)
				$(this).css({'height':win_h})
			});
	} else {
		showAllzz("请先登录后在发表留言！");
		window.location.href="../dom/denglu.php@username="+user_name+"&wap=1";
		return false;
	}
});

$("#addGuestBook .myMeTit .back").on("click", function() {
	$("#addGuestBook").slideUp(500);
});

$('#title').on('blur',function(){
	var title = $.trim($("#title").val());

	if (title == '') {
		$("#titleError").html('<span class="caution">标题不能为空！</span>');
		iGuestBookErrorCount++;
		return false;
	} else {
		$("#titleError").html('');
	}
});

$('#name').on('blur',function(){
	var name = $.trim($("#name").val());

	if (name == '') {
		$("#nameError").html('<span class="caution">您的姓名不能为空！</span>');
		iGuestBookErrorCount++;
		return false;
	} else if(public.getStringLength(name) < 4) {
		$("#nameError").html('<span class="caution">您的姓名不能小于4个字符！</span>');
		iGuestBookErrorCount++;
		return false;
	} else {
		$("#nameError").html('');
	}
});

$('#tel').on('blur',function(){
	var tel = $.trim($("#tel").val());

	if (tel == '') {
		$("#telError").html('<span class="caution">您的电话不能为空,不显示在留言内容里！</span>');
		iGuestBookErrorCount++;
		return false;
	} else if(public.yzTel(tel)==false) {
		$("#telError").html('<span class="caution">您的电话格式有误！</span>');
		iGuestBookErrorCount++;
		return false;
	} else {
		$("#telError").html('');
	}
});

$('#con').on('blur',function(){
	var con = $.trim($("#con").val());

	if (con == '') {
		$("#conError").html('<span class="caution">内容不能为空！</span>');
		iGuestBookErrorCount++;
		return false;
	} else {
		$("#conError").html('');
	}
});

$('#yzm').on('blur',function(){
	var yzm = $.trim($("#yzm").val());

	if (yzm == '') {
		$("#yzmError").html('<span class="caution">验证码不能为空！</span>');
		iGuestBookErrorCount++;
		return false;
	} else {
		$("#yzmError").html('');
	}
});
if(form_str){
$(document).on("click","#mySubmit, #queryMySubmit", function() {
	iGuestBookErrorCount = 0;

	$('#title').trigger('blur');
	$('#name').trigger('blur');
	$('#tel').trigger('blur');
	$('#con').trigger('blur');
	$('#yzm').trigger('blur');

	if (parseInt(iGuestBookErrorCount) > 0) {
		return false;
	} else {
		$("#add_tag").val(1);
		$("#myform").submit();
	}
})
}
/**
	回复消息操作
*/
function conBind() {
  $(".returnAbut").each(function() {
			$(this).on("click", function() {
				if ((iReGuestBook != 1 && iUserUserId) || iReGuestBook == 1) {
					var id = $(this).attr('rel');
					if (!id) return false;
					$("#re_id").val(id);

					$("#reGuestBook").css({'left':0,'height':$("body").height()});
				} else {
					showAllzz("请先登录后在发表回复！");
					window.location.href="../dom/denglu.php@username="+user_name+"&wap=1";
					return false;
				}
			})
  });

	$(".praiseAbut").each(function() {
			$(this).on("click", function() {
				var _this = $(this);
				var id = _this.attr('rel');
				if (!id) return false;

				if (readCookie('GUESTBOOK_'+user_name+'_'+id)) {
					showAllzz('您已经点击过了！');
					return false;
				}

				$.post('ajax_set.php@username='+user_name, {'type':1, 'id':id}, function(num) {
					if (isNaN(num)) {
						showAllzz('操作失败，请重新点击！');
					} else {
						_this.find("font").html($.trim(num));
						writeCookie('GUESTBOOK_'+user_name+'_'+id, 1, 24*3600)
					}
				})
			})
  });
}

function conUnBind() {
  $(".returnAbut").each(function() {
			$(this).unbind("click");
  });

	$(".praiseAbut").each(function() {
			$(this).unbind("click");
	});
}

$(document).ready(function(){
	conBind();
});

$("#reGuestBook .myMeTit .back").on("click", function() {
	$("#reGuestBook").css({'left':'100%','position':'fixed'});
});

$('#re_con').on('blur',function(){
	var re_con = $.trim($("#re_con").val());

	if (re_con == '') {
		$("#reConError").html('<span class="caution">回复内容不能为空！</span>');
		iGuestBookErrorCount++;
		return false;
	} else {
		$("#reConError").html('');
	}
});

$('#re_yzm').on('blur',function(){
	var re_yzm = $.trim($("#re_yzm").val());

	if (re_yzm == '') {
		$("#reYzmError").html('<span class="caution">回复验证码不能为空！</span>');
		iGuestBookErrorCount++;
		return false;
	} else {
		$("#reYzmError").html('');
	}
});

$(document).on("click",'#myReSubmit, #queryReSubmit', function() {
	iGuestBookErrorCount = 0;

	$('#re_yzm').trigger('blur');
	$('#re_con').trigger('blur');

	if (parseInt(iGuestBookErrorCount) > 0) {
		return false;
	} else {
		$("#re_tag").val(1);
		$("#myReForm").submit();
	}
})

// 刷新验证码
$(document).on("click","#vcodesrc,#reVcodesrc", function() {
	var date=new Date();
	$(this).attr("src","../include/captcha/captcha.php@datete="+date.getTime());
})

