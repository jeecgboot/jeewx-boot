$(function(){
	var baseUrl = $("#bcgbaseUrl").val();
	var fileExtensions = "jpg,png,gif";
	if($("#fileExt").length>0){
		fileExtensions = $("#fileExt").val();
	}
	var uploader = new plupload.Uploader({
		runtimes: 'gears,html5,flash,silverlight,html4',
        browse_button: ['choseThisBcg'],
        url: baseUrl+"/cms/back/cmsAd/doUploadFile.do",
        flash_swf_url: baseUrl+"/content/cms/plug-in/plupload/Moxie.swf",
        silverlight_xap_url: baseUrl+"/content/cms/plug-in/plupload/Moxie.xap",
        filters: {
            max_file_size: '10mb',
            mime_types: [
                {title: "common_file", extensions:fileExtensions}
            ],
            prevent_duplicates:false
        },
        multipart_params:{},
		multi_selection: false,
		init: {
        	FilesAdded: function(up, files) {
        		uploader.start();
        	},
        	UploadProgress: function(up, file) {
                var percent = file.percent;
                $("#progressThisBcg").find('.progress-bar').css({"width": percent + "%"}).html(percent + "%");
            },
        	BeforeUpload: function(up, file) {
				$("#progressThisBcg").show();
    		},
    		FileUploaded: function(up, file, info) {
				$("#progressThisBcg").fadeOut("slow");
            	var response = jQuery.parseJSON(info.response);
    	        if (response.success) {
    	        	$("#site_background_img").val(response.obj);
    	        	$("#pic_bcg_div").show();
    	        	$("#pic_bcg_div").children("img").attr("src",baseUrl+"/upload/img/cms/"+response.obj);
    	        }
            },
    		UploadComplete: function(up, files) {
    			
    		},
    		Error: function(up, err) {
    	        if(err.code == plupload.FILE_EXTENSION_ERROR){
    	        	layeralert("文件类型不识别！");
    	        }else if(plupload.FILE_SIZE_ERROR == err.code){
    	        	layeralert("文件大小超标！");
    	        }
    	        console.log(err);
        	}
        }
	});
    $("#delThisBcg").on("click",function(){
    	var tip = $(this).data("tip");
    	delThisBcg(tip);
    });

	uploader.init();
});
function delThisBcg(tip){
	var name =  $("#site_background_img").val();
	var baseUrl = $("#bcgbaseUrl").val();
	jQuery.ajax({
		url:baseUrl+"/cms/back/cmsAd/delFile.do",
		type:"POST",
		dataType:"JSON",
		data:{filepath:name},
		success:function(response){
			if(response.success){
				$("#site_background_img").val('');
				$("#pic_bcg_div").hide();
				if(!tip){
					tip = "图片已删除,请重新上传！";
				}
				layeralert(tip);
			}
		}
	});
}


