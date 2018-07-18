var customerSubscribe;
var customerSubscribe_table;
var amq = org.activemq.Amq;
$(function(){
	initData();
});
function initData(){
	customerSubscribe = new MycustomerSubscribe();
	var st = null;
	if(cu.hasRoles("q_area_shopManager,q_admin,q_receptionist,q_counselor")){
		st = 0;
	}
	var initQueryParams={status:st};
	customerSubscribe.initCompant(initQueryParams);
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$("#rootIn").combobox({
		url:content+'/common/queryRootIn?id='+user.user.companyid
	});
	$(".profession").combobox({
		url:content+'/common/getView?viewname=v_customer_profession'
	});
	$(".sex").combobox({
		url:content+'/common/querySex?id='+user.user.companyid
	});
	$(".counsoler").combobox({
		url:content+'/common/queryCounsoler?id='+user.user.companyid
	});
	if(cu.hasRoles("sadmin,generalManager,h_admin,h_option")){
		$("#subscribeDate").datebox().datebox('calendar').calendar({
			validator: function(date){
				var now = new Date();
				now.setTime(now.getTime()-24*60*60*1000);
				return date>=now;
			}
		});
	}
	amq.init({uri: content+'/amq', logging: true, timeout: 20, clientId:(new Date()).getTime().toString()});
}
function MycustomerSubscribe(){
	this.initCompant = function(params){
		customerSubscribe_table = $('#customerSubscribe_table').datagrid({
		        url: content+"/customerSubscribe/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:params,
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'customerName',title:'客户姓名',width:"10%",align:'center'},
				        {field:'statusName',title:'订单状态',width:"10%",align:'center'},
				        {field:'customerPhone',title:'客户手机',width:"8%",align:'center'},
				        {field:'sex',title:'性别',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">女</font>';
					        }else if(val==1){
					        	return '<font color="33CC33">男</font>';
					        }else {
					        	return '<font color="00CC33">未知</font>';
					        }
				        }},
				        {field:'professionName',title:'客户职业',width:"8%",align:'center'},
				        {field:'rootInName',title:'来源',width:"8%",align:'center'},
				        {field:'companyName',title:'所属公司',width:"10%",align:'center'},
				        {field:'project',title:'预约项目',width:"12%",align:'center'},
				        {field:'subscribeDate',title:'约诊时间',width:"8%",align:'center'},
				        {field:'createUser',title:'创建人',width:"6%",align:'center'},
				        {field:'createTime',title:'预约时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
		        view: detailview,
			    detailFormatter:function(index,row){
			    	return row.remark;
			    },
			    onCollapseRow:function(index,row){
		        	 $("#customerSubscribe_table").datagrid("selectRow", index);
		        },
			    onExpandRow: function(index,row){
			    	$("#customerSubscribe_table").datagrid("selectRow", index);
			    },
				onDblClickRow:function(index,row){
					$("#customerSubscribedlg").dialog("open").dialog("center").dialog("setTitle","预约客户信息");
					if(row.sex==0)row.sexName="女";
					else if(row.sex==1)row.sexName="男";
					else row.sexName="未知";
					if(cu.hasRoles("h_admin,h_option"))cu.initClearCombobox("rootIn");
					cu.initClearCombobox("sex");
					if(cu.hasRoles("h_admin,h_option"))cu.initClearCombobox("companyId");
					if(cu.hasRoles("h_admin,h_option"))cu.initClearCombobox("profession");
					else{
						cu.initClearCombobox("profession1");
					}
					$("#customerSubscribedlg-fm").form("clear").form("load",row);
					if(row.status==1){
						$("#basecomplate").hide();
					}
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
		var phone = $("#queryphone").textbox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var params = {};
		if(name!="") {params.customerName = name;}
		if(phone!="") {params.customerPhone = phone;}
		if(createTime!="" && createTime!=null) {params.createtime = createTime;}
		params.companyId = companyid;
		if(cu.hasRoles("q_area_shopManager,q_admin,q_receptionist,q_counselor")){
			params.status=0;
		}
		customerSubscribe_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("customerSubscribeform").serializeJson();
	};
	this.clearForm = function(){
		$('#customerSubscribeform').form('clear');
	};
	this.add = function(){
		$("#basecomplate").show();
		$("#customerSubscribedlg-fm").form("clear");
		cu.initClearCombobox("rootIn");
		cu.initClearCombobox("sex");
		if(cu.hasRoles("h_admin,h_option"))cu.initClearCombobox("companyId");
		cu.initClearCombobox("profession");
		$("#customerSubscribedlg").dialog("open").dialog("center").dialog("setTitle","添加预约客户信息");
	};
	this.reception = function(){
		var row=$('#customerSubscribe_table').datagrid('getSelected');
		if(row){
			row.customername = row.customerName;
			row.phonenumb = row.customerPhone;
			if(cu.hasRoles("h_admin,h_option"))cu.initClearCombobox("rootIn");
			cu.initClearCombobox("sex");
			if(cu.hasRoles("h_admin,h_option"))cu.initClearCombobox("companyId");
			cu.initClearCombobox("profession1");
			$("#ccacdlg-fm").form("clear").form("load",row);
			$("#ccacdlg").dialog("open").dialog("center").dialog("setTitle","接诊客户");
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.doReception = function(){
		$('#ccacdlg-fm').form('submit',{
			url:content+"/ccac/saveOrUpdateTFMCust",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					var counsoler = $("#counsoler").combobox("getValue");
					var customerName = $("#customername").textbox("getValue");
					var text = $("#counsoler").combobox("getText");
					$.messager.alert('提示','操作成功!请美容师'+text+'在《客户消费维护》页面刷新后继续操作!');
					$.messager.progress('close');
					$('#ccacdlg').dialog('close');
					cu.clearSelected("customerSubscribe_table");
					$('#customerSubscribe_table').datagrid('reload');
					$('#ccacdlg-fm').form('clear');
					cu.sendMessage(amq,"topic://before_subscribe_msg",counsoler+"|"+text+"，你有新顾客需要咨询->姓名："+customerName);
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
//				$('#basecomplate').linkbutton('enable');
			}
		});
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.complate = function(){
		$('#customerSubscribedlg-fm').form('submit',{
			url:content+"/customerSubscribe/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					var id = $("#csid").val();
					var subscribeDate = $("#subscribeDate").datetimebox("getValue");
					var customerPhone = $("#customerPhone").textbox("getValue");
					var customerName = $("#customerName").textbox("getValue");
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#customerSubscribedlg').dialog('close');
					$('#customerSubscribe_table').datagrid('reload');
					if(id=="" || id==null){
						cu.sendMessage(amq,"topic://back_subscribe_msg","后台用户："+user.user.nickname+"，在"+cu.getCurrentDateTime()+"成功预约了新顾客->姓名："+customerName+"，手机号："+customerPhone+",预约到店时间："+subscribeDate+"。");
					}
					$('#customerSubscribedlg-fm').form('clear');
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
		var row=$('#customerSubscribe_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/customerSubscribe/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#customerSubscribe_table').datagrid('reload');
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
