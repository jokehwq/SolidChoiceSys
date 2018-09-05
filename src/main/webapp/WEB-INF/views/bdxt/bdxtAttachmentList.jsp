<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户登录信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtAttachment/">用户登录信息列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtAttachment:edit"><li><a href="${ctx}/bdxt/bdxtAttachment/form">用户登录信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtAttachment" action="${ctx}/bdxt/bdxtAttachment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>附件名称：</label>
				<form:input path="attachName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>附件名称</th>
				<th>附件url</th>
				<shiro:hasPermission name="bdxt:bdxtAttachment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtAttachment">
			<tr>
				<td><a href="${ctx}/bdxt/bdxtAttachment/form?id=${bdxtAttachment.id}">
					${bdxtAttachment.attachName}
				</a></td>
				<td>
					${bdxtAttachment.attachUrl}
				</td>
				<shiro:hasPermission name="bdxt:bdxtAttachment:edit"><td>
    				<a href="${ctx}/bdxt/bdxtAttachment/form?id=${bdxtAttachment.id}">修改</a>
					<a href="${ctx}/bdxt/bdxtAttachment/delete?id=${bdxtAttachment.id}" onclick="return confirmx('确认要删除该用户登录信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>