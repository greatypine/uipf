//<!--target(数据查询层级) 0：总部  1:城市级别 2：门店 3:业务事业群-->
var CU = new CommonUtils();
var cu = CU;
var report = new MyDataGrid();
$(function(){
	searchDataReturnOrderTest = new MySearchDataReturnOrderTest();
	searchDataReturnOrderTest.init();
});
 
 function MySearchDataReturnOrderTest(){
	 //初始化页面信息
	this.init = function(){
		searchDataReturnOrderTest.query();
	};
    this.titles = ["ID","商品名称","商品金额"];
    this.fields = ["id","projectName","money"];
  //查询上
    this.query = function(){
    	$("#pmanual_body").empty();
    	var datatable = '<table id="manual_body" class="table table-striped no-margin table-bordered hover"></table>';
    	$("#pmanual_body").append(datatable);
    	searchDataReturnOrderTest.createDataTable();
    };
    this.resetSerachParam = function (){
      	$("#searchDataReturnOrderTest").resetForm();
    }
    this.createDataTable = function(){
    	var params = cu.getParams("searchDataReturnOrderTestForm");
    	searchDataReturnOrderTest.goDataTable(params);
    }
    this.goDataTable = function(param){
    	var params = {};
    	var dtid = "manual_body";
    	params.dtid= dtid;
		params.snames = this.titles;
		params.names = this.fields;
		params.url = content+"/project/queryList";
		params.param = param;
		report.initDataTable(params);
    }
    
 }