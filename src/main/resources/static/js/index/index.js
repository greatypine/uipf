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
	if(cu.hasRoles("sadmin,q_area_shopManager,generalManager,q_admin,h_admin")){//管理员
		indexOption.initUserSubscribeListener();//监听后台预约信息
		indexOption.initUserLoginInfoListener();//监听用户登录信息
		indexOption.getBackSubscribeDayAllInfo();//查询登录时监听当天预约信息
	}
	if(cu.hasRoles("q_receptionist,q_option,q_counselor")) {//前台
		indexOption.initUserSubscribeListener();//监听后台预约信息
	}
	if(cu.hasRoles("h_option")) {//预约人员
		indexOption.getSubscribeReceptionInfo();//查询监听预约接诊情况
	}
	if(cu.hasRoles("sadmin,generalManager,q_area_shopManager")){
		//注释
		indexOption.initQCardInfo();
		indexOption.initQReport();
		indexOption.initWFCompant();
	}
	if(cu.hasRoles("q_admin,q_counselor,q_option,q_receptionist")){
		indexOption.initQCardInfo();
		indexOption.initSBCompant();
		indexOption.initWFCompant();
	}
	if(cu.hasRoles("h_admin,h_option")) {
		indexOption.initSBCompant();
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
	this.initUserSubscribeListener = function(){
	    var myUserHandler ={
	        rcvMessage: function(message){
	            //接收到消息后，自己的业务处理逻辑 (new Date()).getTime().toString()
	        	cu.bottomRight(message.textContent);
	        }
	    };
	    amq.addListener(user.user.companyid+'back_subscribe_msg','topic://'+user.user.companyid+'back_subscribe_msg',myUserHandler.rcvMessage);
	};
	this.getSubscribeReceptionInfo = function(){
		$.ajax({
			type:"POST",
			dataType:"json",
			url : content+"/customerSubscribe/querySubscribeReceptionInfo",
			error : function(data) {
				return false;
			},
			success : function(data) {
				if(data!=null){
					cu.bottomRight(cu.replaceAll(data.mess,":","<br>"));
				}
			}
		});
	};
	
	this.getBackSubscribeDayAllInfo = function(){
		$.ajax({
			type:"POST",
			dataType:"json",
			url : content+"/customerSubscribe/querySubscribeInfo",
			error : function(data) {
				return false;
			},
			success : function(data) {
				if(data!=null){
					cu.bottomRight(cu.replaceAll(data.mess,":","<br>"));
				}
			}
		});
	};
	
	this.initQCardInfo = function(){
		$.ajax({
			type:"POST",
			dataType:"json",
			url : content+"/common/queryIndexUserCount",
			error : function(data) {
				return false;
			},
			success : function(data) {
				if(data!=null){
					indexOption.assenblyUserCount(data);
				}
			}
		});
	}
	this.assenblyUserCount = function(data){
		var sd = cu.getCurrentDateMonth();
		$("#cardh1").html("前台人员信息统计【"+sd+"】");
		var html = '<ul id="cards-split" class="cards-split transition">';
		var counsolers = data.counsoler;
		var therapeutists = data.therapeutist;
		/**
		 * 循环咨询师和治疗师
		 */
		for (var i = 0; i < counsolers.length; i++) {
			var f = false
			var therapeutist = null;
			for (var j = 0; j < therapeutists.length; j++) {
				if(counsolers[i].role_sign==therapeutists[j].role_sign && counsolers[i].nickname==therapeutists[j].nickname){
					f = true;
					therapeutist = therapeutists[j];
					therapeutists.remove(therapeutist);
					break;
				}
			}
			if(f && therapeutist!=null){
				html += indexOption.createCardHtml(i,counsolers[i],therapeutist);
			}else{
				html += indexOption.createCardHtml(i,counsolers[i],null);
			}
		}
		/*
		 * 循环治疗师
		 */
		for (var i = 0; i < therapeutists.length; i++) {
			html += indexOption.createCardHtml(i+counsolers.length,null,therapeutists[i]);
		}
		html+='</ul>';
		$("#cardcontent").html(html);
		var al = counsolers.length+therapeutists.length;
		for (var i = 0; i < al; i++) {
			var l = i*220;
			$(".ul.cards-split.transition li.card.card-"+i).css("left",l+"px");
		}
		$("#cards-split").css("width",al*220+"px");
		$('ul.cards-split').on('click', function () {
	        $(this).toggleClass('transition');
	    });
	}
	
	this.createCardHtml = function(i,data,data1){
		var html = "";
		if(data!=null){
			html ='<li class="card card-'+i+'" style="background-color:'+colors[i]+'"><img src="'+content+'/'+data.image_path+'"/>'
				html+='<div class="content">'
					html+='<h1><i class="fa fa-user-o"></i>&nbsp;'+data.nickname+'</h1>'
					html+='<p style="padding-top:3px;"><i class="fa fa-support"></i>&nbsp;咨询总人数：'+data.employeeTotalOrder+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;咨询初诊人数：'+data.CHUZHEN+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;咨询复诊人数：'+data.FUZHEN+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;咨询流失人数：'+data.employeeWastageOrder+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;咨询总金额：'+data.total_amont+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;咨询初诊金额：'+data.c_total_amount+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;咨询复诊金额：'+data.f_total_amount+'</p>'
					if(data1!=null){
						html+='<p><i class="fa fa-support"></i>&nbsp;治疗总人数：'+data1.employeeTotalOrder+'</p>'
						html+='<p><i class="fa fa-support"></i>&nbsp;治疗初诊人数：'+data1.CHUZHEN+'</p>'
						html+='<p><i class="fa fa-support"></i>&nbsp;治疗复诊人数：'+data1.FUZHEN+'</p>'
						html+='<p><i class="fa fa-support"></i>&nbsp;治疗流失人数：'+data1.employeeWastageOrder+'</p>'
						html+='<p><i class="fa fa-support"></i>&nbsp;治疗总金额：'+data1.total_amont+'</p>'
						html+='<p><i class="fa fa-support"></i>&nbsp;治疗初诊金额：'+data1.c_total_amount+'</p>'
						html+='<p><i class="fa fa-support"></i>&nbsp;治疗复诊金额：'+data1.f_total_amount+'</p>'
					}
				html+='</div>'
			html+='</li>'
		}else{
			html ='<li class="card card-'+i+'" style="background-color:'+colors[i]+'"><img src="'+content+'/'+data1.image_path+'"/>'
				html+='<div class="content">'
					html+='<h1><i class="fa fa-user-o"></i>&nbsp;'+data1.nickname+'</h1>'
					html+='<p style="padding-top:3px;"><i class="fa fa-support"></i>&nbsp;治疗总人数：'+data1.employeeTotalOrder+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;治疗初诊人数：'+data1.CHUZHEN+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;治疗复诊人数：'+data1.FUZHEN+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;治疗流失人数：'+data1.employeeWastageOrder+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;治疗总金额：'+data1.total_amont+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;治疗初诊金额：'+data1.c_total_amount+'</p>'
					html+='<p><i class="fa fa-support"></i>&nbsp;治疗复诊金额：'+data1.f_total_amount+'</p>'
				html+='</div>'
			html+='</li>'
		}
		return html;
	}
	
	this.initQReport = function(){
		$.ajax({
			type:"POST",
			dataType:"json",
			url : content+"/common/queryIndexCompanyCount",
			error : function(data) {
				return false;
			},
			success : function(data) {
				if(data!=null){
					reports.initQReport(data);
				}
			}
		});
	};
	
	this.initWFCompant = function(){
		var columns = this.getcolumns();
		workforceManagementQuery_table = $('#workforceManagementQuery_table').datagrid({
		        url: content+"/common/queryWorkforceList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        columns:[columns],
				onDblClickRow:function(index,row){
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(data){
					if(data.total==0){
					}else{
					}
				}
			});
	};
	//----------
	this.getcolumns = function(){
		var cls = new Array();
		var year = cu.getCurrentYear();
		var month = cu.getCurrentMonth();
		var day = cu.getCurrentDay();
		var ld = cu.getLastMonthDay(year,month);
		var sd = 0;
		var ed = 0;
		var clun = {field:'username',title:'姓名',width:'14%',align:'center'};
		cls.push(clun);
		if(ld-day<=7){
			var nextmonth = cu.month_add(new Date(),1);
			sd = day;
			if(ld-day==0){//月末
				var edate = new Date(nextmonth);
				var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
				year = edate.getFullYear();
				month = edate.getMonth();
				ed = 1;
				sd = 1;
				month = (Number(month)<10)?'0'+Number(month):month;
				for (var i = 0; i < 6; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
			}else if(ld-day==1){
				var edate = new Date(nextmonth);
				for (var i = 0; i < 2; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
				year = edate.getFullYear();
				month = edate.getMonth();
				ed = 2;
				sd = 1;
				month = (Number(month)<10)?'0'+Number(month):month;
				for (var i = 0; i < 5; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
				
			}else if(ld-day==2){
				var edate = new Date(nextmonth);
				for (var i = 0; i < 3; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
				year = edate.getFullYear();
				month = edate.getMonth();
				ed = 3;
				sd = 1;
				month = (Number(month)<10)?'0'+Number(month):month;
				for (var i = 0; i < 4; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
			}else if(ld-day==3){
				var edate = new Date(nextmonth);
				for (var i = 0; i < 4; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
				year = edate.getFullYear();
				month = edate.getMonth();
				ed = 4;
				sd = 1;
				month = (Number(month)<10)?'0'+Number(month):month;
				for (var i = 0; i < 3; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
			}else if(ld-day==4){
				var edate = new Date(nextmonth);
				for (var i = 0; i < 5; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
				year = edate.getFullYear();
				month = edate.getMonth();
				ed = 5;
				sd = 1;
				month = (Number(month)<10)?'0'+Number(month):month;
				for (var i = 0; i < 2; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
			}else if(ld-day==5){
				var edate = new Date(nextmonth);
				for (var i = 0; i < 6; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
				year = edate.getFullYear();
				month = edate.getMonth();
				ed = 6;
				sd = 1;
				month = (Number(month)<10)?'0'+Number(month):month;
				for (var i = 0; i < 1; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
			}else if(ld-day==6){
				var edate = new Date(nextmonth);
				for (var i = 0; i < 7; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
				year = edate.getFullYear();
				month = edate.getMonth();
				ed = 7;
				sd = 1;
				month = (Number(month)<10)?'0'+Number(month):month;
				for (var i = 0; i < 0; i++) {
					var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
					sd++;
				}
			}
		}else{
			sd = day;
			ed = day+7;
			month = (Number(month)<10)?'0'+Number(month):month;
			for (var i = 0; i < 7; i++) {
				var cl = {field:'day'+(sd),title:year+""+month+((sd<10)?['0'+(sd)]:sd),width:'12.5%',align:'center',formatter:function(val,row){
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
				sd++;
			}
		}
//		console.log(cls);
		return cls;
	};
	this.formatval = function(val){
		if(val=='休'){
    		return '<font color="FF0033"><b>'+val+'<b></font>';
    	}else if(val=='早'){
    		return '<font color="#009933">'+val+'</font>';
    	}else if(val=='中'){
    		return '<font color="#CC9933">'+val+'</font>';
    	}else if(val=='晚'){
    		return '<font color="#660033">'+val+'</font>';
    	}
	}
	this.initSBCompant = function(){
		$('#subscribeQuery_table').datagrid({
		        url: content+"/common/querySubscribeList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        columns:[[
	        	 	{field:'id',title:'编号',hidden:true},
			        {field:'customerName',title:'客户姓名',width:"12%",align:'center'},
			        {field:'customerPhone',title:'客户手机',width:"14%",align:'center'},
			        {field:'sex',title:'性别',width:"6%",align:'center',formatter:function(val,row){
			        	if(val==0){
				        	return '<font color="FF0033">女</font>';
				        }else if(val==1){
				        	return '<font color="33CC33">男</font>';
				        }else {
				        	return '<font color="00CC33">未知</font>';
				        }
			        }},
			        {field:'subscribeDate',title:'约诊时间',width:"18%",align:'center',formatter:function(val,row){
			        	return CU.DateTimeFormatter(val,1);
			        }},
			        {field:'project',title:'预约项目',width:"15%",align:'left'},
			        {field:'professionName',title:'客户职业',width:"10%",align:'center'},
			        {field:'createUser',title:'预约人',width:"9%",align:'center'},
			        {field:'createTime',title:'预约时间',width:"18%",align:'center',formatter:function(val,row){
			        	return CU.DateTimeFormatter(val,1);
			        }}
			    ]],
				onDblClickRow:function(index,row){
				},
				onSelect:function(index,row){
				},
				onLoadSuccess:function(data){
				}
			});
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
