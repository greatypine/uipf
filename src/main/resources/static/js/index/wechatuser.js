var wechatuser;
var cu;
var wechatuserdiv;
var wechatuser_table;
$(function(){
	initData();
});
function initData(){
	wechatuser = new WechatUser();
	cu = new CommonUtils();
	wechatuserdiv = $("#wechatuserdiv");
	wechatuserdiv.hide();
}
function WechatUser(){
	this.query = function(){
		wechatuser.complate();
	};
	this.getParams = function(){
		return $("wechatuserform").serializeJson();
	};
	this.clearForm = function(){
		$('#wechatuserform').form('clear');
	};
	this.clear = function(){
		wechatuser.clearForm();
		wechatuserdiv.fadeOut();
	};
	this.complate = function(){
		var phonenumb = $("#queryphonenumb").textbox('getValue');
		if(phonenumb=="" || phonenumb==null) {
			$.messager.alert('提示','请填写手机号码，手机号码不能为空！','warning');
			return ;
		}
		$.ajax({
			cache : false,
			type : 'POST',
			dataType : "json",
			data : {"phonenumb": phonenumb},
			async : false,
			url : content+"/wechat_user/queryWechatUserInfo",
			error : function(data) {
				createErrorInfo('服务器连接超时请重试!');
			},
			success : function(result) {
				if(result.status){
					var wcuser = result.user;
					for(var item in wcuser){
						var ele = $("#"+item);
						if(ele.length > 0){
							if(item=="userImgUrl") ele.attr("src",wcuser[item]);
							else if(item=="registerTime"){
								if(wcuser[item]!=null|| wcuser[item]!=""){
									ele.text(cu.DateTimeFormatter(wcuser[item],1));
								}
							}
							else if(item=="birthday"){
								if(wcuser[item]!=null && wcuser[item]!="" && wcuser[item]!="null"){
									var birthday = cu.DateTimeFormatter(wcuser[item],1);
									var age = cu.DateDiff(birthday,new Date());
									$("#age").text(age);
									ele.text(cu.DateTimeFormatter(wcuser[item],1));
								}
							}
							else ele.text(wcuser[item]);
						}
					}
					wechatuserdiv.fadeIn();
					$.messager.progress('close');
				}else{
					$.messager.alert('提示','操作失败','warning');
					$.messager.progress('close');
				}
			}  
		});
	}
}
