var systempermission;
var cu;
var systempermission_table;
$(function(){
	initData();
});
function initData(){
	systempermission = new systempermission();
	cu = new CommonUtils();
	systempermission.initCompant();
}
function systempermission(){
	this.initCompant = function(){
		systempermission_table = $('#systempermission_table').datagrid({
		        url: content+"/permission/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'permissionName',title:'用户名称',width:"15%",align:'center'},
				        {field:'permissionSign',title:'昵称',width:"15%",align:'center'},
				        {field:'description',title:'描述',width:"70%",align:'left'}
		        ]],
				onDblClickRow:function(index,row){
					$("#systempermissiondlg").dialog("open").dialog("center").dialog("setTitle","更新用户信息");
					$("#systempermissiondlg-fm").form("clear").form("load",row);
				},
				onSelect:function(index,row){
				
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var name = $("#queryname").val();
		var permissionName = $("#permissionName").textbox("getValue");
		var params = {};
		params.permissionName = permissionName;
		systempermission_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("systempermissionform").serializeJson();
	};
	this.clearForm = function(){
		$('#systempermissionform').form('clear');
	};
	this.add = function(){
		$("#systempermissiondlg").dialog("open").dialog("center").dialog("setTitle","添加systempermission任务");
		$("#systempermissiondlg-fm").form("clear");
	};
	this.complate = function(){
		$('#systempermissiondlg-fm').form('submit',{
			url:content+"/permission/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#systempermissiondlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#systempermissiondlg').dialog('close');
					$('#systempermission_table').datagrid('reload');
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
		var row=$('#systempermission_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						type:"POST",
						data :{id:row.id},
						url : content+"/permission/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#systempermission_table').datagrid('reload');
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
