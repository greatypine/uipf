var dataSet;
var leftMenusTree;
$(function(){
	init();
});
function init(){
	initData();
}
function initData(){
	dataSet = new DataSet();
	dataSet.querydataSetTree();
	$("#type").combobox({
		url:content+'/data/getDateType'
	})
}

function DataSet(){
	this.querydataSetTree = function(index){
		leftMenusTree = $('#choosedata').tree({url: content+"/data/queryDataTree",
			animate:true,
			lines:true,
			checkbox:false,
			onlyLeafCheck:true,
			queryParams: {},
			onBeforeLoad: function (node, param) {
                if(node){
                    param.id = node.id;
                }
            },
	        onBeforeSelect: function(node){
	        	var tree = $(this).tree;
	        	var isLeaf = tree('isLeaf', node.target);  
//	        	if(!isLeaf) {tree('uncheck', node.target);  return ;}
			},
			onClick:function(node){
				var tree = $(this).tree;
	        	var isLeaf = tree('isLeaf', node.target);  
	        	if(!isLeaf) {tree('uncheck', node.target);  return ;}
	        	$("#dataColumnstb").datagrid({
	    		    url:content + "/data/queryDataColumns",
	    		    fitColumns:true,
			        singleSelect:true,
			        rownumbers:true,
			        pagination:true,
			        fit:true,
			        pageSize:20,
			        loadMsg:'正在加载，请稍后...',
			        collapsible:false,
	    		    queryParams:{"dataId":node.id},
	    		    columns:[[
	    		        {field:'id',title:'id',hidden:true},
	    		        {field:'code',title:'字段名',width:"15%"},
	    		        {field:'type',title:'类型',width:"20%",formatter:function(val,row){
	    		        	var result = "";
	    		        	switch (val) {
							case 1:
								result = "整型";
								break;
							case 2:
								result = "浮点型";							
								break;
							case 3:
								result = "字符串型";
								break;
							case 4:
								result = "日期";
								break;
							case 5:
								result = "时间戳";
								break;
							default:
								break;
							}
					        return result;
				        }},
				        {field:'status',title:'状态',width:"8%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">关闭</font>'
					        }else{
					        	return '<font color="33CC33">开启</font>'
					        }
				        }},
				        {field:'createuser',title:'创建人',width:"10%",align:'center'},
				        {field:'updateuser',title:'更新人',width:"10%",align:'center'},
				        {field:'updatetime',title:'更新时间',width:"12%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
	    		        {field:'name',title:'描述',width:"40%"}
	    		    ]],
	                onDblClickRow:function(index,row){
	                	$("#datacolumnsdlg").dialog("open").dialog("center").dialog("setTitle","更新字段");
						$("#datacolumnsdlg-fm").form("clear").form("load",row);
	                },
	                onSelect:function(index,row){
	                	$("#removedc").linkbutton('enable');
	                }
	        	});
			},
			onContextMenu: function(e, node){ //给结点添加右键菜单   
                e.preventDefault();  //阻止右键默认事件
                $('#choosedata').tree('select', node.target);
                var root = null;
                try{
                	root = $('#choosedata').tree('getParent', node.target)//判断该结点有没有父结点  
                }catch(e){
                	root = null;
                	console.log("右键对象获取失败！");
                }
                if(root == null){//若成立则为根结点，添加一个右键样式，可以添加子节点  
                    $('#parentNode').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                }else{
                	$('#leaf').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            } 
		});
	};
	this.add = function(){
		$("#datacolumnsdlg").dialog("open").dialog("center").dialog("setTitle","添加数据字段");
		$("#datacolumnsdlg-fm").form("clear");
	};
	this.complate = function(){
		var node = $('#choosedata').tree('getSelected');
		$("#dataId").val(node.id);
		$('#datacolumnsdlg-fm').form('submit',{
			url:content+"/data/saveOrUpdateDataColumns",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#datacolumnsdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#datacolumnsdlg').dialog('close');
					$('#dataColumnstb').datagrid('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.remove = function(){
		var row=$('#dataColumnstb').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/data/deleteColumns",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#dataColumnstb').datagrid('reload');
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.datacomplate = function(){
		$('#datadlg-fm').form('submit',{
			url:content+"/data/saveOrUpdateData",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#datadlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#datadlg').dialog('close');
					$('#choosedata').tree('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.addNode = function(){
		$("#datadlg").dialog("open").dialog("center").dialog("setTitle","添加对象");
		$("#datadlg-fm").form("clear");
	};
	this.updateNode = function(){
		var node = $('#choosedata').tree('getSelected');
		$("#datadlg").dialog("open").dialog("center").dialog("setTitle","更新对象");
		$("#datadlg-fm").form("clear").form("load",node);
	};
	this.removeNode = function(){
		var node = $('#choosedata').tree('getSelected');
		if(node){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:node.id},
						url : content+"/data/deleteData",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#choosedata').tree('reload');
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
}