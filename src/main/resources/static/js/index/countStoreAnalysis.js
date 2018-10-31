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
		        url: content+"/common/queryStoreReport",
		        fitColumns:false,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        showFooter:true,
		        pageSize: 30,
		        pageList: [30, 50],
		        queryParams:params,
		        frozenColumns:[[
		        	{field:'cityName',title:'城市名称',width:"7%",align:'center'},
			        {field:'companyName',title:'门店名称',width:"7%",align:'center'}
				]],
		        columns:[[
				        {field:'t_CHUZHEN',title:'日出诊',width:"6%",align:'center'},
				        {field:'t_LIUSHI',title:'日流失',width:"6%",align:'center'},
				        {field:'t_FUZHEN',title:'日复诊',width:"6%",align:'center'},
				        {field:'t_c_total_amount',title:'日出诊金额',width:"8%",align:'center',formatter:function(val,row){
				        	return cu.formatNumber(val,2,"￥");
				        }},
				        {field:'t_f_total_amount',title:'日复诊金额',width:"8%",align:'center',formatter:function(val,row){
				        	return cu.formatNumber(val,2,"￥");
				        }},
				        {field:'t_total_amount',title:'日总金额',width:"8%",align:'center',formatter:function(val,row){
				        	return cu.formatNumber(val,2,"￥");
				        }},
				        {field:'y_CHUZHEN',title:'月出诊',width:"6%",align:'center'},
				        {field:'y_LIUSHI',title:'月流失',width:"6%",align:'center'},
				        {field:'y_FUZHEN',title:'月复诊',width:"6%",align:'center'},
				        {field:'y_c_total_amount',title:'月出诊金额',width:"8%",align:'center',formatter:function(val,row){
				        	return cu.formatNumber(val,2,"￥");
				        }},
				        {field:'y_f_total_amount',title:'月复诊金额',width:"8%",align:'center',formatter:function(val,row){
				        	return cu.formatNumber(val,2,"￥");
				        }},
				        {field:'y_avg_total_amount',title:'月单体平均金额',width:"8%",align:'center',formatter:function(val,row){
				        	return cu.formatNumber(val,2,"￥");
				        }},
				        {field:'y_total_amount',title:'月总金额',width:"10%",align:'center',formatter:function(val,row){
				        	return cu.formatNumber(val,2,"￥");
				        }},
				        {field:'ziranlaikequdao',title:'自然来客渠道',width:"8%",align:'center'},
				        {field:'laogukejieshao',title:'老顾客介绍',width:"8%",align:'center'},
				        {field:'meituan',title:'美团',width:"4%",align:'center'},
				        {field:'dazhong',title:'大众',width:"4%",align:'center'},
				        {field:'douyin',title:'抖音',width:"4%",align:'center'},
				        {field:'xinyang',title:'新氧',width:"4%",align:'center'},
				        {field:'xinlangweibo',title:'新浪微博',width:"4%",align:'center'},
				        {field:'pengyoujieshao',title:'朋友介绍',width:"4%",align:'center'},
				        {field:'luguo',title:'路过',width:"4%",align:'center'},
				        {field:'zhuandian',title:'转店',width:"4%",align:'center'},
				        {field:'other',title:'其他',width:"4%",align:'center'}
		        ]],
				onDblClickRow:function(index,row){
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(){
					cu.autoMergeCells("countquery_table",['cityName','companyName'],"cityName"); //三个参数分别为：表格id，要合并字段的数组，判断字段（不一样则不合并）  
				} 
			});
	};
	this.initDatas = function(){
		$(".companylist").combobox({
			url:content+'/company/queryMapBeanList'
		});
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
