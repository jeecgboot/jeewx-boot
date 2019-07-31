window.onload=function(){
	lottery.init('lottery');
};

var lottery={
		index:0,	//当前转动到哪个位置
		count:0,	//总共有多少个位置
		timer:0,	//setTimeout的ID，用clearTimeout清除
		speed:1000,	//初始转动速度
		times:0,	//转动次数
		cycle:50,	//转动基本次数：即至少需要转动多少次再进入抽奖环节
		prize:-1,	//中奖位置
		init:function(id){
			if ($("#"+id).find(".lottery-unit").length>0) {
				slottery = $("#"+id);
				sunits = slottery.find(".lottery-unit");
				this.obj = slottery;
				this.count = sunits.length;
				slottery.find(".lottery-unit-"+this.index).find('span').addClass("active");
			};
		},
		roll:function(){
			var index = this.index;
			var count = this.count;
			var lottery = this.obj;
			$(lottery).find(".lottery-unit-"+index).find('span').removeClass("active");
			index += 1;
			if (index>count-1) {
				index = 0;
			};
			$(lottery).find(".lottery-unit-"+index).find('span').addClass("active");
			this.index=index;
			return false;
		},
		stop:function(index){
			this.prize=index;
			 
			return false;
		}
	};
var click=false;//是否已进入转动抽奖
var flag = 1;
var myindex =7;

function jiugong(){
	lottery.init('lottery');
	 var openid =$("#openid").val();
	 var subscribe =$("#subscribe").val();
	 var actId =$("#actId").val();
	 var jwid =$("#jwid").val();
	if (click) {
		return false;
	}else{		
		var url = "../jiugongge/getAwards.do";
		$.ajax({
			url : url,
			type:"post",
			dataType : "json",
		    data:{
			      	actId:actId,
			      	openid:openid,
			      	subscribe:subscribe,
			      	jwid:jwid
			},
			beforeSend : function(){
				$('#zjl').hide();
				$('#mzj').hide();
			 
			},
			success : function(data){
				if(data.success){
					var awardsName = data.attributes.wxActJiugonggePrize.awardsName;
					var awardsValue = data.attributes.index;
		  			var name=data.attributes.wxActJiugonggePrize.name;
		  			var imgsrc=data.attributes.basePath+'/upload/img/jiugongge/'+jwid+'/'+data.attributes.wxActJiugonggePrize.img;
		  			var recordId=data.attributes.wxActJiugonggeRecord.id;
		  			$('#recordId').val(recordId);
					if(awardsValue == 1) {
						$('#jpimg').attr("src",imgsrc);
						$('#jptype').html(awardsName);
						$('#jpname').text(name);
						flag =1;
						myindex = 0;
					}else if(awardsValue == 2) {
						$('#jpimg').attr("src",imgsrc);
						$('#jptype').html(awardsName);
						$('#jpname').text(name);
						flag =1;
						myindex = 4;
					}else if(awardsValue == 3) {
						$('#jpimg').attr("src",imgsrc);
						$('#jptype').html(awardsName);
						$('#jpname').text(name);
						flag =1;
						myindex = 6;
					}else if(awardsValue == 4) {
						$('#jpimg').attr("src",imgsrc);
						$('#jptype').html(awardsName);
						$('#jpname').html(name);
						flag =1;
						myindex = 2;
					}else if(awardsValue == 5) {
						$('#jpimg').attr("src",imgsrc);
						$('#jptype').html(awardsName);
						$('#jpname').text(name);
						flag =1;
						myindex = 5;
					}else if(awardsValue == 6) {
						$('#jpimg').attr("src",imgsrc);
						$('#jptype').html(awardsName);
						$('#jpname').text(name);
						flag =1;
						myindex = 1;
					}else {					
						flag = 1;
						myindex = 7;
					}

				}else{
					flag = 0;
					myindex = 7;
					if(data.obj=="isNotFoucs"){
						flag = 3;
						popup('no_foucs');
	        		}else if(data.obj=="isNotBind"){
	        			flag = 3;
	        			popup('no_binding_phone');
	        		}else{
	        			$('#mzj').show();
	        			$('#ncz').text(data.msg);
	        			$('#hyh').html("");
	        		}
					
				}
			},
			complete :function() {
				if(flag==1){
					lottery.speed=100;
					roll();
					var use_times = $("#count").html();//每天剩余抽奖次数
					if(use_times==null){
						use_times = $("#num").html();//剩余抽奖次数
						use_times--;
						$("#num").html(use_times);
					}else{						
						use_times--;
						$("#count").html(use_times);
					}					
					click=true;
					return false;
				}else if(flag==0){
					
					$('#mzj').show();
					myindex = 7;
				}
			}
		});

	}
}

function roll(){
	lottery.times += 1;
	lottery.roll();
	if (lottery.times > lottery.cycle+10 && lottery.prize==lottery.index) {
		clearTimeout(lottery.timer);
		lottery.prize=-1;
		lottery.times=0;
		click=false;
		if(lottery.index<7){
			//;
			setTimeout('$("#zjl").show()',1300);
		}else{
			//$("#mzj").show();
			 
			setTimeout('$("#mzj").show()',1300);
		}
		//alert(lottery.index);//最终停靠弹出中奖层!
	}else{
		if (lottery.times<lottery.cycle) {
			lottery.speed -= 10;
		}else if(lottery.times==lottery.cycle) {
			index = myindex;//Math.random()*(lottery.count)|0;
			// alert(index);
			lottery.prize = index;//随机停靠获取中奖数据
		 
		}else{
			if (lottery.times > lottery.cycle+10 && ((lottery.prize==0 && lottery.index==7) || lottery.prize==lottery.index+1)) {
				lottery.speed += 110;
			}else{
				lottery.speed += 20;
			}
		}
		if (lottery.speed<40) {
			lottery.speed=40;
		};
		//console.log(lottery.times+'^^^^^^'+lottery.speed+'^^^^^^^'+lottery.prize);
		lottery.timer = setTimeout(roll,lottery.speed);
	}
	return false;
}


function lq(){
	document.getElementById('zjl').style.display='';
	$('#mcovear').show();
}

function popup(id){
	$("#"+id).show();
}

function popuphide(id){
	$("#"+id).hide();
}

function msgTip(msg){
	$("#tip_msg_content").html(msg);
	popup('tip_msg');
}
//初始化下标
function resetIndex(idv) {
	$box = $("#"+idv+"");
	$box.find("tr").each(function(i){
		$(this).find("td").each(function(){
			var $this = $(this).children(), name = $this.attr('name'), val = $this.val();
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
		});
	});	
}


//手机号数字正则验证
function isMobile(s){
	var patrn= /^1((3\d)|(4[579])|(5[012356789])|(7[01235678])|(8\d))\d{8}$/;
	if (!patrn.exec(s)) return false;
	return true;
}