var ajaxInfo = {
	page	  : 2,
	timestamp : Date.parse(new Date())
};

ajaxInfo.editPromptMsg = function() {
	alert("该功能不支持编辑模式下查看！");
}

ajaxInfo.itemList = function (type, username, channelId,uuid) {
	username 	= $.trim(username);
	channelId 	= parseInt(channelId);
	type 		= parseInt(type);
	uuid 		= parseInt(uuid);

	if (!username || !channelId) {
		return false;
	}

	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		//dataType: "json",
		data: {"type":type, "username":username, "channel_id":channelId, "u_u_id":uuid, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}

ajaxInfo.docList = function (type, username, channelId, big_id, sub_id, style,uuid) {
	username 	= $.trim(username);
	channelId 	= parseInt(channelId);
	type 		= parseInt(type);
	big_id 		= parseInt(big_id);
	sub_id 		= parseInt(sub_id);
	uuid 		= parseInt(uuid);

	if (!username || !channelId) return false;

	var data =  {"type":type, "username":username, "channel_id":channelId, "big_id":big_id, "sub_id":sub_id, "style":style, "u_u_id":uuid, "page":ajaxInfo.page};
	if (typeof(proF) != "undefined") {
		if (proF.clickNameVal.filtrate) {
			for (k in proF.clickNameVal.filtrate){
				data[k] = proF.clickNameVal.filtrate[k][0];
			}
		}
	}

	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: data,
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	});
	return false;
}

ajaxInfo.serverList = function (type, username, channelId, big_id,style,uuid) {
	username 	= $.trim(username);
	channelId 	= parseInt(channelId);
	type 		= parseInt(type);
	big_id 		= parseInt(big_id);
	uuid 		= parseInt(uuid);

	if (!username || !channelId) return false;

	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":type, "username":username, "channel_id":channelId, "big_id":big_id,"style":style,"u_u_id":uuid, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}

ajaxInfo.productList = function (type, username, channelId, big_id, sub_id, sort, style, uuid, manu_id) {
	username 	= $.trim(username);
	channelId 	= parseInt(channelId);
	type 		= parseInt(type);
	big_id 		= parseInt(big_id);
	sub_id 		= parseInt(sub_id);
	uuid 		= parseInt(uuid);
	manu_id     = parseInt(manu_id);
	if (!username || !channelId) {
		return false;
	}

	var isUserInfo = (type == 10) ? 1 : 0;
	if (uuid) {
		//商铺
		$.ajax({
			'url' : "wap/ajax.php@isUserInfo="+ isUserInfo +"&u_u_id="+uuid+"&timestamp="+ ajaxInfo.timestamp,
			type: "POST",
			async: false,
			cache: false,
			data: {"type":type, "username":username, "channel_id":channelId, "big_id":big_id, "sub_id":sub_id, "sort":sort, "style":style, "u_u_id":uuid, "page":ajaxInfo.page, "manu_id":manu_id},
			success: function(data) {
				if ($.trim(data) != 'fail') {
					var myObj = eval('('+data+')');
					if (myObj.end == 1) $("#listMore").remove();
					if (myObj.con) {
						$("#contents").append(myObj.con);
						ajaxInfo.page++;
					}
				}
			}
		});
	} else {
		//产品列表
		$.ajax({
			'url' : "wap/ajax.php@isUserInfo="+ isUserInfo +"&timestamp="+ ajaxInfo.timestamp,
			type: "POST",
			async: false,
			cache: false,
			data: {"type":type, "username":username, "channel_id":channelId, "big_id":big_id, "sub_id":sub_id, "sort":sort, "style":style, "page":ajaxInfo.page, "manu_id":manu_id},
			success: function(data) {
				if ($.trim(data) != 'fail') {
					var myObj = eval('('+data+')');
					if (myObj.end == 1) $("#listMore").remove();
					if (myObj.con) {
						$("#contents").append(myObj.con);
						ajaxInfo.page++;
					}
				}
			}
		});
	}
	
	return false;
}

ajaxInfo.picList = function (type, username, channelId) {
	username 	= $.trim(username);
	channelId 	= parseInt(channelId);
	type 		= parseInt(type);

	if (!username || !channelId) return false;

	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":type, "username":username, "channel_id":channelId, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}

//下载功能
ajaxInfo.fileDownList = function (type, username, channelId,big_id,sub_id) {
	username 	= $.trim(username);
	channelId 	= parseInt(channelId);
	type 		= parseInt(type);
	big_id 		= parseInt(big_id);
	sub_id 		= parseInt(sub_id);

	if (!username || !channelId) return false;

	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":type, "username":username, "channel_id":channelId,"big_id":big_id,"sub_id":sub_id,"page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}

ajaxInfo.userMemberList = function (type, username, status) {
	username 	= $.trim(username);
	type 			= parseInt(type);
	status 		= parseInt(status);
	if (!username) return false;

	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":type, "username":username, "status":status, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}

ajaxInfo.userNoticeList = function (type, username) {
	username 	= $.trim(username);
	type 			= parseInt(type);

	if (!username) return false;

	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":type, "username":username, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#messageMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
					if (myObj.type == 1) {
						liList();
					}
				}
			}
		}
	})
	return false;
}

ajaxInfo.guestBookList = function (type, username, channelId) {
	username 		= $.trim(username);
	channelId 	= parseInt(channelId);
	type 				= parseInt(type);

	if (!username || !channelId) return false;

	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":type, "username":username, "channel_id":channelId, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
					conUnBind();
					conBind();
				}
			}
		}
	})
	return false;
}

ajaxInfo.userOrderList = function (status, username) {
	username 	= $.trim(username);
	status		= parseInt(status);
	if (!username) return false;

	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":3, "ostatus":status, "username":username, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#orderMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
					$(".orderSH").off("click");
					$(".orderFail").off("click");
					orderDels();
  					orderSH();
				}
			}
		}
	})
	return false;
}
ajaxInfo.userThirdOrderList = function (status, username) {
	username 	= $.trim(username);
	status		= parseInt(status);
	if (!username) return false;

	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":13, "ostatus":status, "username":username, "wap":1, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#orderMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
					$(".orderSH").off("click");
					$(".orderFail").off("click");
					orderDels();
  					orderSH();
				}
			}
		}
	})
	return false;
}
ajaxInfo.userRechargeList = function (type, username,status) {
	username 	= $.trim(username);
	status		= parseInt(status);
	if (!username) return false;

	$.ajax({
		'url' : "wap/ajax_user_recharge.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":type, "ostatus":status, "username":username, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}
ajaxInfo.userCouponList = function (channel_id, username, this_type,class_id) {
	username = $.trim(username);
	this_type = parseInt(this_type);
	if (!username) return false;
	var temp_data={"type":41, "channel_id":channel_id, "big_id":this_type, "username":username, "page":ajaxInfo.page};
	if(class_id){
		class_id=parseInt(class_id);
		temp_data={"type":41, "channel_id":channel_id, "big_id":this_type, "username":username, "page":ajaxInfo.page,"class_id":class_id};
	}
	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: temp_data,
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) {
					$("#listMore").remove();
				}
				if (myObj.con) {
					$("#contents").append(myObj.con);
					$("body").removeData('loads');
					ajaxInfo.page++;
				}
			} else {
				$("#listMore").remove();
			}

		}
	})
	return false;
}
ajaxInfo.platformCouponList = function (channel_id, username, type_id,class_id) {
	username = $.trim(username);
	if (!username) return false;
	type_id = parseInt(type_id);
	var temp_data={"type":66, "channel_id":channel_id, "big_id":type_id, "username":username, "page":ajaxInfo.page};
	if(class_id){
		temp_data={"type":66, "channel_id":channel_id, "big_id":type_id, "username":username, "page":ajaxInfo.page,"classid":class_id};
	}
	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: temp_data,
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) {
					$("#listMore").remove();
				}
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}
ajaxInfo.userCenterNoticeList = function (type, username, status) {
	username = $.trim(username);
	type     = parseInt(type);
	status   = parseInt(status);

	if (!username || !status) return false;

	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":type, "status":status, "username":username, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#messageMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}


ajaxInfo.ActivityList = function (channel_id, username, big_id,activity_tag) {
	username = $.trim(username);
	if (!username) return false;

	big_id = parseInt(big_id);
	activity_tag=parseInt(activity_tag);
	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":42, "channel_id":channel_id, "big_id":big_id, "username":username, "page":ajaxInfo.page,"activity_tag":activity_tag},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) {
					$("#listMore").remove();
				}
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}

ajaxInfo.userCenterActivityList = function (channel_id, username, status) {
	username = $.trim(username);
	if (!username) return false;

	status = parseInt(status);
	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":8, "channel_id":channel_id, "status":status, "username":username, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) {
					$("#listMore").remove();
				}
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}

ajaxInfo.userReservationList = function (username) {
	username = $.trim(username);
	if (!username) return false;

	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":4, "username":username, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) {
					$("#orderMore").remove();
				}
				if (myObj.con) {
					$("#reservationList").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}
// 我的收藏
ajaxInfo.userWapSearch = function (username, navtype, keyword, dluserid, bbs) {
	username = $.trim(username);
	keyword = $.trim(keyword);
	navtype = parseInt(navtype);
	if (!username || !keyword || !navtype) {
		return false;
	}

	$.ajax({
		'url' : "wap/ajax_set.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":3, "username":username, "navtype":navtype, "keyword":keyword, "page":ajaxInfo.page,'zz_userid':dluserid,'bbs':bbs},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) {
					$("#listMore").remove();
				}
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}
//我的收藏
ajaxInfo.collectList = function(username,collect_channel,dluserid){
	username  = $.trim(username);
	collect_channel = parseInt(collect_channel);
	dluserid = parseInt(dluserid);
	if (!username) { return false; }
	$.ajax({
	'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		//dataType: "json",
		data: {"type":44,"username":username, "collect_channel":collect_channel, "page":ajaxInfo.page,'zz_userid':dluserid},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#my_collect_list").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
}
ajaxInfo.productPl = function (type, username, channelId, id) {
	username  = $.trim(username);
	channelId = parseInt(channelId);
	type      = parseInt(type);
	id        = parseInt(id);

	if (!username || !channelId || !id) { return false; }

	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		//dataType: "json",
		data: {"type":type, "username":username, "channel_id":channelId, "id":id, "pl":1, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}
ajaxInfo.userYygList = function (status, username,channelId) {
	username 	= $.trim(username);
	status		= parseInt(status);
	if (!username) return false;

	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":10, "ostatus":status, "username":username, "channel_id":channelId, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#orderMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}
ajaxInfo.userYygWin = function (status, username) {
	username 	= $.trim(username);
	status		= parseInt(status);
	if (!username) return false;

	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":11, "ostatus":status, "username":username, "page":ajaxInfo.page},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#orderMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}
ajaxInfo.userYygShare = function (status, username,channel_id) {
	username 	= $.trim(username);
	status		= parseInt(status);
	if (!username) return false;

	$.ajax({
		'url' : "wap/ajax_user.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"type":12, "ostatus":status, "username":username, "page":ajaxInfo.page,"channel_id":channel_id},
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#orderMore").remove();
				if (myObj.con) {
					$("#contents").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}
ajaxInfo.huangYeList = function (channel_id, username, big_id,sub_id) {
	username = $.trim(username);
	if (!username) return false;
	big_id = parseInt(big_id);
	sub_id = parseInt(sub_id);
	var temp_data={"type":59, "channel_id":channel_id,"username":username, "page":ajaxInfo.page};
	if(big_id){
		temp_data={"type":59, "channel_id":channel_id, "big_id":big_id, "username":username, "page":ajaxInfo.page};
		if(sub_id){
			temp_data={"type":59, "channel_id":channel_id, "big_id":big_id, "username":username, "page":ajaxInfo.page,"sub_id":sub_id};
		}
	}

	$.ajax({
		'url' : "wap/ajax.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: temp_data,
		success: function(data) {
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) {
					$("#listMore").remove();
				}
				if (myObj.con) {
					$(".list_fanli").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}


ajaxInfo.paycard = function (status, username) {
	username 	= $.trim(username);
	status		= parseInt(status);
	if (!username) return false;
	alert_layer();
	$.ajax({
		'url' : "wap/ajax_pay_card.php@timestamp="+ ajaxInfo.timestamp,
		type: "POST",
		async: false,
		cache: false,
		data: {"username":username, "page":ajaxInfo.page,'type':status},
		success: function(data) {
			del_layer();
			if ($.trim(data) != 'fail') {
				var myObj = eval('('+data+')');
				if (myObj.end == 1) $("#listMore").remove();
				if (myObj.con) {
					$("#content").append(myObj.con);
					ajaxInfo.page++;
				}
			}
		}
	})
	return false;
}
