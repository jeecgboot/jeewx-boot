$(function (){ 
			var form = $("#dailogForm").Validform();
			//update----begin----author:scott ----- date:20180906 ----- for：自定义校验样式------------
        	//form.config({tiptype:4});
        	form.config({tiptype:function(msg,o,cssctl){
                if(o.type==3){
                     validationMessage(o.obj,msg);
                }else{
                     removeMessage(o.obj);
                }
              }
            });
        	//update----end----author:scott ----- date:20180906 ----- for：自定义校验样式------------
    		//form.tipmsg.s="非空";
    		//form.tipmsg.r=" ";
        	$("#formSubmit").bind("click",function(){
        		var flag = form.check();
        		if(flag){
        			parent.layer.confirm('确认提交吗？', {
        			    btn: ['确定','取消'] //按钮
//        			    shade: [0.3, '#393D49'] //不显示遮罩
        			}, function(){
        				ajaxdoFormSubmit('dailogForm');
        			}, function(){
        			   
        			});
    			}else{
    				layeralert("信息校验没有通过，请检查填写的信息",0);
				}
        	});
});