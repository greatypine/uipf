var indexOption;
var leftMenusTree;
var reports;
var colors = ["darkseagreen","thistle","pink","thistle","powderblue","antiquewhite","lightgrey","plum"];
if(!cu.isPC()){
	$("#airreprot").remove();
	$("#glyphicontime").remove();
	$("#companyandtime").remove();
	$("#indexSubscribeQuery").remove();
	$("#indexworkforceManagementQuery").remove();
	$("#indexcompanyreport").css("width","100%");
	$("#indexwest").css("width","30%");
	$("#indexwest").css("indexcenter","70%");
}
$(function(){
	init();
});

function init(){
	initdata();
	if(cu.hasRoles("sadmin,q_area_shopManager,generalManager")){//管理员
		indexOption.initUserLoginInfoListener();//监听用户登录信息
	}
}

function initdata(){
	indexOption = new IndexOption();
	reports = new Reports();
	indexOption.queryMenus({"pid":0});
	$(".loginOut").bind("click",function(){
		indexOption.logout();
	});
	$(".changepwd").bind("click",function(){
		indexOption.changepwd();
	});
	var wh = $(window).height();
	if(cu.isPC()){
		if(wh>1100){
			$("#north").css("height","4%");
		}else if(wh>900 && wh<1100){
			$("#north").css("height","5%");
		}else if(wh >700 && wh <900){
			$("#north").css("height","6%");
		}
	}
	$(".l-btn-text").css({"font-size":"16px","padding-top":"5px"});
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
			formatter:function(node){
				var s = '';
				s = '<font size="3">'+node.text+'</font>';
				return s;
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
	            		iconCls:node.iconCls,
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
	    amq.addListener(user.user.companyid+'user_msg','topic://'+user.user.companyid+'user_msg',myUserHandler.rcvMessage);
	};
	
//	this.initCounsolerSubscribeListener = function(){
//		 var myUserHandler ={
//	        rcvMessage: function(message){
//	            //接收到消息后，自己的业务处理逻辑 (new Date()).getTime().toString()
//	        	var tcs = message.textContent.split("|");
//	        	if(tcs[0]==user.user.id) cu.bottomRight(tcs[1]);
//	        }
//	    };
//	    amq.addListener(user.user.companyid+'before_subscribe_msg','topic://before_subscribe_msg',myUserHandler.rcvMessage);
//	};
	
}
