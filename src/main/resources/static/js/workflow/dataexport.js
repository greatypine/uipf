var dataexport;
var cu;
var dataexport_table;
$(function(){
	initData();
});
function initData(){
	dataexport = new dataexport();
	cu = new CommonUtils();
	dataexport.initCompant();
	$(".exporttype").combobox({
		url:content+'/dataexport/queryExprotType'
	});
	$("#inputParams").combobox({
		url:content+'/data/queryDataList?status=1'
	});
}
function dataexport(){
	this.initCompant = function(){
		dataexport_table = $('#dataexport_table').datagrid({
		        url: content+"/dataexport/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'name',title:'名称',width:"13%"},
				        {field:'status',title:'状态',width:"4%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">关闭</font>'
					        }else{
					        	return '<font color="33CC33">开启</font>'
					        }
				        }},
				        {field:'typename',title:'类型',width:"5%",align:'center'},
				        {field:'outpath',title:'输出路径',width:"12%",align:'left'},
				        {field:'filename',title:'文件名称',width:"8%",align:'center'},
				        {field:'nametype',title:'文件后缀',width:"12%",align:'center'},
				        {field:'inputParamsName',title:'输入对象',width:"7%",align:'center',formatter:function(val,row){
				        	if(val==undefined ||val=="") return "";
				        	return '<a id="'+row.inputparams+'" href="javascripte:void(0)" onClick="dataexport.queryInputObj(this.id)" class="inputobj easyui-linkbutton" data-options="plain:true,iconCls:\'icon-search\'">'+val+'</a>';
				        }},
				        {field:'createuser',title:'创建人',width:"7%",align:'center'},
				        {field:'createtime',title:'创建时间',width:"12%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'updateuser',title:'更新人',width:"7%",align:'center'},
				        {field:'updatetime',title:'更新时间',width:"12%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
		        view: detailview,
			    detailFormatter:function(index,row){
			    	return row.remark;
			    },
			    onExpandRow: function(index,row){
			    	$("#dataexport_table").datagrid("selectRow", index);
			    },
				onDblClickRow:function(index,row){
					$("#dataexportdlg").dialog("open").dialog("center").dialog("setTitle","更新dataexport任务");
					row.diffDay_d = CU.date_add(null,row.diffDay);
					row.status = row.status==true?1:0;
					$("#dataexportdlg-fm").form("clear").form("load",row);
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(){
					$(".inputobj").linkbutton();
				} 
			});
	};
	this.query = function(){
		var name = $("#queryname").val();
		var status = ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue");
		var type = ($("#querytype").combobox("getValue")<0)?null:$("#querytype").combobox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var params = {};
		if(name!="") {params.name = name;}
		if(status!="" && status!=null) params.status = status;
		if(type!="") {params.type = type;}
		if(createTime!="" && createTime!=null) {params.createtime = createTime;}
		dataexport_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("dataexportform").serializeJson();
	};
	this.clearForm = function(){
		$('#dataexportform').form('clear');
	};
	this.add = function(){
		$("#dataexportdlg").dialog("open").dialog("center").dialog("setTitle","添加dataexport任务");
		$("#dataexportdlg-fm").form("clear");
	};
	this.complate = function(){
		$('#dataexportdlg-fm').form('submit',{
			url:content+"/dataexport/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#dataexportdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#dataexportdlg').dialog('close');
					$('#dataexport_table').datagrid('reload');
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
		var row=$('#dataexport_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/dataexport/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#dataexport_table').datagrid('reload');
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
	this.clear = function(){
		$("#dataexportform").form("clear");
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
}
