<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="${path }/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${path }/static/css/font-awesome-4.6.3/css/font-awesome.min.css">
<link id="easyuiTheme" rel="stylesheet" href="${path}/static/easyUI/themes/bootstrap/easyui.css">
<link rel="stylesheet" href="${path }/static/easyUI/themes/icon.css">
<%-- <link rel="stylesheet" href="${path}/static/easyUI/uimaker/icon.css"> --%>
<script src="${path}/static/Jquery/jquery.min.js"></script>
<script src="${path }/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${path}/static/jquery-cookie/jquery.cookie.js"></script>
<script src="${path}/static/easyUI/jquery.easyui.min.js"></script>
<script src="${path}/static/easyUI/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
String.prototype.replaceAll = function(s1,s2){ 
	return this.replace(new RegExp(s1,"gm"),s2); 
}
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
(function($){ 
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
})(jQuery);
</script>
<%
	String basePath = "";
	basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>
<%
	request.setCharacterEncoding("UTF-8");
	out.print("<script type='text/javascript'>");
	out.print("var basePath = '" + request.getContextPath() + "';"); //项目入口
	StringBuffer sb = new StringBuffer();
	sb.append("var systemuser = " + request.getSession().getAttribute("systemuser") + ";");//用户
	sb.append("$(function() {");//页面加载完成后执行
	sb.append("		if(typeof init!='undefined'&&typeof init == \"function\"){init();}"); //执行如果init方法存在且执行
	sb.append("});");
	out.print(sb.toString());
	out.print("</script>");
%>