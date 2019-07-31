//update-begin--Author:zhangjiaqiang  Date:20160906 for：【插件demo】一对多，明细行间距太大
$(function(){
	$("#jpDemoOrderProduct_add").click(function(){
		var list=$('#jpDemoOrderProduct tbody').find("tr");
    	if(list.size()<6){
			var tr = $("#add_jpDemoOrderProduct_template tr").clone();
			$("#jpDemoOrderProduct_table tbody").append(tr);
			resetTrNum('jpDemoOrderProduct_table');
		}else{
			alert("最多能添加六个奖品");
		}
	});
	
	$("#jpDemoOrderProduct_del").click(function(){
		$("#jpDemoOrderProduct_table").find("input:checked").parent().parent().remove();   
        resetTrNum('jpDemoOrderProduct_table'); 
        return false;
	});
});




//初始化下标
function resetTrNum(tableId) {
	$tbody = $("#"+tableId+" tbody");
	$tbody.find('>tr').each(function(i){
		$(':input, select,button,a', this).each(function(){
			var $this = $(this), name = $this.attr('name'),id=$this.attr('id'),onclick_str=$this.attr('onclick'), val = $this.val();
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
			if(id!=null){
				if (id.indexOf("#index#") >= 0){
					$this.attr("id",id.replace('#index#',i));
				}else{
					var s = id.indexOf("[");
					var e = id.indexOf("]");
					var new_id = id.substring(s+1,e);
					$this.attr("id",id.replace(new_id,i));
				}
			}
			if(onclick_str!=null){
				if (onclick_str.indexOf("#index#") >= 0){
					$this.attr("onclick",onclick_str.replace(/#index#/g,i));
				}else{
				}
			}
		});
		$(this).find('div[name=\'xh\']').html(i+1);
	});
}

//update-end--Author:zhangjiaqiang  Date:20160906 for：【插件demo】一对多，明细行间距太大
