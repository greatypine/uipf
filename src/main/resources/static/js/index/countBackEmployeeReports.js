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
		countquery_table = $('#countquery_table').datagrid({
		        url: content+"/common/queryBackEmployeeOrderDataDetail",
		        fitColumns:false,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:true,
		        pageSize: 10,
		        pageList: [10, 15, 20],
		        queryParams:params,
		        columns:[[
				        {field:'datetime',title:'日期',width:"6%",align:'center'},
				        {field:'nickname',title:'员工姓名',width:"6%",align:'center'},
				        {field:'companyname',title:'预约门店',width:"10%",align:'center'},
				        {field:'totalOrder',title:'预约总人数',width:"6%",align:'center'},
				        {field:'secuessOrder',title:'成功接诊人数',width:"8%",align:'center'},
				        {field:'unfinishedOrder',title:'未接诊人数',width:"6%",align:'center'},
				        {field:'actual_subscribe_enable_arrive',title:'周期能就诊人数',width:"8%",align:'center'},
				        {field:'actual_jiezhen',title:'周期就诊人数',width:"8%",align:'center'},
				        {field:'wastageOrder',title:'流失人数',width:"6%",align:'center'},
				        {field:'all_totalOrder',title:'预约率',width:'18%',
						    formatter:function(val,row){
						    	var s = "";
						    	var value = 0;
						    	if (val==0){
						    			value = 0;
						    			s = '<div style="width:100%;border:1px solid #ccc;">' +
						    			'<div style="width:' + value + '%;background:#FF0000;color:#FF0000"><p style="color:#FF0000;">' + value + '%' + '</p></div>'
						    			'</div>';
						    	} else {
						    		value = Math.round((Number(row.totalOrder))/Number(val)*100);
						    		var color = "#336600";
						    		var fc = "#fff";
						    		if(value>=80 && value<100){
						    			color="#339900";
						    			fc = "#fff";
						    		}else if(value<80 && value>60){
						    			color = "#66CC00";
						    			fc = "#fff";
						    		}else if(value<60 && value>40){
						    			color="#FF3300";
						    			fc = "orange";
						    		}else if(value<40){
						    			color="#FF0000";
						    			fc = "red";
						    		}else{
						    			fc = "red";
						    		}
						    		s = '<div style="width:100%;border:1px solid #ccc">' +
						    		'<div style="width:' + value + '%;background:green;color:'+fc+'">' + value + '%' + '</div>'
						    		'</div>';
						    	}
						    	return s;
					    	}
						},
						{field:'daozhenlv',title:'到诊率',width:'18%',
						    formatter:function(val,row){
						    	var s = "";
						    	var value = 0;
						    	if (row.actual_subscribe_enable_arrive==0 || row.actual_jiezhen==0){
						    			s = '<div style="width:100%;border:1px solid #ccc;">' +
						    			'<div style="width:' + value + '%;background:#FF0000;color:#FF0000"><p style="color:#FF0000;">' + value + '%' + '</p></div>'
						    			'</div>';
						    	} else {
						    		value = Math.round(Number(row.actual_jiezhen)/(Number(row.actual_subscribe_enable_arrive))*100);
						    		var color = "#336600";
						    		var fc = "#fff";
						    		if(value>=80 && value<100){
						    			color="#339900";
						    			fc = "#fff";
						    		}else if(value<80 && value>60){
						    			color = "#66CC00";
						    			fc = "#fff";
						    		}else if(value<60 && value>40){
						    			color="#FF3300";
						    			fc = "orange";
						    		}else if(value<40){
						    			color="#FF0000";
						    			fc = "red";
						    		}else{
						    			fc = "red";
						    		}
						    		s = '<div style="width:100%;border:1px solid #ccc">' +
						    		'<div style="width:' + value + '%;background:green;color:'+fc+'">' + value + '%' + '</div>'
						    		'</div>';
						    	}
						    	return s;
					    	}
						}
		        ]],
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
