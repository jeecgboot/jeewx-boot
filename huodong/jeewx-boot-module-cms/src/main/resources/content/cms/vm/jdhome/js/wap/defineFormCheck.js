function auto_check() {
	var checkArr = [];
	for (var i=0; i<param.length; i++) {
		(function(n){
			var _this = param[n];

			if (_this['type'] == 2) {
				$('#field_'+_this['id']).on('change',function(){
					var _thisVal = $('#field_'+_this['id']).val();

					if (_this['required'] == 1 && _thisVal == '') {
						$('#error_'+_this['id']).html('<span class="caution">不能为空！</span>');
						iEditUserinfoErrorCount++;
						if (iEditUserinfoErrorCount == 1) $('#field_'+_this['id']).focus();
						return false;
					} else {
						$('#error_'+_this['id']).html('');
						return ;
					}
				})

			} else if (_this['type'] == 3) {
				checkArr[_this['id']] = $("input[name='field_"+ _this['id'] +"[]']");
				for (var j=0; j<checkArr.length; j++) {
					(function(m){
						$(checkArr[_this['id']][m]).on('click',function(){
							if (_this['required'] == 1 && $("input[name='field_"+ _this['id'] +"[]']:checked").length === 0) {
								$('#error_'+_this['id']).html('<span class="error">请选择一个项目！</span>');
								iEditUserinfoErrorCount++;
							} else {
								$('#error_'+_this['id']).html('');
							}
						})
						return false;
					})(j);
				}

			} else {
				$('#field_'+_this['id']).on('blur',function(){
					var _thisVal = $('#field_'+_this['id']).val();
					if (_this['required'] == 1 && _thisVal == '') {
						$('#error_'+_this['id']).html('<span class="caution">不能为空！</span>');
						iEditUserinfoErrorCount++;
						if (iEditUserinfoErrorCount == 1) $('#field_'+_this['id']).focus();
						return false;
					} else {
						if ($.trim(_this['regex']) && (_this['required'] == 1 || _thisVal)) {
							if (paramRegexp(_this['regex'], _thisVal) === false) {
								$('#error_'+_this['id']).html('<span class="error">'+_this['msg']+'</span>');
								iEditUserinfoErrorCount++;
								if (iEditUserinfoErrorCount == 1) $('#field_'+_this['id']).focus();
								return false;
							} else {
								$('#error_'+_this['id']).html('');
								return ;
							}
						} else {
							$('#error_'+_this['id']).html('');
							return ;
						}
					}

				})
			}
		})(i);
	}
}
auto_check();

function submit_form(){
	iEditUserinfoErrorCount = 0;
	var checkArr = [];
	for (var i=0; i<param.length; i++) {
		(function(n){
			var _this = param[n];
			if (_this['type'] == 2) {
				$('#field_'+_this['id']).trigger('change');

			} else if (_this['type'] == 3) {
				var checkArr = $("input[name='field_"+ _this['id'] +"[]']:checked");
				if (checkArr.length < 1) {
					$('#error_'+_this['id']).html('<span class="caution">不能为空！</span>！');
					iEditUserinfoErrorCount++
					if (iEditUserinfoErrorCount == 1) $("input[name='field_"+ _this['id'] +"[]']").focus();
				}

			} else if (_this['type'] == 4 || _this['type'] == 5) {
				if (_this['type'] == 5) {
					var _thisVal = $("input[name=sex]:checked").length;
				} else {
					var _thisVal = $("input[name='field_"+ _this['id'] +"']:checked").length;
				}

				if (_this['required'] == 1 && _thisVal < 1) {
					$('#error_'+_this['id']).html('<span class="caution">不能为空！</span>！');
					iEditUserinfoErrorCount++;
					if (_this['type'] == 5) {
						if (iEditUserinfoErrorCount == 1) $("input[name=sex]").focus();
					} else {
						if (iEditUserinfoErrorCount == 1) $("input[name='field_"+ _this['id'] +"']").focus();
					}


				} else {
					$('#error_'+_this['id']).html('');
				}
			} else {
					$('#field_'+_this['id']).trigger('blur');
			}
		})(i);
	}

	 if ($("input[id='agreement']:checked").length == 0 && $("input[id='agreement']").length > 0) {
      	$("#agreement_error").html('<span class="caution">请勾选注册协议！</span>');
        if (!sIdTag) { sIdTag = '#agreement_error'; }
     	 iEditUserinfoErrorCount++;
  	} else {
    	$("#agreement_error").html('');
 	 }
	if (parseInt(iEditUserinfoErrorCount) > 0) {
		return false;
	} else {
		$('#add_tag').val(1);
		$('#myform').submit();
	}
}

function paramRegexp(regexpString, string) {
	if ($.trim(regexpString)=='' || $.trim(string)=='') return false;

	if(string.match(eval(regexpString))){
		return true;
	}else{
		return false;
	}
}
