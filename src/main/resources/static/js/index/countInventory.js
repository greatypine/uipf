var countreports;
var reports;
var systemuser_table;
$(function(){
	initData();
});
function initData(){
	countreports = new CountReport();
	reports = new Reports();
	countreports.initDatas();
	countreports.initCompant();
}
function CountReport(){
	this.initDataGridCompant = function(params){
		countquery_table = $('#countquery_table').datagrid({
		        url: content+"/common/queryCountInventory",
		        fitColumns:false,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        pageSize: 10,
		        pageList: [10, 15, 20],
		        queryParams:params,
		        columns:[[
				        {field:'datetime',title:'日期',width:"7%",align:'center'},
				        {field:'companyName',title:'预约门店',width:"10%",align:'center'},
				        {field:'productName',title:'产品名称',width:"10%",align:'center'},
				        {field:'units',title:'单位',width:"5%",align:'center'},
				        {field:'currentInventoryNumbs',title:'当前剩余库存数',width:"7%",align:'center'},
				        {field:'currentInventoryMonery',title:'当前剩余库存金额',width:"7%",align:'center',formatter:function(val,row){
				        	return (val!=0)?"￥"+(Number(val)).toFixed(2):"￥"+0.00;
				        }},
				        {field:'addNumbs',title:'本期添加库存数量',width:"9%",align:'center'},
				        {field:'addInventoryMonery',title:'本期添加库存金额',width:"9%",align:'center',formatter:function(val,row){
				        	return (val!=0)?"￥"+(Number(val)).toFixed(2):"￥"+0.00;
				        }},
				        {field:'removeNumbs',title:'本期删除库存数量',width:"9%",align:'center'},
				        {field:'removeInventoryMonery',title:'本期删除库存金额',width:"9%",align:'center',formatter:function(val,row){
				        	return (val!=0)?"￥"+(Number(val)).toFixed(2):"￥"+0.00;
				        }},
				        {field:'orderUseNumbs',title:'本期订单使用库存数量',width:"9%",align:'center'},
				        {field:'useInventoryMonery',title:'本期订单使用库存金额',width:"9%",align:'center',formatter:function(val,row){
				        	return (val!=0)?"￥"+(Number(val)).toFixed(2):"￥"+0.00;
				        }},
				        {field:'orderBackNumbs',title:'本期订单退回库存数量',width:"9%",align:'center'},
				        {field:'backInventoryMonery',title:'本期订单退回库存金额',width:"9%",align:'center',formatter:function(val,row){
				        	return (val!=0)?"￥"+(Number(val)).toFixed(2):"￥"+0.00;
				        }}
		        ]],
				onDblClickRow:function(index,row){
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.initDatas = function(){
		$(".companylist").combobox({
			url:content+'/company/queryMapBeanList'
		});
		$("#year").combobox({
			url:content+'/common/getView?viewname=v_years&id='+user.user.companyid
		});
		$("#month").combobox({
			url:content+'/common/getView?viewname=v_months&id='+user.user.companyid
		});
		$("#datetype").combobox('select', '2');
		$('#year').combobox('setValue', cu.getCurrentYear());
		$('#month').combobox('setValue', cu.getCurrentMonth());
	};
	this.initCompant = function(){
		var h = $("#reprotContent").height();
		var ch = $("#cron_bar").height();
		var rh = (h-ch)/2;
		$("#customerConsumption").css("height",rh);
		countreports.query();
	};
	this.query = function(){
		var params = countreports.getParams();
		countreports.queryData(params);
		countreports.initDataGridCompant(params);
	};
	this.queryData = function(params){
		$.ajax({
			cache : false,
			type : 'POST',
			dataType : "json",
			data : params,
			async : false,
			url : content+"/common/queryCountInventoryPie",
			error : function(data) {
				createErrorInfo('服务器连接超时请重试!');
			},
			success : function(data) {
				if(data!=null && data!=''){
					reports.queryCountInventoryPie(data);
				}
			}  
		});
	};
	this.getParams = function(){
		return $("#countqueryform").serializeJson();
	};
	this.clearForm = function(){
		$('#countqueryform').form('clear');
	};
}
