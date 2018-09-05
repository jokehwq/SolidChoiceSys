<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户登录信息管理</title>
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
		<li><a href="${ctx}/bdxt/bdxtAttachment/">用户登录信息列表</a></li>
		<li class="active"><a href="${ctx}/bdxt/bdxtAttachment/form?id=${bdxtAttachment.id}">用户登录信息<shiro:hasPermission name="bdxt:bdxtAttachment:edit">${not empty bdxtAttachment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bdxt:bdxtAttachment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bdxtAttachment" action="${ctx}/bdxt/bdxtAttachment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户账号id：</label>
			<div class="controls">
				<form:input path="bdxtUserId" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签类型ids(bxdt_tag表关联id)：</label>
			<div class="controls">
				<form:input path="bdxtTag" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件名称：</label>
			<div class="controls">
				<form:input path="attachName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件类型 1-图片 2-视频：</label>
			<div class="controls">
				<form:input path="attachType" htmlEscape="false" maxlength="1" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件url：</label>
			<div class="controls">
				<form:input path="attachUrl" htmlEscape="false" maxlength="2048" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态 1-启用 2-停用：</label>
			<div class="controls">
				<form:select path="attachStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bdxtAttachment.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bdxt:bdxtAttachment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/bdxt/bdxtAttachment'"/>
		</div>
	</form:form>
</body>
</html>