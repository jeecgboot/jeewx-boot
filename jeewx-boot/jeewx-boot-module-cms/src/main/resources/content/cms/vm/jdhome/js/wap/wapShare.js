  function fengxiang(){
    var ua = navigator.userAgent.toLowerCase();
    var is_weixn = (ua.match(/MicroMessenger/i)=="micromessenger") ? 1 : 0;
      if(is_weixn){
        fJson.weixin()
    }else{
    var _f = this;
    var dW = $(document).width(),dH = $(document).height(),scT = $(document).scrollTop(),
        wW = $(window).width(),wH = $(window).height();
        var fxZ = $('<div class="allzz"></div>');
        fxZ.css({height:dH}).appendTo("body");
        var fxWrap = $('<div class="fx-wrap"></div>');
        //fxWrap.css({position:'fixed',left:'50%',top:'50%',marginLeft:"-25%",marginTop:"-25%",zIndex:2000});
        var fenA = ["tenxT","tenxZ","sinaT","renren","douban"];
        for(var i = 0;i < fenA.length;i++){
          var span = '<span class="fx-but '+fenA[i]+'" data-id="'+fenA[i]+'"></span>';
          fxWrap.append(span);
        }
        fxWrap.appendTo("body");
        fxWrap.on({
          click : function(){
            var dataId = $(this).data('id');
            fJson[dataId]();
            fxZ.remove();
            fxWrap.remove();
          }
        },'span');
        fxZ.on("click",function(){
          fxZ.remove();
          fxWrap.remove();
        });
    }
  }

 var fJson = {
    'tenxT' : function(){
      var _t = encodeURI(document.title);  var _url = encodeURI(window.location); var _appkey = '801cf76d3cfc44ada52ec13114e84a96'; var _site = encodeURI; var _pic = ''; var _u = '../../../v.t.qq.com/share/share.php@title='+_t+'&url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic; window.open( _u,'转播到腾讯微博', 'width=700, height=580, top=180, left=320, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' );
      },
    'tenxZ' : function(){
      window.open('../../../sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey@url='+encodeURIComponent(document.location.href));
      },
    'sinaT' : function(){
        (function(){window.open('../../../v.t.sina.com.cn/share/share.php@title='+encodeURIComponent(document.title)+'&url='+encodeURIComponent(location.href)+'&source=bookmark','_blank','width=450,height=400');})()
      },
		'renren':function(){
			   (function(){window.open('../../../widget.renren.com/dialog/share@resourceUrl='+encodeURIComponent(location.href)+'&srcUrl='+encodeURIComponent(location.href)+'&title='+encodeURIComponent(document.title)+'&description='+encodeURIComponent(document.title),'_blank','width=450,height=400');})()
		},
    'douban':function(){
       (function(){window.open('../../../www.douban.com/share/service@href='+encodeURIComponent(location.href)+'&name='+encodeURIComponent(document.title)+'&text='+encodeURIComponent(document.title),'_blank','width=450,height=400');})()
    },
    'weixin' : function(){
        var weixin_ts = $('<div class="weixin_ts"><div><img src="../../images/wap/share_weixin.png" /></div></div>');
        weixin_ts.appendTo("body");
        weixin_ts.on("click",function(){
          $(this).remove();
        })
    }
  }



/**微名片中的分享**/
$(function(){
  $("#evTWeiDl").on('click','dt',function(){
      var share = $(this).data("share");
      if(share == 1){
        var ua = navigator.userAgent.toLowerCase(),is_weixn = (ua.match(/MicroMessenger/i)=="micromessenger") ? 1 : 0;
        if(is_weixn){
          fengxiang();
        }else{
          showAllzz("此功能只在微信下可用！");
        }
      } else if (share ==2){
        var imgsrc = $(this).find("img").attr("src"),
          img = $('<img src="'+ imgsrc +'" />');
          img.load(function(){
            showAllzz(img);            
          })
      }
    })
})
/**新版分享的方法**/
function app_share_fn(){
                var title=document.title;
                var url=window.location.href;
                url=url.replace(/\#/g,"");
                var con=$("meta[name='description']").attr("content");
                if(!con)con=title;
                var pic=$("img")[0].src;
                android.share(title,con,pic,url);
              }