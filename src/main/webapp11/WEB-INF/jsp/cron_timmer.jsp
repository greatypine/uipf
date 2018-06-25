<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="includ.jsp"%>
<script type="text/javascript" src="${path}/static/easyUI/datagrid-detailview.js"></script>
</head>
<body>
	<table id="cron_table" class="easyui-datagrid" style="width: 80%; height: 100%;"
		data-options="singleSelect: true,rownumbers:true,fit:true,pageSize:20,toolbar:'#tb_cron_option'" pagination="true">
	</table>
	<div id="tb_cron_option" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="schedule.cronAdd()">添加</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="schedule.cronedit()">编辑</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="schedule.cronRemove()">删除</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="schedule.manualExecute()">手动执行</a> 
		</div>
		<div id="cron_bar">
			定时模式: <input id="crons" class="easyui-text"
				style="width: 120px; height: 22px;"> 定时名称: <input id="names"
				class="easyui-text" style="width: 120px; height: 22px;">
			定时分组: <input id="groups" class="easyui-text"
				style="width: 120px; height: 22px;"> <a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" onclick="cron_search()">查询</a>
		</div>
	</div>
	<div id="crondlg" class="easyui-dialog" style="width: 400px; overflow: hidden;" closed="true" buttons="#cron_add_dlg-buttons">
		<form id="crondlg-fm" method="post" novalidate style="margin: 0; padding: 20px 50px">
			<div style="margin-bottom: 20px; font-size: 14px; border-bottom: 1px solid #ccc">定时器配置信息</div>
			<div style="margin-bottom: 10px">
				定时模式：<input id="cron1" name="cron" class="easyui-textbox" required="true" label="" style="width: 75%;">
			</div>
			<div style="margin-bottom: 10px">
				定时名称：<input id="name" name="name" class="easyui-textbox" required="true" style="width: 75%;">
			</div>
			<div style="margin-bottom: 10px">
				定时分组：<input id="groupname" name="groupname" class="easyui-textbox" required="true" label="" style="width: 75%;">
			</div>
			<div style="margin-bottom: 10px">
				是否开启：<input name="status" id="status" class="easyui-combobox" label="" style="width: 75%;" data-options="valueField:'value',textField:'text',data:[{value:0,text:'关闭'},{value:1,text:'开启'}]">
			</div>
			<div style="margin-bottom: 0px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述：<input id="remark" name="remark" data-options="multiline:true" class="easyui-textbox" label="" style="width: 75%; height: 150px">
			</div>
			<input id="id" name="id" value="" type="hidden" />
		</form>
	</div>
	<div id="cron_add_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="schedule.saveCronOrUpdate()" style="width: 90px">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#crondlg').dialog('close')" style="width: 90px">关闭</a>
	</div>
	<div id="jobadddlg" class="easyui-dialog" style="width: 400px; overflow: hidden; min-height:50px;" closed="true" buttons="#job_add_dlg-buttons">
			<div class="easyui-panel" style="padding: 5px">
				<ul id="chooseworkflow" class="easyui-tree" data-options=""></ul>
			</div>
	</div>
	<div id="job_add_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="schedule.saveWorkflow()" style="width: 90px">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#jobadddlg').dialog('close')" style="width: 90px">关闭</a>
	</div>
	<div id="workflowdlg" class="easyui-dialog" style="width: 400px; overflow: hidden;" closed="true" buttons="#job_update_dlg-buttons">
		<form id="workflowdlg-fm" method="post" novalidate style="margin: 0; padding: 20px 50px">
			<div style="margin-bottom: 20px; font-size: 14px; border-bottom: 1px solid #ccc">定时工作流信息</div>
			<div style="margin-bottom: 10px">
				所属定时器：<input id="schedulename" name="schedulename" class="easyui-textbox" disabled="true" label="" style="width: 75%;">
			</div>
			<div style="margin-bottom: 10px">
				工作流名称：<input id="name" name="name" class="easyui-textbox" required="true" style="width: 75%;">
			</div>
			<div style="margin-bottom: 10px">
				是否开启：<input name="status" id="status" class="easyui-combobox" label="" style="width: 75%;" data-options="valueField:'value',textField:'text',data:[{value:0,text:'关闭'},{value:1,text:'开启'}]">
			</div>
			<div style="margin-bottom: 0px"> 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述：<input id="remark" name="remark" data-options="multiline:true" class="easyui-textbox" label="" style="width: 75%; height: 150px">
			</div>
			<input id="schedule_id" name="scheduleId" value="" type="hidden" />
			<input id="workflowId" name="workflowId" value="" type="hidden" />
		</form>
	</div>
	<div id="job_update_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="schedule.updateWorkflow()" style="width: 90px">更新</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#workflowdlg').dialog('close')" style="width: 90px">关闭</a>
	</div>
</body>
<script type="text/javascript" src="${path}/static/js/schedule/schedule.js"></script>
</html>