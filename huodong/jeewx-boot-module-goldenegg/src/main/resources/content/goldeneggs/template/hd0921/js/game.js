(function (lib, img, cjs, ss) {

var p; // shortcut to reference prototypes

// library properties:
lib.properties = {
	width: 640,
	height: 459,
	fps: 30,
	color: "#FFFFFF",
	manifest: [
		{src:"../../content/goldeneggs/template/hd0921/img/da.png", id:"da"},
		{src:"../../content/goldeneggs/template/hd0921/img/egg.png", id:"egg"},
		{src:"../../content/goldeneggs/template/hd0921/img/egg2.png", id:"egg2"},
		{src:"../../content/goldeneggs/template/hd0921/img/egg2_0.png", id:"egg2_0"},
		{src:"../../content/goldeneggs/template/hd0921/img/egg2_bg.png", id:"egg2_bg"},
		{src:"../../content/goldeneggs/template/hd0921/img/egg3.png", id:"egg3"}
	]
};



// symbols:



(lib.da = function() {
	this.initialize(img.da);
}).prototype = p = new cjs.Bitmap();
p.nominalBounds = new cjs.Rectangle(0,0,140,112);


(lib.egg = function() {
	this.initialize(img.egg);
}).prototype = p = new cjs.Bitmap();
p.nominalBounds = new cjs.Rectangle(0,0,194,195);


(lib.egg2 = function() {
	this.initialize(img.egg2);
}).prototype = p = new cjs.Bitmap();
p.nominalBounds = new cjs.Rectangle(0,0,233,181);


(lib.egg2_0 = function() {
	this.initialize(img.egg2_0);
}).prototype = p = new cjs.Bitmap();
p.nominalBounds = new cjs.Rectangle(0,0,91,85);


(lib.egg2_bg = function() {
	this.initialize(img.egg2_bg);
}).prototype = p = new cjs.Bitmap();
p.nominalBounds = new cjs.Rectangle(0,0,101,27);


(lib.egg3 = function() {
	this.initialize(img.egg3);
}).prototype = p = new cjs.Bitmap();
p.nominalBounds = new cjs.Rectangle(0,0,490,268);


(lib.xxx_xx = function() {
	this.initialize();

	// 图层 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#669900").s().p("AvJPPIAA+dIeTAAIAAedg");
	this.shape.setTransform(97,97.5);

	this.addChild(this.shape);
}).prototype = p = new cjs.Container();
p.nominalBounds = new cjs.Rectangle(0,0,194,195);


(lib.xxx_3 = function() {
	this.initialize();

	// 图层 1
	this.instance = new lib.egg2_0();

	this.addChild(this.instance);
}).prototype = p = new cjs.Container();
p.nominalBounds = new cjs.Rectangle(0,0,91,85);


(lib.xxx_1 = function() {
	this.initialize();

	// 图层 1
	this.instance = new lib.da();

	this.addChild(this.instance);
}).prototype = p = new cjs.Container();
p.nominalBounds = new cjs.Rectangle(0,0,140,112);


(lib.xxx_0 = function() {
	this.initialize();

	// 图层 1
	this.instance = new lib.egg3();

	this.addChild(this.instance);
}).prototype = p = new cjs.Container();
p.nominalBounds = new cjs.Rectangle(0,0,490,268);


(lib.xxx_2 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// timeline functions:
	this.frame_0 = function() {
		this.stop();
	}
	this.frame_37 = function() {
		this.stop();
	}

	// actions tween:
	this.timeline.addTween(cjs.Tween.get(this).call(this.frame_0).wait(37).call(this.frame_37).wait(1));

	// 图层 1
	this.instance = new lib.xxx_3();
	this.instance.setTransform(45.5,42.5,1,1,0,0,0,45.5,42.5);
	this.instance._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance).wait(23).to({_off:false},0).to({y:-4.8},14,cjs.Ease.get(0.6)).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = null;


(lib.eggs = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// timeline functions:
	this.frame_0 = function() {
		this.stop();
	}
	this.frame_53 = function() {
		this.stop();
	}

	// actions tween:
	this.timeline.addTween(cjs.Tween.get(this).call(this.frame_0).wait(53).call(this.frame_53).wait(1));

	// 图层 9
	this.mc = new lib.xxx_xx();
	this.mc.setTransform(2.2,-11.5,1,1,0,0,0,97,97.5);
	this.mc.alpha = 0.012;

	this.timeline.addTween(cjs.Tween.get(this.mc).wait(54));

	// 图层 7
	this.instance = new lib.xxx_1();
	this.instance.setTransform(143.9,-75.1,1,1,0,0,0,127.8,100.8);
	this.instance._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance).wait(1).to({_off:false},0).to({regX:127.9,rotation:15,x:144},11,cjs.Ease.get(0.5)).to({regY:100.7,rotation:-15,y:-75.2},10,cjs.Ease.get(0.8)).to({alpha:0},11).to({_off:true},1).wait(20));

	// 图层 6
	this.instance_1 = new lib.xxx_0();
	this.instance_1.setTransform(-5.6,-83.9,0.125,0.125,0,0,0,245.1,134);
	this.instance_1.alpha = 0.012;
	this.instance_1._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance_1).wait(23).to({_off:false},0).to({regX:245,scaleX:1,scaleY:1,y:-98.2,alpha:1},29,cjs.Ease.get(0.8)).wait(2));

	// 图层 4
	this.instance_2 = new lib.egg2();
	this.instance_2.setTransform(-115.4,-109);
	this.instance_2._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance_2).wait(23).to({_off:false},0).wait(31));

	// 图层 5
	this.prize = new lib.xxx_2();
	this.prize.setTransform(2.2,-15.2,1,1,0,0,0,45.5,42.5);

	this.timeline.addTween(cjs.Tween.get(this.prize).wait(54));

	// 图层 1
	this.instance_3 = new lib.egg();
	this.instance_3.setTransform(-94.8,-109);

	this.timeline.addTween(cjs.Tween.get(this.instance_3).to({_off:true},23).wait(31));

	// 图层 8
	this.instance_4 = new lib.egg2_bg();
	this.instance_4.setTransform(-50.5,-65.5);
	this.instance_4._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance_4).wait(23).to({_off:false},0).wait(31));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-94.8,-109,194,195);


// stage content:



(lib.game = function() {
	this.initialize();

	// 图层 2
	this.instance = new lib.eggs();
	this.instance.setTransform(507.9,-159.1,1,1,0,0,0,2.2,-11.5);

	this.addChild(this.instance);
}).prototype = p = new cjs.Container();
p.nominalBounds = new cjs.Rectangle(730.9,-27.1,194,195);

})(Game = Game||{}, images_game = images_game||{}, createjs = createjs||{}, ss = ss||{});
var Game, images_game, createjs, ss;