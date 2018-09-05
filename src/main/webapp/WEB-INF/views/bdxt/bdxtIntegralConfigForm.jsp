<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>积分配置管理管理</title>
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
		<li><a href="${ctx}/bdxt/bdxtIntegralConfig/">积分配置管理列表</a></li>
		<li class="active"><a href="${ctx}/bdxt/bdxtIntegralConfig/form?id=${bdxtIntegralConfig.id}">积分配置管理<shiro:hasPermission name="bdxt:bdxtIntegralConfig:edit">${not empty bdxtIntegralConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bdxt:bdxtIntegralConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bdxtIntegralConfig" action="${ctx}/bdxt/bdxtIntegralConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
         <label class="control-label">操作类型：</label>
			<div class="controls">
				<form:select path="operateType" class="input-medium">
				<form:option value="1">签到</form:option>
				<form:option value="2">投稿审核通过</form:option>
				<form:option value="3">待定</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">积分金额：</label>
			<div class="controls">
				<form:input path="operateCapital" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bdxt:bdxtIntegralConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/bdxt/bdxtIntegralConfig'"/>
		</div>
	</form:form>
</body>
</html>