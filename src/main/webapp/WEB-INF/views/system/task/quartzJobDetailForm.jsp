<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/system/task/quartzJobDetail/">定时任务列表</a></li>
		<li class="active"><a href="${ctx}/system/task/quartzJobDetail/form?id=${quartzJobDetail.id}">定时任务<shiro:hasPermission name="system:task:quartzJobDetail:edit">${not empty quartzJobDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="system:task:quartzJobDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="quartzJobDetail" action="${ctx}/system/task/quartzJobDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="schedName"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">组名：</label>
			<div class="controls">
				<form:input path="jobGroup" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">任务名：</label>
			<div class="controls">
				<form:input path="jobName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Job类：</label>
			<div class="controls">
				<form:input path="jobClassName" htmlEscape="false" maxlength="250" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">cron表达式：</label>
			<div class="controls">
				<form:input path="cron" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="250" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否持久化：</label>
			<div class="controls">
				<form:checkbox path="isDurable" value="1"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否非并发：</label>
			<div class="controls">
				<form:checkbox path="isNonconcurrent" value="1"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否更新数据：</label>
			<div class="controls">
				<form:checkbox path="isUpdateData" value="1"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">请求恢复：</label>
			<div class="controls">
				<form:checkbox path="requestsRecovery" value="1"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="system:task:quartzJobDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/system/task/quartzJobDetail'"/>
		</div>
	</form:form>
</body>
</html>