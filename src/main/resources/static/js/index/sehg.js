var sehg;
var cu;
var sehg_table;
var flag = true;
$(function(){
	initData();
});
function initData(){
	sehg = new SehgBean();
	cu = new CommonUtils();
	sehg.initCompant();
}
function SehgBean(){
	this.initCompant = function(){
		sehg_table = $('#sehg_table').datagrid({
		        url: content+"/score_exchange/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'userName',title:'用户名称',width:"7%",align:'center'},
				        {field:'nickAme',title:'昵称',width:"7%",align:'center'},
				        {field:'mobilePhone',title:'电话号码',width:"7%",align:'center'},
				        {field:'email',title:'邮件地址',width:"10%"},
				        {field:'age',title:'年龄',width:"4%",align:'center'},
				        {field:'sex',title:'性别',width:"4%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">女</font>'
					        }else{
					        	return '<font color="33CC33">男</font>'
					        }
				        }},
				        {field:'payPrice',title:'支付金额',width:"12%"},
				        {field:'recomCode',title:'推荐码',width:"5%",align:'center',hidden:true},
				        {field:'buycode',title:'兑换码',width:"5%",align:'center'},
				        {field:'birthday',title:'客户生日',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'orderCreateTime',title:'下单时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'createTime',title:'兑换时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'createUser',title:'兑换人',width:"10%",align:'center'}
		        ]],
				onDblClickRow:function(index,row){
				},
				onSelect:function(index,row){
				
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var querymobilePhone = $("#querymobilePhone").val();
		var querybuycode = $("#querybuycode").val();
		var params = {"mobilePhone":querymobilePhone,"buycode":querybuycode};
		sehg_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("sehgform").serializeJson();
	};
	this.clearForm = function(){
		$('#sehgform').form('clear');
	};
	this.exchange = function(){
		$("#sehgdlg").dialog("open").dialog("center").dialog("setTitle","兑换操作");
		$("#sehgdlg-fm").form("clear");
	};
	this.complate = function(){
		$('#basecomplate').linkbutton('disable');
		var username = $("#username").numberbox("getValue");
		var buycode = $("#buycode").textbox("getValue");
		if(username==""){$.messager.alert('提示信息','手机号码不能为空!','error');return ;}
		if(buycode==""){$.messager.alert('提示信息','兑换码不能为空!','error');return ;}
		var params = {"buycode":buycode,"username":username};
		$.ajax({
			type:"POST",
			data :params,
			url : content+"/score_exchange/saveOrUpdate",
			error : function(data) {
				$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
				flag = true;
				return false;
			},
			success : function(data) {
//				var data = JSON.parse(result); 
				if(data.status){
					$('#sehgdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#sehgdlg').dialog('close');
					$('#sehg_table').datagrid('reload');
				}else{
					$.messager.alert('提示',data.rs,'warning');
					$.messager.progress('close');
				}
				$('#basecomplate').linkbutton('enable');
			}
		});
		
//		$('#sehgdlg-fm').form('submit',{
//			url:content+"/score_exchange/saveOrUpdate",
//			onSubmit:function(){
//				return $(this).form('enableValidation').form('validate');
//			},
//			success: function(result){
//				var data = JSON.parse(result); 
//				if(data.status){
//					$('#sehgdlg-fm').form('clear');
//					$.messager.alert('提示','操作成功');
//					$.messager.progress('close');
//					$('#sehgdlg').dialog('close');
//					$('#sehg_table').datagrid('reload');
//				}else{
//					$.messager.alert('提示',data.rs,'warning');
//					$.messager.progress('close');
//				}
//			}
//		});
	};
	this.onDateDiffSelect = function(date){
		var day = CU.DateDiff(date,new Date());
		$("#diffDay").textbox("setValue",day);
	};
	this.remove = function(){
		var row=$('#sehg_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						type:"POST",
						data :{id:row.id},
						url : content+"/score_exchange/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#sehg_table').datagrid('reload');
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
