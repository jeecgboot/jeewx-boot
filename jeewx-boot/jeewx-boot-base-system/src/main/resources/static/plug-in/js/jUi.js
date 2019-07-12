// JavaScript Document
;(function($){
			jQuery.extend({
				accordion:function(id){
					var _h5=$(id).find('h5');
					/*h5点击事件*/
					$(_h5).click(function(){
						$("#accordion").find('h5').each(function(){
							if($(this).hasClass('a_down'))
							{
								//$(this).removeClass("a_down");
								//$(this).siblings('ul').hide();
							}
						});
						/*判断是否有子节点*/
						var _ul=$(this).siblings('ul');
						var len=$(_ul).length;
						//alert(len);
						if(len>0)
						{
							//判断样式是否展开
							if($(this).hasClass('a_down'))
							{
								$(this).removeClass("a_down");
								$(this).siblings('ul').hide();
							}
							else{
								$(this).addClass('a_down');
								$(this).siblings('ul').show();
							}
						}
						else{
							//alert("直接跳转1");
						}
					});
				}	
			});
			$("#accordion").find('h5').siblings('ul').hide();
		})($);
		$.accordion("#accordion");
		$("#accordion h5").hover(function(){
			$(this).css("backgroundColor","#f4f4f4").children('a').css("color","#0095cd");
		},function(){
			$(this).css("backgroundColor","").children('a').css("color","");
		});
		$("#accordion li").hover(function(){
			$(this).css("backgroundColor","#f4f4f4").children('a').css("color","#0095cd");;
		},function(){
			$(this).css("backgroundColor","").children('a').css("color","");
		});
		$("#accordion a").click(function(){
			$("#accordion").find('h5').each(function(){
				if($(this).hasClass('current'))
				{
					$(this).removeClass("current");
				}
			});
			$(this).parent("li").parent("ul").parent("div").find('h5').addClass("current");
			$("#accordion").find("li").each(function(){
				if($(this).hasClass('current'))
				{
					$(this).removeClass("current");
				}
			});
			$(this).parent("li").addClass("current");
		});
		$("#accordion h5").click(function(){
			$("#accordion").find('h5').each(function(){
				if($(this).hasClass('current'))
				{
					$(this).removeClass("current");
				}
			});
			$("#accordion").find("li").each(function(){
				if($(this).hasClass('current'))
				{
					$(this).removeClass("current");
				}
			});
			$(this).addClass("current");
		});