var role;
var cu;
var role_table;
var menuSet;
var leftMenusTree;
$(function(){
	initData();
});
function initData(){
	role = new MyRole();
	cu = new CommonUtils();
	role.initCompant();
	menuSet = new MenuSet();
}
function MyRole(){
	this.roleid = null;
	this.initCompant = function(){
		role_table = $('#role_table').datagrid({
		        url: content+"/role/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'roleName',title:'角色名称',width:"18%"},
				        {field:'status',title:'状态',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">关闭</font>'
					        }else{
					        	return '<font color="33CC33">开启</font>'
					        }
				        }},
				        {field:'roleSign',title:'标识符',width:"14%",align:'center'},
				        {field:'permission_names',title:'标识符',width:"24%",align:'center'},
				        {field:'description',title:'描述',width:"25%",align:'left'}
		        ]],
				onDblClickRow:function(index,row){
					$("#roledlg").dialog("open").dialog("center").dialog("setTitle","更新role任务");
					role.initClearCombobox("permissions");
					$("#roledlg-fm").form("clear").form("load",row);
					$("#permissions").combobox({
						url:content+'/permission/queryBeanList',
						multiple:true,
						panelHeight:'auto',
						onLoadSuccess:function(){
				        	$(this).combobox("setValues",row.permissions.split(","));
				         }
					});
				},
				onSelect:function(index,row){
					role.roleid = row.id;
					menuSet.querymenuSetTree({"pid":null,"roleid":role.roleid});
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.query = function(){
		var name = $("#queryname").val();
		var status = ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue");
		var params = {};
		if(name!="") {params.roleName = name;}
		params.status = status;
		role_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("roleform").serializeJson();
	};
	this.clearForm = function(){
		$('#roleform').form('clear');
	};
	this.add = function(){
		$("#roledlg").dialog("open").dialog("center").dialog("setTitle","添加role任务");
		$("#roledlg-fm").form("clear");
		$("#permissions").combobox({
			url:content+'/permission/queryBeanList',
			multiple:true,
			panelHeight:'auto'
		});
	};
	this.complate = function(){
		$('#roledlg-fm').form('submit',{
			url:content+"/role/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#roledlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#roledlg').dialog('close');
					$('#role_table').datagrid('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.onDateDiffSelect = function(date){
		var day = CU.DateDiff(date,new Date());
		$("#diffDay").textbox("setValue",day);
	};
	this.remove = function(){
		var row=$('#role_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						type:"POST",
						data :{id:row.id},
						url : content+"/role/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#role_table').datagrid('reload');
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

function MenuSet(){
	this.querymenuSetTree = function(params){
		leftMenusTree = $('#choosemenu').tree({url: content+"/menu/selectAllMenusTreeByRole?roleid="+role.roleid,
			animate:true,
			lines:true,
			checkbox:true,
			cascadeCheck: false,
			onlyLeafCheck:false,
			queryParams: {},
			onLoadSuccess:function(node, data){
				$("#choosemenu").tree('expand',$('#choosemenu').tree("getRoot").target);
			},
	        onBeforeSelect: function(node){
			},
			onClick:function(node){
				if (checked) {
					var parentNode = $("#choosemenu").tree('getParent', node.target);
					if (parentNode != null) {
						$("#choosemenu").tree('check', parentNode.target);
					 }
				} else {
					var childNode = $("#choosemenu").tree('getChildren', node.target);
					if (childNode.length > 0) {
						for (var i = 0; i < childNode.length; i++) {
							$("#choosemenu").tree('uncheck', childNode[i].target);
						}
					}
				}
			},
			formatter:function(node){
				var s = '';
				s = '<font color="green" size="3">'+node.text+'</font>';
				if(node.checked){
					s = '<font color="green" size="3">'+node.text+'</font>';
				}else{
					s = '<font color="red" size="3">'+node.text+'</font>';
				}
				if (node.children){
					s += '&nbsp;<span style=\'color:blue\'>(' + node.children.length + ')</span>';
				}
				return s;
			}
		});
	};
	
	this.saveRoleMenus = function(){
//		var data = $('#choosemenu').tree('getData',$('#choosemenu').tree("getRoot").target);
//		var nodes = data.children;
		var nodes = $('#choosemenu').tree('getChecked');
//		var nodes = menuSet.getTreeSelected();
		if(nodes.length<=0){
			$.messager.alert('提示信息','没有要保存的菜单项!','warning');
			return ;
		}
		$.messager.confirm('提示信息', '你确认要保存此菜单配置吗?', function(r){
			if (r){
				$.ajax({
					type:'POST',
					dataType: 'json',
					data :{"menuss":JSON.stringify(nodes),"roleid":role.roleid},
					url : content+"/rolemenu/saveOrUpdate",
					error : function(menu) {
						$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
						return false;
					},
					success : function(rs) {
						if(rs){//成功
							$.messager.alert('提示信息','保存成功!');
							leftMenusTree.tree("reload");
						}else{
							$.messager.alert('提示信息','保存失败!','warning');
						}
					}
				});
			}
		});
	};
	this.arr = new Array();
	this.getTreeSelected = function(){
        var nodes = $('#choosemenu').tree('getChecked');
        for (var i = 0; i < nodes.length;i++) {
        	menuSet.arr.push(nodes[i]);
            menuSet.myFuc(nodes[i]);
        }
        return menuSet.arr;
	};
   //内部递归函数
   this.myFuc = function(n) {
        var parent = $('#choosemenu').tree('getParent', n.target);
        if (parent == null) return;
        if (menuSet.isExistItem(parent)) return;
        menuSet.arr.push(parent);
        menuSet.myFuc(parent);
    };
   //验证节点是否已存在数组中
   this.isExistItem = function(item){
        var flag = false;
        for (var i = 0; i < menuSet.arr.length;i++)
        {
            if (menuSet.arr[i] == item) {
                flag = true; break;
            }
        }
        return flag;
    };
}