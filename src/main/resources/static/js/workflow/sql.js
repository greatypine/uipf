var sql;
var cu;
var sql_table;
$(function(){
	initData();
});
function initData(){
	sql = new sql();
	cu = new CommonUtils();
	sql.initCompant();
	$("#querycreateTime").datebox({
		onSelect: function(date){
			sql.onDateDiffSelect(date);
		}
	});
	$('#querycreateTime').datebox().datebox('calendar').calendar({
		validator: function(date){
			var now = new Date();
			var d1 = new Date('2017', '01', '01');
			var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
			return d1<=date && date<=d2;
		}
	});
	$("#dbCode").combobox({
		url:content+'/db/queryDataBaseTree'
	});
	$("#inputParams").combobox({
		url:content+'/data/queryDataList?status=1'
	});
	
}
function sql(){
	this.initCompant = function(){
		sql_table = $('#sql_table').datagrid({
		        url: content+"/sql/querySqls",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'name',title:'名称',width:"18%"},
				        {field:'status',title:'状态',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">关闭</font>'
					        }else{
					        	return '<font color="33CC33">开启</font>'
					        }
				        }},
				        {field:'db_name',title:'数据源',width:"18%",align:'center'},
				        {field:'ismultiple',title:'执行多条',width:"6%",align:'center',formatter:function(val,row){
				        	if(val){
					        	return '<font color="FF0033">是</font>'
					        }else{
					        	return '<font color="33CC33">否</font>'
					        }
				        }},
				        {field:'input_params_name',title:'输入对象',width:"8%"},
				        {field:'createuser',title:'创建人',width:"8%",align:'center'},
				        {field:'createtime',title:'创建时间',width:"13%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'updateuser',title:'更新人',width:"8%",align:'center'},
				        {field:'updatetime',title:'更新时间',width:"13%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
		        view: detailview,
			    detailFormatter:function(index,row){
			    	return row.diySql;
			    },
			    onCollapseRow:function(index,row){
		        	 $("#sql_table").datagrid("selectRow", index);
		        },
			    onExpandRow: function(index,row){
			    	$("#sql_table").datagrid("selectRow", index);
			    },
				onDblClickRow:function(index,row){
					$("#sqldlg").dialog("open").dialog("center").dialog("setTitle","更新sql任务");
					row.status = row.status==true?1:0;
					row.ischeck = row.ischeck==true?1:0;
					$("#sqldlg-fm").form("clear").form("load",row);
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
		var ischeck = ($("#queryischeck").combobox('getValue')<0)?null:$("#queryischeck").combobox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var params = {};
		if(name!="") {params.name = name;}
		params.status = status;
		params.ischeck = ischeck;
		if(createTime!="" && createTime!=null) {params.createtime = createTime;}
		sql_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("sqlform").serializeJson();
	};
	this.clearForm = function(){
		$('#sqlform').form('clear');
	};
	this.add = function(){
		$("#sqldlg").dialog("open").dialog("center").dialog("setTitle","添加sql任务");
		$("#sqldlg-fm").form("clear");
	};
	this.complate = function(){
		$('#sqldlg-fm').form('submit',{
			url:content+"/sql/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#sqldlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#sqldlg').dialog('close');
					$('#sql_table').datagrid('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.remove = function(){
		var row=$('#sql_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/sql/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#sql_table').datagrid('reload');
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
