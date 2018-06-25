$(function(){
	init();
});
/** 初始化 **/
function init() {
	initData();
	initComponent();
	initListener();
	initFace();
}

/** 初始化页面、内存等基本数据 **/
function initData() {
	var smokyBG = $('#wavybg-wrapper').waterpipe({
	    //Default values
	    gradientStart: '#eb10c3',
	    gradientEnd: '#edc811',
	    smokeOpacity: 0.1,
	    numCircles: 1,
	    maxMaxRad: 'auto',
	    minMaxRad: 'auto',
	    minRadFactor: 0,
	    iterations: 8,
	    drawsPerFrame: 10,
	    lineWidth: 2,
	    speed: 1,
	    bgColorInner: "#017CAB",
	    bgColorOuter: "#016AA9"
	});
	$(".logindiv").css({
		  "position":"absolute",
		  "margin-top":0,
		  "top":function(){
			  return ($("body").height()-$(this).height())/2;
		  },
		  "color":"#ffffff",
		  "box-sizing":"border-box",
		  "left":function(){
			  return ($(window).width()-$(this).width())/2;
		  }
		  });
	
	//判断之前是否有设置cookie，如果有，则设置【记住我】选择框
	if($.cookie('userName')!=undefined){
		$("#rememberMe").attr("checked", true);
	}else{
		$("#rememberMe").attr("checked", false);
	}
	
	//读取cookie
	if($('#rememberMe:checked').length>0){
		$('#username').val($.cookie('userName'));
		$('#password').val($.cookie('password'));
	}
	
	//监听【记住我】事件
	$("#rememberMe").click(function(){
		if($('#rememberMe:checked').length>0){//设置cookie
			$.cookie('username', $('#username').val());
			$.cookie('password', $('#password').val());
		}else{//清除cookie
			$.removeCookie('username');
			$.removeCookie('password');
		}
	});
}

/** 初始化组件 **/
function initComponent() {
}

/** 对组件设置监听 **/
function initListener() {
}

/** 初始化界面 **/
function initFace() {
}


/** 登录验证 **/
function validate(invalidate) {
	var username = $("#username").val();
	var password = $("#password").val();
	if(username=="" || username==null){
		$("#info").html("").html("用户名不能为空！").css("color","red");
	}
	if(password=="" || password==null){
		$("#info").html("").html("密码不能为空！").css("color","red");
	}
	$("#loginform").attr("action",content+"/login").submit();
	$.cookie('username', $('#username').val());
	$.cookie('password', $('#password').val());
}

function onLoginKeyUp() {
	if(event.keyCode == 13) document.getElementById("img_login_submit").click();
}

function login() {
}
