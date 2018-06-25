var sqlInsertOrUpdate;
var cu;
var sqlInsertOrUpdate_table;
$(function(){
	initData();
});
function initData(){
	sqlInsertOrUpdate = new SqlInsertOrUpdate();
	cu = new CommonUtils();
	sqlInsertOrUpdate.initCompant();
	$("#querycreateTime").datebox({
		onSelect: function(date){
			sqlInsertOrUpdate.onDateDiffSelect(date);
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
	$("#dbId").combobox({
		url:content+'/db/queryDataBaseTree'
	});
	$("#inputParams").combobox({
		url:content+'/data/queryDataList?status=1'
	});
	$("#outParams").combobox({
		url:content+'/data/queryDataList?status=1'
	});
	$("#dbCode").combobox({
		url:content+'/db/queryDataBaseTree',
		onSelect: function (row) {
			if (row != null) {
				$("#tableName").combobox({
					url:content+'/db/queryTablesByDb?dbcode='+row.value
				});
			}
		}
	});
	
}
function SqlInsertOrUpdate(){
	this.selectedKeysNode = null;
	this.selectedKeysIndex  = null;
	this.lastSelectedVal = null;
	this.initCompant = function(){
		sqlInsertOrUpdate_table = $('#sqlInsertOrUpdate_table').datagrid({
		        url: content+"/sqlInsertOrUpdate/querySqlInsertOrUpdates",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
		        	 	{field:'inputParams',title:'输入对象',hidden:true},
				        {field:'name',title:'名称',width:"12%"},
				        {field:'status',title:'状态',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">关闭</font>'
					        }else{
					        	return '<font color="33CC33">开启</font>'
					        }
				        }},
				        {field:'dbname',title:'数据源',width:"18%",align:'center'},
				        {field:'tableName',title:'表名称',width:"12%",align:'center'},
				        {field:'updateColumns',title:'更新字段',width:"8%",align:'center',formatter:function(val,row){
//				        	if(val==undefined ||val=="") return "";
					        return '<a id="'+row.id+'" href="javascripte:void(0)" onClick="sqlInsertOrUpdate.queryUpdateColumns(this.id)" class="updateColumnslinkbtn easyui-linkbutton" data-options="plain:true,iconCls:\'icon-search\'">更新字段</a>';
				        }},
				        {field:'updateKeys',title:'检查字段',width:"8%",align:'center',formatter:function(val,row){
//				        	if(val==undefined ||val=="") return "";
				        	return '<a id="'+row.id+'" href="javascripte:void(0)" onClick="sqlInsertOrUpdate.queryUpdateKeys(this.id)" class="updateKeyslinkbtn easyui-linkbutton" data-options="plain:true,iconCls:\'icon-search\'">检查字段</a>';
				        }},
				        {field:'inputParamsName',title:'输入对象',width:"8%",formatter:function(val,row){
				        	if(val==undefined ||val=="") return "";
				        	return '<a id="'+row.inputParams+'" href="javascripte:void(0)" onClick="sqlInsertOrUpdate.queryInputObj(this.id)" class="updateKeyslinkbtn easyui-linkbutton" data-options="plain:true,iconCls:\'icon-search\'">'+val+'</a>';
				        }},
				        {field:'createuser',title:'创建人',width:"8%",align:'center'},
				        {field:'createtime',title:'创建时间',width:"13%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'updateuser',title:'更新人',width:"8%",align:'center'},
				        {field:'updatetime',title:'更新时间',width:"13%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
				onDblClickRow:function(index,row){
					$("#sqlInsertOrUpdatedlg").dialog("open").dialog("center").dialog("setTitle","更新sqlInsertOrUpdate任务");
					row.status = row.status==true?1:0;
					row.ischeck = row.ischeck==true?1:0;
					$("#sqlInsertOrUpdatedlg-fm").form("clear").form("load",row);
					sqlInsertOrUpdate.configtionConditionKeys(row);
					sqlInsertOrUpdate.configtionUpdateColumns(row);
				},
				onSelect:function(index,row){
				
				},
				onLoadSuccess:function(){
					$(".updateColumnslinkbtn").linkbutton();
					$(".updateKeyslinkbtn").linkbutton();
				} 
			});
	};
	this.query = function(){
		var name = $("#queryname").val();
		var status = ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var params = {};
		if(name!="") {params.name = name;}
		params.status = status;
		if(createTime!="" && createTime!=null) {params.createtime = createTime;}
		sqlInsertOrUpdate_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("sqlInsertOrUpdateform").serializeJson();
	};
	this.clearForm = function(){
		$('#sqlInsertOrUpdateform').form('clear');
	};
	this.add = function(){
		$("#sqlInsertOrUpdatedlg").dialog("open").dialog("center").dialog("setTitle","添加sqlInsertOrUpdate任务");
		$("#sqlInsertOrUpdatedlg-fm").form("clear");
		$("#sqlInsertOrUpdate_table").datagrid("unselectAll");
		var row = {id:0};
		sqlInsertOrUpdate.configtionConditionKeys(row);
		sqlInsertOrUpdate.configtionUpdateColumns(row);
	};
	this.complate = function(){
//		$('#sqlInsertOrUpdatedlg-fm').form('submit',{
//			url:content+"/sqlInsertOrUpdate/saveOrUpdate",
//			onSubmit:function(param){
//				return $(this).form('enableValidation').form('validate');
//			},
//			success: function(result){
//				if(result){
//					$('#sqlInsertOrUpdatedlg-fm').form('clear');
//					$.messager.alert('提示','操作成功');
//					$.messager.progress('close');
//					$('#sqlInsertOrUpdatedlg').dialog('close');
//					$('#sqlInsertOrUpdate_table').datagrid('reload');
//				}else{
//					$.messager.alert('提示','操作失败','warning');
//					$.messager.progress('close');
//				}
//			}
//		});
		var sqlInsertOrUpdatedform = $('#sqlInsertOrUpdatedlg-fm').serializeJson();
		if(sqlInsertOrUpdatedform.status==1) sqlInsertOrUpdatedform.status = true;else sqlInsertOrUpdatedform.status = false;
		var conditionDatas = $('#condition_table').datagrid('getData').rows;
		var columnsDatas = $('#updatekeys_condition_table').datagrid('getData').rows;
		if(conditionDatas.length<=0) conditionDatas=null;
		if(columnsDatas.length<=0) columnsDatas=null;
		$.ajax({
			type:"POST",
			data :{baseData:JSON.stringify(sqlInsertOrUpdatedform),keys:conditionDatas!=null?JSON.stringify(conditionDatas):conditionDatas,columns:columnsDatas!=null?JSON.stringify(columnsDatas):columnsDatas},
			url : content+"/sqlInsertOrUpdate/saveAll",
			error : function(data) {
				$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
				return false;
			},
			success : function(data) {
				if(data){//成功
					$('#sqlInsertOrUpdatedlg').dialog('close')
					$('#sqlInsertOrUpdate_table').datagrid('reload');
					$("#sqlInsertOrUpdatedlg-fm").form("clear");
					$.messager.alert('提示信息','操作成功!');
				}else{
					$.messager.alert('提示信息','操作失败!','warning');
				}
			}
		});
	};
	this.remove = function(){
		var row=$('#sqlInsertOrUpdate_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/sqlInsertOrUpdate/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#sqlInsertOrUpdate_table').datagrid('reload');
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
	this.queryUpdateColumns = function(id){
		$("#updateColumnsdlg").dialog("open").dialog("center").dialog("setTitle","更新字段");
		$('#updateColumns_table').datagrid({
	        url: content+"/updatecolumns/queryList",
	        fitColumns:true,
	        pagination:true,
	        loadMsg:'正在加载，请稍后...',
	        collapsible:false,
	        queryParams:{
	        	"insertUpdateJobId":id
			},
	        columns:[[
	        	 	{field:'id',title:'编号',hidden:true},
	        	 	{field:'code',title:'编码',width:"33%",align:'center'},
			        {field:'name',title:'名称',width:"33%"},
			        {field:'type',title:'类型',width:"33%",align:'center',formatter:function(val,row){
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
			        }}
	        ]]
		});
	};
	this.queryUpdateKeys = function(id){
		$("#updateKeysdlg").dialog("open").dialog("center").dialog("setTitle","更新字段");
		$('#updateKeys_table').datagrid({
	        url: content+"/updatekeys/queryList",
	        fitColumns:true,
	        pagination:true,
	        loadMsg:'正在加载，请稍后...',
	        collapsible:false,
	        queryParams:{
	        	"insertUpdateJobId":id
			},
	        columns:[[
	        	 	{field:'id',title:'编号',hidden:true},
	        	 	{field:'code',title:'编码',width:"50%",align:'center'},
			        {field:'name',title:'名称',width:"50%"}
	        ]]
		});
	};
	
	this.queryInputObj = function(id){
		$("#inputparamsdlg").dialog("open").dialog("center").dialog("setTitle","更新字段");
		$('#inputparams_tree').tree({url: content+"/data/queryDataColumnsTree?id="+id,
			animate:true,
			lines:true,
			checkbox:false,
			onlyLeafCheck:true,
	        onBeforeSelect: function(node){
	        	var tree = $(this).tree;
	        	var isLeaf = tree('isLeaf', node.target);  
	        	if(!isLeaf) {tree('uncheck', node.target);  return ;}
			},
			onClick:function(node){
				var tree = $(this).tree;
	        	var isLeaf = tree('isLeaf', node.target);  
	        	if(!isLeaf) {tree('uncheck', node.target);  return ;}
			}
		});
	};
	
	//------------------------条件字段----------------------------------
	this.configtionConditionKeys = function(row){
		var dbcode = $("#dbCode").combobox("getValue");
		var tableName = $("#tableName").combobox("getValue");
		$('#condition_table').datagrid({
	        url: content+"/updatekeys/queryBeanList",
	        fitColumns:true,
	        loadMsg:'正在加载，请稍后...',
	        collapsible:false,
	        queryParams:{
	        	"insertUpdateJobId":row.id
			},
	        columns:[[
	        	 	{field:'id',title:'编号',hidden:true},
	        	 	{field:'code',title:'表字段',width:"22%",align:'center',editor:{
						type:'combobox',
						options:{
							valueField:'id',
							textField:'text',
							method:'get',
							url:content+'/db/getColumnsByTable?dbcode='+dbcode+'&tablename='+tableName,
							required:false,
							onSelect:function(record){
								sqlInsertOrUpdate.lastSelectedVal = record.id;
							}
						}
					}},
			        {field:'compareSymbol',title:'比较符',width:"22%",align:'center',editor:{
						type:'combobox',
						options:{
							valueField:'name',
							textField:'name',
							method:'get',
							url:content+'/data/getCompareSymbol',
							required:false,
							onSelect:function(record){
								sqlInsertOrUpdate.lastSelectedVal = record.name;
							}
						}
					}},
	        	 	{field:'dataCode1',title:'字段1',width:"22%",align:'left',editor:{
						type:'combobox',
						options:{
							valueField:'code',
							textField:'code',
							method:'get',
							url:content+'/data/queryDataColumnsList?dataId='+row.inputParams,
							required:false,
							onSelect:function(record){
								sqlInsertOrUpdate.lastSelectedVal = record.code;
							}
						}
					}},
	        	 	{field:'dataCode2',title:'字段2',width:"22%",align:'left',editor:{
						type:'combobox',
						options:{
							valueField:'code',
							textField:'code',
							method:'get',
							url:content+'/data/queryDataColumnsList?dataId='+row.inputParams,
							required:false,
							onSelect:function(record){
								sqlInsertOrUpdate.lastSelectedVal = record.code;
								sqlInsertOrUpdate.addConditionEditComplate();
							}
						}
					}},
					{field:'action',title:'操作',width:"12%",align:'center',
						formatter:function(value,row,index){
							var s = '<a id="'+index+'" class="'+row.id+'" href="#" onclick="sqlInsertOrUpdate.saveConditionRow(this)">确认</a> ';
							var d = '<a id="'+index+'" class="'+row.id+'" href="#" onclick="sqlInsertOrUpdate.removeConditionForm(this)">删除</a>';
							return s+d;
						}
					}
	        ]],
	        onBeforeEdit:function(index,row){
				row.editing = true;
				sqlInsertOrUpdate.selectedKeysNode = row;
				sqlInsertOrUpdate.selectedKeysIndex = index;
				sqlInsertOrUpdate.updateActions(index);
				dbcode = $("#dbCode").combobox("getValue");
				tableName = $("#tableName").combobox("getValue");
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				sqlInsertOrUpdate.updateActions(index);
			},
			onCancelEdit:function(index,row){
				row.editing = false;
				sqlInsertOrUpdate.updateActions(index);
			}
		});
		$('#condition_table').datagrid("enableCellEditing");
	};
	this.updateActions = function(index){
		$('#condition_table').datagrid('refreshRow',index);
	};
	this.addConditionForm = function(){
		$('#condition_table').datagrid('appendRow',{
			id:'',
			code:'',
			compareSymbol: '',
			dataCode1: '',
			dataCode2: ''
		});
	};
	this.addConditionEditComplate = function(){
		var selectedkeyindex = sqlInsertOrUpdate.selectedKeysIndex;
		var selectednode = $('#condition_table').datagrid("selectRow",selectedkeyindex);
		selectednode.editing = false;
		sqlInsertOrUpdate.updateActions(selectedkeyindex);
	};
	this.removeConditionForm = function(target){
		$.messager.confirm('Confirm','确定要删除此项?',function(r){
			if (r){
				var id = $(target).attr("class");
				if(id!=""){
					$.ajax({
						data :{id:id},
						url : content+"/updatekeys/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(!data){//成功
								$.messager.alert('提示信息','删除失败!','warning');
							}
						}
					});
				}
				$('#condition_table').datagrid('deleteRow', sqlInsertOrUpdate.getRowIndex(target));
			}
		});
	};
	this.saveConditionRow = function(target){
		$('#condition_table').datagrid('endEdit', sqlInsertOrUpdate.getRowIndex(target));
	}
	
	//------------------------更新字段----------------------------------
	this.configtionUpdateColumns = function(row){
		var dbcode = $("#dbCode").combobox("getValue");
		var tableName = $("#tableName").combobox("getValue");
		$('#updatekeys_condition_table').datagrid({
	        url: content+"/updatecolumns/queryList",
	        fitColumns:true,
	        loadMsg:'正在加载，请稍后...',
	        collapsible:false,
	        queryParams:{
	        	"insertUpdateJobId":row.id
			},
	        columns:[[
	        	 	{field:'id',title:'编号',hidden:true},
	        	 	{field:'code',title:'表字段',width:"42%",align:'left',editor:{
						type:'combobox',
						options:{
							valueField:'id',
							textField:'text',
							method:'get',
							url:content+'/db/getColumnsByTable?dbcode='+dbcode+'&tablename='+tableName,
							required:false,
							onSelect:function(record){
								sqlInsertOrUpdate.lastSelectedVal = record.id;
							}
						}
					}},
	        	 	{field:'dataCode',title:'流字段',width:"42%",align:'left',editor:{
						type:'combobox',
						options:{
							valueField:'code',
							textField:'code',
							method:'get',
							url:content+'/data/queryDataColumnsList?dataId='+row.inputParams,
							required:false,
							onSelect:function(record){
								sqlInsertOrUpdate.lastSelectedVal = record.code;
								sqlInsertOrUpdate.addConditionEditComplate();
							}
						}
					}},
					{field:'action',title:'操作',width:"16%",align:'center',
						formatter:function(value,row,index){
							var s = '<a id="'+index+'" class="'+row.id+'" href="#" onclick="sqlInsertOrUpdate.saveColumnsRow(this)">确认</a> ';
							var d = '<a id="'+index+'" class="'+row.id+'" href="#" onclick="sqlInsertOrUpdate.removeColumnsForm(this)">删除</a>';
							return s+d;
						}
					}
	        ]],
	        onBeforeEdit:function(index,row){
				row.editing = true;
				sqlInsertOrUpdate.selectedKeysNode = row;
				sqlInsertOrUpdate.selectedKeysIndex = index;
				sqlInsertOrUpdate.updateColumnsActions(index);
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				sqlInsertOrUpdate.updateColumnsActions(index);
			},
			onCancelEdit:function(index,row){
				row.editing = false;
				sqlInsertOrUpdate.updateColumnsActions(index);
			}
		});
		$('#updatekeys_condition_table').datagrid("enableCellEditing");
	};
	this.updateColumnsActions = function(index){
		$('#updatekeys_condition_table').datagrid('refreshRow',index);
	};
	this.addColumnsForm = function(){
		$('#updatekeys_condition_table').datagrid('appendRow',{
			id:'',
			code:'',
			dataId: ''
		});
	};
	this.addColumnsEditComplate = function(){
		var selectedkeyindex = sqlInsertOrUpdate.selectedKeysIndex;
		var selectednode = $('#updatekeys_condition_table').datagrid("selectRow",selectedkeyindex);
		selectednode.editing = false;
		sqlInsertOrUpdate.updateColumnsActions(selectedkeyindex);
	};
	this.removeColumnsForm = function(target){
		$.messager.confirm('Confirm','确定要删除此项?',function(r){
			if (r){
				var id = $(target).attr("class");
				if(id!=""){
					$.ajax({
						data :{id:id},
						url : content+"/updatecolumns/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(!data){//成功
								$.messager.alert('提示信息','删除失败!','warning');
							}
						}
					});
				}
				$('#updatekeys_condition_table').datagrid('deleteRow', sqlInsertOrUpdate.getRowIndex(target));
			}
		});
	};
	this.getRowIndex = function(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	};
	this.saveColumnsRow = function(target){
		$('#updatekeys_condition_table').datagrid('endEdit', sqlInsertOrUpdate.getRowIndex(target));
	};
}
