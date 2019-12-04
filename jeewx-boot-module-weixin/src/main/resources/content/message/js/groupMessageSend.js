var basePath=$("#basePath").val();
var checkedPic="text";//群发类型,默认text

$(function(){
	/*//加载不同类型消息图标
	$(".c_tree i").each(function(i, element) {
		var _top=i*-60,_top2=i*-60-30;
		if(i==0){
			$(this).css({"background-position":"0px "+_top2+"px"});
		}else{
			$(this).css({"background-position":"0px "+_top+"px"});
			$(this).hover(function(){
					$(this).css({"background-position":"0px "+_top2+"px"});
				},function(){
		 			$(this).css({"background-position":"0px "+_top+"px"});
			});
		}
	});*/
})

//切换群发对象
$("#isToAll").change(function(){
	var isToAll = $("#isToAll").find("option:selected").val();
	if(isToAll=="false"){
		$(".c_two").css("display","block");
		$("#selectWeixinAccount").css("display","none");
		$(".c_three").css("display","none");
		clearAll();
	}else if(isToAll=="tag"){
		$(".c_three").css("display","block");
		$("#selectWeixinAccount").css("display","none");
		$(".c_two").css("display","none");
		clearAll();
	}else{
	  	$(".c_two").css("display","none");
	  	$("#selectWeixinAccount").css("display","block");
	  	$(".c_three").css("display","none");
	}
});

//点击不同消息图标按钮
$(".c_tree i").click(function(){
	var id=$(this).attr("id");
	checkedPic=id;  //获取群发消息的类型
	var message = "";
	var url =basePath+"/message/back/weixinGroupMessageSendLog/getAllUploadNewsTemplate.do?";
	if(id=="text"){
	    var htmlContent ="<textarea class='wz' placeholder='请输入内容' name='param' id='param' onkeyup='checkWords(this)'></textarea>";
	    $(".c_bj").html(htmlContent);
		$(".wz").focus();
		$("#text").attr("style","background-position:0px -30px;");
		$("#mpnews").attr("style","background-position:0px -240px;");
		$(".ts").attr("style","display:block");
		$("#remainFont").html(600);
	}else if(id=="image"){
		message = "选择图片";
		url+="type=image";
	}else if(id=="video"){
		message = "选择视频";
		url+="type=video";
	}else if(id=="voice"){
		message = "选择语音";
		url+="type=voice";
	}else if(id=="mpnews"){
		var htmlContent = "<div class=\"media_preview_area pp\" style=\"width:320px;\"></div>";
		$(".c_bj").html(htmlContent);
		message = "选择素材";
		url+="type=mpnews";
		$("#text").attr("style","background-position:0px 0px;");
		$("#mpnews").attr("style","background-position:0px -270px;");
		$(".ts").attr("style","display:none");
	}
	//模板Title
	$('#myLargeModalLabel').html(message);
	//跳转获取素材方法
	getAllUploadNewsTemplate(url);
})

//获取素材
function getAllUploadNewsTemplate(url){
	jQuery.ajax({
		url:url,
		type:'post',
		dataType:'json',
		success:function(data){
			var	htmlContent="";
			var i=0;
			for(var key in data.obj){
				i++;
				if(i%2==0){
					htmlContent +="<div class='pright' onclick=checkedNews('"+key+"')><input type='hidden' name='media_id' id='media_id' value='"+key+"'>";
				}else{
					htmlContent +="<div class='pleft' onclick=checkedNews('"+key+"')><input type='hidden' name='media_id' id='media_id' value='"+key+"'>";
				}
				htmlContent +="<div class='media_preview_area pp' style='width:320px;'><div class='appmsg multi editing'><div id='js_appmsg_preview' class='appmsg_content'>";
				htmlContent +="<div id='cover"+key+"' class='cover'><i id='chose"+key+"' style='display:none;position:absolute;top:45%;left:45%;font-size:30px;'>√</i></div>";
				jQuery.each(data.obj[key],function(index,element){
					if(index==0){
						htmlContent += "<div id='appmsgItem1' class='js_appmsg_item has_thumb' data-id='1' data-fileid='200159570'>";
						htmlContent += "<div class='appmsg_info'> <h3>"+element.title+"</h3><em class='appmsg_date'></em></div>";
						htmlContent += "<div class='cover_appmsg_item'><h4 class='appmsg_title'> <a target='_blank' onclick='return false;' href='javascript:void(0);'>"+element.description+"</a> </h4>";
						htmlContent += "<div class='appmsg_thumb_wrp'><img class='js_appmsg_thumb appmsg_thumb' src='"+element.imagePath+"'></div></div></div>";
					}else{
						htmlContent += "<div id='appmsgItem2' class='appmsg_item js_appmsg_item has_thumb' data-id='2' data-fileid='200159577'> <img class='js_appmsg_thumb appmsg_thumb' src='"+element.imagePath+"'>";
						htmlContent += "<h4 class='appmsg_title'> <a target='_blank' href='javascript:void(0);'>"+element.title+"</a></h4></div>";
					}
				})
				htmlContent += "</div></div></div></div>";
			}
			$(".pmain").html(htmlContent);
		},
		error:function(data){
		}
	});
}

//选择图文素材
function checkedNews(key){
	$("#Template").val(key);
	$("[id^=cover]").attr("class","cover");
	$("[id^=chose]").css("display","none");
	$("#chose"+key).css("display","block");
	$("#cover"+key).attr("class","cover pf");
}

//确定
function sure(){
	templateId=$("#Template").val();
	jQuery.ajax({
		url:basePath+"/weixin/back/weixinNewsitem/getNewsTempate.do",
		type:'post',
		data:{templateId:templateId},
		dataType:'json',
		success:function(data){
			var htmlContent = "<div class=\"media_preview_area pp\" style=\"width:320px;\">"+
			  "<div class=\"appmsg multi editing\"><div id=\"js_appmsg_preview\" class=\"appmsg_content\">";
			jQuery.each(data.obj,function(index,element){
				if(index==0){
					htmlContent += "<div id='appmsgItem1' class='js_appmsg_item has_thumb' data-id='1' data-fileid='200159570'>";
					htmlContent += "<div class='appmsg_info'> <h3>"+element.title+"</h3><em class='appmsg_date'></em></div>";
					htmlContent += "<div class='cover_appmsg_item'><h4 class='appmsg_title'> <a target='_blank' onclick='return false;' href='javascript:void(0);'>"+element.description+"</a> </h4>";
					htmlContent += "<div class='appmsg_thumb_wrp'><img class='js_appmsg_thumb appmsg_thumb' src='"+element.imagePath+"'></div></div></div>";
				}else{
					htmlContent += "<div id='appmsgItem2' class='appmsg_item js_appmsg_item has_thumb' data-id='2' data-fileid='200159577'> <img class='js_appmsg_thumb appmsg_thumb' src='"+element.imagePath+"'>";
					htmlContent += "<h4 class='appmsg_title'> <a target='_blank' href='javascript:void(0);'>"+element.title+"</a></h4></div>";
				}
			});
			htmlContent += "</div></div></div>";
			$(".c_bj").html(htmlContent);
			$("#close").click();
			$("#Template").val(templateId);
		},
		error:function(data){}
	});
}

$("#close").click(function(){
	$("#Template").val("");
})

//群发
$("#sendMessage").click(function(){
	var msgType=checkedPic;  
	var isToAll=$("#isToAll option:selected").val();
	var param = $("#param").val();
	var template_id = $("#Template").val();
	var tagId = $("#tagId option:selected").val();
	if(param!=null){
		var len = $("#param").val().length;
		if(len>600){
			layer.alert('您已超过字数限制！');
			return false;
		}
	}
	if(checkedPic==""||checkedPic=="null"){
		msgType="text";
	}
	if(isToAll==""||isToAll=="null"){
		layer.alert('请选择分组');
		return false;
	}
	if(isToAll=="tag"){
		if(tagId==""||tagId=="null"){
			layer.alert('请选择标签');
			return false;
		}
	}
	if(param==""||param=="null"){
		layer.alert('请输入内容');
		return false;
	}
	if(msgType!="text"){
		if(template_id==""||template_id=="null"){
			layer.alert('请选择模板');
			return false;
		}
	}
	layer.confirm('你确定群发该信息吗?',function(index){
		if(index){
			layer.close(index);
			layer.load(1,{shade: [0.5, '#393D49']});
			jQuery.ajax({
				url:basePath+"/message/back/sendGroupMessageController/sendGroupMessage.do",
				type:'post',
				data:{
					msgType:msgType,
					isToAll:isToAll,
					param:param,
					templateId:template_id,
					tagId:tagId
				},
				dataType:'json',
				success:function(data){
					layer.closeAll();
					layer.alert(data.msg,function(){
						location.href="";
					});
				},
				error:function(data){}
			})
		}
	});   
});

//实现文本字数限制功能
function checkWords(t){
	var len = $(t).val().length;
	$("#remainFont").html(600-len);
	if(len>600){
		layeralert("您已超过字数限制！");
	}
}
