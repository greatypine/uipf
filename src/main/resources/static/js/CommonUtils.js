function CommonUtils(){
	this.DATE_FULL_FORMAT = "yyyy-MM-dd hh:mm:ss";
	this.DATE_DAY_FORMAT = "yyyy-MM-dd";
	this.DATE_MONTH_FORMAT = "yyyy-MM";
	
	/**
	 * 计算两个时间之间的天数 ：取正整
	 */
	this.DateDiffABS = function(sDate1,sDate2){
		 var aDate,oDate1,oDate2,iDays;
		 if(typeof sDate1 == 'string' && sDate1!=""){
			aDate = sDate1.split("-");
			oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);//转换为12-18-2006格式  
		 }else{
			 oDate1  =  sDate1;
		 }
	     if(typeof sDate2 == 'string' && sDate2!=""){
	    	 aDate = sDate2.split("-") ; 
	    	 oDate2 = new Date(aDate[1] + '-' + aDate[2]  + '-' + aDate[0]);
	     }else if(typeof sDate2 == 'object'){
	    	 oDate2 = sDate2;
	     }
	     iDays  =  parseInt(Math.abs(oDate1 - oDate2)  /  1000  /  60  /  60  /24);//把相差的毫秒数转换为天数  
	     return  iDays;
	};
	/**
	 * 计算两个时间之间的天数
	 */
	this.DateDiff = function(sDate1,sDate2){
		var aDate,oDate1,oDate2,iDays;
		 if(typeof sDate1 == 'string' && sDate1!=""){
			aDate = sDate1.split("-");
			oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);//转换为12-18-2006格式  
		 }else{
			oDate1  =  sDate1;
		 }
	     if(typeof sDate2 == 'string' && sDate2!=""){
	    	 aDate = sDate2.split("-") ; 
	    	 oDate2 = new Date(aDate[1] + '-' + aDate[2]  + '-' + aDate[0]);
	     }else if(typeof sDate2 == 'object'){
	    	 oDate2 = sDate2;
	     }
	     iDays = parseInt((oDate1-oDate2)/1000/60/60/24);//把相差的毫秒数转换为天数  
	     return iDays;
	};
	this.formatStrDateToDate = function(date){
		if(typeof date == 'string' && date!=""){
			var aDate = date.split("-");
	    	return new Date(aDate[1] + '-' + aDate[2]  + '-' + aDate[0]);
		}else{
			return null;
		}
	};
	/**
	 * 返回yyyy-mm-dd hh:mm:ss格式
	 */
	this.getCurrentYear = function(){
		var t = new Date();
		var y = t.getFullYear();
        return y;
	};
	/**
	 * 返回yyyy-mm-dd hh:mm:ss格式
	 */
	this.getCurrentMonth = function(){
		var t = new Date();
        var m = t.getMonth() + 1;
        return (m < 10 ? '0' + m : m);
	};
	/**
	 * 返回yyyy-mm-dd hh:mm:ss格式
	 */
	this.getCurrentDateTime = function(){
		var t = new Date();
		var y = t.getFullYear();
		var m = t.getMonth() + 1;
        var d = t.getDate();
        var h = t.getHours();
        var i = t.getMinutes();
        var s = t.getSeconds();
        return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
	};
	/**
	 * 返回yyyy-mm-dd hh:mm:ss格式
	 */
	this.getCurrentDate = function(){
		var t = new Date();
		var y = t.getFullYear();
        var m = t.getMonth() + 1;
        var d = t.getDate();
        var h = t.getHours();
        var i = t.getMinutes();
        var s = t.getSeconds();
        return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
	};
	/**
	 * 增加days天数
	 */
	this.date_add = function(date,days){
		if(days==null || days==undefined || days =="") return null;
		var d = null;
		if(date == null) d = new Date();
		else d=new Date(date);
		d.setDate(d.getDate()+days);
		var m=d.getMonth()+1;
		return d.getFullYear()+'-'+m+'-'+d.getDate();
     };
	/**
	 * @param value:时间戳
	 * @param type : type为1时带时间yyyy-MM-dd hh:mm:ss,type为0时不带时间yyyy-MM-dd
	 * 格式化日期时间
	 */
	this.DateTimeFormatter = function (value,type) {
	    if (value == undefined || value=="" || value==null) {
	        return null;
	    }
        var t, y, m, d, h, i, s;
        t = value?new Date(value) : new Date();
        y = t.getFullYear();
        m = t.getMonth() + 1;
        d = t.getDate();
        h = t.getHours();
        i = t.getMinutes();
        s = t.getSeconds();
        // 可根据需要在这里定义时间格式 
        if(type==1){
        	return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
        }else{
        	return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
        }
	}
	
	/**
	 *根据用户角色标记判断用户是否有角色
	 */
	this.hasRoles = function(flag){
		if(user!=null && user!=""){
			if(typeof user == "string"){
				user = JSON.parse(user);
			}
			var roles = user.role;
			for (var i = 0; i < roles.length; i++) {
				var flags = flag.split(",");
				for ( var role in flags) {
					if(roles[i].roleSign==flags[role]) return true;
				}
			}
		}
		return false;
	};
	/**
	 * 根据权限标记判断当前用户是否有权限
	 */
	this.hasPermission = function(flag){
		if(user!=null && user!=""){
			if(typeof user == "string"){
				user = JSON.parse(user);
			}
			var promissions = user.promissions;
			for (var i = 0; i < promissions.length; i++) {
				if(promissions[i].permissionSign.indexOf(flag)!=-1) return true;
			}
		}
		return false;
	};
	this.initClearCombobox = function(id){
		var data = $('#'+id).combobox('getData');
		$('#'+id).combobox('clear');//清空选中项 
		$('#'+id).combobox('loadData',data);
	};
	this.clearSelected = function(id){
		$('#'+id).datagrid("unselectAll");
		$('#'+id).datagrid("clearSelections");
	};
	
	this.disableForm = function(formId,isDisabled) {
	    var attr="disable";
	    if(!isDisabled){
	       attr="enable";
	    }
	    $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);
	    $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);
	    $("form[id='"+formId+"'] select").attr("disabled",isDisabled);
	    $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);
	    $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);
	    if(isDisabled)$("#"+formId+" .textbox-addon-right").hide();
	    else $("#"+formId+" .textbox-addon-right").show();
	    //禁用jquery easyui中的下拉选（使用input生成的combox）
	  
	    $("#" + formId + " input[class='combobox-f combo-f']").each(function () {
	        if (this.id) {alert("input"+this.id);
	            $("#" + this.id).combobox(attr);
	        }
	    });
	      
	    //禁用jquery easyui中的下拉选（使用select生成的combox）  
	    $("#" + formId + " select[class='combobox-f combo-f']").each(function () {
	        if (this.id) {
	        alert(this.id);  
	            $("#" + this.id).combobox(attr);
	        }  
	    });  
	      
	    //禁用jquery easyui中的日期组件dataBox  
	    $("#" + formId + " input[class='datebox-f combo-f']").each(function () {  
	        if (this.id) {  
	        alert(this.id)  
	            $("#" + this.id).datebox(attr);  
	        }  
	    });  
	};
	this.bottomRight = function(mess){
		$.messager.show({
			title:'信息提示！',
			msg:mess,
			width:350,
			height:100,
			timeout:10000,
			showType:'show'
		});
		cu.warnVoice();
	};
	this.sendMessage = function(amq,topic,msg){
		amq.sendMessage(topic,"<message>"+msg+"</message>");
	};
	this.warnVoice = function(){
		$('#newMessageDIV').html('<audio autoplay="autoplay"><source src="'+content+'/static/voice/warn.wav" type="audio/wav"/><source src="'+content+'/static/voice/warn.wav" type="audio/mpeg"/></audio>');
	};
}
$.fn.serializeJson=function(){
    var serializeObj={};
    var array=this.serializeArray();
    var str=this.serialize();
    $(array).each(function(){
        if(serializeObj[this.name]){
            if($.isArray(serializeObj[this.name])){
                serializeObj[this.name].push(this.value);
            }else{
                serializeObj[this.name]=[serializeObj[this.name],this.value];
            }
        }else{
            serializeObj[this.name]=this.value;
        }
    });
    return serializeObj;
};
/**
 * From扩展
 * getData 获取数据接口
 * 
 * @param {Object} jq
 * @param {Object} params 设置为true的话，会把string型"true"和"false"字符串值转化为boolean型。
 */
$.extend($.fn.form.methods, {
    getData: function(jq, params){
        var formArray = jq.serializeArray();
        var oRet = {};
        for (var i in formArray) {
            if (typeof(oRet[formArray[i].name]) == 'undefined') {
                if (params) {
                    oRet[formArray[i].name] = (formArray[i].value == "true" || formArray[i].value == "false") ? formArray[i].value == "true" : formArray[i].value;
                }
                else {
                    oRet[formArray[i].name] = formArray[i].value;
                }
            }
            else {
                if (params) {
                    oRet[formArray[i].name] = (formArray[i].value == "true" || formArray[i].value == "false") ? formArray[i].value == "true" : formArray[i].value;
                }
                else {
                    oRet[formArray[i].name] += "," + formArray[i].value;
                }
            }
        }
        return oRet;
    }
});