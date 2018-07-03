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
	countreports.initDataGridCompant(null);
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
}
function CountReport(){
	this.initDataGridCompant = function(params){
		countquery_table = $('#countquery_table').datagrid({
		        url: content+"/ccac/queryCountConsumptionReportList",
		        fitColumns:false,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        pageSize: 10,
		        pageList: [10, 15, 20],
		        queryParams:params,
		        columns:[[
				        {field:'datetime',title:'日期',width:"20%",align:'center'},
				        {field:'c_total_amount',title:'出诊金额',width:"20%",align:'center',formatter:function(val,row){
				        	return (val!=0)?"￥"+(Number(val)*10000).toFixed(2):"￥"+0.00;
				        }},
				        {field:'f_total_amount',title:'复诊金额',width:"20%",align:'center',formatter:function(val,row){
				        	return (val!=0)?"￥"+(Number(val)*10000).toFixed(2):"￥"+0.00;
				        }},
				        {field:'CHUZHEN',title:'出诊人数',width:"20%",align:'center'},
				        {field:'FUZHEN',title:'复诊人数',width:"20%",align:'center'}
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
		$("#datetype").combobox('select', '3');
		$('#starttime').datebox('setValue', cu.date_add(new Date(),-31));
		$('#endtime').datebox('setValue', cu.date_add(new Date(),-1));
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
			url : content+"/ccac/countConsumptionReport",
			error : function(data) {
				createErrorInfo('服务器连接超时请重试!');
			},
			success : function(data) {
				if(data!=null && data!=''){
					reports.initCustomerConsumption(data);
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
