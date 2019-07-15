// JavaScript Document
function zTreeOnClick(event, treeId, treeNode) {
	alert(treeNode.tId + ", " + treeNode.name);
}

var settings= {
	data: {
		key: {
			title:"t"
		},
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: zTreeOnClick
	}
};
var zNodes2 =[
	{ id:1, pId:0, name:"京东网上商城", open:true},
	{ id:11, pId:1, name:"Accoutn", open:true},
	{ id:111, pId:11, name:"随意勾选 1-1-1"},
	{ id:112, pId:11, name:"随意勾选 1-1-2"},
	{ id:12, pId:1, name:"随意勾选 1-2", open:true},
	{ id:121, pId:12, name:"随意勾选 1-2-1"},
	{ id:122, pId:12, name:"随意勾选 1-2-2"},
	{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
	{ id:21, pId:2, name:"随意勾选 2-1"},
	{ id:22, pId:2, name:"随意勾选 2-2", open:true},
	{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
	{ id:222, pId:22, name:"随意勾选 2-2-2"},
	{ id:23, pId:2, name:"随意勾选 2-3"}
];
var text;
function zTreeOnClick(event, treeId, treeNode) {
   //lert(treeNode.tId + ", " + treeNode.name);
    text=treeNode.name;
}