var cache;
var cache_table;
basePath = content;
$(function(){
	initData();
});
function initData(){
	cache = new Mycache();
	cache.initCompant();
	cache.query();
}
function Mycache(){
	this.initCompant = function(){
		cache_table = $('#views-datagrid-cache-option').datagrid({
		    url:basePath + "/cache/readCache",
			loadMsg : "正在加载，请稍后...",
		    columns:[[
		        {field:'projectname',title:'模块名称',width:"30%"},
		        {field:'proabbreviation',title:'模块简称',width:"40%"},
		        {field:'key',title:'缓存名称',width:"30%"}
		    ]],
		    onDblClickRow:function(index,row){
		    	cache.roDblClickRow(index,row);
	        },
	        onSelect:function(index,row){
            	$("#cacheRemoveBykey").linkbutton('enable');
            }
		});
	};
	this.query = function(){
		var params = cache.getParams();
		cache_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("cachequeryform").serializeJson();
	};
	this.clearForm = function(){
		$('#cachequeryform').form('clear');
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	/**
	 * 角色操作编辑弹窗方法
	 */
	this.roDblClickRow = function(index,row){
		cache.roedit(index,row);
	}
	/**
	 * 操作编辑弹窗方法
	 */
	this.roedit = function(index,row){
		var row = $("#views-datagrid-role-option").datagrid('getSelected');
		if(!row){
			$.messager.alert("提示","请先选择要修改的数据！"); return;
		}
		$('#rodlg').dialog('open').dialog('center').dialog('setTitle','修改角色列表项');
		$('#roiew-fm').form('clear').form('load',row);
	}
	this.cacheRemoveBykey = function(){
		var row = $('#views-datagrid-cache-option').datagrid("getSelected");
		if(!row){
			$.messager.alert('信息提示','请选中要清除的项目列!','info');
			return ;
		}
		$.messager.confirm('信息提示', '您想要删除此项吗？', function(r){
			if (r){
				var url = basePath+"/cache/deleteCacheByKey";
				$.getJSON(url,{"cacheStore":row.proabbreviation,"key":row.key},function(result){
					if(result>0){
						$.messager.alert('信息提示','清除成功!','info');
						$('#views-datagrid-cache-option').datagrid("reload");
					}
				});
			}
		});
	}
	this.cacheRemove = function (cachetype){
		var url = basePath+"/cache/clearCache";
		$.getJSON(url,{"type":cachetype},function(result){
			if(result>0){
				$.messager.alert('信息提示','清除成功!','info');
			}
		});
	}
}
