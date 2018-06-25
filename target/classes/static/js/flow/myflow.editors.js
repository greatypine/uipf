var flowOption;
var workflowdata;
var map;
(function($){
var myflow = $.myflow;
$.extend(true, myflow.editors, {
	inputEditor : function(){
		var _props,_k,_div,_src,_r;
		this.init = function(props, k, div, src, r){
			_props=props; _k=k; _div=div; _src=src; _r=r;
			var input;
			if(k=="name"){
				input = $('<label style="width:150px;"/>');
				if(workflowdata !=null || workflowdata != undefined){
					var name = workflowdata.props.props.name.value;
					var key = workflowdata.props.props.key.value;
					$('#'+_div).attr("key",key);
					input.html(name);
				}
			}else if(k=="key"){
				input = $('<input style="width:100%;"/>');
				if(workflowdata !=null || workflowdata != undefined){
					var key = workflowdata.props.props.key.value;
					input.val(key);
				}
			}else{
				input = $('<input style="width:100%;"/>');
				input.val(props[_k].value);
			}
			input.appendTo('#'+_div);
			if(k=="key"){
				$('#'+_div).data('editor', this).parent().parent().hide();
			}else{
				$('#'+_div).data('editor', this);
			}
		}
		this.destroy = function(){
			$('#'+_div+' input').each(function(){
				_props[_k].value = $(this).val();
			});
			$('#'+_div+' input').each(function(){
				_props[_k].value = $(this).val();
			});
		}
	},
	selectEditor : function(arg){
		var _props,_k,_div,_src,_r;
		this.init = function(props, k, div, src, r){
			_props=props; _k=k; _div=div; _src=src; _r=r;
			var sle = $('<select style="width:100%;"/>').val(props[_k].value).change(function(){
				props[_k].value = $(this).val();
			}).appendTo('#'+_div);
			if(arg.type == 0){//文件
				$.ajax({
				   type: "GET",
				   url: arg.url,
				   success: function(data){
					  var opts = eval(data);
					 if(opts && opts.length){
						 sle.append('<option value="0">请选择</option>');
						for(var idx=0; idx<opts.length; idx++){
							sle.append('<option value="'+opts[idx].value+'">'+opts[idx].name+'</option>');
						}
//						sle.val(_props[_k].value);
					 }
				   }
				});
			}else if(arg.type == 1) {//类型
				var node = eval("("+_src.toJson()+")");
				var code = _src.getId();
				var typeid = node.props.temp1.value;
				$.ajax({ async: false, url : content+"/workflow/getWorkflowJobType",
					type:"GET",
					data:"",
					success : function(data) {
						if(data!=null){
							sle.append('<option value="0">请选择</option>');
							for(var idx=0; idx<data.length; idx++){
								if(typeid!=data[idx].value){
									sle.append('<option id="jobtype'+data[idx].value+'" class="jobtype" value="'+data[idx].value+'">'+data[idx].name+'</option>');
								}else{
									sle.append('<option id="jobtype'+data[idx].value+'" class="jobtype" selected="selected" value="'+data[idx].value+'">'+data[idx].name+'</option>');
								}
							}
							flowOption.bindTaskTypeChange(code,node);
						}
					}
				});
			}else if(arg.type == 2) {//任务
				var node = eval("("+_src.toJson()+")");
				var code = _src.getId();
				var typeid = node.props.temp1.value;
				var jobid = node.props.temp2.value;
				$.ajax({ async: false, url : content+"/workflow/getWorkflowJobList",
					type: "POST",
					data:{"typeid":typeid,"jobid":jobid},
					success : function(data) {
						if(data!=null){
							sle.append('<option value="0">请选择</option>');
							for(var idx=0; idx<data.length; idx++){
								if(jobid==""){
									sle.append('<option id="task'+data[idx].type+'" class="taskjob" value="'+data[idx].value+'">'+data[idx].name+'</option>');
								}else{
									sle.append('<option id="task'+data[idx].type+'" selected="selected" class="taskjob" value="'+data[idx].value+'">'+data[idx].name+'</option>');
								}
							}
						}
					}
				});
			}
			$('#'+_div).data('editor', this);
		};
		this.destroy = function(){
			$('#'+_div+' input').each(function(){
				_props[_k].value = $(this).val();
			});
			$('#'+_div+' input').each(function(){
				_props[_k].value = $(this).val();
			});
		};
	}
});
})(jQuery);

$(function(){
	init();
});

function init(){
	map = new Map();
	flowOption = new FlowOption();
	flowOption.initWorkFlowView();
}
function FlowOption(){
	this.initWorkFlowView=function(){
		var viewdata = $("#viewdata").val();
//		var testdata = eval("({states:{rect1:{type:'start',text:{text:'开始'}, attr:{ x:384, y:10, width:101, height:50}, props:{text:{value:'开始'},temp1:{value:''},temp2:{value:''}}},rect2:{type:'task',text:{text:'任务1'}, attr:{ x:386, y:116, width:100, height:50}, props:{text:{value:'任务1'},temp1:{value:''},temp2:{value:''},assignee:{value:''},form:{value:''},desc:{value:''}}},rect3:{type:'fork',text:{text:'分支'}, attr:{ x:384, y:209, width:103, height:50}, props:{text:{value:'分支'},temp1:{value:''},temp2:{value:''}}},rect4:{type:'task',text:{text:'任务2'}, attr:{ x:192, y:317, width:100, height:50}, props:{text:{value:'任务2'},temp1:{value:''},temp2:{value:''},assignee:{value:''},form:{value:''},desc:{value:''}}},rect5:{type:'task',text:{text:'任务3'}, attr:{ x:385, y:317, width:100, height:50}, props:{text:{value:'任务3'},temp1:{value:''},temp2:{value:''},assignee:{value:''},form:{value:''},desc:{value:''}}},rect6:{type:'task',text:{text:'任务4'}, attr:{ x:556, y:320, width:100, height:50}, props:{text:{value:'任务4'},temp1:{value:''},temp2:{value:''},assignee:{value:''},form:{value:''},desc:{value:''}}},rect7:{type:'join',text:{text:'合并'}, attr:{ x:386, y:416, width:103, height:50}, props:{text:{value:'合并'},temp1:{value:''},temp2:{value:''}}},rect8:{type:'end',text:{text:'结束'}, attr:{ x:382, y:632, width:107, height:50}, props:{text:{value:'结束'},temp1:{value:''},temp2:{value:''}}},rect9:{type:'task',text:{text:'任务5'}, attr:{ x:384, y:528, width:100, height:50}, props:{text:{value:'任务5'},temp1:{value:''},temp2:{value:''},assignee:{value:''},form:{value:''},desc:{value:''}}}},paths:{path10:{from:'rect1',to:'rect2', dots:[],text:{text:'TO 任务1'},textPos:{x:37,y:-4}, props:{text:{value:''}}},path11:{from:'rect2',to:'rect3', dots:[],text:{text:'TO 分支'},textPos:{x:56,y:-1}, props:{text:{value:''}}},path12:{from:'rect3',to:'rect5', dots:[],text:{text:'TO 任务3'},textPos:{x:24,y:-5}, props:{text:{value:''}}},path13:{from:'rect5',to:'rect7', dots:[],text:{text:'TO 合并'},textPos:{x:41,y:8}, props:{text:{value:''}}},path14:{from:'rect7',to:'rect9', dots:[],text:{text:'TO 任务5'},textPos:{x:36,y:-5}, props:{text:{value:''}}},path15:{from:'rect9',to:'rect8', dots:[],text:{text:'TO 结束'},textPos:{x:32,y:0}, props:{text:{value:''}}},path16:{from:'rect3',to:'rect4', dots:[{x:244,y:232}],text:{text:'TO 任务2'},textPos:{x:0,y:-10}, props:{text:{value:'TO 任务2'}}},path17:{from:'rect4',to:'rect7', dots:[{x:242,y:435}],text:{text:'TO 合并'},textPos:{x:-3,y:17}, props:{text:{value:'TO 合并'}}},path18:{from:'rect3',to:'rect6', dots:[{x:607,y:234}],text:{text:'TO 任务4'},textPos:{x:0,y:-10}, props:{text:{value:'TO 任务4'}}},path19:{from:'rect6',to:'rect7', dots:[{x:607,y:439}],text:{text:'TO 合并'},textPos:{x:-8,y:16}, props:{text:{value:'TO 合并'}}}},props:{props:{name:{value:'新建流程'},key:{value:''},desc:{value:''}}}})");
		workflowdata = JSON.parse(viewdata);
		if(workflowdata!=null && "states" in workflowdata){
			var states = workflowdata.states;
			if(states!=null){
				for ( var rect in states) {
					if(states[rect].type=='start' || states[rect].type=='task' || states[rect].type=='end'){
						map.put(rect,states[rect].props);
					}
				}
			}
		}
		$('#myflow').myflow({restore:workflowdata});
	};
	this.saveWorkflow = function(data){;;;
		var states = data.states;
		var paths = data.paths;
		var props = data.props;
		var paramArray = new Array();
		for ( var state in states) {
			var rect = states[state];
			var param = {};
			param.code = state;
			var node_type = rect.type;
			if(node_type=="start"){
				param.node_type = 0;
			}else if(node_type=="task"){
				param.node_type = 1;
			}else if(node_type=="fork"){
				param.node_type = 2;
			}else if(node_type=="join"){
				param.node_type = 3;
			}else if(node_type=="end"){
				param.node_type = 99;
			}else{
				param.node_type = 99;
			}
			var text = rect.text.text;
			param.text = text=="null"?"":text;
			param.x = rect.attr.x;
			param.y = rect.attr.y;
			param.width = rect.attr.width;
			param.height = rect.attr.height;
			if(text !="" && text!=null){ param.text = rect.props.text.value; }
			param.props1 = (rect.props.temp1==undefined)?"":(rect.props.temp1.value=="null")?"":rect.props.temp1.value;
			param.props2 = (rect.props.temp2==undefined)?"":(rect.props.temp2.value=="null")?"":rect.props.temp2.value;
			param.props3 = (rect.props.temp3==undefined)?"":(rect.props.temp3.value=="null")?"":rect.props.temp3.value;
//			param.props1 = map.get(rect).props.temp1;
//			param.props2 = map.get(rect).props.temp2;
//			param.props3 = map.get(rect).props.temp3;
			param.model = 0;
			paramArray.push(param);
		}
		for ( var p in paths) {
			var path = paths[p];
			var param = {};
			param.code = p;
			param.start = path.from;
			param.end = path.to
			param.path_dots = path.dots
			param.text =  (path.text.text=="null")?"":path.text.text;
			param.x = path.textPos.x;
			param.y = path.textPos.y;
			param.path_props_text = (path.props.text.value=="null")?"":path.props.text.value;
			param.model = 1;
			paramArray.push(param);
		}
		var param = {};
		param.name = props.props.name.value;
		param.id = props.props.key.value;
		param.remark = props.props.desc.value=="null"?"":props.props.desc.value;
		param.model = 2;
		paramArray.push(param);
//		console.log(paramArray);
		$.ajax({ async: false, url : content+"/job/saveJobs",
			data:JSON.stringify(paramArray),
			dataType:"json",
			type: "POST",
			contentType : 'application/json;charset=utf-8', //设置请求头信息
			success : function(data) {
				if(data){
					alert('保存成功!'); 
				}else{
					alert('操作 失败!'); 
				}
			}
		});
	};
	this.bindTaskTypeChange = function(code,node){
		$("#ptemp1").find("select").change(function(){
			var taskselect = $("#ptemp2").find("select");
			taskselect.html("");
			var _this = $(this);
			var type = _this.val();
			node.props.temp1.value=type;
			map.put(code,node);
			if(type==0){
				$(".task").remove();
				return ;
			}
			$.ajax({ async: false, url : content+"/workflow/getWorkflowJobList",
				type:"POST",
				data:{"typeid":type},
				success : function(data) {
					if(data!=null){
						taskselect.append('<option value="0">请选择</option>');
						for(var idx=0; idx<data.length; idx++){
							taskselect.append('<option id="task'+data[idx].type+'" class="task" value="'+data[idx].value+'">'+data[idx].name+'</option>');
						}
					}
				}
			});
		});
	};
	this.removeNode = function(node){
		var id = node.getId();
		$.ajax({ async: false, url : content+"/job/removeNode",
			type:"POST",data:{"code":id,"workflowId":wfid},
			success : function(data) {
				if(!data){
					alert('操作失败');
				}
			}
		});
	};
}
