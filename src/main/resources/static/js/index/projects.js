var project;
var cu;
var project_table;
$(function(){
	initData();
});
function initData(){
	project = new MyProject();
	cu = new CommonUtils();
	project.initCompant();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$(".projectType").combobox({
		url:content+'/common/getView?viewname=v_project_type&id='+user.user.companyid
	});
}
function MyProject(){
	this.initCompant = function(){
		project_table = $('#project_table').datagrid({
		        url: content+"/project/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'projectName',title:'名称',width:"18%"},
				        {field:'status',title:'状态',width:"5%",align:'center',formatter:function(val,row){
				        	if(val==0){
					        	return '<font color="FF0033">关闭</font>'
					        }else{
					        	return '<font color="33CC33">开启</font>'
					        }
				        }},
				        {field:'imageUrl',title:'商品地址',width:"14%",align:'left',hidden:true},
				        {field:'companyName',title:'所属公司',width:"10%",align:'center'},
				        {field:'money',title:'金额',width:"8%",align:'center'},
				        {field:'discount',title:'折扣',width:"5%",align:'center',formatter:function(val,row){
				        	if(val=="" || val==null || val=="null") val = 0;
				        	if(Number(val)==1) return "没折扣";
				        	return Number(val)*100+"折"
				        }},
				        {field:'projectTypeName',title:'消费类型',width:"10%",align:'center'},
				        {field:'projectNums',title:'次数',width:"10%",align:'center'},
				        {field:'deadline',title:'期限（月）',width:"10%",align:'center'},
				        {field:'companyId',title:'数据库',width:"5%",align:'center',hidden:true},
				        {field:'createuser',title:'创建人',width:"8%",align:'center'},
				        {field:'createtime',title:'创建时间',width:"13%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
		        view: detailview,
			    detailFormatter:function(index,row){
			    	return row.remark;
			    },
			    onCollapseRow:function(index,row){
		        	 $("#project_table").datagrid("selectRow", index);
		        },
			    onExpandRow: function(index,row){
			    	$("#project_table").datagrid("selectRow", index);
			    },
				onDblClickRow:function(index,row){
					$("#projectdlg").dialog("open").dialog("center").dialog("setTitle","更新产品");
					row.status = row.status==true?1:0;
					$("#projectdlg-fm").form("clear").form("load",row);
				},
				onSelect:function(index,row){
				
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager,h_option")?$("#querycompanyid").combobox("getValue"):null;
		var name = $("#queryname").val();
		var status = ($("#querystatus").combobox("getValue")<0)?null:$("#querystatus").combobox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var params = {};
		if(name!="") {params.projectName = name;}
		params.status = status;
		if(createTime!="" && createTime!=null) {params.createtime = createTime;}
		params.companyId = companyid;
		project_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("projectform").serializeJson();
	};
	this.clearForm = function(){
		$('#projectform').form('clear');
	};
	this.add = function(){
		$("#projectdlg-fm").form("clear");
		project.initClearCombobox("status");
		project.initClearCombobox("companyId");
		$("#projectdlg").dialog("open").dialog("center").dialog("setTitle","添加产品");
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.complate = function(){
		$('#projectdlg-fm').form('submit',{
			url:content+"/project/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#projectdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#projectdlg').dialog('close');
					$('#project_table').datagrid('reload');
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
		var row=$('#project_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:row.id},
						url : content+"/project/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#project_table').datagrid('reload');
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
