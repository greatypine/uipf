    var path = '<%=basePath%>';  
    var userId = 'lys';  
    if(userId==-1){  
        window.location.href="<%=basePath2%>";  
    }  
    var jspCode = userId+"_AAA";  
    var websocket;  
    if ('WebSocket' in window) {  
        websocket = new WebSocket("ws://" + path + "wsMy?jspCode=" + jspCode);  
    } else if ('MozWebSocket' in window) {  
        websocket = new MozWebSocket("ws://" + path + "wsMy?jspCode=" + jspCode);  
    } else {  
        websocket = new SockJS("http://" + path + "wsMy/sockjs?jspCode=" + jspCode);  
    }  
    websocket.onopen = function(event) {  
        console.log("WebSocket:已连接");  
        console.log(event);  
    };  
    websocket.onmessage = function(event) {  
        var data = JSON.parse(event.data);  
        console.log("WebSocket:收到一条消息-norm", data);  
        alert("WebSocket:收到一条消息");  
    };  
    websocket.onerror = function(event) {  
        console.log("WebSocket:发生错误 ");  
        console.log(event);  
    };  
    websocket.onclose = function(event) {  
        console.log("WebSocket:已关闭");  
        console.log(event);  
    }  
