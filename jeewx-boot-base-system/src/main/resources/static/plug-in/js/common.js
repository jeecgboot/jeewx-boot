//icon 0 叹号 1对号 2叉号3问号4章5哭脸6笑脸
function layeralert(msg,icon){
	parent.layer.alert(msg, {
        icon: icon,
        shadeClose: false,
        title: '提示'
    });
}

function remoteUrl(url,title){ 
	if(url.indexOf('?')>-1){
 		url += '&t=' + Math.random(1000);  
 	}else{
 		url += '?t=' + Math.random(1000);  
 	}
 	$('#addOrUpdateModalLabel').html(title);
	$.ajax({
      url: url,
      cache: false,
	  dataType: "html",
      success: function(data){
        $('#addOrUpdateModal .modal-body').html(data); 
      },  
      error: function(data, status, e){  
      	if(data.status == "401"){
      			layeralert("没有权限！",0);
				return;
			}
      }
    });
	$('#addOrUpdateModal').modal({show:true,backdrop:false}); 
} 

function doUrl(url){ 
	location.href=url;
} 

function dialogDiv(modalId,title){ 
 	$('#'+modalId+'Label').html(title);
	$('#'+modalId).modal({show:true,backdrop:false}); 
}

function detail(url,title){ 
	if(url.indexOf('?')>-1){
 		url += '&t=' + Math.random(1000);  
 	}else{
 		url += '?t=' + Math.random(1000);  
 	}
	$('#detailModalLabel').html(title);
	$.ajax({
      url: url,
      cache: false,
	  dataType: "html",
      success: function(data){
        $('#detailModal .modal-body').html(data); 
      },  
      error: function(data, status, e){  
      	if(data.status == "401"){
      			layeralert("没有权限！",0);
				return;
			}
      }
    });
	$('#detailModal').modal({show:true,backdrop:false}); 
} 

function showPrivilegeTree(url,title){ 
	if(url.indexOf('?')>-1){
 		url += '&t=' + Math.random(1000);  
 	}else{
 		url += '?t=' + Math.random(1000);  
 	}
 	$('#privilegeTreeModalLabel').html(title);
	$.ajax({
      url: url,
      cache: false,
	  dataType: "html",
      success: function(data){
        $('#privilegeTreeModal .modal-body').html(data); 
      },  
      error: function(data, status, e){  
      	if(data.status == "401"){
      			layeralert("没有权限！",0);
				return;
			}
      }
    });
	$('#privilegeTreeModal').modal({show:true,backdrop:false});
} 


/**
 * ajax提交FORM
 * 
 * @param url
 * @param gridname
 */
function ajaxdoForm(formId) {
	var url = $('#' + formId).attr("action");
	$('#'+formId).ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        success: function(data, status){ 
        	if(data.success){
        		parent.layer.alert(data.msg, {
        	        icon: 1,
        	        shadeClose: false,
        	        title: '提示'
        	    },function(index){
        	    	document.getElementById('formSubmit').submit();
        	    	parent.layer.close(index);
        	    });
        	}else{
        		layeralert(data.msg,0);
        	}
        },  
        error: function(data, status, e){  
        	if(data.status == "401"){
        		layeralert("没有权限！",0);
				return;
			}
        }
	}); 
}

/**
 * ajax提交FORM
 * 
 * @param url
 * @param gridname
 */
function ajaxdoFormSubmit(formId) {
	var url = $('#' + formId).attr("action");
	$('#'+formId).ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        success: function(data, status){
        	if(data.status == "401"){
        		layeralert("没有权限！",0);
				return;
			}
        	if(data.success){
//        		layeralert(data.msg,1);
        		parent.layer.alert(data.msg, {
        	        icon: 1,
        	        shadeClose: false,
        	        title: '提示'
        	    },function(index){
        	    	$('#formReturn').click();
        	    	parent.layer.close(index);
        	    });
        		//location.reload();
        	}else{
        		layeralert(data.msg,0);
        	}
        },  
        error: function(data, status, e){  
        	if(data.status == "401"){
        		layeralert("没有权限！",0);
				return;
			}
        }
	}); 
}

function delData(url){
	parent.layer.confirm('确认删除吗？', {
	    btn: ['确定','取消'] //按钮
//	    shade: [0.3, '#393D49'] //不显示遮罩
	}, function(){
		$.ajax({
	      url: url,
	      cache: false,
		  dataType: "json",
	      success: function(data){
	    	  	if(data.success){
//	    	  		alert(data.msg);
	    	  		parent.layer.alert(data.msg, {
	        	        icon: 1,
	        	        shadeClose: false,
	        	        title: '提示'
	        	    },function(index){
	        	    	document.getElementById('formSubmit').submit();
	        	    	parent.layer.close(index);
	        	    });
	        	}else{
	        		layeralert(data.msg,0);
	        	}
	      },  
	        error: function(data, status, e){  
	        	if(data.status == "401"){
	        		layeralert("没有权限！",0);
					return;
				}
	        }
	    });
	}, function(){
		   
	});
	
}

function delDataGrid(url){
	parent.layer.confirm('确认提交吗？', {
	    btn: ['确定','取消'] //按钮
//	    shade: [0.3, '#393D49'] //不显示遮罩
	}, function(){
		$.ajax({
	      url: url,
	      cache: false,
		  dataType: "json",
	      success: function(data){
	    	  	if(data.success){
//	    	  		alert(data.msg);
	    	  		parent.layer.alert(data.msg, {
	        	        icon: 1,
	        	        shadeClose: false,
	        	        title: '提示'
	        	    },function(index){
	        	    	dateGrid.reload();
	        	    	parent.layer.close(index);
	        	    });
	        	}else{
	        		layeralert(data.msg,0);
	        	}
	      },  
	        error: function(data, status, e){  
	        	if(data.status == "401"){
	        		layeralert("没有权限！",0);
					return;
				}
	        }
	    });
	}, function(){
		   
	});
	
}

function blockUser(id){
	var url = '../rcOperateUser/blockUser.do?id='+id;
	parent.layer.confirm('确认停用该用户吗？', {
	    btn: ['确定','取消']//按钮
//	    shade: false //不显示遮罩
	}, function(){
		$.ajax({
	      url: url,
	      cache: false,
		  dataType: "json",
	      success: function(data){
	    	  	if(data.success){
//	    	  		alert(data.msg);
	    	  		parent.layer.alert(data.msg, {
	        	        icon: 1,
	        	        shadeClose: false,
	        	        title: '提示'
	        	    },function(index){
	        	    	document.getElementById('formSubmit').submit();
	        	    	parent.layer.close(index);
	        	    });
	        	}else{
	        		layeralert(data.msg,0);
	        	}
	      },  
	        error: function(data, status, e){  
	        	if(data.status == "401"){
	        		layeralert("没有权限！",0);
					return;
				}
	        }
	    });
	}, function(){
	    
	});
	
}

function unlockUser(id){
	var url = '../rcOperateUser/unlockUser.do?id='+id;
	parent.layer.confirm('确认启用该用户吗？', {
	    btn: ['确定','取消'] //按钮
//	    shade: [0.3, '#393D49'] //不显示遮罩
	}, function(){
		$.ajax({
	      url: url,
	      cache: false,
		  dataType: "json",
	      success: function(data){
	    	  	if(data.success){
//	    	  		alert(data.msg);
	    	  		parent.layer.alert(data.msg, {
	        	        icon: 1,
	        	        shadeClose: false,
	        	        title: '提示'
	        	    },function(index){
	        	    	document.getElementById('formSubmit').submit();
	        	    	parent.layer.close(index);
	        	    });
	        	}else{
	        		layeralert(data.msg,0);
	        	}
	      },  
	        error: function(data, status, e){  
	        	if(data.status == "401"){
	        		layeralert("没有权限！",0);
					return;
				}
	        }
	    });
	}, function(){
		   
	});
	
	
}


function redirectUrl(url){
	$.ajax({
		      url: url,
		      cache: false,
			  dataType: "json",
		      success: function(data){
		    	  	if(data.success){
//		    	  		alert(data.msg);
		    	  		parent.layer.alert(data.msg, {
		        	        icon: 1,
		        	        shadeClose: false,
		        	        title: '提示'
		        	    },function(index){
		        	    	document.getElementById('formSubmit').submit();
		        	    	parent.layer.close(index);
		        	    });
		        	}else{
		        		layeralert(data.msg,0);
		        	}
		      }
		    });

	}

	
//jquery将表单序列化json对象 
$.fn.serializeObject = function () {
    var obj = {};
    var count = 0;
    $.each(this.serializeArray(), function (i, o) {
        var n = o.name, v = o.value;
        count++;
        obj[n] = obj[n] === undefined ? v: $.isArray(obj[n]) ? obj[n].concat(v) : [obj[n], v];
    });
    //obj.nameCounts = count + "";//表单name个数
    //return JSON.stringify(obj);
    return obj;
};


//初始化下标
function resetIndex(idv) {
	$box = $("#"+idv+"");
	$box.find("div[for$='divBox']").each(function(i){
		$(':input, select', this).each(function(){
			var $this = $(this), name = $this.attr('name'), val = $this.val();
			if(name!=null){
				if (name.indexOf("#index#") >= 0){
					$this.attr("name",name.replace('#index#',i));
				}else{
					var s = name.indexOf("[");
					var e = name.indexOf("]");
					var new_name = name.substring(s+1,e);
					$this.attr("name",name.replace(new_name,i));
				}
			}
			$this.parent().parent().find("label[for$='trtitle']").each(function(){
				var $this = $(this);
				var title = $this.html();
				if (title.indexOf("#index#") >= 0){
					$this.html(title.replace('#index#',(i+1)));
				}else{
					var s = title.indexOf("[");
					var e = title.indexOf("]");
					var new_title = title.substring(s+1,e);
					$this.html(title.replace(new_title,(i+1)));
				}
			});
		});
	});
	
	$box.find("div[for$='divBox']").each(function(i){
		$('div>select', this).each(function(){
			var $this = $(this),id = $this.attr('id'),val = $this.val();
			if(id!=null){
				if (id.indexOf("#index#") >= 0){
					$this.attr("id",id.replace('#index#',i));
				}
			}
			$this.parent().parent().find("label[for$='trtitle']").each(function(){
				var $this = $(this);
				var title = $this.html();
				if (title.indexOf("#index#") >= 0){
					$this.html(title.replace('#index#',(i+1)));
				}else{
					var s = title.indexOf("[");
					var e = title.indexOf("]");
					var new_title = title.substring(s+1,e);
					$this.html(title.replace(new_title,(i+1)));
				}
			});
			
			if(id.indexOf("tppCode") >=0){
				$this.bind("change",function(){
	    			changeTpp(i);
	    		});
			}
			if(id.indexOf("pmCode") >=0){
				$this.bind("change",function(){
	    			changePm(i);
	    		});
			}
		});
	});
}


/**
 * 格式化日期时间
 * @param format
 * @returns format
 */
Date.prototype.format = function(format) {
	if (isNaN(this.getMonth())) {
		return '';
	}
	if (!format) {
		format = "yyyy-MM-dd hh:mm:ss";
	}
	var o = {
		/* month */
		"M+" : this.getMonth() + 1,
		/* day */
		"d+" : this.getDate(),
		/* hour */
		"h+" : this.getHours(),
		/* minute */
		"m+" : this.getMinutes(),
		/* second */
		"s+" : this.getSeconds(),
		/* quarter */
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		/* millisecond */
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

$.FormatDateTime = function (obj,farmat) { 
	if(obj==null) return "";
    var d = new Date(obj);   
    return d.format(farmat);
}; 


function amountIsNotZero(obj){
	var amount = $(obj).val();
	if(parseFloat(amount)==0){
		$(obj).val("");
		layeralert("金额不能为0",0);
		$(obj).focus();
		return false;
	}
	return true;
}

function isAmount(obj){
	var amount = $(obj).val();
	if(amount==""){
		return false;
	}
	var s_currency = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if(parseFloat(amount)==0){
		$(obj).val("");
		layeralert("金额不能为0",0);
		$(obj).focus();
		return false;
	}
	if(!s_currency.test(amount)){
		$(obj).val("");
		layeralert("请输入正确的数字。",0);
	   $(obj).focus();
	   return false; 
	}
	return true;
}

$.escapeHTML = function (obj) { 
	if(obj==null) return "";
	var div = document.createElement('div');    
	var text = document.createTextNode(obj);   
	div.appendChild(text);   
	return  div.innerHTML; 
//    return  obj.replace(/&/g, '&').replace(/>/g, '>').replace(/</g, '<').replace(/”/g, '"');
};