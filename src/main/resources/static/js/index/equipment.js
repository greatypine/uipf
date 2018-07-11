var equipment;
var equipment_table;
$(function(){
	initData();
});
function initData(){
	equipment = new Myequipment();
	equipment.initCompant();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$("#buyUser").combobox({
		url:content+'/common/getView?viewname=v_enable_user&id='+user.user.companyid
	});
	$("#manager").combobox({
		url:content+'/common/getView?viewname=v_enable_user&id='+user.user.companyid
	});
	$(".type").combobox({
		url:content+'/common/getView?viewname=v_equipment_type'
	});
	$(".status").combobox({
		url:content+'/common/getView?viewname=v_equipment_status'
	});
}
function Myequipment(){
	this.initCompant = function(){
		equipment_table = $('#equipment_table').datagrid({
		        url: content+"/equipment/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'name',title:'名称',width:"12%",align:'left'},
				        {field:'code',title:'编号',width:"8%",align:'center'},
				        {field:'statusName',title:'状态',width:"5%",align:'center'},
				        {field:'typeName',title:'类型',width:"5%",align:'center'},
				        {field:'factory',title:'设备厂商',width:"12%",align:'left'},
				        {field:'companyName',title:'所属公司',width:"10%",align:'center'},
				        {field:'buyPrice',title:'购买价格',width:"8%",align:'center'},
				        {field:'depreciationLife',title:'折旧年限（月）',width:"8%",align:'center'},
				        {field:'managerName',title:'管理者',width:"8%",align:'center'},
				        {field:'buyUserName',title:'入库人',width:"8%",align:'center'},
				        {field:'buyDate',title:'入库时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'createUser',title:'记录人',width:"8%",align:'center'},
				        {field:'createTime',title:'记录时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'updateUser',title:'修改人',width:"8%",align:'center'},
				        {field:'updateTime',title:'修改时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
				onDblClickRow:function(index,row){
					$("#equipmentdlg").dialog("open").dialog("center").dialog("setTitle","更新产品");
					cu.initClearCombobox("status");
					cu.initClearCombobox("type");
					cu.initClearCombobox("manager");
					cu.initClearCombobox("buyUser");
					$("#equipmentdlg-fm").form("clear").form("load",row);
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager")?$("#querycompanyid").combobox("getValue"):null;
		var name = $("#queryname").textbox("getValue");
		var status = ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue");
		var type = ($("#querytype").combobox("getValue")<0)?null:$("#querytype").combobox("getValue");
		var params = {};
		if(name!="") {params.name = name;}
		params.status = status;
		if(type!="") params.type = type;
		equipment_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("equipmentform").serializeJson();
	};
	this.clearForm = function(){
		$('#equipmentform').form('clear');
	};
	this.add = function(){
		$("#equipmentdlg-fm").form("clear");
		cu.initClearCombobox("status");
		cu.initClearCombobox("type");
		cu.initClearCombobox("manager");
		cu.initClearCombobox("buyUser");
		$("#equipmentdlg").dialog("open").dialog("center").dialog("setTitle","添加设备");
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.complate = function(){
		$('#equipmentdlg-fm').form('submit',{
			url:content+"/equipment/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#equipmentdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#equipmentdlg').dialog('close');
					$('#equipment_table').datagrid('reload');
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
		var row=$('#equipment_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						type:"POST",
						url : content+"/equipment/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#equipment_table').datagrid('reload');
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
		var row=$('#equipment_table').datagrid('getSelected');
		if(row){
			equipment.initClearCombobox("status");
			$("#modifyequipmentdlg").dialog("open").dialog("center").dialog("setTitle","产品入库");
			row.equipment = 0;
			$("#doappend").show();
			$("#dodelivery").hide();
			$("#modifyequipmentdlg-fm").form("clear").form("load",row);
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.delivery = function(){
		var row=$('#equipment_table').datagrid('getSelected');
		if(row){
			equipment.initClearCombobox("status");
			$("#modifyequipmentdlg").dialog("open").dialog("center").dialog("setTitle","产品出库");
			row.equipment = 0;
			$("#doappend").hide();
			$("#dodelivery").show();
			$("#modifyequipmentdlg-fm").form("clear").form("load",row);
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.doAppend = function(){
		$('#modifyequipmentdlg-fm').form('submit',{
			url:content+"/equipment/appendequipment",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				var data = eval('(' + result + ')');
				if(data.status){
					$('#modifyequipmentdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#modifyequipmentdlg').dialog('close');
					$('#equipment_table').datagrid('reload');
				}else{
					$.messager.alert('提示',data.status,'warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.doDelivery = function(){
		$('#modifyequipmentdlg-fm').form('submit',{
			url:content+"/equipment/deliveryequipment",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				var data = eval('(' + result + ')');
				if(data.status){
					$('#modifyequipmentdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#modifyequipmentdlg').dialog('close');
					$('#equipment_table').datagrid('reload');
				}else{
					$.messager.alert('提示',data.mess,'warning');
					$.messager.progress('close');
				}
			}
		});
	};
}
