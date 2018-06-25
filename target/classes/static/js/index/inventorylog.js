var inventorylog;
var cu;
var inventorylog_table;
$(function(){
	initData();
});
function initData(){
	inventorylog = new Myinventorylog();
	cu = new CommonUtils();
	inventorylog.initCompant();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$("#append").linkbutton('disable');
	$("#delivery").linkbutton('disable');
}
function Myinventorylog(){
	this.initCompant = function(){
		inventorylog_table = $('#inventorylog_table').datagrid({
		        url: content+"/inventorylog/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'name',title:'产品名称',width:"15%"},
				        {field:'code',title:'产品编号',width:"15%"},
				        {field:'optionName',title:'操作类型',width:"10%",align:'center'},
				        {field:'numbs',title:'数量',width:"14%",align:'left'},
				        {field:'customername',title:'客户姓名',width:"8%",align:'center'},
				        {field:'totalAmount',title:'消费金额',width:"8%",align:'center'},
				        {field:'orderFinishTime',title:'订单操作时间',width:"8%",align:'center'},
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
		if(name!="") {params.inventorylogName = name;}
		params.status = status;
		if(createTime!="" && createTime!=null) {params.createtime = createTime;}
		inventorylog_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("inventorylogform").serializeJson();
	};
	this.clearForm = function(){
		$('#inventorylogform').form('clear');
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.remove = function(){
		var row=$('#inventorylog_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/inventorylog/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#inventorylog_table').datagrid('reload');
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
