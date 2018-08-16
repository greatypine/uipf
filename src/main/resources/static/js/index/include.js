var CU = new CommonUtils();
var cu = CU;
var amq = org.activemq.Amq;
amq.init({ uri: content+'/amq', logging: false, timeout: 45, clientId:(new Date()).getTime().toString() });
$(function(){
	var lock_flag = $.cookie('lock_flag');
	if(lock_flag==true || lock_flag=='true'){
		$('#dlg-lock').dialog('open').dialog('center');
	    $('#lock_form').form('clear');
	}else{
		$('#dlg-lock').dialog('close');
	}
	cu.showtime("currenttime");
});
$(document).keyup(function(event){
    switch(event.keyCode){
        case 27:
            {
                //检测按键：ESC,锁住网页
                $.cookie('lock_flag', true);
                $('#dlg-lock').dialog('open').dialog('center');
                $('#lock_form').form('clear');                
            }
            break;  
    }
});
