<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全球地区管理</title>
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
		<li><a href="${ctx}/bdxt/bdxtDistrict/">全球地区列表</a></li>
		<li class="active">
			<a href="${ctx}/bdxt/bdxtDistrict/form?id=${bdxtDistrict.id}">全球地区
			<shiro:hasPermission name="bdxt:bdxtDistrict:edit">${not empty bdxtDistrict.id?'修改':'添加'}
			</shiro:hasPermission>
			<shiro:lacksPermission name="bdxt:bdxtDistrict:edit">查看</shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bdxtDistrict" action="${ctx}/bdxt/bdxtDistrict/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">地区名称：</label>
			<div class="controls">
				<form:input path="districtname" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">层级：</label>
			<div class="controls">
				<form:input path="level" htmlEscape="false" maxlength="2" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域code：</label>
			<div class="controls">
				<form:input path="districtcode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">父级菜单：</label>
			<div class="controls">
				<form:select path="parent">
					<form:option  value="" >请选择上级菜单</form:option>
					<form:options items="${fns:getDistrictList('3000336','中国')}" itemLabel="districtname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bdxt:bdxtDistrict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/bdxt/bdxtDistrict'"/>
		</div>
	</form:form>
</body>
</html>