// JavaScript Document
function scrollNav(){
	//列表自适应
		var ptListWrp = $('.jQ_ptLst');				//列表容器
		var valLstLiWth = $('.jQ_ptLst li').width();//图片宽度（图片可能包括边框样式等，取Li宽度参与可视图片计算避免误差）
		var valImgLth = 1;								//可见图数变量*
		var valpLstMg = 0;								//边距变量*
		var pLstLesMg = 5;								//临界间距
		//列表滚动
		var pLstRoLt = $('.jQ_plstRoLt');				//左滚动元素
		var pLstRoRt = $('.jQ_plstRoRt');				//右滚动元素
		var pLstImgLth = ptListWrp.find('li').length;	//获取图片总数
	
		//宽度自适应方法
		function fnAutoWth(){
			
			//重置滚动
			$('.jQ_ptLst ul').css({'marginLeft':'0'});
			pLstRoLt.hide();
			pLstRoRt.show();
			var pLstWrpWth = ptListWrp.width();		 			 //获取当前可视区域宽度
			valImgLth = Math.floor(pLstWrpWth / valLstLiWth);	///当前可视图片数计算*
			//图片间距算法
			function fnpLstMg(){
				//间距 = 除去图片的宽度 /（图片数 + 1），并向上取整（避免小数误差）
				//valpLstMg = Math.ceil((pLstWrpWth - valImgLth * valLstLiWth) / (valImgLth + 1));
			};
			//执行计算
			fnpLstMg();
			//间距临界值
			if(valpLstMg < pLstLesMg){
				valImgLth = valImgLth;	//当间距达到临界值qian片数-1
				fnpLstMg();					//重新计算间距
			};
			//可视图片数 >= 总图片数时，总图片数即可视图片数
			if(valImgLth >= pLstImgLth){
				valImgLth = pLstImgLth;
				fnpLstMg();					//重新计算间距
				pLstRoRt.hide();
			};
			//使可视图不为0
	//		if(valImgLth == 0){
	//			valpLstMg = 5;
	//		};
			//设置间距
			ptListWrp.find('li').css('margin-left',valpLstMg);
		};
		//初始执行宽度自适应方法
		fnAutoWth();
		//动态变化宽度时执行方法
		$(window).resize(function(){
			fnAutoWth();	//宽度自适应方法
		});
		//滚动方法
		function fnPlstArr($this){
			
			var pLstRoWrp = ptListWrp.find('ul');
			var ptLstCssMg = parseInt(pLstRoWrp.css('margin-left'));					//获取当前已滚动宽度
			var ptLstImgMg = parseInt(pLstRoWrp.find('li:first').css('margin-left'));	//获取当前图片间距
			//向右滚动
			if($this.hasClass('jQ_plstRoRt')){
				//当点击右箭头时，列表向左滚动。需滚动的宽度 = 当前图片间距 + 图片宽度（取Li宽度）
				pLstRoWrp.not(':animated').animate({marginLeft: ptLstCssMg - (ptLstImgMg + valLstLiWth)},200,function(){
					
					//回调函数判断滚动之后的箭头状态
					var ptLstCurMg = parseInt(pLstRoWrp.css('margin-left'));				//获取当前已滚动宽度
					var ptLstRoWth = (pLstImgLth - valImgLth) * (ptLstImgMg + valLstLiWth);//计算非可视区域宽度
					//当已滚动宽度 = 非可视区宽度，即已滚动至最后一图
					if(ptLstCurMg + ptLstRoWth == 0){
						$this.hide();	//隐藏右箭头
					};
					pLstRoLt.show();	//一旦向右滚动，左箭头即显示
				});
			};
			//向左滚动
			if($this.hasClass('jQ_plstRoLt')){
				pLstRoWrp.not(':animated').animate({marginLeft: ptLstCssMg + (ptLstImgMg + valLstLiWth)},200,function(){
					//回调函数判断滚动之后的箭头状态
					var ptLstCurMg = parseInt(pLstRoWrp.css('margin-left'));					//获取当前已滚动宽度
					var ptLstRoWth = (pLstImgLth - valImgLth - 1) * (ptLstImgMg + valLstLiWth);//计算非可视区域宽度
					//当已滚动宽度 = 0，即已滚动至最前一图
					if(ptLstCurMg == 0){
						$this.hide();	//隐藏左箭头
					};
					pLstRoRt.show();	//一旦向左滚动，右箭头即显示
				});
			};
		};
		//滚动事件绑定
		$('.jQ_plstRoLt, .jQ_plstRoRt').click(function(){
				fnPlstArr($(this));
			})
}

/*控制页面滑动*/
var flag_control=1;
var oScreenWidth;
function control(){
	//var oControlBtn=$(".opt");
			//oScreenWidth=$(window).width()-242
			//$("#contentDIV").css("marginLeft","0").css("width",oScreenWidth);
			//oControlBtn.toggle(function(){
				//alert($(this).parents(".pr").html());
				//$(this).children("i").addClass("arrowRight").end().parents(".pr").children(".subBar").animate({left: -232}, "fast",function(){
				//	$(".subBar").hide()
				//	});/*.css("display","none")*/
				//$(".welcome").animate({left:-232}, "fast",function(){$(".welcome").hide()});
				//$(this).parents(".resizer").animate({left:0}, "fast");
				//$("#contentDIV").animate({width:"98%",marginLeft:10}, "fast");
			//	flag_control=0;
			//},function(){
				//alert(2);
				//oScreenWidth=$(window).width()-242;
			    //alert(oScreenWidth);
				//$(this).children("i").removeClass("arrowRight").end().parents(".pr").children(".subBar").animate({left: 0}, "fast").css("display","block");
				//$(".welcome").animate({left:10}, "fast").css("display","block");
				//$(this).parents(".resizer").animate({left:220}, "fast");
				//$("#contentDIV").animate({width:oScreenWidth,marginLeft:0}, "fast");
			//	flag_control=1;
			//});
	}
$(window).resize(function(){
		if(flag_control==1)
		{
			//oScreenWidth=$(window).width()-242;
			//$("#contentDIV").animate({width:oScreenWidth,marginLeft:0}, "fast");
			}
		else{
			//$("#contentDIV").animate({width:"99%",marginLeft:10}, "fast");
			}
	})
	
function addControl(){
	//oScreenWidth=$(window).width()-242
	//$("#contentDIV").css("marginLeft","0").css("width",oScreenWidth);
	}	
/*菜单展开效果*/
function menuTree(){
	var ospan=$(".subBarList span > a");
	$(ospan).click(function(){
		var indexNum=$(this).index();
		var oclassName=$(this).parents("li").attr("class");
		if(oclassName=="open")
			{
				$(this).parents("li").children("ul").slideUp()
				.parents("li").attr("class","close").end();
				//console.log(oclassName);
				}
		else{
				$(this).parents("li").children("ul").slideDown()
				.parents("li").attr("class","open")
				.siblings(".open").attr("class","close").children("ul").slideUp();
				//console.log(oclassName);
			}		
		})
	}
	
/*表单默认灰色提示*/
;(function($){
    $.fn.extend({
        iclear: function(options){
            var iset = {
                name: null,//获取表单元素比如$(':text')
                curval: null,//默认显示文字,优先于表单默认值,为空时调用表单默认值
                color: '#000',//点击后输入值颜色
                curcolor: '#ccc',//默认颜色
                enter: null
            }
            options=$.extend(iset, options || {});
            iset.name.each(function(){
                //当设置默认值是为表单赋默认值
                if (iset.curval != null) {
                    iset.name.val(iset.curval);
                }
                //表单focus,blur事件
                $(this).css('color', iset.curcolor).focus(function(){
     $(this).css('color',iset.color);
                    if ($(this).val() == (iset.curval ? iset.curval : this.defaultvalue)) {
                        $(this).val('');
                    }
                }).blur(function(){
                    if ($(this).val() == '') {
                        $(this).val(iset.curval ? iset.curval : this.defaultvalue).css('color', iset.curcolor);
                    }
                });
                //绑定回车事件
                if (iset.enter != null) {
                    $(this).keydown(function(e){
                        if (e.keycode == 13) {
                            iset.enter.click();
                        }
                    });
                }
            });
        }
    });
})($);


