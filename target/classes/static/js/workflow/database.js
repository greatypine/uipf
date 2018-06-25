var database;
var cu;
var database_table;
$(function(){
	initData();
});
function initData(){
	database = new database();
	cu = new CommonUtils();
	database.initCompant();
}
function database(){
	this.initCompant = function(){
		database_table = $('#database_table').datagrid({
		        url: content+"/db/queryDataBases",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
//		        	"name" : $("#queryname").val(),
//		    		"status" : ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue"),
//		    		"ischeck" : ($("#queryischeck").combobox('getValue')<0)?null:$("#queryischeck").combobox("getValue"),
//		    		"createtime" : ($("#querycreateTime").datebox('getValue')!=undefined)?CU.formatStrDateToDate($("#querycreateTime").datebox('getValue')):null,
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
		        	 	{field:'code',title:'编码',width:"13%",align:'left'},
				        {field:'remark',title:'描述',width:"13%"},
				        {field:'status',title:'状态',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">关闭</font>'
					        }else{
					        	return '<font color="33CC33">开启</font>'
					        }
				        }},
				        {field:'url',title:'链接地址',width:"35%",align:'left',hidden:true},
				        {field:'driver',title:'驱动',width:"14%",align:'center'},
				        {field:'name',title:'用户名',width:"10%",align:'center'},
				        {field:'createuser',title:'创建人',width:"10%",align:'center'},
				        {field:'createtime',title:'创建时间',width:"13%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'updateuser',title:'更新人',width:"10%",align:'center'},
				        {field:'updatetime',title:'更新时间',width:"13%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
				onDblClickRow:function(index,row){
					$("#databasedlg").dialog("open").dialog("center").dialog("setTitle","更新database任务");
					row.status = row.status==true?1:0;
					$("#databasedlg-fm").form("clear").form("load",row);
				},
				onSelect:function(index,row){
				
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var remak = $("#queryremak").val();
		var code = $("#querycode").val();
		var status = ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var params = {};
		if(code!="") {params.code = code;}
		params.status = status;
		if(coremakde!="") {params.remak = remak;}
		if(createTime!="" && createTime!=null) {params.createtime = createTime;}
		database_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("databaseform").serializeJson();
	};
	this.clearForm = function(){
		$('#databaseform').form('clear');
	};
	this.add = function(){
		$("#databasedlg").dialog("open").dialog("center").dialog("setTitle","添加database任务");
		$("#databasedlg-fm").form("clear");
	};
	this.complate = function(){
		$('#databasedlg-fm').form('submit',{
			url:content+"/db/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#databasedlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#databasedlg').dialog('close');
					$('#database_table').datagrid('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.remove = function(){
		var row=$('#database_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/db/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#database_table').datagrid('reload');
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
	this.check = function(){
		$.ajax({
			data :$("#databasedlg-fm").serializeJson(),
			url : content+"/db/check",
			error : function(data) {
				$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
				return false;
			},
			success : function(data) {
				if(data){//成功
					$.messager.alert('提示信息','链接成功!');
				}else{
					$.messager.alert('提示信息','链接失败!','warning');
				}
			}
		});
	};
}
