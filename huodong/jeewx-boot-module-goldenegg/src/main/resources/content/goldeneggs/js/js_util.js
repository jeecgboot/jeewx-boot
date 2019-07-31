/**
 *  默认值
 *  $("#key").setDefauleValue();
 * 
 */
$.fn.setDefauleValue = function() {
    var defauleValue = $(this).val();
    $(this).val(defauleValue).css("color","#9f9f9f");
 
    return this.each(function() {      
        $(this).focus(function() {
            if ($(this).val() == defauleValue) {
                $(this).css("color","#555555");//输入值颜色
            }
        }).blur(function() {
            if ($(this).val() == "") {
                $(this).val(defauleValue).css("color","#9f9f9f");//默认值颜色
            }
            if ($(this).val() == defauleValue) {
                $(this).css("color","#9f9f9f");//输入值颜色
            }
        });
    });
}


