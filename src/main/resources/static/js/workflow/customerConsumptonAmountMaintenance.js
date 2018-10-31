var ccac;
var ccac_table;
var flag = true;
var editIndex = undefined;
var project_dg;
var projects_dg;
var projectlogs_dg;
var icolumns = null;
var iccolumns = null;
if(!cu.isPC()){
	$(".pc-ccacform").remove();
	icolumns = [[
	 	{field:'id',title:'编号',hidden:true},
	 	{field:'customername',title:'客户名称',width:"60px",align:'center'},
	 	{field:'phonenumb',title:'电话号码',width:"100px",align:'center'},
	 	{field:'rootInName',title:'客户来源',width:"90px",align:'center'},
	 	{field:'chuFuZhen',title:'初复诊',width:"60px",align:'center',formatter:function(val,row){
        	if(val==0){
	        	return '<font color="FF0033">初诊</font>';
	        }else{
	        	return '<font color="33CC33">复诊</font>';
	        }
        }},
        {field:'totalConsumptonAmount',title:'总消费金额',width:"80px",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'createTime',title:'接诊时间',width:"120px",align:'center',formatter:function(val,row){
        	return CU.DateTimeFormatter(val,1);
        }},
        {field:'remark',title:'描述',width:"600px",align:'left'}
    ]];
	
	iccolumns = [[
	 	{field:'projectName',title:'套餐名称',width:"80px",align:'left'},
	 	{field:'projectAmount',title:'套餐金额',width:"80px",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'discount',title:'折扣',width:"80px",align:'center',formatter:function(val,row){
        	return val*100+"%";
        }},
        {field:'projectNums',title:'套餐次数',width:"80px",align:'center'},
        {field:'deadline',title:'套餐时长(月)',width:"80px",align:'center'},
	 	{field:'arrears',title:'欠款金额',width:"80px",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
	 	{field:'repayment',title:'还款金额',width:"80px",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'movePayment',title:'移动支付',width:"80px",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'cashIncome',title:'现金收入',width:"80px",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'creditCashIncome',title:'刷卡收入',width:"80px",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'totalAmount',title:'总金额',width:"100px",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }}
      ]];
}else{
	$(".mb-ccacform").remove();
	icolumns = [[
	 	{field:'id',title:'编号',hidden:true},
//	        {field:'companyName',title:'公司名称',width:"7%"},
	 	{field:'customername',title:'客户名称',width:"5%",align:'center'},
	 	{field:'phonenumb',title:'电话号码',width:"6%",align:'center'},
	 	{field:'sexName',title:'性别',width:"3%",align:'center'},
	 	{field:'rootInName',title:'客户来源',width:"5%",align:'center'},
	 	{field:'professionName',title:'客户职业',width:"4%",align:'center'},
	 	{field:'createUser',title:'创建人',width:"4%",align:'center'},
        {field:'statusName',title:'结算状态',width:"4%",align:'center',formatter:function(val,row){
        	if(row.status==0){
	        	return '<font color="FF0033">'+val+'</font>'
	        }else{
	        	return '<font color="33CC33">'+val+'</font>'
	        }
        }},
        {field:'chuFuZhen',title:'初复诊',width:"3%",align:'center',formatter:function(val,row){
        	if(val==0){
        		return '<font color="FF0033">初诊</font>'
        	}else{
        		return '<font color="33CC33">复诊</font>'
        	}
        }},
        {field:'counsolerName',title:'咨询师',width:"4%",align:'center'},
        {field:'therapeutistName',title:'治疗师',width:"4%",align:'center'},
        {field:'treatmentOptionDay',title:'治疗日期',width:"5%",align:'center'},
        {field:'treatmentOptionTimeName',title:'治疗时间段',width:"6%",align:'center'},
        {field:'typeName',title:'结算类型',width:"5%",align:'center'},
        {field:'totalConsumptonAmount',title:'总消费金额',width:"6%",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'remindtime',title:'回访时间',width:"5%",align:'center'},
        {field:'subscribeName',title:'预约人',width:"4%",align:'center'},
        {field:'comments',title:'就诊照片',width:"6%",align:'center',
        	formatter:function(value, row, index){
        	return '<a shiro:hasAnyRoles="sadmin,q_area_shopManager,q_admin,q_option" href="javascripte:void(0)" onClick="ccac.uploadImg('+index+')" class="imageslinkbtn" data-options="plain:true">上传</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javascripte:void(0)" onClick="ccac.viewImages('+index+')" class="imageslinkbtn" data-options="plain:true">预览</a>';
        }},
        {field:'createTime',title:'接诊时间',width:"10%",align:'center',formatter:function(val,row){
        	return CU.DateTimeFormatter(val,1);
        }},
//	        {field:'updateTime',title:'完成时间',width:"10%",align:'center',formatter:function(val,row){
//	        	return CU.DateTimeFormatter(val,1);
//	        }},
        {field:'remark',title:'描述',width:"10%",align:'left'}
        ]];
	iccolumns = [[
	 	{field:'projectName',title:'套餐名称',width:"12%",align:'left'},
	 	{field:'projectAmount',title:'套餐金额',width:"6%",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'discount',title:'折扣',width:"6%",align:'center',formatter:function(val,row){
        	return val*100+"%";
        }},
        {field:'projectNums',title:'套餐次数',width:"6%",align:'center'},
        {field:'deadline',title:'套餐时长(月)',width:"7%",align:'center'},
	 	{field:'arrears',title:'欠款金额',width:"6%",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
	 	{field:'repayment',title:'还款金额',width:"6%",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'movePayment',title:'移动支付',width:"6%",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'cashIncome',title:'现金收入',width:"6%",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'creditCashIncome',title:'刷卡收入',width:"6%",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'totalAmount',title:'总金额',width:"10%",align:'center',formatter:function(val,row){
        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
        }},
        {field:'remark',title:'说明',width:"23%",align:'left'}
      ]];
	}
$(function(){
	initData();
});
function initData(){
	if(cu.hasRoles("sadmin,q_area_shopManager,generalManager,q_admin")){
		$('#remindtime').datebox().datebox('calendar').calendar({
			validator: function(date){
				var now = new Date();
				return date>now;
			}
		});
		if(cu.hasRoles("q_area_shopManager,generalManager")){
			$('#querycreateTime').datebox().datebox('calendar').calendar({
				validator: function(date){
					var disableday = new Date(cu.month_add(new Date(),-12));
					return disableday<date;
				}
			});
		}else if(cu.hasRoles("q_admin")){
			$('#querycreateTime').datebox().datebox('calendar').calendar({
				validator: function(date){
					var disableday = new Date(cu.month_add(new Date(),-6));
					return disableday<date;
				}
			});
		}
	}
	if(cu.hasRoles("q_receptionist,q_counselor,q_option,query")){
		$('#querycreateTime').datebox().datebox('calendar').calendar({
			validator: function(date){
				var disableday = new Date(cu.date_add(new Date(),-7));
				return disableday<date;
			}
		});
	}
	$("#userid").combobox({
		url:content+'/common/queryCosmetologist?id='+user.user.companyid
	});
	$("#therapeutist").combobox({
		url:content+'/common/queryCosmetologist?id='+user.user.companyid
//		,
//		onSelect: function(row){
//			if (row != null) {
//				var cfz = $("#chuFuZhen").combobox("getValue");
//				var cureTime = $("#cureTime").datebox("getValue");
//				if(cfz==null || cfz==""){
////					$.messager.alert('提示信息','请先填写初复诊!','warning');
//					return ;
//				}
//				if(cureTime==null || cureTime==""){
////					$.messager.alert('提示信息','请先填写治疗日期!','warning');
//					return ;
//				}
//				$("#treatmentTime").combobox({
//					url:content+'/ttti/queryZLSList?userId='+row.id+'&companyid='+user.user.companyid+'&status='+1+"&chuFuZhen="+cfz+"&day="+cureTime
//				});
//			}
//		}
	});

	$('#cureTime').datebox({
		onSelect: function(date){
			var cfz = $("#chuFuZhen").combobox("getValue");
			var therapeutist = $("#therapeutist").combobox("getValue");
			if((cfz!=null && cfz!="")&& (therapeutist!=null && therapeutist!="")){
				$("#treatmentTime").combobox({
					url:content+'/ttti/queryZLSList?userId='+therapeutist+'&companyid='+user.user.companyid+'&status='+1+"&chuFuZhen="+cfz+"&day="+cu.DateTimeFormatter(date,0)
				});
			}
		}
	});
	$('#cureTime').datebox().datebox('calendar').calendar({
		validator: function(date){
			var disableday = new Date(cu.date_add(new Date(),-1));
			return disableday<date;
		}
	});
	$(".projectId").combobox({
		prompt:'输入关键字或首字母拼音（空格则查询所有）',
        required:true,
        mode:'remote',
        url:content+'/project/queryBeanList?companyId='+user.user.companyid,
        editable:true,
        hasDownArrow:false,
        valueField:'id',
        textField:'projectName',
		onSelect:function(record){
			$("#projectAmount").numberbox("setValue",record.money);
			$("#discount").numberbox("setValue",Number(record.discount)*100);
			if(record.projectType==1){
				$("#projectNums").numberbox("setValue",record.projectNums);
				$("#projectNums").textbox('textbox').attr('readonly',false);
				$("#deadline").numberbox("setValue",0);
				$("#deadline").textbox('textbox').attr('readonly',true);
			}else if(record.projectType==2){
				$("#projectNums").numberbox("setValue",0);
				$("#projectNums").textbox('textbox').attr('readonly',true);
				$("#deadline").numberbox("setValue",record.deadline);
				$("#deadline").textbox('textbox').attr('readonly',false);
			}
		},
		onBeforeLoad: function(param){
            if(param == null || param.q == null || param.q.replace(/ /g, '') == ''){
                var value = $(this).combobox('getValue');
                if(value){// 修改的时候才会出现q为空而value不为空
                    param.id = value;
                    return true;
                }
                return false;
            }
        }
	});
	
	$(".counsoler").combobox({
		url:content+'/common/queryCounsoler?id='+user.user.companyid
	});
	$(".sex").combobox({
        url:content+'/common/querySex?id='+user.user.companyid
	});
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$("#inventoryId").combobox({
		prompt:'输入关键字或首字母拼音（空格则查询所有）',
        required:true,
        mode:'remote',
        url:content+'/common/queryProjectInventory?id='+user.user.companyid,
        editable:true,
        hasDownArrow:false,
        valueField:'id',
        textField:'name',
        onBeforeLoad: function(param){
            if(param == null || param.q == null || param.q.replace(/ /g, '') == ''){
                var value = $(this).combobox('getValue');
                if(value){// 修改的时候才会出现q为空而value不为空
                    param.id = value;
                    return true;
                }
                return false;
            }
        }
	});
	$(".rootIn").combobox({
		url:content+'/common/queryRootIn?id='+user.user.companyid
	});
	$("#chuFuZhen").combobox({
		url:content+'/common/getView?viewname=v_chu_fu_zhen',
		onChange:function(newValue, oldValue){
			if(newValue!=oldValue && oldValue!=""){
				$("#therapeutist").combobox("setValue","");
				$("#therapeutist").combobox("setText","");
				$("#treatmentTime").combobox("setValue","");
				$("#treatmentTime").combobox("setText","");
				cu.initClearCombobox("therapeutist");
				$("#treatmentTime").combobox("clear");
			}
		}
	});
	
	$("#profession").combobox({
		url:content+'/common/getView?viewname=v_customer_profession'
	});
	var h = $(document).height();
	if(h>800){
		$("#consumpton_project_list").css("height",'56%');
	}else if(h<800 && h>650){
		$("#consumpton_project_list").css("height",'47%');
	}else if(h<650){
		$("#consumpton_project_list").css("height",'32%');
	}
	ccac = new CustomerConsumptonAmount();
	var starttime = cu.date_add(new Date(),-1);
	$("#querycreateTime").datebox('setValue',starttime);
//	$("#userid").combobox("setValue",user.user.id);
//	$("#userid").combobox("select",user.user.id);
	var params = ccac.getRequestParams();
	ccac.initCompant(params);;
	ccac.initProjectDg({"consumptonAmountId":-1});
	ccac.initProjectsDg({"phonenumb":-1});
	ccac.initProjectLogsDg({"cpId":-1});
};
function CustomerConsumptonAmount(){
	this.initProjectDg = function(params){
		project_dg = $("#project_dg").datagrid({
			fitColumns:true,
	        singleSelect:true,
	        rownumbers:true,
	        pageSize: 5,
	        pageList: [5, 10, 20],
	        loadMsg:'正在加载，请稍后...',
	        collapsible:false,
			url: content+'/consumptonProject/queryList',
			queryParams:params,
			columns:[[
		        {field:'name',width:"40%",align:'center',title:"产品"},
        	 	{field:'numbs',title:'数量',width:"30%",align:'center',editor:{type:'numberbox',options:{precision:2}}},
        	 	{field:'units',width:"30%",align:'center',title:"单位"}
        	]]
		});
	};
	this.initCompant = function(params){
		ccac.index = null;
		ccac_table = $('#ccac_table').datagrid({
		        url: content+"/ccac/queryCustomerList",
		        fitColumns: true, 
		        pageList: [30, 40, 50],
		        loadMsg:'正在加载，请稍后...',
		        queryParams:params,
		        columns:icolumns,
		        view: detailview,
		        collapsible:false,
			    detailFormatter:function(index,row){
			    	return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>'; 
			    },
			    onCollapseRow:function(index,row){
		        	 $("#ccac_table").datagrid("unselectRow", index);
		        	 $('#ddv-'+ccac.index).datagrid("unselectAll");
	            	 $('#ddv-'+ccac.index).datagrid("uncheckAll");
		        	 ccac.index = null;
		        },
			    onExpandRow: function(index,row){
			    	var srow = $("#ccac_table").datagrid("getSelected");
			    	$("#ccac_table").datagrid("selectRow", index);
			    	ccac.index = index;
		            $('#ddv-'+index).datagrid({
		                url:content+"/ccac/queryCustomerAmountList?customerId="+row.id,
		                fitColumns:true,
		                singleSelect:true,
		                rownumbers:true,
		                loadMsg:'正在加载，请稍后...',
		                height:'auto',
		                columns:iccolumns,
		                toolbar: [{
		                    text:'添加消费记录',
		                iconCls: 'icon-add',
		                handler: function(){
		                	if(cu.hasRoles("h_option")){
		                		$.messager.alert('提示','消费订单不可操作！','warning');
		                		return ;
		                	}
		                	$('#ddv-'+ccac.index).datagrid("unselectAll");
		            		$('#ddv-'+ccac.index).datagrid("uncheckAll");
	                		var row = $("#ccac_table").datagrid("getSelected");
		                	if(row.status==1){
		                		$.messager.alert('提示','消费订单已完成！不可再次操作！','warning');
		                		return ;
		                	}else if(row.status==99){
		                		$.messager.alert('提示','消费订单已关闭！不可再次操作！','warning');
		                		return ;
		                	}else{
		                		$("#baseproductcomplate").show();
		                		cu.disableForm("ccacdetaildlg-fm",false);
		                		$("#project_tb .easyui-linkbutton").linkbutton('enable');
		                	}
		                	if(CU.hasPermission("update")){
		                		ccac.customerAmountAdd(index);
		                	}
		                	else $.messager.alert('提示','没有操作权限！','warning');
		                }
		                },'-',{
		                text:'修改消费记录',
		                iconCls: 'icon-edit',
		                handler: function(){
		                	var crow=$('#ddv-'+ccac.index).datagrid('getSelected');
		                	if(!crow){
		                		$.messager.alert('提示','请先选中要操作的数据！','warning');
		                		return ;
		                	}
		                	var row = $("#ccac_table").datagrid("getSelected");
		                	project_dg.datagrid("load",{"consumptonAmountId":crow.id});
					    	$("#consumptonAmountId").val($("#ccacdid").val());
					    	$("#baseproductcomplate").hide();
					    	ccac.customerAmountEdit(crow);
		                	if(row.status==1 || row.status==99 || cu.hasRoles("h_option")){
		                		$("#baseproductcomplate").hide();
		                		cu.disableForm("ccacdetaildlg-fm",true);
		                		$("#project_tb .easyui-linkbutton").linkbutton('disable');
		                	}else{
		                		$("#baseproductcomplate").show();
		                		cu.disableForm("ccacdetaildlg-fm",false);
		                		$("#project_tb .easyui-linkbutton").linkbutton('enable');
		                	}
		                	ccac.customerId = crow.customerId;
		                	if(!CU.hasPermission("update")){
		                		$.messager.alert('提示','没有操作权限！','warning');
		                	}
		                }},'-',{
		                text:'删除消费记录',
		                iconCls: 'icon-remove',
		                handler: function(){
		                	if(cu.hasRoles("h_option")){
		                		$.messager.alert('提示','消费订单不可操作！','warning');
		                		return ;
		                	}
		                	var row = $("#ccac_table").datagrid("getSelected");
		                	if(row.status==1){
		                		$.messager.alert('提示','消费订单已完成！不可再次操作！','warning');
		                		return ;
		                	}
		                	if(row.status==99){
		                		$.messager.alert('提示','消费订单已关闭！不可再次操作！','warning');
		                		return ;
		                	}
		                	if(CU.hasPermission("delete")) ccac.customerAmountRemove(index);
		                	else $.messager.alert('提示','没有操作权限！','warning');
		                }
		                }],
				    onDblClickRow:function(index,row){
				    	project_dg.datagrid("load",{"consumptonAmountId":row.id});
				    	$("#consumptonAmountId").val($("#ccacdid").val());
				    	$("#baseproductcomplate").hide();
				    	var row1 = $("#ccac_table").datagrid("getSelected");
	                	if(row1.status==1 || row1.status==99 || cu.hasRoles("h_option")){
	                		$("#baseproductcomplate").hide();
	                		cu.disableForm("ccacdetaildlg-fm",true);
	                		$("#project_tb .easyui-linkbutton").linkbutton('disable');
	                	}else{
	                		$("#baseproductcomplate").show();
	                		cu.disableForm("ccacdetaildlg-fm",false);
	                		$("#project_tb .easyui-linkbutton").linkbutton('enable');
	                	}
	                	ccac.customerAmountEdit(row);
	                	ccac.customerId = row.customerId;
			        },
			        onSelect:function(index,row){
			        	ccac.customerId = row.customerId;
			        },
			        onLoadSuccess:function(data){
			        	setTimeout(function(){
			                $('#ccac_table').datagrid('fixDetailRowHeight',index); 
			            },0); 
			        } 
			       });
			    },
				onDblClickRow:function(index,row){
					$("#ccacdlg").dialog("open").dialog("center").dialog("setTitle","更新用户信息");
					cu.initClearCombobox("sex");
					cu.initClearCombobox("chuFuZhen");
					cu.initClearCombobox("therapeutist");
					cu.initClearCombobox("rootIn");
					cu.initClearCombobox("profession");
//					cu.initClearCombobox("treatmentTime");
					var st = row.status;
					if(st==1){
						cu.disableForm("ccacdlg-fm",true);
						$("#basecomplate").hide();
					}else if(st==99){
						cu.disableForm("ccacdlg-fm",true);
						$("#basecomplate").hide();
					}else{
						cu.disableForm("ccacdlg-fm",false);
						$("#basecomplate").show();
					}
					$("#ccacdlg-fm").form("clear").form("load",row);
					
					$("#treatmentTime").combobox({
						url:content+'/ttti/queryZLSList?userId='+row.therapeutist+'&companyid='+user.user.companyid+'&status='+1+"&chuFuZhen="+row.chuFuZhen+"&day="+row.treatmentOptionDay,
						onLoadSuccess:function(){
							$("#treatmentTime").combobox("setValue",row.treatmentOptionTime);
							$("#treatmentTime").combobox("setText",row.treatmentOptionTimeName);
						}
					});
				},
				onSelect:function(index,row){
					ccac.index = index;
//					$('#ccac_table').datagrid("clearSelections");
					if(row.status==1){
		        		$("#jiesuanfx").linkbutton('disable');
		        		$("#jiesuanpt").linkbutton('disable');
		        		$("#jiesuanhy").linkbutton('disable');
		        		$("#jiesuansk").linkbutton('enable');
		        		$("#orderback").linkbutton('enable');
		        	}else if(row.status==0){
		        		$("#jiesuanfx").linkbutton('enable');
		        		$("#jiesuanpt").linkbutton('enable');
		        		$("#jiesuanhy").linkbutton('enable');
		        		$("#jiesuansk").linkbutton('disable');
		        		$("#orderback").linkbutton('disable');
		        	}else if(row.status==99){
		        		$("#jiesuanfx").linkbutton('disable');
		        		$("#jiesuanpt").linkbutton('disable');
		        		$("#jiesuanhy").linkbutton('disable');
		        		$("#jiesuansk").linkbutton('disable');
		        		$("#orderback").linkbutton('enable');
		        	}else{
		        		$("#jiesuanfx").linkbutton('disable');
		        		$("#jiesuanpt").linkbutton('disable');
		        		$("#jiesuanhy").linkbutton('disable');
		        		$("#jiesuansk").linkbutton('disable');
		        		$("#orderback").linkbutton('disable');
		        	}
				},
				onLoadSuccess:function(data){
//					$(".imageslinkbtn").linkbutton();
					cu.clearSelected("ccac_table");
					var params = ccac.getRequestParams();
					ccac.calculateAmount(params);
				}
			});
	};
	
	this.sumPrice = function(data){
		$('#ccac_table').datagrid("getPanel").panel("setTitle","<span style='margin-left:0.5%'></span>总消费金额：<b style='color: red;'>￥"+data.total_amount.toFixed(2)+"</b>元"); 
	};
	this.sumPriceC = function(data){
		ccac_table.datagrid('updateRow',{
        	index: ccac.index,
        	row: {
        		totalConsumptonAmount: data.one_total_amount
        	}
        });
	};
	this.query = function(){
		cu.clearSelected("ccac_table");
		var params = ccac.getRequestParams();
		ccac_table.datagrid("load",params);
	};
	this.getRequestParams = function(){
		var params = {};
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager,h_option")?$("#querycompanyid").combobox("getValue"):null;
		var customername = $("#queryname").textbox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="" || $("#querycreateTime").datebox('getValue')=="undefined")?null:$("#querycreateTime").datebox('getValue');
		if(cu.isPC()){
			var customerphone = $("#queryphone").textbox("getValue");
			var endTime = ($("#queryendTime").datebox('getValue')=="")?null:$("#queryendTime").datebox('getValue');
			var rootIn = ($("#queryrootIn").combobox("getValue")<=0)?null:$("#queryrootIn").combobox("getValue")==""?null:$("#queryrootIn").combobox("getValue");
			var therapeutist = ($("#userid").combobox("getValue")<=0)?null:$("#userid").combobox("getValue")==""?null:$("#userid").combobox("getValue");
			var chuFuZhen = ($("#queryChuFuZhen").combobox('getValue')<0)?null:$("#queryChuFuZhen").combobox("getValue")==""?null:$("#queryChuFuZhen").combobox("getValue");
			var status = $("#querystatus").combobox('getValue');
			params.status = status;
			params.rootIn = rootIn;
			params.chuFuZhen = chuFuZhen;
			params.therapeutist = therapeutist;
			params.endtime = endTime;
			params.phonenumb = customerphone;
		}
		params.createtime = createTime;
		params.customername = customername;
		params.companyId = companyid;
		if(status==-1) params.status=null;
		if(params.phonenumb!='' || params.customername!='')  params.createtime=null;
		return params;
	}
	this.getParams = function(){
		return $("ccacform").serializeJson();
	};
	this.clearForm = function(){
		$('#ccacform').form('clear');
	};
	this.add = function(){
		$('#ccac_table').datagrid("unselectAll");
		$("#ccacdlg").dialog("open").dialog("center").dialog("setTitle","添加客户消费记录");
		$("#ccacdlg-fm").form("clear");
		$("#basecomplate").show();
		cu.disableForm("ccacdlg-fm",false);
		cu.initClearCombobox("sex");
		cu.initClearCombobox("chuFuZhen");
		cu.initClearCombobox("therapeutist");
		cu.initClearCombobox("rootIn");
		cu.initClearCombobox("profession");
	};
	this.clear = function(){
		$("#ccacform").form("clear");
		if(cu.hasRoles("q_receptionist,q_counselor,q_option,query")){
			var starttime = cu.date_add(new Date(),-1);
			$("#querycreateTime").datebox('setValue',starttime);
		}
	};
	this.exportData = function(){
		$.messager.confirm('提示信息', '你确认要导出客户信息吗?', function(r){
			if (r){
				var params = ccac.getRequestParams();
				window.location.href = content+"/exportExcel/exportDataList?customername="+params.customername+"&therapeutist="+params.therapeutist+"&status="+params.status+"&chuFuZhen="+params.chuFuZhen+"&createtime="+params.createtime+"&endtime"+params.endTime;
			}
		});
	};
	this.complate = function(){
		$.messager.progress();
		$('#basecomplate').linkbutton('disable');
		$('#ccacdlg-fm').form('submit',{
			url:content+"/ccac/saveOrUpdateCust",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				$.messager.progress('close');
				var data = eval('(' + result + ')');
				if(data!=null && (data.status || data.status=='true')){
					$('#ccacdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$('#ccacdlg').dialog('close');
					cu.clearSelected("ccac_table");
					$('#ccac_table').datagrid('reload');
				}else{
					$.messager.alert('提示',result.mess,'warning');
				}
				$('#basecomplate').linkbutton('enable');
			},error:function(XMLHttpRequest,textStatus,errorThrown){
				$.messager.progress('close');
                $.messager.alert('提示',"操作失败！","error");
                $('#basecomplate').linkbutton('enable');
            }
		});
	};
	this.amountcomplate = function(){
		$.messager.progress();
		$('#baseproductcomplate').linkbutton('disable');
		$('#ccacdetaildlg-fm').form('submit',{
			url:content+"/ccac/saveOrUpdateCustAmount",
			onSubmit:function(){
				var b = $(this).form('enableValidation').form('validate');
				if(b){
					var pid = $("#projectId").combobox("getValue");
					var ptext = $("#projectId").combobox("getText");
					if(pid==ptext){
						$.messager.alert('提示',"请正确选择项目名称！","error");
						$.messager.progress('close');
						$('#baseproductcomplate').linkbutton('enable');
						b = false;
					}
				}
				return b;
			},
			success: function(result){
				$.messager.progress('close');
				if(result!=null && result!=""){
//					$('#ccacdetaildlg-fm').form('clear');
//					cu.disableForm("ccacdetaildlg-fm",false);
					$('#ccac_table').datagrid('reload');
					$("#consumptonAmountId").val(result);
					$("#ccacdid").val(result);
//					$("#baseproductcomplate").hide();
					$.messager.alert('提示','操作成功');
					$('#ccac_table').datagrid('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
				}
				$('#baseproductcomplate').linkbutton('enable');
			},error:function(XMLHttpRequest,textStatus,errorThrown){
				$.messager.progress('close');
                $.messager.alert('提示',"操作失败！","error");
                $('#baseproductcomplate').linkbutton('enable');
            }
		});
	};
	this.fxcommit = function(){
		var row=$('#ccac_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要以《分销结算》此用户信息吗?', function(r){
				if (r){
					$.messager.progress();
					$.ajax({
						async: false,
						type:"POST",
						data :row,
						url : content+"/ccac/commit",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							$.messager.progress('close');
							if(data.status){//成功
								$.messager.alert('提示信息','结算成功!');
								cu.clearSelected("ccac_table");
								$('#ccac_table').datagrid('reload');
							}else{
								$.messager.alert('提示信息','结算失败!','warning');
							}
						},
						error:function(){
							$.messager.progress('close');
			                $.messager.alert('提示',"操作失败！","error");
						}
					});
				}
			});	
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.hycommit = function(){
		var row=$('#ccac_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要以《会员结算》此用户信息吗?', function(r){
				if (r){
					$.messager.progress();
					$.ajax({
						async: false,
						type:"POST",
						data :row,
						url : content+"/ccac/hycommit",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							$.messager.progress('close');
							if(data.status){//成功
								$.messager.alert('提示信息','结算成功!');
								cu.clearSelected("ccac_table");
								$('#ccac_table').datagrid('reload');
							}else{
								$.messager.alert('提示信息',data.mess,'warning');
							}
						},
						error:function(){
							$.messager.progress('close');
			                $.messager.alert('提示',"操作失败！","error");
						}
					});
				}
			});	
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.querySwipingCardCommit = function(){
		$("#projectsdlg").dialog("open").dialog("center").dialog("setTitle","用户套餐列表");
		ccac.querySwipingCardProjects();
	};
	
	this.querySwipingCardProjects = function(){
		var row=$('#ccac_table').datagrid('getSelected');
		if(row){
			$("#querycphone").textbox("setValue",row.phonenumb);
		}
		var params = {"phonenumb":$("#querycphone").textbox("getValue")};
		projects_dg.datagrid("load",params);
		var params1 = {"cpId":-1};
		projectlogs_dg.datagrid("load",params1);
	};
	
	this.initProjectsDg = function(params){
		projects_dg = $('#projects_dg').datagrid({
			url: content+"/customerProject/queryCustomerAmountListByCustomerPhone",
			fitColumns:true,
	        singleSelect:true,
	        rownumbers:true,
	        pageSize: 10,
	        pageList: [10, 20],
	        loadMsg:'正在加载，请稍后...',
	        queryParams:params,
	        columns:[[
	        		{field:'projectName',title:'套餐名称',width:"14%",align:'center'},
	        		{field:'projectTypeName',title:'套餐卡类型',width:"8%",align:'center'},
	        		{field:'totalProjectNums',title:'套餐总次数',width:"10%",align:'center'},
	        		{field:'projectNums',title:'套餐剩余次数',width:"10%",align:'center'},
	        		{field:'create_time',title:'套餐开始时间',width:"12%",align:'center',formatter:function(val,row){
	        			return CU.DateTimeFormatter(val,1);
	        		}},
	        		{field:'deadline',title:'套餐到期时间',width:"14%",align:'center',formatter:function(val,row){
			        	return CU.DateTimeFormatter(val,1);
			        }},
			        {field:'updateUser',title:'上次刷卡人',width:"8%",align:'center'},
			        {field:'update_time',title:'上次刷卡时间',width:"12%",align:'center',formatter:function(val,row){
			        	return CU.DateTimeFormatter(val,1);
			        }},
			        {field:'comments',title:'刷卡消费',width:"10%",align:'center',
			        	formatter:function(value, row, index){
		            	return '<a id="sc_'+row.id+'" href="javascripte:void(0)" onClick="ccac.swipingCardCommit(this.id)" class="projectslinkbtn easyui-linkbutton" data-options="plain:true,iconCls:\'icon-card\'">刷卡消费</a>';
		            }}
	        ]],
			onDblClickRow:function(index,row){
			},
			onSelect:function(index,row){
				var params = {"cpId":row.id};
				projectlogs_dg.datagrid("load",params);
			},
			onLoadSuccess:function(data){
				$(".projectslinkbtn").linkbutton();
			}
		});
	};
	
	this.initProjectLogsDg = function(params){
		projectlogs_dg = $('#projectlogs_dg').datagrid({
			url: content+"/customerProject/queryCustomerProjectLogs",
			fitColumns:true,
	        pageSize: 10,
	        pageList: [10, 20],
	        loadMsg:'正在加载，请稍后...',
	        queryParams:params,
	        columns:[[
		        	{field:'create_user',title:'操作刷卡人',width:"40%",align:'center'},
		        	{field:'create_time',title:'刷卡时间',width:"30%",align:'center',formatter:function(val,row){
		        		return CU.DateTimeFormatter(val,1);
		        	}},
	        		{field:'typeName',title:'类型',width:"30%",align:'center'}
	        ]]
		});
	};
	this.swipingCardCommit = function(id){
		id = id.split("_")[1];
		$.messager.confirm('提示信息', '确认要刷卡结算吗?', function(r){
			if (r){
				$.messager.progress();
				$("#"+id).linkbutton("disable");
				$.ajax({
					async: false,
					type:"POST",
					datType: "JSON",
					data :{"id":id},
					url : content+"/customerProject/swipingCardCommit",
					error : function(data) {
						$.messager.alert('提示信息','服务器连接超时请重试!','error');
						$("#"+id).linkbutton("enable");
						return false;
					},
					success : function(data) {
						$.messager.progress('close');
						$("#"+id).linkbutton("enable");
						if(data.status){//成功
							$.messager.alert('提示信息','刷卡成功!');
							$('#projects_dg').datagrid('reload');
							$('#projectlogs_dg').datagrid('reload');
						}else{
							$.messager.alert('提示信息',data.mess,'warning');
						}
					},
					error:function(){
						$.messager.progress('close');
		                $.messager.alert('提示',"操作失败！","error");
					}
				});
			}
		});
	};
	this.ptcommit = function(){
		var row=$('#ccac_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要以《普通结算》此用户信息吗?', function(r){
				if (r){
					$.messager.progress();
					$.ajax({
						async: false,
						type:"POST",
						data :row,
						url : content+"/ccac/ptcommit",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							$.messager.progress('close');
							if(data.status){//成功
								$.messager.alert('提示信息','结算成功!');
								cu.clearSelected("ccac_table");
								$('#ccac_table').datagrid('reload');
							}else{
								$.messager.alert('提示信息',data.mess,'warning');
							}
						},
						error:function(){
							$.messager.progress('close');
			                $.messager.alert('提示',"操作失败！","error");
						}
					});
				}
			});	
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.remove = function(){
		var row=$('#ccac_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此条消费记录吗?', function(r){
				if (r){
					$.messager.progress();
					$.ajax({
						async: false,
						type:"POST",
						data :{id:row.id},
						url : content+"/ccac/deleteCust",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							$.messager.progress('close');
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								cu.clearSelected("ccac_table");
								$('#ccac_table').datagrid('reload');
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						},
						error:function(){
							$.messager.progress('close');
			                $.messager.alert('提示',"操作失败！","error");
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.refund = function(){
		var row=$('#ccac_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要退款此条消费记录吗?', function(r){
				if (r){
					$.messager.progress();
					$.ajax({
						async: false,
						type:"POST",
						data :{id:row.id},
						url : content+"/ccac/refundCust",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							$.messager.progress('close');
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								cu.clearSelected("ccac_table");
								$('#ccac_table').datagrid('reload');
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						},
						error:function(){
							$.messager.progress('close');
			                $.messager.alert('提示',"操作失败！","error");
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.customerAmountAdd = function(index){
		$("#ccacdetaildlg-fm").form("clear");
		$("#ccacdetaildlg").dialog("open").dialog("center").dialog("setTitle","添加消费记录");
		var row=$('#ccac_table').datagrid('getSelected');
		$("#customerId").val(row.id);
		project_dg.datagrid("load",{"consumptonAmountId":-1});
		cu.initClearCombobox("projectId");
	};
	this.customerAmountEdit = function(row){
		cu.initClearCombobox("projectId");
		$("#ccacdetaildlg").dialog("open").dialog("center").dialog("setTitle","修改消费记录");
		$("#ccacdetaildlg-fm").form("clear").form("load",row);
		$("#projectId").combobox("setText",row.projectName);
		$("#deadline").numberbox("setValue",row.deadline);
	};
	this.customerAmountRemove = function(index){
		var row=$('#ddv-'+index).datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.messager.progress();
					$.ajax({
						async: false,
						type:"POST",
						data :{id:row.id},
						url : content+"/ccac/deleteCustAmount",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							$.messager.progress('close');
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								cu.clearSelected("ccac_table");
								$('#ccac_table').datagrid('reload');
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						},
						error:function(){
							$.messager.progress('close');
			                $.messager.alert('提示',"操作失败！","error");
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.calculateAmount = function(params){
		$.ajax({
			async: false,
			type:"POST",
			data :params,
			url : content+"/ccac/queryAmountSum",
			error : function(data) {
				$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
				return false;
			},
			success : function(data) {
				if(data!=null || data !=""){//成功
					ccac.sumPrice(data);
//						ccac.sumPrice(data);
//						ccac.sumPriceC(data);
				}
			}
		});
	};
	
	this.addProjectList = function(){
		var cid = $("#ccacdid").val();
		if(cid!=""&&cid!=null){
			$("#consumptonAmountId").val(cid);
			$("#projectListdlg").dialog("open").dialog("center").dialog("setTitle","添加客户消费记录");
		}else{
			$.messager.alert('提示','请先填写项目！然后确认后再添加产品。','warning');
		}
	};
	this.projectListcomplate = function(){
		$.messager.progress();
		$('#projectListcomplate').linkbutton('disable');
		$('#projectListdlg-fm').form('submit',{
			url:content+"/consumptonProject/saveOrUpdate",
			onSubmit:function(){
				var b = $(this).form('enableValidation').form('validate');
				if(b){
					var pid = $("#inventoryId").combobox("getValue");
					var ptext = $("#inventoryId").combobox("getText");
					if(pid==ptext){
						$.messager.alert('提示',"请正确选择产品名称！","error");
						$.messager.progress('close');
						$('#projectListcomplate').linkbutton('enable');
						b = false;
					}
				}
				return b;
			},
			success: function(result){
				$.messager.progress('close');
				$('#projectListcomplate').linkbutton('enable');
				var data = eval('(' + result + ')');
				if(data.status || data.status=='true'){
					var cid = $("#ccacdid").val();
					$('#projectListdlg-fm').form('clear');
					cu.disableForm("projectListdlg-fm",false);
					$('#projectListdlg').dialog('close');
					project_dg.datagrid("load",{"consumptonAmountId":cid});
				}else{
					$.messager.alert('提示',data.mess,'warning');
				}
				$('#projectListcomplate').linkbutton('enable');
			},error:function(XMLHttpRequest,textStatus,errorThrown){
				$.messager.progress('close');
                $.messager.alert('提示',"操作失败！","error");
                $('#projectListcomplate').linkbutton('enable');
            }
		});
	};
	this.removeaProjectList = function(){
		var row=$('#project_dg').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.messager.progress();
					$.ajax({
						async: false,
						type:"POST",
						data :{id:row.id},
						url : content+"/consumptonProject/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							$.messager.progress('close');
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#project_dg').datagrid('reload');
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						},
						error:function(){
							$.messager.progress('close');
			                $.messager.alert('提示',"操作失败！","error");
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	/**
	 * 打印
	 */
	this.printData = function(){
		$("#test").show().jqprint({
			debug: false,
            importCSS: true,
            printContainer: true,
            operaSupport: false,
            printTime : 6000
		});
	};
	
	this.uploadImg = function(index){
		$('#ccac_table').datagrid("selectRow", index);
		$("#uploaddlg").dialog("open").dialog("center").dialog("setTitle","添加图片");
	};
	this.uploadPic = function(){
 		if($("#file").val()==""){
 			$.messager.alert("提示","请选择文件夹");
 			return false;
 		}
	}
	this.doupload =function(){
		var row=$('#ccac_table').datagrid('getSelected');
		if(row){
			$.messager.progress();
			$("#img_orderId").val(row.id);
			$("#img_customerPhone").val(row.phonenumb);
			$('#uploadform').form('submit',{
				url:content+"/common/img/upload",
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success: function(result){
					$.messager.progress('close');
					var data = eval('(' + result + ')');
					if(data.status || data.status=='true'){
						$('#uploadform').form('clear');
						$('#uploaddlg').dialog('close');
					}else{
						$.messager.alert('提示',data.mess,'warning');
					}
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.viewImages = function(index){
		$('#ccac_table').datagrid("selectRow", index);
		var row=$('#ccac_table').datagrid('getSelected');
		if(row){
			$.messager.progress();
			$.ajax({
				async: false,
				type:"POST",
				datatype:"json",
				data :{"orderId":row.id,"customerPhone":row.phonenumb},
				url : content+"/customerImages/getCount",
				error : function(data) {
					$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
					return false;
				},
				success : function(count) {
					$.messager.progress('close');
					if(count>0){//成功
						$("#viewimages").attr("src",content+"/goViewImages?orderId="+row.id+"&customerPhone="+row.phonenumb);
						$("#viewimagesdlg").dialog("open").dialog("center").dialog("setTitle","就诊图片预览");
					}else{
						$.messager.alert('提示信息','没有要查看的照片!','warning');
					}
				},
				error:function(){
					$.messager.progress('close');
	                $.messager.alert('提示',"操作失败！","error");
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	
	this.orderBack = function(){
		var row=$('#ccac_table').datagrid('getSelected');
		if(row){
			if(row.status==99){
				$.messager.alert('提示','订单已关闭不可操作！','error');
				return ;
			}
			$.messager.confirm('提示信息', '你确认要回退用户：“'+row.customername+'”的订单吗?', function(r){
				if (r){
					$.messager.progress();
					$.ajax({
						async: false,
						type:"POST",
						datatype:"json",
						data :{id:row.id},
						url : content+"/ccac/orderBack",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							$.messager.progress('close');
							if(data.status){//成功
								$.messager.alert('提示信息','订单回退成功!');
								$('#ccac_table').datagrid('reload');
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						},
						error:function(){
							$.messager.progress('close');
			                $.messager.alert('提示',"操作失败！","error");
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.doZLSSearch = function(v){
		if(v!='' && v!=null){
			
		}else{
			$('#queryZLS').datagrid("uncheckAll");
			$('#queryZLS').datagrid("unselectAll");
			$("#zlsdlg").dialog("open").dialog("center").dialog("setTitle","选择治疗师");
		}
	};
	this.queryZLS = function(){
		$('#queryZLS').datagrid("uncheckAll");
		$('#queryZLS').datagrid("unselectAll");
		$("#zlsdlg").dialog("open").dialog("center").dialog("setTitle","选择治疗师");
	};
}

