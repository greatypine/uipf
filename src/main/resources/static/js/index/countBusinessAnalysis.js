var countreports;
var reports;
var systemuser_table;
$(function(){
	initData();
});
function initData(){
	countreports = new CountReport();
	countreports.initDatas();
	countreports.initCompant();
}
function CountReport(){
	this.initDataGridCompant = function(params){
		countquery_table = $('#countquery_table').datagrid({
		        url: content+"/common/queryCountBusinessAnalysisDataDetail",
		        fitColumns:false,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        showFooter:true,
		        pageSize: 30,
		        pageList: [30, 50],
		        queryParams:params,
		        columns:[[
				        {field:'datetime',title:'日期',width:"10%",align:'center'},
				        {field:'companyName',title:'预约门店',width:"10%",align:'center'},
				        {field:'name',title:'渠道名称',width:"10%",align:'center'},
				        {field:'text',title:'客户职业',width:"10%",align:'center'},
				        {field:'numbs',title:'就诊人数',width:"10%",align:'center'},
				        {field:'total_amount',title:'消费总金额',width:"10%",align:'center',formatter:function(val,row){
				        	return (val!=0)?"￥"+(Number(val)).toFixed(2):"￥"+0.00;
				        }},
				        {field:'total_amount_avg',title:'人均消费金额',width:"10%",align:'center',formatter:function(val,row){
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
		$("#datetype").combobox('select', '3');
		$('#starttime').datebox('setValue', cu.date_add(new Date(),-31));
		$('#endtime').datebox('setValue', cu.date_add(new Date(),-1));
	};
	this.initCompant = function(){
		countreports.query();
	};
	this.query = function(){
		var params = countreports.getParams();
		countreports.initDataGridCompant(params);
	};
	this.getParams = function(){
		return $("#countqueryform").serializeJson();
	};
	this.clearForm = function(){
		$('#countqueryform').form('clear');
	};
}
