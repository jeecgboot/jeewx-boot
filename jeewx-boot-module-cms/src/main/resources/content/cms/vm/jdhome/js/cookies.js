function readCookie(name)
{
  var cookieValue = "";
  var search_s = name + "=";
  if(document.cookie.length > 0)
  {
    offset = document.cookie.indexOf(search_s);
    if (offset != -1)
    {
      offset += search_s.length;
      end = document.cookie.indexOf(";", offset);
      if (end == -1) end = document.cookie.length;
      cookieValue = unescape(document.cookie.substring(offset, end))
    }
  }
  if(cookieValue=='null'){
    cookieValue='';
  }
  return cookieValue;
}
function writeCookie(name, value, hours)
{
  var expire = "";
  if(hours != null)
  {
    expire = new Date((new Date()).getTime() + hours * 3600);
    expire = "; expires=" + expire.toGMTString();
  }
  if(value==null){
    value='';
  }
  document.cookie = name + "=" + escape(value) + expire+ ";path=/;";
}
//退出
function clear_cookies(name,url){
    writeCookie(name,'');
	if(name=='zz_userid'){
		if(url){
			var tmp_arr=url.split("vip_");
			
			if(tmp_arr[1]){
				var tmp_arr2=tmp_arr[1].split(".");
				if(tmp_arr2[0]){
					var tmp_cok=tmp_arr2[0]+'_gouwuche';
					writeCookie(tmp_cok,'');
				}	
			}
		}	
	}
    if(url!=''){
        location.href=url;
        //$("#fixedShopcar").hide();
    }else{
        location.reload();
    }
}

/*function clear_cookies(){
    writeCookie('ev_userid','');
    document.getElementById("login_info").innerHTML = '<div style="padding:5px 0 0 ;line-height:14px"><a href="../st.ev123.com/default.htm">注册</a> | <a href="javascript:set_user_login();">登录</a></div>';
}*/

function HCmarquee(id,mw,mh,mr,sr,ms,pause){

    var obj=document.getElementById(id);
    obj.ss=false; //stop tag
    obj.mr=mr; //marquee rows
    obj.sr=sr; //marquee display rows
    obj.mw=mw; //marquee width
    obj.mh=mh; //marquee height
    obj.ms=ms; //marquee speed
    obj.pause=pause; //pause time
    obj.pt=0; //pre top
    obj.st=0; //stop time
    obj.mul=1;
    obj.con="";
    with(obj){
        style.width=mw+"px";
        style.height=mh+"px";
        noWrap=false;
        onmouseover=stopm;
        onmouseout=startm;
        scrollTop=0+"px";
        scrollLeft=0+"px";
    }

    if(obj.mr!=1){
                obj.tt=mh*mr/sr;
                obj.ct=mh; //current top
                obj.innerHTML+=obj.innerHTML;
                setInterval(scrollUp,obj.ms);
    }
    function scrollUp(){
        if(obj.ss==true) return;
        obj.ct+=1;
        if(obj.ct==obj.mh+1){
            obj.st+=1; obj.ct-=1;
            if(obj.st==(obj.pause*obj.mul)){
                obj.ct=0; obj.st=0;
                if(obj.mul==1) obj.mul = 1;
                else obj.mul = 1;
            }
        }else {
            obj.pt=(++obj.scrollTop);
            if(obj.pt==obj.tt){obj.scrollTop=0;}
        }
    }

    function stopm(){obj.ss=true;}
    function startm(){obj.ss=false;}

}
