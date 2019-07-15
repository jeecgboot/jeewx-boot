(function(){
    UM.registerWidget('inserthtmlty', {
    	 tpl: "<div style='height:180px;margin:5px 0px 2px 3px'>"+
         "<textarea id='edui-insertHtmlContent' rows='10' style='width:99%;height:99%'></textarea>" +
         "</div>",
        initContent: function (editor) {
        	var html = $.parseTmpl(this.tpl);
            this.root().html(html);
        },
        initEvent: function (editor, $w) {
        	console.log("插入html代码窗口实例化");
        },
        buttons: {
            'ok': {
                exec: function (editor, $w) {
                	var insertHtmlContent = document.getElementById('edui-insertHtmlContent').value;
                	if(!!insertHtmlContent){
                		editor.execCommand('inserthtml', insertHtmlContent);
                	}
                }
            },
            'cancel':{}
        },
        width: 400
    })
})();

