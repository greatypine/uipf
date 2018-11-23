/**
 * 
 */
function MyDataGrid(){
	/**
	 * 初始化数据列表
	 * params: dtid：列表id、url：请求数据地址、snames[]：行标题、names[]：数据库返回字段（对于snames）
	 */
	this.initDataTable = function(params){
		//1：生成表格 2：生产对于的colums
		report.createDataTableShowColumns(params.dtid,params.snames);
		var columns = report.createDataTableColumns(params.dtid,params.names);
		var url = params.url;
		var param =params.param;
		var dtele = $('#'+params.dtid);
		//预编译模板
		rm_datatable = dtele.DataTable({
			// 是否表示 "processing" 加载中的信息，这个信息可以修改
			processing: true,
			stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
			serverSide: true,  //启用服务器端分页
			bSort : false,
			sClass: "text-center",
			searching: false,  //禁用原生搜索
			// 自动列宽
            "autoWidth": true,
            "dom": 'lrtip',
            // 每次创建是否销毁以前的DataTable,默认false
            "destroy": false,
			ordering:  false,
			orderMulti: false,  //启用多列排序
			aLengthMenu:[10,20,50,100],
			order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
			renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
			pagingType: "simple_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
	        ajax:{//ajax请求数据源
	            "url": url,
	            "type": "post",
	            "data": function (data) {//添加额外的数据给服务器
	            	param['pageIndex']=(data.start / data.length)+1;
	            	data=$.extend(data,param);
		         }
	        },
	        "language":{ "url":content+"/static/dataTables/Chinese.json"},
	        "columns": columns
	    });
	};
	/**
	 * 创建表格显示名称行
	 */
	this.createDataTableShowColumns = function(id,snames){
		var str = "<thead><tr>";
		var endstr ="</tr></thead>";
		var mstr = "";
		if(snames.length>0){
			for (var i = 0; i < snames.length; i++) {
				mstr+="<th>"+snames[i]+"</th>";
			}
		}
		$("#"+id).html("");
		$("#"+id).append(str+mstr+endstr);
	};
	/**
	 * 创建表格colums
	 */
	this.createDataTableColumns = function(id,names){
		var clms = new Array();
		if(names.length>0){
			var pw = $("#"+id).width();
			var s = names.length;
			var width = (pw/s).toFixed(2);
			for (var i = 0; i < names.length; i++) {
				clms.push({ "data": names[i]});
			}
		}
		return clms;
	};
	
//	this.createGridTable = function(titles,fields,datas,appendDivId){
//    	$("#"+appendDivId).html("");
//    	var html = "";
//    	html += "<tr id='manual_title'>";
//  		for (var j = 0; j < titles.length; j++) {
//  			html += "<td>"+titles[j]+"</td>";
//  		}
//  		html += "</tr>";
//  		$("#"+appendDivId).append(html);
//  		html = "";
//  		for (var i = 0; i < datas.length; i++) {
//  			html += "<tr>";
//			for (var j = 0; j < titles.length; j++) {
//				html += "<td>"+datas[i][fields[j]]+"</td>"
//			}
//			html += "</tr>";
//		}
//  		$("#"+appendDivId).append(html);
//    };
}

