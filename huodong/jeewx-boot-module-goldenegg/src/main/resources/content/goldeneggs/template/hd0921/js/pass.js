function pass(funs) {
    var d=document.getElementById('canvas');
    if(d!=undefined){
        d.style.top=parseInt(window.innerWidth/10*3)+'px';
        d.style.display='block';
        d.style.position='absolute';
        var _stage = new createjs.Stage(d);
        createjs.Ticker.setFPS(30);
        createjs.Ticker.addEventListener("tick", _stage);
        createjs.Touch.enable(_stage);
        var _this=this;

    loadImage(function () {
        // var _mc=new createjs.Shape()
        // _mc.graphics.beginFill("#ff0000").drawRect(0, 0, 640, 459);
        // _mc.alpha=0.01;
        var _zhongmc;
        // _stage.addChild(_mc);

        var _eggs=[];
        var _point=[312,347];
        var _long=190;
        var _ys=0.08;
        var _jiao=Math.PI/2;
        for(var i=0;i<3;i++){
            _eggs[i]=new Game.eggs();
            _eggs[i].x=320+_long*Math.sin(_jiao+i*Math.PI*2/3);
            _eggs[i].y=347+_long*Math.cos(_jiao+i*Math.PI*2/3)*_ys;
            _stage.addChild(_eggs[i]);
            _eggs[i].mc.addEventListener('click',onClickHandle);
        }
        // _mc.addEventListener('click',onClickHandle);

        function onClickHandle(e) {
            for(var i=0;i<3;i++){
                _eggs[i].mc.removeEventListener('click',onClickHandle);
            }
            _zhongmc=e.currentTarget.parent;
            // var winner=Math.random()*4;
            console.log('click')
            // 对话框
            var TIMEOUT_DIALOG = 500;
            //点击之前的判断逻辑
          /*   jQuery.post(APP.urls.take, {uid: APP.uid}, function (data) {
            	console.log(data);
                data = data || {};
                if(!data.success){
                	switch (data.obj) {
                	case '3': // 天次数用完
                		// egg.removeClass(CLS_ON);
                		setTimeout(function() {
                			dialog.renderUI(Dialog.TYPE.noDayTimes, data.attributes);
                			// 重置参与次数
                			
                		}, TIMEOUT_DIALOG);
                		break;
                	case '4': // 总次数用完
                		setTimeout(function() {
                			dialog.renderUI(Dialog.TYPE.noAllTimes, data.attributes);
                			// 重置参与次数
                		}, TIMEOUT_DIALOG);
                		break;
                	default: // 异常提示
                	setTimeout(function() {
                		dialog.renderUI(Dialog.TYPE.noDayTimes, {title: data.error});
                	}, TIMEOUT_DIALOG);
                	}
                }else{
                	
                }
            }, 'json');*/
             funs();
            // _this.play(true);
        }

        _this.play=function(p){
            if(_zhongmc==undefined) return;
            if(p){
                _zhongmc.gotoAndPlay(1);
                _zhongmc.prize.gotoAndPlay(1);
            }else{
                _zhongmc.gotoAndPlay(1);
            }
        }
        
        _this.reset=function () {
            _zhongmc.gotoAndStop(0);
            _zhongmc.prize.gotoAndStop(0);
            for(var i=0;i<3;i++){
                _eggs[i].mc.addEventListener('click',onClickHandle);
            }
        }

        createjs.Ticker.addEventListener("tick", onplayHandle);
        
        function onplayHandle() {
            _jiao-=0.021;
            _jiao%=Math.PI*2;
            var px=[];
            for(var i=0;i<3;i++) {
                _eggs[i].x = 320 + _long * Math.sin(_jiao + i * Math.PI * 2 / 3);
                _eggs[i].y = 347 + _long * Math.cos(_jiao + i * Math.PI * 2 / 3) * _ys;
                if(i==0){
                    px[0]=0;
                }else if(i==1){
                    if(_eggs[i-1].y<_eggs[i].y){
                        px[1]=0;
                        px[0]=1;
                    }else{
                        px[1]=1;
                    }
                }else if(i==2){
                    if(_eggs[px[1]].y>_eggs[i].y){
                        px[2]=2;
                    }else if(_eggs[px[0]].y>_eggs[i].y){
                        px[2]=px[1];
                        px[1]=2;
                    }else{
                        px[2]=px[1];
                        px[1]=px[0];
                        px[0]=2;
                    }
                }
            }
            for(var i=px.length-1;i>=0;i--){
                _stage.addChild(_eggs[px[i]]);
            }
        }
    });
    }

    function  loadImage(fun) {
        var imgs=Game.properties.manifest;
        var num=0;
        for(var i=0;i<imgs.length;i++){
            var c=new Image();
            c.src=imgs[i].src;
            c.onload=onLoadHandle;
            images_game[imgs[i].id]=c;

        }
        
        function onLoadHandle() {
            num++;
            if(num==imgs.length){
                fun();
            }
        }
    }

    
}