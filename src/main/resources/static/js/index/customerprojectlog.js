var customerprojectlog;
var cu;
var customerprojectlog_table;
$(function(){
	initData();
});
function initData(){
	customerprojectlog = new Mycustomerprojectlog();
	cu = new CommonUtils();
	customerprojectlog.initCompant();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$("#append").linkbutton('disable');
	$("#delivery").linkbutton('disable');
}
function Mycustomerprojectlog(){
	this.initCompant = function(){
		customerprojectlog_table = $('#customerprojectlog_table').datagrid({
		        url: content+"/cpl/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'customer_name',title:'客户姓名',width:"10%"},
				        {field:'customer_phone',title:'客户电话',width:"10%"},
				        {field:'project_name',title:'消费项目',width:"12%"},
				        {field:'create_time',title:'消费时间',width:"12%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'projectName',title:'消费类型',width:"10%",align:'left'},
				        {field:'project_nums',title:'剩余次数',width:"8%",align:'center'},
				        {field:'deadline',title:'截止日期',width:"10%",align:'center'},
				        {field:'create_user',title:'操作人',width:"8%",align:'center'}
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
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager")?$("#querycompanyid").combobox("getValue"):null;
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var params = {};
		params.companyid = companyid;
		if(createTime!="" && createTime!=null) {params.createTime = createTime;}
		customerprojectlog_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("customerprojectlogform").serializeJson();
	};
	this.clearForm = function(){
		$('#customerprojectlogform').form('clear');
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.remove = function(){
		var row=$('#customerprojectlog_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/cpl/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#customerprojectlog_table').datagrid('reload');
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
