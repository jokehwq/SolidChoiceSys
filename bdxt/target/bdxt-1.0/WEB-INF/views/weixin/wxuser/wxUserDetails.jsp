<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理管理</title>
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
		<li><a href="${ctx}/weixin/wxUser?accountId=${wxUser.accountId}">用户管理列表</a></li>
		<li class="active"><a href="${ctx}/weixin/wxUser/selectUser?id=${wxUser.id}">用户详情信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxUser" action="${ctx}/basic/user/hrUser/updateUser" method="post" class="form-horizontal">
			<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<input type="hidden" name="accountid" id='accountid' value="${wxUser.accountId}"/>
			<label class="control-label">头像：</label>
			<div class="controls" >
				<img src="${wxUser.headimgurl}" alt="" height="200" width="200">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">昵称：</label>
			<div class="controls">
				${wxUser.nickname}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">openid：</label>
			<div class="controls">
					${wxUser.openid}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
					<c:if test="${wxUser.sex==3}">未知</c:if>
					<c:if test="${wxUser.sex==1}">男</c:if>
					<c:if test="${wxUser.sex==2}">女</c:if>
					<c:if test="${fns:getDictLabel(wxUser.sex, '', '')}"></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">省份：</label>
			<div class="controls">
					${wxUser.province}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">国家：</label>
			<div class="controls">
				${wxUser.country}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市：</label>
			<div class="controls">
					${wxUser.city}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否关注 ：</label>
			<div class="controls">
				<c:if test="${wxUser.subscribe==1}">已关注</c:if>
				<c:if test="${wxUser.subscribe==0}">未关注</c:if>
				<c:if test="${fns:getDictLabel(wxUser.subscribe, '', '')}"></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<fmt:formatDate value="${wxUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/weixin/wxUser?accountId=${wxUser.accountId}'"/>
		</div>
		</form:form>
</body>
</html>