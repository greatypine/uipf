var workforceManagementQuery;
var workforceManagementQuery_table;
var dgedit;
$(function(){
	initData();
});
function initData(){
	workforceManagementQuery = new MyworkforceManagementQuery();
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$(".year").combobox({
		url:content+'/common/getView?viewname=v_years&id='+user.user.companyid
	});
	$(".month").combobox({
		url:content+'/common/getView?viewname=v_months&id='+user.user.companyid
	});
	$(".usercbx").combobox({
		url:content+'/common/queryCosmetologist?id='+user.user.companyid
//		,
//		onLoadSuccess:function(){
//			var data = $("#queryusercbx").combobox("getData");
//			var ids = new Array();
//			for (var i = 0; i < data.length; i++) {
//				ids.push(data[i].id);
//			}
//			$("#queryusercbx").combobox("setValues",ids);
//			workforceManagementQuery.createUserWorkforce();
//		}
	});
	$('#queryyear').combobox('setValue', cu.getCurrentYear());
	$('#querymonth').combobox('setValue', cu.getCurrentMonth());
	$('#queryyear').combobox('select', cu.getCurrentYear());
	$('#querymonth').combobox('select', Number(cu.getCurrentMonth()));
//	dgedit = new DGedit();
	workforceManagementQuery.initCompant();
}
function MyworkforceManagementQuery(){
	this.initCompant = function(){
		var columns = this.getcolumns();
		var month = $('#querymonth').combobox("getValue");
		workforceManagementQuery_table = $('#workforceManagementQuery_table').datagrid({
		        url: content+"/workforceManagement/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
		        	cycle:$('#queryyear').combobox("getValue")+(Number(month)<10)?"0"+month:month
				},
		        columns:[columns],
				onDblClickRow:function(index,row){
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(data){
					if(data.total==0){
						$("#addwf").linkbutton("enable");
//						$("#savewf").linkbutton("disable");
					}else{
//						$("#savewf").linkbutton("enable");
					}
				}
			});
	};
	this.getcolumns = function(){
		var cls = new Array();
		var year = $("#queryyear").combobox("getValue");
		var month = $("#querymonth").combobox("getValue");
		if(year=="" || month == ""){
			$.messager.alert('提示信息','日期不能为空!','error'); 
			return "";
		}
		var ld = cu.getLastMonthDay(year,month);
		var w = Math.floor(100%(ld+1));
		var clun = {field:'username',title:'姓名',width:(w+'%'),align:'center'};
		cls.push(clun);
		for (var i = 0; i < ld; i++) {
			var cl = {field:'day'+(i+1),title:year+month+((i<10)?['0'+(i+1)]:i+1),width:(w+'%'),align:'center',formatter:function(val,row){
	        	if(val=='休'){
	        		return '<font color="FF0033"><b>'+val+'<b></font>';
	        	}else if(val=='早'){
	        		return '<font color="#009933">'+val+'</font>';
	        	}else if(val=='中'){
	        		return '<font color="#CC9933">'+val+'</font>';
	        	}else if(val=='晚'){
	        		return '<font color="#660033">'+val+'</font>';
	        	}
	        }};
			cls.push(cl);
		}
//		console.log(cls);
		return cls;
	};
	this.query = function(){
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager")?$("#querycompanyid").combobox("getValue"):null;
		var year = ($("#queryyear").combobox("getValue")<0)?null:$("#queryyear").combobox("getValue");
		var month = ($("#querymonth").combobox("getValue")<0)?null:$("#querymonth").combobox("getValue");
		var params = {};
		if(companyid!="") {params.companyid = companyid;}
		if(year!="" && month!="") {params.cycle = year+((Number(month)<10)?"0"+month:month);}
		workforceManagementQuery_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("workforceManagementQueryform").serializeJson();
	};
	this.clearForm = function(){
		$('#workforceManagementQueryform').form('clear');
	};
}
