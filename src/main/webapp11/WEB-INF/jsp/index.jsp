<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>流程管理系统</title>
	<%@ include file="includ.jsp"%>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="width:100%;height: 100%;">
		<div data-options="region:'north',split:false" style="height: 5%;vertical-align: middle;">
				<span style="float: left;font-weight: bold;font-size: 16px;" >流程管理系统</span>
		</div>
		<div data-options="region:'west',split:false" title="系统菜单" style="width:15%;height: 95%;">
				<ul class="easyui-tree" id="myTree"> </ul>
		</div>
		<div data-options="region:'center',split:false" style="width:85%;height: 95%;">
			<div id="cnt-tabs" class="easyui-tabs" data-options="fit:true" style="width:100%;height: 100%;">
				<div title="主页" data-options="closable:false" style="width:100%;height: 100%;">
					<div style="width: 27%;margin: auto;">
						<h2><b>流程管理系统</b></h2>
					</div>
				</div>
			</div>
		</div>
<!-- 		<div data-options="region:'south',split:false" style="height:5%;"> -->
<!-- 			<h4 style="width: 300px;margin: auto;"><b style="font-size: 16px;">© 2016 中信国安. All rights reserved</b></h4> -->
<!-- 		</div> -->
	</div>
</body>
<script type="text/javascript" src="${path}/static/js/index/index.js"></script>
</html>