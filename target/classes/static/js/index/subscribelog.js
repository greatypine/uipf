var subscribelog;
var cu;
var subscribelog_table;
$(function(){
	initData();
});
function initData(){
	subscribelog = new Mysubscribelog();
	cu = new CommonUtils();
	subscribelog.initCompant();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$("#append").linkbutton('disable');
	$("#delivery").linkbutton('disable');
}
function Mysubscribelog(){
	this.initCompant = function(){
		subscribelog_table = $('#subscribelog_table').datagrid({
		        url: content+"/subscribelog/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'statusName',title:'预约单状态',width:"8%"},
				        {field:'optionName',title:'操作类型',width:"10%",align:'center'},
				        {field:'rootInName',title:'来源',width:"8%",align:'left'},
				        {field:'customerName',title:'客户姓名',width:"8%",align:'center'},
				        {field:'sexName',title:'客户性别',width:"6%"},
				        {field:'customerPhone',title:'客户电话',width:"8%",align:'center'},
				        {field:'subscribe_create_user',title:'预约人',width:"8%",align:'center'},
				        {field:'subscribe_date',title:'预约时间',width:"10%",align:'center'},
				        {field:'createTime',title:'操作时间',width:"10%",align:'center'},
				        {field:'createUser',title:'操作人',width:"8%",align:'center'}
		        ]],
				onDblClickRow:function(index,row){
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var name = $("#queryname").val();
		var status = ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var params = {};
		if(name!="") {params.subscribelogName = name;}
		params.status = status;
		if(createTime!="" && createTime!=null) {params.createtime = createTime;}
		subscribelog_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("subscribelogform").serializeJson();
	};
	this.clearForm = function(){
		$('#subscribelogform').form('clear');
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.remove = function(){
		var row=$('#subscribelog_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/subscribelog/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#subscribelog_table').datagrid('reload');
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
