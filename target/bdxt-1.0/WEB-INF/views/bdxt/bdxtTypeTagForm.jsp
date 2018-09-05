<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户标签信息管理</title>
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
		<li><a href="${ctx}/bdxt/bdxtTypeTag/">用户标签信息列表</a></li>
		<li class="active"><a href="${ctx}/bdxt/bdxtTypeTag/form?id=${bdxtTypeTag.id}">用户标签信息<shiro:hasPermission name="bdxt:bdxtTypeTag:edit">${not empty bdxtTypeTag.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bdxt:bdxtTypeTag:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bdxtTypeTag" action="${ctx}/bdxt/bdxtTypeTag/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="bxdtType" class="input-xlarge ">
					<form:option value="" label="">请选择</form:option>
					<form:options items="${fns:queryDictListByCondition('USER_CODE','个人类型')}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签名称：</label>
			<div class="controls">
				<form:input path="tagName" htmlEscape="false" maxlength="2048" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="2" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-medium">
					<form:option value="1">启用</form:option>
					<form:option value="2">停用</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否推荐：</label>
			<div class="controls">
				<form:select path="isRecommended" class="input-medium">
					<form:option value="1">是</form:option>
					<form:option value="2">否</form:option>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bdxt:bdxtTypeTag:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/bdxt/bdxtTypeTag'"/>
		</div>
	</form:form>
</body>
</html>