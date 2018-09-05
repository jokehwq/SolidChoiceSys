<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>附件管理</title>
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
		<li><a href="${ctx}/system/file/sysFile/">附件列表</a></li>
		<li class="active"><a href="${ctx}/system/file/sysFile/form?id=${sysFile.id}">附件<shiro:hasPermission name="system:file:sysFile:edit">${not empty sysFile.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="system:file:sysFile:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysFile" action="${ctx}/system/file/sysFile/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模块：</label>
			<div class="controls">
				<form:input path="module" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子模块：</label>
			<div class="controls">
				<form:input path="submodule" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件：</label>
			<div class="controls">
				<input class="input-xlarge" type="file" name="file"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="system:file:sysFile:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/system/file/sysFile'"/>
		</div>
	</form:form>
</body>
</html>