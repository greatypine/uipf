var menuSet;
var leftMenusTree;
$(function(){
	init();
});
function init(){
	initmenu();
}
function initmenu(){
	menuSet = new menuSet();
	menuSet.querymenuSetTree({"pid":-1});
//	$("#address").textbox({readonly:"readonly"});
	//项目查询
	$("#roleids").combobox({
		url: content+"/role/queryBeanList",
	    valueField:'id',
	    textField:'roleName',
	    multiple:true,
		panelHeight:'auto'
//        onLoadSuccess:function(){
//        	var dataTemp = "";
//        	var _this = $(this);
//        	var rows = _this.combobox("getData");
//        	var selectRow = $("#views-datagrid-child-role-user").datagrid('getSelected');
//        	if(selectRow!=null){
//        		$.ajax({
//        			cache : false,
//        			type : 'POST',
//        			dataType : "json",
//        			data : {"userId": selectRow.user_id},
//        			async : false,
//        			url : basePath+"/user/queryUserRoleProject",
//        			error : function(data) {
//        				createErrorInfo('服务器连接超时请重试!');
//        			},
//        			success : function(data) {
//        				_this.combobox("setValues",data);
//        			}  
//        		});
//        	}
//	    }
	});
};

function menuSet(){
	this.querymenuSetTree = function(params){
		leftMenusTree = $('#choosemenu').tree({url: content+"/menu/queryAllMenus?pid="+params.pid,
			animate:true,
			lines:true,
			onBeforeExpand:function(node){
				$('#choosemenu').tree('options').url = content+'/menu/queryAllMenus?pid='+node.id;
	        },
			checkbox:false,
			onlyLeafCheck:true,
			queryParams: {},
			onBeforeLoad: function (node, param) {
                if(node){
                    param.id = node.id;
                }
            },
            formatter:function(node){
				var s = '';
				s = '<font color="green" size="2">'+node.text+'</font>';
				if (node.children){
					s += '&nbsp;<span style=\'color:blue\'>(' + node.children.length + ')</span>';
				}
				return s;
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
	        	$("#menuColumnstb").menugrid({
	    		    url:content + "/menu/querymenuColumns",
	    		    fitColumns:true,
			        singleSelect:true,
			        rownumbers:true,
			        pagination:true,
			        fit:true,
			        pageSize:20,
			        loadMsg:'正在加载，请稍后...',
			        collapsible:false,
	    		    queryParams:{"menuId":node.id},
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
	                	$("#menucolumnsdlg").dialog("open").dialog("center").dialog("setTitle","更新字段");
	                	cu.initClearCombobox("roleids");
	                	cu.initClearCombobox("state");
	                	cu.initClearCombobox("status");
						$("#menucolumnsdlg-fm").form("clear").form("load",row);
	                },
	                onSelect:function(index,row){
	                	$("#removedc").linkbutton('enable');
	                }
	        	});
			},
			onContextMenu: function(e, node){ //给结点添加右键菜单   
                e.preventDefault();  //阻止右键默认事件
                $('#choosemenu').tree('select', node.target);
                var tree = $(this).tree;
	        	var isLeaf = tree('isLeaf', node.target);  
                if(!isLeaf){//若成立则为根结点，添加一个右键样式，可以添加子节点  
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
		$("#menucolumnsdlg").dialog("open").dialog("center").dialog("setTitle","添加数据字段");
		cu.initClearCombobox("roleids");
		cu.initClearCombobox("state");
		cu.initClearCombobox("status");
		$("#menucolumnsdlg-fm").form("clear");
	};
	this.complate = function(){
		var node = $('#choosemenu').tree('getSelected');
		var id = $("#id").val();
		if(id=="" || id == null){
			$("#code").val(node.code);
			$("#parendid").val(node.id);
		}
		$('#menudlg-fm').form('submit',{
			url:content+"/menu/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#menudlg-fm').form('clear');
					$("#roleids").combobox("unselect");
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#menudlg').dialog('close');
					$('#choosemenu').tree('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.remove = function(){
		var row=$('#menuColumnstb').menugrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/menu/deleteColumns",
						error : function(menu) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(menu) {
							if(menu){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#menuColumnstb').menugrid('reload');
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
	this.addNode = function(){
		$("#menudlg").dialog("open").dialog("center").dialog("setTitle","添加目录");
		$("#menudlg-fm").form("clear");
	};
	this.updateNode = function(){
		var node = $('#choosemenu').tree('getSelected');
		$("#menudlg").dialog("open").dialog("center").dialog("setTitle","更新目录");
		$("#menudlg-fm").form("clear").form("load",node);
	};
	this.removeNode = function(){
		var node = $('#choosemenu').tree('getSelected');
		if(node){
			if(node.parendId==-1){
				$.messager.alert('提示信息','顶级菜单不能删除!','warning');
				return ;
			}
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						type:'POST',
						data :{id:node.id},
						url : content+"/menu/delete",
						error : function(menu) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(menu) {
							if(menu){//成功
								$.messager.alert('提示信息','删除成功!');
								menuSet.querymenuSetTree({"pid":-1});
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