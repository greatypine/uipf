var schedule;
var wfdd;
var websocket;
var interval;
var map;
$(function(){
	init();
});
function init(){
	initdata();
	schedule.datagrid();
	$("#dependentId").combobox({
		url:content+'/schedule/queryScheduleTree'
	});
}
function initdata(){
	schedule = new Schedule();
	map = new Map();
	Date.prototype.format = function(format) {
	    var o = {
	        "M+" : this.getMonth() + 1,// month
	        "d+" : this.getDate(),// day
	        "h+" : this.getHours(),// hour
	        "m+" : this.getMinutes(),// minute
	        "s+" : this.getSeconds(),// second
	        "q+" : Math.floor((this.getMonth() + 3) / 3),// quarter
	        "S" : this.getMilliseconds()
	    // millisecond
	    };
	    if (/(y+)/.test(format) || /(Y+)/.test(format)) {
	        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    }
	    for ( var k in o) {
	        if (new RegExp("(" + k + ")").test(format)) {
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	        }
	    }
	    return format;
	};
	wfdd = $('#wfdd').dialog({
	    width: 350,
	    height: 100,
	    closed: true,
	    cache: false,
	    modal: true
	});
}
function Schedule(){
	this.index = null;
	this.websocketConn = function(){
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://"+window.location.host+content+"/schedule");
	    } else if ('MozWebSocket' in window) {
	        websocket = new MozWebSocket("ws://schedule");
	    } else {
	    	return;
	    }
		websocket.onopen = function (evnt) {
			schedule.send();
	    };
	    websocket.onmessage = function (evnt) {
	        $("#wfddcontext").html(evnt.data);
	    };
	    websocket.onerror = function (evnt) {
	    };
	    websocket.onclose = function (evnt) {
	    }
	};
	this.send =  function(){
        if (websocket != null) {
        	websocket.send("客户端请求");
        }
    };
	this.datagrid = function(){
			$("#cron_table").datagrid({
		 	url:content+"/schedule/getList",
		 	fitColumns: true, 
			loadMsg : "正在加载，请稍后...",
			queryParams:{
				"cron":$("#crons").val(),
				"name":$("#names").val(),
				"groupname":$("#groups").val()
			},
		    columns:[[
		        {field:'id',title:'定时编号',width:"5%",hidden:true},
		        {field:'cron',title:'定时模式',align:'center',width:"10%"},
		        {field:'groupname',title:'分组',align:'center',width:"8%",hidden:true},
		        {field:'name',title:'定时名称',align:'left',width:"16%"},
		        {field:'remark',title:'定时器说明',width:"18%"},
		        {field:'status',title:'定时状态',align:'center',width:"6%",formatter:function(val,row){
		        	if(val==0){
		        	return '<font color="FF0033">关闭</font>'
		        	}else{
		        	return '<font color="33CC33">开启</font>'
		        	}
		        }},
		        {field:'operate',title:'启用标志',align:'center',width:"6%",  
		            formatter:function(value, row, index){
		            	if(row.status==0){
		            		var str = '<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="schedule.changeScheduleStatus('+row.id+',\''+row.name+'\','+row.status+')">打开</a>';	
		            	}else{
		            		var str = '<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="schedule.changeScheduleStatus('+row.id+',\''+row.name+'\','+row.status+')">关闭</a>';
		            	}
		                  
		                return str;  
		        }},
		        {field:'createuser',title:'创建人',align:'center',width:"8%"},
		        {field:'createtime',title:'创建时间',width:"13%",formatter:function(value, row, index){
		        	return CU.DateTimeFormatter(value,1); 
		        }},
		        {field:'updateuser',title:'更新人',width:"8%",align:'center'},
		        {field:'updatetime',title:'更新时间',width:"13%",align:'center',formatter:function(val,row){
		        	return CU.DateTimeFormatter(val,1);
		        }}
		    ]],
	
	        view: detailview,
	        collapsible:false,
	        detailFormatter:function(index,row){
	            return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>'; 
	        },
	        onCollapseRow:function(index,row){
	        	 $("#cron_table").datagrid("selectRow", index);
	        },
	        onExpandRow: function(index,row){
	        	schedule.index = index;
	        	$("#cron_table").datagrid("selectRow", index);
	            $('#ddv-'+index).datagrid({
	                url:content+"/scheduleWorkflow/getlist?scheduleId="+row.id,
	                fitColumns:true,
	                singleSelect:true,
	                rownumbers:true,
	                loadMsg:'正在加载，请稍后...',
	                height:'auto',
	                columns:[[
	                		{field:'schedule_id',title:'定时器id',width:"10%",hidden:true},
	                	 	{field:'workflow_id',title:'工作流Id',width:"10%",hidden:true},
					        {field:'name',title:'流程名称',width:"25%"},
					        {field:'indexopera',title:'执行顺序',width:"10%",align:'center',formatter:function(val,row){
					        	var  str=' <a onClick=\"schedule.workflowsort('+row.scheduleId+','+row.workflowId+',1)\"><i class=\"fa fa-hand-o-up\" aria-hidden=\"true\"></i></a>  <a  onClick=\"schedule.workflowsort('+row.scheduleId+','+row.workflowId+',-1)\"><i class=\"fa fa-hand-o-down\" aria-hidden=\"true\"></i></a>';
					        	return str;
					        }
					        },
					        {field:'status',title:'任务状态',width:"10%",align:'center',formatter:function(val,row){if(val==0){return '<font color="red">关</font>'}else{return '<font color="greed">开启</font>'}}},
					        {field:'operate',title:'启用标志',align:'center',width:"10%",  
					            formatter:function(value, row, index){
					            	if(row.status==0){
					            		var str = '<a href="javascript:void(0);" name="opera" style="cursor:pointer" class="easyui-linkbutton" onClick="schedule.changeWorkflowStatus('+row.scheduleId+','+row.workflowId+','+row.status+')">打开</a>';	
					            	}else{
					            		var str = '<a href="javascript:void(0);" name="opera" style="cursor:pointer" class="easyui-linkbutton" onClick="schedule.changeWorkflowStatus('+row.scheduleId+','+row.workflowId+','+row.status+')">关闭</a>';
					            	}
					                return str;
					        }},
					        {field:'logs',title:'执行日志',align:'center',width:"10%",  
					            formatter:function(value, row, index){
					            	return '<a id="'+row.scheduleId+"|"+row.workflowId+"|"+index+'" href="javascripte:void(0)" onClick="schedule.queryLogs(this.id)" class="logslinkbtn easyui-linkbutton" data-options="plain:true,iconCls:\'icon-more\'">日志</a>';
					        }},
					        {field:'createuser',title:'创建人',width:"10%",align:'center'},
					        {field:'remark',title:'流程描述',width:"15%"}
	                ]],
	                toolbar: [{
	                    text:'添加工作流信息',
	                iconCls: 'icon-add',
	                handler: function(){
	                	if(CU.hasPermission("update")) schedule.workflowAdd(index);
	                	else $.messager.alert('提示','没有操作权限！','warning');
	                }
	                },'-',{
	                text:'修改工作流信息',
	                iconCls: 'icon-remove',
	                handler: function(){
	                	if(CU.hasPermission("update")) schedule.editRemove(index);
	                	else $.messager.alert('提示','没有操作权限！','warning');
	                }},'-',{
	                text:'删除工作流信息',
	                iconCls: 'icon-remove',
	                handler: function(){
	                	if(CU.hasPermission("delete")) schedule.workflowRemove(index);
	                	else $.messager.alert('提示','没有操作权限！','warning');
	                }
	                }],
			    onDblClickRow:function(index,row){
			    	schedule.editRemove(schedule.index);
		        },
		        onSelect:function(index,row){
		
		        },
		        onLoadSuccess:function(){
		        	$(".logslinkbtn").linkbutton();
		            setTimeout(function(){
		                $('#cron_table').datagrid('fixDetailRowHeight',index); 
		            },0); 
		        } 
		       });
		      },
			  onDblClickRow:function(index,row){
				  schedule.cronAdd(row);
		      },
		      onSelect:function(index,row){
		    	  $('#idcron').val(index+','+row.id);
		      }
		 });
	}
	this.cronAdd = function(data){
		if(data==null || data == ""){
			$("#crondlg").dialog("open").dialog("center").dialog("setTitle","添加定时器配置");
			$("#crondlg-fm").form("clear");
			var rows = $("#status").combobox("getData");
			$("#status").combobox("select",rows[0].value);	 
		}else{
			$("#crondlg").dialog("open").dialog("center").dialog("setTitle","修改定时器配置");
			$("#crondlg-fm").form("clear").form('load',data);
		}
	}
	this.cronedit = function(){
		var row=$('#cron_table').datagrid('getSelected');
		if(row){
			schedule.cronAdd(row);
		}else{
			$.messager.alert('提示','请先选中要修改的数据！','warning');
			$.messager.progress('close');
			$('#crondlg').dialog('close');
		}
	}
	this.saveCronOrUpdate = function(){
		$('#crondlg-fm').form('submit', {
			url:content+"/schedule/saveOrUpdate",
			onSubmit: function(data){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result>0){
					$('#crondlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#crondlg').dialog('close');
					$('#cron_table').datagrid('reload');

				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
				
			}
		});
	}
	this.cronRemove = function(){
		var row=$('#cron_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此条数据吗?', function(r){
				if (r){
					$.ajax({
						data : {"id":row.id,"name":row.name},
						url : content+"/schedule/removeById",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data>0){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#cron_table').datagrid('reload');
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	/**
	 * 更改定时器接口状态
	 */
	this.changeScheduleStatus = function(id,name,status){
		$.ajax({
			type:"POST",
			data : {"id":id,"status":!status,"name":name},
			url : content+"/schedule/saveOrUpdate",
			error : function(data) {
				$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
				return false;
			},
			success : function(data) {
				if(data>0){//成功
					$.messager.alert('提示信息','操作成功!');
					$('#cron_table').datagrid('reload');
				}else{
					$.messager.alert('提示信息','操作失败!','warning');
				}
			}
		});
	};
	/**
	 * 更改工作流状态
	 */
	this.changeWorkflowStatus = function(scheduleId,workflowId,status){
		$.ajax({
			type:"POST",
			data : {"scheduleId":scheduleId,"status":!status,"workflowId":workflowId},
			url : content+"/scheduleWorkflow/changeWorkflowStatus",
			error : function(data) {
				$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
				return false;
			},
			success : function(data) {
				if(data>0){//成功
					$.messager.alert('提示信息','操作成功!');
					$('#cron_table').datagrid('reload');
				}else{
					$.messager.alert('提示信息','操作失败!','warning');
				}
			}
		});
	};
	
	this.workflowAdd = function(index){
		$("#jobadddlg").dialog("open").dialog("center").dialog("setTitle","添加工作流");
		schedule.queryWorkFlowTree(index);
	};
	this.workflowedit = function(){
		var row=$('#cron_table').datagrid('getSelected');
		$("#jobadddlg-fm").form("clear").form("load",row);
	};
	this.workflowRomove = function(){
		var row=$('#cron_table').datagrid('getSelected');
	};
	this.queryWorkFlowTree = function(index){
		leftMenusTree = $('#chooseworkflow').tree({url:content+"/workflow/queryTree",
			animate:true,
			lines:true,
			checkbox:true,
			onlyLeafCheck:true,
	        onBeforeSelect: function(node){
	        	var tree = $(this).tree;
	        	var isLeaf = tree('isLeaf', node.target);  
	        	if(!isLeaf) {tree('uncheck', node.target);  return ;}
			},
			onExpand:function(node){
	        	var cdata = $('#chooseworkflow').tree('getChildren', node.target);
	        	var gdata = $("#ddv-"+index).datagrid("getRows");
	        	for (var j = 0; j < gdata.length; j++) {
        			for (var i = 0; i < cdata.length; i++) {
        				if(gdata[j].workflowId==cdata[i].id){
        					if(cdata[i].checkState != "checked"){
        						$('#chooseworkflow').tree('update', {
        							target: cdata[i].target,
        							checked:'checked'
        						});
        					}
        				}
        			}
	        	}
			}
		});
	}
	this.saveWorkflow = function(){
		var nodes = $('#chooseworkflow').tree('getChecked');
		var selectNode = $("#cron_table").datagrid("getSelected");
		if(selectNode){
			if(nodes.length>0){
				var params = new Array();
				for (var i = 0; i < nodes.length; i++) {
					var data = {"workflowId":nodes[i].id,"scheduleId":selectNode.id,"name":nodes[i].text};
					params.push(data);
				}
				$.ajax({
					data : JSON.stringify(params),
					dataType:"json",
					type: "POST",
					contentType : 'application/json;charset=utf-8', //设置请求头信息
					url : content+"/scheduleWorkflow/batchsaveOrUpdate",
					error : function(data) {
						$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
						return false;
					},
					success : function(data) {
						if(data>0){//成功
							$.messager.alert('提示信息','添加成功!');
							if(schedule.index!=null){
								$("#ddv-"+schedule.index).datagrid('reload');
								$("#jobadddlg").dialog("close");
							}else{
								$('#cron_table').datagrid('reload');
								$("#jobadddlg").dialog("close");
							}
						}else{
							$.messager.alert('提示信息','添加失败!','warning');
						}
					}
				});
			}
		}
	};
	
	this.editRemove = function(index){
		var row=$('#ddv-'+index).datagrid('getSelected');
		var selectNode = $("#cron_table").datagrid("getSelected");
		if(row){
			row.schedulename = selectNode.name;
			$("#workflowdlg").dialog("open").dialog("center").dialog("setTitle","修改工作流");
			$("#workflowdlg-fm").form("clear").form("load",row);
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	
	this.workflowRemove = function(index){
		var row=$('#ddv-'+index).datagrid('getSelected');
		var selectNode = $("#cron_table").datagrid("getSelected");
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此条数据吗?', function(r){
				if (r){
					$.ajax({
						type:"POST",
						data :row,
						url : content+"/scheduleWorkflow/removeById",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data>0){//成功
								$.messager.alert('提示信息','删除成功!');
								if(index!=null){
									$("#ddv-"+index).datagrid('reload');
									$("#jobadddlg").dialog("close");
								}else{
									$('#cron_table').datagrid('reload');
									$("#jobadddlg").dialog("close");
								}
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	
	this.updateWorkflow = function(){
		var data = $("#workflowdlg-fm").form('getData',true);
		var params = new Array();
		params.push(data);
		$.ajax({
			data : JSON.stringify(params),
			dataType:"json",
			type: "POST",
			contentType : 'application/json;charset=utf-8', //设置请求头信息
			url : content+"/scheduleWorkflow/batchsaveOrUpdate",
			error : function(data) {
				$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
				return false;
			},
			success : function(data) {
				if(data>0){//成功
					$.messager.alert('提示信息','更新成功!');
					if(schedule.index!=null){
						$("#ddv-"+schedule.index).datagrid('reload');
						$("#workflowdlg").dialog("close");
					}else{
						$('#cron_table').datagrid('reload');
						$("#workflowdlg").dialog("close");
					}
				}else{
					$.messager.alert('提示信息','更新失败!','warning');
				}
			}
		});
	};
	this.workflowsort = function(scheduleId,workflowId,sort){
		$.ajax({
			data : {"scheduleId":scheduleId,"sort":sort,"workflowId":workflowId},
			url : content+"/scheduleWorkflow/changeWorkflowSort",
			error : function(data) {
				$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
				return false;
			},
			success : function(data) {
				if(data>0){//成功
					$.messager.alert('提示信息','操作成功!');
					$("#ddv-"+schedule.index).datagrid('reload');
				}else{
					$.messager.alert('提示信息','操作失败!','warning');
				}
			}
		});
	};
	this.manualExecute = function(){
		var row=$('#cron_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要运行此工作流吗?', function(r){
				if (r){
					$("#wfddcontext").html("手动执行开始.....");
					wfdd.dialog('open');
					$(".panel-tool").children().hide();
//					$.messager.progress({ title: '提示', msg: '定时器正在执行，请稍后......',text:''});
					$.ajax({
						data : {"id":row.id},
						type: "POST",
						async: true,
						url : content+"/schedule/manualExecute",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
//							$.messager.progress('close');
							wfdd.dialog('close');
							schedule.getWorkFlowStatus(false);
							$("#wfddcontext").html("");
							return false;
						},
						success : function(data) {
							wfdd.dialog('close');
							schedule.getWorkFlowStatus(false);
							if(data.status){//成功
								$.messager.alert('提示信息','操作成功!');
							}else{
								$.messager.alert('提示信息',data.mess,'warning');
							}
//							$.messager.progress('close');
							$("#wfddcontext").html("");
						}
					});
					schedule.getWorkFlowStatus(true);
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.getWorkFlowStatus = function(b){
			if(!b){
				clearInterval(interval);
			}else{
				interval = setInterval(function(){
					$.ajax({
						url : content+"/schedule/getWorkFlowStatus",
						success : function(data) {
							$("#wfddcontext").html(data.mess);
						}
					});
				}, 200);
			}
	};
	this.queryLogs = function(ids){
		if(ids!=null){
			var id = ids.split("|");
			$("#logsdlg").dialog("open").dialog("center").dialog("setTitle","日志列表");
			$('#cron_table').datagrid("selectRow",id[2]);
			$('#logs_table').datagrid({
		        url: content+"/log/queryList",
		        fitColumns:true,
		        pagination:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
		        	"scid":id[0],
		        	"wfid":id[1]
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
		        	 	{field:'modelname',title:'名称',width:"23%",align:'left'},
				        {field:'type',title:'名称',width:"5%",align:'center',formatter:function(val,row,index){
				        	if(val==0){
				        		return '<font color="FF0033">自动</font>';
				        	}else{
				        		return '<font color="33CC33">手动</font>';
				        	}
				        }},
				        {field:'status',title:'类型',width:"5%",align:'center',formatter:function(val,row,index){
				        	if(val){
				        		return '<font color="33CC33">成功</font>';
				        	}else{
				        		return '<font color="FF0033">失败</font>';
				        	}
				        }},
				        {field:'wfinfo',title:'执行信息',align:'center',width:"12%",  
				            formatter:function(value, row, index){
				            	return '<a id="'+index+'" href="javascripte:void(0)" onClick="schedule.querywfinfos(this.id)" class="wfinfolinkbtn easyui-linkbutton" data-options="plain:true,iconCls:\'icon-more\'">执行流程</a>';
				        }},
				        {field:'createtime',title:'运行时间',width:"18%",align:'center',formatter:function(value, row, index){
				        	var str="";
				        	if(value==null || value ==""){
			            		str = "";
			            	}else{
			            		str = (new Date(value)).format("yyyy-MM-dd hh:mm:ss");
			            	}
			                return str;  
				        }},
				        {field:'message',title:'错误信息',width:"37%",align:'left'}
		        ]],
		        onLoadSuccess:function(){
		        	$(".wfinfolinkbtn").linkbutton();
		        }
			});
		}
	};
	this.querywfinfos = function(index){
		if(index!=null){
			$("#wfinfodlg").dialog("open").dialog("center").dialog("setTitle","执行流程");
			var selectednode = $('#logs_table').datagrid("selectRow",index);
			var row=$('#logs_table').datagrid('getSelected');
			var cronrow=$('#cron_table').datagrid('getSelected');
			if(!row) return ;
			$.ajax({
				data : {"wfid":row.wfid,"logid":row.id},
				type: "POST",
				url:content+"/job/getWorkFlowNodes",
				error : function(data) {
					$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
					$("#wfinfodlg").dialog('close');
					$("#wfinfoimage").html("");
					return false;
				},
				success : function(result) {
					var html="";
					var data = result.allwf;
					var okwf = result.okwf;
					var list = new Array();
					var lastlist = new Array();
					if(data!=null){
						var x = 0;
						var errorindex = data.length;
						if(okwf.length!=data.length){
							errorindex = okwf.length;
						}
						for(var i = 0;i<data.length;i++){
							var imgtype = "";
							if(data[i].nodeType==0)imgtype = "play";
							else if(data[i].nodeType==99)imgtype = "stop";
							else if(data[i].nodeType==1)imgtype = "briefcase";
							if(i>=errorindex){
								if(data.length == errorindex && i!=data.length-1){
									var exetime = i>=okwf.length?0:okwf[i].exeTime;
									html='<a href="javascript:void(0)" data-toggle="tooltip" id="'+data[i].id+'" title="'+"名称"+data[i].name+"。 执行时间："+exetime+'分钟。" class="btn btn-info btn-lg"><span class="glyphicon glyphicon-'+imgtype+'">'+data[i].text+'</span></a><span class="glyphicon glyphicon-ok" style="color: green;border:0px;font-size: 20px">';
								}else{
									html='<a href="javascript:void(0)" data-toggle="tooltip" id="'+data[i].id+'" title="'+"名称"+data[i].name+"。 执行时间："+0+'分钟。"  class="btn btn-info btn-lg" style="background-color: rgb(212, 106, 64);border:0px"><span class="glyphicon glyphicon-'+imgtype+'"  style="background-color: rgb(212, 106, 64);border:0px">'+data[i].text+'</span></a><span class="glyphicon glyphicon-remove" style="color:rgb(212, 106, 64);border:0px;font-size: 20px">';
								}
							}else{
								var exetime = i>=okwf.length?0:okwf[i].exeTime;
								html='<a href="javascript:void(0)" data-toggle="tooltip" id="'+data[i].id+'" title="'+"名称"+data[i].name+"。 执行时间："+exetime+'分钟。" class="btn btn-info btn-lg"><span class="glyphicon glyphicon-'+imgtype+'">'+data[i].text+'</span></a><span class="glyphicon glyphicon-ok" style="color: green;border:0px;font-size: 20px">';
							}
							list.push(html);
							if((i+1)%5==0){
								x++;
								map.put(x,list);
								list = new Array();
							}else{
								if((data.length-5*x)<5){
									lastlist.push(html);
								}
							}
						}
						if(lastlist.length>0){
							x++;
							map.put(x,lastlist);
						}
						if(map!=null){
							html ='<table class="table"><caption style="width:300px;margin:auto;"><h3>'+cronrow.remark+'</h3></caption><tbody';
							for (var i = 1; i <= x; i++) {
								var list = map.get(i);
								if(i%2==0){//偶数行-箭头向右
									html += '<tr>';
									if(list.length==5){//正常一行
										for (var j = list.length-1; j >= 0; j--) {
											if(j == 0){
												html+='<td>'+list[j]+'</td>';
											}else{
												html+='<td>'+list[j]+'</td>'+'<td align="center" style="vertical-align: middle;">'+'<span class="glyphicon glyphicon-arrow-left"></span>'+'</td>';
											}
										}
										if(map.get(i+1)!=null){//有下一行
											html+='</tr>';
											html+='<tr>';
											html+='<td align="center">'+'<span class="glyphicon glyphicon-arrow-down"></span>'+'</td><td colspan="8"></td>';
											html+='</tr>';
										}else{//没有下一行
											html+='</tr>';
										}
									}else{//最后一行
										var surplus = 9-list.length;
										html += '<td  colspan="'+surplus+'"></td>';
										for (var j = list.length-1; j >= 0; j--) {
											if(j == 0){
												html+='<td>'+list[j]+'</td>';
											}else{
												html+='<td>'+list[j]+'</td>'+'<td align="center" style="vertical-align: middle;">'+'<span class="glyphicon glyphicon-arrow-left" style="width:3%;margin-left:1%;margin-right:1%"></span>'+'</td>';
											}
										}
										html+='</tr>';
									}
								}else{//奇数行-箭头向左如果有下一行且最后一个箭头向下
									html += '<tr>';
									for (var j = 0; j < list.length; j++) {
										if(j == (list.length-1)){
											html+='<td>'+list[j]+'</td>';
										}else{
											html+='<td>'+list[j]+'</td>'+'<td align="center" style="vertical-align: middle;">'+'<span class="glyphicon glyphicon-arrow-right" style=""></span>'+'</td>';
										}
									}
									if(list.length==5){//正常一行
										if(map.get(i+1)!=null){//有下一行
											html+='</tr>';
											html+='<tr><td colspan="8"></td>';
											html+='<td align="center">'+'<span class="glyphicon glyphicon-arrow-down" style=""></span>'+'</td>';
											html+='</tr>';
										}else{//没有下一行
											html+='</tr>';
										}
									}else{//最后一行
										html+='</tr>';
									}
								}
							}
							html +='</tbody></table>';
							$("#wfinfoimage").html(html);
							map.clear();
						}
					}
				}
			});
		}
	};
	this.cron_search = function(){
		$("#cron_table").datagrid("load",{
			"cron":$("#crons").val(),
			"name":$("#names").val(),
			"groupname":$("#groups").val()
		});
	};
}
