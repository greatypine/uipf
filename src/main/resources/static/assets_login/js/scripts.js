var flag = false;

jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
    	$(".errorinfo").remove();
    	$(".error").fadeOut('fast');
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        username = "shiyq";
        password = "12";
        $(this).find('.username').val(username);
        $(this).find('.password').val(password);
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
    });

    $('.page-container form .username, .page-container form .password').keyup(function(){
    	if(flag){
    		$(".errorinfo").remove();
    		$(".error").fadeOut('fast');
    		$(this).parent().find('.error').fadeOut('fast');
    	}
    });
    
    /**校验用户名是否存在*/
    $(".username").blur(function(){
    	var username = $(this).val();
    	if(username == null || username == ""){
    		createErrorInfo("用户名不能为空!");
    		return;
    	}
    	var url = "loginCheck";
    	var paramData = {"username":username};
    	$.ajax({
    		cache : false,
			type : 'POST',
			dataType : "json",
			data : paramData,
			url : url,
			error : function(data) {
				createErrorInfo('服务器连接超时请重试!');
			},
			success : function(data) {
				flag = data.flag;
				if(!flag){//用户名不存在 禁用提交按钮
					createErrorInfo("用户名不存在!");
					$(":submit").attr("disabled", true); 
				}else{
					$(".errorinfo").remove();
			    	$(".error").fadeOut('fast');
					$(":submit").attr("disabled", false);
				}
			}
    	});
    });

});
