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
				        {field:'name',title:'名称',width:"9%",align:'left'},
				        {field:'code',title:'编号',width:"6%",align:'center'},
				        {field:'statusName',title:'状态',width:"4%",align:'center'},
				        {field:'typeName',title:'类型',width:"4%",align:'center'},
				        {field:'factory',title:'设备厂商',width:"10%",align:'left'},
				        {field:'companyName',title:'所属公司',width:"8%",align:'center'},
				        {field:'buyPrice',title:'购买价格(元)',width:"8%",align:'center',formatter:function(val,row){
				        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
				        }},
				        {field:'depreciationLife',title:'折旧年限（月）',width:"7%",align:'center'},
				        {field:'managerName',title:'管理者',width:"6%",align:'center'},
				        {field:'buyUserName',title:'入库人',width:"6%",align:'center'},
				        {field:'buyDate',title:'入库时间',width:"9%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'createUser',title:'记录人',width:"6%",align:'center'},
				        {field:'create_time',title:'记录时间',width:"9%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'updateUser',title:'修改人',width:"6%",align:'center'},
				        {field:'update_time',title:'修改时间',width:"9%",align:'center',formatter:function(val,row){
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
					var params = equipment.getParams();
					equipment.calculateAmount(params);
				}
			});
	};
	this.query = function(){
		var params = equipment.getParams();
		equipment_table.datagrid("load",params);
	};
	this.sumPrice = function(data){
		$('#equipment_table').datagrid("getPanel").panel("setTitle","<span style='margin-left:0.5%'></span>设备总金额：<b style='color: red;'>￥"+data.total_amount.toFixed(2)+"</b>元。&nbsp;&nbsp;&nbsp;实际总金额：<b style='color: green;'>￥"+data.actual_total_amount.toFixed(2)+"</b>元"); 
	};
	this.calculateAmount = function(params){
		$.ajax({
			type:"POST",
			dataType:"json",
			data :params,
			url : content+"/equipment/queryAmountSum",
			error : function(data) {
				$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
				return false;
			},
			success : function(data) {
				equipment.sumPrice(data);
			}
		});
	};
	this.getParams = function(){
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager")?$("#querycompanyid").combobox("getValue"):null;
		var name = $("#queryname").textbox("getValue");
		var status = ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue");
		var type = ($("#querytype").combobox("getValue")<0)?null:$("#querytype").combobox("getValue");
		var params = {};
		if(name!="") {params.name = name;}
		params.status = status;
		if(type!="") params.type = type;
		return params;
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
}
