var basePath=$("#basePath").val();
$(function(){
	jQuery.ajax({
		url:basePath +"/weixin/back/weixinCommon/checkPermission.do",
		type:"POST",
		dataType:"JSON",
		data:{},
		success:function(data){
			if(data.success){
				//正常
			}else{
				//拦截
				layer.open({
					  type: 1 //Page层类型
					  ,area: ['300px', '150px']
					  ,title: '提示'
					  ,content: '<div style="padding:20px;font-size: 15px;text-align:center"><img width="10" src="'+basePath+'/content/weixin/img/gantan.jpg"> &nbsp;&nbsp;请先授权公众号，关闭当前菜单！</div>'
					,cancel: function() {
					 return false;
					}
				});
			}
		}
	});
});
