var inventory;
var cu;
var inventory_table;
$(function(){
	initData();
});
function initData(){
	inventory = new Myinventory();
	cu = new CommonUtils();
	inventory.initCompant();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$("#append").linkbutton('disable');
	$("#delivery").linkbutton('disable');
}
function Myinventory(){
	this.initCompant = function(){
		inventory_table = $('#inventory_table').datagrid({
		        url: content+"/inventory/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'name',title:'名称',width:"15%"},
				        {field:'status',title:'状态',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="33CC33">正常</font>'
					        }else if(val==99){
					        	return '<font color="FF0033">下架</font>'
					        }
				        }},
				        {field:'factory',title:'商品厂商',width:"14%",align:'left'},
				        {field:'companyName',title:'所属公司',width:"10%",align:'center'},
				        {field:'inventory',title:'库存数',width:"8%",align:'center'},
				        {field:'price',title:'单价',width:"8%",align:'center'},
				        {field:'createuser',title:'入库人',width:"8%",align:'center'},
				        {field:'createtime',title:'入库时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'updateuser',title:'修改人',width:"8%",align:'center'},
				        {field:'updatetime',title:'修改时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
		        view: detailview,
			    detailFormatter:function(index,row){
			    	return row.remark;
			    },
			    onCollapseRow:function(index,row){
		        	 $("#inventory_table").datagrid("selectRow", index);
		        },
			    onExpandRow: function(index,row){
			    	$("#inventory_table").datagrid("selectRow", index);
			    },
				onDblClickRow:function(index,row){
					$("#inventorydlg").dialog("open").dialog("center").dialog("setTitle","更新产品");
					row.status = row.status==true?1:0;
					$("#inventorydlg-fm").form("clear").form("load",row);
					$("#iuinfo").hide();
				},
				onSelect:function(index,row){
					if(row.status==0){
						$("#append").linkbutton('enable');
						$("#delivery").linkbutton('enable');
					}
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager")?$("#querycompanyid").combobox("getValue"):null;
		var name = $("#queryname").val();
		var status = ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var params = {};
		if(name!="") {params.inventoryName = name;}
		params.status = status;
		if(createTime!="" && createTime!=null) {params.createtime = createTime;}
		params.companyid = companyid;
		inventory_table.datagrid("load",params);
		$("#append").linkbutton('disable');
		$("#delivery").linkbutton('disable');
	};
	this.getParams = function(){
		return $("inventoryform").serializeJson();
	};
	this.clearForm = function(){
		$('#inventoryform').form('clear');
	};
	this.add = function(){
		$("#inventorydlg-fm").form("clear");
		inventory.initClearCombobox("status");
		$("#inventorydlg").dialog("open").dialog("center").dialog("setTitle","添加产品");
		$("#iuinfo").show();
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.complate = function(){
		$('#inventorydlg-fm').form('submit',{
			url:content+"/inventory/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#inventorydlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#inventorydlg').dialog('close');
					$('#inventory_table').datagrid('reload');
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
		var row=$('#inventory_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						type:"POST",
						url : content+"/inventory/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#inventory_table').datagrid('reload');
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
	this.append = function(){
		var row=$('#inventory_table').datagrid('getSelected');
		if(row){
			inventory.initClearCombobox("status");
			$("#modifyInventorydlg").dialog("open").dialog("center").dialog("setTitle","产品入库");
			row.inventory = 0;
			$("#doappend").show();
			$("#dodelivery").hide();
			$("#modifyInventorydlg-fm").form("clear").form("load",row);
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.delivery = function(){
		var row=$('#inventory_table').datagrid('getSelected');
		if(row){
			inventory.initClearCombobox("status");
			$("#modifyInventorydlg").dialog("open").dialog("center").dialog("setTitle","产品出库");
			row.inventory = 0;
			$("#doappend").hide();
			$("#dodelivery").show();
			$("#modifyInventorydlg-fm").form("clear").form("load",row);
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.doAppend = function(){
		$('#modifyInventorydlg-fm').form('submit',{
			url:content+"/inventory/appendInventory",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				var data = eval('(' + result + ')');
				if(data.status){
					$('#modifyInventorydlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#modifyInventorydlg').dialog('close');
					$('#inventory_table').datagrid('reload');
				}else{
					$.messager.alert('提示',data.status,'warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.doDelivery = function(){
		$('#modifyInventorydlg-fm').form('submit',{
			url:content+"/inventory/deliveryInventory",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				var data = eval('(' + result + ')');
				if(data.status){
					$('#modifyInventorydlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#modifyInventorydlg').dialog('close');
					$('#inventory_table').datagrid('reload');
				}else{
					$.messager.alert('提示',data.mess,'warning');
					$.messager.progress('close');
				}
			}
		});
	};
}
