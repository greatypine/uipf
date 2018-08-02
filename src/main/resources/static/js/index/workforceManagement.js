var workforceManagement;
var workforceManagement_table;
var dgedit;
$(function(){
	initData();
});
function initData(){
	workforceManagement = new MyworkforceManagement();
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
//			workforceManagement.createUserWorkforce();
//		}
	});
	$('#queryyear').combobox('setValue', cu.getCurrentYear());
	$('#querymonth').combobox('setValue', cu.getCurrentMonth());
	$('#queryyear').combobox('select', cu.getCurrentYear());
	$('#querymonth').combobox('select', Number(cu.getCurrentMonth()));
//	dgedit = new DGedit();
	workforceManagement.initCompant();
}
function MyworkforceManagement(){
	this.initCompant = function(){
		var columns = this.getcolumns();
		var month = $('#querymonth').combobox("getValue");
		workforceManagement_table = $('#workforceManagement_table').datagrid({
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
						$("#savewf").linkbutton("disable");
					}else{
						$("#addwf").linkbutton("disable");
						$("#savewf").linkbutton("enable");
					}
				},
				onBeforeEdit:function(index,row){
					row.editing = true;
					workforceManagement.selectedKeysNode = row;
					workforceManagement.selectedKeysIndex = index;
				},
				onAfterEdit:function(index,row){
					row.editing = false;
				},
				onCancelEdit:function(index,row){
					row.editing = false;
				}
			});
		$('#workforceManagement_table').datagrid("enableCellEditing");
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
			var cl = {field:'day'+(i+1),title:year+month+((i<10)?['0'+(i+1)]:i+1),width:(w+'%'),align:'center',
					editor:{
						type:'combobox',
						options:{
							valueField:'name',
							textField:'name',
							method:'post',
							url:content+'/common/getView?viewname=v_workforce_type&id='+user.user.companyid
						}
					}
			};
			cls.push(cl);
		}
		console.log(cls);
		return cls;
	};
	this.createUserWorkforce = function(){
		var data = $("#userctbbx").combobox("getValues");
		if(data!=null){
			if(data.length>0){
				for (var i = 0; i < data.length; i++) {
					var row = data[i];
					console.log(row);
				}
			}
		}
	}
	this.query = function(){
		var params = workforceManagement.getParams();
		workforceManagement_table.datagrid("load",params);
	};
	this.getParams = function(){
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager")?$("#querycompanyid").combobox("getValue"):null;
		var year = ($("#queryyear").combobox("getValue")<0)?null:$("#queryyear").combobox("getValue");
		var month = ($("#querymonth").combobox("getValue")<0)?null:$("#querymonth").combobox("getValue");
		var params = {};
		if(companyid!="") {params.companyid = companyid;}
		if(year!="" && month!="") {params.cycle = year+((Number(month)<10)?"0"+month:month);}
		return params;
	};
	this.clearForm = function(){
		$('#workforceManagementform').form('clear');
	};
	this.add = function(){
		$("#workforceManagementdlg-fm").form("clear");
		workforceManagement.initClearCombobox("userctbbx");
		var data = $("#userctbbx").combobox("getData");
		var ids = new Array();
		for (var i = 0; i < data.length; i++) {
			ids.push(data[i].id);
		}
		$("#userctbbx").combobox("setValues",ids);
		$('#fyear').combobox('setValue', cu.getCurrentYear());
		$('#fmonth').combobox('setValue', cu.getCurrentMonth());
		$("#workforceManagementdlg").dialog("open").dialog("center").dialog("setTitle","添加公司");
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.complate = function(){
		$('#workforceManagementdlg-fm').form('submit',{
			url:content+"/workforceManagement/addwf",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				var data = eval('(' + result + ')');
				if(data.status){
					$('#workforceManagementdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#workforceManagementdlg').dialog('close');
					$('#workforceManagement_table').datagrid('reload');
				}else{
					$.messager.alert('提示',data.mess,'warning');
					$.messager.progress('close');
				}
			}
		});
	};
	this.onClickRow = function(){
		dgedit.onClickRow(index);
	}
	this.onDateDiffSelect = function(date){
		var day = CU.DateDiff(date,new Date());
		$("#diffDay").textbox("setValue",day);
	};
	
	this.savewf = function(){
		$('#workforceManagement_table').datagrid('endEdit', workforceManagement.selectedKeysIndex);
		var datag = $("#workforceManagement_table").datagrid("getData");
		var data = datag.rows;
		$.messager.confirm('提示信息', '你确认要生成排班吗?', function(r){
			if (r){
				$.ajax({
					headers: {
				        'Accept': 'application/json',
				        'Content-Type': 'application/json'
				    },
				    sync:false,
					data:JSON.stringify(data),
					type:"POST",
					dataType: "json", //表示返回值类型
					url : content+"/workforceManagement/saveOrUpdate",
					error : function(data) {
						$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
						return false;
					},
					success : function(data) {
						if(data){//成功
							$.messager.alert('提示信息','排版生成成功!');
							$('#workforceManagement_table').datagrid('reload');
						}else{
							$.messager.alert('提示信息','操作失败!','warning');
						}
					}
				});
			}
		});
		
	}
	this.remove = function(){
		var params = workforceManagement.getParams();
		$.messager.confirm('提示信息', '你确认要删除《'+params.year+'年'+params.month+'月》排班信息吗?', function(r){
			if (r){
				$.ajax({
					data :params,
					type:"POST",
					url : content+"/workforceManagement/delete",
					error : function(data) {
						$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
						return false;
					},
					success : function(data) {
						if(data){//成功
							$.messager.alert('提示信息','删除成功!');
							$('#workforceManagement_table').datagrid('reload');
						}else{
							$.messager.alert('提示信息','删除失败!','warning');
						}
					}
				});
			}
		});
	};
	this.exportData = function(){
		var params = workforceManagement.getParams();
		$.messager.confirm('提示信息', '你确认要导出【'+params.cycle+'】排班信息吗?', function(r){
			if (r){
				window.location.href = content+"/exportExcel/exportWorkforceDataList?cycle="+params.cycle+"&companyid="+params.companyid;
			}
		});
	};
}
