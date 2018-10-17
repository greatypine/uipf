var systemuser;
var systemuser_table;
$(function(){
	initData();
});
function initData(){
	systemuser = new systemuser();
	systemuser.initCompant();
	$(".roleids").combobox({
		url:content+'/role/queryMapBeanList'
	});
	$(".companylist").combobox({
		url:content+'/company/queryMapBeanList'
	});
	$("#sex").combobox({
		url:content+'/common/querySex?id='+user.user.companyid
	});
	$(".status").combobox({
		url:content+'/common/queryUserStatus?id='+user.user.companyid
	});
}
function systemuser(){
	this.initCompant = function(){
		systemuser_table = $('#systemuser_table').datagrid({
		        url: content+"/systemuser/queryList",
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        pageSize: 30,
		        pageList: [30, 40, 50],
		        loadMsg:'正在加载，请稍后...',
		        collapsible:false,
		        queryParams:{
				},
		        columns:[[
		        	 	{field:'id',title:'编号',hidden:true},
				        {field:'username',title:'用户名称',width:"7%",align:'center'},
				        {field:'nickname',title:'昵称',width:"7%",align:'center'},
				        {field:'phonenum',title:'电话号码',width:"7%",align:'center'},
				        {field:'companyName',title:'公司名称',width:"12%"},
				        {field:'email',title:'邮件地址',width:"10%"},
				        {field:'age',title:'年龄',width:"4%",align:'center'},
				        {field:'sexName',title:'性别',width:"4%",align:'center'},
				        {field:'address',title:'地址',width:"12%"},
				        {field:'comments',title:'就诊照片',width:"6%",align:'center',
				        	formatter:function(value, row, index){
			            	return '<a href="javascripte:void(0)" onClick="systemuser.uploadImg('+index+')" class="imageslinkbtn" data-options="plain:true">上传</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javascripte:void(0)" onClick="systemuser.viewImages('+index+')" class="imageslinkbtn" data-options="plain:true">预览</a>';
			            }},
				        {field:'statusName',title:'状态',width:"5%",align:'center'},
				        {field:'createTime',title:'创建时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }},
				        {field:'updateTime',title:'更新时间',width:"10%",align:'center',formatter:function(val,row){
				        	return CU.DateTimeFormatter(val,1);
				        }}
		        ]],
				onDblClickRow:function(index,row){
					$("#systemuserdlg").dialog("open").dialog("center").dialog("setTitle","更新用户信息");
					row.password = "";
					cu.initClearCombobox("roleids");
					cu.initClearCombobox("companyid");
					cu.initClearCombobox("status");
					cu.initClearCombobox("sex");
					$("#systemuserdlg-fm").form("clear").form("load",row);
					$("#username").textbox({"editable":false});
					$("#password").textbox({"editable":false});
					
				},
				onSelect:function(index,row){
					var userid = user.user.id;
					if(userid == row.id){
						$("#pwdbtn").linkbutton('enable');
					}else{
						$("#pwdbtn").linkbutton('disable');
					}
				},
				onLoadSuccess:function(){
				} 
			});
	};
	this.query = function(){
		var params = {};
		var companyid = cu.hasRoles("sadmin,q_area_shopManager,generalManager,h_option")?$("#querycompanyid").combobox("getValue"):null;
		var name = $("#queryname").textbox("getValue");
		var roleid = ($("#queryroleid").combobox("getValue")<0)?null:$("#queryroleid").combobox("getValue");
		var status = ($("#querystatus").combobox('getValue')<0)?null:$("#querystatus").combobox("getValue");
		var createTime = ($("#querycreateTime").datebox('getValue')=="")?null:$("#querycreateTime").datebox('getValue');
		var endTime = ($("#queryendTime").datebox('getValue')=="")?null:$("#queryendTime").datebox('getValue');
		if(name!="") {params.name = name;}
		params.roleids = roleid;
		params.companyid = companyid;
		params.status = status;
		if(createTime!="" && createTime!=null) {params.createTime = createTime;}
		if(endTime!="" && endTime!=null) {params.endTime = endTime;}
		systemuser_table.datagrid("load",params);
	};
	this.getParams = function(){
		return $("systemuserform").serializeJson();
	};
	this.clearForm = function(){
		$('#systemuserform').form('clear');
	};
	this.add = function(){
		$("#systemuserdlg").dialog("open").dialog("center").dialog("setTitle","添加用户窗口");
		$("#systemuserdlg-fm").form("clear");
		cu.initClearCombobox("roleids");
		cu.initClearCombobox("companyid");
		cu.initClearCombobox("status");
		cu.initClearCombobox("sex");
		$("#username").textbox({"editable":true});
	};
	this.complate = function(){
		$.messager.progress();
		$('#systemuserdlg-fm').form('submit',{
			url:content+"/systemuser/saveOrUpdate",
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success: function(result){
				$.messager.progress('close');
				var data = eval('(' + result + ')');
				if(data.status){
					$('#systemuserdlg-fm').form('clear');
					$.messager.alert('提示','操作成功');
					$('#systemuserdlg').dialog('close');
					$('#systemuser_table').datagrid('reload');
				}else{
					$.messager.alert('提示',data.mess,'warning');
				}
			},error:function(XMLHttpRequest,textStatus,errorThrown){
				$.messager.progress('close');
                $.messager.alert('提示',"操作失败！","error");
            }
		});
	};
	this.onDateDiffSelect = function(date){
		var day = CU.DateDiff(date,new Date());
		$("#diffDay").textbox("setValue",day);
	};
	this.remove = function(){
		var row=$('#systemuser_table').datagrid('getSelected');
		if(row){
			$.messager.confirm('提示信息', '你确认要删除此项配置吗?', function(r){
				if (r){
					$.messager.progress();
					$.ajax({
						type:"POST",
						data :{id:row.id},
						url : content+"/systemuser/delete",
						error : function(data) {
							$.messager.alert('提示信息','服务器连接超时请重试!','error'); 
							return false;
						},
						success : function(data) {
							$.messager.progress('close');
							if(data){//成功
								$.messager.alert('提示信息','删除成功!');
								$('#systemuser_table').datagrid('reload');
							}else{
								$.messager.alert('提示信息','删除失败!','warning');
							}
						},
						error:function(){
							$.messager.progress('close');
			                $.messager.alert('提示',"操作失败！","error");
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	
	this.uploadImg = function(index){
		$('#systemuser_table').datagrid("selectRow", index);
		$("#uploaddlg").dialog("open").dialog("center").dialog("setTitle","添加图片");
	};
	this.uploadPic = function(){
 		if($("#file").val()==""){
 			$.messager.alert("提示","请选择文件夹");
 			return false;
 		}
	}
	this.doupload =function(){
		var row=$('#systemuser_table').datagrid('getSelected');
		if(row){
			$.messager.progress();
			$("#img_username").val(row.username);
			$("#img_userid").val(row.id);
			$('#uploadform').form('submit',{
				url:content+"/common/userimg/upload",
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success: function(result){
					$.messager.progress('close');
					var data = eval('(' + result + ')');
					if(data.status || data.status=='true'){
						$('#uploadform').form('clear');
						$('#uploaddlg').dialog('close');
					}else{
						$.messager.alert('提示',data.mess,'warning');
					}
				},
				error:function(){
					$.messager.progress('close');
	                $.messager.alert('提示',"操作失败！","error");
				}
			});
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	this.viewImages = function(index){
		$('#systemuser_table').datagrid("selectRow", index);
		var row=$('#systemuser_table').datagrid('getSelected');
		if(row){
			$("#viewimages").attr("src",content+"/goUserImage?id="+row.id);
			$("#viewimagesdlg").dialog("open").dialog("center").dialog("setTitle","就诊图片预览");
		}else{
			$.messager.alert('提示','请先选中要操作的数据！','error');
		}
	};
	
	this.changePwd = function(){
		var row=$('#systemuser_table').datagrid('getSelected');
		if(row){
			$('#passwordform').form('clear');
			$("#passworddlg").dialog("open").dialog("center").dialog("setTitle","修改用户密码");
		}else{
			$.messager.alert('提示','请先选中要操作的用户！','error');
		}
	};
	
	this.doChangePwd = function(){
		var opd = $("#oldpwd").textbox("getValue");
		var npd = $("#newpwd").textbox("getValue");
		var snpd = $("#snewpwd").textbox("getValue");
		if(opd ==""){
			$.messager.alert('提示','旧密码不能为空！','warning');
			return ;
		}
		if(npd =="" || snpd=="" ){
			$.messager.alert('提示','用户名密码不能为空！','warning');
			return ;
		}
		if(npd!=snpd){
			$.messager.alert('提示','新密码和确认密码不相同！','warning');
			return ;
		}
		$.messager.confirm('提示信息', '修改成功后需重新登录！你确认要修改密码？', function(r){
			if (r){
				$.messager.progress();
				$('#passwordform').form('submit',{
					url:content+"/systemuser/changePassword",
					onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					},
					success: function(result){
						$.messager.progress('close');
						var data = eval('(' + result + ')');
						if(data.status || data.status=='true'){
							$('#passwordform').form('clear');
							$('#passworddlg').dialog('close');
							var c = cu.getParentWindow();
							c.location.href = content+"/logout";
						}else{
							$.messager.alert('提示',data.mess,'warning');
						}
					},error:function(XMLHttpRequest,textStatus,errorThrown){
						$.messager.progress('close');
		                $.messager.alert('提示',"操作失败！","error");
		            }
				});
			}
		});
		
	};
}
