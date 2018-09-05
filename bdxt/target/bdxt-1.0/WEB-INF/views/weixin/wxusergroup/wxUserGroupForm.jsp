<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信用户分组管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules:{
					groupName:{required:true,rangelength:[1,15]},
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
		<li><a href="${ctx}/weixin/wxUser?accountId=${accountid}">用户管理列表</a></li>
		<li class="active"><a href="${ctx}/weixin/wxUserGroup/form?id=${wxUserGroup.id}&accountid=${accountid}">用户分组<shiro:hasPermission name="weixin:wxUserGroup:edit">${not empty wxUserGroup.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:wxUserGroup:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxUserGroup" action="${ctx}/weixin/wxUserGroup/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="accountid" id='accountid' value="${accountid}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">分组名字：</label>
			<div class="controls">
				<form:input path="groupName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<input type="hidden" name=groupId value="${wxUserGroup.groupId}">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="weixin:wxUserGroup:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/weixin/wxUserGroup?accountid=${accountid}'"/>
		</div>
	</form:form>
</body>
</html>