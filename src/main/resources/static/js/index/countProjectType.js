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
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
}
function CountReport(){
	this.initDataGridCompant = function(params){
		countquery_table = $('#projecttype_table').datagrid({
		        url: content+"/common/queryProjectTypeChangeReport",
		        fitColumns:false,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        pageSize: 10,
		        pageList: [10, 15, 20],
		        queryParams:params,
		        columns:[[
				        {field:'p1',title:'体验转包治数',width:"12%",align:'center'},
				        {field:'p2',title:'单次转包治数',width:"12%",align:'center'},
				        {field:'p3',title:'疗程转包治数',width:"12%",align:'center'},
				        {field:'p4',title:'祛印转包治数',width:"12%",align:'center'},
				        {field:'p5',title:'黑头套转包治数',width:"12%",align:'center'},
				        {field:'p6',title:'水动力套转包治数',width:"12%",align:'center'},
				        {field:'p7',title:'抑螨套转包治数',width:"12%",align:'center'}
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
		$('#starttime').datebox('setValue', cu.date_add(new Date(),-8));
		$('#endtime').datebox('setValue', cu.date_add(new Date(),-1));
	};
	this.initCompant = function(){
		var h = $("#reprotContent").height();
		var ch = $("#cron_bar").height();
		var rh = (h-ch)/2;
		$("#projecttypeReprot").css("height",rh);
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
			async : true,
			url : content+"/common/queryProjectTypeReport",
			error : function(data) {
				createErrorInfo('服务器连接超时请重试!');
			},
			success : function(data) {
				if(data!=null && data!=''){
					reports.initProjectType(data);
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
