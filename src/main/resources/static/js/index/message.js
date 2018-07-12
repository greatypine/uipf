var message;
var message_table;
$(function(){
	initData();
});
function initData(){
	message = new Mymessage();
	message.initCompant();
	$(".type").combobox({
		url:content+'/common/getView?viewname=v_message_type'
	});
	$(".status").combobox({
		url:content+'/common/getView?viewname=v_vip_customer_status&companyid='+user.user.companyid
	});
}
function Mymessage(){
	this.initCompant = function(){
		message_table = $('#message_table').datagrid({
		        url: content+"/message/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'name',title:'消息名称',width:"15%"},
				        {field:'statusName',title:'状态',width:"5%",align:'center'},
				        {field:'typeName',title:'类型',width:"10%",align:'center'},
				        {field:'title',title:'标题',width:"15%",align:'left'},
				        {field:'create_user',title:'创建人',width:"10%",align:'center'},
				        {field:'create_time',title:'创建时间',width:"15%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'update_user',title:'修改人',width:"10%",align:'center'},
				        {field:'update_time',title:'修改时间',width:"15%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
		        view: detailview,
			    detailFormatter:function(index,row){
			    	return row.message;
			    },
			    onCollapseRow:function(index,row){
		        	 $("#message_table").datagrid("selectRow", index);
		        },
			    onExpandRow: function(index,row){
			    	$("#message_table").datagrid("selectRow", index);
			    },
				onDblClickRow:function(index,row){
					$("#messagedlg").dialog("open").dialog("center").dialog("setTitle","更新消息");
					cu.initClearCombobox("status");
					cu.initClearCombobox("type");
					$("#messagedlg-fm").form("clear").form("load",row);
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var params = message.getParams();
		message_table.datagrid("load",params);
	};
	this.getParams = function(){
		var name = $("#queryname").textbox("getValue");
		var params = {};
		if(name!="") {params.name = name;}
		return params;
	};
	this.clearForm = function(){
		$('#messageform').form('clear');
	};
	this.add = function(){
		$("#messagedlg-fm").form("clear");
		cu.initClearCombobox("status");
		cu.initClearCombobox("type");
		$("#messagedlg").dialog("open").dialog("center").dialog("setTitle","添加产品");
	};
	this.complate = function(){
		$('#messagedlg-fm').form('submit',{
			url:content+"/message/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#messagedlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#messagedlg').dialog('close');
					$('#message_table').datagrid('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.remove = function(){
		var row=$('#message_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						type:"POST",
						url : content+"/message/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#message_table').datagrid('reload');
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
