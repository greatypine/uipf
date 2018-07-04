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
		        url: content+"/common/queryBackEmployeeOrderDataDetail",
		        fitColumns:false,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        pageSize: 10,
		        pageList: [10, 15, 20],
		        queryParams:params,
		        columns:[[
				        {field:'datetime',title:'日期',width:"10%",align:'center'},
				        {field:'nickname',title:'员工姓名',width:"15%",align:'center'},
				        {field:'companyname',title:'预约门店',width:"15%",align:'center'},
				        {field:'totalOrder',title:'预约总人数',width:"15%",align:'center'},
				        {field:'secuessOrder',title:'成功接诊人数',width:"15%",align:'center'},
				        {field:'unfinishedOrder',title:'未接诊人数',width:"15%",align:'center'},
				        {field:'wastageOrder',title:'流失人数',width:"15%",align:'center'}
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
			url : content+"/common/queryBackEmployeeOrderReport",
			error : function(data) {
				createErrorInfo('服务器连接超时请重试!');
			},
			success : function(data) {
				if(data!=null && data!=''){
					reports.initBackEmployeeTreatOrder(data);
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
