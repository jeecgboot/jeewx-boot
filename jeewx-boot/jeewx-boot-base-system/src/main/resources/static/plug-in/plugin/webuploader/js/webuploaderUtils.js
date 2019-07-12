(function($){
	var dft={
		url:null,//上传路径
		swf:"./Uploader.swf",//flash位置，建议不要随便更改
		multiple:false,//是否开启选择多张图片的能力
		queue:true,//是否显示进度条
		duplicate :true,//是否可以重复上传,true可以重复上传，false不可以重复上传
		queueID:"progress"+new Date().getTime(),//进度条ID
		fileSizeLimit:5*1024*1024 //默认验证图片大小为5M
	};
		
	$.fn.uploader=function(options,callback){
		var opts=$.extend(dft,options);
		webUploader($(this)[0], opts, function(data){
			callback(data);
		});
	}
})(jQuery);

function webUploader(pick,opts,callback){
	var uploader = WebUploader.create({
		auto: true,
	    swf: opts.swf,
	    server: opts.url,
	    pick:{
	    	id:pick,
	    	multiple:opts.multiple
	    },
	    duplicate :opts.duplicate,
	    fileSizeLimit:opts.fileSizeLimit,
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/jpg,image/jpeg,image/png'
	    }
	});
	
	uploader.on('uploadSuccess',function(file,data) {
	    callback(data);
	});

	uploader.on('uploadError', function( file ) {
		$(document.getElementById(opts.queueID)).remove();
		layeralert("上传失败");
	});
	
	/**
	* 验证文件格式以及文件大小
	*/
	uploader.on("error", function (type) {
	    if (type == "Q_TYPE_DENIED") {
	        layeralert("请上传JPG、PNG、GIF、BMP格式文件");
	    } else if (type == "Q_EXCEED_SIZE_LIMIT") {
	        layeralert("文件大小不能超过"+(Number(opts.fileSizeLimit)/1024/1024)+"M");
	    }else {
            layeralert("上传出错！请检查后重新上传！错误代码"+type);
	    }
	});
	
	if(opts.queue==true){
		uploader.on( 'uploadProgress', function(file, percentage ){
		    if(!document.getElementById(opts.queueID)){
		    	var h="<div class=\"progress\" id=\""+opts.queueID+"\">"
		    		 +"<div class=\"progress-bar\" role=\"progressbar\"></div>"
		    		 +"</div>"
		    	$("#filePicker").append(h);
		    }
		    $("#"+opts.queueID+" .progress-bar").css('width', percentage * 100 + '%' );
		});

		uploader.on( 'uploadComplete', function( file ) {
		    $(document.getElementById(opts.queueID)).remove();
		});
	}
}
