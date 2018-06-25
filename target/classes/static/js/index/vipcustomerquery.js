var systemvipcustomerquery;
var systemvipcustomerquery_table;
$(function(){
	initData();
});
function initData(){
	systemvipcustomerquery = new systemvipcustomerquery();
	systemvipcustomerquery.initCompant();
}
function systemvipcustomerquery(){
	this.initCompant = function(){
		$('#systemvipcustomerquery_table').treegrid({
			rownumbers: true,
			animate:true,
			collapsible:true,
			fitColumns:true,
			url:content+'/vipcustomer/queryMapGridList',
			method: 'POST',
			idField:'id',
			treeField:'customer_name',
			queryParams:{
				customerPhone:-1
			},
			columns:[[
                {field:'customer_name',title:'客户姓名',width:'10%',align:'center'},
				{field:'customer_phone',title:'客户电话',width:'8%',align:'center'},
				{field:'accountBalance',title:'账户余额',width:'8%',align:'center',formatter:function(val,row){
		        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
		        }},
				{field:'companyName',title:'消费门店',width:'9%',align:'center'},
				{field:'consumeName',title:'消费类型',width:'10%',align:'center'},
				{field:'projectName',title:'产品套餐',width:'9%',align:'center'},
				{field:'totalConsumptonAmount',title:'消费金额',width:'7%',align:'center',formatter:function(val,row){
		        	return (val!=0 && val!=null)?"￥"+val:"￥"+0.00;
		        }},
				{field:'createTime',title:'消费时间',width:'10%',align:'center'},
				{field:'products',title:'使用率/产品列表',width:'27%',
				    formatter:function(val,row){
				    	if (row.parendId==0){
				    		var s = "";
				    		var value = 0;
				    		if(row.type==3){
				    			value = Math.round((Number(row.totalConsumptonAmount)/Number(row.accountBalance))*100);
				    			s = '<div style="width:100%;border:1px solid #ccc">' +
				    			'<div style="width:' + value + '%;background:#cc0000;color:#fff">' + value + '%' + '</div>'
				    			'</div>';
				    		}else{
				    			value = 100;
				    			s = '<div style="width:100%;border:1px solid #ccc">' +
				    			'<div style="width:' + value + '%;background:green;color:#fff">' + value + '%' + '</div>'
				    			'</div>';
				    		}
					    	return s;
				    	} else {
					    	return val;
				    	}
			    	}
				}
			]],
			onBeforeExpand:function(row){
                $('#systemvipcustomerquery_table').treegrid('options').url = content+'/vipcustomer/queryMapGridChildren?id='+row.id;           
            }
		});
	};
	this.query = function(){
		var customerPhone = $("#queryphone").textbox("getValue");
		$.post(content+'/vipcustomer/queryMapGridList',{"customerPhone":customerPhone},function(data){
			  $('#systemvipcustomerquery_table').treegrid('loadData',data);
			},'json');
	};
	this.getParams = function(){
		return $("systemvipcustomerqueryform").serializeJson();
	};
	this.clearForm = function(){
		$('#systemvipcustomerqueryform').form('clear');
	};
	this.onDateDiffSelect = function(date){
		var day = CU.DateDiff(date,new Date());
		$("#diffDay").textbox("setValue",day);
	};
	
	this.clear = function(){
		$("#systemvipcustomerqueryform").form("clear");
	}
}
