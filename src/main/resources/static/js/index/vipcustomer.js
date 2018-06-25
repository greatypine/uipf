var systemvipcustomer;
var systemvipcustomer_table;
$(function(){
	initData();
});
function initData(){
	systemvipcustomer = new systemvipcustomer();
	systemvipcustomer.initCompant();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$("#sex").combobox({
		url:content+'/common/querySex?id='+user.user.companyid
	});
	$("#cityId1").combobox({
		url:content+'/common/getView?viewname=v_city'
	});
	$("#countyId").combobox({
		url:content+'/common/getView?viewname=v_county'
	});
	$("#provinceId").combobox({
		url:content+'/common/getView?pid=0&viewname=v_province',
		onSelect: function (row) {
			if (row != null) {
				$("#cityId1").combobox({
					url:content+'/common/getView?pid='+row.id+'&viewname=v_city',
					onSelect: function (row) {
						if (row != null) {
							$("#countyId").combobox({
								url:content+'/common/getView?pid='+row.id+'&viewname=v_county'
							});
						}
					}
				});
			}
		}
	});
	$(".status").combobox({
		url:content+'/common/getView?id='+user.user.companyid+"&viewname=v_vip_customer_status"
	});
}
function systemvipcustomer(){
	this.initCompant = function(){
		systemvipcustomer_table = $('#systemvipcustomer_table').datagrid({
		        url: content+"/vipcustomer/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        pageSize: 30,
		        pageList: [30, 40, 50],
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'customerName',title:'用户名称',width:"7%",align:'center'},
				        {field:'customerPhone',title:'电话号码',width:"7%",align:'center'},
				        {field:'statusName',title:'状态',width:"5%",align:'center'},
				        {field:'email',title:'邮件地址',width:"10%"},
				        {field:'brithday',title:'生日',width:"8%",align:'center'},
				        {field:'sexName',title:'性别',width:"4%",align:'center'},
				        {field:'alladdress',title:'地址',width:"15%"},
				        {field:'actualAmount',title:'实际金额',width:"6%",align:'center',formatter:function(val,row){
				        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
				        }},
				        {field:'giveAmount',title:'赠送金额',width:"6%",align:'center',formatter:function(val,row){
				        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
				        }},
				        {field:'createUser',title:'创建人',width:"6%",align:'center'},
				        {field:'createTime',title:'创建时间',width:"10%",align:'center'},
				        {field:'updateUser',title:'更新人',width:"6%",align:'center'},
				        {field:'updateTime',title:'更新时间',width:"10%",align:'center'}
		        ]],
				onDblClickRow:function(index,row){
					$("#systemvipcustomerdlg").dialog("open").dialog("center").dialog("setTitle","更新客户信息");
					cu.initClearCombobox("provinceId");
					cu.initClearCombobox("cityId1");
					cu.initClearCombobox("countyId");
					cu.initClearCombobox("status");
					cu.initClearCombobox("sex");
					$("#systemvipcustomerdlg-fm").form("clear").form("load",row);
				},
				onSelect:function(index,row){
				
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var params = {};
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager")?$("#querycompanyid").combobox("getValue"):null;
		var customerName = $("#queryname").textbox("getValue");
		var customerPhone = $("#queryphone").textbox("getValue");
		var status = ($("#querystatus").combobox('getValue')<0)?null:$("#querystatus").combobox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var endTime = ($("#queryendTime").datebox('getValue')=="")?null:$("#queryendTime").datebox('getValue');
		if(customerName!="") {params.customerName = customerName;}
		if(customerPhone!="") {params.customerPhone = customerPhone;}
		params.companyId = companyid;
		params.status = status;
		if(createTime!="" && createTime!=null) {params.createTime = createTime;}
		if(endTime!="" && endTime!=null) {params.endtime = endTime;}
		systemvipcustomer_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("systemvipcustomerform").serializeJson();
	};
	this.clearForm = function(){
		$('#systemvipcustomerform').form('clear');
	};
	this.add = function(){
		$("#systemvipcustomerdlg").dialog("open").dialog("center").dialog("setTitle","添加systemvipcustomer任务");
		$("#systemvipcustomerdlg-fm").form("clear");
		cu.initClearCombobox("provinceId");
		cu.initClearCombobox("cityId1");
		cu.initClearCombobox("countyId");
		cu.initClearCombobox("status");
		cu.initClearCombobox("sex");
	};
	this.complate = function(){
		$('#systemvipcustomerdlg-fm').form('submit',{
			url:content+"/vipcustomer/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result || result=="true"){
					$('#systemvipcustomerdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#systemvipcustomerdlg').dialog('close');
					$('#systemvipcustomer_table').datagrid('reload');
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
		var row=$('#systemvipcustomer_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						type:"POST",
						data :{id:row.id},
						url : content+"/vipcustomer/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#systemvipcustomer_table').datagrid('reload');
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
		$("#systemvipcustomerform").form("clear");
	}
}
