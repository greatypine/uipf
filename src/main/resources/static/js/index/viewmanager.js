var viewmanager;
var viewmanager_table;
$(function(){
	initData();
});
function initData(){
	viewmanager = new Myviewmanager();
	viewmanager.initCompant();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
}
function Myviewmanager(){
	/**
	 * 视图
	 */
	this.initCompant = function(params){
		$('#views-datagrid').datagrid({
			fitColumns:false,
	        singleSelect:true,
	        rownumbers:true,
	        loadMsg:'正在加载，请稍后...',
	        collapsible:false,
	        pageSize: 10,
	        pageList: [10, 15, 20],
		    url:content + "/views/queryList",
		    columns:[[
		        {field:'id',title:'id',width:100,hidden:true},
		        {field:'companyName',title:'门店名称',width:"25%"},
		        {field:'code',title:'视图编码',width:"20%"},
		        {field:'name',title:'视图名称',width:"20%"},
		        {field:'statusName',title:'状态',width:"10%"},
		        {field:'remark',title:'描述',width:"25%"}
		    ]],
            onSelect:function(index,row){
            	$("#views-datagrid-child").datagrid({
            		fitColumns:false,
        	        singleSelect:true,
        	        rownumbers:true,
        	        loadMsg:'正在加载，请稍后...',
        	        collapsible:false,
        	        pageSize: 10,
        	        pageList: [10, 15, 20],
        		    url:content + "/basecode/queryList",
        		    queryParams:{"viewName":row.code,"companyId":row.companyid},
        		    columns:[[
        		        {field:'id',title:'id',width:100,hidden:true},
        		        {field:'code',title:'编码',width:"20%"},
        		        {field:'name',title:'视图名称',width:"20%"},
        		        {field:'type',title:'类型',width:"12%"},
        		        {field:'status',title:'状态',width:100,width:"12%",
        		        	formatter:function(value,row,index){
        		        		if(value==1){
            		                return "显示";
            		            }else{
            		                return "隐藏";
            		            }
        		        	}
        		        }
        		    ]],
                    onDblClickRow:function(index,row){
                    	viewmanager.cvDblClickRow(index,row);
                    }
            	});
            },
            onDblClickRow:function(index,row){
            	viewmanager.pvDblClickRow(index,row);
            }
		});
	};
	this.query = function(){
		var name = $("#queryname").textbox("getValue");
		var params = {};
		if(name!="") {params.name = name;}
		viewmanager_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("viewmanagerform").serializeJson();
	};
	this.clearForm = function(){
		$('#viewmanagerform').form('clear');
	};
	this.vpadd = function(){
		$("#viewmanagerdlg-fm").form("clear");
		$("#viewmanagerdlg").dialog("open").dialog("center").dialog("setTitle","添加视图");
	};
	this.pvDblClickRow = function(index,row){
		$('#viewmanagerdlg').dialog('open').dialog('center').dialog('setTitle','修改视图');
		$('#viewmanagerdlg-fm').form('load',row);
	};
	this.cvDblClickRow = function(index,row){
		$('#cvdlg').dialog('open').dialog('center').dialog('setTitle','修改视图列表项');
		$('#cview-fm').form('clear').form('load',row);
	};
	this.vcadd = function(){
		var row = $("#views-datagrid").datagrid('getSelected');
		if(!row){
			$.messager.alert('提示','请先选择上面视图数据');
			return;
		}else{
			 $('#cvdlg').dialog('open').dialog('center').dialog('setTitle','添加视图列表项');
			 $('#cview-fm').form('clear');
			 $("#ccompanyId").textbox("setValue",row.companyid);
			 $("#ctype").textbox("setValue",row.type);
			 $("#cname").textbox("setValue",row.code);
		}
	}
	this.cvsearch = function(){
		var cvcode = $("#cvcode").val();
		var cvname = $("#cvname").val();
		var row = $('#views-datagrid').datagrid("getSelected");
		$('#views-datagrid-child').datagrid();
	}
	this.saveView = function (){
		$.messager.progress();
		$('#viewmanagerdlg-fm').form('submit', {
			url:content+"/views/saveOrUpdate",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');
				}
				return isValid;	
			},
			success: function(){
				$.messager.alert('提示','操作成功');
				$.messager.progress('close');
				$('#viewmanagerdlg').dialog('close');
				$('#views-datagrid').datagrid('reload');
			}
		});
	}
	this.savecView = function(){
		$.messager.progress();
		$('#cview-fm').form('submit', {
			url:content+"/basecode/saveOrUpdate",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');
				}
				return isValid;
			},
			success: function(){
				$.messager.alert('提示','添加成功');
				$.messager.progress('close');
				$('#cvdlg').dialog('close');
				$('#views-datagrid-child').datagrid('reload');
			}
		});
	}
	this.vpremove = function(){
		var row=$('#views-datagrid').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :row,
						type:"POST",
						url : content+"/views/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#views-datagrid').datagrid('reload');
								$('#views-datagrid-child').datagrid('reload');
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
	this.vcremove = function(){
		var row=$('#views-datagrid-child').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						type:"POST",
						url : content+"/basecode/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#views-datagrid-child').datagrid('reload');
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
