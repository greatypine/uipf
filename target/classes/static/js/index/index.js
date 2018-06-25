var indexOption;
var leftMenusTree;
$(function(){
	init();
});

function init(){
	initdata();
	if(user.user.roleids.indexOf("4")!=-1){//管理员
		indexOption.initUserSubscribeListener();//监听用户登录信息
		indexOption.initUserLoginInfoListener();//监听用户登录信息
	}
	if(user.user.roleids.indexOf("1")!=-1) {//门店店长
		indexOption.initUserSubscribeListener();//监听用户登录信息
		indexOption.initUserLoginInfoListener();//监听用户登录信息
	}
	if(user.user.roleids.indexOf("10")!=-1) {//前台
		indexOption.initUserSubscribeListener();//监听用户登录信息
	}
	if(user.user.roleids.indexOf("11")!=-1) {//前台
		indexOption.initCounsolerSubscribeListener();//监听用户登录信息
	}
}

function initdata(){
	amq.init({ uri: content+'/amq', logging: true, timeout: 45, clientId:(new Date()).getTime().toString() });
	indexOption = new IndexOption();
	indexOption.queryMenus({"pid":0});
	$(".loginOut").bind("click",function(){
		indexOption.logout();
	});
	$(".changepwd").bind("click",function(){
		indexOption.changepwd();
	});
}

function IndexOption(){
	this.queryMenus = function(params){
		leftMenusTree = $('#myTree').tree({url:content+"/menu/queryMenus?pid="+params.pid,
			animate:true,
			lines:true,
			onBeforeExpand:function(node){
				$('#myTree').tree('options').url = content+'/menu/queryMenus?pid='+node.id;
	        },
	        onBeforeSelect: function(node){
	        	var tree = $(this).tree;
	        	var isLeaf = tree('isLeaf', node.target);  
	        	if(!isLeaf) {return ;}
			},
			onClick: function(node){
				var state=node.state;
	            if(state !="open"){
	            	return null;
	            }else{
	            	if($("#cnt-tabs").tabs('exists',node.text)){
	            		$('#cnt-tabs').tabs('select',node.text);
	            		return ;
	            	}
	            	var content_ = '<iframe frameborder="0" src="'+content+node.jsp+'" style="width:100%;height:99.3%;padding:0;margin:0;"></iframe>';
	            	$('#cnt-tabs').tabs('add',{
	            		title:node.text,
	            		content:content_,
	            		fit:true,
	            		width:"100%",
	            		height:"100%",
	            		border:false,
	            		closable:true
	            	});
	            }
			}
		});
	};
	
	this.logout =  function(){
		$.messager.confirm('提示信息', '你确认要退出系统?', function(r){
			if (r){
				window.location.href=content+"/logout";
			}
		});
	};
	
	this.changepwd =  function(){
		$("#systemuserdlg").dialog("open").dialog("center").dialog("setTitle","更新用户信息");
		user.user.roleid = user.role[0].id;
		$("#systemuserdlg-fm").form("clear").form("load",user.user);
		$.messager.alert("提示信息","修改成功后请重新登录系统！");
	};
	
	this.initUserLoginInfoListener = function(){
	    var myUserHandler ={
	        rcvMessage: function(message){
	            //接收到消息后，自己的业务处理逻辑 (new Date()).getTime().toString()
	        	if(message.childNodes.length > 0){
	        		cu.bottomRight(message.childNodes[0].nodeValue);
	            }else{
	            	cu.bottomRight(message.nodeValue);
	            }
	        }
	    };
	    amq.addListener('user_msg','topic://user_msg',myUserHandler.rcvMessage);
	};
	this.initUserSubscribeListener = function(){
	    var myUserHandler ={
	        rcvMessage: function(message){
	            //接收到消息后，自己的业务处理逻辑 (new Date()).getTime().toString()
	        	cu.bottomRight(message.textContent);
	        }
	    };
	    amq.addListener('back_subscribe_msg','topic://back_subscribe_msg',myUserHandler.rcvMessage);
	};
	this.initCounsolerSubscribeListener = function(){
		 var myUserHandler ={
	        rcvMessage: function(message){
	            //接收到消息后，自己的业务处理逻辑 (new Date()).getTime().toString()
	        	var tcs = message.textContent.split("|");
	        	if(tcs[0]==user.user.id) cu.bottomRight(tcs[1]);
	        }
	    };
	    amq.addListener('before_subscribe_msg','topic://before_subscribe_msg',myUserHandler.rcvMessage);
	};
	
}
