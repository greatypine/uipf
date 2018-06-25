<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="includ.jsp"%>
</head>
<body>
<div class="easyui-layout" data-options="fit:true"  style="width:100%;height: 100%;">
	<div data-options="region:'west',split:true" style="width:15%;height: 100%;">
 		<ul id="chooseworkflow" class="easyui-tree" data-options=""></ul>
	</div>
	<div data-options="region:'center'" style="width:85%;">
		<iframe src="" op="${path}/job/queryJobList" id="workflowiframe" style="padding: 0px; width: 100%; height: 100%;" scrolling="no" frameborder="0"></iframe>
	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${path}/static/js/workflow/workflow.js"></script>
