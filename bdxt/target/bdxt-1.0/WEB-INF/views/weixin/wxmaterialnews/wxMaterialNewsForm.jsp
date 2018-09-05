<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>素材管理</title>
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
		<li><a href="${ctx}/weixin/wxMaterialNews/?accountId=${accountId}">素材列表</a></li>
		<li class="active"><a href="${ctx}/weixin/wxMaterialNews/form?id=${wxMaterialNews.id}">素材<shiro:hasPermission name="weixin:wxMaterialNews:edit">${not empty wxMaterialNews.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:wxMaterialNews:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxMaterialNews" action="${ctx}/weixin/wxMaterialNews/saveImage" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<input type="hidden" name="accountId" value="${accountId}">
		<sys:message content="${message}"/>	
		<sys:message type="error" content="${errormessage}"/>	

		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:input path="digest" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">图片素材上传：</label>
			<div class="controls">
				<input type="file" name="image">
			</div>
		</div>		

		<div class="form-actions">
			<shiro:hasPermission name="weixin:wxMaterialNews:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/weixin/wxMaterialNews?accountId=${accountId}'"/>
		</div>
	</form:form>
</body>
</html>