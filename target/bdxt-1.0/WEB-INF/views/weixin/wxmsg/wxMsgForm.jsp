<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文本回复管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function() {
			var rules = {
					content:{required:true,rangelength:[1,600]},
			  }
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				 rules:rules,
				 messages:{
					 content:{
		                    required:"必填",
		                    rangelength: $.format("消息内容最小长度:{0}, 最大长度:{1}。")
		                },
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
		<li><a href="${ctx}/weixin/wxMsg/?accountid=${accountid}">文本回复列表</a></li>
		<li class="active"><a href="${ctx}/weixin/wxMsg/form?id=${wxMsg.id}">文本回复<shiro:hasPermission name="weixin:wxMsg:edit">${not empty wxMsg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:wxMsg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxMsg" action="${ctx}/weixin/wxMsg/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">消息内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="1000" class="input-xxlarge "/>
			</div>
			<div class="controls">
				<input type="hidden" name="accountid" value="${accountid}"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="weixin:wxMsg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/weixin/wxMsg/?accountid=${accountid}'"/>
		</div>
	</form:form>
</body>
</html>