layui.extend(
		{
			admin: content+'/static/js/admin'
		});
$(function(){
	$("#loginForm").attr("action",content+"/login");
	layui.use(['form','admin','jquery'], function(){
		var form = layui.form
		,admin = layui.admin;
		var $ = layui.jquery;
		// layer.msg('玩命卖萌中', function(){
		//   //关闭后的操作
		//   });
		//监听提交
		form.on('submit(login)', function(data){
//			console.log(data);
//            return false;
		});
		
//  form.on('submit(login)', function(data){
//	  layer.alert(JSON.stringify(data.field), {
//	      title: '最终的提交信息'
//	  });
//	  return false;
//  });
	});
});
