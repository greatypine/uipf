var hive;
var cu;
var hive_table;
$(function(){
	initData();
});
function initData(){
	hive = new hive();
	cu = new CommonUtils();
	hive.initCompant();
	$("#diffDay_d").datebox({
		onSelect: function(date){
			hive.onDateDiffSelect(date);
		}
	});
	$('#diffDay_d').datebox().datebox('calendar').calendar({
		validator: function(date){
			var now = new Date();
			var d1 = new Date('2017', '01', '01');
			var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
			return d1<=date && date<=d2;
		}
	});
}
function hive(){
	this.initCompant = function(){
		hive_table = $('#hive_table').datagrid({
		        url: content+"/hive/queryHives",
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
				        {field:'name',title:'名称',width:"18%"},
				        {field:'status',title:'状态',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">关闭</font>'
					        }else{
					        	return '<font color="33CC33">开启</font>'
					        }
				        }},
				        {field:'url',title:'链接地址',width:"14%",align:'left',hidden:true},
				        {field:'user',title:'用户名',width:"5%",align:'center',hidden:true},
				        {field:'pass',title:'密码',width:"5%",align:'center',hidden:true},
				        {field:'dbname',title:'数据库',width:"5%",align:'center',hidden:true},
				        {field:'tablename',title:'目标表',width:"19%",align:'left'},
				        {field:'isimport',title:'是否导入',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">关闭</font>';
					        }else{
					        	return '<font color="33CC33">开启</font>';
					        }
				        }},
				        {field:'diffDay',title:'操作日期',width:"9%",align:'center',formatter:function(val,row){
					        return CU.date_add(null,val);
				        }},
				        {field:'filepath',title:'文件路径',width:"12%",hidden:true},
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
			    	return row.hql;
			    },
			    onCollapseRow:function(index,row){
		        	 $("#hive_table").datagrid("selectRow", index);
		        },
			    onExpandRow: function(index,row){
			    	$("#hive_table").datagrid("selectRow", index);
			    },
				onDblClickRow:function(index,row){
					$("#hivedlg").dialog("open").dialog("center").dialog("setTitle","更新hive任务");
					row.diffDay_d = CU.date_add(null,row.diffDay);
					row.status = row.status==true?1:0;
					row.ischeck = row.ischeck==true?1:0;
					$("#hivedlg-fm").form("clear").form("load",row);
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
		hive_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("hiveform").serializeJson();
	};
	this.clearForm = function(){
		$('#hiveform').form('clear');
	};
	this.add = function(){
		$("#hivedlg").dialog("open").dialog("center").dialog("setTitle","添加hive任务");
		$("#hivedlg-fm").form("clear");
	};
	this.complate = function(){
		$('#hivedlg-fm').form('submit',{
			url:content+"/hive/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#hivedlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#hivedlg').dialog('close');
					$('#hive_table').datagrid('reload');
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
		var row=$('#hive_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/hive/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#hive_table').datagrid('reload');
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
