//提示信息
  function validationMessage(obj, Validatemsg) {
      try {
          removeMessage(obj);
          obj.focus();
          
          //-- update---begin--author：scott---date:20180925-----for:抽奖活动行编辑奖品和奖项配置总提示为空选不上问题-----
          //console.log('-----validationMessage----------');
          //console.log(obj);
          //console.log("val: "+ obj.val());
          var idkey = obj.context.name.replace("Name","Id");
          console.log("name: "+ idkey);
          if(idkey.indexOf("].awardId")!=-1 || idkey.indexOf("].awardsId")!=-1 || idkey.indexOf("].prizeId")!=-1 || ( idkey.indexOf("].name")!=-1 && idkey.indexOf("awarsList[")!=-1) ){
        	  return;
          }
          //var ipvalue = $("input[name='"+idkey+"']").val();
          //console.log("ipvalue: "+ ipvalue);
          
          //-- update---end--author：scott---date:20180925-----for:抽奖活动行编辑奖品和奖项配置总提示为空选不上问题-----
          
          var $poptip_error = $('<div class="poptip"><span class="poptip-arrow poptip-arrow-top"><em>◆</em></span>' + Validatemsg + '</div>').css("left", '10px').css("top",'40px')
          obj.parent().append($poptip_error);
          if (obj.hasClass('form-control') || obj.hasClass('ui-select')) {
              obj.parent().addClass('has-error');
          }
          if (obj.hasClass('ui-select')) {
              $('.input-error').remove();
          }
          obj.change(function () {
              if (obj.val()) {
                  removeMessage(obj);
              }
          });
          if (obj.hasClass('ui-select')) {
              $(document).click(function (e) {
                  if (obj.attr('data-value')) {
                      removeMessage(obj);
                  }
                  e.stopPropagation();
              });
          }
          return false; 
      } catch (e) {
          alert(e)
      }
  }
  //移除提示
  function removeMessage(obj) {
      obj.parent().removeClass('has-error');
      obj.parent().children('div .poptip').remove();
  }
