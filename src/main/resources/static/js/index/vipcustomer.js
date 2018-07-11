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
	        queryParams:{
			},
	        columns:[[
	        	{field:'id',title:'编号',hidden:true},
		        {field:'customerName',title:'用户名称',width:"7%",align:'center'},
		        {field:'customerPhone',title:'电话号码',width:"7%",align:'center'},
		        {field:'statusName',title:'状态',width:"5%",align:'center'},
		        {field:'email',title:'邮件地址',width:"12%"},
		        {field:'brithday',title:'生日',width:"8%",align:'center'},
		        {field:'sexName',title:'性别',width:"4%",align:'center'},
		        {field:'alladdress',title:'地址',width:"11%"},
		        {field:'actualAmount',title:'实际金额',width:"6%",align:'center',formatter:function(val,row){
		        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
		        }},
		        {field:'giveAmount',title:'赠送金额',width:"6%",align:'center',formatter:function(val,row){
		        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
		        }},
		        {field:'comments',title:'留言',width:"6%",align:'center',
		        	formatter:function(value, row, index){
	            	return '<a id="'+row.id+'" href="javascripte:void(0)" onClick="systemvipcustomer.queryComments(this.id)" class="productslinkbtn easyui-linkbutton" data-options="plain:true,iconCls:\'icon-more\'">留言</a>';
	            }},
		        {field:'createUser',title:'创建人',width:"6%",align:'center'},
		        {field:'createTime',title:'创建时间',width:"11%",align:'center'},
		        {field:'updateTime',title:'更新时间',width:"11%",align:'center'}
	        ]],
	        view: detailview,
	        collapsible:false,
		    detailFormatter:function(index,row){
		    	return '<div style="padding:2px"><table id="ccac_table-' + index + '"></table></div>'; 
		    },
		    onCollapseRow:function(index,row){
		    	$('#systemvipcustomer_table').datagrid("unselectRow", index);
	        	$('#ccac_table-'+index).datagrid("unselectAll");
            	$('#ccac_table-'+index).datagrid("uncheckAll");
	        },
		    onExpandRow: function(index,row){
	        	$("#systemvipcustomer_table").datagrid("selectRow", index);
	            $('#ccac_table-'+index).datagrid({
	            	url: content+"/ccac/queryCustomerList",
	            	fitColumns:true,
	                singleSelect:true,
	                rownumbers:true,
	                pagination : true,
	                pageSize: 10,
	                pageList: [10, 20],
	                height:'auto',
			        loadMsg:'正在加载，请稍后...',
			        queryParams:{
			        	phonenumb:row.customerPhone
					},
			        columns:[[
			        	 	{field:'customername',title:'就诊人',width:"5%",align:'center'},
					        {field:'statusName',title:'结算状态',width:"5%",align:'center'},
					        {field:'counsolerName',title:'咨询师',width:"5%",align:'center'},
					        {field:'therapeutistName',title:'治疗师',width:"5%",align:'center'},
					        {field:'chuFuZhen',title:'初复诊',width:"5%",align:'center',formatter:function(val,row){
					        	if(val==0){
						        	return '<font color="FF0033">初诊</font>'
						        }else{
						        	return '<font color="33CC33">复诊</font>'
						        }
					        }},
					        {field:'typeName',title:'结算类型',width:"5%",align:'center'},
					        {field:'totalConsumptonAmount',title:'总消费金额',width:"7%",align:'center',formatter:function(val,row){
					        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
					        }},
					        {field:'remindtime',title:'回访时间',width:"7%",align:'center'},
					        {field:'subscribeName',title:'预约人',width:"6%",align:'center'},
					        {field:'createTime',title:'接诊时间',width:"11%",align:'center',formatter:function(val,row){
					        	return CU.DateTimeFormatter(val,1);
					        }},
//					        {field:'updateUser',title:'更新人',width:"4%",align:'center'},
					        {field:'updateTime',title:'完成时间',width:"11%",align:'center',formatter:function(val,row){
					        	return CU.DateTimeFormatter(val,1);
					        }},
					        {field:'products',title:'产品',align:'center',width:"8%",
					        	formatter:function(value, row, index){
				            	return '<a id="'+row.id+'" href="javascripte:void(0)" onClick="systemvipcustomer.queryProducts(this.id)" class="productslinkbtn easyui-linkbutton" data-options="plain:true,iconCls:\'icon-more\'">产品列表</a>';
				            }},
					        {field:'remark',title:'描述',width:"19%",align:'center'}
			        ]],
					onSelect:function(index,row){
					},
					onLoadSuccess:function(data){
						$(".productslinkbtn").linkbutton();
						setTimeout(function(){
			                $('#systemvipcustomer_table').datagrid('fixDetailRowHeight',index); 
			            },0);
					}
	          });
		    },
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
			onLoadSuccess:function(data){
				$(".productslinkbtn").linkbutton();
			}
		});
	};
	
	this.queryProducts = function(id){
		if(id=="" || id==null){
			$.messager.alert('提示','没有相应的产品！','warning');
			return ;
		}
		var params = {"customerId":id};
		systemvipcustomer.initProjectDg(params);
		$("#productsdlg").dialog("open").dialog("center").dialog("setTitle","查看产品列表");
	};
	
	this.initProjectDg = function(params){
		$('#project_dg').datagrid({
			url: content+"/ccac/queryCustomerAmountList",
			fitColumns:true,
	        singleSelect:true,
	        rownumbers:true,
	        pageSize: 30,
	        pageList: [30, 40, 50],
	        loadMsg:'正在加载，请稍后...',
	        queryParams:params,
	        columns:[[
	        		{field:'projectName',title:'套餐名称',width:"14%",align:'center'},
	        		{field:'projectAmount',title:'套餐金额',width:"8%",align:'center',formatter:function(val,row){
			        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
			        }},
			        {field:'discount',title:'折扣',width:"7%",align:'center',formatter:function(val,row){
			        	return val*100+"%";
			        }},
			        {field:'arrears',title:'欠款金额',width:"7%",align:'center',formatter:function(val,row){
			        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
			        }},
			        {field:'repayment',title:'还款金额',width:"7%",align:'center',formatter:function(val,row){
			        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
			        }},
			        {field:'movePayment',title:'移动支付',width:"7%",align:'center',formatter:function(val,row){
			        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
			        }},
			        {field:'cashIncome',title:'现金收入',width:"7%",align:'center',formatter:function(val,row){
			        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
			        }},
			        {field:'creditCashIncome',title:'刷卡收入',width:"7%",align:'center',formatter:function(val,row){
			        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
			        }},
			        {field:'totalAmount',title:'总金额',width:"10%",align:'center',formatter:function(val,row){
			        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
			        }},
			        {field:'remark',title:'说明',width:"25%",align:'center'}
	        ]],
	        view: detailview,
	        collapsible:false,
		    detailFormatter:function(index,row){
		    	return '<div style="padding:2px"><table id="product_table-' + index + '"></table></div>'; 
		    },
		    onCollapseRow:function(index,row){
	        },
		    onExpandRow: function(index,row){
	            $('#product_table-'+index).datagrid({
	            	url: content+"/consumptonProject/queryList",
	            	fitColumns:true,
	                singleSelect:true,
	                rownumbers:true,
	                height:'auto',
			        loadMsg:'正在加载，请稍后...',
			        queryParams:{
			        	consumptonAmountId:row.id,
			    		projectId:row.project_id
					},
			        columns:[[
			        	{field:'name',width:"40%",align:'center',title:"产品"},
		        	 	{field:'numbs',title:'数量',width:"29%",align:'center',editor:{type:'numberbox',options:{precision:2}}},
		        	 	{field:'units',width:"30%",align:'center',title:"单位"}
			        ]],
					onLoadSuccess:function(data){
						setTimeout(function(){
			                $('#project_dg').datagrid('fixDetailRowHeight',index); 
			            },0);
					}
	          });
		    },
			onDblClickRow:function(index,row){
			},
			onSelect:function(index,row){
			},
			onLoadSuccess:function(data){
			}
		});
	};
	
	this.queryComments = function(id){
		if(id=="" || id==null){
			$.messager.alert('提示','没有相应的产品！','warning');
			return ;
		}
		var params = {"vipId":id};
		systemvipcustomer.initCommentDg(params);
		$("#commentsdlg").dialog("open").dialog("center").dialog("setTitle","客户留言列表");
	};
	this.initCommentDg = function(params){
		$("#comments_dg").datagrid({
			fitColumns:true,
	        singleSelect:true,
	        rownumbers:true,
	        pageSize:5,
	        pageList: [5, 10],
	        loadMsg:'正在加载，请稍后...',
	        collapsible:false,
			url: content+'/customerComment/queryList',
			queryParams:params,
			columns:[[
		        {field:'nickname',width:"10%",align:'center',title:"评论人"},
		        {field:'create_time',width:"15%",align:'center',title:"评论时间",formatter:function(val,row){
		        	return CU.DateTimeFormatter(val,1);
		        }},
	    	 	{field:'remark',width:"75%",align:'left',title:"评论留言"}
	    	]]
		});
	};
	this.addcomment = function(){
		var row = $("#systemvipcustomer_table").datagrid("getSelected");
		if(row){
			$("#customercommentdlg").dialog("open").dialog("center").dialog("setTitle","添加客户信息");
			$("#customercommentdlg-fm").form("clear");
			$("#vip_id").val(row.id);
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.customerCommentComplate =function(){
		$('#customercommentdlg-fm').form('submit',{
			url:content+"/customerComment/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result || result=="true"){
					$('#customercommentdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#customercommentdlg').dialog('close');
					$('#comments_dg').datagrid('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.removecomment = function(){
		var row=$('#comments_dg').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						type:"POST",
						data :{id:row.id},
						url : content+"/customerComment/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#comments_dg').datagrid('reload');
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
		$("#systemvipcustomerdlg").dialog("open").dialog("center").dialog("setTitle","添加客户信息");
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
	this.swipingCard = function(){
		var row=$('#systemvipcustomer_table').datagrid('getSelected');
		if(row){
			var params = {"vipId":row.id};
			systemvipcustomer.initProjectsDg(params);
			$("#projectsdlg").dialog("open").dialog("center").dialog("setTitle","客户项目列表");
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.initProjectsDg = function(){
		$("#projects_dg").datagrid({
			fitColumns:true,
	        singleSelect:true,
	        rownumbers:true,
	        pageSize:5,
	        pageList: [5, 10],
	        loadMsg:'正在加载，请稍后...',
	        collapsible:false,
			url: content+'/customerProject/queryList',
			queryParams:params,
			columns:[[
		        {field:'nickname',width:"10%",align:'center',title:"评论人"},
		        {field:'create_time',width:"15%",align:'center',title:"评论时间",formatter:function(val,row){
		        	return CU.DateTimeFormatter(val,1);
		        }},
	    	 	{field:'remark',width:"75%",align:'left',title:"评论留言"}
	    	]]
		});
	};
}
