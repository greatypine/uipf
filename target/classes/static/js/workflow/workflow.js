var workflow;

$(function(){
	init();
});
function init(){
	initData();
}
function initData(){
	workflow = new WorkFlow();
	workflow.queryWorkFlowTree();
}

function WorkFlow(){
	this.queryWorkFlowTree = function(index){
		leftMenusTree = $('#chooseworkflow').tree({url: content+"/workflow/queryTree",
			animate:true,
			lines:true,
			checkbox:false,
			onlyLeafCheck:true,
			queryParams: {},
			onBeforeLoad: function (node, param) {
                if(node){
                    param.pid = node.id;
                }
            },
	        onBeforeSelect: function(node){
	        	var tree = $(this).tree;
	        	var isLeaf = tree('isLeaf', node.target);  
//	        	if(!isLeaf) {tree('uncheck', node.target);  return ;}
			},
			onClick:function(node){
				var tree = $(this).tree;
	        	var isLeaf = tree('isLeaf', node.target);  
	        	if(!isLeaf) {tree('uncheck', node.target);  return ;}
	        	var fm = $("#workflowiframe");
	        	var op=fm.attr('class');
	        	fm[0].contentWindow.location=content+op+'?wfid='+node.id
	        	workflow.setIframeHeight(fm.attr("id"));
			},
			onContextMenu: function(e, node){ //给结点添加右键菜单   
                e.preventDefault();  //阻止右键默认事件
                $('#chooseworkflow').tree('select', node.target);
                var root = null;
                try{
                	root = $('#chooseworkflow').tree('getParent', node.target)//判断该结点有没有父结点  
                }catch(e){
                	root = null;
                	console.log("右键对象获取失败！");
                }
                if(root == null){//若成立则为根结点，添加一个右键样式，可以添加子节点  
                    $('#parentNode').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                }else{
                	$('#leaf').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            }
		});
	};
	this.setIframeHeight = function(id){
	    try{
	        var iframe = document.getElementById(id);
	        var iframeheight = 0;
	        if(iframe.attachEvent){
	            iframe.attachEvent("onload", function(){
	            	iframeheight =  iframe.contentWindow.document.documentElement.scrollHeight;
	            	iframe.height = iframeheight;
	            });
	            return;
	        }else{
	            iframe.onload = function(){
	            	iframeheight = iframe.contentDocument.body.scrollHeight;
	            	iframe.height = iframeheight;
	            };
	            return;
	        }
	    }catch(e){
	        throw new Error('setIframeHeight Error');
	    }
	};
	this.addNode = function(){
		$("#workflowdlg").dialog("open").dialog("center").dialog("setTitle","添加对象");
		$("#workflowdlg-fm").form("clear");
	};
	this.updateNode = function(){
		var node = $('#chooseworkflow').tree('getSelected');
		$("#workflowdlg").dialog("open").dialog("center").dialog("setTitle","更新对象");
		$("#workflowdlg-fm").form("clear").form("load",node);
	};
	this.removeNode = function(){
		var node = $('#chooseworkflow').tree('getSelected');
		if(node){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.ajax({
						data :{id:node.id},
						url : content+"/workflow/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								var fm = $("#workflowiframe");
								var op=fm.attr('class');
					        	fm[0].contentWindow.location=content+op+'?wfid=-1'
					        	workflow.setIframeHeight(fm.attr("id"));
								$('#chooseworkflow').tree('reload');
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
		$('#workflowdlg-fm').form('submit',{
			url:content+"/workflow/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				if(result){
					$('#workflowdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$.messager.progress('close');
					$('#workflowdlg').dialog('close');
					$('#chooseworkflow').tree('reload');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}
		});
	};
}