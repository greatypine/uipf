var therapistTreatmentTimeQuery;
var therapistTreatmentTimeQuery_table;
var dgedit;
$(function(){
	initData();
});
function initData(){
	therapistTreatmentTimeQuery = new MytherapistTreatmentTimeQuery();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});

	$('#querycycle').datebox('setValue', cu.getCurrentDate());
	therapistTreatmentTimeQuery.initCompant();
}
function MytherapistTreatmentTimeQuery(){
	this.initCompant = function(){
		therapistTreatmentTimeQuery_table = $('#therapistTreatmentTimeQuery_table').datagrid({
		        url: content+"/tttq/queryList",
		        pageList: [7, 14, 30],
		        pageSize:7,
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:false,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
		        	cycle:$('#querycycle').datebox('getValue'),
		        	companyid:user.user.companyid
				},
				frozenColumns:[[
					{field:'companyName',title:'门店',width:"5%",align:'center'},
					{field:'username',title:'治疗师',width:"5%",align:'center'},
					{field:'day',title:'日期',width:"5%",align:'center'},
					{field:'days',title:'班次',width:"5%",align:'center'}
				]],
		        columns:[[
	        	 	{field:'id',title:'编号',hidden:true},
			        {field:'time1Name1',title:'01:00~02:00',width:"9%",height:'100px',align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name2',title:'02:00~03:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name3',title:'03:00~04:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name4',title:'04:00~05:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name5',title:'05:00~06:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name6',title:'06:00~07:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name7',title:'07:00~08:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name8',title:'08:00~09:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name9',title:'09:00~10:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name10',title:'10:00~11:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name11',title:'11:00~12:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name12',title:'12:00~14:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name13',title:'13:00~14:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name14',title:'14:00~15:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name15',title:'15:00~16:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name16',title:'16:00~19:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name17',title:'17:00~18:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name18',title:'18:00~19:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name19',title:'19:00~20:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name20',title:'20:00~21:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name21',title:'21:00~22:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name22',title:'22:00~23:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name23',title:'23:00~00:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}},
			        {field:'time1Name24',title:'00:00~01:00',width:"9%",align:'center',formatter:function(val,row){return therapistTreatmentTimeQuery.formatTimeCloumn(val,row);}}
			    ]],
				onDblClickRow:function(index,row){
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(data){
					$(".datagrid-row").css({"height": "100px"});
				}
			});
	};
	this.formatTimeCloumn = function(val,row){
		if(val!=undefined){
    		if(val.indexOf("-")!=-1){
    			var vs = val.split("-");
    			var html = val;
    			if(vs.length>0){
    				html = "";
    				for (var i = 0; i < vs.length; i++) {
    					if(vs.length-1!=i){
    						if(i==0){
    							html += '<font color="firebrick" style="text-align: left;float: left;">'+vs[i]+"预约人数：</font></br>";
    						}else{
    							html += '<font color="cadetblue" style="text-align: left;float: left;padding-left:10px;">'+vs[i]+"</font></br>";
    						}
    					}else {
    						var p = vs[i];
    						var pns = p.split("：");
    						var pnst = pns[0];
    						html += '<font color="firebrick" style="text-align: left;float: left;">'+pnst+"：</font></br>";
    						var pnsv = pns[1];
    						html += '<font color="cadetblue" style="text-align: left;float: left;padding-left:10px;">'+pnsv+"</font></br>";
    					}
    				}
    			}
    			return html
    		}else{
    			return val;
    		}
    	}
    	return val;
	}
	this.query = function(){
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager")?$("#querycompanyid").combobox("getValue"):null;
		var cycle = $('#querycycle').datebox('getValue');
		var params = {};
		if(companyid!="") {params.companyid = companyid;}
		if(cycle!="") {params.cycle = cycle;}
		therapistTreatmentTimeQuery_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("therapistTreatmentTimeQueryform").serializeJson();
	};
	this.clearForm = function(){
		$('#therapistTreatmentTimeQueryform').form('clear');
	};
}
