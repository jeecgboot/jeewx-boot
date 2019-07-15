	var zTree = $.fn.zTree.getZTreeObj("au_chage");
    //初始化权限
	function showAuth(url,userId) {
        if(userId!=null && userId!=""){
        	 url=url+"?userId="+userId;
        	if(url.indexOf('?')>-1){
         		url += '&t=' + Math.random(1000);  
         	}else{
         		url += '?t=' + Math.random(1000);  
         	}
            jQuery.ajax({
                type: "POST",
                cache: false,
                url:url ,
                success: function(zNodes){
                	jQuery.fn.zTree.init($("#au_chage"), setting, eval(zNodes));
                	//Disabled1();
                }
            });
        } else {
            alert("请选择一个公众号!");
        }
    }
	
	//保存权限设置
    function saveAuth(url) {
    	var curUserId = $("#curUserId").val();
        var zTree = $.fn.zTree.getZTreeObj("au_chage");
        var checkCount = zTree.getCheckedNodes(true);
        var checkedNodes = [];
    	for(var i=0;i<checkCount.length;i++){
    		checkedNodes.push(checkCount[i].id);
    	}
        jQuery.ajax({
        	url:url ,
            data : {
            	checkedNodes : checkedNodes.join(','),
            	userId:curUserId
			},
			dataType: "json",
            success: function(data){
            	if(data.success){
            		alert(data.msg);
            		$('#dialogFormClose').click();
            	}else{
            		alert(data.msg);
            	}
            }
        });
    }
	
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
	
    var setting2 = {
        data: {
            simpleData: {
                enable: true
            }
        }
    };

  //禁用、解禁选中节点
    function Disabled1() {
        var treeObj = $.fn.zTree.getZTreeObj("au_chage");
       // var nodes = treeObj.getCheckedNodes();
        var nodes = treeObj.getNodes();
        for (var i = 0; i < nodes.length; i++) {
        	alert(nodes[i].id);
            //treeObj.setChkDisabled(nodes[i], true);
           // treeObj.checkNode(nodes[i], false,false);
        	if(nodes[i].id=='21'){
        		alert('a');
        		var childNodes = treeObj.getNodesByParam("isParent", false , nodes[i]);
        		for(var m = 0; m < childNodes.length; m++) {
        			alert(childNodes[i].id);
        			treeObj.setChkDisabled(childNodes[i], true);
        		}
        	}
        }
    }
    function Disabled2() {
        var treeObj = $.fn.zTree.getZTreeObj("au_chage");
        var nodes = treeObj.getCheckedNodes();

        for (var i = 0; i < nodes.length; i++) {
            treeObj.setChkDisabled(nodes[i], false);
        }
    }
    
    function getChildNodes(treeNode) {
        var childNodes = ztree.transformToArray(treeNode);
        var nodes = new Array();
        for(i = 0; i < childNodes.length; i++) {
                   nodes[i] = childNodes[i].id;
        }
        return nodes.join(",");
    }


