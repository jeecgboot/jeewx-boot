define(function(require) {
    var $ = require("./zepto");
    jQuery(function() {
        if (sessionStorage.getItem("adClosed")) {
        	jQuery(".ad").hide();
        } else {
        	jQuery(".ad").show();
        }
    });

    var Egg = require('./egg');

    var cEgg = new Egg();

});