var company;
var company_table;
$(function(){
	initData();
});
function initData(){
	company = new Mycompany();
	company.initCompant();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
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
}
function Mycompany(){
	this.initCompant = function(){
		company_table = $('#company_table').datagrid({
		        url: content+"/company/queryList",
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
				        {field:'code',title:'编号',width:"10%"},
				        {field:'status',title:'状态',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="33CC33">关闭</font>'
					        }else if(val==1){
					        	return '<font color="FF0033">正常</font>'
					        }
				        }},
				        {field:'address',title:'公司地址',width:"19%",align:'left'},
				        {field:'createUser',title:'创建人',width:"10%",align:'center'},
				        {field:'createTime',title:'创建时间',width:"15%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'updateUser',title:'修改人',width:"10%",align:'center'},
				        {field:'updateTime',title:'修改时间',width:"15%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
		        view: detailview,
			    detailFormatter:function(index,row){
			    	return row.remark;
			    },
			    onCollapseRow:function(index,row){
		        	 $("#company_table").datagrid("selectRow", index);
		        },
			    onExpandRow: function(index,row){
			    	$("#company_table").datagrid("selectRow", index);
			    },
				onDblClickRow:function(index,row){
					$("#companydlg").dialog("open").dialog("center").dialog("setTitle","更新产品");
					row.status = row.status==true?1:0;
					$("#companydlg-fm").form("clear").form("load",row);
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var name = $("#queryname").textbox("getValue");
		var params = {};
		if(name!="") {params.name = name;}
		company_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("companyform").serializeJson();
	};
	this.clearForm = function(){
		$('#companyform').form('clear');
	};
	this.add = function(){
		$("#companydlg-fm").form("clear");
		company.initClearCombobox("status");
		$("#companydlg").dialog("open").dialog("center").dialog("setTitle","添加产品");
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.complate = function(){
		$('#companydlg-fm').form('submit',{
			url:content+"/company/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#companydlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#companydlg').dialog('close');
					$('#company_table').datagrid('reload');
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
		var row=$('#company_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						type:"POST",
						url : content+"/company/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#company_table').datagrid('reload');
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
